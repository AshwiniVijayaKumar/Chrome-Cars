package twitter4j;

import java.io.Serializable;
import java.util.Date;

public abstract interface User
  extends Comparable<User>, TwitterResponse, Serializable
{
  public abstract String getBiggerProfileImageURL();
  
  public abstract String getBiggerProfileImageURLHttps();
  
  public abstract Date getCreatedAt();
  
  public abstract String getDescription();
  
  public abstract URLEntity[] getDescriptionURLEntities();
  
  public abstract int getFavouritesCount();
  
  public abstract int getFollowersCount();
  
  public abstract int getFriendsCount();
  
  public abstract long getId();
  
  public abstract String getLang();
  
  public abstract int getListedCount();
  
  public abstract String getLocation();
  
  public abstract String getMiniProfileImageURL();
  
  public abstract String getMiniProfileImageURLHttps();
  
  public abstract String getName();
  
  public abstract String getOriginalProfileImageURL();
  
  public abstract String getOriginalProfileImageURLHttps();
  
  public abstract String getProfileBackgroundColor();
  
  public abstract String getProfileBackgroundImageURL();
  
  public abstract String getProfileBackgroundImageUrlHttps();
  
  public abstract String getProfileBannerIPadRetinaURL();
  
  public abstract String getProfileBannerIPadURL();
  
  public abstract String getProfileBannerMobileRetinaURL();
  
  public abstract String getProfileBannerMobileURL();
  
  public abstract String getProfileBannerRetinaURL();
  
  public abstract String getProfileBannerURL();
  
  public abstract String getProfileImageURL();
  
  public abstract String getProfileImageURLHttps();
  
  public abstract String getProfileLinkColor();
  
  public abstract String getProfileSidebarBorderColor();
  
  public abstract String getProfileSidebarFillColor();
  
  public abstract String getProfileTextColor();
  
  public abstract String getScreenName();
  
  public abstract Status getStatus();
  
  public abstract int getStatusesCount();
  
  public abstract String getTimeZone();
  
  public abstract String getURL();
  
  public abstract URLEntity getURLEntity();
  
  public abstract int getUtcOffset();
  
  public abstract boolean isContributorsEnabled();
  
  public abstract boolean isDefaultProfile();
  
  public abstract boolean isDefaultProfileImage();
  
  public abstract boolean isFollowRequestSent();
  
  public abstract boolean isGeoEnabled();
  
  public abstract boolean isProfileBackgroundTiled();
  
  public abstract boolean isProfileUseBackgroundImage();
  
  public abstract boolean isProtected();
  
  public abstract boolean isShowAllInlineMedia();
  
  public abstract boolean isTranslator();
  
  public abstract boolean isVerified();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\User.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */