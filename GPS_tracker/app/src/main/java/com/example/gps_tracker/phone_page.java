package com.example.gps_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class phone_page extends AppCompatActivity {
    EditText editText;
    Button btn;
    String ph_no;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_page);
        editText = (EditText) findViewById(R.id.et1);
        btn = (Button) findViewById(R.id.btnOtp);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                ph_no = editText.getText().toString();
                if(ph_no.equals("")){
                    Toast.makeText(getApplicationContext(), "please enter sender's phone number", Toast.LENGTH_LONG).show();
                }else{
                    Intent i = new Intent(phone_page.this,Otp_activity.class);
                    i.putExtra("number",ph_no);
                    startActivity(i);
                }

            }

        });




    }
}