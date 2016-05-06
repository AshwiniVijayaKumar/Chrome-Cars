package org.apache.james.mime4j.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

public class EOLConvertingInputStream
  extends InputStream
{
  public static final int CONVERT_BOTH = 3;
  public static final int CONVERT_CR = 1;
  public static final int CONVERT_LF = 2;
  private int flags = 3;
  private PushbackInputStream in = null;
  private int previous = 0;
  
  public EOLConvertingInputStream(InputStream paramInputStream)
  {
    this(paramInputStream, 3);
  }
  
  public EOLConvertingInputStream(InputStream paramInputStream, int paramInt)
  {
    this.in = new PushbackInputStream(paramInputStream, 2);
    this.flags = paramInt;
  }
  
  public void close()
    throws IOException
  {
    this.in.close();
  }
  
  public int read()
    throws IOException
  {
    int j = this.in.read();
    if (j == -1) {
      return -1;
    }
    int i;
    if (((this.flags & 0x1) != 0) && (j == 13))
    {
      int k = this.in.read();
      if (k != -1) {
        this.in.unread(k);
      }
      i = j;
      if (k != 10)
      {
        this.in.unread(10);
        i = j;
      }
    }
    for (;;)
    {
      this.previous = i;
      return i;
      i = j;
      if ((this.flags & 0x2) != 0)
      {
        i = j;
        if (j == 10)
        {
          i = j;
          if (this.previous != 13)
          {
            i = 13;
            this.in.unread(10);
          }
        }
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\io\EOLConvertingInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */