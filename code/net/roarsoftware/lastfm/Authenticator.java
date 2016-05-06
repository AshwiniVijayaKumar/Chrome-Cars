package net.roarsoftware.lastfm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import net.roarsoftware.util.StringUtilities;
import net.roarsoftware.xml.DomElement;

public class Authenticator
{
  static String createSignature(String paramString1, Map<String, String> paramMap, String paramString2)
  {
    paramMap = new TreeMap(paramMap);
    paramMap.put("method", paramString1);
    paramString1 = new StringBuilder(100);
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      paramString1.append((String)localEntry.getKey());
      paramString1.append((String)localEntry.getValue());
    }
    paramString1.append(paramString2);
    return StringUtilities.md5(paramString1.toString());
  }
  
  public static Session getMobileSession(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    String str = paramString2;
    if (!StringUtilities.isMD5(paramString2)) {
      str = StringUtilities.md5(paramString2);
    }
    paramString2 = StringUtilities.md5(paramString1 + str);
    str = createSignature("auth.getMobileSession", StringUtilities.map(new String[] { "api_key", paramString3, "username", paramString1, "authToken", paramString2 }), paramString4);
    return Session.sessionFromElement(Caller.getInstance().call("auth.getMobileSession", paramString3, new String[] { "username", paramString1, "authToken", paramString2, "api_sig", str }).getContentElement(), paramString3, paramString4);
  }
  
  public static Session getSession(String paramString1, String paramString2, String paramString3)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("api_key", paramString2);
    localHashMap.put("token", paramString1);
    localHashMap.put("api_sig", createSignature("auth.getSession", localHashMap, paramString3));
    return Session.sessionFromElement(Caller.getInstance().call("auth.getSession", paramString2, localHashMap).getContentElement(), paramString2, paramString3);
  }
  
  public static String getToken(String paramString)
  {
    return Caller.getInstance().call("auth.getToken", paramString, new String[0]).getContentElement().getText();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\Authenticator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */