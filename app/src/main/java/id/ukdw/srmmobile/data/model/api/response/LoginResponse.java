package id.ukdw.srmmobile.data.model.api.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String accessToken;
    private String idToken;
    private String refreshToken;
    private String nomorInduk;
    private String nama;
    private String email;
    private String imageUrl;
    private String role;
}
