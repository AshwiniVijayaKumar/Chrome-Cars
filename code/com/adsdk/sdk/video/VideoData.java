package com.adsdk.sdk.video;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

public class VideoData
  implements Serializable
{
  public static final int DELIVERY_PROGRESSIVE = 0;
  public static final int DELIVERY_STREAMING = 1;
  public static final int DISPLAY_FULLSCREEN = 0;
  public static final int DISPLAY_NORMAL = 1;
  public static final int OVERLAY_MARKUP = 1;
  public static final int OVERLAY_URL = 0;
  private static final long serialVersionUID = 3402649540160829602L;
  boolean allowTapNavigationBars;
  int bitrate;
  String bottomNavigationBarBackground;
  Vector<String> completeEvents = new Vector();
  int delivery;
  int display;
  int duration;
  int height;
  String htmlOverlayMarkup;
  int htmlOverlayType;
  String htmlOverlayUrl;
  Vector<NavIconData> icons = new Vector();
  Vector<String> muteEvents = new Vector();
  int orientation;
  String pauseButtonImage;
  Vector<String> pauseEvents = new Vector();
  String playButtonImage;
  String replayButtonImage;
  Vector<String> replayEvents = new Vector();
  boolean showBottomNavigationBar;
  boolean showHtmlOverlay = false;
  int showHtmlOverlayAfter;
  boolean showNavigationBars;
  boolean showPauseButton;
  boolean showReplayButton;
  boolean showSkipButton;
  int showSkipButtonAfter;
  boolean showTimer;
  boolean showTopNavigationBar;
  String skipButtonImage;
  Vector<String> skipEvents = new Vector();
  Vector<String> startEvents = new Vector();
  HashMap<Integer, Vector<String>> timeTrackingEvents = new HashMap();
  String topNavigationBarBackground;
  String type;
  Vector<String> unmuteEvents = new Vector();
  Vector<String> unpauseEvents = new Vector();
  String videoUrl;
  int width;
  
  public Vector<String> getCompleteEvents()
  {
    return this.completeEvents;
  }
  
  public Vector<String> getStartEvents()
  {
    return this.startEvents;
  }
  
  public void setCompleteEvents(Vector<String> paramVector)
  {
    this.completeEvents = paramVector;
  }
  
  public void setStartEvents(Vector<String> paramVector)
  {
    this.startEvents = paramVector;
  }
  
  public String toString()
  {
    return "VideoData \n[\norientation=" + this.orientation + ",\ndisplay=" + this.display + ",\ndelivery=" + this.delivery + ",\ntype=" + this.type + ",\nbitrate=" + this.bitrate + ",\nwidth=" + this.width + ",\nheight=" + this.height + ",\nvideoUrl=" + this.videoUrl + ",\nduration=" + this.duration + ",\nshowSkipButton=" + this.showSkipButton + ",\nshowSkipButtonAfter=" + this.showSkipButtonAfter + ",\nskipButtonImage=" + this.skipButtonImage + ",\nshowNavigationBars=" + this.showNavigationBars + ",\nallowTapNavigationBars=" + this.allowTapNavigationBars + ",\nshowTopNavigationBar=" + this.showTopNavigationBar + ",\ntopNavigationBarBackground=" + this.topNavigationBarBackground + ",\nshowBottomNavigationBar=" + this.showBottomNavigationBar + ",\nbottomNavigationBarBackground=" + this.bottomNavigationBarBackground + ",\nshowPauseButton=" + this.showPauseButton + ",\npauseButtonImage=" + this.pauseButtonImage + ",\nplayButtonImage=" + this.playButtonImage + ",\nshowReplayButton=" + this.showReplayButton + ",\nreplayButtonImage=" + this.replayButtonImage + ",\nshowTimer=" + this.showTimer + ",\nicons=" + this.icons + ",\ntimeTrackingEvents=" + this.timeTrackingEvents + ",\nstartEvents=" + getStartEvents() + ",\ncompleteEvents=" + getCompleteEvents() + ",\nmuteEvents=" + this.muteEvents + ",\nunmuteEvents=" + this.unmuteEvents + ",\npauseEvents=" + this.pauseEvents + ",\nunpauseEvents=" + this.unpauseEvents + ",\nskipEvents=" + this.skipEvents + ",\nreplayEvents=" + this.replayEvents + ",\nshowHtmlOverlay=" + this.showHtmlOverlay + ",\nshowHtmlOverlayAfter=" + this.showHtmlOverlayAfter + ",\nhtmlOverlayType=" + this.htmlOverlayType + ",\nhtmlOverlayUrl=" + this.htmlOverlayUrl + ",\nhtmlOverlayMarkup=" + this.htmlOverlayMarkup + "\n]";
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\video\VideoData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */