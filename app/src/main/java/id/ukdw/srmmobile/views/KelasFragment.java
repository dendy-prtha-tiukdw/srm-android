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
import java.util.List;

import id.ukdw.srmmobile.R;

public class KelasFragment extends Fragment {
    View v;

    public KelasFragment() {
    }

    RecyclerView recyclerView;
    List<RecyclerViewModel> itemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_kelas, container, false);

        recyclerView = v.findViewById(R.id.recyclerKelas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(new PengumumanAdapter(initData()));

        return v;
    }

    private List<RecyclerViewModel> initData(){
        itemList=new ArrayList<>();
        itemList.add(new RecyclerViewModel(R.drawable.ic_news_admin, "Daftar Kelas"));
        itemList.add(new RecyclerViewModel(R.drawable.ic_news_admin, "Daftar Kelas"));
        itemList.add(new RecyclerViewModel(R.drawable.ic_news_admin, "Daftar Kelas"));
        itemList.add(new RecyclerViewModel(R.drawable.ic_news_admin, "Daftar Kelas"));
        itemList.add(new RecyclerViewModel(R.drawable.ic_news_admin, "Daftar Kelas"));
        itemList.add(new RecyclerViewModel(R.drawable.ic_news_admin, "Daftar Kelas"));
        itemList.add(new RecyclerViewModel(R.drawable.ic_news_admin, "Daftar Kelas"));

        return itemList;
    }

}
