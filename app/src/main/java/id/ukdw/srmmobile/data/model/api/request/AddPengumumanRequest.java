package id.ukdw.srmmobile.data.model.api.request;

import lombok.Data;

@Data
public class AddPengumumanRequest {
    public String group;
    public String judulPengumuman;
    public String namaMatakuliah;
    public String pengumuman;
    public String semester;
    public String tahunAjaran;
    public String tanggalBerakhir;

    public AddPengumumanRequest(String group, String judulPengumuman, String namaMatkul, String pengumuman, String semester, String tahunAjaran, String tanggalBerakhir) {
        this.group = group;
        this.judulPengumuman = judulPengumuman;
        this.namaMatakuliah = namaMatkul;
        this.pengumuman = pengumuman;
        this.semester = semester;
        this.tahunAjaran = tahunAjaran;
        this.tanggalBerakhir = tanggalBerakhir;
    }
}
