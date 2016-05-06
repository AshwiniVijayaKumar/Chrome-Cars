package com.google.zxing;

public final class RGBLuminanceSource
  extends LuminanceSource
{
  private final int dataHeight;
  private final int dataWidth;
  private final int left;
  private final byte[] luminances;
  private final int top;
  
  public RGBLuminanceSource(int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    super(paramInt1, paramInt2);
    this.dataWidth = paramInt1;
    this.dataHeight = paramInt2;
    this.left = 0;
    this.top = 0;
    this.luminances = new byte[paramInt1 * paramInt2];
    int i = 0;
    int k;
    int j;
    for (;;)
    {
      if (i >= paramInt2) {
        return;
      }
      k = i * paramInt1;
      j = 0;
      if (j < paramInt1) {
        break;
      }
      i += 1;
    }
    int i1 = paramArrayOfInt[(k + j)];
    int m = i1 >> 16 & 0xFF;
    int n = i1 >> 8 & 0xFF;
    i1 &= 0xFF;
    if ((m == n) && (n == i1)) {
      this.luminances[(k + j)] = ((byte)m);
    }
    for (;;)
    {
      j += 1;
      break;
      this.luminances[(k + j)] = ((byte)(m + n + n + i1 >> 2));
    }
  }
  
  private RGBLuminanceSource(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    super(paramInt5, paramInt6);
    if ((paramInt3 + paramInt5 > paramInt1) || (paramInt4 + paramInt6 > paramInt2)) {
      throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
    }
    this.luminances = paramArrayOfByte;
    this.dataWidth = paramInt1;
    this.dataHeight = paramInt2;
    this.left = paramInt3;
    this.top = paramInt4;
  }
  
  public LuminanceSource crop(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return new RGBLuminanceSource(this.luminances, this.dataWidth, this.dataHeight, this.left + paramInt1, this.top + paramInt2, paramInt3, paramInt4);
  }
  
  public byte[] getMatrix()
  {
    int k = getWidth();
    int m = getHeight();
    Object localObject;
    if ((k == this.dataWidth) && (m == this.dataHeight))
    {
      localObject = this.luminances;
      return (byte[])localObject;
    }
    int i = k * m;
    byte[] arrayOfByte1 = new byte[i];
    int j = this.top * this.dataWidth + this.left;
    if (k == this.dataWidth)
    {
      System.arraycopy(this.luminances, j, arrayOfByte1, 0, i);
      return arrayOfByte1;
    }
    byte[] arrayOfByte2 = this.luminances;
    i = 0;
    for (;;)
    {
      localObject = arrayOfByte1;
      if (i >= m) {
        break;
      }
      System.arraycopy(arrayOfByte2, j, arrayOfByte1, i * k, k);
      j += this.dataWidth;
      i += 1;
    }
  }
  
  public byte[] getRow(int paramInt, byte[] paramArrayOfByte)
  {
    if ((paramInt < 0) || (paramInt >= getHeight())) {
      throw new IllegalArgumentException("Requested row is outside the image: " + paramInt);
    }
    int i = getWidth();
    byte[] arrayOfByte;
    if (paramArrayOfByte != null)
    {
      arrayOfByte = paramArrayOfByte;
      if (paramArrayOfByte.length >= i) {}
    }
    else
    {
      arrayOfByte = new byte[i];
    }
    int j = this.top;
    int k = this.dataWidth;
    int m = this.left;
    System.arraycopy(this.luminances, (j + paramInt) * k + m, arrayOfByte, 0, i);
    return arrayOfByte;
  }
  
  public boolean isCropSupported()
  {
    return true;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\RGBLuminanceSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */