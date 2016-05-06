package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.drive.DriveId;

public class b
  implements Parcelable.Creator<ConflictEvent>
{
  static void a(ConflictEvent paramConflictEvent, Parcel paramParcel, int paramInt)
  {
    int i = com.google.android.gms.common.internal.safeparcel.b.p(paramParcel);
    com.google.android.gms.common.internal.safeparcel.b.c(paramParcel, 1, paramConflictEvent.wj);
    com.google.android.gms.common.internal.safeparcel.b.a(paramParcel, 2, paramConflictEvent.CS, paramInt, false);
    com.google.android.gms.common.internal.safeparcel.b.D(paramParcel, i);
  }
  
  public ConflictEvent B(Parcel paramParcel)
  {
    int j = a.o(paramParcel);
    int i = 0;
    DriveId localDriveId = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.n(paramParcel);
      switch (a.S(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        localDriveId = (DriveId)a.a(paramParcel, k, DriveId.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new ConflictEvent(i, localDriveId);
  }
  
  public ConflictEvent[] ag(int paramInt)
  {
    return new ConflictEvent[paramInt];
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\events\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */