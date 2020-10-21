package id.ukdw.srmmobile.data.remote;

import id.ukdw.srmmobile.data.model.api.request.SemesterRequest;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.data.model.api.response.SemesterResponse;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SemesterApi {
    @POST("semester/getCurrentSemester")
    Observable<ResponseWrapper<SemesterResponse>> getTanggalSemester (@Body SemesterRequest request);
}
