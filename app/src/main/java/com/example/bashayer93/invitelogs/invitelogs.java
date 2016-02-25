package com.example.bashayer93.invitelogs;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class invitelogs extends Activity {

    String id , name, date, email, num_of_visits, ref_num;
    TextView idTV ,nameTV, dateTV , emailTV, num_of_visitsTV, ref_numTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitelogs);

        idTV = (TextView) findViewById(R.id.v_id);
        nameTV = (TextView) findViewById(R.id.vname);
        dateTV = (TextView) findViewById(R.id.vdate);
        emailTV = (TextView) findViewById(R.id.vemail);
        num_of_visitsTV =(TextView) findViewById(R.id.vnum);
        ref_numTV=(TextView) findViewById(R.id.vref);


        id = getIntent().getStringExtra("r_id");
        name = getIntent().getStringExtra("v_name");
        date = getIntent().getStringExtra("v_date");
        email = getIntent().getStringExtra("v_email");
        num_of_visits = getIntent().getStringExtra("num_of_visits");
        ref_num = getIntent().getStringExtra("ref_num");


        idTV.setText(""+id);
        nameTV.setText(" "+name);
        dateTV.setText(""+date);
        emailTV.setText(""+email);
        num_of_visitsTV.setText(""+num_of_visits);
        ref_numTV.setText(" "+ref_num);
    }
}