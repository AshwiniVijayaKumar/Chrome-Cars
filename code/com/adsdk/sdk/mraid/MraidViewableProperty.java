package com.adsdk.sdk.mraid;

class MraidViewableProperty
  extends MraidProperty
{
  private final boolean mViewable;
  
  MraidViewableProperty(boolean paramBoolean)
  {
    this.mViewable = paramBoolean;
  }
  
  public static MraidViewableProperty createWithViewable(boolean paramBoolean)
  {
    return new MraidViewableProperty(paramBoolean);
  }
  
  public String toJsonPair()
  {
    StringBuilder localStringBuilder = new StringBuilder("viewable: ");
    if (this.mViewable) {}
    for (String str = "true";; str = "false") {
      return str;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\mraid\MraidViewableProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */