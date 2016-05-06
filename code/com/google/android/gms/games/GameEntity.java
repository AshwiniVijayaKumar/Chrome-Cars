package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;
import com.google.android.gms.internal.fm;
import com.google.android.gms.internal.fy;

public final class GameEntity
  extends fy
  implements Game
{
  public static final Parcelable.Creator<GameEntity> CREATOR = new a();
  private final String FE;
  private final String FF;
  private final String FG;
  private final String FH;
  private final String FI;
  private final Uri FJ;
  private final Uri FK;
  private final Uri FL;
  private final boolean FM;
  private final boolean FN;
  private final String FO;
  private final int FP;
  private final int FQ;
  private final int FR;
  private final boolean FS;
  private final boolean FT;
  private final String FU;
  private final String FV;
  private final String FW;
  private final boolean FX;
  private final int wj;
  private final String wk;
  
  GameEntity(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, Uri paramUri1, Uri paramUri2, Uri paramUri3, boolean paramBoolean1, boolean paramBoolean2, String paramString7, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean3, boolean paramBoolean4, String paramString8, String paramString9, String paramString10, boolean paramBoolean5)
  {
    this.wj = paramInt1;
    this.wk = paramString1;
    this.FE = paramString2;
    this.FF = paramString3;
    this.FG = paramString4;
    this.FH = paramString5;
    this.FI = paramString6;
    this.FJ = paramUri1;
    this.FU = paramString8;
    this.FK = paramUri2;
    this.FV = paramString9;
    this.FL = paramUri3;
    this.FW = paramString10;
    this.FM = paramBoolean1;
    this.FN = paramBoolean2;
    this.FO = paramString7;
    this.FP = paramInt2;
    this.FQ = paramInt3;
    this.FR = paramInt4;
    this.FS = paramBoolean3;
    this.FT = paramBoolean4;
    this.FX = paramBoolean5;
  }
  
  public GameEntity(Game paramGame)
  {
    this.wj = 2;
    this.wk = paramGame.getApplicationId();
    this.FF = paramGame.getPrimaryCategory();
    this.FG = paramGame.getSecondaryCategory();
    this.FH = paramGame.getDescription();
    this.FI = paramGame.getDeveloperName();
    this.FE = paramGame.getDisplayName();
    this.FJ = paramGame.getIconImageUri();
    this.FU = paramGame.getIconImageUrl();
    this.FK = paramGame.getHiResImageUri();
    this.FV = paramGame.getHiResImageUrl();
    this.FL = paramGame.getFeaturedImageUri();
    this.FW = paramGame.getFeaturedImageUrl();
    this.FM = paramGame.isPlayEnabledGame();
    this.FN = paramGame.isInstanceInstalled();
    this.FO = paramGame.getInstancePackageName();
    this.FP = paramGame.getGameplayAclStatus();
    this.FQ = paramGame.getAchievementTotalCount();
    this.FR = paramGame.getLeaderboardCount();
    this.FS = paramGame.isRealTimeMultiplayerEnabled();
    this.FT = paramGame.isTurnBasedMultiplayerEnabled();
    this.FX = paramGame.isMuted();
  }
  
  static int a(Game paramGame)
  {
    return ep.hashCode(new Object[] { paramGame.getApplicationId(), paramGame.getDisplayName(), paramGame.getPrimaryCategory(), paramGame.getSecondaryCategory(), paramGame.getDescription(), paramGame.getDeveloperName(), paramGame.getIconImageUri(), paramGame.getHiResImageUri(), paramGame.getFeaturedImageUri(), Boolean.valueOf(paramGame.isPlayEnabledGame()), Boolean.valueOf(paramGame.isInstanceInstalled()), paramGame.getInstancePackageName(), Integer.valueOf(paramGame.getGameplayAclStatus()), Integer.valueOf(paramGame.getAchievementTotalCount()), Integer.valueOf(paramGame.getLeaderboardCount()), Boolean.valueOf(paramGame.isRealTimeMultiplayerEnabled()), Boolean.valueOf(paramGame.isTurnBasedMultiplayerEnabled()), Boolean.valueOf(paramGame.isMuted()) });
  }
  
  static boolean a(Game paramGame, Object paramObject)
  {
    boolean bool3 = true;
    boolean bool1;
    if (!(paramObject instanceof Game)) {
      bool1 = false;
    }
    do
    {
      return bool1;
      bool1 = bool3;
    } while (paramGame == paramObject);
    paramObject = (Game)paramObject;
    boolean bool4;
    if ((ep.equal(((Game)paramObject).getApplicationId(), paramGame.getApplicationId())) && (ep.equal(((Game)paramObject).getDisplayName(), paramGame.getDisplayName())) && (ep.equal(((Game)paramObject).getPrimaryCategory(), paramGame.getPrimaryCategory())) && (ep.equal(((Game)paramObject).getSecondaryCategory(), paramGame.getSecondaryCategory())) && (ep.equal(((Game)paramObject).getDescription(), paramGame.getDescription())) && (ep.equal(((Game)paramObject).getDeveloperName(), paramGame.getDeveloperName())) && (ep.equal(((Game)paramObject).getIconImageUri(), paramGame.getIconImageUri())) && (ep.equal(((Game)paramObject).getHiResImageUri(), paramGame.getHiResImageUri())) && (ep.equal(((Game)paramObject).getFeaturedImageUri(), paramGame.getFeaturedImageUri())) && (ep.equal(Boolean.valueOf(((Game)paramObject).isPlayEnabledGame()), Boolean.valueOf(paramGame.isPlayEnabledGame()))) && (ep.equal(Boolean.valueOf(((Game)paramObject).isInstanceInstalled()), Boolean.valueOf(paramGame.isInstanceInstalled()))) && (ep.equal(((Game)paramObject).getInstancePackageName(), paramGame.getInstancePackageName())) && (ep.equal(Integer.valueOf(((Game)paramObject).getGameplayAclStatus()), Integer.valueOf(paramGame.getGameplayAclStatus()))) && (ep.equal(Integer.valueOf(((Game)paramObject).getAchievementTotalCount()), Integer.valueOf(paramGame.getAchievementTotalCount()))) && (ep.equal(Integer.valueOf(((Game)paramObject).getLeaderboardCount()), Integer.valueOf(paramGame.getLeaderboardCount()))) && (ep.equal(Boolean.valueOf(((Game)paramObject).isRealTimeMultiplayerEnabled()), Boolean.valueOf(paramGame.isRealTimeMultiplayerEnabled()))))
    {
      bool4 = ((Game)paramObject).isTurnBasedMultiplayerEnabled();
      if ((!paramGame.isTurnBasedMultiplayerEnabled()) || (!ep.equal(Boolean.valueOf(((Game)paramObject).isMuted()), Boolean.valueOf(paramGame.isMuted())))) {
        break label414;
      }
    }
    label414:
    for (boolean bool2 = true;; bool2 = false)
    {
      bool1 = bool3;
      if (ep.equal(Boolean.valueOf(bool4), Boolean.valueOf(bool2))) {
        break;
      }
      return false;
    }
  }
  
  static String b(Game paramGame)
  {
    return ep.e(paramGame).a("ApplicationId", paramGame.getApplicationId()).a("DisplayName", paramGame.getDisplayName()).a("PrimaryCategory", paramGame.getPrimaryCategory()).a("SecondaryCategory", paramGame.getSecondaryCategory()).a("Description", paramGame.getDescription()).a("DeveloperName", paramGame.getDeveloperName()).a("IconImageUri", paramGame.getIconImageUri()).a("IconImageUrl", paramGame.getIconImageUrl()).a("HiResImageUri", paramGame.getHiResImageUri()).a("HiResImageUrl", paramGame.getHiResImageUrl()).a("FeaturedImageUri", paramGame.getFeaturedImageUri()).a("FeaturedImageUrl", paramGame.getFeaturedImageUrl()).a("PlayEnabledGame", Boolean.valueOf(paramGame.isPlayEnabledGame())).a("InstanceInstalled", Boolean.valueOf(paramGame.isInstanceInstalled())).a("InstancePackageName", paramGame.getInstancePackageName()).a("AchievementTotalCount", Integer.valueOf(paramGame.getAchievementTotalCount())).a("LeaderboardCount", Integer.valueOf(paramGame.getLeaderboardCount())).a("RealTimeMultiplayerEnabled", Boolean.valueOf(paramGame.isRealTimeMultiplayerEnabled())).a("TurnBasedMultiplayerEnabled", Boolean.valueOf(paramGame.isTurnBasedMultiplayerEnabled())).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public Game freeze()
  {
    return this;
  }
  
  public int getAchievementTotalCount()
  {
    return this.FQ;
  }
  
  public String getApplicationId()
  {
    return this.wk;
  }
  
  public String getDescription()
  {
    return this.FH;
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    fm.b(this.FH, paramCharArrayBuffer);
  }
  
  public String getDeveloperName()
  {
    return this.FI;
  }
  
  public void getDeveloperName(CharArrayBuffer paramCharArrayBuffer)
  {
    fm.b(this.FI, paramCharArrayBuffer);
  }
  
  public String getDisplayName()
  {
    return this.FE;
  }
  
  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    fm.b(this.FE, paramCharArrayBuffer);
  }
  
  public Uri getFeaturedImageUri()
  {
    return this.FL;
  }
  
  public String getFeaturedImageUrl()
  {
    return this.FW;
  }
  
  public int getGameplayAclStatus()
  {
    return this.FP;
  }
  
  public Uri getHiResImageUri()
  {
    return this.FK;
  }
  
  public String getHiResImageUrl()
  {
    return this.FV;
  }
  
  public Uri getIconImageUri()
  {
    return this.FJ;
  }
  
  public String getIconImageUrl()
  {
    return this.FU;
  }
  
  public String getInstancePackageName()
  {
    return this.FO;
  }
  
  public int getLeaderboardCount()
  {
    return this.FR;
  }
  
  public String getPrimaryCategory()
  {
    return this.FF;
  }
  
  public String getSecondaryCategory()
  {
    return this.FG;
  }
  
  public int getVersionCode()
  {
    return this.wj;
  }
  
  public int hashCode()
  {
    return a(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public boolean isInstanceInstalled()
  {
    return this.FN;
  }
  
  public boolean isMuted()
  {
    return this.FX;
  }
  
  public boolean isPlayEnabledGame()
  {
    return this.FM;
  }
  
  public boolean isRealTimeMultiplayerEnabled()
  {
    return this.FS;
  }
  
  public boolean isTurnBasedMultiplayerEnabled()
  {
    return this.FT;
  }
  
  public String toString()
  {
    return b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = 1;
    Object localObject2 = null;
    if (!dZ())
    {
      a.a(this, paramParcel, paramInt);
      return;
    }
    paramParcel.writeString(this.wk);
    paramParcel.writeString(this.FE);
    paramParcel.writeString(this.FF);
    paramParcel.writeString(this.FG);
    paramParcel.writeString(this.FH);
    paramParcel.writeString(this.FI);
    Object localObject1;
    if (this.FJ == null)
    {
      localObject1 = null;
      paramParcel.writeString((String)localObject1);
      if (this.FK != null) {
        break label189;
      }
      localObject1 = null;
      label93:
      paramParcel.writeString((String)localObject1);
      if (this.FL != null) {
        break label201;
      }
      localObject1 = localObject2;
      label110:
      paramParcel.writeString((String)localObject1);
      if (!this.FM) {
        break label213;
      }
      paramInt = 1;
      label125:
      paramParcel.writeInt(paramInt);
      if (!this.FN) {
        break label218;
      }
    }
    label189:
    label201:
    label213:
    label218:
    for (paramInt = i;; paramInt = 0)
    {
      paramParcel.writeInt(paramInt);
      paramParcel.writeString(this.FO);
      paramParcel.writeInt(this.FP);
      paramParcel.writeInt(this.FQ);
      paramParcel.writeInt(this.FR);
      return;
      localObject1 = this.FJ.toString();
      break;
      localObject1 = this.FK.toString();
      break label93;
      localObject1 = this.FL.toString();
      break label110;
      paramInt = 0;
      break label125;
    }
  }
  
  static final class a
    extends a
  {
    public GameEntity aj(Parcel paramParcel)
    {
      if ((GameEntity.b(GameEntity.fk())) || (GameEntity.at(GameEntity.class.getCanonicalName()))) {
        return super.aj(paramParcel);
      }
      String str1 = paramParcel.readString();
      String str2 = paramParcel.readString();
      String str3 = paramParcel.readString();
      String str4 = paramParcel.readString();
      String str5 = paramParcel.readString();
      String str6 = paramParcel.readString();
      Object localObject1 = paramParcel.readString();
      Object localObject2;
      label90:
      Object localObject3;
      label104:
      boolean bool1;
      if (localObject1 == null)
      {
        localObject1 = null;
        localObject2 = paramParcel.readString();
        if (localObject2 != null) {
          break label183;
        }
        localObject2 = null;
        localObject3 = paramParcel.readString();
        if (localObject3 != null) {
          break label193;
        }
        localObject3 = null;
        if (paramParcel.readInt() <= 0) {
          break label203;
        }
        bool1 = true;
        label113:
        if (paramParcel.readInt() <= 0) {
          break label208;
        }
      }
      label183:
      label193:
      label203:
      label208:
      for (boolean bool2 = true;; bool2 = false)
      {
        return new GameEntity(2, str1, str2, str3, str4, str5, str6, (Uri)localObject1, (Uri)localObject2, (Uri)localObject3, bool1, bool2, paramParcel.readString(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), false, false, null, null, null, false);
        localObject1 = Uri.parse((String)localObject1);
        break;
        localObject2 = Uri.parse((String)localObject2);
        break label90;
        localObject3 = Uri.parse((String)localObject3);
        break label104;
        bool1 = false;
        break label113;
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\games\GameEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */