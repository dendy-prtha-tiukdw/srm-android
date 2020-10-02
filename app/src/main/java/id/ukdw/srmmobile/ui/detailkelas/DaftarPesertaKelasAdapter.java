package id.ukdw.srmmobile.ui.detailkelas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.data.model.api.response.PesertaKelasResponse;

/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.ui.detailkelas
 * <p>
 * User: dendy
 * Date: 02/10/2020
 * Time: 12:35
 * <p>
 * Description : DaftarPesertaKelasAdapter
 */

public class DaftarPesertaKelasAdapter extends RecyclerView.Adapter<DaftarPesertaKelasAdapter.ViewHolder> {

    private Context mContext;
    private List<PesertaKelasResponse> listPeserta;

    public DaftarPesertaKelasAdapter(Context mContext, List<PesertaKelasResponse> listPeserta) {
        this.mContext = mContext;
        this.listPeserta = listPeserta;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_peserta_kelas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nim.setText(listPeserta.get(position).getNim());
        holder.namaMhs.setText(listPeserta.get(position).getNama());
    }

    @Override
    public int getItemCount() {
        return listPeserta.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nim, namaMhs;

        public ViewHolder(View itemView) {
            super(itemView);
            nim = (TextView) itemView.findViewById(R.id.txtNim);
            namaMhs = (TextView) itemView.findViewById(R.id.txtNamaMhs);
        }
    }
}
