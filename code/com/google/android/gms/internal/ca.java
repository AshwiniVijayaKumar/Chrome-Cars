package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesUtil;

public final class ca
{
  public static ct a(Context paramContext, cd paramcd, a parama)
  {
    if (paramcd.kN.pX) {
      return b(paramContext, paramcd, parama);
    }
    return c(paramContext, paramcd, parama);
  }
  
  private static ct b(Context paramContext, cd paramcd, a parama)
  {
    da.s("Fetching ad response from local ad request service.");
    paramContext = new cb.a(paramContext, paramcd, parama);
    paramContext.start();
    return paramContext;
  }
  
  private static ct c(Context paramContext, cd paramcd, a parama)
  {
    da.s("Fetching ad response from remote ad request service.");
    if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext) != 0)
    {
      da.w("Failed to connect to remote ad request service.");
      return null;
    }
    return new cb.b(paramContext, paramcd, parama);
  }
  
  public static abstract interface a
  {
    public abstract void a(cf paramcf);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ca.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */