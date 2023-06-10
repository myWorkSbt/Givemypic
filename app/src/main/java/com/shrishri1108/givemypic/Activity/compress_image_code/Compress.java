package com.shrishri1108.givemypic.Activity.compress_image_code;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.shrishri1108.givemypic.R;
import com.shrishri1108.givemypic.UtilityMethods.DbHelper;
import com.shrishri1108.givemypic.UtilityMethods.OnActionButtonsClick;
import com.shrishri1108.givemypic.databinding.ActivityCompressBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Compress extends AppCompatActivity {

    private static int PICK_IMAGE_REQUEST_CODE = 23;
    private ActivityCompressBinding compressBinding;
    private DbHelper dbHelper;
    private CompressedImageAdapter compressedImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compressBinding = DataBindingUtil.setContentView(this, R.layout.activity_compress);

        dbHelper = new DbHelper(compressBinding.getRoot().getContext(), "compressed");

        compressedImageAdapter = new CompressedImageAdapter(new ArrayList<>(), new OnActionButtonsClick() {
            @Override
            public boolean onDownloadButtonClicked(int position) {

                return false;
            }

            @Override
            public boolean onDeleteButtonClicked(int id) {
                return false;
            }

            @Override
            public void onItemClicked(int position) {

            }
        });
        compressBinding.compressedImageRecyclerLists.setAdapter(compressedImageAdapter);
        AlertDialog.Builder alertBuildrs = new AlertDialog.Builder(this);
        getCompressedImageLists();
        View alertViews = LayoutInflater.from(Compress.this).inflate(R.layout.image_uploading_dialog_lays, null);
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

        compressBinding.btnUploadAndcompress.setOnClickListener(View -> {
            alertDialog.show();
        });
    }

    private void getCompressedImageLists() {
        compressedImageAdapter.refreshLists(dbHelper.getAllImages());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST_CODE && data != null && data.getData() != null) {
                Uri selectedImageUri = data.getData();
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                compressImage(bitmap);
            } else if (requestCode == PICK_IMAGE_REQUEST_CODE && data != null && data.getData() != null) {
                Bundle extras = data.getExtras();
                Bitmap bitmap = (Bitmap) extras.get("data");
                compressImage(bitmap);
            }

        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, " Cancelled! ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, " Something is wrong ", Toast.LENGTH_SHORT).show();
        }
    }

    private void compressImage(Bitmap bitmap) {
        compressBinding.selectedImg.setImageBitmap(bitmap);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] compressedImage = baos.toByteArray();
        saveToSqliteDb(compressedImage);
    }

    private void saveToSqliteDb(byte[] compressedImage) {
        getOtherDetailsAlso(compressedImage);
        dbHelper.addNewImage("compressed3", compressedImage, "34", "34", "232", "232");
        getCompressedImageLists();
    }

    private void getOtherDetailsAlso(byte[] compressedImage) {
        Bitmap bmps = BitmapFactory.decodeByteArray(compressedImage, 0, compressedImage.length);
        int width = bmps.getWidth();
        int height = bmps.getHeight();
//         compressedName =
    }

}