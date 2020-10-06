package id.ukdw.srmmobile.ui.pengumuman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.data.model.api.response.PengumumanResponse;

public class PengumumanAdapter extends RecyclerView.Adapter<PengumumanAdapter.ViewHolder> {

    private Context mContext;
    private List<PengumumanResponse> mItemListPengumuman;


    public PengumumanAdapter(Context mContext, List<PengumumanResponse> mItemListPengumuman) {
        this.mContext = mContext;
        this.mItemListPengumuman= mItemListPengumuman;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowitempengumuman, parent, false);
        return new ViewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull PengumumanAdapter.ViewHolder holder, int position) {
        holder.Judul.setText( mItemListPengumuman.get( position ).getNamaMatakuliah()+" "+ mItemListPengumuman.get( position ).getGroup() );
        holder.NamaDosen.setText( mItemListPengumuman.get( position ).getNamaDosen() );
        holder.tanggal.setText( mItemListPengumuman.get( position ).getTanggalInput() );
        holder.Detail.setText( mItemListPengumuman.get( position ).getPengumuman() );


    }

    @Override
    public int getItemCount() {
        return mItemListPengumuman.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView NamaDosen, Judul, Detail,tanggal;
        public ViewHolder(View v) {
            super( v );
            NamaDosen = (TextView) v.findViewById( R.id.NamaDosenPengumuman );
            Judul = (TextView) v.findViewById( R.id.groupPengumuman );
            Detail = (TextView) v.findViewById( R.id.detailPengumuman );
            tanggal = (TextView) v.findViewById( R.id.tanggalPengumuman );
        }
    }
}
