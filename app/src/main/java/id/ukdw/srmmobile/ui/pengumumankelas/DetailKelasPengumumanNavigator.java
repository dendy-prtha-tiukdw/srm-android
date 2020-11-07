package id.ukdw.srmmobile.ui.pengumumankelas;

import java.util.List;

import id.ukdw.srmmobile.data.model.api.response.PengumumanDetailKelasResponse;

public interface DetailKelasPengumumanNavigator {

    void handleError(Throwable throwable);

    void onGetListDetailKelasPengumuman (List<PengumumanDetailKelasResponse> pengumumanDetailKelasResponses);
}
