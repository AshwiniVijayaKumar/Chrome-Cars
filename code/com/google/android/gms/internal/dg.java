package com.google.android.gms.internal;

import android.content.Context;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class dg
  extends de
{
  public dg(dd paramdd, boolean paramBoolean)
  {
    super(paramdd, paramBoolean);
  }
  
  private static WebResourceResponse d(Context paramContext, String paramString1, String paramString2)
    throws IOException
  {
    paramString2 = (HttpURLConnection)new URL(paramString2).openConnection();
    try
    {
      cv.a(paramContext, paramString1, true, paramString2);
      paramString2.connect();
      paramContext = new WebResourceResponse("application/javascript", "UTF-8", new ByteArrayInputStream(cv.a(new InputStreamReader(paramString2.getInputStream())).getBytes("UTF-8")));
      return paramContext;
    }
    finally
    {
      paramString2.disconnect();
    }
  }
  
  public WebResourceResponse shouldInterceptRequest(WebView paramWebView, String paramString)
  {
    try
    {
      if (!"mraid.js".equalsIgnoreCase(new File(paramString).getName())) {
        return super.shouldInterceptRequest(paramWebView, paramString);
      }
      if (!(paramWebView instanceof dd))
      {
        da.w("Tried to intercept request from a WebView that wasn't an AdWebView.");
        return super.shouldInterceptRequest(paramWebView, paramString);
      }
      Object localObject = (dd)paramWebView;
      ((dd)localObject).bb().ar();
      if (((dd)localObject).Q().lo)
      {
        da.v("shouldInterceptRequest(http://media.admob.com/mraid/v1/mraid_app_interstitial.js)");
        return d(((dd)localObject).getContext(), this.ng.bd().pU, "http://media.admob.com/mraid/v1/mraid_app_interstitial.js");
      }
      if (((dd)localObject).be())
      {
        da.v("shouldInterceptRequest(http://media.admob.com/mraid/v1/mraid_app_expanded_banner.js)");
        return d(((dd)localObject).getContext(), this.ng.bd().pU, "http://media.admob.com/mraid/v1/mraid_app_expanded_banner.js");
      }
      da.v("shouldInterceptRequest(http://media.admob.com/mraid/v1/mraid_app_banner.js)");
      localObject = d(((dd)localObject).getContext(), this.ng.bd().pU, "http://media.admob.com/mraid/v1/mraid_app_banner.js");
      return (WebResourceResponse)localObject;
    }
    catch (IOException localIOException)
    {
      da.w("Could not fetching MRAID JS. " + localIOException.getMessage());
    }
    return super.shouldInterceptRequest(paramWebView, paramString);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\dg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */