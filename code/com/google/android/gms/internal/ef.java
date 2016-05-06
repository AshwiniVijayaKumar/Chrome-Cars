package com.google.android.gms.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.util.Log;

public class ef
  implements DialogInterface.OnClickListener
{
  private final int Bq;
  private final Intent mIntent;
  private final Activity nd;
  
  public ef(Activity paramActivity, Intent paramIntent, int paramInt)
  {
    this.nd = paramActivity;
    this.mIntent = paramIntent;
    this.Bq = paramInt;
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    try
    {
      if (this.mIntent != null) {
        this.nd.startActivityForResult(this.mIntent, this.Bq);
      }
      paramDialogInterface.dismiss();
      return;
    }
    catch (ActivityNotFoundException paramDialogInterface)
    {
      Log.e("SettingsRedirect", "Can't redirect to app settings for Google Play services");
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */