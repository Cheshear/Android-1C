package com.example.mariia.withoutdoctor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends Activity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    final String TAG = "something";

    private EditText ETemail;
    private EditText ETpassword;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                } else {
                    // User is signed out

                }
                // ...
            }
        };
        ETemail = (EditText)findViewById(R.id.editLogin);
        ETpassword = (EditText)findViewById(R.id.editPassword);

        findViewById(R.id.login).setOnClickListener(this);
        findViewById(R.id.register).setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        if(view.getId()==R.id.login){
            signIn(ETemail.getText().toString(), ETpassword.getText().toString());
        } else if(view.getId()==R.id.register){
            registration(ETemail.getText().toString(), ETpassword.getText().toString());
        }
    }

    public void signIn(final String email, final String password){
        mAuth.signInWithEmailAndPassword(email.trim(), password.trim()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Авторизация прошла успешно", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, UserActivity2.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                } else{
                    Toast.makeText(MainActivity.this, "Авторизация не получилась ", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "onComplete: Failed=" + task.getException().getMessage());
                }
            }
        });
    }

    public void registration(final String email, final String password){
        mAuth.createUserWithEmailAndPassword(email.trim(), password.trim()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, RegistrationActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                } else{
                    Toast.makeText(MainActivity.this, "Регистрация не получилась"+' '+email, Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "onComplete: Failed=" + task.getException().getMessage());
                }
            }
        });
    }

}
