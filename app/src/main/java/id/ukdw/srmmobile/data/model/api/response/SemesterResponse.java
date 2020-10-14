package id.ukdw.srmmobile.data.model.api.response;

import lombok.Data;

@Data
public class SemesterResponse {
    private String namaSemester;
    private String tahunAjaran;
    private String tanggalMulai;
    private String tanggalSelesai;

    public SemesterResponse(String namaSemester, String tahunAjaran, String tanggalMulai, String tanggalSelesai) {
        this.namaSemester = namaSemester;
        this.tahunAjaran = tahunAjaran;
        this.tanggalMulai = tanggalMulai;
        this.tanggalSelesai = tanggalSelesai;
    }
}
