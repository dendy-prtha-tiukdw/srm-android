package id.ukdw.srmmobile.model.network;


import javax.security.auth.login.LoginException;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SrmApi {

    @POST("auth/signin")
    Call<Post> LoginPost(@Body Post post);

    @POST("auth/signup")
    Call<Post> SignUpPost(@Body Post post);
}
