package com.google.android.gms.analytics;

import android.app.Activity;
import java.util.HashMap;
import java.util.Map;

class aj
  implements j
{
  String uK;
  double uL = -1.0D;
  int uM = -1;
  int uN = -1;
  int uO = -1;
  int uP = -1;
  Map<String, String> uQ = new HashMap();
  
  public String H(String paramString)
  {
    String str = (String)this.uQ.get(paramString);
    if (str != null) {
      return str;
    }
    return paramString;
  }
  
  public boolean cB()
  {
    return this.uK != null;
  }
  
  public String cC()
  {
    return this.uK;
  }
  
  public boolean cD()
  {
    return this.uL >= 0.0D;
  }
  
  public double cE()
  {
    return this.uL;
  }
  
  public boolean cF()
  {
    return this.uM >= 0;
  }
  
  public boolean cG()
  {
    return this.uN != -1;
  }
  
  public boolean cH()
  {
    return this.uN == 1;
  }
  
  public boolean cI()
  {
    return this.uO != -1;
  }
  
  public boolean cJ()
  {
    return this.uO == 1;
  }
  
  public boolean cK()
  {
    return this.uP == 1;
  }
  
  public int getSessionTimeout()
  {
    return this.uM;
  }
  
  public String h(Activity paramActivity)
  {
    return H(paramActivity.getClass().getCanonicalName());
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\analytics\aj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */