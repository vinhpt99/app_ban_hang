package com.example.shoponline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ListAdapter;

import com.example.shoponline.R;
import com.example.shoponline.activity.MainActivity;
import com.example.shoponline.activity.loaiSP;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
public class loaiSpadapter extends BaseAdapter {
      ArrayList<loaiSP> loaiSPList;
      Context context;

    public loaiSpadapter(ArrayList<loaiSP> loaiSPList, Context context) {
        this.loaiSPList = loaiSPList;
        this.context = context;
    }

    @Override
        public int getCount() {
           return  loaiSPList.size();  //trả về số bản ghi
        }
        @Override
        public Object getItem(int position)
        {
            return loaiSPList.get(position);


        }
        public class ViewHolder
        {
            TextView txtloaiSp;
            ImageView imagloaiSp;


        }

        @Override
        public long getItemId(int position) {

        return position;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder1 = null;
            if(convertView == null)
            {
                holder1 = new ViewHolder();
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//lấy dữ liệu
                convertView = layoutInflater.inflate(R.layout.dong_list_view_loaisp, null); //gán view
                holder1.txtloaiSp = convertView.findViewById(R.id.textviewLoaiSp);
                holder1.imagloaiSp = convertView.findViewById(R.id.imgviewLoaisp);
                convertView.setTag(holder1);



            }
            else {
                holder1 = (ViewHolder) convertView.getTag();


            }
            loaiSP loaiSP =  (loaiSP) getItem(position);
            holder1.txtloaiSp.setText(loaiSP.getTenSp());
            Picasso.with(context).load(loaiSP.getHinhanhSp())
                    .placeholder(R.drawable.noimg)
                    .error(R.drawable.error)
                    .into(holder1.imagloaiSp);
            return convertView;

        }

}
