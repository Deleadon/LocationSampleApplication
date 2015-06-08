package dhbw.mysampleapplication;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    public double latitude;
    public double longitude;
    public Location mLocation;
    public Button mGpsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager locManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 500.0f, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                TextView locationView = (TextView) findViewById(R.id.location_text_view);
                locationView.setText("Latitude: " + latitude + " Longitude: " + longitude);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        });
        mLocation = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        mGpsButton = (Button) findViewById(R.id.gps_button);
        mGpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLocation != null) {
                    TextView locationView = (TextView) findViewById(R.id.location_text_view);
                    locationView.setText("Latitude: " + mLocation.getLatitude() + "\nLongitude: " + mLocation.getLongitude());
                }
            }
        });
    }

}
