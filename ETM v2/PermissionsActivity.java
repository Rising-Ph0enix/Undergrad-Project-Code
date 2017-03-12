package com.example.dell.etmv2;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.security.Permission;

public class PermissionsActivity extends AppCompatActivity {
    Intent main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);
        ActivityCompat.requestPermissions(PermissionsActivity.this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        main=new Intent(this,MainActivity.class);
        startActivity(main);
    }
}
