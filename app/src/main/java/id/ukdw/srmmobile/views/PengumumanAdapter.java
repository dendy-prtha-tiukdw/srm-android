package id.ukdw.srmmobile.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ukdw.srmmobile.R;

public class PengumumanAdapter extends RecyclerView.Adapter<PengumumanAdapter.ViewHolder> {

    List<RecyclerViewModel> itemListPengumuman;

    public PengumumanAdapter(List<RecyclerViewModel> itemList) {
        this.itemListPengumuman = itemList;
    }

    @NonNull
    @Override
    public PengumumanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowitempengumuman, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PengumumanAdapter.ViewHolder holder, int position) {
        holder.itemImage.setImageResource(itemListPengumuman.get(position).getImage());
        holder.itemtxt.setText(itemListPengumuman.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return itemListPengumuman.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        TextView itemtxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.itemImg);
            itemtxt = itemView.findViewById(R.id.itemPengumuman);

        }
    }
}
