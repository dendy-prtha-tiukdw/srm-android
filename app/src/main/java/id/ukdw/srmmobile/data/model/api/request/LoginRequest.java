package id.ukdw.srmmobile.data.model.api.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String provider;
    private String serverAuthCode;

    public LoginRequest(String provider, String authCode) {
        this.provider = provider;
        this.serverAuthCode = authCode;
    }
}
