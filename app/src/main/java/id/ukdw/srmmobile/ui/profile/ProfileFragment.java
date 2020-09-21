package id.ukdw.srmmobile.ui.profile;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.databinding.FragmentProfilBinding;
import id.ukdw.srmmobile.di.component.FragmentComponent;
import id.ukdw.srmmobile.ui.base.BaseFragment;
import id.ukdw.srmmobile.ui.home.HomeActivity;

public class ProfileFragment extends BaseFragment<FragmentProfilBinding, ProfileViewModel>
        implements ProfileNavigator {

    CircleImageView imageView;
    String link;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentProfilBinding = getViewDataBinding();
        imageView = (CircleImageView) view.findViewById(R.id.fotoProfil);
        Glide.with(this)
                .load(link)
                .circleCrop()
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);
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

}
