package id.ukdw.srmmobile.data.model.api;

import lombok.Data;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.data.model.api
 * <p>
 * User: dendy
 * Date: 30/08/2020
 * Time: 16:09
 * <p>
 * Description : ErrorMessage
 */
@Data
public class ErrorMessage {
    private String message;
    private String code;
    private String detail;

    /**
     * @param message
     * @param code
     * @param detail
     */
    public ErrorMessage(String message, String code, String detail) {
        super();
        this.message = message;
        this.code = code;
        this.detail = detail;
    }

    public ErrorMessage(String message) {
        super();
        this.message = message;
        this.code = null;
        this.detail = null;
    }
}
