package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class cf
  extends aj
{
  private static final String ID = a.Z.toString();
  private static final String Wa = b.dl.toString();
  private static final String Wb = b.dk.toString();
  
  public cf()
  {
    super(ID, new String[0]);
  }
  
  public boolean iy()
  {
    return false;
  }
  
  public d.a u(Map<String, d.a> paramMap)
  {
    Object localObject = (d.a)paramMap.get(Wa);
    paramMap = (d.a)paramMap.get(Wb);
    double d2;
    double d1;
    if ((localObject != null) && (localObject != di.ku()) && (paramMap != null) && (paramMap != di.ku()))
    {
      localObject = di.k((d.a)localObject);
      paramMap = di.k(paramMap);
      if ((localObject != di.ks()) && (paramMap != di.ks()))
      {
        d2 = ((dh)localObject).doubleValue();
        d1 = paramMap.doubleValue();
        if (d2 > d1) {}
      }
    }
    for (;;)
    {
      return di.r(Long.valueOf(Math.round((d1 - d2) * Math.random() + d2)));
      d1 = 2.147483647E9D;
      d2 = 0.0D;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\cf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */