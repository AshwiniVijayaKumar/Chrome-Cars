package com.google.zxing.pdf417.encoder;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.lang.reflect.Array;
import java.util.EnumMap;
import java.util.Map;

public final class PDF417Writer
  implements Writer
{
  private static BitMatrix bitMatrixFromEncoder(PDF417 paramPDF417, String paramString, int paramInt1, int paramInt2)
    throws WriterException
  {
    paramPDF417.generateBarcodeLogic(paramString, 2);
    byte[][] arrayOfByte = paramPDF417.getBarcodeMatrix().getScaledMatrix(2, 8);
    int i = 0;
    int j;
    int k;
    if (paramInt2 > paramInt1)
    {
      j = 1;
      if (arrayOfByte[0].length >= arrayOfByte.length) {
        break label124;
      }
      k = 1;
      label43:
      paramString = arrayOfByte;
      if ((j ^ k) != 0)
      {
        paramString = rotateArray(arrayOfByte);
        i = 1;
      }
      paramInt1 /= paramString[0].length;
      paramInt2 /= paramString.length;
      if (paramInt1 >= paramInt2) {
        break label130;
      }
    }
    for (;;)
    {
      if (paramInt1 <= 1) {
        break label135;
      }
      paramString = paramPDF417.getBarcodeMatrix().getScaledMatrix(paramInt1 * 2, paramInt1 * 4 * 2);
      paramPDF417 = paramString;
      if (i != 0) {
        paramPDF417 = rotateArray(paramString);
      }
      return bitMatrixFrombitArray(paramPDF417);
      j = 0;
      break;
      label124:
      k = 0;
      break label43;
      label130:
      paramInt1 = paramInt2;
    }
    label135:
    return bitMatrixFrombitArray(paramString);
  }
  
  private static BitMatrix bitMatrixFrombitArray(byte[][] paramArrayOfByte)
  {
    BitMatrix localBitMatrix = new BitMatrix(paramArrayOfByte[0].length + 60, paramArrayOfByte.length + 60);
    localBitMatrix.clear();
    int j = 0;
    int i = localBitMatrix.getHeight() - 30;
    if (j >= paramArrayOfByte.length) {
      return localBitMatrix;
    }
    int k = 0;
    for (;;)
    {
      if (k >= paramArrayOfByte[0].length)
      {
        j += 1;
        i -= 1;
        break;
      }
      if (paramArrayOfByte[j][k] == 1) {
        localBitMatrix.set(k + 30, i);
      }
      k += 1;
    }
  }
  
  private static byte[][] rotateArray(byte[][] paramArrayOfByte)
  {
    int i = paramArrayOfByte[0].length;
    int j = paramArrayOfByte.length;
    byte[][] arrayOfByte = (byte[][])Array.newInstance(Byte.TYPE, new int[] { i, j });
    i = 0;
    if (i >= paramArrayOfByte.length) {
      return arrayOfByte;
    }
    int k = paramArrayOfByte.length;
    j = 0;
    for (;;)
    {
      if (j >= paramArrayOfByte[0].length)
      {
        i += 1;
        break;
      }
      arrayOfByte[j][(k - i - 1)] = paramArrayOfByte[i][j];
      j += 1;
    }
  }
  
  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2)
    throws WriterException
  {
    return encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, null);
  }
  
  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2, Map<EncodeHintType, ?> paramMap)
    throws WriterException
  {
    if (paramBarcodeFormat != BarcodeFormat.PDF_417) {
      throw new IllegalArgumentException("Can only encode PDF_417, but got " + paramBarcodeFormat);
    }
    paramBarcodeFormat = new PDF417();
    if (paramMap != null)
    {
      if (paramMap.containsKey(EncodeHintType.PDF417_COMPACT)) {
        paramBarcodeFormat.setCompact(((Boolean)paramMap.get(EncodeHintType.PDF417_COMPACT)).booleanValue());
      }
      if (paramMap.containsKey(EncodeHintType.PDF417_COMPACTION)) {
        paramBarcodeFormat.setCompaction((Compaction)paramMap.get(EncodeHintType.PDF417_COMPACTION));
      }
      if (paramMap.containsKey(EncodeHintType.PDF417_DIMENSIONS))
      {
        paramMap = (Dimensions)paramMap.get(EncodeHintType.PDF417_DIMENSIONS);
        paramBarcodeFormat.setDimensions(paramMap.getMaxCols(), paramMap.getMinCols(), paramMap.getMaxRows(), paramMap.getMinRows());
      }
    }
    return bitMatrixFromEncoder(paramBarcodeFormat, paramString, paramInt1, paramInt2);
  }
  
  @Deprecated
  public BitMatrix encode(String paramString, BarcodeFormat paramBarcodeFormat, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, Compaction paramCompaction)
    throws WriterException
  {
    EnumMap localEnumMap = new EnumMap(EncodeHintType.class);
    localEnumMap.put(EncodeHintType.PDF417_COMPACT, Boolean.valueOf(paramBoolean));
    localEnumMap.put(EncodeHintType.PDF417_COMPACTION, paramCompaction);
    localEnumMap.put(EncodeHintType.PDF417_DIMENSIONS, new Dimensions(paramInt3, paramInt4, paramInt5, paramInt6));
    return encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, localEnumMap);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\pdf417\encoder\PDF417Writer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */