package com.google.android.gms.games.request;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;
import java.util.ArrayList;
import java.util.Arrays;

public final class GameRequestEntity
  implements SafeParcelable, GameRequest
{
  public static final GameRequestEntityCreator CREATOR = new GameRequestEntityCreator();
  private final int AI;
  private final String Hh;
  private final GameEntity Jq;
  private final long Jr;
  private final byte[] Kf;
  private final PlayerEntity Km;
  private final ArrayList<PlayerEntity> Kn;
  private final long Ko;
  private final Bundle Kp;
  private final int wj;
  
  GameRequestEntity(int paramInt1, GameEntity paramGameEntity, PlayerEntity paramPlayerEntity, byte[] paramArrayOfByte, String paramString, ArrayList<PlayerEntity> paramArrayList, int paramInt2, long paramLong1, long paramLong2, Bundle paramBundle)
  {
    this.wj = paramInt1;
    this.Jq = paramGameEntity;
    this.Km = paramPlayerEntity;
    this.Kf = paramArrayOfByte;
    this.Hh = paramString;
    this.Kn = paramArrayList;
    this.AI = paramInt2;
    this.Jr = paramLong1;
    this.Ko = paramLong2;
    this.Kp = paramBundle;
  }
  
  public GameRequestEntity(GameRequest paramGameRequest)
  {
    this.wj = 1;
    this.Jq = new GameEntity(paramGameRequest.getGame());
    this.Km = new PlayerEntity(paramGameRequest.getSender());
    this.Hh = paramGameRequest.getRequestId();
    this.AI = paramGameRequest.getType();
    this.Jr = paramGameRequest.getCreationTimestamp();
    this.Ko = paramGameRequest.getExpirationTimestamp();
    Object localObject = paramGameRequest.getData();
    if (localObject == null) {
      this.Kf = null;
    }
    for (;;)
    {
      localObject = paramGameRequest.fU();
      int j = ((ArrayList)localObject).size();
      this.Kn = new ArrayList(j);
      this.Kp = new Bundle();
      int i = 0;
      while (i < j)
      {
        Player localPlayer = (Player)((Player)((ArrayList)localObject).get(i)).freeze();
        String str = localPlayer.getPlayerId();
        this.Kn.add((PlayerEntity)localPlayer);
        this.Kp.putInt(str, paramGameRequest.getRecipientStatus(str));
        i += 1;
      }
      this.Kf = new byte[localObject.length];
      System.arraycopy(localObject, 0, this.Kf, 0, localObject.length);
    }
  }
  
  static int a(GameRequest paramGameRequest)
  {
    return ep.hashCode(new Object[] { paramGameRequest.getGame(), paramGameRequest.fU(), paramGameRequest.getRequestId(), paramGameRequest.getSender(), b(paramGameRequest), Integer.valueOf(paramGameRequest.getType()), Long.valueOf(paramGameRequest.getCreationTimestamp()), Long.valueOf(paramGameRequest.getExpirationTimestamp()) });
  }
  
  static boolean a(GameRequest paramGameRequest, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof GameRequest)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramGameRequest == paramObject);
      paramObject = (GameRequest)paramObject;
      if ((!ep.equal(((GameRequest)paramObject).getGame(), paramGameRequest.getGame())) || (!ep.equal(((GameRequest)paramObject).fU(), paramGameRequest.fU())) || (!ep.equal(((GameRequest)paramObject).getRequestId(), paramGameRequest.getRequestId())) || (!ep.equal(((GameRequest)paramObject).getSender(), paramGameRequest.getSender())) || (!Arrays.equals(b((GameRequest)paramObject), b(paramGameRequest))) || (!ep.equal(Integer.valueOf(((GameRequest)paramObject).getType()), Integer.valueOf(paramGameRequest.getType()))) || (!ep.equal(Long.valueOf(((GameRequest)paramObject).getCreationTimestamp()), Long.valueOf(paramGameRequest.getCreationTimestamp())))) {
        break;
      }
      bool1 = bool2;
    } while (ep.equal(Long.valueOf(((GameRequest)paramObject).getExpirationTimestamp()), Long.valueOf(paramGameRequest.getExpirationTimestamp())));
    return false;
  }
  
  private static int[] b(GameRequest paramGameRequest)
  {
    ArrayList localArrayList = paramGameRequest.fU();
    int j = localArrayList.size();
    int[] arrayOfInt = new int[j];
    int i = 0;
    while (i < j)
    {
      arrayOfInt[i] = paramGameRequest.getRecipientStatus(((Player)localArrayList.get(i)).getPlayerId());
      i += 1;
    }
    return arrayOfInt;
  }
  
  static String c(GameRequest paramGameRequest)
  {
    return ep.e(paramGameRequest).a("Game", paramGameRequest.getGame()).a("Sender", paramGameRequest.getSender()).a("Recipients", paramGameRequest.fU()).a("Data", paramGameRequest.getData()).a("RequestId", paramGameRequest.getRequestId()).a("Type", Integer.valueOf(paramGameRequest.getType())).a("CreationTimestamp", Long.valueOf(paramGameRequest.getCreationTimestamp())).a("ExpirationTimestamp", Long.valueOf(paramGameRequest.getExpirationTimestamp())).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public ArrayList<Player> fU()
  {
    return new ArrayList(this.Kn);
  }
  
  public GameRequest freeze()
  {
    return this;
  }
  
  public long getCreationTimestamp()
  {
    return this.Jr;
  }
  
  public byte[] getData()
  {
    return this.Kf;
  }
  
  public long getExpirationTimestamp()
  {
    return this.Ko;
  }
  
  public Game getGame()
  {
    return this.Jq;
  }
  
  public Player getRecipient()
  {
    if (this.Kn.isEmpty()) {
      return null;
    }
    return (Player)this.Kn.get(0);
  }
  
  public int getRecipientStatus()
  {
    return this.Kp.getInt(((PlayerEntity)this.Kn.get(0)).getPlayerId(), 0);
  }
  
  public int getRecipientStatus(String paramString)
  {
    return this.Kp.getInt(paramString, 0);
  }
  
  public String getRequestId()
  {
    return this.Hh;
  }
  
  public Player getSender()
  {
    return this.Km;
  }
  
  public int getType()
  {
    return this.AI;
  }
  
  public int getVersionCode()
  {
    return this.wj;
  }
  
  public Bundle gf()
  {
    return this.Kp;
  }
  
  public int hashCode()
  {
    return a(this);
  }
  
  public boolean isConsumed()
  {
    return getRecipientStatus() == 1;
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public String toString()
  {
    return c(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    GameRequestEntityCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\games\request\GameRequestEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */