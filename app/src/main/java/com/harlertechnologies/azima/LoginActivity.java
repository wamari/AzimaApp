package com.harlertechnologies.azima;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onBackPressed(View view){
        super.onBackPressed();
    }

    public void loadLogin(View view){
        Intent intent  =  new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
