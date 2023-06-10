package com.shrishri1108.givemypic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.shrishri1108.givemypic.Activity.compress_image_code.Compress;
import com.shrishri1108.givemypic.R;
import com.shrishri1108.givemypic.databinding.ActivityHomeBinding;

public class Home extends AppCompatActivity {
    private ActivityHomeBinding homeBinding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         homeBinding = DataBindingUtil.setContentView(this , R.layout.activity_home);

          homeBinding.btnCompressImage.setOnClickListener(View ->{
            Intent intents = new Intent(Home.this , Compress.class);
            startActivity(intents);
          });

          homeBinding.btnResize.setOnClickListener(View ->{
              Intent inten = new Intent(Home.this , Resize.class);
              startActivity(inten);
          });
    }
}