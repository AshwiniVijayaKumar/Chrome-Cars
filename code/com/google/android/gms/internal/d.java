package com.google.android.gms.internal;

import java.io.IOException;
import java.util.List;

public abstract interface d
{
  public static final class a
    extends ka<a>
  {
    private static volatile a[] fX;
    public String fY;
    public a[] fZ;
    public a[] ga;
    public a[] gb;
    public String gc;
    public String gd;
    public long ge;
    public boolean gf;
    public a[] gg;
    public int[] gh;
    public boolean gi;
    public int type;
    
    public a()
    {
      s();
    }
    
    public static a[] r()
    {
      if (fX == null) {}
      synchronized (kc.aah)
      {
        if (fX == null) {
          fX = new a[0];
        }
        return fX;
      }
    }
    
    public void a(jz paramjz)
      throws IOException
    {
      int j = 0;
      paramjz.f(1, this.type);
      if (!this.fY.equals("")) {
        paramjz.b(2, this.fY);
      }
      int i;
      a locala;
      if ((this.fZ != null) && (this.fZ.length > 0))
      {
        i = 0;
        while (i < this.fZ.length)
        {
          locala = this.fZ[i];
          if (locala != null) {
            paramjz.a(3, locala);
          }
          i += 1;
        }
      }
      if ((this.ga != null) && (this.ga.length > 0))
      {
        i = 0;
        while (i < this.ga.length)
        {
          locala = this.ga[i];
          if (locala != null) {
            paramjz.a(4, locala);
          }
          i += 1;
        }
      }
      if ((this.gb != null) && (this.gb.length > 0))
      {
        i = 0;
        while (i < this.gb.length)
        {
          locala = this.gb[i];
          if (locala != null) {
            paramjz.a(5, locala);
          }
          i += 1;
        }
      }
      if (!this.gc.equals("")) {
        paramjz.b(6, this.gc);
      }
      if (!this.gd.equals("")) {
        paramjz.b(7, this.gd);
      }
      if (this.ge != 0L) {
        paramjz.b(8, this.ge);
      }
      if (this.gi) {
        paramjz.a(9, this.gi);
      }
      if ((this.gh != null) && (this.gh.length > 0))
      {
        i = 0;
        while (i < this.gh.length)
        {
          paramjz.f(10, this.gh[i]);
          i += 1;
        }
      }
      if ((this.gg != null) && (this.gg.length > 0))
      {
        i = j;
        while (i < this.gg.length)
        {
          locala = this.gg[i];
          if (locala != null) {
            paramjz.a(11, locala);
          }
          i += 1;
        }
      }
      if (this.gf) {
        paramjz.a(12, this.gf);
      }
      super.a(paramjz);
    }
    
    public int c()
    {
      int m = 0;
      int j = super.c() + jz.g(1, this.type);
      int i = j;
      if (!this.fY.equals("")) {
        i = j + jz.g(2, this.fY);
      }
      j = i;
      a locala;
      int k;
      if (this.fZ != null)
      {
        j = i;
        if (this.fZ.length > 0)
        {
          j = 0;
          while (j < this.fZ.length)
          {
            locala = this.fZ[j];
            k = i;
            if (locala != null) {
              k = i + jz.b(3, locala);
            }
            j += 1;
            i = k;
          }
          j = i;
        }
      }
      i = j;
      if (this.ga != null)
      {
        i = j;
        if (this.ga.length > 0)
        {
          i = j;
          j = 0;
          while (j < this.ga.length)
          {
            locala = this.ga[j];
            k = i;
            if (locala != null) {
              k = i + jz.b(4, locala);
            }
            j += 1;
            i = k;
          }
        }
      }
      j = i;
      if (this.gb != null)
      {
        j = i;
        if (this.gb.length > 0)
        {
          j = 0;
          while (j < this.gb.length)
          {
            locala = this.gb[j];
            k = i;
            if (locala != null) {
              k = i + jz.b(5, locala);
            }
            j += 1;
            i = k;
          }
          j = i;
        }
      }
      i = j;
      if (!this.gc.equals("")) {
        i = j + jz.g(6, this.gc);
      }
      j = i;
      if (!this.gd.equals("")) {
        j = i + jz.g(7, this.gd);
      }
      i = j;
      if (this.ge != 0L) {
        i = j + jz.d(8, this.ge);
      }
      j = i;
      if (this.gi) {
        j = i + jz.b(9, this.gi);
      }
      i = j;
      if (this.gh != null)
      {
        i = j;
        if (this.gh.length > 0)
        {
          i = 0;
          k = 0;
          while (i < this.gh.length)
          {
            k += jz.cC(this.gh[i]);
            i += 1;
          }
          i = j + k + this.gh.length * 1;
        }
      }
      j = i;
      if (this.gg != null)
      {
        j = i;
        if (this.gg.length > 0)
        {
          k = m;
          for (;;)
          {
            j = i;
            if (k >= this.gg.length) {
              break;
            }
            locala = this.gg[k];
            j = i;
            if (locala != null) {
              j = i + jz.b(11, locala);
            }
            k += 1;
            i = j;
          }
        }
      }
      i = j;
      if (this.gf) {
        i = j + jz.b(12, this.gf);
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
      label54:
      label118:
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
                    } while (!(paramObject instanceof a));
                    paramObject = (a)paramObject;
                    bool1 = bool2;
                  } while (this.type != ((a)paramObject).type);
                  if (this.fY != null) {
                    break;
                  }
                  bool1 = bool2;
                } while (((a)paramObject).fY != null);
                bool1 = bool2;
              } while (!kc.equals(this.fZ, ((a)paramObject).fZ));
              bool1 = bool2;
            } while (!kc.equals(this.ga, ((a)paramObject).ga));
            bool1 = bool2;
          } while (!kc.equals(this.gb, ((a)paramObject).gb));
          if (this.gc != null) {
            break label264;
          }
          bool1 = bool2;
        } while (((a)paramObject).gc != null);
        if (this.gd != null) {
          break label280;
        }
        bool1 = bool2;
      } while (((a)paramObject).gd != null);
      label264:
      label280:
      while (this.gd.equals(((a)paramObject).gd))
      {
        bool1 = bool2;
        if (this.ge != ((a)paramObject).ge) {
          break;
        }
        bool1 = bool2;
        if (this.gf != ((a)paramObject).gf) {
          break;
        }
        bool1 = bool2;
        if (!kc.equals(this.gg, ((a)paramObject).gg)) {
          break;
        }
        bool1 = bool2;
        if (!kc.equals(this.gh, ((a)paramObject).gh)) {
          break;
        }
        bool1 = bool2;
        if (this.gi != ((a)paramObject).gi) {
          break;
        }
        if ((this.aae != null) && (!this.aae.isEmpty())) {
          break label296;
        }
        if (((a)paramObject).aae != null)
        {
          bool1 = bool2;
          if (!((a)paramObject).aae.isEmpty()) {
            break;
          }
        }
        return true;
        if (this.fY.equals(((a)paramObject).fY)) {
          break label54;
        }
        return false;
        if (this.gc.equals(((a)paramObject).gc)) {
          break label118;
        }
        return false;
      }
      return false;
      label296:
      return this.aae.equals(((a)paramObject).aae);
    }
    
    public int hashCode()
    {
      int n = 1231;
      int i2 = 0;
      int i3 = this.type;
      int i;
      int i4;
      int i5;
      int i6;
      int j;
      label59:
      int k;
      label68:
      int i7;
      int m;
      label95:
      int i8;
      int i9;
      if (this.fY == null)
      {
        i = 0;
        i4 = kc.hashCode(this.fZ);
        i5 = kc.hashCode(this.ga);
        i6 = kc.hashCode(this.gb);
        if (this.gc != null) {
          break label234;
        }
        j = 0;
        if (this.gd != null) {
          break label245;
        }
        k = 0;
        i7 = (int)(this.ge ^ this.ge >>> 32);
        if (!this.gf) {
          break label256;
        }
        m = 1231;
        i8 = kc.hashCode(this.gg);
        i9 = kc.hashCode(this.gh);
        if (!this.gi) {
          break label264;
        }
        label120:
        i1 = i2;
        if (this.aae != null) {
          if (!this.aae.isEmpty()) {
            break label272;
          }
        }
      }
      label234:
      label245:
      label256:
      label264:
      label272:
      for (int i1 = i2;; i1 = this.aae.hashCode())
      {
        return ((((m + ((k + (j + ((((i + (i3 + 527) * 31) * 31 + i4) * 31 + i5) * 31 + i6) * 31) * 31) * 31 + i7) * 31) * 31 + i8) * 31 + i9) * 31 + n) * 31 + i1;
        i = this.fY.hashCode();
        break;
        j = this.gc.hashCode();
        break label59;
        k = this.gd.hashCode();
        break label68;
        m = 1237;
        break label95;
        n = 1237;
        break label120;
      }
    }
    
    public a l(jy paramjy)
      throws IOException
    {
      for (;;)
      {
        int i = paramjy.ky();
        int j;
        Object localObject;
        int k;
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
          case 4: 
          case 5: 
          case 6: 
          case 7: 
          case 8: 
            this.type = i;
          }
          break;
        case 18: 
          this.fY = paramjy.readString();
          break;
        case 26: 
          j = kh.c(paramjy, 26);
          if (this.fZ == null) {}
          for (i = 0;; i = this.fZ.length)
          {
            localObject = new a[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.fZ, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new a();
              paramjy.a(localObject[j]);
              paramjy.ky();
              j += 1;
            }
          }
          localObject[j] = new a();
          paramjy.a(localObject[j]);
          this.fZ = ((a[])localObject);
          break;
        case 34: 
          j = kh.c(paramjy, 34);
          if (this.ga == null) {}
          for (i = 0;; i = this.ga.length)
          {
            localObject = new a[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.ga, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new a();
              paramjy.a(localObject[j]);
              paramjy.ky();
              j += 1;
            }
          }
          localObject[j] = new a();
          paramjy.a(localObject[j]);
          this.ga = ((a[])localObject);
          break;
        case 42: 
          j = kh.c(paramjy, 42);
          if (this.gb == null) {}
          for (i = 0;; i = this.gb.length)
          {
            localObject = new a[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.gb, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new a();
              paramjy.a(localObject[j]);
              paramjy.ky();
              j += 1;
            }
          }
          localObject[j] = new a();
          paramjy.a(localObject[j]);
          this.gb = ((a[])localObject);
          break;
        case 50: 
          this.gc = paramjy.readString();
          break;
        case 58: 
          this.gd = paramjy.readString();
          break;
        case 64: 
          this.ge = paramjy.kA();
          break;
        case 72: 
          this.gi = paramjy.kC();
          break;
        case 80: 
          int m = kh.c(paramjy, 80);
          localObject = new int[m];
          j = 0;
          i = 0;
          if (j < m)
          {
            if (j != 0) {
              paramjy.ky();
            }
            int n = paramjy.kB();
            switch (n)
            {
            }
            for (;;)
            {
              j += 1;
              break;
              k = i + 1;
              localObject[i] = n;
              i = k;
            }
          }
          if (i != 0)
          {
            if (this.gh == null) {}
            for (j = 0;; j = this.gh.length)
            {
              if ((j != 0) || (i != localObject.length)) {
                break label810;
              }
              this.gh = ((int[])localObject);
              break;
            }
            int[] arrayOfInt = new int[j + i];
            if (j != 0) {
              System.arraycopy(this.gh, 0, arrayOfInt, 0, j);
            }
            System.arraycopy(localObject, 0, arrayOfInt, j, i);
            this.gh = arrayOfInt;
          }
          break;
        case 82: 
          k = paramjy.cw(paramjy.kE());
          i = paramjy.getPosition();
          j = 0;
          while (paramjy.kJ() > 0) {
            switch (paramjy.kB())
            {
            default: 
              break;
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 17: 
              j += 1;
            }
          }
          if (j != 0)
          {
            paramjy.cy(i);
            if (this.gh == null) {}
            for (i = 0;; i = this.gh.length)
            {
              localObject = new int[j + i];
              j = i;
              if (i != 0)
              {
                System.arraycopy(this.gh, 0, localObject, 0, i);
                j = i;
              }
              while (paramjy.kJ() > 0)
              {
                i = paramjy.kB();
                switch (i)
                {
                default: 
                  break;
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 5: 
                case 6: 
                case 7: 
                case 8: 
                case 9: 
                case 10: 
                case 11: 
                case 12: 
                case 13: 
                case 14: 
                case 15: 
                case 16: 
                case 17: 
                  localObject[j] = i;
                  j += 1;
                }
              }
            }
            this.gh = ((int[])localObject);
          }
          paramjy.cx(k);
          break;
        case 90: 
          j = kh.c(paramjy, 90);
          if (this.gg == null) {}
          for (i = 0;; i = this.gg.length)
          {
            localObject = new a[j + i];
            j = i;
            if (i != 0)
            {
              System.arraycopy(this.gg, 0, localObject, 0, i);
              j = i;
            }
            while (j < localObject.length - 1)
            {
              localObject[j] = new a();
              paramjy.a(localObject[j]);
              paramjy.ky();
              j += 1;
            }
          }
          localObject[j] = new a();
          paramjy.a(localObject[j]);
          this.gg = ((a[])localObject);
          break;
        case 96: 
          label810:
          this.gf = paramjy.kC();
        }
      }
    }
    
    public a s()
    {
      this.type = 1;
      this.fY = "";
      this.fZ = r();
      this.ga = r();
      this.gb = r();
      this.gc = "";
      this.gd = "";
      this.ge = 0L;
      this.gf = false;
      this.gg = r();
      this.gh = kh.aaj;
      this.gi = false;
      this.aae = null;
      this.DY = -1;
      return this;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */