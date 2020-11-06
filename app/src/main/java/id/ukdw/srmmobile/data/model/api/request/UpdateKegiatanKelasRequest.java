package id.ukdw.srmmobile.data.model.api.request;

import lombok.Data;

@Data
public class UpdateKegiatanKelasRequest {
    String idKegiatan;
    String isComplete;
    String judulKegiatan;
    String kegiatan;
    String tanggalBerakhir;
    String tanngalDibuat;

    public UpdateKegiatanKelasRequest(String idKegiatan, String isComplete, String judulKegiatan, String kegiatan, String tanggalBerakhir, String tanngalDibuat) {
        this.idKegiatan = idKegiatan;
        this.isComplete = isComplete;
        this.judulKegiatan = judulKegiatan;
        this.kegiatan = kegiatan;
        this.tanggalBerakhir = tanggalBerakhir;
        this.tanngalDibuat = tanngalDibuat;
    }
}
