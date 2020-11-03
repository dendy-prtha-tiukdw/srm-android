package id.ukdw.srmmobile.ui.detailkelas;

import java.io.Serializable;

import lombok.Data;

@Data
public class RecyclerVIewModelPengumumanKelas implements Serializable {
    private String idPengumuman;
    private String namaMatakuliah;
    private String namaDosen;
    private String group;
    private String tahunAjaran;
    private String semester;
    private String pengumuman;
    private String tanggalInput;
    private String judulPengumuman;
    private String state;

    public RecyclerVIewModelPengumumanKelas(String idPengumuman, String namaMatakuliah,
                                            String namaDosen, String group, String tahunAjaran, String semester,
                                            String pengumuman, String tanggalInput, String judulPengumuman, String state) {
        this.idPengumuman = idPengumuman;
        this.namaMatakuliah = namaMatakuliah;
        this.namaDosen = namaDosen;
        this.group = group;
        this.tahunAjaran = tahunAjaran;
        this.semester = semester;
        this.pengumuman = pengumuman;
        this.tanggalInput = tanggalInput;
        this.judulPengumuman = judulPengumuman;
        this.state = state;
    }
}
