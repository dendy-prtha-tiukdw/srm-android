package id.ukdw.srmmobile.ui.detailkelas.kegiatanKelas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ukdw.srmmobile.R;

public class DetailKelasLihatKegiatanAdapter extends RecyclerView.Adapter<DetailKelasLihatKegiatanAdapter.ViewHolder> {

    private Context context;
    List<RecyclerViewModelKegiatanKelas> listKegiatan;
    private  OnItemListener mlistener;

    public DetailKelasLihatKegiatanAdapter(Context context, List<RecyclerViewModelKegiatanKelas> listKegiatan) {
        this.context = context;
        this.listKegiatan = listKegiatan;
    }

    public void setOnItemClickListener(DetailKelasLihatKegiatanAdapter.OnItemListener listener){
        mlistener = listener;

    }

    @NonNull
    @Override
    public DetailKelasLihatKegiatanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.item_kegiatan_kelas, parent, false );
        ViewHolder viewHolder = new ViewHolder( view, mlistener );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.matkul.setText( listKegiatan.get( position ).getNamaMatakuliah() + " "+listKegiatan.get( position ).getGroup() );
        holder.judul.setText( listKegiatan.get( position ).getJudulKegiatan() );
        holder.tanggalBerakhir.setText( listKegiatan.get( position ).getTanggalBerakhir() );

    }

    @Override
    public int getItemCount() {
        return listKegiatan.size();
    }
    public interface OnItemListener {
        void onItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView judul,matkul,tanggalBerakhir;

        public ViewHolder(View itemView, OnItemListener mlistener) {
            super( itemView );
            judul = (TextView) itemView.findViewById( R.id.judulKegiaatanDetailKelas );
            matkul = (TextView) itemView.findViewById( R.id.groupKegiatanDetailKelas );
            tanggalBerakhir = (TextView) itemView.findViewById( R.id.tanggalDeadlineKegiatan );
            itemView.setOnClickListener( viewOnClick -> {
                if (mlistener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mlistener.onItemClick( position );
                    }
                }
            } );
        }
    }
}
