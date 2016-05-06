package com.ons.radio;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.ons.radio.data.information;
import com.spoledge.aacdecoder.IcyURLStreamHandler;
import com.spoledge.aacdecoder.MultiPlayer;
import com.spoledge.aacdecoder.PlayerCallback;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.Timer;
import java.util.TimerTask;

public class AudioSignal
  extends Service
  implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener
{
  private static final int AAC_BUFFER_CAPACITY_MS = 2500;
  private static final int AAC_DECODER_CAPACITY_MS = 700;
  public static final String MODE_EIGHT;
  public static final String MODE_ELEVEN;
  public static final String MODE_FIVE;
  public static final String MODE_FOUR;
  public static final String MODE_FOURTEEN = modeActivity.m_fourteen;
  public static final String MODE_NINE;
  public static final String MODE_ONE = modeActivity.m_one;
  public static final String MODE_SEVEN;
  public static final String MODE_SIX;
  public static final String MODE_TEN;
  public static final String MODE_THIRTEEN;
  public static final String MODE_THREE;
  public static final String MODE_TWELVE;
  public static final String MODE_TWO = modeActivity.m_two;
  private static final String SIGNAL_AAC = "aac";
  private static final String SIGNAL_MP3 = "mp3";
  private static final String nativeplayer = "false";
  public static RemoteViews remoteViews;
  private String STATUS_BUFFERING;
  private String STATUS_CONNECTING;
  private String STATUS_ERROR;
  private String STATUS_NETWORK;
  private String STATUS_PLAYING;
  private String STATUS_READY;
  private String STATUS_STOPPED;
  private String album = "";
  private Bitmap albumCover = null;
  private String artist = "";
  private String befCantor = "";
  private String befRola = "";
  private final IBinder binder = new RadioBinder();
  final Context context = this;
  private final Handler handler = new Handler();
  private boolean isPrepared = false;
  private boolean isPreparingStarted = false;
  private boolean isRadioPlaying = false;
  private MediaPlayer mediaPlayer;
  private Timer metadataTimer;
  private MultiPlayer multiPlayer;
  public final PlayerCallback multiPlayerCallback = new PlayerCallback()
  {
    public void playerAudioTrackCreated(AudioTrack paramAnonymousAudioTrack) {}
    
    public void playerException(Throwable paramAnonymousThrowable)
    {
      AudioSignal.this.handler.post(new Runnable()
      {
        public void run()
        {
          AudioSignal.access$402(AudioSignal.this, false);
          AudioSignal.access$002(AudioSignal.this, false);
          AudioSignal.access$602(AudioSignal.this, "");
          AudioSignal.access$502(AudioSignal.this, -1);
          System.out.println(": Error Playing Stream");
          AudioSignal.this.setStatus(AudioSignal.this.STATUS_ERROR);
          AudioSignal.this.sendBroadcast(new Intent(AudioSignal.MODE_NINE));
          Toast.makeText(AudioSignal.this.getApplicationContext(), "Connection timeout", 1).show();
        }
      });
    }
    
    public void playerMetadata(final String paramAnonymousString1, String paramAnonymousString2)
    {
      System.out.println(paramAnonymousString1 + " ===> " + paramAnonymousString2);
      System.out.println("MetaInfo======>");
      try
      {
        if (("StreamTitle".equals(paramAnonymousString1)) || ("icy-name".equals(paramAnonymousString1)) || ("icy-description".equals(paramAnonymousString1)))
        {
          paramAnonymousString1 = AudioSignal.this.getArtistFromAAC(paramAnonymousString2);
          final String str = AudioSignal.this.getTrackFromAAC(paramAnonymousString2);
          AudioSignal.access$1402(AudioSignal.this, AudioSignal.this.getArtistFromAAC(paramAnonymousString2));
          AudioSignal.access$1502(AudioSignal.this, AudioSignal.this.getTrackFromAAC(paramAnonymousString2));
          AudioSignal.this.handler.post(new Runnable()
          {
            public void run()
            {
              AudioSignal.this.updateMetadataTitle(paramAnonymousString1, str);
            }
          });
        }
        return;
      }
      catch (Exception paramAnonymousString1) {}
    }
    
    public void playerPCMFeedBuffer(boolean paramAnonymousBoolean, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      float f = paramAnonymousInt1 * 100 / paramAnonymousInt2;
      System.out.println("Buffer = " + f + "% , " + paramAnonymousInt1 + " / " + paramAnonymousInt2);
      if (paramAnonymousBoolean == true)
      {
        AudioSignal.access$702(AudioSignal.this, true);
        AudioSignal.access$002(AudioSignal.this, false);
        if (paramAnonymousInt1 < 500)
        {
          AudioSignal.access$402(AudioSignal.this, false);
          AudioSignal.this.setStatus(AudioSignal.this.STATUS_BUFFERING);
          AudioSignal.this.sendBroadcast(new Intent(AudioSignal.MODE_TEN));
          return;
        }
        AudioSignal.access$402(AudioSignal.this, true);
        AudioSignal.this.setStatus(AudioSignal.this.STATUS_PLAYING);
        AudioSignal.this.sendBroadcast(new Intent(AudioSignal.MODE_SIX));
        return;
      }
      AudioSignal.access$402(AudioSignal.this, false);
      AudioSignal.this.setStatus(AudioSignal.this.STATUS_BUFFERING);
      AudioSignal.this.sendBroadcast(new Intent(AudioSignal.MODE_TEN));
    }
    
    public void playerStarted()
    {
      AudioSignal.access$502(AudioSignal.this, 0);
    }
    
    public void playerStopped(int paramAnonymousInt)
    {
      AudioSignal.this.handler.post(new Runnable()
      {
        public void run()
        {
          AudioSignal.access$502(AudioSignal.this, -1);
          AudioSignal.access$602(AudioSignal.this, "");
          AudioSignal.access$402(AudioSignal.this, false);
          AudioSignal.access$002(AudioSignal.this, false);
          System.out.println("RadioSignal: stop");
          AudioSignal.this.setStatus("");
          AudioSignal.this.sendBroadcast(new Intent(AudioSignal.MODE_SEVEN));
        }
      });
    }
  };
  private String playingTime;
  private Timer playtimeTimer;
  SharedPreferences prefs;
  private String status;
  private int timeCounter = -1;
  private String track = "";
  
  static
  {
    MODE_THREE = modeActivity.m_three;
    MODE_FOUR = modeActivity.m_four;
    MODE_FIVE = modeActivity.m_five;
    MODE_SIX = modeActivity.m_six;
    MODE_SEVEN = modeActivity.m_seven;
    MODE_EIGHT = modeActivity.m_eight;
    MODE_NINE = modeActivity.m_nine;
    MODE_TEN = modeActivity.m_ten;
    MODE_ELEVEN = modeActivity.m_eleven;
    MODE_TWELVE = modeActivity.m_twelve;
    MODE_THIRTEEN = modeActivity.m_thirteen;
  }
  
  private String getArtistFromAAC(String paramString)
  {
    int j = paramString.indexOf("-");
    int i = j;
    if (j <= 0) {
      i = paramString.indexOf(":");
    }
    if (i > 0) {
      paramString = paramString.substring(0, i);
    }
    for (;;)
    {
      return paramString.trim();
    }
  }
  
  private String getTrackFromAAC(String paramString)
  {
    int j = paramString.indexOf("-") + 1;
    int i = j;
    if (j <= 0) {
      i = paramString.indexOf(":") + 1;
    }
    if (i > 0) {}
    for (String str = paramString.substring(i);; str = paramString)
    {
      j = paramString.indexOf("(");
      if (j > 0) {
        str = paramString.substring(i, j);
      }
      j = paramString.indexOf("[");
      if (j > 0) {
        str = paramString.substring(i, j);
      }
      return str.trim();
    }
  }
  
  public String StationName()
  {
    return information.RadioName;
  }
  
  public String StationURL()
  {
    try
    {
      String str = information.StreamURL;
      return str;
    }
    catch (Exception localException) {}
    return information.StreamURL;
  }
  
  public void clearAlbum()
  {
    this.album = "";
    this.albumCover = null;
  }
  
  public void clearServiceData()
  {
    this.timeCounter = -1;
    stop();
    resetMetadata();
    this.isPrepared = false;
    stopRefreshingMetadata();
    stopPlayTimer();
    this.mediaPlayer.stop();
  }
  
  public String getAlbum()
  {
    return this.album;
  }
  
  public Bitmap getAlbumCover()
  {
    return this.albumCover;
  }
  
  public String getArtist()
  {
    return this.artist;
  }
  
  public String getCurrentStationType()
  {
    if ("false".equals("false")) {
      return "aac";
    }
    return "mp3";
  }
  
  public String getPlayingTime()
  {
    if (this.timeCounter < 0)
    {
      this.playingTime = "";
      return "";
    }
    return this.playingTime;
  }
  
  public String getStatus()
  {
    if (!isConnected()) {
      this.status = this.STATUS_NETWORK;
    }
    return this.status;
  }
  
  public String getTotalStationNumber()
  {
    return "0";
  }
  
  public String getTrack()
  {
    return this.track;
  }
  
  public boolean isConnected()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting());
  }
  
  public boolean isPlaying()
  {
    if (getCurrentStationType().equals("aac")) {
      return this.isRadioPlaying;
    }
    return this.mediaPlayer.isPlaying();
  }
  
  public boolean isPreparingStarted()
  {
    return this.isPreparingStarted;
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return this.binder;
  }
  
  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    this.isRadioPlaying = false;
    this.timeCounter = -1;
    paramMediaPlayer.stop();
    paramMediaPlayer.reset();
    resetMetadata();
    this.isPrepared = false;
    setStatus(this.STATUS_STOPPED);
    sendBroadcast(new Intent(MODE_EIGHT));
  }
  
  public void onCreate()
  {
    this.STATUS_CONNECTING = getResources().getString(2131231103);
    this.STATUS_BUFFERING = getResources().getString(2131231104);
    this.STATUS_READY = getResources().getString(2131231105);
    this.STATUS_PLAYING = getResources().getString(2131231106);
    this.STATUS_STOPPED = getResources().getString(2131231108);
    this.STATUS_ERROR = getResources().getString(2131231109);
    this.STATUS_NETWORK = getResources().getString(2131231110);
    try
    {
      this.mediaPlayer = new MediaPlayer();
      this.mediaPlayer.setOnCompletionListener(this);
      this.mediaPlayer.setOnErrorListener(this);
      this.mediaPlayer.setOnPreparedListener(this);
      this.mediaPlayer.setOnInfoListener(this);
      this.multiPlayer = new MultiPlayer(this.multiPlayerCallback, 2500, 700);
      sendBroadcast(new Intent(MODE_ONE));
      startRefreshingMetadata();
      startCounting();
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
    {
      for (;;)
      {
        try
        {
          URL.setURLStreamHandlerFactory(new URLStreamHandlerFactory()
          {
            public URLStreamHandler createURLStreamHandler(String paramAnonymousString)
            {
              if ("icy".equals(paramAnonymousString)) {
                return new IcyURLStreamHandler();
              }
              return null;
            }
          });
          return;
        }
        catch (Throwable localThrowable) {}
        localUnsatisfiedLinkError = localUnsatisfiedLinkError;
        localUnsatisfiedLinkError.printStackTrace();
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    try
    {
      if (this.mediaPlayer != null) {
        clearServiceData();
      }
      SharedPreferences.Editor localEditor = this.prefs.edit();
      localEditor.putBoolean("first_time", true);
      localEditor.commit();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    this.isRadioPlaying = false;
    this.timeCounter = -1;
    stop();
    switch (paramInt1)
    {
    }
    for (;;)
    {
      setStatus(this.STATUS_ERROR);
      sendBroadcast(new Intent(MODE_NINE));
      return false;
      Log.v("ERROR", "MEDIA ERROR NOT VALID FOR PROGRESSIVE PLAYBACK " + paramInt2);
      continue;
      Log.v("ERROR", "MEDIA ERROR SERVER DIED " + paramInt2);
      continue;
      Log.v("ERROR", "MEDIA ERROR UNKNOWN " + paramInt2);
    }
  }
  
  public boolean onInfo(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2)
  {
    if (paramInt1 == 701)
    {
      this.isRadioPlaying = false;
      setStatus(this.STATUS_BUFFERING);
      sendBroadcast(new Intent(MODE_TEN));
    }
    while (paramInt1 != 702) {
      return false;
    }
    this.isRadioPlaying = true;
    setStatus(this.STATUS_PLAYING);
    sendBroadcast(new Intent(MODE_ELEVEN));
    return false;
  }
  
  public void onPrepared(MediaPlayer paramMediaPlayer)
  {
    sendBroadcast(new Intent(MODE_FOUR));
    this.isPrepared = true;
    this.isPreparingStarted = false;
    this.timeCounter = 0;
    setStatus("");
    play();
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (isPlaying()) {
      setStatus(this.STATUS_PLAYING);
    }
    for (;;)
    {
      if (this.mediaPlayer.isPlaying()) {
        sendBroadcast(new Intent(MODE_SIX));
      }
      return 2;
      if (isPreparingStarted())
      {
        setStatus(this.STATUS_BUFFERING);
        sendBroadcast(new Intent(MODE_THREE));
      }
      else
      {
        setStatus("");
        sendBroadcast(new Intent(MODE_FIVE));
      }
    }
  }
  
  public void play()
  {
    if (isConnected())
    {
      if (getCurrentStationType().equals("aac"))
      {
        prepare();
        return;
      }
      if (this.isPrepared)
      {
        this.isRadioPlaying = true;
        this.mediaPlayer.start();
        System.out.println("RadioSignal: play");
        setStatus(this.STATUS_PLAYING);
        sendBroadcast(new Intent(MODE_SIX));
        return;
      }
      prepare();
      return;
    }
    sendBroadcast(new Intent(MODE_SEVEN));
  }
  
  public void prepare()
  {
    this.isPreparingStarted = true;
    setStatus(this.STATUS_BUFFERING);
    sendBroadcast(new Intent(MODE_THREE));
    try
    {
      if (getCurrentStationType().equals("aac"))
      {
        this.multiPlayer.playAsync(StationURL());
        return;
      }
      this.mediaPlayer.setDataSource(StationURL());
      this.mediaPlayer.prepareAsync();
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
      stop();
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      localNullPointerException.printStackTrace();
      stop();
      return;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      localIllegalStateException.printStackTrace();
      stop();
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      stop();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      stop();
    }
  }
  
  public void resetMetadata()
  {
    this.artist = "";
    this.track = "";
    this.befCantor = "";
    this.befRola = "";
    clearAlbum();
  }
  
  public void setAlbum(String paramString)
  {
    this.album = paramString;
  }
  
  public void setArtist(String paramString)
  {
    this.artist = paramString;
  }
  
  public void setStatus(String paramString)
  {
    if (!isConnected())
    {
      this.status = this.STATUS_NETWORK;
      return;
    }
    this.status = paramString;
  }
  
  public void setTrack(String paramString)
  {
    this.track = paramString;
  }
  
  public void startCounting()
  {
    this.playtimeTimer = new Timer();
    TimerTask local3 = new TimerTask()
    {
      public void run()
      {
        int k;
        int j;
        if (AudioSignal.this.isRadioPlaying == true)
        {
          AudioSignal.access$508(AudioSignal.this);
          k = AudioSignal.this.timeCounter;
          j = k / 60;
          int i = j / 60;
          k %= 60;
          j %= 60;
          if (i > 0) {
            AudioSignal.access$602(AudioSignal.this, String.format("%02d:%02d:%02d", new Object[] { Integer.valueOf(i), Integer.valueOf(j), Integer.valueOf(k) }));
          }
        }
        else
        {
          return;
        }
        AudioSignal.access$602(AudioSignal.this, String.format("%02d:%02d", new Object[] { Integer.valueOf(j), Integer.valueOf(k) }));
      }
    };
    this.playtimeTimer.schedule(local3, 0L, 1000L);
  }
  
  public void startRefreshingMetadata()
  {
    final Object localObject = new Handler();
    this.metadataTimer = new Timer();
    localObject = new TimerTask()
    {
      public void run()
      {
        localObject.post(new Runnable()
        {
          public void run()
          {
            try
            {
              if ((AudioSignal.this.getCurrentStationType().equals("mp3")) && (!AudioSignal.this.isPlaying()))
              {
                boolean bool = AudioSignal.this.isPreparingStarted;
                if (!bool) {}
              }
              return;
            }
            catch (Exception localException) {}
          }
        });
      }
    };
    this.metadataTimer.schedule((TimerTask)localObject, 0L, 10000L);
  }
  
  public void stop()
  {
    this.timeCounter = -1;
    resetMetadata();
    this.isPrepared = false;
    this.isPreparingStarted = false;
    System.out.println("RadioStation: stop-audio");
    if (getCurrentStationType().equals("aac"))
    {
      if (this.isRadioPlaying)
      {
        this.isRadioPlaying = false;
        this.multiPlayer.stop();
      }
      return;
    }
    this.mediaPlayer.stop();
    this.mediaPlayer.reset();
    this.isRadioPlaying = false;
    setStatus("");
    sendBroadcast(new Intent(MODE_SEVEN));
  }
  
  public void stopPlayTimer()
  {
    this.playtimeTimer.cancel();
  }
  
  public void stopRefreshingMetadata()
  {
    this.metadataTimer.cancel();
  }
  
  public void updateAlbum()
  {
    try
    {
      String[] arrayOfString = new String[2];
      arrayOfString[0] = getArtist();
      arrayOfString[1] = getTrack();
      if ((!arrayOfString[0].equals("")) && (!arrayOfString[1].equals(""))) {
        new LastFMCoverAsyncTask(null).execute(arrayOfString);
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void updateMetadataTitle(String paramString1, String paramString2)
  {
    if (paramString2 != null)
    {
      this.artist = paramString1;
      this.track = paramString2;
      if ((this.artist.equals(this.befCantor)) && (this.track.equals(this.befRola))) {}
      do
      {
        return;
        this.befCantor = this.artist;
        this.befRola = this.track;
        clearAlbum();
        sendBroadcast(new Intent(MODE_TWELVE));
        System.out.println(this.artist);
        System.out.println(this.track);
      } while (!information.EnableLastFm.equals("yes"));
      updateAlbum();
      return;
    }
    clearAlbum();
  }
  
  private class LastFMCoverAsyncTask
    extends AsyncTask<String, Integer, Bitmap>
  {
    private LastFMCoverAsyncTask() {}
    
    protected Bitmap doInBackground(String... paramVarArgs)
    {
      try
      {
        paramVarArgs = LastFMCover.getCoverImageFromTrack(information.LastFm, paramVarArgs[0], paramVarArgs[1]);
        return paramVarArgs;
      }
      catch (Exception paramVarArgs)
      {
        paramVarArgs.printStackTrace();
      }
      return null;
    }
    
    protected void onPostExecute(Bitmap paramBitmap)
    {
      AudioSignal.access$202(AudioSignal.this, paramBitmap);
      if (paramBitmap != null) {
        AudioSignal.access$302(AudioSignal.this, LastFMCover.album);
      }
      if (information.EnableLastFm.equals("yes")) {}
      AudioSignal.access$302(AudioSignal.this, "");
      AudioSignal.this.sendBroadcast(new Intent(AudioSignal.MODE_THIRTEEN));
      System.out.println("notification cover updated");
      System.out.println(AudioSignal.this.album);
      System.out.println(AudioSignal.this.albumCover);
    }
  }
  
  public class RadioBinder
    extends Binder
  {
    public RadioBinder() {}
    
    AudioSignal getService()
    {
      return AudioSignal.this;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\radio\AudioSignal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */