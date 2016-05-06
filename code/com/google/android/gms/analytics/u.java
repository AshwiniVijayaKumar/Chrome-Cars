package com.google.android.gms.analytics;

import java.util.SortedSet;
import java.util.TreeSet;

class u
{
  private static final u sk = new u();
  private SortedSet<a> sh = new TreeSet();
  private StringBuilder si = new StringBuilder();
  private boolean sj = false;
  
  public static u bR()
  {
    return sk;
  }
  
  public void a(a parama)
  {
    try
    {
      if (!this.sj)
      {
        this.sh.add(parama);
        this.si.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(parama.ordinal()));
      }
      return;
    }
    finally
    {
      parama = finally;
      throw parama;
    }
  }
  
  public String bS()
  {
    try
    {
      Object localObject1 = new StringBuilder();
      int j = 6;
      int i = 0;
      while (this.sh.size() > 0)
      {
        a locala = (a)this.sh.first();
        this.sh.remove(locala);
        int k = locala.ordinal();
        while (k >= j)
        {
          ((StringBuilder)localObject1).append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(i));
          j += 6;
          i = 0;
        }
        i += (1 << locala.ordinal() % 6);
      }
      if ((i > 0) || (((StringBuilder)localObject1).length() == 0)) {
        ((StringBuilder)localObject1).append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".charAt(i));
      }
      this.sh.clear();
      localObject1 = ((StringBuilder)localObject1).toString();
      return (String)localObject1;
    }
    finally {}
  }
  
  public String bT()
  {
    try
    {
      if (this.si.length() > 0) {
        this.si.insert(0, ".");
      }
      String str = this.si.toString();
      this.si = new StringBuilder();
      return str;
    }
    finally {}
  }
  
  public void r(boolean paramBoolean)
  {
    try
    {
      this.sj = paramBoolean;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static enum a
  {
    private a() {}
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\analytics\u.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */