package com.adsdk.sdk.mraid;

class MraidScreenSizeProperty
  extends MraidProperty
{
  private final int mScreenHeight;
  private final int mScreenWidth;
  
  MraidScreenSizeProperty(int paramInt1, int paramInt2)
  {
    this.mScreenWidth = paramInt1;
    this.mScreenHeight = paramInt2;
  }
  
  public static MraidScreenSizeProperty createWithSize(int paramInt1, int paramInt2)
  {
    return new MraidScreenSizeProperty(paramInt1, paramInt2);
  }
  
  public String toJsonPair()
  {
    return "screenSize: { width: " + this.mScreenWidth + ", height: " + this.mScreenHeight + " }";
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\mraid\MraidScreenSizeProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */