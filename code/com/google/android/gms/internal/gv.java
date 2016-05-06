package com.google.android.gms.internal;

public final class gv
{
  public static String aW(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      fz.h("MatchTurnStatus", "Unknown match turn status: " + paramInt);
      return "TURN_STATUS_UNKNOWN";
    case 0: 
      return "TURN_STATUS_INVITED";
    case 1: 
      return "TURN_STATUS_MY_TURN";
    case 2: 
      return "TURN_STATUS_THEIR_TURN";
    }
    return "TURN_STATUS_COMPLETE";
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\gv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */