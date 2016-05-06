package com.adsdk.sdk.mraid;

import android.content.Intent;
import android.view.View;

public class MraidActivity
  extends BaseActivity
{
  private MraidView mAdView;
  
  public View getAdView()
  {
    this.mAdView = new MraidView(this, MraidView.ExpansionStyle.DISABLED, MraidView.NativeCloseButtonStyle.AD_CONTROLLED, MraidView.PlacementType.INTERSTITIAL);
    this.mAdView.setOnReadyListener(new MraidView.OnReadyListener()
    {
      public void onReady(MraidView paramAnonymousMraidView)
      {
        MraidActivity.this.showInterstitialCloseButton();
      }
    });
    this.mAdView.setOnCloseButtonStateChange(new MraidView.OnCloseButtonStateChangeListener()
    {
      public void onCloseButtonStateChange(MraidView paramAnonymousMraidView, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          MraidActivity.this.showInterstitialCloseButton();
          return;
        }
        MraidActivity.this.hideInterstitialCloseButton();
      }
    });
    this.mAdView.setOnCloseListener(new MraidView.OnCloseListener()
    {
      public void onClose(MraidView paramAnonymousMraidView, MraidView.ViewState paramAnonymousViewState)
      {
        MraidActivity.this.finish();
      }
    });
    String str = getIntent().getStringExtra("com.adsdk.sdk.mraid.Source");
    this.mAdView.loadHtmlData(str);
    return this.mAdView;
  }
  
  protected void onDestroy()
  {
    this.mAdView.destroy();
    super.onDestroy();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\mraid\MraidActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */