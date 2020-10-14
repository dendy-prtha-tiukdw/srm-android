package id.ukdw.srmmobile.ui.calendar;

import com.google.api.client.util.DateTime;

import java.io.Serializable;

import lombok.Data;

@Data
public class RecyclerViewModelKalender implements Serializable {
    private String namaEvent;
    private String tanggal;

    public RecyclerViewModelKalender(String namaEvent, String tanggal) {
        this.namaEvent = namaEvent;
        this.tanggal = tanggal;
    }


}
