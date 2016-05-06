package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d.a;
import java.util.Locale;
import java.util.Map;

class bc
  extends aj
{
  private static final String ID = a.W.toString();
  
  public bc()
  {
    super(ID, new String[0]);
  }
  
  public boolean iy()
  {
    return false;
  }
  
  public d.a u(Map<String, d.a> paramMap)
  {
    paramMap = Locale.getDefault();
    if (paramMap == null) {
      return di.ku();
    }
    paramMap = paramMap.getLanguage();
    if (paramMap == null) {
      return di.ku();
    }
    return di.r(paramMap.toLowerCase());
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\bc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */