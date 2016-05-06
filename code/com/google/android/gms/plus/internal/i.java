package com.google.android.gms.plus.internal;

import android.content.Context;
import java.util.ArrayList;
import java.util.Arrays;

public class i
{
  private String[] Ro;
  private String Rp;
  private String Rq;
  private String Rr;
  private PlusCommonExtras Rt;
  private final ArrayList<String> Ru = new ArrayList();
  private String[] Rv;
  private String vi;
  
  public i(Context paramContext)
  {
    this.Rq = paramContext.getPackageName();
    this.Rp = paramContext.getPackageName();
    this.Rt = new PlusCommonExtras();
    this.Ru.add("https://www.googleapis.com/auth/plus.login");
  }
  
  public i aS(String paramString)
  {
    this.vi = paramString;
    return this;
  }
  
  public i e(String... paramVarArgs)
  {
    this.Ru.clear();
    this.Ru.addAll(Arrays.asList(paramVarArgs));
    return this;
  }
  
  public i f(String... paramVarArgs)
  {
    this.Rv = paramVarArgs;
    return this;
  }
  
  public h hA()
  {
    if (this.vi == null) {
      this.vi = "<<default account>>";
    }
    String[] arrayOfString = (String[])this.Ru.toArray(new String[this.Ru.size()]);
    return new h(this.vi, arrayOfString, this.Rv, this.Ro, this.Rp, this.Rq, this.Rr, this.Rt);
  }
  
  public i hz()
  {
    this.Ru.clear();
    return this;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\plus\internal\i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */