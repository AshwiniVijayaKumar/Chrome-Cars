package com.nostra13.universalimageloader.core;

public abstract interface ImageLoadingListener
{
  public abstract void onLoadingComplete();
  
  public abstract void onLoadingFailed(FailReason paramFailReason);
  
  public abstract void onLoadingStarted();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\nostra13\universalimageloader\core\ImageLoadingListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */