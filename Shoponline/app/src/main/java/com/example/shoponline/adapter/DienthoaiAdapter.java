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

public class DienthoaiAdapter extends BaseAdapter {
    Context context;
    ArrayList<sanpham> arrayListDienthoai;

    public DienthoaiAdapter(Context context, ArrayList<sanpham> arrayListDienthoai) {
        this.context = context;
        this.arrayListDienthoai = arrayListDienthoai;
    }

    @Override
    public int getCount() {
        return arrayListDienthoai.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListDienthoai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    //khi có dữ liệu sẽ gán lại không phải load đi load lại nhiều lần
    public class ViewHolder
    {
       //khai báo lại các thuộc tính bên dong_dien_thoai layout
        public TextView txtTendienthoai, txtGiadienthoai, txtMotadienthoai;
        public ImageView imgDienthoai;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_dien_thoai, null);
            //gọi lại các thuộc tính trong viewholder gắn các id
            viewHolder.txtTendienthoai = convertView.findViewById(R.id.textviewTensanpham);
            viewHolder.txtGiadienthoai = convertView.findViewById(R.id.textviewgiasanpham);
            viewHolder.txtMotadienthoai = convertView.findViewById(R.id.textviewMotadienthoai);
            viewHolder.imgDienthoai = convertView.findViewById(R.id.imgviewDienthoai);
            //set id vào cho view holder
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        sanpham sanpham = (com.example.shoponline.activity.sanpham) getItem(position);
        viewHolder.txtTendienthoai.setText(sanpham.getTenSanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiadienthoai.setText("giá: " + decimalFormat.format(sanpham.getGiaSanpham())+ " VNĐ");
        viewHolder.txtMotadienthoai.setMaxLines(2);
        viewHolder.txtMotadienthoai.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMotadienthoai.setText(sanpham.getMotaSanpham());
        //gọi lại thư viện pisacso load hình ảnh
        Picasso.with(context).load(sanpham.getHinhanhSanpham())
                .placeholder(R.drawable.noimg)
                .error(R.drawable.error)
                .into(viewHolder.imgDienthoai);
        return convertView;
    }
}
