package id.ukdw.srmmobile.data.model.api.request;

import lombok.Data;

@Data
public class DeletePengumumanKelasRequest {
    String idPengumuman;

    public DeletePengumumanKelasRequest(String idPengumuman) {
        this.idPengumuman = idPengumuman;
    }
}
