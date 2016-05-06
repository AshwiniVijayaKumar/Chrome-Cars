package com.adsdk.sdk.mraid;

import java.util.Map;

class MraidCommandOpen
  extends MraidCommand
{
  MraidCommandOpen(Map<String, String> paramMap, MraidView paramMraidView)
  {
    super(paramMap, paramMraidView);
  }
  
  void execute()
  {
    String str = getStringFromParamsForKey("url");
    this.mView.getBrowserController().open(str);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\mraid\MraidCommandOpen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */