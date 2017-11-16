package com.example.mariia.fragments;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by mariia on 15.11.2017.
 */

public class Fragment2 extends Fragment {
    private static final String TAG="Fragment2";
    private Button callBtn;
    private EditText editPhone;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2_layout, container, false);
        callBtn =(Button)view.findViewById(R.id.callBtn);
        editPhone=(EditText)view.findViewById(R.id.editPhone);


        Log.d(TAG, "onCreateView: started");

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int REQUEST_PHONE_CALL=1;
                final String phoneNumber = editPhone.getText().toString();
                if(!phoneNumber.equals("")) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+phoneNumber));
                    Log.d(TAG, "----------------------------------------");
                    Toast.makeText(getActivity(), phoneNumber, Toast.LENGTH_SHORT).show();
                    Log.d(TAG, phoneNumber);
                    Log.d(TAG, "----------------------------------------");

                    if (android.os.Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                        if(ContextCompat.checkSelfPermission(getActivity(),
                                Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);

                        }else {
                            startActivity(callIntent);
                        }
                    }

                }
                else {
                    Toast.makeText(getActivity(), "Please enter phone number", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}
