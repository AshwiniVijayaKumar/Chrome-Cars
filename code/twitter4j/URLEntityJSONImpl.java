package twitter4j;

final class URLEntityJSONImpl
  extends EntityIndex
  implements URLEntity
{
  private static final long serialVersionUID = 7333552738058031524L;
  private String displayURL;
  private String expandedURL;
  private String url;
  
  URLEntityJSONImpl() {}
  
  URLEntityJSONImpl(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3)
  {
    setStart(paramInt1);
    setEnd(paramInt2);
    this.url = paramString1;
    this.expandedURL = paramString2;
    this.displayURL = paramString3;
  }
  
  URLEntityJSONImpl(JSONObject paramJSONObject)
    throws TwitterException
  {
    init(paramJSONObject);
  }
  
  private void init(JSONObject paramJSONObject)
    throws TwitterException
  {
    try
    {
      JSONArray localJSONArray = paramJSONObject.getJSONArray("indices");
      setStart(localJSONArray.getInt(0));
      setEnd(localJSONArray.getInt(1));
      this.url = paramJSONObject.getString("url");
      if (!paramJSONObject.isNull("expanded_url")) {}
      for (this.expandedURL = paramJSONObject.getString("expanded_url"); !paramJSONObject.isNull("display_url"); this.expandedURL = this.url)
      {
        this.displayURL = paramJSONObject.getString("display_url");
        return;
      }
      this.displayURL = this.url;
    }
    catch (JSONException paramJSONObject)
    {
      throw new TwitterException(paramJSONObject);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass())) {
        return false;
      }
      paramObject = (URLEntityJSONImpl)paramObject;
      if (this.displayURL != null)
      {
        if (this.displayURL.equals(((URLEntityJSONImpl)paramObject).displayURL)) {}
      }
      else {
        while (((URLEntityJSONImpl)paramObject).displayURL != null) {
          return false;
        }
      }
      if (this.expandedURL != null)
      {
        if (this.expandedURL.equals(((URLEntityJSONImpl)paramObject).expandedURL)) {}
      }
      else {
        while (((URLEntityJSONImpl)paramObject).expandedURL != null) {
          return false;
        }
      }
      if (this.url == null) {
        break;
      }
    } while (this.url.equals(((URLEntityJSONImpl)paramObject).url));
    for (;;)
    {
      return false;
      if (((URLEntityJSONImpl)paramObject).url == null) {
        break;
      }
    }
  }
  
  public String getDisplayURL()
  {
    return this.displayURL;
  }
  
  public int getEnd()
  {
    return super.getEnd();
  }
  
  public String getExpandedURL()
  {
    return this.expandedURL;
  }
  
  public int getStart()
  {
    return super.getStart();
  }
  
  public String getText()
  {
    return this.url;
  }
  
  public String getURL()
  {
    return this.url;
  }
  
  public int hashCode()
  {
    int k = 0;
    int i;
    if (this.url != null)
    {
      i = this.url.hashCode();
      if (this.expandedURL == null) {
        break label64;
      }
    }
    label64:
    for (int j = this.expandedURL.hashCode();; j = 0)
    {
      if (this.displayURL != null) {
        k = this.displayURL.hashCode();
      }
      return (i * 31 + j) * 31 + k;
      i = 0;
      break;
    }
  }
  
  public String toString()
  {
    return "URLEntityJSONImpl{url='" + this.url + '\'' + ", expandedURL='" + this.expandedURL + '\'' + ", displayURL='" + this.displayURL + '\'' + '}';
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\URLEntityJSONImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */