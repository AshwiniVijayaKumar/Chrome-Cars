package org.apache.james.mime4j.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class ContentUtil
{
  public static String decode(Charset paramCharset, ByteSequence paramByteSequence)
  {
    return decode(paramCharset, paramByteSequence, 0, paramByteSequence.length());
  }
  
  public static String decode(Charset paramCharset, ByteSequence paramByteSequence, int paramInt1, int paramInt2)
  {
    if ((paramByteSequence instanceof ByteArrayBuffer)) {
      return decode(paramCharset, ((ByteArrayBuffer)paramByteSequence).buffer(), paramInt1, paramInt2);
    }
    return decode(paramCharset, paramByteSequence.toByteArray(), paramInt1, paramInt2);
  }
  
  private static String decode(Charset paramCharset, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return paramCharset.decode(ByteBuffer.wrap(paramArrayOfByte, paramInt1, paramInt2)).toString();
  }
  
  public static String decode(ByteSequence paramByteSequence)
  {
    return decode(CharsetUtil.US_ASCII, paramByteSequence, 0, paramByteSequence.length());
  }
  
  public static String decode(ByteSequence paramByteSequence, int paramInt1, int paramInt2)
  {
    return decode(CharsetUtil.US_ASCII, paramByteSequence, paramInt1, paramInt2);
  }
  
  public static ByteSequence encode(String paramString)
  {
    return encode(CharsetUtil.US_ASCII, paramString);
  }
  
  public static ByteSequence encode(Charset paramCharset, String paramString)
  {
    paramCharset = paramCharset.encode(CharBuffer.wrap(paramString));
    paramString = new ByteArrayBuffer(paramCharset.remaining());
    paramString.append(paramCharset.array(), paramCharset.position(), paramCharset.remaining());
    return paramString;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\util\ContentUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */