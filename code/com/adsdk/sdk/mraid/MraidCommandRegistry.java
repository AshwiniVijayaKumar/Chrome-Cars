package com.adsdk.sdk.mraid;

import java.util.HashMap;
import java.util.Map;

class MraidCommandRegistry
{
  private static Map<String, MraidCommandFactory> commandMap = new HashMap();
  
  static
  {
    commandMap.put("close", new MraidCommandFactory()
    {
      public MraidCommand create(Map<String, String> paramAnonymousMap, MraidView paramAnonymousMraidView)
      {
        return new MraidCommandClose(paramAnonymousMap, paramAnonymousMraidView);
      }
    });
    commandMap.put("expand", new MraidCommandFactory()
    {
      public MraidCommand create(Map<String, String> paramAnonymousMap, MraidView paramAnonymousMraidView)
      {
        return new MraidCommandExpand(paramAnonymousMap, paramAnonymousMraidView);
      }
    });
    commandMap.put("usecustomclose", new MraidCommandFactory()
    {
      public MraidCommand create(Map<String, String> paramAnonymousMap, MraidView paramAnonymousMraidView)
      {
        return new MraidCommandUseCustomClose(paramAnonymousMap, paramAnonymousMraidView);
      }
    });
    commandMap.put("open", new MraidCommandFactory()
    {
      public MraidCommand create(Map<String, String> paramAnonymousMap, MraidView paramAnonymousMraidView)
      {
        return new MraidCommandOpen(paramAnonymousMap, paramAnonymousMraidView);
      }
    });
  }
  
  static MraidCommand createCommand(String paramString, Map<String, String> paramMap, MraidView paramMraidView)
  {
    paramString = (MraidCommandFactory)commandMap.get(paramString);
    if (paramString != null) {
      return paramString.create(paramMap, paramMraidView);
    }
    return null;
  }
  
  private static abstract interface MraidCommandFactory
  {
    public abstract MraidCommand create(Map<String, String> paramMap, MraidView paramMraidView);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\mraid\MraidCommandRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */