package com.example.example75f1799f07eb;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GetAddressOnMap
  extends FragmentActivity
  implements LocationListener, View.OnClickListener
{
  private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1L;
  private static final long MIN_TIME_BW_UPDATES = 1L;
  String address;
  List<Address> addresses;
  ImageView btnCancel;
  boolean canGetLocation = false;
  Geocoder geocoder;
  GoogleMap googleMap;
  public boolean isGPSEnabled = false;
  boolean isNetworkEnabled = false;
  double latitude;
  LatLng latlng;
  Location location;
  protected LocationManager locationManager;
  double longitude;
  Marker marker;
  TextView tvSave;
  
  private String getAddress()
  {
    this.geocoder = new Geocoder(this, Locale.getDefault());
    try
    {
      this.addresses = this.geocoder.getFromLocation(this.latitude, this.longitude, 1);
      this.address = ((Address)this.addresses.get(0)).getAddressLine(0);
      str5 = ((Address)this.addresses.get(0)).getLocality();
      str4 = ((Address)this.addresses.get(0)).getSubAdminArea();
      str2 = ((Address)this.addresses.get(0)).getCountryName();
      str3 = ((Address)this.addresses.get(0)).getPostalCode();
      str1 = ((Address)this.addresses.get(0)).getFeatureName();
      if (str5 == null) {}
    }
    catch (IOException localIOException)
    {
      try
      {
        for (;;)
        {
          String str5;
          String str4;
          String str2;
          String str3;
          String str1;
          this.address = new String(this.address + ", " + str5);
          if (str4 != null) {}
          try
          {
            this.address = new String(this.address + ", " + str4);
            if (str3 != null) {}
            try
            {
              this.address = new String(this.address + ", " + str3);
              if (str2 != null) {}
              try
              {
                this.address = new String(this.address + ", " + str2);
                if (str1 != null) {}
                return this.address;
                localIOException = localIOException;
                localIOException.printStackTrace();
              }
              catch (Exception localException1)
              {
                for (;;) {}
              }
            }
            catch (Exception localException2)
            {
              for (;;) {}
            }
          }
          catch (Exception localException3)
          {
            for (;;) {}
          }
        }
      }
      catch (Exception localException4)
      {
        for (;;) {}
      }
    }
  }
  
  public Location getLocation()
  {
    for (;;)
    {
      try
      {
        this.locationManager = ((LocationManager)getSystemService("location"));
        this.isGPSEnabled = this.locationManager.isProviderEnabled("gps");
        Log.v("isGPSEnabled", "=" + this.isGPSEnabled);
        this.isNetworkEnabled = this.locationManager.isProviderEnabled("network");
        Log.v("isNetworkEnabled", "=" + this.isNetworkEnabled);
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
        this.location = null;
        this.locationManager.requestLocationUpdates("network", 1L, 1.0F, this);
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
      if (this.isGPSEnabled)
      {
        this.location = null;
        if (this.location == null)
        {
          this.locationManager.requestLocationUpdates("gps", 1L, 1.0F, this);
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
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131558537: 
      finish();
      return;
    }
    getAddress();
    MyPhoneGapActivity.sendLocationService(this.latitude, this.longitude, this.address);
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903078);
    this.btnCancel = ((ImageView)findViewById(2131558537));
    this.tvSave = ((TextView)findViewById(2131558538));
    this.tvSave.setOnClickListener(this);
    this.btnCancel.setOnClickListener(this);
  }
  
  public void onLocationChanged(Location paramLocation) {}
  
  public void onProviderDisabled(String paramString) {}
  
  public void onProviderEnabled(String paramString) {}
  
  protected void onResume()
  {
    super.onResume();
    try
    {
      this.googleMap = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(2131558539)).getMap();
      if (this.googleMap != null)
      {
        this.googleMap.setMapType(1);
        this.location = getLocation();
        this.latlng = new LatLng(this.latitude, this.longitude);
        this.marker = this.googleMap.addMarker(new MarkerOptions().position(this.latlng).title("Location").snippet("Hello"));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(this.latlng, 12.0F));
      }
      this.googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
      {
        public void onMapClick(LatLng paramAnonymousLatLng)
        {
          MarkerOptions localMarkerOptions = new MarkerOptions();
          localMarkerOptions.position(paramAnonymousLatLng);
          localMarkerOptions.title(paramAnonymousLatLng.latitude + " : " + paramAnonymousLatLng.longitude);
          GetAddressOnMap.this.googleMap.clear();
          GetAddressOnMap.this.googleMap.animateCamera(CameraUpdateFactory.newLatLng(paramAnonymousLatLng));
          GetAddressOnMap.this.googleMap.addMarker(localMarkerOptions);
          GetAddressOnMap.this.latitude = paramAnonymousLatLng.latitude;
          GetAddressOnMap.this.longitude = paramAnonymousLatLng.longitude;
        }
      });
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e("GetAddressOnMap", "Map ERROR: " + localException.toString());
      }
    }
  }
  
  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {}
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\GetAddressOnMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */