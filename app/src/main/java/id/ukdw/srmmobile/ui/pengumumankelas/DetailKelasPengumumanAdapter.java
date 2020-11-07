package id.ukdw.srmmobile.ui.pengumumankelas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ukdw.srmmobile.R;

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

}
