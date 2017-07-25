package imd.psiapp.android.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

/**
 * data model for region metadata such as name and location detail
 * Created by egiadtya on 7/24/17.
 */

public class RegionMetadata implements Parcelable {
    @SerializedName("name")
    private String name;
    @SerializedName("label_location")
    private Location location;

    public RegionMetadata() {
    }

    private RegionMetadata(Parcel in) {
        name = in.readString();
    }

    public static final Creator<RegionMetadata> CREATOR = new Creator<RegionMetadata>() {
        @Override
        public RegionMetadata createFromParcel(Parcel in) {
            return new RegionMetadata(in);
        }

        @Override
        public RegionMetadata[] newArray(int size) {
            return new RegionMetadata[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    /**
     * data model for region location
     */
    public static class Location implements Parcelable {
        private double latitude;
        private double longitude;
        private LatLng latLng;

        public Location() {
        }

        Location(Parcel in) {
            latitude = in.readDouble();
            longitude = in.readDouble();
        }

        public static final Creator<Location> CREATOR = new Creator<Location>() {
            @Override
            public Location createFromParcel(Parcel in) {
                return new Location(in);
            }

            @Override
            public Location[] newArray(int size) {
                return new Location[size];
            }
        };

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public LatLng getLatLng() {
            if (latLng == null) {
                latLng = new LatLng(latitude, longitude);
            }
            return latLng;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeDouble(latitude);
            dest.writeDouble(longitude);
        }
    }
}
