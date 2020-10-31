package id.ukdw.srmmobile.data.model.api.request;

import lombok.Data;

@Data
public class AddPengumumanRequest {
    public String group;
    public String namaMatkul;
    public String pengumuman;
    public String semester;
    public String tahunAjaran;

    public AddPengumumanRequest(String group, String namaMatkul, String pengumuman, String semester, String tahunAjaran) {
        this.group = group;
        this.namaMatkul = namaMatkul;
        this.pengumuman = pengumuman;
        this.semester = semester;
        this.tahunAjaran = tahunAjaran;
    }
}
