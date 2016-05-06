package org.apache.james.mime4j.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.util.ByteArrayBuffer;

public abstract class LineReaderInputStream
  extends FilterInputStream
{
  protected LineReaderInputStream(InputStream paramInputStream)
  {
    super(paramInputStream);
  }
  
  public abstract int readLine(ByteArrayBuffer paramByteArrayBuffer)
    throws IOException;
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\io\LineReaderInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */