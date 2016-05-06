package com.adsdk.sdk.video;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import com.adsdk.sdk.Log;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class WebFrame
  extends FrameLayout
  implements InterstitialController.BrowserControl
{
  private static Field mWebView_LAYER_TYPE_SOFTWARE;
  private static Method mWebView_SetLayerType;
  private boolean enableZoom = true;
  private Activity mActivity;
  private InterstitialController mController;
  private ImageView mExitButton;
  private WebView mWebView;
  private WebViewClient mWebViewClient;
  
  static {}
  
  public WebFrame(final Activity paramActivity, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    super(paramActivity);
    initCompatibility();
    this.mActivity = paramActivity;
    this.mWebView = new WebView(paramActivity);
    this.mWebView.setVerticalScrollBarEnabled(paramBoolean2);
    this.mWebView.setHorizontalScrollBarEnabled(paramBoolean2);
    this.mWebView.setBackgroundColor(0);
    setLayer(this.mWebView);
    Object localObject = this.mWebView.getSettings();
    ((WebSettings)localObject).setSavePassword(false);
    ((WebSettings)localObject).setSaveFormData(false);
    ((WebSettings)localObject).setJavaScriptEnabled(true);
    ((WebSettings)localObject).setPluginsEnabled(true);
    ((WebSettings)localObject).setSupportZoom(this.enableZoom);
    ((WebSettings)localObject).setBuiltInZoomControls(this.enableZoom);
    this.mWebViewClient = new WebViewClient(this.mActivity, paramBoolean1);
    this.mWebView.setWebViewClient(this.mWebViewClient);
    if (paramBoolean3)
    {
      localObject = new ImageView(paramActivity);
      ((ImageView)localObject).setBackgroundColor(0);
      addView((View)localObject, new FrameLayout.LayoutParams(-1, -1, 17));
      addView(this.mWebView, new FrameLayout.LayoutParams(-1, -1, 17));
      this.mExitButton = new ImageView(paramActivity);
      this.mExitButton.setAdjustViewBounds(false);
      this.mExitButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramActivity.finish();
        }
      });
      int i = (int)TypedValue.applyDimension(1, 35.0F, getResources().getDisplayMetrics());
      this.mExitButton.setImageDrawable(ResourceManager.getStaticResource(paramActivity, -18));
      paramActivity = new FrameLayout.LayoutParams(i, i, 53);
      i = (int)TypedValue.applyDimension(1, 6.0F, getResources().getDisplayMetrics());
      paramActivity.topMargin = i;
      paramActivity.rightMargin = i;
      addView(this.mExitButton, paramActivity);
      return;
    }
    addView(this.mWebView, new FrameLayout.LayoutParams(-1, -1, 17));
  }
  
  private void attachController()
  {
    if (this.mController != null) {
      this.mController.setBrowser(this);
    }
  }
  
  private String getUserAgentString()
  {
    return this.mWebView.getSettings().getUserAgentString();
  }
  
  private static void initCompatibility()
  {
    for (;;)
    {
      int j;
      int i;
      try
      {
        arrayOfMethod = WebView.class.getMethods();
        j = arrayOfMethod.length;
        i = 0;
      }
      catch (SecurityException localSecurityException)
      {
        Method[] arrayOfMethod;
        Method localMethod;
        Log.v("SecurityException");
        return;
        i += 1;
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        Log.v("NoSuchFieldException");
        return;
      }
      Log.v("set layer " + mWebView_SetLayerType);
      mWebView_LAYER_TYPE_SOFTWARE = WebView.class.getField("LAYER_TYPE_SOFTWARE");
      Log.v("set1 layer " + mWebView_LAYER_TYPE_SOFTWARE);
      return;
      localMethod = arrayOfMethod[i];
      if (localMethod.getName().equals("setLayerType")) {
        mWebView_SetLayerType = localMethod;
      } else if (i < j) {}
    }
  }
  
  private static void setLayer(WebView paramWebView)
  {
    if ((mWebView_SetLayerType != null) && (mWebView_LAYER_TYPE_SOFTWARE != null)) {
      try
      {
        Log.v("Set Layer is supported");
        mWebView_SetLayerType.invoke(paramWebView, new Object[] { Integer.valueOf(mWebView_LAYER_TYPE_SOFTWARE.getInt(WebView.class)), null });
        return;
      }
      catch (InvocationTargetException paramWebView)
      {
        Log.v("Set InvocationTargetException");
        return;
      }
      catch (IllegalArgumentException paramWebView)
      {
        Log.v("Set IllegalArgumentException");
        return;
      }
      catch (IllegalAccessException paramWebView)
      {
        Log.v("Set IllegalAccessException");
        return;
      }
    }
    Log.v("Set Layer is not supported");
  }
  
  public boolean canGoBack()
  {
    return this.mWebView.canGoBack();
  }
  
  public boolean canGoForward()
  {
    return this.mWebView.canGoForward();
  }
  
  public String getPageTitle()
  {
    return this.mWebView.getTitle();
  }
  
  public int getTime()
  {
    int i = 0;
    long l = this.mWebViewClient.getFinishedLoadingTime();
    if (l > 0L) {
      i = (int)(System.currentTimeMillis() - l);
    }
    return i;
  }
  
  public WebView getWebView()
  {
    return this.mWebView;
  }
  
  public void goBack()
  {
    this.mWebView.goBack();
  }
  
  public void goForward()
  {
    this.mWebView.goForward();
  }
  
  public boolean isEnableZoom()
  {
    return this.enableZoom;
  }
  
  public void launchExternalBrowser()
  {
    String str = this.mWebViewClient.getAllowedUrl();
    if (str != null)
    {
      localObject = str;
      if (str.length() != 0) {}
    }
    else
    {
      localObject = "about:blank";
    }
    Object localObject = new Intent("android.intent.action.VIEW", Uri.parse((String)localObject));
    this.mActivity.startActivity((Intent)localObject);
  }
  
  public void loadUrl(String paramString)
  {
    new LoadUrlTask().execute(new String[] { paramString });
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    onTouchEvent(paramMotionEvent);
    return false;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    super.onTouchEvent(paramMotionEvent);
    return true;
  }
  
  public void reload()
  {
    this.mWebView.reload();
  }
  
  public void setBackgroundColor(int paramInt)
  {
    super.setBackgroundColor(paramInt);
    this.mWebView.setBackgroundColor(paramInt);
  }
  
  public void setBrowserController(InterstitialController paramInterstitialController)
  {
    if (this.mController != null) {
      this.mController.hide();
    }
    this.mController = paramInterstitialController;
    attachController();
  }
  
  public void setEnableZoom(boolean paramBoolean)
  {
    this.enableZoom = paramBoolean;
    WebSettings localWebSettings = this.mWebView.getSettings();
    localWebSettings.setSupportZoom(paramBoolean);
    localWebSettings.setBuiltInZoomControls(paramBoolean);
  }
  
  public void setMarkup(String paramString)
  {
    paramString = Uri.encode(paramString);
    this.mWebViewClient.setAllowedUrl(null);
    this.mWebView.loadData(paramString, "text/html", "UTF-8");
  }
  
  public void setOnPageLoadedListener(WebViewClient.OnPageLoadedListener paramOnPageLoadedListener)
  {
    this.mWebViewClient.setOnPageLoadedListener(paramOnPageLoadedListener);
  }
  
  private class LoadUrlTask
    extends AsyncTask<String, Void, String>
  {
    String userAgent = WebFrame.this.getUserAgentString();
    
    public LoadUrlTask() {}
    
    protected String doInBackground(String... paramVarArgs)
    {
      paramVarArgs = paramVarArgs[0];
      try
      {
        localURL = new URL(paramVarArgs);
        Log.d("Checking URL redirect:" + paramVarArgs);
        localHttpURLConnection = null;
        paramVarArgs = localURL.toString();
        localHashSet = new HashSet();
        localHashSet.add(paramVarArgs);
      }
      catch (MalformedURLException localMalformedURLException)
      {
        try
        {
          for (;;)
          {
            URL localURL;
            HashSet localHashSet;
            HttpURLConnection localHttpURLConnection = (HttpURLConnection)localURL.openConnection();
            localObject2 = localHttpURLConnection;
            localObject1 = paramVarArgs;
            localObject3 = localHttpURLConnection;
            localHttpURLConnection.setRequestProperty("User-Agent", this.userAgent);
            localObject2 = localHttpURLConnection;
            localObject1 = paramVarArgs;
            localObject3 = localHttpURLConnection;
            localHttpURLConnection.setInstanceFollowRedirects(false);
            localObject2 = localHttpURLConnection;
            localObject1 = paramVarArgs;
            localObject3 = localHttpURLConnection;
            int i = localHttpURLConnection.getResponseCode();
            if (i == 200)
            {
              localObject2 = localHttpURLConnection;
              localObject1 = paramVarArgs;
              localObject3 = localHttpURLConnection;
              localHttpURLConnection.disconnect();
              localObject1 = paramVarArgs;
            }
            do
            {
              if (localHttpURLConnection != null) {
                localHttpURLConnection.disconnect();
              }
              return (String)localObject1;
              localMalformedURLException = localMalformedURLException;
              if (paramVarArgs != null) {}
              for (;;)
              {
                return paramVarArgs;
                paramVarArgs = "";
              }
              localObject2 = localMalformedURLException;
              localObject1 = paramVarArgs;
              localObject3 = localMalformedURLException;
              paramVarArgs = localMalformedURLException.getHeaderField("location");
              localObject2 = localMalformedURLException;
              localObject1 = paramVarArgs;
              localObject3 = localMalformedURLException;
              localMalformedURLException.disconnect();
              localObject2 = localMalformedURLException;
              localObject1 = paramVarArgs;
              localObject3 = localMalformedURLException;
              if (!localHashSet.add(paramVarArgs))
              {
                localObject2 = localMalformedURLException;
                localObject1 = paramVarArgs;
                localObject3 = localMalformedURLException;
                Log.d("URL redirect cycle detected");
                if (localMalformedURLException != null) {
                  localMalformedURLException.disconnect();
                }
                return "";
              }
              localObject2 = localMalformedURLException;
              localObject1 = paramVarArgs;
              localObject3 = localMalformedURLException;
              localURL = new URL(paramVarArgs);
              if ((i == 302) || (i == 301) || (i == 307)) {
                break;
              }
              localObject1 = paramVarArgs;
            } while (i != 303);
          }
        }
        catch (IOException paramVarArgs)
        {
          Object localObject2;
          Object localObject1;
          if (localObject1 == null) {
            break label317;
          }
          for (;;)
          {
            return (String)localObject1;
            localObject1 = "";
          }
        }
        finally
        {
          Object localObject3;
          if (localObject3 == null) {
            break label335;
          }
          ((HttpURLConnection)localObject3).disconnect();
        }
      }
      localObject2 = localHttpURLConnection;
      localObject1 = paramVarArgs;
      localObject3 = localHttpURLConnection;
    }
    
    protected void onPostExecute(String paramString)
    {
      String str;
      if (paramString != null)
      {
        str = paramString;
        if (!paramString.equals("")) {}
      }
      else
      {
        str = "about:blank";
      }
      Log.d("Show URL: " + str);
      WebFrame.this.mWebViewClient.setAllowedUrl(str);
      WebFrame.this.mWebView.loadUrl(str);
      WebFrame.this.requestLayout();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\video\WebFrame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */