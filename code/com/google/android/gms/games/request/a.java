package com.google.android.gms.games.request;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.d;
import java.util.ArrayList;

public final class a
  extends com.google.android.gms.common.data.b
  implements GameRequest
{
  private final int IN;
  
  public a(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    super(paramDataHolder, paramInt1);
    this.IN = paramInt2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return GameRequestEntity.a(this, paramObject);
  }
  
  public ArrayList<Player> fU()
  {
    ArrayList localArrayList = new ArrayList(this.IN);
    int i = 0;
    while (i < this.IN)
    {
      localArrayList.add(new d(this.zU, this.zW + i, "recipient_"));
      i += 1;
    }
    return localArrayList;
  }
  
  public GameRequest freeze()
  {
    return new GameRequestEntity(this);
  }
  
  public long getCreationTimestamp()
  {
    return getLong("creation_timestamp");
  }
  
  public byte[] getData()
  {
    return getByteArray("data");
  }
  
  public long getExpirationTimestamp()
  {
    return getLong("expiration_timestamp");
  }
  
  public Game getGame()
  {
    return new com.google.android.gms.games.b(this.zU, this.zW);
  }
  
  public Player getRecipient()
  {
    return new d(this.zU, this.zW, "recipient_");
  }
  
  public int getRecipientStatus()
  {
    return getInteger("recipient_status");
  }
  
  public int getRecipientStatus(String paramString)
  {
    int i = this.zW;
    while (i < this.zW + this.IN)
    {
      int j = this.zU.I(i);
      if (this.zU.getString("recipient_external_player_id", i, j).equals(paramString)) {
        return this.zU.getInteger("recipient_status", i, j);
      }
      i += 1;
    }
    return -1;
  }
  
  public String getRequestId()
  {
    return getString("external_request_id");
  }
  
  public Player getSender()
  {
    return new d(this.zU, this.zW, "sender_");
  }
  
  public int getType()
  {
    return getInteger("type");
  }
  
  public int hashCode()
  {
    return GameRequestEntity.a(this);
  }
  
  public boolean isConsumed()
  {
    return getRecipientStatus() == 1;
  }
  
  public String toString()
  {
    return GameRequestEntity.c(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((GameRequestEntity)freeze()).writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\games\request\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */