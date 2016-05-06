package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;

public final class ConflictEvent
  implements SafeParcelable, DriveEvent
{
  public static final Parcelable.Creator<ConflictEvent> CREATOR = new b();
  final DriveId CS;
  final int wj;
  
  ConflictEvent(int paramInt, DriveId paramDriveId)
  {
    this.wj = paramInt;
    this.CS = paramDriveId;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int getType()
  {
    return 1;
  }
  
  public String toString()
  {
    return String.format("ConflictEvent [id=%s]", new Object[] { this.CS });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    b.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\events\ConflictEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */