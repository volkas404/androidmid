package com.nam.qtbai4;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.nam.model.model;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import com.nam.model.model;
import com.nam.adapter.adapterpic;
public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 10001;
    private static final int CAMERA_REQUEST_CODE = 10001;
    ListView lvhinh;
    ArrayList<model> md = new ArrayList<model>();
    adapterpic adappic;
    File[] f;
    String currentPhotoPath;
    String dirPath = "/storage/emulated/0/Android/data/com.nam.qtbai4/files/Pictures/";
    final long TIME_PUSH_NOTIFI = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        capquyen();
        createNotificationChanel();
        addControl();
        getFileInDir();
        addEvent();
}

    private void addControl() {
        lvhinh = findViewById(R.id.lvanh);
        lvhinh.setSmoothScrollbarEnabled(true);
        getFileInDir();
        adappic = new adapterpic(MainActivity.this, R.layout.listpic, md);
        lvhinh.setAdapter(adappic);
        registerForContextMenu(lvhinh);
    }

    private void addEvent() {
        lvhinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showImage(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.camera:
                dispatchTakePictureIntent();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showImage(int i) {
        Intent intent = new Intent(MainActivity.this, detailpic.class);
        Bundle bundle = new Bundle();
        bundle.putString("filename", md.get(i).getFilename());
        bundle.putString("filepath", md.get(i).getFilepath());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void getFileInDir() { // đọc các file trong thư mục rồi đưa lên danh sách
        f = new File[]{};
        File directory = new File(dirPath);
        if(directory.exists()){
            f = directory.listFiles();
            md.clear();
            if(f.length > 0){
                for (int i = 0; i < f.length; i++) {
                    md.add(new model(f[i].getName(), f[i].getAbsolutePath()));
                }
            }
        }

    }

    private void dispatchTakePictureIntent() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
            }

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }
    private void capquyen(){
        int quyencamera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int quyendocfile = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE);
        int quyenghifile = ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int quyenthongbao = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_NOTIFICATION_POLICY);
        if (quyencamera != PackageManager.PERMISSION_GRANTED ||
                quyendocfile != PackageManager.PERMISSION_GRANTED ||
                quyenghifile != PackageManager.PERMISSION_GRANTED ||
                quyenthongbao != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_NOTIFICATION_POLICY}, 1);
        }
    }
    public void onActivityResult(int requestcode, int resultcode, Intent data) {
        super.onActivityResult(requestcode, resultcode, data);
        if(requestcode == REQUEST_IMAGE_CAPTURE && resultcode == RESULT_OK){
            pushNoti();
            getFileInDir();
            adappic.notifyDataSetChanged();
        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
    private void createNotificationChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "notifyAppSelfyChannel";
            String des = "Channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel("notifyAppSelfy", name, importance);
            notificationChannel.setDescription(des);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void pushNoti() {
        Intent intent = new Intent(MainActivity.this, buildnotice.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis(),
                TIME_PUSH_NOTIFI * 10, pendingIntent);
    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.submenu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        switch (item.getItemId()) {
            case R.id.view:
                showImage(index);
                return true;
            case R.id.del:
                if(deleteFile(md.get(index).getFilepath())){
                    Toast.makeText(MainActivity.this, "Xóa thành công !", Toast.LENGTH_SHORT).show();
                }
                getFileInDir();
                adappic.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public boolean deleteFile(String path) {
        try {
            File f = new File(path);
            if (f.exists()) {
                return f.delete();
            } else {
                try {
                    f.mkdir();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

        }
        return false;
    }

}