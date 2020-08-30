package id.ukdw.srmmobile.data.remote;


import id.ukdw.srmmobile.data.model.api.Post;
import id.ukdw.srmmobile.data.model.api.request.LoginRequest;
import id.ukdw.srmmobile.data.model.api.request.SignupRequest;
import id.ukdw.srmmobile.data.model.api.response.LoginResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.data.model.api.response.SignupResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {

    @POST("auth/signin")
    Call<ResponseWrapper<LoginResponse>> loginPost(@Body LoginRequest request);

    @POST("auth/signup")
    Call<SignupResponse> SignUpPost(@Body SignupRequest request);
}
