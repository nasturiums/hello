package com.example.myapplication;

import static com.example.myapplication.CheckBoxAdapter.selected_position;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.models.Box;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Box> boxs=getListData();
        final ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new CheckBoxAdapter(boxs,this));
        final Button start=(Button) findViewById(R.id.button);
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},101);
        }
        if(selected_position==-1) {
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                    b.setTitle("Xác nhận");
                    b.setMessage("Bạn cần phải tick lựa chọn trước đó");
                    b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    b.show();
                }
            });
        }else if(selected_position==0||selected_position==1){
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("ss", "selected_position"+selected_position);
                    openFontCamera();
                }
            });
        }else if(selected_position==2||selected_position==3) {
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("ss", "selected_position"+selected_position);
                    openFontCamera();
                }
            });
        }
    }
    void openFontCamera(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1 && Build.VERSION.SDK_INT < Build.VERSION_CODES.O){
            cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", CameraCharacteristics.LENS_FACING_FRONT);
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", CameraCharacteristics.LENS_FACING_FRONT); // Tested on API 27 Android version 8.0(Nexus 6P)
            cameraIntent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);
        }else {
            cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);
        }
        startActivityForResult(cameraIntent,101);
    }
//    void openBackCamera(){
//        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(cameraIntent,101);
//    }

    private List<Box> getListData() {
        List<Box> list = new ArrayList<Box>();
        Box box1=new Box("Armpit","armfit",false);
        Box box2=new Box("Face","face",false);
        Box box3=new Box("Feet","feet",false);
        Box box4=new Box("Hand","hand",false);
        list.add(box1);
        list.add(box2);
        list.add(box3);
        list.add(box4);
        return list;
    }

}
