package id.ukdw.srmmobile.data.model.api.response;

import lombok.Data;

@Data
public class RefreshAccessTokenResponse {
    private String accessToken;
    private String idToken;

    public RefreshAccessTokenResponse(String accessToken, String idToken) {
        this.accessToken = accessToken;
        this.idToken = idToken;
    }
}
