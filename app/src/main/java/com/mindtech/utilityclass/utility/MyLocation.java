package com.mindtech.utilityclass.utility;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Ramchandra Singh on 10-03-2017.
 */
public class MyLocation {
    Timer timer1;
    LocationManager lm;
    LocationResult locationResult;
    boolean gps_enabled = false;
    boolean network_enabled = false;
    Context con;


    String TAG = "MyLocation";
    int REQUEST_CHECK_SETTINGS = 1;
//    private void displayLocationSettingsRequest(final Context context) {
//        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
//                .addApi(LocationServices.API).build();
//        googleApiClient.connect();
//
//        LocationRequest locationRequest = LocationRequest.create();
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        locationRequest.setInterval(10000);
//        locationRequest.setFastestInterval(10000 / 2);
//
//        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
//        builder.setAlwaysShow(true);
//
//        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
//        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
//            @Override
//            public void onResult(LocationSettingsResult result) {
//                final Status status = result.getStatus();
//                switch (status.getStatusCode()) {
//                    case LocationSettingsStatusCodes.SUCCESS:
//                        Log.i(TAG, "All location settings are satisfied.");
//                        break;
//                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
//                        Log.i(TAG, "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");
//
//                        try {
//                            // Show the dialog by calling startResolutionForResult(), and check the result
//                            // in onActivityResult().
//                            status.startResolutionForResult((Activity)context, REQUEST_CHECK_SETTINGS);
//                        } catch (IntentSender.SendIntentException e) {
//                            Log.i(TAG, "PendingIntent unable to execute request.");
//                        }
//                        break;
//                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
//                        Log.i(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
//                        break;
//                }
//            }
//        });
//    }
    LocationListener locationListenerNetwork = new LocationListener() {
        public void onLocationChanged(Location location) {
            timer1.cancel();
            locationResult.gotLocation(location);
            if (ActivityCompat.checkSelfPermission(con, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(con, Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            lm.removeUpdates(this);
            lm.removeUpdates(locationListenerGps);
        }

        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };
    LocationListener locationListenerGps = new LocationListener() {
        public void onLocationChanged(Location location) {
            timer1.cancel();
            locationResult.gotLocation(location);
            if (ActivityCompat.checkSelfPermission(con, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(con, Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            lm.removeUpdates(this);
            lm.removeUpdates(locationListenerNetwork);
        }

        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };

    public boolean getLocation(Context context, LocationResult result) {
        //I use LocationResult callback class to pass location value from MyLocation to user code.

        con = context;
        locationResult = result;
        if (lm == null)
            lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        //exceptions will be thrown if provider is not permitted.
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
           // ad.showError(e.getMessage()+"\n\n"+result);
        }
        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        //Toast.makeText(context, gps_enabled+" "+network_enabled,     Toast.LENGTH_LONG).show();

        //don't start listeners if no provider is enabled


        if (!gps_enabled && !network_enabled)
            return false;

        if (ActivityCompat.checkSelfPermission(con, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(con, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        if (gps_enabled)
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListenerGps);
        if (network_enabled)
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListenerNetwork);
        timer1 = new Timer();


        timer1.schedule(new GetLastLocation(), 10000);
        //    Toast.makeText(context, " Yaha Tak AAya", Toast.LENGTH_LONG).show();
        return true;
    }

    public static abstract class LocationResult {
        public abstract void gotLocation(Location location);
    }

    class GetLastLocation extends TimerTask {
        @Override

        public void run() {

            //Context context = getClass().getgetApplicationContext();
            Location net_loc = null, gps_loc = null;
            if (ActivityCompat.checkSelfPermission(con, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(con, Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            if (gps_enabled)
                gps_loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (network_enabled)
                net_loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            //if there are both values use the latest one
            if (gps_loc != null && net_loc != null) {
                if (gps_loc.getTime() > net_loc.getTime())
                    locationResult.gotLocation(gps_loc);
                else
                    locationResult.gotLocation(net_loc);
                return;
            }

            if (gps_loc != null) {
                locationResult.gotLocation(gps_loc);
                return;
            }
            if (net_loc != null) {
                locationResult.gotLocation(net_loc);
                return;
            }
            locationResult.gotLocation(null);
        }
    }
}
