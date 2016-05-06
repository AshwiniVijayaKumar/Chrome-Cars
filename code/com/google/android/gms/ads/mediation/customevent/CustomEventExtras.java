package com.google.android.gms.ads.mediation.customevent;

import com.google.ads.mediation.NetworkExtras;
import java.util.HashMap;

public final class CustomEventExtras
  implements NetworkExtras
{
  private final HashMap<String, Object> qt = new HashMap();
  
  public Object getExtra(String paramString)
  {
    return this.qt.get(paramString);
  }
  
  public void setExtra(String paramString, Object paramObject)
  {
    this.qt.put(paramString, paramObject);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\ads\mediation\customevent\CustomEventExtras.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */