package id.ukdw.srmmobile.data.model.api.request;

import lombok.Data;

@Data
public class UpdatePengumumanKelasRequest {
    String idPengumuman;
    String judulPengumuman;
    String pengumuman;

    public UpdatePengumumanKelasRequest(String idPengumuman, String judulPengumuman, String pengumuman) {
        this.idPengumuman = idPengumuman;
        this.judulPengumuman = judulPengumuman;
        this.pengumuman = pengumuman;
    }
}
