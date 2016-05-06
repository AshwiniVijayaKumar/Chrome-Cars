package com.adsdk.sdk.mraid;

import java.util.Map;

class MraidCommandClose
  extends MraidCommand
{
  MraidCommandClose(Map<String, String> paramMap, MraidView paramMraidView)
  {
    super(paramMap, paramMraidView);
  }
  
  void execute()
  {
    this.mView.getDisplayController().close();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\mraid\MraidCommandClose.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */