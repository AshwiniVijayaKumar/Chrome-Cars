package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.ep.a;
import com.google.android.gms.internal.er;
import com.google.android.gms.internal.fy;
import java.util.ArrayList;

public final class InvitationEntity
  extends fy
  implements Invitation
{
  public static final Parcelable.Creator<InvitationEntity> CREATOR = new a();
  private final String GP;
  private final GameEntity Jq;
  private final long Jr;
  private final int Js;
  private final ParticipantEntity Jt;
  private final ArrayList<ParticipantEntity> Ju;
  private final int Jv;
  private final int Jw;
  private final int wj;
  
  InvitationEntity(int paramInt1, GameEntity paramGameEntity, String paramString, long paramLong, int paramInt2, ParticipantEntity paramParticipantEntity, ArrayList<ParticipantEntity> paramArrayList, int paramInt3, int paramInt4)
  {
    this.wj = paramInt1;
    this.Jq = paramGameEntity;
    this.GP = paramString;
    this.Jr = paramLong;
    this.Js = paramInt2;
    this.Jt = paramParticipantEntity;
    this.Ju = paramArrayList;
    this.Jv = paramInt3;
    this.Jw = paramInt4;
  }
  
  InvitationEntity(Invitation paramInvitation)
  {
    this.wj = 2;
    this.Jq = new GameEntity(paramInvitation.getGame());
    this.GP = paramInvitation.getInvitationId();
    this.Jr = paramInvitation.getCreationTimestamp();
    this.Js = paramInvitation.getInvitationType();
    this.Jv = paramInvitation.getVariant();
    this.Jw = paramInvitation.getAvailableAutoMatchSlots();
    String str = paramInvitation.getInviter().getParticipantId();
    Participant localParticipant = null;
    ArrayList localArrayList = paramInvitation.getParticipants();
    int j = localArrayList.size();
    this.Ju = new ArrayList(j);
    int i = 0;
    paramInvitation = localParticipant;
    while (i < j)
    {
      localParticipant = (Participant)localArrayList.get(i);
      if (localParticipant.getParticipantId().equals(str)) {
        paramInvitation = localParticipant;
      }
      this.Ju.add((ParticipantEntity)localParticipant.freeze());
      i += 1;
    }
    er.b(paramInvitation, "Must have a valid inviter!");
    this.Jt = ((ParticipantEntity)paramInvitation.freeze());
  }
  
  static int a(Invitation paramInvitation)
  {
    return ep.hashCode(new Object[] { paramInvitation.getGame(), paramInvitation.getInvitationId(), Long.valueOf(paramInvitation.getCreationTimestamp()), Integer.valueOf(paramInvitation.getInvitationType()), paramInvitation.getInviter(), paramInvitation.getParticipants(), Integer.valueOf(paramInvitation.getVariant()), Integer.valueOf(paramInvitation.getAvailableAutoMatchSlots()) });
  }
  
  static boolean a(Invitation paramInvitation, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof Invitation)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramInvitation == paramObject);
      paramObject = (Invitation)paramObject;
      if ((!ep.equal(((Invitation)paramObject).getGame(), paramInvitation.getGame())) || (!ep.equal(((Invitation)paramObject).getInvitationId(), paramInvitation.getInvitationId())) || (!ep.equal(Long.valueOf(((Invitation)paramObject).getCreationTimestamp()), Long.valueOf(paramInvitation.getCreationTimestamp()))) || (!ep.equal(Integer.valueOf(((Invitation)paramObject).getInvitationType()), Integer.valueOf(paramInvitation.getInvitationType()))) || (!ep.equal(((Invitation)paramObject).getInviter(), paramInvitation.getInviter())) || (!ep.equal(((Invitation)paramObject).getParticipants(), paramInvitation.getParticipants())) || (!ep.equal(Integer.valueOf(((Invitation)paramObject).getVariant()), Integer.valueOf(paramInvitation.getVariant())))) {
        break;
      }
      bool1 = bool2;
    } while (ep.equal(Integer.valueOf(((Invitation)paramObject).getAvailableAutoMatchSlots()), Integer.valueOf(paramInvitation.getAvailableAutoMatchSlots())));
    return false;
  }
  
  static String b(Invitation paramInvitation)
  {
    return ep.e(paramInvitation).a("Game", paramInvitation.getGame()).a("InvitationId", paramInvitation.getInvitationId()).a("CreationTimestamp", Long.valueOf(paramInvitation.getCreationTimestamp())).a("InvitationType", Integer.valueOf(paramInvitation.getInvitationType())).a("Inviter", paramInvitation.getInviter()).a("Participants", paramInvitation.getParticipants()).a("Variant", Integer.valueOf(paramInvitation.getVariant())).a("AvailableAutoMatchSlots", Integer.valueOf(paramInvitation.getAvailableAutoMatchSlots())).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return a(this, paramObject);
  }
  
  public Invitation freeze()
  {
    return this;
  }
  
  public int getAvailableAutoMatchSlots()
  {
    return this.Jw;
  }
  
  public long getCreationTimestamp()
  {
    return this.Jr;
  }
  
  public Game getGame()
  {
    return this.Jq;
  }
  
  public String getInvitationId()
  {
    return this.GP;
  }
  
  public int getInvitationType()
  {
    return this.Js;
  }
  
  public Participant getInviter()
  {
    return this.Jt;
  }
  
  public ArrayList<Participant> getParticipants()
  {
    return new ArrayList(this.Ju);
  }
  
  public int getVariant()
  {
    return this.Jv;
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
  
  public String toString()
  {
    return b(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (!dZ()) {
      a.a(this, paramParcel, paramInt);
    }
    for (;;)
    {
      return;
      this.Jq.writeToParcel(paramParcel, paramInt);
      paramParcel.writeString(this.GP);
      paramParcel.writeLong(this.Jr);
      paramParcel.writeInt(this.Js);
      this.Jt.writeToParcel(paramParcel, paramInt);
      int j = this.Ju.size();
      paramParcel.writeInt(j);
      int i = 0;
      while (i < j)
      {
        ((ParticipantEntity)this.Ju.get(i)).writeToParcel(paramParcel, paramInt);
        i += 1;
      }
    }
  }
  
  static final class a
    extends a
  {
    public InvitationEntity an(Parcel paramParcel)
    {
      if ((InvitationEntity.b(InvitationEntity.fk())) || (InvitationEntity.at(InvitationEntity.class.getCanonicalName()))) {
        return super.an(paramParcel);
      }
      GameEntity localGameEntity = (GameEntity)GameEntity.CREATOR.createFromParcel(paramParcel);
      String str = paramParcel.readString();
      long l = paramParcel.readLong();
      int j = paramParcel.readInt();
      ParticipantEntity localParticipantEntity = (ParticipantEntity)ParticipantEntity.CREATOR.createFromParcel(paramParcel);
      int k = paramParcel.readInt();
      ArrayList localArrayList = new ArrayList(k);
      int i = 0;
      while (i < k)
      {
        localArrayList.add(ParticipantEntity.CREATOR.createFromParcel(paramParcel));
        i += 1;
      }
      return new InvitationEntity(2, localGameEntity, str, l, j, localParticipantEntity, localArrayList, -1, 0);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\games\multiplayer\InvitationEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */