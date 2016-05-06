package com.google.zxing.qrcode.decoder;

final class FormatInformation
{
  private static final int[] BITS_SET_IN_HALF_BYTE;
  private static final int[][] FORMAT_INFO_DECODE_LOOKUP;
  private static final int FORMAT_INFO_MASK_QR = 21522;
  private final byte dataMask;
  private final ErrorCorrectionLevel errorCorrectionLevel;
  
  static
  {
    int[] arrayOfInt1 = new int[2];
    arrayOfInt1[0] = 21522;
    int[] arrayOfInt2 = { 20773, 1 };
    int[] arrayOfInt3 = { 24188, 2 };
    int[] arrayOfInt4 = { 23371, 3 };
    int[] arrayOfInt5 = { 17913, 4 };
    int[] arrayOfInt6 = { 16590, 5 };
    int[] arrayOfInt7 = { 19104, 7 };
    int[] arrayOfInt8 = { 29427, 9 };
    int[] arrayOfInt9 = { 32170, 10 };
    int[] arrayOfInt10 = { 30877, 11 };
    int[] arrayOfInt11 = { 26159, 12 };
    int[] arrayOfInt12 = { 25368, 13 };
    int[] arrayOfInt13 = { 26998, 15 };
    int[] arrayOfInt14 = { 5769, 16 };
    int[] arrayOfInt15 = { 5054, 17 };
    int[] arrayOfInt16 = { 7399, 18 };
    int[] arrayOfInt17 = { 597, 21 };
    int[] arrayOfInt18 = { 2107, 23 };
    int[] arrayOfInt19 = { 16177, 26 };
    int[] arrayOfInt20 = { 9396, 28 };
    int[] arrayOfInt21 = { 11994, 30 };
    int[] arrayOfInt22 = { 11245, 31 };
    FORMAT_INFO_DECODE_LOOKUP = new int[][] { arrayOfInt1, arrayOfInt2, arrayOfInt3, arrayOfInt4, arrayOfInt5, arrayOfInt6, { 20375, 6 }, arrayOfInt7, { 30660, 8 }, arrayOfInt8, arrayOfInt9, arrayOfInt10, arrayOfInt11, arrayOfInt12, { 27713, 14 }, arrayOfInt13, arrayOfInt14, arrayOfInt15, arrayOfInt16, { 6608, 19 }, { 1890, 20 }, arrayOfInt17, { 3340, 22 }, arrayOfInt18, { 13663, 24 }, { 12392, 25 }, arrayOfInt19, { 14854, 27 }, arrayOfInt20, { 8579, 29 }, arrayOfInt21, arrayOfInt22 };
    arrayOfInt1 = new int[16];
    arrayOfInt1[1] = 1;
    arrayOfInt1[2] = 1;
    arrayOfInt1[3] = 2;
    arrayOfInt1[4] = 1;
    arrayOfInt1[5] = 2;
    arrayOfInt1[6] = 2;
    arrayOfInt1[7] = 3;
    arrayOfInt1[8] = 1;
    arrayOfInt1[9] = 2;
    arrayOfInt1[10] = 2;
    arrayOfInt1[11] = 3;
    arrayOfInt1[12] = 2;
    arrayOfInt1[13] = 3;
    arrayOfInt1[14] = 3;
    arrayOfInt1[15] = 4;
    BITS_SET_IN_HALF_BYTE = arrayOfInt1;
  }
  
  private FormatInformation(int paramInt)
  {
    this.errorCorrectionLevel = ErrorCorrectionLevel.forBits(paramInt >> 3 & 0x3);
    this.dataMask = ((byte)(paramInt & 0x7));
  }
  
  static FormatInformation decodeFormatInformation(int paramInt1, int paramInt2)
  {
    FormatInformation localFormatInformation = doDecodeFormatInformation(paramInt1, paramInt2);
    if (localFormatInformation != null) {
      return localFormatInformation;
    }
    return doDecodeFormatInformation(paramInt1 ^ 0x5412, paramInt2 ^ 0x5412);
  }
  
  private static FormatInformation doDecodeFormatInformation(int paramInt1, int paramInt2)
  {
    int m = Integer.MAX_VALUE;
    int j = 0;
    int[][] arrayOfInt = FORMAT_INFO_DECODE_LOOKUP;
    int i2 = arrayOfInt.length;
    int n = 0;
    for (;;)
    {
      if (n >= i2)
      {
        if (m > 3) {
          break;
        }
        return new FormatInformation(j);
      }
      int[] arrayOfInt1 = arrayOfInt[n];
      int i1 = arrayOfInt1[0];
      if ((i1 == paramInt1) || (i1 == paramInt2)) {
        return new FormatInformation(arrayOfInt1[1]);
      }
      int k = numBitsDiffering(paramInt1, i1);
      int i = m;
      if (k < m)
      {
        j = arrayOfInt1[1];
        i = k;
      }
      m = i;
      k = j;
      if (paramInt1 != paramInt2)
      {
        i1 = numBitsDiffering(paramInt2, i1);
        m = i;
        k = j;
        if (i1 < i)
        {
          k = arrayOfInt1[1];
          m = i1;
        }
      }
      n += 1;
      j = k;
    }
    return null;
  }
  
  static int numBitsDiffering(int paramInt1, int paramInt2)
  {
    paramInt1 ^= paramInt2;
    return BITS_SET_IN_HALF_BYTE[(paramInt1 & 0xF)] + BITS_SET_IN_HALF_BYTE[(paramInt1 >>> 4 & 0xF)] + BITS_SET_IN_HALF_BYTE[(paramInt1 >>> 8 & 0xF)] + BITS_SET_IN_HALF_BYTE[(paramInt1 >>> 12 & 0xF)] + BITS_SET_IN_HALF_BYTE[(paramInt1 >>> 16 & 0xF)] + BITS_SET_IN_HALF_BYTE[(paramInt1 >>> 20 & 0xF)] + BITS_SET_IN_HALF_BYTE[(paramInt1 >>> 24 & 0xF)] + BITS_SET_IN_HALF_BYTE[(paramInt1 >>> 28 & 0xF)];
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof FormatInformation)) {}
    do
    {
      return false;
      paramObject = (FormatInformation)paramObject;
    } while ((this.errorCorrectionLevel != ((FormatInformation)paramObject).errorCorrectionLevel) || (this.dataMask != ((FormatInformation)paramObject).dataMask));
    return true;
  }
  
  byte getDataMask()
  {
    return this.dataMask;
  }
  
  ErrorCorrectionLevel getErrorCorrectionLevel()
  {
    return this.errorCorrectionLevel;
  }
  
  public int hashCode()
  {
    return this.errorCorrectionLevel.ordinal() << 3 | this.dataMask;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\qrcode\decoder\FormatInformation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */