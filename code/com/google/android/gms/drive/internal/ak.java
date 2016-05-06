package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a.c;

public class ak
  extends c
{
  private final a.c<Status> vj;
  
  public ak(a.c<Status> paramc)
  {
    this.vj = paramc;
  }
  
  public void l(Status paramStatus)
    throws RemoteException
  {
    this.vj.b(paramStatus);
  }
  
  public void onSuccess()
    throws RemoteException
  {
    this.vj.b(Status.zQ);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\internal\ak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */