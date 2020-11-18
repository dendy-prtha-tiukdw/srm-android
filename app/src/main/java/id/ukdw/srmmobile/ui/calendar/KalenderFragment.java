package id.ukdw.srmmobile.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import id.ukdw.srmmobile.BR;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.data.model.api.response.CalenderResponse;
import id.ukdw.srmmobile.databinding.FragmentKalenderBinding;
import id.ukdw.srmmobile.di.component.FragmentComponent;
import id.ukdw.srmmobile.ui.base.BaseFragment;
import id.ukdw.srmmobile.ui.daftarkelas.DaftarKelasAdapter;
import id.ukdw.srmmobile.ui.daftarkelas.DaftarKelasFragment;
import id.ukdw.srmmobile.ui.home.HomeActivity;
import id.ukdw.srmmobile.ui.home.HomeViewModel;
import lombok.SneakyThrows;

public class KalenderFragment extends BaseFragment<FragmentKalenderBinding, KalenderViewModel>
        implements KalenderNavigator {

    List<RecyclerViewModelKalender> itemlist;
    FragmentKalenderBinding fragmentKalenderBinding;

    public static KalenderFragment newInstance() {
        Bundle args = new Bundle();
        KalenderFragment fragment = new KalenderFragment();
        fragment.setArguments( args );
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

    @SneakyThrows
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        Date today = Calendar.getInstance().getTime();
        String pattern = getString( R.string.date_pattern);
        DateFormat df = new SimpleDateFormat( pattern );
        String todayAsString = df.format( today );
        mViewModel.getListEventCalenderApi( todayAsString );

        getViewDataBinding().calendarView.setOnDateChangeListener( new CalendarView.OnDateChangeListener() {
            @SneakyThrows
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //karna month mulai dari 0
                month = month + 1;
                String dateChanged = year + "-" + month + "-" + dayOfMonth;
                mViewModel.getListEventCalenderApi( dateChanged );
            }
        } );
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        fragmentKalenderBinding = getViewDataBinding();
        mViewModel.setNavigator( this );
        mViewModel.setContext( getBaseActivity() );

    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject( this );
        getBaseActivity().showLoading();

    }

    public void onResume() {
        super.onResume();

        // Set title bar
        ((HomeActivity) getActivity())
                .setActionBarTitle( "Kalender" );
    }

    @Override
    public void onGetListCalenderApi(List<CalenderResponse> ListEventCalender) {
        itemlist = new ArrayList<>();
        if (ListEventCalender.isEmpty()) {
            getViewDataBinding().recyclerKalender.setVisibility( View.INVISIBLE );
        }

        getViewDataBinding().recyclerKalender.setHasFixedSize( true );
        getViewDataBinding().recyclerKalender.setVisibility( View.VISIBLE );
        KalenderAdapter kalenderAdapter = new KalenderAdapter( getContext(), itemlist );
        getViewDataBinding().recyclerKalender.setLayoutManager( new LinearLayoutManager( getActivity() ) );
        getViewDataBinding().recyclerKalender.setAdapter( kalenderAdapter );

        for (CalenderResponse calenderResponse : ListEventCalender) {
            itemlist.add( new RecyclerViewModelKalender(
                    calenderResponse.getSummary(),
                    calenderResponse.getStart().concat( calenderResponse.getEnd() )

            ) );
        }
        getBaseActivity().hideLoading();

    }
}
