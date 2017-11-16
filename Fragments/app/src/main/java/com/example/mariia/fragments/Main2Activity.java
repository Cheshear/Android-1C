package com.example.mariia.fragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    EditText editLogin;
    EditText editPassword;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editLogin=(EditText)findViewById(R.id.editLogin);
        editPassword=(EditText)findViewById(R.id.editPassword);
        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myLogin = editLogin.getText().toString();
                String myPassword = editPassword.getText().toString();
                if(myLogin.length()>3 && myPassword.length()>3){
                    Intent intent=new Intent(Main2Activity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
