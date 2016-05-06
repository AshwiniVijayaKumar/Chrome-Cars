package com.adsdk.sdk.video;

import android.app.Activity;
import android.view.View;
import android.webkit.WebChromeClient.CustomViewCallback;
import com.adsdk.sdk.Log;

public class WebChromeClient
  extends android.webkit.WebChromeClient
{
  private RichMediaActivity mActivity;
  
  public WebChromeClient(Activity paramActivity)
  {
    if ((paramActivity instanceof RichMediaActivity)) {
      this.mActivity = ((RichMediaActivity)paramActivity);
    }
  }
  
  public void onHideCustomView()
  {
    Log.d("WebChromeClient onHideCustomView");
    if (this.mActivity == null)
    {
      super.onHideCustomView();
      return;
    }
    this.mActivity.onHideCustomView();
  }
  
  public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
  {
    Log.d("WebChromeClient onShowCustomView");
    if (this.mActivity == null)
    {
      super.onShowCustomView(paramView, paramCustomViewCallback);
      return;
    }
    this.mActivity.onShowCustomView(paramView, paramCustomViewCallback);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\video\WebChromeClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */