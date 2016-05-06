package com.appyjump.video.sdk.data;

import android.os.Build;
import android.os.Build.VERSION;
import com.appyjump.video.sdk.Mode;

public class Request
{
  private String deviceId;
  private String headers;
  private double latitude = 0.0D;
  private String location;
  private double longitude = 0.0D;
  private Mode mode;
  private String protocolVersion;
  private String publisherId;
  private String requestType;
  private String serverUrl;
  private String userAgent;
  
  public String getAndroidVersion()
  {
    return Build.VERSION.RELEASE;
  }
  
  public String getDeviceId()
  {
    if (this.deviceId == null) {
      return "";
    }
    return this.deviceId;
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
  
  public double getLatitude()
  {
    return this.latitude;
  }
  
  public String getLocation()
  {
    return this.location;
  }
  
  public double getLongitude()
  {
    return this.longitude;
  }
  
  public Mode getMode()
  {
    if (this.mode == null) {
      this.mode = Mode.LIVE;
    }
    return this.mode;
  }
  
  public String getProtocolVersion()
  {
    if (this.protocolVersion == null) {
      return "";
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
    if (this.requestType == null) {
      return "";
    }
    return this.requestType;
  }
  
  public String getServerUrl()
  {
    if (this.serverUrl == null) {
      return "";
    }
    return this.serverUrl;
  }
  
  public String getUserAgent()
  {
    if (this.userAgent == null) {
      return "";
    }
    return this.userAgent;
  }
  
  public void setDeviceId(String paramString)
  {
    this.deviceId = paramString;
  }
  
  public void setHeaders(String paramString)
  {
    this.headers = paramString;
  }
  
  public void setLatitude(double paramDouble)
  {
    this.latitude = paramDouble;
  }
  
  public void setLocation(String paramString)
  {
    this.location = paramString;
  }
  
  public void setLongitude(double paramDouble)
  {
    this.longitude = paramDouble;
  }
  
  public void setMode(Mode paramMode)
  {
    this.mode = paramMode;
  }
  
  public void setProtocolVersion(String paramString)
  {
    this.protocolVersion = paramString;
  }
  
  public void setPublisherId(String paramString)
  {
    this.publisherId = paramString;
  }
  
  public void setRequestType(String paramString)
  {
    this.requestType = paramString;
  }
  
  public void setServerUrl(String paramString)
  {
    this.serverUrl = paramString;
  }
  
  public void setUserAgent(String paramString)
  {
    this.userAgent = paramString;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\appyjump\video\sdk\data\Request.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */