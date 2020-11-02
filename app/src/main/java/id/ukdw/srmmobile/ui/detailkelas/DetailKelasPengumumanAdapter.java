package id.ukdw.srmmobile.ui.detailkelas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.data.model.api.response.PengumumanDetailKelasResponse;
import id.ukdw.srmmobile.data.model.api.response.PesertaKelasResponse;

public class DetailKelasPengumumanAdapter extends  RecyclerView.Adapter<DetailKelasPengumumanAdapter.ViewHolder> {
    private Context mContext;
    private List<PengumumanDetailKelasResponse> listPengumuman;

    public DetailKelasPengumumanAdapter(Context mContext, List<PengumumanDetailKelasResponse> listPengumuman) {
        this.mContext = mContext;
        this.listPengumuman = listPengumuman;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.item_pengumuman_kelas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailKelasPengumumanAdapter.ViewHolder holder, int position) {
        holder.matkul.setText(listPengumuman.get(position).getNamaMatakuliah() + " " + listPengumuman.get( position ).getGroup());
        holder.Judul.setText( listPengumuman.get( position ).getJudulPengumuman() );
        holder.NamaDosen.setText(listPengumuman.get(position).getNamaDosen());
        holder.tanggal.setText( convertTime( listPengumuman.get( position ).getTanggalInput() )  );
        holder.Detail.setText( listPengumuman.get( position ).getPengumuman() );
    }

    @Override
    public int getItemCount() {
        return listPengumuman.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView NamaDosen, Judul, Detail,tanggal,matkul;

        public ViewHolder(View itemView) {
            super(itemView);
            NamaDosen = (TextView) itemView.findViewById(R.id.NamaDosenPengumumanDetailKelas);
            Judul = (TextView) itemView.findViewById( R.id.judulPengumumanDetailKelas );
            matkul = (TextView) itemView.findViewById(R.id.groupPengumumanDetailKelas);
            Detail = (TextView) itemView.findViewById( R.id.detailPengumumanDetailKelas );
            tanggal = (TextView)itemView.findViewById( R.id.tanggalPengumumanDetailKelas );
        }
    }
    private String convertTime(String time) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        SimpleDateFormat format1 = new SimpleDateFormat("EEEE-dd-MM-yyyy HH:mm:ss");
        java.util.Date date = null;

        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String convertedDate = format1.format(date);

        return convertedDate;
    }
}
