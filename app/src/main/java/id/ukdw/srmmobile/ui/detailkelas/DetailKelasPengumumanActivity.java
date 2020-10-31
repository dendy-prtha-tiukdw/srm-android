package id.ukdw.srmmobile.ui.detailkelas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import id.ukdw.srmmobile.BR;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.data.model.api.response.PengumumanDetailKelasResponse;
import id.ukdw.srmmobile.databinding.ActivityLihatPengumumanBinding;
import id.ukdw.srmmobile.di.component.ActivityComponent;
import id.ukdw.srmmobile.ui.base.BaseActivity;
import id.ukdw.srmmobile.ui.detailkelas.addpengumumankelas.AddPengumumanKelasActivity;

public class DetailKelasPengumumanActivity extends BaseActivity<ActivityLihatPengumumanBinding, DetailKelasPengumumanViewModel>
        implements DetailKelasPengumumanNavigator {

    List<PengumumanDetailKelasResponse> pengumumanDetailKelasResponses;
    private ActivityLihatPengumumanBinding activityLihatPengumumanBinding;

    @Override
    public int getBindingVariable() {
        return BR.detailKelasPengumumanViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_lihat_pengumuman;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        activityLihatPengumumanBinding = getViewDataBinding();
        mViewModel.setNavigator( this );
        mViewModel.setContext( this );
        Intent intent = getIntent();
        String matkul = intent.getStringExtra( "namaMakul" );
        String group = intent.getStringExtra( "group" );
        String semester = intent.getStringExtra( "semester" );
        String tahunAjaran = intent.getStringExtra( "tahunAjaran" );
        mViewModel.getDetailKelasListPengumuman( matkul, group, semester, tahunAjaran );
        if (mViewModel.checkRole() == true) {
            getViewDataBinding().fab.setVisibility( View.VISIBLE );
        }

        getViewDataBinding().fab.setOnClickListener( v -> {
            Intent intentAddPengumuman = new Intent( DetailKelasPengumumanActivity.this, AddPengumumanKelasActivity.class );
            intentAddPengumuman.putExtra("namaMakul", matkul);
            intentAddPengumuman.putExtra("group", group);
            intentAddPengumuman.putExtra("semester", semester);
            intentAddPengumuman.putExtra("tahunAjaran", tahunAjaran);
            startActivity( intentAddPengumuman );
            finish();
        } );

    }


    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject( this );

    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void onGetListDetailKelasPengumuman(List<PengumumanDetailKelasResponse> pengumumanDetailKelasResponses) {
        this.pengumumanDetailKelasResponses = pengumumanDetailKelasResponses;
        DetailKelasPengumumanAdapter detailKelasPengumumanAdapter = new DetailKelasPengumumanAdapter( this, pengumumanDetailKelasResponses );
        getViewDataBinding().recyclerPengumumanKelas.setHasFixedSize( true );
        getViewDataBinding().recyclerPengumumanKelas.setLayoutManager( new LinearLayoutManager( this ) );
        getViewDataBinding().recyclerPengumumanKelas.setAdapter( detailKelasPengumumanAdapter );

    }
}