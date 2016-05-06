package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.location.Location;
import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class cj
{
  private static final SimpleDateFormat ow = new SimpleDateFormat("yyyyMMdd");
  
  public static cf a(Context paramContext, cd paramcd, String paramString)
  {
    Object localObject4;
    Object localObject1;
    String str2;
    String str1;
    long l2;
    long l1;
    Object localObject2;
    int i;
    label193:
    label231:
    int j;
    for (;;)
    {
      try
      {
        localObject4 = new JSONObject(paramString);
        paramString = ((JSONObject)localObject4).optString("ad_base_url", null);
        localObject1 = ((JSONObject)localObject4).optString("ad_url", null);
        str2 = ((JSONObject)localObject4).optString("ad_size", null);
        str1 = ((JSONObject)localObject4).optString("ad_html", null);
        l2 = -1L;
        if (!((JSONObject)localObject4).has("interstitial_timeout")) {
          break label592;
        }
        l1 = (((JSONObject)localObject4).getDouble("interstitial_timeout") * 1000.0D);
        localObject2 = ((JSONObject)localObject4).optString("orientation", null);
        i = -1;
        if ("portrait".equals(localObject2))
        {
          i = cv.aU();
          if (!TextUtils.isEmpty(str1))
          {
            if (!TextUtils.isEmpty(paramString)) {
              break label586;
            }
            da.w("Could not parse the mediation config: Missing required ad_base_url field");
            return new cf(0);
          }
        }
        else
        {
          if (!"landscape".equals(localObject2)) {
            continue;
          }
          i = cv.aT();
          continue;
        }
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          localObject1 = ci.a(paramContext, paramcd.kN.pU, (String)localObject1);
          paramString = ((cf)localObject1).nw;
          str1 = ((cf)localObject1).oi;
          l2 = ((cf)localObject1).oo;
          localObject2 = ((JSONObject)localObject4).optJSONArray("click_urls");
          if (localObject1 == null)
          {
            paramContext = null;
            if (localObject2 == null) {
              break;
            }
            paramcd = paramContext;
            if (paramContext != null) {
              break label600;
            }
            paramcd = new LinkedList();
            break label600;
            if (j >= ((JSONArray)localObject2).length()) {
              break label606;
            }
            paramcd.add(((JSONArray)localObject2).getString(j));
            j += 1;
            continue;
          }
        }
        else
        {
          da.w("Could not parse the mediation config: Missing required ad_html or ad_url field.");
          paramContext = new cf(0);
          return paramContext;
        }
      }
      catch (JSONException paramContext)
      {
        da.w("Could not parse the mediation config: " + paramContext.getMessage());
        return new cf(0);
      }
      paramContext = ((cf)localObject1).mt;
    }
    label324:
    Object localObject3 = ((JSONObject)localObject4).optJSONArray("impression_urls");
    if (localObject1 == null) {
      paramContext = null;
    }
    while (localObject3 != null)
    {
      paramcd = paramContext;
      if (paramContext != null) {
        break label612;
      }
      paramcd = new LinkedList();
      break label612;
      label362:
      while (j < ((JSONArray)localObject3).length())
      {
        paramcd.add(((JSONArray)localObject3).getString(j));
        j += 1;
      }
      paramContext = ((cf)localObject1).mu;
      continue;
      label404:
      localObject4 = ((JSONObject)localObject4).optJSONArray("manual_impression_urls");
      if (localObject1 == null) {}
      for (paramContext = null; localObject4 != null; paramContext = ((cf)localObject1).om)
      {
        paramcd = paramContext;
        if (paramContext != null) {
          break label624;
        }
        paramcd = new LinkedList();
        break label624;
        label442:
        while (j < ((JSONArray)localObject4).length())
        {
          paramcd.add(((JSONArray)localObject4).getString(j));
          j += 1;
        }
      }
    }
    for (;;)
    {
      long l3 = l1;
      j = i;
      if (localObject1 != null)
      {
        if (((cf)localObject1).orientation != -1) {
          i = ((cf)localObject1).orientation;
        }
        l3 = l1;
        j = i;
        if (((cf)localObject1).oj > 0L)
        {
          l3 = ((cf)localObject1).oj;
          j = i;
        }
      }
      paramContext = new cf(paramString, str1, (List)localObject2, (List)localObject3, l3, false, -1L, paramContext, -1L, j, str2, l2);
      return paramContext;
      continue;
      localObject3 = paramContext;
      break label404;
      localObject2 = paramContext;
      break label324;
      label586:
      localObject1 = null;
      break label193;
      label592:
      l1 = -1L;
      break;
      label600:
      j = 0;
      break label231;
      label606:
      localObject2 = paramcd;
      break label324;
      label612:
      j = 0;
      break label362;
      localObject3 = paramcd;
      break label404;
      label624:
      j = 0;
      break label442;
      paramContext = paramcd;
    }
  }
  
  public static String a(cd paramcd, cm paramcm, Location paramLocation)
  {
    try
    {
      paramLocation = new HashMap();
      if (paramcd.ob != null) {
        paramLocation.put("ad_pos", paramcd.ob);
      }
      a(paramLocation, paramcd.oc);
      paramLocation.put("format", paramcd.kQ.ln);
      if (paramcd.kQ.width == -1) {
        paramLocation.put("smart_w", "full");
      }
      if (paramcd.kQ.height == -2) {
        paramLocation.put("smart_h", "auto");
      }
      if (paramcd.kQ.lp != null)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        ab[] arrayOfab = paramcd.kQ.lp;
        int k = arrayOfab.length;
        int i = 0;
        if (i < k)
        {
          ab localab = arrayOfab[i];
          if (localStringBuilder.length() != 0) {
            localStringBuilder.append("|");
          }
          if (localab.width == -1)
          {
            j = (int)(localab.widthPixels / paramcm.pc);
            label175:
            localStringBuilder.append(j);
            localStringBuilder.append("x");
            if (localab.height != -2) {
              break label240;
            }
          }
          label240:
          for (int j = (int)(localab.heightPixels / paramcm.pc);; j = localab.height)
          {
            localStringBuilder.append(j);
            i += 1;
            break;
            j = localab.width;
            break label175;
          }
        }
        paramLocation.put("sz", localStringBuilder);
      }
      paramLocation.put("slotname", paramcd.adUnitId);
      paramLocation.put("pn", paramcd.applicationInfo.packageName);
      if (paramcd.od != null) {
        paramLocation.put("vc", Integer.valueOf(paramcd.od.versionCode));
      }
      paramLocation.put("ms", paramcd.oe);
      paramLocation.put("seq_num", paramcd.of);
      paramLocation.put("session_id", paramcd.og);
      paramLocation.put("js", paramcd.kN.pU);
      a(paramLocation, paramcm);
      if ((paramcd.oc.versionCode >= 2) && (paramcd.oc.ll != null)) {
        a(paramLocation, paramcd.oc.ll);
      }
      if (paramcd.versionCode >= 2) {
        paramLocation.put("quality_signals", paramcd.oh);
      }
      if (da.n(2))
      {
        paramcd = cv.m(paramLocation).toString(2);
        da.v("Ad Request JSON: " + paramcd);
      }
      paramcd = cv.m(paramLocation).toString();
      return paramcd;
    }
    catch (JSONException paramcd)
    {
      da.w("Problem serializing ad request to JSON: " + paramcd.getMessage());
    }
    return null;
  }
  
  private static void a(HashMap<String, Object> paramHashMap, Location paramLocation)
  {
    HashMap localHashMap = new HashMap();
    float f = paramLocation.getAccuracy();
    long l1 = paramLocation.getTime();
    long l2 = (paramLocation.getLatitude() * 1.0E7D);
    long l3 = (paramLocation.getLongitude() * 1.0E7D);
    localHashMap.put("radius", Float.valueOf(f * 1000.0F));
    localHashMap.put("lat", Long.valueOf(l2));
    localHashMap.put("long", Long.valueOf(l3));
    localHashMap.put("time", Long.valueOf(l1 * 1000L));
    paramHashMap.put("uule", localHashMap);
  }
  
  private static void a(HashMap<String, Object> paramHashMap, am paramam)
  {
    Object localObject2 = null;
    if (Color.alpha(paramam.lI) != 0) {
      paramHashMap.put("acolor", m(paramam.lI));
    }
    if (Color.alpha(paramam.backgroundColor) != 0) {
      paramHashMap.put("bgcolor", m(paramam.backgroundColor));
    }
    if ((Color.alpha(paramam.lJ) != 0) && (Color.alpha(paramam.lK) != 0))
    {
      paramHashMap.put("gradientto", m(paramam.lJ));
      paramHashMap.put("gradientfrom", m(paramam.lK));
    }
    if (Color.alpha(paramam.lL) != 0) {
      paramHashMap.put("bcolor", m(paramam.lL));
    }
    paramHashMap.put("bthick", Integer.toString(paramam.lM));
    Object localObject1;
    switch (paramam.lN)
    {
    default: 
      localObject1 = null;
      if (localObject1 != null) {
        paramHashMap.put("btype", localObject1);
      }
      switch (paramam.lO)
      {
      default: 
        localObject1 = localObject2;
      }
      break;
    }
    for (;;)
    {
      if (localObject1 != null) {
        paramHashMap.put("callbuttoncolor", localObject1);
      }
      if (paramam.lP != null) {
        paramHashMap.put("channel", paramam.lP);
      }
      if (Color.alpha(paramam.lQ) != 0) {
        paramHashMap.put("dcolor", m(paramam.lQ));
      }
      if (paramam.lR != null) {
        paramHashMap.put("font", paramam.lR);
      }
      if (Color.alpha(paramam.lS) != 0) {
        paramHashMap.put("hcolor", m(paramam.lS));
      }
      paramHashMap.put("headersize", Integer.toString(paramam.lT));
      if (paramam.lU != null) {
        paramHashMap.put("q", paramam.lU);
      }
      return;
      localObject1 = "none";
      break;
      localObject1 = "dashed";
      break;
      localObject1 = "dotted";
      break;
      localObject1 = "solid";
      break;
      localObject1 = "dark";
      continue;
      localObject1 = "light";
      continue;
      localObject1 = "medium";
    }
  }
  
  private static void a(HashMap<String, Object> paramHashMap, cm paramcm)
  {
    paramHashMap.put("am", Integer.valueOf(paramcm.oM));
    paramHashMap.put("cog", j(paramcm.oN));
    paramHashMap.put("coh", j(paramcm.oO));
    if (!TextUtils.isEmpty(paramcm.oP)) {
      paramHashMap.put("carrier", paramcm.oP);
    }
    paramHashMap.put("gl", paramcm.oQ);
    if (paramcm.oR) {
      paramHashMap.put("simulator", Integer.valueOf(1));
    }
    paramHashMap.put("ma", j(paramcm.oS));
    paramHashMap.put("sp", j(paramcm.oT));
    paramHashMap.put("hl", paramcm.oU);
    if (!TextUtils.isEmpty(paramcm.oV)) {
      paramHashMap.put("mv", paramcm.oV);
    }
    paramHashMap.put("muv", Integer.valueOf(paramcm.oW));
    if (paramcm.oX != -2) {
      paramHashMap.put("cnt", Integer.valueOf(paramcm.oX));
    }
    paramHashMap.put("gnt", Integer.valueOf(paramcm.oY));
    paramHashMap.put("pt", Integer.valueOf(paramcm.oZ));
    paramHashMap.put("rm", Integer.valueOf(paramcm.pa));
    paramHashMap.put("riv", Integer.valueOf(paramcm.pb));
    paramHashMap.put("u_sd", Float.valueOf(paramcm.pc));
    paramHashMap.put("sh", Integer.valueOf(paramcm.pe));
    paramHashMap.put("sw", Integer.valueOf(paramcm.pd));
  }
  
  private static void a(HashMap<String, Object> paramHashMap, z paramz)
  {
    String str = cs.aR();
    if (str != null) {
      paramHashMap.put("abf", str);
    }
    if (paramz.le != -1L) {
      paramHashMap.put("cust_age", ow.format(new Date(paramz.le)));
    }
    if (paramz.extras != null) {
      paramHashMap.put("extras", paramz.extras);
    }
    if (paramz.lf != -1) {
      paramHashMap.put("cust_gender", Integer.valueOf(paramz.lf));
    }
    if (paramz.lg != null) {
      paramHashMap.put("kw", paramz.lg);
    }
    if (paramz.tagForChildDirectedTreatment != -1) {
      paramHashMap.put("tag_for_child_directed_treatment", Integer.valueOf(paramz.tagForChildDirectedTreatment));
    }
    if (paramz.lh) {
      paramHashMap.put("adtest", "on");
    }
    if (paramz.versionCode >= 2)
    {
      if (paramz.li) {
        paramHashMap.put("d_imp_hdr", Integer.valueOf(1));
      }
      if (!TextUtils.isEmpty(paramz.lj)) {
        paramHashMap.put("ppid", paramz.lj);
      }
      if (paramz.lk != null) {
        a(paramHashMap, paramz.lk);
      }
    }
    if ((paramz.versionCode >= 3) && (paramz.lm != null)) {
      paramHashMap.put("url", paramz.lm);
    }
  }
  
  private static Integer j(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 1;; i = 0) {
      return Integer.valueOf(i);
    }
  }
  
  private static String m(int paramInt)
  {
    return String.format(Locale.US, "#%06x", new Object[] { Integer.valueOf(0xFFFFFF & paramInt) });
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\cj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */