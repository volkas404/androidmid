package com.nam.qtbai2;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nam.model.country;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import com.nam.model.country;
public class Detailcountry extends AppCompatActivity{
    TextView ds, dt, td, qg;
    ImageView ctmap;
    private country ct;
    Bitmap bitmap;
    DecimalFormat fm = new DecimalFormat("###,###.### ");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailcountry);
        addControl();
        addEvent();
    }

    private void addEvent() {
        ct = (country) getIntent().getSerializableExtra("CountryInfo");
        getqk getqkct = new getqk();
        getqkct.execute();
    }

    private void addControl() {
        qg = (TextView)  findViewById(R.id.txttennuoc);
        ds = (TextView)  findViewById(R.id.txtdan);
        dt = (TextView)  findViewById(R.id.txtdientich);
        td = (TextView)  findViewById(R.id.txtthudo);
        ctmap = findViewById(R.id.imgmap);
    }
    class getqk extends AsyncTask<Void, Void, Bitmap> {
        private ProgressDialog dialog;

        public getqk() {
            dialog = new ProgressDialog(Detailcountry.this);
        }


        @Override
        protected Bitmap doInBackground(Void... voids) {
            try {
                final String bando = ct.getMap();
                URL url = new URL(bando);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();
                InputStream inputStream = conn.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Đang tải dữ liệu...");
            dialog.show();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            qg.setText("Quốc gia: "+ct.getTenquocgia());
            ds.setText("Dân số: "+fm.format(Float.parseFloat(ct.getDanso()))+" Người");
            dt.setText("Diện tích: "+fm.format(Float.parseFloat(ct.getDientich()))+" km² ");
            td.setText("Thủ đô: "+ct.getThudo());
            ctmap.setImageBitmap(bitmap);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }
}
