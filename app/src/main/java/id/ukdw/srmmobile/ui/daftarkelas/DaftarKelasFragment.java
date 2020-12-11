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
    public static final String STATE_ON_NEXT = "ONNEXT";

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewDataBinding().containerError.setVisibility( View.GONE );
        mViewModel.getListKelas();
        getViewDataBinding().reconnect.setOnClickListener( v -> {
            getViewDataBinding().containerError.setVisibility( View.GONE );
            getViewDataBinding().containerSuccess.setVisibility( View.VISIBLE );
            mViewModel.getListKelas();
            getBaseActivity().showLoading();
        } );

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
        getBaseActivity().showLoading();

    }

    public void onResume() {
        super.onResume();
        ((HomeActivity) getBaseActivity()).setActionBarTitle(getString( R.string.titlebar_daftarkelas));
    }


    @Override
    public void updateListDaftarKelas(List<KelasResponse> kelasList) {
        if (kelasList.isEmpty()){
            getBaseActivity().hideLoading();
            getViewDataBinding().txtKelasEmpty.setVisibility( View.VISIBLE );
        }
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
            startActivity(DetailKelasActivity.newIntent(getBaseActivity()).putExtra(DETAIL_KELAS_DATA, kelas).putExtra( "state", STATE_ON_NEXT ));
        });
        getBaseActivity().hideLoading();
    }

    @Override
    public void onGetError() {
        getViewDataBinding().containerError.setVisibility( View.VISIBLE );
        getViewDataBinding().txtDaftarKelasError.setText( R.string.error_koneksi );
        getBaseActivity().hideLoading();
    }

    @Override
    public void onServerError() {
        getViewDataBinding().containerError.setVisibility( View.VISIBLE );
        getViewDataBinding().txtDaftarKelasError.setText( R.string.error_komunikasi_server );
        getViewDataBinding().reconnect.setVisibility( View.GONE );
        getBaseActivity().hideLoading();
    }
}
