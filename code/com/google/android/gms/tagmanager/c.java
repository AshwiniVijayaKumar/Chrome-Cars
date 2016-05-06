package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class c
  extends aj
{
  private static final String ID = com.google.android.gms.internal.a.G.toString();
  private final a TC;
  
  public c(Context paramContext)
  {
    this(a.E(paramContext));
  }
  
  c(a parama)
  {
    super(ID, new String[0]);
    this.TC = parama;
  }
  
  public boolean iy()
  {
    return false;
  }
  
  public d.a u(Map<String, d.a> paramMap)
  {
    return di.r(Boolean.valueOf(this.TC.isLimitAdTrackingEnabled()));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */