package com.nam.qtbai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nam.adapter.countryadapter;
import com.nam.model.country;

import org.apache.http.params.HttpConnectionParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ListView lvct;
    ArrayList<country> dscountry = new ArrayList<country>();
    countryadapter ctadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        lvct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                country ct = dscountry.get(i);
                Intent intent = new Intent(MainActivity.this, Detailcountry.class);
                intent.putExtra("CountryInfo", ct);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        lvct = findViewById(R.id.lvcountry);

        ctadapter = new countryadapter(
                MainActivity.this,
                R.layout.countryitem,
                dscountry
        );
        countrytask ctt = new countrytask();
        ctt.execute();
        lvct.setAdapter(ctadapter);


    }

    class countrytask extends AsyncTask<Void, Void, ArrayList<country>>
    {
        private ProgressDialog dialog;

        public countrytask() {
            dialog = new ProgressDialog(MainActivity.this);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ctadapter.clear();
            dialog.setMessage("Đang tải dữ liệu...");
            dialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<country> dscountry) {
            super.onPostExecute(dscountry);
            ctadapter.clear();
            ctadapter.addAll(dscountry);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<country> doInBackground(Void... voids) {
            ArrayList<country> ds = new ArrayList<country>();
            try {
                URL url = new URL("http://api.geonames.org/countryInfoJSON?username=namkeje");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder builder = new StringBuilder();
                String line = bufferedReader.readLine();
                if(line != null){
                    builder.append(line);
                    line = bufferedReader.readLine();
                }
                JSONObject result = new JSONObject(builder.toString());
                JSONArray jsonArray = result.getJSONArray("geonames");
                for(int i = 0; i < jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    country ct = new country();
                    if(jsonObject.has("countryName"))
                        ct.setTenquocgia(jsonObject.getString("countryName"));
                    if(jsonObject.has("capital"))
                        ct.setThudo(jsonObject.getString("capital"));
                    if(jsonObject.has("areaInSqKm"))
                        ct.setDientich(jsonObject.getString("areaInSqKm"));
                    if(jsonObject.has("population"))
                        ct.setDanso(jsonObject.getString("population"));
                    if(jsonObject.has("countryCode")) {
                        String ctcode = jsonObject.getString("countryCode");
                        String map = "https://img.geonames.org/img/country/250/" + ctcode +".png";
                        ct.setMap(map);
                        String ctcode1 = jsonObject.getString("countryCode").toLowerCase();
                        String quocky = "https://img.geonames.org/flags/x/" + ctcode1 +".gif";
                        ct.setQuocky(quocky);
                    }
                    ds.add(ct);
                }
            }
            catch (Exception e){
                Log.e("loi",e.toString());
            }
            return ds;
        }
    }
}