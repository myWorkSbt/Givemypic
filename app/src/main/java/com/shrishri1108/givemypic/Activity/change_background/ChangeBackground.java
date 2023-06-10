package com.shrishri1108.givemypic.Activity.change_background;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.shrishri1108.givemypic.Activity.compress_image_code.Compress;
import com.shrishri1108.givemypic.R;
import com.shrishri1108.givemypic.databinding.ActivityChangeBackgroundBinding;

public class ChangeBackground extends AppCompatActivity {

    private static int PICK_IMAGE_REQUEST_CODE = 23 ;
    private ActivityChangeBackgroundBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChangeBackgroundBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        RegisterActi
        initViews();
    }

    private void initViews() {
        AlertDialog.Builder alertBuildrs = new AlertDialog.Builder(this);
        View alertViews = LayoutInflater.from(ChangeBackground.this).inflate(R.layout.image_uploading_dialog_lays, null);
        alertBuildrs.setView(alertViews);
        alertBuildrs.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog alertDialog = alertBuildrs.create();
        alertViews.findViewById(R.id.choose_gallery).setOnClickListener(View -> {
            alertDialog.dismiss();
            PICK_IMAGE_REQUEST_CODE = 103;

            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);

        });


        alertViews.findViewById(R.id.take_pic_tabs).setOnClickListener(View -> {
            alertDialog.dismiss();
            PICK_IMAGE_REQUEST_CODE = 101;
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);
        });

    }

}