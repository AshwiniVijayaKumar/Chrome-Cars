package net.roarsoftware.lastfm;

import net.roarsoftware.xml.DomElement;

public class Session
{
  private String apiKey;
  private String key;
  private String secret;
  private boolean subscriber;
  private String username;
  
  public static Session createSession(String paramString1, String paramString2, String paramString3)
  {
    return createSession(paramString1, paramString2, paramString3, null, false);
  }
  
  public static Session createSession(String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean)
  {
    Session localSession = new Session();
    localSession.apiKey = paramString1;
    localSession.secret = paramString2;
    localSession.key = paramString3;
    localSession.username = paramString4;
    localSession.subscriber = paramBoolean;
    return localSession;
  }
  
  static Session sessionFromElement(DomElement paramDomElement, String paramString1, String paramString2)
  {
    if (paramDomElement == null) {
      return null;
    }
    String str = paramDomElement.getChildText("name");
    return createSession(paramString1, paramString2, paramDomElement.getChildText("key"), str, paramDomElement.getChildText("subscriber").equals("1"));
  }
  
  public String getApiKey()
  {
    return this.apiKey;
  }
  
  public String getKey()
  {
    return this.key;
  }
  
  public String getSecret()
  {
    return this.secret;
  }
  
  public String getUsername()
  {
    return this.username;
  }
  
  public boolean isSubscriber()
  {
    return this.subscriber;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\Session.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */