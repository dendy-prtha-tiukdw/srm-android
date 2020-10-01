package id.ukdw.srmmobile.data.model.api.request;

import lombok.Data;

@Data
public class PesertaKelasRequest {
    String group;
    String namaMatakuliah;
    String semester;
    String tahunAjaran;

    public PesertaKelasRequest(String group, String namaMatakuliah, String semester, String tahunAjaran) {
        this.group = group;
        this.namaMatakuliah = namaMatakuliah;
        this.semester = semester;
        this.tahunAjaran = tahunAjaran;
    }
}
