package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;
import com.google.android.gms.internal.er;
import com.google.android.gms.internal.gu;
import java.util.HashMap;

public final class ScoreSubmissionData
{
  private static final String[] IH = { "leaderboardId", "playerId", "timeSpan", "hasResult", "rawScore", "formattedScore", "newBest", "scoreTag" };
  private String Gh;
  private String IJ;
  private HashMap<Integer, Result> Jp;
  private int yJ;
  
  public ScoreSubmissionData(DataHolder paramDataHolder)
  {
    this.yJ = paramDataHolder.getStatusCode();
    this.Jp = new HashMap();
    int j = paramDataHolder.getCount();
    if (j == 3) {}
    for (boolean bool = true;; bool = false)
    {
      er.x(bool);
      int i = 0;
      while (i < j)
      {
        int k = paramDataHolder.I(i);
        if (i == 0)
        {
          this.IJ = paramDataHolder.getString("leaderboardId", i, k);
          this.Gh = paramDataHolder.getString("playerId", i, k);
        }
        if (paramDataHolder.getBoolean("hasResult", i, k)) {
          a(new Result(paramDataHolder.getLong("rawScore", i, k), paramDataHolder.getString("formattedScore", i, k), paramDataHolder.getString("scoreTag", i, k), paramDataHolder.getBoolean("newBest", i, k)), paramDataHolder.getInteger("timeSpan", i, k));
        }
        i += 1;
      }
    }
  }
  
  private void a(Result paramResult, int paramInt)
  {
    this.Jp.put(Integer.valueOf(paramInt), paramResult);
  }
  
  public String getLeaderboardId()
  {
    return this.IJ;
  }
  
  public String getPlayerId()
  {
    return this.Gh;
  }
  
  public Result getScoreResult(int paramInt)
  {
    return (Result)this.Jp.get(Integer.valueOf(paramInt));
  }
  
  public String toString()
  {
    ep.a locala = ep.e(this).a("PlayerId", this.Gh).a("StatusCode", Integer.valueOf(this.yJ));
    int i = 0;
    if (i < 3)
    {
      Object localObject = (Result)this.Jp.get(Integer.valueOf(i));
      locala.a("TimesSpan", gu.aW(i));
      if (localObject == null) {}
      for (localObject = "null";; localObject = ((Result)localObject).toString())
      {
        locala.a("Result", localObject);
        i += 1;
        break;
      }
    }
    return locala.toString();
  }
  
  public static final class Result
  {
    public final String formattedScore;
    public final boolean newBest;
    public final long rawScore;
    public final String scoreTag;
    
    public Result(long paramLong, String paramString1, String paramString2, boolean paramBoolean)
    {
      this.rawScore = paramLong;
      this.formattedScore = paramString1;
      this.scoreTag = paramString2;
      this.newBest = paramBoolean;
    }
    
    public String toString()
    {
      return ep.e(this).a("RawScore", Long.valueOf(this.rawScore)).a("FormattedScore", this.formattedScore).a("ScoreTag", this.scoreTag).a("NewBest", Boolean.valueOf(this.newBest)).toString();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\games\leaderboard\ScoreSubmissionData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */