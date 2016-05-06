package com.google.zxing;

public final class FormatException
  extends ReaderException
{
  private static final FormatException instance = new FormatException();
  
  public static FormatException getFormatInstance()
  {
    return instance;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\FormatException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */