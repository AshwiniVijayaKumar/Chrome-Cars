package twitter4j;

import twitter4j.api.HelpResources.Language;
import twitter4j.conf.Configuration;

public class LanguageJSONImpl
  implements HelpResources.Language
{
  private String code;
  private String name;
  private String status;
  
  LanguageJSONImpl(JSONObject paramJSONObject)
    throws TwitterException
  {
    init(paramJSONObject);
  }
  
  static ResponseList<HelpResources.Language> createLanguageList(HttpResponse paramHttpResponse, Configuration paramConfiguration)
    throws TwitterException
  {
    return createLanguageList(paramHttpResponse.asJSONArray(), paramHttpResponse, paramConfiguration);
  }
  
  static ResponseList<HelpResources.Language> createLanguageList(JSONArray paramJSONArray, HttpResponse paramHttpResponse, Configuration paramConfiguration)
    throws TwitterException
  {
    if (paramConfiguration.isJSONStoreEnabled()) {
      TwitterObjectFactory.clearThreadLocalMap();
    }
    for (;;)
    {
      int i;
      try
      {
        int j = paramJSONArray.length();
        paramHttpResponse = new ResponseListImpl(j, paramHttpResponse);
        i = 0;
        if (i < j)
        {
          JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
          LanguageJSONImpl localLanguageJSONImpl = new LanguageJSONImpl(localJSONObject);
          paramHttpResponse.add(localLanguageJSONImpl);
          if (paramConfiguration.isJSONStoreEnabled()) {
            TwitterObjectFactory.registerJSONObject(localLanguageJSONImpl, localJSONObject);
          }
        }
        else
        {
          if (paramConfiguration.isJSONStoreEnabled()) {
            TwitterObjectFactory.registerJSONObject(paramHttpResponse, paramJSONArray);
          }
          return paramHttpResponse;
        }
      }
      catch (JSONException paramJSONArray)
      {
        throw new TwitterException(paramJSONArray);
      }
      i += 1;
    }
  }
  
  private void init(JSONObject paramJSONObject)
    throws TwitterException
  {
    try
    {
      this.name = paramJSONObject.getString("name");
      this.code = paramJSONObject.getString("code");
      this.status = paramJSONObject.getString("status");
      return;
    }
    catch (JSONException localJSONException)
    {
      throw new TwitterException(localJSONException.getMessage() + ":" + paramJSONObject.toString(), localJSONException);
    }
  }
  
  public String getCode()
  {
    return this.code;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getStatus()
  {
    return this.status;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\LanguageJSONImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */