package com.example.mariia.withoutdoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserPolicyInformation extends AppCompatActivity {
    TextView package_name;
    TextView package_price;
    TextView package_derscription;
    DatabaseReference walletReference;
    DatabaseReference companyAccountRef;
    Button buyPackage;
    DatabaseReference walletdbRef;
    private String userID;
    private FirebaseUser currentUser;
    final String TAG = "hugyft";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_policy_information);
        walletdbRef = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        final String packageName = intent.getStringExtra("package_name");
        final String packagePrice = intent.getStringExtra("package_cost");
        final String packageDesription = intent.getStringExtra("package_description");
        final String companyID = intent.getStringExtra("company_id");
        final String companyName = intent.getStringExtra("company_name");

        package_name = (TextView)findViewById(R.id.user_package_name);
        package_price = (TextView)findViewById(R.id.user_package_price);
        package_derscription = (TextView)findViewById(R.id.user_package_description);



        // Capture the layout's TextView and set the string as its text
        package_name.setText(packageName);
        package_price.setText(packagePrice);
        package_derscription.setText(packageDesription);
    }
}
