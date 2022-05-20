package com.nam.qtbai3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Spinner sp1,sp2;
    TextView txtkq,txtdate,txtrate,txttieude;
    EditText txt;
    String a1,a2;
    Button btn;
    ImageButton swp;
    String []arrcurrentcy;
    Integer tmp1,tmp2;
    ArrayAdapter<String>adaptercurrentcy;
    DecimalFormat fm = new DecimalFormat("###,###.### ");
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();

    }

    private void addEvents() {
        btn = (Button) findViewById(R.id.ex);
        swp = (ImageButton) findViewById(R.id.swap);
        swp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp1.setSelection(tmp2);
                sp2.setSelection(tmp1);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String url = "https://" + a1 +".fxexchangerate.com/rss.xml";
                        new docxml().execute(url);
                    }
                });
            }
        });
    }

    private void addControls() {
        txtkq = (TextView) findViewById(R.id.kq);
        txtdate = (TextView) findViewById(R.id.date);
        txtrate = (TextView) findViewById(R.id.rate);
        txttieude = (TextView) findViewById(R.id.tieude);
        txt = (EditText) findViewById(R.id.texttien);
        arrcurrentcy = getResources().getStringArray(R.array.currentcy);
        adaptercurrentcy = new ArrayAdapter<String>(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arrcurrentcy);
        sp1 = (Spinner) findViewById(R.id.spinner1);
        sp2 = (Spinner) findViewById(R.id.spinner2);
        sp1.setAdapter(adaptercurrentcy);
        sp2.setAdapter(adaptercurrentcy);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                a1 = (String) sp1.getItemAtPosition(i);
                tmp1 = i;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                a2 = (String) sp2.getItemAtPosition(i);
                tmp2 = i;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    class docxml extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... params) {
            String kq = getxml(params[0]);
            return kq;
        }

        @Override
        protected void onPostExecute(String s) {

            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            String title = "";
            String description = "";
            String pubDate = "";
            for (int i=0; i<nodeList.getLength();i++){
                Element element = (Element) nodeList.item(i);
                description = parser.getValue(element,"description");
                title = parser.getValue(element,"title");
                pubDate = parser.getValue(element,"pubDate");
                if(title.contains("("+a2+")")) {
                    txttieude.setText(title);
                    int vitri = description.indexOf("=");
                    Double giatri = Double.parseDouble(description.substring(vitri+2, vitri+8).trim());
                    Double ketqua = Double.parseDouble(String.valueOf(txt.getText())) * giatri;
                    txtkq.setText("Kết quả: "+String.valueOf(fm.format(ketqua)));
                    txtdate.setText("Thời gian cập nhật: " + pubDate);
                    txtrate.setText("Tỷ giá: " + description);
                }
                }
            super.onPostExecute(s);
        }
    }
    private String getxml(String theUrl){
        StringBuilder content = new StringBuilder();
        try    {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }
        return content.toString();
    }
}