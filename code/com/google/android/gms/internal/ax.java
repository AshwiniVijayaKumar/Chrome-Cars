package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ax
{
  public final String adJson;
  public final String mm;
  public final List<String> mn;
  public final String mo;
  public final List<String> mp;
  public final String mq;
  
  public ax(JSONObject paramJSONObject)
    throws JSONException
  {
    this.mm = paramJSONObject.getString("id");
    Object localObject1 = paramJSONObject.getJSONArray("adapters");
    ArrayList localArrayList = new ArrayList(((JSONArray)localObject1).length());
    int i = 0;
    while (i < ((JSONArray)localObject1).length())
    {
      localArrayList.add(((JSONArray)localObject1).getString(i));
      i += 1;
    }
    this.mn = Collections.unmodifiableList(localArrayList);
    this.mo = paramJSONObject.optString("allocation_id", null);
    this.mp = bd.a(paramJSONObject, "imp_urls");
    localObject1 = paramJSONObject.optJSONObject("ad");
    if (localObject1 != null) {}
    for (localObject1 = ((JSONObject)localObject1).toString();; localObject1 = null)
    {
      this.adJson = ((String)localObject1);
      localObject1 = paramJSONObject.optJSONObject("data");
      paramJSONObject = (JSONObject)localObject2;
      if (localObject1 != null) {
        paramJSONObject = ((JSONObject)localObject1).toString();
      }
      this.mq = paramJSONObject;
      return;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */