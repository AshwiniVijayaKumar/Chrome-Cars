package com.google.android.gms.internal;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class dp
{
  private static final AtomicInteger xA = new AtomicInteger(0);
  protected final du xB;
  private final String xC;
  private dw xD;
  
  protected dp(String paramString1, String paramString2)
  {
    this.xC = paramString1;
    this.xB = new du(paramString2);
    this.xB.U(String.format("instance-%d", new Object[] { Integer.valueOf(xA.incrementAndGet()) }));
  }
  
  public void P(String paramString) {}
  
  public void a(long paramLong, int paramInt) {}
  
  public final void a(dw paramdw)
  {
    this.xD = paramdw;
    if (this.xD == null) {
      cX();
    }
  }
  
  protected final void a(String paramString1, long paramLong, String paramString2)
    throws IOException
  {
    this.xB.a("Sending text message: %s to: %s", new Object[] { paramString1, paramString2 });
    this.xD.a(this.xC, paramString1, paramLong, paramString2);
  }
  
  protected final long cW()
  {
    return this.xD.cV();
  }
  
  public void cX() {}
  
  public final String getNamespace()
  {
    return this.xC;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\dp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */