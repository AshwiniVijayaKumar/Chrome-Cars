package com.google.zxing.qrcode.encoder;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;
import com.google.zxing.qrcode.decoder.Version.ECBlocks;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public final class Encoder
{
  private static final int[] ALPHANUMERIC_TABLE;
  static final String DEFAULT_BYTE_MODE_ENCODING = "ISO-8859-1";
  
  static
  {
    int[] arrayOfInt = new int[96];
    arrayOfInt[0] = -1;
    arrayOfInt[1] = -1;
    arrayOfInt[2] = -1;
    arrayOfInt[3] = -1;
    arrayOfInt[4] = -1;
    arrayOfInt[5] = -1;
    arrayOfInt[6] = -1;
    arrayOfInt[7] = -1;
    arrayOfInt[8] = -1;
    arrayOfInt[9] = -1;
    arrayOfInt[10] = -1;
    arrayOfInt[11] = -1;
    arrayOfInt[12] = -1;
    arrayOfInt[13] = -1;
    arrayOfInt[14] = -1;
    arrayOfInt[15] = -1;
    arrayOfInt[16] = -1;
    arrayOfInt[17] = -1;
    arrayOfInt[18] = -1;
    arrayOfInt[19] = -1;
    arrayOfInt[20] = -1;
    arrayOfInt[21] = -1;
    arrayOfInt[22] = -1;
    arrayOfInt[23] = -1;
    arrayOfInt[24] = -1;
    arrayOfInt[25] = -1;
    arrayOfInt[26] = -1;
    arrayOfInt[27] = -1;
    arrayOfInt[28] = -1;
    arrayOfInt[29] = -1;
    arrayOfInt[30] = -1;
    arrayOfInt[31] = -1;
    arrayOfInt[32] = 36;
    arrayOfInt[33] = -1;
    arrayOfInt[34] = -1;
    arrayOfInt[35] = -1;
    arrayOfInt[36] = 37;
    arrayOfInt[37] = 38;
    arrayOfInt[38] = -1;
    arrayOfInt[39] = -1;
    arrayOfInt[40] = -1;
    arrayOfInt[41] = -1;
    arrayOfInt[42] = 39;
    arrayOfInt[43] = 40;
    arrayOfInt[44] = -1;
    arrayOfInt[45] = 41;
    arrayOfInt[46] = 42;
    arrayOfInt[47] = 43;
    arrayOfInt[49] = 1;
    arrayOfInt[50] = 2;
    arrayOfInt[51] = 3;
    arrayOfInt[52] = 4;
    arrayOfInt[53] = 5;
    arrayOfInt[54] = 6;
    arrayOfInt[55] = 7;
    arrayOfInt[56] = 8;
    arrayOfInt[57] = 9;
    arrayOfInt[58] = 44;
    arrayOfInt[59] = -1;
    arrayOfInt[60] = -1;
    arrayOfInt[61] = -1;
    arrayOfInt[62] = -1;
    arrayOfInt[63] = -1;
    arrayOfInt[64] = -1;
    arrayOfInt[65] = 10;
    arrayOfInt[66] = 11;
    arrayOfInt[67] = 12;
    arrayOfInt[68] = 13;
    arrayOfInt[69] = 14;
    arrayOfInt[70] = 15;
    arrayOfInt[71] = 16;
    arrayOfInt[72] = 17;
    arrayOfInt[73] = 18;
    arrayOfInt[74] = 19;
    arrayOfInt[75] = 20;
    arrayOfInt[76] = 21;
    arrayOfInt[77] = 22;
    arrayOfInt[78] = 23;
    arrayOfInt[79] = 24;
    arrayOfInt[80] = 25;
    arrayOfInt[81] = 26;
    arrayOfInt[82] = 27;
    arrayOfInt[83] = 28;
    arrayOfInt[84] = 29;
    arrayOfInt[85] = 30;
    arrayOfInt[86] = 31;
    arrayOfInt[87] = 32;
    arrayOfInt[88] = 33;
    arrayOfInt[89] = 34;
    arrayOfInt[90] = 35;
    arrayOfInt[91] = -1;
    arrayOfInt[92] = -1;
    arrayOfInt[93] = -1;
    arrayOfInt[94] = -1;
    arrayOfInt[95] = -1;
    ALPHANUMERIC_TABLE = arrayOfInt;
  }
  
  static void append8BitBytes(String paramString1, BitArray paramBitArray, String paramString2)
    throws WriterException
  {
    for (;;)
    {
      int i;
      try
      {
        paramString1 = paramString1.getBytes(paramString2);
        int j = paramString1.length;
        i = 0;
        if (i >= j) {
          return;
        }
      }
      catch (UnsupportedEncodingException paramString1)
      {
        throw new WriterException(paramString1);
      }
      paramBitArray.appendBits(paramString1[i], 8);
      i += 1;
    }
  }
  
  static void appendAlphanumericBytes(CharSequence paramCharSequence, BitArray paramBitArray)
    throws WriterException
  {
    int j = paramCharSequence.length();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return;
      }
      int k = getAlphanumericCode(paramCharSequence.charAt(i));
      if (k == -1) {
        throw new WriterException();
      }
      if (i + 1 < j)
      {
        int m = getAlphanumericCode(paramCharSequence.charAt(i + 1));
        if (m == -1) {
          throw new WriterException();
        }
        paramBitArray.appendBits(k * 45 + m, 11);
        i += 2;
      }
      else
      {
        paramBitArray.appendBits(k, 6);
        i += 1;
      }
    }
  }
  
  static void appendBytes(String paramString1, Mode paramMode, BitArray paramBitArray, String paramString2)
    throws WriterException
  {
    switch (paramMode)
    {
    case FNC1_FIRST_POSITION: 
    case HANZI: 
    default: 
      throw new WriterException("Invalid mode: " + paramMode);
    case BYTE: 
      appendNumericBytes(paramString1, paramBitArray);
      return;
    case ECI: 
      appendAlphanumericBytes(paramString1, paramBitArray);
      return;
    case FNC1_SECOND_POSITION: 
      append8BitBytes(paramString1, paramBitArray, paramString2);
      return;
    }
    appendKanjiBytes(paramString1, paramBitArray);
  }
  
  private static void appendECI(CharacterSetECI paramCharacterSetECI, BitArray paramBitArray)
  {
    paramBitArray.appendBits(Mode.ECI.getBits(), 4);
    paramBitArray.appendBits(paramCharacterSetECI.getValue(), 8);
  }
  
  static void appendKanjiBytes(String paramString, BitArray paramBitArray)
    throws WriterException
  {
    for (;;)
    {
      int j;
      try
      {
        paramString = paramString.getBytes("Shift_JIS");
        int m = paramString.length;
        j = 0;
        if (j >= m) {
          return;
        }
      }
      catch (UnsupportedEncodingException paramString)
      {
        throw new WriterException(paramString);
      }
      int n = (paramString[j] & 0xFF) << 8 | paramString[(j + 1)] & 0xFF;
      int k = -1;
      int i;
      if ((n >= 33088) && (n <= 40956)) {
        i = n - 33088;
      }
      while (i == -1)
      {
        throw new WriterException("Invalid byte sequence");
        i = k;
        if (n >= 57408)
        {
          i = k;
          if (n <= 60351) {
            i = n - 49472;
          }
        }
      }
      paramBitArray.appendBits((i >> 8) * 192 + (i & 0xFF), 13);
      j += 2;
    }
  }
  
  static void appendLengthInfo(int paramInt, Version paramVersion, Mode paramMode, BitArray paramBitArray)
    throws WriterException
  {
    int i = paramMode.getCharacterCountBits(paramVersion);
    if (paramInt >= 1 << i) {
      throw new WriterException(paramInt + " is bigger than " + ((1 << i) - 1));
    }
    paramBitArray.appendBits(paramInt, i);
  }
  
  static void appendModeInfo(Mode paramMode, BitArray paramBitArray)
  {
    paramBitArray.appendBits(paramMode.getBits(), 4);
  }
  
  static void appendNumericBytes(CharSequence paramCharSequence, BitArray paramBitArray)
  {
    int j = paramCharSequence.length();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return;
      }
      int k = paramCharSequence.charAt(i) - '0';
      if (i + 2 < j)
      {
        paramBitArray.appendBits(k * 100 + (paramCharSequence.charAt(i + 1) - '0') * 10 + (paramCharSequence.charAt(i + 2) - '0'), 10);
        i += 3;
      }
      else if (i + 1 < j)
      {
        paramBitArray.appendBits(k * 10 + (paramCharSequence.charAt(i + 1) - '0'), 7);
        i += 2;
      }
      else
      {
        paramBitArray.appendBits(k, 4);
        i += 1;
      }
    }
  }
  
  private static int calculateMaskPenalty(ByteMatrix paramByteMatrix)
  {
    return MaskUtil.applyMaskPenaltyRule1(paramByteMatrix) + MaskUtil.applyMaskPenaltyRule2(paramByteMatrix) + MaskUtil.applyMaskPenaltyRule3(paramByteMatrix) + MaskUtil.applyMaskPenaltyRule4(paramByteMatrix);
  }
  
  private static int chooseMaskPattern(BitArray paramBitArray, ErrorCorrectionLevel paramErrorCorrectionLevel, Version paramVersion, ByteMatrix paramByteMatrix)
    throws WriterException
  {
    int j = Integer.MAX_VALUE;
    int m = -1;
    int i = 0;
    for (;;)
    {
      if (i >= 8) {
        return m;
      }
      MatrixUtil.buildMatrix(paramBitArray, paramErrorCorrectionLevel, paramVersion, i, paramByteMatrix);
      int n = calculateMaskPenalty(paramByteMatrix);
      int k = j;
      if (n < j)
      {
        k = n;
        m = i;
      }
      i += 1;
      j = k;
    }
  }
  
  public static Mode chooseMode(String paramString)
  {
    return chooseMode(paramString, null);
  }
  
  private static Mode chooseMode(String paramString1, String paramString2)
  {
    if ("Shift_JIS".equals(paramString2))
    {
      if (isOnlyDoubleByteKanji(paramString1)) {
        return Mode.KANJI;
      }
      return Mode.BYTE;
    }
    int j = 0;
    int k = 0;
    int i = 0;
    if (i >= paramString1.length())
    {
      if (k != 0) {
        return Mode.ALPHANUMERIC;
      }
    }
    else
    {
      int m = paramString1.charAt(i);
      if ((m >= 48) && (m <= 57)) {
        j = 1;
      }
      for (;;)
      {
        i += 1;
        break;
        if (getAlphanumericCode(m) == -1) {
          break label93;
        }
        k = 1;
      }
      label93:
      return Mode.BYTE;
    }
    if (j != 0) {
      return Mode.NUMERIC;
    }
    return Mode.BYTE;
  }
  
  private static Version chooseVersion(int paramInt, ErrorCorrectionLevel paramErrorCorrectionLevel)
    throws WriterException
  {
    int i = 1;
    for (;;)
    {
      if (i > 40) {
        throw new WriterException("Data too big");
      }
      Version localVersion = Version.getVersionForNumber(i);
      if (localVersion.getTotalCodewords() - localVersion.getECBlocksForLevel(paramErrorCorrectionLevel).getTotalECCodewords() >= (paramInt + 7) / 8) {
        return localVersion;
      }
      i += 1;
    }
  }
  
  public static QRCode encode(String paramString, ErrorCorrectionLevel paramErrorCorrectionLevel)
    throws WriterException
  {
    return encode(paramString, paramErrorCorrectionLevel, null);
  }
  
  public static QRCode encode(String paramString, ErrorCorrectionLevel paramErrorCorrectionLevel, Map<EncodeHintType, ?> paramMap)
    throws WriterException
  {
    Object localObject1;
    Object localObject2;
    Object localObject3;
    BitArray localBitArray;
    if (paramMap == null)
    {
      paramMap = null;
      localObject1 = paramMap;
      if (paramMap == null) {
        localObject1 = "ISO-8859-1";
      }
      paramMap = chooseMode(paramString, (String)localObject1);
      localObject2 = new BitArray();
      if ((paramMap == Mode.BYTE) && (!"ISO-8859-1".equals(localObject1)))
      {
        localObject3 = CharacterSetECI.getCharacterSetECIByName((String)localObject1);
        if (localObject3 != null) {
          appendECI((CharacterSetECI)localObject3, (BitArray)localObject2);
        }
      }
      appendModeInfo(paramMap, (BitArray)localObject2);
      localObject3 = new BitArray();
      appendBytes(paramString, paramMap, (BitArray)localObject3, (String)localObject1);
      localObject1 = chooseVersion(((BitArray)localObject2).getSize() + paramMap.getCharacterCountBits(Version.getVersionForNumber(1)) + ((BitArray)localObject3).getSize(), paramErrorCorrectionLevel);
      localObject1 = chooseVersion(((BitArray)localObject2).getSize() + paramMap.getCharacterCountBits((Version)localObject1) + ((BitArray)localObject3).getSize(), paramErrorCorrectionLevel);
      localBitArray = new BitArray();
      localBitArray.appendBitArray((BitArray)localObject2);
      if (paramMap != Mode.BYTE) {
        break label321;
      }
    }
    label321:
    for (int i = ((BitArray)localObject3).getSizeInBytes();; i = paramString.length())
    {
      appendLengthInfo(i, (Version)localObject1, paramMap, localBitArray);
      localBitArray.appendBitArray((BitArray)localObject3);
      paramString = ((Version)localObject1).getECBlocksForLevel(paramErrorCorrectionLevel);
      i = ((Version)localObject1).getTotalCodewords() - paramString.getTotalECCodewords();
      terminateBits(i, localBitArray);
      paramString = interleaveWithECBytes(localBitArray, ((Version)localObject1).getTotalCodewords(), i, paramString.getNumBlocks());
      localObject2 = new QRCode();
      ((QRCode)localObject2).setECLevel(paramErrorCorrectionLevel);
      ((QRCode)localObject2).setMode(paramMap);
      ((QRCode)localObject2).setVersion((Version)localObject1);
      i = ((Version)localObject1).getDimensionForVersion();
      paramMap = new ByteMatrix(i, i);
      i = chooseMaskPattern(paramString, paramErrorCorrectionLevel, (Version)localObject1, paramMap);
      ((QRCode)localObject2).setMaskPattern(i);
      MatrixUtil.buildMatrix(paramString, paramErrorCorrectionLevel, (Version)localObject1, i, paramMap);
      ((QRCode)localObject2).setMatrix(paramMap);
      return (QRCode)localObject2;
      paramMap = (String)paramMap.get(EncodeHintType.CHARACTER_SET);
      break;
    }
  }
  
  static byte[] generateECBytes(byte[] paramArrayOfByte, int paramInt)
  {
    int j = paramArrayOfByte.length;
    int[] arrayOfInt = new int[j + paramInt];
    int i = 0;
    if (i >= j)
    {
      new ReedSolomonEncoder(GenericGF.QR_CODE_FIELD_256).encode(arrayOfInt, paramInt);
      paramArrayOfByte = new byte[paramInt];
      i = 0;
    }
    for (;;)
    {
      if (i >= paramInt)
      {
        return paramArrayOfByte;
        paramArrayOfByte[i] &= 0xFF;
        i += 1;
        break;
      }
      paramArrayOfByte[i] = ((byte)arrayOfInt[(j + i)]);
      i += 1;
    }
  }
  
  static int getAlphanumericCode(int paramInt)
  {
    if (paramInt < ALPHANUMERIC_TABLE.length) {
      return ALPHANUMERIC_TABLE[paramInt];
    }
    return -1;
  }
  
  static void getNumDataBytesAndNumECBytesForBlockID(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
    throws WriterException
  {
    if (paramInt4 >= paramInt3) {
      throw new WriterException("Block ID too large");
    }
    int i = paramInt1 % paramInt3;
    int j = paramInt3 - i;
    int k = paramInt1 / paramInt3;
    paramInt2 /= paramInt3;
    int m = paramInt2 + 1;
    int n = k - paramInt2;
    k = k + 1 - m;
    if (n != k) {
      throw new WriterException("EC bytes mismatch");
    }
    if (paramInt3 != j + i) {
      throw new WriterException("RS blocks mismatch");
    }
    if (paramInt1 != (paramInt2 + n) * j + (m + k) * i) {
      throw new WriterException("Total bytes mismatch");
    }
    if (paramInt4 < j)
    {
      paramArrayOfInt1[0] = paramInt2;
      paramArrayOfInt2[0] = n;
      return;
    }
    paramArrayOfInt1[0] = m;
    paramArrayOfInt2[0] = k;
  }
  
  static BitArray interleaveWithECBytes(BitArray paramBitArray, int paramInt1, int paramInt2, int paramInt3)
    throws WriterException
  {
    if (paramBitArray.getSizeInBytes() != paramInt2) {
      throw new WriterException("Number of bits and data bytes does not match");
    }
    int m = 0;
    int j = 0;
    int i = 0;
    ArrayList localArrayList = new ArrayList(paramInt3);
    int k = 0;
    byte[] arrayOfByte;
    for (;;)
    {
      if (k >= paramInt3)
      {
        if (paramInt2 == m) {
          break;
        }
        throw new WriterException("Data bytes does not match offset");
      }
      localObject1 = new int[1];
      Object localObject2 = new int[1];
      getNumDataBytesAndNumECBytesForBlockID(paramInt1, paramInt2, paramInt3, k, (int[])localObject1, (int[])localObject2);
      int n = localObject1[0];
      arrayOfByte = new byte[n];
      paramBitArray.toBytes(m * 8, arrayOfByte, 0, n);
      localObject2 = generateECBytes(arrayOfByte, localObject2[0]);
      localArrayList.add(new BlockPair(arrayOfByte, (byte[])localObject2));
      j = Math.max(j, n);
      i = Math.max(i, localObject2.length);
      m += localObject1[0];
      k += 1;
    }
    paramBitArray = new BitArray();
    paramInt2 = 0;
    if (paramInt2 >= j)
    {
      paramInt2 = 0;
      if (paramInt2 >= i)
      {
        if (paramInt1 == paramBitArray.getSizeInBytes()) {
          return paramBitArray;
        }
        throw new WriterException("Interleaving error: " + paramInt1 + " and " + paramBitArray.getSizeInBytes() + " differ.");
      }
    }
    else
    {
      localObject1 = localArrayList.iterator();
      for (;;)
      {
        if (!((Iterator)localObject1).hasNext())
        {
          paramInt2 += 1;
          break;
        }
        arrayOfByte = ((BlockPair)((Iterator)localObject1).next()).getDataBytes();
        if (paramInt2 < arrayOfByte.length) {
          paramBitArray.appendBits(arrayOfByte[paramInt2], 8);
        }
      }
    }
    Object localObject1 = localArrayList.iterator();
    for (;;)
    {
      if (!((Iterator)localObject1).hasNext())
      {
        paramInt2 += 1;
        break;
      }
      arrayOfByte = ((BlockPair)((Iterator)localObject1).next()).getErrorCorrectionBytes();
      if (paramInt2 < arrayOfByte.length) {
        paramBitArray.appendBits(arrayOfByte[paramInt2], 8);
      }
    }
    return paramBitArray;
  }
  
  private static boolean isOnlyDoubleByteKanji(String paramString)
  {
    int j;
    try
    {
      paramString = paramString.getBytes("Shift_JIS");
      j = paramString.length;
      if (j % 2 != 0) {
        return false;
      }
    }
    catch (UnsupportedEncodingException paramString)
    {
      return false;
    }
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return true;
      }
      int k = paramString[i] & 0xFF;
      if (((k < 129) || (k > 159)) && ((k < 224) || (k > 235))) {
        break;
      }
      i += 2;
    }
  }
  
  static void terminateBits(int paramInt, BitArray paramBitArray)
    throws WriterException
  {
    int k = paramInt << 3;
    if (paramBitArray.getSize() > k) {
      throw new WriterException("data bits cannot fit in the QR Code" + paramBitArray.getSize() + " > " + k);
    }
    int i = 0;
    if ((i >= 4) || (paramBitArray.getSize() >= k))
    {
      i = paramBitArray.getSize() & 0x7;
      if (i <= 0) {}
    }
    for (;;)
    {
      if (i >= 8)
      {
        int m = paramBitArray.getSizeInBytes();
        i = 0;
        if (i < paramInt - m) {
          break label147;
        }
        if (paramBitArray.getSize() == k) {
          return;
        }
        throw new WriterException("Bits size does not equal capacity");
        paramBitArray.appendBit(false);
        i += 1;
        break;
      }
      paramBitArray.appendBit(false);
      i += 1;
    }
    label147:
    if ((i & 0x1) == 0) {}
    for (int j = 236;; j = 17)
    {
      paramBitArray.appendBits(j, 8);
      i += 1;
      break;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\qrcode\encoder\Encoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */