package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestEntity;
import java.util.ArrayList;

public final class gz
  implements SafeParcelable, GameRequest
{
  public static final ha CREATOR = new ha();
  private final ArrayList<GameRequestEntity> IG;
  private final int wj;
  
  gz(int paramInt, ArrayList<GameRequestEntity> paramArrayList)
  {
    this.wj = paramInt;
    this.IG = paramArrayList;
    fR();
  }
  
  private void fR()
  {
    GameRequest localGameRequest1;
    int i;
    label39:
    GameRequest localGameRequest2;
    if (!this.IG.isEmpty())
    {
      bool = true;
      ed.v(bool);
      localGameRequest1 = (GameRequest)this.IG.get(0);
      int j = this.IG.size();
      i = 1;
      if (i >= j) {
        return;
      }
      localGameRequest2 = (GameRequest)this.IG.get(i);
      if (localGameRequest1.getType() != localGameRequest2.getType()) {
        break label116;
      }
    }
    label116:
    for (boolean bool = true;; bool = false)
    {
      ed.a(bool, "All the requests must be of the same type");
      ed.a(localGameRequest1.getSender().equals(localGameRequest2.getSender()), "All the requests must be from the same sender");
      i += 1;
      break label39;
      bool = false;
      break;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof gz)) {
      return false;
    }
    if (this == paramObject) {
      return true;
    }
    paramObject = (gz)paramObject;
    if (((gz)paramObject).IG.size() != this.IG.size()) {
      return false;
    }
    int j = this.IG.size();
    int i = 0;
    while (i < j)
    {
      if (!((GameRequest)this.IG.get(i)).equals((GameRequest)((gz)paramObject).IG.get(i))) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public ArrayList<GameRequest> fT()
  {
    return new ArrayList(this.IG);
  }
  
  public ArrayList<Player> fU()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public GameRequest freeze()
  {
    return this;
  }
  
  public long getCreationTimestamp()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public byte[] getData()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public long getExpirationTimestamp()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public Game getGame()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public Player getRecipient()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public int getRecipientStatus()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public int getRecipientStatus(String paramString)
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public String getRequestId()
  {
    return ((GameRequestEntity)this.IG.get(0)).getRequestId();
  }
  
  public Player getSender()
  {
    return ((GameRequestEntity)this.IG.get(0)).getSender();
  }
  
  public int getType()
  {
    return ((GameRequestEntity)this.IG.get(0)).getType();
  }
  
  public int getVersionCode()
  {
    return this.wj;
  }
  
  public int hashCode()
  {
    return ep.hashCode(this.IG.toArray());
  }
  
  public boolean isConsumed()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ha.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\gz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */