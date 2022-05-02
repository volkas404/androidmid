package com.nam.bai4tuan3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    View.OnClickListener buttonListener;
    EditText tvresult;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bc, bt, bn, bch, brsult, brset;
    int lastvalue = 0, currentvalue;
    String operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvresult = (EditText) findViewById(R.id.textView);
        b0 = (Button) findViewById(R.id.b0);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);
        bc = (Button) findViewById(R.id.bc);
        bt = (Button) findViewById(R.id.bt);
        bn = (Button) findViewById(R.id.bn);
        bch = (Button) findViewById(R.id.bch);
        brsult = (Button) findViewById(R.id.brsult);
        brset = (Button) findViewById(R.id.brset);

        buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button btn = (Button) view;
                switch (btn.getId()) {
                    case R.id.b0:
                        setvalue(tvresult, "0");
                        break;
                    case R.id.b1:
                        setvalue(tvresult, "1");
                        break;
                    case R.id.b2:
                        setvalue(tvresult, "2");
                        break;
                    case R.id.b3:
                        setvalue(tvresult, "3");
                        break;
                    case R.id.b4:
                        setvalue(tvresult, "4");
                        break;
                    case R.id.b5:
                        setvalue(tvresult, "5");
                        break;
                    case R.id.b6:
                        setvalue(tvresult, "6");
                        break;
                    case R.id.b7:
                        setvalue(tvresult, "7");
                        break;
                    case R.id.b8:
                        setvalue(tvresult, "8");
                        break;
                    case R.id.b9:
                        setvalue(tvresult, "9");
                        break;
                    case R.id.bc:
                        operation = "cong";
                        lastvalue = Integer.parseInt(tvresult.getText().toString());
                        resetvalue();
                        break;
                    case R.id.bt:
                        operation = "tru";
                        lastvalue = Integer.parseInt(tvresult.getText().toString());
                        resetvalue();
                        break;
                    case R.id.bn:
                        operation = "nhan";
                        lastvalue = Integer.parseInt(tvresult.getText().toString());
                        resetvalue();
                        break;
                    case R.id.bch:
                        operation = "chia";
                        lastvalue = Integer.parseInt(tvresult.getText().toString());
                        resetvalue();
                        break;
                    case R.id.brsult:
                        process(lastvalue,currentvalue);
                        break;
                    case R.id.brset:
                        resetvalue();
                        break;
                    default:
                        break;
                }
            }
        };
        b0.setOnClickListener(buttonListener);
        b1.setOnClickListener(buttonListener);
        b2.setOnClickListener(buttonListener);
        b3.setOnClickListener(buttonListener);
        b4.setOnClickListener(buttonListener);
        b5.setOnClickListener(buttonListener);
        b6.setOnClickListener(buttonListener);
        b7.setOnClickListener(buttonListener);
        b8.setOnClickListener(buttonListener);
        b9.setOnClickListener(buttonListener);
        b0.setOnClickListener(buttonListener);
        bc.setOnClickListener(buttonListener);
        bt.setOnClickListener(buttonListener);
        bn.setOnClickListener(buttonListener);
        bch.setOnClickListener(buttonListener);
        brset.setOnClickListener(buttonListener);
        brsult.setOnClickListener(buttonListener);


    }

    public void setvalue(EditText a, String b) {
        String last = a.getText().toString();
        if (!last.equals("0")) {
            last += b;
            b = last;
        }
        a.setText(b);
        currentvalue = Integer.parseInt(b);
    }

    public void resetvalue() {
        tvresult.setText("0");
    }

    public int process(int a, int b) {
        int rs = 0;
        if (operation == "cong") {
            rs = a + b;
            String s = String.valueOf(rs);
            tvresult.setText(s);
        }
        if (operation == "tru") {
            rs = a - b;
            String s = String.valueOf(rs);
            tvresult.setText(s);
        }
        if (operation == "nhan") {
            rs = a * b;
            String s = String.valueOf(rs);
            tvresult.setText(s);
        }
        if (operation == "chia") {
            rs = a / b;
            String s = String.valueOf(rs);
            tvresult.setText(s);
        }
        return rs;
    }
}