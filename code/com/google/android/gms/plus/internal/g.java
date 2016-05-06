package com.google.android.gms.plus.internal;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.internal.er;
import com.google.android.gms.plus.PlusOneDummyView;

public final class g
{
  private static Context PB;
  private static c Rl;
  
  private static c D(Context paramContext)
    throws g.a
  {
    er.f(paramContext);
    if (Rl == null)
    {
      if (PB == null)
      {
        PB = GooglePlayServicesUtil.getRemoteContext(paramContext);
        if (PB == null) {
          throw new a("Could not get remote context.");
        }
      }
      paramContext = PB.getClassLoader();
    }
    try
    {
      Rl = c.a.az((IBinder)paramContext.loadClass("com.google.android.gms.plus.plusone.PlusOneButtonCreatorImpl").newInstance());
      return Rl;
    }
    catch (ClassNotFoundException paramContext)
    {
      throw new a("Could not load creator class.");
    }
    catch (InstantiationException paramContext)
    {
      throw new a("Could not instantiate creator.");
    }
    catch (IllegalAccessException paramContext)
    {
      throw new a("Could not access creator.");
    }
  }
  
  public static View a(Context paramContext, int paramInt1, int paramInt2, String paramString, int paramInt3)
  {
    if (paramString == null) {
      try
      {
        throw new NullPointerException();
      }
      catch (Exception paramString)
      {
        return new PlusOneDummyView(paramContext, paramInt1);
      }
    }
    paramString = (View)com.google.android.gms.dynamic.c.b(D(paramContext).a(com.google.android.gms.dynamic.c.h(paramContext), paramInt1, paramInt2, paramString, paramInt3));
    return paramString;
  }
  
  public static class a
    extends Exception
  {
    public a(String paramString)
    {
      super();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\plus\internal\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */