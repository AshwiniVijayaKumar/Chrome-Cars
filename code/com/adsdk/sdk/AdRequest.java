package com.adsdk.sdk;

import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;

public class AdRequest
{
  public static final int BANNER = 0;
  private static final String REQUEST_TYPE_ANDROID = "android_app";
  public static final int VAD = 1;
  private String connectionType;
  private String deviceId;
  private String deviceId2;
  private String headers;
  private String ipAddress;
  private double latitude = 0.0D;
  private String listAds;
  private double longitude = 0.0D;
  private String protocolVersion;
  private String publisherId;
  private String requestURL;
  private long timestamp;
  private int type = -1;
  private String userAgent;
  private String userAgent2;
  
  public String getAndroidVersion()
  {
    return Build.VERSION.RELEASE;
  }
  
  public String getConnectionType()
  {
    return this.connectionType;
  }
  
  public String getDeviceId()
  {
    if (this.deviceId == null) {
      return "";
    }
    return this.deviceId;
  }
  
  public String getDeviceId2()
  {
    return this.deviceId2;
  }
  
  public String getDeviceMode()
  {
    return Build.MODEL;
  }
  
  public String getHeaders()
  {
    if (this.headers == null) {
      return "";
    }
    return this.headers;
  }
  
  public String getIpAddress()
  {
    if (this.ipAddress == null) {
      return "";
    }
    return this.ipAddress;
  }
  
  public double getLatitude()
  {
    return this.latitude;
  }
  
  public String getListAds()
  {
    if (this.listAds != null) {
      return this.listAds;
    }
    return "";
  }
  
  public double getLongitude()
  {
    return this.longitude;
  }
  
  public String getProtocolVersion()
  {
    if (this.protocolVersion == null) {
      return "4.1.6";
    }
    return this.protocolVersion;
  }
  
  public String getPublisherId()
  {
    if (this.publisherId == null) {
      return "";
    }
    return this.publisherId;
  }
  
  public String getRequestType()
  {
    return "android_app";
  }
  
  public String getRequestURL()
  {
    return this.requestURL;
  }
  
  public long getTimestamp()
  {
    return this.timestamp;
  }
  
  public int getType()
  {
    return this.type;
  }
  
  public String getUserAgent()
  {
    if (this.userAgent == null) {
      return "";
    }
    return this.userAgent;
  }
  
  public String getUserAgent2()
  {
    if (this.userAgent2 == null) {
      return "";
    }
    return this.userAgent2;
  }
  
  public void setConnectionType(String paramString)
  {
    this.connectionType = paramString;
  }
  
  public void setDeviceId(String paramString)
  {
    this.deviceId = paramString;
  }
  
  public void setDeviceId2(String paramString)
  {
    this.deviceId2 = paramString;
  }
  
  public void setHeaders(String paramString)
  {
    this.headers = paramString;
  }
  
  public void setIpAddress(String paramString)
  {
    this.ipAddress = paramString;
  }
  
  public void setLatitude(double paramDouble)
  {
    this.latitude = paramDouble;
  }
  
  public void setListAds(String paramString)
  {
    this.listAds = paramString;
  }
  
  public void setLongitude(double paramDouble)
  {
    this.longitude = paramDouble;
  }
  
  public void setProtocolVersion(String paramString)
  {
    this.protocolVersion = paramString;
  }
  
  public void setPublisherId(String paramString)
  {
    this.publisherId = paramString;
  }
  
  public void setRequestURL(String paramString)
  {
    this.requestURL = paramString;
  }
  
  public void setTimestamp(long paramLong)
  {
    this.timestamp = paramLong;
  }
  
  public void setType(int paramInt)
  {
    this.type = paramInt;
  }
  
  public void setUserAgent(String paramString)
  {
    this.userAgent = paramString;
  }
  
  public void setUserAgent2(String paramString)
  {
    this.userAgent2 = paramString;
  }
  
  public String toString()
  {
    return toUri().toString();
  }
  
  public Uri toUri()
  {
    Uri.Builder localBuilder = Uri.parse(getRequestURL()).buildUpon();
    localBuilder.appendQueryParameter("rt", getRequestType());
    localBuilder.appendQueryParameter("v", getProtocolVersion());
    localBuilder.appendQueryParameter("i", getIpAddress());
    localBuilder.appendQueryParameter("u", getUserAgent());
    localBuilder.appendQueryParameter("u2", getUserAgent2());
    localBuilder.appendQueryParameter("s", getPublisherId());
    localBuilder.appendQueryParameter("o", getDeviceId());
    localBuilder.appendQueryParameter("o2", getDeviceId2());
    localBuilder.appendQueryParameter("t", Long.toString(getTimestamp()));
    localBuilder.appendQueryParameter("connection_type", getConnectionType());
    localBuilder.appendQueryParameter("listads", getListAds());
    switch (getType())
    {
    }
    for (;;)
    {
      localBuilder.appendQueryParameter("u_wv", getUserAgent());
      return localBuilder.build();
      localBuilder.appendQueryParameter("c.mraid", "1");
      localBuilder.appendQueryParameter("sdk", "banner");
      continue;
      localBuilder.appendQueryParameter("c.mraid", "0");
      localBuilder.appendQueryParameter("sdk", "vad");
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\AdRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */