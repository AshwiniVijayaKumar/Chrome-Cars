package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Map;

class ah
  extends aj
{
  private static final String ID = a.T.toString();
  private final ct TO;
  
  public ah(ct paramct)
  {
    super(ID, new String[0]);
    this.TO = paramct;
  }
  
  public boolean iy()
  {
    return false;
  }
  
  public d.a u(Map<String, d.a> paramMap)
  {
    paramMap = this.TO.jY();
    if (paramMap == null) {
      return di.ku();
    }
    return di.r(paramMap);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\ah.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */