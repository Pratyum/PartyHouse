package com.google.devplat.lmoroney.maps3_1;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Page2Activity extends ActionBarActivity {
    private ArrayAdapter<String> mLogsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        String[] LogsArray={"Pratyum","Shantanu","Priyanshu","Divyansh","Varun","Manav"};

        List<String> Logs = new ArrayList<>(Arrays.asList(LogsArray));


        mLogsAdapter= new ArrayAdapter<>(this,R.layout.list_logs,R.id.list_logs_textview,Logs);

        ListView listView= (ListView)findViewById(R.id.listview_logs);

        listView.setAdapter(mLogsAdapter);

        Button submit = (Button) findViewById(R.id.bt_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText address = (EditText) findViewById(R.id.et_address);
                String address_string = address.getText().toString();
                address_string.replaceAll(" ","+");
                address_string.replaceAll(",",",+");
                HttpURLConnection urlConnection = null;

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_party) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
