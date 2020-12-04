package id.ukdw.srmmobile.ui.kegiatankelas.detailkegiatankelas;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import id.ukdw.srmmobile.BR;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.databinding.ActivityDetailKegiatanKelasBinding;
import id.ukdw.srmmobile.di.component.ActivityComponent;
import id.ukdw.srmmobile.ui.base.BaseActivity;
import id.ukdw.srmmobile.ui.detailkelas.DetailKelasActivity;
import id.ukdw.srmmobile.ui.kegiatankelas.DetailKelasLihatKegiatanActivity;
import id.ukdw.srmmobile.ui.kegiatankelas.RecyclerViewModelKegiatanKelas;
import id.ukdw.srmmobile.ui.pengumumankelas.RecyclerVIewModelPengumumanKelas;

public class DetailKegiatanKelasActivity extends BaseActivity<ActivityDetailKegiatanKelasBinding, DetailKegiatanKelasViewModel>
        implements DetailKegiatanKelasNavigator {

    public static final String DETAIL_KEGIATAN_DATA = "DETAIL_KEGIATAN_DATA";
    public static final String STATE_ON_CLICK = "ONCLICK";
    public static final String STATE_ADD = "ADD";
    ActivityDetailKegiatanKelasBinding activityDetailKegiatanKelasBinding;
    String matkul;
    String group;
    String tahunAjaran;
    String semester;
    String state;
    TextView tanggalBerakhir;
    RadioButton rbleft,rbright;
    String isComplete="false";

    @Override
    public int getBindingVariable() {
        return BR.detailKegiatanKelasViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_kegiatan_kelas;
    }

    @Override
    public void onBackPressed() {
        Intent moveDetailKelas = new Intent( DetailKegiatanKelasActivity.this, DetailKelasLihatKegiatanActivity.class );
        moveDetailKelas.putExtra( "namaMakul", matkul );
        moveDetailKelas.putExtra( "group", group );
        moveDetailKelas.putExtra( "semester", semester );
        moveDetailKelas.putExtra( "tahunAjaran", tahunAjaran );
        System.out.println("test" + matkul+group+semester+tahunAjaran);
        startActivity( moveDetailKelas );
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        activityDetailKegiatanKelasBinding = getViewDataBinding();
        tanggalBerakhir = getViewDataBinding().kegiatanKelasTanggalDeadline;
        rbleft = getViewDataBinding().rbLeft;
        rbright = getViewDataBinding().rbRight;

        getViewDataBinding().SetTanggalBerakhir.setOnClickListener( v -> {
            showDateTimeDialog( tanggalBerakhir );
        } );
        mViewModel.setNavigator( this );
        mViewModel.setContext( this );
        if (mViewModel.checkRole() == true) {
            getViewDataBinding().editKegiatan.setVisibility( View.VISIBLE );
            getViewDataBinding().deteleKegiatan.setVisibility( View.VISIBLE );
        }
        getViewDataBinding().updatejudulkegiatan.setInputType( InputType.TYPE_NULL );
        getViewDataBinding().updateisikegiatan.setInputType( InputType.TYPE_NULL );
        Intent intent = getIntent();
        matkul = intent.getStringExtra( "namaMakul" );
        group = intent.getStringExtra( "group" );
        semester = intent.getStringExtra( "semester" );
        tahunAjaran = intent.getStringExtra( "tahunAjaran" );
        state = intent.getStringExtra( "state" );

        if (state.equalsIgnoreCase( STATE_ADD )) {
            getViewDataBinding().updateisikegiatan.setInputType( InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE );
            getViewDataBinding().updatejudulkegiatan.setInputType( InputType.TYPE_CLASS_TEXT );
            getViewDataBinding().saveKegiatan.setVisibility( View.VISIBLE );
            getViewDataBinding().textAtasIsiKegiatan.setVisibility( View.VISIBLE );
            getViewDataBinding().textAtasJudulKegiatan.setVisibility( View.VISIBLE );
            getViewDataBinding().deteleKegiatan.setVisibility( View.GONE );
            getViewDataBinding().editKegiatan.setVisibility( View.GONE );
            getViewDataBinding().kegiatanKelasNamaDosen.setVisibility( View.GONE );
            getViewDataBinding().kegiatanKelasTanggalInput.setVisibility( View.GONE );
            getViewDataBinding().kegiatanKelasTanggalBerakhir.setVisibility( View.GONE );
            getViewDataBinding().kegiatanKelasTanggalDeadline.setVisibility( View.VISIBLE );
            getViewDataBinding().SetTanggalBerakhir.setVisibility( View.VISIBLE );
            hideLoading();

            getViewDataBinding().saveKegiatan.setOnClickListener( v -> {
                String isiKegiatan = String.valueOf( getViewDataBinding().updateisikegiatan.getText() );
                String judulKegiatan = String.valueOf( getViewDataBinding().updatejudulkegiatan.getText() );
                String tanggalBerakhir = String.valueOf( getViewDataBinding().kegiatanKelasTanggalDeadline.getText() );
                mViewModel.addkegiatanKelas( group, judulKegiatan, isiKegiatan, matkul, semester, tahunAjaran,tanggalBerakhir );
            } );

        } else {
            RecyclerViewModelKegiatanKelas recyclerViewModelKegiatanKelas = (RecyclerViewModelKegiatanKelas) getIntent().getSerializableExtra( DETAIL_KEGIATAN_DATA );

            if (state.equalsIgnoreCase( STATE_ON_CLICK )) {
                getViewDataBinding().updatejudulkegiatan.setText( recyclerViewModelKegiatanKelas.getJudulKegiatan() );
                getViewDataBinding().updateisikegiatan.setText( recyclerViewModelKegiatanKelas.getIsiKegiatan() );
                getViewDataBinding().kegiatanKelasNamaDosen.setText( recyclerViewModelKegiatanKelas.getNamaDosen() );
                getViewDataBinding().kegiatanKelasTanggalInput.setText( recyclerViewModelKegiatanKelas.getTanggalDibuat()  );
                getViewDataBinding().kegiatanKelasTanggalBerakhir.append( recyclerViewModelKegiatanKelas.getTanggalBerakhir() );
                matkul = recyclerViewModelKegiatanKelas.getNamaMatakuliah();
                group = recyclerViewModelKegiatanKelas.getGroup();
                semester = recyclerViewModelKegiatanKelas.getSemester();
                tahunAjaran = recyclerViewModelKegiatanKelas.getTahunAjaran();
                hideLoading();
            }


            getViewDataBinding().editKegiatan.setOnClickListener( v -> {
                getViewDataBinding().updateKegiatan.setVisibility( View.VISIBLE );
                getViewDataBinding().saveKegiatan.setVisibility( View.GONE );
                getViewDataBinding().deteleKegiatan.setVisibility( View.GONE );
                getViewDataBinding().editKegiatan.setVisibility( View.GONE );
                getViewDataBinding().textAtasIsiKegiatan.setVisibility( View.VISIBLE );
                getViewDataBinding().textAtasJudulKegiatan.setVisibility( View.VISIBLE );
                getViewDataBinding().updateisikegiatan.setInputType( InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE );
                getViewDataBinding().updatejudulkegiatan.setInputType( InputType.TYPE_CLASS_TEXT );
                getViewDataBinding().kegiatanKelasTanggalDeadline.setVisibility( View.VISIBLE );
                getViewDataBinding().kegiatanKelasTanggalDeadline.setText( recyclerViewModelKegiatanKelas.getTanggalBerakhir() );
                getViewDataBinding().SetTanggalBerakhir.setVisibility( View.VISIBLE );
                getViewDataBinding().jdul.setVisibility( View.VISIBLE );
                getViewDataBinding().statusComplete.setVisibility( View.VISIBLE );


            } );

            getViewDataBinding().updateKegiatan.setOnClickListener( v -> {
                String isiKegiatan = String.valueOf( getViewDataBinding().updateisikegiatan.getText() );
                String judulKegiatan = String.valueOf( getViewDataBinding().updatejudulkegiatan.getText() );
                String tanggalBerakhir = String.valueOf( getViewDataBinding().kegiatanKelasTanggalDeadline.getText() );
                mViewModel.UpdatekegiatanKelas( recyclerViewModelKegiatanKelas.getIdKegiatan(),isComplete,
                        judulKegiatan, isiKegiatan,tanggalBerakhir,recyclerViewModelKegiatanKelas.getTanggalDibuat() );
            } );

            getViewDataBinding().deteleKegiatan.setOnClickListener( v -> {

                new AlertDialog.Builder( this )
                        .setTitle( "Delete pengumuman " + recyclerViewModelKegiatanKelas.getJudulKegiatan() + " ?" )
                        .setMessage( "Are you sure you want to delete this entry?" )


                        .setPositiveButton( android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                matkul = recyclerViewModelKegiatanKelas.getNamaMatakuliah();
                                group = recyclerViewModelKegiatanKelas.getGroup();
                                semester = recyclerViewModelKegiatanKelas.getSemester();
                                tahunAjaran = recyclerViewModelKegiatanKelas.getTahunAjaran();
                                mViewModel.deleteKegiatanKelas( recyclerViewModelKegiatanKelas.getIdKegiatan() );
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

    public void onRadioButtonClicked(View view){
        boolean isSelected = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.rbLeft:
                if (isSelected){
                    isComplete= "true";
                }
                break;
            case R.id.rbRight:
                if (isSelected){
                    isComplete= "false";
                }
                break;
        }
    }

    private void showDateTimeDialog(TextView tanggalBerakhir) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set( Calendar.YEAR, year );
                calendar.set( Calendar.MONTH, month );
                calendar.set( Calendar.DAY_OF_MONTH, dayOfMonth );

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set( Calendar.HOUR_OF_DAY, hourOfDay );
                        calendar.set( Calendar.MINUTE, minute );

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

                        tanggalBerakhir.setText( simpleDateFormat.format( calendar.getTime() ) );
                    }
                };

                new TimePickerDialog( DetailKegiatanKelasActivity.this, timeSetListener, calendar.get( Calendar.HOUR_OF_DAY ), calendar.get( Calendar.MINUTE ), false ).show();
            }
        };

        new DatePickerDialog( DetailKegiatanKelasActivity.this, dateSetListener, calendar.get( Calendar.YEAR ), calendar.get( Calendar.MONTH ), calendar.get( Calendar.DAY_OF_MONTH ) ).show();
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject( this );


    }

    @Override
    public void onSuccessAddKegiatan(String data) {
        Toast.makeText( this, "Saving Kegiatan Succesfull", Toast.LENGTH_LONG ).show();
        Intent moveKegiatan = new Intent( DetailKegiatanKelasActivity.this, DetailKelasLihatKegiatanActivity.class );
        moveKegiatan.putExtra( "namaMakul", matkul );
        moveKegiatan.putExtra( "group", group );
        moveKegiatan.putExtra( "semester", semester );
        moveKegiatan.putExtra( "tahunAjaran", tahunAjaran );
        startActivity( moveKegiatan );
        finish();

    }

    @Override
    public void onSuccessDeleteKegiatan(String data) {
        Toast.makeText( this, "Delete Kegiatan Succesfull", Toast.LENGTH_LONG ).show();
        Intent moveKegiatan = new Intent( DetailKegiatanKelasActivity.this, DetailKelasLihatKegiatanActivity.class );
        moveKegiatan.putExtra( "namaMakul", matkul );
        moveKegiatan.putExtra( "group", group );
        moveKegiatan.putExtra( "semester", semester );
        moveKegiatan.putExtra( "tahunAjaran", tahunAjaran );
        startActivity( moveKegiatan );
        finish();

    }

    @Override
    public void onSuccessUpdateKegiatan(String data) {
        Toast.makeText( this, "Update Kegiatan Succesfull", Toast.LENGTH_LONG ).show();
        Intent moveKegiatan = new Intent( DetailKegiatanKelasActivity.this, DetailKelasLihatKegiatanActivity.class );
        moveKegiatan.putExtra( "namaMakul", matkul );
        moveKegiatan.putExtra( "group", group );
        moveKegiatan.putExtra( "semester", semester );
        moveKegiatan.putExtra( "tahunAjaran", tahunAjaran );
        startActivity( moveKegiatan );
        finish();

    }
}