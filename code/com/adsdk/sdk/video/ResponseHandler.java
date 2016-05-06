package com.adsdk.sdk.video;

import java.io.CharArrayWriter;
import java.util.HashMap;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ResponseHandler
  extends DefaultHandler
{
  private CharArrayWriter contents = new CharArrayWriter();
  private long currentExpiration;
  private TrackerData currentTracker = new TrackerData();
  private boolean insideInterstitial = false;
  private boolean insideMarkup = false;
  private boolean insideVideo = false;
  private boolean insideVideoList = false;
  private RichMediaAd richMediaAd = null;
  HashMap<String, Long> videoList = null;
  
  private boolean getBoolean(String paramString)
  {
    if (paramString == null) {}
    for (;;)
    {
      return false;
      try
      {
        int i = Integer.parseInt(paramString);
        if (i > 0) {
          return true;
        }
      }
      catch (NumberFormatException paramString) {}
    }
    return false;
  }
  
  private int getInteger(String paramString)
  {
    if (paramString == null) {
      return -1;
    }
    try
    {
      int i = Integer.parseInt(paramString);
      return i;
    }
    catch (NumberFormatException paramString) {}
    return -1;
  }
  
  private long getLong(String paramString)
  {
    if (paramString == null) {
      return -1L;
    }
    try
    {
      long l = Long.parseLong(paramString);
      return l;
    }
    catch (NumberFormatException paramString) {}
    return -1L;
  }
  
  public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws SAXException
  {
    this.contents.write(paramArrayOfChar, paramInt1, paramInt2);
  }
  
  public void endElement(String paramString1, String paramString2, String paramString3)
    throws SAXException
  {
    if (paramString2.equals("creative"))
    {
      if ((getRichMediaAd() == null) || (getRichMediaAd().getVideo() == null)) {
        throw new SAXException("Creative tag found outside video node");
      }
      getRichMediaAd().getVideo().videoUrl = this.contents.toString().trim();
    }
    do
    {
      for (;;)
      {
        return;
        if (paramString2.equals("duration"))
        {
          if ((getRichMediaAd() == null) || (getRichMediaAd().getVideo() == null)) {
            throw new SAXException("Duration tag found outside video node");
          }
          getRichMediaAd().getVideo().duration = getInteger(this.contents.toString().trim());
          return;
        }
        if (!paramString2.equals("tracker")) {
          break;
        }
        if ((getRichMediaAd() == null) || (getRichMediaAd().getVideo() == null)) {
          throw new SAXException("Tracker tag found outside video node");
        }
        paramString3 = getRichMediaAd().getVideo();
        this.currentTracker.url = this.contents.toString().trim();
        paramString1 = null;
        switch (this.currentTracker.type)
        {
        }
        while (paramString1 != null)
        {
          paramString1.add(this.currentTracker.url);
          return;
          paramString2 = (Vector)paramString3.timeTrackingEvents.get(Integer.valueOf(this.currentTracker.time));
          paramString1 = paramString2;
          if (paramString2 == null)
          {
            paramString1 = new Vector();
            paramString3.timeTrackingEvents.put(Integer.valueOf(this.currentTracker.time), paramString1);
            continue;
            paramString1 = paramString3.getStartEvents();
            continue;
            paramString1 = paramString3.getCompleteEvents();
            continue;
            paramString1 = paramString3.pauseEvents;
            continue;
            paramString1 = paramString3.unpauseEvents;
            continue;
            paramString1 = paramString3.muteEvents;
            continue;
            paramString1 = paramString3.unmuteEvents;
            continue;
            paramString1 = paramString3.replayEvents;
            continue;
            paramString1 = paramString3.skipEvents;
          }
        }
      }
      if (paramString2.equals("htmloverlay"))
      {
        if ((getRichMediaAd() == null) || (getRichMediaAd().getVideo() == null)) {
          throw new SAXException("htmloverlay tag found outside video node");
        }
        getRichMediaAd().getVideo().htmlOverlayMarkup = this.contents.toString().trim();
        this.insideMarkup = false;
        return;
      }
      if (paramString2.equals("video"))
      {
        if (this.insideVideoList)
        {
          paramString1 = this.contents.toString().trim();
          this.videoList.put(paramString1, Long.valueOf(this.currentExpiration));
        }
        this.insideVideo = false;
        return;
      }
      if (paramString2.equals("interstitial"))
      {
        this.insideInterstitial = false;
        return;
      }
      if (paramString2.equals("markup"))
      {
        if ((getRichMediaAd() == null) || (getRichMediaAd().getInterstitial() == null)) {
          throw new SAXException("markup tag found outside interstitial node");
        }
        this.insideMarkup = false;
        getRichMediaAd().getInterstitial().interstitialMarkup = this.contents.toString().trim();
        return;
      }
    } while (!paramString2.equals("error"));
    getRichMediaAd().setType(2);
  }
  
  public RichMediaAd getRichMediaAd()
  {
    return this.richMediaAd;
  }
  
  public void setRichMediaAd(RichMediaAd paramRichMediaAd)
  {
    this.richMediaAd = paramRichMediaAd;
  }
  
  public void startDocument()
    throws SAXException
  {
    setRichMediaAd(new RichMediaAd());
    this.insideVideoList = false;
  }
  
  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
    throws SAXException
  {
    if (!this.insideMarkup)
    {
      this.contents.reset();
      if (!paramString2.equals("activevideolist")) {
        break label40;
      }
      this.videoList = new HashMap();
      this.insideVideoList = true;
    }
    label40:
    label212:
    label809:
    label1064:
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    return;
                    if (paramString2.equals("ad"))
                    {
                      paramString1 = paramAttributes.getValue("type");
                      if ("video-to-interstitial".equalsIgnoreCase(paramString1)) {
                        getRichMediaAd().setType(3);
                      }
                      for (;;)
                      {
                        paramString1 = paramAttributes.getValue("animation");
                        if (!"fade-in".equalsIgnoreCase(paramString1)) {
                          break label212;
                        }
                        getRichMediaAd().setAnimation(1);
                        return;
                        if ("interstitial-to-video".equalsIgnoreCase(paramString1))
                        {
                          getRichMediaAd().setType(4);
                        }
                        else if ("video".equalsIgnoreCase(paramString1))
                        {
                          getRichMediaAd().setType(5);
                        }
                        else if ("interstitial".equalsIgnoreCase(paramString1))
                        {
                          getRichMediaAd().setType(6);
                        }
                        else
                        {
                          if (!"noAd".equalsIgnoreCase(paramString1)) {
                            break;
                          }
                          getRichMediaAd().setType(2);
                        }
                      }
                      throw new SAXException("Unknown response type " + paramString1);
                      if ("slide-in-top".equalsIgnoreCase(paramString1))
                      {
                        getRichMediaAd().setAnimation(2);
                        return;
                      }
                      if ("slide-in-bottom".equalsIgnoreCase(paramString1))
                      {
                        getRichMediaAd().setAnimation(3);
                        return;
                      }
                      if ("slide-in-left".equalsIgnoreCase(paramString1))
                      {
                        getRichMediaAd().setAnimation(4);
                        return;
                      }
                      if ("slide-in-right".equalsIgnoreCase(paramString1))
                      {
                        getRichMediaAd().setAnimation(5);
                        return;
                      }
                      if ("flip-in".equalsIgnoreCase(paramString1))
                      {
                        getRichMediaAd().setAnimation(6);
                        return;
                      }
                      getRichMediaAd().setAnimation(0);
                      return;
                    }
                    if (paramString2.equals("video"))
                    {
                      if (this.insideVideoList)
                      {
                        this.currentExpiration = (getLong(paramAttributes.getValue("expiration")) * 1000L);
                        return;
                      }
                      this.insideVideo = true;
                      paramString1 = new VideoData();
                      paramString2 = paramAttributes.getValue("orientation");
                      if ("landscape".equalsIgnoreCase(paramString2)) {
                        paramString1.orientation = 0;
                      }
                      while (getRichMediaAd() != null) {
                        if ((getRichMediaAd().getType() == 6) && (getRichMediaAd().getType() != 4) && (getRichMediaAd().getType() != 3))
                        {
                          throw new SAXException("Found Video tag in an interstitial ad:" + getRichMediaAd().getType());
                          if ("portrait".equalsIgnoreCase(paramString2)) {
                            paramString1.orientation = 1;
                          } else {
                            paramString1.orientation = 0;
                          }
                        }
                        else
                        {
                          getRichMediaAd().setVideo(paramString1);
                          return;
                        }
                      }
                      throw new SAXException("Video tag found outside document root");
                    }
                    if (paramString2.equals("interstitial"))
                    {
                      this.insideInterstitial = true;
                      paramString1 = new InterstitialData();
                      paramString1.autoclose = getInteger(paramAttributes.getValue("autoclose"));
                      paramString2 = paramAttributes.getValue("type");
                      if ("url".equalsIgnoreCase(paramString2))
                      {
                        paramString1.interstitialType = 0;
                        paramString3 = paramAttributes.getValue("url");
                        if ((paramString3 == null) || (paramString3.length() == 0)) {
                          throw new SAXException("Empty url for interstitial type " + paramString2);
                        }
                        paramString1.interstitialUrl = paramString3;
                        paramString2 = paramAttributes.getValue("orientation");
                        if (!"landscape".equalsIgnoreCase(paramString2)) {
                          break label809;
                        }
                        paramString1.orientation = 0;
                      }
                      for (;;)
                      {
                        if (getRichMediaAd() != null)
                        {
                          if ((getRichMediaAd().getType() == 5) && (getRichMediaAd().getType() != 4) && (getRichMediaAd().getType() != 3))
                          {
                            throw new SAXException("Found Interstitial tag in a video ad:" + getRichMediaAd().getType());
                            if ("markup".equalsIgnoreCase(paramString2))
                            {
                              paramString1.interstitialType = 1;
                              this.insideMarkup = true;
                              break;
                            }
                            paramString1.interstitialType = 0;
                            paramString3 = paramAttributes.getValue("url");
                            if ((paramString3 == null) || (paramString3.length() == 0)) {
                              throw new SAXException("Empty url for interstitial type " + paramString2);
                            }
                            paramString1.interstitialUrl = paramString3;
                            break;
                            if ("portrait".equalsIgnoreCase(paramString2))
                            {
                              paramString1.orientation = 1;
                              continue;
                            }
                            paramString1.orientation = 0;
                            continue;
                          }
                          getRichMediaAd().setInterstitial(paramString1);
                          return;
                        }
                      }
                      throw new SAXException("Interstitial tag found outside document root");
                    }
                    if (paramString2.equals("creative"))
                    {
                      if ((getRichMediaAd() == null) || (getRichMediaAd().getVideo() == null)) {
                        throw new SAXException("Creative tag found outside video node");
                      }
                      paramString3 = getRichMediaAd().getVideo();
                      paramString1 = paramAttributes.getValue("delivery");
                      if ("progressive".equalsIgnoreCase(paramString1))
                      {
                        paramString3.delivery = 0;
                        paramString2 = paramAttributes.getValue("type");
                        if (paramString2 != null)
                        {
                          paramString1 = paramString2;
                          if (paramString2.length() != 0) {}
                        }
                        else
                        {
                          paramString1 = "application/mp4";
                        }
                        paramString2 = paramAttributes.getValue("display");
                        if (!"fullscreen".equalsIgnoreCase(paramString2)) {
                          break label1064;
                        }
                        paramString3.display = 0;
                      }
                      for (;;)
                      {
                        paramString3.type = paramString1;
                        paramString3.width = getInteger(paramAttributes.getValue("width"));
                        paramString3.height = getInteger(paramAttributes.getValue("height"));
                        paramString3.bitrate = getInteger(paramAttributes.getValue("bitrate"));
                        return;
                        if ("streaming".equalsIgnoreCase(paramString1))
                        {
                          paramString3.delivery = 1;
                          break;
                        }
                        paramString3.delivery = 1;
                        break;
                        if ("normal".equalsIgnoreCase(paramString2)) {
                          paramString3.display = 0;
                        } else {
                          paramString3.display = 0;
                        }
                      }
                    }
                    if (!paramString2.equals("skipbutton")) {
                      break;
                    }
                    if (this.insideVideo)
                    {
                      if ((getRichMediaAd() == null) || (getRichMediaAd().getVideo() == null)) {
                        throw new SAXException("skipbutton tag found inside wrong video node");
                      }
                      paramString1 = getRichMediaAd().getVideo();
                      paramString1.showSkipButton = getBoolean(paramAttributes.getValue("show"));
                      paramString1.showSkipButtonAfter = getInteger(paramAttributes.getValue("showafter"));
                      paramString1.skipButtonImage = paramAttributes.getValue("graphic");
                      return;
                    }
                  } while (!this.insideInterstitial);
                  if ((getRichMediaAd() == null) || (getRichMediaAd().getInterstitial() == null)) {
                    throw new SAXException("skipbutton tag found inside wrong interstitial node");
                  }
                  paramString1 = getRichMediaAd().getInterstitial();
                  paramString1.showSkipButton = getBoolean(paramAttributes.getValue("show"));
                  paramString1.showSkipButtonAfter = getInteger(paramAttributes.getValue("showafter"));
                  paramString1.skipButtonImage = paramAttributes.getValue("graphic");
                  return;
                  if (!paramString2.equals("navigation")) {
                    break;
                  }
                  if (this.insideVideo)
                  {
                    if ((getRichMediaAd() == null) || (getRichMediaAd().getVideo() == null)) {
                      throw new SAXException("navigation tag found inside wrong video node");
                    }
                    paramString1 = getRichMediaAd().getVideo();
                    paramString1.showNavigationBars = getBoolean(paramAttributes.getValue("show"));
                    paramString1.allowTapNavigationBars = getBoolean(paramAttributes.getValue("allowtap"));
                    return;
                  }
                } while (!this.insideInterstitial);
                if ((getRichMediaAd() == null) || (getRichMediaAd().getInterstitial() == null)) {
                  throw new SAXException("navigation tag found inside wrong interstitial node");
                }
                paramString1 = getRichMediaAd().getInterstitial();
                paramString1.showNavigationBars = getBoolean(paramAttributes.getValue("show"));
                paramString1.allowTapNavigationBars = getBoolean(paramAttributes.getValue("allowtap"));
                return;
                if (!paramString2.equals("topbar")) {
                  break;
                }
                if (this.insideVideo)
                {
                  if ((getRichMediaAd() == null) || (getRichMediaAd().getVideo() == null)) {
                    throw new SAXException("topbar tag found inside wrong video node");
                  }
                  paramString1 = getRichMediaAd().getVideo();
                  paramString1.showTopNavigationBar = getBoolean(paramAttributes.getValue("show"));
                  paramString1.topNavigationBarBackground = paramAttributes.getValue("custombackgroundurl");
                  return;
                }
              } while (!this.insideInterstitial);
              if ((getRichMediaAd() == null) || (getRichMediaAd().getInterstitial() == null)) {
                throw new SAXException("topbar tag found inside wrong interstitial node");
              }
              paramString1 = getRichMediaAd().getInterstitial();
              paramString1.showTopNavigationBar = getBoolean(paramAttributes.getValue("show"));
              paramString1.topNavigationBarBackground = paramAttributes.getValue("custombackgroundurl");
              paramString2 = paramAttributes.getValue("title");
              if ("fixed".equalsIgnoreCase(paramString2))
              {
                paramString1.topNavigationBarTitleType = 0;
                paramString1.topNavigationBarTitle = paramAttributes.getValue("titlecontent");
                return;
              }
              if ("variable".equalsIgnoreCase(paramString2))
              {
                paramString1.topNavigationBarTitleType = 1;
                return;
              }
              paramString1.topNavigationBarTitleType = 2;
              return;
              if (!paramString2.equals("bottombar")) {
                break;
              }
              if (this.insideVideo)
              {
                if ((getRichMediaAd() == null) || (getRichMediaAd().getVideo() == null)) {
                  throw new SAXException("bottombar tag found inside wrong video node");
                }
                paramString1 = getRichMediaAd().getVideo();
                paramString1.showBottomNavigationBar = getBoolean(paramAttributes.getValue("show"));
                paramString1.bottomNavigationBarBackground = paramAttributes.getValue("custombackgroundurl");
                paramString1.showPauseButton = getBoolean(paramAttributes.getValue("pausebutton"));
                paramString1.showReplayButton = getBoolean(paramAttributes.getValue("replaybutton"));
                paramString1.showTimer = getBoolean(paramAttributes.getValue("timer"));
                paramString1.pauseButtonImage = paramAttributes.getValue("pausebuttonurl");
                paramString1.playButtonImage = paramAttributes.getValue("playbuttonurl");
                paramString1.replayButtonImage = paramAttributes.getValue("replaybuttonurl");
                return;
              }
            } while (!this.insideInterstitial);
            if ((getRichMediaAd() == null) || (getRichMediaAd().getInterstitial() == null)) {
              throw new SAXException("bottombar tag found inside wrong interstitial node");
            }
            paramString1 = getRichMediaAd().getInterstitial();
            paramString1.showBottomNavigationBar = getBoolean(paramAttributes.getValue("show"));
            paramString1.bottomNavigationBarBackground = paramAttributes.getValue("custombackgroundurl");
            paramString1.showBackButton = getBoolean(paramAttributes.getValue("backbutton"));
            paramString1.showForwardButton = getBoolean(paramAttributes.getValue("forwardbutton"));
            paramString1.showReloadButton = getBoolean(paramAttributes.getValue("reloadbutton"));
            paramString1.showExternalButton = getBoolean(paramAttributes.getValue("externalbutton"));
            paramString1.showTimer = getBoolean(paramAttributes.getValue("timer"));
            paramString1.backButtonImage = paramAttributes.getValue("backbuttonurl");
            paramString1.forwardButtonImage = paramAttributes.getValue("forwardbuttonurl");
            paramString1.reloadButtonImage = paramAttributes.getValue("reloadbuttonurl");
            paramString1.externalButtonImage = paramAttributes.getValue("externalbuttonurl");
            return;
            if (!paramString2.equals("navicon")) {
              break;
            }
            if (this.insideVideo)
            {
              if ((getRichMediaAd() == null) || (getRichMediaAd().getVideo() == null)) {
                throw new SAXException("navicon tag found inside wrong video node");
              }
              paramString1 = getRichMediaAd().getVideo();
              paramString2 = new NavIconData();
              paramString2.title = paramAttributes.getValue("title");
              paramString2.clickUrl = paramAttributes.getValue("clickurl");
              paramString2.iconUrl = paramAttributes.getValue("iconurl");
              if ("inapp".equalsIgnoreCase(paramAttributes.getValue("opentype"))) {}
              for (paramString2.openType = 0;; paramString2.openType = 1)
              {
                paramString1.icons.add(paramString2);
                return;
              }
            }
          } while (!this.insideInterstitial);
          if ((getRichMediaAd() == null) || (getRichMediaAd().getInterstitial() == null)) {
            throw new SAXException("navicon tag found inside wrong interstitial node");
          }
          paramString1 = getRichMediaAd().getInterstitial();
          paramString2 = new NavIconData();
          paramString2.title = paramAttributes.getValue("title");
          paramString2.clickUrl = paramAttributes.getValue("clickurl");
          paramString2.iconUrl = paramAttributes.getValue("iconurl");
          if ("inapp".equalsIgnoreCase(paramAttributes.getValue("opentype"))) {}
          for (paramString2.openType = 0;; paramString2.openType = 1)
          {
            paramString1.icons.add(paramString2);
            return;
          }
          if (!paramString2.equals("tracker")) {
            break;
          }
        } while (!this.insideVideo);
        if ((getRichMediaAd() == null) || (getRichMediaAd().getVideo() == null)) {
          throw new SAXException("tracker tag found inside wrong video node");
        }
        paramString1 = getRichMediaAd().getVideo();
        this.currentTracker.reset();
        paramString2 = paramAttributes.getValue("type");
        if ("start".equalsIgnoreCase(paramString2))
        {
          this.currentTracker.type = 0;
          return;
        }
        if ("complete".equalsIgnoreCase(paramString2))
        {
          this.currentTracker.type = 1;
          return;
        }
        if ("midpoint".equalsIgnoreCase(paramString2))
        {
          this.currentTracker.type = 2;
          this.currentTracker.time = (paramString1.duration / 2);
          return;
        }
        if ("firstquartile".equalsIgnoreCase(paramString2))
        {
          this.currentTracker.type = 3;
          this.currentTracker.time = (paramString1.duration / 4);
          return;
        }
        if ("thirdquartile".equalsIgnoreCase(paramString2))
        {
          this.currentTracker.type = 4;
          this.currentTracker.time = (paramString1.duration * 3 / 4);
          return;
        }
        if ("pause".equalsIgnoreCase(paramString2))
        {
          this.currentTracker.type = 6;
          return;
        }
        if ("unpause".equalsIgnoreCase(paramString2))
        {
          this.currentTracker.type = 7;
          return;
        }
        if ("mute".equalsIgnoreCase(paramString2))
        {
          this.currentTracker.type = 8;
          return;
        }
        if ("unmute".equalsIgnoreCase(paramString2))
        {
          this.currentTracker.type = 9;
          return;
        }
        if ("replay".equalsIgnoreCase(paramString2))
        {
          this.currentTracker.type = 11;
          return;
        }
        if ("skip".equalsIgnoreCase(paramString2))
        {
          this.currentTracker.type = 10;
          return;
        }
      } while ((paramString2 == null) || (!paramString2.startsWith("sec:")));
      this.currentTracker.type = 5;
      this.currentTracker.time = getInteger(paramString2.substring(4));
      return;
    } while ((!paramString2.equals("htmloverlay")) || (!this.insideVideo));
    if ((getRichMediaAd() == null) || (getRichMediaAd().getVideo() == null)) {
      throw new SAXException("htmloverlay tag found inside wrong video node");
    }
    paramString1 = getRichMediaAd().getVideo();
    this.insideMarkup = true;
    paramString2 = paramAttributes.getValue("type");
    if ("url".equalsIgnoreCase(paramString2))
    {
      paramString1.htmlOverlayType = 0;
      paramString3 = paramAttributes.getValue("url");
      if ((paramString3 == null) || (paramString3.length() == 0)) {
        throw new SAXException("Empty url for overlay type " + paramString2);
      }
      paramString1.htmlOverlayUrl = paramString3;
    }
    for (;;)
    {
      paramString1.showHtmlOverlayAfter = getInteger(paramAttributes.getValue("showafter"));
      paramString1.showHtmlOverlay = getBoolean(paramAttributes.getValue("show"));
      return;
      if ("markup".equalsIgnoreCase(paramString2))
      {
        paramString1.htmlOverlayType = 1;
        this.insideMarkup = true;
      }
      else
      {
        paramString1.htmlOverlayType = 0;
        paramString3 = paramAttributes.getValue("url");
        if ((paramString3 == null) || (paramString3.length() == 0)) {
          throw new SAXException("Empty url for overlay type " + paramString2);
        }
        paramString1.htmlOverlayUrl = paramString3;
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\video\ResponseHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */