package com.google.android.gms.internal;

import java.util.Map;

public final class ao
  implements ar
{
  private final ap lV;
  
  public ao(ap paramap)
  {
    this.lV = paramap;
  }
  
  public void a(dd paramdd, Map<String, String> paramMap)
  {
    paramdd = (String)paramMap.get("name");
    if (paramdd == null)
    {
      da.w("App event with no name parameter.");
      return;
    }
    this.lV.onAppEvent(paramdd, (String)paramMap.get("info"));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */