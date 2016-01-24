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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GuestListActivity extends ActionBarActivity {

    public LinearLayout linearList ;
    CheckBox checkBox;
    ArrayList<String> finalArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_list);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ParseUser user = ParseUser.getCurrentUser();
        ArrayList<String> Logs  = (ArrayList<String>) user.get("friends");
//        String[] ContactsArray = {"Pratyum", "Shantanu", "Priyanshu", "Divyansh", "Varun", "Manav"};

//        ArrayList<String> Logs = new ArrayList<>(Arrays.asList(ContactsArray));

       // ArrayList<String> Logs = getIntent().getSerializableExtra("KEY");

//        mContactsAdapter = new ArrayAdapter<>(this, R.layout.checkboxlist, R.id.friend_checkbox, Logs);
//

      linearList = (LinearLayout) findViewById(R.id.linear_list);
//        //listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//
//        listView.setAdapter(mContactsAdapter);

        for(int i=0; i<Logs.size(); i++)
        {
            checkBox=new CheckBox(this);
            checkBox.setId(i);
            checkBox.setText(Logs.get(i));
            checkBox.setOnClickListener(getOnClickDo(checkBox));
            linearList.addView(checkBox);
        }

//        final Button confirm = (Button) findViewById(R.id.next_button);
//        confirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                confirm(view);
//            }
//        });
    }
    View.OnClickListener getOnClickDo(final Button b)
    {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(finalArray.contains(b.getText().toString()))
                    finalArray.remove(b.getText().toString());
                else
                    finalArray.add(b.getText().toString());
            }
        };
    }

//    public void confirm(View view)
//    {
//        Log.d("Party", "Confirm Pressed");
//        String partyName = getIntent().getStringExtra("Party");
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("Party");
//        query.whereEqualTo("Name", partyName);
//        query.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> objects, com.parse.ParseException e) {
//                if (e == null) {
//                    Log.d("Party", "Confirm Pressed");
//                    String[] finalParse = new String[finalArray.size()];
//                    finalParse = finalArray.toArray(finalParse);
//                    objects.get(0).put("Members", finalArray);
//                    objects.get(0).saveInBackground();
//                    Intent intent = new Intent(getApplicationContext(), Page2Activity.class);
//                    intent.putExtra("KEY", finalArray);
//                    startActivity(intent);
//                } else {
//                    Log.d("score", "Error: " + e.getMessage());
//                }
//            }
//        });
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_guest_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (item.getItemId() == R.id.next_button) {
            String partyName = getIntent().getStringExtra("Party");
//            Log.d("Party",partyName);
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Party");
            query.whereEqualTo("Name", partyName);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, com.parse.ParseException e) {
                    if (e == null) {
                        objects.get(0).put("Members", finalArray);
                        objects.get(0).saveInBackground();
                        String partyname = getIntent().getStringExtra("Party");
                        Intent intent = new Intent(getApplicationContext(), Page2Activity.class);
                        intent.putExtra("KEY", finalArray);
                        intent.putExtra("Party",partyname);

                        startActivity(intent);
                    } else {
                        Log.d("score", "Error: " + e.getMessage());
                    }
                }
            });
            return true;
            }
        else if(id==android.R.id.home){
            this.finish();
            return true;
        }
        // other menu select events may be present here

        return super.onOptionsItemSelected(item);
    }

}
