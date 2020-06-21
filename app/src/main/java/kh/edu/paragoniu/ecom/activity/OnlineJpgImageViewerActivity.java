package kh.edu.paragoniu.ecom.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.drawee.view.SimpleDraweeView;

import kh.edu.paragoniu.ecom.R;

public class OnlineJpgImageViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_online_jpg_viewer);

        // Extract data from the Intent
        Intent intent = getIntent();
        Uri data = intent.getData();

        // Display the jpg image with SimpleDraweeView
        SimpleDraweeView imgViewer = findViewById(R.id.img_viewer);
        imgViewer.setImageURI(data);
    }
}
