package com.harlertechnologies.azima;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by wamari on 8/13/17.
 */

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
    }
    
    //// TODO: 8/14/17 Request permissions to read sms, contacts, location and calls.
    //// TODO: 8/14/17 Remember shared preferences such as user already signed in.
}
