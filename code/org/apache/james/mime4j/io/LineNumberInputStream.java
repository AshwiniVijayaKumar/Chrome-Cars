package org.apache.james.mime4j.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LineNumberInputStream
  extends FilterInputStream
  implements LineNumberSource
{
  private int lineNumber = 1;
  
  public LineNumberInputStream(InputStream paramInputStream)
  {
    super(paramInputStream);
  }
  
  public int getLineNumber()
  {
    return this.lineNumber;
  }
  
  public int read()
    throws IOException
  {
    int i = this.in.read();
    if (i == 10) {
      this.lineNumber += 1;
    }
    return i;
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = this.in.read(paramArrayOfByte, paramInt1, paramInt2);
    paramInt2 = paramInt1;
    while (paramInt2 < paramInt1 + i)
    {
      if (paramArrayOfByte[paramInt2] == 10) {
        this.lineNumber += 1;
      }
      paramInt2 += 1;
    }
    return i;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\io\LineNumberInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */