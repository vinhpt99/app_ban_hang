package com.example.shoponline.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.shoponline.R;
import com.example.shoponline.activity.Chitietsanpham;
import com.example.shoponline.activity.sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class sanphamadapter extends RecyclerView.Adapter<sanphamadapter.ItemHolder> {
    Context context; //truyền vào màn hình muốn vẽ
    ArrayList<sanpham> arrayListsanpham;

    public sanphamadapter(Context context, ArrayList<sanpham> arrayListsanpham) {
        this.context = context;
        this.arrayListsanpham = arrayListsanpham;
    }

    @Override
    //bắt đầu khởi tạo lại view đã thiết kế layout
    public ItemHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        //tạo biến view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_san_pham_moi, null);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }
    //kết thúc hàm khởi tạo view đã thiết kế layout
    @Override
    public void onBindViewHolder( ItemHolder holder, int position) {
        //hỗ trợ get set các thuộc tính lên layout
        sanpham sanpham = arrayListsanpham.get(position);
        holder.txtTensanpham.setText(sanpham.getTenSanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGiasanpham.setText("Giá : "+decimalFormat.format(sanpham.getGiaSanpham()) +" VNĐ");
        Picasso.with(context).load(sanpham.getHinhanhSanpham())
                .placeholder(R.drawable.noimg)
                .error(R.drawable.error)
                .into(holder.imgHinhsanpham);

    }


    @Override
    public int getItemCount() {
        return arrayListsanpham.size();
    }

    //bắt buộc phải khai báo viewholeder
    public class ItemHolder extends RecyclerView.ViewHolder
    {
        public ImageView imgHinhsanpham;
        public TextView txtTensanpham, txtGiasanpham;

        public ItemHolder( View itemView) {
            super(itemView);

            imgHinhsanpham = itemView.findViewById(R.id.imgviewLoaisp);
            txtTensanpham = itemView.findViewById(R.id.txttensanpham);
            txtGiasanpham = itemView.findViewById(R.id.txtgiasanpham);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //set sự kiện click chuyển màn hình
                    Intent intent = new Intent(context, Chitietsanpham.class);
                    intent.putExtra("thongtinsanpham", arrayListsanpham.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
