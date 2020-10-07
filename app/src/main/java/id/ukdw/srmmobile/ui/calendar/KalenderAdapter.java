package id.ukdw.srmmobile.ui.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ukdw.srmmobile.R;

public class KalenderAdapter extends RecyclerView.Adapter<KalenderAdapter.ViewHolder> {

    Context mContext;
    List<RecyclerViewModelKalender> mItemListKalender;

    public KalenderAdapter(Context mContext, List<RecyclerViewModelKalender> mItemListKalender) {
        this.mContext = mContext;
        this.mItemListKalender = mItemListKalender;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.item_kalender, parent, false );
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.event.setText( mItemListKalender.get( position ).getNamaEvent() );
        holder.tanggal.setText( mItemListKalender.get( position ).getTanggal() );
    }

    @Override
    public int getItemCount() {
        return mItemListKalender.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView event, tanggal;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            event = itemView.findViewById( R.id.judulkalender );
            tanggal = itemView.findViewById( R.id.tanggalKalender );
        }
    }
}
