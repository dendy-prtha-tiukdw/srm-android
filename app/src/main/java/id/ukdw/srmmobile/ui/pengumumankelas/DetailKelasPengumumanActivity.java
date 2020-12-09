package id.ukdw.srmmobile.ui.pengumumankelas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import id.ukdw.srmmobile.BR;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.data.model.api.response.PengumumanDetailKelasResponse;
import id.ukdw.srmmobile.databinding.ActivityLihatPengumumanBinding;
import id.ukdw.srmmobile.di.component.ActivityComponent;
import id.ukdw.srmmobile.ui.base.BaseActivity;
import id.ukdw.srmmobile.ui.pengumumankelas.detailpengumumankelas.DetailPengumumanKelasActivity;

public class DetailKelasPengumumanActivity extends BaseActivity<ActivityLihatPengumumanBinding, DetailKelasPengumumanViewModel>
        implements DetailKelasPengumumanNavigator {

    public static final String DETAIL_PENGUMUMAN_DATA = "DETAIL_PENGUMUMAN_DATA";
    public static final String STATE_ON_CLICK = "ONCLICK";
    public static final String STATE_ON_BACK = "ONBACK";
    public static final String STATE_ADD = "ADD";
    List<RecyclerVIewModelPengumumanKelas> itemList;
    String matkul;
    String group;
    String semester;
    String tahunAjaran;
    private ActivityLihatPengumumanBinding activityLihatPengumumanBinding;

    @Override
    public int getBindingVariable() {
        return BR.detailKelasPengumumanViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_lihat_pengumuman;
    }

//    @Override
//    public void onBackPressed() {
//        Intent moveDetailKelas = new Intent( DetailKelasPengumumanActivity.this, DetailKelasActivity.class );
//        moveDetailKelas.putExtra( "state", STATE_ON_BACK );
//        moveDetailKelas.putExtra( "namaMakul", matkul );
//        moveDetailKelas.putExtra( "group", group );
//        moveDetailKelas.putExtra( "semester", semester );
//        moveDetailKelas.putExtra( "tahunAjaran", tahunAjaran );
//        startActivity( moveDetailKelas );
//        finish();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        activityLihatPengumumanBinding = getViewDataBinding();
        mViewModel.setNavigator( this );
        mViewModel.setContext( this );
        Intent intent = getIntent();
        matkul = intent.getStringExtra( "namaMakul" );
        group = intent.getStringExtra( "group" );
        semester = intent.getStringExtra( "semester" );
        tahunAjaran = intent.getStringExtra( "tahunAjaran" );
        mViewModel.getDetailKelasListPengumuman( matkul, group, semester, tahunAjaran );
        if (mViewModel.checkRole() == true) {
            getViewDataBinding().fab.setVisibility( View.VISIBLE );
        }

        getViewDataBinding().fab.setOnClickListener( v -> {
            Intent intentAddPengumuman = new Intent( DetailKelasPengumumanActivity.this, DetailPengumumanKelasActivity.class );
            intentAddPengumuman.putExtra( "namaMakul", matkul );
            intentAddPengumuman.putExtra( "group", group );
            intentAddPengumuman.putExtra( "semester", semester );
            intentAddPengumuman.putExtra( "tahunAjaran", tahunAjaran );
            intentAddPengumuman.putExtra( "state", STATE_ADD );
            startActivity( intentAddPengumuman );
            finish();
        } );

        getViewDataBinding().reconnect.setOnClickListener( v -> {
            getViewDataBinding().txtEventConnectTimeOut.setVisibility( View.GONE );
            getViewDataBinding().reconnect.setVisibility( View.GONE );
            if (mViewModel.checkRole() == true){
                getViewDataBinding().fab.setVisibility( View.VISIBLE );
            }
            mViewModel.getDetailKelasListPengumuman( matkul, group, semester, tahunAjaran );
            showLoading();
        } );

    }


    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject( this );
        showLoading();

    }



    @Override
    public void onGetError() {
        getViewDataBinding().txtEventConnectTimeOut.setVisibility( View.VISIBLE );
        getViewDataBinding().txtEventConnectTimeOut1.setVisibility( View.VISIBLE );
        getViewDataBinding().reconnect.setVisibility( View.VISIBLE );
        hideLoading();
    }

    @Override
    public void onServerError() {
        getViewDataBinding().txtErrorServerRequest.setVisibility( View.VISIBLE );
        hideLoading();

    }

    @Override
    public void onGetListDetailKelasPengumuman(List<PengumumanDetailKelasResponse> listPengumumanKelas) {
        if (listPengumumanKelas.isEmpty()){
            getViewDataBinding().txtPengumumanKelasEmpty.setVisibility( View.VISIBLE );
        }
        itemList = new ArrayList<>();

        for (PengumumanDetailKelasResponse pengumumanDetailKelasResponses : listPengumumanKelas) {
            itemList.add( new RecyclerVIewModelPengumumanKelas(
                            pengumumanDetailKelasResponses.getIdPengumuman(),
                            pengumumanDetailKelasResponses.getNamaMatakuliah(),
                            pengumumanDetailKelasResponses.getNamaDosen(),
                            pengumumanDetailKelasResponses.getGroup(),
                            pengumumanDetailKelasResponses.getTahunAjaran(),
                            pengumumanDetailKelasResponses.getSemester(),
                            pengumumanDetailKelasResponses.getPengumuman(),
                            pengumumanDetailKelasResponses.getTanggalInput(),
                            pengumumanDetailKelasResponses.getJudulPengumuman(),
                            pengumumanDetailKelasResponses.getTanggalBerakhir()
                    )
            );
        }

        DetailKelasPengumumanAdapter detailKelasPengumumanAdapter = new DetailKelasPengumumanAdapter( this, itemList );
        getViewDataBinding().recyclerPengumumanKelas.setHasFixedSize( true );
        getViewDataBinding().recyclerPengumumanKelas.setLayoutManager( new LinearLayoutManager( this ) );
        getViewDataBinding().recyclerPengumumanKelas.setAdapter( detailKelasPengumumanAdapter );

        detailKelasPengumumanAdapter.setOnItemClickListener( position -> {
            RecyclerVIewModelPengumumanKelas PengumumanKelas = itemList.get( position );
            Intent moveDetailPengumumanKelas = new Intent( DetailKelasPengumumanActivity.this, DetailPengumumanKelasActivity.class );
            moveDetailPengumumanKelas.putExtra( DETAIL_PENGUMUMAN_DATA, PengumumanKelas );
            moveDetailPengumumanKelas.putExtra( "state", STATE_ON_CLICK );
            startActivity( moveDetailPengumumanKelas );
            finish();
        } );

        hideLoading();
    }
}