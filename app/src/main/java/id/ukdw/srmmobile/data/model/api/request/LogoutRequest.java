package id.ukdw.srmmobile.data.model.api.request;

import lombok.Data;

@Data
public class LogoutRequest {
    private String accessToken;

    public LogoutRequest(String accessToken) {
        this.accessToken = accessToken;
    }
}
