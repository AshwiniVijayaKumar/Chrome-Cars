package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.a;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.internal.er;
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.maps.internal.q;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class MapView
  extends FrameLayout
{
  private GoogleMap Pn;
  private final b Pr;
  
  public MapView(Context paramContext)
  {
    super(paramContext);
    this.Pr = new b(this, paramContext, null);
  }
  
  public MapView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.Pr = new b(this, paramContext, GoogleMapOptions.createFromAttributes(paramContext, paramAttributeSet));
  }
  
  public MapView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.Pr = new b(this, paramContext, GoogleMapOptions.createFromAttributes(paramContext, paramAttributeSet));
  }
  
  public MapView(Context paramContext, GoogleMapOptions paramGoogleMapOptions)
  {
    super(paramContext);
    this.Pr = new b(this, paramContext, paramGoogleMapOptions);
  }
  
  public final GoogleMap getMap()
  {
    if (this.Pn != null) {
      return this.Pn;
    }
    this.Pr.gW();
    if (this.Pr.fj() == null) {
      return null;
    }
    try
    {
      this.Pn = new GoogleMap(((a)this.Pr.fj()).gX().getMap());
      return this.Pn;
    }
    catch (RemoteException localRemoteException)
    {
      throw new RuntimeRemoteException(localRemoteException);
    }
  }
  
  public final void onCreate(Bundle paramBundle)
  {
    this.Pr.onCreate(paramBundle);
    if (this.Pr.fj() == null)
    {
      paramBundle = this.Pr;
      b.b(this);
    }
  }
  
  public final void onDestroy()
  {
    this.Pr.onDestroy();
  }
  
  public final void onLowMemory()
  {
    this.Pr.onLowMemory();
  }
  
  public final void onPause()
  {
    this.Pr.onPause();
  }
  
  public final void onResume()
  {
    this.Pr.onResume();
  }
  
  public final void onSaveInstanceState(Bundle paramBundle)
  {
    this.Pr.onSaveInstanceState(paramBundle);
  }
  
  static class a
    implements LifecycleDelegate
  {
    private final ViewGroup Ps;
    private final IMapViewDelegate Pt;
    private View Pu;
    
    public a(ViewGroup paramViewGroup, IMapViewDelegate paramIMapViewDelegate)
    {
      this.Pt = ((IMapViewDelegate)er.f(paramIMapViewDelegate));
      this.Ps = ((ViewGroup)er.f(paramViewGroup));
    }
    
    public IMapViewDelegate gX()
    {
      return this.Pt;
    }
    
    public void onCreate(Bundle paramBundle)
    {
      try
      {
        this.Pt.onCreate(paramBundle);
        this.Pu = ((View)com.google.android.gms.dynamic.c.b(this.Pt.getView()));
        this.Ps.removeAllViews();
        this.Ps.addView(this.Pu);
        return;
      }
      catch (RemoteException paramBundle)
      {
        throw new RuntimeRemoteException(paramBundle);
      }
    }
    
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
      throw new UnsupportedOperationException("onCreateView not allowed on MapViewDelegate");
    }
    
    public void onDestroy()
    {
      try
      {
        this.Pt.onDestroy();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onDestroyView()
    {
      throw new UnsupportedOperationException("onDestroyView not allowed on MapViewDelegate");
    }
    
    public void onInflate(Activity paramActivity, Bundle paramBundle1, Bundle paramBundle2)
    {
      throw new UnsupportedOperationException("onInflate not allowed on MapViewDelegate");
    }
    
    public void onLowMemory()
    {
      try
      {
        this.Pt.onLowMemory();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onPause()
    {
      try
      {
        this.Pt.onPause();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onResume()
    {
      try
      {
        this.Pt.onResume();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
    }
    
    public void onSaveInstanceState(Bundle paramBundle)
    {
      try
      {
        this.Pt.onSaveInstanceState(paramBundle);
        return;
      }
      catch (RemoteException paramBundle)
      {
        throw new RuntimeRemoteException(paramBundle);
      }
    }
    
    public void onStart() {}
    
    public void onStop() {}
  }
  
  static class b
    extends a<MapView.a>
  {
    protected d<MapView.a> Pq;
    private final ViewGroup Pv;
    private final GoogleMapOptions Pw;
    private final Context mContext;
    
    b(ViewGroup paramViewGroup, Context paramContext, GoogleMapOptions paramGoogleMapOptions)
    {
      this.Pv = paramViewGroup;
      this.mContext = paramContext;
      this.Pw = paramGoogleMapOptions;
    }
    
    protected void a(d<MapView.a> paramd)
    {
      this.Pq = paramd;
      gW();
    }
    
    public void gW()
    {
      if ((this.Pq != null) && (fj() == null)) {}
      try
      {
        IMapViewDelegate localIMapViewDelegate = q.A(this.mContext).a(com.google.android.gms.dynamic.c.h(this.mContext), this.Pw);
        this.Pq.a(new MapView.a(this.Pv, localIMapViewDelegate));
        return;
      }
      catch (RemoteException localRemoteException)
      {
        throw new RuntimeRemoteException(localRemoteException);
      }
      catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException) {}
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\maps\MapView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */