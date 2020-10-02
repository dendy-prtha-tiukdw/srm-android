package id.ukdw.srmmobile.ui.daftarkelas;

import java.io.Serializable;

import lombok.Data;

@Data
public class RecyclerViewModelKelas implements Serializable {
    private String namaMakul;
    private String group;
    private String hari;
    private String jam;
    private String tahunAjaran;
    private String semester;

    public RecyclerViewModelKelas(String namaMakul, String group, String hari, String jam, String semester, String tahunAjaran) {
        this.namaMakul = namaMakul;
        this.group = group;
        this.hari = hari;
        this.jam = jam;
        this.semester = semester;
        this.tahunAjaran = tahunAjaran;
    }
}
