package com.example.bashayer93.invitelogs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity {

    EditText id, name;
    String Id, Name;
    Context ctx=this;
    String ID=null, NAME=null, DATE=null, EMAIL=null ,NUM_OF_VISIITS=null ,REF_NUM =null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        id = (EditText) findViewById(R.id.rid);
        name = (EditText) findViewById(R.id.name);
    }


    public void main_log(View v){
        Id = id.getText().toString();
        Name = name.getText().toString();
        BackGround b = new BackGround();
        b.execute(Id, Name);
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String id = params[0];
            String name = params[1];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://10.0.2.2/ES/login.php");
                String urlParams = "r_id="+id+"&v_name="+name;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }

                is.close();
                httpURLConnection.disconnect();

                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");
                ID = user_data.getString("r_id");
                NAME = user_data.getString("v_name");
                DATE = user_data.getString("v_date");
                EMAIL = user_data.getString("v_email");
                NUM_OF_VISIITS = user_data.getString("num_of_visits");
                REF_NUM = user_data.getString("ref_num");
            } catch (JSONException e) {
                e.printStackTrace();

            }

            Intent i = new Intent(ctx, invitelogs.class);
            i.putExtra("r_id", ID);
            i.putExtra("v_name", NAME);
            i.putExtra("v_date", DATE);
            i.putExtra("v_email", EMAIL);
            i.putExtra("num_of_visits", NUM_OF_VISIITS);
            i.putExtra("ref_num", REF_NUM);
            startActivity(i);

        }
    }
}