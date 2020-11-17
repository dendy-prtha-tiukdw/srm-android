package id.ukdw.srmmobile.ui.calendar;

import com.google.api.services.calendar.model.Event;

import java.util.List;

import id.ukdw.srmmobile.data.model.api.response.CalenderResponse;

public interface KalenderNavigator {
   void onGetListEventCalender(List<Event> kalenderEvent);
   void onGetListCalenderApi(List<CalenderResponse> ListEventCalender);
}
