package id.ukdw.srmmobile.data.model.api.request;

import lombok.Data;

@Data
public class AddKegiatanRequest {
    String group;
    String judulKegiatan;
    String kegiatan;
    String namaMatkul;
    String semester;
    String tahunAjaran;
    String tanggalBerakhir;

    public AddKegiatanRequest(String group, String judulKegiatan, String kegiatan, String namaMatkul, String semester, String tahunAjaran, String tanggalBerakhir) {
        this.group = group;
        this.judulKegiatan = judulKegiatan;
        this.kegiatan = kegiatan;
        this.namaMatkul = namaMatkul;
        this.semester = semester;
        this.tahunAjaran = tahunAjaran;
        this.tanggalBerakhir = tanggalBerakhir;
    }
}
