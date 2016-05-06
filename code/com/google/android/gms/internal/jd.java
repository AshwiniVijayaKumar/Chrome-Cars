package com.google.android.gms.internal;

import java.io.IOException;
import java.util.List;

public abstract interface jd
{
  public static final class a
    extends ka<a>
  {
    public long Yb;
    public c.j Yc;
    public c.f fV;
    
    public a()
    {
      kw();
    }
    
    public static a l(byte[] paramArrayOfByte)
      throws kd
    {
      return (a)ke.a(new a(), paramArrayOfByte);
    }
    
    public void a(jz paramjz)
      throws IOException
    {
      paramjz.b(1, this.Yb);
      if (this.fV != null) {
        paramjz.a(2, this.fV);
      }
      if (this.Yc != null) {
        paramjz.a(3, this.Yc);
      }
      super.a(paramjz);
    }
    
    public int c()
    {
      int j = super.c() + jz.d(1, this.Yb);
      int i = j;
      if (this.fV != null) {
        i = j + jz.b(2, this.fV);
      }
      j = i;
      if (this.Yc != null) {
        j = i + jz.b(3, this.Yc);
      }
      this.DY = j;
      return j;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (paramObject == this) {
        bool1 = true;
      }
      label55:
      do
      {
        do
        {
          do
          {
            do
            {
              return bool1;
              bool1 = bool2;
            } while (!(paramObject instanceof a));
            paramObject = (a)paramObject;
            bool1 = bool2;
          } while (this.Yb != ((a)paramObject).Yb);
          if (this.fV != null) {
            break;
          }
          bool1 = bool2;
        } while (((a)paramObject).fV != null);
        if (this.Yc != null) {
          break label129;
        }
        bool1 = bool2;
      } while (((a)paramObject).Yc != null);
      for (;;)
      {
        if ((this.aae == null) || (this.aae.isEmpty()))
        {
          if (((a)paramObject).aae != null)
          {
            bool1 = bool2;
            if (!((a)paramObject).aae.isEmpty()) {
              break;
            }
          }
          return true;
          if (this.fV.equals(((a)paramObject).fV)) {
            break label55;
          }
          return false;
          label129:
          if (!this.Yc.equals(((a)paramObject).Yc)) {
            return false;
          }
        }
      }
      return this.aae.equals(((a)paramObject).aae);
    }
    
    public int hashCode()
    {
      int m = 0;
      int n = (int)(this.Yb ^ this.Yb >>> 32);
      int i;
      int j;
      if (this.fV == null)
      {
        i = 0;
        if (this.Yc != null) {
          break label94;
        }
        j = 0;
        label36:
        k = m;
        if (this.aae != null) {
          if (!this.aae.isEmpty()) {
            break label105;
          }
        }
      }
      label94:
      label105:
      for (int k = m;; k = this.aae.hashCode())
      {
        return (j + (i + (n + 527) * 31) * 31) * 31 + k;
        i = this.fV.hashCode();
        break;
        j = this.Yc.hashCode();
        break label36;
      }
    }
    
    public a kw()
    {
      this.Yb = 0L;
      this.fV = null;
      this.Yc = null;
      this.aae = null;
      this.DY = -1;
      return this;
    }
    
    public a n(jy paramjy)
      throws IOException
    {
      for (;;)
      {
        int i = paramjy.ky();
        switch (i)
        {
        default: 
          if (a(paramjy, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          this.Yb = paramjy.kA();
          break;
        case 18: 
          if (this.fV == null) {
            this.fV = new c.f();
          }
          paramjy.a(this.fV);
          break;
        case 26: 
          if (this.Yc == null) {
            this.Yc = new c.j();
          }
          paramjy.a(this.Yc);
        }
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\jd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */