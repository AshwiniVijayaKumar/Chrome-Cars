package com.google.android.gms.games.multiplayer;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.b;
import com.google.android.gms.games.Player;

public final class d
  extends b
  implements Participant
{
  private final com.google.android.gms.games.d JE;
  
  public d(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
    this.JE = new com.google.android.gms.games.d(paramDataHolder, paramInt);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return ParticipantEntity.a(this, paramObject);
  }
  
  public Participant freeze()
  {
    return new ParticipantEntity(this);
  }
  
  public String ge()
  {
    return getString("client_address");
  }
  
  public int getCapabilities()
  {
    return getInteger("capabilities");
  }
  
  public String getDisplayName()
  {
    if (ab("external_player_id")) {
      return getString("default_display_name");
    }
    return this.JE.getDisplayName();
  }
  
  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    if (ab("external_player_id"))
    {
      a("default_display_name", paramCharArrayBuffer);
      return;
    }
    this.JE.getDisplayName(paramCharArrayBuffer);
  }
  
  public Uri getHiResImageUri()
  {
    if (ab("external_player_id")) {
      return aa("default_display_hi_res_image_uri");
    }
    return this.JE.getHiResImageUri();
  }
  
  public String getHiResImageUrl()
  {
    if (ab("external_player_id")) {
      return getString("default_display_hi_res_image_url");
    }
    return this.JE.getHiResImageUrl();
  }
  
  public Uri getIconImageUri()
  {
    if (ab("external_player_id")) {
      return aa("default_display_image_uri");
    }
    return this.JE.getIconImageUri();
  }
  
  public String getIconImageUrl()
  {
    if (ab("external_player_id")) {
      return getString("default_display_image_url");
    }
    return this.JE.getIconImageUrl();
  }
  
  public String getParticipantId()
  {
    return getString("external_participant_id");
  }
  
  public Player getPlayer()
  {
    if (ab("external_player_id")) {
      return null;
    }
    return this.JE;
  }
  
  public ParticipantResult getResult()
  {
    if (ab("result_type")) {
      return null;
    }
    int i = getInteger("result_type");
    int j = getInteger("placing");
    return new ParticipantResult(getParticipantId(), i, j);
  }
  
  public int getStatus()
  {
    return getInteger("player_status");
  }
  
  public int hashCode()
  {
    return ParticipantEntity.a(this);
  }
  
  public boolean isConnectedToRoom()
  {
    return getInteger("connected") > 0;
  }
  
  public String toString()
  {
    return ParticipantEntity.b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((ParticipantEntity)freeze()).writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\games\multiplayer\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */