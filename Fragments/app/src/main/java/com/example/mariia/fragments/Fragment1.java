package com.example.mariia.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by mariia on 15.11.2017.
 */

public class Fragment1 extends Fragment {
    private static final String TAG="Fragment1";
    private Button btnNavFrag1;
    private Button btnNavFrag2;
    private Button btnNavFrag3;


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout, container, false);
        btnNavFrag1=(Button)view.findViewById(R.id.btnNavFrag1);
        btnNavFrag2=(Button)view.findViewById(R.id.btnNavFrag2);
        btnNavFrag3=(Button)view.findViewById(R.id.btnNavFrag3);

        Log.d(TAG, "onCreateView: started");

        btnNavFrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Going to Fragment1", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(0);
            }
        });

        btnNavFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Going to Fragment2", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(1);
            }
        });

        btnNavFrag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Going to Fragment3", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(2);
            }
        });

        return view;
    }
}
