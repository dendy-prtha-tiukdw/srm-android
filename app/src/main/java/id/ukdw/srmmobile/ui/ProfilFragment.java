package id.ukdw.srmmobile.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.ui.home.HomeActivity;
import id.ukdw.srmmobile.ui.home.HomeViewModel;

public class ProfilFragment extends Fragment {

    CircleImageView imageView;
    String link;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profil, container, false);
        return v;
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
    }

    public void onResume() {
        super.onResume();

        // Set title bar
        ((HomeActivity) getActivity())
                .setActionBarTitle("Profil");
    }

}
