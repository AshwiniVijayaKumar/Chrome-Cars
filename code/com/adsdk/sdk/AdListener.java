package com.adsdk.sdk;

public abstract interface AdListener
{
  public abstract void adClicked();
  
  public abstract void adClosed(Ad paramAd, boolean paramBoolean);
  
  public abstract void adLoadSucceeded(Ad paramAd);
  
  public abstract void adShown(Ad paramAd, boolean paramBoolean);
  
  public abstract void noAdFound();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\AdListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */