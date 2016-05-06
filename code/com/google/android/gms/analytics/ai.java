package com.google.android.gms.analytics;

import android.content.Context;
import java.util.Map;

class ai
  extends k<aj>
{
  public ai(Context paramContext)
  {
    super(paramContext, new a());
  }
  
  private static class a
    implements k.a<aj>
  {
    private final aj uJ = new aj();
    
    public void a(String paramString, int paramInt)
    {
      if ("ga_sessionTimeout".equals(paramString))
      {
        this.uJ.uM = paramInt;
        return;
      }
      aa.w("int configuration name not recognized:  " + paramString);
    }
    
    public void a(String paramString1, String paramString2)
    {
      this.uJ.uQ.put(paramString1, paramString2);
    }
    
    public void b(String paramString1, String paramString2)
    {
      if ("ga_trackingId".equals(paramString1))
      {
        this.uJ.uK = paramString2;
        return;
      }
      if ("ga_sampleFrequency".equals(paramString1)) {
        try
        {
          this.uJ.uL = Double.parseDouble(paramString2);
          return;
        }
        catch (NumberFormatException paramString1)
        {
          aa.t("Error parsing ga_sampleFrequency value: " + paramString2);
          return;
        }
      }
      aa.w("string configuration name not recognized:  " + paramString1);
    }
    
    public void c(String paramString, boolean paramBoolean)
    {
      int j = 1;
      int k = 1;
      int i = 1;
      if ("ga_autoActivityTracking".equals(paramString))
      {
        paramString = this.uJ;
        if (paramBoolean) {}
        for (;;)
        {
          paramString.uN = i;
          return;
          i = 0;
        }
      }
      if ("ga_anonymizeIp".equals(paramString))
      {
        paramString = this.uJ;
        if (paramBoolean) {}
        for (i = j;; i = 0)
        {
          paramString.uO = i;
          return;
        }
      }
      if ("ga_reportUncaughtExceptions".equals(paramString))
      {
        paramString = this.uJ;
        if (paramBoolean) {}
        for (i = k;; i = 0)
        {
          paramString.uP = i;
          return;
        }
      }
      aa.w("bool configuration name not recognized:  " + paramString);
    }
    
    public aj cA()
    {
      return this.uJ;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\analytics\ai.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */