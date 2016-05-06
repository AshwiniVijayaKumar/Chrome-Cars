package com.google.android.gms.internal;

public final class fn
  implements fl
{
  private static fn CN;
  
  public static fl eI()
  {
    try
    {
      if (CN == null) {
        CN = new fn();
      }
      fn localfn = CN;
      return localfn;
    }
    finally {}
  }
  
  public long currentTimeMillis()
  {
    return System.currentTimeMillis();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\fn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */