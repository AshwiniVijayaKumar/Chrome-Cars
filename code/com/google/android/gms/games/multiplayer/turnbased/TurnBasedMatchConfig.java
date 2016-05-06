package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import com.google.android.gms.internal.er;
import java.util.ArrayList;

public final class TurnBasedMatchConfig
{
  private final String[] JN;
  private final Bundle JO;
  private final int JZ;
  private final int Jv;
  
  private TurnBasedMatchConfig(Builder paramBuilder)
  {
    this.Jv = paramBuilder.Jv;
    this.JZ = paramBuilder.JZ;
    this.JO = paramBuilder.JO;
    int i = paramBuilder.JR.size();
    this.JN = ((String[])paramBuilder.JR.toArray(new String[i]));
  }
  
  public static Builder builder()
  {
    return new Builder(null);
  }
  
  public static Bundle createAutoMatchCriteria(int paramInt1, int paramInt2, long paramLong)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("min_automatch_players", paramInt1);
    localBundle.putInt("max_automatch_players", paramInt2);
    localBundle.putLong("exclusive_bit_mask", paramLong);
    return localBundle;
  }
  
  public Bundle getAutoMatchCriteria()
  {
    return this.JO;
  }
  
  public String[] getInvitedPlayerIds()
  {
    return this.JN;
  }
  
  public int getMinPlayers()
  {
    return this.JZ;
  }
  
  public int getVariant()
  {
    return this.Jv;
  }
  
  public static final class Builder
  {
    Bundle JO = null;
    ArrayList<String> JR = new ArrayList();
    int JZ = 2;
    int Jv = -1;
    
    public Builder addInvitedPlayer(String paramString)
    {
      er.f(paramString);
      this.JR.add(paramString);
      return this;
    }
    
    public Builder addInvitedPlayers(ArrayList<String> paramArrayList)
    {
      er.f(paramArrayList);
      this.JR.addAll(paramArrayList);
      return this;
    }
    
    public TurnBasedMatchConfig build()
    {
      return new TurnBasedMatchConfig(this, null);
    }
    
    public Builder setAutoMatchCriteria(Bundle paramBundle)
    {
      this.JO = paramBundle;
      return this;
    }
    
    public Builder setMinPlayers(int paramInt)
    {
      this.JZ = paramInt;
      return this;
    }
    
    public Builder setVariant(int paramInt)
    {
      if ((paramInt == -1) || (paramInt > 0)) {}
      for (boolean bool = true;; bool = false)
      {
        er.b(bool, "Variant must be a positive integer or TurnBasedMatch.MATCH_VARIANT_ANY");
        this.Jv = paramInt;
        return this;
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\games\multiplayer\turnbased\TurnBasedMatchConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */