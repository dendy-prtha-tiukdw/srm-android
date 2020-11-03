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
import id.ukdw.srmmobile.ui.daftarkelas.DaftarKelasAdapter;

public class DetailKelasPengumumanAdapter extends RecyclerView.Adapter<DetailKelasPengumumanAdapter.ViewHolder> {
    private Context mContext;
    private List<RecyclerVIewModelPengumumanKelas> listPengumuman;
    private OnItemListener mlistener;

    public DetailKelasPengumumanAdapter(Context mContext, List<RecyclerVIewModelPengumumanKelas> listPengumuman) {
        this.mContext = mContext;
        this.listPengumuman = listPengumuman;
    }

    public void setOnItemClickListener(DetailKelasPengumumanAdapter.OnItemListener listener) {
        mlistener = listener;
    }

    @NonNull
    @Override
    public DetailKelasPengumumanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.item_pengumuman_kelas, parent, false );
        ViewHolder viewHolder = new ViewHolder( view, mlistener );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailKelasPengumumanAdapter.ViewHolder holder, int position) {
        holder.matkul.setText( listPengumuman.get( position ).getNamaMatakuliah() + " " + listPengumuman.get( position ).getGroup() );
        holder.Judul.setText( listPengumuman.get( position ).getJudulPengumuman() );


    }

    @Override
    public int getItemCount() {
        return listPengumuman.size();
    }

    public interface OnItemListener {
        void onItemClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView Judul, matkul;

        public ViewHolder(View itemView, final DetailKelasPengumumanAdapter.OnItemListener mlistener) {
            super( itemView );

            Judul = (TextView) itemView.findViewById( R.id.judulPengumumanDetailKelas );
            matkul = (TextView) itemView.findViewById( R.id.groupPengumumanDetailKelas );
            itemView.setOnClickListener( viewOnclick -> {
                if (mlistener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mlistener.onItemClick( position );
                    }
                }
            } );
        }
    }


//    private String convertTime(String time) {
//
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
//        SimpleDateFormat format1 = new SimpleDateFormat("EEEE-dd-MM-yyyy HH:mm:ss");
//        java.util.Date date = null;
//
//        try {
//            date = format.parse(time);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        String convertedDate = format1.format(date);
//
//        return convertedDate;
//    }
}
