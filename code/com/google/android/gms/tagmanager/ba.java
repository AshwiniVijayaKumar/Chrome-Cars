package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class ba
{
  public static cr.c br(String paramString)
    throws JSONException
  {
    paramString = k(new JSONObject(paramString));
    cr.d locald = cr.c.jI();
    int i = 0;
    while (i < paramString.ga.length)
    {
      locald.a(cr.a.jE().b(b.cT.toString(), paramString.ga[i]).b(b.cI.toString(), di.bI(m.iB())).b(m.iC(), paramString.gb[i]).jH());
      i += 1;
    }
    return locald.jL();
  }
  
  private static d.a k(Object paramObject)
    throws JSONException
  {
    return di.r(l(paramObject));
  }
  
  static Object l(Object paramObject)
    throws JSONException
  {
    if ((paramObject instanceof JSONArray)) {
      throw new RuntimeException("JSONArrays are not supported");
    }
    if (JSONObject.NULL.equals(paramObject)) {
      throw new RuntimeException("JSON nulls are not supported");
    }
    Object localObject = paramObject;
    if ((paramObject instanceof JSONObject))
    {
      paramObject = (JSONObject)paramObject;
      localObject = new HashMap();
      Iterator localIterator = ((JSONObject)paramObject).keys();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        ((Map)localObject).put(str, l(((JSONObject)paramObject).get(str)));
      }
    }
    return localObject;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\ba.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */