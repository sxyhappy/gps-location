package com.sxyhappy.capacitor;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationManager;

import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;

import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

@CapacitorPlugin(
    name = "GeoLocation",
    permissions = {
        @Permission(strings = { Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION }, alias = "location")
    }
)
public class GeoLocationPlugin extends Plugin {
    protected LocationManager locationManager;
    protected Location location;

    @Override
    public void load() {
        super.load();
        locationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);
    }

    @PluginMethod()
    public void getLocation(PluginCall call) {
        if (getPermissionState("location") != PermissionState.GRANTED) {
            requestAllPermissions(call, "completeCurrentPosition");
        } else {
            listenLocation(call);
        }
    }

    @PermissionCallback
    private void completeCurrentPosition(PluginCall call) {
        listenLocation(call);
    }

    private void listenLocation(PluginCall call) {
        Location lastKnownLocation = getLastKnownLocation();
        JSObject ret = new JSObject();
        ret.put("latitude", lastKnownLocation.getLatitude());
        ret.put("longitude", lastKnownLocation.getLongitude());
        call.resolve(ret);
    }

    @SuppressLint("MissingPermission")
    private Location getLastKnownLocation() {
        locationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location l = locationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = l;
            }
        }
        return bestLocation;
    }
}
