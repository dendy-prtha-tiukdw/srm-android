package id.ukdw.srmmobile.ui.pengumuman;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import id.ukdw.srmmobile.BR;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.data.model.api.response.PengumumanResponse;
import id.ukdw.srmmobile.databinding.FragmentPengumumanBinding;
import id.ukdw.srmmobile.di.component.FragmentComponent;
import id.ukdw.srmmobile.ui.base.BaseFragment;
import id.ukdw.srmmobile.ui.home.HomeActivity;

public class PengumumanFragment extends BaseFragment<FragmentPengumumanBinding, PengumumanViewModel>
        implements PengumumanNavigator {

    List<PengumumanResponse> itemList;
    private FragmentPengumumanBinding fragmentPengumumanBinding;

    public static PengumumanFragment newInstance() {
        Bundle args = new Bundle();
        PengumumanFragment fragment = new PengumumanFragment();
        fragment.setArguments( args );
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        mViewModel.setNavigator( this );
        mViewModel.setContext( getBaseActivity() );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );


    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject( this );
        getBaseActivity().showLoading();
        mViewModel.setContext( getBaseActivity() );
    }

    public void onResume() {
        super.onResume();
        // Set title bar
        ((HomeActivity) getActivity())
                .setActionBarTitle( "Pengumuman" );
    }


    @Override
    public void onGetListPengumuman() {
//        getViewDataBinding().recyclerPengumuman.setHasFixedSize( true );
//        PengumumanAdapter pengumumanAdapter = new PengumumanAdapter( getContext(), initData() );
//        getViewDataBinding().recyclerPengumuman.setLayoutManager( new LinearLayoutManager( getActivity() ) );
//        getViewDataBinding().recyclerPengumuman.setAdapter( pengumumanAdapter );

    }
}
