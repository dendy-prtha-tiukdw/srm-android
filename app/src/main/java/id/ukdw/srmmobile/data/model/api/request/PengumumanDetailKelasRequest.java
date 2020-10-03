package id.ukdw.srmmobile.data.model.api.request;

import lombok.Data;

@Data
public class PengumumanDetailKelasRequest {

    private String group;
    private String namaMatakuliah;
    private String semester;
    private String tahunAjaran;

    public PengumumanDetailKelasRequest(String group, String namaMatakuliah, String semester, String tahunAjaran) {
        this.group = group;
        this.namaMatakuliah = namaMatakuliah;
        this.semester = semester;
        this.tahunAjaran = tahunAjaran;
    }
}
