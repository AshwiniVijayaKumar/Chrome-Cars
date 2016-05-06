package org.apache.james.mime4j.codec;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.BitSet;
import java.util.Locale;
import org.apache.james.mime4j.util.CharsetUtil;

public class EncoderUtil
{
  private static final BitSet ATEXT_CHARS = initChars("()<>@.,;:\\\"[]");
  private static final char BASE64_PAD = '=';
  private static final byte[] BASE64_TABLE = Base64OutputStream.BASE64_TABLE;
  private static final int ENCODED_WORD_MAX_LENGTH = 75;
  private static final String ENC_WORD_PREFIX = "=?";
  private static final String ENC_WORD_SUFFIX = "?=";
  private static final int MAX_USED_CHARACTERS = 50;
  private static final BitSet Q_REGULAR_CHARS = initChars("=_?");
  private static final BitSet Q_RESTRICTED_CHARS = initChars("=_?\"#$%&'(),.:;<>@[\\]^`{|}~");
  private static final BitSet TOKEN_CHARS = initChars("()<>@,;:\\\"/[]?=");
  
  private static int bEncodedLength(byte[] paramArrayOfByte)
  {
    return (paramArrayOfByte.length + 2) / 3 * 4;
  }
  
  private static Charset determineCharset(String paramString)
  {
    int j = 1;
    int k = paramString.length();
    int i = 0;
    while (i < k)
    {
      int m = paramString.charAt(i);
      if (m > 255) {
        return CharsetUtil.UTF_8;
      }
      if (m > 127) {
        j = 0;
      }
      i += 1;
    }
    if (j != 0) {
      return CharsetUtil.US_ASCII;
    }
    return CharsetUtil.ISO_8859_1;
  }
  
  private static Encoding determineEncoding(byte[] paramArrayOfByte, Usage paramUsage)
  {
    if (paramArrayOfByte.length == 0) {
      return Encoding.Q;
    }
    if (paramUsage == Usage.TEXT_TOKEN) {}
    int j;
    for (paramUsage = Q_REGULAR_CHARS;; paramUsage = Q_RESTRICTED_CHARS)
    {
      j = 0;
      int i = 0;
      while (i < paramArrayOfByte.length)
      {
        int m = paramArrayOfByte[i] & 0xFF;
        int k = j;
        if (m != 32)
        {
          k = j;
          if (!paramUsage.get(m)) {
            k = j + 1;
          }
        }
        i += 1;
        j = k;
      }
    }
    if (j * 100 / paramArrayOfByte.length > 30) {
      return Encoding.B;
    }
    return Encoding.Q;
  }
  
  private static byte[] encode(String paramString, Charset paramCharset)
  {
    paramString = paramCharset.encode(paramString);
    paramCharset = new byte[paramString.limit()];
    paramString.get(paramCharset);
    return paramCharset;
  }
  
  public static String encodeAddressDisplayName(String paramString)
  {
    if (isAtomPhrase(paramString)) {
      return paramString;
    }
    if (hasToBeEncoded(paramString, 0)) {
      return encodeEncodedWord(paramString, Usage.WORD_ENTITY);
    }
    return quote(paramString);
  }
  
  public static String encodeAddressLocalPart(String paramString)
  {
    if (isDotAtomText(paramString)) {
      return paramString;
    }
    return quote(paramString);
  }
  
  private static String encodeB(String paramString1, String paramString2, int paramInt, Charset paramCharset, byte[] paramArrayOfByte)
  {
    int i = bEncodedLength(paramArrayOfByte);
    if (paramString1.length() + i + "?=".length() <= 75 - paramInt) {
      return paramString1 + encodeB(paramArrayOfByte) + "?=";
    }
    paramArrayOfByte = paramString2.substring(0, paramString2.length() / 2);
    paramArrayOfByte = encodeB(paramString1, paramArrayOfByte, paramInt, paramCharset, encode(paramArrayOfByte, paramCharset));
    paramString2 = paramString2.substring(paramString2.length() / 2);
    paramString1 = encodeB(paramString1, paramString2, 0, paramCharset, encode(paramString2, paramCharset));
    return paramArrayOfByte + " " + paramString1;
  }
  
  public static String encodeB(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    int j = paramArrayOfByte.length;
    while (i < j - 2)
    {
      int k = (paramArrayOfByte[i] & 0xFF) << 16 | (paramArrayOfByte[(i + 1)] & 0xFF) << 8 | paramArrayOfByte[(i + 2)] & 0xFF;
      localStringBuilder.append((char)BASE64_TABLE[(k >> 18 & 0x3F)]);
      localStringBuilder.append((char)BASE64_TABLE[(k >> 12 & 0x3F)]);
      localStringBuilder.append((char)BASE64_TABLE[(k >> 6 & 0x3F)]);
      localStringBuilder.append((char)BASE64_TABLE[(k & 0x3F)]);
      i += 3;
    }
    if (i == j - 2)
    {
      i = (paramArrayOfByte[i] & 0xFF) << 16 | (paramArrayOfByte[(i + 1)] & 0xFF) << 8;
      localStringBuilder.append((char)BASE64_TABLE[(i >> 18 & 0x3F)]);
      localStringBuilder.append((char)BASE64_TABLE[(i >> 12 & 0x3F)]);
      localStringBuilder.append((char)BASE64_TABLE[(i >> 6 & 0x3F)]);
      localStringBuilder.append('=');
    }
    for (;;)
    {
      return localStringBuilder.toString();
      if (i == j - 1)
      {
        i = (paramArrayOfByte[i] & 0xFF) << 16;
        localStringBuilder.append((char)BASE64_TABLE[(i >> 18 & 0x3F)]);
        localStringBuilder.append((char)BASE64_TABLE[(i >> 12 & 0x3F)]);
        localStringBuilder.append('=');
        localStringBuilder.append('=');
      }
    }
  }
  
  public static String encodeEncodedWord(String paramString, Usage paramUsage)
  {
    return encodeEncodedWord(paramString, paramUsage, 0, null, null);
  }
  
  public static String encodeEncodedWord(String paramString, Usage paramUsage, int paramInt)
  {
    return encodeEncodedWord(paramString, paramUsage, paramInt, null, null);
  }
  
  public static String encodeEncodedWord(String paramString, Usage paramUsage, int paramInt, Charset paramCharset, Encoding paramEncoding)
  {
    if (paramString == null) {
      throw new IllegalArgumentException();
    }
    if ((paramInt < 0) || (paramInt > 50)) {
      throw new IllegalArgumentException();
    }
    Charset localCharset = paramCharset;
    if (paramCharset == null) {
      localCharset = determineCharset(paramString);
    }
    String str = CharsetUtil.toMimeCharset(localCharset.name());
    if (str == null) {
      throw new IllegalArgumentException("Unsupported charset");
    }
    byte[] arrayOfByte = encode(paramString, localCharset);
    paramCharset = paramEncoding;
    if (paramEncoding == null) {
      paramCharset = determineEncoding(arrayOfByte, paramUsage);
    }
    if (paramCharset == Encoding.B) {
      return encodeB("=?" + str + "?B?", paramString, paramInt, localCharset, arrayOfByte);
    }
    return encodeQ("=?" + str + "?Q?", paramString, paramUsage, paramInt, localCharset, arrayOfByte);
  }
  
  public static String encodeHeaderParameter(String paramString1, String paramString2)
  {
    paramString1 = paramString1.toLowerCase(Locale.US);
    if (isToken(paramString2)) {
      return paramString1 + "=" + paramString2;
    }
    return paramString1 + "=" + quote(paramString2);
  }
  
  public static String encodeIfNecessary(String paramString, Usage paramUsage, int paramInt)
  {
    String str = paramString;
    if (hasToBeEncoded(paramString, paramInt)) {
      str = encodeEncodedWord(paramString, paramUsage, paramInt);
    }
    return str;
  }
  
  private static String encodeQ(String paramString1, String paramString2, Usage paramUsage, int paramInt, Charset paramCharset, byte[] paramArrayOfByte)
  {
    int i = qEncodedLength(paramArrayOfByte, paramUsage);
    if (paramString1.length() + i + "?=".length() <= 75 - paramInt) {
      return paramString1 + encodeQ(paramArrayOfByte, paramUsage) + "?=";
    }
    paramArrayOfByte = paramString2.substring(0, paramString2.length() / 2);
    paramArrayOfByte = encodeQ(paramString1, paramArrayOfByte, paramUsage, paramInt, paramCharset, encode(paramArrayOfByte, paramCharset));
    paramString2 = paramString2.substring(paramString2.length() / 2);
    paramString1 = encodeQ(paramString1, paramString2, paramUsage, 0, paramCharset, encode(paramString2, paramCharset));
    return paramArrayOfByte + " " + paramString1;
  }
  
  public static String encodeQ(byte[] paramArrayOfByte, Usage paramUsage)
  {
    StringBuilder localStringBuilder;
    int i;
    label25:
    int k;
    if (paramUsage == Usage.TEXT_TOKEN)
    {
      paramUsage = Q_REGULAR_CHARS;
      localStringBuilder = new StringBuilder();
      int j = paramArrayOfByte.length;
      i = 0;
      if (i >= j) {
        break label127;
      }
      k = paramArrayOfByte[i] & 0xFF;
      if (k != 32) {
        break label68;
      }
      localStringBuilder.append('_');
    }
    for (;;)
    {
      i += 1;
      break label25;
      paramUsage = Q_RESTRICTED_CHARS;
      break;
      label68:
      if (!paramUsage.get(k))
      {
        localStringBuilder.append('=');
        localStringBuilder.append(hexDigit(k >>> 4));
        localStringBuilder.append(hexDigit(k & 0xF));
      }
      else
      {
        localStringBuilder.append((char)k);
      }
    }
    label127:
    return localStringBuilder.toString();
  }
  
  public static boolean hasToBeEncoded(String paramString, int paramInt)
  {
    if (paramString == null) {
      throw new IllegalArgumentException();
    }
    if ((paramInt < 0) || (paramInt > 50)) {
      throw new IllegalArgumentException();
    }
    int i = 0;
    if (i < paramString.length())
    {
      int j = paramString.charAt(i);
      if ((j == 9) || (j == 32)) {
        paramInt = 0;
      }
      do
      {
        i += 1;
        break;
        paramInt += 1;
        if (paramInt > 77) {}
        while (j < 32) {
          return true;
        }
      } while (j < 127);
      return true;
    }
    return false;
  }
  
  private static char hexDigit(int paramInt)
  {
    if (paramInt < 10) {
      return (char)(paramInt + 48);
    }
    return (char)(paramInt - 10 + 65);
  }
  
  private static BitSet initChars(String paramString)
  {
    BitSet localBitSet = new BitSet(128);
    for (int i = 33; i < 127; i = (char)(i + 1)) {
      if (paramString.indexOf(i) == -1) {
        localBitSet.set(i);
      }
    }
    return localBitSet;
  }
  
  private static boolean isAtomPhrase(String paramString)
  {
    boolean bool1 = false;
    int j = paramString.length();
    int i = 0;
    boolean bool2 = bool1;
    if (i < j)
    {
      char c = paramString.charAt(i);
      if (ATEXT_CHARS.get(c)) {
        bool1 = true;
      }
      while (CharsetUtil.isWhitespace(c))
      {
        i += 1;
        break;
      }
      bool2 = false;
    }
    return bool2;
  }
  
  private static boolean isDotAtomText(String paramString)
  {
    int j = 46;
    int m = paramString.length();
    if (m == 0) {}
    int i;
    int k;
    do
    {
      return false;
      i = 0;
      if (i >= m) {
        break label71;
      }
      k = paramString.charAt(i);
      if (k != 46) {
        break;
      }
    } while ((j == 46) || (i == m - 1));
    while (ATEXT_CHARS.get(k))
    {
      j = k;
      i += 1;
      break;
    }
    return false;
    label71:
    return true;
  }
  
  public static boolean isToken(String paramString)
  {
    int j = paramString.length();
    if (j == 0) {
      return false;
    }
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label41;
      }
      int k = paramString.charAt(i);
      if (!TOKEN_CHARS.get(k)) {
        break;
      }
      i += 1;
    }
    label41:
    return true;
  }
  
  private static int qEncodedLength(byte[] paramArrayOfByte, Usage paramUsage)
  {
    int i;
    int j;
    label15:
    int k;
    if (paramUsage == Usage.TEXT_TOKEN)
    {
      paramUsage = Q_REGULAR_CHARS;
      i = 0;
      j = 0;
      if (j >= paramArrayOfByte.length) {
        return i;
      }
      k = paramArrayOfByte[j] & 0xFF;
      if (k != 32) {
        break label55;
      }
      i += 1;
    }
    for (;;)
    {
      j += 1;
      break label15;
      paramUsage = Q_RESTRICTED_CHARS;
      break;
      label55:
      if (!paramUsage.get(k)) {
        i += 3;
      } else {
        i += 1;
      }
    }
    return i;
  }
  
  private static String quote(String paramString)
  {
    paramString = paramString.replaceAll("[\\\\\"]", "\\\\$0");
    return "\"" + paramString + "\"";
  }
  
  public static enum Encoding
  {
    B,  Q;
    
    private Encoding() {}
  }
  
  public static enum Usage
  {
    TEXT_TOKEN,  WORD_ENTITY;
    
    private Usage() {}
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\codec\EncoderUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */