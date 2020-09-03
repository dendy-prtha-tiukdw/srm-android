package id.ukdw.srmmobile.data.model.network;

import com.google.gson.GsonBuilder;

import id.ukdw.srmmobile.data.remote.AuthApi;
import id.ukdw.srmmobile.data.remote.SrmApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static id.ukdw.srmmobile.utils.AppConstants.BASE_URL;

public class RetrofitBuilder {

    private static Retrofit mRetrofitInstance = null;

    private static Retrofit getRetrofitInstance() {
        if (mRetrofitInstance == null) {
            return new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                    .build();
        }
        return mRetrofitInstance;
    }

    public static SrmApi getApiService() {
        return getRetrofitInstance().create(SrmApi.class);
    }

    public static AuthApi getAuthApi() {
        return getRetrofitInstance().create(AuthApi.class);
    }
}
