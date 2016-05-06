package com.adsdk.sdk.video;

import java.io.Serializable;

public class NavIconData
  implements Serializable
{
  public static final int TYPE_EXTERNAL = 1;
  public static final int TYPE_INAPP = 0;
  private static final long serialVersionUID = -6812948324043252699L;
  String clickUrl;
  String iconUrl;
  int openType;
  String title;
  
  public String toString()
  {
    return "NavIconData [title=" + this.title + ", iconUrl=" + this.iconUrl + ", openType=" + this.openType + ", clickUrl=" + this.clickUrl + "]";
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\video\NavIconData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */