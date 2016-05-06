package com.adsdk.sdk.video;

import java.io.Serializable;
import java.util.Vector;

public class InterstitialData
  implements Serializable
{
  public static final int INTERSTITIAL_MARKUP = 1;
  public static final int INTERSTITIAL_TITLE_FIXED = 0;
  public static final int INTERSTITIAL_TITLE_HIDDEN = 2;
  public static final int INTERSTITIAL_TITLE_HTML = 1;
  public static final int INTERSTITIAL_URL = 0;
  private static final long serialVersionUID = 8764230341946345345L;
  boolean allowTapNavigationBars;
  int autoclose;
  String backButtonImage;
  String bottomNavigationBarBackground;
  String externalButtonImage;
  String forwardButtonImage;
  Vector<NavIconData> icons = new Vector();
  String interstitialMarkup;
  int interstitialType;
  String interstitialUrl;
  int orientation;
  String reloadButtonImage;
  boolean showBackButton;
  boolean showBottomNavigationBar;
  boolean showExternalButton;
  boolean showForwardButton;
  boolean showNavigationBars;
  boolean showReloadButton;
  boolean showSkipButton;
  int showSkipButtonAfter;
  boolean showTimer;
  boolean showTopNavigationBar;
  String skipButtonImage;
  String topNavigationBarBackground;
  String topNavigationBarTitle;
  int topNavigationBarTitleType;
  
  public String toString()
  {
    return "InterstitialData \n[\nautoclose=" + this.autoclose + ",\norientation=" + this.orientation + ",\ninterstitialType=" + this.interstitialType + ",\ninterstitialUrl=" + this.interstitialUrl + ",\ninterstitialMarkup=" + this.interstitialMarkup + ",\nshowSkipButton=" + this.showSkipButton + ",\nshowSkipButtonAfter=" + this.showSkipButtonAfter + ",\nskipButtonImage=" + this.skipButtonImage + ",\nshowNavigationBars=" + this.showNavigationBars + ",\nallowTapNavigationBars=" + this.allowTapNavigationBars + ",\nshowTopNavigationBar=" + this.showTopNavigationBar + ",\ntopNavigationBarBackground=" + this.topNavigationBarBackground + ",\ntopNavigationBarTitleType=" + this.topNavigationBarTitleType + ",\ntopNavigationBarTitle=" + this.topNavigationBarTitle + ",\nshowBottomNavigationBar=" + this.showBottomNavigationBar + ",\nbottomNavigationBarBackground=" + this.bottomNavigationBarBackground + ",\nshowBackButton=" + this.showBackButton + ",\nbackButtonImage=" + this.backButtonImage + ",\nshowForwardButton=" + this.showForwardButton + ",\nforwardButtonImage=" + this.forwardButtonImage + ",\nshowReloadButton=" + this.showReloadButton + ",\nreloadButtonImage=" + this.reloadButtonImage + ",\nshowExternalButton=" + this.showExternalButton + ",\nexternalButtonImage=" + this.externalButtonImage + ",\nshowTimer=" + this.showTimer + ",\nicons=" + this.icons + "\n]";
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\video\InterstitialData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */