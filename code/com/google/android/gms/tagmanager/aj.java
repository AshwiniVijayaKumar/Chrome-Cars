package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d.a;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class aj
{
  private final Set<String> UW;
  private final String UX;
  
  public aj(String paramString, String... paramVarArgs)
  {
    this.UX = paramString;
    this.UW = new HashSet(paramVarArgs.length);
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      paramString = paramVarArgs[i];
      this.UW.add(paramString);
      i += 1;
    }
  }
  
  boolean a(Set<String> paramSet)
  {
    return paramSet.containsAll(this.UW);
  }
  
  public abstract boolean iy();
  
  public String jc()
  {
    return this.UX;
  }
  
  public Set<String> jd()
  {
    return this.UW;
  }
  
  public abstract d.a u(Map<String, d.a> paramMap);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\aj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */