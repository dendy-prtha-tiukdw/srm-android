package id.ukdw.srmmobile.data.model.api.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String serverAuthCode;
    private String clientType;

    public LoginRequest(String authCode, String clientType) {
        this.serverAuthCode = authCode;
        this.clientType = clientType;
    }
}
