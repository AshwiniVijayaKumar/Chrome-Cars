package com.google.android.gms.common.data;

import com.google.android.gms.internal.er;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class a<T>
  implements Iterator<T>
{
  private final DataBuffer<T> mDataBuffer;
  private int zV;
  
  public a(DataBuffer<T> paramDataBuffer)
  {
    this.mDataBuffer = ((DataBuffer)er.f(paramDataBuffer));
    this.zV = -1;
  }
  
  public boolean hasNext()
  {
    return this.zV < this.mDataBuffer.getCount() - 1;
  }
  
  public T next()
  {
    if (!hasNext()) {
      throw new NoSuchElementException("Cannot advance the iterator beyond " + this.zV);
    }
    DataBuffer localDataBuffer = this.mDataBuffer;
    int i = this.zV + 1;
    this.zV = i;
    return (T)localDataBuffer.get(i);
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\data\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */