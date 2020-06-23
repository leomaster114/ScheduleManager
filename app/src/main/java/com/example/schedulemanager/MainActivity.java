package com.example.schedulemanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout drawerLayout;
    private Button btn_person_info, btn_view_tkb, btn_view_history, btn_mh_current;
    private ActionBarDrawerToggle drawerToggle;
    MHFragment mhFragment;
    TKBFragment tkbFragment;
    HistoryCheckinFragment historyCheckinFragment;
    InfoFragment infoFragment;
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
        btn_view_tkb.setOnClickListener(this);
        btn_view_history.setOnClickListener(this);
        btn_person_info.setOnClickListener(this);
        btn_mh_current = findViewById(R.id.btn_subject_current);
        btn_mh_current.setOnClickListener(this);
        tkbFragment = new TKBFragment();
        mhFragment = new MHFragment();
        historyCheckinFragment = new HistoryCheckinFragment();
        infoFragment = new InfoFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_container, mhFragment).commit();

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_person_info:
                FrameLayout frameLayout1 = this.findViewById(R.id.replaceFragment1);
                frameLayout1.setVisibility(View.GONE);
                frameLayout1 = this.findViewById(R.id.replaceFragment2);
                frameLayout1.setVisibility(View.VISIBLE);
                this.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.replaceFragment2,infoFragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.btn_view_checkin_history:
                FrameLayout frameLayout2 = this.findViewById(R.id.replaceFragment1);
                frameLayout2.setVisibility(View.GONE);
                frameLayout2 = this.findViewById(R.id.replaceFragment2);
                frameLayout2.setVisibility(View.VISIBLE);
                this.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.replaceFragment2,historyCheckinFragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.btn_view_schedule:
                FrameLayout frameLayout3 = this.findViewById(R.id.replaceFragment1);
                frameLayout3.setVisibility(View.GONE);
                frameLayout3 = this.findViewById(R.id.replaceFragment2);
                frameLayout3.setVisibility(View.VISIBLE);
                this.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.replaceFragment2,tkbFragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.btn_subject_current:
                FrameLayout frameLayout4 = this.findViewById(R.id.replaceFragment2);
                frameLayout4.setVisibility(View.GONE);
                frameLayout4 = this.findViewById(R.id.replaceFragment1);
                frameLayout4.setVisibility(View.VISIBLE);
                break;
        }
    }
}