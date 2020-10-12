package id.ukdw.srmmobile.data.remote;


import java.util.List;

import id.ukdw.srmmobile.data.model.api.request.DetailKelasRequest;
import id.ukdw.srmmobile.data.model.api.request.PengumumanDetailKelasRequest;
import id.ukdw.srmmobile.data.model.api.request.PesertaKelasRequest;
import id.ukdw.srmmobile.data.model.api.request.ProfilRequest;
import id.ukdw.srmmobile.data.model.api.request.SemesterRequest;
import id.ukdw.srmmobile.data.model.api.request.UpdateFcmRequest;
import id.ukdw.srmmobile.data.model.api.response.DetailKelasResponse;
import id.ukdw.srmmobile.data.model.api.response.KelasResponse;
import id.ukdw.srmmobile.data.model.api.response.PengumumanDetailKelasResponse;
import id.ukdw.srmmobile.data.model.api.response.PengumumanResponse;
import id.ukdw.srmmobile.data.model.api.response.PesertaKelasResponse;
import id.ukdw.srmmobile.data.model.api.response.ProfilResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.data.model.api.response.SemesterResponse;
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

    @POST("kelas/detail")
    Observable<ResponseWrapper<DetailKelasResponse>> detailKelas(@Body DetailKelasRequest request);

    @POST("kelas/mahasiswa/list")
    Observable<ResponseWrapper<List<PesertaKelasResponse>>> getPesertaKelas (@Body PesertaKelasRequest request);

    @POST("pengumuman/list")
    Observable<ResponseWrapper<List<PengumumanDetailKelasResponse>>> getPengumumanDetailKelas (@Body PengumumanDetailKelasRequest request);

    @GET("user/pengumuman")
    Observable<ResponseWrapper<List<PengumumanResponse>>> getPengumumanList ();

    @POST("semester/getCurrentSemester")
    Observable<ResponseWrapper<SemesterResponse>> getTanggalSemester (@Body SemesterRequest request);

    
}
