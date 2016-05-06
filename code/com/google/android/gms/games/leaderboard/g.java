package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.b;

public final class g
  extends b
  implements LeaderboardVariant
{
  g(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  public boolean equals(Object paramObject)
  {
    return f.a(this, paramObject);
  }
  
  public String ga()
  {
    return getString("top_page_token_next");
  }
  
  public String gb()
  {
    return getString("window_page_token_prev");
  }
  
  public String gc()
  {
    return getString("window_page_token_next");
  }
  
  public LeaderboardVariant gd()
  {
    return new f(this);
  }
  
  public int getCollection()
  {
    return getInteger("collection");
  }
  
  public String getDisplayPlayerRank()
  {
    return getString("player_display_rank");
  }
  
  public String getDisplayPlayerScore()
  {
    return getString("player_display_score");
  }
  
  public long getNumScores()
  {
    if (ab("total_scores")) {
      return -1L;
    }
    return getLong("total_scores");
  }
  
  public long getPlayerRank()
  {
    if (ab("player_rank")) {
      return -1L;
    }
    return getLong("player_rank");
  }
  
  public String getPlayerScoreTag()
  {
    return getString("player_score_tag");
  }
  
  public long getRawPlayerScore()
  {
    if (ab("player_raw_score")) {
      return -1L;
    }
    return getLong("player_raw_score");
  }
  
  public int getTimeSpan()
  {
    return getInteger("timespan");
  }
  
  public boolean hasPlayerInfo()
  {
    return !ab("player_raw_score");
  }
  
  public int hashCode()
  {
    return f.a(this);
  }
  
  public String toString()
  {
    return f.b(this);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\games\leaderboard\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */