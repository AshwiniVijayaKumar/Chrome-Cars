package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class p
  extends aj
{
  private static final String ID = a.O.toString();
  private final String Un;
  
  public p(String paramString)
  {
    super(ID, new String[0]);
    this.Un = paramString;
  }
  
  public boolean iy()
  {
    return true;
  }
  
  public d.a u(Map<String, d.a> paramMap)
  {
    if (this.Un == null) {
      return di.ku();
    }
    return di.r(this.Un);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\p.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */