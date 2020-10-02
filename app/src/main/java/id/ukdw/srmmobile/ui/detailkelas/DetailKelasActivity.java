package id.ukdw.srmmobile.ui.detailkelas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;


import java.util.List;

import id.ukdw.srmmobile.BR;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.data.model.api.response.PesertaKelasResponse;
import id.ukdw.srmmobile.databinding.ActivityDetailKelasBinding;
import id.ukdw.srmmobile.di.component.ActivityComponent;
import id.ukdw.srmmobile.ui.base.BaseActivity;
import id.ukdw.srmmobile.ui.daftarkelas.RecyclerViewModelKelas;
import id.ukdw.srmmobile.ui.login.LoginActivity;

public class DetailKelasActivity extends BaseActivity<ActivityDetailKelasBinding, DetailKelasViewModel>
implements DetailKelasNavigator{

    private ActivityDetailKelasBinding activityDetailKelasBinding;
    ListView listView;



    public static Intent newIntent(Context context) {
        return new Intent(context, DetailKelasActivity.class);
    }


    @Override
    public int getBindingVariable() {
        return BR.detailkelas;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_kelas;
    }



    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject( this );

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailKelasBinding = getViewDataBinding();
        mViewModel.setNavigator( this );
        mViewModel.setContext( this );
        Intent intent = getIntent();
        RecyclerViewModelKelas recyclerViewModelKelas = intent.getParcelableExtra( "RecyclerViewModelKelas" );
        mViewModel.getDetailKelas( recyclerViewModelKelas.getJudul1(),recyclerViewModelKelas.getDetail1(),recyclerViewModelKelas.getSemester(),recyclerViewModelKelas.getTahunAjaran() );
        mViewModel.getPesertaKelas( recyclerViewModelKelas.getJudul1(),recyclerViewModelKelas.getDetail1(),recyclerViewModelKelas.getSemester(),recyclerViewModelKelas.getTahunAjaran() );

    }



    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void onGetDetailKelasCompleted(String namaMatakuliah, String group, String hari, String jam, String semester, String tahunAjaran, List<String> namaDosen) {
        activityDetailKelasBinding.namaMatkul.setText( namaMatakuliah + " "+ group );
        activityDetailKelasBinding.sesi.setText( hari + " "+ jam );
        activityDetailKelasBinding.tahunAjaran.setText( semester +" "+tahunAjaran );

    }

    @Override
    public void onGetPesertaKelasCompleted(List<PesertaKelasResponse> pesertaKelasResponses) {

    }
}
