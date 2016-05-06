package com.adsdk.sdk.mraid;

import java.util.Map;

class MraidCommandUseCustomClose
  extends MraidCommand
{
  MraidCommandUseCustomClose(Map<String, String> paramMap, MraidView paramMraidView)
  {
    super(paramMap, paramMraidView);
  }
  
  void execute()
  {
    boolean bool = getBooleanFromParamsForKey("shouldUseCustomClose");
    this.mView.getDisplayController().useCustomClose(bool);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\mraid\MraidCommandUseCustomClose.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */