package id.ukdw.srmmobile.data.model.api.response;

import java.util.List;

import lombok.Data;

@Data
public class DetailkelasResponse {

    String namaMatakuliah;
    String group;
    String hari;
    String tahunAjaran;
    String semester;
    String sesi;
    List<String> namaDosen;


}
