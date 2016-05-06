package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.r;
import com.google.android.gms.maps.model.internal.g;
import com.google.android.gms.maps.model.internal.g.a;

public final class TileOverlayOptions
  implements SafeParcelable
{
  public static final TileOverlayOptionsCreator CREATOR = new TileOverlayOptionsCreator();
  private float PP;
  private boolean PQ = true;
  private g Qt;
  private TileProvider Qu;
  private boolean Qv = true;
  private final int wj;
  
  public TileOverlayOptions()
  {
    this.wj = 1;
  }
  
  TileOverlayOptions(int paramInt, IBinder paramIBinder, boolean paramBoolean1, float paramFloat, boolean paramBoolean2)
  {
    this.wj = paramInt;
    this.Qt = g.a.au(paramIBinder);
    if (this.Qt == null) {}
    for (paramIBinder = null;; paramIBinder = new TileProvider()
        {
          private final g Qw = TileOverlayOptions.a(TileOverlayOptions.this);
          
          public Tile getTile(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
          {
            try
            {
              Tile localTile = this.Qw.getTile(paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3);
              return localTile;
            }
            catch (RemoteException localRemoteException) {}
            return null;
          }
        })
    {
      this.Qu = paramIBinder;
      this.PQ = paramBoolean1;
      this.PP = paramFloat;
      this.Qv = paramBoolean2;
      return;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public TileOverlayOptions fadeIn(boolean paramBoolean)
  {
    this.Qv = paramBoolean;
    return this;
  }
  
  public boolean getFadeIn()
  {
    return this.Qv;
  }
  
  public TileProvider getTileProvider()
  {
    return this.Qu;
  }
  
  int getVersionCode()
  {
    return this.wj;
  }
  
  public float getZIndex()
  {
    return this.PP;
  }
  
  IBinder hh()
  {
    return this.Qt.asBinder();
  }
  
  public boolean isVisible()
  {
    return this.PQ;
  }
  
  public TileOverlayOptions tileProvider(final TileProvider paramTileProvider)
  {
    this.Qu = paramTileProvider;
    if (this.Qu == null) {}
    for (paramTileProvider = null;; paramTileProvider = new g.a()
        {
          public Tile getTile(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
          {
            return paramTileProvider.getTile(paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3);
          }
        })
    {
      this.Qt = paramTileProvider;
      return this;
    }
  }
  
  public TileOverlayOptions visible(boolean paramBoolean)
  {
    this.PQ = paramBoolean;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (r.hc())
    {
      j.a(this, paramParcel, paramInt);
      return;
    }
    TileOverlayOptionsCreator.a(this, paramParcel, paramInt);
  }
  
  public TileOverlayOptions zIndex(float paramFloat)
  {
    this.PP = paramFloat;
    return this;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\maps\model\TileOverlayOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */