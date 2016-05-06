package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.ConflictEvent;

public class OnEventResponse
  implements SafeParcelable
{
  public static final Parcelable.Creator<OnEventResponse> CREATOR = new ac();
  final int Dm;
  final ChangeEvent Eb;
  final ConflictEvent Ec;
  final int wj;
  
  OnEventResponse(int paramInt1, int paramInt2, ChangeEvent paramChangeEvent, ConflictEvent paramConflictEvent)
  {
    this.wj = paramInt1;
    this.Dm = paramInt2;
    this.Eb = paramChangeEvent;
    this.Ec = paramConflictEvent;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public ChangeEvent fa()
  {
    return this.Eb;
  }
  
  public ConflictEvent fb()
  {
    return this.Ec;
  }
  
  public int getEventType()
  {
    return this.Dm;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ac.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\internal\OnEventResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */