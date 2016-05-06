package com.appyjump.video.sdk.data;

import com.appyjump.video.sdk.AdType;

public class Response
{
  public static int bannerHeight;
  public static int bannerWidth;
  public static String clickUrl;
  public static String imageUrl;
  private ClickType clickType;
  private int refresh;
  private boolean scale;
  private boolean skipPreflight;
  private String text;
  private AdType type;
  private UrlType urlType;
  
  public int getBannerHeight()
  {
    return bannerHeight;
  }
  
  public int getBannerWidth()
  {
    return bannerWidth;
  }
  
  public ClickType getClickType()
  {
    return this.clickType;
  }
  
  public String getClickUrl()
  {
    return clickUrl;
  }
  
  public String getImageUrl()
  {
    return imageUrl;
  }
  
  public int getRefresh()
  {
    return this.refresh;
  }
  
  public String getText()
  {
    return this.text;
  }
  
  public AdType getType()
  {
    return this.type;
  }
  
  public UrlType getUrlType()
  {
    return this.urlType;
  }
  
  public boolean isScale()
  {
    return this.scale;
  }
  
  public boolean isSkipPreflight()
  {
    return this.skipPreflight;
  }
  
  public void setBannerHeight(int paramInt)
  {
    bannerHeight = paramInt;
  }
  
  public void setBannerWidth(int paramInt)
  {
    bannerWidth = paramInt;
  }
  
  public void setClickType(ClickType paramClickType)
  {
    this.clickType = paramClickType;
  }
  
  public void setClickUrl(String paramString)
  {
    clickUrl = paramString;
  }
  
  public void setImageUrl(String paramString)
  {
    imageUrl = paramString;
  }
  
  public void setRefresh(int paramInt)
  {
    this.refresh = paramInt;
  }
  
  public void setScale(boolean paramBoolean)
  {
    this.scale = paramBoolean;
  }
  
  public void setSkipPreflight(boolean paramBoolean)
  {
    this.skipPreflight = paramBoolean;
  }
  
  public void setText(String paramString)
  {
    this.text = paramString;
  }
  
  public void setType(AdType paramAdType)
  {
    this.type = paramAdType;
  }
  
  public void setUrlType(UrlType paramUrlType)
  {
    this.urlType = paramUrlType;
  }
  
  public String toString()
  {
    return "Response [refresh=" + this.refresh + ", type=" + this.type + ", bannerWidth=" + bannerWidth + ", bannerHeight=" + bannerHeight + ", text=" + this.text + ", imageUrl=" + imageUrl + ", clickType=" + this.clickType + ", clickUrl=" + clickUrl + ", urlType=" + this.urlType + ", scale=" + this.scale + ", skipPreflight=" + this.skipPreflight + "]";
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\appyjump\video\sdk\data\Response.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */