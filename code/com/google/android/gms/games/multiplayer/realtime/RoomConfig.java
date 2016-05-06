package com.google.android.gms.games.multiplayer.realtime;

import android.os.Bundle;
import com.google.android.gms.internal.er;
import java.util.ArrayList;
import java.util.Arrays;

public final class RoomConfig
{
  private final String GP;
  private final RoomUpdateListener JK;
  private final RoomStatusUpdateListener JL;
  private final RealTimeMessageReceivedListener JM;
  private final String[] JN;
  private final Bundle JO;
  private final boolean JP;
  private final int Jv;
  
  private RoomConfig(Builder paramBuilder)
  {
    this.JK = paramBuilder.JK;
    this.JL = paramBuilder.JL;
    this.JM = paramBuilder.JM;
    this.GP = paramBuilder.JQ;
    this.Jv = paramBuilder.Jv;
    this.JO = paramBuilder.JO;
    this.JP = paramBuilder.JP;
    int i = paramBuilder.JR.size();
    this.JN = ((String[])paramBuilder.JR.toArray(new String[i]));
    if (this.JM == null) {
      er.a(this.JP, "Must either enable sockets OR specify a message listener");
    }
  }
  
  public static Builder builder(RoomUpdateListener paramRoomUpdateListener)
  {
    return new Builder(paramRoomUpdateListener, null);
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
  
  public String getInvitationId()
  {
    return this.GP;
  }
  
  public String[] getInvitedPlayerIds()
  {
    return this.JN;
  }
  
  public RealTimeMessageReceivedListener getMessageReceivedListener()
  {
    return this.JM;
  }
  
  public RoomStatusUpdateListener getRoomStatusUpdateListener()
  {
    return this.JL;
  }
  
  public RoomUpdateListener getRoomUpdateListener()
  {
    return this.JK;
  }
  
  public int getVariant()
  {
    return this.Jv;
  }
  
  public boolean isSocketEnabled()
  {
    return this.JP;
  }
  
  public static final class Builder
  {
    final RoomUpdateListener JK;
    RoomStatusUpdateListener JL;
    RealTimeMessageReceivedListener JM;
    Bundle JO;
    boolean JP = false;
    String JQ = null;
    ArrayList<String> JR = new ArrayList();
    int Jv = -1;
    
    private Builder(RoomUpdateListener paramRoomUpdateListener)
    {
      this.JK = ((RoomUpdateListener)er.b(paramRoomUpdateListener, "Must provide a RoomUpdateListener"));
    }
    
    public Builder addPlayersToInvite(ArrayList<String> paramArrayList)
    {
      er.f(paramArrayList);
      this.JR.addAll(paramArrayList);
      return this;
    }
    
    public Builder addPlayersToInvite(String... paramVarArgs)
    {
      er.f(paramVarArgs);
      this.JR.addAll(Arrays.asList(paramVarArgs));
      return this;
    }
    
    public RoomConfig build()
    {
      return new RoomConfig(this, null);
    }
    
    public Builder setAutoMatchCriteria(Bundle paramBundle)
    {
      this.JO = paramBundle;
      return this;
    }
    
    public Builder setInvitationIdToAccept(String paramString)
    {
      er.f(paramString);
      this.JQ = paramString;
      return this;
    }
    
    public Builder setMessageReceivedListener(RealTimeMessageReceivedListener paramRealTimeMessageReceivedListener)
    {
      this.JM = paramRealTimeMessageReceivedListener;
      return this;
    }
    
    public Builder setRoomStatusUpdateListener(RoomStatusUpdateListener paramRoomStatusUpdateListener)
    {
      this.JL = paramRoomStatusUpdateListener;
      return this;
    }
    
    public Builder setSocketCommunicationEnabled(boolean paramBoolean)
    {
      this.JP = paramBoolean;
      return this;
    }
    
    public Builder setVariant(int paramInt)
    {
      if ((paramInt == -1) || (paramInt > 0)) {}
      for (boolean bool = true;; bool = false)
      {
        er.b(bool, "Variant must be a positive integer or Room.ROOM_VARIANT_ANY");
        this.Jv = paramInt;
        return this;
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\games\multiplayer\realtime\RoomConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */