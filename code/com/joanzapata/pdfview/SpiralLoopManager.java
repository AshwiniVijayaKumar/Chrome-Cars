package com.joanzapata.pdfview;

class SpiralLoopManager
{
  private SpiralLoopListener listener;
  
  public SpiralLoopManager(SpiralLoopListener paramSpiralLoopListener)
  {
    if (paramSpiralLoopListener == null) {
      throw new IllegalArgumentException("SpiralLoopListener must not be null");
    }
    this.listener = paramSpiralLoopListener;
  }
  
  private boolean isValidCell(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return (paramInt1 >= 0) && (paramInt1 < paramInt3) && (paramInt2 >= 0) && (paramInt2 < paramInt4);
  }
  
  public void startLoop(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int j = paramInt4;
    int i = 1;
    paramInt4 = 1;
    this.listener.onLoop(paramInt3, j);
    int m = 0 + 1;
    int k = paramInt3;
    paramInt3 = m;
    for (;;)
    {
      if (paramInt3 < paramInt2 * paramInt1) {
        m = 0;
      }
      int n;
      while (m < i)
      {
        n = k + paramInt4;
        k = paramInt3;
        if (isValidCell(n, j, paramInt1, paramInt2))
        {
          k = paramInt3 + 1;
          if (!this.listener.onLoop(n, j)) {
            return;
          }
        }
        m += 1;
        paramInt3 = k;
        k = n;
      }
      m = 0;
      for (;;)
      {
        if (m >= i) {
          break label178;
        }
        j += paramInt4;
        n = paramInt3;
        if (isValidCell(k, j, paramInt1, paramInt2))
        {
          n = paramInt3 + 1;
          if (!this.listener.onLoop(k, j)) {
            break;
          }
        }
        m += 1;
        paramInt3 = n;
      }
      label178:
      i += 1;
      paramInt4 *= -1;
    }
  }
  
  public static abstract interface SpiralLoopListener
  {
    public abstract boolean onLoop(int paramInt1, int paramInt2);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\joanzapata\pdfview\SpiralLoopManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */