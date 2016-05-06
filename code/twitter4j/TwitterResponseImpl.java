package twitter4j;

import java.io.Serializable;

abstract class TwitterResponseImpl
  implements Serializable, TwitterResponse
{
  private static final long serialVersionUID = 7422171124869859808L;
  private final transient int accessLevel;
  private transient RateLimitStatus rateLimitStatus = null;
  
  public TwitterResponseImpl()
  {
    this.accessLevel = 0;
  }
  
  public TwitterResponseImpl(HttpResponse paramHttpResponse)
  {
    this.rateLimitStatus = RateLimitStatusJSONImpl.createFromResponseHeader(paramHttpResponse);
    this.accessLevel = ParseUtil.toAccessLevel(paramHttpResponse);
  }
  
  public int getAccessLevel()
  {
    return this.accessLevel;
  }
  
  public RateLimitStatus getRateLimitStatus()
  {
    return this.rateLimitStatus;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\TwitterResponseImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */