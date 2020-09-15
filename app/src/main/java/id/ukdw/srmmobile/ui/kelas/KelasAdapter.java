package id.ukdw.srmmobile.ui.kelas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.ui.RecyclerViewModelKelas;

public class KelasAdapter extends RecyclerView.Adapter<KelasAdapter.ViewHolder> {

    Context mContext;
    ArrayList<RecyclerViewModelKelas> mItemListKelas;
    private OnItemListener mlistener;

    public interface OnItemListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener (KelasAdapter.OnItemListener listener){
        mlistener = listener;
    }

    public KelasAdapter(Context mContext, ArrayList<RecyclerViewModelKelas> mItemListKelas) {
        this.mContext = mContext;
        this.mItemListKelas = mItemListKelas;
    }

    @NonNull
    @Override
    public KelasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowitemkelas, parent, false);
        ViewHolder viewHolder = new ViewHolder(v,mlistener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KelasAdapter.ViewHolder holder, int position) {
        holder.judulItem.setText(mItemListKelas.get(position).getJudul());
        holder.detailItem.setText(mItemListKelas.get(position).getDetail());
    }

    @Override
    public int getItemCount() {
        return mItemListKelas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView judulItem, detailItem;

        public ViewHolder(@NonNull View itemView, final OnItemListener mlistener) {
            super(itemView);

            judulItem = itemView.findViewById(R.id.judulKelas);
            detailItem = itemView.findViewById(R.id.detailKelas);
            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mlistener.onItemClick( position );
                        }
                    }
                }
            } );
        }
    }
}
