package id.ukdw.srmmobile.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.ui.home.HomeActivity;
import id.ukdw.srmmobile.ui.home.HomeViewModel;

public class KalenderFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kalender, container, false);
    }

    public void onResume() {
        super.onResume();

        // Set title bar
        ((HomeActivity) getActivity())
                .setActionBarTitle("Kalender");
    }
}
