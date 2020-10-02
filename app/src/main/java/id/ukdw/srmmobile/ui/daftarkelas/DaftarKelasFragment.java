package id.ukdw.srmmobile.ui.daftarkelas;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;


import java.util.ArrayList;
import java.util.List;

import id.ukdw.srmmobile.BR;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.data.model.api.response.KelasResponse;
import id.ukdw.srmmobile.databinding.FragmentDaftarKelasBinding;
import id.ukdw.srmmobile.di.component.FragmentComponent;
import id.ukdw.srmmobile.ui.base.BaseFragment;
import id.ukdw.srmmobile.ui.detailkelas.DetailKelasActivity;
import id.ukdw.srmmobile.ui.home.HomeActivity;

import static id.ukdw.srmmobile.ui.detailkelas.DetailKelasActivity.DETAIL_KELAS_DATA;

public class DaftarKelasFragment extends BaseFragment<FragmentDaftarKelasBinding, DaftarKelasViewModel>
        implements DaftarKelasNavigator {

    List<RecyclerViewModelKelas> itemList;

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
        mViewModel.getListKelas();
    }

    public void onResume() {
        super.onResume();
        ((HomeActivity) getBaseActivity()).setActionBarTitle("Daftar Kelas");
    }


    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void updateListDaftarKelas(List<KelasResponse> kelasList) {
        itemList = new ArrayList<>();

        for (KelasResponse kelasresponse : kelasList) {
            itemList.add(new RecyclerViewModelKelas(
                    kelasresponse.getNamaMatakuliah(),
                    kelasresponse.getGroup(),
                    kelasresponse.getHari(),
                    kelasresponse.getJam(),
                    kelasresponse.getSemester(),
                    kelasresponse.getTahunAjaran())
            );
        }

        getViewDataBinding().recyclerKelas.setHasFixedSize(true);
        DaftarKelasAdapter daftarKelasAdapter = new DaftarKelasAdapter(getContext(), itemList);
        getViewDataBinding().recyclerKelas.setLayoutManager(new LinearLayoutManager(getActivity()));
        getViewDataBinding().recyclerKelas.setAdapter(daftarKelasAdapter);

        daftarKelasAdapter.setOnItemClickListener(position -> {
            RecyclerViewModelKelas kelas = itemList.get(position);
            startActivity(DetailKelasActivity.newIntent(getBaseActivity()).putExtra(DETAIL_KELAS_DATA, kelas));
        });
    }
}
