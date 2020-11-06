package id.ukdw.srmmobile.data.remote;

import java.util.List;

import id.ukdw.srmmobile.data.model.api.request.AddKegiatanRequest;
import id.ukdw.srmmobile.data.model.api.request.AddPengumumanRequest;
import id.ukdw.srmmobile.data.model.api.request.DeleteKegiatanRequest;
import id.ukdw.srmmobile.data.model.api.request.DeletePengumumanKelasRequest;
import id.ukdw.srmmobile.data.model.api.request.KegiatanDetailKelasRequest;
import id.ukdw.srmmobile.data.model.api.request.PengumumanDetailKelasRequest;
import id.ukdw.srmmobile.data.model.api.request.UpdateKegiatanKelasRequest;
import id.ukdw.srmmobile.data.model.api.request.UpdatePengumumanKelasRequest;
import id.ukdw.srmmobile.data.model.api.response.KegiatanDetailKelasResponse;
import id.ukdw.srmmobile.data.model.api.response.PengumumanDetailKelasResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface KegiatanApi {
    @POST("kegiatan/list")
    Observable<ResponseWrapper<List<KegiatanDetailKelasResponse>>> getKegiatanDetailKelas (@Body KegiatanDetailKelasRequest request);

    @POST("kegiatan/create")
    Observable<ResponseWrapper<String>> setAddKegiatan (@Body AddKegiatanRequest request);

    @POST("kegiatan/update")
    Observable<ResponseWrapper<String>> setUpdateKegiatan (@Body UpdateKegiatanKelasRequest request);

    @POST("kegiatan/delete")
    Observable<ResponseWrapper<String>> setDeleteKegiatan (@Body DeleteKegiatanRequest request);
}