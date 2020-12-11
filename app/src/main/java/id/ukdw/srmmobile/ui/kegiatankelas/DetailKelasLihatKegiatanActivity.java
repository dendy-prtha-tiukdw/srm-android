package id.ukdw.srmmobile.ui.kegiatankelas;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import id.ukdw.srmmobile.BR;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.data.model.api.response.KegiatanDetailKelasResponse;
import id.ukdw.srmmobile.databinding.ActivityKelasLihatKegiatanBinding;
import id.ukdw.srmmobile.di.component.ActivityComponent;
import id.ukdw.srmmobile.ui.base.BaseActivity;
import id.ukdw.srmmobile.ui.kegiatankelas.detailkegiatankelas.DetailKegiatanKelasActivity;

public class DetailKelasLihatKegiatanActivity extends BaseActivity<ActivityKelasLihatKegiatanBinding, DetailkelasLihatKegiatanViewModel>
        implements DetailKelasLihatKegiatNavigator  {

    List<RecyclerViewModelKegiatanKelas> itemList;
    private ActivityKelasLihatKegiatanBinding activityKelasLihatKegiatanBinding;
    public static final String DETAIL_KEGIATAN_DATA = "DETAIL_KEGIATAN_DATA";
    public static final String STATE_ON_CLICK = "ONCLICK";
    public static final String STATE_ADD = "ADD";

    @Override
    public int getBindingVariable() {
        return BR.detailKelasLihatKegiatanViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_kelas_lihat_kegiatan;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        activityKelasLihatKegiatanBinding = getViewDataBinding();
        mViewModel.setNavigator( this );
        mViewModel.setContext( this );
        Intent intent = getIntent();
        String matkul = intent.getStringExtra( "namaMakul" );
        String group = intent.getStringExtra( "group" );
        String semester = intent.getStringExtra( "semester" );
        String tahunAjaran = intent.getStringExtra( "tahunAjaran" );
        mViewModel.getDetailKelasListKegiatan( group, matkul, semester, tahunAjaran );
        if (mViewModel.checkRole() == true){
            getViewDataBinding().fabKegiatan.setVisibility( View.VISIBLE );
        }
        getViewDataBinding().fabKegiatan.setOnClickListener( v->{
            Intent intentaddKegiatan = new Intent( DetailKelasLihatKegiatanActivity.this,DetailKegiatanKelasActivity.class );
            intentaddKegiatan.putExtra( "namaMakul", matkul );
            intentaddKegiatan.putExtra( "group", group );
            intentaddKegiatan.putExtra( "semester", semester );
            intentaddKegiatan.putExtra( "tahunAjaran", tahunAjaran );
            intentaddKegiatan.putExtra( "state", STATE_ADD );
            startActivity( intentaddKegiatan );
            finish();
        } );

        getViewDataBinding().reconnect.setOnClickListener( v -> {
            getViewDataBinding().containerError.setVisibility( View.GONE );
            getViewDataBinding().recyclerKegiatanKelas.setVisibility( View.VISIBLE );
            getViewDataBinding().reconnect.setVisibility( View.GONE );
            if (mViewModel.checkRole() == true){
                getViewDataBinding().fabKegiatan.setVisibility( View.VISIBLE );
            }
            mViewModel.getDetailKelasListKegiatan( group, matkul, semester, tahunAjaran );
            showLoading();
        } );



    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject( this );
        showLoading();

    }

    @Override
    public void onGetListKegiatanKelas(List<KegiatanDetailKelasResponse> listKegiatanKelas) {
        if (listKegiatanKelas.isEmpty()){
            getViewDataBinding().recyclerKegiatanKelas.setVisibility( View.GONE );
            getViewDataBinding().fabKegiatan.setVisibility( View.GONE );
            getViewDataBinding().txtKegiatanKelasEmpty.setVisibility( View.VISIBLE );
        }
        itemList = new ArrayList<>(  );

        for(KegiatanDetailKelasResponse kegiatanDetailKelasResponse : listKegiatanKelas){
            itemList.add( new RecyclerViewModelKegiatanKelas(
                    kegiatanDetailKelasResponse.getIdKegiatan(),
                    kegiatanDetailKelasResponse.getNamaMatakuliah(),
                    kegiatanDetailKelasResponse.getNamaDosen(),
                    kegiatanDetailKelasResponse.getGroup(),
                    kegiatanDetailKelasResponse.getTahunAjaran(),
                    kegiatanDetailKelasResponse.getSemester(),
                    kegiatanDetailKelasResponse.getIsiKegiatan(),
                    kegiatanDetailKelasResponse.getTanggalDibuat(),
                    kegiatanDetailKelasResponse.getTanggalBerakhir(),
                    kegiatanDetailKelasResponse.getJudulKegiatan(),
                    kegiatanDetailKelasResponse.getComplete()
            ) );
        }

        DetailKelasLihatKegiatanAdapter detailKelasLihatKegiatanAdapter = new DetailKelasLihatKegiatanAdapter( this,itemList );
        getViewDataBinding().recyclerKegiatanKelas.setHasFixedSize( true );
        getViewDataBinding().recyclerKegiatanKelas.setLayoutManager( new LinearLayoutManager( this ) );
        getViewDataBinding().recyclerKegiatanKelas.setAdapter( detailKelasLihatKegiatanAdapter );

        detailKelasLihatKegiatanAdapter.setOnItemClickListener( position -> {
            RecyclerViewModelKegiatanKelas kegiatanKelas = itemList.get( position );
            Intent moveDetailKegiatanKelas = new Intent( DetailKelasLihatKegiatanActivity.this, DetailKegiatanKelasActivity.class );
            moveDetailKegiatanKelas.putExtra( DETAIL_KEGIATAN_DATA, kegiatanKelas );
            moveDetailKegiatanKelas.putExtra( "state",  STATE_ON_CLICK );
            startActivity( moveDetailKegiatanKelas );
            finish();
        } );

    hideLoading();
    }

    @Override
    public void onGetError() {
        getViewDataBinding().containerError.setVisibility( View.VISIBLE );
        getViewDataBinding().txtListKegiatanError.setText( R.string.error_koneksi );
        getViewDataBinding().fabKegiatan.setVisibility( View.GONE );
        getViewDataBinding().recyclerKegiatanKelas.setVisibility( View.GONE );
        hideLoading();

    }

    @Override
    public void onServerError() {
        getViewDataBinding().containerError.setVisibility( View.VISIBLE );
        getViewDataBinding().txtListKegiatanError.setText( R.string.error_komunikasi_server );
        getViewDataBinding().reconnect.setVisibility( View.GONE );
        getViewDataBinding().fabKegiatan.setVisibility( View.GONE );
        getViewDataBinding().recyclerKegiatanKelas.setVisibility( View.GONE );
        hideLoading();

    }
}