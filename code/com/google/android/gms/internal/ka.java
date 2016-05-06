package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class ka<M extends ka<M>>
  extends ke
{
  protected List<kg> aae;
  
  public final <T> T a(kb<M, T> paramkb)
  {
    return (T)paramkb.g(this.aae);
  }
  
  public void a(jz paramjz)
    throws IOException
  {
    if (this.aae == null) {}
    for (int i = 0;; i = this.aae.size())
    {
      int j = 0;
      while (j < i)
      {
        kg localkg = (kg)this.aae.get(j);
        paramjz.cF(localkg.tag);
        paramjz.p(localkg.aai);
        j += 1;
      }
    }
  }
  
  protected final boolean a(jy paramjy, int paramInt)
    throws IOException
  {
    int i = paramjy.getPosition();
    if (!paramjy.cv(paramInt)) {
      return false;
    }
    if (this.aae == null) {
      this.aae = new ArrayList();
    }
    paramjy = paramjy.e(i, paramjy.getPosition() - i);
    this.aae.add(new kg(paramInt, paramjy));
    return true;
  }
  
  public int c()
  {
    if (this.aae == null) {}
    int k;
    for (int i = 0;; i = this.aae.size())
    {
      int j = 0;
      k = 0;
      while (j < i)
      {
        kg localkg = (kg)this.aae.get(j);
        k = k + jz.cG(localkg.tag) + localkg.aai.length;
        j += 1;
      }
    }
    this.DY = k;
    return k;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ka.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */