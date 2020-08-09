package com.example.shoponline.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.shoponline.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class Chitietsanpham extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbarctsanpham;
    ImageView imgChitiet;
    TextView txtTen, txtGia, txtMota;
    Spinner spinner;
    Button btndatmua;
    int id = 0;
    String tenchitiet = "";
    int giachitiet = 0;
    String hinhanhchitiet = "";
    String motachitiet = "";
    int idloaisanpham = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsanpham);
        Anhxa();
        ActionToolBar();
        GetData();
        EventSpiner();
        EventButton();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent =  new Intent(getApplicationContext(), GiohangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private void EventButton() {
        btndatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.manggiohang.size() > 0)
                {
                    //nếu có dữ liệu rồi kiểm tra có sản phẩm nào update thêm số lượng hay không
                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exists = false;
                    //duyệt toàn bộ sản phẩm trong mảng
                    for(int i = 0; i < MainActivity.manggiohang.size(); i++)
                    {
                        //nếu có sản phẩm trùng với id sản phẩm có trong mảng
                      if(MainActivity.manggiohang.get(i).getIdsp() == id)
                      {
                          MainActivity.manggiohang.get(i).setSoluongsp(MainActivity.manggiohang.get(i).getSoluongsp() + sl);
                          if(MainActivity.manggiohang.get(i).getSoluongsp() >= 10)
                          {
                              MainActivity.manggiohang.get(i).setSoluongsp(10);
                          }
                          //có đc số lượng update giá trị
                          MainActivity.manggiohang.get(i).setGiasp(giachitiet * MainActivity.manggiohang.get(i).getSoluongsp());
                          //nếu tìm được sản phẩm trùng id
                          exists = true;
                      }

                    }
                       //không tìm được sản phẩm nào trùng id không cập nhật lại dữ liệu
                    if(exists == false)
                    {
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long giamoi = soluong*giachitiet;
                        MainActivity.manggiohang.add(new Giohang(id,tenchitiet,giamoi,hinhanhchitiet,soluong));

                    }


                }
                else
                {
                    //nếu mảng đang rỗng, không có dữ liệu add thêm dữ liệu mới
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long giamoi = soluong*giachitiet;
                    MainActivity.manggiohang.add(new Giohang(id,tenchitiet,giamoi,hinhanhchitiet,soluong));




                }
                Intent intent = new Intent(getApplicationContext(), GiohangActivity.class);
                //mảng khai báo bên main actvity nên k cần chuyển dữ liệu
                startActivity(intent);
            }
        });
    }

    private void EventSpiner() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, soluong);
        spinner.setAdapter(arrayAdapter);
    }

    private void GetData() {

        sanpham sanpham = (sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        id = sanpham.getId();
        tenchitiet = sanpham.getTenSanpham();
        giachitiet = sanpham.getGiaSanpham();
        hinhanhchitiet = sanpham.getHinhanhSanpham();
        motachitiet = sanpham.getMotaSanpham();
        idloaisanpham = sanpham.getIdloaiSanpham();
        txtTen.setText(tenchitiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGia.setText("Giá: " + decimalFormat.format(giachitiet) + " VNĐ");
        txtMota.setText(motachitiet);
        Picasso.with(getApplicationContext()).load(hinhanhchitiet)
                .placeholder(R.drawable.noimg)
                 .error(R.drawable.error)
                .into(imgChitiet);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarctsanpham);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //tạo nút home
        toolbarctsanpham.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    private void Anhxa() {
        toolbarctsanpham = (Toolbar) findViewById(R.id.toolbarChitietsanpham);
        imgChitiet = (ImageView) findViewById(R.id.imgchitietsanpham);
        txtTen = (TextView) findViewById(R.id.txttenchitietsanpham);
        txtGia = (TextView) findViewById(R.id.txtgiachitietsanpham);
        txtMota = (TextView) findViewById(R.id.txtmotachitietsanpham);
        spinner = (Spinner) findViewById(R.id.spiner);
        btndatmua = (Button) findViewById(R.id.btndatmua);

    }
}