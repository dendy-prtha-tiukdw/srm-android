package id.ukdw.srmmobile.data.model.api.request;

import lombok.Data;

@Data
public class DeleteKegiatanRequest {
    String idKegiatan;

    public DeleteKegiatanRequest(String idKegiatan) {
        this.idKegiatan = idKegiatan;
    }
}
