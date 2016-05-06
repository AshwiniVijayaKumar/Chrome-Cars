package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class cl
{
  private int mOrientation = -1;
  private String oC;
  private String oD;
  private String oE;
  private List<String> oF;
  private List<String> oG;
  private long oH = -1L;
  private boolean oI = false;
  private final long oJ = -1L;
  private List<String> oK;
  private long oL = -1L;
  
  private static long a(Map<String, List<String>> paramMap, String paramString)
  {
    paramMap = (List)paramMap.get(paramString);
    if ((paramMap != null) && (!paramMap.isEmpty()))
    {
      paramMap = (String)paramMap.get(0);
      try
      {
        float f = Float.parseFloat(paramMap);
        return (f * 1000.0F);
      }
      catch (NumberFormatException localNumberFormatException)
      {
        da.w("Could not parse float from " + paramString + " header: " + paramMap);
      }
    }
    return -1L;
  }
  
  private static List<String> b(Map<String, List<String>> paramMap, String paramString)
  {
    paramMap = (List)paramMap.get(paramString);
    if ((paramMap != null) && (!paramMap.isEmpty()))
    {
      paramMap = (String)paramMap.get(0);
      if (paramMap != null) {
        return Arrays.asList(paramMap.trim().split("\\s+"));
      }
    }
    return null;
  }
  
  private void e(Map<String, List<String>> paramMap)
  {
    paramMap = (List)paramMap.get("X-Afma-Ad-Size");
    if ((paramMap != null) && (!paramMap.isEmpty())) {
      this.oC = ((String)paramMap.get(0));
    }
  }
  
  private void f(Map<String, List<String>> paramMap)
  {
    paramMap = b(paramMap, "X-Afma-Click-Tracking-Urls");
    if (paramMap != null) {
      this.oF = paramMap;
    }
  }
  
  private void g(Map<String, List<String>> paramMap)
  {
    paramMap = b(paramMap, "X-Afma-Tracking-Urls");
    if (paramMap != null) {
      this.oG = paramMap;
    }
  }
  
  private void h(Map<String, List<String>> paramMap)
  {
    long l = a(paramMap, "X-Afma-Interstitial-Timeout");
    if (l != -1L) {
      this.oH = l;
    }
  }
  
  private void i(Map<String, List<String>> paramMap)
  {
    paramMap = (List)paramMap.get("X-Afma-Mediation");
    if ((paramMap != null) && (!paramMap.isEmpty())) {
      this.oI = Boolean.valueOf((String)paramMap.get(0)).booleanValue();
    }
  }
  
  private void j(Map<String, List<String>> paramMap)
  {
    paramMap = b(paramMap, "X-Afma-Manual-Tracking-Urls");
    if (paramMap != null) {
      this.oK = paramMap;
    }
  }
  
  private void k(Map<String, List<String>> paramMap)
  {
    long l = a(paramMap, "X-Afma-Refresh-Rate");
    if (l != -1L) {
      this.oL = l;
    }
  }
  
  private void l(Map<String, List<String>> paramMap)
  {
    paramMap = (List)paramMap.get("X-Afma-Orientation");
    if ((paramMap != null) && (!paramMap.isEmpty()))
    {
      paramMap = (String)paramMap.get(0);
      if (!"portrait".equalsIgnoreCase(paramMap)) {
        break label53;
      }
      this.mOrientation = cv.aU();
    }
    label53:
    while (!"landscape".equalsIgnoreCase(paramMap)) {
      return;
    }
    this.mOrientation = cv.aT();
  }
  
  public void a(String paramString1, Map<String, List<String>> paramMap, String paramString2)
  {
    this.oD = paramString1;
    this.oE = paramString2;
    d(paramMap);
  }
  
  public void d(Map<String, List<String>> paramMap)
  {
    e(paramMap);
    f(paramMap);
    g(paramMap);
    h(paramMap);
    i(paramMap);
    j(paramMap);
    k(paramMap);
    l(paramMap);
  }
  
  public cf f(long paramLong)
  {
    return new cf(this.oD, this.oE, this.oF, this.oG, this.oH, this.oI, -1L, this.oK, this.oL, this.mOrientation, this.oC, paramLong);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\cl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */