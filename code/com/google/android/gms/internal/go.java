package com.google.android.gms.internal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Games.a;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.android.gms.games.request.Requests;
import com.google.android.gms.games.request.Requests.LoadRequestsResult;
import com.google.android.gms.games.request.Requests.UpdateRequestsResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class go
  implements Requests
{
  public PendingResult<Requests.UpdateRequestsResult> acceptRequest(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramString);
    return acceptRequests(paramGoogleApiClient, localArrayList);
  }
  
  public PendingResult<Requests.UpdateRequestsResult> acceptRequests(GoogleApiClient paramGoogleApiClient, final List<String> paramList)
  {
    if (paramList == null) {}
    for (paramList = null;; paramList = (String[])paramList.toArray(new String[paramList.size()])) {
      paramGoogleApiClient.b(new b(paramList)
      {
        protected void a(fx paramAnonymousfx)
        {
          paramAnonymousfx.a(this, paramList);
        }
      });
    }
  }
  
  public PendingResult<Requests.UpdateRequestsResult> dismissRequest(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramString);
    return dismissRequests(paramGoogleApiClient, localArrayList);
  }
  
  public PendingResult<Requests.UpdateRequestsResult> dismissRequests(GoogleApiClient paramGoogleApiClient, final List<String> paramList)
  {
    if (paramList == null) {}
    for (paramList = null;; paramList = (String[])paramList.toArray(new String[paramList.size()])) {
      paramGoogleApiClient.b(new b(paramList)
      {
        protected void a(fx paramAnonymousfx)
        {
          paramAnonymousfx.b(this, paramList);
        }
      });
    }
  }
  
  public ArrayList<GameRequest> getGameRequestsFromBundle(Bundle paramBundle)
  {
    if ((paramBundle == null) || (!paramBundle.containsKey("requests"))) {
      return new ArrayList();
    }
    paramBundle = (ArrayList)paramBundle.get("requests");
    ArrayList localArrayList = new ArrayList();
    int j = paramBundle.size();
    int i = 0;
    while (i < j)
    {
      localArrayList.add((GameRequest)paramBundle.get(i));
      i += 1;
    }
    return localArrayList;
  }
  
  public ArrayList<GameRequest> getGameRequestsFromInboxResponse(Intent paramIntent)
  {
    if (paramIntent == null) {
      return new ArrayList();
    }
    return getGameRequestsFromBundle(paramIntent.getExtras());
  }
  
  public Intent getInboxIntent(GoogleApiClient paramGoogleApiClient)
  {
    return Games.c(paramGoogleApiClient).fD();
  }
  
  public int getMaxLifetimeDays(GoogleApiClient paramGoogleApiClient)
  {
    return Games.c(paramGoogleApiClient).fF();
  }
  
  public int getMaxPayloadSize(GoogleApiClient paramGoogleApiClient)
  {
    return Games.c(paramGoogleApiClient).fE();
  }
  
  public Intent getSendIntent(GoogleApiClient paramGoogleApiClient, int paramInt1, byte[] paramArrayOfByte, int paramInt2, Bitmap paramBitmap, String paramString)
  {
    return Games.c(paramGoogleApiClient).a(paramInt1, paramArrayOfByte, paramInt2, paramBitmap, paramString);
  }
  
  public PendingResult<Requests.LoadRequestsResult> loadRequests(GoogleApiClient paramGoogleApiClient, final int paramInt1, final int paramInt2, final int paramInt3)
  {
    paramGoogleApiClient.a(new a(paramInt1)
    {
      protected void a(fx paramAnonymousfx)
      {
        paramAnonymousfx.a(this, paramInt1, paramInt2, paramInt3);
      }
    });
  }
  
  public void registerRequestListener(GoogleApiClient paramGoogleApiClient, OnRequestReceivedListener paramOnRequestReceivedListener)
  {
    Games.c(paramGoogleApiClient).a(paramOnRequestReceivedListener);
  }
  
  public void unregisterRequestListener(GoogleApiClient paramGoogleApiClient)
  {
    Games.c(paramGoogleApiClient).fx();
  }
  
  private static abstract class a
    extends Games.a<Requests.LoadRequestsResult>
  {
    public Requests.LoadRequestsResult B(final Status paramStatus)
    {
      new Requests.LoadRequestsResult()
      {
        public GameRequestBuffer getRequests(int paramAnonymousInt)
        {
          return null;
        }
        
        public Status getStatus()
        {
          return paramStatus;
        }
        
        public void release() {}
      };
    }
  }
  
  private static abstract class b
    extends Games.a<Requests.UpdateRequestsResult>
  {
    public Requests.UpdateRequestsResult C(final Status paramStatus)
    {
      new Requests.UpdateRequestsResult()
      {
        public Set<String> getRequestIds()
        {
          return null;
        }
        
        public int getRequestOutcome(String paramAnonymousString)
        {
          throw new IllegalArgumentException("Unknown request ID " + paramAnonymousString);
        }
        
        public Status getStatus()
        {
          return paramStatus;
        }
        
        public void release() {}
      };
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\go.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */