package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ay
{
  public final List<ax> mr;
  public final long ms;
  public final List<String> mt;
  public final List<String> mu;
  public final List<String> mv;
  public final String mw;
  public final long mx;
  public int my;
  public int mz;
  
  public ay(String paramString)
    throws JSONException
  {
    paramString = new JSONObject(paramString);
    if (da.n(2)) {
      da.v("Mediation Response JSON: " + paramString.toString(2));
    }
    JSONArray localJSONArray = paramString.getJSONArray("ad_networks");
    ArrayList localArrayList = new ArrayList(localJSONArray.length());
    int j = -1;
    int i = 0;
    while (i < localJSONArray.length())
    {
      ax localax = new ax(localJSONArray.getJSONObject(i));
      localArrayList.add(localax);
      int k = j;
      if (j < 0)
      {
        k = j;
        if (a(localax)) {
          k = i;
        }
      }
      i += 1;
      j = k;
    }
    this.my = j;
    this.mz = localJSONArray.length();
    this.mr = Collections.unmodifiableList(localArrayList);
    this.mw = paramString.getString("qdata");
    paramString = paramString.optJSONObject("settings");
    if (paramString != null)
    {
      this.ms = paramString.optLong("ad_network_timeout_millis", -1L);
      this.mt = bd.a(paramString, "click_urls");
      this.mu = bd.a(paramString, "imp_urls");
      this.mv = bd.a(paramString, "nofill_urls");
      long l = paramString.optLong("refresh", -1L);
      if (l > 0L) {}
      for (l *= 1000L;; l = -1L)
      {
        this.mx = l;
        return;
      }
    }
    this.ms = -1L;
    this.mt = null;
    this.mu = null;
    this.mv = null;
    this.mx = -1L;
  }
  
  private boolean a(ax paramax)
  {
    paramax = paramax.mn.iterator();
    while (paramax.hasNext()) {
      if (((String)paramax.next()).equals("com.google.ads.mediation.admob.AdMobAdapter")) {
        return true;
      }
    }
    return false;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */