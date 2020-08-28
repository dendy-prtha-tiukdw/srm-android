package id.ukdw.srmmobile.views;

public class RecyclerViewModelPengumuman {

    String JudulPengumuman;
    String TanggalPengumuman;

    public String getJudulPengumuman() {
        return JudulPengumuman;
    }

    public void setJudulPengumuman(String judulPengumuman) {
        JudulPengumuman = judulPengumuman;
    }

    public String getTanggalPengumuman() {
        return TanggalPengumuman;
    }

    public void setTanggalPengumuman(String tanggalPengumuman) {
        TanggalPengumuman = tanggalPengumuman;
    }
    
    public RecyclerViewModelPengumuman(String judul, String tanggal) {
        this.JudulPengumuman = judul;
        this.TanggalPengumuman= tanggal;
    }

}
