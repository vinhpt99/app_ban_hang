package com.example.shoponline.activity;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.shoponline.R;
import com.example.shoponline.adapter.loaiSpadapter;
import com.example.shoponline.adapter.sanphamadapter;
import com.google.android.material.navigation.NavigationView;

import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    String urlGetdata = "http://192.168.1.82:8080/server_android/getloaiSP.php";
    String data1 = "http://192.168.1.82:8080/server_android/getSPmoi.php";
    //ánh xạ
    androidx.appcompat.widget.Toolbar toolbar; //vì đang sử dụng adroidx
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;
    ArrayList<loaiSP> mangloaisp; //mảng hứng giá trị từ class
    loaiSpadapter spadapter; //bản vẽ
    ArrayList<sanpham> sanphamArrayList;
    sanphamadapter sanphamadapter;
    public static ArrayList<Giohang> manggiohang;

   @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        ActionBar();
        ActionViewFliper();
        GetDuLieuLoaispJson(urlGetdata);
        GetDuLieuSanPham(data1);
        GetItemLoaiSanPham();

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

    private void GetItemLoaiSanPham() {
       listViewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               switch (position) {
                   //positison = 0: trang chính
                   case 0:
                       Intent intent = new Intent(MainActivity.this, MainActivity.class);
                       startActivity(intent);
                       drawerLayout.closeDrawer(GravityCompat.START);
                       break;
                   case 1:
                       Intent intent1 = new Intent(MainActivity.this, DienthoaiActivity.class);
                       //phương thức truyền thông tin sang một trang khác
                       intent1.putExtra("idloaisanpham", mangloaisp.get(position).getId());
                       startActivity(intent1);
                       drawerLayout.closeDrawer(GravityCompat.START);
                       break;
                   case 2:
                       Intent intent2 = new Intent(MainActivity.this, LaptopActivity.class);
                       intent2.putExtra("idloaisanpham", mangloaisp.get(position).getId());
                       startActivity(intent2);
                       drawerLayout.closeDrawer(GravityCompat.START);
                       break;
                   case 3:
                       Intent intent3 = new Intent(MainActivity.this, LienheActivity.class);
                       startActivity(intent3);
                       drawerLayout.closeDrawer(GravityCompat.START);
                       break;
                   case 4:
                       Intent intent4 = new Intent(MainActivity.this, ThongtinActivity.class);
                       startActivity(intent4);
                       drawerLayout.closeDrawer(GravityCompat.START);
                       break;




               }

           }
       });
    }

    private void GetDuLieuSanPham(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response != null)
                        {
                            int idSanpham = 0;
                            String tenSanpham = "";
                            Integer giaSanpham = 0;
                            String hinhanhSanpham = "";
                            String motaSanpham = "";
                            int idloaiSanpham = 0;
                            for (int i = 0 ; i< response.length(); i++)
                            {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    idSanpham = jsonObject.getInt("id");
                                    tenSanpham = jsonObject.getString("tensanpham");
                                    giaSanpham =jsonObject.getInt("giasanpham");
                                    hinhanhSanpham=jsonObject.getString("hinhanhsanpham");
                                    motaSanpham=jsonObject.getString("motasanpham");
                                    idloaiSanpham=jsonObject.getInt("idloaisanpham");
                                    sanphamArrayList.add(new sanpham(idSanpham,tenSanpham,giaSanpham,hinhanhSanpham,motaSanpham,idloaiSanpham));
                                    sanphamadapter.notifyDataSetChanged();


                                }
                                catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }
                            }




                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //có lỗi sẽ in ra lỗi
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }
        );
        requestQueue.add(jsonArrayRequest);

    }


    private void GetDuLieuLoaispJson(String url) //nhận một đường dẫn
    {
          RequestQueue requestQueue = Volley.newRequestQueue(this);
          JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                  new Response.Listener<JSONArray>() {
              @Override
              public void onResponse(JSONArray response) {
                   if(response != null)
                   {
                       int id = 0;
                       String tenloaiSp = "";
                       String hinhanhloaiSp = "";
                       for (int i = 0 ; i< response.length(); i++)
                       {
                           try {
                                   JSONObject jsonObject = response.getJSONObject(i);
                                   id = jsonObject.getInt("id");
                                   tenloaiSp = jsonObject.getString("tenSp");
                                   hinhanhloaiSp = jsonObject.getString("hinhanhSp");
                                   mangloaisp.add(new loaiSP(id, tenloaiSp, hinhanhloaiSp));
                                   spadapter.notifyDataSetChanged();

                                   
                              }
                           catch (JSONException e)
                              {
                                   e.printStackTrace();
                              }
                       }
                       mangloaisp.add(3, new loaiSP(0, "Liên hệ", "https://mtee.vn/wp-content/uploads/2017/12/contact-icon-phone.png"));
                       mangloaisp.add(4, new loaiSP(0, "Thông tin", "https://hotramvillas.vn/wp-content/uploads/sites/32/2016/01/icon-form-150x150.png"));



                   }
              }
          }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                  //có lỗi sẽ in ra lỗi
                  Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

              }
          }
          );
         requestQueue.add(jsonArrayRequest);
    }
    //kết thúc hàm lấy dữ liệu



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void ActionBar()
    {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
            //bắt sự kiện click mở menu, mở menu nhờ drawerlayout
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    drawerLayout.openDrawer(GravityCompat.START);
                }
            });
        }
    }
    // bắt đầu funtion chạy quảng cáo
    private void ActionViewFliper()
    {
        //mảng chứa các hình ảnh quảng cáo
        ArrayList<String> mangquangcao = new ArrayList<>(); //khai báo và cấp phát bộ nhớ
        mangquangcao.add("https://cdn.tgdd.vn/Files/2018/01/01/1054901/24giotrainghiemiphone6s21-2_1280x720-800-resize.jpg");
        mangquangcao.add("https://laptoptcc.com/wp-content/uploads/2018/08/laptop-tcc-hp-elitebook-9470m-1-e1572782636806.jpg");
        mangquangcao.add("http://thuthuatphanmem.vn/uploads/2018/09/11/hinh-anh-dep-1_044126531.jpg");

        for (int i = 0; i < mangquangcao.size(); i++)
        {
            //khởi tạo imgview
            ImageView imageView = new ImageView(getApplicationContext());
           //gọi hàm thư viện load hình ảnh từ internet
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            //ảnh k bị cắt
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        //tự động chạy ảnh
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
      }
    //kết thúc fuction chạy quảng cáo

    private void setSupportActionBar(Toolbar toolbar) { }
    //hàm ánh xạ
    private void anhxa()
    {

        toolbar =  findViewById(R.id.tbmanhinhchinh);
        viewFlipper = findViewById(R.id.viewFlipper);
        recyclerView = findViewById(R.id.recycleViewsanpham);
        navigationView = findViewById(R.id.navigationView);
        listViewmanhinhchinh = findViewById(R.id.listViewmanhinhchinh);
        drawerLayout = findViewById(R.id.drawerlayout);
        mangloaisp = new ArrayList<>(); //khởi tạo mảng
        mangloaisp.add(0, new loaiSP(0, "Trang chính", "https://png.pngtree.com/png-vector/20190121/ourlarge/pngtree-vector-house-icon-png-image_332900.jpg"));
        spadapter = new loaiSpadapter(mangloaisp,getApplicationContext());
        listViewmanhinhchinh.setAdapter(spadapter);
        sanphamArrayList = new ArrayList<>();
        sanphamadapter = new sanphamadapter(getApplicationContext(), sanphamArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setAdapter(sanphamadapter);
        if(manggiohang != null)
        {
            //không cần khởi tạo lại
        }
        else
        {
            manggiohang = new ArrayList<>();

        }
    }
}
//kết thúc hàm ánh xạ