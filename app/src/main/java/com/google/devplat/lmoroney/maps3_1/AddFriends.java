package com.google.devplat.lmoroney.maps3_1;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class AddFriends extends ActionBarActivity {
    private final String LOG_TAG = AddFriends.class.getSimpleName();
    private ArrayAdapter<String> mLogsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);
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
                                textView.setText(objects.get(0).getUsername());
                                textView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        ParseUser user = ParseUser.getCurrentUser();
                                        ArrayList<ParseUser> friends_ar = (ArrayList<ParseUser>) user.get("friends");
                                        if (friends_ar == null){
                                            friends_ar = new ArrayList<ParseUser>();
                                        }
//                                        Log.d(LOG_TAG,friends_ar.toString());
                                        friends_ar.add(objects.get(0));
                                        user.put("friends", friends_ar);
                                        user.saveInBackground();
                                        Toast toast = Toast.makeText(getApplicationContext(),"Friend Added!",Toast.LENGTH_SHORT);
                                        toast.show();
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

}
