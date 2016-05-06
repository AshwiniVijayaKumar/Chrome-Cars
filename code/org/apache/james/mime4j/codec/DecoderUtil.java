package org.apache.james.mime4j.codec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.james.mime4j.util.CharsetUtil;

public class DecoderUtil
{
  private static Log log = LogFactory.getLog(DecoderUtil.class);
  
  public static String decodeB(String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    return new String(decodeBase64(paramString1), paramString2);
  }
  
  public static byte[] decodeBase64(String paramString)
  {
    localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      paramString = new Base64InputStream(new ByteArrayInputStream(paramString.getBytes("US-ASCII")));
      for (;;)
      {
        int i = paramString.read();
        if (i == -1) {
          break;
        }
        localByteArrayOutputStream.write(i);
      }
      return localByteArrayOutputStream.toByteArray();
    }
    catch (IOException paramString)
    {
      log.error(paramString);
    }
  }
  
  public static byte[] decodeBaseQuotedPrintable(String paramString)
  {
    localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      paramString = new QuotedPrintableInputStream(new ByteArrayInputStream(paramString.getBytes("US-ASCII")));
      for (;;)
      {
        int i = paramString.read();
        if (i == -1) {
          break;
        }
        localByteArrayOutputStream.write(i);
      }
      return localByteArrayOutputStream.toByteArray();
    }
    catch (IOException paramString)
    {
      log.error(paramString);
    }
  }
  
  private static String decodeEncodedWord(String paramString, int paramInt1, int paramInt2)
  {
    int i = paramString.indexOf('?', paramInt1 + 2);
    if (i == paramInt2 - 2) {}
    do
    {
      for (;;)
      {
        return null;
        int j = paramString.indexOf('?', i + 1);
        if (j != paramInt2 - 2)
        {
          String str1 = paramString.substring(paramInt1 + 2, i);
          String str2 = paramString.substring(i + 1, j);
          String str3 = paramString.substring(j + 1, paramInt2 - 2);
          String str4 = CharsetUtil.toJavaCharset(str1);
          if (str4 == null)
          {
            if (log.isWarnEnabled())
            {
              log.warn("MIME charset '" + str1 + "' in encoded word '" + paramString.substring(paramInt1, paramInt2) + "' doesn't have a " + "corresponding Java charset");
              return null;
            }
          }
          else if (!CharsetUtil.isDecodingSupported(str4))
          {
            if (log.isWarnEnabled())
            {
              log.warn("Current JDK doesn't support decoding of charset '" + str4 + "' (MIME charset '" + str1 + "' in encoded word '" + paramString.substring(paramInt1, paramInt2) + "')");
              return null;
            }
          }
          else if (str3.length() == 0)
          {
            if (log.isWarnEnabled())
            {
              log.warn("Missing encoded text in encoded word: '" + paramString.substring(paramInt1, paramInt2) + "'");
              return null;
            }
          }
          else {
            try
            {
              if (str2.equalsIgnoreCase("Q")) {
                return decodeQ(str3, str4);
              }
              if (str2.equalsIgnoreCase("B")) {
                return decodeB(str3, str4);
              }
              if (log.isWarnEnabled())
              {
                log.warn("Warning: Unknown encoding in encoded word '" + paramString.substring(paramInt1, paramInt2) + "'");
                return null;
              }
            }
            catch (UnsupportedEncodingException localUnsupportedEncodingException)
            {
              if (log.isWarnEnabled())
              {
                log.warn("Unsupported encoding in encoded word '" + paramString.substring(paramInt1, paramInt2) + "'", localUnsupportedEncodingException);
                return null;
              }
            }
            catch (RuntimeException localRuntimeException) {}
          }
        }
      }
    } while (!log.isWarnEnabled());
    log.warn("Could not decode encoded word '" + paramString.substring(paramInt1, paramInt2) + "'", localRuntimeException);
    return null;
  }
  
  public static String decodeEncodedWords(String paramString)
  {
    int j = 0;
    int i = 0;
    StringBuilder localStringBuilder = new StringBuilder();
    int m = paramString.indexOf("=?", j);
    int k;
    if (m == -1) {
      k = -1;
    }
    while (k == -1) {
      if (j == 0)
      {
        return paramString;
        k = paramString.indexOf("?=", m + 2);
      }
      else
      {
        localStringBuilder.append(paramString.substring(j));
        return localStringBuilder.toString();
      }
    }
    k += 2;
    String str1 = paramString.substring(j, m);
    String str2 = decodeEncodedWord(paramString, m, k);
    if (str2 == null)
    {
      localStringBuilder.append(str1);
      localStringBuilder.append(paramString.substring(m, k));
      label120:
      j = k;
      if (str2 == null) {
        break label163;
      }
    }
    label163:
    for (i = 1;; i = 0)
    {
      break;
      if ((i == 0) || (!CharsetUtil.isWhitespace(str1))) {
        localStringBuilder.append(str1);
      }
      localStringBuilder.append(str2);
      break label120;
    }
  }
  
  public static String decodeQ(String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    int i = 0;
    if (i < paramString1.length())
    {
      char c = paramString1.charAt(i);
      if (c == '_') {
        localStringBuilder.append("=20");
      }
      for (;;)
      {
        i += 1;
        break;
        localStringBuilder.append(c);
      }
    }
    return new String(decodeBaseQuotedPrintable(localStringBuilder.toString()), paramString2);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\codec\DecoderUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */