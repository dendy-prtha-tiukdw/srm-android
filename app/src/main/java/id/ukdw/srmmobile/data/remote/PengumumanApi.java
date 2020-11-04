package id.ukdw.srmmobile.data.remote;

import java.util.List;

import id.ukdw.srmmobile.data.model.api.request.AddPengumumanRequest;
import id.ukdw.srmmobile.data.model.api.request.DeletePengumumanKelasRequest;
import id.ukdw.srmmobile.data.model.api.request.PengumumanDetailKelasRequest;
import id.ukdw.srmmobile.data.model.api.request.UpdatePengumumanKelasRequest;
import id.ukdw.srmmobile.data.model.api.response.AddPengumumanResponse;
import id.ukdw.srmmobile.data.model.api.response.PengumumanDetailKelasResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PengumumanApi {
    @POST("pengumuman/list")
    Observable<ResponseWrapper<List<PengumumanDetailKelasResponse>>> getPengumumanDetailKelas (@Body PengumumanDetailKelasRequest request);

    @POST("pengumuman/create")
    Observable<ResponseWrapper<String>> setAddPengumuman (@Body AddPengumumanRequest request);

    @POST("pengumuman/update")
    Observable<ResponseWrapper<String>> setUpdatePengumuman (@Body UpdatePengumumanKelasRequest request);

    @POST("pengumuman/delete")
    Observable<ResponseWrapper<String>> setDeletePengumuman(@Body DeletePengumumanKelasRequest request);
}
