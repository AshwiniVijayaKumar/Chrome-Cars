package com.google.zxing.datamatrix.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;

public final class Decoder
{
  private final ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GenericGF.DATA_MATRIX_FIELD_256);
  
  private void correctErrors(byte[] paramArrayOfByte, int paramInt)
    throws ChecksumException
  {
    int j = paramArrayOfByte.length;
    int[] arrayOfInt = new int[j];
    int i = 0;
    if (i >= j) {
      i = paramArrayOfByte.length;
    }
    for (;;)
    {
      try
      {
        this.rsDecoder.decode(arrayOfInt, i - paramInt);
        i = 0;
        if (i < paramInt) {
          break label64;
        }
        return;
      }
      catch (ReedSolomonException paramArrayOfByte)
      {
        throw ChecksumException.getChecksumInstance();
      }
      paramArrayOfByte[i] &= 0xFF;
      i += 1;
      break;
      label64:
      paramArrayOfByte[i] = ((byte)arrayOfInt[i]);
      i += 1;
    }
  }
  
  public DecoderResult decode(BitMatrix paramBitMatrix)
    throws FormatException, ChecksumException
  {
    paramBitMatrix = new BitMatrixParser(paramBitMatrix);
    Object localObject1 = paramBitMatrix.getVersion();
    paramBitMatrix = DataBlock.getDataBlocks(paramBitMatrix.readCodewords(), (Version)localObject1);
    int k = paramBitMatrix.length;
    int j = 0;
    int m = paramBitMatrix.length;
    int i = 0;
    for (;;)
    {
      if (i >= m)
      {
        localObject1 = new byte[j];
        i = 0;
        if (i < k) {
          break;
        }
        return DecodedBitStreamParser.decode((byte[])localObject1);
      }
      j += paramBitMatrix[i].getNumDataCodewords();
      i += 1;
    }
    Object localObject2 = paramBitMatrix[i];
    byte[] arrayOfByte = ((DataBlock)localObject2).getCodewords();
    m = ((DataBlock)localObject2).getNumDataCodewords();
    correctErrors(arrayOfByte, m);
    j = 0;
    for (;;)
    {
      if (j >= m)
      {
        i += 1;
        break;
      }
      localObject1[(j * k + i)] = arrayOfByte[j];
      j += 1;
    }
  }
  
  public DecoderResult decode(boolean[][] paramArrayOfBoolean)
    throws FormatException, ChecksumException
  {
    int k = paramArrayOfBoolean.length;
    BitMatrix localBitMatrix = new BitMatrix(k);
    int i = 0;
    if (i >= k) {
      return decode(localBitMatrix);
    }
    int j = 0;
    for (;;)
    {
      if (j >= k)
      {
        i += 1;
        break;
      }
      if (paramArrayOfBoolean[i][j] != 0) {
        localBitMatrix.set(j, i);
      }
      j += 1;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\datamatrix\decoder\Decoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */