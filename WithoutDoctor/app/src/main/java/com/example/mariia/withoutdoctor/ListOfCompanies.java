package com.example.mariia.withoutdoctor;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.database.Query;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by mariia on 10.12.2017.
 */

public class ListOfCompanies extends ListFragment{
    DatabaseReference db;
    ArrayList<String> names;
    ListView lv;
    EditText nameEditTxt, emailTxt;
    private ArrayList<Company> companies;
    final String TAG = "tag";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        db= FirebaseDatabase.getInstance().getReference("companies");
        names = new ArrayList<>();

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Company  company = dataSnapshot.getValue(Company.class);
                Log.wtf(TAG, company.getName());
                Log.wtf(TAG, "AAAAAAAAAAAAAAAAa"+dataSnapshot.getKey());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        FirebaseListAdapter<Company> fireAdapter = new FirebaseListAdapter<Company>
                (getActivity(),
                Company.class,
                android.R.layout.simple_list_item_2,
                        db) {
            @Override
            protected void populateView(View v, Company model, int position) {
                ((TextView)v.findViewById(android.R.id.text1)).setText(model.getName());
                ((TextView)v.findViewById(android.R.id.text2)).setText(model.getEmail());
                names.add(model.getName());
                Log.wtf(TAG ,model.getName());
            }
        };

        setListAdapter(fireAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

//        @Override
//        public void onActivityCreated(Bundle savedInstanceState) {
//            super.onActivityCreated(savedInstanceState);
//
//            ListAdapter adapter = new ArrayAdapter<>(getActivity(),
//                    R.layout.fragment_list, catNamesArray);
//            setListAdapter(adapter);
//        }

    @Override
    public void onStart() {
        super.onStart();

        /** Setting the multiselect choice mode for the listview */
        getListView().setChoiceMode(ListView.CHOICE_MODE_NONE);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(getActivity(), PackagesInCompany.class);
        String message = names.get(position);
        intent.putExtra("company_name", message);
        Log.wtf("company_name", message);
        String pos = String.valueOf(position);
        Log.wtf("position", pos);
        getActivity().startActivity(intent);

        // Capture the layout's TextView and set the string as its text
    }


}
