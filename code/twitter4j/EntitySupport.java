package twitter4j;

public abstract interface EntitySupport
{
  public abstract MediaEntity[] getExtendedMediaEntities();
  
  public abstract HashtagEntity[] getHashtagEntities();
  
  public abstract MediaEntity[] getMediaEntities();
  
  public abstract SymbolEntity[] getSymbolEntities();
  
  public abstract URLEntity[] getURLEntities();
  
  public abstract UserMentionEntity[] getUserMentionEntities();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\EntitySupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */