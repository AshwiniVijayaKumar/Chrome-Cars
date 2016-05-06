package com.google.zxing.qrcode.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import java.util.Map;

public final class Decoder
{
  private final ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GenericGF.QR_CODE_FIELD_256);
  
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
    throws ChecksumException, FormatException
  {
    return decode(paramBitMatrix, null);
  }
  
  public DecoderResult decode(BitMatrix paramBitMatrix, Map<DecodeHintType, ?> paramMap)
    throws FormatException, ChecksumException
  {
    Object localObject1 = new BitMatrixParser(paramBitMatrix);
    paramBitMatrix = ((BitMatrixParser)localObject1).readVersion();
    ErrorCorrectionLevel localErrorCorrectionLevel = ((BitMatrixParser)localObject1).readFormatInformation().getErrorCorrectionLevel();
    localObject1 = DataBlock.getDataBlocks(((BitMatrixParser)localObject1).readCodewords(), paramBitMatrix, localErrorCorrectionLevel);
    int j = 0;
    int k = localObject1.length;
    int i = 0;
    byte[] arrayOfByte1;
    for (;;)
    {
      if (i >= k)
      {
        arrayOfByte1 = new byte[j];
        i = 0;
        int m = localObject1.length;
        j = 0;
        if (j < m) {
          break;
        }
        return DecodedBitStreamParser.decode(arrayOfByte1, paramBitMatrix, localErrorCorrectionLevel, paramMap);
      }
      j += localObject1[i].getNumDataCodewords();
      i += 1;
    }
    Object localObject2 = localObject1[j];
    byte[] arrayOfByte2 = ((DataBlock)localObject2).getCodewords();
    int n = ((DataBlock)localObject2).getNumDataCodewords();
    correctErrors(arrayOfByte2, n);
    k = 0;
    for (;;)
    {
      if (k >= n)
      {
        j += 1;
        break;
      }
      arrayOfByte1[i] = arrayOfByte2[k];
      k += 1;
      i += 1;
    }
  }
  
  public DecoderResult decode(boolean[][] paramArrayOfBoolean)
    throws ChecksumException, FormatException
  {
    return decode(paramArrayOfBoolean, null);
  }
  
  public DecoderResult decode(boolean[][] paramArrayOfBoolean, Map<DecodeHintType, ?> paramMap)
    throws ChecksumException, FormatException
  {
    int k = paramArrayOfBoolean.length;
    BitMatrix localBitMatrix = new BitMatrix(k);
    int i = 0;
    if (i >= k) {
      return decode(localBitMatrix, paramMap);
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


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\qrcode\decoder\Decoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */