package id.ukdw.srmmobile.data.model.api.request;

import lombok.Data;

@Data
public class  ProfilRequest {
    private String idToken;

    public ProfilRequest(String idToken){
        this.idToken = idToken;
    }
}
