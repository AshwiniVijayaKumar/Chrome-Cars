package com.google.zxing.qrcode.decoder;

final class DataBlock
{
  private final byte[] codewords;
  private final int numDataCodewords;
  
  private DataBlock(int paramInt, byte[] paramArrayOfByte)
  {
    this.numDataCodewords = paramInt;
    this.codewords = paramArrayOfByte;
  }
  
  static DataBlock[] getDataBlocks(byte[] paramArrayOfByte, Version paramVersion, ErrorCorrectionLevel paramErrorCorrectionLevel)
  {
    if (paramArrayOfByte.length != paramVersion.getTotalCodewords()) {
      throw new IllegalArgumentException();
    }
    paramVersion = paramVersion.getECBlocksForLevel(paramErrorCorrectionLevel);
    int j = 0;
    paramErrorCorrectionLevel = paramVersion.getECBlocks();
    int k = paramErrorCorrectionLevel.length;
    int i = 0;
    DataBlock[] arrayOfDataBlock;
    int m;
    label81:
    label85:
    int i1;
    label115:
    int i2;
    if (i >= k)
    {
      arrayOfDataBlock = new DataBlock[j];
      k = 0;
      m = paramErrorCorrectionLevel.length;
      i = 0;
      if (i < m) {
        break label169;
      }
      j = arrayOfDataBlock[0].codewords.length;
      i = arrayOfDataBlock.length - 1;
      if (i >= 0) {
        break label239;
      }
      i1 = i + 1;
      n = j - paramVersion.getECCodewordsPerBlock();
      i = 0;
      j = 0;
      if (j < n) {
        break label259;
      }
      j = i1;
      if (j < k) {
        break label305;
      }
      i2 = arrayOfDataBlock[0].codewords.length;
      m = n;
      j = i;
      i = m;
    }
    for (;;)
    {
      if (i >= i2)
      {
        return arrayOfDataBlock;
        j += paramErrorCorrectionLevel[i].getCount();
        i += 1;
        break;
        label169:
        Object localObject = paramErrorCorrectionLevel[i];
        j = 0;
        for (;;)
        {
          if (j >= ((Version.ECB)localObject).getCount())
          {
            i += 1;
            break;
          }
          n = ((Version.ECB)localObject).getDataCodewords();
          arrayOfDataBlock[k] = new DataBlock(n, new byte[paramVersion.getECCodewordsPerBlock() + n]);
          j += 1;
          k += 1;
        }
        label239:
        if (arrayOfDataBlock[i].codewords.length == j) {
          break label85;
        }
        i -= 1;
        break label81;
        label259:
        m = 0;
        for (;;)
        {
          if (m >= k)
          {
            j += 1;
            break;
          }
          arrayOfDataBlock[m].codewords[j] = paramArrayOfByte[i];
          m += 1;
          i += 1;
        }
        label305:
        arrayOfDataBlock[j].codewords[n] = paramArrayOfByte[i];
        j += 1;
        i += 1;
        break label115;
      }
      m = 0;
      if (m < k) {
        break label349;
      }
      i += 1;
    }
    label349:
    if (m < i1) {}
    for (int n = i;; n = i + 1)
    {
      arrayOfDataBlock[m].codewords[n] = paramArrayOfByte[j];
      m += 1;
      j += 1;
      break;
    }
  }
  
  byte[] getCodewords()
  {
    return this.codewords;
  }
  
  int getNumDataCodewords()
  {
    return this.numDataCodewords;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\qrcode\decoder\DataBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */