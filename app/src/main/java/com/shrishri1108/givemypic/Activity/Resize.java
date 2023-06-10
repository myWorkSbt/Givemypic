package com.shrishri1108.givemypic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.shrishri1108.givemypic.R;
import com.shrishri1108.givemypic.databinding.ActivityResizeBinding;

public class Resize extends AppCompatActivity {
private ActivityResizeBinding resizeBinding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resizeBinding = DataBindingUtil.setContentView(this , R.layout.activity_resize);


    }
}