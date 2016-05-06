package com.google.android.gms.internal;

import java.io.IOException;
import java.util.List;

public abstract interface c
{
  public static final class a
    extends ka<a>
  {
    public int eP;
    public int eQ;
    public int level;
    
    public a()
    {
      b();
    }
    
    public a a(jy paramjy)
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
          i = paramjy.kB();
          switch (i)
          {
          default: 
            break;
          case 1: 
          case 2: 
          case 3: 
            this.level = i;
          }
          break;
        case 16: 
          this.eP = paramjy.kB();
          break;
        case 24: 
          this.eQ = paramjy.kB();
        }
      }
    }
    
    public void a(jz paramjz)
      throws IOException
    {
      if (this.level != 1) {
        paramjz.f(1, this.level);
      }
      if (this.eP != 0) {
        paramjz.f(2, this.eP);
      }
      if (this.eQ != 0) {
        paramjz.f(3, this.eQ);
      }
      super.a(paramjz);
    }
    
    public a b()
    {
      this.level = 1;
      this.eP = 0;
      this.eQ = 0;
      this.aae = null;
      this.DY = -1;
      return this;
    }
    
    public int c()
    {
      int j = super.c();
      int i = j;
      if (this.level != 1) {
        i = j + jz.g(1, this.level);
      }
      j = i;
      if (this.eP != 0) {
        j = i + jz.g(2, this.eP);
      }
      i = j;
      if (this.eQ != 0) {
        i = j + jz.g(3, this.eQ);
      }
      this.DY = i;
      return i;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (paramObject == this) {
        bool1 = true;
      }
      do
      {
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
            } while (this.level != ((a)paramObject).level);
            bool1 = bool2;
          } while (this.eP != ((a)paramObject).eP);
          bool1 = bool2;
        } while (this.eQ != ((a)paramObject).eQ);
        if ((this.aae != null) && (!this.aae.isEmpty())) {
          break label106;
        }
        if (((a)paramObject).aae == null) {
          break;
        }
        bool1 = bool2;
      } while (!((a)paramObject).aae.isEmpty());
      return true;
      label106:
      return this.aae.equals(((a)paramObject).aae);
    }
    
    public int hashCode()
    {
      int j = this.level;
      int k = this.eP;
      int m = this.eQ;
      if ((this.aae == null) || (this.aae.isEmpty())) {}
      for (int i = 0;; i = this.aae.hashCode()) {
        return i + (((j + 527) * 31 + k) * 31 + m) * 31;
      }
    }
  }
  
  public static final class b
    extends ka<b>
  {
    private static volatile b[] eR;
    public int[] eS;
    public int eT;
    public boolean eU;
    public boolean eV;
    public int name;
    
    public b()
    {
      e();
    }
    
    public static b[] d()
    {
      if (eR == null) {}
      synchronized (kc.aah)
      {
        if (eR == null) {
          eR = new b[0];
        }
        return eR;
      }
    }
    
    public void a(jz paramjz)
      throws IOException
    {
      if (this.eV) {
        paramjz.a(1, this.eV);
      }
      paramjz.f(2, this.eT);
      if ((this.eS != null) && (this.eS.length > 0))
      {
        int i = 0;
        while (i < this.eS.length)
        {
          paramjz.f(3, this.eS[i]);
          i += 1;
        }
      }
      if (this.name != 0) {
        paramjz.f(4, this.name);
      }
      if (this.eU) {
        paramjz.a(6, this.eU);
      }
      super.a(paramjz);
    }
    
    public int c()
    {
      int j = 0;
      int k = super.c();
      int i = k;
      if (this.eV) {
        i = k + jz.b(1, this.eV);
      }
      k = jz.g(2, this.eT) + i;
      if ((this.eS != null) && (this.eS.length > 0))
      {
        i = 0;
        while (i < this.eS.length)
        {
          j += jz.cC(this.eS[i]);
          i += 1;
        }
      }
      for (j = k + j + this.eS.length * 1;; j = k)
      {
        i = j;
        if (this.name != 0) {
          i = j + jz.g(4, this.name);
        }
        j = i;
        if (this.eU) {
          j = i + jz.b(6, this.eU);
        }
        this.DY = j;
        return j;
      }
    }
    
    public b c(jy paramjy)
      throws IOException
    {
      for (;;)
      {
        int i = paramjy.ky();
        int j;
        int[] arrayOfInt;
        switch (i)
        {
        default: 
          if (a(paramjy, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          this.eV = paramjy.kC();
          break;
        case 16: 
          this.eT = paramjy.kB();
          break;
        case 24: 
          j = kh.c(paramjy, 24);
          if (this.eS == null) {}
          for (i = 0;; i = this.eS.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.eS, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramjy.kB();
              paramjy.ky();
              j += 1;
            }
          }
          arrayOfInt[j] = paramjy.kB();
          this.eS = arrayOfInt;
          break;
        case 26: 
          int k = paramjy.cw(paramjy.kE());
          i = paramjy.getPosition();
          j = 0;
          while (paramjy.kJ() > 0)
          {
            paramjy.kB();
            j += 1;
          }
          paramjy.cy(i);
          if (this.eS == null) {}
          for (i = 0;; i = this.eS.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.eS, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramjy.kB();
              j += 1;
            }
          }
          this.eS = arrayOfInt;
          paramjy.cx(k);
          break;
        case 32: 
          this.name = paramjy.kB();
          break;
        case 48: 
          this.eU = paramjy.kC();
        }
      }
    }
    
    public b e()
    {
      this.eS = kh.aaj;
      this.eT = 0;
      this.name = 0;
      this.eU = false;
      this.eV = false;
      this.aae = null;
      this.DY = -1;
      return this;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (paramObject == this) {
        bool1 = true;
      }
      do
      {
        do
        {
          do
          {
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
                  } while (!(paramObject instanceof b));
                  paramObject = (b)paramObject;
                  bool1 = bool2;
                } while (!kc.equals(this.eS, ((b)paramObject).eS));
                bool1 = bool2;
              } while (this.eT != ((b)paramObject).eT);
              bool1 = bool2;
            } while (this.name != ((b)paramObject).name);
            bool1 = bool2;
          } while (this.eU != ((b)paramObject).eU);
          bool1 = bool2;
        } while (this.eV != ((b)paramObject).eV);
        if ((this.aae != null) && (!this.aae.isEmpty())) {
          break label135;
        }
        if (((b)paramObject).aae == null) {
          break;
        }
        bool1 = bool2;
      } while (!((b)paramObject).aae.isEmpty());
      return true;
      label135:
      return this.aae.equals(((b)paramObject).aae);
    }
    
    public int hashCode()
    {
      int j = 1231;
      int m = kc.hashCode(this.eS);
      int n = this.eT;
      int i1 = this.name;
      int i;
      if (this.eU)
      {
        i = 1231;
        if (!this.eV) {
          break label105;
        }
        label43:
        if ((this.aae != null) && (!this.aae.isEmpty())) {
          break label112;
        }
      }
      label105:
      label112:
      for (int k = 0;; k = this.aae.hashCode())
      {
        return k + ((i + (((m + 527) * 31 + n) * 31 + i1) * 31) * 31 + j) * 31;
        i = 1237;
        break;
        j = 1237;
        break label43;
      }
    }
  }
  
  public static final class c
    extends ka<c>
  {
    private static volatile c[] eW;
    public String eX;
    public long eY;
    public long eZ;
    public boolean fa;
    public long fb;
    
    public c()
    {
      g();
    }
    
    public static c[] f()
    {
      if (eW == null) {}
      synchronized (kc.aah)
      {
        if (eW == null) {
          eW = new c[0];
        }
        return eW;
      }
    }
    
    public void a(jz paramjz)
      throws IOException
    {
      if (!this.eX.equals("")) {
        paramjz.b(1, this.eX);
      }
      if (this.eY != 0L) {
        paramjz.b(2, this.eY);
      }
      if (this.eZ != 2147483647L) {
        paramjz.b(3, this.eZ);
      }
      if (this.fa) {
        paramjz.a(4, this.fa);
      }
      if (this.fb != 0L) {
        paramjz.b(5, this.fb);
      }
      super.a(paramjz);
    }
    
    public int c()
    {
      int j = super.c();
      int i = j;
      if (!this.eX.equals("")) {
        i = j + jz.g(1, this.eX);
      }
      j = i;
      if (this.eY != 0L) {
        j = i + jz.d(2, this.eY);
      }
      i = j;
      if (this.eZ != 2147483647L) {
        i = j + jz.d(3, this.eZ);
      }
      j = i;
      if (this.fa) {
        j = i + jz.b(4, this.fa);
      }
      i = j;
      if (this.fb != 0L) {
        i = j + jz.d(5, this.fb);
      }
      this.DY = i;
      return i;
    }
    
    public c d(jy paramjy)
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
        case 10: 
          this.eX = paramjy.readString();
          break;
        case 16: 
          this.eY = paramjy.kA();
          break;
        case 24: 
          this.eZ = paramjy.kA();
          break;
        case 32: 
          this.fa = paramjy.kC();
          break;
        case 40: 
          this.fb = paramjy.kA();
        }
      }
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (paramObject == this) {
        bool1 = true;
      }
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (!(paramObject instanceof c));
        paramObject = (c)paramObject;
        if (this.eX != null) {
          break;
        }
        bool1 = bool2;
      } while (((c)paramObject).eX != null);
      while (this.eX.equals(((c)paramObject).eX))
      {
        bool1 = bool2;
        if (this.eY != ((c)paramObject).eY) {
          break;
        }
        bool1 = bool2;
        if (this.eZ != ((c)paramObject).eZ) {
          break;
        }
        bool1 = bool2;
        if (this.fa != ((c)paramObject).fa) {
          break;
        }
        bool1 = bool2;
        if (this.fb != ((c)paramObject).fb) {
          break;
        }
        if ((this.aae != null) && (!this.aae.isEmpty())) {
          break label154;
        }
        if (((c)paramObject).aae != null)
        {
          bool1 = bool2;
          if (!((c)paramObject).aae.isEmpty()) {
            break;
          }
        }
        return true;
      }
      return false;
      label154:
      return this.aae.equals(((c)paramObject).aae);
    }
    
    public c g()
    {
      this.eX = "";
      this.eY = 0L;
      this.eZ = 2147483647L;
      this.fa = false;
      this.fb = 0L;
      this.aae = null;
      this.DY = -1;
      return this;
    }
    
    public int hashCode()
    {
      int m = 0;
      int i;
      int n;
      int i1;
      int j;
      label53:
      int i2;
      if (this.eX == null)
      {
        i = 0;
        n = (int)(this.eY ^ this.eY >>> 32);
        i1 = (int)(this.eZ ^ this.eZ >>> 32);
        if (!this.fa) {
          break label138;
        }
        j = 1231;
        i2 = (int)(this.fb ^ this.fb >>> 32);
        k = m;
        if (this.aae != null) {
          if (!this.aae.isEmpty()) {
            break label145;
          }
        }
      }
      label138:
      label145:
      for (int k = m;; k = this.aae.hashCode())
      {
        return ((j + (((i + 527) * 31 + n) * 31 + i1) * 31) * 31 + i2) * 31 + k;
        i = this.eX.hashCode();
        break;
        j = 1237;
        break label53;
      }
    }
  }
  
  public static final class d
    extends ka<d>
  {
    public d.a[] fc;
    public d.a[] fd;
    public c.c[] fe;
    
    public d()
    {
      h();
    }
    
    public void a(jz paramjz)
      throws IOException
    {
      int j = 0;
      int i;
      Object localObject;
      if ((this.fc != null) && (this.fc.length > 0))
      {
        i = 0;
        while (i < this.fc.length)
        {
          localObject = this.fc[i];
          if (localObject != null) {
            paramjz.a(1, (ke)localObject);
          }
          i += 1;
        }
      }
      if ((this.fd != null) && (this.fd.length > 0))
      {
        i = 0;
        while (i < this.fd.length)
        {
          localObject = this.fd[i];
          if (localObject != null) {
            paramjz.a(2, (ke)localObject);
          }
          i += 1;
        }
      }
      if ((this.fe != null) && (this.fe.length > 0))
      {
        i = j;
        while (i < this.fe.length)
        {
          localObject = this.fe[i];
          if (localObject != null) {
            paramjz.a(3, (ke)localObject);
          }
          i += 1;
        }
      }
      super.a(paramjz);
    }
    
    public int c()
    {
      int m = 0;
      int i = super.c();
      int j = i;
      Object localObject;
      if (this.fc != null)
      {
        j = i;
        if (this.fc.length > 0)
        {
          j = 0;
          while (j < this.fc.length)
          {
            localObject = this.fc[j];
            k = i;
            if (localObject != null) {
              k = i + jz.b(1, (ke)localObject);
            }
            j += 1;
            i = k;
          }
          j = i;
        }
      }
      i = j;
      if (this.fd != null)
      {
        i = j;
        if (this.fd.length > 0)
        {
          i = j;
          j = 0;
          while (j < this.fd.length)
          {
            localObject = this.fd[j];
            k = i;
            if (localObject != null) {
              k = i + jz.b(2, (ke)localObject);
            }
            j += 1;
            i = k;
          }
        }
      }
      int k = i;
      if (this.fe != null)
      {
        k = i;
        if (this.fe.length > 0)
        {
          j = m;
          for (;;)
          {
            k = i;
            if (j >= this.fe.length) {
              break;
            }
            localObject = this.fe[j];
            k = i;
            if (localObject != null) {
              k = i + jz.b(3, (ke)localObject);
            }
            j += 1;
            i = k;
          }
        }
      }
      this.DY = k;
      return k;
    }
    
    public d e(jy paramjy)
      throws IOException
    {
      for (;;)
      {
        int i = paramjy.ky();
        int j;
        Object localObject;
        switch (i)
        {
        default: 
          if (a(paramjy, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          j = kh.c(paramjy, 10);
          if (this.fc == null) {}
          for (i = 0;; i = this.fc.length)
          {
            localObject = new d.a[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fc, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new d.a();
              paramjy.a(localObject[j]);
              paramjy.ky();
              j += 1;
            }
          }
          localObject[j] = new d.a();
          paramjy.a(localObject[j]);
          this.fc = ((d.a[])localObject);
          break;
        case 18: 
          j = kh.c(paramjy, 18);
          if (this.fd == null) {}
          for (i = 0;; i = this.fd.length)
          {
            localObject = new d.a[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fd, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new d.a();
              paramjy.a(localObject[j]);
              paramjy.ky();
              j += 1;
            }
          }
          localObject[j] = new d.a();
          paramjy.a(localObject[j]);
          this.fd = ((d.a[])localObject);
          break;
        case 26: 
          j = kh.c(paramjy, 26);
          if (this.fe == null) {}
          for (i = 0;; i = this.fe.length)
          {
            localObject = new c.c[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fe, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new c.c();
              paramjy.a(localObject[j]);
              paramjy.ky();
              j += 1;
            }
          }
          localObject[j] = new c.c();
          paramjy.a(localObject[j]);
          this.fe = ((c.c[])localObject);
        }
      }
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (paramObject == this) {
        bool1 = true;
      }
      do
      {
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
              } while (!(paramObject instanceof d));
              paramObject = (d)paramObject;
              bool1 = bool2;
            } while (!kc.equals(this.fc, ((d)paramObject).fc));
            bool1 = bool2;
          } while (!kc.equals(this.fd, ((d)paramObject).fd));
          bool1 = bool2;
        } while (!kc.equals(this.fe, ((d)paramObject).fe));
        if ((this.aae != null) && (!this.aae.isEmpty())) {
          break label115;
        }
        if (((d)paramObject).aae == null) {
          break;
        }
        bool1 = bool2;
      } while (!((d)paramObject).aae.isEmpty());
      return true;
      label115:
      return this.aae.equals(((d)paramObject).aae);
    }
    
    public d h()
    {
      this.fc = d.a.r();
      this.fd = d.a.r();
      this.fe = c.c.f();
      this.aae = null;
      this.DY = -1;
      return this;
    }
    
    public int hashCode()
    {
      int j = kc.hashCode(this.fc);
      int k = kc.hashCode(this.fd);
      int m = kc.hashCode(this.fe);
      if ((this.aae == null) || (this.aae.isEmpty())) {}
      for (int i = 0;; i = this.aae.hashCode()) {
        return i + (((j + 527) * 31 + k) * 31 + m) * 31;
      }
    }
  }
  
  public static final class e
    extends ka<e>
  {
    private static volatile e[] ff;
    public int key;
    public int value;
    
    public e()
    {
      j();
    }
    
    public static e[] i()
    {
      if (ff == null) {}
      synchronized (kc.aah)
      {
        if (ff == null) {
          ff = new e[0];
        }
        return ff;
      }
    }
    
    public void a(jz paramjz)
      throws IOException
    {
      paramjz.f(1, this.key);
      paramjz.f(2, this.value);
      super.a(paramjz);
    }
    
    public int c()
    {
      int i = super.c() + jz.g(1, this.key) + jz.g(2, this.value);
      this.DY = i;
      return i;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (paramObject == this) {
        bool1 = true;
      }
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
            } while (!(paramObject instanceof e));
            paramObject = (e)paramObject;
            bool1 = bool2;
          } while (this.key != ((e)paramObject).key);
          bool1 = bool2;
        } while (this.value != ((e)paramObject).value);
        if ((this.aae != null) && (!this.aae.isEmpty())) {
          break label93;
        }
        if (((e)paramObject).aae == null) {
          break;
        }
        bool1 = bool2;
      } while (!((e)paramObject).aae.isEmpty());
      return true;
      label93:
      return this.aae.equals(((e)paramObject).aae);
    }
    
    public e f(jy paramjy)
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
          this.key = paramjy.kB();
          break;
        case 16: 
          this.value = paramjy.kB();
        }
      }
    }
    
    public int hashCode()
    {
      int j = this.key;
      int k = this.value;
      if ((this.aae == null) || (this.aae.isEmpty())) {}
      for (int i = 0;; i = this.aae.hashCode()) {
        return i + ((j + 527) * 31 + k) * 31;
      }
    }
    
    public e j()
    {
      this.key = 0;
      this.value = 0;
      this.aae = null;
      this.DY = -1;
      return this;
    }
  }
  
  public static final class f
    extends ka<f>
  {
    public String[] fg;
    public String[] fh;
    public d.a[] fi;
    public c.e[] fj;
    public c.b[] fk;
    public c.b[] fl;
    public c.b[] fm;
    public c.g[] fn;
    public String fo;
    public String fp;
    public String fq;
    public String fr;
    public c.a fs;
    public float ft;
    public boolean fu;
    public String[] fv;
    public int fw;
    
    public f()
    {
      k();
    }
    
    public static f a(byte[] paramArrayOfByte)
      throws kd
    {
      return (f)ke.a(new f(), paramArrayOfByte);
    }
    
    public void a(jz paramjz)
      throws IOException
    {
      int j = 0;
      int i;
      Object localObject;
      if ((this.fh != null) && (this.fh.length > 0))
      {
        i = 0;
        while (i < this.fh.length)
        {
          localObject = this.fh[i];
          if (localObject != null) {
            paramjz.b(1, (String)localObject);
          }
          i += 1;
        }
      }
      if ((this.fi != null) && (this.fi.length > 0))
      {
        i = 0;
        while (i < this.fi.length)
        {
          localObject = this.fi[i];
          if (localObject != null) {
            paramjz.a(2, (ke)localObject);
          }
          i += 1;
        }
      }
      if ((this.fj != null) && (this.fj.length > 0))
      {
        i = 0;
        while (i < this.fj.length)
        {
          localObject = this.fj[i];
          if (localObject != null) {
            paramjz.a(3, (ke)localObject);
          }
          i += 1;
        }
      }
      if ((this.fk != null) && (this.fk.length > 0))
      {
        i = 0;
        while (i < this.fk.length)
        {
          localObject = this.fk[i];
          if (localObject != null) {
            paramjz.a(4, (ke)localObject);
          }
          i += 1;
        }
      }
      if ((this.fl != null) && (this.fl.length > 0))
      {
        i = 0;
        while (i < this.fl.length)
        {
          localObject = this.fl[i];
          if (localObject != null) {
            paramjz.a(5, (ke)localObject);
          }
          i += 1;
        }
      }
      if ((this.fm != null) && (this.fm.length > 0))
      {
        i = 0;
        while (i < this.fm.length)
        {
          localObject = this.fm[i];
          if (localObject != null) {
            paramjz.a(6, (ke)localObject);
          }
          i += 1;
        }
      }
      if ((this.fn != null) && (this.fn.length > 0))
      {
        i = 0;
        while (i < this.fn.length)
        {
          localObject = this.fn[i];
          if (localObject != null) {
            paramjz.a(7, (ke)localObject);
          }
          i += 1;
        }
      }
      if (!this.fo.equals("")) {
        paramjz.b(9, this.fo);
      }
      if (!this.fp.equals("")) {
        paramjz.b(10, this.fp);
      }
      if (!this.fq.equals("0")) {
        paramjz.b(12, this.fq);
      }
      if (!this.fr.equals("")) {
        paramjz.b(13, this.fr);
      }
      if (this.fs != null) {
        paramjz.a(14, this.fs);
      }
      if (Float.floatToIntBits(this.ft) != Float.floatToIntBits(0.0F)) {
        paramjz.a(15, this.ft);
      }
      if ((this.fv != null) && (this.fv.length > 0))
      {
        i = 0;
        while (i < this.fv.length)
        {
          localObject = this.fv[i];
          if (localObject != null) {
            paramjz.b(16, (String)localObject);
          }
          i += 1;
        }
      }
      if (this.fw != 0) {
        paramjz.f(17, this.fw);
      }
      if (this.fu) {
        paramjz.a(18, this.fu);
      }
      if ((this.fg != null) && (this.fg.length > 0))
      {
        i = j;
        while (i < this.fg.length)
        {
          localObject = this.fg[i];
          if (localObject != null) {
            paramjz.b(19, (String)localObject);
          }
          i += 1;
        }
      }
      super.a(paramjz);
    }
    
    public int c()
    {
      int i2 = 0;
      int i1 = super.c();
      int i;
      int k;
      Object localObject;
      int n;
      int m;
      if ((this.fh != null) && (this.fh.length > 0))
      {
        i = 0;
        j = 0;
        for (k = 0; i < this.fh.length; k = m)
        {
          localObject = this.fh[i];
          n = j;
          m = k;
          if (localObject != null)
          {
            m = k + 1;
            n = j + jz.bQ((String)localObject);
          }
          i += 1;
          j = n;
        }
      }
      for (int j = i1 + j + k * 1;; j = i1)
      {
        i = j;
        if (this.fi != null)
        {
          i = j;
          if (this.fi.length > 0)
          {
            i = j;
            j = 0;
            while (j < this.fi.length)
            {
              localObject = this.fi[j];
              k = i;
              if (localObject != null) {
                k = i + jz.b(2, (ke)localObject);
              }
              j += 1;
              i = k;
            }
          }
        }
        j = i;
        if (this.fj != null)
        {
          j = i;
          if (this.fj.length > 0)
          {
            j = 0;
            while (j < this.fj.length)
            {
              localObject = this.fj[j];
              k = i;
              if (localObject != null) {
                k = i + jz.b(3, (ke)localObject);
              }
              j += 1;
              i = k;
            }
            j = i;
          }
        }
        i = j;
        if (this.fk != null)
        {
          i = j;
          if (this.fk.length > 0)
          {
            i = j;
            j = 0;
            while (j < this.fk.length)
            {
              localObject = this.fk[j];
              k = i;
              if (localObject != null) {
                k = i + jz.b(4, (ke)localObject);
              }
              j += 1;
              i = k;
            }
          }
        }
        j = i;
        if (this.fl != null)
        {
          j = i;
          if (this.fl.length > 0)
          {
            j = 0;
            while (j < this.fl.length)
            {
              localObject = this.fl[j];
              k = i;
              if (localObject != null) {
                k = i + jz.b(5, (ke)localObject);
              }
              j += 1;
              i = k;
            }
            j = i;
          }
        }
        i = j;
        if (this.fm != null)
        {
          i = j;
          if (this.fm.length > 0)
          {
            i = j;
            j = 0;
            while (j < this.fm.length)
            {
              localObject = this.fm[j];
              k = i;
              if (localObject != null) {
                k = i + jz.b(6, (ke)localObject);
              }
              j += 1;
              i = k;
            }
          }
        }
        j = i;
        if (this.fn != null)
        {
          j = i;
          if (this.fn.length > 0)
          {
            j = 0;
            while (j < this.fn.length)
            {
              localObject = this.fn[j];
              k = i;
              if (localObject != null) {
                k = i + jz.b(7, (ke)localObject);
              }
              j += 1;
              i = k;
            }
            j = i;
          }
        }
        i = j;
        if (!this.fo.equals("")) {
          i = j + jz.g(9, this.fo);
        }
        j = i;
        if (!this.fp.equals("")) {
          j = i + jz.g(10, this.fp);
        }
        i = j;
        if (!this.fq.equals("0")) {
          i = j + jz.g(12, this.fq);
        }
        j = i;
        if (!this.fr.equals("")) {
          j = i + jz.g(13, this.fr);
        }
        k = j;
        if (this.fs != null) {
          k = j + jz.b(14, this.fs);
        }
        i = k;
        if (Float.floatToIntBits(this.ft) != Float.floatToIntBits(0.0F)) {
          i = k + jz.b(15, this.ft);
        }
        j = i;
        if (this.fv != null)
        {
          j = i;
          if (this.fv.length > 0)
          {
            j = 0;
            k = 0;
            for (m = 0; j < this.fv.length; m = n)
            {
              localObject = this.fv[j];
              i1 = k;
              n = m;
              if (localObject != null)
              {
                n = m + 1;
                i1 = k + jz.bQ((String)localObject);
              }
              j += 1;
              k = i1;
            }
            j = i + k + m * 2;
          }
        }
        k = j;
        if (this.fw != 0) {
          k = j + jz.g(17, this.fw);
        }
        i = k;
        if (this.fu) {
          i = k + jz.b(18, this.fu);
        }
        j = i;
        if (this.fg != null)
        {
          j = i;
          if (this.fg.length > 0)
          {
            k = 0;
            m = 0;
            j = i2;
            while (j < this.fg.length)
            {
              localObject = this.fg[j];
              i1 = k;
              n = m;
              if (localObject != null)
              {
                n = m + 1;
                i1 = k + jz.bQ((String)localObject);
              }
              j += 1;
              k = i1;
              m = n;
            }
            j = i + k + m * 2;
          }
        }
        this.DY = j;
        return j;
      }
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (paramObject == this) {
        bool1 = true;
      }
      label169:
      label185:
      label201:
      label217:
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
                      do
                      {
                        do
                        {
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
                                } while (!(paramObject instanceof f));
                                paramObject = (f)paramObject;
                                bool1 = bool2;
                              } while (!kc.equals(this.fg, ((f)paramObject).fg));
                              bool1 = bool2;
                            } while (!kc.equals(this.fh, ((f)paramObject).fh));
                            bool1 = bool2;
                          } while (!kc.equals(this.fi, ((f)paramObject).fi));
                          bool1 = bool2;
                        } while (!kc.equals(this.fj, ((f)paramObject).fj));
                        bool1 = bool2;
                      } while (!kc.equals(this.fk, ((f)paramObject).fk));
                      bool1 = bool2;
                    } while (!kc.equals(this.fl, ((f)paramObject).fl));
                    bool1 = bool2;
                  } while (!kc.equals(this.fm, ((f)paramObject).fm));
                  bool1 = bool2;
                } while (!kc.equals(this.fn, ((f)paramObject).fn));
                if (this.fo != null) {
                  break;
                }
                bool1 = bool2;
              } while (((f)paramObject).fo != null);
              if (this.fp != null) {
                break label352;
              }
              bool1 = bool2;
            } while (((f)paramObject).fp != null);
            if (this.fq != null) {
              break label368;
            }
            bool1 = bool2;
          } while (((f)paramObject).fq != null);
          if (this.fr != null) {
            break label384;
          }
          bool1 = bool2;
        } while (((f)paramObject).fr != null);
        if (this.fs != null) {
          break label400;
        }
        bool1 = bool2;
      } while (((f)paramObject).fs != null);
      label352:
      label368:
      label384:
      label400:
      while (this.fs.equals(((f)paramObject).fs))
      {
        bool1 = bool2;
        if (Float.floatToIntBits(this.ft) != Float.floatToIntBits(((f)paramObject).ft)) {
          break;
        }
        bool1 = bool2;
        if (this.fu != ((f)paramObject).fu) {
          break;
        }
        bool1 = bool2;
        if (!kc.equals(this.fv, ((f)paramObject).fv)) {
          break;
        }
        bool1 = bool2;
        if (this.fw != ((f)paramObject).fw) {
          break;
        }
        if ((this.aae != null) && (!this.aae.isEmpty())) {
          break label416;
        }
        if (((f)paramObject).aae != null)
        {
          bool1 = bool2;
          if (!((f)paramObject).aae.isEmpty()) {
            break;
          }
        }
        return true;
        if (this.fo.equals(((f)paramObject).fo)) {
          break label169;
        }
        return false;
        if (this.fp.equals(((f)paramObject).fp)) {
          break label185;
        }
        return false;
        if (this.fq.equals(((f)paramObject).fq)) {
          break label201;
        }
        return false;
        if (this.fr.equals(((f)paramObject).fr)) {
          break label217;
        }
        return false;
      }
      return false;
      label416:
      return this.aae.equals(((f)paramObject).aae);
    }
    
    public f g(jy paramjy)
      throws IOException
    {
      for (;;)
      {
        int i = paramjy.ky();
        int j;
        Object localObject;
        switch (i)
        {
        default: 
          if (a(paramjy, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          j = kh.c(paramjy, 10);
          if (this.fh == null) {}
          for (i = 0;; i = this.fh.length)
          {
            localObject = new String[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fh, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = paramjy.readString();
              paramjy.ky();
              j += 1;
            }
          }
          localObject[j] = paramjy.readString();
          this.fh = ((String[])localObject);
          break;
        case 18: 
          j = kh.c(paramjy, 18);
          if (this.fi == null) {}
          for (i = 0;; i = this.fi.length)
          {
            localObject = new d.a[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fi, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new d.a();
              paramjy.a(localObject[j]);
              paramjy.ky();
              j += 1;
            }
          }
          localObject[j] = new d.a();
          paramjy.a(localObject[j]);
          this.fi = ((d.a[])localObject);
          break;
        case 26: 
          j = kh.c(paramjy, 26);
          if (this.fj == null) {}
          for (i = 0;; i = this.fj.length)
          {
            localObject = new c.e[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fj, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new c.e();
              paramjy.a(localObject[j]);
              paramjy.ky();
              j += 1;
            }
          }
          localObject[j] = new c.e();
          paramjy.a(localObject[j]);
          this.fj = ((c.e[])localObject);
          break;
        case 34: 
          j = kh.c(paramjy, 34);
          if (this.fk == null) {}
          for (i = 0;; i = this.fk.length)
          {
            localObject = new c.b[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fk, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new c.b();
              paramjy.a(localObject[j]);
              paramjy.ky();
              j += 1;
            }
          }
          localObject[j] = new c.b();
          paramjy.a(localObject[j]);
          this.fk = ((c.b[])localObject);
          break;
        case 42: 
          j = kh.c(paramjy, 42);
          if (this.fl == null) {}
          for (i = 0;; i = this.fl.length)
          {
            localObject = new c.b[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fl, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new c.b();
              paramjy.a(localObject[j]);
              paramjy.ky();
              j += 1;
            }
          }
          localObject[j] = new c.b();
          paramjy.a(localObject[j]);
          this.fl = ((c.b[])localObject);
          break;
        case 50: 
          j = kh.c(paramjy, 50);
          if (this.fm == null) {}
          for (i = 0;; i = this.fm.length)
          {
            localObject = new c.b[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fm, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new c.b();
              paramjy.a(localObject[j]);
              paramjy.ky();
              j += 1;
            }
          }
          localObject[j] = new c.b();
          paramjy.a(localObject[j]);
          this.fm = ((c.b[])localObject);
          break;
        case 58: 
          j = kh.c(paramjy, 58);
          if (this.fn == null) {}
          for (i = 0;; i = this.fn.length)
          {
            localObject = new c.g[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fn, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new c.g();
              paramjy.a(localObject[j]);
              paramjy.ky();
              j += 1;
            }
          }
          localObject[j] = new c.g();
          paramjy.a(localObject[j]);
          this.fn = ((c.g[])localObject);
          break;
        case 74: 
          this.fo = paramjy.readString();
          break;
        case 82: 
          this.fp = paramjy.readString();
          break;
        case 98: 
          this.fq = paramjy.readString();
          break;
        case 106: 
          this.fr = paramjy.readString();
          break;
        case 114: 
          if (this.fs == null) {
            this.fs = new c.a();
          }
          paramjy.a(this.fs);
          break;
        case 125: 
          this.ft = paramjy.readFloat();
          break;
        case 130: 
          j = kh.c(paramjy, 130);
          if (this.fv == null) {}
          for (i = 0;; i = this.fv.length)
          {
            localObject = new String[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fv, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = paramjy.readString();
              paramjy.ky();
              j += 1;
            }
          }
          localObject[j] = paramjy.readString();
          this.fv = ((String[])localObject);
          break;
        case 136: 
          this.fw = paramjy.kB();
          break;
        case 144: 
          this.fu = paramjy.kC();
          break;
        case 154: 
          j = kh.c(paramjy, 154);
          if (this.fg == null) {}
          for (i = 0;; i = this.fg.length)
          {
            localObject = new String[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fg, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = paramjy.readString();
              paramjy.ky();
              j += 1;
            }
          }
          localObject[j] = paramjy.readString();
          this.fg = ((String[])localObject);
        }
      }
    }
    
    public int hashCode()
    {
      int i3 = 0;
      int i4 = kc.hashCode(this.fg);
      int i5 = kc.hashCode(this.fh);
      int i6 = kc.hashCode(this.fi);
      int i7 = kc.hashCode(this.fj);
      int i8 = kc.hashCode(this.fk);
      int i9 = kc.hashCode(this.fl);
      int i10 = kc.hashCode(this.fm);
      int i11 = kc.hashCode(this.fn);
      int i;
      int j;
      label93:
      int k;
      label102:
      int m;
      label112:
      int n;
      label122:
      int i12;
      int i1;
      label143:
      int i13;
      int i14;
      if (this.fo == null)
      {
        i = 0;
        if (this.fp != null) {
          break label302;
        }
        j = 0;
        if (this.fq != null) {
          break label313;
        }
        k = 0;
        if (this.fr != null) {
          break label324;
        }
        m = 0;
        if (this.fs != null) {
          break label336;
        }
        n = 0;
        i12 = Float.floatToIntBits(this.ft);
        if (!this.fu) {
          break label348;
        }
        i1 = 1231;
        i13 = kc.hashCode(this.fv);
        i14 = this.fw;
        i2 = i3;
        if (this.aae != null) {
          if (!this.aae.isEmpty()) {
            break label356;
          }
        }
      }
      label302:
      label313:
      label324:
      label336:
      label348:
      label356:
      for (int i2 = i3;; i2 = this.aae.hashCode())
      {
        return (((i1 + ((n + (m + (k + (j + (i + ((((((((i4 + 527) * 31 + i5) * 31 + i6) * 31 + i7) * 31 + i8) * 31 + i9) * 31 + i10) * 31 + i11) * 31) * 31) * 31) * 31) * 31) * 31 + i12) * 31) * 31 + i13) * 31 + i14) * 31 + i2;
        i = this.fo.hashCode();
        break;
        j = this.fp.hashCode();
        break label93;
        k = this.fq.hashCode();
        break label102;
        m = this.fr.hashCode();
        break label112;
        n = this.fs.hashCode();
        break label122;
        i1 = 1237;
        break label143;
      }
    }
    
    public f k()
    {
      this.fg = kh.aao;
      this.fh = kh.aao;
      this.fi = d.a.r();
      this.fj = c.e.i();
      this.fk = c.b.d();
      this.fl = c.b.d();
      this.fm = c.b.d();
      this.fn = c.g.l();
      this.fo = "";
      this.fp = "";
      this.fq = "0";
      this.fr = "";
      this.fs = null;
      this.ft = 0.0F;
      this.fu = false;
      this.fv = kh.aao;
      this.fw = 0;
      this.aae = null;
      this.DY = -1;
      return this;
    }
  }
  
  public static final class g
    extends ka<g>
  {
    private static volatile g[] fx;
    public int[] fA;
    public int[] fB;
    public int[] fC;
    public int[] fD;
    public int[] fE;
    public int[] fF;
    public int[] fG;
    public int[] fH;
    public int[] fy;
    public int[] fz;
    
    public g()
    {
      m();
    }
    
    public static g[] l()
    {
      if (fx == null) {}
      synchronized (kc.aah)
      {
        if (fx == null) {
          fx = new g[0];
        }
        return fx;
      }
    }
    
    public void a(jz paramjz)
      throws IOException
    {
      int j = 0;
      int i;
      if ((this.fy != null) && (this.fy.length > 0))
      {
        i = 0;
        while (i < this.fy.length)
        {
          paramjz.f(1, this.fy[i]);
          i += 1;
        }
      }
      if ((this.fz != null) && (this.fz.length > 0))
      {
        i = 0;
        while (i < this.fz.length)
        {
          paramjz.f(2, this.fz[i]);
          i += 1;
        }
      }
      if ((this.fA != null) && (this.fA.length > 0))
      {
        i = 0;
        while (i < this.fA.length)
        {
          paramjz.f(3, this.fA[i]);
          i += 1;
        }
      }
      if ((this.fB != null) && (this.fB.length > 0))
      {
        i = 0;
        while (i < this.fB.length)
        {
          paramjz.f(4, this.fB[i]);
          i += 1;
        }
      }
      if ((this.fC != null) && (this.fC.length > 0))
      {
        i = 0;
        while (i < this.fC.length)
        {
          paramjz.f(5, this.fC[i]);
          i += 1;
        }
      }
      if ((this.fD != null) && (this.fD.length > 0))
      {
        i = 0;
        while (i < this.fD.length)
        {
          paramjz.f(6, this.fD[i]);
          i += 1;
        }
      }
      if ((this.fE != null) && (this.fE.length > 0))
      {
        i = 0;
        while (i < this.fE.length)
        {
          paramjz.f(7, this.fE[i]);
          i += 1;
        }
      }
      if ((this.fF != null) && (this.fF.length > 0))
      {
        i = 0;
        while (i < this.fF.length)
        {
          paramjz.f(8, this.fF[i]);
          i += 1;
        }
      }
      if ((this.fG != null) && (this.fG.length > 0))
      {
        i = 0;
        while (i < this.fG.length)
        {
          paramjz.f(9, this.fG[i]);
          i += 1;
        }
      }
      if ((this.fH != null) && (this.fH.length > 0))
      {
        i = j;
        while (i < this.fH.length)
        {
          paramjz.f(10, this.fH[i]);
          i += 1;
        }
      }
      super.a(paramjz);
    }
    
    public int c()
    {
      int m = 0;
      int k = super.c();
      int i;
      if ((this.fy != null) && (this.fy.length > 0))
      {
        i = 0;
        j = 0;
        while (i < this.fy.length)
        {
          j += jz.cC(this.fy[i]);
          i += 1;
        }
      }
      for (int j = k + j + this.fy.length * 1;; j = k)
      {
        i = j;
        if (this.fz != null)
        {
          i = j;
          if (this.fz.length > 0)
          {
            i = 0;
            k = 0;
            while (i < this.fz.length)
            {
              k += jz.cC(this.fz[i]);
              i += 1;
            }
            i = j + k + this.fz.length * 1;
          }
        }
        j = i;
        if (this.fA != null)
        {
          j = i;
          if (this.fA.length > 0)
          {
            j = 0;
            k = 0;
            while (j < this.fA.length)
            {
              k += jz.cC(this.fA[j]);
              j += 1;
            }
            j = i + k + this.fA.length * 1;
          }
        }
        i = j;
        if (this.fB != null)
        {
          i = j;
          if (this.fB.length > 0)
          {
            i = 0;
            k = 0;
            while (i < this.fB.length)
            {
              k += jz.cC(this.fB[i]);
              i += 1;
            }
            i = j + k + this.fB.length * 1;
          }
        }
        j = i;
        if (this.fC != null)
        {
          j = i;
          if (this.fC.length > 0)
          {
            j = 0;
            k = 0;
            while (j < this.fC.length)
            {
              k += jz.cC(this.fC[j]);
              j += 1;
            }
            j = i + k + this.fC.length * 1;
          }
        }
        i = j;
        if (this.fD != null)
        {
          i = j;
          if (this.fD.length > 0)
          {
            i = 0;
            k = 0;
            while (i < this.fD.length)
            {
              k += jz.cC(this.fD[i]);
              i += 1;
            }
            i = j + k + this.fD.length * 1;
          }
        }
        j = i;
        if (this.fE != null)
        {
          j = i;
          if (this.fE.length > 0)
          {
            j = 0;
            k = 0;
            while (j < this.fE.length)
            {
              k += jz.cC(this.fE[j]);
              j += 1;
            }
            j = i + k + this.fE.length * 1;
          }
        }
        i = j;
        if (this.fF != null)
        {
          i = j;
          if (this.fF.length > 0)
          {
            i = 0;
            k = 0;
            while (i < this.fF.length)
            {
              k += jz.cC(this.fF[i]);
              i += 1;
            }
            i = j + k + this.fF.length * 1;
          }
        }
        j = i;
        if (this.fG != null)
        {
          j = i;
          if (this.fG.length > 0)
          {
            j = 0;
            k = 0;
            while (j < this.fG.length)
            {
              k += jz.cC(this.fG[j]);
              j += 1;
            }
            j = i + k + this.fG.length * 1;
          }
        }
        i = j;
        if (this.fH != null)
        {
          i = j;
          if (this.fH.length > 0)
          {
            k = 0;
            i = m;
            while (i < this.fH.length)
            {
              k += jz.cC(this.fH[i]);
              i += 1;
            }
            i = j + k + this.fH.length * 1;
          }
        }
        this.DY = i;
        return i;
      }
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (paramObject == this) {
        bool1 = true;
      }
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
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
                            } while (!(paramObject instanceof g));
                            paramObject = (g)paramObject;
                            bool1 = bool2;
                          } while (!kc.equals(this.fy, ((g)paramObject).fy));
                          bool1 = bool2;
                        } while (!kc.equals(this.fz, ((g)paramObject).fz));
                        bool1 = bool2;
                      } while (!kc.equals(this.fA, ((g)paramObject).fA));
                      bool1 = bool2;
                    } while (!kc.equals(this.fB, ((g)paramObject).fB));
                    bool1 = bool2;
                  } while (!kc.equals(this.fC, ((g)paramObject).fC));
                  bool1 = bool2;
                } while (!kc.equals(this.fD, ((g)paramObject).fD));
                bool1 = bool2;
              } while (!kc.equals(this.fE, ((g)paramObject).fE));
              bool1 = bool2;
            } while (!kc.equals(this.fF, ((g)paramObject).fF));
            bool1 = bool2;
          } while (!kc.equals(this.fG, ((g)paramObject).fG));
          bool1 = bool2;
        } while (!kc.equals(this.fH, ((g)paramObject).fH));
        if ((this.aae != null) && (!this.aae.isEmpty())) {
          break label227;
        }
        if (((g)paramObject).aae == null) {
          break;
        }
        bool1 = bool2;
      } while (!((g)paramObject).aae.isEmpty());
      return true;
      label227:
      return this.aae.equals(((g)paramObject).aae);
    }
    
    public g h(jy paramjy)
      throws IOException
    {
      for (;;)
      {
        int i = paramjy.ky();
        int j;
        int[] arrayOfInt;
        int k;
        switch (i)
        {
        default: 
          if (a(paramjy, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          j = kh.c(paramjy, 8);
          if (this.fy == null) {}
          for (i = 0;; i = this.fy.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fy, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramjy.kB();
              paramjy.ky();
              j += 1;
            }
          }
          arrayOfInt[j] = paramjy.kB();
          this.fy = arrayOfInt;
          break;
        case 10: 
          k = paramjy.cw(paramjy.kE());
          i = paramjy.getPosition();
          j = 0;
          while (paramjy.kJ() > 0)
          {
            paramjy.kB();
            j += 1;
          }
          paramjy.cy(i);
          if (this.fy == null) {}
          for (i = 0;; i = this.fy.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fy, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramjy.kB();
              j += 1;
            }
          }
          this.fy = arrayOfInt;
          paramjy.cx(k);
          break;
        case 16: 
          j = kh.c(paramjy, 16);
          if (this.fz == null) {}
          for (i = 0;; i = this.fz.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fz, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramjy.kB();
              paramjy.ky();
              j += 1;
            }
          }
          arrayOfInt[j] = paramjy.kB();
          this.fz = arrayOfInt;
          break;
        case 18: 
          k = paramjy.cw(paramjy.kE());
          i = paramjy.getPosition();
          j = 0;
          while (paramjy.kJ() > 0)
          {
            paramjy.kB();
            j += 1;
          }
          paramjy.cy(i);
          if (this.fz == null) {}
          for (i = 0;; i = this.fz.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fz, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramjy.kB();
              j += 1;
            }
          }
          this.fz = arrayOfInt;
          paramjy.cx(k);
          break;
        case 24: 
          j = kh.c(paramjy, 24);
          if (this.fA == null) {}
          for (i = 0;; i = this.fA.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fA, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramjy.kB();
              paramjy.ky();
              j += 1;
            }
          }
          arrayOfInt[j] = paramjy.kB();
          this.fA = arrayOfInt;
          break;
        case 26: 
          k = paramjy.cw(paramjy.kE());
          i = paramjy.getPosition();
          j = 0;
          while (paramjy.kJ() > 0)
          {
            paramjy.kB();
            j += 1;
          }
          paramjy.cy(i);
          if (this.fA == null) {}
          for (i = 0;; i = this.fA.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fA, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramjy.kB();
              j += 1;
            }
          }
          this.fA = arrayOfInt;
          paramjy.cx(k);
          break;
        case 32: 
          j = kh.c(paramjy, 32);
          if (this.fB == null) {}
          for (i = 0;; i = this.fB.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fB, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramjy.kB();
              paramjy.ky();
              j += 1;
            }
          }
          arrayOfInt[j] = paramjy.kB();
          this.fB = arrayOfInt;
          break;
        case 34: 
          k = paramjy.cw(paramjy.kE());
          i = paramjy.getPosition();
          j = 0;
          while (paramjy.kJ() > 0)
          {
            paramjy.kB();
            j += 1;
          }
          paramjy.cy(i);
          if (this.fB == null) {}
          for (i = 0;; i = this.fB.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fB, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramjy.kB();
              j += 1;
            }
          }
          this.fB = arrayOfInt;
          paramjy.cx(k);
          break;
        case 40: 
          j = kh.c(paramjy, 40);
          if (this.fC == null) {}
          for (i = 0;; i = this.fC.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fC, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramjy.kB();
              paramjy.ky();
              j += 1;
            }
          }
          arrayOfInt[j] = paramjy.kB();
          this.fC = arrayOfInt;
          break;
        case 42: 
          k = paramjy.cw(paramjy.kE());
          i = paramjy.getPosition();
          j = 0;
          while (paramjy.kJ() > 0)
          {
            paramjy.kB();
            j += 1;
          }
          paramjy.cy(i);
          if (this.fC == null) {}
          for (i = 0;; i = this.fC.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fC, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramjy.kB();
              j += 1;
            }
          }
          this.fC = arrayOfInt;
          paramjy.cx(k);
          break;
        case 48: 
          j = kh.c(paramjy, 48);
          if (this.fD == null) {}
          for (i = 0;; i = this.fD.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fD, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramjy.kB();
              paramjy.ky();
              j += 1;
            }
          }
          arrayOfInt[j] = paramjy.kB();
          this.fD = arrayOfInt;
          break;
        case 50: 
          k = paramjy.cw(paramjy.kE());
          i = paramjy.getPosition();
          j = 0;
          while (paramjy.kJ() > 0)
          {
            paramjy.kB();
            j += 1;
          }
          paramjy.cy(i);
          if (this.fD == null) {}
          for (i = 0;; i = this.fD.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fD, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramjy.kB();
              j += 1;
            }
          }
          this.fD = arrayOfInt;
          paramjy.cx(k);
          break;
        case 56: 
          j = kh.c(paramjy, 56);
          if (this.fE == null) {}
          for (i = 0;; i = this.fE.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fE, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramjy.kB();
              paramjy.ky();
              j += 1;
            }
          }
          arrayOfInt[j] = paramjy.kB();
          this.fE = arrayOfInt;
          break;
        case 58: 
          k = paramjy.cw(paramjy.kE());
          i = paramjy.getPosition();
          j = 0;
          while (paramjy.kJ() > 0)
          {
            paramjy.kB();
            j += 1;
          }
          paramjy.cy(i);
          if (this.fE == null) {}
          for (i = 0;; i = this.fE.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fE, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramjy.kB();
              j += 1;
            }
          }
          this.fE = arrayOfInt;
          paramjy.cx(k);
          break;
        case 64: 
          j = kh.c(paramjy, 64);
          if (this.fF == null) {}
          for (i = 0;; i = this.fF.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fF, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramjy.kB();
              paramjy.ky();
              j += 1;
            }
          }
          arrayOfInt[j] = paramjy.kB();
          this.fF = arrayOfInt;
          break;
        case 66: 
          k = paramjy.cw(paramjy.kE());
          i = paramjy.getPosition();
          j = 0;
          while (paramjy.kJ() > 0)
          {
            paramjy.kB();
            j += 1;
          }
          paramjy.cy(i);
          if (this.fF == null) {}
          for (i = 0;; i = this.fF.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fF, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramjy.kB();
              j += 1;
            }
          }
          this.fF = arrayOfInt;
          paramjy.cx(k);
          break;
        case 72: 
          j = kh.c(paramjy, 72);
          if (this.fG == null) {}
          for (i = 0;; i = this.fG.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fG, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramjy.kB();
              paramjy.ky();
              j += 1;
            }
          }
          arrayOfInt[j] = paramjy.kB();
          this.fG = arrayOfInt;
          break;
        case 74: 
          k = paramjy.cw(paramjy.kE());
          i = paramjy.getPosition();
          j = 0;
          while (paramjy.kJ() > 0)
          {
            paramjy.kB();
            j += 1;
          }
          paramjy.cy(i);
          if (this.fG == null) {}
          for (i = 0;; i = this.fG.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fG, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramjy.kB();
              j += 1;
            }
          }
          this.fG = arrayOfInt;
          paramjy.cx(k);
          break;
        case 80: 
          j = kh.c(paramjy, 80);
          if (this.fH == null) {}
          for (i = 0;; i = this.fH.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fH, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramjy.kB();
              paramjy.ky();
              j += 1;
            }
          }
          arrayOfInt[j] = paramjy.kB();
          this.fH = arrayOfInt;
          break;
        case 82: 
          k = paramjy.cw(paramjy.kE());
          i = paramjy.getPosition();
          j = 0;
          while (paramjy.kJ() > 0)
          {
            paramjy.kB();
            j += 1;
          }
          paramjy.cy(i);
          if (this.fH == null) {}
          for (i = 0;; i = this.fH.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fH, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramjy.kB();
              j += 1;
            }
          }
          this.fH = arrayOfInt;
          paramjy.cx(k);
        }
      }
    }
    
    public int hashCode()
    {
      int j = kc.hashCode(this.fy);
      int k = kc.hashCode(this.fz);
      int m = kc.hashCode(this.fA);
      int n = kc.hashCode(this.fB);
      int i1 = kc.hashCode(this.fC);
      int i2 = kc.hashCode(this.fD);
      int i3 = kc.hashCode(this.fE);
      int i4 = kc.hashCode(this.fF);
      int i5 = kc.hashCode(this.fG);
      int i6 = kc.hashCode(this.fH);
      if ((this.aae == null) || (this.aae.isEmpty())) {}
      for (int i = 0;; i = this.aae.hashCode()) {
        return i + ((((((((((j + 527) * 31 + k) * 31 + m) * 31 + n) * 31 + i1) * 31 + i2) * 31 + i3) * 31 + i4) * 31 + i5) * 31 + i6) * 31;
      }
    }
    
    public g m()
    {
      this.fy = kh.aaj;
      this.fz = kh.aaj;
      this.fA = kh.aaj;
      this.fB = kh.aaj;
      this.fC = kh.aaj;
      this.fD = kh.aaj;
      this.fE = kh.aaj;
      this.fF = kh.aaj;
      this.fG = kh.aaj;
      this.fH = kh.aaj;
      this.aae = null;
      this.DY = -1;
      return this;
    }
  }
  
  public static final class h
    extends ka<h>
  {
    public static final kb<d.a, h> fI = kb.a(11, h.class, 810);
    private static final h[] fJ = new h[0];
    public int[] fK;
    public int[] fL;
    public int[] fM;
    public int fN;
    public int[] fO;
    public int fP;
    public int fQ;
    
    public h()
    {
      n();
    }
    
    public void a(jz paramjz)
      throws IOException
    {
      int j = 0;
      int i;
      if ((this.fK != null) && (this.fK.length > 0))
      {
        i = 0;
        while (i < this.fK.length)
        {
          paramjz.f(1, this.fK[i]);
          i += 1;
        }
      }
      if ((this.fL != null) && (this.fL.length > 0))
      {
        i = 0;
        while (i < this.fL.length)
        {
          paramjz.f(2, this.fL[i]);
          i += 1;
        }
      }
      if ((this.fM != null) && (this.fM.length > 0))
      {
        i = 0;
        while (i < this.fM.length)
        {
          paramjz.f(3, this.fM[i]);
          i += 1;
        }
      }
      if (this.fN != 0) {
        paramjz.f(4, this.fN);
      }
      if ((this.fO != null) && (this.fO.length > 0))
      {
        i = j;
        while (i < this.fO.length)
        {
          paramjz.f(5, this.fO[i]);
          i += 1;
        }
      }
      if (this.fP != 0) {
        paramjz.f(6, this.fP);
      }
      if (this.fQ != 0) {
        paramjz.f(7, this.fQ);
      }
      super.a(paramjz);
    }
    
    public int c()
    {
      int m = 0;
      int k = super.c();
      int i;
      if ((this.fK != null) && (this.fK.length > 0))
      {
        i = 0;
        j = 0;
        while (i < this.fK.length)
        {
          j += jz.cC(this.fK[i]);
          i += 1;
        }
      }
      for (int j = k + j + this.fK.length * 1;; j = k)
      {
        i = j;
        if (this.fL != null)
        {
          i = j;
          if (this.fL.length > 0)
          {
            i = 0;
            k = 0;
            while (i < this.fL.length)
            {
              k += jz.cC(this.fL[i]);
              i += 1;
            }
            i = j + k + this.fL.length * 1;
          }
        }
        j = i;
        if (this.fM != null)
        {
          j = i;
          if (this.fM.length > 0)
          {
            j = 0;
            k = 0;
            while (j < this.fM.length)
            {
              k += jz.cC(this.fM[j]);
              j += 1;
            }
            j = i + k + this.fM.length * 1;
          }
        }
        i = j;
        if (this.fN != 0) {
          i = j + jz.g(4, this.fN);
        }
        j = i;
        if (this.fO != null)
        {
          j = i;
          if (this.fO.length > 0)
          {
            k = 0;
            j = m;
            while (j < this.fO.length)
            {
              k += jz.cC(this.fO[j]);
              j += 1;
            }
            j = i + k + this.fO.length * 1;
          }
        }
        i = j;
        if (this.fP != 0) {
          i = j + jz.g(6, this.fP);
        }
        j = i;
        if (this.fQ != 0) {
          j = i + jz.g(7, this.fQ);
        }
        this.DY = j;
        return j;
      }
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (paramObject == this) {
        bool1 = true;
      }
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
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
                      } while (!(paramObject instanceof h));
                      paramObject = (h)paramObject;
                      bool1 = bool2;
                    } while (!kc.equals(this.fK, ((h)paramObject).fK));
                    bool1 = bool2;
                  } while (!kc.equals(this.fL, ((h)paramObject).fL));
                  bool1 = bool2;
                } while (!kc.equals(this.fM, ((h)paramObject).fM));
                bool1 = bool2;
              } while (this.fN != ((h)paramObject).fN);
              bool1 = bool2;
            } while (!kc.equals(this.fO, ((h)paramObject).fO));
            bool1 = bool2;
          } while (this.fP != ((h)paramObject).fP);
          bool1 = bool2;
        } while (this.fQ != ((h)paramObject).fQ);
        if ((this.aae != null) && (!this.aae.isEmpty())) {
          break label170;
        }
        if (((h)paramObject).aae == null) {
          break;
        }
        bool1 = bool2;
      } while (!((h)paramObject).aae.isEmpty());
      return true;
      label170:
      return this.aae.equals(((h)paramObject).aae);
    }
    
    public int hashCode()
    {
      int j = kc.hashCode(this.fK);
      int k = kc.hashCode(this.fL);
      int m = kc.hashCode(this.fM);
      int n = this.fN;
      int i1 = kc.hashCode(this.fO);
      int i2 = this.fP;
      int i3 = this.fQ;
      if ((this.aae == null) || (this.aae.isEmpty())) {}
      for (int i = 0;; i = this.aae.hashCode()) {
        return i + (((((((j + 527) * 31 + k) * 31 + m) * 31 + n) * 31 + i1) * 31 + i2) * 31 + i3) * 31;
      }
    }
    
    public h i(jy paramjy)
      throws IOException
    {
      for (;;)
      {
        int i = paramjy.ky();
        int j;
        int[] arrayOfInt;
        int k;
        switch (i)
        {
        default: 
          if (a(paramjy, i)) {}
          break;
        case 0: 
          return this;
        case 8: 
          j = kh.c(paramjy, 8);
          if (this.fK == null) {}
          for (i = 0;; i = this.fK.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fK, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramjy.kB();
              paramjy.ky();
              j += 1;
            }
          }
          arrayOfInt[j] = paramjy.kB();
          this.fK = arrayOfInt;
          break;
        case 10: 
          k = paramjy.cw(paramjy.kE());
          i = paramjy.getPosition();
          j = 0;
          while (paramjy.kJ() > 0)
          {
            paramjy.kB();
            j += 1;
          }
          paramjy.cy(i);
          if (this.fK == null) {}
          for (i = 0;; i = this.fK.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fK, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramjy.kB();
              j += 1;
            }
          }
          this.fK = arrayOfInt;
          paramjy.cx(k);
          break;
        case 16: 
          j = kh.c(paramjy, 16);
          if (this.fL == null) {}
          for (i = 0;; i = this.fL.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fL, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramjy.kB();
              paramjy.ky();
              j += 1;
            }
          }
          arrayOfInt[j] = paramjy.kB();
          this.fL = arrayOfInt;
          break;
        case 18: 
          k = paramjy.cw(paramjy.kE());
          i = paramjy.getPosition();
          j = 0;
          while (paramjy.kJ() > 0)
          {
            paramjy.kB();
            j += 1;
          }
          paramjy.cy(i);
          if (this.fL == null) {}
          for (i = 0;; i = this.fL.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fL, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramjy.kB();
              j += 1;
            }
          }
          this.fL = arrayOfInt;
          paramjy.cx(k);
          break;
        case 24: 
          j = kh.c(paramjy, 24);
          if (this.fM == null) {}
          for (i = 0;; i = this.fM.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fM, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramjy.kB();
              paramjy.ky();
              j += 1;
            }
          }
          arrayOfInt[j] = paramjy.kB();
          this.fM = arrayOfInt;
          break;
        case 26: 
          k = paramjy.cw(paramjy.kE());
          i = paramjy.getPosition();
          j = 0;
          while (paramjy.kJ() > 0)
          {
            paramjy.kB();
            j += 1;
          }
          paramjy.cy(i);
          if (this.fM == null) {}
          for (i = 0;; i = this.fM.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fM, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramjy.kB();
              j += 1;
            }
          }
          this.fM = arrayOfInt;
          paramjy.cx(k);
          break;
        case 32: 
          this.fN = paramjy.kB();
          break;
        case 40: 
          j = kh.c(paramjy, 40);
          if (this.fO == null) {}
          for (i = 0;; i = this.fO.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fO, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length - 1)
            {
              arrayOfInt[j] = paramjy.kB();
              paramjy.ky();
              j += 1;
            }
          }
          arrayOfInt[j] = paramjy.kB();
          this.fO = arrayOfInt;
          break;
        case 42: 
          k = paramjy.cw(paramjy.kE());
          i = paramjy.getPosition();
          j = 0;
          while (paramjy.kJ() > 0)
          {
            paramjy.kB();
            j += 1;
          }
          paramjy.cy(i);
          if (this.fO == null) {}
          for (i = 0;; i = this.fO.length)
          {
            arrayOfInt = new int[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fO, 0, arrayOfInt, 0, i);
              j = i;
            }
            while (j < arrayOfInt.length)
            {
              arrayOfInt[j] = paramjy.kB();
              j += 1;
            }
          }
          this.fO = arrayOfInt;
          paramjy.cx(k);
          break;
        case 48: 
          this.fP = paramjy.kB();
          break;
        case 56: 
          this.fQ = paramjy.kB();
        }
      }
    }
    
    public h n()
    {
      this.fK = kh.aaj;
      this.fL = kh.aaj;
      this.fM = kh.aaj;
      this.fN = 0;
      this.fO = kh.aaj;
      this.fP = 0;
      this.fQ = 0;
      this.aae = null;
      this.DY = -1;
      return this;
    }
  }
  
  public static final class i
    extends ka<i>
  {
    private static volatile i[] fR;
    public d.a fS;
    public c.d fT;
    public String name;
    
    public i()
    {
      p();
    }
    
    public static i[] o()
    {
      if (fR == null) {}
      synchronized (kc.aah)
      {
        if (fR == null) {
          fR = new i[0];
        }
        return fR;
      }
    }
    
    public void a(jz paramjz)
      throws IOException
    {
      if (!this.name.equals("")) {
        paramjz.b(1, this.name);
      }
      if (this.fS != null) {
        paramjz.a(2, this.fS);
      }
      if (this.fT != null) {
        paramjz.a(3, this.fT);
      }
      super.a(paramjz);
    }
    
    public int c()
    {
      int j = super.c();
      int i = j;
      if (!this.name.equals("")) {
        i = j + jz.g(1, this.name);
      }
      j = i;
      if (this.fS != null) {
        j = i + jz.b(2, this.fS);
      }
      i = j;
      if (this.fT != null) {
        i = j + jz.b(3, this.fT);
      }
      this.DY = i;
      return i;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (paramObject == this) {
        bool1 = true;
      }
      label41:
      label57:
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
            } while (!(paramObject instanceof i));
            paramObject = (i)paramObject;
            if (this.name != null) {
              break;
            }
            bool1 = bool2;
          } while (((i)paramObject).name != null);
          if (this.fS != null) {
            break label131;
          }
          bool1 = bool2;
        } while (((i)paramObject).fS != null);
        if (this.fT != null) {
          break label147;
        }
        bool1 = bool2;
      } while (((i)paramObject).fT != null);
      for (;;)
      {
        if ((this.aae == null) || (this.aae.isEmpty()))
        {
          if (((i)paramObject).aae != null)
          {
            bool1 = bool2;
            if (!((i)paramObject).aae.isEmpty()) {
              break;
            }
          }
          return true;
          if (this.name.equals(((i)paramObject).name)) {
            break label41;
          }
          return false;
          label131:
          if (this.fS.equals(((i)paramObject).fS)) {
            break label57;
          }
          return false;
          label147:
          if (!this.fT.equals(((i)paramObject).fT)) {
            return false;
          }
        }
      }
      return this.aae.equals(((i)paramObject).aae);
    }
    
    public int hashCode()
    {
      int n = 0;
      int i;
      int j;
      label21:
      int k;
      if (this.name == null)
      {
        i = 0;
        if (this.fS != null) {
          break label90;
        }
        j = 0;
        if (this.fT != null) {
          break label101;
        }
        k = 0;
        label30:
        m = n;
        if (this.aae != null) {
          if (!this.aae.isEmpty()) {
            break label112;
          }
        }
      }
      label90:
      label101:
      label112:
      for (int m = n;; m = this.aae.hashCode())
      {
        return (k + (j + (i + 527) * 31) * 31) * 31 + m;
        i = this.name.hashCode();
        break;
        j = this.fS.hashCode();
        break label21;
        k = this.fT.hashCode();
        break label30;
      }
    }
    
    public i j(jy paramjy)
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
        case 10: 
          this.name = paramjy.readString();
          break;
        case 18: 
          if (this.fS == null) {
            this.fS = new d.a();
          }
          paramjy.a(this.fS);
          break;
        case 26: 
          if (this.fT == null) {
            this.fT = new c.d();
          }
          paramjy.a(this.fT);
        }
      }
    }
    
    public i p()
    {
      this.name = "";
      this.fS = null;
      this.fT = null;
      this.aae = null;
      this.DY = -1;
      return this;
    }
  }
  
  public static final class j
    extends ka<j>
  {
    public c.i[] fU;
    public c.f fV;
    public String fW;
    
    public j()
    {
      q();
    }
    
    public static j b(byte[] paramArrayOfByte)
      throws kd
    {
      return (j)ke.a(new j(), paramArrayOfByte);
    }
    
    public void a(jz paramjz)
      throws IOException
    {
      if ((this.fU != null) && (this.fU.length > 0))
      {
        int i = 0;
        while (i < this.fU.length)
        {
          c.i locali = this.fU[i];
          if (locali != null) {
            paramjz.a(1, locali);
          }
          i += 1;
        }
      }
      if (this.fV != null) {
        paramjz.a(2, this.fV);
      }
      if (!this.fW.equals("")) {
        paramjz.b(3, this.fW);
      }
      super.a(paramjz);
    }
    
    public int c()
    {
      int i = super.c();
      int j = i;
      if (this.fU != null)
      {
        j = i;
        if (this.fU.length > 0)
        {
          int k = 0;
          for (;;)
          {
            j = i;
            if (k >= this.fU.length) {
              break;
            }
            c.i locali = this.fU[k];
            j = i;
            if (locali != null) {
              j = i + jz.b(1, locali);
            }
            k += 1;
            i = j;
          }
        }
      }
      i = j;
      if (this.fV != null) {
        i = j + jz.b(2, this.fV);
      }
      j = i;
      if (!this.fW.equals("")) {
        j = i + jz.g(3, this.fW);
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
      label57:
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
            } while (!(paramObject instanceof j));
            paramObject = (j)paramObject;
            bool1 = bool2;
          } while (!kc.equals(this.fU, ((j)paramObject).fU));
          if (this.fV != null) {
            break;
          }
          bool1 = bool2;
        } while (((j)paramObject).fV != null);
        if (this.fW != null) {
          break label131;
        }
        bool1 = bool2;
      } while (((j)paramObject).fW != null);
      for (;;)
      {
        if ((this.aae == null) || (this.aae.isEmpty()))
        {
          if (((j)paramObject).aae != null)
          {
            bool1 = bool2;
            if (!((j)paramObject).aae.isEmpty()) {
              break;
            }
          }
          return true;
          if (this.fV.equals(((j)paramObject).fV)) {
            break label57;
          }
          return false;
          label131:
          if (!this.fW.equals(((j)paramObject).fW)) {
            return false;
          }
        }
      }
      return this.aae.equals(((j)paramObject).aae);
    }
    
    public int hashCode()
    {
      int m = 0;
      int n = kc.hashCode(this.fU);
      int i;
      int j;
      if (this.fV == null)
      {
        i = 0;
        if (this.fW != null) {
          break label88;
        }
        j = 0;
        label30:
        k = m;
        if (this.aae != null) {
          if (!this.aae.isEmpty()) {
            break label99;
          }
        }
      }
      label88:
      label99:
      for (int k = m;; k = this.aae.hashCode())
      {
        return (j + (i + (n + 527) * 31) * 31) * 31 + k;
        i = this.fV.hashCode();
        break;
        j = this.fW.hashCode();
        break label30;
      }
    }
    
    public j k(jy paramjy)
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
        case 10: 
          int j = kh.c(paramjy, 10);
          if (this.fU == null) {}
          c.i[] arrayOfi;
          for (i = 0;; i = this.fU.length)
          {
            arrayOfi = new c.i[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fU, 0, arrayOfi, 0, i);
              j = i;
            }
            while (j < arrayOfi.length - 1)
            {
              arrayOfi[j] = new c.i();
              paramjy.a(arrayOfi[j]);
              paramjy.ky();
              j += 1;
            }
          }
          arrayOfi[j] = new c.i();
          paramjy.a(arrayOfi[j]);
          this.fU = arrayOfi;
          break;
        case 18: 
          if (this.fV == null) {
            this.fV = new c.f();
          }
          paramjy.a(this.fV);
          break;
        case 26: 
          this.fW = paramjy.readString();
        }
      }
    }
    
    public j q()
    {
      this.fU = c.i.o();
      this.fV = null;
      this.fW = "";
      this.aae = null;
      this.DY = -1;
      return this;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */