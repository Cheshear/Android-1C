package com.example.mariia.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by mariia on 15.11.2017.
 */

public class Fragment3 extends Fragment {
    private static final String TAG="Fragment1";
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    private EditText phoneNumber;
    private EditText massage;
    private String phone;
    private String message;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3_layout, container, false);
        Button sendBtn = (Button) view.findViewById(R.id.sendBtn);
        phoneNumber=(EditText)view.findViewById(R.id.phoneNumber2);
        massage=(EditText)view.findViewById(R.id.massage) ;

        Log.d(TAG, "onCreateView: started");


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = phoneNumber.getText().toString();
                message = massage.getText().toString();
                SmsManager smsManager = SmsManager.getDefault();
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.SEND_SMS)
                        != PackageManager.PERMISSION_GRANTED) {
                    getPermissionToReadSMS();
                } else {
                    smsManager.sendTextMessage(phone, null, message, null, null);
                    Toast.makeText(getActivity(), "Message sent!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private static final int READ_SMS_PERMISSIONS_REQUEST = 1;

    public void getPermissionToReadSMS() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_SMS)) {
                Toast.makeText(getActivity(), "Please allow permission!", Toast.LENGTH_SHORT).show();
            }
            requestPermissions(new String[]{Manifest.permission.READ_SMS},
                    READ_SMS_PERMISSIONS_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == READ_SMS_PERMISSIONS_REQUEST) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "Read SMS permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Read SMS permission denied", Toast.LENGTH_SHORT).show();
            }

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
