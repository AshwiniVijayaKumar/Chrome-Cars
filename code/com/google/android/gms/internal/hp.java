package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class hp
  implements SafeParcelable
{
  public static final hq CREATOR = new hq();
  private final String LE;
  private final String mTag;
  final int wj;
  
  hp(int paramInt, String paramString1, String paramString2)
  {
    this.wj = paramInt;
    this.LE = paramString1;
    this.mTag = paramString2;
  }
  
  public int describeContents()
  {
    hq localhq = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof hp)) {}
    do
    {
      return false;
      paramObject = (hp)paramObject;
    } while ((!ep.equal(this.LE, ((hp)paramObject).LE)) || (!ep.equal(this.mTag, ((hp)paramObject).mTag)));
    return true;
  }
  
  public String getTag()
  {
    return this.mTag;
  }
  
  public String gt()
  {
    return this.LE;
  }
  
  public int hashCode()
  {
    return ep.hashCode(new Object[] { this.LE, this.mTag });
  }
  
  public String toString()
  {
    return ep.e(this).a("mPlaceId", this.LE).a("mTag", this.mTag).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    hq localhq = CREATOR;
    hq.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\hp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */