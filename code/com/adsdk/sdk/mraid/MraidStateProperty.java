package com.adsdk.sdk.mraid;

class MraidStateProperty
  extends MraidProperty
{
  private final MraidView.ViewState mViewState;
  
  MraidStateProperty(MraidView.ViewState paramViewState)
  {
    this.mViewState = paramViewState;
  }
  
  public static MraidStateProperty createWithViewState(MraidView.ViewState paramViewState)
  {
    return new MraidStateProperty(paramViewState);
  }
  
  public String toJsonPair()
  {
    return "state: '" + this.mViewState.toString().toLowerCase() + "'";
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\mraid\MraidStateProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */