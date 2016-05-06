package com.google.android.gms.games.multiplayer;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;
import com.google.android.gms.internal.fm;
import com.google.android.gms.internal.fy;

public final class ParticipantEntity
  extends fy
  implements Participant
{
  public static final Parcelable.Creator<ParticipantEntity> CREATOR = new a();
  private final String FE;
  private final Uri FJ;
  private final Uri FK;
  private final String FU;
  private final String FV;
  private final String GZ;
  private final boolean JA;
  private final PlayerEntity JB;
  private final int JC;
  private final ParticipantResult JD;
  private final int Jy;
  private final String Jz;
  private final int wj;
  
  ParticipantEntity(int paramInt1, String paramString1, String paramString2, Uri paramUri1, Uri paramUri2, int paramInt2, String paramString3, boolean paramBoolean, PlayerEntity paramPlayerEntity, int paramInt3, ParticipantResult paramParticipantResult, String paramString4, String paramString5)
  {
    this.wj = paramInt1;
    this.GZ = paramString1;
    this.FE = paramString2;
    this.FJ = paramUri1;
    this.FK = paramUri2;
    this.Jy = paramInt2;
    this.Jz = paramString3;
    this.JA = paramBoolean;
    this.JB = paramPlayerEntity;
    this.JC = paramInt3;
    this.JD = paramParticipantResult;
    this.FU = paramString4;
    this.FV = paramString5;
  }
  
  public ParticipantEntity(Participant paramParticipant)
  {
    this.wj = 3;
    this.GZ = paramParticipant.getParticipantId();
    this.FE = paramParticipant.getDisplayName();
    this.FJ = paramParticipant.getIconImageUri();
    this.FK = paramParticipant.getHiResImageUri();
    this.Jy = paramParticipant.getStatus();
    this.Jz = paramParticipant.ge();
    this.JA = paramParticipant.isConnectedToRoom();
    Object localObject = paramParticipant.getPlayer();
    if (localObject == null) {}
    for (localObject = null;; localObject = new PlayerEntity((Player)localObject))
    {
      this.JB = ((PlayerEntity)localObject);
      this.JC = paramParticipant.getCapabilities();
      this.JD = paramParticipant.getResult();
      this.FU = paramParticipant.getIconImageUrl();
      this.FV = paramParticipant.getHiResImageUrl();
      return;
    }
  }
  
  static int a(Participant paramParticipant)
  {
    return ep.hashCode(new Object[] { paramParticipant.getPlayer(), Integer.valueOf(paramParticipant.getStatus()), paramParticipant.ge(), Boolean.valueOf(paramParticipant.isConnectedToRoom()), paramParticipant.getDisplayName(), paramParticipant.getIconImageUri(), paramParticipant.getHiResImageUri(), Integer.valueOf(paramParticipant.getCapabilities()), paramParticipant.getResult() });
  }
  
  static boolean a(Participant paramParticipant, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof Participant)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramParticipant == paramObject);
      paramObject = (Participant)paramObject;
      if ((!ep.equal(((Participant)paramObject).getPlayer(), paramParticipant.getPlayer())) || (!ep.equal(Integer.valueOf(((Participant)paramObject).getStatus()), Integer.valueOf(paramParticipant.getStatus()))) || (!ep.equal(((Participant)paramObject).ge(), paramParticipant.ge())) || (!ep.equal(Boolean.valueOf(((Participant)paramObject).isConnectedToRoom()), Boolean.valueOf(paramParticipant.isConnectedToRoom()))) || (!ep.equal(((Participant)paramObject).getDisplayName(), paramParticipant.getDisplayName())) || (!ep.equal(((Participant)paramObject).getIconImageUri(), paramParticipant.getIconImageUri())) || (!ep.equal(((Participant)paramObject).getHiResImageUri(), paramParticipant.getHiResImageUri())) || (!ep.equal(Integer.valueOf(((Participant)paramObject).getCapabilities()), Integer.valueOf(paramParticipant.getCapabilities())))) {
        break;
      }
      bool1 = bool2;
    } while (ep.equal(((Participant)paramObject).getResult(), paramParticipant.getResult()));
    return false;
  }
  
  static String b(Participant paramParticipant)
  {
    return ep.e(paramParticipant).a("Player", paramParticipant.getPlayer()).a("Status", Integer.valueOf(paramParticipant.getStatus())).a("ClientAddress", paramParticipant.ge()).a("ConnectedToRoom", Boolean.valueOf(paramParticipant.isConnectedToRoom())).a("DisplayName", paramParticipant.getDisplayName()).a("IconImage", paramParticipant.getIconImageUri()).a("IconImageUrl", paramParticipant.getIconImageUrl()).a("HiResImage", paramParticipant.getHiResImageUri()).a("HiResImageUrl", paramParticipant.getHiResImageUrl()).a("Capabilities", Integer.valueOf(paramParticipant.getCapabilities())).a("Result", paramParticipant.getResult()).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public Participant freeze()
  {
    return this;
  }
  
  public String ge()
  {
    return this.Jz;
  }
  
  public int getCapabilities()
  {
    return this.JC;
  }
  
  public String getDisplayName()
  {
    if (this.JB == null) {
      return this.FE;
    }
    return this.JB.getDisplayName();
  }
  
  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    if (this.JB == null)
    {
      fm.b(this.FE, paramCharArrayBuffer);
      return;
    }
    this.JB.getDisplayName(paramCharArrayBuffer);
  }
  
  public Uri getHiResImageUri()
  {
    if (this.JB == null) {
      return this.FK;
    }
    return this.JB.getHiResImageUri();
  }
  
  public String getHiResImageUrl()
  {
    if (this.JB == null) {
      return this.FV;
    }
    return this.JB.getHiResImageUrl();
  }
  
  public Uri getIconImageUri()
  {
    if (this.JB == null) {
      return this.FJ;
    }
    return this.JB.getIconImageUri();
  }
  
  public String getIconImageUrl()
  {
    if (this.JB == null) {
      return this.FU;
    }
    return this.JB.getIconImageUrl();
  }
  
  public String getParticipantId()
  {
    return this.GZ;
  }
  
  public Player getPlayer()
  {
    return this.JB;
  }
  
  public ParticipantResult getResult()
  {
    return this.JD;
  }
  
  public int getStatus()
  {
    return this.Jy;
  }
  
  public int getVersionCode()
  {
    return this.wj;
  }
  
  public int hashCode()
  {
    return a(this);
  }
  
  public boolean isConnectedToRoom()
  {
    return this.JA;
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
    int j = 0;
    if (!dZ())
    {
      c.a(this, paramParcel, paramInt);
      return;
    }
    paramParcel.writeString(this.GZ);
    paramParcel.writeString(this.FE);
    Object localObject1;
    if (this.FJ == null)
    {
      localObject1 = null;
      label46:
      paramParcel.writeString((String)localObject1);
      if (this.FK != null) {
        break label143;
      }
      localObject1 = localObject2;
      label63:
      paramParcel.writeString((String)localObject1);
      paramParcel.writeInt(this.Jy);
      paramParcel.writeString(this.Jz);
      if (!this.JA) {
        break label155;
      }
      i = 1;
      label94:
      paramParcel.writeInt(i);
      if (this.JB != null) {
        break label160;
      }
    }
    label143:
    label155:
    label160:
    for (int i = j;; i = 1)
    {
      paramParcel.writeInt(i);
      if (this.JB == null) {
        break;
      }
      this.JB.writeToParcel(paramParcel, paramInt);
      return;
      localObject1 = this.FJ.toString();
      break label46;
      localObject1 = this.FK.toString();
      break label63;
      i = 0;
      break label94;
    }
  }
  
  static final class a
    extends c
  {
    public ParticipantEntity ao(Parcel paramParcel)
    {
      int i = 1;
      if ((ParticipantEntity.b(ParticipantEntity.fk())) || (ParticipantEntity.at(ParticipantEntity.class.getCanonicalName()))) {
        return super.ao(paramParcel);
      }
      String str1 = paramParcel.readString();
      String str2 = paramParcel.readString();
      Object localObject1 = paramParcel.readString();
      Object localObject2;
      label68:
      int j;
      String str3;
      boolean bool;
      if (localObject1 == null)
      {
        localObject1 = null;
        localObject2 = paramParcel.readString();
        if (localObject2 != null) {
          break label151;
        }
        localObject2 = null;
        j = paramParcel.readInt();
        str3 = paramParcel.readString();
        if (paramParcel.readInt() <= 0) {
          break label161;
        }
        bool = true;
        label89:
        if (paramParcel.readInt() <= 0) {
          break label167;
        }
        label96:
        if (i == 0) {
          break label172;
        }
      }
      label151:
      label161:
      label167:
      label172:
      for (paramParcel = (PlayerEntity)PlayerEntity.CREATOR.createFromParcel(paramParcel);; paramParcel = null)
      {
        return new ParticipantEntity(3, str1, str2, (Uri)localObject1, (Uri)localObject2, j, str3, bool, paramParcel, 7, null, null, null);
        localObject1 = Uri.parse((String)localObject1);
        break;
        localObject2 = Uri.parse((String)localObject2);
        break label68;
        bool = false;
        break label89;
        i = 0;
        break label96;
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\games\multiplayer\ParticipantEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */