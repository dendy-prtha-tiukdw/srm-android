package id.ukdw.srmmobile.data.model.api.response;

import java.util.List;

import lombok.Data;

@Data
public class DetailKelasResponse {

    String namaMatakuliah;
    String group;
    String hari;
    String tahunAjaran;
    String semester;
    String sesi;
    List<String> namaDosen;
}
