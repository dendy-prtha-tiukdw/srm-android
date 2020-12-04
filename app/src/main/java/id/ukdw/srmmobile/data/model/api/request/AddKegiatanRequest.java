package id.ukdw.srmmobile.data.model.api.request;

import lombok.Data;

@Data
public class AddKegiatanRequest {
    String group;
    String judulKegiatan;
    String kegiatan;
    String namaMatakuliah;
    String semester;
    String tahunAjaran;
    String tanggalBerakhir;

    public AddKegiatanRequest(String group, String judulKegiatan, String kegiatan, String namaMatakuliah, String semester, String tahunAjaran, String tanggalBerakhir) {
        this.group = group;
        this.judulKegiatan = judulKegiatan;
        this.kegiatan = kegiatan;
        this.namaMatakuliah = namaMatakuliah;
        this.semester = semester;
        this.tahunAjaran = tahunAjaran;
        this.tanggalBerakhir = tanggalBerakhir;
    }
}
