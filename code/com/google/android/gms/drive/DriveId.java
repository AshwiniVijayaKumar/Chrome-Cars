package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.internal.y;
import com.google.android.gms.internal.er;
import com.google.android.gms.internal.kd;
import com.google.android.gms.internal.ke;

public class DriveId
  implements SafeParcelable
{
  public static final Parcelable.Creator<DriveId> CREATOR = new d();
  final String Dc;
  final long Dd;
  final long De;
  private volatile String Df = null;
  final int wj;
  
  DriveId(int paramInt, String paramString, long paramLong1, long paramLong2)
  {
    this.wj = paramInt;
    this.Dc = paramString;
    if (!"".equals(paramString)) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      er.x(bool1);
      if (paramString == null)
      {
        bool1 = bool2;
        if (paramLong1 == -1L) {}
      }
      else
      {
        bool1 = true;
      }
      er.x(bool1);
      this.Dd = paramLong1;
      this.De = paramLong2;
      return;
    }
  }
  
  public DriveId(String paramString, long paramLong1, long paramLong2)
  {
    this(1, paramString, paramLong1, paramLong2);
  }
  
  public static DriveId aq(String paramString)
  {
    er.f(paramString);
    return new DriveId(paramString, -1L, -1L);
  }
  
  public static DriveId decodeFromString(String paramString)
  {
    er.b(paramString.startsWith("DriveId:"), "Invalid DriveId: " + paramString);
    return f(Base64.decode(paramString.substring("DriveId:".length()), 10));
  }
  
  static DriveId f(byte[] paramArrayOfByte)
  {
    for (;;)
    {
      y localy;
      try
      {
        localy = y.g(paramArrayOfByte);
        if ("".equals(localy.DV))
        {
          paramArrayOfByte = null;
          return new DriveId(localy.versionCode, paramArrayOfByte, localy.DW, localy.DX);
        }
      }
      catch (kd paramArrayOfByte)
      {
        throw new IllegalArgumentException();
      }
      paramArrayOfByte = localy.DV;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  final byte[] eR()
  {
    y localy = new y();
    localy.versionCode = this.wj;
    if (this.Dc == null) {}
    for (String str = "";; str = this.Dc)
    {
      localy.DV = str;
      localy.DW = this.Dd;
      localy.DX = this.De;
      return ke.d(localy);
    }
  }
  
  public final String encodeToString()
  {
    if (this.Df == null)
    {
      String str = Base64.encodeToString(eR(), 10);
      this.Df = ("DriveId:" + str);
    }
    return this.Df;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof DriveId)) {}
    do
    {
      return false;
      paramObject = (DriveId)paramObject;
      if (((DriveId)paramObject).De != this.De)
      {
        Log.w("DriveId", "Attempt to compare invalid DriveId detected. Has local storage been cleared?");
        return false;
      }
      if ((((DriveId)paramObject).Dd == -1L) && (this.Dd == -1L)) {
        return ((DriveId)paramObject).Dc.equals(this.Dc);
      }
    } while (((DriveId)paramObject).Dd != this.Dd);
    return true;
  }
  
  public String getResourceId()
  {
    return this.Dc;
  }
  
  public int hashCode()
  {
    if (this.Dd == -1L) {
      return this.Dc.hashCode();
    }
    return (String.valueOf(this.De) + String.valueOf(this.Dd)).hashCode();
  }
  
  public String toString()
  {
    return encodeToString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    d.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\DriveId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */