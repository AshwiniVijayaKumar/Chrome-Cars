package com.google.android.gms.appstate;

import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;

public final class a
  implements AppState
{
  private final int uU;
  private final String uV;
  private final byte[] uW;
  private final boolean uX;
  private final String uY;
  private final byte[] uZ;
  
  public a(AppState paramAppState)
  {
    this.uU = paramAppState.getKey();
    this.uV = paramAppState.getLocalVersion();
    this.uW = paramAppState.getLocalData();
    this.uX = paramAppState.hasConflict();
    this.uY = paramAppState.getConflictVersion();
    this.uZ = paramAppState.getConflictData();
  }
  
  static int a(AppState paramAppState)
  {
    return ep.hashCode(new Object[] { Integer.valueOf(paramAppState.getKey()), paramAppState.getLocalVersion(), paramAppState.getLocalData(), Boolean.valueOf(paramAppState.hasConflict()), paramAppState.getConflictVersion(), paramAppState.getConflictData() });
  }
  
  static boolean a(AppState paramAppState, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof AppState)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramAppState == paramObject);
      paramObject = (AppState)paramObject;
      if ((!ep.equal(Integer.valueOf(((AppState)paramObject).getKey()), Integer.valueOf(paramAppState.getKey()))) || (!ep.equal(((AppState)paramObject).getLocalVersion(), paramAppState.getLocalVersion())) || (!ep.equal(((AppState)paramObject).getLocalData(), paramAppState.getLocalData())) || (!ep.equal(Boolean.valueOf(((AppState)paramObject).hasConflict()), Boolean.valueOf(paramAppState.hasConflict()))) || (!ep.equal(((AppState)paramObject).getConflictVersion(), paramAppState.getConflictVersion()))) {
        break;
      }
      bool1 = bool2;
    } while (ep.equal(((AppState)paramObject).getConflictData(), paramAppState.getConflictData()));
    return false;
  }
  
  static String b(AppState paramAppState)
  {
    return ep.e(paramAppState).a("Key", Integer.valueOf(paramAppState.getKey())).a("LocalVersion", paramAppState.getLocalVersion()).a("LocalData", paramAppState.getLocalData()).a("HasConflict", Boolean.valueOf(paramAppState.hasConflict())).a("ConflictVersion", paramAppState.getConflictVersion()).a("ConflictData", paramAppState.getConflictData()).toString();
  }
  
  public AppState cL()
  {
    return this;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public byte[] getConflictData()
  {
    return this.uZ;
  }
  
  public String getConflictVersion()
  {
    return this.uY;
  }
  
  public int getKey()
  {
    return this.uU;
  }
  
  public byte[] getLocalData()
  {
    return this.uW;
  }
  
  public String getLocalVersion()
  {
    return this.uV;
  }
  
  public boolean hasConflict()
  {
    return this.uX;
  }
  
  public int hashCode()
  {
    return a(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public String toString()
  {
    return b(this);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\appstate\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */