package id.ukdw.srmmobile.ui.detailkelas;

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
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.google.api.services.calendar.model.Events;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.data.model.api.request.DetailKelasRequest;
import id.ukdw.srmmobile.data.model.api.request.PesertaKelasRequest;
import id.ukdw.srmmobile.data.model.api.response.DetailKelasResponse;
import id.ukdw.srmmobile.data.model.api.response.PesertaKelasResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static id.ukdw.srmmobile.utils.AppConstants.SCOPE_GOOGLE_CALENDAR;

public class DetailKelasViewModel extends BaseViewModel<DetailKelasNavigator> {
    private static final String TAG = DetailKelasViewModel.class.getSimpleName();

    public DetailKelasViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        super(dataManager, schedulerProvider, googleSignInClient);
    }

    public void getDetailKelas(String Matkul, String Group, String Semester, String tahunAjaran) {
        getDataManager().getUserApi(getDataManager().getCurrentAccessToken(), getDataManager().getCurrentRefreshToken())
                .detailKelas(new DetailKelasRequest("C", "DESAIN GAME", "Gasal", "2020/2021"))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Observer<ResponseWrapper<DetailKelasResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        setIsLoading(true);
                    }

                    @Override
                    public void onNext(ResponseWrapper<DetailKelasResponse> detailkelasResponseResponseWrapper) {
                        DetailKelasResponse detailkelasResponse = detailkelasResponseResponseWrapper.getData();
                        getNavigator().onGetDetailKelasCompleted(detailkelasResponse);
                        setIsLoading(false);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getPesertaKelas(String Matkul, String Group, String Semester, String tahunAjaran) {
        getDataManager().getUserApi(getDataManager().getCurrentAccessToken(), getDataManager().getCurrentRefreshToken())
                .getPesertaKelas(new PesertaKelasRequest(Group, Matkul, Semester, tahunAjaran))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Observer<ResponseWrapper<List<PesertaKelasResponse>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseWrapper<List<PesertaKelasResponse>> listResponseWrapper) {
                        getNavigator().onGetPesertaKelasCompleted(listResponseWrapper.getData());
                    }


                    @Override
                    public void onError(Throwable e) {
                        getNavigator().handleError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void schedulingClass(String sumary, String location, String description,
                                Date startDate, Date endDate) {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());

        GoogleAccountCredential credential = GoogleAccountCredential
                .usingOAuth2(getContext(), Arrays.asList(SCOPE_GOOGLE_CALENDAR))
                .setSelectedAccount(account.getAccount());

        Calendar calendar = new Calendar.Builder(
                new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName("Google Calendar API Android Quickstart")
                .build();

        Event event = new Event()
                .setSummary(sumary)
                .setLocation(location)
                .setDescription(description);

        DateTime startDateTime = new DateTime(startDate);
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("Asia/Jakarta");
        event.setStart(start);

        DateTime endDateTime = new DateTime(endDate);
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("Asia/Jakarta");
        event.setEnd(end);
        EventReminder[] reminderOverrides = new EventReminder[]{
                new EventReminder().setMethod("popup").setMinutes(30),
        };
        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
        event.setReminders(reminders);

        //String[] recurrence = new String[] {"RRULE:FREQ=WEEKLY;COUNT=2"};
        //event.setRecurrence(Arrays.asList(recurrence));

        String calendarId = "primary";
        Event finalEvent = event;
        Observable
                .fromCallable(() -> {
                    try {
                        return calendar.events().insert(calendarId, finalEvent).execute();
                    } catch (IOException e) {
                        Log.e(TAG, "schedulingClass: fail " + e.getMessage());
                        return null;
                    }
                })
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Observer<Event>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Event eventResponse) {
                        Log.i(TAG, "Event Created: " + eventResponse.getHtmlLink());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        getNavigator().onSchedulingClassCompleted();
                    }
                });

    }

}
