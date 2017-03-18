package com.example.dell.passengerinformationsystem;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    String latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button= (Button)this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                RequestQueue MyRequestQueue = Volley.newRequestQueue(getApplicationContext());
                String url = "https://infinite-caverns-16309.herokuapp.com/getLocation";
                /* StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //This code is executed if the server responds, whether or not the response contains data.
                        //The String 'response' contains the server's response
                        Toast.makeText(MainActivity.this,"Ohh yeahhh", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this,"I have received a response from server and the response is ", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //This code is executed if there is an error.
                        Toast.makeText(MainActivity.this, "Error message", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> MyData = new HashMap<String, String>();
                        MyData.put("routeNo","17D");
                        return MyData;
                    }
                };
                MyRequestQueue.add(MyStringRequest);
            */

                HashMap<String, String> MyData = new HashMap<>();
                MyData.put("routeNo", "17D");
                JsonObjectRequest req = new JsonObjectRequest(url, new JSONObject(MyData),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //VolleyLog.v("Response:%n %s", response.toString(4));
                                Toast.makeText(MainActivity.this,"Naruto Uzamaki!",Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"Sasuke Uchiha",Toast.LENGTH_SHORT).show();
                    }
                });
                MyRequestQueue.add(req);
            }
        });
    }





}
