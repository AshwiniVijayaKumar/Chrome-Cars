package twitter4j;

import java.util.Map;
import twitter4j.auth.Authorization;

public abstract interface HttpClient
{
  public abstract void addDefaultRequestHeader(String paramString1, String paramString2);
  
  public abstract HttpResponse delete(String paramString)
    throws TwitterException;
  
  public abstract HttpResponse delete(String paramString, HttpParameter[] paramArrayOfHttpParameter, Authorization paramAuthorization, HttpResponseListener paramHttpResponseListener)
    throws TwitterException;
  
  public abstract HttpResponse get(String paramString)
    throws TwitterException;
  
  public abstract HttpResponse get(String paramString, HttpParameter[] paramArrayOfHttpParameter, Authorization paramAuthorization, HttpResponseListener paramHttpResponseListener)
    throws TwitterException;
  
  public abstract Map<String, String> getRequestHeaders();
  
  public abstract HttpResponse head(String paramString)
    throws TwitterException;
  
  public abstract HttpResponse post(String paramString)
    throws TwitterException;
  
  public abstract HttpResponse post(String paramString, HttpParameter[] paramArrayOfHttpParameter, Authorization paramAuthorization, HttpResponseListener paramHttpResponseListener)
    throws TwitterException;
  
  public abstract HttpResponse put(String paramString)
    throws TwitterException;
  
  public abstract HttpResponse put(String paramString, HttpParameter[] paramArrayOfHttpParameter, Authorization paramAuthorization, HttpResponseListener paramHttpResponseListener)
    throws TwitterException;
  
  public abstract HttpResponse request(HttpRequest paramHttpRequest)
    throws TwitterException;
  
  public abstract HttpResponse request(HttpRequest paramHttpRequest, HttpResponseListener paramHttpResponseListener)
    throws TwitterException;
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\HttpClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */