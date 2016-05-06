package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class c<T extends SafeParcelable>
  extends DataBuffer<T>
{
  private static final String[] zY = { "data" };
  private final Parcelable.Creator<T> zZ;
  
  public c(DataHolder paramDataHolder, Parcelable.Creator<T> paramCreator)
  {
    super(paramDataHolder);
    this.zZ = paramCreator;
  }
  
  public T H(int paramInt)
  {
    Object localObject = this.zU.getByteArray("data", paramInt, 0);
    Parcel localParcel = Parcel.obtain();
    localParcel.unmarshall((byte[])localObject, 0, localObject.length);
    localParcel.setDataPosition(0);
    localObject = (SafeParcelable)this.zZ.createFromParcel(localParcel);
    localParcel.recycle();
    return (T)localObject;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\data\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */