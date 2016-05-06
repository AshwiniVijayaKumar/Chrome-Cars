package com.google.android.gms.analytics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class ab
{
  private final Map<String, Integer> tW = new HashMap();
  private final Map<String, String> tX = new HashMap();
  private final boolean tY;
  private final String tZ;
  
  ab(String paramString, boolean paramBoolean)
  {
    this.tY = paramBoolean;
    this.tZ = paramString;
  }
  
  void c(String paramString, int paramInt)
  {
    if (!this.tY) {
      return;
    }
    Integer localInteger2 = (Integer)this.tW.get(paramString);
    Integer localInteger1 = localInteger2;
    if (localInteger2 == null) {
      localInteger1 = Integer.valueOf(0);
    }
    this.tW.put(paramString, Integer.valueOf(localInteger1.intValue() + paramInt));
  }
  
  String cn()
  {
    if (!this.tY) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.tZ);
    Iterator localIterator = this.tW.keySet().iterator();
    String str;
    while (localIterator.hasNext())
    {
      str = (String)localIterator.next();
      localStringBuilder.append("&").append(str).append("=").append(this.tW.get(str));
    }
    localIterator = this.tX.keySet().iterator();
    while (localIterator.hasNext())
    {
      str = (String)localIterator.next();
      localStringBuilder.append("&").append(str).append("=").append((String)this.tX.get(str));
    }
    return localStringBuilder.toString();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\analytics\ab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */