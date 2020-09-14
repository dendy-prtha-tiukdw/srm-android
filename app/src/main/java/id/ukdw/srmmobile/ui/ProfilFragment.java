package id.ukdw.srmmobile.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.SrmMobileApplication;
import id.ukdw.srmmobile.databinding.FragmentProfilBinding;
import id.ukdw.srmmobile.di.component.DaggerFragmentComponent;
import id.ukdw.srmmobile.di.component.FragmentComponent;
import id.ukdw.srmmobile.di.module.FragmentModule;
import id.ukdw.srmmobile.ui.base.BaseFragment;
import id.ukdw.srmmobile.ui.home.HomeActivity;
import id.ukdw.srmmobile.ui.profil.ProfilViewModel;

public class ProfilFragment extends BaseFragment<FragmentProfilBinding, ProfilViewModel> {

    Context mContext;
    CircleImageView imageView;
    String link;
    private FragmentComponent fragmentComponent;
    private View mRootView;
    private ViewDataBinding mViewDataBinding;
    private ProfilViewModel profilViewModel;
    private FragmentProfilBinding fragmentProfilBinding;

    @Override
    public int getBindingVariable() {
        return BR.profilViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_profil;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profil, container, false);
        fragmentProfilBinding = getViewDataBinding();
        mRootView = mViewDataBinding.getRoot();
        DaggerFragmentComponent.builder();
        return v;
    }

    public FragmentComponent getFragmentComponent() {
        if (fragmentComponent == null) {
                    fragmentComponent = DaggerFragmentComponent.builder()
                    .fragmentModule(new FragmentModule(this))
                    .appComponent(SrmMobileApplication.get(mContext).getComponent())
                    .build();
        }
        return fragmentComponent;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = (CircleImageView) view.findViewById(R.id.fotoProfil);
        Glide.with(this)
                .load(link)
                .circleCrop()
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);

        getViewDataBinding().profileName.setText(profilViewModel.onFragmentProfil());
        getViewDataBinding().profileEmail.setText(profilViewModel.onFragmentProfil());
        getViewDataBinding().profileNim.setText(profilViewModel.onFragmentProfil());
        getViewDataBinding().profileRole.setText(profilViewModel.onFragmentProfil());
        getViewDataBinding().profileJenisKelamin.setText(profilViewModel.onFragmentProfil());
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
    }

    public void onResume() {
        super.onResume();
        // Set title bar
        ((HomeActivity) getActivity())
                .setActionBarTitle("Profil");
    }

}
