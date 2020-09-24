package id.ukdw.srmmobile.data.remote;

import android.util.Log;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import id.ukdw.srmmobile.BuildConfig;
import id.ukdw.srmmobile.data.local.prefs.PreferencesHelper;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static id.ukdw.srmmobile.utils.AppConstants.BASE_URL;
import static id.ukdw.srmmobile.utils.AppConstants.RETROFIT_CONNECTION_TIMEOUT;
import static id.ukdw.srmmobile.utils.AppConstants.RETROFIT_READ_TIMEOUT;

public class AppRetrofitBuilder implements ApiHelper {
    PreferencesHelper mPreferencesHelper;
    SchedulerProvider schedulerProvider;

    @Inject
    public AppRetrofitBuilder(PreferencesHelper mPreferencesHelper, SchedulerProvider schedulerProvider) {
        this.mPreferencesHelper = mPreferencesHelper;
        this.schedulerProvider = schedulerProvider;
    }

    private Retrofit getRetrofitInstance() {
        return getRetrofitInstance(new ArrayList<>());
    }

    private Retrofit getRetrofitInstance(ArrayList<Interceptor> interceptors) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.level(HttpLoggingInterceptor.Level.NONE);
        }
        interceptors.add(loggingInterceptor);
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .connectTimeout(RETROFIT_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(RETROFIT_READ_TIMEOUT, TimeUnit.SECONDS);

        interceptors.forEach(interceptor -> {
            clientBuilder.addInterceptor(interceptor);
        });

        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .setLenient()
                        .create()))
                .client(clientBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public AuthApi getAuthApi() {
        return getRetrofitInstance().create(AuthApi.class);
    }

    public UserApi getUserApi(String accessToken, String refreshToken) {
        ArrayList<Interceptor> interceptors = new ArrayList<>();
        AuthorizationInterceptor authorizationInterceptor = new AuthorizationInterceptor(getAuthApi(),
                mPreferencesHelper,
                schedulerProvider);
        interceptors.add(authorizationInterceptor);
        return getRetrofitInstance(interceptors).create(UserApi.class);
    }
}
