package com.ooyala.android;

import com.ooyala.android.util.WeakReferencePassThroughEquals;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ID3TagNotifier
{
  private static final ID3TagNotifier s_instance = new ID3TagNotifier();
  private final Set<WeakReferencePassThroughEquals<ID3TagNotifierListener>> listeners = new HashSet();
  
  public static final ID3TagNotifier s_getInstance()
  {
    return s_instance;
  }
  
  public void addWeakListener(ID3TagNotifierListener paramID3TagNotifierListener)
  {
    synchronized (this.listeners)
    {
      this.listeners.add(new WeakReferencePassThroughEquals(paramID3TagNotifierListener));
      return;
    }
  }
  
  public void onTag(byte[] paramArrayOfByte)
  {
    synchronized (this.listeners)
    {
      Iterator localIterator = this.listeners.iterator();
      while (localIterator.hasNext())
      {
        ID3TagNotifierListener localID3TagNotifierListener = (ID3TagNotifierListener)((WeakReferencePassThroughEquals)localIterator.next()).get();
        if (localID3TagNotifierListener != null) {
          localID3TagNotifierListener.onTag(paramArrayOfByte);
        }
      }
    }
  }
  
  public void removeWeakListener(ID3TagNotifierListener paramID3TagNotifierListener)
  {
    synchronized (this.listeners)
    {
      this.listeners.remove(new WeakReferencePassThroughEquals(paramID3TagNotifierListener));
      return;
    }
  }
  
  public static abstract interface ID3TagNotifierListener
  {
    public abstract void onTag(byte[] paramArrayOfByte);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\ID3TagNotifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */