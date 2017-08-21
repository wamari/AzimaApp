package com.harlertechnologies.azima;

import java.util.Calendar;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText date;
    String phone_IMEI;

    String userGender;

    private EditText editTextFirstName;
    private EditText editTextOtherNames;
    private EditText editTextEmail;
    private EditText editTextIDNo;
    private EditText editTextDOB;
    private EditText editTextPhoneNo;
    private EditText editTextPIN;
    private EditText editTextConfirm;

    private static final String REGISTER_URL = "http://harlertechnologies.com/azima/addUser.php";




    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 0;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextOtherNames = findViewById(R.id.editTextOtherNames);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextIDNo = findViewById(R.id.editTextIDNo);
        editTextDOB = findViewById(R.id.editTextDOB);
        editTextPhoneNo = findViewById(R.id.editTextPhoneNo);
        editTextPIN = findViewById(R.id.editTextPIN);
        editTextConfirm = findViewById(R.id.editTextConfirm);


        //// TODO: 8/21/17 Remember to insert IMEI
        readIMEI();


        date = findViewById(R.id.editTextDOB);
        date.addTextChangedListener(tw);
    }

    //show the dd/mm/yyyy date format
    TextWatcher tw = new TextWatcher() {
        private String current = "";
        private String ddmmyyyy = "DDMMYYYY";
        private Calendar cal = Calendar.getInstance();

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!s.toString().equals(current)) {
                String clean = s.toString().replaceAll("[^\\d.]", "");
                String cleanC = current.replaceAll("[^\\d.]", "");

                int cl = clean.length();
                int sel = cl;
                for (int i = 2; i <= cl && i < 6; i += 2) {
                    sel++;
                }
                //Fix for pressing delete next to a forward slash
                if (clean.equals(cleanC)) sel--;

                if (clean.length() < 8){
                    clean = clean + ddmmyyyy.substring(clean.length());
                }else{
                    //This part makes sure that when we finish entering numbers
                    //the date is correct, fixing it otherwise
                    int day  = Integer.parseInt(clean.substring(0,2));
                    int mon  = Integer.parseInt(clean.substring(2,4));
                    int year = Integer.parseInt(clean.substring(4,8));

                    if(mon > 12) mon = 12;
                    cal.set(Calendar.MONTH, mon-1);
                    year = (year<1900)?1900:(year>2100)?2100:year;
                    cal.set(Calendar.YEAR, year);
                    // ^ first set year for the line below to work correctly
                    //with leap years - otherwise, date e.g. 29/02/2012
                    //would be automatically corrected to 28/02/2012

                    day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                    clean = String.format("%02d%02d%02d",day, mon, year);
                }

                clean = String.format("%s/%s/%s", clean.substring(0, 2),
                        clean.substring(2, 4),
                        clean.substring(4, 8));

                sel = sel < 0 ? 0 : sel;
                current = clean;
                date.setText(current);
                date.setSelection(sel < current.length() ? sel : current.length());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    public void onBackPressed(View view){
        super.onBackPressed();
    }

    public void SignUp(View view){
        Toast.makeText(RegisterActivity.this,"Validate, save and Load the main activity", Toast.LENGTH_SHORT).show();
        //// TODO: 8/21/17 Register user and load login activity 
    }

    public void radioButtonClicked(View view){
        //This variable will store whether the user was male or female
        userGender = "";
        // Check that the button is  now checked?
        boolean checked = ((RadioButton) view).isChecked();
        //check which radio button is checked
        switch (view.getId()){
            case R.id.radioMale:
                if (checked)
                    userGender="Male";
                break;
            case R.id.radioFemale:
                if(checked)
                    userGender="Female";
                break;
        }

    }

    public void readIMEI(){
        //check if the READ_PHONE_STATE permission is already available
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED){
            //READ_PHONE_STATE permission has not been granted
            requestReadPhoneStatePermission();
        }else {
            //READ_PHONE_STATE PERMISSION HAS BEEN GRANTED
            //Have an  object of TelephonyManager
            TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            //GET IMEI NUMBER OF PHONE
            phone_IMEI = tm.getDeviceId();
            Toast.makeText(this, "PHONE IMEI: " +phone_IMEI,Toast.LENGTH_SHORT).show();
        }
    }

    private void requestReadPhoneStatePermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_PHONE_STATE)){
            new AlertDialog.Builder(RegisterActivity.this)
                    .setTitle("Permission Request")
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //re-request
                            ActivityCompat.requestPermissions(RegisterActivity.this,
                                    new String[]{Manifest.permission.READ_PHONE_STATE},
                                    MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
                        }
                    })
                    .show();
        }else{
            // READ_PHONE_STATE permission has not been granted yet. Request it directly.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},
                    MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if(requestCode == MY_PERMISSIONS_REQUEST_READ_PHONE_STATE){
            // Received permission result for READ_PHONE_STATE permission.est.");
            // Check if the only required permission has been granted
            if (grantResults.length ==1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // READ_PHONE_STATE permission has been granted, proceed with displaying IMEI Number
                alertAlert(getString(R.string.permission_available_read_phone_state));
                //doPermissionGrantedStuff();
                checkLocationPermission();
                //// TODO: 8/19/17 Request SMS permissions 
            }else{
                alertAlert(getString(R.string.permissions_not_granted_read_phone_state));
            }
        }
    }

    private void alertAlert(String msg) {
        new AlertDialog.Builder(RegisterActivity.this)
                .setTitle("Permission Required")
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do something here
                    }
                })
                .show();
    }

    public boolean checkLocationPermission(){
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                !=PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)){
                new AlertDialog.Builder(this)
                        .setTitle(R.string.title_location_permission)
                        .setMessage(R.string.text_location_permission)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i){
                                //prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(RegisterActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();
            }else{
                //no explanation needed, we can request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        }else{
            return true;
        }
    }


    private void addUser(){
        //// TODO: 8/21/17 http://www.vetbossel.in/android-login-registration-form-php-mysql/

        String fisrtName = editTextFirstName.getText().toString().trim();
        String otherNames = editTextOtherNames.getText().toString().trim();
    }
}
