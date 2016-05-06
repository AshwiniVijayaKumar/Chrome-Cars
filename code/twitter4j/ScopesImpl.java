package twitter4j;

public class ScopesImpl
  implements Scopes
{
  private final String[] placeIds;
  
  ScopesImpl()
  {
    this.placeIds = new String[0];
  }
  
  public ScopesImpl(String[] paramArrayOfString)
  {
    this.placeIds = paramArrayOfString;
  }
  
  public String[] getPlaceIds()
  {
    return this.placeIds;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\ScopesImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */