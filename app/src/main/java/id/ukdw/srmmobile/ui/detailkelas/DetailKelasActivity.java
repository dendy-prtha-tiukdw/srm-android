package id.ukdw.srmmobile.ui.detailkelas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import id.ukdw.srmmobile.BR;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.data.model.api.response.DetailKelasResponse;
import id.ukdw.srmmobile.data.model.api.response.PesertaKelasResponse;
import id.ukdw.srmmobile.databinding.ActivityDetailKelasBinding;
import id.ukdw.srmmobile.di.component.ActivityComponent;
import id.ukdw.srmmobile.ui.base.BaseActivity;
import id.ukdw.srmmobile.ui.daftarkelas.RecyclerViewModelKelas;

public class DetailKelasActivity extends BaseActivity<ActivityDetailKelasBinding, DetailKelasViewModel>
        implements DetailKelasNavigator {

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
}
