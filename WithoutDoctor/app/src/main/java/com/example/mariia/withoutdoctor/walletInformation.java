package com.example.mariia.withoutdoctor;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by mariia on 03.12.2017.
 */

public class walletInformation extends Fragment{
    public walletInformation(){ }
    private DatabaseReference dbRef;
    private FirebaseUser currentUser;
    private String userID;
    private Wallet wallet;
    private TextView balance;
    private Button addMoney;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wallet_layout, container, false);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            userID = currentUser.getUid();
        } else {
            Log.e("error", "we haven't registred");
        }
        dbRef = FirebaseDatabase.getInstance().getReference("wallet/"+userID);
        balance = (TextView)view.findViewById(R.id.balance);
        dbRef.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(DataSnapshot dataSnapshot) {
                  wallet = new Wallet();
                  wallet.setEmountOfMoney(dataSnapshot.getValue(Wallet.class).getEmountOfMoney());
                  balance.setText(wallet.getEmountOfMoney().toString());
              }

              @Override
              public void onCancelled(DatabaseError databaseError) {

              }
        });

        addMoney = (Button)view.findViewById(R.id.add_money);
        addMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddMoney.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
