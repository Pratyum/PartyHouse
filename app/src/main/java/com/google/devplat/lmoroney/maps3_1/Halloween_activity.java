package com.google.devplat.lmoroney.maps3_1;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Halloween_activity extends ActionBarActivity implements OnMapReadyCallback {
    private ArrayAdapter<String> mLogsAdapter;
    GoogleMap m_map;
    boolean mapReady=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halloween_activity);
        Button et = (Button) findViewById(R.id.venue_button);
        et.setText("Universal Studios");
        Button et1 = (Button) findViewById(R.id.time_button);
        et1.setText("25/03/16 15:15");

        ArrayList<String> Logs = new ArrayList<>();
        Logs.add("Manav");
        Logs.add("Priyanshu");
        Logs.add("Kamath");

        int sub=3;
        Logs.add(Logs.get(0)+" is Nudged! ");
        Logs.add("Location is Set! ");
        Logs.add("Date & Time Set! ");
        if(Logs.size()>3) {

            sub=4;
            Logs.add(Logs.get(1) + " is Nudged! ");
        }
        for(int i=0;i<Logs.size()-sub;i++){
            String word=Logs.get(i);
            Logs.remove(i);
            Logs.add(i,word+" is Added!");
        }

        //List<String> Logs = new ArrayList<>(Arrays.asList(LogsArray));
        mLogsAdapter = new ArrayAdapter<>(this, R.layout.list_logs, R.id.list_logs_textview, Logs);

        ListView listView = (ListView) findViewById(R.id.listview_logs);
        listView.setAdapter(mLogsAdapter);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mapReady=true;
        m_map = map;
        LatLng universal = new LatLng(1.2540, 103.8240);
        LatLng coords01_hall12 = new LatLng(1.3507285,103.6810061);
        LatLng coords02_ion = new LatLng(1.3045298,103.8327203);
        LatLng coords03_ne_jurong = new LatLng(1.3644129,103.7282631);
        LatLng coords04_bedok = new LatLng(1.323668,103.9485604);
        LatLng coords05_chua = new LatLng(1.405547,103.7612728);
        LatLng coords06_yishun = new LatLng(1.4452451,103.8642289);
        LatLng coords07_clementi = new LatLng(1.3161811,103.7649377);
        LatLng coords08_nus = new LatLng(1.295053,103.773846);
        LatLng coords09_lake = new LatLng(1.346337680291502,103.6861356302915);
        LatLng ntu = new LatLng(1.3447, 103.6813);
        CameraPosition target = CameraPosition.builder().target(ntu).zoom(14).build();
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
        MarkerOptions result_marker01 = new MarkerOptions()
                .position(coords01_hall12)
                .title("Priyanshu")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker));
        MarkerOptions result_marker02 = new MarkerOptions()
                .position(coords02_ion)
                .title("Manav")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker));
        MarkerOptions result_marker03 = new MarkerOptions()
                .position(coords03_ne_jurong)
                .title("Shantanu")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker));
        MarkerOptions result_marker04 = new MarkerOptions()
                .position(coords04_bedok)
                .title("Jacob")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker));
        MarkerOptions result_marker05 = new MarkerOptions()
                .position(coords05_chua)
                .title("Pratyum")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker));
        MarkerOptions result_marker06 = new MarkerOptions()
                .position(coords06_yishun)
                .title("Divyansh")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker));
        MarkerOptions result_marker07 = new MarkerOptions()
                .position(coords07_clementi)
                .title("Nikhil")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker));
        MarkerOptions result_marker08 = new MarkerOptions()
                .position(coords08_nus)
                .title("XYZ")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker));
        MarkerOptions result_marker09 = new MarkerOptions()
                .position(coords09_lake)
                .title("ABC")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker));
        MarkerOptions result_marker = new MarkerOptions()
                .position(coords09_lake)
                .title("Universal Studios")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.flag_finish));

        m_map.addMarker(result_marker01);
        m_map.addMarker(result_marker02);
        m_map.addMarker(result_marker03);
        m_map.addMarker(result_marker04);
        m_map.addMarker(result_marker05);
        m_map.addMarker(result_marker06);
        m_map.addMarker(result_marker07);
        m_map.addMarker(result_marker08);
        m_map.addMarker(result_marker09);
        m_map.addMarker(result_marker);

    }
}
