/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.devplat.lmoroney.maps3_1;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
        ParseLoginBuilder builder = new ParseLoginBuilder(MainActivity.this);
        startActivityForResult(builder.build(), 0);

        final Party party_data[] = new Party[]
                {
                        new Party("Halloween Party", "universal studios\n11/02/16 17:47"),
                        new Party("Batch of '96 Reunion", "jurong west, Singapore\n04/02/16 19:30"),
                        new Party("Happy Holiday", "singapore 637658\n05/02/16 16:20"),
                        new Party("Bachelor times", "ion orchard\n 24/02/16 04:20"),
                        new Party("Birthday Girls", "jurong point, singapore\n25/03/16 02:50")
                };

        PartyAdapter adapter = new PartyAdapter(this,
                R.layout.main_list, party_data);


        ListView listView1 = (ListView)findViewById(R.id.party_listview);
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(getApplicationContext(),Halloween_activity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        setContentView(R.layout.reunion);
                        break;
                    case 2:
                        setContentView(R.layout.holiday);
                        break;
                    case 3:
                        setContentView(R.layout.bachelor);
                        break;
                    case 4:
                        setContentView(R.layout.birthday);
                        break;
                    default:
                        setContentView(R.layout.activity_halloween_activity);
                        break;


                }
            }
        });
        ImageButton FAB = (ImageButton) findViewById(R.id.imageButton);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PartySetting.class);
                startActivity(intent);
                }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            System.exit(0);
            return true;
        }

        return super.onKeyDown(keyCode, event);
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
        if (id == R.id.friends) {
            Intent intent = new Intent(this, FriendsList.class); //Show all friends
            startActivity(intent);
            return true;
            } else if (id == R.id.log_out) {
            ParseUser.logOut();
            ParseLoginBuilder builder = new ParseLoginBuilder(MainActivity.this);
            startActivityForResult(builder.build(), 0);
            finish();
            System.exit(0);
            return true;
            }else if (id == R.id.invite_friend){ //Add functionality for user not present.
            Intent intent = new Intent(this, AddFriends.class);
            startActivity(intent);

            return true;
            }
        else if(id==android.R.id.home){
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
