package com.google.android.gms.internal;

import android.net.Uri;
import com.google.android.gms.common.api.Api.b;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.Moments;
import com.google.android.gms.plus.Moments.LoadMomentsResult;
import com.google.android.gms.plus.Plus.a;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.plus.model.moments.Moment;
import com.google.android.gms.plus.model.moments.MomentBuffer;

public final class ik
  implements Moments
{
  private final Api.b<e> Rw;
  
  public ik(Api.b<e> paramb)
  {
    this.Rw = paramb;
  }
  
  public PendingResult<Moments.LoadMomentsResult> load(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.a(new a(this.Rw)
    {
      protected void a(e paramAnonymouse)
      {
        paramAnonymouse.i(this);
      }
    });
  }
  
  public PendingResult<Moments.LoadMomentsResult> load(GoogleApiClient paramGoogleApiClient, final int paramInt, final String paramString1, final Uri paramUri, final String paramString2, final String paramString3)
  {
    paramGoogleApiClient.a(new a(this.Rw)
    {
      protected void a(e paramAnonymouse)
      {
        paramAnonymouse.a(this, paramInt, paramString1, paramUri, paramString2, paramString3);
      }
    });
  }
  
  public PendingResult<Status> remove(GoogleApiClient paramGoogleApiClient, final String paramString)
  {
    paramGoogleApiClient.b(new b(this.Rw)
    {
      protected void a(e paramAnonymouse)
      {
        paramAnonymouse.removeMoment(paramString);
        a(Status.zQ);
      }
    });
  }
  
  public PendingResult<Status> write(GoogleApiClient paramGoogleApiClient, final Moment paramMoment)
  {
    paramGoogleApiClient.b(new c(this.Rw)
    {
      protected void a(e paramAnonymouse)
      {
        paramAnonymouse.a(this, paramMoment);
      }
    });
  }
  
  private static abstract class a
    extends Plus.a<Moments.LoadMomentsResult>
  {
    a(Api.b<e> paramb)
    {
      super();
    }
    
    public Moments.LoadMomentsResult M(final Status paramStatus)
    {
      new Moments.LoadMomentsResult()
      {
        public MomentBuffer getMomentBuffer()
        {
          return null;
        }
        
        public String getNextPageToken()
        {
          return null;
        }
        
        public Status getStatus()
        {
          return paramStatus;
        }
        
        public String getUpdated()
        {
          return null;
        }
        
        public void release() {}
      };
    }
  }
  
  private static abstract class b
    extends Plus.a<Status>
  {
    b(Api.b<e> paramb)
    {
      super();
    }
    
    public Status f(Status paramStatus)
    {
      return paramStatus;
    }
  }
  
  private static abstract class c
    extends Plus.a<Status>
  {
    c(Api.b<e> paramb)
    {
      super();
    }
    
    public Status f(Status paramStatus)
    {
      return paramStatus;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ik.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */