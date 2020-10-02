package id.ukdw.srmmobile.data.remote;


import java.util.List;

import id.ukdw.srmmobile.data.model.api.request.DetailKelasRequest;
import id.ukdw.srmmobile.data.model.api.request.KelasRequest;
import id.ukdw.srmmobile.data.model.api.request.PesertaKelasRequest;
import id.ukdw.srmmobile.data.model.api.request.ProfilRequest;
import id.ukdw.srmmobile.data.model.api.request.UpdateFcmRequest;
import id.ukdw.srmmobile.data.model.api.response.DetailkelasResponse;
import id.ukdw.srmmobile.data.model.api.response.KelasResponse;
import id.ukdw.srmmobile.data.model.api.response.PesertaKelasResponse;
import id.ukdw.srmmobile.data.model.api.response.ProfilResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {
    @POST("user/profile")
    Observable<ResponseWrapper<ProfilResponse>> getProfile(@Body ProfilRequest request);

    @GET("user/kelas")
    Observable<ResponseWrapper<List<KelasResponse>>> listKelasGet();

    @POST("user/updatefcmtoken")
    Observable<ResponseWrapper<Boolean>> updateFcmToken(@Body UpdateFcmRequest request);

    @POST("/kelas/daftarKelas")
    Observable<ResponseWrapper<DetailkelasResponse>> detailKelas(@Body DetailKelasRequest request);

    @POST("/kelas/mahasiswa/list")
    Observable<ResponseWrapper<List<PesertaKelasResponse>>> getPesertaKelas (@Body PesertaKelasRequest request);

    
}
