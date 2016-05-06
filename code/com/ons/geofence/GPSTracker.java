package com.ons.geofence;

import android.app.AlertDialog.Builder;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class GPSTracker
  extends Service
  implements LocationListener
{
  private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1L;
  private static final long MIN_TIME_BW_UPDATES = 1000L;
  boolean canGetLocation = false;
  boolean isGPSEnabled = false;
  boolean isNetworkEnabled = false;
  double latitude;
  Location location;
  protected LocationManager locationManager;
  double longitude;
  private final Context mContext;
  
  public GPSTracker(Context paramContext)
  {
    this.mContext = paramContext;
    Log.v("amriteshlatlong", "GPSTracker(Context context)");
    getLocation();
  }
  
  public boolean canGetLocation()
  {
    return this.canGetLocation;
  }
  
  public double getLatitude()
  {
    if (this.location != null) {
      this.latitude = this.location.getLatitude();
    }
    return this.latitude;
  }
  
  public Location getLocation()
  {
    for (;;)
    {
      try
      {
        Log.v("amriteshlatlong", "getLocation()");
        this.locationManager = ((LocationManager)this.mContext.getSystemService("location"));
        this.isGPSEnabled = this.locationManager.isProviderEnabled("gps");
        this.isNetworkEnabled = this.locationManager.isProviderEnabled("network");
        if (this.isGPSEnabled) {
          continue;
        }
        boolean bool = this.isNetworkEnabled;
        if (bool) {
          continue;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        continue;
      }
      return this.location;
      this.canGetLocation = true;
      if (this.isNetworkEnabled)
      {
        this.locationManager.requestLocationUpdates("network", 1000L, 1.0F, this);
        Log.d("Network", "Network");
        if (this.locationManager != null)
        {
          this.location = this.locationManager.getLastKnownLocation("network");
          if (this.location != null)
          {
            this.latitude = this.location.getLatitude();
            this.longitude = this.location.getLongitude();
          }
        }
      }
      if ((this.isGPSEnabled) && (this.location == null))
      {
        this.locationManager.requestLocationUpdates("gps", 1000L, 1.0F, this);
        Log.d("GPS Enabled", "GPS Enabled");
        if (this.locationManager != null)
        {
          this.location = this.locationManager.getLastKnownLocation("gps");
          if (this.location != null)
          {
            this.latitude = this.location.getLatitude();
            this.longitude = this.location.getLongitude();
          }
        }
      }
    }
  }
  
  public double getLongitude()
  {
    if (this.location != null) {
      this.longitude = this.location.getLongitude();
    }
    return this.longitude;
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onLocationChanged(Location paramLocation) {}
  
  public void onProviderDisabled(String paramString) {}
  
  public void onProviderEnabled(String paramString) {}
  
  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {}
  
  public void showSettingsAlert()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.mContext);
    localBuilder.setTitle("GPS is settings");
    localBuilder.setMessage("GPS is not enabled. Do you want to go to settings menu?");
    localBuilder.setPositiveButton("Settings", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
        GPSTracker.this.mContext.startActivity(paramAnonymousDialogInterface);
      }
    });
    localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    localBuilder.show();
  }
  
  public void stopUsingGPS()
  {
    if (this.locationManager != null) {
      this.locationManager.removeUpdates(this);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\geofence\GPSTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */