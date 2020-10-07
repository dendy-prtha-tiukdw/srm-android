package id.ukdw.srmmobile.ui.calendar;

import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static id.ukdw.srmmobile.utils.AppConstants.SCOPE_GOOGLE_CALENDAR;

public class KalenderViewModel extends BaseViewModel<KalenderNavigator> {

    public KalenderViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        super(dataManager, schedulerProvider, googleSignInClient);
    }

    public void testGoogleCalendar() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());

        GoogleAccountCredential credential = GoogleAccountCredential
                .usingOAuth2(getContext(), Arrays.asList(SCOPE_GOOGLE_CALENDAR))
                .setSelectedAccount(account.getAccount());

        Calendar calendar = new Calendar.Builder(
                new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName("Google Calendar API Android Quickstart")
                .build();
        java.util.Calendar jCalendar = java.util.Calendar.getInstance();

        DateTime now = new DateTime(jCalendar.getTime());
        jCalendar.add(java.util.Calendar.MONTH, 2);
        DateTime nextTwoMonth = new DateTime(jCalendar.getTime());

        Observable
                .fromCallable(() -> {
                    try {
                        Events events = calendar.events().list("primary")
                                .setTimeMin(now)
                                .setTimeMax(nextTwoMonth)
                                .setOrderBy("startTime")
                                .setSingleEvents(true)
                                .execute();
                        return events;
                    } catch (IOException e) {
                        //e.printStackTrace();
                        return null;
                    }
                })
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Observer<Events>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Events events) {
                        List<Event> items = events.getItems();
                        getNavigator().onGetListEventCalender( items );
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
