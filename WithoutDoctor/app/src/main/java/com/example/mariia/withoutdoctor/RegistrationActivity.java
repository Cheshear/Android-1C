package com.example.mariia.withoutdoctor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends Activity {
    Button btn;
    EditText name;
    EditText surname;
    EditText passport;
    String email;
    EditText walletNumber;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        context = getApplicationContext();
        btn =(Button) findViewById(R.id.save);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        name = (EditText)findViewById(R.id.name1);
        surname = (EditText)findViewById(R.id.surname);
        passport = (EditText)findViewById(R.id.passport);
        walletNumber = (EditText)findViewById(R.id.wallet_number);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = "";
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    userID = user.getUid();
                } else {
                    Log.e("error","We have big troubles");
                }
                DatabaseReference usersRef = database.getReference("users/"+userID);
                usersRef.setValue(new User(email, name.getText().toString(), surname.getText().toString(), passport.getText().toString()));
                DatabaseReference walletRef = database.getReference("wallet/"+userID);
                walletRef.setValue(new Wallet(walletNumber.getText().toString(), 0));
                Intent intent = new Intent(context, UserActivity2.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
    }
}
