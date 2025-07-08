package com.example.gps_tracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Otp_activity extends AppCompatActivity {
    String s,res,phone_no;
    Button btnMapsPage,btnDecrypt;
    EditText etCypherText,etPlainText;
    private static final int REQ_USER_CONSENT = 200;
    SmsBrocastReceiver smsBrocastReceiver;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        btnMapsPage = (Button) findViewById(R.id.btn2);
        btnDecrypt = (Button) findViewById(R.id.btn1);
        etCypherText = (EditText) findViewById(R.id.et1);
        etPlainText = (EditText) findViewById(R.id.et2);
        Intent intent = getIntent();
        phone_no = intent.getStringExtra("number");
//        Toast.makeText(this, "this is the number "+ phone_no, Toast.LENGTH_SHORT).show();


        btnDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = etCypherText.getText().toString();
                res = "";
                int i;
                char ch = '\0';
                for(i = 0; i < s.length() ; i++) {
                    ch = s.charAt(i);
                    res = res + (char) (((int) ch) - 2);
                }
                etPlainText.setText(""+res);

                Toast.makeText(getApplicationContext(),"Message is Decrypted",Toast.LENGTH_LONG).show();
//                String s="",lat="",lon="",word="",word1=",";
//                Integer count = null,j=0;
//                for(int ii=0;ii<res.length();ii++){
//                    word = word+res.charAt(ii);
//                    if(word.equals(word1)){
//                        count=ii;
//                    }
//                    word="";
//
//                }
//                Toast.makeText(Otp_activity.this, "i value = "+ count, Toast.LENGTH_SHORT).show();
//                for(int ii=0;ii<count;ii++){
//                    lat=lat+res.charAt(ii);
//                }
//
//                for(int ii=(count+1);ii<res.length();ii++){
//                    lon=lon+res.charAt(ii);
//                }
//                Toast.makeText(Otp_activity.this, "value of lat = "+lat + " longitude = "+ lon, Toast.LENGTH_SHORT).show();
            }
        });
        btnMapsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"Opening Maps",Toast.LENGTH_LONG).show();
                Intent i = new Intent(Otp_activity.this,MapsActivity3.class);
                i.putExtra("coordinates",res);
                startActivity(i);
            }
        });
        startSmartUserConsent();

    }

    private void startSmartUserConsent() {
        SmsRetrieverClient client = SmsRetriever.getClient(this);
        //Add contact number here to get OTP
        client.startSmsUserConsent(phone_no);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_USER_CONSENT){
            if((resultCode == RESULT_OK) && (data != null)){
                String message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
                Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
                etCypherText.setText(message);
//                getOTPfromMessage(message);

            }
        }
    }

    private void getOTPfromMessage(String message) {
        etCypherText.setText(message);
//        Pattern otpPattern = Pattern.compile("^\\d+;[\\d<>/:]*$");
//        Matcher matcher = otpPattern.matcher(message);
//        if(matcher.find()){
//            etCypherText.setText(matcher.group(0));
//        }

        //et.setText(message);
    }
    private void registerBroadcastReceiver(){
        smsBrocastReceiver = new SmsBrocastReceiver();
        smsBrocastReceiver.smsBroadcastReceiverListener = new SmsBrocastReceiver.SmsBroadcastReceiverListener() {
            @Override
            public void onSuccess(Intent intent) {
                startActivityForResult(intent,REQ_USER_CONSENT);
            }

            @Override
            public void onFailure() {

            }
        };
        IntentFilter intentFilter = new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);
        registerReceiver(smsBrocastReceiver,intentFilter);
    }
    protected void onStart() {
        super.onStart();
        registerBroadcastReceiver();

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(smsBrocastReceiver);
    }


}


/* This is my project */

