package id.ukdw.srmmobile.ui.detailkelas;

import java.util.List;

import id.ukdw.srmmobile.data.model.api.response.PesertaKelasResponse;

public interface DetailKelasNavigator {

    void handleError(Throwable throwable);

    void onGetDetailKelasCompleted(String namaMatakuliah, String group, String hari, String jam, String semester, String tahunAjaran, List<String> namaDosen);

    void onGetPesertaKelasCompleted(List<PesertaKelasResponse> pesertaKelasResponses);
}
