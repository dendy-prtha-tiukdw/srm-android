package id.ukdw.srmmobile.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Toast;

import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.databinding.ActivityMainBinding;
import id.ukdw.srmmobile.model.User;
import id.ukdw.srmmobile.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setLoginViewModel(loginViewModel);

        //setup view binding
        loginViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                binding.lblEmailAnswer.setText(user.getEmail());
                binding.lblPasswordAnswer.setText(user.getPassword());
                Toast.makeText(getApplicationContext(), user.getEmail(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}