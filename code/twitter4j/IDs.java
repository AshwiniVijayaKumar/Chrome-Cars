package twitter4j;

import java.io.Serializable;

public abstract interface IDs
  extends Serializable, CursorSupport, TwitterResponse
{
  public abstract long[] getIDs();
  
  public abstract long getNextCursor();
  
  public abstract long getPreviousCursor();
  
  public abstract boolean hasNext();
  
  public abstract boolean hasPrevious();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\IDs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */