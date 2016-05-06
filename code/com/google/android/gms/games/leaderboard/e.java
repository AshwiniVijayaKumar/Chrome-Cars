package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.b;
import com.google.android.gms.games.Player;

public final class e
  extends b
  implements LeaderboardScore
{
  private final com.google.android.gms.games.d Jc;
  
  e(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
    this.Jc = new com.google.android.gms.games.d(paramDataHolder, paramInt);
  }
  
  public boolean equals(Object paramObject)
  {
    return d.a(this, paramObject);
  }
  
  public LeaderboardScore fZ()
  {
    return new d(this);
  }
  
  public String getDisplayRank()
  {
    return getString("display_rank");
  }
  
  public void getDisplayRank(CharArrayBuffer paramCharArrayBuffer)
  {
    a("display_rank", paramCharArrayBuffer);
  }
  
  public String getDisplayScore()
  {
    return getString("display_score");
  }
  
  public void getDisplayScore(CharArrayBuffer paramCharArrayBuffer)
  {
    a("display_score", paramCharArrayBuffer);
  }
  
  public long getRank()
  {
    return getLong("rank");
  }
  
  public long getRawScore()
  {
    return getLong("raw_score");
  }
  
  public Player getScoreHolder()
  {
    if (ab("external_player_id")) {
      return null;
    }
    return this.Jc;
  }
  
  public String getScoreHolderDisplayName()
  {
    if (ab("external_player_id")) {
      return getString("default_display_name");
    }
    return this.Jc.getDisplayName();
  }
  
  public void getScoreHolderDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    if (ab("external_player_id"))
    {
      a("default_display_name", paramCharArrayBuffer);
      return;
    }
    this.Jc.getDisplayName(paramCharArrayBuffer);
  }
  
  public Uri getScoreHolderHiResImageUri()
  {
    if (ab("external_player_id")) {
      return null;
    }
    return this.Jc.getHiResImageUri();
  }
  
  public String getScoreHolderHiResImageUrl()
  {
    if (ab("external_player_id")) {
      return null;
    }
    return this.Jc.getHiResImageUrl();
  }
  
  public Uri getScoreHolderIconImageUri()
  {
    if (ab("external_player_id")) {
      return aa("default_display_image_uri");
    }
    return this.Jc.getIconImageUri();
  }
  
  public String getScoreHolderIconImageUrl()
  {
    if (ab("external_player_id")) {
      return getString("default_display_image_url");
    }
    return this.Jc.getIconImageUrl();
  }
  
  public String getScoreTag()
  {
    return getString("score_tag");
  }
  
  public long getTimestampMillis()
  {
    return getLong("achieved_timestamp");
  }
  
  public int hashCode()
  {
    return d.a(this);
  }
  
  public String toString()
  {
    return d.b(this);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\games\leaderboard\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */