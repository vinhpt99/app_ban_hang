package com.example.shoponline.adapter;
import com.example.shoponline.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoponline.activity.Giohang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GiohangAdapter extends BaseAdapter {
    Context context;
    ArrayList<Giohang> arrayListgiohang;

    public GiohangAdapter(Context context, ArrayList<Giohang> arrayListgiohang) {
        this.context = context;
        this.arrayListgiohang = arrayListgiohang;
    }

    @Override
    public int getCount() {
        return arrayListgiohang.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListgiohang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    //gán ánh xạ run lần thứ hai lên
    public class ViewHolder
    {
        public TextView txttengiohang, txtgiagiohang;
        public ImageView imggiohang;
        public Button btntang, btnvalue, btngiam;



    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null)
        { //view null khai báo lại viewlhoder
            viewHolder = new ViewHolder();
            //gán layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_gio_hang, null);
            //lấy dữ liệu trong class view holder
            viewHolder.txttengiohang = (TextView) convertView.findViewById(R.id.txttengiohang);
            viewHolder.txtgiagiohang = (TextView) convertView.findViewById(R.id.txtgiagiohang);
            viewHolder.imggiohang = (ImageView) convertView.findViewById(R.id.imggiohang);
            viewHolder.btngiam = (Button) convertView.findViewById(R.id.btngiam);
            viewHolder.btntang = (Button) convertView.findViewById(R.id.btntang);
            viewHolder.btnvalue = (Button) convertView.findViewById(R.id.btnvalues);
            convertView.setTag(viewHolder);


        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();


        }
        Giohang giohang = (Giohang) getItem(position);
        viewHolder.txttengiohang.setText(giohang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiagiohang.setText(decimalFormat.format(giohang.getGiasp()) + " VNĐ");
        Picasso.with(context).load(giohang.getHinhanhsp())
                .placeholder(R.drawable.noimg)
                .error(R.drawable.error)
                .into(viewHolder.imggiohang);
        viewHolder.btnvalue.setText(giohang.getSoluongsp() + "");

        return convertView;
    }
}
