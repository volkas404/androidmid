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

                //Initial color state values

                int[] redArray = {255, 0, 0};
                int[] blueArray = {0, 0, 255};
                int[] yellowArray = {255, 255, 0};
                int[] orangeArray = {255, 125, 0};
                int[] greenArray = {0, 255, 0};

                progChange = progress;

                Random rd = new Random(10+1);
                int n = rd.nextInt(255+1-1)+1;
                redArray[0] = redArray[0] - 2*progChange;
                redArray[1] = redArray[1] + 2*progChange;
                redArray[2] = redArray[2] + 2*progChange;
                blueArray[0] = blueArray[0] + 2*progChange;
                blueArray[1] = blueArray[1] + 2*progChange;
                blueArray[2] = blueArray[2] - 2*progChange;
                yellowArray[0] = yellowArray[0] - 2*progChange;
                yellowArray[1] = yellowArray[1] - 2*progChange;
                yellowArray[2] = yellowArray[2] + 2*progChange;
                orangeArray[0] = orangeArray[0] - 2*progChange;
                orangeArray[1] = orangeArray[1] - 1*progChange;
                orangeArray[2] = orangeArray[2] + 2*progChange;
                greenArray[0] = greenArray[0] + 2*progChange;
                greenArray[1] = greenArray[1] - 2*progChange;
                greenArray[2] = greenArray[2] + 2*progChange;

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