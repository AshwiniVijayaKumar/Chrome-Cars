package com.example.example75f1799f07eb;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

public class ConnectionDetector
{
  private Context _context;
  
  public ConnectionDetector(Context paramContext)
  {
    this._context = paramContext;
  }
  
  public boolean isConnectingToInternet()
  {
    Object localObject = (ConnectivityManager)this._context.getSystemService("connectivity");
    if (localObject != null)
    {
      localObject = ((ConnectivityManager)localObject).getAllNetworkInfo();
      if (localObject != null)
      {
        int i = 0;
        while (i < localObject.length)
        {
          if (localObject[i].getState() == NetworkInfo.State.CONNECTED) {
            return true;
          }
          i += 1;
        }
      }
    }
    return false;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\ConnectionDetector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */