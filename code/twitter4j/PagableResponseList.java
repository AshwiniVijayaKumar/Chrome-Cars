package twitter4j;

public abstract interface PagableResponseList<T extends TwitterResponse>
  extends ResponseList<T>, CursorSupport
{
  public abstract long getNextCursor();
  
  public abstract long getPreviousCursor();
  
  public abstract boolean hasNext();
  
  public abstract boolean hasPrevious();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\PagableResponseList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */