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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GuestListActivity extends ActionBarActivity {

    private ArrayAdapter<String> mContactsAdapter;
    public LinearLayout linearList ;
    CheckBox checkBox;
    ArrayList<String> finalArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_list);

        String[] ContactsArray = {"Pratyum", "Shantanu", "Priyanshu", "Divyansh", "Varun", "Manav"};

        ArrayList<String> Logs = new ArrayList<>(Arrays.asList(ContactsArray));

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
        Intent intent = new Intent(this,Page2Activity.class);
        intent.putExtra("KEY",finalArray);
        startActivity(intent);


    }

}
