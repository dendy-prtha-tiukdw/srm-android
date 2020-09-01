package id.ukdw.srmmobile.data.model.api.response;

import java.util.List;

import id.ukdw.srmmobile.data.model.api.ErrorMessage;
import lombok.Data;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.data.model.api.response
 * <p>
 * User: dendy
 * Date: 30/08/2020
 * Time: 16:08
 * <p>
 * Description : a response wrapper
 */
@Data
public class ResponseWrapper<responseType> {
    private responseType data;
    private Object metadata;
    private List<ErrorMessage> errors;

    /**
     * @param data
     * @param metadata
     * @param errors
     */
    public ResponseWrapper(responseType data, Object metadata, List<ErrorMessage> errors) {
        super();
        this.data = data;
        this.metadata = metadata;
        this.errors = errors;
    }

    /**
     * @param data
     */
    public ResponseWrapper(responseType data) {
        super();
        this.data = data;
    }
}
