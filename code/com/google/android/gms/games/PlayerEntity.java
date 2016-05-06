package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ed;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;
import com.google.android.gms.internal.fm;
import com.google.android.gms.internal.fy;

public final class PlayerEntity
  extends fy
  implements Player
{
  public static final Parcelable.Creator<PlayerEntity> CREATOR = new a();
  private final String FE;
  private final Uri FJ;
  private final Uri FK;
  private final String FU;
  private final String FV;
  private final String Gh;
  private final long Gi;
  private final int Gj;
  private final long Gk;
  private final int wj;
  
  PlayerEntity(int paramInt1, String paramString1, String paramString2, Uri paramUri1, Uri paramUri2, long paramLong1, int paramInt2, long paramLong2, String paramString3, String paramString4)
  {
    this.wj = paramInt1;
    this.Gh = paramString1;
    this.FE = paramString2;
    this.FJ = paramUri1;
    this.FU = paramString3;
    this.FK = paramUri2;
    this.FV = paramString4;
    this.Gi = paramLong1;
    this.Gj = paramInt2;
    this.Gk = paramLong2;
  }
  
  public PlayerEntity(Player paramPlayer)
  {
    this.wj = 4;
    this.Gh = paramPlayer.getPlayerId();
    this.FE = paramPlayer.getDisplayName();
    this.FJ = paramPlayer.getIconImageUri();
    this.FU = paramPlayer.getIconImageUrl();
    this.FK = paramPlayer.getHiResImageUri();
    this.FV = paramPlayer.getHiResImageUrl();
    this.Gi = paramPlayer.getRetrievedTimestamp();
    this.Gj = paramPlayer.fl();
    this.Gk = paramPlayer.getLastPlayedWithTimestamp();
    ed.d(this.Gh);
    ed.d(this.FE);
    if (this.Gi > 0L) {}
    for (boolean bool = true;; bool = false)
    {
      ed.v(bool);
      return;
    }
  }
  
  static int a(Player paramPlayer)
  {
    return ep.hashCode(new Object[] { paramPlayer.getPlayerId(), paramPlayer.getDisplayName(), paramPlayer.getIconImageUri(), paramPlayer.getHiResImageUri(), Long.valueOf(paramPlayer.getRetrievedTimestamp()) });
  }
  
  static boolean a(Player paramPlayer, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof Player)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramPlayer == paramObject);
      paramObject = (Player)paramObject;
      if ((!ep.equal(((Player)paramObject).getPlayerId(), paramPlayer.getPlayerId())) || (!ep.equal(((Player)paramObject).getDisplayName(), paramPlayer.getDisplayName())) || (!ep.equal(((Player)paramObject).getIconImageUri(), paramPlayer.getIconImageUri())) || (!ep.equal(((Player)paramObject).getHiResImageUri(), paramPlayer.getHiResImageUri()))) {
        break;
      }
      bool1 = bool2;
    } while (ep.equal(Long.valueOf(((Player)paramObject).getRetrievedTimestamp()), Long.valueOf(paramPlayer.getRetrievedTimestamp())));
    return false;
  }
  
  static String b(Player paramPlayer)
  {
    return ep.e(paramPlayer).a("PlayerId", paramPlayer.getPlayerId()).a("DisplayName", paramPlayer.getDisplayName()).a("IconImageUri", paramPlayer.getIconImageUri()).a("IconImageUrl", paramPlayer.getIconImageUrl()).a("HiResImageUri", paramPlayer.getHiResImageUri()).a("HiResImageUrl", paramPlayer.getHiResImageUrl()).a("RetrievedTimestamp", Long.valueOf(paramPlayer.getRetrievedTimestamp())).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public int fl()
  {
    return this.Gj;
  }
  
  public Player freeze()
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
  
  public long getLastPlayedWithTimestamp()
  {
    return this.Gk;
  }
  
  public String getPlayerId()
  {
    return this.Gh;
  }
  
  public long getRetrievedTimestamp()
  {
    return this.Gi;
  }
  
  public int getVersionCode()
  {
    return this.wj;
  }
  
  public boolean hasHiResImage()
  {
    return getHiResImageUri() != null;
  }
  
  public boolean hasIconImage()
  {
    return getIconImageUri() != null;
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
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    Object localObject2 = null;
    if (!dZ())
    {
      c.a(this, paramParcel, paramInt);
      return;
    }
    paramParcel.writeString(this.Gh);
    paramParcel.writeString(this.FE);
    if (this.FJ == null)
    {
      localObject1 = null;
      paramParcel.writeString((String)localObject1);
      if (this.FK != null) {
        break label82;
      }
    }
    label82:
    for (Object localObject1 = localObject2;; localObject1 = this.FK.toString())
    {
      paramParcel.writeString((String)localObject1);
      paramParcel.writeLong(this.Gi);
      return;
      localObject1 = this.FJ.toString();
      break;
    }
  }
  
  static final class a
    extends c
  {
    public PlayerEntity ak(Parcel paramParcel)
    {
      if ((PlayerEntity.b(PlayerEntity.fk())) || (PlayerEntity.at(PlayerEntity.class.getCanonicalName()))) {
        return super.ak(paramParcel);
      }
      String str1 = paramParcel.readString();
      String str2 = paramParcel.readString();
      Object localObject1 = paramParcel.readString();
      Object localObject2 = paramParcel.readString();
      if (localObject1 == null)
      {
        localObject1 = null;
        if (localObject2 != null) {
          break label93;
        }
      }
      label93:
      for (localObject2 = null;; localObject2 = Uri.parse((String)localObject2))
      {
        return new PlayerEntity(4, str1, str2, (Uri)localObject1, (Uri)localObject2, paramParcel.readLong(), -1, -1L, null, null);
        localObject1 = Uri.parse((String)localObject1);
        break;
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\games\PlayerEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */