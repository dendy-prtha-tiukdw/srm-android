package id.ukdw.srmmobile.data.remote;

import id.ukdw.srmmobile.data.model.api.request.LoginRequest;
import id.ukdw.srmmobile.data.model.api.request.LogoutRequest;
import id.ukdw.srmmobile.data.model.api.request.RefreshAccessTokenRequest;
import id.ukdw.srmmobile.data.model.api.request.SignupRequest;
import id.ukdw.srmmobile.data.model.api.response.LoginResponse;
import id.ukdw.srmmobile.data.model.api.response.RefreshAccessTokenResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.data.model.api.response.SignupResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {

    @POST("auth/signin")
    Call<ResponseWrapper<LoginResponse>> loginPost(@Body LoginRequest request);

    @POST("auth/signup")
    Call<SignupResponse> signUpPost(@Body SignupRequest request);

    @POST("auth/refreshAccessToken")
    Call<ResponseWrapper<RefreshAccessTokenResponse>> refreshAccessTokenPost(@Body RefreshAccessTokenRequest request);

    @POST("auth/signout")
    Call<ResponseWrapper> signOutPost(@Body LogoutRequest request);
}
