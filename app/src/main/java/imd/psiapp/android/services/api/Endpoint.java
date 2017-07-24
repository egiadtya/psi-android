package imd.psiapp.android.services.api;

import imd.psiapp.android.models.PSI;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * api endpoint collection
 * Created by egiadtya on 21/07/17.
 */

public interface Endpoint {

    @GET("environment/psi")
    Call<PSI> getPsi(@Query("date_time") String dateTime, @Query("date") String date);

}
