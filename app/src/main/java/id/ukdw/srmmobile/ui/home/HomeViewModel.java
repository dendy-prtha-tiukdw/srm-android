package id.ukdw.srmmobile.ui.home;

import android.util.Log;

import id.ukdw.srmmobile.data.DataManager;
import id.ukdw.srmmobile.data.model.api.request.LogoutRequest;
import id.ukdw.srmmobile.data.model.api.response.ResponseWrapper;
import id.ukdw.srmmobile.ui.base.BaseViewModel;
import id.ukdw.srmmobile.utils.rx.SchedulerProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.ui.home
 * <p>
 * User: dendy
 * Date: 03/09/2020
 * Time: 10:45
 * <p>
 * Description : HomeViewModel
 */
public class HomeViewModel extends BaseViewModel<HomeNavigator> {
    private static final String TAG = HomeViewModel.class.getSimpleName();

    public HomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void logOut() {
        setIsLoading(true);
        getDataManager().getAuthApi()
                .signOutPost(new LogoutRequest(getDataManager().getAccessToken()))
                .enqueue(new Callback<ResponseWrapper>() {
                    @Override
                    public void onResponse(Call<ResponseWrapper> call, Response<ResponseWrapper> response) {
                        if (response.isSuccessful()) {
                            getDataManager().clearUserInfo();
                            getNavigator().openLoginActivity();
                        } else {
                            switch (response.code()) {
                                default:
                                    Log.e(TAG, "onResponse: " + response.errorBody());
                                    break;
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseWrapper> call, Throwable t) {
                        //show error message as dialog box / Toast
                    }
                });
    }
}
