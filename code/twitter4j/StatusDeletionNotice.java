package twitter4j;

import java.io.Serializable;

public abstract interface StatusDeletionNotice
  extends Comparable<StatusDeletionNotice>, Serializable
{
  public abstract long getStatusId();
  
  public abstract long getUserId();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\StatusDeletionNotice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */