package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.internal.gv;

public final class LoadMatchesResponse
{
  private final InvitationBuffer JV;
  private final TurnBasedMatchBuffer JW;
  private final TurnBasedMatchBuffer JX;
  private final TurnBasedMatchBuffer JY;
  
  public LoadMatchesResponse(Bundle paramBundle)
  {
    DataHolder localDataHolder = a(paramBundle, 0);
    if (localDataHolder != null)
    {
      this.JV = new InvitationBuffer(localDataHolder);
      localDataHolder = a(paramBundle, 1);
      if (localDataHolder == null) {
        break label101;
      }
      this.JW = new TurnBasedMatchBuffer(localDataHolder);
      label48:
      localDataHolder = a(paramBundle, 2);
      if (localDataHolder == null) {
        break label109;
      }
    }
    label101:
    label109:
    for (this.JX = new TurnBasedMatchBuffer(localDataHolder);; this.JX = null)
    {
      paramBundle = a(paramBundle, 3);
      if (paramBundle == null) {
        break label117;
      }
      this.JY = new TurnBasedMatchBuffer(paramBundle);
      return;
      this.JV = null;
      break;
      this.JW = null;
      break label48;
    }
    label117:
    this.JY = null;
  }
  
  private static DataHolder a(Bundle paramBundle, int paramInt)
  {
    String str = gv.aW(paramInt);
    if (!paramBundle.containsKey(str)) {
      return null;
    }
    return (DataHolder)paramBundle.getParcelable(str);
  }
  
  public void close()
  {
    if (this.JV != null) {
      this.JV.close();
    }
    if (this.JW != null) {
      this.JW.close();
    }
    if (this.JX != null) {
      this.JX.close();
    }
    if (this.JY != null) {
      this.JY.close();
    }
  }
  
  public TurnBasedMatchBuffer getCompletedMatches()
  {
    return this.JY;
  }
  
  public InvitationBuffer getInvitations()
  {
    return this.JV;
  }
  
  public TurnBasedMatchBuffer getMyTurnMatches()
  {
    return this.JW;
  }
  
  public TurnBasedMatchBuffer getTheirTurnMatches()
  {
    return this.JX;
  }
  
  public boolean hasData()
  {
    if ((this.JV != null) && (this.JV.getCount() > 0)) {}
    while (((this.JW != null) && (this.JW.getCount() > 0)) || ((this.JX != null) && (this.JX.getCount() > 0)) || ((this.JY != null) && (this.JY.getCount() > 0))) {
      return true;
    }
    return false;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\games\multiplayer\turnbased\LoadMatchesResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */