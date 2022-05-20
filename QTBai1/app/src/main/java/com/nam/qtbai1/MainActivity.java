package com.nam.qtbai1;

import static java.lang.Math.random;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    static private final String URL = "http://moma.org";
    SeekBar sb = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sb = (SeekBar) findViewById(R.id.slider);
        sb.setMax(100);

        final TextView redbox1 = (TextView) findViewById(R.id.redbox1);
        final TextView yellowbox1 = (TextView) findViewById(R.id.yellowbox1);
        final TextView bluebox1 = (TextView) findViewById(R.id.bluebox1);
        final TextView orangebox1 = (TextView) findViewById(R.id.orangebox1);
        final TextView greenbox1 = (TextView) findViewById(R.id.greenbox1);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progChange = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ArrayList<Integer> num = new ArrayList<Integer>();
                for(int i=0;i<15;i++){
                    num.add(i);
                }

                Collections.shuffle(num);
                //Initial color state values
                Collections.shuffle(num);
                int[] redArray = {num.get(0), num.get(1), num.get(2)};
                int[] blueArray = {num.get(3), num.get(4), num.get(5)};
                int[] yellowArray = {num.get(6), num.get(7), num.get(8)};
                int[] orangeArray = {num.get(9), num.get(10), num.get(11)};
                int[] greenArray = {num.get(12), num.get(13), num.get(14)};

                progChange = progress;


                redArray[0] = redArray[0] + num.get(0)*progChange;
                redArray[1] = redArray[1] + num.get(1)*progChange;
                redArray[2] = redArray[2] + num.get(2)*progChange;
                blueArray[0] = blueArray[0] + num.get(3)*progChange;
                blueArray[1] = blueArray[1] + num.get(4)*progChange;
                blueArray[2] = blueArray[2] + num.get(5)*progChange;
                yellowArray[0] = yellowArray[0] + num.get(6)*progChange;
                yellowArray[1] = yellowArray[1] + num.get(7)*progChange;
                yellowArray[2] = yellowArray[2] + num.get(8)*progChange;
                orangeArray[0] = orangeArray[0] + num.get(9)*progChange;
                orangeArray[1] = orangeArray[1] + num.get(10)*progChange;
                orangeArray[2] = orangeArray[2] + num.get(11)*progChange;
                greenArray[0] = greenArray[0] + num.get(12)*progChange;
                greenArray[1] = greenArray[1] + num.get(13)*progChange;
                greenArray[2] = greenArray[2] + num.get(14)*progChange;

                redbox1.setBackgroundColor(Color.rgb(redArray[0],redArray[1],redArray[2]));
                bluebox1.setBackgroundColor(Color.rgb(blueArray[0],blueArray[1],blueArray[2]));
                greenbox1.setBackgroundColor(Color.rgb(greenArray[0],greenArray[1],greenArray[2]));
                yellowbox1.setBackgroundColor(Color.rgb(yellowArray[0],yellowArray[1],yellowArray[2]));
                orangebox1.setBackgroundColor(Color.rgb(orangeArray[0],orangeArray[1],orangeArray[2]));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_more_info) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            TextView dialog_title = new TextView(this);
            dialog_title.setText(R.string.dialog_title);
            dialog_title.setGravity(Gravity.CENTER_HORIZONTAL);
            dialog_title.setPadding(100,30,100,30);
            dialog_title.setTextSize(20);
            builder.setCustomTitle(dialog_title);
            builder.setMessage(R.string.dialog_message);

            builder.setPositiveButton(R.string.not_now, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            builder.setNegativeButton(R.string.visit_moma, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent momaIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                    startActivity(momaIntent);
                }
            });

            AlertDialog dialog = builder.show();

            TextView dialog_msg = (TextView) dialog.findViewById(android.R.id.message);
            dialog_msg.setGravity(Gravity.CENTER_HORIZONTAL);
            dialog_msg.setTextSize(14);
            dialog_msg.setPadding(10,60,10,0);
        }
        return super.onOptionsItemSelected(item);
    }

}