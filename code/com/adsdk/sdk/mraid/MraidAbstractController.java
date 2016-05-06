package com.adsdk.sdk.mraid;

class MraidAbstractController
{
  private final MraidView mView;
  
  MraidAbstractController(MraidView paramMraidView)
  {
    this.mView = paramMraidView;
  }
  
  public MraidView getView()
  {
    return this.mView;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\mraid\MraidAbstractController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */