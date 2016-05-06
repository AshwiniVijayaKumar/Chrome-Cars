package com.appyjump.video.sdk;

import android.webkit.WebView;

class SetBackgroundResourceAction
  implements Runnable
{
  private int backgroundResource;
  private WebView view;
  
  public SetBackgroundResourceAction(WebView paramWebView, int paramInt)
  {
    this.view = paramWebView;
    this.backgroundResource = paramInt;
  }
  
  public void run()
  {
    try
    {
      this.view.setBackgroundResource(this.backgroundResource);
      return;
    }
    catch (Exception localException) {}
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\appyjump\video\sdk\SetBackgroundResourceAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */