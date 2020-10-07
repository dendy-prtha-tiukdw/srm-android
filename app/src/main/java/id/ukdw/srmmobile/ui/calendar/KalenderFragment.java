package id.ukdw.srmmobile.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;

import java.util.ArrayList;
import java.util.List;

import id.ukdw.srmmobile.BR;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.databinding.FragmentKalenderBinding;
import id.ukdw.srmmobile.di.component.FragmentComponent;
import id.ukdw.srmmobile.ui.base.BaseFragment;
import id.ukdw.srmmobile.ui.daftarkelas.DaftarKelasAdapter;
import id.ukdw.srmmobile.ui.daftarkelas.DaftarKelasFragment;
import id.ukdw.srmmobile.ui.home.HomeActivity;
import id.ukdw.srmmobile.ui.home.HomeViewModel;

public class KalenderFragment extends BaseFragment<FragmentKalenderBinding, KalenderViewModel>
    implements KalenderNavigator{

    List<RecyclerViewModelKalender> itemlist;

    public static KalenderFragment newInstance() {
        Bundle args = new Bundle();
        KalenderFragment fragment = new KalenderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.kalenderViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_kalender;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        mViewModel.testGoogleCalendar();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        mViewModel.setNavigator( this );
        mViewModel.setContext( getBaseActivity() );
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject( this );
//        getBaseActivity().showLoading();

    }

    public void onResume() {
        super.onResume();

        // Set title bar
        ((HomeActivity) getActivity())
                .setActionBarTitle("Kalender");
    }

    @Override
    public void onGetListEventCalender(List<Event> kalenderEvent) {
        itemlist = new ArrayList<>(  );
        for (Event event : kalenderEvent) {
            DateTime start = event.getStart().getDateTime();
            String Start = ""+ start;
            if (start == null) {
                // All-day events don't have start times, so just use
                // the start date.
                start = event.getStart().getDate();

            }
            getViewDataBinding().recyclerKalender.setHasFixedSize(true);
            KalenderAdapter kalenderAdapter = new KalenderAdapter(getContext(), itemlist);
            getViewDataBinding().recyclerKalender.setLayoutManager(new LinearLayoutManager(getActivity()));
            getViewDataBinding().recyclerKalender.setAdapter(kalenderAdapter);

            //Log.i(TAG, "testGoogleCalendar: " + String.format("%s (%s)", event.getSummary(), start));
            itemlist.add( new RecyclerViewModelKalender(
                    event.getSummary(),
                    Start
            ) );
        }

    }
}
