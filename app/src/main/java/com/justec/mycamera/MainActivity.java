package com.justec.mycamera;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btTakePic;
    private ImageView ivMyPic;
    private static final int REQUEST_CODE_CAMERA = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btTakePic = findViewById(R.id.btn_takePic);
        ivMyPic = findViewById(R.id.iv_my_photo);
        btTakePic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_takePic:
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivityForResult(intent,REQUEST_CODE_CAMERA);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE_CAMERA){
            switch (resultCode){
                case 1:
                    if (data != null) {
                        ivMyPic.setImageBitmap(FileUtil.getBitmap(data.getStringExtra("path")));
                    }
                    break;
            }
        }
    }
}
