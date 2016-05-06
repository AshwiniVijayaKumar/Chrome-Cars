package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.b;
import com.google.android.gms.plus.model.moments.ItemScope;
import com.google.android.gms.plus.model.moments.Moment;

public final class iq
  extends b
  implements Moment
{
  private io SJ;
  
  public iq(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  private io hV()
  {
    try
    {
      if (this.SJ == null)
      {
        byte[] arrayOfByte = getByteArray("momentImpl");
        Parcel localParcel = Parcel.obtain();
        localParcel.unmarshall(arrayOfByte, 0, arrayOfByte.length);
        localParcel.setDataPosition(0);
        this.SJ = io.CREATOR.aH(localParcel);
        localParcel.recycle();
      }
      return this.SJ;
    }
    finally {}
  }
  
  public String getId()
  {
    return hV().getId();
  }
  
  public ItemScope getResult()
  {
    return hV().getResult();
  }
  
  public String getStartDate()
  {
    return hV().getStartDate();
  }
  
  public ItemScope getTarget()
  {
    return hV().getTarget();
  }
  
  public String getType()
  {
    return hV().getType();
  }
  
  public io hU()
  {
    return hV();
  }
  
  public boolean hasId()
  {
    return hV().hasId();
  }
  
  public boolean hasResult()
  {
    return hV().hasId();
  }
  
  public boolean hasStartDate()
  {
    return hV().hasStartDate();
  }
  
  public boolean hasTarget()
  {
    return hV().hasTarget();
  }
  
  public boolean hasType()
  {
    return hV().hasType();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\iq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */