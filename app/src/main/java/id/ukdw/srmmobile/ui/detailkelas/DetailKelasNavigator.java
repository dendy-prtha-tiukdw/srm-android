package id.ukdw.srmmobile.ui.detailkelas;

import java.util.List;

import id.ukdw.srmmobile.data.model.api.response.DetailKelasResponse;
import id.ukdw.srmmobile.data.model.api.response.PesertaKelasResponse;

public interface DetailKelasNavigator {

    void handleError(Throwable throwable);

    void onGetDetailKelasCompleted(DetailKelasResponse detailkelasResponse);

    void onGetPesertaKelasCompleted(List<PesertaKelasResponse> pesertaKelasResponses);
}
