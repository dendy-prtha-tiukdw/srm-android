package id.ukdw.srmmobile.di.module;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import id.ukdw.srmmobile.BuildConfig;
import id.ukdw.srmmobile.data.remote.AuthApi;
import id.ukdw.srmmobile.data.remote.UserApi;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static id.ukdw.srmmobile.utils.AppConstants.BASE_URL;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.di.module
 * <p>
 * User: dendy
 * Date: 22/09/2020
 * Time: 14:43
 * <p>
 * Description : NetworkModule
 */
@Module
public class NetworkModule {

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(ArrayList<Interceptor> interceptors) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .followRedirects(false);

        interceptors.forEach(interceptor -> {
            clientBuilder.addInterceptor(interceptor);
        });

        return clientBuilder.build();
    }

    @Singleton
    @Provides
    ArrayList<Interceptor> provideInterceptors() {
        ArrayList<Interceptor> interceptors = new ArrayList<>();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.level(HttpLoggingInterceptor.Level.NONE);
        }
        interceptors.add(loggingInterceptor);
        return interceptors;
    }

    @Singleton
    @Provides
    AuthApi provideAuthApi(Retrofit retrofit) {
        return retrofit.create(AuthApi.class);
    }

    @Singleton
    @Provides
    UserApi provideUserApi(Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }
}
