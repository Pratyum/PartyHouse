package com.google.devplat.lmoroney.maps3_1;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.ParseUser;

import java.util.ArrayList;

public class FriendsList extends ActionBarActivity {
    private ArrayAdapter<String> mLogsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ParseUser user = ParseUser.getCurrentUser();
        ArrayList<String> Logs  = (ArrayList<String>) user.get("friends");
        mLogsAdapter = new ArrayAdapter<>(this, R.layout.list_logs, R.id.list_logs_textview, Logs);
        ListView listView = (ListView) findViewById(R.id.lv_friends_list);
        listView.setAdapter(mLogsAdapter);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home){
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

}
