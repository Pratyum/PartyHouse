package com.google.devplat.lmoroney.maps3_1;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class AddFriends extends ActionBarActivity {
    private final String LOG_TAG = AddFriends.class.getSimpleName();
    private ArrayAdapter<String> mLogsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Button button = (Button) findViewById(R.id.bt_search);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText username = (EditText) findViewById(R.id.et_username);
                String username_st = username.getText().toString();
                Log.d(LOG_TAG,username_st);
                if(username_st!=""){
                    ParseQuery<ParseUser> query = ParseUser.getQuery();
                    query.whereEqualTo("username", username_st);
                    query.findInBackground(new FindCallback<ParseUser>() {
                        @Override
                        public void done(final List<ParseUser> objects, com.parse.ParseException e) {
                            if (e == null) {
                                // The query was successful.
                                TextView textView = (TextView) findViewById(R.id.tv_username);
                                textView.setText((String) objects.get(0).get("name"));
                                textView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        ParseUser user = ParseUser.getCurrentUser();
                                        ArrayList<String> friends_ar = (ArrayList<String>) user.get("friends");
                                        if (friends_ar == null){
                                            friends_ar = new ArrayList<String>();
                                        }
                                            if (friends_ar.contains(objects.get(0).get("name"))) {
                                                Toast toast = Toast.makeText(getApplicationContext(), "Already There!", Toast.LENGTH_SHORT);
                                                toast.show();
                                            } else {
//                                        Log.d(LOG_TAG,friends_ar.toString());
                                                friends_ar.add((String)objects.get(0).get("name"));
                                                user.put("friends", friends_ar);
                                                user.saveInBackground();
                                                Toast toast = Toast.makeText(getApplicationContext(), "Friend Added!", Toast.LENGTH_SHORT);
                                                toast.show();
                                            }

                                    }
                                });
                                Log.d(LOG_TAG,objects.get(0).getUsername());

                            } else {
                                e.printStackTrace();
                                // Something went wrong.
                            }
                        }
                    });
                }
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_friends, menu);
        return true;
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