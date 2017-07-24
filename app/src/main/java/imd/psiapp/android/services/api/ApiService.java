package imd.psiapp.android.services.api;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import imd.psiapp.android.models.PSI;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * service class for consume API call
 * Created by egiadtya on 7/21/17.
 */

public class ApiService {
    private static ApiService instance;
    private Retrofit client;
    private Endpoint endpoint;
    private static OkHttpClient okHttpClient;
    private static final String API_HOST = "https://api.data.gov.sg/v1/";

    /**
     * single instance for {@link ApiService} class
     *
     * @return ApiService
     */
    public static ApiService getInstance() {
        if (instance == null) {
            instance = new ApiService();
        }
        return instance;
    }

    private ApiService() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("api-key", "3kkVzCyHlj7mbPmwRhyYcK9RdGx4ny4h")
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        };
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(logInterceptor)
                    .addInterceptor(headerInterceptor)
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(2, TimeUnit.MINUTES)
                    .build();

        }
        client = new Retrofit.Builder()
                .baseUrl(API_HOST)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * method for get psi data from api, this method will be return psi data by today
     *
     * @param callback {@link ApiCallback} of api request
     * @see PSI
     */
    public void getPsi(ApiCallback<PSI> callback) {
        getPsi(null, null, callback);
    }

    /**
     * method for get psi data from api using date time parameter
     *
     * @param dateTime {@link String} of dateTime (format should be like this YYYY-MM-DD[T]HH:mm:ss (SGT) E.g. 2016-12-12T09:45:00)
     * @param callback {@link ApiCallback} of api request
     * @see PSI
     */
    public void getPsiByDateTime(String dateTime, ApiCallback<PSI> callback) {
        getPsi(dateTime, null, callback);
    }

    /**
     * method for get psi data from api using date parameter
     *
     * @param date     {@link String} of date (format should be like this YYYY-MM-DD)
     * @param callback {@link ApiCallback} of api request
     * @see PSI
     */
    public void getPsiByDate(String date, ApiCallback<PSI> callback) {
        getPsi(null, date, callback);
    }

    /**
     * method for get psi data from api
     *
     * @param dateTime {@link String} of dateTime (format should be like this YYYY-MM-DD[T]HH:mm:ss (SGT) E.g. 2016-12-12T09:45:00)
     * @param date     {@link String} of date (format should be like this YYYY-MM-DD)
     * @param callback {@link ApiCallback} of api request
     * @see PSI
     */
    private void getPsi(String dateTime, String date, final ApiCallback<PSI> callback) {
        getEndpoint().getPsi(dateTime, date).enqueue(new Callback<PSI>() {
            @Override
            public void onResponse(Call<PSI> call, retrofit2.Response<PSI> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    String errorMessage;
                    try {
                        JSONObject errorObj = new JSONObject(response.errorBody().string());
                        errorMessage = errorObj.optString("message");
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        errorMessage = e.getMessage();
                    }
                    callback.onFailed(errorMessage);

                }
            }

            @Override
            public void onFailure(Call<PSI> call, Throwable t) {
                callback.onFailed(t.getMessage());
            }
        });
    }


    /**
     * @return Endpoint of api
     */
    private Endpoint getEndpoint() {
        if (endpoint == null) {
            endpoint = client.create(Endpoint.class);
        }
        return endpoint;
    }
}
