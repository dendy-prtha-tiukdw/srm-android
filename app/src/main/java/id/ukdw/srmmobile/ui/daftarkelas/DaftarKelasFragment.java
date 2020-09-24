package id.ukdw.srmmobile.ui.daftarkelas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import id.ukdw.srmmobile.BR;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.databinding.FragmentDaftarKelasBinding;
import id.ukdw.srmmobile.di.component.FragmentComponent;
import id.ukdw.srmmobile.ui.detailkelas.RecyclerViewModelKelas;
import id.ukdw.srmmobile.ui.base.BaseFragment;
import id.ukdw.srmmobile.ui.detailkelas.detailKelas_activity;
import id.ukdw.srmmobile.ui.home.HomeActivity;

public class DaftarKelasFragment extends BaseFragment<FragmentDaftarKelasBinding, DaftarKelasViewModel>
        implements DaftarKelasNavigator {

    ArrayList<RecyclerViewModelKelas> itemList;

    public static DaftarKelasFragment newInstance() {
        Bundle args = new Bundle();
        DaftarKelasFragment fragment = new DaftarKelasFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.daftarKelasViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_daftar_kelas;
    }

    @Nullable
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getViewDataBinding().recyclerKelas.setHasFixedSize(true);
        DaftarKelasAdapter daftarKelasAdapter = new DaftarKelasAdapter(getContext(), initData());
        getViewDataBinding().recyclerKelas.setLayoutManager(new LinearLayoutManager(getActivity()));
        getViewDataBinding().recyclerKelas.setAdapter(daftarKelasAdapter);

        daftarKelasAdapter.setOnItemClickListener(new DaftarKelasAdapter.OnItemListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent;
                intent = new Intent(getActivity(), detailKelas_activity.class);
                System.out.println("TESTSTSDADA" + itemList.get(position));
                intent.putExtra("RecyclerViewModelKelas", itemList.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
        mViewModel.setContext(getBaseActivity());
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    public void onResume() {
        super.onResume();

        // Set title bar
        ((HomeActivity) getActivity())
                .setActionBarTitle("Daftar Kelas");
    }

    private ArrayList<RecyclerViewModelKelas> initData() {
        itemList = new ArrayList<>();
        itemList.add(new RecyclerViewModelKelas("Administrasi Sistem (A)", "Detail Matakuliah"));
        itemList.add(new RecyclerViewModelKelas("Teknik Komputer (B)", "Detail Matakuliah"));
        itemList.add(new RecyclerViewModelKelas("Jaringan Nirkabel (C)", "Detail Matakuliah"));
        itemList.add(new RecyclerViewModelKelas("Pendidikan Pancasila (B)", "Detail Matakuliah"));
        itemList.add(new RecyclerViewModelKelas("Pemrograman Web (D)", "Detail Matakuliah"));
        itemList.add(new RecyclerViewModelKelas("Sistem Basis Data (A)", "Detail Matakuliah"));

        return itemList;
    }

}
