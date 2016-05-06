package com.google.android.gms.tagmanager;

class dh
  extends Number
  implements Comparable<dh>
{
  private double XF;
  private long XG;
  private boolean XH;
  
  private dh(double paramDouble)
  {
    this.XF = paramDouble;
    this.XH = false;
  }
  
  private dh(long paramLong)
  {
    this.XG = paramLong;
    this.XH = true;
  }
  
  public static dh a(Double paramDouble)
  {
    return new dh(paramDouble.doubleValue());
  }
  
  public static dh bH(String paramString)
    throws NumberFormatException
  {
    try
    {
      dh localdh1 = new dh(Long.parseLong(paramString));
      return localdh1;
    }
    catch (NumberFormatException localNumberFormatException1)
    {
      try
      {
        dh localdh2 = new dh(Double.parseDouble(paramString));
        return localdh2;
      }
      catch (NumberFormatException localNumberFormatException2)
      {
        throw new NumberFormatException(paramString + " is not a valid TypedNumber");
      }
    }
  }
  
  public static dh v(long paramLong)
  {
    return new dh(paramLong);
  }
  
  public int a(dh paramdh)
  {
    if ((kk()) && (paramdh.kk())) {
      return new Long(this.XG).compareTo(Long.valueOf(paramdh.XG));
    }
    return Double.compare(doubleValue(), paramdh.doubleValue());
  }
  
  public byte byteValue()
  {
    return (byte)(int)longValue();
  }
  
  public double doubleValue()
  {
    if (kk()) {
      return this.XG;
    }
    return this.XF;
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof dh)) && (a((dh)paramObject) == 0);
  }
  
  public float floatValue()
  {
    return (float)doubleValue();
  }
  
  public int hashCode()
  {
    return new Long(longValue()).hashCode();
  }
  
  public int intValue()
  {
    return km();
  }
  
  public boolean kj()
  {
    return !kk();
  }
  
  public boolean kk()
  {
    return this.XH;
  }
  
  public long kl()
  {
    if (kk()) {
      return this.XG;
    }
    return this.XF;
  }
  
  public int km()
  {
    return (int)longValue();
  }
  
  public short kn()
  {
    return (short)(int)longValue();
  }
  
  public long longValue()
  {
    return kl();
  }
  
  public short shortValue()
  {
    return kn();
  }
  
  public String toString()
  {
    if (kk()) {
      return Long.toString(this.XG);
    }
    return Double.toString(this.XF);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\dh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */