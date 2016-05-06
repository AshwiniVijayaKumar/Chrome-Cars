package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.c;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.dynamic.e.a;

public final class bv
  extends e<bx>
{
  private static final bv nL = new bv();
  
  private bv()
  {
    super("com.google.android.gms.ads.AdOverlayCreatorImpl");
  }
  
  public static bw a(Activity paramActivity)
  {
    try
    {
      if (b(paramActivity))
      {
        da.s("Using AdOverlay from the client jar.");
        return new bo(paramActivity);
      }
      paramActivity = nL.c(paramActivity);
      return paramActivity;
    }
    catch (a paramActivity)
    {
      da.w(paramActivity.getMessage());
    }
    return null;
  }
  
  private static boolean b(Activity paramActivity)
    throws bv.a
  {
    paramActivity = paramActivity.getIntent();
    if (!paramActivity.hasExtra("com.google.android.gms.ads.internal.overlay.useClientJar")) {
      throw new a("Ad overlay requires the useClientJar flag in intent extras.");
    }
    return paramActivity.getBooleanExtra("com.google.android.gms.ads.internal.overlay.useClientJar", false);
  }
  
  private bw c(Activity paramActivity)
  {
    try
    {
      b localb = c.h(paramActivity);
      paramActivity = bw.a.m(((bx)z(paramActivity)).a(localb));
      return paramActivity;
    }
    catch (RemoteException paramActivity)
    {
      da.b("Could not create remote AdOverlay.", paramActivity);
      return null;
    }
    catch (e.a paramActivity)
    {
      da.b("Could not create remote AdOverlay.", paramActivity);
    }
    return null;
  }
  
  protected bx l(IBinder paramIBinder)
  {
    return bx.a.n(paramIBinder);
  }
  
  private static final class a
    extends Exception
  {
    public a(String paramString)
    {
      super();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\bv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */