package com.example.mariia.withoutdoctor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends Activity {
    Button btn;
    EditText name;
    EditText surname;
    EditText passport;
    String email;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        context = getApplicationContext();
        btn =(Button) findViewById(R.id.save);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        name = (EditText)findViewById(R.id.name);
        surname = (EditText)findViewById(R.id.surname);
        passport = (EditText)findViewById(R.id.passport);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference usersRef = database.getReference("users");
                DatabaseReference newUserRef = usersRef.push();
                newUserRef.setValue(new User(email, name.getText().toString(), surname.getText().toString(), passport.getText().toString()));
                Intent intent = new Intent(context, UserActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
    }
}
