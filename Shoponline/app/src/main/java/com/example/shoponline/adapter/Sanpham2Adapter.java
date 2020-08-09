package com.example.shoponline.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoponline.R;
import com.example.shoponline.activity.sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Sanpham2Adapter extends BaseAdapter {

    Context context;
    ArrayList<sanpham> arrayListsp2;

    public Sanpham2Adapter(Context context, ArrayList<sanpham> arrayListsp2) {
        this.context = context;
        this.arrayListsp2 = arrayListsp2;
    }

    @Override
    public int getCount() {
        return arrayListsp2.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListsp2.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    //khi có dữ liệu sẽ gán lại không phải load đi load lại nhiều lần
    public class ViewHolder
    {
        //khai báo lại các thuộc tính bên dong_dien_thoai layout
        public TextView txtTensp2, txtGiasp2, txtMotasp2;
        public ImageView imgsp2;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       Sanpham2Adapter.ViewHolder viewHolder = null;
        if(convertView == null)
        {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_sanpham_hai, null);
            //gọi lại các thuộc tính trong viewholder gắn các id
            viewHolder.txtTensp2 = convertView.findViewById(R.id.textviewTensp2);
            viewHolder.txtGiasp2 = convertView.findViewById(R.id.textviewgiasp2);
            viewHolder.txtMotasp2 = convertView.findViewById(R.id.textviewMotasp2);
            viewHolder.imgsp2 = convertView.findViewById(R.id.imgviewsp2);
            //set id vào cho view holder
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (Sanpham2Adapter.ViewHolder) convertView.getTag();
        }
        sanpham sanpham = (com.example.shoponline.activity.sanpham) getItem(position);
        viewHolder.txtTensp2.setText(sanpham.getTenSanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiasp2.setText("giá: " + decimalFormat.format(sanpham.getGiaSanpham())+ " VNĐ");
        viewHolder.txtMotasp2.setMaxLines(2);
        viewHolder.txtMotasp2.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMotasp2.setText(sanpham.getMotaSanpham());
        //gọi lại thư viện pisacso load hình ảnh
        Picasso.with(context).load(sanpham.getHinhanhSanpham())
                .placeholder(R.drawable.noimg)
                .error(R.drawable.error)
                .into(viewHolder.imgsp2);
        return convertView;
    }
}
