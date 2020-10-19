package id.ukdw.srmmobile.ui.daftarkelas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ukdw.srmmobile.R;

public class DaftarKelasAdapter extends RecyclerView.Adapter<DaftarKelasAdapter.ViewHolder> {

    Context mContext;
    List<RecyclerViewModelKelas> mItemListKelas;
    private OnItemListener mlistener;

    public DaftarKelasAdapter(Context mContext, List<RecyclerViewModelKelas> mItemListKelas) {
        this.mContext = mContext;
        this.mItemListKelas = mItemListKelas;
    }

    public void setOnItemClickListener(DaftarKelasAdapter.OnItemListener listener) {
        mlistener = listener;
    }

    @NonNull
    @Override
    public DaftarKelasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( parent.getContext() ).inflate( R.layout.rowitemkelas, parent, false );
        ViewHolder viewHolder = new ViewHolder( v, mlistener );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarKelasAdapter.ViewHolder holder, int position) {
        holder.judulItem.setText( mItemListKelas.get( position ).getNamaMakul() +" "+ mItemListKelas.get(position).getGroup() );
        holder.detailItem.setText( mItemListKelas.get( position ).getHari() + " : " + mItemListKelas.get(position).getJam() );
    }

    @Override
    public int getItemCount() {
        return mItemListKelas.size();
    }

    public interface OnItemListener {
        void onItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView judulItem, detailItem;

        public ViewHolder(@NonNull View itemView, final OnItemListener mlistener) {
            super( itemView );

            judulItem = itemView.findViewById( R.id.judulKelas );
            detailItem = itemView.findViewById( R.id.detailKelas );
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
