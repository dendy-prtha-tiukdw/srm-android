package id.ukdw.srmmobile.ui.pengumuman;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.ukdw.srmmobile.BR;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.databinding.FragmentPengumumanBinding;
import id.ukdw.srmmobile.di.component.FragmentComponent;
import id.ukdw.srmmobile.ui.base.BaseFragment;
import id.ukdw.srmmobile.ui.home.HomeActivity;

public class PengumumanFragment extends BaseFragment<FragmentPengumumanBinding, PengumumanViewModel>
        implements PengumumanNavigator {

    RecyclerView.Adapter mAdapter;
    ArrayList<RecyclerViewModelPengumuman> itemList;

    public static PengumumanFragment newInstance() {
        Bundle args = new Bundle();
        PengumumanFragment fragment = new PengumumanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.pengumumanViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_pengumuman;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewDataBinding().recyclerPengumuman.setHasFixedSize(true);
        PengumumanAdapter pengumumanAdapter = new PengumumanAdapter(getContext(), initData());
        getViewDataBinding().recyclerPengumuman.setLayoutManager(new LinearLayoutManager(getActivity()));
        getViewDataBinding().recyclerPengumuman.setAdapter(pengumumanAdapter);

        pengumumanAdapter.setOnItemClickListener(new PengumumanAdapter.OnItemListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
    }

    private ArrayList<RecyclerViewModelPengumuman> initData() {
        itemList = new ArrayList<>();
        itemList.add(new RecyclerViewModelPengumuman("Tugas Adsis Minggu 11", "12 September 2020"));
        itemList.add(new RecyclerViewModelPengumuman("Tugas Adsis Minggu 11", "12 September 2020"));
        itemList.add(new RecyclerViewModelPengumuman("Tugas Adsis Minggu 11", "12 September 2020"));
        itemList.add(new RecyclerViewModelPengumuman("Tugas Adsis Minggu 11", "12 September 2020"));
        itemList.add(new RecyclerViewModelPengumuman("Tugas Adsis Minggu 11", "12 September 2020"));
        itemList.add(new RecyclerViewModelPengumuman("Tugas Adsis Minggu 11", "12 September 2020"));

        return itemList;
    }

    public void onResume() {
        super.onResume();

        // Set title bar
        ((HomeActivity) getActivity())
                .setActionBarTitle("Pengumuman");
    }

    public void removeItem(int position) {
        itemList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
}
