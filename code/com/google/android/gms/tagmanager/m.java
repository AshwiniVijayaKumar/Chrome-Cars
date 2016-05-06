package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class m
  extends aj
{
  private static final String ID = a.L.toString();
  private static final String VALUE = b.eH.toString();
  
  public m()
  {
    super(ID, new String[] { VALUE });
  }
  
  public static String iB()
  {
    return ID;
  }
  
  public static String iC()
  {
    return VALUE;
  }
  
  public boolean iy()
  {
    return true;
  }
  
  public d.a u(Map<String, d.a> paramMap)
  {
    return (d.a)paramMap.get(VALUE);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\m.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */