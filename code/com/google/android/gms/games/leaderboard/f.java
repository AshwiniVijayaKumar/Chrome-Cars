package com.google.android.gms.games.leaderboard;

import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;
import com.google.android.gms.internal.gq;
import com.google.android.gms.internal.gu;

public final class f
  implements LeaderboardVariant
{
  private final int Jd;
  private final int Je;
  private final boolean Jf;
  private final long Jg;
  private final String Jh;
  private final long Ji;
  private final String Jj;
  private final String Jk;
  private final long Jl;
  private final String Jm;
  private final String Jn;
  private final String Jo;
  
  public f(LeaderboardVariant paramLeaderboardVariant)
  {
    this.Jd = paramLeaderboardVariant.getTimeSpan();
    this.Je = paramLeaderboardVariant.getCollection();
    this.Jf = paramLeaderboardVariant.hasPlayerInfo();
    this.Jg = paramLeaderboardVariant.getRawPlayerScore();
    this.Jh = paramLeaderboardVariant.getDisplayPlayerScore();
    this.Ji = paramLeaderboardVariant.getPlayerRank();
    this.Jj = paramLeaderboardVariant.getDisplayPlayerRank();
    this.Jk = paramLeaderboardVariant.getPlayerScoreTag();
    this.Jl = paramLeaderboardVariant.getNumScores();
    this.Jm = paramLeaderboardVariant.ga();
    this.Jn = paramLeaderboardVariant.gb();
    this.Jo = paramLeaderboardVariant.gc();
  }
  
  static int a(LeaderboardVariant paramLeaderboardVariant)
  {
    return ep.hashCode(new Object[] { Integer.valueOf(paramLeaderboardVariant.getTimeSpan()), Integer.valueOf(paramLeaderboardVariant.getCollection()), Boolean.valueOf(paramLeaderboardVariant.hasPlayerInfo()), Long.valueOf(paramLeaderboardVariant.getRawPlayerScore()), paramLeaderboardVariant.getDisplayPlayerScore(), Long.valueOf(paramLeaderboardVariant.getPlayerRank()), paramLeaderboardVariant.getDisplayPlayerRank(), Long.valueOf(paramLeaderboardVariant.getNumScores()), paramLeaderboardVariant.ga(), paramLeaderboardVariant.gc(), paramLeaderboardVariant.gb() });
  }
  
  static boolean a(LeaderboardVariant paramLeaderboardVariant, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof LeaderboardVariant)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramLeaderboardVariant == paramObject);
      paramObject = (LeaderboardVariant)paramObject;
      if ((!ep.equal(Integer.valueOf(((LeaderboardVariant)paramObject).getTimeSpan()), Integer.valueOf(paramLeaderboardVariant.getTimeSpan()))) || (!ep.equal(Integer.valueOf(((LeaderboardVariant)paramObject).getCollection()), Integer.valueOf(paramLeaderboardVariant.getCollection()))) || (!ep.equal(Boolean.valueOf(((LeaderboardVariant)paramObject).hasPlayerInfo()), Boolean.valueOf(paramLeaderboardVariant.hasPlayerInfo()))) || (!ep.equal(Long.valueOf(((LeaderboardVariant)paramObject).getRawPlayerScore()), Long.valueOf(paramLeaderboardVariant.getRawPlayerScore()))) || (!ep.equal(((LeaderboardVariant)paramObject).getDisplayPlayerScore(), paramLeaderboardVariant.getDisplayPlayerScore())) || (!ep.equal(Long.valueOf(((LeaderboardVariant)paramObject).getPlayerRank()), Long.valueOf(paramLeaderboardVariant.getPlayerRank()))) || (!ep.equal(((LeaderboardVariant)paramObject).getDisplayPlayerRank(), paramLeaderboardVariant.getDisplayPlayerRank())) || (!ep.equal(Long.valueOf(((LeaderboardVariant)paramObject).getNumScores()), Long.valueOf(paramLeaderboardVariant.getNumScores()))) || (!ep.equal(((LeaderboardVariant)paramObject).ga(), paramLeaderboardVariant.ga())) || (!ep.equal(((LeaderboardVariant)paramObject).gc(), paramLeaderboardVariant.gc()))) {
        break;
      }
      bool1 = bool2;
    } while (ep.equal(((LeaderboardVariant)paramObject).gb(), paramLeaderboardVariant.gb()));
    return false;
  }
  
  static String b(LeaderboardVariant paramLeaderboardVariant)
  {
    ep.a locala = ep.e(paramLeaderboardVariant).a("TimeSpan", gu.aW(paramLeaderboardVariant.getTimeSpan())).a("Collection", gq.aW(paramLeaderboardVariant.getCollection()));
    if (paramLeaderboardVariant.hasPlayerInfo())
    {
      localObject = Long.valueOf(paramLeaderboardVariant.getRawPlayerScore());
      locala = locala.a("RawPlayerScore", localObject);
      if (!paramLeaderboardVariant.hasPlayerInfo()) {
        break label191;
      }
      localObject = paramLeaderboardVariant.getDisplayPlayerScore();
      label76:
      locala = locala.a("DisplayPlayerScore", localObject);
      if (!paramLeaderboardVariant.hasPlayerInfo()) {
        break label197;
      }
      localObject = Long.valueOf(paramLeaderboardVariant.getPlayerRank());
      label103:
      locala = locala.a("PlayerRank", localObject);
      if (!paramLeaderboardVariant.hasPlayerInfo()) {
        break label203;
      }
    }
    label191:
    label197:
    label203:
    for (Object localObject = paramLeaderboardVariant.getDisplayPlayerRank();; localObject = "none")
    {
      return locala.a("DisplayPlayerRank", localObject).a("NumScores", Long.valueOf(paramLeaderboardVariant.getNumScores())).a("TopPageNextToken", paramLeaderboardVariant.ga()).a("WindowPageNextToken", paramLeaderboardVariant.gc()).a("WindowPagePrevToken", paramLeaderboardVariant.gb()).toString();
      localObject = "none";
      break;
      localObject = "none";
      break label76;
      localObject = "none";
      break label103;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public String ga()
  {
    return this.Jm;
  }
  
  public String gb()
  {
    return this.Jn;
  }
  
  public String gc()
  {
    return this.Jo;
  }
  
  public LeaderboardVariant gd()
  {
    return this;
  }
  
  public int getCollection()
  {
    return this.Je;
  }
  
  public String getDisplayPlayerRank()
  {
    return this.Jj;
  }
  
  public String getDisplayPlayerScore()
  {
    return this.Jh;
  }
  
  public long getNumScores()
  {
    return this.Jl;
  }
  
  public long getPlayerRank()
  {
    return this.Ji;
  }
  
  public String getPlayerScoreTag()
  {
    return this.Jk;
  }
  
  public long getRawPlayerScore()
  {
    return this.Jg;
  }
  
  public int getTimeSpan()
  {
    return this.Jd;
  }
  
  public boolean hasPlayerInfo()
  {
    return this.Jf;
  }
  
  public int hashCode()
  {
    return a(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public String toString()
  {
    return b(this);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\games\leaderboard\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */