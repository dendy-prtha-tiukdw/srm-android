package id.ukdw.srmmobile.ui.pengumuman;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import id.ukdw.srmmobile.BR;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.data.model.api.response.PengumumanResponse;
import id.ukdw.srmmobile.data.model.api.response.UpdateSemingguResponse;
import id.ukdw.srmmobile.databinding.FragmentPengumumanBinding;
import id.ukdw.srmmobile.di.component.FragmentComponent;
import id.ukdw.srmmobile.ui.base.BaseFragment;
import id.ukdw.srmmobile.ui.home.HomeActivity;

public class PengumumanFragment extends BaseFragment<FragmentPengumumanBinding, PengumumanViewModel>
        implements PengumumanNavigator {


    List<UpdateSemingguResponse> itemList;
    private FragmentPengumumanBinding fragmentPengumumanBinding;
    String defvalue = String.valueOf(R.string.default_spinner_pengumuman);
    private String TAG = PengumumanFragment.class.getSimpleName();

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentPengumumanBinding = getViewDataBinding();

        mViewModel.setNavigator(this);
        mViewModel.setContext(getBaseActivity());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.getListPengumuman();
        getViewDataBinding().reconnect.setOnClickListener( v -> {
            getViewDataBinding().txtEventConnectTimeOut.setVisibility( View.GONE );
            getViewDataBinding().reconnect.setVisibility( View.GONE );
            mViewModel.getListPengumuman();
            getBaseActivity().showLoading();
        } );
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    public void onResume() {
        super.onResume();
        // Set title bar
        ((HomeActivity) getActivity())
                .setActionBarTitle("Update perkuliahan");
    }

    @Override
    public void onGetListPengumuman(List<UpdateSemingguResponse> pengumumanResponseList) {
        if (pengumumanResponseList.isEmpty()){
            getViewDataBinding().txtUpdatePerkuliahanEmpty.setVisibility( View.VISIBLE );
        }
        itemList = pengumumanResponseList;
        PengumumanAdapter pengumumanAdapter = new PengumumanAdapter(getContext(), pengumumanResponseList);
        getViewDataBinding().recyclerPengumuman.setHasFixedSize(true);
        getViewDataBinding().recyclerPengumuman.setLayoutManager(new LinearLayoutManager(getActivity()));
        getViewDataBinding().recyclerPengumuman.setAdapter(pengumumanAdapter);
        getBaseActivity().hideLoading();

    }

    public void isLoading(boolean flag) {
        if (flag) {
            getBaseActivity().showLoading();
        } else {
            getBaseActivity().hideLoading();
        }
    }

    @Override
    public void onGetError() {
        getViewDataBinding().txtEventConnectTimeOut.setVisibility( View.VISIBLE );
        getViewDataBinding().txtEventConnectTimeOut1.setVisibility( View.VISIBLE );
        getViewDataBinding().reconnect.setVisibility( View.VISIBLE );
        getBaseActivity().hideLoading();

    }

    @Override
    public void onServerError() {
        getViewDataBinding().txtErrorServerRequest.setVisibility( View.VISIBLE );
        getBaseActivity().hideLoading();

    }


}
