package com.adsdk.sdk.mraid;

import android.app.Activity;
import android.content.Intent;

public class MraidInterstitialAdapter
  extends BaseInterstitialAdapter
{
  public void loadInterstitial()
  {
    if (this.mAdapterListener != null) {
      this.mAdapterListener.onNativeInterstitialLoaded(this);
    }
  }
  
  public void showInterstitial()
  {
    Activity localActivity = this.mInterstitial.getActivity();
    Intent localIntent = new Intent(localActivity, MraidActivity.class);
    localIntent.putExtra("com.adsdk.sdk.mraid.Source", this.mJsonParams);
    localActivity.startActivity(localIntent);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\mraid\MraidInterstitialAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */