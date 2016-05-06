package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class s
  extends aj
{
  private static final String ID = a.U.toString();
  private static final String TF = b.bi.toString();
  private static final String Up = b.cJ.toString();
  private final a Uq;
  
  public s(a parama)
  {
    super(ID, new String[] { Up });
    this.Uq = parama;
  }
  
  public boolean iy()
  {
    return false;
  }
  
  public d.a u(Map<String, d.a> paramMap)
  {
    String str = di.j((d.a)paramMap.get(Up));
    HashMap localHashMap = new HashMap();
    paramMap = (d.a)paramMap.get(TF);
    if (paramMap != null)
    {
      paramMap = di.o(paramMap);
      if (!(paramMap instanceof Map))
      {
        bh.w("FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
        return di.ku();
      }
      paramMap = ((Map)paramMap).entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        localHashMap.put(localEntry.getKey().toString(), localEntry.getValue());
      }
    }
    try
    {
      paramMap = di.r(this.Uq.b(str, localHashMap));
      return paramMap;
    }
    catch (Exception paramMap)
    {
      bh.w("Custom macro/tag " + str + " threw exception " + paramMap.getMessage());
    }
    return di.ku();
  }
  
  public static abstract interface a
  {
    public abstract Object b(String paramString, Map<String, Object> paramMap);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\s.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */