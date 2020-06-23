package com.example.schedulemanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Button btn_person_info, btn_view_tkb, btn_view_history, btn_diemdanh;
    ImageView imageView;
    TextView tv_name, tv_room, tv_gv, tv_st, tv_et, tv_stt;
    int REQUEST_CODE_CAMERA = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout = findViewById(R.id.activity_main_drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_draw_open, R.string.nav_draw_close);
        drawerLayout.addDrawerListener(drawerToggle);
        btn_person_info = findViewById(R.id.btn_person_info);
        btn_view_history = findViewById(R.id.btn_view_checkin_history);
        btn_view_tkb = findViewById(R.id.btn_view_schedule);
        btn_diemdanh = findViewById(R.id.btn_dd);
        imageView = findViewById(R.id.imgv);
        tv_stt = findViewById(R.id.tv_status);
        //
        btn_view_tkb.setOnClickListener(this);
        btn_view_history.setOnClickListener(this);
        btn_person_info.setOnClickListener(this);
        btn_diemdanh.setOnClickListener(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_action, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.btn_checkin:
                Toast.makeText(this, "Đây là chức năng điểm danh", Toast.LENGTH_SHORT).show();
                imageView.setVisibility(View.VISIBLE);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
                break;
            case R.id.setup:
                Toast.makeText(this, "Cài đặt", Toast.LENGTH_SHORT).show();
                break;
            case R.id.app_permission:
                Toast.makeText(this, "Quyền hạn ứng dụng", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK && data != null) {
            Toast.makeText(this, "Returned image from camera", Toast.LENGTH_SHORT).show();
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_person_info:
                Toast.makeText(this, "Xem thông tin cá nhân", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_view_checkin_history:
                Toast.makeText(this, "Xem lịch sử điểm danh", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_view_schedule:
                Intent intent = new Intent(MainActivity.this, TKBActivity.class);
                startActivity(intent);
            case R.id.btn_dd:
                if (imageView.getVisibility() == View.GONE) {
                    Toast.makeText(this, "vui lòng chụp ảnh", Toast.LENGTH_SHORT).show();
                } else {
                    tv_stt.setText("Đã điểm danh");
                }
        }
    }
}