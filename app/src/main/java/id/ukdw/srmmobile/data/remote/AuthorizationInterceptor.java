package id.ukdw.srmmobile.data.remote;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import id.ukdw.srmmobile.data.local.prefs.PreferencesHelper;
import id.ukdw.srmmobile.data.model.api.request.RefreshAccessTokenRequest;
import id.ukdw.srmmobile.data.model.api.response.RefreshAccessTokenResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.data.remote
 * <p>
 * User: dendy
 * Date: 23/09/2020
 * Time: 8:39
 * <p>
 * Description :
 * the best approach to handle authentication is to use the new Authenticator API, designed specifically for this purpose.
 * OkHttp will automatically ask the Authenticator for credentials when a response is 401 Not Authorised
 * retrying last failed request with them.
 */
public class AuthorizationInterceptor implements Interceptor {
    private static final String TAG = AuthorizationInterceptor.class.getSimpleName();
    private AuthApi authApi;
    private PreferencesHelper mPreferencesHelper;
    private SchedulerProvider schedulerProvider;

    public AuthorizationInterceptor(AuthApi AuthApi, PreferencesHelper mPreferencesHelper,
                                    SchedulerProvider schedulerProvider) {
        this.authApi = AuthApi;
        this.mPreferencesHelper = mPreferencesHelper;
        this.schedulerProvider = schedulerProvider;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request().newBuilder().addHeader("Authorization", "Bearer " + mPreferencesHelper.getCurrentAccessToken()).build();
        // try the request
        Response response = chain.proceed(request);

        //warning for infinite loop
        //will change to code 400 latter
        if (response.code() == 500) {
            Log.e(TAG, "intercept: errorrrrr");
            ResponseWrapper<RefreshAccessTokenResponse> refreshResponse = authApi
                    .refreshAccessTokenPost(new RefreshAccessTokenRequest(mPreferencesHelper.getCurrentRefreshToken()))
                    .blockingLast();
            mPreferencesHelper.setAccessToken(refreshResponse.getData().getAccessToken());
            mPreferencesHelper.setCurrentIdToken(refreshResponse.getData().getIdToken());
            request = chain.request().newBuilder().addHeader("Authorization", "Bearer " + mPreferencesHelper.getCurrentAccessToken()).build();
            response = chain.proceed(request);
        }

        return response;
    }
}
