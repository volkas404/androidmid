package com.nam.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.nam.qtbai2.R;
import com.squareup.picasso.Picasso;
import com.nam.model.country;

import java.util.List;

public class countryadapter extends ArrayAdapter<country> {
    Activity context;
    int resource;
    List <country>objects;
    public countryadapter(Activity context, int resource, List<country> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView,@NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View r = inflater.inflate(this.resource,null);
        country ct = this.objects.get(position);
        TextView txtten = (TextView) r.findViewById(R.id.txtquocgia);
        txtten.setText(ct.getTenquocgia());

        ImageView imgquocky = r.findViewById(R.id.quocky);
        Picasso.get().load(ct.getQuocky()).into(imgquocky);
        return r;
    }


}
