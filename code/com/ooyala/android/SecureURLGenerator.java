package com.ooyala.android;

import java.net.URL;
import java.util.Map;

public abstract interface SecureURLGenerator
{
  public abstract URL secureURL(String paramString1, String paramString2, Map<String, String> paramMap);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\SecureURLGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */