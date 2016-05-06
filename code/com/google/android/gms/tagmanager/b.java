package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class b
  extends aj
{
  private static final String ID = com.google.android.gms.internal.a.F.toString();
  private final a TC;
  
  public b(Context paramContext)
  {
    this(a.E(paramContext));
  }
  
  b(a parama)
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
    paramMap = this.TC.iu();
    if (paramMap == null) {
      return di.ku();
    }
    return di.r(paramMap);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */