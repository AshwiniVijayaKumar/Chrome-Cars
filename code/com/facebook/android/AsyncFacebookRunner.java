package com.facebook.android;

import android.content.Context;
import android.os.Bundle;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

public class AsyncFacebookRunner
{
  Facebook fb;
  
  public AsyncFacebookRunner(Facebook paramFacebook)
  {
    this.fb = paramFacebook;
  }
  
  public void logout(final Context paramContext, final RequestListener paramRequestListener)
  {
    new Thread()
    {
      public void run()
      {
        try
        {
          String str = AsyncFacebookRunner.this.fb.logout(paramContext);
          if ((str.length() == 0) || (str.equals("false")))
          {
            paramRequestListener.onFacebookError(new FacebookError("auth.expireSession failed"));
            return;
          }
          paramRequestListener.onComplete(str);
          return;
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          paramRequestListener.onFileNotFoundException(localFileNotFoundException);
          return;
        }
        catch (MalformedURLException localMalformedURLException)
        {
          paramRequestListener.onMalformedURLException(localMalformedURLException);
          return;
        }
        catch (IOException localIOException)
        {
          paramRequestListener.onIOException(localIOException);
        }
      }
    }.start();
  }
  
  public void request(Bundle paramBundle, RequestListener paramRequestListener)
  {
    request(null, paramBundle, "GET", paramRequestListener);
  }
  
  public void request(String paramString, Bundle paramBundle, RequestListener paramRequestListener)
  {
    request(paramString, paramBundle, "GET", paramRequestListener);
  }
  
  public void request(final String paramString1, final Bundle paramBundle, final String paramString2, final RequestListener paramRequestListener)
  {
    new Thread()
    {
      public void run()
      {
        try
        {
          String str = AsyncFacebookRunner.this.fb.request(paramString1, paramBundle, paramString2);
          paramRequestListener.onComplete(str);
          return;
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          paramRequestListener.onFileNotFoundException(localFileNotFoundException);
          return;
        }
        catch (MalformedURLException localMalformedURLException)
        {
          paramRequestListener.onMalformedURLException(localMalformedURLException);
          return;
        }
        catch (IOException localIOException)
        {
          paramRequestListener.onIOException(localIOException);
        }
      }
    }.start();
  }
  
  public void request(String paramString, RequestListener paramRequestListener)
  {
    request(paramString, new Bundle(), "GET", paramRequestListener);
  }
  
  public static abstract interface RequestListener
  {
    public abstract void onComplete(String paramString);
    
    public abstract void onFacebookError(FacebookError paramFacebookError);
    
    public abstract void onFileNotFoundException(FileNotFoundException paramFileNotFoundException);
    
    public abstract void onIOException(IOException paramIOException);
    
    public abstract void onMalformedURLException(MalformedURLException paramMalformedURLException);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\facebook\android\AsyncFacebookRunner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */