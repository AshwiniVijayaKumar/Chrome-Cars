package com.google.android.gms.tagmanager;

import android.net.Uri;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

class ce
{
  private static ce VS;
  private volatile String TM;
  private volatile a VT;
  private volatile String VU;
  private volatile String VV;
  
  ce()
  {
    clear();
  }
  
  private String bt(String paramString)
  {
    return paramString.split("&")[0].split("=")[1];
  }
  
  private String g(Uri paramUri)
  {
    return paramUri.getQuery().replace("&gtm_debug=x", "");
  }
  
  static ce ju()
  {
    try
    {
      if (VS == null) {
        VS = new ce();
      }
      ce localce = VS;
      return localce;
    }
    finally {}
  }
  
  void clear()
  {
    this.VT = a.VW;
    this.VU = null;
    this.TM = null;
    this.VV = null;
  }
  
  boolean f(Uri paramUri)
  {
    boolean bool = true;
    String str;
    try
    {
      str = URLDecoder.decode(paramUri.toString(), "UTF-8");
      if (!str.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?$")) {
        break label153;
      }
      bh.v("Container preview url: " + str);
      if (!str.matches(".*?&gtm_debug=x$")) {
        break label138;
      }
      this.VT = a.VY;
    }
    catch (UnsupportedEncodingException paramUri)
    {
      for (;;)
      {
        bool = false;
        continue;
        this.VT = a.VX;
      }
    }
    finally {}
    this.VV = g(paramUri);
    if ((this.VT == a.VX) || (this.VT == a.VY)) {
      this.VU = ("/r?" + this.VV);
    }
    this.TM = bt(this.VV);
    for (;;)
    {
      return bool;
      label138:
      label153:
      if (str.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$"))
      {
        if (bt(paramUri.getQuery()).equals(this.TM))
        {
          bh.v("Exit preview mode for container: " + this.TM);
          this.VT = a.VW;
          this.VU = null;
        }
      }
      else
      {
        bh.w("Invalid preview uri: " + str);
        bool = false;
        continue;
      }
      bool = false;
    }
  }
  
  String getContainerId()
  {
    return this.TM;
  }
  
  a jv()
  {
    return this.VT;
  }
  
  String jw()
  {
    return this.VU;
  }
  
  static enum a
  {
    private a() {}
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\ce.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */