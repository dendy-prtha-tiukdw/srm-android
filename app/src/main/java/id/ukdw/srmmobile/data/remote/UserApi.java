package id.ukdw.srmmobile.data.remote;


import java.util.List;

import id.ukdw.srmmobile.data.model.api.request.PengumumanRequest;
import id.ukdw.srmmobile.data.model.api.request.ProfilRequest;
import id.ukdw.srmmobile.data.model.api.request.CalenderRequest;
import id.ukdw.srmmobile.data.model.api.request.UpdateFcmRequest;
import id.ukdw.srmmobile.data.model.api.response.KelasResponse;
import id.ukdw.srmmobile.data.model.api.response.PengumumanResponse;
import id.ukdw.srmmobile.data.model.api.response.ProfilResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.data.model.api.response.CalenderResponse;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {
    @POST("user/profile")
    Observable<ResponseWrapper<ProfilResponse>> getProfile(@Body ProfilRequest request);

    @GET("user/kelas")
    Observable<ResponseWrapper<List<KelasResponse>>> getListKelas();

    @POST("user/updatefcmtoken")
    Observable<ResponseWrapper<Boolean>> updateFcmToken(@Body UpdateFcmRequest request);

    @POST("user/pengumuman")
    Observable<ResponseWrapper<List<PengumumanResponse>>> getPengumumanList (@Body PengumumanRequest request);

    
}
