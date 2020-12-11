package id.ukdw.srmmobile.ui.pengumumankelas;

import java.util.List;

import id.ukdw.srmmobile.data.model.api.response.PengumumanDetailKelasResponse;

public interface DetailKelasPengumumanNavigator {

    void onGetError();

    void onServerError();

    void onGetListDetailKelasPengumuman (List<PengumumanDetailKelasResponse> pengumumanDetailKelasResponses);
}
