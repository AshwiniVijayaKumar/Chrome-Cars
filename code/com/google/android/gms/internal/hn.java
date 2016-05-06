package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class hn
  implements SafeParcelable
{
  public static final ho CREATOR = new ho();
  final List<ht> LA;
  private final String LB;
  private final boolean LC;
  private final Set<ht> LD;
  final int wj;
  
  hn(int paramInt, List<ht> paramList, String paramString, boolean paramBoolean)
  {
    this.wj = paramInt;
    if (paramList == null) {}
    for (paramList = Collections.emptyList();; paramList = Collections.unmodifiableList(paramList))
    {
      this.LA = paramList;
      paramList = paramString;
      if (paramString == null) {
        paramList = "";
      }
      this.LB = paramList;
      this.LC = paramBoolean;
      if (!this.LA.isEmpty()) {
        break;
      }
      this.LD = Collections.emptySet();
      return;
    }
    this.LD = Collections.unmodifiableSet(new HashSet(this.LA));
  }
  
  public int describeContents()
  {
    ho localho = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof hn)) {
        return false;
      }
      paramObject = (hn)paramObject;
    } while ((this.LD.equals(((hn)paramObject).LD)) && (this.LB == ((hn)paramObject).LB) && (this.LC == ((hn)paramObject).LC));
    return false;
  }
  
  public String gr()
  {
    return this.LB;
  }
  
  public boolean gs()
  {
    return this.LC;
  }
  
  public int hashCode()
  {
    return ep.hashCode(new Object[] { this.LD, this.LB, Boolean.valueOf(this.LC) });
  }
  
  public String toString()
  {
    return ep.e(this).a("types", this.LD).a("textQuery", this.LB).a("isOpenNowRequired", Boolean.valueOf(this.LC)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ho localho = CREATOR;
    ho.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\hn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */