package com.appyjump.video.sdk;

public abstract interface BannerListener
{
  public abstract void adClicked();
  
  public abstract void bannerLoadFailed(RequestException paramRequestException);
  
  public abstract void bannerLoadSucceeded();
  
  public abstract void noAdFound();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\appyjump\video\sdk\BannerListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */