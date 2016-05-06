package com.adsdk.sdk.video;

import com.adsdk.sdk.Ad;

public class RichMediaAd
  implements Ad
{
  public static final int ANIMATION_FADE_IN = 1;
  public static final int ANIMATION_FLIP_IN = 6;
  public static final int ANIMATION_NONE = 0;
  public static final int ANIMATION_SLIDE_IN_BOTTOM = 3;
  public static final int ANIMATION_SLIDE_IN_LEFT = 4;
  public static final int ANIMATION_SLIDE_IN_RIGHT = 5;
  public static final int ANIMATION_SLIDE_IN_TOP = 2;
  private static final long serialVersionUID = 6443573739926220979L;
  private int animation;
  private InterstitialData interstitial;
  private long timestamp;
  private int type;
  private VideoData video;
  
  public int getAnimation()
  {
    return this.animation;
  }
  
  public InterstitialData getInterstitial()
  {
    return this.interstitial;
  }
  
  public long getTimestamp()
  {
    return this.timestamp;
  }
  
  public int getType()
  {
    return this.type;
  }
  
  public VideoData getVideo()
  {
    return this.video;
  }
  
  public void setAnimation(int paramInt)
  {
    this.animation = paramInt;
  }
  
  public void setInterstitial(InterstitialData paramInterstitialData)
  {
    this.interstitial = paramInterstitialData;
  }
  
  public void setTimestamp(long paramLong)
  {
    this.timestamp = paramLong;
  }
  
  public void setType(int paramInt)
  {
    this.type = paramInt;
  }
  
  public void setVideo(VideoData paramVideoData)
  {
    this.video = paramVideoData;
  }
  
  public String toString()
  {
    return "RichMediaAD [timestamp=" + this.timestamp + ", type=" + this.type + ", animation=" + this.animation + ", video=" + this.video + ", interstitial=" + this.interstitial + "]";
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\video\RichMediaAd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */