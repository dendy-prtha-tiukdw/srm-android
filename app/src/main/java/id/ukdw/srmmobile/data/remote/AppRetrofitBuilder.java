package id.ukdw.srmmobile.data.remote;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static id.ukdw.srmmobile.utils.AppConstants.BASE_URL;
import static id.ukdw.srmmobile.utils.AppConstants.RETROFIT_CONNECTION_TIMEOUT;
import static id.ukdw.srmmobile.utils.AppConstants.RETROFIT_READ_TIMEOUT;

public class AppRetrofitBuilder implements ApiHelper {

    @Inject
    public AppRetrofitBuilder() {

    }

    private Retrofit getRetrofitInstance() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .setLenient()
                        .create()))
                .client(new OkHttpClient.Builder()
                        .connectTimeout(RETROFIT_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                        .readTimeout(RETROFIT_READ_TIMEOUT, TimeUnit.SECONDS)
                        .addInterceptor(interceptor)
                        .build())
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create() )
                .build();
    }

    public AuthApi getAuthApi() {
        return getRetrofitInstance().create(AuthApi.class);
    }
}
