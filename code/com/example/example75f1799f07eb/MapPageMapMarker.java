package com.example.example75f1799f07eb;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class MapPageMapMarker
  extends Activity
{
  List<String> addrList = new ArrayList();
  TextView appName;
  List<String> clickedMarkerIndexList = new ArrayList();
  List<String> lattList = new ArrayList();
  List<String> lngList = new ArrayList();
  private GoogleMap mMap;
  private HashMap<Marker, ShowMapMarker> mMarkersHashMap;
  private ArrayList<ShowMapMarker> mShowMapMarkersArray = new ArrayList();
  
  private String getCompleteAddressString(double paramDouble1, double paramDouble2)
  {
    String str2 = "";
    Object localObject = new Geocoder(this, Locale.getDefault());
    String str1 = str2;
    try
    {
      localObject = ((Geocoder)localObject).getFromLocation(paramDouble1, paramDouble2, 1);
      if (localObject != null)
      {
        str1 = str2;
        Address localAddress = (Address)((List)localObject).get(0);
        str1 = str2;
        localObject = new StringBuilder("");
        int i = 0;
        for (;;)
        {
          str1 = str2;
          if (i >= localAddress.getMaxAddressLineIndex()) {
            break;
          }
          str1 = str2;
          ((StringBuilder)localObject).append(localAddress.getAddressLine(i)).append(",");
          i += 1;
        }
        str1 = str2;
        str2 = ((StringBuilder)localObject).toString();
        str1 = str2;
        Log.e("My Current loction address", "" + ((StringBuilder)localObject).toString());
        return str2;
      }
      str1 = str2;
      Log.e("My Current loction address", "No Address returned!");
      return "";
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      Log.e("My Current loction address", "Canont get Address!");
    }
    return str1;
  }
  
  private void plotMarkers(ArrayList<ShowMapMarker> paramArrayList)
  {
    if (paramArrayList.size() > 0)
    {
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        ShowMapMarker localShowMapMarker = (ShowMapMarker)paramArrayList.next();
        Object localObject = new MarkerOptions().position(new LatLng(localShowMapMarker.getmLatitude().doubleValue(), localShowMapMarker.getmLongitude().doubleValue()));
        ((MarkerOptions)localObject).icon(BitmapDescriptorFactory.fromResource(2130837577));
        localObject = this.mMap.addMarker((MarkerOptions)localObject);
        this.mMarkersHashMap.put(localObject, localShowMapMarker);
        this.mMap.setInfoWindowAdapter(new MarkerInfoWindowAdapter());
      }
    }
  }
  
  private void setUpMap()
  {
    if (this.mMap == null)
    {
      this.mMap = ((MapFragment)getFragmentManager().findFragmentById(2131558448)).getMap();
      CameraUpdate localCameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble((String)this.lattList.get(0)), Double.parseDouble((String)this.lngList.get(0))), 15.0F);
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
            paramAnonymousMarker = new Intent("android.intent.action.VIEW", Uri.parse("google.navigation:q=" + paramAnonymousMarker.getPosition().latitude + "," + paramAnonymousMarker.getPosition().longitude));
            paramAnonymousMarker.addFlags(268435456);
            MapPageMapMarker.this.startActivity(paramAnonymousMarker);
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
        paramBundle = getIntent().getStringExtra("urlData").substring(11);
        System.out.println("krishna lat long>>>>>>>" + paramBundle);
        arrayOfString1 = paramBundle.split("&&");
        paramBundle = new String[0];
        System.out.println("krishna22>>>>>>>" + arrayOfString1[0]);
        if (!arrayOfString1[0].equalsIgnoreCase("")) {
          continue;
        }
        String[] arrayOfString2 = arrayOfString1[1].split(",");
        arrayOfString1 = arrayOfString1[2].split(",");
        if (paramBundle.length > 0)
        {
          System.out.println("krishna>>>>>>>" + paramBundle.length);
          this.lattList = Arrays.asList(paramBundle);
          this.lngList = Arrays.asList(arrayOfString2);
          this.addrList = Arrays.asList(arrayOfString1);
        }
      }
      catch (Exception paramBundle)
      {
        String[] arrayOfString1;
        int i;
        paramBundle.printStackTrace();
        continue;
        this.mShowMapMarkersArray.add(new ShowMapMarker(getCompleteAddressString(Double.parseDouble((String)this.lattList.get(i)), Double.parseDouble((String)this.lngList.get(i))), "currentlocation_icon", Double.valueOf(Double.parseDouble((String)this.lattList.get(i))), Double.valueOf(Double.parseDouble((String)this.lngList.get(i))), i));
        continue;
        setUpMap();
        plotMarkers(this.mShowMapMarkersArray);
      }
      this.mMarkersHashMap = new HashMap();
      this.mMarkersHashMap.clear();
      this.mShowMapMarkersArray.clear();
      i = 0;
      if (i >= this.lattList.size()) {
        continue;
      }
      paramBundle = ((String)this.addrList.get(i)).replaceAll("commaseprator", ",");
      System.out.println("address==" + paramBundle);
      if (((String)this.addrList.get(i)).equalsIgnoreCase("no address")) {
        continue;
      }
      this.mShowMapMarkersArray.add(new ShowMapMarker(paramBundle, "currentlocation_icon", Double.valueOf(Double.parseDouble((String)this.lattList.get(i))), Double.valueOf(Double.parseDouble((String)this.lngList.get(i))), i));
      i += 1;
      continue;
      paramBundle = arrayOfString1[0].split(",");
    }
  }
  
  public class MarkerInfoWindowAdapter
    implements GoogleMap.InfoWindowAdapter
  {
    public MarkerInfoWindowAdapter() {}
    
    public View getInfoContents(Marker paramMarker)
    {
      View localView = MapPageMapMarker.this.getLayoutInflater().inflate(2130903084, null);
      paramMarker = (ShowMapMarker)MapPageMapMarker.this.mMarkersHashMap.get(paramMarker);
      ((TextView)localView.findViewById(2131558440)).setText(paramMarker.getmLabel());
      return localView;
    }
    
    public View getInfoWindow(Marker paramMarker)
    {
      return null;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\MapPageMapMarker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */