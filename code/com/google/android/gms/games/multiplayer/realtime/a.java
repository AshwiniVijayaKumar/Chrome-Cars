package com.google.android.gms.games.multiplayer.realtime;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class a
  extends d<Room>
{
  public a(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }
  
  protected Room d(int paramInt1, int paramInt2)
  {
    return new c(this.zU, paramInt1, paramInt2);
  }
  
  protected String getPrimaryDataMarkerColumn()
  {
    return "external_match_id";
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\games\multiplayer\realtime\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */