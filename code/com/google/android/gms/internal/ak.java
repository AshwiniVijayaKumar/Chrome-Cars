package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.c;

public final class ak
{
  private final be lF = new be();
  private ag lG;
  private ViewGroup lH;
  private AdListener lc;
  private AppEventListener lq;
  private AdSize[] lr;
  private String ls;
  
  public ak(ViewGroup paramViewGroup)
  {
    this.lH = paramViewGroup;
  }
  
  public ak(ViewGroup paramViewGroup, AttributeSet paramAttributeSet, boolean paramBoolean)
  {
    this.lH = paramViewGroup;
    Context localContext = paramViewGroup.getContext();
    try
    {
      paramAttributeSet = new ae(localContext, paramAttributeSet);
      this.lr = paramAttributeSet.c(paramBoolean);
      this.ls = paramAttributeSet.getAdUnitId();
      if (paramViewGroup.isInEditMode()) {
        cz.a(paramViewGroup, new ab(localContext, this.lr[0]), "Ads by Google");
      }
      return;
    }
    catch (IllegalArgumentException paramAttributeSet)
    {
      cz.a(paramViewGroup, new ab(localContext, AdSize.BANNER), paramAttributeSet.getMessage(), paramAttributeSet.getMessage());
    }
  }
  
  private void am()
  {
    try
    {
      b localb = this.lG.P();
      if (localb == null) {
        return;
      }
      this.lH.addView((View)c.b(localb));
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Failed to get an ad frame.", localRemoteException);
    }
  }
  
  private void an()
    throws RemoteException
  {
    if (((this.lr == null) || (this.ls == null)) && (this.lG == null)) {
      throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
    }
    Context localContext = this.lH.getContext();
    this.lG = y.a(localContext, new ab(localContext, this.lr), this.ls, this.lF);
    if (this.lc != null) {
      this.lG.a(new x(this.lc));
    }
    if (this.lq != null) {
      this.lG.a(new ad(this.lq));
    }
    am();
  }
  
  public void a(aj paramaj)
  {
    try
    {
      if (this.lG == null) {
        an();
      }
      if (this.lG.a(new z(this.lH.getContext(), paramaj))) {
        this.lF.c(paramaj.ak());
      }
      return;
    }
    catch (RemoteException paramaj)
    {
      da.b("Failed to load ad.", paramaj);
    }
  }
  
  public void a(AdSize... paramVarArgs)
  {
    this.lr = paramVarArgs;
    try
    {
      if (this.lG != null) {
        this.lG.a(new ab(this.lH.getContext(), this.lr));
      }
      this.lH.requestLayout();
      return;
    }
    catch (RemoteException paramVarArgs)
    {
      for (;;)
      {
        da.b("Failed to set the ad size.", paramVarArgs);
      }
    }
  }
  
  public void destroy()
  {
    try
    {
      if (this.lG != null) {
        this.lG.destroy();
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Failed to destroy AdView.", localRemoteException);
    }
  }
  
  public AdListener getAdListener()
  {
    return this.lc;
  }
  
  public AdSize getAdSize()
  {
    try
    {
      if (this.lG != null)
      {
        AdSize localAdSize = this.lG.Q().ai();
        return localAdSize;
      }
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Failed to get the current AdSize.", localRemoteException);
      if (this.lr != null) {
        return this.lr[0];
      }
    }
    return null;
  }
  
  public AdSize[] getAdSizes()
  {
    return this.lr;
  }
  
  public String getAdUnitId()
  {
    return this.ls;
  }
  
  public AppEventListener getAppEventListener()
  {
    return this.lq;
  }
  
  public void pause()
  {
    try
    {
      if (this.lG != null) {
        this.lG.pause();
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Failed to call pause.", localRemoteException);
    }
  }
  
  public void recordManualImpression()
  {
    try
    {
      this.lG.Z();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Failed to record impression.", localRemoteException);
    }
  }
  
  public void resume()
  {
    try
    {
      if (this.lG != null) {
        this.lG.resume();
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Failed to call resume.", localRemoteException);
    }
  }
  
  public void setAdListener(AdListener paramAdListener)
  {
    try
    {
      this.lc = paramAdListener;
      ag localag;
      if (this.lG != null)
      {
        localag = this.lG;
        if (paramAdListener == null) {
          break label38;
        }
      }
      label38:
      for (paramAdListener = new x(paramAdListener);; paramAdListener = null)
      {
        localag.a(paramAdListener);
        return;
      }
      return;
    }
    catch (RemoteException paramAdListener)
    {
      da.b("Failed to set the AdListener.", paramAdListener);
    }
  }
  
  public void setAdSizes(AdSize... paramVarArgs)
  {
    if (this.lr != null) {
      throw new IllegalStateException("The ad size can only be set once on AdView.");
    }
    a(paramVarArgs);
  }
  
  public void setAdUnitId(String paramString)
  {
    if (this.ls != null) {
      throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
    }
    this.ls = paramString;
  }
  
  public void setAppEventListener(AppEventListener paramAppEventListener)
  {
    try
    {
      this.lq = paramAppEventListener;
      ag localag;
      if (this.lG != null)
      {
        localag = this.lG;
        if (paramAppEventListener == null) {
          break label38;
        }
      }
      label38:
      for (paramAppEventListener = new ad(paramAppEventListener);; paramAppEventListener = null)
      {
        localag.a(paramAppEventListener);
        return;
      }
      return;
    }
    catch (RemoteException paramAppEventListener)
    {
      da.b("Failed to set the AppEventListener.", paramAppEventListener);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */