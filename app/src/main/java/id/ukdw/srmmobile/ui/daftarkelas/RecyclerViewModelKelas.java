package id.ukdw.srmmobile.ui.daftarkelas;

import android.os.Parcel;
import android.os.Parcelable;

public class  RecyclerViewModelKelas implements Parcelable {
    String Judul;
    String Detail;
    String hari;
    String jam;
    String tahunAjaran;
    String semester;

    protected RecyclerViewModelKelas(Parcel in) {
        Judul = in.readString();
        Detail = in.readString();
    }

    public RecyclerViewModelKelas(String Judul, String Detail, String hari, String jam, String tahunAjaran, String semester) {
        this.Judul = Judul;
        this.Detail = Detail;
        this.hari = hari;
        this.jam = jam;
        this.tahunAjaran = tahunAjaran;
        this.semester = semester;
    }

    public static final Creator<RecyclerViewModelKelas> CREATOR = new Creator<RecyclerViewModelKelas>() {
        @Override
        public RecyclerViewModelKelas createFromParcel(Parcel in) {
            return new RecyclerViewModelKelas(in);
        }

        @Override
        public RecyclerViewModelKelas[] newArray(int size) {
            return new RecyclerViewModelKelas[size];
        }
    };

    public String getJudul(){
        return Judul + " "+ Detail;
    }

    public String getJudul1(){
        return Judul;
    }

    public void setJudul(String Judul) {
        this.Judul = Judul;
    }

    public String getDetail() {
        return  hari + " " + jam;
    }

    public String getTahunAjaran() {
        return tahunAjaran;
    }

    public String getSemester() {
        return semester;
    }

    public String getDetail1() {
        return  Detail;
    }

    public void setDetail(String Detail) {
        this.Detail = Detail;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Judul);
        dest.writeString(Detail);
    }
}
