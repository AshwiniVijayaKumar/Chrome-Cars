package com.joanzapata.pdfview.util;

public class NumberUtils
{
  public static int limit(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 <= paramInt2) {
      return paramInt2;
    }
    if (paramInt1 >= paramInt3) {
      return paramInt3;
    }
    return paramInt1;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\joanzapata\pdfview\util\NumberUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */