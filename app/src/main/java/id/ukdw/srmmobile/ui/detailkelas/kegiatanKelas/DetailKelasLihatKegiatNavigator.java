package id.ukdw.srmmobile.ui.detailkelas.kegiatanKelas;

import java.util.List;

import id.ukdw.srmmobile.data.model.api.response.KegiatanDetailKelasResponse;

public interface DetailKelasLihatKegiatNavigator {
    void onGetListKegiatanKelas(List<KegiatanDetailKelasResponse> kegiatanDetailKelasResponses);
}
