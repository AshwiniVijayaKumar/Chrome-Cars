package twitter4j.auth;

import java.io.Serializable;
import twitter4j.HttpRequest;

public abstract interface Authorization
  extends Serializable
{
  public abstract String getAuthorizationHeader(HttpRequest paramHttpRequest);
  
  public abstract boolean isEnabled();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\auth\Authorization.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */