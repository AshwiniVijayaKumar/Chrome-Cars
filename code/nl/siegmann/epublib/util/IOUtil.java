package nl.siegmann.epublib.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class IOUtil
{
  public static final int IO_COPY_BUFFER_SIZE = 4096;
  
  protected static int calcNewNrReadSize(int paramInt1, int paramInt2)
  {
    if (paramInt2 < 0) {
      return paramInt2;
    }
    if (paramInt2 > Integer.MAX_VALUE - paramInt1) {
      return -1;
    }
    return paramInt2 + paramInt1;
  }
  
  public static int copy(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte['က'];
    int j;
    for (int i = 0;; i = calcNewNrReadSize(j, i))
    {
      j = paramInputStream.read(arrayOfByte);
      if (j < 0) {
        break;
      }
      paramOutputStream.write(arrayOfByte, 0, j);
    }
    paramOutputStream.flush();
    return i;
  }
  
  public static int copy(Reader paramReader, Writer paramWriter)
    throws IOException
  {
    char[] arrayOfChar = new char['က'];
    int j;
    for (int i = 0;; i = calcNewNrReadSize(j, i))
    {
      j = paramReader.read(arrayOfChar);
      if (j < 0) {
        break;
      }
      paramWriter.write(arrayOfChar, 0, j);
    }
    paramWriter.flush();
    return i;
  }
  
  public static byte[] toByteArray(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    copy(paramInputStream, localByteArrayOutputStream);
    localByteArrayOutputStream.flush();
    return localByteArrayOutputStream.toByteArray();
  }
  
  public static byte[] toByteArray(Reader paramReader, String paramString)
    throws IOException
  {
    StringWriter localStringWriter = new StringWriter();
    copy(paramReader, localStringWriter);
    localStringWriter.flush();
    return localStringWriter.toString().getBytes(paramString);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\util\IOUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */