package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ew
  implements SafeParcelable
{
  public static final ex CREATOR = new ex();
  private final ey Co;
  private final int wj;
  
  ew(int paramInt, ey paramey)
  {
    this.wj = paramInt;
    this.Co = paramey;
  }
  
  private ew(ey paramey)
  {
    this.wj = 1;
    this.Co = paramey;
  }
  
  public static ew a(fb.b<?, ?> paramb)
  {
    if ((paramb instanceof ey)) {
      return new ew((ey)paramb);
    }
    throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
  }
  
  public int describeContents()
  {
    ex localex = CREATOR;
    return 0;
  }
  
  ey ei()
  {
    return this.Co;
  }
  
  public fb.b<?, ?> ej()
  {
    if (this.Co != null) {
      return this.Co;
    }
    throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
  }
  
  int getVersionCode()
  {
    return this.wj;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ex localex = CREATOR;
    ex.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ew.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */