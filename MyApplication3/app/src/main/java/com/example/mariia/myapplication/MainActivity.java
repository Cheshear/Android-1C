package com.example.mariia.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.mariia.myapplication.R.id.editText;

public class MainActivity extends AppCompatActivity {

    public int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button dec = (Button) findViewById(R.id.dec);
        Button inc = (Button) findViewById(R.id.inc);
        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Hello World!", Toast.LENGTH_SHORT).show();
                //MainActivity.onClick(v);
                counter--;
                TextView view = (TextView)findViewById(R.id.textView);
                view.setText(""+counter);
            }
        });
        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Hello World!", Toast.LENGTH_SHORT).show();
                //MainActivity.onClick(v);
                counter++;
                TextView view = (TextView)findViewById(R.id.textView);
                view.setText(""+counter);
            }
        });
        Button equal=(Button) findViewById(R.id.equal);
        equal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText text = (EditText)findViewById(R.id.editText);
                int number = Integer.parseInt(text.getText().toString());
                int result = number*counter;
                TextView view1 = (TextView) findViewById(R.id.value);
                view1.setText(""+result);
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        outstate.putInt("myCounter", counter);
        TextView view1 = (TextView) findViewById(R.id.value);
        TextView view = (TextView)findViewById(R.id.textView);
        outstate.putString("myStr", view.getText().toString());
        outstate.putString("myStr1", view1.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        counter=savedInstanceState.getInt("myCounter");
        TextView view1 = (TextView) findViewById(R.id.value);
        TextView view = (TextView)findViewById(R.id.textView);
        view1.setText(savedInstanceState.getString("myStr1"));
        view.setText(savedInstanceState.getString("myStr"));
    }


//    public void onClick(View view)
//    {
//        Toast.makeText(getApplicationContext(), "Hello World!", Toast.LENGTH_SHORT).show();
//    }
}
