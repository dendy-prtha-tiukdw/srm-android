package id.ukdw.srmmobile.ui.kegiatankelas.detailkegiatankelas;

import android.os.Bundle;

import id.ukdw.srmmobile.BR;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.databinding.ActivityDetailKegiatanKelasBinding;
import id.ukdw.srmmobile.di.component.ActivityComponent;
import id.ukdw.srmmobile.ui.base.BaseActivity;

public class DetailKegiatanKelasActivity extends BaseActivity<ActivityDetailKegiatanKelasBinding, DetailKegiatanKelasViewModel>
        implements DetailKegiatanKelasNavigator {

    public static final String DETAIL_PENGUMUMAN_DATA = "DETAIL_PENGUMUMAN_DATA";
    public static final String STATE_ON_CLICK = "ONCLICK";
    public static final String STATE_ADD = "ADD";
    ActivityDetailKegiatanKelasBinding activityDetailKegiatanKelasBinding;
    String matkul;
    String group;
    String tahunAjaran;
    String semester;
    String state;

    @Override
    public int getBindingVariable() {
        return BR.detailKegiatanKelasViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_kegiatan_kelas;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        activityDetailKegiatanKelasBinding = getViewDataBinding();

    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject( this );


    }

    @Override
    public void onSuccessAddKegiatan(String data) {

    }

    @Override
    public void onSuccessDeleteKegiatan(String data) {

    }

    @Override
    public void onSuccessUpdateKegiatan(String data) {

    }
}