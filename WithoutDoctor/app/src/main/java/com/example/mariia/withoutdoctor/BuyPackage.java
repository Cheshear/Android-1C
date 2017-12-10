package com.example.mariia.withoutdoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

public class BuyPackage extends AppCompatActivity {
    TextView package_name;
    TextView package_price;
    TextView package_derscription;
    DatabaseReference walletReference;
    DatabaseReference companyAccountRef;
    Button buyPackage;
    DatabaseReference dbRef;
    private String userID;
    private FirebaseUser currentUser;
    final String TAG = "hugyft";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbRef = FirebaseDatabase.getInstance().getReference();

        setContentView(R.layout.activity_buy_package);
        Intent intent = getIntent();
        final String packageName = intent.getStringExtra("package_name");
        final String packagePrice = intent.getStringExtra("package_cost");
        final String packageDesription = intent.getStringExtra("package_description");
        final String companyID = intent.getStringExtra("company_id");
        final String companyName = intent.getStringExtra("company_name");

        package_name = (TextView)findViewById(R.id.package_name);
        package_price = (TextView)findViewById(R.id.package_price);
        package_derscription = (TextView)findViewById(R.id.package_description);
        buyPackage = (Button) findViewById(R.id.buy_package);



        // Capture the layout's TextView and set the string as its text
        package_name.setText(packageName);
        package_price.setText(packagePrice);
        package_derscription.setText(packageDesription);

        buyPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser != null) {
                    userID = currentUser.getUid();
                } else {
                    Log.e("error", "we haven't registred");
                }
                walletReference = dbRef.child("wallet/"+userID+"/emountOfMoney");
                if(walletReference==null){
                    Log.wtf(TAG, "aaaaaaaaaaaaaaaaaaaaaaaaa");
                }
                Log.wtf(TAG, userID);
                walletReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Integer balance = dataSnapshot.getValue(Integer.class);
                        balance -= Integer.parseInt(packagePrice);
                        walletReference.setValue(balance);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.wtf(TAG,"aaaaaaaaaaaaaaaaaaaaa");
                    }
                });
            companyAccountRef = FirebaseDatabase.getInstance().getReference("accounts/" + companyID + "/amountOfMoney");
            companyAccountRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Integer balance = dataSnapshot.getValue(Integer.class);
                    balance += Integer.parseInt(packagePrice);
                    companyAccountRef.setValue(balance);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.wtf(TAG,"aaaaaaaaaaaaaaaaaaaaa");
                }
            });

                DatabaseReference policiesRef = dbRef.child("policy/"+userID);
                Log.wtf(TAG, userID);
                policiesRef.push().setValue(new InsurancePackage(packageName, companyID, companyName, packageDesription, Integer.valueOf(packagePrice)));

                Toast.makeText(BuyPackage.this, "You have bought this package!", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
