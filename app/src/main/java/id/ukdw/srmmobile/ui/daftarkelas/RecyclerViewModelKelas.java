package id.ukdw.srmmobile.ui.daftarkelas;

import android.os.Parcel;
import android.os.Parcelable;

public class  RecyclerViewModelKelas implements Parcelable {
    String Judul;
    String Detail;

    protected RecyclerViewModelKelas(Parcel in) {
        Judul = in.readString();
        Detail = in.readString();
    }

    public RecyclerViewModelKelas(String Judul, String Detail) {
        this.Judul = Judul;
        this.Detail = Detail;
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
        return Judul;
    }

    public void setJudul(String Judul) {
        this.Judul = Judul;
    }

    public String getDetail() {
        return Detail;
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
