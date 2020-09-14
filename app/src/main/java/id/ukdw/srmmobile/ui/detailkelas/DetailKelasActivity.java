package id.ukdw.srmmobile.ui.detailkelas;

import android.os.Bundle;

import androidx.annotation.Nullable;


import id.ukdw.srmmobile.BR;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.databinding.ActivityDetailKelasBinding;
import id.ukdw.srmmobile.di.component.ActivityComponent;
import id.ukdw.srmmobile.ui.base.BaseActivity;

public class DetailKelasActivity extends BaseActivity<ActivityDetailKelasBinding, DetailKelasViewModel> {

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
}
