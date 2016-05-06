package com.google.android.gms.internal;

import java.io.IOException;

public final class kh
{
  public static final int[] aaj = new int[0];
  public static final long[] aak = new long[0];
  public static final float[] aal = new float[0];
  public static final double[] aam = new double[0];
  public static final boolean[] aan = new boolean[0];
  public static final String[] aao = new String[0];
  public static final byte[][] aap = new byte[0][];
  public static final byte[] aaq = new byte[0];
  
  public static boolean b(jy paramjy, int paramInt)
    throws IOException
  {
    return paramjy.cv(paramInt);
  }
  
  public static final int c(jy paramjy, int paramInt)
    throws IOException
  {
    int i = 1;
    int j = paramjy.getPosition();
    paramjy.cv(paramInt);
    for (;;)
    {
      if ((paramjy.kJ() <= 0) || (paramjy.ky() != paramInt))
      {
        paramjy.cy(j);
        return i;
      }
      paramjy.cv(paramInt);
      i += 1;
    }
  }
  
  static int cJ(int paramInt)
  {
    return paramInt & 0x7;
  }
  
  public static int cK(int paramInt)
  {
    return paramInt >>> 3;
  }
  
  static int i(int paramInt1, int paramInt2)
  {
    return paramInt1 << 3 | paramInt2;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\kh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */