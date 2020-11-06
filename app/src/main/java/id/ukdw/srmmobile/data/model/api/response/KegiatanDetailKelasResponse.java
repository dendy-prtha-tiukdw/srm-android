package id.ukdw.srmmobile.data.model.api.response;

import lombok.Data;

@Data
public class KegiatanDetailKelasResponse {
    String idKegiatan;
    String namaMatakuliah;
    String namaDosen;
    String group;
    String tahunAjaran;
    String semester;
    String isiKegiatan;
    String tanggalDibuat;
    String tanggalBerakhir;
    String judulKegiatan;
    Boolean complete;
}
