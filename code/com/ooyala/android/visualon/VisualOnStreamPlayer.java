package com.ooyala.android.visualon;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.View.MeasureSpec;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.ooyala.android.ID3TagNotifier;
import com.ooyala.android.OoyalaAPIClient;
import com.ooyala.android.OoyalaException;
import com.ooyala.android.OoyalaException.OoyalaErrorCode;
import com.ooyala.android.OoyalaPlayer;
import com.ooyala.android.OoyalaPlayer.SeekStyle;
import com.ooyala.android.OoyalaPlayer.State;
import com.ooyala.android.apis.AuthorizeCallback;
import com.ooyala.android.configuration.ReadonlyOptionsInterface;
import com.ooyala.android.configuration.VisualOnConfiguration;
import com.ooyala.android.item.Stream;
import com.ooyala.android.player.ControlSharingSurfaceView;
import com.ooyala.android.player.StreamPlayer;
import com.ooyala.android.util.DebugMode;
import com.visualon.OSMPPlayer.VOCommonPlayer;
import com.visualon.OSMPPlayer.VOCommonPlayerAssetSelection;
import com.visualon.OSMPPlayer.VOCommonPlayerAssetSelection.VOOSMPAssetIndex;
import com.visualon.OSMPPlayer.VOCommonPlayerAssetSelection.VOOSMPAssetProperty;
import com.visualon.OSMPPlayer.VOCommonPlayerListener;
import com.visualon.OSMPPlayer.VOCommonPlayerListener.VO_OSMP_CB_EVENT_ID;
import com.visualon.OSMPPlayer.VOCommonPlayerListener.VO_OSMP_CB_SYNC_EVENT_ID;
import com.visualon.OSMPPlayer.VOCommonPlayerListener.VO_OSMP_SRC_CUSTOMERTAGID;
import com.visualon.OSMPPlayer.VOOSMPInitParam;
import com.visualon.OSMPPlayer.VOOSMPOpenParam;
import com.visualon.OSMPPlayer.VOOSMPType.VO_OSMP_MODULE_TYPE;
import com.visualon.OSMPPlayer.VOOSMPType.VO_OSMP_PLAYER_ENGINE;
import com.visualon.OSMPPlayer.VOOSMPType.VO_OSMP_RETURN_CODE;
import com.visualon.OSMPPlayer.VOOSMPType.VO_OSMP_SRC_FLAG;
import com.visualon.OSMPPlayer.VOOSMPType.VO_OSMP_SRC_FORMAT;
import com.visualon.OSMPPlayer.VOOSMPType.VO_OSMP_STATUS;
import com.visualon.OSMPPlayerImpl.VOCommonPlayerImpl;
import com.visualon.OSMPSubTitle.voSubTitleManager.voSubtitleDisplayInfo;
import com.visualon.OSMPSubTitle.voSubTitleManager.voSubtitleInfo;
import com.visualon.OSMPSubTitle.voSubTitleManager.voSubtitleInfoEntry;
import com.visualon.OSMPSubTitle.voSubTitleManager.voSubtitleTextInfoEntry;
import com.visualon.OSMPSubTitle.voSubTitleManager.voSubtitleTextRowInfo;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class VisualOnStreamPlayer
  extends StreamPlayer
  implements SurfaceHolder.Callback, AcquireRightsCallback, FileDownloadCallback, PersonalizationCallback, VOCommonPlayerListener
{
  private static final String DISCREDIX_MANAGER_CLASS = "com.discretix.drmdlc.api.DxDrmDlc";
  private static final boolean ENABLE_DEBUGGING = false;
  private static final String EXPECTED_SECUREPLAYER_VO_VERSION = "3.17.3-B83848";
  private static final String EXPECTED_VISUALON_VERSION = "3.17.3-B84015";
  private static final boolean EXTREME_DEBUGGING = false;
  private static final String TAG = "VisualOnStreamPlayer";
  protected static final long TIMER_DELAY = 0L;
  protected static final long TIMER_PERIOD = 1000L;
  private static boolean didCleanupLocalFiles = false;
  private boolean _completedQueued = false;
  protected SurfaceHolder _holder = null;
  protected boolean _isDiscredixLoaded = false;
  private boolean _isLiveClosedCaptionsAvailable = false;
  private boolean _isLiveClosedCaptionsEnabled = false;
  private int _lastPlayhead = 0;
  private String _localFilePath;
  private boolean _playQueued = false;
  protected VOCommonPlayer _player = null;
  protected Timer _playheadUpdateTimer = null;
  private final Handler _playheadUpdateTimerHandler = new Handler(new Handler.Callback()
  {
    public boolean handleMessage(Message paramAnonymousMessage)
    {
      VisualOnStreamPlayer.this.setChanged();
      VisualOnStreamPlayer.this.notifyObservers("timeChanged");
      return false;
    }
  });
  private int _selectedSubtitleIndex = 0;
  private OoyalaPlayer.State _stateBeforeSuspend = OoyalaPlayer.State.INIT;
  Stream _stream = null;
  protected String _streamUrl = "";
  private List<String> _subtitleDescriptions = null;
  boolean _surfaceExists = false;
  private Integer _timeBeforeSuspend = null;
  protected int _videoHeight = 9;
  protected int _videoWidth = 16;
  private VisualOnConfiguration _visualOnConfiguration = null;
  
  private void _handleSubtitles()
  {
    if (this._player == null) {
      DebugMode.logE("VisualOnStreamPlayer", "handleSubtitles: player is null");
    }
    Object localObject;
    do
    {
      VOCommonPlayer localVOCommonPlayer;
      do
      {
        return;
        applySubtitleSettings();
        localVOCommonPlayer = this._player;
        int j = localVOCommonPlayer.getSubtitleCount();
        this._subtitleDescriptions.clear();
        this._isLiveClosedCaptionsAvailable = false;
        int i = 0;
        if (i < j)
        {
          localObject = localVOCommonPlayer.getSubtitleProperty(i);
          if (((VOCommonPlayerAssetSelection.VOOSMPAssetProperty)localObject).getPropertyCount() == 0) {}
          for (localObject = "CC" + String.valueOf(i);; localObject = (String)((VOCommonPlayerAssetSelection.VOOSMPAssetProperty)localObject).getValue(1))
          {
            if (localVOCommonPlayer.isSubtitleAvailable(i)) {
              this._isLiveClosedCaptionsAvailable = true;
            }
            this._subtitleDescriptions.add(localObject);
            i += 1;
            break;
          }
        }
        i = localVOCommonPlayer.getPlayingAsset().getSubtitleIndex();
        DebugMode.logD("VisualOnStreamPlayer", "handleSubtitles: selected subtitle " + i);
      } while ((!this._isLiveClosedCaptionsEnabled) || (!this._isLiveClosedCaptionsAvailable));
      localObject = localVOCommonPlayer.selectSubtitle(this._selectedSubtitleIndex);
    } while (localObject == VOOSMPType.VO_OSMP_RETURN_CODE.VO_OSMP_ERR_NONE);
    DebugMode.logD("VisualOnStreamPlayer", "handleSubtitles: selectSubtitle(" + String.valueOf(this._selectedSubtitleIndex) + ") failed with error: " + ((VOOSMPType.VO_OSMP_RETURN_CODE)localObject).toString());
  }
  
  private void applySubtitleSettings()
  {
    if (this._player == null) {
      DebugMode.logE("VisualOnStreamPlayer", "enableSubtitles: player is null");
    }
    VOOSMPType.VO_OSMP_RETURN_CODE localVO_OSMP_RETURN_CODE;
    do
    {
      return;
      localVO_OSMP_RETURN_CODE = this._player.enableSubtitle(this._isLiveClosedCaptionsEnabled);
    } while (localVO_OSMP_RETURN_CODE == VOOSMPType.VO_OSMP_RETURN_CODE.VO_OSMP_ERR_NONE);
    DebugMode.logE("VisualOnStreamPlayer", "enable subtitles(" + String.valueOf(this._isLiveClosedCaptionsEnabled) + ") failed with error:" + localVO_OSMP_RETURN_CODE.toString());
  }
  
  private boolean checkForDiscredixLibrary(Context paramContext)
  {
    try
    {
      getClass().getClassLoader().loadClass("com.discretix.drmdlc.api.DxDrmDlc");
      DebugMode.logD("VisualOnStreamPlayer", "This app has the ability to play protected content");
      return true;
    }
    catch (Exception paramContext)
    {
      DebugMode.logD("VisualOnStreamPlayer", "This app cannot play protected content");
    }
    return false;
  }
  
  private void currentItemCompleted()
  {
    stopPlayheadTimer();
    setState(OoyalaPlayer.State.COMPLETED);
  }
  
  private void dequeueAll()
  {
    dequeueCompleted();
    dequeuePlay();
  }
  
  private void dequeueCompleted()
  {
    if (this._completedQueued)
    {
      this._playQueued = false;
      this._completedQueued = false;
      setState(OoyalaPlayer.State.COMPLETED);
    }
  }
  
  private void dequeuePlay()
  {
    if (this._playQueued) {}
    switch (6.$SwitchMap$com$ooyala$android$OoyalaPlayer$State[getState().ordinal()])
    {
    default: 
      return;
    }
    this._playQueued = false;
    play();
  }
  
  private void handleSubtitles()
  {
    boolean bool = this._isLiveClosedCaptionsAvailable;
    _handleSubtitles();
    if (bool != this._isLiveClosedCaptionsAvailable)
    {
      setChanged();
      notifyObservers("liveCCAvailabilityChanged");
    }
  }
  
  private void handle_VO_OSMP_SRC_CB_CUSTOMER_TAG(VOCommonPlayerListener.VO_OSMP_CB_EVENT_ID paramVO_OSMP_CB_EVENT_ID, int paramInt1, int paramInt2, Object paramObject)
  {
    paramVO_OSMP_CB_EVENT_ID = VOCommonPlayerListener.VO_OSMP_SRC_CUSTOMERTAGID.valueOf(paramInt1);
    switch (paramVO_OSMP_CB_EVENT_ID)
    {
    default: 
      return;
    }
    paramVO_OSMP_CB_EVENT_ID = (byte[])paramObject;
    DebugMode.logV("VisualOnStreamPlayer", "tag: time=" + paramInt2 + ", bytes=" + paramVO_OSMP_CB_EVENT_ID + ", string=" + new String(paramVO_OSMP_CB_EVENT_ID));
    ID3TagNotifier.s_getInstance().onTag(paramVO_OSMP_CB_EVENT_ID);
  }
  
  private boolean isPlayerValid()
  {
    return (this._player != null) && (this._player.getPlayerStatus() != VOOSMPType.VO_OSMP_STATUS.VO_OSMP_STATUS_STOPPED);
  }
  
  private void queueCompleted()
  {
    this._completedQueued = true;
  }
  
  private void queuePlay()
  {
    this._playQueued = true;
  }
  
  private void removeView()
  {
    this._parent.removeVideoView();
    if (this._holder != null) {
      this._holder.removeCallback(this);
    }
    this._view = null;
    this._holder = null;
  }
  
  private void resetPlayerState()
  {
    this._buffer = 0;
    this._lastPlayhead = 0;
    this._playQueued = false;
  }
  
  private void setVisualOnConfigurations()
  {
    if (this._visualOnConfiguration != null)
    {
      this._player.setInitialBitrate(this._visualOnConfiguration.getInitialBitrate());
      this._player.setMaxBufferingTime(this._visualOnConfiguration.getMaxBufferingTime());
      this._player.setBitrateThreshold(this._visualOnConfiguration.getUpperBitrateThreshold(), this._visualOnConfiguration.getLowerBitrateThreshold());
      this._player.setInitialBufferingTime(this._visualOnConfiguration.getInitialBufferingTime());
      this._player.setPlaybackBufferingTime(this._visualOnConfiguration.getPlaybackBufferingTime());
    }
  }
  
  private void setupView()
  {
    if (this._view != null)
    {
      DebugMode.logE("VisualOnStreamPlayer", "DANGER DANGER: setupView while we still have a view");
      return;
    }
    this._view = new ControlSharingSurfaceView(this._parent.getOptions().getPreventVideoViewSharing(), this._parent.getLayout().getContext())
    {
      protected void onMeasure(int paramAnonymousInt1, int paramAnonymousInt2)
      {
        DebugMode.logV("VisualOnStreamPlayer", "Remeasuring Surface: " + View.MeasureSpec.toString(paramAnonymousInt1) + "," + View.MeasureSpec.toString(paramAnonymousInt2));
        int i = View.MeasureSpec.getSize(paramAnonymousInt1);
        int j = View.MeasureSpec.getSize(paramAnonymousInt2);
        paramAnonymousInt2 = i;
        int k = VisualOnStreamPlayer.this._videoHeight * paramAnonymousInt2 / VisualOnStreamPlayer.this._videoWidth;
        paramAnonymousInt1 = k;
        if ((j - k) / 2 < 0)
        {
          paramAnonymousInt1 = j;
          paramAnonymousInt2 = VisualOnStreamPlayer.this._videoWidth * j / VisualOnStreamPlayer.this._videoHeight;
          i = (i - paramAnonymousInt2) / 2;
        }
        setMeasuredDimension(paramAnonymousInt2, paramAnonymousInt1);
        DebugMode.logV("VisualOnStreamPlayer", "Surface remeasured to: " + paramAnonymousInt2 + "," + paramAnonymousInt1);
      }
    };
    this._parent.addVideoView(this._view);
    this._view.setBackgroundColor(0);
    this._holder = this._view.getHolder();
    this._holder.addCallback(this);
    this._holder.setFormat(1);
  }
  
  private boolean shouldLoadPlayreadyPlayer()
  {
    return (this._isDiscredixLoaded) && (OoyalaPlayer.enableCustomPlayreadyPlayer);
  }
  
  private void suspend(int paramInt, OoyalaPlayer.State paramState)
  {
    DebugMode.logV("VisualOnStreamPlayer", "Player Suspend");
    if (getState() == OoyalaPlayer.State.SUSPENDED) {
      return;
    }
    if (this._player != null)
    {
      this._timeBeforeSuspend = Integer.valueOf(paramInt);
      this._stateBeforeSuspend = paramState;
      destroyBasePlayer();
    }
    removeView();
    resetPlayerState();
    setState(OoyalaPlayer.State.SUSPENDED);
  }
  
  public static void warmDxDrmDlc(Context paramContext)
  {
    DiscredixDrmUtils.warmDxDrmDlc(paramContext);
  }
  
  public String GetCCString(voSubTitleManager.voSubtitleInfo paramvoSubtitleInfo)
  {
    Object localObject2;
    if (paramvoSubtitleInfo == null)
    {
      localObject2 = "";
      return (String)localObject2;
    }
    if (paramvoSubtitleInfo.getSubtitleEntry() == null) {
      return "";
    }
    Object localObject1 = "";
    int i = 0;
    for (;;)
    {
      localObject2 = localObject1;
      if (i >= paramvoSubtitleInfo.getSubtitleEntry().size()) {
        break;
      }
      voSubTitleManager.voSubtitleDisplayInfo localvoSubtitleDisplayInfo = ((voSubTitleManager.voSubtitleInfoEntry)paramvoSubtitleInfo.getSubtitleEntry().get(i)).getSubtitleDispInfo();
      localObject2 = localObject1;
      if (localvoSubtitleDisplayInfo.getTextRowInfo() != null)
      {
        int j = 0;
        localObject2 = localObject1;
        if (j < localvoSubtitleDisplayInfo.getTextRowInfo().size())
        {
          voSubTitleManager.voSubtitleTextRowInfo localvoSubtitleTextRowInfo = (voSubTitleManager.voSubtitleTextRowInfo)localvoSubtitleDisplayInfo.getTextRowInfo().get(j);
          Object localObject3;
          if (localvoSubtitleTextRowInfo == null) {
            localObject3 = localObject1;
          }
          for (;;)
          {
            j += 1;
            localObject1 = localObject3;
            break;
            localObject3 = localObject1;
            if (localvoSubtitleTextRowInfo.getTextInfoEntry() != null)
            {
              localObject2 = "";
              int k = 0;
              while (k < localvoSubtitleTextRowInfo.getTextInfoEntry().size())
              {
                localObject2 = (String)localObject2 + ((voSubTitleManager.voSubtitleTextInfoEntry)localvoSubtitleTextRowInfo.getTextInfoEntry().get(k)).getStringText();
                k += 1;
              }
              localObject3 = localObject1;
              if (((String)localObject2).length() > 0)
              {
                localObject3 = localObject1;
                if (((String)localObject1).length() > 0) {
                  localObject3 = (String)localObject1 + "\n";
                }
                localObject3 = (String)localObject3 + (String)localObject2;
              }
            }
          }
        }
      }
      i += 1;
      localObject1 = localObject2;
    }
  }
  
  public void afterAcquireRights(Exception paramException)
  {
    setChanged();
    notifyObservers("drmRightsAcquireCompleted");
    if (paramException != null)
    {
      DebugMode.logE("VisualOnStreamPlayer", "Acquire Rights failed: " + paramException.getClass());
      this._error = DiscredixDrmUtils.handleDRMError(paramException);
      setState(OoyalaPlayer.State.ERROR);
      return;
    }
    DebugMode.logD("VisualOnStreamPlayer", "Acquire Rights successful");
    createMediaPlayer();
  }
  
  public void afterFileDownload(String paramString)
  {
    this._localFilePath = paramString;
    if (this._localFilePath == null)
    {
      DebugMode.logE("VisualOnStreamPlayer", "File Download failed!");
      this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_DRM_FILE_DOWNLOAD_FAILED);
      setState(OoyalaPlayer.State.ERROR);
      return;
    }
    if ((this._isDiscredixLoaded) && (DiscredixDrmUtils.isStreamProtected(this._parent.getLayout().getContext(), this._localFilePath)))
    {
      DebugMode.logD("VisualOnStreamPlayer", "File Download Succeeded: Need to acquire rights");
      new PersonalizationAsyncTask(this, this._parent.getLayout().getContext(), this._parent.getOoyalaAPIClient().getPcode(), this._visualOnConfiguration.getPersonalizationServerUrl()).execute(new Void[0]);
      return;
    }
    DebugMode.logD("VisualOnStreamPlayer", "File Download Succeeded: No rights needed");
    createMediaPlayer();
  }
  
  public void afterPersonalization(Exception paramException)
  {
    if (!this._isDiscredixLoaded)
    {
      DebugMode.logE("VisualOnStreamPlayer", "Personalzied without Discredix loaded");
      this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_PLAYBACK_FAILED, "Personalzied without Discredix loaded");
      setState(OoyalaPlayer.State.ERROR);
      return;
    }
    if (paramException != null)
    {
      DebugMode.logE("VisualOnStreamPlayer", "Personalization resulted in an exception! " + paramException);
      this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_DRM_GENERAL_FAILURE, paramException);
      setState(OoyalaPlayer.State.ERROR);
      return;
    }
    if (!DiscredixDrmUtils.isDevicePersonalized(this._parent.getLayout().getContext()))
    {
      DebugMode.logE("VisualOnStreamPlayer", "Personalization failed");
      this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_DRM_PERSONALIZATION_FAILED, "Personalization Failed");
      setState(OoyalaPlayer.State.ERROR);
      return;
    }
    DebugMode.logD("VisualOnStreamPlayer", "Personalization successful");
    tryToAcquireRights();
  }
  
  public int buffer()
  {
    return this._buffer;
  }
  
  protected void createMediaPlayer()
  {
    Object localObject3 = this._parent.getLayout().getContext();
    try
    {
      if (!this._surfaceExists)
      {
        DebugMode.logE("VisualOnStreamPlayer", "Trying to create a player without a valid surface");
        return;
      }
      if ((shouldLoadPlayreadyPlayer()) && (!DiscredixDrmUtils.canFileBePlayed((Context)localObject3, this._stream, this._localFilePath)))
      {
        DebugMode.logE("VisualOnStreamPlayer", "File cannot be played yet, we haven't gotten rights yet");
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      return;
    }
    DebugMode.logD("VisualOnStreamPlayer", "File can be played, surface created. Creating media player");
    Object localObject1;
    Object localObject4;
    if (this._player != null)
    {
      DebugMode.logE("VisualOnStreamPlayer", "DANGER: Creating a Media player when one already exists");
      localObject1 = VOOSMPType.VO_OSMP_PLAYER_ENGINE.VO_OSMP_VOME2_PLAYER;
      localObject2 = ((Context)localObject3).getFilesDir().getParentFile().getPath() + "/lib/";
      if (this._isDiscredixLoaded) {
        DiscredixDrmUtils.warmDxDrmDlc((Context)localObject3);
      }
      localObject4 = new VOOSMPInitParam();
      ((VOOSMPInitParam)localObject4).setLibraryPath((String)localObject2);
      ((VOOSMPInitParam)localObject4).setContext(localObject3);
      localObject1 = this._player.init((VOOSMPType.VO_OSMP_PLAYER_ENGINE)localObject1, (VOOSMPInitParam)localObject4);
      if (localObject1 != VOOSMPType.VO_OSMP_RETURN_CODE.VO_OSMP_ERR_NONE) {
        break label442;
      }
      DebugMode.logV("VisualOnStreamPlayer", "MediaPlayer is created.");
      localObject4 = this._player.getVersion(VOOSMPType.VO_OSMP_MODULE_TYPE.VO_OSMP_MODULE_TYPE_SDK);
      DebugMode.logI("VisualOnStreamPlayer", "VisualOn Version: " + (String)localObject4);
      if (!this._isDiscredixLoaded) {
        break label884;
      }
      localObject1 = "3.17.3-B83848";
      label233:
      if (!this._isDiscredixLoaded) {
        break label890;
      }
    }
    label442:
    label587:
    label884:
    label890:
    String str;
    for (Object localObject2 = "SecurePlayer";; str = "VisualOn")
    {
      if (((String)localObject1).compareTo((String)localObject4) != 0)
      {
        DebugMode.logE("VisualOnStreamPlayer", (String)localObject2 + " Version was not expected! Expected: " + (String)localObject1 + ", Actual: " + (String)localObject4);
        DebugMode.logE("VisualOnStreamPlayer", "Please ask your CSM for updated versions of the " + (String)localObject2 + " libraries");
        if (!this._visualOnConfiguration.getDisableLibraryVersionChecks())
        {
          localObject1 = new Handler(Looper.getMainLooper());
          localObject3 = new Runnable()
          {
            public void run()
            {
              VisualOnStreamPlayer.this.setState(OoyalaPlayer.State.ERROR);
            }
          };
          this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_PLAYBACK_FAILED, (String)localObject2 + " Initialization error: Unexpected VisualOn Player Version");
          ((Handler)localObject1).post((Runnable)localObject3);
          return;
          if (shouldLoadPlayreadyPlayer())
          {
            DebugMode.logD("VisualOnStreamPlayer", "Using VODXPlayer");
            this._player = DiscredixDrmUtils.getVODXPlayerImpl();
            break;
          }
          DebugMode.logD("VisualOnStreamPlayer", "Using VOCommonPlayer");
          this._player = new VOCommonPlayerImpl();
          break;
          onError(this._player, (VOOSMPType.VO_OSMP_RETURN_CODE)localObject1, 0);
          return;
        }
        DebugMode.logE("VisualOnStreamPlayer", "Disabled Library version checks. Attempting to continue playback");
      }
      for (;;)
      {
        localObject1 = new DisplayMetrics();
        ((WindowManager)this._view.getContext().getSystemService("window")).getDefaultDisplay().getMetrics((DisplayMetrics)localObject1);
        this._player.setViewSize(Math.max(((DisplayMetrics)localObject1).widthPixels, ((DisplayMetrics)localObject1).heightPixels), Math.max(((DisplayMetrics)localObject1).widthPixels, ((DisplayMetrics)localObject1).heightPixels));
        this._player.setView(this._view);
        this._player.setOnEventListener(this);
        setVisualOnConfigurations();
        if (shouldLoadPlayreadyPlayer())
        {
          DebugMode.logD("VisualOnStreamPlayer", "SecurePlayer: Setting DRM Library: voDRM_Discretix_PlayReady");
          this._player.setDRMLibrary("voDRM_Discretix_PlayReady", "voGetDXDRMAPI");
          this._player.setPreAgreedLicense("VOTRUST_OOYALA_754321974");
          localObject1 = new byte[32768];
        }
        try
        {
          localObject2 = this._view.getContext().getAssets().open("voVidDec.dat");
          ((InputStream)localObject2).read((byte[])localObject1);
          ((InputStream)localObject2).close();
          this._player.setLicenseContent((byte[])localObject1);
          this._player.enableAudioEffect(false);
          localObject1 = ((Context)localObject3).getFilesDir().getParentFile().getPath() + "/";
          localObject1 = (String)localObject1 + "cap.xml";
          this._player.setDeviceCapabilityByFile((String)localObject1);
          localObject1 = new VOOSMPOpenParam();
          localObject1 = this._player.open(this._streamUrl, VOOSMPType.VO_OSMP_SRC_FLAG.VO_OSMP_FLAG_SRC_OPEN_ASYNC, VOOSMPType.VO_OSMP_SRC_FORMAT.VO_OSMP_SRC_AUTO_DETECT, (VOOSMPOpenParam)localObject1);
          if (localObject1 == VOOSMPType.VO_OSMP_RETURN_CODE.VO_OSMP_ERR_NONE)
          {
            DebugMode.logV("VisualOnStreamPlayer", "MediaPlayer is Opened.");
            return;
            DebugMode.logI("VisualOnStreamPlayer", (String)localObject2 + " libraries version correct for this SDK version");
            continue;
            if (this._isDiscredixLoaded)
            {
              DebugMode.logD("VisualOnStreamPlayer", "SecurePlayer: Setting DRM Library:  voDRM_VisualOn_AES128");
              this._player.setDRMLibrary("voDRM_VisualOn_AES128", "voGetDRMAPI");
              break label587;
            }
            DebugMode.logD("VisualOnStreamPlayer", "VisualOn-Only: assuming old DRM Library - Setting DRM Library:  voDRM");
            this._player.setDRMLibrary("voDRM", "voGetDRMAPI");
          }
        }
        catch (IOException localIOException)
        {
          for (;;)
          {
            DebugMode.logE("VisualOnStreamPlayer", "Caught!", localIOException);
          }
          DebugMode.logE("VisualOnStreamPlayer", "Could not open VisualOn Player");
          onError(this._player, (VOOSMPType.VO_OSMP_RETURN_CODE)localObject1, 0);
          return;
        }
      }
      localObject1 = "3.17.3-B84015";
      break label233;
    }
  }
  
  public int currentTime()
  {
    if (!isPlayerValid())
    {
      DebugMode.logW("VisualOnStreamPlayer", "No player when asking for time. using last known playhead time");
      return this._lastPlayhead;
    }
    switch (6.$SwitchMap$com$ooyala$android$OoyalaPlayer$State[getState().ordinal()])
    {
    case 2: 
    case 3: 
    case 5: 
    default: 
      return (int)this._player.getPosition();
    }
    if ((this._timeBeforeSuspend != null) && (this._timeBeforeSuspend.intValue() > 0)) {
      return this._timeBeforeSuspend.intValue();
    }
    return 0;
  }
  
  public void destroy()
  {
    destroyBasePlayer();
    removeView();
    resetPlayerState();
    this._timeBeforeSuspend = Integer.valueOf(-1);
    setState(OoyalaPlayer.State.INIT);
  }
  
  public void destroyBasePlayer()
  {
    if (this._player != null)
    {
      stop();
      this._player.destroy();
      this._player.setView(null);
      this._player = null;
    }
  }
  
  public int duration()
  {
    if (this._player == null) {
      return 0;
    }
    switch (6.$SwitchMap$com$ooyala$android$OoyalaPlayer$State[getState().ordinal()])
    {
    }
    return (int)this._player.getDuration();
  }
  
  public OoyalaPlayer.SeekStyle getSeekStyle()
  {
    return OoyalaPlayer.SeekStyle.BASIC;
  }
  
  public void init(OoyalaPlayer paramOoyalaPlayer, Set<Stream> paramSet)
  {
    DebugMode.logD("VisualOnStreamPlayer", "Using VOPlayer");
    this._stream = Stream.bestStream(paramSet, ((WifiManager)paramOoyalaPlayer.getLayout().getContext().getSystemService("wifi")).isWifiEnabled());
    if (this._stream == null)
    {
      DebugMode.logE("VisualOnStreamPlayer", "ERROR: Invalid Stream (no valid stream available)");
      this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_PLAYBACK_FAILED, "Invalid Stream");
      setState(OoyalaPlayer.State.ERROR);
      return;
    }
    if (paramOoyalaPlayer == null)
    {
      DebugMode.logE("VisualOnStreamPlayer", "ERROR: Invalid parent (no parent provided to Stream Player)");
      this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_PLAYBACK_FAILED, "Invalid Parent");
      setState(OoyalaPlayer.State.ERROR);
      return;
    }
    Context localContext = paramOoyalaPlayer.getLayout().getContext();
    this._visualOnConfiguration = paramOoyalaPlayer.getOptions().getVisualOnConfiguration();
    this._isDiscredixLoaded = checkForDiscredixLibrary(localContext);
    StringBuilder localStringBuilder = new StringBuilder();
    if (this._isDiscredixLoaded) {}
    for (paramSet = "Using";; paramSet = "Not using")
    {
      DebugMode.logD("VisualOnStreamPlayer", paramSet + " Discredix");
      setState(OoyalaPlayer.State.LOADING);
      this._streamUrl = this._stream.decodedURL().toString();
      this._subtitleDescriptions = new ArrayList();
      setParent(paramOoyalaPlayer);
      VisualOnUtils.copyFile(localContext, "voVidDec.dat", "voVidDec.dat");
      VisualOnUtils.copyFile(localContext, "cap.xml", "cap.xml");
      if (!didCleanupLocalFiles)
      {
        didCleanupLocalFiles = true;
        VisualOnUtils.cleanupLocalFiles(localContext);
      }
      if ((!this._isDiscredixLoaded) || (this._localFilePath != null)) {
        break label349;
      }
      if (DiscredixDrmUtils.isDiscredixVersionCorrect(localContext)) {
        break label354;
      }
      if (this._visualOnConfiguration.getDisableLibraryVersionChecks()) {
        break;
      }
      this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_PLAYBACK_FAILED, "SecurePlayer Initialization error: Unexpected Discredix Version");
      setState(OoyalaPlayer.State.ERROR);
      return;
    }
    DebugMode.logE("VisualOnStreamPlayer", "Disabled Library version checks. Attempting to continue playback");
    for (;;)
    {
      new FileDownloadAsyncTask(localContext, this, paramOoyalaPlayer.getEmbedCode(), this._streamUrl).execute(new Void[0]);
      label349:
      setupView();
      return;
      label354:
      DebugMode.logI("VisualOnStreamPlayer", "Discredix Version correct for this SDK version");
    }
  }
  
  public boolean isLiveClosedCaptionsAvailable()
  {
    return this._isLiveClosedCaptionsAvailable;
  }
  
  public int livePlayheadPercentage()
  {
    if (this._player != null)
    {
      long l1 = this._player.getMaxPosition();
      long l2 = this._player.getMinPosition();
      return (int)((float)(this._player.getPosition() - l2) / ((float)l1 - (float)l2) * 100.0F);
    }
    return 100;
  }
  
  public boolean onError(VOCommonPlayer paramVOCommonPlayer, VOOSMPType.VO_OSMP_RETURN_CODE paramVO_OSMP_RETURN_CODE, int paramInt)
  {
    this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_PLAYBACK_FAILED, "VisualOn Playback Error: " + paramVO_OSMP_RETURN_CODE + " " + paramInt);
    setState(OoyalaPlayer.State.ERROR);
    return false;
  }
  
  public VOOSMPType.VO_OSMP_RETURN_CODE onVOEvent(VOCommonPlayerListener.VO_OSMP_CB_EVENT_ID paramVO_OSMP_CB_EVENT_ID, int paramInt1, int paramInt2, Object paramObject)
  {
    switch (6.$SwitchMap$com$visualon$OSMPPlayer$VOCommonPlayerListener$VO_OSMP_CB_EVENT_ID[paramVO_OSMP_CB_EVENT_ID.ordinal()])
    {
    }
    for (;;)
    {
      DebugMode.logV("VisualOnStreamPlayer", "VisualOn Message: " + paramVO_OSMP_CB_EVENT_ID + ". param is " + paramInt1 + ", " + paramInt2);
      return VOOSMPType.VO_OSMP_RETURN_CODE.VO_OSMP_ERR_NONE;
      DebugMode.logV("VisualOnStreamPlayer", "OnEvent VO_OSMP_SRC_CB_OPEN_FINISHED");
      setState(OoyalaPlayer.State.READY);
      handleSubtitles();
      continue;
      currentItemCompleted();
      continue;
      if (paramInt1 <= 0)
      {
        setChanged();
        notifyObservers("seekCompleted");
        if (this._player.getPlayerStatus() == VOOSMPType.VO_OSMP_STATUS.VO_OSMP_STATUS_PLAYING)
        {
          setState(OoyalaPlayer.State.PLAYING);
        }
        else
        {
          setState(OoyalaPlayer.State.PAUSED);
          dequeuePlay();
          continue;
          this._buffer = paramInt1;
          continue;
          this._videoWidth = paramInt1;
          this._videoHeight = paramInt2;
          DebugMode.logV("VisualOnStreamPlayer", "onEvent: Video Size Changed, " + this._videoWidth + ", " + this._videoHeight);
          this._view.requestLayout();
          continue;
          DebugMode.logD("VisualOnStreamPlayer", "onEvent: Buffering Done! " + paramInt1 + ", " + paramInt2 + " with current playhead = " + this._player.getPosition() + " and buffer duration = " + this._player.getValidBufferDuration());
          setChanged();
          notifyObservers("bufferingCompleted");
          if (this._player.getPlayerStatus() == VOOSMPType.VO_OSMP_STATUS.VO_OSMP_STATUS_PLAYING)
          {
            setState(OoyalaPlayer.State.PLAYING);
          }
          else
          {
            setState(OoyalaPlayer.State.PAUSED);
            dequeuePlay();
            continue;
            DebugMode.logD("VisualOnStreamPlayer", "onEvent: Buffering Starting " + paramInt1 + ", " + paramInt2);
            setChanged();
            notifyObservers("bufferingStarted");
            setState(OoyalaPlayer.State.LOADING);
            continue;
            DebugMode.logE("VisualOnStreamPlayer", "onEvent: Error. " + paramInt1);
            destroyBasePlayer();
            onError(this._player, null, paramVO_OSMP_CB_EVENT_ID.getValue());
            continue;
            switch (paramInt1)
            {
            }
            for (;;)
            {
              return VOOSMPType.VO_OSMP_RETURN_CODE.VO_OSMP_ERR_NONE;
              DebugMode.logV("VisualOnStreamPlayer", "OnEvent VOOSMP_SRC_ADAPTIVE_STREAMING_INFO_EVENT_BITRATE_CHANGE, param2 is " + paramInt2);
              continue;
              DebugMode.logV("VisualOnStreamPlayer", "OnEvent VOOSMP_SRC_ADAPTIVE_STREAMING_INFO_EVENT_MEDIATYPE_CHANGE, param2 is" + paramInt2);
              switch (paramInt2)
              {
              default: 
                break;
              case 0: 
                DebugMode.logV("VisualOnStreamPlayer", "OnEvent VOOSMP_SRC_ADAPTIVE_STREAMING_INFO_EVENT_MEDIATYPE_CHANGE, VOOSMP_AVAILABLE_PUREAUDIO");
                break;
              case 1: 
                DebugMode.logV("VisualOnStreamPlayer", "OnEvent VOOSMP_SRC_ADAPTIVE_STREAMING_INFO_EVENT_MEDIATYPE_CHANGE, VOOSMP_AVAILABLE_PUREVIDEO");
                break;
              case 2: 
                DebugMode.logV("VisualOnStreamPlayer", "OnEvent VOOSMP_SRC_ADAPTIVE_STREAMING_INFO_EVENT_MEDIATYPE_CHANGE, VOOSMP_AVAILABLE_AUDIOVIDEO");
              }
            }
            handle_VO_OSMP_SRC_CB_CUSTOMER_TAG(paramVO_OSMP_CB_EVENT_ID, paramInt1, paramInt2, paramObject);
            continue;
            DebugMode.logV("VisualOnStreamPlayer", "OnEvent VO_OSMP_SRC_CB_PROGRAM_CHANGED");
            handleSubtitles();
          }
        }
      }
    }
  }
  
  public VOOSMPType.VO_OSMP_RETURN_CODE onVOSyncEvent(VOCommonPlayerListener.VO_OSMP_CB_SYNC_EVENT_ID paramVO_OSMP_CB_SYNC_EVENT_ID, int paramInt1, int paramInt2, Object paramObject)
  {
    return VOOSMPType.VO_OSMP_RETURN_CODE.VO_OSMP_ERR_NONE;
  }
  
  public void pause()
  {
    this._playQueued = false;
    switch (6.$SwitchMap$com$ooyala$android$OoyalaPlayer$State[getState().ordinal()])
    {
    default: 
      return;
    }
    stopPlayheadTimer();
    this._player.pause();
    setState(OoyalaPlayer.State.PAUSED);
  }
  
  public void play()
  {
    this._playQueued = false;
    switch (6.$SwitchMap$com$ooyala$android$OoyalaPlayer$State[getState().ordinal()])
    {
    default: 
      DebugMode.logD("VisualOnStreamPlayer", "Play: invalid status? " + getState());
      return;
    case 1: 
    case 2: 
      queuePlay();
      DebugMode.logV("VisualOnStreamPlayer", "Play: still loading, queued");
      return;
    case 3: 
    case 4: 
    case 5: 
      DebugMode.logV("VisualOnStreamPlayer", "Play: ready - about to start");
      if (this._timeBeforeSuspend == null) {
        if (this._stream.isLiveStream()) {
          this._timeBeforeSuspend = Integer.valueOf(1);
        }
      }
      VOOSMPType.VO_OSMP_RETURN_CODE localVO_OSMP_RETURN_CODE;
      for (;;)
      {
        localVO_OSMP_RETURN_CODE = this._player.start();
        if (localVO_OSMP_RETURN_CODE != VOOSMPType.VO_OSMP_RETURN_CODE.VO_OSMP_ERR_NONE) {
          break;
        }
        DebugMode.logV("VisualOnStreamPlayer", "MediaPlayer started.");
        setState(OoyalaPlayer.State.PLAYING);
        startPlayheadTimer();
        return;
        this._timeBeforeSuspend = Integer.valueOf(-1);
        continue;
        if ((this._timeBeforeSuspend.intValue() >= 0) && (!this._stream.isLiveStream()))
        {
          seekToTime(this._timeBeforeSuspend.intValue());
          this._timeBeforeSuspend = Integer.valueOf(-1);
        }
        else if ((this._timeBeforeSuspend.intValue() <= 0) && (this._stream.isLiveStream()))
        {
          seekToTime(this._timeBeforeSuspend.intValue());
          this._timeBeforeSuspend = Integer.valueOf(1);
        }
      }
      onError(this._player, localVO_OSMP_RETURN_CODE, 0);
      return;
    }
    queuePlay();
    DebugMode.logD("VisualOnStreamPlayer", "Play: Suspended already. re-queue: " + getState());
  }
  
  public void reset()
  {
    suspend(0, OoyalaPlayer.State.PAUSED);
    setupView();
    resume();
  }
  
  public void resume()
  {
    resume(this._timeBeforeSuspend.intValue(), this._stateBeforeSuspend);
  }
  
  public void resume(int paramInt, OoyalaPlayer.State paramState)
  {
    this._timeBeforeSuspend = Integer.valueOf(paramInt);
    this._stateBeforeSuspend = paramState;
    this._lastPlayhead = paramInt;
    DebugMode.logV("VisualOnStreamPlayer", "Player Resume");
    if ((this._isDiscredixLoaded) && (DiscredixDrmUtils.isStreamProtected(this._parent.getLayout().getContext(), this._localFilePath)) && (!DiscredixDrmUtils.canFileBePlayed(this._parent.getLayout().getContext(), this._stream, this._localFilePath))) {
      tryToAcquireRights();
    }
    if ((this._stateBeforeSuspend == OoyalaPlayer.State.PLAYING) || (this._stateBeforeSuspend == OoyalaPlayer.State.LOADING)) {
      play();
    }
    while (this._stateBeforeSuspend != OoyalaPlayer.State.COMPLETED) {
      return;
    }
    queueCompleted();
  }
  
  public void seekToPercentLive(int paramInt)
  {
    int i = (int)this._player.getMaxPosition();
    int j = (int)this._player.getMinPosition();
    paramInt = (i - j) * paramInt / 100 + j;
    DebugMode.logD("VisualOnStreamPlayer", "Seeking Live. Min=" + j + "max=" + i + ", newPosition=" + paramInt);
    if (this._player.setPosition(paramInt) < 0L) {
      DebugMode.logE("VisualOnStreamPlayer", "setPosition failed.");
    }
  }
  
  public void seekToTime(int paramInt)
  {
    if (this._player == null) {}
    do
    {
      return;
      DebugMode.logD("VisualOnStreamPlayer", "Seeking to " + paramInt);
    } while (this._player.setPosition(paramInt) >= 0L);
    DebugMode.logE("VisualOnStreamPlayer", "setPosition failed.");
  }
  
  public void setClosedCaptionsLanguage(String paramString)
  {
    DebugMode.logD("VisualOnStreamPlayer", "Checking if closed captions is in-stream: " + paramString);
    setLiveClosedCaptionsEnabled("Closed Captions".equals(paramString));
  }
  
  public void setLiveClosedCaptionsEnabled(boolean paramBoolean)
  {
    this._isLiveClosedCaptionsEnabled = paramBoolean;
    applySubtitleSettings();
  }
  
  public void setParent(OoyalaPlayer paramOoyalaPlayer)
  {
    super.setParent(paramOoyalaPlayer);
  }
  
  protected void setState(OoyalaPlayer.State paramState)
  {
    DebugMode.logV("VisualOnStreamPlayer", "Set State: " + paramState.name());
    super.setState(paramState);
    dequeueAll();
  }
  
  protected void startPlayheadTimer()
  {
    if (this._playheadUpdateTimer != null) {
      stopPlayheadTimer();
    }
    this._playheadUpdateTimer = new Timer();
    this._playheadUpdateTimer.scheduleAtFixedRate(new PlayheadUpdateTimerTask(), 0L, 1000L);
  }
  
  public void stop()
  {
    DebugMode.logV("VisualOnStreamPlayer", "MediaPlayer stopped.");
    stopPlayheadTimer();
    this._playQueued = false;
    this._player.stop();
    this._player.close();
  }
  
  protected void stopPlayheadTimer()
  {
    if (this._playheadUpdateTimer != null)
    {
      this._playheadUpdateTimer.cancel();
      this._playheadUpdateTimer = null;
    }
  }
  
  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
  {
    DebugMode.logV("VisualOnStreamPlayer", "Surface Changed: " + paramInt2 + "," + paramInt3);
    if (this._player != null) {
      this._player.setSurfaceChangeFinished();
    }
  }
  
  public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
  {
    DebugMode.logI("VisualOnStreamPlayer", "Surface Created");
    this._surfaceExists = true;
    if (this._player != null)
    {
      this._player.resume(this._view);
      return;
    }
    createMediaPlayer();
  }
  
  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
  {
    DebugMode.logI("VisualOnStreamPlayer", "Surface Destroyed");
    this._surfaceExists = false;
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        if (VisualOnStreamPlayer.this._surfaceExists)
        {
          DebugMode.logI("VisualOnStreamPlayer", "Surface Destroyed Runnable called after a different surface was created");
          return;
        }
        if (VisualOnStreamPlayer.this._player != null)
        {
          DebugMode.logI("VisualOnStreamPlayer", "Player stopped after Surface Destroyed");
          VisualOnStreamPlayer.this._player.stop();
          VisualOnStreamPlayer.this._player.setView(null);
          return;
        }
        DebugMode.logE("VisualOnStreamPlayer", "Player did not exist after Surface Destroyed");
      }
    }, 0L);
  }
  
  public void suspend()
  {
    suspend(currentTime(), getState());
  }
  
  public void tryToAcquireRights()
  {
    if (!this._isDiscredixLoaded)
    {
      DebugMode.logE("VisualOnStreamPlayer", "Trying to acquire rights when Discredix doesn't exist");
      this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_PLAYBACK_FAILED, "Trying to acquire rights when Discredix doesn't exist");
      setState(OoyalaPlayer.State.ERROR);
      return;
    }
    boolean bool = DiscredixDrmUtils.isDevicePersonalized(this._parent.getLayout().getContext());
    if ((!bool) || (this._localFilePath == null))
    {
      DebugMode.logE("VisualOnStreamPlayer", "We are not able to acquire rights: We are either not personalized or no file: Personalization = " + bool + ", localFilePath = " + this._localFilePath);
      this._error = new OoyalaException(OoyalaException.OoyalaErrorCode.ERROR_DRM_GENERAL_FAILURE, "Acquire Rights being called when personalization/download did not happen");
      setState(OoyalaPlayer.State.ERROR);
      return;
    }
    DebugMode.logD("VisualOnStreamPlayer", "Acquiring rights");
    AuthorizeCallback local5 = new AuthorizeCallback()
    {
      public void callback(boolean paramAnonymousBoolean, OoyalaException paramAnonymousOoyalaException)
      {
        paramAnonymousOoyalaException = VisualOnStreamPlayer.this._parent.getAuthToken();
        String str = VisualOnStreamPlayer.this._parent.getCustomDRMData();
        paramAnonymousOoyalaException = new AcquireRightsAsyncTask(VisualOnStreamPlayer.this, VisualOnStreamPlayer.this._parent.getLayout().getContext(), VisualOnStreamPlayer.this._localFilePath, paramAnonymousOoyalaException, str);
        VisualOnStreamPlayer.this.setChanged();
        VisualOnStreamPlayer.this.notifyObservers("drmRightsAcquireStarted");
        paramAnonymousOoyalaException.execute(new Void[0]);
      }
    };
    if (this._parent.isAuthTokenExpired())
    {
      this._parent.reauthorizeCurrentItemWithCallback(local5);
      return;
    }
    local5.callback(true, null);
  }
  
  protected class PlayheadUpdateTimerTask
    extends TimerTask
  {
    protected PlayheadUpdateTimerTask() {}
    
    public void run()
    {
      synchronized (VisualOnStreamPlayer.this._player)
      {
        if (!VisualOnStreamPlayer.this.isPlayerValid()) {
          return;
        }
        try
        {
          if (VisualOnStreamPlayer.this._lastPlayhead != VisualOnStreamPlayer.this._player.getPosition()) {
            VisualOnStreamPlayer.this._playheadUpdateTimerHandler.sendEmptyMessage(0);
          }
          VisualOnStreamPlayer.access$102(VisualOnStreamPlayer.this, (int)VisualOnStreamPlayer.this._player.getPosition());
        }
        catch (Exception localException)
        {
          for (;;)
          {
            DebugMode.logE("VisualOnStreamPlayer", "Player is not null, yet position fails, player state: " + VisualOnStreamPlayer.this.getState().name());
          }
        }
        return;
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\visualon\VisualOnStreamPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */