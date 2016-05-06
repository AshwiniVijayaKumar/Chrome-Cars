package com.google.android.gms.internal;

import java.util.Map;

public final class as
  implements ar
{
  private static boolean a(Map<String, String> paramMap)
  {
    return "1".equals(paramMap.get("custom_close"));
  }
  
  private static int b(Map<String, String> paramMap)
  {
    paramMap = (String)paramMap.get("o");
    if (paramMap != null)
    {
      if ("p".equalsIgnoreCase(paramMap)) {
        return cv.aU();
      }
      if ("l".equalsIgnoreCase(paramMap)) {
        return cv.aT();
      }
    }
    return -1;
  }
  
  public void a(dd paramdd, Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("a");
    if (str == null)
    {
      da.w("Action missing from an open GMSG.");
      return;
    }
    de localde = paramdd.bb();
    if ("expand".equalsIgnoreCase(str))
    {
      if (paramdd.be())
      {
        da.w("Cannot expand WebView that is already expanded.");
        return;
      }
      localde.a(a(paramMap), b(paramMap));
      return;
    }
    if ("webapp".equalsIgnoreCase(str))
    {
      paramdd = (String)paramMap.get("u");
      if (paramdd != null)
      {
        localde.a(a(paramMap), b(paramMap), paramdd);
        return;
      }
      localde.a(a(paramMap), b(paramMap), (String)paramMap.get("html"), (String)paramMap.get("baseurl"));
      return;
    }
    localde.a(new bn((String)paramMap.get("i"), (String)paramMap.get("u"), (String)paramMap.get("m"), (String)paramMap.get("p"), (String)paramMap.get("c"), (String)paramMap.get("f"), (String)paramMap.get("e")));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\as.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */