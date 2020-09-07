package id.ukdw.srmmobile.data.model.api.request;

import lombok.Data;

@Data
public class RefreshAccessTokenRequest {
    private String refreshToken;

    public RefreshAccessTokenRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
