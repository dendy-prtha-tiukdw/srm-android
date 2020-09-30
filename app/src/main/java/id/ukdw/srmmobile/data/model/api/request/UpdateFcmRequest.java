package id.ukdw.srmmobile.data.model.api.request;

import lombok.Data;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.data.model.api.request
 * <p>
 * User: dendy
 * Date: 30/09/2020
 * Time: 10:36
 * <p>
 * Description : UpdateFcmRequest
 */
@Data
public class UpdateFcmRequest {
    private String fcmToken;

    public UpdateFcmRequest(String fcmToken){
        this.fcmToken = fcmToken;
    }
}
