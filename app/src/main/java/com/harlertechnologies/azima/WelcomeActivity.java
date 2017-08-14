package com.harlertechnologies.azima;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by wamari on 8/13/17.
 */

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
    }

    public void login(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void CreateAccount(View view){
        Intent intent  =  new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    
    //// TODO: 8/14/17 Request permissions to read sms, contacts, location and calls.
    //// TODO: 8/14/17 Remember shared preferences such as user already signed in.
}
