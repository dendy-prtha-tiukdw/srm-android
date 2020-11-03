package id.ukdw.srmmobile.ui.detailkelas.addpengumumankelas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import id.ukdw.srmmobile.BR;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.databinding.ActivityTambahPengumumanKelasBinding;
import id.ukdw.srmmobile.di.component.ActivityComponent;
import id.ukdw.srmmobile.ui.base.BaseActivity;
import id.ukdw.srmmobile.ui.daftarkelas.RecyclerViewModelKelas;
import id.ukdw.srmmobile.ui.detailkelas.DetailKelasPengumumanActivity;
import id.ukdw.srmmobile.ui.detailkelas.RecyclerVIewModelPengumumanKelas;



public class AddPengumumanKelasActivity extends BaseActivity<ActivityTambahPengumumanKelasBinding, AddPengumumanKelasViewModel>
        implements AddPengumumanKelasNavigator {
    public static final String DETAIL_PENGUMUMAN_DATA = "DETAIL_PENGUMUMAN_DATA";
    public static final String STATE_UPDATE = "UPDATE";
    ActivityTambahPengumumanKelasBinding  activityAddPengumumanBinding;
    String matkul;
    String group;
    String tahunAjaran;
    String semester;

    @Override
    public int getBindingVariable() {
        return BR.addPengumumanKelasViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tambah_pengumuman_kelas;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject( this );
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        activityAddPengumumanBinding = getViewDataBinding();
        mViewModel.setNavigator( this );
        mViewModel.setContext( this );
        Intent intent = getIntent();
        matkul = intent.getStringExtra( "namaMakul" );
        group = intent.getStringExtra( "group" );
        semester = intent.getStringExtra( "semester" );
        tahunAjaran = intent.getStringExtra( "tahunAjaran" );
        RecyclerVIewModelPengumumanKelas recyclerVIewModelPengumumanKelas = (RecyclerVIewModelPengumumanKelas) getIntent().getSerializableExtra(DETAIL_PENGUMUMAN_DATA);
        getViewDataBinding().savePengumuman.setOnClickListener( v -> {
            String isiPengumuman = String.valueOf( getViewDataBinding().updateisipengumuman.getText() );
            String judulPengumuman = String.valueOf( getViewDataBinding().updatejudulpengumuman.getText() );
            mViewModel.addPengumumanKelas( group,judulPengumuman,matkul,isiPengumuman,semester,tahunAjaran );
        } );
        if (recyclerVIewModelPengumumanKelas.getState().equalsIgnoreCase( STATE_UPDATE )){
            getViewDataBinding().updatejudulpengumuman.setText( recyclerVIewModelPengumumanKelas.getJudulPengumuman() );
            getViewDataBinding().updateisipengumuman.setText( recyclerVIewModelPengumumanKelas.getPengumuman()  );
            getViewDataBinding().pengumumanKelasNamaDosen.setText( recyclerVIewModelPengumumanKelas.getNamaDosen() );
            getViewDataBinding().pengumumanKelasTanggalInput.setText( convertTime( recyclerVIewModelPengumumanKelas.getTanggalInput() ) );
        }


    }



    private String convertTime(String time) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        SimpleDateFormat format1 = new SimpleDateFormat("EEEE-dd-MM-yyyy HH:mm:ss");
        java.util.Date date = null;

        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String convertedDate = format1.format(date);

        return convertedDate;
    }

    @Override
    public void onSuccessAddPengumuman(String data) {
        System.out.println("TEST MASUK ACTIVITY");
        Toast.makeText( this, "Saving Pengumuman Succesfull", Toast.LENGTH_LONG ).show();
        Intent movePengumuman = new Intent( AddPengumumanKelasActivity.this, DetailKelasPengumumanActivity.class );
        movePengumuman.putExtra("namaMakul", matkul);
        movePengumuman.putExtra("group",group);
        movePengumuman.putExtra("semester",semester);
        movePengumuman.putExtra("tahunAjaran", tahunAjaran);
        startActivity(movePengumuman);


    }
}
