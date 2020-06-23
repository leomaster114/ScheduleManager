package com.example.schedulemanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MHFragment extends Fragment {
    private Button btn_diemdanh;
    ImageView imageView;
    ImageButton btn_checkin;
    Context mContext;
    TextView tv_name, tv_room, tv_gv, tv_st, tv_et, tv_stt;
    int REQUEST_CODE_CAMERA = 123;
    public MHFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_m_h, container, false);
        mContext = getContext();
        btn_diemdanh = view.findViewById(R.id.btn_dd);
        imageView = view.findViewById(R.id.imgv);
        tv_stt = view.findViewById(R.id.tv_status);
        btn_checkin = view.findViewById(R.id.btn_checkin);
        btn_checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });
        btn_diemdanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageView.getVisibility() == View.GONE) {
                    Toast.makeText(mContext, "vui lòng chụp ảnh", Toast.LENGTH_SHORT).show();
                } else {
                    tv_stt.setText("Đã điểm danh");
                }
            }
        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageBitmap(bitmap);
        }
    }
}