package com.nam.qtbai4;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class detailpic extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailpic);
        addControl();
        addEvent();
    }


    private void addEvent() {

    }

    private void addControl() {
        imageView = findViewById(R.id.detailimg);
        Bundle bundle = getIntent().getExtras();
        String filepath = (String) bundle.get("filepath");
        imageView.setImageBitmap(BitmapFactory.decodeFile(filepath));
    }
}
