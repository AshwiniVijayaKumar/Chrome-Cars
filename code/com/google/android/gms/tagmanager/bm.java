package com.google.android.gms.tagmanager;

import android.os.Build.VERSION;

class bm
{
  int iA()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public bl ji()
  {
    if (iA() < 8) {
      return new av();
    }
    return new aw();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\bm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */