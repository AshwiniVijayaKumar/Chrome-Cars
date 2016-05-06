package com.google.android.gms.tagmanager;

import android.content.Context;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class y
  implements aq
{
  private static y UO;
  private static final Object qI = new Object();
  private String UP;
  private String UQ;
  private ar UR;
  private cg Uc;
  
  private y(Context paramContext)
  {
    this(as.H(paramContext), new cw());
  }
  
  y(ar paramar, cg paramcg)
  {
    this.UR = paramar;
    this.Uc = paramcg;
  }
  
  public static aq F(Context paramContext)
  {
    synchronized (qI)
    {
      if (UO == null) {
        UO = new y(paramContext);
      }
      paramContext = UO;
      return paramContext;
    }
  }
  
  public boolean bk(String paramString)
  {
    if (!this.Uc.cl())
    {
      bh.w("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
      return false;
    }
    String str = paramString;
    if (this.UP != null)
    {
      str = paramString;
      if (this.UQ == null) {}
    }
    try
    {
      str = this.UP + "?" + this.UQ + "=" + URLEncoder.encode(paramString, "UTF-8");
      bh.v("Sending wrapped url hit: " + str);
      this.UR.bn(str);
      return true;
    }
    catch (UnsupportedEncodingException paramString)
    {
      bh.b("Error wrapping URL for testing.", paramString);
    }
    return false;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\y.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */