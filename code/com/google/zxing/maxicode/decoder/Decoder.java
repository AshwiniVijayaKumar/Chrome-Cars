package com.google.zxing.maxicode.decoder;

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
  private static final int ALL = 0;
  private static final int EVEN = 1;
  private static final int ODD = 2;
  private final ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GenericGF.MAXICODE_FIELD_64);
  
  private void correctErrors(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws ChecksumException
  {
    int k = paramInt2 + paramInt3;
    int i;
    int[] arrayOfInt;
    int j;
    if (paramInt4 == 0)
    {
      i = 1;
      arrayOfInt = new int[k / i];
      j = 0;
      label26:
      if (j < k) {
        break label63;
      }
    }
    for (;;)
    {
      try
      {
        this.rsDecoder.decode(arrayOfInt, paramInt3 / i);
        paramInt3 = 0;
        if (paramInt3 < paramInt2) {
          break label111;
        }
        return;
      }
      catch (ReedSolomonException paramArrayOfByte)
      {
        label63:
        throw ChecksumException.getChecksumInstance();
      }
      i = 2;
      break;
      if ((paramInt4 == 0) || (j % 2 == paramInt4 - 1)) {
        arrayOfInt[(j / i)] = (paramArrayOfByte[(j + paramInt1)] & 0xFF);
      }
      j += 1;
      break label26;
      label111:
      if ((paramInt4 == 0) || (paramInt3 % 2 == paramInt4 - 1)) {
        paramArrayOfByte[(paramInt3 + paramInt1)] = ((byte)arrayOfInt[(paramInt3 / i)]);
      }
      paramInt3 += 1;
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
    paramMap = new BitMatrixParser(paramBitMatrix).readCodewords();
    correctErrors(paramMap, 0, 10, 10, 0);
    int i = paramMap[0] & 0xF;
    switch (i)
    {
    default: 
      throw FormatException.getFormatInstance();
    case 2: 
    case 3: 
    case 4: 
      correctErrors(paramMap, 20, 84, 40, 1);
      correctErrors(paramMap, 20, 84, 40, 2);
    }
    for (paramBitMatrix = new byte[94];; paramBitMatrix = new byte[78])
    {
      System.arraycopy(paramMap, 0, paramBitMatrix, 0, 10);
      System.arraycopy(paramMap, 20, paramBitMatrix, 10, paramBitMatrix.length - 10);
      return DecodedBitStreamParser.decode(paramBitMatrix, i);
      correctErrors(paramMap, 20, 68, 56, 1);
      correctErrors(paramMap, 20, 68, 56, 2);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\maxicode\decoder\Decoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */