package com.radiusnetworks.ibeacon.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Messenger;
import android.os.Parcelable;
import android.util.Log;

public class Callback
{
  private String TAG = "Callback";
  private Intent intent;
  private Messenger messenger;
  
  public Callback(String paramString)
  {
    if (paramString != null)
    {
      this.intent = new Intent();
      this.intent.setComponent(new ComponentName(paramString, "com.radiusnetworks.ibeacon.IBeaconIntentProcessor"));
    }
  }
  
  public boolean call(Context paramContext, String paramString, Parcelable paramParcelable)
  {
    if (this.intent != null)
    {
      Log.d(this.TAG, "attempting callback via intent: " + this.intent.getComponent());
      this.intent.putExtra(paramString, paramParcelable);
      paramContext.startService(this.intent);
      return true;
    }
    return false;
  }
  
  public Intent getIntent()
  {
    return this.intent;
  }
  
  public void setIntent(Intent paramIntent)
  {
    this.intent = paramIntent;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\radiusnetworks\ibeacon\service\Callback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */