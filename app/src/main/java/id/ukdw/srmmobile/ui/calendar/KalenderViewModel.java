package id.ukdw.srmmobile.ui.calendar;

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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.data.model.api.request.CalenderRequest;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.data.model.api.response.CalenderResponse;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static id.ukdw.srmmobile.utils.AppConstants.SCOPE_GOOGLE_CALENDAR;

public class KalenderViewModel extends BaseViewModel<KalenderNavigator> {

    public KalenderViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        super( dataManager, schedulerProvider, googleSignInClient );
    }

    public void getListEventCalenderApi(String date) {
        getDataManager().getCalenderApi( getDataManager().getCurrentAccessToken(), getDataManager().getCurrentRefreshToken() )
                .getListCalender( new CalenderRequest( date ) )
                .subscribeOn( getSchedulerProvider().io() )
                .observeOn( getSchedulerProvider().ui() )
                .subscribe( new Observer<ResponseWrapper<List<CalenderResponse>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        setIsLoading(true);
                    }

                    @Override
                    public void onNext(ResponseWrapper<List<CalenderResponse>> listResponseWrapper) {
                        getNavigator().onGetListCalenderApi( listResponseWrapper.getData() );

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("LEEWAAATT GAKKKKK   ");

                        System.out.println("tes : " + e.getMessage());

                        if (e.getMessage().matches( "Unable to resolve host .*" )){
                            getNavigator().onGetError(  );
                        }
                        else {
                            getNavigator().onServerError();
                        }
//
                    }

                    @Override
                    public void onComplete() {

                    }
                } );


    }

}
