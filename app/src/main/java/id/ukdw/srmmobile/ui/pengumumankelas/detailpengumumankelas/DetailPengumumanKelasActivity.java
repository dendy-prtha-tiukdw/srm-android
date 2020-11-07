package id.ukdw.srmmobile.ui.pengumumankelas.detailpengumumankelas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import id.ukdw.srmmobile.BR;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.databinding.ActivityTambahPengumumanKelasBinding;
import id.ukdw.srmmobile.di.component.ActivityComponent;
import id.ukdw.srmmobile.ui.base.BaseActivity;
import id.ukdw.srmmobile.ui.pengumumankelas.DetailKelasPengumumanActivity;
import id.ukdw.srmmobile.ui.pengumumankelas.RecyclerVIewModelPengumumanKelas;


public class DetailPengumumanKelasActivity extends BaseActivity<ActivityTambahPengumumanKelasBinding, DetailPengumumanKelasViewModel>
        implements DetailPengumumanKelasNavigator {
    public static final String DETAIL_PENGUMUMAN_DATA = "DETAIL_PENGUMUMAN_DATA";
    public static final String STATE_ON_CLICK = "ONCLICK";
    public static final String STATE_ADD = "ADD";
    ActivityTambahPengumumanKelasBinding activityAddPengumumanBinding;
    String matkul;
    String group;
    String tahunAjaran;
    String semester;
    String state;

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
        if (mViewModel.checkRole() == true) {
            getViewDataBinding().editPengumuman.setVisibility( View.VISIBLE );
            getViewDataBinding().detelePengumuman.setVisibility( View.VISIBLE );
        }
        getViewDataBinding().updatejudulpengumuman.setInputType( InputType.TYPE_NULL );
        getViewDataBinding().updateisipengumuman.setInputType( InputType.TYPE_NULL );
        Intent intent = getIntent();
        matkul = intent.getStringExtra( "namaMakul" );
        group = intent.getStringExtra( "group" );
        semester = intent.getStringExtra( "semester" );
        tahunAjaran = intent.getStringExtra( "tahunAjaran" );
        state = intent.getStringExtra( "state" );

        if (state.equalsIgnoreCase( STATE_ADD )) {
            getViewDataBinding().updateisipengumuman.setInputType( InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE );
            getViewDataBinding().updatejudulpengumuman.setInputType( InputType.TYPE_CLASS_TEXT );
            getViewDataBinding().savePengumuman.setVisibility( View.VISIBLE );
            getViewDataBinding().detelePengumuman.setVisibility( View.GONE );
            getViewDataBinding().editPengumuman.setVisibility( View.GONE );
            getViewDataBinding().pengumumanKelasNamaDosen.setText( "" );
            getViewDataBinding().pengumumanKelasTanggalInput.setText( "" );
            hideLoading();

            getViewDataBinding().savePengumuman.setOnClickListener( v -> {
                String isiPengumuman = String.valueOf( getViewDataBinding().updateisipengumuman.getText() );
                String judulPengumuman = String.valueOf( getViewDataBinding().updatejudulpengumuman.getText() );
                mViewModel.addPengumumanKelas( group, judulPengumuman, matkul, isiPengumuman, semester, tahunAjaran );
            } );

        } else {
            RecyclerVIewModelPengumumanKelas recyclerVIewModelPengumumanKelas = (RecyclerVIewModelPengumumanKelas) getIntent().getSerializableExtra( DETAIL_PENGUMUMAN_DATA );

            if (state.equalsIgnoreCase( STATE_ON_CLICK )) {
                getViewDataBinding().updatejudulpengumuman.setText( recyclerVIewModelPengumumanKelas.getJudulPengumuman() );
                getViewDataBinding().updateisipengumuman.setText( recyclerVIewModelPengumumanKelas.getPengumuman() );
                getViewDataBinding().pengumumanKelasNamaDosen.setText( recyclerVIewModelPengumumanKelas.getNamaDosen() );
                getViewDataBinding().pengumumanKelasTanggalInput.setText( convertTime( recyclerVIewModelPengumumanKelas.getTanggalInput() ) );
                hideLoading();
            }


            getViewDataBinding().editPengumuman.setOnClickListener( v -> {
                getViewDataBinding().updatePengumuman.setVisibility( View.VISIBLE );
                getViewDataBinding().savePengumuman.setVisibility( View.GONE );
                getViewDataBinding().detelePengumuman.setVisibility( View.GONE );
                getViewDataBinding().editPengumuman.setVisibility( View.GONE );
                getViewDataBinding().updateisipengumuman.setInputType( InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE );
                getViewDataBinding().updatejudulpengumuman.setInputType( InputType.TYPE_CLASS_TEXT );

            } );

            getViewDataBinding().updatePengumuman.setOnClickListener( v -> {
                matkul = recyclerVIewModelPengumumanKelas.getNamaMatakuliah();
                group = recyclerVIewModelPengumumanKelas.getGroup();
                semester = recyclerVIewModelPengumumanKelas.getSemester();
                tahunAjaran = recyclerVIewModelPengumumanKelas.getTahunAjaran();
                String isiPengumuman = String.valueOf( getViewDataBinding().updateisipengumuman.getText() );
                String judulPengumuman = String.valueOf( getViewDataBinding().updatejudulpengumuman.getText() );
                mViewModel.UpdatePengumumanKelas( recyclerVIewModelPengumumanKelas.getIdPengumuman(), judulPengumuman, isiPengumuman );
            } );

            getViewDataBinding().detelePengumuman.setOnClickListener( v -> {

                new AlertDialog.Builder( this )
                        .setTitle( "Delete pengumuman " + recyclerVIewModelPengumumanKelas.getJudulPengumuman() + " ?" )
                        .setMessage( "Are you sure you want to delete this entry?" )


                        .setPositiveButton( android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                matkul = recyclerVIewModelPengumumanKelas.getNamaMatakuliah();
                                group = recyclerVIewModelPengumumanKelas.getGroup();
                                semester = recyclerVIewModelPengumumanKelas.getSemester();
                                tahunAjaran = recyclerVIewModelPengumumanKelas.getTahunAjaran();
                                mViewModel.deletePengumumanKelas( recyclerVIewModelPengumumanKelas.getIdPengumuman() );
                            }
                        } )

                        .setNegativeButton( android.R.string.no, null )
                        .setIcon( android.R.drawable.ic_dialog_alert )
                        .show();
            } );
        }

    }


    private String convertTime(String time) {

        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSSZ" );
        SimpleDateFormat format1 = new SimpleDateFormat( "EEEE-dd-MM-yyyy HH:mm:ss" );
        java.util.Date date = null;

        try {
            date = format.parse( time );
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String convertedDate = format1.format( date );

        return convertedDate;
    }

    @Override
    public void onSuccessAddPengumuman(String data) {
        Toast.makeText( this, "Saving Pengumuman Succesfull", Toast.LENGTH_LONG ).show();
        Intent movePengumuman = new Intent( DetailPengumumanKelasActivity.this, DetailKelasPengumumanActivity.class );
        movePengumuman.putExtra( "namaMakul", matkul );
        movePengumuman.putExtra( "group", group );
        movePengumuman.putExtra( "semester", semester );
        movePengumuman.putExtra( "tahunAjaran", tahunAjaran );
        startActivity( movePengumuman );


    }

    @Override
    public void onSuccessDeletePengumuman(String data) {
        Toast.makeText( this, "Delete Pengumuman Succesfull", Toast.LENGTH_LONG ).show();
        Intent movePengumuman = new Intent( DetailPengumumanKelasActivity.this, DetailKelasPengumumanActivity.class );
        movePengumuman.putExtra( "namaMakul", matkul );
        movePengumuman.putExtra( "group", group );
        movePengumuman.putExtra( "semester", semester );
        movePengumuman.putExtra( "tahunAjaran", tahunAjaran );
        startActivity( movePengumuman );

    }

    @Override
    public void onSuccessUpdatePengumuman(String data) {
        Toast.makeText( this, "Update Pengumuman Succesfull", Toast.LENGTH_LONG ).show();
        Intent movePengumuman = new Intent( DetailPengumumanKelasActivity.this, DetailKelasPengumumanActivity.class );
        movePengumuman.putExtra( "namaMakul", matkul );
        movePengumuman.putExtra( "group", group );
        movePengumuman.putExtra( "semester", semester );
        movePengumuman.putExtra( "tahunAjaran", tahunAjaran );
        startActivity( movePengumuman );

    }
}
