package com.nam.bai4tuan2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText txt;
    AlertDialog ald;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (EditText) findViewById(R.id.text);
        ald = new AlertDialog.Builder(this).create();
        txt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == keyEvent.ACTION_DOWN && i == keyEvent.KEYCODE_DPAD_CENTER) {
                    String mes = txt.getText().toString();
                    ald.setMessage(mes);
                    ald.show();
                    return true;
                }
                return false;
            }
        });
    }
}