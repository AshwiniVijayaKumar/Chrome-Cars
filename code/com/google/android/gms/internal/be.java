package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationServerParameters;
import java.util.Map;

public final class be
  extends bf.a
{
  private Map<Class<? extends com.google.android.gms.ads.mediation.NetworkExtras>, com.google.android.gms.ads.mediation.NetworkExtras> mQ;
  
  private <NETWORK_EXTRAS extends com.google.ads.mediation.NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> bg n(String paramString)
    throws RemoteException
  {
    try
    {
      Class localClass = Class.forName(paramString, false, be.class.getClassLoader());
      if (!MediationAdapter.class.isAssignableFrom(localClass))
      {
        da.w("Could not instantiate mediation adapter: " + paramString + ".");
        throw new RemoteException();
      }
    }
    catch (Throwable localThrowable)
    {
      da.w("Could not instantiate mediation adapter: " + paramString + ". " + localThrowable.getMessage());
      throw new RemoteException();
    }
    Object localObject = (MediationAdapter)localThrowable.newInstance();
    localObject = new bi((MediationAdapter)localObject, (com.google.ads.mediation.NetworkExtras)this.mQ.get(((MediationAdapter)localObject).getAdditionalParametersType()));
    return (bg)localObject;
  }
  
  public void c(Map<Class<? extends com.google.android.gms.ads.mediation.NetworkExtras>, com.google.android.gms.ads.mediation.NetworkExtras> paramMap)
  {
    this.mQ = paramMap;
  }
  
  public bg m(String paramString)
    throws RemoteException
  {
    return n(paramString);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\be.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */