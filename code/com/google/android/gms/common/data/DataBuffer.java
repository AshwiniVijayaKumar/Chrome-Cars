package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Iterator;

public abstract class DataBuffer<T>
  implements Iterable<T>
{
  protected final DataHolder zU;
  
  protected DataBuffer(DataHolder paramDataHolder)
  {
    this.zU = paramDataHolder;
    if (this.zU != null) {
      this.zU.c(this);
    }
  }
  
  public void close()
  {
    if (this.zU != null) {
      this.zU.close();
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public abstract T get(int paramInt);
  
  public int getCount()
  {
    if (this.zU == null) {
      return 0;
    }
    return this.zU.getCount();
  }
  
  public Bundle getMetadata()
  {
    return this.zU.getMetadata();
  }
  
  public boolean isClosed()
  {
    if (this.zU == null) {
      return true;
    }
    return this.zU.isClosed();
  }
  
  public Iterator<T> iterator()
  {
    return new a(this);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\data\DataBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */