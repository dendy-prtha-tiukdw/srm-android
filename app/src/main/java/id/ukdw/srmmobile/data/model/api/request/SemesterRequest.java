package id.ukdw.srmmobile.data.model.api.request;

import lombok.Data;

@Data
public class SemesterRequest {
    private String  tanggal;

    public SemesterRequest(String tanggal) {
        this.tanggal = tanggal;
    }
}
