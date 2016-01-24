package com.google.devplat.lmoroney.maps3_1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Page2Activity extends ActionBarActivity implements OnMapReadyCallback {
    private ArrayAdapter<String> mLogsAdapter;
    GoogleMap m_map;
    boolean mapReady=false;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private final String LOG_TAG = Page2Activity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
//        String[] LogsArray = {"Pratyum", "Shantanu", "Priyanshu", "Divyansh", "Varun", "Manav"};
        Intent intent = getIntent();
        ArrayList<String> Logs = (ArrayList<String>)intent.getSerializableExtra("KEY");
//        List<String> Logs = new ArrayList<>(Arrays.asList(LogsArray));
        mLogsAdapter = new ArrayAdapter<>(this, R.layout.list_logs, R.id.list_logs_textview, Logs);

        ListView listView = (ListView) findViewById(R.id.listview_logs);
        listView.setAdapter(mLogsAdapter);

//        Button submit = (Button) findViewById(R.id.bt_submit);
//        final EditText address_et = (EditText)findViewById(R.id.et_address);
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                InputMethodManager imm = (InputMethodManager)getSystemService(
//                        Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(address_et.getWindowToken(), 0);
//                String address = address_et.getText().toString();
//                FetchLocationTask locationTask = new FetchLocationTask();
//                locationTask.execute(address);
//
//            }
//        });

        //VENUE BUTTON FUNCTIONALITY
        String partyName = getIntent().getStringExtra("Party");
        String venueDetails="",dates="";
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Party");
        Log.d("party", partyName);

        query.whereEqualTo("Name", partyName);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, com.parse.ParseException e) {
                if (e == null) {
//                    Log.d("party", String.valueOf(objects.size()));
                    ParseGeoPoint geoPoint = (ParseGeoPoint) objects.get(0).get("Venue");
                    LatLng coord = new LatLng(geoPoint.getLatitude(),geoPoint.getLongitude());
                    CameraPosition target = CameraPosition.builder().target(coord).zoom(14).build();
                    m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
                    MarkerOptions result_marker = new MarkerOptions()
                            .position(coord)
                            .title(objects.get(0).get("VenueDetail").toString())
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker));
                    m_map.addMarker(result_marker);
                    Button venueButton = (Button) findViewById(R.id.venue_button);
                    venueButton.setText(objects.get(0).get("VenueDetail").toString());
                    Log.d("party",objects.get(0).get("VenueDetail").toString());
                    Log.d("party", objects.get(0).get("Date").toString());
                    venueButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            venueClick(v);
                        }
                    });
                    venueButton.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            venueLongClick(v);
                            return false;
                        }
                    });

                    Button timeButton = (Button) findViewById(R.id.time_button);
                    timeButton.setText(objects.get(0).get("Date").toString());
                    timeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            timeClick(v);
                        }
                    });
                    timeButton.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            timeLongClick(v);
                            return false;
                        }
                    });
//                    FetchLocationTask locationTask = new FetchLocationTask();
//                    locationTask.execute(objects.get(0).get("VenueDetails").toString());
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

                    FetchLocationTask locationTask = new FetchLocationTask();
                    locationTask.execute(venueDetails);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void timeLongClick(View v) {
    }

    private void timeClick(View v) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_page, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.invite_friend) {
            return true;
            }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Page2 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.google.devplat.lmoroney.maps3_1/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Page2 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.google.devplat.lmoroney.maps3_1/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    void venueClick(View view) {
        //Relocate on Map
    }

    void venueLongClick(View view)
    {
        //Choose location from another activity
    }

    private double[] getLocationDataFromJson(String JsonStr)
            throws JSONException {
        Log.d(LOG_TAG,JsonStr);
        // These are the names of the JSON objects that need to be extracted.
        final String OWM_RESULTS = "results";
        final String OWM_GEOMETRY = "geometry";
        final String OWM_LOCATION = "location";

        JSONObject locationJson = new JSONObject(JsonStr);
        JSONArray locationArray = locationJson.getJSONArray(OWM_RESULTS);

        double[] result = new double[2];
        JSONObject Object= locationArray.getJSONObject(0);
        JSONObject geometry = Object.getJSONObject(OWM_GEOMETRY);
        JSONObject locationObject = geometry.getJSONObject(OWM_LOCATION);
        result[0]=locationObject.getDouble("lat");
        result[1]=locationObject.getDouble("lng");

        return result;

    }

    public class FetchLocationTask extends AsyncTask<String,Void,double[]>{

        @Override
        protected double[] doInBackground(String... strings) {
            strings[0].replaceAll(" ", "+");
            strings[0].replaceAll(",", ",+");
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String JsonStr=null;

            try {
                final String BASE_ADDR = "https://maps.googleapis.com/maps/api/geocode/json?";
                final String ADDRESS_PARAM = "address";
                final String KEY_PARAM = "key";
                final String KEY_VALUE = "AIzaSyCkmr5QmzIKiLRWalD4M0ngPmPxeOQfFic";
                Uri builtUri = Uri.parse(BASE_ADDR).buildUpon()
                        .appendQueryParameter(ADDRESS_PARAM, strings[0])
                        .appendQueryParameter(KEY_PARAM, KEY_VALUE)
                        .build();

                URL url = new URL(builtUri.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                JsonStr = buffer.toString();
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }
            try {
                return getLocationDataFromJson(JsonStr);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }

            // This will only happen if there was an error getting or parsing the forecast.
            return null;
        }
        @Override
        protected void onPostExecute(double[] result) {
            if (result != null) {
                LatLng ntu = new LatLng(result[0], result[1]);
                CameraPosition target = CameraPosition.builder().target(ntu).zoom(14).build();
                m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
                MarkerOptions result_marker = new MarkerOptions()
                        .position(ntu)
                        .title(((EditText) findViewById(R.id.address)).getText().toString())
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker));
                m_map.addMarker(result_marker);

            }
        }
    }
    @Override
    public void onMapReady(GoogleMap map){
        mapReady=true;
        m_map = map;
        LatLng ntu = new LatLng(1.3447, 103.6813);
        CameraPosition target = CameraPosition.builder().target(ntu).zoom(14).build();
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
    }
}
