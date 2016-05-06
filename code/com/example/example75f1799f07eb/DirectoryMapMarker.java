package com.example.example75f1799f07eb;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.PrintStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class DirectoryMapMarker
  extends Activity
{
  List<String> addressList = new ArrayList();
  TextView appName;
  List<String> clickedMarkerIndexList = new ArrayList();
  String completeaddressw = "";
  String countIndex = "";
  String currentLat = "";
  String currentLong = "";
  List<String> lattList = new ArrayList();
  List<String> lngList = new ArrayList();
  private GoogleMap mMap;
  private HashMap<Marker, ShowMapMarker> mMarkersHashMap;
  private ArrayList<ShowMapMarker> mShowMapMarkersArray = new ArrayList();
  int totalMarkerSize = 0;
  
  private String getCompleteAddressString(double paramDouble1, double paramDouble2)
  {
    Object localObject1 = "";
    Object localObject2 = new Geocoder(this, Locale.getDefault());
    try
    {
      localObject2 = ((Geocoder)localObject2).getFromLocation(paramDouble1, paramDouble2, 1);
      if (localObject2 != null)
      {
        localObject1 = (Address)((List)localObject2).get(0);
        localObject2 = new StringBuilder("");
        int i = 0;
        while (i < ((Address)localObject1).getMaxAddressLineIndex())
        {
          ((StringBuilder)localObject2).append(((Address)localObject1).getAddressLine(i)).append(",");
          i += 1;
        }
        localObject1 = ((StringBuilder)localObject2).toString();
      }
      return (String)localObject1;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  private void plotMarkers(ArrayList<ShowMapMarker> paramArrayList)
  {
    int i = 0;
    if (paramArrayList.size() > 0) {
      paramArrayList = paramArrayList.iterator();
    }
    for (;;)
    {
      ShowMapMarker localShowMapMarker;
      if (paramArrayList.hasNext()) {
        localShowMapMarker = (ShowMapMarker)paramArrayList.next();
      }
      try
      {
        if (i < this.totalMarkerSize)
        {
          str = URLDecoder.decode((String)this.addressList.get(i), "UTF-8");
          localObject = new MarkerOptions().position(new LatLng(localShowMapMarker.getmLatitude().doubleValue(), localShowMapMarker.getmLongitude().doubleValue()));
          ((MarkerOptions)localObject).icon(BitmapDescriptorFactory.fromResource(2130837577));
          localObject = this.mMap.addMarker((MarkerOptions)localObject);
          this.mMarkersHashMap.put(localObject, localShowMapMarker);
          ((Marker)localObject).setTitle(str);
          this.mMap.setInfoWindowAdapter(new MarkerInfoWindowAdapter());
          i += 1;
          continue;
        }
        String str = "" + this.completeaddressw;
        Object localObject = new MarkerOptions().position(new LatLng(localShowMapMarker.getmLatitude().doubleValue(), localShowMapMarker.getmLongitude().doubleValue()));
        ((MarkerOptions)localObject).icon(BitmapDescriptorFactory.fromResource(2130837634));
        localObject = this.mMap.addMarker((MarkerOptions)localObject);
        this.mMarkersHashMap.put(localObject, localShowMapMarker);
        ((Marker)localObject).setTitle(str);
        this.mMap.setInfoWindowAdapter(new MarkerInfoWindowAdapter());
        i += 1;
      }
      catch (Exception localException) {}
      return;
    }
  }
  
  private void setUpMap()
  {
    if (this.mMap == null)
    {
      this.mMap = ((MapFragment)getFragmentManager().findFragmentById(2131558448)).getMap();
      CameraUpdate localCameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(this.currentLat), Double.parseDouble(this.currentLong)), 8.0F);
      this.mMap.animateCamera(localCameraUpdate);
      if (this.mMap != null)
      {
        this.mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {
          public boolean onMarkerClick(Marker paramAnonymousMarker)
          {
            paramAnonymousMarker.showInfoWindow();
            return true;
          }
        });
        this.mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()
        {
          public void onInfoWindowClick(Marker paramAnonymousMarker)
          {
            int i = 0;
            for (;;)
            {
              if (i <= DirectoryMapMarker.this.totalMarkerSize) {}
              try
              {
                String str = URLDecoder.decode((String)DirectoryMapMarker.this.addressList.get(i), "UTF-8");
                if ((i != DirectoryMapMarker.this.totalMarkerSize) && (paramAnonymousMarker.getTitle().toString().equalsIgnoreCase(str)))
                {
                  MyPhoneGapActivity.ShowDirectoryDetails((String)DirectoryMapMarker.this.clickedMarkerIndexList.get(i), "", DirectoryMapMarker.this.countIndex, str);
                  DirectoryMapMarker.this.finish();
                }
                i += 1;
                continue;
                return;
              }
              catch (Exception localException)
              {
                for (;;) {}
              }
            }
          }
        });
      }
    }
    else
    {
      return;
    }
    Toast.makeText(getApplicationContext(), "Unable to create Maps", 0).show();
  }
  
  public void close(View paramView)
  {
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(7);
    setContentView(2130903100);
    getWindow().setFeatureInt(7, 2130903063);
    paramBundle = getResources().getString(2131230720);
    this.appName = ((TextView)findViewById(2131558500));
    this.appName.setText(paramBundle);
    for (;;)
    {
      try
      {
        arrayOfString1 = getIntent().getStringExtra("urlData").substring(15).split("%%%");
        paramBundle = new String[0];
        System.out.println("krishna22>>>>>>>" + arrayOfString1[0]);
        if (!arrayOfString1[0].equalsIgnoreCase("")) {
          continue;
        }
        String[] arrayOfString2 = arrayOfString1[1].split(",");
        String[] arrayOfString3 = arrayOfString1[2].split(",");
        String[] arrayOfString4 = arrayOfString1[3].split(",");
        this.countIndex = arrayOfString1[5];
        this.currentLat = arrayOfString1[6].toString();
        this.currentLong = arrayOfString1[7].toString();
        this.completeaddressw = getCompleteAddressString(Double.parseDouble(this.currentLat), Double.parseDouble(this.currentLong));
        if (paramBundle.length > 0)
        {
          System.out.println("krishna>>>>>>>" + paramBundle.length);
          this.lattList = Arrays.asList(paramBundle);
          this.lngList = Arrays.asList(arrayOfString2);
          this.addressList = Arrays.asList(arrayOfString4);
          this.clickedMarkerIndexList = Arrays.asList(arrayOfString3);
        }
      }
      catch (Exception paramBundle)
      {
        String[] arrayOfString1;
        paramBundle.printStackTrace();
        continue;
        this.mMarkersHashMap = new HashMap();
        this.mMarkersHashMap.clear();
        this.mShowMapMarkersArray.clear();
        this.totalMarkerSize = this.lattList.size();
        i = 0;
      }
      if (this.lattList.size() != 0) {
        continue;
      }
      Toast.makeText(getApplicationContext(), "Data not available", 1).show();
      return;
      paramBundle = arrayOfString1[0].split(",");
    }
    for (;;)
    {
      int i;
      if (i < this.lattList.size()) {}
      try
      {
        paramBundle = URLDecoder.decode((String)this.addressList.get(i), "UTF-8");
        this.mShowMapMarkersArray.add(new ShowMapMarker(paramBundle, "currentlocation_icon", Double.valueOf(Double.parseDouble((String)this.lattList.get(i))), Double.valueOf(Double.parseDouble((String)this.lngList.get(i))), i));
        i += 1;
        continue;
        this.mShowMapMarkersArray.add(new ShowMapMarker("" + this.completeaddressw, "currentlocation_icon", Double.valueOf(Double.parseDouble(this.currentLat)), Double.valueOf(Double.parseDouble(this.currentLong)), this.totalMarkerSize));
        setUpMap();
        plotMarkers(this.mShowMapMarkersArray);
        return;
      }
      catch (Exception paramBundle)
      {
        for (;;) {}
      }
    }
  }
  
  public class MarkerInfoWindowAdapter
    implements GoogleMap.InfoWindowAdapter
  {
    public MarkerInfoWindowAdapter() {}
    
    public View getInfoContents(Marker paramMarker)
    {
      View localView = DirectoryMapMarker.this.getLayoutInflater().inflate(2130903084, null);
      paramMarker = (ShowMapMarker)DirectoryMapMarker.this.mMarkersHashMap.get(paramMarker);
      ((TextView)localView.findViewById(2131558440)).setText(paramMarker.getmLabel());
      return localView;
    }
    
    public View getInfoWindow(Marker paramMarker)
    {
      return null;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\DirectoryMapMarker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */