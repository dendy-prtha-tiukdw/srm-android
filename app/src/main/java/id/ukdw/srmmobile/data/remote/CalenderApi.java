package id.ukdw.srmmobile.data.remote;

import java.util.List;

import id.ukdw.srmmobile.data.model.api.request.CalenderRequest;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.data.model.api.response.CalenderResponse;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CalenderApi {
    @POST("googleCalendar/date")
    Observable<ResponseWrapper<List<CalenderResponse>>> getListCalender (@Body CalenderRequest request);
}
