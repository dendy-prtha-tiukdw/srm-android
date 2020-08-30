package id.ukdw.srmmobile.data.remote;


import id.ukdw.srmmobile.data.model.api.Post;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SrmApi {

    @POST("auth/signin")
    Call<Post> LoginPost(@Body Post post);

    @POST("auth/signup")
    Call<Post> SignUpPost(@Body Post post);
}
