package id.ukdw.srmmobile.model.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private static final String BASE_URL = "isi disina url API";

    private static Retrofit getRetrofitInstance() {
        Gson gson = new GsonBuilder().setLenient().create();

        return new Retrofit.Builder().baseUrl( BASE_URL )
                .addConverterFactory( GsonConverterFactory.create( gson ) )
                .build();
    }
    public static SrmApi getAPIService(){
        return getRetrofitInstance().create(SrmApi.class);
    }
}
