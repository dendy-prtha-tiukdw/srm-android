package id.ukdw.srmmobile.ui.detailkelas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import id.ukdw.srmmobile.BR;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.data.model.api.response.DetailKelasResponse;
import id.ukdw.srmmobile.data.model.api.response.PesertaKelasResponse;

import id.ukdw.srmmobile.databinding.ActivityDetailKelasBinding;
import id.ukdw.srmmobile.di.component.ActivityComponent;
import id.ukdw.srmmobile.ui.base.BaseActivity;
import id.ukdw.srmmobile.ui.daftarkelas.RecyclerViewModelKelas;
import id.ukdw.srmmobile.ui.detailkelas.kegiatanKelas.DetailKelasLihatKegiatanActivity;

public class DetailKelasActivity extends BaseActivity<ActivityDetailKelasBinding, DetailKelasViewModel>
        implements DetailKelasNavigator {

    private static final String TAG = DetailKelasActivity.class.getSimpleName();
    private ActivityDetailKelasBinding activityDetailKelasBinding;
    DetailKelasResponse detailkelasResponse;
    List<PesertaKelasResponse> pesertaKelas;

    public static final String DETAIL_KELAS_DATA = "DETAIL_KELAS_DATA";

    public static Intent newIntent(Context context) {
        return new Intent(context, DetailKelasActivity.class);
    }


    @Override
    public int getBindingVariable() {
        return BR.detailKelasViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_kelas;
    }


    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailKelasBinding = getViewDataBinding();
        mViewModel.setNavigator(this);
        mViewModel.setContext(this);
        RecyclerViewModelKelas recyclerViewModelKelas = (RecyclerViewModelKelas) getIntent().getSerializableExtra(DETAIL_KELAS_DATA);
        mViewModel.getDetailKelas(recyclerViewModelKelas.getNamaMakul(), recyclerViewModelKelas.getGroup(), recyclerViewModelKelas.getSemester(), recyclerViewModelKelas.getTahunAjaran());
        mViewModel.getPesertaKelas(recyclerViewModelKelas.getNamaMakul(), recyclerViewModelKelas.getGroup(), recyclerViewModelKelas.getSemester(), recyclerViewModelKelas.getTahunAjaran());
        getViewDataBinding().btPengumumanDetailKelas.setOnClickListener(v -> {
            Intent movePengumuman = new Intent(DetailKelasActivity.this, DetailKelasPengumumanActivity.class);
            movePengumuman.putExtra("namaMakul", recyclerViewModelKelas.getNamaMakul());
            movePengumuman.putExtra("group", recyclerViewModelKelas.getGroup());
            movePengumuman.putExtra("semester", recyclerViewModelKelas.getSemester());
            movePengumuman.putExtra("tahunAjaran", recyclerViewModelKelas.getTahunAjaran());
            startActivity(movePengumuman);
        });
        getViewDataBinding().imgSchedule.setOnClickListener(onClick -> {
            handleSchedule();
        });

        getViewDataBinding().btKegiatanDetailKelas.setOnClickListener( v-> {
            Intent moveKegiatan = new Intent( DetailKelasActivity.this, DetailKelasLihatKegiatanActivity.class );
            moveKegiatan.putExtra("namaMakul", recyclerViewModelKelas.getNamaMakul());
            moveKegiatan.putExtra("group", recyclerViewModelKelas.getGroup());
            moveKegiatan.putExtra("semester", recyclerViewModelKelas.getSemester());
            moveKegiatan.putExtra("tahunAjaran", recyclerViewModelKelas.getTahunAjaran());
            startActivity(moveKegiatan);
        } );

    }

    private void handleSchedule() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage("Apakah Anda ingin sistem menjadwalkan perkuliahan untuk minggu depan?");
        dialogBuilder.setCancelable(true);

        dialogBuilder.setPositiveButton(
                "Ya", (dialog, id) -> {
                    showLoading();
                    if (detailkelasResponse != null) {
                        String[] hoursPart = detailkelasResponse.getSesi().split("-");
                        String[] startHoursPart = hoursPart[0].split(":");
                        String[] endHoursPart = hoursPart[1].split(":");
                        // get today and clear time of day
                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
                        cal.clear(Calendar.MINUTE);
                        cal.clear(Calendar.SECOND);
                        cal.clear(Calendar.MILLISECOND);

                        // get start of this week in milliseconds
                        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());

                        // get start of nexr week in milliseconds
                        cal.add(Calendar.WEEK_OF_YEAR, 1);
                        String[] days = new String[]{"minggu", "senin", "selasa", "rabu", "kamis", "jumat", "sabtu"};
                        //loop through the week
                        for (int i = 0; i < days.length; i++) {
                            //check if day is the same.
                            if (detailkelasResponse.getHari().equalsIgnoreCase(days[cal.get(Calendar.DAY_OF_WEEK) - 1])) {
                                Log.i(TAG, "Hari:       " + days[cal.get(Calendar.DAY_OF_WEEK) - 1] +
                                        ", Tanggal:" + cal.get(Calendar.DATE) +
                                        ", Bulan:" + cal.get(Calendar.MONTH));
                                Calendar start = (Calendar) cal.clone();
                                Calendar end = (Calendar) cal.clone();
                                start.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startHoursPart[0]));
                                start.set(Calendar.MINUTE, Integer.parseInt(startHoursPart[1]));
                                end.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endHoursPart[0]));
                                end.set(Calendar.MINUTE, Integer.parseInt(endHoursPart[1]));

                                mViewModel.schedulingClass(
                                        detailkelasResponse.getNamaMatakuliah() + " " + detailkelasResponse.getGroup(),
                                        "",
                                        detailkelasResponse.getNamaMatakuliah(),
                                        start.getTime(),
                                        end.getTime()
                                );
                                return;
                            }
                            //increase the day
                            cal.add(Calendar.DAY_OF_WEEK, 1);
                        }
                    }
                    dialog.dismiss();
                });

        dialogBuilder.setNegativeButton(
                "Tidak", (dialog, id) -> dialog.cancel());

        AlertDialog alert11 = dialogBuilder.create();
        alert11.show();
    }


    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void onGetDetailKelasCompleted(DetailKelasResponse detailkelasResponse) {
        this.detailkelasResponse = detailkelasResponse;
        activityDetailKelasBinding.txtNamaMakul.setText(detailkelasResponse.getNamaMatakuliah() +
                " " + detailkelasResponse.getGroup());
        for (int i = 0; i < detailkelasResponse.getNamaDosen().size(); i++) {
            activityDetailKelasBinding.txtPengajar.append(" " + detailkelasResponse.getNamaDosen().get(i));
            if (i + 1 < detailkelasResponse.getNamaDosen().size()) {
                activityDetailKelasBinding.txtPengajar.append("\n");
            }
        }

        activityDetailKelasBinding.txtPeriode.append(" Semester " + detailkelasResponse.getSemester() + " " + detailkelasResponse.getTahunAjaran());
        activityDetailKelasBinding.txtWaktu.append(" " + detailkelasResponse.getHari() + " " + detailkelasResponse.getSesi());
    }

    @Override
    public void onGetPesertaKelasCompleted(List<PesertaKelasResponse> pesertaKelasResponses) {
        this.pesertaKelas = pesertaKelasResponses;
        DaftarPesertaKelasAdapter daftarPesertaKelasAdapter = new DaftarPesertaKelasAdapter(this, pesertaKelasResponses);
        getViewDataBinding().recyclerPesertaKelas.setHasFixedSize(true);
        getViewDataBinding().recyclerPesertaKelas.setLayoutManager(new LinearLayoutManager(this));
        getViewDataBinding().recyclerPesertaKelas.setAdapter(daftarPesertaKelasAdapter);
    }

    @Override
    public void onSchedulingClassCompleted() {
        Toast.makeText(this, "Penjadwalan berhasil", Toast.LENGTH_LONG).show();
        hideLoading();
    }
}
