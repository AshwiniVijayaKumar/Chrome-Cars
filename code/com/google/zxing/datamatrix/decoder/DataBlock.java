package com.google.zxing.datamatrix.decoder;

final class DataBlock
{
  private final byte[] codewords;
  private final int numDataCodewords;
  
  private DataBlock(int paramInt, byte[] paramArrayOfByte)
  {
    this.numDataCodewords = paramInt;
    this.codewords = paramArrayOfByte;
  }
  
  static DataBlock[] getDataBlocks(byte[] paramArrayOfByte, Version paramVersion)
  {
    Version.ECBlocks localECBlocks = paramVersion.getECBlocks();
    int j = 0;
    Version.ECB[] arrayOfECB = localECBlocks.getECBlocks();
    int k = arrayOfECB.length;
    int i = 0;
    DataBlock[] arrayOfDataBlock;
    int m;
    label91:
    label100:
    int n;
    label103:
    int i2;
    if (i >= k)
    {
      arrayOfDataBlock = new DataBlock[j];
      j = 0;
      m = arrayOfECB.length;
      i = 0;
      if (i < m) {
        break label168;
      }
      i1 = arrayOfDataBlock[0].codewords.length - localECBlocks.getECCodewords();
      i = 0;
      k = 0;
      if (k < i1 - 1) {
        break label237;
      }
      if (paramVersion.getVersionNumber() != 24) {
        break label282;
      }
      m = 1;
      if (m == 0) {
        break label288;
      }
      k = 8;
      n = 0;
      if (n < k) {
        break label294;
      }
      i2 = arrayOfDataBlock[0].codewords.length;
      n = i1;
      k = i;
      i = n;
    }
    for (;;)
    {
      if (i >= i2)
      {
        if (k == paramArrayOfByte.length) {
          break label392;
        }
        throw new IllegalArgumentException();
        j += arrayOfECB[i].getCount();
        i += 1;
        break;
        label168:
        Version.ECB localECB = arrayOfECB[i];
        k = 0;
        for (;;)
        {
          if (k >= localECB.getCount())
          {
            i += 1;
            break;
          }
          n = localECB.getDataCodewords();
          arrayOfDataBlock[j] = new DataBlock(n, new byte[localECBlocks.getECCodewords() + n]);
          k += 1;
          j += 1;
        }
        label237:
        m = 0;
        for (;;)
        {
          if (m >= j)
          {
            k += 1;
            break;
          }
          arrayOfDataBlock[m].codewords[k] = paramArrayOfByte[i];
          m += 1;
          i += 1;
        }
        label282:
        m = 0;
        break label91;
        label288:
        k = j;
        break label100;
        label294:
        arrayOfDataBlock[n].codewords[(i1 - 1)] = paramArrayOfByte[i];
        n += 1;
        i += 1;
        break label103;
      }
      n = 0;
      if (n < j) {
        break label339;
      }
      i += 1;
    }
    label339:
    if ((m != 0) && (n > 7)) {}
    for (int i1 = i - 1;; i1 = i)
    {
      arrayOfDataBlock[n].codewords[i1] = paramArrayOfByte[k];
      n += 1;
      k += 1;
      break;
    }
    label392:
    return arrayOfDataBlock;
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


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\datamatrix\decoder\DataBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */