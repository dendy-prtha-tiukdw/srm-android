package id.ukdw.srmmobile.ui.profile;

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
import id.ukdw.srmmobile.data.model.api.request.ProfilRequest;
import id.ukdw.srmmobile.data.model.api.response.ProfilResponse;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static id.ukdw.srmmobile.utils.AppConstants.SCOPE_GOOGLE_CALENDAR;

public class ProfileViewModel extends BaseViewModel<ProfileNavigator> {
    private static final String TAG = ProfileViewModel.class.getSimpleName();

    public ProfileViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, GoogleSignInClient googleSignInClient) {
        super(dataManager, schedulerProvider, googleSignInClient);
    }

    public void getProfile() {
        setIsLoading(true);
        getDataManager().getUserApi(getDataManager().getCurrentAccessToken(), getDataManager().getCurrentRefreshToken())
                .getProfile(new ProfilRequest(getDataManager().getCurrentIdToken()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Observer<ResponseWrapper<ProfilResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseWrapper<ProfilResponse> profilResponseResponseWrapper) {
                        ProfilResponse profilResponse = profilResponseResponseWrapper.getData();
                        String imageUrl = profilResponse.getImageUrl() == null ? getDataManager().getCurrentUserImageURL() : profilResponse.getImageUrl();
                        getNavigator().onGetProfileCompleted(
                                profilResponse.getName(),
                                profilResponse.getNomorInduk(),
                                profilResponse.getJenisKelamin(),
                                profilResponse.getEmail(),
                                imageUrl
                        );

                        //update preference
                        getDataManager().profileUserInfo(
                                profilResponse.getNomorInduk(),
                                profilResponse.getName(),
                                profilResponse.getEmail(),
                                profilResponse.getJenisKelamin(),
                                imageUrl
                        );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        setIsLoading(false);
                    }
                });
    }

    public void syncGoogleCalendar(){
        setIsLoading( true );
        getDataManager().getCalenderApi( getDataManager().getCurrentAccessToken(), getDataManager().getCurrentAccessToken() )
                .syncGoogleCalendar()
                .subscribeOn( getSchedulerProvider().io() )
                .observeOn( getSchedulerProvider().ui() )
                .subscribe( new Observer<ResponseWrapper<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseWrapper<String> stringResponseWrapper) {
                        getNavigator().onSyncGoogleCalendar(stringResponseWrapper.getData());

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                } );
    }



}


