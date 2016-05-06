package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api.b;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.People.LoadPeopleResult;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.Plus.a;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;
import java.util.Collection;

public final class il
  implements People
{
  private final Api.b<e> Rw;
  
  public il(Api.b<e> paramb)
  {
    this.Rw = paramb;
  }
  
  public Person getCurrentPerson(GoogleApiClient paramGoogleApiClient)
  {
    return Plus.a(paramGoogleApiClient, this.Rw).getCurrentPerson();
  }
  
  public PendingResult<People.LoadPeopleResult> load(GoogleApiClient paramGoogleApiClient, final Collection<String> paramCollection)
  {
    paramGoogleApiClient.a(new a(this.Rw)
    {
      protected void a(e paramAnonymouse)
      {
        paramAnonymouse.a(this, paramCollection);
      }
    });
  }
  
  public PendingResult<People.LoadPeopleResult> load(GoogleApiClient paramGoogleApiClient, final String... paramVarArgs)
  {
    paramGoogleApiClient.a(new a(this.Rw)
    {
      protected void a(e paramAnonymouse)
      {
        paramAnonymouse.c(this, paramVarArgs);
      }
    });
  }
  
  public PendingResult<People.LoadPeopleResult> loadConnected(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.a(new a(this.Rw)
    {
      protected void a(e paramAnonymouse)
      {
        paramAnonymouse.j(this);
      }
    });
  }
  
  public PendingResult<People.LoadPeopleResult> loadVisible(GoogleApiClient paramGoogleApiClient, final int paramInt, final String paramString)
  {
    paramGoogleApiClient.a(new a(this.Rw)
    {
      protected void a(e paramAnonymouse)
      {
        paramAnonymouse.a(this, paramInt, paramString);
      }
    });
  }
  
  public PendingResult<People.LoadPeopleResult> loadVisible(GoogleApiClient paramGoogleApiClient, final String paramString)
  {
    paramGoogleApiClient.a(new a(this.Rw)
    {
      protected void a(e paramAnonymouse)
      {
        paramAnonymouse.i(this, paramString);
      }
    });
  }
  
  private static abstract class a
    extends Plus.a<People.LoadPeopleResult>
  {
    a(Api.b<e> paramb)
    {
      super();
    }
    
    public People.LoadPeopleResult N(final Status paramStatus)
    {
      new People.LoadPeopleResult()
      {
        public String getNextPageToken()
        {
          return null;
        }
        
        public PersonBuffer getPersonBuffer()
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
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\il.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */