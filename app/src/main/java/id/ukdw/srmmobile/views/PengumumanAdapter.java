package id.ukdw.srmmobile.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.ukdw.srmmobile.R;

public class PengumumanAdapter extends RecyclerView.Adapter<PengumumanAdapter.ViewHolder> {

    Context mContext;
    ArrayList<RecyclerViewModelPengumuman> mItemListPengumuman;
    private OnItemListener mlistener;

    public interface OnItemListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener (OnItemListener listener){
        mlistener = listener;
    }

    public PengumumanAdapter(Context mContext, ArrayList<RecyclerViewModelPengumuman> mItemListPengumuman) {
        this.mContext = mContext;
        this.mItemListPengumuman= mItemListPengumuman;
    }

    @NonNull
    @Override
    public PengumumanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowitempengumuman, parent, false);
        ViewHolder viewHolder = new ViewHolder(v, mlistener);

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
    public void onBindViewHolder(@NonNull PengumumanAdapter.ViewHolder viewHolder, int position) {
        //RecyclerViewModelKelas recyclerViewModelKelas = mItemListKelas.get( position );
        RecyclerViewModelPengumuman currentItem = mItemListPengumuman.get(position);
        viewHolder.judulPengumuman.setText(currentItem.getJudulPengumuman());
        viewHolder.tanggalPengumuman.setText(currentItem.getTanggalPengumuman());
    }

    @Override
    public int getItemCount() {
        return mItemListPengumuman.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //LinearLayout item_daftar_kelas;
        TextView judulPengumuman, tanggalPengumuman;
        ImageView mDelete;

        public ViewHolder(@NonNull View itemView, final OnItemListener mlistener) {
            super(itemView);
            //item_daftar_kelas = (LinearLayout) itemView.findViewById(R.id.daftarkelas_item);

            judulPengumuman = itemView.findViewById(R.id.judulPengumuman);
            tanggalPengumuman = itemView.findViewById(R.id.tanggalPengumuman);
            mDelete = itemView.findViewById(R.id.deletePengumuman);

            itemView.setOnClickListener( new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mlistener != null){
                        int position = getAdapterPosition();
                        System.out.println("CEK SINI CEK ITEMVIEW = " + position);
                        if (position != RecyclerView.NO_POSITION){
                            mlistener.onItemClick(position);
                        }
                    }
                }
            });

            mDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener != null){
                        int position = getAdapterPosition();
                        System.out.println("CEK SINI CEK MDELETE = " + position);
                        if (position != RecyclerView.NO_POSITION){
                            mlistener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }
}
