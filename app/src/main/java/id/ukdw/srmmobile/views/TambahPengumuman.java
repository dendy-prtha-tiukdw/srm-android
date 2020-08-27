package id.ukdw.srmmobile.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import id.ukdw.srmmobile.R;

public class TambahPengumuman extends AppCompatActivity {
    EditText editJudul;
    EditText editDeskripsi;
//    Spinner pilihKelas;
    ImageView prevpage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addpengumuman);

        editJudul = findViewById(R.id.editTextJudul);
        editDeskripsi = findViewById(R.id.editTextDeskripsi);
        editJudul.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && editJudul.getText().toString()!=null){
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });

        editDeskripsi.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && editJudul.getText().toString()!=null){
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });


        prevpage = findViewById(R.id.prevPage);
        prevpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TambahPengumuman.this, PengumumanFragment.class);
                startActivity(intent);
            }
        });

    }
}
