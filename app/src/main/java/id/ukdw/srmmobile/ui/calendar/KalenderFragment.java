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
    java.util.Calendar jCalendar = java.util.Calendar.getInstance();
    FragmentKalenderBinding fragmentKalenderBinding;
    CalendarView calendarView;

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
        //mViewModel.testGoogleCalendar();

        Date c = Calendar.getInstance().getTime();
        jCalendar.setTime( c );
        jCalendar.set( jCalendar.HOUR_OF_DAY, 23 );
        jCalendar.set( jCalendar.MINUTE, 59 );
        jCalendar.set( jCalendar.SECOND, 59 );
        jCalendar.setTimeZone( TimeZone.getDefault() );
        DateTime min = new DateTime( c, TimeZone.getDefault() );
        DateTime max = new DateTime( jCalendar.getTime() );
        mViewModel.getListEvent( min, max );


        getViewDataBinding().calendarView.setOnDateChangeListener( new CalendarView.OnDateChangeListener() {
            @SneakyThrows
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //karna month mulai dari 0
                month = month + 1;

                String dateChanged = year + "-" + month + "-" + dayOfMonth;

                DateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
                jCalendar.setTime( df.parse( dateChanged ) );

                DateTime min = new DateTime( jCalendar.getTime() );
                jCalendar.set( jCalendar.HOUR_OF_DAY, 23 );
                jCalendar.set( jCalendar.MINUTE, 59 );
                jCalendar.set( jCalendar.SECOND, 59 );
                jCalendar.setTimeZone( TimeZone.getDefault() );
                DateTime max = new DateTime( jCalendar.getTime() );
                mViewModel.getListEvent( min, max );

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
    public void onGetListEventCalender(List<Event> kalenderEvent) {
        if (kalenderEvent.isEmpty()) {
            getViewDataBinding().recyclerKalender.setVisibility( View.INVISIBLE );

        }
        itemlist = new ArrayList<>();
        for (Event event : kalenderEvent) {
            DateTime start = event.getStart().getDateTime();
            String Start = "" + start;
            if (start == null) {
                // All-day events don't have start times, so just use
                // the start date.
                start = event.getStart().getDate();

            }
            getViewDataBinding().recyclerKalender.setHasFixedSize( true );
            getViewDataBinding().recyclerKalender.setVisibility( View.VISIBLE );
            KalenderAdapter kalenderAdapter = new KalenderAdapter( getContext(), itemlist );
            getViewDataBinding().recyclerKalender.setLayoutManager( new LinearLayoutManager( getActivity() ) );
            getViewDataBinding().recyclerKalender.setAdapter( kalenderAdapter );

            //Log.i(TAG, "testGoogleCalendar: " + String.format("%s (%s)", event.getSummary(), start));
            itemlist.add( new RecyclerViewModelKalender(
                    event.getSummary(),
                    Start
            ) );
        }
        getBaseActivity().hideLoading();

    }
}
