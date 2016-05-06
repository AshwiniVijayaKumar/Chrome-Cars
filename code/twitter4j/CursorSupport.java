package twitter4j;

public abstract interface CursorSupport
{
  public static final long START = -1L;
  
  public abstract long getNextCursor();
  
  public abstract long getPreviousCursor();
  
  public abstract boolean hasNext();
  
  public abstract boolean hasPrevious();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\CursorSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */