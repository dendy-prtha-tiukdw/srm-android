package id.ukdw.srmmobile.data.model.api.response;

import lombok.Data;

@Data
public class CalenderResponse {
    private String id;
    private String location;
    private String description;
    private String summary;
    private String start;
    private String end;
    private String status;


}
