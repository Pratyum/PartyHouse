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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Parse.enableLocalDatastore(this);
//        Parse.initialize(this);
//        ParseLoginBuilder builder = new ParseLoginBuilder(MainActivity.this);
//        startActivityForResult(builder.build(), 0);
//
//        Party party_data[] = new Party[]
//                {
//                        new Party("Party1", "NUS\n9:31pm"),
//                        new Party("Party2", "NUS\n9:32pm"),
//                        new Party("Party1", "NUS\n9:31pm"),
//                        new Party("Party1", "NUS\n9:31pm"),
//                        new Party("Party1", "NUS\n9:31pm")
//                };
//
//        PartyAdapter adapter = new PartyAdapter(this,
//                R.layout.main_list, party_data);
//
//
//        ListView listView1 = (ListView)findViewById(R.id.party_listview);
//
//        listView1.setAdapter(adapter);
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
        if (id == R.id.add_party) {
            Intent intent = new Intent(this, GuestListActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.log_out) {
            ParseUser.logOut();
            ParseLoginBuilder builder = new ParseLoginBuilder(MainActivity.this);
            startActivityForResult(builder.build(), 0);
            finish();
            System.exit(0);
            return true;
        }else if (id == R.id.add_friend){
            Intent intent = new Intent(this, AddFriends.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
