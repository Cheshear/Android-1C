package com.example.mariia.withoutdoctor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class AddMoney extends Activity {
    private DatabaseReference dbRef;
    private FirebaseUser currentUser;
    private String userID;
    private EditText emountOfMoney;
    private Button addMoney;
    private Integer newBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_money);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            userID = currentUser.getUid();
        } else {
            Log.e("error", "we haven't registred");
        }
        dbRef = FirebaseDatabase.getInstance().getReference("wallet/" + userID + "/emountOfMoney");
        addMoney = (Button) findViewById(R.id.add_money_btn);
        emountOfMoney = (EditText) findViewById(R.id.emount_of_money);
        addMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String money = emountOfMoney.getText().toString();
                dbRef.runTransaction(new Transaction.Handler() {
                    @Override
                    public Transaction.Result doTransaction(MutableData mutableData) {
                        Integer prevBalance = mutableData.getValue(Integer.class);
                        final Integer intMoney = Integer.parseInt(money);
                        Integer newBalance = prevBalance + intMoney;
                        mutableData.setValue(newBalance);
                        return Transaction.success(mutableData);
                    }

                    @Override
                    public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                        Log.e("ghhjk", "transaction:onComplete:" + databaseError);
                    }
                });
//                try{
//                    walletdbRef.setValue(intMoney);
//                } catch (Exception e){
//                    Log.e("hgh", e.toString());
//                }
                Toast.makeText(getApplicationContext(), "Деньги были сняты с вашего яндекс кошелька!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
