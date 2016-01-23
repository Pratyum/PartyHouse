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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private ArrayAdapter<RelativeLayout> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
//        Parse.enableLocalDatastore(this);
//        Parse.initialize(this);
//        ParseLoginBuilder builder = new ParseLoginBuilder(MainActivity.this);
//        startActivityForResult(builder.build(), 0);

        RelativeLayout r1 = createRelLay("Party1", "NUS\n9:31pm");
        RelativeLayout r2 = createRelLay("Party2", "NUS\n9:32pm");
        RelativeLayout r3 = createRelLay("Party3", "NUS\n9:33pm");
        RelativeLayout r4 = createRelLay("Party4", "NUS\n9:34pm");
        RelativeLayout r5 = createRelLay("Party5", "NUS\n9:35pm");
        RelativeLayout r6 = createRelLay("Party6", "NUS\n9:36pm");

        ArrayList<RelativeLayout> mLogs = new ArrayList<RelativeLayout>();
        mLogs.add(r1);
        mLogs.add(r2);
        mLogs.add(r3);
        mLogs.add(r4);
        mLogs.add(r5);
        mLogs.add(r6);


        mAdapter = new ArrayAdapter<RelativeLayout>(this, R.layout.main_list, mLogs);

        ListView listView = (ListView) findViewById(R.id.party_listview);

        listView.setAdapter(mAdapter);
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
            Intent intent = new Intent(this, Page2Activity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.log_out) {
            ParseUser.logOut();
            ParseLoginBuilder builder = new ParseLoginBuilder(MainActivity.this);
            startActivityForResult(builder.build(), 0);
            finish();
            System.exit(0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    RelativeLayout createRelLay(String one, String two)
    {
        // Definition
        RelativeLayout l1 = new RelativeLayout(this);
        TextView t1 = new TextView(this);
        TextView t2 = new TextView(this);


        //Adding content
        t1.setText(one);
        t2.setText(two);


        //Creating LayoutParams
        RelativeLayout.LayoutParams t1Details = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        RelativeLayout.LayoutParams t2Details = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        t1Details.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        t1Details.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        t2Details.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        t2Details.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        //Adding textviews to RelLay
        l1.addView(t1,t1Details);
        l1.addView(t2,t2Details);
        return l1;
    }

}
