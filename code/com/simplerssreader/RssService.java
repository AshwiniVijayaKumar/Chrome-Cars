package com.simplerssreader;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import org.xmlpull.v1.XmlPullParserException;

public class RssService
  extends IntentService
{
  public static final String ITEMS = "items";
  public static final String RECEIVER = "receiver";
  private String RSS_LINK = MainActivity.rssURL;
  
  public RssService()
  {
    super("RssService");
  }
  
  public InputStream getInputStream(String paramString)
  {
    try
    {
      paramString = new URL(paramString).openConnection().getInputStream();
      return paramString;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    Object localObject1 = null;
    try
    {
      localObject2 = new PcWorldRssParser().parse(getInputStream(this.RSS_LINK));
      localObject1 = localObject2;
    }
    catch (XmlPullParserException localXmlPullParserException)
    {
      for (;;)
      {
        Object localObject2;
        Log.w(localXmlPullParserException.getMessage(), localXmlPullParserException);
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        Log.w(localIOException.getMessage(), localIOException);
      }
    }
    localObject2 = new Bundle();
    ((Bundle)localObject2).putSerializable("items", (Serializable)localObject1);
    ((ResultReceiver)paramIntent.getParcelableExtra("receiver")).send(0, (Bundle)localObject2);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\simplerssreader\RssService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */