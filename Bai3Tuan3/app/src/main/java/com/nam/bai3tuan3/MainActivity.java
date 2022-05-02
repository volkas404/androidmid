package com.nam.bai3tuan3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.os.IResultReceiver;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    RelativeLayout rl;
    RadioGroup group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        group = (RadioGroup) findViewById(R.id.gr);
        rl = (RelativeLayout) findViewById(R.id.bg);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.radioButton4:
                        rl.setBackgroundColor(Color.RED);
                        break;
                    case R.id.radioButton5:
                        rl.setBackgroundColor(Color.GREEN);
                        break;
                    case R.id.radioButton6:
                        rl.setBackgroundColor(Color.BLUE);
                        break;
                    case R.id.radioButton7:
                        rl.setBackgroundColor(Color.GRAY);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}