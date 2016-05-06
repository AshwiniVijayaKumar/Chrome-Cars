package com.google.android.gms.tagmanager;

import android.os.Build.VERSION;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class bz
  extends aj
{
  private static final String ID = a.X.toString();
  
  public bz()
  {
    super(ID, new String[0]);
  }
  
  public boolean iy()
  {
    return true;
  }
  
  public d.a u(Map<String, d.a> paramMap)
  {
    return di.r(Build.VERSION.RELEASE);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\bz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */