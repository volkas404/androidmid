package com.nam.bai1tuan3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import android.view.*;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView im = (ImageView) findViewById(R.id.imageView);
        registerForContextMenu(im);

    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater im = getMenuInflater();
        im.inflate(R.menu.optionmenu, menu);
        return true;
    }
    public boolean onMenuItemSelected(MenuItem item){
        Toast.makeText(MainActivity.this,item.getTitle(),Toast.LENGTH_SHORT).show();
        return true;
    }
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        MenuInflater im = getMenuInflater();
        im.inflate(R.menu.contextmenu,menu);
    }
    public boolean onContextItemSelected(MenuItem item){
        Toast.makeText(MainActivity.this,item.getTitle(),Toast.LENGTH_SHORT).show();
        return true;
    }
}