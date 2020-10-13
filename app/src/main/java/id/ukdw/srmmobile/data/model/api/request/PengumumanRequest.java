package id.ukdw.srmmobile.data.model.api.request;

import lombok.Data;

@Data
public class PengumumanRequest {
    private String jmlhPengumuman;

    public PengumumanRequest(String jmlhPengumuman) {
        this.jmlhPengumuman = jmlhPengumuman;
    }
}
