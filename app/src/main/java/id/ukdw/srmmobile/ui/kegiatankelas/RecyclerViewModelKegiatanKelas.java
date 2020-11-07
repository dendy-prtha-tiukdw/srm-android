package id.ukdw.srmmobile.ui.kegiatankelas;

import java.io.Serializable;

import lombok.Data;

@Data
public class RecyclerViewModelKegiatanKelas implements Serializable {
    private String idKegiatan;
    private String namaMatakuliah;
    private String namaDosen;
    private String group;
    private String tahunAjaran;
    private String semester;
    private String isiKegiatan;
    private String tanggalDibuat;
    private String tanggalBerakhir;
    private String judulKegiatan;
    private Boolean complete;

    public RecyclerViewModelKegiatanKelas(String idKegiatan, String namaMatakuliah, String namaDosen,
                                          String group, String tahunAjaran, String semester,
                                          String isiKegiatan, String tanggalDibuat, String tanggalBerakhir,
                                          String judulKegiatan, Boolean complete) {
        this.idKegiatan = idKegiatan;
        this.namaMatakuliah = namaMatakuliah;
        this.namaDosen = namaDosen;
        this.group = group;
        this.tahunAjaran = tahunAjaran;
        this.semester = semester;
        this.isiKegiatan = isiKegiatan;
        this.tanggalDibuat = tanggalDibuat;
        this.tanggalBerakhir = tanggalBerakhir;
        this.judulKegiatan = judulKegiatan;
        this.complete = complete;
    }
}
