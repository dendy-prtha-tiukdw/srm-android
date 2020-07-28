package id.ukdw.srmmobile.viewmodels;

import android.view.View;

import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import id.ukdw.srmmobile.model.User;

/**
 * Created by Dendy Prtha on 7/28/2020.
 * description : TODO
 */

public class LoginViewModel extends ViewModel {
    public MutableLiveData<String> EmailAddress = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();

    private MutableLiveData<User> userMutableLiveData;

    public MutableLiveData<User> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }

    public void onClick(View view) {
        User loginUser = new User(EmailAddress.getValue(), Password.getValue());
        userMutableLiveData.setValue(loginUser);
    }
}
