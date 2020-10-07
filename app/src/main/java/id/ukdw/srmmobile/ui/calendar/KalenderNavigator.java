package id.ukdw.srmmobile.ui.calendar;

import com.google.api.services.calendar.model.Event;

import java.util.List;

public interface KalenderNavigator {
   void onGetListEventCalender(List<Event> kalenderEvent);
}
