package com.ooyala.android.player;

import android.annotation.TargetApi;
import android.content.Context;
import android.drm.DrmErrorEvent;
import android.drm.DrmEvent;
import android.drm.DrmInfoEvent;
import android.drm.DrmInfoRequest;
import android.drm.DrmManagerClient;
import android.drm.DrmManagerClient.OnErrorListener;
import android.drm.DrmManagerClient.OnEventListener;
import android.drm.DrmManagerClient.OnInfoListener;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.Secure;
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
import java.net.URL;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

@TargetApi(11)
public class WidevineOsPlayer
  extends MoviePlayer
  implements DrmManagerClient.OnErrorListener, DrmManagerClient.OnEventListener, DrmManagerClient.OnInfoListener, WidevineStuckMonitor.Listener
{
  private static final String TAG = "WidevineOsPlayer";
  private static DrmManagerClient _drmClient;
  private boolean _live = false;
  private WidevineStuckMonitor _stuckMonitor;
  private boolean isSeeking = false;
  
  private static String eventToString(DrmEvent paramDrmEvent)
  {
    switch (paramDrmEvent.getType())
    {
    default: 
      return "";
    case 1001: 
      return "All Rights Removed";
    case 1002: 
      return "DRM Info Processed";
    case 5: 
      return "Account Already Registered";
    case 1: 
      return "Already Registered by Another Account";
    case 2: 
      return "Remove Rights";
    case 3: 
      return "Rights Installed";
    case 6: 
      return "Rights Removed";
    case 4: 
      return "Wait for Rights";
    case 2008: 
      return "Acquire DRM Info Failed";
    case 2005: 
      return "No Internet Connection";
    case 2003: 
      return "Type Not Supported";
    case 2004: 
      return "Out of Memory";
    case 2006: 
      return "DRM Info Request Failed";
    case 2007: 
      return "Remove All Rights Failed";
    case 2001: 
      return "Rights Not Installed";
    }
    return "Rights Renewal Not Allowed";
  }
  
  public void destroy()
  {
    if (this._stuckMonitor != null) {
      this._stuckMonitor.destroy();
    }
    super.destroy();
  }
  
  public OoyalaPlayer.SeekStyle getSeekStyle()
  {
    return OoyalaPlayer.SeekStyle.BASIC;
  }
  
  public void init(OoyalaPlayer paramOoyalaPlayer, Set<Stream> paramSet)
  {
    Stream localStream = null;
    if (Stream.streamSetContainsDeliveryType(paramSet, "wv_wvm")) {
      localStream = Stream.getStreamWithDeliveryType(paramSet, "wv_wvm");
    }
    while (localStream == null)
    {
      DebugMode.logE("WidevineOsPlayer", "No available streams for the Widevine Lib Player, Cannot continue. " + paramSet.toString());
      this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_PLAYBACK_FAILED, "Invalid Stream");
      setState(OoyalaPlayer.State.ERROR);
      return;
      if (Stream.streamSetContainsDeliveryType(paramSet, "wv_hls")) {
        localStream = Stream.getStreamWithDeliveryType(paramSet, "wv_hls");
      }
    }
    if (_drmClient == null)
    {
      _drmClient = new DrmManagerClient(paramOoyalaPlayer.getLayout().getContext());
      _drmClient.setOnErrorListener(this);
      _drmClient.setOnEventListener(this);
      _drmClient.setOnInfoListener(this);
    }
    paramSet = localStream.decodedURL();
    if (paramSet == null)
    {
      DebugMode.logE("WidevineOsPlayer", "Invalid stream, Malformed URL, Cannot continue. URL: " + localStream.getUrl());
      this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_PLAYBACK_FAILED, "Invalid Stream");
      setState(OoyalaPlayer.State.ERROR);
      return;
    }
    paramSet = Uri.parse(paramSet.toString());
    if (paramSet.getLastPathSegment().endsWith(".m3u8")) {
      this._live = true;
    }
    localStream.setUrl(paramSet.buildUpon().scheme("widevine").build().toString());
    localStream.setUrlFormat("text");
    DrmInfoRequest localDrmInfoRequest = new DrmInfoRequest(3, "video/wvm");
    String str1 = Environment.DRM_HOST + String.format("/sas/drm2/%s/%s/%s/%s", new Object[] { paramOoyalaPlayer.getOoyalaAPIClient().getPcode(), paramOoyalaPlayer.getEmbedCode(), "widevine", "ooyala" });
    if (localStream.getWidevineServerPath() != null) {
      paramSet = localStream.getWidevineServerPath();
    }
    for (;;)
    {
      localDrmInfoRequest.put("WVDRMServerKey", paramSet);
      localDrmInfoRequest.put("WVAssetURIKey", localStream.getUrl());
      localDrmInfoRequest.put("WVPortalKey", "ooyala");
      localDrmInfoRequest.put("WVDeviceIDKey", Settings.Secure.getString(paramOoyalaPlayer.getLayout().getContext().getContentResolver(), "android_id"));
      localDrmInfoRequest.put("WVLicenseTypeKey", "3");
      _drmClient.acquireRights(localDrmInfoRequest);
      paramSet = new HashSet();
      paramSet.add(localStream);
      super.init(paramOoyalaPlayer, paramSet);
      this._stuckMonitor = new WidevineStuckMonitor(paramOoyalaPlayer, this, this);
      return;
      String str2 = paramOoyalaPlayer.getAuthToken();
      paramSet = str1;
      if (str2 != null)
      {
        paramSet = str1;
        if (!str2.equals("")) {
          paramSet = str1 + "?auth_token=" + str2;
        }
      }
    }
  }
  
  public void onError(DrmManagerClient paramDrmManagerClient, DrmErrorEvent paramDrmErrorEvent)
  {
    DebugMode.logD("WidevineOsPlayer", "WidevineError: " + eventToString(paramDrmErrorEvent));
    this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_PLAYBACK_FAILED, Integer.toString(paramDrmErrorEvent.getType()));
    new Handler(Looper.getMainLooper()).post(new Runnable()
    {
      public void run()
      {
        WidevineOsPlayer.this.setState(OoyalaPlayer.State.ERROR);
      }
    });
  }
  
  public void onEvent(DrmManagerClient paramDrmManagerClient, DrmEvent paramDrmEvent)
  {
    DebugMode.logD("WidevineOsPlayer", "WidevineEvent: " + eventToString(paramDrmEvent));
  }
  
  public void onFrozen()
  {
    DebugMode.logV("WidevineOsPlayer", "onFrozen(): posting the runnable");
    new Handler(Looper.getMainLooper()).post(new Runnable()
    {
      public void run()
      {
        DebugMode.logV("WidevineOsPlayer", "onFrozen(): running the runnable");
        if (WidevineOsPlayer.this.getState() != OoyalaPlayer.State.ERROR)
        {
          WidevineOsPlayer.this.setState(OoyalaPlayer.State.COMPLETED);
          WidevineOsPlayer.this._stuckMonitor.reset();
        }
      }
    });
  }
  
  public void onInfo(DrmManagerClient paramDrmManagerClient, DrmInfoEvent paramDrmInfoEvent)
  {
    DebugMode.logD("WidevineOsPlayer", "WidevineInfoEvent: " + eventToString(paramDrmInfoEvent));
  }
  
  public void seekToTime(int paramInt)
  {
    if (this._live) {
      return;
    }
    if (!this.isSeeking)
    {
      DebugMode.logD("WidevineOsPlayer", "Seek started. Disabling seeking");
      super.seekToTime(paramInt);
      this.isSeeking = true;
      return;
    }
    DebugMode.logI("WidevineOsPlayer", "Trying to seek while already seeking, dropping the incoming seek");
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    if (paramObject == "seekCompleted")
    {
      this.isSeeking = false;
      DebugMode.logD("WidevineOsPlayer", "Seek completed. Re-enabling seeking");
    }
    super.update(paramObservable, paramObject);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\player\WidevineOsPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */