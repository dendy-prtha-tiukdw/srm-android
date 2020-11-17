package id.ukdw.srmmobile.data.model.api.request;

import lombok.Data;

@Data
public class CalenderRequest {
    private String  tanggal;

    public CalenderRequest(String tanggal) {
        this.tanggal = tanggal;
    }
}
