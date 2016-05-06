package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

public final class aq
{
  public static final ar lW = new ar()
  {
    public void a(dd paramAnonymousdd, Map<String, String> paramAnonymousMap)
    {
      paramAnonymousMap = (String)paramAnonymousMap.get("urls");
      if (paramAnonymousMap == null)
      {
        da.w("URLs missing in canOpenURLs GMSG.");
        return;
      }
      String[] arrayOfString = paramAnonymousMap.split(",");
      HashMap localHashMap = new HashMap();
      PackageManager localPackageManager = paramAnonymousdd.getContext().getPackageManager();
      int j = arrayOfString.length;
      int i = 0;
      if (i < j)
      {
        String str1 = arrayOfString[i];
        paramAnonymousMap = str1.split(";", 2);
        String str2 = paramAnonymousMap[0].trim();
        if (paramAnonymousMap.length > 1)
        {
          paramAnonymousMap = paramAnonymousMap[1].trim();
          label97:
          if (localPackageManager.resolveActivity(new Intent(paramAnonymousMap, Uri.parse(str2)), 65536) == null) {
            break label149;
          }
        }
        label149:
        for (boolean bool = true;; bool = false)
        {
          localHashMap.put(str1, Boolean.valueOf(bool));
          i += 1;
          break;
          paramAnonymousMap = "android.intent.action.VIEW";
          break label97;
        }
      }
      paramAnonymousdd.a("openableURLs", localHashMap);
    }
  };
  public static final ar lX = new ar()
  {
    public void a(dd paramAnonymousdd, Map<String, String> paramAnonymousMap)
    {
      str = (String)paramAnonymousMap.get("u");
      if (str == null)
      {
        da.w("URL missing from click GMSG.");
        return;
      }
      paramAnonymousMap = Uri.parse(str);
      try
      {
        Object localObject = paramAnonymousdd.bc();
        if ((localObject == null) || (!((l)localObject).a(paramAnonymousMap))) {
          break label111;
        }
        localObject = ((l)localObject).a(paramAnonymousMap, paramAnonymousdd.getContext());
        paramAnonymousMap = (Map<String, String>)localObject;
      }
      catch (m localm)
      {
        for (;;)
        {
          da.w("Unable to append parameter to URL: " + str);
        }
      }
      paramAnonymousMap = paramAnonymousMap.toString();
      new cy(paramAnonymousdd.getContext(), paramAnonymousdd.bd().pU, paramAnonymousMap).start();
    }
  };
  public static final ar lY = new ar()
  {
    public void a(dd paramAnonymousdd, Map<String, String> paramAnonymousMap)
    {
      paramAnonymousdd = paramAnonymousdd.ba();
      if (paramAnonymousdd == null)
      {
        da.w("A GMSG tried to close something that wasn't an overlay.");
        return;
      }
      paramAnonymousdd.close();
    }
  };
  public static final ar lZ = new ar()
  {
    public void a(dd paramAnonymousdd, Map<String, String> paramAnonymousMap)
    {
      paramAnonymousdd = paramAnonymousdd.ba();
      if (paramAnonymousdd == null)
      {
        da.w("A GMSG tried to use a custom close button on something that wasn't an overlay.");
        return;
      }
      paramAnonymousdd.g("1".equals(paramAnonymousMap.get("custom_close")));
    }
  };
  public static final ar ma = new ar()
  {
    public void a(dd paramAnonymousdd, Map<String, String> paramAnonymousMap)
    {
      paramAnonymousMap = (String)paramAnonymousMap.get("u");
      if (paramAnonymousMap == null)
      {
        da.w("URL missing from httpTrack GMSG.");
        return;
      }
      new cy(paramAnonymousdd.getContext(), paramAnonymousdd.bd().pU, paramAnonymousMap).start();
    }
  };
  public static final ar mb = new ar()
  {
    public void a(dd paramAnonymousdd, Map<String, String> paramAnonymousMap)
    {
      da.u("Received log message: " + (String)paramAnonymousMap.get("string"));
    }
  };
  public static final ar mc = new as();
  public static final ar md = new ar()
  {
    public void a(dd paramAnonymousdd, Map<String, String> paramAnonymousMap)
    {
      String str1 = (String)paramAnonymousMap.get("tx");
      String str2 = (String)paramAnonymousMap.get("ty");
      paramAnonymousMap = (String)paramAnonymousMap.get("td");
      try
      {
        int i = Integer.parseInt(str1);
        int j = Integer.parseInt(str2);
        int k = Integer.parseInt(paramAnonymousMap);
        paramAnonymousdd = paramAnonymousdd.bc();
        if (paramAnonymousdd != null) {
          paramAnonymousdd.y().a(i, j, k);
        }
        return;
      }
      catch (NumberFormatException paramAnonymousdd)
      {
        da.w("Could not parse touch parameters from gmsg.");
      }
    }
  };
  public static final ar me = new at();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\aq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */