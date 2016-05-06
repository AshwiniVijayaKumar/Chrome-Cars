package com.ooyala.android;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class AdsLearnMoreButton
  extends RelativeLayout
{
  private TextView _learnMore;
  
  public AdsLearnMoreButton(Context paramContext)
  {
    super(paramContext);
  }
  
  @TargetApi(11)
  public AdsLearnMoreButton(Context paramContext, final AdsLearnMoreInterface paramAdsLearnMoreInterface, int paramInt)
  {
    super(paramContext);
    this._learnMore = new TextView(paramContext);
    paramContext = LocalizationSupport.localizedStringFor("Learn More");
    this._learnMore.setText(paramContext);
    this._learnMore.setTextSize(15.0F);
    this._learnMore.setTextColor(-16777216);
    this._learnMore.setBackgroundColor(-7829368);
    this._learnMore.setPadding(20, 20, 20, 20);
    if (Build.VERSION.SDK_INT >= 11) {
      this._learnMore.setAlpha(0.7F);
    }
    this._learnMore.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAdsLearnMoreInterface.processClickThrough();
      }
    });
    paramContext = new RelativeLayout.LayoutParams(-2, -2);
    paramContext.addRule(11, -1);
    addView(this._learnMore, paramContext);
    setTopMargin(paramInt);
  }
  
  public void destroy()
  {
    removeView(this._learnMore);
    this._learnMore = null;
  }
  
  public void setTopMargin(int paramInt)
  {
    ((RelativeLayout.LayoutParams)this._learnMore.getLayoutParams()).setMargins(0, paramInt, 0, 0);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\AdsLearnMoreButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */