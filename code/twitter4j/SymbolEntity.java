package twitter4j;

import java.io.Serializable;

public abstract interface SymbolEntity
  extends Serializable, TweetEntity
{
  public abstract int getEnd();
  
  public abstract int getStart();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\SymbolEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */