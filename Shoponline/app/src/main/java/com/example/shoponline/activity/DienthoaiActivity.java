package com.example.shoponline.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shoponline.R;
import com.example.shoponline.adapter.DienthoaiAdapter;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DienthoaiActivity extends AppCompatActivity {
    String url = "http://192.168.1.82:8080/server_android/getSP.php?page=";
    Toolbar toolbardt;
    ListView listViewdt;
    DienthoaiAdapter dienthoaiAdapter;
    ArrayList<sanpham> mangdt;
    int iddt = 0;
    int page = 1;
    //sử dụng layout processbar
    View footerView;
    boolean isLoading = false;
    boolean limitData = false;
    mHanlder mHanlder;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dienthoai);
        Anhxa();
        GetIdLoaiSanPham();
        ActionToolbarDt();
        GetData(page);
        LoadmoreData();
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
    private void LoadmoreData() {
        listViewdt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Chitietsanpham.class);
                intent.putExtra("thongtinsanpham", mangdt.get(position));
                startActivity(intent);
            }
        });
        //gọi lại sự kiến kéo listview
        listViewdt.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                      //firstVisibleItem: view đầu tiên, visible.. các giá trị item có thể nhìn thấy, total tổng số item
                if(firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0 && isLoading == false && limitData ==  false)
                {
                    isLoading = true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();

                }
            }
        });
    }

    private void GetData(int Page) {
        //đọc dữ liệu từ một đường dẫn
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan =  url+String.valueOf(Page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String tenDt = "";
                int giaDt = 0;
                String Hinhanhdt = "";
                String Mota = "";
                int idsanphamdt = 0;
                if(response != null && response.length() != 2)
                {
                    listViewdt.removeFooterView(footerView);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tenDt = jsonObject.getString("tensanpham");
                            giaDt = jsonObject.getInt("giasanpham");
                            Hinhanhdt = jsonObject.getString("hinhanhsanpham");
                            Mota = jsonObject.getString("motasanpham");
                            idsanphamdt = jsonObject.getInt("idloaisanpham");
                            mangdt.add(new sanpham(id, tenDt, giaDt, Hinhanhdt, Mota, idsanphamdt));
                            dienthoaiAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    // <  0 => reponse hết dữ liệu
                    limitData = true;
                    listViewdt.removeFooterView(footerView);



                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String, String>();
                param.put("idloaisanpham",String.valueOf(iddt));
                return param;
            }

        };
        requestQueue.add(stringRequest);
    }

    private void ActionToolbarDt() {
        setSupportActionBar(toolbardt);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbardt.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIdLoaiSanPham() {
        iddt = getIntent().getIntExtra("idloaisanpham", -1); //nếu k tìm thấy giá trị id trả về -1;
        Log.d("id san pham",iddt+"");
    }

    private void Anhxa() {
        toolbardt = (Toolbar) findViewById(R.id.toolbardienthoai);
        listViewdt = (ListView) findViewById(R.id.ListviewDienthoai);
        mangdt = new ArrayList<>();
        dienthoaiAdapter = new DienthoaiAdapter(getApplicationContext(), mangdt);
        listViewdt.setAdapter(dienthoaiAdapter);
        //gọi lại phương thức để gán layout
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerView = inflater.inflate(R.layout.progcessbar, null);
        mHanlder = new mHanlder();
    }
    public class mHanlder extends Handler
    {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what)
            {
                case 0:
                    listViewdt.addFooterView(footerView);
                    break;
                case 1:

                    GetData(++page);
                    isLoading = false; //trả về trạng thái chưa load dữ liệu
                    break;

            }
            super.handleMessage(msg);
        }
    }
    public class ThreadData extends Thread
    {
        @Override
        public void run() {
            mHanlder.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mHanlder.obtainMessage(1); //phương thức liên kết thread và hanlder
            mHanlder.sendMessage(message);
            super.run();
        }
    }
}