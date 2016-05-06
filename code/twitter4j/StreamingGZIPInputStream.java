package twitter4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

final class StreamingGZIPInputStream
  extends GZIPInputStream
{
  private final InputStream wrapped;
  
  public StreamingGZIPInputStream(InputStream paramInputStream)
    throws IOException
  {
    super(paramInputStream);
    this.wrapped = paramInputStream;
  }
  
  public int available()
    throws IOException
  {
    return this.wrapped.available();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\StreamingGZIPInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */