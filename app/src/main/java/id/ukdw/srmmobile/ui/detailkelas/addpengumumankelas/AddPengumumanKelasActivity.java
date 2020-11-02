package id.ukdw.srmmobile.ui.detailkelas.addpengumumankelas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import id.ukdw.srmmobile.BR;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.databinding.ActivityTambahPengumumanKelasBinding;
import id.ukdw.srmmobile.di.component.ActivityComponent;
import id.ukdw.srmmobile.ui.base.BaseActivity;
import id.ukdw.srmmobile.ui.detailkelas.DetailKelasPengumumanActivity;
import id.ukdw.srmmobile.ui.login.LoginActivity;

public class AddPengumumanKelasActivity extends BaseActivity<ActivityTambahPengumumanKelasBinding, AddPengumumanKelasViewModel>
        implements AddPengumumanKelasNavigator {

    ActivityTambahPengumumanKelasBinding  activityAddPengumumanBinding;

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
        String matkul = intent.getStringExtra( "namaMakul" );
        String group = intent.getStringExtra( "group" );
        String semester = intent.getStringExtra( "semester" );
        String tahunAjaran = intent.getStringExtra( "tahunAjaran" );
        getViewDataBinding().savePengumuman.setOnClickListener( v -> {
            String isiPengumuman = String.valueOf( getViewDataBinding().updateisipengumuman.getText() );
            String judulPengumuman = String.valueOf( getViewDataBinding().updatejudulpengumuman.getText() );
            mViewModel.addPengumumanKelas( group,judulPengumuman,matkul,isiPengumuman,semester,tahunAjaran );
        } );

    }

    @Override
    public void onSuccessAddPengumuman() {
        System.out.println("TEST MASUK ACTIVITY");
        Toast.makeText( this, "Saving Pengumuman Succesfull", Toast.LENGTH_LONG ).show();
        Intent movePengumumanKelas = new Intent( AddPengumumanKelasActivity.this, DetailKelasPengumumanActivity.class );
        startActivity( movePengumumanKelas );


    }
}
