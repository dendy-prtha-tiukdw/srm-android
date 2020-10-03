package id.ukdw.srmmobile.data.model.api.response;

import lombok.Data;

@Data
public class PengumumanDetailKelasResponse {
    String namaMatakuliah;
    String namaDosen;
    String group;
    String tahunAjaran;
    String semester;
    String pengumuman;
    String tanggalInput;

    public PengumumanDetailKelasResponse(String namaMatakuliah, String namaDosen, String group, String tahunAjaran, String semester, String pengumuman, String tanggalInput) {
        this.namaMatakuliah = namaMatakuliah;
        this.namaDosen = namaDosen;
        this.group = group;
        this.tahunAjaran = tahunAjaran;
        this.semester = semester;
        this.pengumuman = pengumuman;
        this.tanggalInput = tanggalInput;
    }
}
