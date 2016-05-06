package com.ooyala.android.player;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.widget.FrameLayout;
import com.ooyala.android.Environment;
import com.ooyala.android.OoyalaAPIClient;
import com.ooyala.android.OoyalaException;
import com.ooyala.android.OoyalaException.OoyalaErrorCode;
import com.ooyala.android.OoyalaPlayer;
import com.ooyala.android.OoyalaPlayer.SeekStyle;
import com.ooyala.android.OoyalaPlayer.State;
import com.ooyala.android.item.Stream;
import com.ooyala.android.util.DebugMode;
import com.widevine.drmapi.android.WVEvent;
import com.widevine.drmapi.android.WVEventListener;
import com.widevine.drmapi.android.WVPlayback;
import com.widevine.drmapi.android.WVStatus;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WidevineLibPlayer
  extends MoviePlayer
  implements Handler.Callback, WVEventListener
{
  private static final int ERROR = -1;
  private static final int INIT = 0;
  private Handler _handler = new Handler(this);
  private Stream _stream = null;
  private WVPlayback _wvplayback = new WVPlayback();
  
  private void initializeWidevine()
  {
    HashMap localHashMap = new HashMap();
    String str = Environment.DRM_HOST + String.format("/sas/drm2/%s/%s/%s/%s", new Object[] { this._parent.getOoyalaAPIClient().getPcode(), this._parent.getEmbedCode(), "widevine", "ooyala" });
    if (this._stream.getWidevineServerPath() != null) {
      str = this._stream.getWidevineServerPath();
    }
    localHashMap.put("WVPortalKey", "ooyala");
    localHashMap.put("WVDRMServer", str);
    localHashMap.put("WVLicenseTypeKey", Integer.valueOf(3));
    if (this._wvplayback.initializeSynchronous(this._parent.getLayout().getContext(), localHashMap, this) == WVStatus.AlreadyInitialized)
    {
      this._wvplayback.terminateSynchronous();
      this._wvplayback.initializeSynchronous(this._parent.getLayout().getContext(), localHashMap, this);
    }
    this._handler.sendEmptyMessage(0);
  }
  
  public void destroy()
  {
    this._wvplayback.terminate();
    super.destroy();
  }
  
  public OoyalaPlayer.SeekStyle getSeekStyle()
  {
    return OoyalaPlayer.SeekStyle.BASIC;
  }
  
  public boolean handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    }
    for (;;)
    {
      return true;
      setState(OoyalaPlayer.State.ERROR);
    }
  }
  
  public void init(OoyalaPlayer paramOoyalaPlayer, Set<Stream> paramSet)
  {
    this._stream = null;
    if (Stream.streamSetContainsDeliveryType(paramSet, "wv_mp4")) {
      this._stream = Stream.getStreamWithDeliveryType(paramSet, "wv_mp4");
    }
    if (this._stream == null)
    {
      DebugMode.logE("Widevine", "No available streams for the WidevineLib Player, Cannot continue." + paramSet.toString());
      this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_PLAYBACK_FAILED, "Invalid Stream");
      setState(OoyalaPlayer.State.ERROR);
      return;
    }
    this._parent = paramOoyalaPlayer;
    initializeWidevine();
  }
  
  public WVStatus onEvent(WVEvent paramWVEvent, HashMap<String, Object> paramHashMap)
  {
    DebugMode.logD("Widevine", paramWVEvent.toString() + ": " + paramHashMap.toString());
    switch (paramWVEvent)
    {
    default: 
      return WVStatus.OK;
    case ???: 
      if (this._error == null) {
        this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_PLAYBACK_FAILED, "Widevine Initialization Failed");
      }
    case ???: 
      if (this._error == null) {
        this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_PLAYBACK_FAILED, "Widevine License Request Failed");
      }
    case ???: 
      if (this._error == null) {
        this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_PLAYBACK_FAILED, "Widevine Playback Failed");
      }
      this._handler.sendEmptyMessage(-1);
      if (paramHashMap.containsKey("WVStatusKey")) {
        return (WVStatus)paramHashMap.get("WVStatusKey");
      }
      return WVStatus.OK;
    case ???: 
      this._wvplayback.registerAsset(this._stream.decodedURL().toString());
      this._wvplayback.requestLicense(this._stream.decodedURL().toString());
      this._stream.setUrl(this._wvplayback.play(this._stream.decodedURL().toString()));
      this._stream.setUrlFormat("text");
      paramWVEvent = new HashSet();
      paramWVEvent.add(this._stream);
      super.init(this._parent, paramWVEvent);
    }
    return WVStatus.OK;
  }
  
  public void resume()
  {
    super.resume();
  }
  
  public void resume(int paramInt, OoyalaPlayer.State paramState)
  {
    initializeWidevine();
    super.resume(paramInt, paramState);
  }
  
  public void suspend()
  {
    super.suspend();
  }
  
  public void suspend(int paramInt, OoyalaPlayer.State paramState)
  {
    this._wvplayback.terminateSynchronous();
    super.suspend(paramInt, paramState);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\player\WidevineLibPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */