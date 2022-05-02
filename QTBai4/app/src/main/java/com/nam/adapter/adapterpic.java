package com.nam.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

//import com.example.baitap4.MainActivity2;
import com.nam.qtbai4.R;

import java.util.List;

import com.nam.model.model;

public class adapterpic extends ArrayAdapter<model> {
    @NonNull Context context;
    int resource ;
    @NonNull List<model> objects;

    public adapterpic(@NonNull Context context, int resource, @NonNull List<model> objects) {

        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listpic, parent, false);
        }
        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView fileName = convertView.findViewById(R.id.textView);

        model md = this.objects.get(position);
        imageView.setImageBitmap(BitmapFactory.decodeFile( md.getFilepath()));
        fileName.setText(md.getFilename());

        return convertView;
    }
}
