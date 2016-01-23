package com.google.devplat.lmoroney.maps3_1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GuestListActivity extends ActionBarActivity {
    private ArrayAdapter<String> mContactsAdapter;
    public  ListView listView;
    private final String LOG_TAG = GuestListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_list);

        String[] ContactsArray = {"Pratyum", "Shantanu", "Priyanshu", "Divyansh", "Varun", "Manav"};

        List<String> Logs = new ArrayList<>(Arrays.asList(ContactsArray));

       // ArrayList<String> Logs = getIntent().getSerializableExtra("KEY");

        mContactsAdapter = new ArrayAdapter<>(this, R.layout.checkboxlist, R.id.friend_checkbox, Logs);

        listView = (ListView) findViewById(R.id.guest_listview);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

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

    public void confirm(View view)
    {
        SparseBooleanArray checked = listView.getCheckedItemPositions();
        Log.d(LOG_TAG,checked.toString());
        ArrayList<String> finalArray = new ArrayList<>();
//        if (checked != null) {
//            for (int i=0; i<checked.size(); i++) {
//                if (checked.valueAt(i)) {
//                    String item = listView.getAdapter().getItem(
//                            checked.keyAt(i)).toString();
//                    Log.d(LOG_TAG, item + " was selected");
//                    finalArray.add(mContactsAdapter.getItem(i));
//                }
//            }
//        }
        if(checked!=null) {
            for (int i = 0; i < mContactsAdapter.getCount(); i++) {
                if (checked.get(i)) {
                    finalArray.add(mContactsAdapter.getItem(i));
                    Log.d(LOG_TAG, mContactsAdapter.getItem(i));
                }
            }
        }
        Intent intent = new Intent(this,Page2Activity.class);
        intent.putExtra("KEY",finalArray);
        startActivity(intent);


    }

}
