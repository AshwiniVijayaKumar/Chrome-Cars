package com.example.example75f1799f07eb;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class AlertDialogManager
{
  public void showAlertDialog(Context paramContext, String paramString1, String paramString2, final int paramInt)
  {
    paramContext = new AlertDialog.Builder(paramContext).create();
    paramContext.setTitle(paramString1);
    paramContext.setMessage(paramString2);
    paramContext.setCanceledOnTouchOutside(false);
    int i;
    if (paramInt > 0) {
      if (paramInt == 1) {
        i = 2130837711;
      }
    }
    for (;;)
    {
      paramContext.setIcon(i);
      paramContext.setButton("OK", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          if (paramInt == 22) {
            System.exit(2);
          }
        }
      });
      paramContext.show();
      return;
      i = 2130837661;
      continue;
      i = 2130837584;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\AlertDialogManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */