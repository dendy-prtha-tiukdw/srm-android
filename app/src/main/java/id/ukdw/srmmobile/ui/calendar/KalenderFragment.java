package id.ukdw.srmmobile.ui.calendar;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import id.ukdw.srmmobile.BR;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.data.model.api.response.CalenderResponse;
import id.ukdw.srmmobile.databinding.FragmentKalenderBinding;
import id.ukdw.srmmobile.di.component.FragmentComponent;
import id.ukdw.srmmobile.ui.base.BaseFragment;
import id.ukdw.srmmobile.ui.home.HomeActivity;
import lombok.SneakyThrows;

public class KalenderFragment extends BaseFragment<FragmentKalenderBinding, KalenderViewModel>
        implements KalenderNavigator {

    List<RecyclerViewModelKalender> itemlist;
    FragmentKalenderBinding fragmentKalenderBinding;
    String selectedDate;

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

    @SneakyThrows
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewDataBinding().containerError.setVisibility( View.GONE );
        Date today = Calendar.getInstance().getTime();
        String pattern = getString(R.string.date_pattern);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String todayAsString = sdf.format(today);
        mViewModel.getListEventCalenderApi(todayAsString);

        getViewDataBinding().calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @SneakyThrows
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                getViewDataBinding().recyclerKalender.setVisibility(View.GONE);
                getViewDataBinding().txtEventEmpty.setVisibility(View.GONE);
                getViewDataBinding().pbLoading.show();
                //karna month mulai dari 0
                month = month + 1;
                String selectedMonth = String.valueOf(month).length() < 2 ? ("0" + month) : "" + month;
                String selectedDay = String.valueOf(dayOfMonth).length() < 2 ? ("0" + dayOfMonth) : "" + dayOfMonth;
                selectedDate = year + "-" + selectedMonth + "-" + selectedDay;
                mViewModel.getListEventCalenderApi(selectedDate);
            }
        });

        getViewDataBinding().reconnect.setOnClickListener( v -> {
            getViewDataBinding().containerError.setVisibility( View.GONE );
            getViewDataBinding().recyclerKalender.setVisibility( View.VISIBLE );
            mViewModel.getListEventCalenderApi( selectedDate );
            getViewDataBinding().pbLoading.show();
        } );
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentKalenderBinding = getViewDataBinding();
        mViewModel.setNavigator(this);
        mViewModel.setContext(getBaseActivity());

    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    public void onResume() {
        super.onResume();

        // Set title bar
        ((HomeActivity) getActivity())
                .setActionBarTitle(getString( R.string.titlebar_kalender));
    }

    @Override
    public void onGetListCalenderApi(List<CalenderResponse> ListEventCalender) {
        getViewDataBinding().pbLoading.hide();
        itemlist = new ArrayList<>();
        if (ListEventCalender.isEmpty()) {
            getViewDataBinding().recyclerKalender.setVisibility(View.GONE);
            getViewDataBinding().txtEventEmpty.setVisibility(View.VISIBLE);
        }else{
            getViewDataBinding().recyclerKalender.setHasFixedSize(true);
            getViewDataBinding().txtEventEmpty.setVisibility(View.GONE);
            getViewDataBinding().recyclerKalender.setVisibility(View.VISIBLE);
            KalenderAdapter kalenderAdapter = new KalenderAdapter(getContext(), itemlist);
            getViewDataBinding().recyclerKalender.setLayoutManager(new LinearLayoutManager(getActivity()));
            getViewDataBinding().recyclerKalender.setAdapter(kalenderAdapter);

            for (CalenderResponse calenderResponse : ListEventCalender) {
                itemlist.add(new RecyclerViewModelKalender(
                        calenderResponse.getSummary(),
                        calenderResponse.getStart().concat(calenderResponse.getEnd())
                ));
            }
        }
    }

    @Override
    public void onGetError() {
        getViewDataBinding().containerError.setVisibility( View.VISIBLE );
        getViewDataBinding().recyclerKalender.setVisibility( View.GONE );
        getViewDataBinding().txtKalenderError.setText( R.string.error_koneksi );
        getViewDataBinding().pbLoading.hide();
    }

    @Override
    public void onServerError() {
        getViewDataBinding().containerError.setVisibility( View.VISIBLE );
        getViewDataBinding().recyclerKalender.setVisibility( View.GONE );
        getViewDataBinding().txtKalenderError.setText( R.string.error_komunikasi_server );
        getViewDataBinding().reconnect.setVisibility( View.GONE );
        getViewDataBinding().pbLoading.hide();
    }
}
