package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class db
  implements SafeParcelable
{
  public static final dc CREATOR = new dc();
  public String pU;
  public int pV;
  public int pW;
  public boolean pX;
  public final int versionCode;
  
  public db(int paramInt1, int paramInt2, boolean paramBoolean) {}
  
  db(int paramInt1, String paramString, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    this.versionCode = paramInt1;
    this.pU = paramString;
    this.pV = paramInt2;
    this.pW = paramInt3;
    this.pX = paramBoolean;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    dc.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\db.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */