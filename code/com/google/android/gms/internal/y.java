package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.e.a;

public final class y
  extends e<ah>
{
  private static final y ld = new y();
  
  private y()
  {
    super("com.google.android.gms.ads.AdManagerCreatorImpl");
  }
  
  public static ag a(Context paramContext, ab paramab, String paramString, be parambe)
  {
    Object localObject;
    if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext) == 0)
    {
      ag localag = ld.b(paramContext, paramab, paramString, parambe);
      localObject = localag;
      if (localag != null) {}
    }
    else
    {
      da.s("Using AdManager from the client jar.");
      localObject = new v(paramContext, paramab, paramString, parambe, new db(4323000, 4323000, true));
    }
    return (ag)localObject;
  }
  
  private ag b(Context paramContext, ab paramab, String paramString, be parambe)
  {
    try
    {
      b localb = c.h(paramContext);
      paramContext = ag.a.f(((ah)z(paramContext)).a(localb, paramab, paramString, parambe, 4323000));
      return paramContext;
    }
    catch (RemoteException paramContext)
    {
      da.b("Could not create remote AdManager.", paramContext);
      return null;
    }
    catch (e.a paramContext)
    {
      da.b("Could not create remote AdManager.", paramContext);
    }
    return null;
  }
  
  protected ah c(IBinder paramIBinder)
  {
    return ah.a.g(paramIBinder);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\y.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */