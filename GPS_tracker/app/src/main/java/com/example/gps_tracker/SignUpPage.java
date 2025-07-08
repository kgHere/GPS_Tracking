package com.example.gps_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpPage extends AppCompatActivity {
    EditText username,password,repassword;
    Button signUp,signIn;

    DBHelper DB;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        username = (EditText) findViewById(R.id.editTextTextPersonName3);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        repassword = (EditText) findViewById(R.id.editTextTextPassword2);
        signIn = (Button) findViewById(R.id.button4);
        signUp = (Button) findViewById(R.id.button3);
        DB = new DBHelper(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("") || pass.equals("") || repass.equals("")){
                    Toast.makeText(getApplicationContext(),"Please Enter all the fields",Toast.LENGTH_LONG).show();
                }else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser == false){
                            Boolean insert = DB.insertData(user,pass);
                            if(insert == true){
                                Toast.makeText(getApplicationContext(),"Registered Successful",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getApplicationContext(),"Registration Failed ! ",Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"User Already Exists, Please Sign In ",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Password does not match",Toast.LENGTH_LONG).show();
                    }
                }


            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUpPage.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}