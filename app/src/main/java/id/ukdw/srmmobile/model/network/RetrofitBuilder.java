package id.ukdw.srmmobile.model.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private static final String BASE_URL = "https://apps.dw.fti.ukdw.ac.id/srm/";

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
