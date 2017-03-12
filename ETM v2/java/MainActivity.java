package com.example.dell.etmv2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{

    SQLiteDatabase myDatbase;
    Cursor resultSet;
    Cursor resultSet2;
    EditText t1;
    EditText t2;
    static final String routeNo="17D";
    String currentStop;
    String destinationStop;
    Intent myIntent;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton up=(ImageButton)this.findViewById(R.id.button2);
        ImageButton down=(ImageButton)this.findViewById(R.id.button3);
        ImageButton power=(ImageButton)this.findViewById(R.id.button4);
        Button one=(Button)this.findViewById(R.id.button5);
        Button two=(Button)this.findViewById(R.id.button6);
        Button three=(Button)this.findViewById(R.id.button7);
        Button four=(Button)this.findViewById(R.id.button8);
        Button five=(Button)this.findViewById(R.id.button9);
        Button six=(Button)this.findViewById(R.id.button10);
        Button seven=(Button)this.findViewById(R.id.button11);
        Button menu=(Button)this.findViewById(R.id.button14);
        Button clear=(Button)this.findViewById(R.id.button15);
        Button ok=(Button)this.findViewById(R.id.button17);
        t1=(EditText)this.findViewById(R.id.EditText1);
        t2=(EditText)this.findViewById(R.id.EditText2);
        new DBTask(this).execute("ETM activated");
        Intent i=new Intent(this,GPS.class);
        this.startService(i);

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
            System.exit(0); onDestroy();
        }
        });
        // Display the stop mapped to the number buttons
        one.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                resultSet=myDatbase.rawQuery("Select Stop from StopMapping where StopNumber=1",null);
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
                resultSet=myDatbase.rawQuery("Select Stop from StopMapping where StopNumber=2",null);
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
                resultSet=myDatbase.rawQuery("Select Stop from StopMapping where StopNumber=3",null);
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
                resultSet=myDatbase.rawQuery("Select Stop from StopMapping where StopNumber=4",null);
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
                resultSet=myDatbase.rawQuery("Select Stop from StopMapping where StopNumber=5",null);
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
                resultSet=myDatbase.rawQuery("Select Stop from StopMapping where StopNumber=6",null);
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
                resultSet=myDatbase.rawQuery("Select Stop from StopMapping where StopNumber=7",null);
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
                myIntent=new Intent(MainActivity.this,MenuActivity.class);
                startActivity(myIntent);
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
                Toast.makeText(MainActivity.this,"Ticket issued",Toast.LENGTH_SHORT).show();
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
        myDatbase.close();
        stopService(i);
    }

    private class DBTask extends AsyncTask<String, Void, String> {

        private Context context;

        public DBTask(Context context){
            this.context=context;
        }

        @Override
        protected String doInBackground(String... params) {
            //String dummy=params[0];
            myDatbase=openOrCreateDatabase("RouteInfo",MODE_PRIVATE,null);
            // Create table for my database
            myDatbase.execSQL("CREATE TABLE IF NOT EXISTS StopMapping(RouteNo VARCHAR(5),Stop VARCHAR(50),StopNumber INTEGER,CurrentStop VARCHAR(50));");
            // Insert "pre-programmed" data onto ETM built-in table
            myDatbase.execSQL("INSERT INTO StopMapping VALUES('16J','Airport',1,'Airport');");
            myDatbase.execSQL("INSERT INTO StopMapping VALUES('16J','Avaniapuram',2,'Airport');");
            myDatbase.execSQL("INSERT INTO StopMapping VALUES('16J','Villapuram',3,'Airport');");
            myDatbase.execSQL("INSERT INTO StopMapping VALUES('16J','Therku vassal',4,'Airport');");
            myDatbase.execSQL("INSERT INTO StopMapping VALUES('16J','Narimedu',5,'Airport');");
            myDatbase.execSQL("INSERT INTO StopMapping VALUES('16J','Krishnapuram Colony',6,'Airport');");
            myDatbase.execSQL("INSERT INTO StopMapping VALUES('16J','Thapal Thanthi Nagar',7,'Airport');");
            // Fetching data (current stop) from database
            resultSet2=myDatbase.rawQuery("Select Stop from StopMapping",null);
            if(resultSet2==null && resultSet2.getCount()==0){
                Toast.makeText(MainActivity.this, "No records selected from your query!!", Toast.LENGTH_SHORT).show();
            }
            resultSet2.moveToFirst();
            currentStop=resultSet2.getString(0);
            // Display the current stop in From: field
            return currentStop;
        }



        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            t1.setText(result);
            //Toast.makeText(this.context,"ETM activated",Toast.LENGTH_SHORT).show();
        }


    }



}




