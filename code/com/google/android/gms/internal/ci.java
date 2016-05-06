package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ci
  extends ch.a
{
  private static final Object op = new Object();
  private static ci oq;
  private final Context mContext;
  private final au or;
  
  private ci(Context paramContext, au paramau)
  {
    this.mContext = paramContext;
    this.or = paramau;
  }
  
  private static cf a(Context paramContext, final au paramau, final cd paramcd)
  {
    da.s("Starting ad request from service.");
    paramau.init();
    cm localcm = new cm(paramContext);
    if (localcm.oX == -1)
    {
      da.s("Device is offline.");
      return new cf(2);
    }
    final ck localck = new ck(paramcd.applicationInfo.packageName);
    if (paramcd.oc.extras != null)
    {
      String str = paramcd.oc.extras.getString("_ad");
      if (str != null) {
        return cj.a(paramContext, paramcd, str);
      }
    }
    paramau = cj.a(paramcd, localcm, paramau.a(250L));
    if (paramau == null) {
      return new cf(0);
    }
    cz.pT.post(new Runnable()
    {
      public void run()
      {
        dd localdd = dd.a(this.os, new ab(), false, false, null, paramcd.kN);
        localdd.setWillNotDraw(true);
        localck.b(localdd);
        de localde = localdd.bb();
        localde.a("/invalidRequest", localck.oz);
        localde.a("/loadAdURL", localck.oA);
        localde.a("/log", aq.mb);
        da.s("Getting the ad request URL.");
        localdd.loadDataWithBaseURL("http://googleads.g.doubleclick.net", "<!DOCTYPE html><html><head><script src=\"http://googleads.g.doubleclick.net/mads/static/sdk/native/sdk-core-v40.js\"></script><script>AFMA_buildAdURL(" + paramau + ");</script></head><body></body></html>", "text/html", "UTF-8", null);
      }
    });
    paramau = localck.aI();
    if (TextUtils.isEmpty(paramau)) {
      return new cf(localck.getErrorCode());
    }
    return a(paramContext, paramcd.kN.pU, paramau);
  }
  
  public static cf a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      localcl = new cl();
      paramString2 = new URL(paramString2);
      l = SystemClock.elapsedRealtime();
      i = 0;
    }
    catch (IOException paramContext)
    {
      try
      {
        for (;;)
        {
          cl localcl;
          long l;
          int i;
          cv.a(paramContext, paramString1, false, localHttpURLConnection);
          int j = localHttpURLConnection.getResponseCode();
          Map localMap = localHttpURLConnection.getHeaderFields();
          if ((j >= 200) && (j < 300))
          {
            paramContext = paramString2.toString();
            paramString1 = cv.a(new InputStreamReader(localHttpURLConnection.getInputStream()));
            a(paramContext, localMap, paramString1, j);
            localcl.a(paramContext, localMap, paramString1);
            paramContext = localcl.f(l);
            return paramContext;
          }
          a(paramString2.toString(), localMap, null, j);
          if ((j >= 300) && (j < 400))
          {
            paramString2 = localHttpURLConnection.getHeaderField("Location");
            if (TextUtils.isEmpty(paramString2))
            {
              da.w("No location header to follow redirect.");
              paramContext = new cf(0);
              return paramContext;
            }
            paramString2 = new URL(paramString2);
            i += 1;
            if (i > 5)
            {
              da.w("Too many redirects.");
              paramContext = new cf(0);
              return paramContext;
            }
          }
          else
          {
            da.w("Received error HTTP response code: " + j);
            paramContext = new cf(0);
            return paramContext;
          }
          localcl.d(localMap);
          localHttpURLConnection.disconnect();
        }
      }
      finally
      {
        HttpURLConnection localHttpURLConnection;
        localHttpURLConnection.disconnect();
      }
      paramContext = paramContext;
      da.w("Error while connecting to ad server: " + paramContext.getMessage());
      return new cf(2);
    }
    localHttpURLConnection = (HttpURLConnection)paramString2.openConnection();
  }
  
  public static ci a(Context paramContext, au paramau)
  {
    synchronized (op)
    {
      if (oq == null) {
        oq = new ci(paramContext.getApplicationContext(), paramau);
      }
      paramContext = oq;
      return paramContext;
    }
  }
  
  private static void a(String paramString1, Map<String, List<String>> paramMap, String paramString2, int paramInt)
  {
    if (da.n(2))
    {
      da.v("Http Response: {\n  URL:\n    " + paramString1 + "\n  Headers:");
      if (paramMap != null)
      {
        paramString1 = paramMap.keySet().iterator();
        while (paramString1.hasNext())
        {
          Object localObject = (String)paramString1.next();
          da.v("    " + (String)localObject + ":");
          localObject = ((List)paramMap.get(localObject)).iterator();
          while (((Iterator)localObject).hasNext())
          {
            String str = (String)((Iterator)localObject).next();
            da.v("      " + str);
          }
        }
      }
      da.v("  Body:");
      if (paramString2 != null)
      {
        int i = 0;
        while (i < Math.min(paramString2.length(), 100000))
        {
          da.v(paramString2.substring(i, Math.min(paramString2.length(), i + 1000)));
          i += 1000;
        }
      }
      da.v("    null");
      da.v("  Response Code:\n    " + paramInt + "\n}");
    }
  }
  
  public cf b(cd paramcd)
  {
    return a(this.mContext, this.or, paramcd);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ci.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */