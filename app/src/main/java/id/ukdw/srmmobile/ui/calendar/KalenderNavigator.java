package id.ukdw.srmmobile.ui.calendar;

import java.util.List;

import id.ukdw.srmmobile.data.model.api.response.CalenderResponse;

public interface KalenderNavigator {

   void onGetListCalenderApi(List<CalenderResponse> ListEventCalender);
   void onGetError();
   void onServerError();
}
