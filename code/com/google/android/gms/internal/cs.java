package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import java.math.BigInteger;
import java.util.Locale;

public final class cs
{
  private static final Object op = new Object();
  private static String pH;
  
  public static String aR()
  {
    synchronized (op)
    {
      String str = pH;
      return str;
    }
  }
  
  public static String b(Context paramContext, String paramString1, String paramString2)
  {
    synchronized (op)
    {
      if ((pH == null) && (!TextUtils.isEmpty(paramString1))) {
        c(paramContext, paramString1, paramString2);
      }
      paramContext = pH;
      return paramContext;
    }
  }
  
  private static void c(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramString2 = paramContext.createPackageContext(paramString2, 3).getClassLoader();
      Class localClass = Class.forName("com.google.ads.mediation.MediationAdapter", false, paramString2);
      paramContext = new BigInteger(new byte[1]);
      String[] arrayOfString = paramString1.split(",");
      int i = 0;
      while (i < arrayOfString.length)
      {
        paramString1 = paramContext;
        if (cv.a(paramString2, localClass, arrayOfString[i])) {
          paramString1 = paramContext.setBit(i);
        }
        i += 1;
        paramContext = paramString1;
      }
    }
    catch (Throwable paramContext)
    {
      pH = "err";
      return;
    }
    tmp93_90[0] = paramContext;
    pH = String.format(Locale.US, "%X", tmp93_90);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\cs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */