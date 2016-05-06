package com.ooyala.android.item;

import android.os.Build.VERSION;
import android.util.Base64;
import com.ooyala.android.StreamSelector;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class Stream
  implements JSONUpdatableItem
{
  public static final String DELIVERY_TYPE_AKAMAI_HD2_VOD_HLS = "akamai_hd2_vod_hls";
  public static final String DELIVERY_TYPE_HLS = "hls";
  public static final String DELIVERY_TYPE_MP4 = "mp4";
  public static final String DELIVERY_TYPE_REMOTE_ASSET = "remote_asset";
  public static final String DELIVERY_TYPE_SMOOTH = "smooth";
  public static final String DELIVERY_TYPE_WV_HLS = "wv_hls";
  public static final String DELIVERY_TYPE_WV_MP4 = "wv_mp4";
  public static final String DELIVERY_TYPE_WV_WVM = "wv_wvm";
  static final String KEY_ASPECT_RATIO = "aspect_ratio";
  static final String KEY_AUDIO_BITRATE = "audio_bitrate";
  static final String KEY_DATA = "data";
  static final String KEY_DELIVERY_TYPE = "delivery_type";
  static final String KEY_FORMAT = "format";
  static final String KEY_FRAMERATE = "framerate";
  static final String KEY_HEIGHT = "height";
  static final String KEY_IS_LIVE_STREAM = "is_live_stream";
  static final String KEY_PROFILE = "profile";
  static final String KEY_URL = "url";
  static final String KEY_VIDEO_BITRATE = "video_bitrate";
  static final String KEY_VIDEO_CODEC = "video_codec";
  static final String KEY_WIDEVINE_SERVER_PATH = "widevine_server_path";
  static final String KEY_WIDTH = "width";
  static final String PROFILE_BASELINE = "baseline";
  public static final String STREAM_URL_FORMAT_B64 = "encoded";
  public static final String STREAM_URL_FORMAT_TEXT = "text";
  private static StreamSelector _selector = new DefaultStreamSelector();
  protected String _aspectRatio = null;
  protected int _audioBitrate = -1;
  protected String _deliveryType = null;
  protected String _framerate = null;
  protected int _height = -1;
  protected boolean _isLiveStream = false;
  protected String _profile = null;
  protected String _url = null;
  protected String _urlFormat = null;
  protected int _videoBitrate = -1;
  protected String _videoCodec = null;
  protected String _widevineServerPath = null;
  protected int _width = -1;
  
  public Stream() {}
  
  public Stream(JSONObject paramJSONObject)
  {
    update(paramJSONObject);
  }
  
  public static Stream bestStream(Set<Stream> paramSet, boolean paramBoolean)
  {
    return _selector.bestStream(paramSet, paramBoolean);
  }
  
  public static Stream getStreamWithDeliveryType(Set<Stream> paramSet, String paramString)
  {
    paramSet = paramSet.iterator();
    while (paramSet.hasNext())
    {
      Stream localStream = (Stream)paramSet.next();
      if (localStream.getDeliveryType().equals(paramString)) {
        return localStream;
      }
    }
    return null;
  }
  
  public static boolean isDeliveryTypePlayable(Stream paramStream)
  {
    boolean bool2 = false;
    paramStream = paramStream.getDeliveryType();
    int i;
    if ((paramStream.equals("hls")) || (paramStream.equals("akamai_hd2_vod_hls")))
    {
      i = 1;
      if ((!paramStream.equals("wv_wvm")) && (!paramStream.equals("wv_hls"))) {
        break label117;
      }
    }
    label110:
    label117:
    for (int j = 1;; j = 0)
    {
      boolean bool1 = paramStream.equals("smooth");
      if ((!paramStream.equals("mp4")) && (!paramStream.equals("remote_asset")) && (!paramStream.equals("wv_mp4")) && (!bool1))
      {
        bool1 = bool2;
        if (Build.VERSION.SDK_INT < 14) {
          break label110;
        }
        if (i == 0)
        {
          bool1 = bool2;
          if (j == 0) {
            break label110;
          }
        }
      }
      bool1 = true;
      return bool1;
      i = 0;
      break;
    }
  }
  
  public static boolean isProfilePlayable(Stream paramStream)
  {
    if (!"mp4".equals(paramStream.getDeliveryType())) {}
    while ((paramStream.getProfile() == null) || ("baseline".equals(paramStream.getProfile()))) {
      return true;
    }
    return false;
  }
  
  public static void resetStreamSelector()
  {
    _selector = new DefaultStreamSelector();
  }
  
  public static void setStreamSelector(StreamSelector paramStreamSelector)
  {
    _selector = paramStreamSelector;
  }
  
  public static boolean streamSetContainsDeliveryType(Set<Stream> paramSet, String paramString)
  {
    return getStreamWithDeliveryType(paramSet, paramString) != null;
  }
  
  boolean betterThan(Stream paramStream, boolean paramBoolean)
  {
    if ((getCombinedBitrate() == paramStream.getCombinedBitrate()) && (getHeight() > paramStream.getHeight())) {}
    do
    {
      do
      {
        return true;
        if (!paramBoolean) {
          break;
        }
      } while (getCombinedBitrate() > paramStream.getCombinedBitrate());
      return false;
    } while (Math.abs(400 - getCombinedBitrate()) < Math.abs(400 - paramStream.getCombinedBitrate()));
    return false;
  }
  
  public URL decodedURL()
  {
    try
    {
      if (this._urlFormat.equals("encoded")) {
        return new URL(new String(Base64.decode(this._url, 0)));
      }
      URL localURL = new URL(this._url);
      return localURL;
    }
    catch (MalformedURLException localMalformedURLException)
    {
      System.out.println("Malformed URL: " + this._url);
    }
    return null;
  }
  
  public String getAspectRatio()
  {
    return this._aspectRatio;
  }
  
  public int getAudioBitrate()
  {
    return this._audioBitrate;
  }
  
  public int getCombinedBitrate()
  {
    return this._videoBitrate + this._audioBitrate;
  }
  
  public String getDeliveryType()
  {
    return this._deliveryType;
  }
  
  public String getFramerate()
  {
    return this._framerate;
  }
  
  public int getHeight()
  {
    return this._height;
  }
  
  public String getProfile()
  {
    return this._profile;
  }
  
  public String getUrl()
  {
    return this._url;
  }
  
  public String getUrlFormat()
  {
    return this._urlFormat;
  }
  
  public int getVideoBitrate()
  {
    return this._videoBitrate;
  }
  
  public String getVideoCodec()
  {
    return this._videoCodec;
  }
  
  public String getWidevineServerPath()
  {
    return this._widevineServerPath;
  }
  
  public int getWidth()
  {
    return this._width;
  }
  
  public boolean isLiveStream()
  {
    return this._isLiveStream;
  }
  
  public void setAspectRatio(String paramString)
  {
    this._aspectRatio = paramString;
  }
  
  public void setAudioBitrate(int paramInt)
  {
    this._audioBitrate = paramInt;
  }
  
  public void setDeliveryType(String paramString)
  {
    this._deliveryType = paramString;
  }
  
  public void setFramerate(String paramString)
  {
    this._framerate = paramString;
  }
  
  public void setHeight(int paramInt)
  {
    this._height = paramInt;
  }
  
  public void setLiveStream(boolean paramBoolean)
  {
    this._isLiveStream = paramBoolean;
  }
  
  public void setProfile(String paramString)
  {
    this._profile = paramString;
  }
  
  public void setUrl(String paramString)
  {
    this._url = paramString;
  }
  
  public void setUrlFormat(String paramString)
  {
    this._urlFormat = paramString;
  }
  
  public void setVideoBitrate(int paramInt)
  {
    this._videoBitrate = paramInt;
  }
  
  public void setVideoCodec(String paramString)
  {
    this._videoCodec = paramString;
  }
  
  public void setWidth(int paramInt)
  {
    this._width = paramInt;
  }
  
  JSONUpdatableItem.ReturnState update(JSONObject paramJSONObject)
  {
    if (paramJSONObject.isNull("delivery_type"))
    {
      System.out.println("ERROR: Fail to update stream with dictionary because no delivery_type exists!");
      return JSONUpdatableItem.ReturnState.STATE_FAIL;
    }
    if (paramJSONObject.isNull("url"))
    {
      System.out.println("ERROR: Fail to update stream with dictionary because no url element exists!");
      return JSONUpdatableItem.ReturnState.STATE_FAIL;
    }
    Object localObject;
    try
    {
      localObject = paramJSONObject.getJSONObject("url");
      if (((JSONObject)localObject).isNull("data"))
      {
        System.out.println("ERROR: Fail to update stream with dictionary because no url.data exists!");
        return JSONUpdatableItem.ReturnState.STATE_FAIL;
      }
    }
    catch (JSONException paramJSONObject)
    {
      System.out.println("ERROR: Fail to update stream with dictionary because url element is invalid.");
      return JSONUpdatableItem.ReturnState.STATE_FAIL;
    }
    if (((JSONObject)localObject).isNull("format"))
    {
      System.out.println("ERROR: Fail to update stream with dictionary because no url.format exists!");
      return JSONUpdatableItem.ReturnState.STATE_FAIL;
    }
    try
    {
      if (!paramJSONObject.isNull("widevine_server_path")) {
        this._widevineServerPath = paramJSONObject.getString("widevine_server_path");
      }
      this._deliveryType = paramJSONObject.getString("delivery_type");
      this._url = ((JSONObject)localObject).getString("data");
      this._urlFormat = ((JSONObject)localObject).getString("format");
      int i;
      label196:
      label216:
      label236:
      label255:
      label275:
      label296:
      boolean bool;
      if (paramJSONObject.isNull("video_bitrate"))
      {
        i = this._videoBitrate;
        this._videoBitrate = i;
        if (!paramJSONObject.isNull("audio_bitrate")) {
          break label354;
        }
        i = this._audioBitrate;
        this._audioBitrate = i;
        if (!paramJSONObject.isNull("video_codec")) {
          break label364;
        }
        localObject = this._videoCodec;
        this._videoCodec = ((String)localObject);
        if (!paramJSONObject.isNull("height")) {
          break label375;
        }
        i = this._height;
        this._height = i;
        if (!paramJSONObject.isNull("width")) {
          break label385;
        }
        i = this._width;
        this._width = i;
        if (!paramJSONObject.isNull("framerate")) {
          break label395;
        }
        localObject = this._framerate;
        this._framerate = ((String)localObject);
        if (!paramJSONObject.isNull("aspect_ratio")) {
          break label406;
        }
        localObject = this._aspectRatio;
        this._aspectRatio = ((String)localObject);
        if (!paramJSONObject.isNull("is_live_stream")) {
          break label417;
        }
        bool = this._isLiveStream;
        label316:
        this._isLiveStream = bool;
        if (!paramJSONObject.isNull("profile")) {
          break label427;
        }
      }
      label354:
      label364:
      label375:
      label385:
      label395:
      label406:
      label417:
      label427:
      for (paramJSONObject = this._profile;; paramJSONObject = paramJSONObject.getString("profile"))
      {
        this._profile = paramJSONObject;
        return JSONUpdatableItem.ReturnState.STATE_MATCHED;
        i = paramJSONObject.getInt("video_bitrate");
        break;
        i = paramJSONObject.getInt("audio_bitrate");
        break label196;
        localObject = paramJSONObject.getString("video_codec");
        break label216;
        i = paramJSONObject.getInt("height");
        break label236;
        i = paramJSONObject.getInt("width");
        break label255;
        localObject = paramJSONObject.getString("framerate");
        break label275;
        localObject = paramJSONObject.getString("aspect_ratio");
        break label296;
        bool = paramJSONObject.getBoolean("is_live_stream");
        break label316;
      }
      return JSONUpdatableItem.ReturnState.STATE_FAIL;
    }
    catch (JSONException paramJSONObject)
    {
      System.out.println("ERROR: Fail to update stream with dictionary because of invalid JSON: " + paramJSONObject);
    }
  }
  
  private static class DefaultStreamSelector
    implements StreamSelector
  {
    public Stream bestStream(Set<Stream> paramSet, boolean paramBoolean)
    {
      if ((paramSet == null) || (paramSet.size() == 0))
      {
        paramSet = null;
        return paramSet;
      }
      Object localObject = null;
      Iterator localIterator = paramSet.iterator();
      for (;;)
      {
        if (!localIterator.hasNext()) {
          return localObject;
        }
        Stream localStream = (Stream)localIterator.next();
        paramSet = localStream;
        if (localStream.getDeliveryType().equals("remote_asset")) {
          break;
        }
        paramSet = localStream;
        if (localStream.getDeliveryType().equals("hls")) {
          break;
        }
        if ((Stream.isDeliveryTypePlayable(localStream)) && (Stream.isProfilePlayable(localStream)) && ((localObject == null) || (localStream.betterThan((Stream)localObject, paramBoolean)))) {
          localObject = localStream;
        }
      }
      return (Stream)localObject;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\item\Stream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */