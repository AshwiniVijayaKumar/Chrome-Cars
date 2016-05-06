package org.vudroid.core.events;

public abstract interface DecodingProgressListener
{
  public abstract void decodingProgressChanged(int paramInt);
  
  public static class DecodingProgressEvent
    extends SafeEvent<DecodingProgressListener>
  {
    private final int currentlyDecoding;
    
    public DecodingProgressEvent(int paramInt)
    {
      this.currentlyDecoding = paramInt;
    }
    
    public void dispatchSafely(DecodingProgressListener paramDecodingProgressListener)
    {
      paramDecodingProgressListener.decodingProgressChanged(this.currentlyDecoding);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\core\events\DecodingProgressListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */