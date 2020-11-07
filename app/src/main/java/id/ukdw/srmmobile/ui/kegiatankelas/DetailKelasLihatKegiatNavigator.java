package id.ukdw.srmmobile.ui.kegiatankelas;

import java.util.List;

import id.ukdw.srmmobile.data.model.api.response.KegiatanDetailKelasResponse;

public interface DetailKelasLihatKegiatNavigator {
    void onGetListKegiatanKelas(List<KegiatanDetailKelasResponse> kegiatanDetailKelasResponses);
}
