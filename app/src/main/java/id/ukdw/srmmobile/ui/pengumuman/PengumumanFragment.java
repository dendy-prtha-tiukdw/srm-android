package id.ukdw.srmmobile.ui.pengumuman;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
        implements PengumumanNavigator, AdapterView.OnItemSelectedListener {


    List<PengumumanResponse> itemList;
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
        Spinner spinner = getViewDataBinding().spinnerPengumuman;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                R.layout.custom_spinner_pengumuman,
                getResources().getStringArray(R.array.spinnerItems));
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_pengumuman);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        if (defvalue != null) {
            int spinnerPosition = adapter.getPosition(defvalue);
            spinner.setSelection(spinnerPosition);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        getBaseActivity().showLoading();
        mViewModel.getListPengumuman(text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    public void onResume() {
        super.onResume();
        // Set title bar
        ((HomeActivity) getActivity())
                .setActionBarTitle("Pengumuman");
    }


    @Override
    public void onGetListPengumuman(List<PengumumanResponse> pengumumanResponseList) {
        itemList = pengumumanResponseList;
        PengumumanAdapter pengumumanAdapter = new PengumumanAdapter(getContext(), pengumumanResponseList);
        getViewDataBinding().recyclerPengumuman.setHasFixedSize(true);
        getViewDataBinding().recyclerPengumuman.setLayoutManager(new LinearLayoutManager(getActivity()));
        getViewDataBinding().recyclerPengumuman.setAdapter(pengumumanAdapter);
        getBaseActivity().hideLoading();
    }

    public void isLoading(boolean flag) {
        if(flag){
            getBaseActivity().showLoading();
        }else{
            getBaseActivity().hideLoading();
        }
    }


}
