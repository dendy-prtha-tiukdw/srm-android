package id.ukdw.srmmobile.views;

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

public class PengumumanFragment extends Fragment {
    View v;
//    PengumumanAdapter pengumumanAdapter;
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    ArrayList<RecyclerViewModelPengumuman> itemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_pengumuman, container, false);
        recyclerView = v.findViewById(R.id.recyclerPengumuman);
        recyclerView.setHasFixedSize(true);
        PengumumanAdapter pengumumanAdapter = new PengumumanAdapter(getContext(),initData());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(pengumumanAdapter);

        pengumumanAdapter.setOnItemClickListener(new PengumumanAdapter.OnItemListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });

        return v;
    }

    private ArrayList<RecyclerViewModelPengumuman> initData(){
        itemList=new ArrayList<>();
        itemList.add(new RecyclerViewModelPengumuman( "Tugas Adsis Minggu 11", "12 September 2020"));
        itemList.add(new RecyclerViewModelPengumuman( "Tugas Adsis Minggu 11", "12 September 2020"));
        itemList.add(new RecyclerViewModelPengumuman( "Tugas Adsis Minggu 11", "12 September 2020"));
        itemList.add(new RecyclerViewModelPengumuman( "Tugas Adsis Minggu 11", "12 September 2020"));
        itemList.add(new RecyclerViewModelPengumuman( "Tugas Adsis Minggu 11", "12 September 2020"));
        itemList.add(new RecyclerViewModelPengumuman( "Tugas Adsis Minggu 11", "12 September 2020"));

        return itemList;
    }

    public void removeItem(int position){
        itemList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
}
