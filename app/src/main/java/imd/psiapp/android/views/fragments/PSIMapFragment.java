package imd.psiapp.android.views.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import butterknife.ButterKnife;
import imd.psiapp.android.R;
import imd.psiapp.android.models.PSI;
import imd.psiapp.android.models.PSIReadings;
import imd.psiapp.android.models.RegionMetadata;
import imd.psiapp.android.services.api.ApiCallback;
import imd.psiapp.android.services.api.ApiService;
import imd.psiapp.android.views.activities.MainActivity;

/**
 * this class for showing psi on map view
 * Created by egiadtya on 7/25/17.
 */

public class PSIMapFragment extends Fragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMarkerClickListener {
    private GoogleMap googleMap;
    private PSIReadings psiReadings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_psi_map, container, false);
        ButterKnife.bind(this, view);
        ((MainActivity) getActivity()).setTitle("PSI Map");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initMapFragment();
    }

    /**
     * for initialize map fragment
     */
    private void initMapFragment() {
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_container);
        if (supportMapFragment == null) {
            supportMapFragment = SupportMapFragment.newInstance();
            getChildFragmentManager().beginTransaction().replace(R.id.map_container, supportMapFragment).commit();
        }
        supportMapFragment.getMapAsync(this);
    }

    /**
     * for create marker on google map, this method will be showing map marker from metadata region
     *
     * @param title    String of marker title
     * @param location LatLng on marker
     */
    private void createMarker(String title, LatLng location) {
        if (googleMap != null) {
            MarkerOptions marker = new MarkerOptions()
                    .position(location)
                    .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("ic_marker", 150, 150)))
                    .title(title);
            googleMap.addMarker(marker);
        }
    }

    /**
     * for resize map marker icon size
     *
     * @param iconName {@link String} of drawable name
     * @param width    int of expectation width
     * @param height   int of expectation height
     * @return Bitmap
     */
    public Bitmap resizeMapIcons(String iconName, int width, int height) {
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(iconName, "drawable", getActivity().getPackageName()));
        return Bitmap.createScaledBitmap(imageBitmap, width, height, false);
    }

    /**
     * for getting psi data from server
     */
    private void populateData() {
        ApiService.getInstance().getPsi(new ApiCallback<PSI>() {
            @Override
            public void onSuccess(final PSI data) {
                psiReadings = data.getPsiItems().get(0).getPsiReadings();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setRegionalMetaDataLocation(data.getRegionMetadata());
                    }
                });
            }

            @Override
            public void onFailed(String message) {
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * for set regional metadata location on map, this method will be draw marker on map based on regional location
     *
     * @param metaDataLocation {@link List} of {@link RegionMetadata}
     */
    private void setRegionalMetaDataLocation(List<RegionMetadata> metaDataLocation) {
        for (RegionMetadata regionMetadata : metaDataLocation) {
            createMarker(regionMetadata.getName(), regionMetadata.getLocation().getLatLng());
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(getActivity(), "Map connection suspended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getActivity(), connectionResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng singaporeLatLng = new LatLng(1.3659974, 103.8533953);
        LatLngBounds singaporeBound = new LatLngBounds(new LatLng(1.1637, 103.5921333), new LatLng(1.46145, 104.0828713));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(singaporeLatLng, 10.5F));
        googleMap.setLatLngBoundsForCameraTarget(singaporeBound);
        googleMap.setOnMarkerClickListener(this);
        populateData();
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        new Handler(getActivity().getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                PSIMapInfoFragment psiMapInfoFragment = new PSIMapInfoFragment();
                Bundle arguments = new Bundle();
                arguments.putString(PSIMapInfoFragment.REGION_NAME, marker.getTitle());
                arguments.putParcelable(PSIMapInfoFragment.PSI_READINGS, psiReadings);
                psiMapInfoFragment.setArguments(arguments);
                psiMapInfoFragment.show(getFragmentManager(), null);
            }
        }, 300);
        return false;
    }
}
