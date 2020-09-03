package id.ukdw.srmmobile.viewmodels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.data.model.api.Post;
import id.ukdw.srmmobile.data.model.network.ResourceProvider;
import id.ukdw.srmmobile.data.model.network.RetrofitBuilder;
import id.ukdw.srmmobile.data.remote.SrmApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends ViewModel {

    public static final String TAG = "ServerAuthCodeActivity";
    String provider;
    private ResourceProvider mResourceProvider;
    public MutableLiveData<String> AuthCode = new MutableLiveData<>();
    String authCode;
    public Context context ;

    //private MutableLiveData<User> userMutableLiveData;
    private GoogleSignInClient mGoogleSignInClient;
    String nomorInduk;
    String role;
    private MutableLiveData<Boolean> status;
//    public String status;

    public SignUpViewModel(Context context) {
        this.mResourceProvider = new ResourceProvider (context);

    }

    /*
    public MutableLiveData<User> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }*/

    public MutableLiveData<Boolean> getStatus() {
        if(status == null) {
            status = new MutableLiveData<Boolean>();
        }
        return  status;
    }

    public void validateServerClientID() {
        String serverClientId = mResourceProvider.getString( R.string.server_client_id );
        String suffix = mResourceProvider.getString( R.string.suffix );
        if (!serverClientId.trim().endsWith( suffix )) {
            String message = mResourceProvider.getString( R.string.message )  + suffix;
            Log.w( TAG, message );
        }
    }


    public void prosesAuthCode(Task<GoogleSignInAccount> Task, String nomorIndukTest, String radio) {
        provider =mResourceProvider.getString( R.string.provider );
        nomorInduk=nomorIndukTest;
        role=radio;
        try {
            GoogleSignInAccount account = Task.getResult( ApiException.class );
            authCode = account.getServerAuthCode();

            System.out.println("AUTHCODE : " +authCode );
            System.out.println("NOMORINDUK :  " + nomorIndukTest);
            System.out.println("PROVIDER : " +provider);
            signUpPost( authCode,nomorIndukTest,role );



        } catch (ApiException e) {
            Log.w( TAG, "Sign-in failed", e );

        }

    }

    private void signUpPost(String authCode, String nomorInduk, String role) {
 /*
        Post post = new Post( provider,authCode,nomorInduk,role );

        SrmApi srmApi = RetrofitBuilder.getAPIService();
        System.out.println("POST : " + post);
        Call<Post> signUp = srmApi.signUpPost( post );
        signUp.enqueue( new Callback<Post>() {

            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    System.out.println("response gak berhasil");
                    System.out.println( response.code() );

                }
                else {
                    System.out.println( "response berhasil" );
                    System.out.println( response.code() );

                    getStatus().setValue( true );
                }

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                System.out.println("tembak api gagal");
                System.out.println( t.getMessage() );
            }

        } );*/

    }
}
