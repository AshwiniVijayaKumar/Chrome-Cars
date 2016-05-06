package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.gms.drive.query.internal.LogicalFilter;

public class a
  implements Parcelable.Creator<Query>
{
  static void a(Query paramQuery, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1000, paramQuery.wj);
    b.a(paramParcel, 1, paramQuery.EL, paramInt, false);
    b.a(paramParcel, 3, paramQuery.EM, false);
    b.D(paramParcel, i);
  }
  
  public Query[] aG(int paramInt)
  {
    return new Query[paramInt];
  }
  
  public Query ab(Parcel paramParcel)
  {
    String str = null;
    int j = com.google.android.gms.common.internal.safeparcel.a.o(paramParcel);
    int i = 0;
    LogicalFilter localLogicalFilter = null;
    if (paramParcel.dataPosition() < j)
    {
      int k = com.google.android.gms.common.internal.safeparcel.a.n(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.a.S(k))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.a.b(paramParcel, k);
      }
      for (;;)
      {
        break;
        i = com.google.android.gms.common.internal.safeparcel.a.g(paramParcel, k);
        continue;
        localLogicalFilter = (LogicalFilter)com.google.android.gms.common.internal.safeparcel.a.a(paramParcel, k, LogicalFilter.CREATOR);
        continue;
        str = com.google.android.gms.common.internal.safeparcel.a.m(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new Query(i, localLogicalFilter, str);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\query\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */