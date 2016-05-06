package com.ons.musicplayer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.example.example75f1799f07eb.MyPhoneGapActivity;
import com.ons.radio.AudioSignal;
import com.ons.radio.PlayerActivity;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
public class MainActivity
  extends Activity
  implements MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener
{
  public static final String MyPREFERENCES = "MyPrefs";
  public static int index = 0;
  public static MediaPlayer mp;
  static SharedPreferences sharedpreferences;
  public static String songArtist;
  public static String songTitle;
  public static String songurl;
  Animation anim;
  TextView appName;
  private ImageButton btnBackward;
  private ImageButton btnForward;
  private ImageButton btnNext;
  private ImageButton btnPlay;
  private ImageButton btnPrevious;
  boolean checkPlayerStatus = false;
  TextView current;
  private int currentSongIndex = 0;
  String enableAutoPlay = "1";
  public Bitmap imageBitmap = null;
  String imageurl = "";
  AnimatedGifImageView img;
  ImageView img_song;
  private boolean isRepeat = false;
  private boolean isShuffle = false;
  private Adapter mAdapter = new BaseAdapter()
  {
    public int getCount()
    {
      return 2;
    }
    
    public Object getItem(int paramAnonymousInt)
    {
      return new Object();
    }
    
    public long getItemId(int paramAnonymousInt)
    {
      return paramAnonymousInt;
    }
    
    public View getView(int paramAnonymousInt, View paramAnonymousView, ViewGroup paramAnonymousViewGroup)
    {
      paramAnonymousView = ((LayoutInflater)MainActivity.this.getSystemService("layout_inflater")).inflate(2130903153, null);
      paramAnonymousViewGroup = (TextView)paramAnonymousView.findViewById(2131558783);
      paramAnonymousViewGroup.setTypeface(null, 1);
      if (MainActivity.songTitle == null) {
        MainActivity.songTitle = (String)((HashMap)MainActivity.this.songsList.get(MainActivity.this.currentSongIndex)).get("songTitle");
      }
      paramAnonymousViewGroup.setText(MainActivity.songTitle);
      return paramAnonymousView;
    }
  };
  private Handler mHandler = new Handler();
  boolean mOngoing = false;
  private Runnable mOngoing_backword_Runnable = new Runnable()
  {
    public void run()
    {
      int i = MainActivity.mp.getCurrentPosition();
      if (i - MainActivity.this.seekBackwardTime >= 0) {
        MainActivity.mp.seekTo(i - MainActivity.this.seekBackwardTime);
      }
      for (;;)
      {
        MainActivity.this.mHandler.postDelayed(MainActivity.this.mOngoing_backword_Runnable, 100L);
        return;
        MainActivity.mp.seekTo(0);
      }
    }
  };
  private Runnable mOngoing_farword_Runnable = new Runnable()
  {
    public void run()
    {
      int i = MainActivity.mp.getCurrentPosition();
      if (MainActivity.this.seekForwardTime + i <= MainActivity.mp.getDuration()) {
        MainActivity.mp.seekTo(MainActivity.this.seekForwardTime + i);
      }
      for (;;)
      {
        MainActivity.this.mHandler.postDelayed(MainActivity.this.mOngoing_farword_Runnable, 100L);
        return;
        MainActivity.mp.seekTo(MainActivity.mp.getDuration());
      }
    }
  };
  private Runnable mUpdateTimeTask = new Runnable()
  {
    public void run()
    {
      long l1 = MainActivity.mp.getDuration();
      long l2 = MainActivity.mp.getCurrentPosition();
      MainActivity.this.total.setText("" + MainActivity.this.utils.milliSecondsToTimer(l1));
      MainActivity.this.current.setText("" + MainActivity.this.utils.milliSecondsToTimer(l2));
      int i = MainActivity.this.utils.getProgressPercentage(l2, l1);
      MainActivity.this.seekBar.setProgress(i);
      MainActivity.this.mHandler.postDelayed(this, 100L);
    }
  };
  ProgressDialog pd;
  BroadcastReceiver phonestatereceiver;
  String[] playerInfo;
  int playlistindex;
  private String plstype = "";
  private int seekBackwardTime = 5000;
  SeekBar seekBar;
  LinearLayout seekBarLayout;
  private int seekForwardTime = 5000;
  TextView songDescLabel;
  private SongsManager songManager;
  TickerView songTitleLabel;
  private ArrayList<HashMap<String, String>> songsList = new ArrayList();
  int status = 0;
  boolean streamValue = false;
  TextView total;
  private String typeLanguage;
  private Utilities utils;
  
  public void close(View paramView)
  {
    if ((mp != null) && (this.checkPlayerStatus))
    {
      mp.stop();
      mp = null;
      this.mHandler.removeCallbacks(this.mUpdateTimeTask);
    }
    MyPhoneGapActivity.loadFirstPage();
    finish();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 100)
    {
      this.currentSongIndex = paramIntent.getExtras().getInt("songIndex");
      this.status = 1;
      this.playlistindex = paramIntent.getExtras().getInt("songIndex");
      playSong(this.currentSongIndex);
    }
  }
  
  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    if (this.isRepeat)
    {
      playSong(this.currentSongIndex);
      return;
    }
    if (this.isShuffle)
    {
      this.currentSongIndex = (new Random().nextInt(this.songsList.size() - 1 + 0 + 1) + 0);
      playSong(this.currentSongIndex);
      return;
    }
    if (this.currentSongIndex < this.songsList.size() - 1)
    {
      playSong(this.currentSongIndex + 1);
      this.currentSongIndex += 1;
      return;
    }
    playSong(0);
    this.currentSongIndex = 0;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(7);
    sharedpreferences = getSharedPreferences("MyPrefs", 0);
    this.typeLanguage = sharedpreferences.getString("appLanguage", "");
    System.out.println("===== type language is in MainActivity : " + this.typeLanguage);
    setContentView(2130903123);
    if (this.typeLanguage.equalsIgnoreCase("en")) {
      getWindow().setFeatureInt(7, 2130903063);
    }
    for (;;)
    {
      paramBundle = getResources().getString(2131230720);
      this.appName = ((TextView)findViewById(2131558500));
      this.appName.setText(paramBundle);
      Log.v("amritesh", "inside on create");
      try
      {
        if ((PlayerActivity.audioSignal != null) && (PlayerActivity.audioSignal.isPlaying())) {
          PlayerActivity.audioSignal.stop();
        }
        ((ImageButton)findViewById(2131558504)).setVisibility(0);
        this.seekBarLayout = ((LinearLayout)findViewById(2131558675));
        this.plstype = getIntent().getStringExtra("plsTypeValue");
        this.enableAutoPlay = getIntent().getStringExtra("enableAutoPlay");
        this.playerInfo = getIntent().getStringExtra("songArray").split("@@--@@");
        this.btnPlay = ((ImageButton)findViewById(2131558672));
        this.btnForward = ((ImageButton)findViewById(2131558673));
        this.btnBackward = ((ImageButton)findViewById(2131558671));
        this.btnNext = ((ImageButton)findViewById(2131558674));
        this.btnPrevious = ((ImageButton)findViewById(2131558670));
        this.seekBar = ((SeekBar)findViewById(2131558677));
        this.current = ((TextView)findViewById(2131558676));
        this.total = ((TextView)findViewById(2131558678));
        this.songTitleLabel = ((TickerView)findViewById(2131558680));
        this.songDescLabel = ((TextView)findViewById(2131558681));
        this.img = ((AnimatedGifImageView)findViewById(2131558679));
        this.img_song = ((ImageView)findViewById(2131558669));
        this.songDescLabel.setTypeface(null, 1);
        if ((mp != null) && (mp.isPlaying()))
        {
          mp.stop();
          mp = null;
        }
        mp = new MediaPlayer();
        mp.setOnCompletionListener(this);
        this.songManager = new SongsManager();
        this.utils = new Utilities();
        com.phonegap.plugins.ebookViewer.PlayerViewer.playercheck = false;
        this.seekBar.setOnSeekBarChangeListener(this);
        this.songsList = this.songManager.getPlayList(this.playerInfo);
        try
        {
          paramBundle = getIntent().getStringExtra("type");
          this.imageurl = getIntent().getStringExtra("imageurl");
          System.out.println("imageurl" + this.imageurl);
          Log.v("type ", "" + paramBundle);
          Log.v("imageurl ", "" + this.imageurl);
          if (paramBundle.equals("custom")) {
            if ((this.imageurl.equalsIgnoreCase("")) || (this.imageurl.length() == 0) || (this.imageurl == null))
            {
              System.out.println("1");
              Log.v("amritesh", "type 1 : " + paramBundle);
              Log.v("amritesh", "imageurl 1 : " + this.imageurl);
              this.img_song.setImageResource(2130837704);
              label731:
              this.img.setAnimatedGif(2131099658, AnimatedGifImageView.TYPE.STREACH_TO_FIT);
            }
          }
        }
        catch (Exception paramBundle)
        {
          try
          {
            label744:
            if ((mp.isPlaying()) && (mp != null))
            {
              Log.v("amritesh", "if (mp.isPlaying() && mp != null)");
              System.out.println("mp not not playyyyyyy");
            }
            for (;;)
            {
              this.phonestatereceiver = new BroadcastReceiver()
              {
                public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
                {
                  paramAnonymousContext = paramAnonymousIntent.getExtras();
                  if (paramAnonymousContext != null)
                  {
                    paramAnonymousContext = paramAnonymousContext.getString("state");
                    if (!paramAnonymousContext.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                      break label73;
                    }
                  }
                  for (;;)
                  {
                    try
                    {
                      if (MainActivity.mp != null)
                      {
                        System.out.println("mp !=null receive ");
                        MainActivity.mp.pause();
                        MainActivity.this.checkPlayerStatus = true;
                        MainActivity.this.btnPlay.setImageResource(2130837518);
                      }
                      return;
                    }
                    catch (Exception paramAnonymousContext)
                    {
                      paramAnonymousContext.printStackTrace();
                      return;
                    }
                    label73:
                    if (paramAnonymousContext.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
                    {
                      if ((MainActivity.mp != null) && (MainActivity.mp.isPlaying())) {
                        MainActivity.mp.pause();
                      }
                    }
                    else if (paramAnonymousContext.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                      try
                      {
                        if (MainActivity.mp != null)
                        {
                          System.out.println("mp !=null receive1111111111111");
                          MainActivity.mp.start();
                          MainActivity.this.checkPlayerStatus = false;
                          MainActivity.this.btnPlay.setImageResource(2130837516);
                          MainActivity.this.img.setAnimatedGif(2131099658, AnimatedGifImageView.TYPE.STREACH_TO_FIT);
                          return;
                        }
                      }
                      catch (Exception paramAnonymousContext)
                      {
                        paramAnonymousContext.printStackTrace();
                      }
                    }
                  }
                }
              };
              registerReceiver(this.phonestatereceiver, new IntentFilter("android.intent.action.PHONE_STATE"));
              this.btnPlay.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymousView)
                {
                  try
                  {
                    if (MainActivity.mp.isPlaying())
                    {
                      System.out.println("11111111");
                      if (MainActivity.mp != null)
                      {
                        System.out.println("22222222");
                        MainActivity.mp.pause();
                        MainActivity.this.checkPlayerStatus = true;
                        MainActivity.this.btnPlay.setImageResource(2130837518);
                      }
                    }
                    else
                    {
                      System.out.println("333333");
                      if (MainActivity.mp != null)
                      {
                        System.out.println("444444444");
                        MainActivity.mp.start();
                        MainActivity.this.checkPlayerStatus = false;
                        MainActivity.this.btnPlay.setImageResource(2130837516);
                        MainActivity.this.img.setAnimatedGif(2131099658, AnimatedGifImageView.TYPE.STREACH_TO_FIT);
                        return;
                      }
                    }
                  }
                  catch (Exception paramAnonymousView) {}
                }
              });
              this.btnForward.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymousView)
                {
                  try
                  {
                    if (MainActivity.this.mOngoing)
                    {
                      MainActivity.this.mHandler.removeCallbacks(MainActivity.this.mOngoing_farword_Runnable);
                      MainActivity.this.mOngoing = false;
                    }
                    int i = MainActivity.mp.getCurrentPosition();
                    if (MainActivity.this.seekForwardTime + i <= MainActivity.mp.getDuration())
                    {
                      MainActivity.mp.seekTo(MainActivity.this.seekForwardTime + i);
                      return;
                    }
                    MainActivity.mp.seekTo(MainActivity.mp.getDuration());
                    return;
                  }
                  catch (Exception paramAnonymousView) {}
                }
              });
              this.btnForward.setOnLongClickListener(new View.OnLongClickListener()
              {
                public boolean onLongClick(View paramAnonymousView)
                {
                  MainActivity.this.mHandler.post(MainActivity.this.mOngoing_farword_Runnable);
                  MainActivity.this.mOngoing = true;
                  return false;
                }
              });
              this.btnBackward.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymousView)
                {
                  if (MainActivity.this.mOngoing)
                  {
                    MainActivity.this.mHandler.removeCallbacks(MainActivity.this.mOngoing_backword_Runnable);
                    MainActivity.this.mOngoing = false;
                  }
                  int i = MainActivity.mp.getCurrentPosition();
                  if (i - MainActivity.this.seekBackwardTime >= 0)
                  {
                    MainActivity.mp.seekTo(i - MainActivity.this.seekBackwardTime);
                    return;
                  }
                  MainActivity.mp.seekTo(0);
                }
              });
              this.btnBackward.setOnLongClickListener(new View.OnLongClickListener()
              {
                public boolean onLongClick(View paramAnonymousView)
                {
                  MainActivity.this.mHandler.post(MainActivity.this.mOngoing_backword_Runnable);
                  MainActivity.this.mOngoing = true;
                  return false;
                }
              });
              this.btnNext.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymousView)
                {
                  if (MainActivity.this.currentSongIndex < MainActivity.this.songsList.size() - 1)
                  {
                    MainActivity.this.playSong(MainActivity.this.currentSongIndex + 1);
                    MainActivity.access$602(MainActivity.this, MainActivity.this.currentSongIndex + 1);
                    return;
                  }
                  MainActivity.this.playSong(0);
                  MainActivity.access$602(MainActivity.this, 0);
                }
              });
              this.btnPrevious.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymousView)
                {
                  if (MainActivity.this.currentSongIndex > 0)
                  {
                    MainActivity.this.playSong(MainActivity.this.currentSongIndex - 1);
                    MainActivity.access$602(MainActivity.this, MainActivity.this.currentSongIndex - 1);
                    return;
                  }
                  MainActivity.this.playSong(MainActivity.this.songsList.size() - 1);
                  MainActivity.access$602(MainActivity.this, MainActivity.this.songsList.size() - 1);
                }
              });
              return;
              getWindow().setFeatureInt(7, 2130903065);
              break;
              if ((this.imageurl == "") || (this.imageurl.length() < 1)) {
                break label731;
              }
              Object localObject = new URL(this.imageurl);
              System.out.println("2");
              localObject = BitmapFactory.decodeStream(((URL)localObject).openConnection().getInputStream());
              Log.v("amritesh", "type 2 : " + paramBundle);
              Log.v("amritesh", "imageurl 2 : " + this.imageurl);
              this.img_song.setImageBitmap((Bitmap)localObject);
              break label731;
              paramBundle = paramBundle;
              paramBundle.printStackTrace();
              break label744;
              if (paramBundle.equals("customRadio"))
              {
                if ((this.imageurl.equalsIgnoreCase("")) || (this.imageurl.length() == 0) || (this.imageurl == null))
                {
                  System.out.println("3");
                  this.img_song.setImageResource(2130837703);
                  break label731;
                }
                if ((this.imageurl == "") || (this.imageurl.length() < 1)) {
                  break label731;
                }
                System.out.println("4");
                paramBundle = BitmapFactory.decodeStream(new URL(this.imageurl).openConnection().getInputStream());
                this.img_song.setImageBitmap(paramBundle);
                break label731;
              }
              if (paramBundle.equals("drible"))
              {
                if ((this.imageurl.equalsIgnoreCase("")) || (this.imageurl.length() == 0) || (this.imageurl == null))
                {
                  System.out.println("5");
                  this.img_song.setImageResource(2130837705);
                  break label731;
                }
                if ((this.imageurl == "") || (this.imageurl.length() < 1)) {
                  break label731;
                }
                System.out.println("6");
                paramBundle = BitmapFactory.decodeStream(new URL(this.imageurl).openConnection().getInputStream());
                this.img_song.setImageBitmap(paramBundle);
                break label731;
              }
              if (paramBundle.equals("rss"))
              {
                if ((this.imageurl.equalsIgnoreCase("")) || (this.imageurl.length() == 0) || (this.imageurl == null))
                {
                  System.out.println("7");
                  this.img_song.setImageResource(2130837702);
                  break label731;
                }
                if ((this.imageurl == "") || (this.imageurl.length() < 1)) {
                  break label731;
                }
                System.out.println("8");
                paramBundle = BitmapFactory.decodeStream(new URL(this.imageurl).openConnection().getInputStream());
                this.img_song.setImageBitmap(paramBundle);
                break label731;
              }
              if (paramBundle.equals("soundcloud"))
              {
                if ((this.imageurl.equalsIgnoreCase("")) || (this.imageurl.length() == 0) || (this.imageurl == null))
                {
                  System.out.println("9");
                  this.img_song.setImageResource(2130837704);
                  break label731;
                }
                if ((this.imageurl == "") || (this.imageurl.length() < 1)) {
                  break label731;
                }
                paramBundle = new URL(this.imageurl);
                System.out.println("10");
                paramBundle = BitmapFactory.decodeStream(paramBundle.openConnection().getInputStream());
                this.img_song.setImageBitmap(paramBundle);
                break label731;
              }
              System.out.println("11");
              this.img_song.setImageResource(2130837704);
              break label731;
              Log.v("amritesh", "if (mp.isPlaying() && mp != null) else ");
              System.out.println("mp not playyyyyyy elseee");
              System.out.println("enableAutoPlay value" + this.enableAutoPlay);
              if (!this.enableAutoPlay.equalsIgnoreCase("0")) {
                playSong(0);
              }
            }
          }
          catch (Exception paramBundle)
          {
            for (;;) {}
          }
        }
      }
      catch (Exception paramBundle)
      {
        for (;;) {}
      }
    }
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    unregisterReceiver(this.phonestatereceiver);
    this.mHandler.removeCallbacks(this.mUpdateTimeTask);
  }
  
  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean) {}
  
  public void onStartTrackingTouch(SeekBar paramSeekBar)
  {
    this.mHandler.removeCallbacks(this.mUpdateTimeTask);
  }
  
  public void onStopTrackingTouch(SeekBar paramSeekBar)
  {
    this.mHandler.removeCallbacks(this.mUpdateTimeTask);
    int i = mp.getDuration();
    i = this.utils.progressToTimer(paramSeekBar.getProgress(), i);
    mp.seekTo(i);
    updateProgressBar();
  }
  
  public void open(String paramString)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage(paramString);
    localBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    });
    localBuilder.create().show();
  }
  
  public void openPlayList(View paramView)
  {
    paramView = new Intent(getApplicationContext(), PlayListActivity.class);
    paramView.putExtra("songInfo", getIntent().getStringExtra("songArray"));
    paramView.putExtra("currentsong", this.currentSongIndex);
    paramView.putExtra("applanguage", this.typeLanguage);
    startActivityForResult(paramView, 100);
  }
  
  /* Error */
  public void playSong(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_3
    //   3: astore_2
    //   4: iload_1
    //   5: putstatic 123	com/ons/musicplayer/MainActivity:index	I
    //   8: aload_3
    //   9: astore_2
    //   10: iconst_0
    //   11: putstatic 480	com/phonegap/plugins/ebookViewer/PlayerViewer:playercheck	Z
    //   14: aload_3
    //   15: astore_2
    //   16: aload_0
    //   17: getfield 149	com/ons/musicplayer/MainActivity:songsList	Ljava/util/ArrayList;
    //   20: iload_1
    //   21: invokevirtual 710	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   24: checkcast 712	java/util/HashMap
    //   27: ldc_w 714
    //   30: invokevirtual 717	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   33: checkcast 322	java/lang/String
    //   36: astore_3
    //   37: aload_3
    //   38: astore_2
    //   39: aload_0
    //   40: getfield 149	com/ons/musicplayer/MainActivity:songsList	Ljava/util/ArrayList;
    //   43: iload_1
    //   44: invokevirtual 710	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   47: checkcast 712	java/util/HashMap
    //   50: ldc_w 719
    //   53: invokevirtual 717	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   56: checkcast 322	java/lang/String
    //   59: astore 5
    //   61: aload_3
    //   62: astore 4
    //   64: aload 5
    //   66: ifnull +108 -> 174
    //   69: aload_3
    //   70: astore 4
    //   72: aload 5
    //   74: ldc -99
    //   76: if_acmpeq +98 -> 174
    //   79: aload_3
    //   80: astore 4
    //   82: aload_3
    //   83: astore_2
    //   84: aload 5
    //   86: invokevirtual 504	java/lang/String:length	()I
    //   89: iconst_1
    //   90: if_icmplt +84 -> 174
    //   93: aload_3
    //   94: astore 4
    //   96: aload_3
    //   97: astore_2
    //   98: aload 5
    //   100: ldc_w 721
    //   103: invokevirtual 326	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   106: ifne +68 -> 174
    //   109: aload_3
    //   110: astore_2
    //   111: new 558	java/net/URL
    //   114: dup
    //   115: aload 5
    //   117: invokespecial 559	java/net/URL:<init>	(Ljava/lang/String;)V
    //   120: astore 4
    //   122: aload_3
    //   123: astore_2
    //   124: getstatic 295	java/lang/System:out	Ljava/io/PrintStream;
    //   127: ldc_w 615
    //   130: invokevirtual 314	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   133: aload_3
    //   134: astore_2
    //   135: ldc_w 362
    //   138: ldc_w 723
    //   141: invokestatic 370	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   144: pop
    //   145: aload_3
    //   146: astore_2
    //   147: aload 4
    //   149: invokevirtual 565	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   152: invokevirtual 571	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   155: invokestatic 577	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   158: astore 4
    //   160: aload_3
    //   161: astore_2
    //   162: aload_0
    //   163: getfield 457	com/ons/musicplayer/MainActivity:img_song	Landroid/widget/ImageView;
    //   166: aload 4
    //   168: invokevirtual 585	android/widget/ImageView:setImageBitmap	(Landroid/graphics/Bitmap;)V
    //   171: aload_3
    //   172: astore 4
    //   174: aload 4
    //   176: ldc -99
    //   178: invokevirtual 326	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   181: ifne +11 -> 192
    //   184: aload 4
    //   186: invokevirtual 504	java/lang/String:length	()I
    //   189: ifgt +32 -> 221
    //   192: aload_0
    //   193: ldc_w 725
    //   196: invokevirtual 727	com/ons/musicplayer/MainActivity:open	(Ljava/lang/String;)V
    //   199: return
    //   200: astore_3
    //   201: aload_0
    //   202: getfield 457	com/ons/musicplayer/MainActivity:img_song	Landroid/widget/ImageView;
    //   205: ldc_w 509
    //   208: invokevirtual 512	android/widget/ImageView:setImageResource	(I)V
    //   211: aload_3
    //   212: invokevirtual 588	java/lang/Exception:printStackTrace	()V
    //   215: aload_2
    //   216: astore 4
    //   218: goto -44 -> 174
    //   221: getstatic 295	java/lang/System:out	Ljava/io/PrintStream;
    //   224: ldc_w 729
    //   227: invokevirtual 314	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   230: new 36	com/ons/musicplayer/MainActivity$SongAsyTask
    //   233: dup
    //   234: aload_0
    //   235: invokespecial 730	com/ons/musicplayer/MainActivity$SongAsyTask:<init>	(Lcom/ons/musicplayer/MainActivity;)V
    //   238: iconst_2
    //   239: anewarray 322	java/lang/String
    //   242: dup
    //   243: iconst_0
    //   244: aload 4
    //   246: aastore
    //   247: dup
    //   248: iconst_1
    //   249: ldc_w 732
    //   252: aastore
    //   253: invokevirtual 736	com/ons/musicplayer/MainActivity$SongAsyTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   256: pop
    //   257: return
    //   258: astore_2
    //   259: aload_2
    //   260: invokevirtual 588	java/lang/Exception:printStackTrace	()V
    //   263: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	264	0	this	MainActivity
    //   0	264	1	paramInt	int
    //   3	213	2	str1	String
    //   258	2	2	localException1	Exception
    //   1	171	3	str2	String
    //   200	12	3	localException2	Exception
    //   62	183	4	localObject	Object
    //   59	57	5	str3	String
    // Exception table:
    //   from	to	target	type
    //   4	8	200	java/lang/Exception
    //   10	14	200	java/lang/Exception
    //   16	37	200	java/lang/Exception
    //   39	61	200	java/lang/Exception
    //   84	93	200	java/lang/Exception
    //   98	109	200	java/lang/Exception
    //   111	122	200	java/lang/Exception
    //   124	133	200	java/lang/Exception
    //   135	145	200	java/lang/Exception
    //   147	160	200	java/lang/Exception
    //   162	171	200	java/lang/Exception
    //   174	192	258	java/lang/Exception
    //   192	199	258	java/lang/Exception
    //   221	257	258	java/lang/Exception
  }
  
  public void shareAudio(View paramView)
  {
    paramView = new Intent("android.intent.action.SEND");
    paramView.setType("text/plain");
    paramView.putExtra("android.intent.extra.TEXT", songurl);
    startActivity(Intent.createChooser(paramView, "Share via"));
  }
  
  public void updateProgressBar()
  {
    this.mHandler.postDelayed(this.mUpdateTimeTask, 100L);
  }
  
  public class SongAsyTask
    extends AsyncTask<String, Void, String>
  {
    public SongAsyTask() {}
    
    public String abcd(String paramString1, String paramString2, String paramString3)
    {
      return StringUtils.substringBetween(paramString1, paramString2, paramString3);
    }
    
    protected String doInBackground(String... paramVarArgs)
    {
      String str = "";
      MainActivity.songurl = paramVarArgs[0];
      Object localObject;
      try
      {
        if (paramVarArgs[0].contains(".mp3"))
        {
          localObject = new MediaMetadataRetriever();
          if (Build.VERSION.SDK_INT >= 14)
          {
            ((MediaMetadataRetriever)localObject).setDataSource(paramVarArgs[0], new HashMap());
            MainActivity.songTitle = ((MediaMetadataRetriever)localObject).extractMetadata(7);
            MainActivity.songArtist = ((MediaMetadataRetriever)localObject).extractMetadata(2);
            System.out.println("songtitle>>>>>>>>>>>>" + MainActivity.songTitle);
            if ((MainActivity.songTitle.equals(null)) || (MainActivity.songTitle == null) || (MainActivity.songTitle.equals("null"))) {
              MainActivity.songTitle = (String)((HashMap)MainActivity.this.songsList.get(MainActivity.this.currentSongIndex)).get("songTitle");
            }
            paramVarArgs = ((MediaMetadataRetriever)localObject).getEmbeddedPicture();
            if (paramVarArgs != null) {
              MainActivity.this.imageBitmap = BitmapFactory.decodeByteArray(paramVarArgs, 0, paramVarArgs.length);
            }
            localObject = "other";
            if ((MainActivity.songurl.length() > 0) && (MainActivity.songurl.charAt(MainActivity.songurl.length() - 1) == '/')) {
              MainActivity.songurl = MainActivity.songurl.substring(0, MainActivity.songurl.length() - 1);
            }
          }
        }
      }
      catch (Exception paramVarArgs)
      {
        try
        {
          do
          {
            for (;;)
            {
              MainActivity.mp.reset();
              MainActivity.mp.setAudioStreamType(3);
              System.out.println("songurl neeti " + MainActivity.songurl);
              MainActivity.mp.setDataSource(MainActivity.songurl);
              System.out.println("check1");
              MainActivity.mp.setWakeMode(MainActivity.this.getApplicationContext(), 1);
              System.out.println("check2");
              MainActivity.mp.prepare();
              System.out.println("check3");
              return (String)localObject;
              ((MediaMetadataRetriever)localObject).setDataSource(paramVarArgs[0]);
              continue;
              paramVarArgs = paramVarArgs;
              MainActivity.songTitle = (String)((HashMap)MainActivity.this.songsList.get(MainActivity.this.currentSongIndex)).get("songTitle");
              MainActivity.songArtist = "";
              paramVarArgs.printStackTrace();
              localObject = str;
            }
            localObject = str;
          } while (!paramVarArgs[1].equals("other"));
          MainActivity.songTitle = (String)((HashMap)MainActivity.this.songsList.get(MainActivity.this.currentSongIndex)).get("songTitle");
          MainActivity.songArtist = "";
          localObject = "other";
        }
        catch (Exception paramVarArgs)
        {
          do
          {
            System.out.println("error in media player catch by krishna");
            cancel(true);
          } while (MainActivity.this.pd == null);
          try
          {
            MainActivity.this.pd.dismiss();
            MainActivity.this.pd = null;
            return (String)localObject;
          }
          catch (Exception paramVarArgs)
          {
            paramVarArgs.printStackTrace();
          }
        }
      }
      return (String)localObject;
    }
    
    protected void onPostExecute(String paramString)
    {
      try
      {
        MainActivity.this.btnPlay.setImageResource(2130837516);
        System.out.println(">>>>>>>>Song url>>>>>krishna" + MainActivity.songurl);
        com.phonegap.plugins.ebookViewer.PlayerViewer.playercheck = false;
        for (;;)
        {
          try
          {
            MainActivity.mp.start();
            paramString = MainActivity.this.pd;
            if (paramString == null) {}
          }
          catch (Exception paramString)
          {
            System.out.println("error in media player catch by Neeti");
            paramString = MainActivity.this.pd;
            if (paramString == null) {
              continue;
            }
            try
            {
              MainActivity.this.pd.dismiss();
              MainActivity.this.pd = null;
              MainActivity.this.open("Programming not in service.");
            }
            catch (Exception paramString)
            {
              paramString.printStackTrace();
              continue;
            }
            paramString = new URL((String)((HashMap)MainActivity.this.songsList.get(MainActivity.this.currentSongIndex)).get("songImagePath"));
            System.out.println("10");
            paramString = BitmapFactory.decodeStream(paramString.openConnection().getInputStream());
            MainActivity.this.img_song.setImageBitmap(paramString);
            continue;
          }
          try
          {
            MainActivity.this.pd.dismiss();
            MainActivity.this.pd = null;
            MainActivity.mp.setOnErrorListener(new MediaPlayer.OnErrorListener()
            {
              public boolean onError(MediaPlayer paramAnonymousMediaPlayer, int paramAnonymousInt1, int paramAnonymousInt2)
              {
                MainActivity.this.open("Programming not in service.");
                return false;
              }
            });
            MainActivity.this.songTitleLabel.setAdapter(MainActivity.this.mAdapter);
            MainActivity.this.songDescLabel.setText(MainActivity.songArtist);
            if (MainActivity.this.imageBitmap != null)
            {
              MainActivity.this.img_song.setImageBitmap(MainActivity.this.imageBitmap);
              MainActivity.this.imageBitmap = null;
              MainActivity.this.btnPlay.setImageResource(2130837516);
              MainActivity.this.seekBar.setProgress(0);
              MainActivity.this.seekBar.setMax(100);
              MainActivity.this.updateProgressBar();
              return;
            }
          }
          catch (Exception paramString)
          {
            paramString.printStackTrace();
          }
        }
        return;
      }
      catch (Exception paramString) {}
    }
    
    protected void onPreExecute()
    {
      MainActivity.this.pd = new ProgressDialog(MainActivity.this);
      MainActivity.this.pd.setCancelable(false);
      MainActivity.this.pd.setCanceledOnTouchOutside(false);
      MainActivity.this.pd.setIndeterminate(true);
      MainActivity.this.pd.show();
      MainActivity.this.pd.setMessage("Buffering...");
      com.phonegap.plugins.ebookViewer.PlayerViewer.playercheck = false;
      MainActivity.this.mHandler.removeCallbacks(MainActivity.this.mUpdateTimeTask);
    }
  }
  
  public class getImageUrl
    extends AsyncTask<String, Void, String>
  {
    String result = "";
    
    public getImageUrl() {}
    
    public String doInBackground(String... paramVarArgs)
    {
      Object localObject = new DefaultHttpClient();
      StringBuilder localStringBuilder;
      try
      {
        paramVarArgs = new URL(paramVarArgs[0]);
        paramVarArgs = ((HttpClient)localObject).execute(new HttpGet(new URI(paramVarArgs.getProtocol(), paramVarArgs.getHost(), paramVarArgs.getPath(), paramVarArgs.getQuery(), null))).getEntity().getContent();
        localObject = new BufferedReader(new InputStreamReader(paramVarArgs));
        localStringBuilder = new StringBuilder();
        for (;;)
        {
          String str = ((BufferedReader)localObject).readLine();
          if (str == null) {
            break;
          }
          localStringBuilder.append(str + "\n");
        }
        return this.result;
      }
      catch (Exception paramVarArgs) {}
      for (;;)
      {
        paramVarArgs.close();
        this.result = localStringBuilder.toString();
      }
    }
    
    public void onPostExecute(String paramString)
    {
      if ((paramString != null) && (!paramString.equals("")))
      {
        try
        {
          paramString = new JSONObject(this.result);
          if (!paramString.has("track")) {
            break label395;
          }
          paramString = paramString.getJSONObject("track");
          if (!paramString.has("album")) {
            break label282;
          }
          paramString = paramString.getJSONObject("album");
          if (paramString.has("image"))
          {
            paramString = paramString.getJSONArray("image").getJSONObject(3).getString("#text");
            System.out.println("image url=" + paramString);
            paramString = BitmapFactory.decodeStream(new URL(paramString).openConnection().getInputStream());
            if ((MainActivity.this.imageurl == "") || (MainActivity.this.imageurl.length() < 1)) {
              return;
            }
            MainActivity.this.img_song.setImageBitmap(paramString);
            return;
          }
          if ((MainActivity.this.imageurl.equalsIgnoreCase("")) || (MainActivity.this.imageurl.length() == 0) || (MainActivity.this.imageurl == null))
          {
            MainActivity.this.img_song.setImageResource(2130837703);
            return;
          }
        }
        catch (Exception paramString)
        {
          paramString.printStackTrace();
          return;
        }
        paramString = BitmapFactory.decodeStream(new URL(MainActivity.this.imageurl).openConnection().getInputStream());
        if ((MainActivity.this.imageurl != "") && (MainActivity.this.imageurl.length() >= 1))
        {
          MainActivity.this.img_song.setImageBitmap(paramString);
          return;
          label282:
          if ((MainActivity.this.imageurl.equalsIgnoreCase("")) || (MainActivity.this.imageurl.length() == 0) || (MainActivity.this.imageurl == null))
          {
            MainActivity.this.img_song.setImageResource(2130837703);
            return;
          }
          paramString = BitmapFactory.decodeStream(new URL(MainActivity.this.imageurl).openConnection().getInputStream());
          if ((MainActivity.this.imageurl != "") && (MainActivity.this.imageurl.length() >= 1))
          {
            MainActivity.this.img_song.setImageBitmap(paramString);
            return;
            label395:
            if ((MainActivity.this.imageurl.equalsIgnoreCase("")) || (MainActivity.this.imageurl.length() == 0) || (MainActivity.this.imageurl == null))
            {
              MainActivity.this.img_song.setImageResource(2130837703);
              return;
            }
            paramString = BitmapFactory.decodeStream(new URL(MainActivity.this.imageurl).openConnection().getInputStream());
            if ((MainActivity.this.imageurl != "") && (MainActivity.this.imageurl.length() >= 1)) {
              MainActivity.this.img_song.setImageBitmap(paramString);
            }
          }
        }
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\musicplayer\MainActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */