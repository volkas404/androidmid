package com.nam.bai2tuan3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
Button btn;
CheckBox ccolor;
CheckBox cbold;
EditText txt;
int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            ccolor = (CheckBox) findViewById(R.id.cb1);
            cbold = (CheckBox)  findViewById(R.id.cb2);
            btn = (Button)findViewById(R.id.button);
            txt = (EditText) findViewById(R.id.txt);
            txt.setSingleLine();
            txt.setInputType(InputType.TYPE_NULL);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(ccolor.isChecked())txt.setTextColor(Color.BLUE);
                    else txt.setTextColor(Color.WHITE);
                    if(cbold.isChecked())txt.setTypeface(Typeface.DEFAULT_BOLD);
                    else txt.setTypeface(Typeface.DEFAULT);
                txt.setText("Ban da bam "+ ++count + "lan!");
                }
            });
    }
}