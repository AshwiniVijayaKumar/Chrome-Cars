package com.google.android.gms.maps;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.b;

public class a
{
  static void a(GoogleMapOptions paramGoogleMapOptions, Parcel paramParcel, int paramInt)
  {
    int i = b.p(paramParcel);
    b.c(paramParcel, 1, paramGoogleMapOptions.getVersionCode());
    b.a(paramParcel, 2, paramGoogleMapOptions.gN());
    b.a(paramParcel, 3, paramGoogleMapOptions.gO());
    b.c(paramParcel, 4, paramGoogleMapOptions.getMapType());
    b.a(paramParcel, 5, paramGoogleMapOptions.getCamera(), paramInt, false);
    b.a(paramParcel, 6, paramGoogleMapOptions.gP());
    b.a(paramParcel, 7, paramGoogleMapOptions.gQ());
    b.a(paramParcel, 8, paramGoogleMapOptions.gR());
    b.a(paramParcel, 9, paramGoogleMapOptions.gS());
    b.a(paramParcel, 10, paramGoogleMapOptions.gT());
    b.a(paramParcel, 11, paramGoogleMapOptions.gU());
    b.D(paramParcel, i);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\maps\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */