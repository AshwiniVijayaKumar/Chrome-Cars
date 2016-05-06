package com.ooyala.android.player;

import android.os.AsyncTask;
import com.ooyala.android.util.DebugMode;
import java.net.HttpURLConnection;
import java.net.URL;

class PingTask
  extends AsyncTask<URL, Void, Void>
{
  protected Void doInBackground(URL... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        Object localObject = paramVarArgs[i];
        try
        {
          localObject = (HttpURLConnection)((URL)localObject).openConnection();
          ((HttpURLConnection)localObject).connect();
          ((HttpURLConnection)localObject).getInputStream();
          i += 1;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            DebugMode.logE(PingTask.class.getName(), "Ping failed!!!");
          }
        }
      }
    }
    return null;
  }
  
  protected void onPostExecute() {}
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\player\PingTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */