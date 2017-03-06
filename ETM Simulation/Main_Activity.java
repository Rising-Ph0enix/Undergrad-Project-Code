package com.example.dell.electronicticketingmachine;

import android.app.Application;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.database.sqlite.*;
import android.widget.TextView;
import android.widget.Toast;
// import Google's valley library to use http post requests
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;



public class MainActivity extends AppCompatActivity {
    // Declaration of variables used in program
    SQLiteDatabase mydatbase;
    ImageButton power;
    ImageButton up;
    ImageButton down;
    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button menu;
    Button clear;
    Button ok;
    Cursor resultSet;
    Cursor resultSet2;
    static final String routeNo="17D";
    String currentStop;
    String destinationStop;
    Intent myintent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        up=(ImageButton)this.findViewById(R.id.button2);
        down=(ImageButton)this.findViewById(R.id.button3);
        power=(ImageButton)this.findViewById(R.id.button4);
        one=(Button)this.findViewById(R.id.button5);
        two=(Button)this.findViewById(R.id.button6);
        three=(Button)this.findViewById(R.id.button7);
        four=(Button)this.findViewById(R.id.button8);
        five=(Button)this.findViewById(R.id.button9);
        six=(Button)this.findViewById(R.id.button10);
        seven=(Button)this.findViewById(R.id.button11);
        menu=(Button)this.findViewById(R.id.button14);
        clear=(Button)this.findViewById(R.id.button15);
        ok=(Button)this.findViewById(R.id.button17);

        final EditText t1=(EditText)this.findViewById(R.id.EditText1);
        final EditText t2=(EditText)this.findViewById(R.id.EditText2);

    // Create or open RouteInfo database
        mydatbase=openOrCreateDatabase("RouteInfo",MODE_PRIVATE,null);
        // Create table for my database
        mydatbase.execSQL("CREATE TABLE IF NOT EXISTS StopMapping(RouteNo VARCHAR(5),Stop VARCHAR(50),StopNumber INTEGER,CurrentStop VARCHAR(50));");
        // Insert "pre-programmed" data onto ETM built-in table
        mydatbase.execSQL("INSERT INTO StopMapping VALUES('16J','Airport',1,'Airport');");
        mydatbase.execSQL("INSERT INTO StopMapping VALUES('16J','Avaniapuram',2,'Airport');");
        mydatbase.execSQL("INSERT INTO StopMapping VALUES('16J','Villapuram',3,'Airport');");
        mydatbase.execSQL("INSERT INTO StopMapping VALUES('16J','Therku vassal',4,'Airport');");
        mydatbase.execSQL("INSERT INTO StopMapping VALUES('16J','Narimedu',5,'Airport');");
        mydatbase.execSQL("INSERT INTO StopMapping VALUES('16J','Krishnapuram Colony',6,'Airport');");
        mydatbase.execSQL("INSERT INTO StopMapping VALUES('16J','Thapal Thanthi Nagar',7,'Airport');");
        // Fetching data (current stop) from database
        resultSet2=mydatbase.rawQuery("Select Stop from StopMapping",null);
        if(resultSet2==null && resultSet2.getCount()==0){
            Toast.makeText(MainActivity.this, "No records selected from your query!!", Toast.LENGTH_SHORT).show();
        }
        resultSet2.moveToFirst();
        currentStop=resultSet2.getString(0);
        // Display the current stop in From: field
        t1.setText(currentStop,null);


        // Increment the current stop
        up.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(resultSet2.isLast()){
                    resultSet2.moveToFirst();
                }
                else{
                    resultSet2.moveToNext();
                }
                currentStop=resultSet2.getString(0);
                t1.setText(currentStop,null);
                RequestQueue MyRequestQueue = Volley.newRequestQueue(getApplicationContext());
                String url = "https://infinite-caverns-16309.herokuapp.com/currentStop";
                StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //This code is executed if the server responds, whether or not the response contains data.
                        //The String 'response' contains the server's response.
                    }
                }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //This code is executed if there is an error.
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> MyData = new HashMap<String, String>();
                        MyData.put("routeNo",routeNo);
                        MyData.put("currentStop", currentStop);
                        return MyData;
                    }
                };
                MyRequestQueue.add(MyStringRequest);

            }
        });
        // Decrement the current stop
        down.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(resultSet2.isFirst()){
                    resultSet2.moveToLast();
                }
                else{
                    resultSet2.moveToPrevious();
                }
                currentStop=resultSet2.getString(0);
                t1.setText(currentStop,null);
                RequestQueue MyRequestQueue = Volley.newRequestQueue(getApplicationContext());
                String url = "https://infinite-caverns-16309.herokuapp.com/currentStop";
                StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //This code is executed if the server responds, whether or not the response contains data.
                        //The String 'response' contains the server's response.
                    }
                }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //This code is executed if there is an error.
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> MyData = new HashMap<String, String>();
                        MyData.put("routeNo",routeNo);
                        MyData.put("currentStop", currentStop);
                        return MyData;
                    }
                };
                MyRequestQueue.add(MyStringRequest);
            }
        });
        // Exit the app when clicking on power button
        power.setOnClickListener(new View.OnClickListener(){public void onClick(View v)
        {
            System.exit(0);
        }
        });
        // Display the stop mapped to the number buttons
        one.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                resultSet=mydatbase.rawQuery("Select Stop from StopMapping where StopNumber=1",null);
                if(resultSet==null && resultSet.getCount()==0){
                    Toast.makeText(MainActivity.this, "No records selected from your query!!", Toast.LENGTH_SHORT).show();
                }
                resultSet.moveToFirst();
                destinationStop=resultSet.getString(0);
                t2.setText(destinationStop,null);
                resultSet.close();
            }
        });
        two.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                resultSet=mydatbase.rawQuery("Select Stop from StopMapping where StopNumber=2",null);
                if(resultSet==null && resultSet.getCount()==0){
                    Toast.makeText(MainActivity.this, "No records selected from your query!!", Toast.LENGTH_SHORT).show();
                }
                resultSet.moveToFirst();
                destinationStop=resultSet.getString(0);
                t2.setText(destinationStop,null);
                resultSet.close();
            }
        });
        three.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                resultSet=mydatbase.rawQuery("Select Stop from StopMapping where StopNumber=3",null);
                if(resultSet==null && resultSet.getCount()==0){
                    Toast.makeText(MainActivity.this, "No records selected from your query!!", Toast.LENGTH_SHORT).show();
                }
                resultSet.moveToFirst();
                destinationStop=resultSet.getString(0);
                t2.setText(destinationStop,null);
                resultSet.close();
            }
        });
        four.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                resultSet=mydatbase.rawQuery("Select Stop from StopMapping where StopNumber=4",null);
                if(resultSet==null && resultSet.getCount()==0){
                    Toast.makeText(MainActivity.this, "No records selected from your query!!", Toast.LENGTH_SHORT).show();
                }
                resultSet.moveToFirst();
                destinationStop=resultSet.getString(0);
                t2.setText(destinationStop,null);
                resultSet.close();
            }
        });
        five.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                resultSet=mydatbase.rawQuery("Select Stop from StopMapping where StopNumber=5",null);
                if(resultSet==null && resultSet.getCount()==0){
                    Toast.makeText(MainActivity.this, "No records selected from your query!!", Toast.LENGTH_SHORT).show();
                }
                resultSet.moveToFirst();
                destinationStop=resultSet.getString(0);
                t2.setText(destinationStop,null);
                resultSet.close();
            }
        });
        six.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                resultSet=mydatbase.rawQuery("Select Stop from StopMapping where StopNumber=6",null);
                if(resultSet==null && resultSet.getCount()==0){
                    Toast.makeText(MainActivity.this, "No records selected from your query!!", Toast.LENGTH_SHORT).show();
                }
                resultSet.moveToFirst();
                destinationStop=resultSet.getString(0);
                t2.setText(destinationStop,null);
                resultSet.close();
            }
        });
        seven.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                resultSet=mydatbase.rawQuery("Select Stop from StopMapping where StopNumber=7",null);
                if(resultSet==null && resultSet.getCount()==0){
                    Toast.makeText(MainActivity.this, "No records selected from your query!!", Toast.LENGTH_SHORT).show();
                }
                resultSet.moveToFirst();
                destinationStop=resultSet.getString(0);
                t2.setText(destinationStop,null);
                resultSet.close();
            }
        });
        menu.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // open Menu activity
                myintent=new Intent(MainActivity.this,MenuActivity.class);
                startActivity(myintent);
            }
        });
        clear.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                t2.setText("",null);
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Enter the dragon",Toast.LENGTH_SHORT).show();
                currentStop=t1.getText().toString();
                destinationStop=t2.getText().toString();
                RequestQueue MyRequestQueue = Volley.newRequestQueue(getApplicationContext());
                String url = "https://infinite-caverns-16309.herokuapp.com/ticket";
                StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //This code is executed if the server responds, whether or not the response contains data.
                        //The String 'response' contains the server's response.
                    }
                }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //This code is executed if there is an error.
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> MyData = new HashMap<String, String>();
                        MyData.put("routeNo",routeNo);
                        MyData.put("currentStop", currentStop);
                        MyData.put("destinationStop",destinationStop);
                        return MyData;
                    }
                };
                MyRequestQueue.add(MyStringRequest);

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        resultSet2.close();
        mydatbase.close();
    }
}
