package com.google.devplat.lmoroney.maps3_1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GuestListActivity extends ActionBarActivity {
    private ArrayAdapter<String> mContactsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_list);

        String[] ContactsArray = {"Pratyum", "Shantanu", "Priyanshu", "Divyansh", "Varun", "Manav"};

        List<String> Logs = new ArrayList<>(Arrays.asList(ContactsArray));


        mContactsAdapter = new ArrayAdapter<>(this, R.layout.checkboxlist, R.id.friend_checkbox, Logs);

        ListView listView = (ListView) findViewById(R.id.guest_listview);

        listView.setAdapter(mContactsAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_guest_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
