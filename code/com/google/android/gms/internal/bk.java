package com.google.android.gms.internal;

import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdRequest.Gender;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.a;
import java.util.Date;
import java.util.HashSet;

public final class bk
{
  public static int a(AdRequest.ErrorCode paramErrorCode)
  {
    switch (1.mX[paramErrorCode.ordinal()])
    {
    default: 
      return 0;
    case 2: 
      return 1;
    case 3: 
      return 2;
    }
    return 3;
  }
  
  public static int a(AdRequest.Gender paramGender)
  {
    switch (1.mW[paramGender.ordinal()])
    {
    default: 
      return 0;
    case 1: 
      return 2;
    }
    return 1;
  }
  
  public static AdSize b(ab paramab)
  {
    return new AdSize(a.a(paramab.width, paramab.height, paramab.ln));
  }
  
  public static MediationAdRequest e(z paramz)
  {
    if (paramz.lg != null) {}
    for (HashSet localHashSet = new HashSet(paramz.lg);; localHashSet = null) {
      return new MediationAdRequest(new Date(paramz.le), g(paramz.lf), localHashSet, paramz.lh);
    }
  }
  
  public static AdRequest.Gender g(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return AdRequest.Gender.UNKNOWN;
    case 2: 
      return AdRequest.Gender.FEMALE;
    }
    return AdRequest.Gender.MALE;
  }
  
  public static final AdRequest.ErrorCode h(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return AdRequest.ErrorCode.INTERNAL_ERROR;
    case 1: 
      return AdRequest.ErrorCode.INVALID_REQUEST;
    case 2: 
      return AdRequest.ErrorCode.NETWORK_ERROR;
    }
    return AdRequest.ErrorCode.NO_FILL;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\bk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */