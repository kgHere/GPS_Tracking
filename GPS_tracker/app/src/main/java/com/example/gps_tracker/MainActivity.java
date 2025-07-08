package com.example.gps_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    Button btnLogin,btnSignUp;
    DBHelper DB;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.Username);
        password = (EditText) findViewById(R.id.Password);
        btnLogin = (Button) findViewById(R.id.button);
        btnSignUp = (Button) findViewById(R.id.button2);
        DB = new DBHelper(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("") || pass.equals("")){
                    Toast.makeText(getApplicationContext(),"Please Enter all fields",Toast.LENGTH_LONG).show();
                }else{
                    Boolean checkPassword = DB.checkusernamepassword(user,pass);
                    if(checkPassword == true){
                        Toast.makeText(getApplicationContext(),"Sign-In Successfully",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(MainActivity.this,phone_page.class);
                        startActivity(i);

                    }else{
                        Toast.makeText(getApplicationContext(),"Invalid Credentails",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,SignUpPage.class);
                startActivity(i);
            }
        });

    }
}