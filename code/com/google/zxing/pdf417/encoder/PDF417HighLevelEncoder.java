package com.google.zxing.pdf417.encoder;

import com.google.zxing.WriterException;
import java.math.BigInteger;
import java.util.Arrays;

final class PDF417HighLevelEncoder
{
  private static final int BYTE_COMPACTION = 1;
  private static final int LATCH_TO_BYTE = 924;
  private static final int LATCH_TO_BYTE_PADDED = 901;
  private static final int LATCH_TO_NUMERIC = 902;
  private static final int LATCH_TO_TEXT = 900;
  private static final byte[] MIXED;
  private static final int NUMERIC_COMPACTION = 2;
  private static final byte[] PUNCTUATION;
  private static final int SHIFT_TO_BYTE = 913;
  private static final int SUBMODE_ALPHA = 0;
  private static final int SUBMODE_LOWER = 1;
  private static final int SUBMODE_MIXED = 2;
  private static final int SUBMODE_PUNCTUATION = 3;
  private static final int TEXT_COMPACTION = 0;
  private static final byte[] TEXT_MIXED_RAW;
  private static final byte[] TEXT_PUNCTUATION_RAW;
  
  static
  {
    byte[] arrayOfByte = new byte[30];
    arrayOfByte[0] = 48;
    arrayOfByte[1] = 49;
    arrayOfByte[2] = 50;
    arrayOfByte[3] = 51;
    arrayOfByte[4] = 52;
    arrayOfByte[5] = 53;
    arrayOfByte[6] = 54;
    arrayOfByte[7] = 55;
    arrayOfByte[8] = 56;
    arrayOfByte[9] = 57;
    arrayOfByte[10] = 38;
    arrayOfByte[11] = 13;
    arrayOfByte[12] = 9;
    arrayOfByte[13] = 44;
    arrayOfByte[14] = 58;
    arrayOfByte[15] = 35;
    arrayOfByte[16] = 45;
    arrayOfByte[17] = 46;
    arrayOfByte[18] = 36;
    arrayOfByte[19] = 47;
    arrayOfByte[20] = 43;
    arrayOfByte[21] = 37;
    arrayOfByte[22] = 42;
    arrayOfByte[23] = 61;
    arrayOfByte[24] = 94;
    arrayOfByte[26] = 32;
    TEXT_MIXED_RAW = arrayOfByte;
    arrayOfByte = new byte[30];
    arrayOfByte[0] = 59;
    arrayOfByte[1] = 60;
    arrayOfByte[2] = 62;
    arrayOfByte[3] = 64;
    arrayOfByte[4] = 91;
    arrayOfByte[5] = 92;
    arrayOfByte[6] = 93;
    arrayOfByte[7] = 95;
    arrayOfByte[8] = 96;
    arrayOfByte[9] = 126;
    arrayOfByte[10] = 33;
    arrayOfByte[11] = 13;
    arrayOfByte[12] = 9;
    arrayOfByte[13] = 44;
    arrayOfByte[14] = 58;
    arrayOfByte[15] = 10;
    arrayOfByte[16] = 45;
    arrayOfByte[17] = 46;
    arrayOfByte[18] = 36;
    arrayOfByte[19] = 47;
    arrayOfByte[20] = 34;
    arrayOfByte[21] = 124;
    arrayOfByte[22] = 42;
    arrayOfByte[23] = 40;
    arrayOfByte[24] = 41;
    arrayOfByte[25] = 63;
    arrayOfByte[26] = 123;
    arrayOfByte[27] = 125;
    arrayOfByte[28] = 39;
    TEXT_PUNCTUATION_RAW = arrayOfByte;
    MIXED = new byte[''];
    PUNCTUATION = new byte[''];
    Arrays.fill(MIXED, (byte)-1);
    int i = 0;
    if (i >= TEXT_MIXED_RAW.length) {
      Arrays.fill(PUNCTUATION, (byte)-1);
    }
    for (i = 0;; i = (byte)(i + 1))
    {
      if (i >= TEXT_PUNCTUATION_RAW.length)
      {
        return;
        j = TEXT_MIXED_RAW[i];
        if (j > 0) {
          MIXED[j] = i;
        }
        i = (byte)(i + 1);
        break;
      }
      int j = TEXT_PUNCTUATION_RAW[i];
      if (j > 0) {
        PUNCTUATION[j] = i;
      }
    }
  }
  
  private static int determineConsecutiveBinaryCount(CharSequence paramCharSequence, byte[] paramArrayOfByte, int paramInt)
    throws WriterException
  {
    int m = paramCharSequence.length();
    int j = paramInt;
    for (;;)
    {
      if (j >= m) {
        return j - paramInt;
      }
      char c = paramCharSequence.charAt(j);
      int i = 0;
      int k;
      label69:
      int n;
      for (;;)
      {
        k = i;
        if (i < 13)
        {
          if (isDigit(c)) {
            break label69;
          }
          k = i;
        }
        do
        {
          if (k < 13) {
            break;
          }
          return j - paramInt;
          i += 1;
          n = j + i;
          k = i;
        } while (n >= m);
        c = paramCharSequence.charAt(n);
      }
      i = 0;
      for (;;)
      {
        k = i;
        if (i < 5)
        {
          if (isText(c)) {
            break label140;
          }
          k = i;
        }
        label140:
        do
        {
          if (k < 5) {
            break;
          }
          return j - paramInt;
          i += 1;
          n = j + i;
          k = i;
        } while (n >= m);
        c = paramCharSequence.charAt(n);
      }
      c = paramCharSequence.charAt(j);
      if ((paramArrayOfByte[j] == 63) && (c != '?')) {
        throw new WriterException("Non-encodable character detected: " + c + " (Unicode: " + c + ')');
      }
      j += 1;
    }
  }
  
  private static int determineConsecutiveDigitCount(CharSequence paramCharSequence, int paramInt)
  {
    int j = 0;
    int k = 0;
    int m = paramCharSequence.length();
    char c;
    int i;
    if (paramInt < m)
    {
      c = paramCharSequence.charAt(paramInt);
      i = paramInt;
      paramInt = k;
    }
    for (;;)
    {
      j = paramInt;
      if (isDigit(c))
      {
        if (i >= m) {
          j = paramInt;
        }
      }
      else {
        return j;
      }
      j = paramInt + 1;
      k = i + 1;
      paramInt = j;
      i = k;
      if (k < m)
      {
        c = paramCharSequence.charAt(k);
        paramInt = j;
        i = k;
      }
    }
  }
  
  private static int determineConsecutiveTextCount(CharSequence paramCharSequence, int paramInt)
  {
    int n = paramCharSequence.length();
    int i = paramInt;
    for (;;)
    {
      if (i >= n) {}
      int j;
      do
      {
        return i - paramInt;
        char c = paramCharSequence.charAt(i);
        int k = 0;
        j = i;
        for (;;)
        {
          if ((k >= 13) || (!isDigit(c)) || (j >= n))
          {
            if (k < 13) {
              break;
            }
            return j - paramInt - k;
          }
          i = k + 1;
          int m = j + 1;
          j = m;
          k = i;
          if (m < n)
          {
            c = paramCharSequence.charAt(m);
            j = m;
            k = i;
          }
        }
        i = j;
        if (k > 0) {
          break;
        }
        i = j;
      } while (!isText(paramCharSequence.charAt(j)));
      i = j + 1;
    }
  }
  
  private static void encodeBinary(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, StringBuilder paramStringBuilder)
  {
    if ((paramInt2 == 1) && (paramInt3 == 0)) {
      paramStringBuilder.append('Α');
    }
    paramInt3 = paramInt1;
    int i = paramInt3;
    char[] arrayOfChar;
    if (paramInt2 >= 6)
    {
      paramStringBuilder.append('Μ');
      arrayOfChar = new char[5];
      if (paramInt1 + paramInt2 - paramInt3 < 6) {
        i = paramInt3;
      }
    }
    else if (i < paramInt1 + paramInt2)
    {
      paramStringBuilder.append('΅');
    }
    for (;;)
    {
      if (i >= paramInt1 + paramInt2)
      {
        return;
        long l = 0L;
        i = 0;
        label88:
        if (i >= 6)
        {
          i = 0;
          label98:
          if (i < 5) {
            break label152;
          }
          i = arrayOfChar.length - 1;
        }
        for (;;)
        {
          if (i < 0)
          {
            paramInt3 += 6;
            break;
            l = (l << 8) + (paramArrayOfByte[(paramInt3 + i)] & 0xFF);
            i += 1;
            break label88;
            label152:
            arrayOfChar[i] = ((char)(int)(l % 900L));
            l /= 900L;
            i += 1;
            break label98;
          }
          paramStringBuilder.append(arrayOfChar[i]);
          i -= 1;
        }
      }
      paramStringBuilder.append((char)(paramArrayOfByte[i] & 0xFF));
      i += 1;
    }
  }
  
  static String encodeHighLevel(String paramString, Compaction paramCompaction)
    throws WriterException
  {
    Object localObject = null;
    StringBuilder localStringBuilder = new StringBuilder(paramString.length());
    int i1 = paramString.length();
    int j = 0;
    int i = 0;
    if (paramCompaction == Compaction.TEXT) {
      encodeText(paramString, 0, i1, localStringBuilder, 0);
    }
    for (;;)
    {
      return localStringBuilder.toString();
      if (paramCompaction == Compaction.BYTE)
      {
        paramString = getBytesForMessage(paramString);
        encodeBinary(paramString, 0, paramString.length, 1, localStringBuilder);
      }
      else
      {
        if (paramCompaction != Compaction.NUMERIC) {
          break;
        }
        localStringBuilder.append('Ά');
        encodeNumeric(paramString, 0, i1, localStringBuilder);
      }
    }
    int k = 0;
    paramCompaction = (Compaction)localObject;
    label109:
    int m;
    while (j < i1)
    {
      m = determineConsecutiveDigitCount(paramString, j);
      if (m >= 13)
      {
        localStringBuilder.append('Ά');
        k = 2;
        i = 0;
        encodeNumeric(paramString, j, m, localStringBuilder);
        j += m;
      }
      else
      {
        int n = determineConsecutiveTextCount(paramString, j);
        if ((n >= 5) || (m == i1))
        {
          m = k;
          if (k != 0)
          {
            localStringBuilder.append('΄');
            m = 0;
            i = 0;
          }
          i = encodeText(paramString, j, n, localStringBuilder, i);
          j += n;
          k = m;
        }
        else
        {
          localObject = paramCompaction;
          if (paramCompaction == null) {
            localObject = getBytesForMessage(paramString);
          }
          n = determineConsecutiveBinaryCount(paramString, (byte[])localObject, j);
          m = n;
          if (n == 0) {
            m = 1;
          }
          if ((m != 1) || (k != 0)) {
            break label292;
          }
          encodeBinary((byte[])localObject, j, 1, 0, localStringBuilder);
        }
      }
    }
    for (;;)
    {
      j += m;
      paramCompaction = (Compaction)localObject;
      break label109;
      break;
      label292:
      encodeBinary((byte[])localObject, j, m, k, localStringBuilder);
      k = 1;
      i = 0;
    }
  }
  
  private static void encodeNumeric(String paramString, int paramInt1, int paramInt2, StringBuilder paramStringBuilder)
  {
    int i = 0;
    StringBuilder localStringBuilder = new StringBuilder(paramInt2 / 3 + 1);
    BigInteger localBigInteger2 = BigInteger.valueOf(900L);
    BigInteger localBigInteger3 = BigInteger.valueOf(0L);
    if (i >= paramInt2 - 1) {
      return;
    }
    localStringBuilder.setLength(0);
    int k = Math.min(44, paramInt2 - i);
    Object localObject = new BigInteger('1' + paramString.substring(paramInt1 + i, paramInt1 + i + k));
    BigInteger localBigInteger1;
    do
    {
      localStringBuilder.append((char)((BigInteger)localObject).mod(localBigInteger2).intValue());
      localBigInteger1 = ((BigInteger)localObject).divide(localBigInteger2);
      localObject = localBigInteger1;
    } while (!localBigInteger1.equals(localBigInteger3));
    int j = localStringBuilder.length() - 1;
    for (;;)
    {
      if (j < 0)
      {
        i += k;
        break;
      }
      paramStringBuilder.append(localStringBuilder.charAt(j));
      j -= 1;
    }
  }
  
  private static int encodeText(CharSequence paramCharSequence, int paramInt1, int paramInt2, StringBuilder paramStringBuilder, int paramInt3)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramInt2);
    int j = 0;
    int i;
    for (;;)
    {
      i = paramCharSequence.charAt(paramInt1 + j);
      switch (paramInt3)
      {
      default: 
        if (isPunctuation(i)) {
          localStringBuilder.append((char)PUNCTUATION[i]);
        }
        break;
      case 0: 
      case 1: 
      case 2: 
        for (;;)
        {
          int k = j + 1;
          j = k;
          if (k < paramInt2) {
            break;
          }
          paramInt2 = 0;
          k = localStringBuilder.length();
          paramInt1 = 0;
          if (paramInt1 < k) {
            break label504;
          }
          if (k % 2 != 0) {
            paramStringBuilder.append((char)(paramInt2 * 30 + 29));
          }
          return paramInt3;
          if (isAlphaUpper(i))
          {
            if (i == 32) {
              localStringBuilder.append('\032');
            } else {
              localStringBuilder.append((char)(i - 65));
            }
          }
          else
          {
            if (isAlphaLower(i))
            {
              paramInt3 = 1;
              localStringBuilder.append('\033');
              break;
            }
            if (isMixed(i))
            {
              paramInt3 = 2;
              localStringBuilder.append('\034');
              break;
            }
            localStringBuilder.append('\035');
            localStringBuilder.append((char)PUNCTUATION[i]);
            continue;
            if (isAlphaLower(i))
            {
              if (i == 32) {
                localStringBuilder.append('\032');
              } else {
                localStringBuilder.append((char)(i - 97));
              }
            }
            else if (isAlphaUpper(i))
            {
              localStringBuilder.append('\033');
              localStringBuilder.append((char)(i - 65));
            }
            else
            {
              if (isMixed(i))
              {
                paramInt3 = 2;
                localStringBuilder.append('\034');
                break;
              }
              localStringBuilder.append('\035');
              localStringBuilder.append((char)PUNCTUATION[i]);
              continue;
              if (isMixed(i))
              {
                localStringBuilder.append((char)MIXED[i]);
              }
              else
              {
                if (isAlphaUpper(i))
                {
                  paramInt3 = 0;
                  localStringBuilder.append('\034');
                  break;
                }
                if (isAlphaLower(i))
                {
                  paramInt3 = 1;
                  localStringBuilder.append('\033');
                  break;
                }
                if ((paramInt1 + j + 1 < paramInt2) && (isPunctuation(paramCharSequence.charAt(paramInt1 + j + 1))))
                {
                  paramInt3 = 3;
                  localStringBuilder.append('\031');
                  break;
                }
                localStringBuilder.append('\035');
                localStringBuilder.append((char)PUNCTUATION[i]);
              }
            }
          }
        }
      }
      paramInt3 = 0;
      localStringBuilder.append('\035');
    }
    label504:
    if (paramInt1 % 2 != 0)
    {
      j = 1;
      label513:
      if (j == 0) {
        break label555;
      }
      i = (char)(paramInt2 * 30 + localStringBuilder.charAt(paramInt1));
      paramStringBuilder.append(i);
    }
    label555:
    for (paramInt2 = i;; paramInt2 = localStringBuilder.charAt(paramInt1))
    {
      paramInt1 += 1;
      break;
      j = 0;
      break label513;
    }
  }
  
  private static byte[] getBytesForMessage(String paramString)
  {
    return paramString.getBytes();
  }
  
  private static boolean isAlphaLower(char paramChar)
  {
    return (paramChar == ' ') || ((paramChar >= 'a') && (paramChar <= 'z'));
  }
  
  private static boolean isAlphaUpper(char paramChar)
  {
    return (paramChar == ' ') || ((paramChar >= 'A') && (paramChar <= 'Z'));
  }
  
  private static boolean isDigit(char paramChar)
  {
    return (paramChar >= '0') && (paramChar <= '9');
  }
  
  private static boolean isMixed(char paramChar)
  {
    return MIXED[paramChar] != -1;
  }
  
  private static boolean isPunctuation(char paramChar)
  {
    return PUNCTUATION[paramChar] != -1;
  }
  
  private static boolean isText(char paramChar)
  {
    return (paramChar == '\t') || (paramChar == '\n') || (paramChar == '\r') || ((paramChar >= ' ') && (paramChar <= '~'));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\pdf417\encoder\PDF417HighLevelEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */