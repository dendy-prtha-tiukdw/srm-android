package id.ukdw.srmmobile.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


import id.ukdw.srmmobile.R;

public class KelasAdapter extends RecyclerView.Adapter<KelasAdapter.ViewHolder> {

    Context mContext;
    ArrayList<RecyclerViewModelKelas> mItemListKelas;
    private OnItemListener mlistener;

    public interface OnItemListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener (OnItemListener listener){
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

//        viewHolder.item_daftar_kelas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                myDialog = new Dialog(mContext);
//                myDialog.setContentView(R.layout.detailmatkul);
//                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
//
//                TextView namaKelas = (TextView)myDialog.findViewById(R.id.judulKelas);
//                TextView deskripsi = (TextView)myDialog.findViewById(R.id.Deskripsi);
//                TextView isiDeskripsi = (TextView)myDialog.findViewById(R.id.isiDeskripsi);
//                ImageView backButton = (ImageView)myDialog.findViewById(R.id.prevPage);
//                ImageView background = (ImageView)myDialog.findViewById(R.id.background);
//                namaKelas.setText(mItemListKelas.get(viewHolder.getAdapterPosition()).getJudul());
//                isiDeskripsi.setText(mItemListKelas.get(viewHolder.getAdapterPosition()).getDetail());
//
//                Toast.makeText(mContext, "Test Click" + String.valueOf(viewHolder.getAdapterPosition()),Toast.LENGTH_SHORT).show();
//                myDialog.show();
//            }
//        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KelasAdapter.ViewHolder viewHolder, int position) {
        //RecyclerViewModelKelas recyclerViewModelKelas = mItemListKelas.get( position );
        viewHolder.judulItem.setText(mItemListKelas.get(position).getJudul());
        viewHolder.detailItem.setText(mItemListKelas.get(position).getDetail());
    }

    @Override
    public int getItemCount() {
        return mItemListKelas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //LinearLayout item_daftar_kelas;
        TextView judulItem, detailItem;

        public ViewHolder(@NonNull View itemView, final OnItemListener mlistener) {
            super(itemView);
            //item_daftar_kelas = (LinearLayout) itemView.findViewById(R.id.daftarkelas_item);
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
