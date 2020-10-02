package id.ukdw.srmmobile.data.model.api.response;

import lombok.Data;

@Data
public class PesertaKelasResponse {
    String nim;
    String nama;

    public PesertaKelasResponse() {
        this.nim = "";
        this.nama = "";
    }
}
