package com.example.adrian.umik;

import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView paramText, nameText, descriptionText;
    private ImageView iv;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private TelephonyManager telephonyManager;
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("cukrzyk","Stanisław","potrzebuje pomocy 92 m od Ciebie", R.drawable.man_1));
        people.add(new Person("bulimik","Jędrzej","potrzebuje pomocy 125 m od Ciebie", R.drawable.man_2));
        people.add(new Person("emerytka","Maria","potrzebuje pomocy 211 m od Ciebie", R.drawable.woman_1));
        people.add(new Person("bolące kolana","Lucyna","potrzebuje pomocy 115 m od Ciebie", R.drawable.woman_2));

        Random generator = new Random();
        int i = generator.nextInt(4);
        displayPerson(people.get(i));
        button = findViewById(R.id.decline);

        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
    }

    private void displayPerson(Person person){
        iv = (ImageView)findViewById(R.id.avatar);
        iv.setImageResource(person.getPicture());
        paramText = findViewById(R.id.param);
        paramText.setText(person.getParam());
        nameText = findViewById(R.id.name);
        nameText.setText(person.getName());
        descriptionText = findViewById(R.id.description);
        descriptionText.setText(person.getDescr());
    }

    boolean shortPress = false;
    boolean longPress = false;

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            Toast.makeText(this, "Long Press", Toast.LENGTH_SHORT).show();
            //Long Press code goes here
            shortPress = false;
            longPress = true;
            // create class object
            gps = new GPSTracker(MainActivity.this);
            // check if GPS enabled
            if (gps.canGetLocation()) {
                double latitude = gps.getLatitude()+0.004;
                double longitude = gps.getLongitude()+0.004;
                Toast.makeText(getApplicationContext(),
                        "Potrzebująca osoba znajduje się: \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                if (latitude != 0 && longitude != 0) {
                    startNavigation(latitude, longitude);
                }
            } else {
                gps.showSettingsAlert();
            }
            return true;
        }
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            event.startTracking();
            if (longPress == true) {
                shortPress = false;
            } else {
                shortPress = true;
                longPress = false;
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {

            event.startTracking();
            if (shortPress) {
                Toast.makeText(this, "Short Press", Toast.LENGTH_SHORT).show();
                //Short Press code goes here
            }
            shortPress = true;
            longPress = false;
            return true;
        }

        return super.onKeyUp(keyCode, event);
    }

    private void startNavigation(Double latitude, Double longitude) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + latitude +","+ longitude+"&mode=w");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    Gson gson = new Gson();
    //String jsonReq = gson.toJson();

}