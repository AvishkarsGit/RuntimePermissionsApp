package com.example.runtimepermissions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import static android.Manifest.permission.*;
public class MainActivity extends AppCompatActivity {

    Button get;
    private static final int PER_REQ_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        get = findViewById(R.id.btn);

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPer()){
                    Toast.makeText(MainActivity.this, "Permission Already is Granted!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{ACCESS_FINE_LOCATION,ACCESS_COARSE_LOCATION},PER_REQ_CODE);

                }
            }
        });

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);

        if (requestCode == PER_REQ_CODE){
            if (grantResults.length>0){
                int loc=grantResults[0];
                int course=grantResults[1];

                if (loc == PackageManager.PERMISSION_GRANTED && course == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this, "Permission is granted...", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Permission is denied!!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private Boolean checkPer(){

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION) ==
         PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(),ACCESS_COARSE_LOCATION)
        == PackageManager.PERMISSION_GRANTED) {
            return true;
        }else {
            return false;
        }
    }


}