package com.adsdk.sdk.mraid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

class MraidBrowserController
  extends MraidAbstractController
{
  private static final String LOGTAG = "MraidBrowserController";
  
  MraidBrowserController(MraidView paramMraidView)
  {
    super(paramMraidView);
  }
  
  protected void open(String paramString)
  {
    Log.d("MraidBrowserController", "Opening in-app browser: " + paramString);
    Object localObject = getView();
    if (((MraidView)localObject).getOnOpenListener() != null) {
      ((MraidView)localObject).getOnOpenListener().onOpen((MraidView)localObject);
    }
    localObject = getView().getContext();
    if (paramString.endsWith(".mp4"))
    {
      localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setDataAndType(Uri.parse(paramString), "video/mp4");
      ((Context)localObject).startActivity(localIntent);
      return;
    }
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(paramString));
    ((Context)localObject).startActivity(localIntent);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\mraid\MraidBrowserController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */