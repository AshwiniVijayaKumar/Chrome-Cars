package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;
import com.google.android.gms.internal.fm;
import java.util.ArrayList;

public final class a
  implements Leaderboard
{
  private final String FE;
  private final Uri FJ;
  private final String FU;
  private final String IJ;
  private final int IK;
  private final ArrayList<f> IL;
  private final Game IM;
  
  public a(Leaderboard paramLeaderboard)
  {
    this.IJ = paramLeaderboard.getLeaderboardId();
    this.FE = paramLeaderboard.getDisplayName();
    this.FJ = paramLeaderboard.getIconImageUri();
    this.FU = paramLeaderboard.getIconImageUrl();
    this.IK = paramLeaderboard.getScoreOrder();
    Object localObject = paramLeaderboard.getGame();
    if (localObject == null) {}
    for (localObject = null;; localObject = new GameEntity((Game)localObject))
    {
      this.IM = ((Game)localObject);
      paramLeaderboard = paramLeaderboard.getVariants();
      int j = paramLeaderboard.size();
      this.IL = new ArrayList(j);
      int i = 0;
      while (i < j)
      {
        this.IL.add((f)((LeaderboardVariant)paramLeaderboard.get(i)).freeze());
        i += 1;
      }
    }
  }
  
  static int a(Leaderboard paramLeaderboard)
  {
    return ep.hashCode(new Object[] { paramLeaderboard.getLeaderboardId(), paramLeaderboard.getDisplayName(), paramLeaderboard.getIconImageUri(), Integer.valueOf(paramLeaderboard.getScoreOrder()), paramLeaderboard.getVariants() });
  }
  
  static boolean a(Leaderboard paramLeaderboard, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof Leaderboard)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramLeaderboard == paramObject);
      paramObject = (Leaderboard)paramObject;
      if ((!ep.equal(((Leaderboard)paramObject).getLeaderboardId(), paramLeaderboard.getLeaderboardId())) || (!ep.equal(((Leaderboard)paramObject).getDisplayName(), paramLeaderboard.getDisplayName())) || (!ep.equal(((Leaderboard)paramObject).getIconImageUri(), paramLeaderboard.getIconImageUri())) || (!ep.equal(Integer.valueOf(((Leaderboard)paramObject).getScoreOrder()), Integer.valueOf(paramLeaderboard.getScoreOrder())))) {
        break;
      }
      bool1 = bool2;
    } while (ep.equal(((Leaderboard)paramObject).getVariants(), paramLeaderboard.getVariants()));
    return false;
  }
  
  static String b(Leaderboard paramLeaderboard)
  {
    return ep.e(paramLeaderboard).a("LeaderboardId", paramLeaderboard.getLeaderboardId()).a("DisplayName", paramLeaderboard.getDisplayName()).a("IconImageUri", paramLeaderboard.getIconImageUri()).a("IconImageUrl", paramLeaderboard.getIconImageUrl()).a("ScoreOrder", Integer.valueOf(paramLeaderboard.getScoreOrder())).a("Variants", paramLeaderboard.getVariants()).toString();
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public Leaderboard fW()
  {
    return this;
  }
  
  public String getDisplayName()
  {
    return this.FE;
  }
  
  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    fm.b(this.FE, paramCharArrayBuffer);
  }
  
  public Game getGame()
  {
    return this.IM;
  }
  
  public Uri getIconImageUri()
  {
    return this.FJ;
  }
  
  public String getIconImageUrl()
  {
    return this.FU;
  }
  
  public String getLeaderboardId()
  {
    return this.IJ;
  }
  
  public int getScoreOrder()
  {
    return this.IK;
  }
  
  public ArrayList<LeaderboardVariant> getVariants()
  {
    return new ArrayList(this.IL);
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


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\games\leaderboard\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */