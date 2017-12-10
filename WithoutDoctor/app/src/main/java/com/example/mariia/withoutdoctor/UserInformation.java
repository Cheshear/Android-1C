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
import com.google.firebase.storage.StreamDownloadTask;

/**
 * Created by mariia on 03.12.2017.
 */

public class UserInformation extends Fragment{

    public UserInformation(){ }
    private DatabaseReference dbRef;
    private FirebaseUser currentUser;
    private String userID;
    private TextView name;
    private TextView surname;
    private TextView email;
    private User user;
    private Button myPolicy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.user_information, container, false );
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            userID = currentUser.getUid();
        } else {
            Log.e("error" ,"we haven't registred");
        }
        dbRef = FirebaseDatabase.getInstance().getReference("users/"+userID);
        Log.wtf("hjkhf", userID);
//        Log.d("hhjv", userID);
        name = (TextView)view.findViewById(R.id.name1);
        surname = (TextView)view.findViewById(R.id.surename1);
        email=(TextView)view.findViewById(R.id.email1);
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user=new User();
                user.setName(dataSnapshot.getValue(User.class).getName());
                user.setEmail(dataSnapshot.getValue(User.class).getEmail());
                user.setSurname(dataSnapshot.getValue(User.class).getSurname());
                Log.wtf("hjkhf", user.getName());
                Log.wtf("hjkhf", user.getEmail());
                Log.wtf("hjkhf", user.getSurname());
                name.setText(user.getName());
                surname.setText(user.getSurname());
                email.setText(user.getEmail());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myPolicy = (Button)view.findViewById(R.id.button3);
        myPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UserPolicy.class);
                startActivity(intent);
            }
        });

//        Log.e("fhjkl", mySurname);
//        Log.e("hgjk", myEmail);
        return view;
    }
}
