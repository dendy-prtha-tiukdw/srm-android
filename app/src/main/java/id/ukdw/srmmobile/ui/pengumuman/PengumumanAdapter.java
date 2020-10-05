package id.ukdw.srmmobile.ui.pengumuman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.data.model.api.response.PengumumanResponse;

public class PengumumanAdapter extends RecyclerView.Adapter<PengumumanAdapter.ViewHolder> {

    Context mContext;
    List<PengumumanResponse> mItemListPengumuman;


    public PengumumanAdapter(Context mContext, ArrayList<PengumumanResponse> mItemListPengumuman) {
        this.mContext = mContext;
        this.mItemListPengumuman= mItemListPengumuman;
    }

    @NonNull
    @Override
    public PengumumanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowitempengumuman, parent, false);
        return new ViewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull PengumumanAdapter.ViewHolder viewHolder, int position) {


    }

    @Override
    public int getItemCount() {
        return mItemListPengumuman.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super( v );
        }
    }
}
