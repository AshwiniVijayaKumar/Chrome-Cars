package com.google.zxing.pdf417.encoder;

final class BarcodeRow
{
  private int currentLocation;
  private final byte[] row;
  
  BarcodeRow(int paramInt)
  {
    this.row = new byte[paramInt];
    this.currentLocation = 0;
  }
  
  void addBar(boolean paramBoolean, int paramInt)
  {
    int i = 0;
    for (;;)
    {
      if (i >= paramInt) {
        return;
      }
      int j = this.currentLocation;
      this.currentLocation = (j + 1);
      set(j, paramBoolean);
      i += 1;
    }
  }
  
  byte[] getRow()
  {
    return this.row;
  }
  
  byte[] getScaledRow(int paramInt)
  {
    byte[] arrayOfByte = new byte[this.row.length * paramInt];
    int i = 0;
    for (;;)
    {
      if (i >= arrayOfByte.length) {
        return arrayOfByte;
      }
      arrayOfByte[i] = this.row[(i / paramInt)];
      i += 1;
    }
  }
  
  void set(int paramInt, byte paramByte)
  {
    this.row[paramInt] = paramByte;
  }
  
  void set(int paramInt, boolean paramBoolean)
  {
    byte[] arrayOfByte = this.row;
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      arrayOfByte[paramInt] = ((byte)i);
      return;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\pdf417\encoder\BarcodeRow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */