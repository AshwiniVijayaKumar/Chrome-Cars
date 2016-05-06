package twitter4j;

import java.io.Serializable;

public abstract interface TimeZone
  extends Serializable
{
  public abstract String getName();
  
  public abstract String tzinfoName();
  
  public abstract int utcOffset();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\TimeZone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */