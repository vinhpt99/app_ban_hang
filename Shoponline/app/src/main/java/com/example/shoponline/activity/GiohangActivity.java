package com.example.shoponline.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shoponline.R;
import com.example.shoponline.adapter.GiohangAdapter;

import java.text.DecimalFormat;

public class GiohangActivity extends AppCompatActivity {
    ListView lvgiohang;
    TextView txtthongbao;
    TextView txttongtien;
    Button btnthanhtoan;
    Button btnmuathem;
    androidx.appcompat.widget.Toolbar toolbargiohang;
    GiohangAdapter giohangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        AnhXa();
        ActionToobar();
        CheckData();
        EventUtil(); //hàm đổ dữ liệu
    }

    private void EventUtil() {
        long tongtien = 0;
        for(int i = 0; i < MainActivity.manggiohang.size(); i++)
        {
            tongtien += MainActivity.manggiohang.get(i).getGiasp();

        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txttongtien.setText(decimalFormat.format(tongtien) + " VNĐ");
    }

    private void CheckData() {
        if(MainActivity.manggiohang.size() <= 0)
        {
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.VISIBLE);//hiện
            lvgiohang.setVisibility(View.INVISIBLE);//ẩn

        }
        else {
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.INVISIBLE);
            lvgiohang.setVisibility(View.VISIBLE);


        }
    }

    private void ActionToobar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void AnhXa() {
        lvgiohang = (ListView) findViewById(R.id.listviewgiohang);
        txtthongbao = (TextView) findViewById(R.id.textviewthongbao);
        txttongtien = (TextView) findViewById(R.id.textviewtongtien);
        btnthanhtoan = (Button) findViewById(R.id.btnthanhtoangiohang);
        btnmuathem = (Button) findViewById(R.id.btnmuathem);
        toolbargiohang = (Toolbar) findViewById(R.id.toolbargiohang);
        giohangAdapter = new GiohangAdapter(GiohangActivity.this, MainActivity.manggiohang);
        lvgiohang.setAdapter(giohangAdapter);


    }
}