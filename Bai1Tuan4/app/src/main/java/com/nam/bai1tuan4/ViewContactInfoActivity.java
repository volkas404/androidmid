package com.nam.bai1tuan4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewContactInfoActivity extends MainActivity{
    TextView txtname1, txtemail1, txtproject1;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactinfo);
        txtname1 = (TextView)findViewById(R.id.name1);
        txtemail1 = (TextView)findViewById(R.id.email1);
        txtproject1 = (TextView)findViewById(R.id.project1);
        btn = (Button) findViewById(R.id.buttonf);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        String name = (String) getIntent().getSerializableExtra("namekey");
        String email = (String) getIntent().getSerializableExtra("emailkey");
        String project = (String) getIntent().getSerializableExtra("projectkey");
        txtname1.setText(name);
        txtemail1.setText(email);
        txtproject1.setText(project);

    }
}
