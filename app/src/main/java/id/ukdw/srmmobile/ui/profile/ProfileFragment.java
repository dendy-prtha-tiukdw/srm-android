package id.ukdw.srmmobile.ui.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;

import com.bumptech.glide.Glide;

import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.databinding.FragmentProfilBinding;
import id.ukdw.srmmobile.di.component.FragmentComponent;
import id.ukdw.srmmobile.ui.base.BaseFragment;
import id.ukdw.srmmobile.ui.home.HomeActivity;

public class ProfileFragment extends BaseFragment<FragmentProfilBinding, ProfileViewModel>
        implements ProfileNavigator {

    private static final String TAG = ProfileFragment.class.getSimpleName();

    private FragmentProfilBinding fragmentProfilBinding;

    public static ProfileFragment newInstance() {
        Bundle args = new Bundle();
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.profilViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_profil;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
        mViewModel.setContext(getBaseActivity());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentProfilBinding = getViewDataBinding();
        mViewModel.getProfile();
        //mViewModel.testGoogleCalendar();
    }



    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    public void onResume() {
        super.onResume();
        // Set title bar
        ((HomeActivity) getActivity())
                .setActionBarTitle("Profil");
    }

    @Override
    public void onGetProfileCompleted(String nama, String nim, String jenisKelamin, String email, String ulrImage) {
        if (jenisKelamin.equalsIgnoreCase( "l" )) {
            jenisKelamin = "Laki-laki";
        }
        else {
            jenisKelamin = "Perempuan";
        }
        fragmentProfilBinding.profileName.append(" " +nama);
        fragmentProfilBinding.profileNim.append(" " +nim);
        fragmentProfilBinding.profileJenisKelamin.append(" " +jenisKelamin);
        fragmentProfilBinding.profileEmail.append(" " +email);
        Glide.with(this)
                .load(ulrImage)
                .circleCrop()
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(fragmentProfilBinding.fotoProfil);
    }
}
