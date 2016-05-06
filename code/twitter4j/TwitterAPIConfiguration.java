package twitter4j;

import java.io.Serializable;
import java.util.Map;

public abstract interface TwitterAPIConfiguration
  extends Serializable, TwitterResponse
{
  public abstract int getCharactersReservedPerMedia();
  
  public abstract int getMaxMediaPerUpload();
  
  public abstract String[] getNonUsernamePaths();
  
  public abstract int getPhotoSizeLimit();
  
  public abstract Map<Integer, MediaEntity.Size> getPhotoSizes();
  
  public abstract int getShortURLLength();
  
  public abstract int getShortURLLengthHttps();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\TwitterAPIConfiguration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */