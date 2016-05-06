package com.adsdk.sdk;

import com.adsdk.sdk.data.ClickType;

public class BannerAd
  implements Ad
{
  public static final String OTHER = "other";
  public static final String WEB = "web";
  private static final long serialVersionUID = 3271938798582141269L;
  private int bannerHeight;
  private int bannerWidth;
  private ClickType clickType;
  private String clickUrl;
  private String imageUrl;
  private int refresh;
  private boolean scale;
  private int skipOverlay = 0;
  private boolean skipPreflight;
  private String text;
  private int type;
  private String urlType;
  
  public int getBannerHeight()
  {
    return this.bannerHeight;
  }
  
  public int getBannerWidth()
  {
    return this.bannerWidth;
  }
  
  public ClickType getClickType()
  {
    return this.clickType;
  }
  
  public String getClickUrl()
  {
    return this.clickUrl;
  }
  
  public String getImageUrl()
  {
    return this.imageUrl;
  }
  
  public int getRefresh()
  {
    return this.refresh;
  }
  
  public int getSkipOverlay()
  {
    return this.skipOverlay;
  }
  
  public String getText()
  {
    return this.text;
  }
  
  public int getType()
  {
    return this.type;
  }
  
  public String getUrlType()
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
    this.bannerHeight = paramInt;
  }
  
  public void setBannerWidth(int paramInt)
  {
    this.bannerWidth = paramInt;
  }
  
  public void setClickType(ClickType paramClickType)
  {
    this.clickType = paramClickType;
  }
  
  public void setClickUrl(String paramString)
  {
    this.clickUrl = paramString;
  }
  
  public void setImageUrl(String paramString)
  {
    this.imageUrl = paramString;
  }
  
  public void setRefresh(int paramInt)
  {
    this.refresh = paramInt;
  }
  
  public void setScale(boolean paramBoolean)
  {
    this.scale = paramBoolean;
  }
  
  public void setSkipOverlay(int paramInt)
  {
    this.skipOverlay = paramInt;
  }
  
  public void setSkipPreflight(boolean paramBoolean)
  {
    this.skipPreflight = paramBoolean;
  }
  
  public void setText(String paramString)
  {
    this.text = paramString;
  }
  
  public void setType(int paramInt)
  {
    this.type = paramInt;
  }
  
  public void setUrlType(String paramString)
  {
    this.urlType = paramString;
  }
  
  public String toString()
  {
    return "Response [refresh=" + this.refresh + ", type=" + this.type + ", bannerWidth=" + this.bannerWidth + ", bannerHeight=" + this.bannerHeight + ", text=" + this.text + ", imageUrl=" + this.imageUrl + ", clickType=" + this.clickType + ", clickUrl=" + this.clickUrl + ", urlType=" + this.urlType + ", scale=" + this.scale + ", skipPreflight=" + this.skipPreflight + "]";
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\BannerAd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */