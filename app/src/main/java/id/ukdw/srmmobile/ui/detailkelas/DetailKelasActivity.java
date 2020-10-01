package id.ukdw.srmmobile.ui.detailkelas;

import android.os.Bundle;

import androidx.annotation.Nullable;


import java.util.List;

import id.ukdw.srmmobile.BR;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.databinding.ActivityDetailKelasBinding;
import id.ukdw.srmmobile.di.component.ActivityComponent;
import id.ukdw.srmmobile.ui.base.BaseActivity;

public class DetailKelasActivity extends BaseActivity<ActivityDetailKelasBinding, DetailKelasViewModel>
implements DetailKelasNavigator{

    private ActivityDetailKelasBinding activityDetailKelasBinding;


    @Override
    public int getBindingVariable() {
        return BR.detailkelas;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_kelas;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kelas );

        }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject( this );

    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void onGetDetailKelasCompleted(String namaMatakuliah, String group, String hari, String jam, String semester, String tahunAjaran, List<String> namaDosen) {

    }

    @Override
    public void onGetPesertaKelasCompleted() {

    }
}
