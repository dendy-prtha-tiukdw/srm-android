package id.ukdw.srmmobile.data.remote;

import java.util.List;

import id.ukdw.srmmobile.data.model.api.request.DetailKelasRequest;
import id.ukdw.srmmobile.data.model.api.request.PesertaKelasRequest;
import id.ukdw.srmmobile.data.model.api.response.DetailKelasResponse;
import id.ukdw.srmmobile.data.model.api.response.PesertaKelasResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface KelasApi {

    @POST("kelas/detail")
    Observable<ResponseWrapper<DetailKelasResponse>> detailKelas(@Body DetailKelasRequest request);

    @POST("kelas/mahasiswa/list")
    Observable<ResponseWrapper<List<PesertaKelasResponse>>> getPesertaKelas (@Body PesertaKelasRequest request);
}
