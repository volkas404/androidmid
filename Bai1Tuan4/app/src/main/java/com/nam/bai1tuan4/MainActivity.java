package com.nam.bai1tuan4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtname, txtemail, txtproject;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtname = (TextView) findViewById(R.id.name);
        txtemail = (TextView) findViewById(R.id.email);
        txtproject = (TextView) findViewById(R.id.project);
        txtname = (TextView) findViewById(R.id.name);
        bt =(Button) findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGetContactInfo = new Intent(MainActivity.this, ViewContactInfoActivity.class);
                iGetContactInfo.putExtra("namekey",txtname.getText().toString());
                iGetContactInfo.putExtra("emailkey",txtemail.getText().toString());
                iGetContactInfo.putExtra("projectkey",txtproject.getText().toString());
                startActivity(iGetContactInfo);
            }
        });
    }
}