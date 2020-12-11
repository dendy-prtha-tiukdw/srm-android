package id.ukdw.srmmobile.ui.detailkelas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Calendar;
import java.util.List;

import id.ukdw.srmmobile.BR;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.data.model.api.response.DetailKelasResponse;
import id.ukdw.srmmobile.data.model.api.response.PesertaKelasResponse;

import id.ukdw.srmmobile.databinding.ActivityDetailKelasBinding;
import id.ukdw.srmmobile.di.component.ActivityComponent;
import id.ukdw.srmmobile.ui.base.BaseActivity;
import id.ukdw.srmmobile.ui.daftarkelas.RecyclerViewModelKelas;
import id.ukdw.srmmobile.ui.kegiatankelas.DetailKelasLihatKegiatanActivity;
import id.ukdw.srmmobile.ui.pengumumankelas.DetailKelasPengumumanActivity;

public class DetailKelasActivity extends BaseActivity<ActivityDetailKelasBinding, DetailKelasViewModel>
        implements DetailKelasNavigator {

    public static final String STATE_ON_BACK = "ONBACK";
    public static final String DETAIL_KELAS_DATA = "DETAIL_KELAS_DATA";
    private static final String TAG = DetailKelasActivity.class.getSimpleName();
    DetailKelasResponse detailkelasResponse;
    List<PesertaKelasResponse> pesertaKelas;
    String matkul;
    String group;
    String semester;
    String tahunAjaran;
    private ActivityDetailKelasBinding activityDetailKelasBinding;

    public static Intent newIntent(Context context) {
        return new Intent( context, DetailKelasActivity.class );
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
        buildComponent.inject( this );
        showLoading();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        activityDetailKelasBinding = getViewDataBinding();
        mViewModel.setNavigator( this );
        mViewModel.setContext( this );
        Intent intent = getIntent();
        String state = intent.getStringExtra( "state" );
        if (state.equalsIgnoreCase( STATE_ON_BACK )) {
            Intent intent1 = getIntent();
            matkul = intent1.getStringExtra( "namaMakul" );
            group = intent1.getStringExtra( "group" );
            semester = intent1.getStringExtra( "semester" );
            tahunAjaran = intent1.getStringExtra( "tahunAjaran" );
            mViewModel.getDetailKelas( matkul,group,semester,tahunAjaran );
            mViewModel.getPesertaKelas( matkul,group,semester,tahunAjaran );


        } else {
            RecyclerViewModelKelas recyclerViewModelKelas = (RecyclerViewModelKelas) getIntent().getSerializableExtra( DETAIL_KELAS_DATA );
            matkul = recyclerViewModelKelas.getNamaMakul();
            group = recyclerViewModelKelas.getGroup();
            semester = recyclerViewModelKelas.getSemester();
            tahunAjaran = recyclerViewModelKelas.getTahunAjaran();
            mViewModel.getDetailKelas( matkul, group, semester,tahunAjaran );
            mViewModel.getPesertaKelas( matkul, group, semester,tahunAjaran );


        }
        getViewDataBinding().btPengumumanDetailKelas.setOnClickListener( v -> {
            Intent movePengumuman = new Intent( DetailKelasActivity.this, DetailKelasPengumumanActivity.class );
            movePengumuman.putExtra( "namaMakul", matkul );
            movePengumuman.putExtra( "group", group );
            movePengumuman.putExtra( "semester", semester );
            movePengumuman.putExtra( "tahunAjaran", tahunAjaran );
            startActivity( movePengumuman );
        } );

        getViewDataBinding().btKegiatanDetailKelas.setOnClickListener( v -> {
            Intent moveKegiatan = new Intent( DetailKelasActivity.this, DetailKelasLihatKegiatanActivity.class );
            moveKegiatan.putExtra( "namaMakul", matkul );
            moveKegiatan.putExtra( "group", group );
            moveKegiatan.putExtra( "semester", semester );
            moveKegiatan.putExtra( "tahunAjaran", tahunAjaran );
            startActivity( moveKegiatan );
        } );

        getViewDataBinding().reconnect.setOnClickListener( v -> {
            getViewDataBinding().containerError.setVisibility( View.GONE );
            mViewModel.getDetailKelas( matkul, group, semester,tahunAjaran );
            mViewModel.getPesertaKelas( matkul, group, semester,tahunAjaran );
            showLoading();
        } );
    }

    @Override
    public void onGetDetailKelasCompleted(DetailKelasResponse detailkelasResponse) {
        this.detailkelasResponse = detailkelasResponse;
        activityDetailKelasBinding.txtNamaMakul.setText( detailkelasResponse.getNamaMatakuliah() +
                " " + detailkelasResponse.getGroup() );
        for (int i = 0; i < detailkelasResponse.getNamaDosen().size(); i++) {
            activityDetailKelasBinding.txtPengajar.append( " " + detailkelasResponse.getNamaDosen().get( i ) );
            if (i + 1 < detailkelasResponse.getNamaDosen().size()) {
                activityDetailKelasBinding.txtPengajar.append( "\n" );
            }
        }

        activityDetailKelasBinding.txtPeriode.append( " Semester " + detailkelasResponse.getSemester() + " " + detailkelasResponse.getTahunAjaran() );
        activityDetailKelasBinding.txtWaktu.append( " " + detailkelasResponse.getHari() + " " + detailkelasResponse.getSesi() );
        hideLoading();
    }

    @Override
    public void onGetPesertaKelasCompleted(List<PesertaKelasResponse> pesertaKelasResponses) {
        if (pesertaKelasResponses.isEmpty()){
            getViewDataBinding().recyclerPesertaKelas.setVisibility( View.GONE );
            getViewDataBinding().txtPesertaEmpty.setVisibility( View.VISIBLE );
        }
        this.pesertaKelas = pesertaKelasResponses;
        DaftarPesertaKelasAdapter daftarPesertaKelasAdapter = new DaftarPesertaKelasAdapter( this, pesertaKelasResponses );
        getViewDataBinding().recyclerPesertaKelas.setHasFixedSize( true );
        getViewDataBinding().recyclerPesertaKelas.setLayoutManager( new LinearLayoutManager( this ) );
        getViewDataBinding().recyclerPesertaKelas.setAdapter( daftarPesertaKelasAdapter );
    }


    @Override
    public void onGetError() {
        getViewDataBinding().recyclerPesertaKelas.setVisibility( View.GONE );
        getViewDataBinding().containerError.setVisibility( View.VISIBLE );
        getViewDataBinding().txtDetailKelasError.setText( R.string.error_komunikasi_server );
        hideLoading();

    }

    @Override
    public void onServerError() {
        getViewDataBinding().recyclerPesertaKelas.setVisibility( View.GONE );
        getViewDataBinding().containerError.setVisibility( View.VISIBLE );
        getViewDataBinding().txtDetailKelasError.setVisibility( View.VISIBLE );
        hideLoading();

    }
}
