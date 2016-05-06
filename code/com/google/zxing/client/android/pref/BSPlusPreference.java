package com.google.zxing.client.android.pref;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.util.AttributeSet;

public final class BSPlusPreference
  extends Preference
{
  private static final String MARKET_URL = "market://details?id=com.srowen.bs.android";
  
  public BSPlusPreference(Context paramContext)
  {
    super(paramContext);
    configureClickListener();
  }
  
  public BSPlusPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    configureClickListener();
  }
  
  public BSPlusPreference(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    configureClickListener();
  }
  
  private void configureClickListener()
  {
    setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
    {
      public boolean onPreferenceClick(Preference paramAnonymousPreference)
      {
        paramAnonymousPreference = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.srowen.bs.android"));
        paramAnonymousPreference.addFlags(524288);
        BSPlusPreference.this.getContext().startActivity(paramAnonymousPreference);
        return true;
      }
    });
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\pref\BSPlusPreference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */