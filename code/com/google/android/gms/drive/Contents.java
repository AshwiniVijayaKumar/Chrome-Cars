package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Contents
  implements SafeParcelable
{
  public static final Parcelable.Creator<Contents> CREATOR = new a();
  final ParcelFileDescriptor AC;
  final int CQ;
  final int CR;
  final DriveId CS;
  private boolean CT = false;
  private boolean CU = false;
  private boolean mClosed = false;
  final int wj;
  
  Contents(int paramInt1, ParcelFileDescriptor paramParcelFileDescriptor, int paramInt2, int paramInt3, DriveId paramDriveId)
  {
    this.wj = paramInt1;
    this.AC = paramParcelFileDescriptor;
    this.CQ = paramInt2;
    this.CR = paramInt3;
    this.CS = paramDriveId;
  }
  
  public void close()
  {
    this.mClosed = true;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int eP()
  {
    return this.CQ;
  }
  
  public DriveId getDriveId()
  {
    return this.CS;
  }
  
  public InputStream getInputStream()
  {
    if (this.mClosed) {
      throw new IllegalStateException("Contents have been closed, cannot access the input stream.");
    }
    if (this.CR != 268435456) {
      throw new IllegalStateException("getInputStream() can only be used with contents opened with MODE_READ_ONLY.");
    }
    if (this.CT) {
      throw new IllegalStateException("getInputStream() can only be called once per Contents instance.");
    }
    this.CT = true;
    return new FileInputStream(this.AC.getFileDescriptor());
  }
  
  public int getMode()
  {
    return this.CR;
  }
  
  public OutputStream getOutputStream()
  {
    if (this.mClosed) {
      throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
    }
    if (this.CR != 536870912) {
      throw new IllegalStateException("getOutputStream() can only be used with contents opened with MODE_WRITE_ONLY.");
    }
    if (this.CU) {
      throw new IllegalStateException("getOutputStream() can only be called once per Contents instance.");
    }
    this.CU = true;
    return new FileOutputStream(this.AC.getFileDescriptor());
  }
  
  public ParcelFileDescriptor getParcelFileDescriptor()
  {
    if (this.mClosed) {
      throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
    }
    return this.AC;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    a.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\Contents.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */