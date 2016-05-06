package com.google.android.gms.common;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import com.google.android.gms.internal.er;

public class b
  extends DialogFragment
{
  private Dialog mDialog = null;
  private DialogInterface.OnCancelListener yK = null;
  
  public static b a(Dialog paramDialog, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    b localb = new b();
    paramDialog = (Dialog)er.b(paramDialog, "Cannot display null dialog");
    paramDialog.setOnCancelListener(null);
    paramDialog.setOnDismissListener(null);
    localb.mDialog = paramDialog;
    if (paramOnCancelListener != null) {
      localb.yK = paramOnCancelListener;
    }
    return localb;
  }
  
  public void onCancel(DialogInterface paramDialogInterface)
  {
    if (this.yK != null) {
      this.yK.onCancel(paramDialogInterface);
    }
  }
  
  public Dialog onCreateDialog(Bundle paramBundle)
  {
    return this.mDialog;
  }
  
  public void show(FragmentManager paramFragmentManager, String paramString)
  {
    super.show(paramFragmentManager, paramString);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */