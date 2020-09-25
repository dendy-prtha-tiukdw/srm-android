package id.ukdw.srmmobile.data.model.api.response;

import lombok.Data;

@Data
public class ProfilResponse {
    private String nomorInduk;

    private String name;

    private String email;

    private String imageUrl;

    private String jenisKelamin;
}
