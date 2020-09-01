package id.ukdw.srmmobile.data.model.api.response;

import lombok.Data;

@Data
public class SignupResponse {

    private String serverAuthCode;
    private String nomorInduk;
    private String provider;
    private String role;
    private String idToken;


    public SignupResponse(String provider, String idToken, String authCode) {
        this.provider=provider;
        this.idToken=idToken;
        this.serverAuthCode= authCode;
    }

    public SignupResponse(String provider, String authCode, String nomorInduk, String role) {
        this.provider=provider;
        this.serverAuthCode= authCode;
        this.nomorInduk = nomorInduk;
        this.role=role;

    }
}
