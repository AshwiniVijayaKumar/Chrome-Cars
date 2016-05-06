package com.google.android.gms.internal;

import android.app.Activity;
import android.os.RemoteException;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.ads.mediation.admob.AdMobServerParameters;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.c;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

public final class bi<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters>
  extends bg.a
{
  private final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> mR;
  private final NETWORK_EXTRAS mS;
  
  public bi(MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> paramMediationAdapter, NETWORK_EXTRAS paramNETWORK_EXTRAS)
  {
    this.mR = paramMediationAdapter;
    this.mS = paramNETWORK_EXTRAS;
  }
  
  private SERVER_PARAMETERS a(String paramString1, int paramInt, String paramString2)
    throws RemoteException
  {
    Object localObject1;
    Object localObject2;
    if (paramString1 != null) {
      try
      {
        localObject1 = new JSONObject(paramString1);
        paramString1 = new HashMap(((JSONObject)localObject1).length());
        localObject2 = ((JSONObject)localObject1).keys();
        while (((Iterator)localObject2).hasNext())
        {
          String str = (String)((Iterator)localObject2).next();
          paramString1.put(str, ((JSONObject)localObject1).getString(str));
        }
        localObject2 = this.mR.getServerParametersType();
      }
      catch (Throwable paramString1)
      {
        da.b("Could not get MediationServerParameters.", paramString1);
        throw new RemoteException();
      }
    }
    for (;;)
    {
      localObject1 = null;
      if (localObject2 != null)
      {
        localObject1 = (MediationServerParameters)((Class)localObject2).newInstance();
        ((MediationServerParameters)localObject1).load(paramString1);
      }
      if (!(localObject1 instanceof AdMobServerParameters)) {
        break;
      }
      paramString1 = (AdMobServerParameters)localObject1;
      paramString1.adJson = paramString2;
      paramString1.tagForChildDirectedTreatment = paramInt;
      return (SERVER_PARAMETERS)localObject1;
      paramString1 = new HashMap(0);
    }
    return (SERVER_PARAMETERS)localObject1;
  }
  
  public void a(b paramb, ab paramab, z paramz, String paramString, bh parambh)
    throws RemoteException
  {
    a(paramb, paramab, paramz, paramString, null, parambh);
  }
  
  public void a(b paramb, ab paramab, z paramz, String paramString1, String paramString2, bh parambh)
    throws RemoteException
  {
    if (!(this.mR instanceof MediationBannerAdapter))
    {
      da.w("MediationAdapter is not a MediationBannerAdapter: " + this.mR.getClass().getCanonicalName());
      throw new RemoteException();
    }
    da.s("Requesting banner ad from adapter.");
    try
    {
      ((MediationBannerAdapter)this.mR).requestBannerAd(new bj(parambh), (Activity)c.b(paramb), a(paramString1, paramz.tagForChildDirectedTreatment, paramString2), bk.b(paramab), bk.e(paramz), this.mS);
      return;
    }
    catch (Throwable paramb)
    {
      da.b("Could not request banner ad from adapter.", paramb);
      throw new RemoteException();
    }
  }
  
  public void a(b paramb, z paramz, String paramString, bh parambh)
    throws RemoteException
  {
    a(paramb, paramz, paramString, null, parambh);
  }
  
  public void a(b paramb, z paramz, String paramString1, String paramString2, bh parambh)
    throws RemoteException
  {
    if (!(this.mR instanceof MediationInterstitialAdapter))
    {
      da.w("MediationAdapter is not a MediationInterstitialAdapter: " + this.mR.getClass().getCanonicalName());
      throw new RemoteException();
    }
    da.s("Requesting interstitial ad from adapter.");
    try
    {
      ((MediationInterstitialAdapter)this.mR).requestInterstitialAd(new bj(parambh), (Activity)c.b(paramb), a(paramString1, paramz.tagForChildDirectedTreatment, paramString2), bk.e(paramz), this.mS);
      return;
    }
    catch (Throwable paramb)
    {
      da.b("Could not request interstitial ad from adapter.", paramb);
      throw new RemoteException();
    }
  }
  
  public void destroy()
    throws RemoteException
  {
    try
    {
      this.mR.destroy();
      return;
    }
    catch (Throwable localThrowable)
    {
      da.b("Could not destroy adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public b getView()
    throws RemoteException
  {
    if (!(this.mR instanceof MediationBannerAdapter))
    {
      da.w("MediationAdapter is not a MediationBannerAdapter: " + this.mR.getClass().getCanonicalName());
      throw new RemoteException();
    }
    try
    {
      b localb = c.h(((MediationBannerAdapter)this.mR).getBannerView());
      return localb;
    }
    catch (Throwable localThrowable)
    {
      da.b("Could not get banner view from adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public void showInterstitial()
    throws RemoteException
  {
    if (!(this.mR instanceof MediationInterstitialAdapter))
    {
      da.w("MediationAdapter is not a MediationInterstitialAdapter: " + this.mR.getClass().getCanonicalName());
      throw new RemoteException();
    }
    da.s("Showing interstitial from adapter.");
    try
    {
      ((MediationInterstitialAdapter)this.mR).showInterstitial();
      return;
    }
    catch (Throwable localThrowable)
    {
      da.b("Could not show interstitial from adapter.", localThrowable);
      throw new RemoteException();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\bi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */