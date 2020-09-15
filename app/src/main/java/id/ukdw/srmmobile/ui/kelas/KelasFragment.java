package id.ukdw.srmmobile.ui.kelas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.ui.RecyclerViewModelKelas;
import id.ukdw.srmmobile.ui.detailkelas.detailKelas_activity;
import id.ukdw.srmmobile.ui.home.HomeActivity;

public class KelasFragment extends Fragment {
    View v;

    public KelasFragment() {
    }

    RecyclerView recyclerView;
    ArrayList<RecyclerViewModelKelas> itemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_kelas, container, false);

        recyclerView = v.findViewById(R.id.recyclerKelas);
        recyclerView.setHasFixedSize(true);
        KelasAdapter kelasAdapter = new KelasAdapter(getContext(),initData());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(kelasAdapter);

        kelasAdapter.setOnItemClickListener( new KelasAdapter.OnItemListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent;
                intent = new Intent( getActivity(), detailKelas_activity.class );
                System.out.println("TESTSTSDADA"+itemList.get( position ));
                intent.putExtra( "RecyclerViewModelKelas", itemList.get( position ) );
                startActivity( intent );
            }
        } );

        return v;
    }

    public void onResume() {
        super.onResume();

        // Set title bar
        ((HomeActivity) getActivity())
                .setActionBarTitle("Daftar Kelas");
    }

    private ArrayList<RecyclerViewModelKelas> initData(){
        itemList=new ArrayList<>();
        itemList.add(new RecyclerViewModelKelas("Administrasi Sistem (A)", "Detail Matakuliah"));
        itemList.add(new RecyclerViewModelKelas("Teknik Komputer (B)", "Detail Matakuliah"));
        itemList.add(new RecyclerViewModelKelas("Jaringan Nirkabel (C)", "Detail Matakuliah"));
        itemList.add(new RecyclerViewModelKelas("Pendidikan Pancasila (B)", "Detail Matakuliah"));
        itemList.add(new RecyclerViewModelKelas("Pemrograman Web (D)", "Detail Matakuliah"));
        itemList.add(new RecyclerViewModelKelas("Sistem Basis Data (A)", "Detail Matakuliah"));

        return itemList;
    }

}