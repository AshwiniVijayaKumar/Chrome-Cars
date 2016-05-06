package com.google.zxing.oned.rss;

public final class RSSUtils
{
  private static int combins(int paramInt1, int paramInt2)
  {
    int i;
    int j;
    int m;
    int k;
    if (paramInt1 - paramInt2 > paramInt2)
    {
      i = paramInt2;
      j = paramInt1 - paramInt2;
      paramInt2 = 1;
      m = 1;
      k = paramInt1;
      paramInt1 = m;
      label24:
      if (k > j) {
        break label46;
      }
    }
    for (;;)
    {
      if (paramInt1 > i)
      {
        return paramInt2;
        i = paramInt1 - paramInt2;
        j = paramInt2;
        break;
        label46:
        int n = paramInt2 * k;
        m = paramInt1;
        paramInt2 = n;
        if (paramInt1 <= i)
        {
          paramInt2 = n / paramInt1;
          m = paramInt1 + 1;
        }
        k -= 1;
        paramInt1 = m;
        break label24;
      }
      paramInt2 /= paramInt1;
      paramInt1 += 1;
    }
  }
  
  static int[] elements(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    int[] arrayOfInt = new int[paramArrayOfInt.length + 2];
    int m = paramInt2 << 1;
    arrayOfInt[0] = 1;
    paramInt2 = 10;
    int k = 1;
    int i = 1;
    if (i >= m - 2)
    {
      arrayOfInt[(m - 1)] = (paramInt1 - k);
      paramInt1 = paramInt2;
      if (arrayOfInt[(m - 1)] < paramInt2) {
        paramInt1 = arrayOfInt[(m - 1)];
      }
      if (paramInt1 > 1) {
        paramInt2 = 0;
      }
    }
    for (;;)
    {
      if (paramInt2 >= m)
      {
        return arrayOfInt;
        arrayOfInt[i] = (paramArrayOfInt[(i - 1)] - arrayOfInt[(i - 1)]);
        arrayOfInt[(i + 1)] = (paramArrayOfInt[i] - arrayOfInt[i]);
        k += arrayOfInt[i] + arrayOfInt[(i + 1)];
        int j = paramInt2;
        if (arrayOfInt[i] < paramInt2) {
          j = arrayOfInt[i];
        }
        i += 2;
        paramInt2 = j;
        break;
      }
      arrayOfInt[paramInt2] += paramInt1 - 1;
      i = paramInt2 + 1;
      arrayOfInt[i] -= paramInt1 - 1;
      paramInt2 += 2;
    }
  }
  
  public static int getRSSvalue(int[] paramArrayOfInt, int paramInt, boolean paramBoolean)
  {
    int i4 = paramArrayOfInt.length;
    int i = 0;
    int k = paramArrayOfInt.length;
    int j = 0;
    int i1;
    int m;
    int n;
    if (j >= k)
    {
      i1 = 0;
      j = 0;
      m = 0;
      n = i;
    }
    int i2;
    for (;;)
    {
      if (m >= i4 - 1)
      {
        return i1;
        i += paramArrayOfInt[j];
        j += 1;
        break;
      }
      i2 = 1;
      i = j | 1 << m;
      if (i2 < paramArrayOfInt[m]) {
        break label99;
      }
      n -= i2;
      m += 1;
      j = i;
    }
    label99:
    k = combins(n - i2 - 1, i4 - m - 2);
    j = k;
    if (paramBoolean)
    {
      j = k;
      if (i == 0)
      {
        j = k;
        if (n - i2 - (i4 - m - 1) >= i4 - m - 1) {
          j = k - combins(n - i2 - (i4 - m), i4 - m - 2);
        }
      }
    }
    int i3;
    if (i4 - m - 1 > 1)
    {
      i3 = 0;
      k = n - i2 - (i4 - m - 2);
      label216:
      if (k <= paramInt) {
        k = j - (i4 - 1 - m) * i3;
      }
    }
    for (;;)
    {
      i1 += k;
      i2 += 1;
      i &= (1 << m ^ 0xFFFFFFFF);
      break;
      i3 += combins(n - i2 - k - 1, i4 - m - 3);
      k -= 1;
      break label216;
      k = j;
      if (n - i2 > paramInt) {
        k = j - 1;
      }
    }
  }
  
  static int[] getRSSwidths(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
  {
    int[] arrayOfInt = new int[paramInt3];
    int i = 0;
    int k = 0;
    int j = paramInt2;
    paramInt2 = i;
    if (k >= paramInt3 - 1)
    {
      arrayOfInt[k] = j;
      return arrayOfInt;
    }
    paramInt2 |= 1 << k;
    int m = 1;
    int n = paramInt1;
    paramInt1 = paramInt2;
    for (;;)
    {
      i = combins(j - m - 1, paramInt3 - k - 2);
      paramInt2 = i;
      if (paramBoolean)
      {
        paramInt2 = i;
        if (paramInt1 == 0)
        {
          paramInt2 = i;
          if (j - m - (paramInt3 - k - 1) >= paramInt3 - k - 1) {
            paramInt2 = i - combins(j - m - (paramInt3 - k), paramInt3 - k - 2);
          }
        }
      }
      int i1;
      if (paramInt3 - k - 1 > 1)
      {
        i1 = 0;
        i = j - m - (paramInt3 - k - 2);
        label157:
        if (i <= paramInt4) {
          i = paramInt2 - (paramInt3 - 1 - k) * i1;
        }
      }
      for (;;)
      {
        n -= i;
        if (n >= 0) {
          break label276;
        }
        i = n + i;
        j -= m;
        arrayOfInt[k] = m;
        k += 1;
        paramInt2 = paramInt1;
        paramInt1 = i;
        break;
        i1 += combins(j - m - i - 1, paramInt3 - k - 3);
        i -= 1;
        break label157;
        i = paramInt2;
        if (j - m > paramInt4) {
          i = paramInt2 - 1;
        }
      }
      label276:
      m += 1;
      paramInt1 &= (1 << k ^ 0xFFFFFFFF);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\rss\RSSUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */