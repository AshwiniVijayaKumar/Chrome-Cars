package com.ons.radio;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Process;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.example.example75f1799f07eb.MyPhoneGapActivity;
import com.ons.radio.data.information;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

@SuppressLint({"NewApi"})
public class PlayerActivity
  extends BaseActivity
{
  private static Button BtnPlay;
  private static Button BtnStop;
  public static final String MyPREFERENCES = "MyPrefs";
  private static final String SIGNAL_AAC = "aac";
  private static TextView artistName;
  private static boolean audiOff;
  public static boolean audioOncall = false;
  public static AudioSignal audioSignal;
  private static TextView radioCounter;
  private static TextView radioStatus;
  static SharedPreferences sharedpreferences;
  public static int status = 0;
  private static TextView trackName;
  private AudioManager AudioMgr;
  private String LOADING;
  String ShowArtist = "no";
  String ShowTrack = "no";
  private UpRe UpRe;
  TextView appName;
  private ImageView backgroundImage;
  private Intent bindIntent;
  private Button bt_mute;
  final Context context = this;
  String disableAutoalbum = "0";
  String enableAutoPlayCheck = "1";
  private boolean flag_mute = false;
  private Handler handler;
  String imageUrl = "";
  private LinearLayout linear;
  private ContentObserver mVolumeObserver;
  private int old_volume_status = 0;
  private TelephonyManager phoneMngr;
  private final ServiceConnection radioConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      PlayerActivity.audioSignal = ((AudioSignal.RadioBinder)paramAnonymousIBinder).getService();
      PlayerActivity.this.updateStatus();
      PlayerActivity.this.updateMetadata();
      if (information.EnableLastFm.equals("yes")) {
        PlayerActivity.this.updateAlbum();
      }
      for (;;)
      {
        PlayerActivity.this.updatePlayTimer();
        paramAnonymousComponentName = information.EnableAutoplay;
        if (!PlayerActivity.this.enableAutoPlayCheck.equalsIgnoreCase("0")) {
          PlayerActivity.audioSignal.play();
        }
        return;
        System.out.println("LastFm is not enable");
      }
    }
    
    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      PlayerActivity.audioSignal = null;
    }
  };
  private TextView radioName;
  private String typeLanguage;
  private SeekBar volControl;
  
  private String getWebResponse(String paramString1, String paramString2)
  {
    String str = "";
    if (paramString2.equals("pls")) {}
    for (paramString2 = "http://apps.appypie.com/mobile_api/pls.php?username=" + paramString1;; paramString2 = "http://apps.appypie.com/mobile_api/m3u.php?username=" + paramString1)
    {
      paramString1 = str;
      try
      {
        Object localObject = new BasicHttpParams();
        paramString1 = str;
        HttpConnectionParams.setConnectionTimeout((HttpParams)localObject, 3000);
        paramString1 = str;
        paramString2 = EntityUtils.toString(new DefaultHttpClient((HttpParams)localObject).execute(new HttpPost(paramString2)).getEntity());
        paramString1 = paramString2;
        str = paramString2.trim();
        paramString2 = str;
        paramString1 = str;
        if (str.length() > 0)
        {
          paramString1 = str;
          paramString2 = str + "\n";
          paramString1 = paramString2;
          Log.e("rsponse >>>>>>>>>>>>>>>>>>>>>>", paramString2);
          paramString1 = paramString2;
          localObject = paramString2.split("\n");
          str = localObject[0];
          paramString1 = str;
          if (str.contains("http://"))
          {
            paramString2 = str;
            paramString1 = str;
            if (str.contains("https://")) {}
          }
          else
          {
            paramString2 = localObject[3];
          }
        }
        return paramString2;
      }
      catch (Exception paramString2)
      {
        paramString2.printStackTrace();
      }
    }
    return paramString1;
  }
  
  private void showDialog(String paramString1, String paramString2)
  {
    AlertDialog localAlertDialog = new AlertDialog.Builder(this.context).create();
    localAlertDialog.setTitle(paramString1);
    localAlertDialog.setMessage(paramString2);
    localAlertDialog.setCanceledOnTouchOutside(false);
    int i;
    if (status > 0) {
      if (status == 1) {
        i = 2130837711;
      }
    }
    for (;;)
    {
      localAlertDialog.setIcon(i);
      localAlertDialog.setButton("OK", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
      });
      localAlertDialog.show();
      return;
      i = 2130837661;
      continue;
      i = 2130837584;
    }
  }
  
  private void unbindDrawables(View paramView)
  {
    try
    {
      if (paramView.getBackground() != null) {
        paramView.getBackground().setCallback(null);
      }
      if ((paramView instanceof ViewGroup))
      {
        int i = 0;
        while (i < ((ViewGroup)paramView).getChildCount())
        {
          unbindDrawables(((ViewGroup)paramView).getChildAt(i));
          i += 1;
        }
        ((ViewGroup)paramView).removeAllViews();
      }
      return;
    }
    catch (Exception paramView)
    {
      paramView.printStackTrace();
    }
  }
  
  public void MusicOn()
  {
    try
    {
      Typeface localTypeface = Typeface.createFromAsset(getAssets(), "main.ttf");
      int i = Integer.parseInt(getResources().getString(2131231094));
      int j = Integer.parseInt(getResources().getString(2131231096));
      int k = Integer.parseInt(getResources().getString(2131231098));
      int m = Integer.parseInt(getResources().getString(2131231100));
      int n = Integer.parseInt(getResources().getString(2131231102));
      BtnPlay = (Button)findViewById(2131558690);
      BtnStop = (Button)findViewById(2131558691);
      BtnPlay.setEnabled(true);
      BtnStop.setEnabled(false);
      this.radioName = ((TextView)findViewById(2131558687));
      this.radioName.setTextColor(Color.parseColor("#FFFFFF"));
      this.radioName.setTypeface(localTypeface);
      this.radioName.setTextSize(2, i);
      radioCounter = (TextView)findViewById(2131558689);
      radioCounter.setTextColor(Color.parseColor("#FFFFFF"));
      radioCounter.setTypeface(localTypeface);
      radioCounter.setTextSize(2, k);
      radioStatus = (TextView)findViewById(2131558688);
      radioStatus.setTextColor(Color.parseColor("#FFFFFF"));
      radioStatus.setTypeface(localTypeface);
      radioStatus.setTextSize(2, j);
      artistName = (TextView)findViewById(2131558693);
      artistName.setTextColor(Color.parseColor("#FFFFFF"));
      artistName.setTypeface(localTypeface);
      if (this.ShowArtist.equals("yes")) {
        artistName.setVisibility(8);
      }
      artistName.setTextSize(2, n);
      trackName = (TextView)findViewById(2131558692);
      trackName.setTextColor(Color.parseColor("#FFFFFF"));
      trackName.setTypeface(localTypeface);
      trackName.setTextSize(2, m);
      if (this.ShowTrack.equals("yes")) {
        trackName.setVisibility(8);
      }
      this.LOADING = getResources().getString(2131231104);
      this.AudioMgr = ((AudioManager)getSystemService("audio"));
      this.bt_mute = ((Button)findViewById(2131558695));
      i = this.AudioMgr.getStreamMaxVolume(3);
      j = this.AudioMgr.getStreamVolume(3);
      this.old_volume_status = j;
      if (j == 0)
      {
        this.bt_mute.setBackgroundResource(2130837675);
        this.flag_mute = true;
      }
      this.bt_mute.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!PlayerActivity.this.flag_mute)
          {
            PlayerActivity.this.bt_mute.setBackgroundResource(2130837675);
            PlayerActivity.access$002(PlayerActivity.this, true);
            PlayerActivity.this.AudioMgr.setStreamVolume(3, 0, 0);
            PlayerActivity.this.volControl.setProgress(0);
            return;
          }
          PlayerActivity.this.bt_mute.setBackgroundResource(2130837676);
          PlayerActivity.access$002(PlayerActivity.this, false);
          PlayerActivity.this.AudioMgr.setStreamVolume(3, PlayerActivity.this.old_volume_status, 0);
          PlayerActivity.this.volControl.setProgress(PlayerActivity.this.old_volume_status);
        }
      });
      this.volControl = ((SeekBar)findViewById(2131558696));
      this.volControl.setMax(i);
      this.volControl.setProgress(j);
      this.volControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
      {
        public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
        {
          PlayerActivity.this.AudioMgr.setStreamVolume(3, paramAnonymousInt, 0);
          if (paramAnonymousInt == 0)
          {
            PlayerActivity.this.bt_mute.setBackgroundResource(2130837675);
            PlayerActivity.access$002(PlayerActivity.this, true);
            return;
          }
          PlayerActivity.access$402(PlayerActivity.this, paramAnonymousInt);
          PlayerActivity.this.bt_mute.setBackgroundResource(2130837676);
          PlayerActivity.access$002(PlayerActivity.this, false);
        }
        
        public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar) {}
        
        public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar) {}
      });
      this.mVolumeObserver = new ContentObserver(new Handler())
      {
        public void onChange(boolean paramAnonymousBoolean)
        {
          super.onChange(paramAnonymousBoolean);
          if ((PlayerActivity.this.volControl != null) && (PlayerActivity.this.AudioMgr != null))
          {
            int i = PlayerActivity.this.AudioMgr.getStreamVolume(3);
            PlayerActivity.this.volControl.setProgress(i);
          }
        }
      };
      if ((audioSignal != null) && (audioSignal.isPlaying())) {
        audioSignal.stop();
      }
      startService(new Intent(this, AudioSignal.class));
      displayAd();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void Off()
  {
    audiOff = true;
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(information.RadioName);
    localBuilder.setMessage(getResources().getString(2131231085));
    localBuilder.setCancelable(true);
    localBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (PlayerActivity.audioSignal != null)
        {
          PlayerActivity.audioSignal.stop();
          PlayerActivity.access$902(true);
          PlayerActivity.this.finish();
          Process.killProcess(Process.myPid());
          return;
        }
        paramAnonymousDialogInterface = new Intent("android.intent.action.MAIN");
        paramAnonymousDialogInterface.addCategory("android.intent.category.HOME");
        paramAnonymousDialogInterface.setFlags(268435456);
        PlayerActivity.this.startActivity(paramAnonymousDialogInterface);
      }
    });
    localBuilder.setNegativeButton("No", null);
    localBuilder.show();
  }
  
  public void StartMusic(View paramView)
  {
    audioSignal.play();
    MyPhoneGapActivity.checkplaying = 0;
  }
  
  public void StopMusic(View paramView)
  {
    audioSignal.stop();
    resetMetadata();
    MyPhoneGapActivity.checkplaying = 1;
  }
  
  public void UpdatetoLogo()
  {
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        LinearLayout localLinearLayout = (LinearLayout)PlayerActivity.this.findViewById(2131558685);
        try
        {
          if ((PlayerActivity.this.imageUrl.equalsIgnoreCase("")) || (PlayerActivity.this.imageUrl.length() == 0) || (PlayerActivity.this.imageUrl == null))
          {
            localLinearLayout.setBackgroundResource(2130837508);
            return;
          }
          localLinearLayout.setBackground(Drawable.createFromStream((InputStream)new URL(PlayerActivity.this.imageUrl).getContent(), "src name"));
          return;
        }
        catch (Exception localException) {}
      }
    }, 2000L);
  }
  
  public void btnShare(View paramView)
  {
    paramView = getResources().getString(2131230720);
    getIntent();
    String str = trackName.getText().toString().trim();
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.TEXT", "I am Listening to " + str + " on " + paramView + " App, Download the App now from Google Play by searching for " + paramView);
    startActivity(Intent.createChooser(localIntent, "Share via"));
  }
  
  public void close(View paramView)
  {
    status = 1;
    finish();
  }
  
  public void displayAd()
  {
    if (information.Enableads.equals("yes")) {
      return;
    }
    LinearLayout localLinearLayout = (LinearLayout)findViewById(2131558460);
    localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
    localLinearLayout.setVisibility(4);
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
    finish();
    status = 1;
  }
  
  /* Error */
  protected void onCreate(android.os.Bundle paramBundle)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial 601	com/ons/radio/BaseActivity:onCreate	(Landroid/os/Bundle;)V
    //   5: aload_0
    //   6: bipush 7
    //   8: invokevirtual 605	com/ons/radio/PlayerActivity:requestWindowFeature	(I)Z
    //   11: pop
    //   12: aload_0
    //   13: ldc 38
    //   15: iconst_0
    //   16: invokevirtual 609	com/ons/radio/PlayerActivity:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   19: putstatic 611	com/ons/radio/PlayerActivity:sharedpreferences	Landroid/content/SharedPreferences;
    //   22: aload_0
    //   23: getstatic 611	com/ons/radio/PlayerActivity:sharedpreferences	Landroid/content/SharedPreferences;
    //   26: ldc_w 613
    //   29: ldc 108
    //   31: invokeinterface 617 3 0
    //   36: putfield 619	com/ons/radio/PlayerActivity:typeLanguage	Ljava/lang/String;
    //   39: getstatic 625	java/lang/System:out	Ljava/io/PrintStream;
    //   42: new 176	java/lang/StringBuilder
    //   45: dup
    //   46: invokespecial 177	java/lang/StringBuilder:<init>	()V
    //   49: ldc_w 627
    //   52: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: aload_0
    //   56: getfield 619	com/ons/radio/PlayerActivity:typeLanguage	Ljava/lang/String;
    //   59: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   65: invokevirtual 632	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   68: aload_0
    //   69: ldc_w 633
    //   72: invokevirtual 636	com/ons/radio/PlayerActivity:setContentView	(I)V
    //   75: aload_0
    //   76: aload_0
    //   77: ldc_w 637
    //   80: invokevirtual 362	com/ons/radio/PlayerActivity:findViewById	(I)Landroid/view/View;
    //   83: checkcast 584	android/widget/LinearLayout
    //   86: putfield 639	com/ons/radio/PlayerActivity:linear	Landroid/widget/LinearLayout;
    //   89: aload_0
    //   90: aload_0
    //   91: ldc_w 640
    //   94: invokevirtual 362	com/ons/radio/PlayerActivity:findViewById	(I)Landroid/view/View;
    //   97: checkcast 642	android/widget/ImageView
    //   100: putfield 644	com/ons/radio/PlayerActivity:backgroundImage	Landroid/widget/ImageView;
    //   103: aload_0
    //   104: getfield 619	com/ons/radio/PlayerActivity:typeLanguage	Ljava/lang/String;
    //   107: ldc_w 646
    //   110: invokevirtual 650	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   113: ifeq +470 -> 583
    //   116: aload_0
    //   117: invokevirtual 654	com/ons/radio/PlayerActivity:getWindow	()Landroid/view/Window;
    //   120: bipush 7
    //   122: ldc_w 655
    //   125: invokevirtual 660	android/view/Window:setFeatureInt	(II)V
    //   128: aload_0
    //   129: invokevirtual 341	com/ons/radio/PlayerActivity:getResources	()Landroid/content/res/Resources;
    //   132: ldc_w 532
    //   135: invokevirtual 348	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   138: astore_1
    //   139: aload_0
    //   140: aload_0
    //   141: ldc_w 661
    //   144: invokevirtual 362	com/ons/radio/PlayerActivity:findViewById	(I)Landroid/view/View;
    //   147: checkcast 371	android/widget/TextView
    //   150: putfield 663	com/ons/radio/PlayerActivity:appName	Landroid/widget/TextView;
    //   153: aload_0
    //   154: invokevirtual 536	com/ons/radio/PlayerActivity:getIntent	()Landroid/content/Intent;
    //   157: ldc_w 665
    //   160: invokevirtual 669	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   163: ldc_w 671
    //   166: invokevirtual 242	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   169: astore_3
    //   170: aload_0
    //   171: aload_3
    //   172: iconst_1
    //   173: aaload
    //   174: putfield 118	com/ons/radio/PlayerActivity:disableAutoalbum	Ljava/lang/String;
    //   177: aload_3
    //   178: iconst_0
    //   179: aaload
    //   180: invokevirtual 228	java/lang/String:length	()I
    //   183: ifle +440 -> 623
    //   186: aload_0
    //   187: getfield 663	com/ons/radio/PlayerActivity:appName	Landroid/widget/TextView;
    //   190: aload_3
    //   191: iconst_0
    //   192: aaload
    //   193: invokevirtual 674	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   196: iconst_0
    //   197: putstatic 92	com/ons/radio/PlayerActivity:status	I
    //   200: aload_0
    //   201: invokevirtual 536	com/ons/radio/PlayerActivity:getIntent	()Landroid/content/Intent;
    //   204: ldc_w 676
    //   207: invokevirtual 669	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   210: ldc_w 678
    //   213: invokevirtual 242	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   216: iconst_0
    //   217: aaload
    //   218: astore 4
    //   220: aload_0
    //   221: aload_0
    //   222: invokevirtual 536	com/ons/radio/PlayerActivity:getIntent	()Landroid/content/Intent;
    //   225: ldc_w 680
    //   228: invokevirtual 669	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   231: putfield 122	com/ons/radio/PlayerActivity:enableAutoPlayCheck	Ljava/lang/String;
    //   234: aload_0
    //   235: aload_0
    //   236: invokevirtual 536	com/ons/radio/PlayerActivity:getIntent	()Landroid/content/Intent;
    //   239: ldc_w 682
    //   242: invokevirtual 669	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   245: putfield 110	com/ons/radio/PlayerActivity:imageUrl	Ljava/lang/String;
    //   248: ldc_w 684
    //   251: aload 4
    //   253: invokestatic 238	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   256: pop
    //   257: aload 4
    //   259: ldc_w 686
    //   262: invokevirtual 248	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   265: istore_2
    //   266: aload 4
    //   268: astore_1
    //   269: iload_2
    //   270: ifeq +93 -> 363
    //   273: aload 4
    //   275: astore_3
    //   276: aload_0
    //   277: aload 4
    //   279: ldc -88
    //   281: invokespecial 688	com/ons/radio/PlayerActivity:getWebResponse	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   284: astore_1
    //   285: aload_1
    //   286: astore 4
    //   288: aload_1
    //   289: astore_3
    //   290: aload_1
    //   291: ldc_w 690
    //   294: invokevirtual 248	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   297: ifeq +27 -> 324
    //   300: aload_1
    //   301: astore_3
    //   302: new 176	java/lang/StringBuilder
    //   305: dup
    //   306: invokespecial 177	java/lang/StringBuilder:<init>	()V
    //   309: aload_1
    //   310: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   313: ldc_w 692
    //   316: invokevirtual 183	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   319: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   322: astore 4
    //   324: aload 4
    //   326: astore_1
    //   327: aload 4
    //   329: astore_3
    //   330: getstatic 698	com/ons/musicplayer/MainActivity:mp	Landroid/media/MediaPlayer;
    //   333: ifnull +30 -> 363
    //   336: aload 4
    //   338: astore_1
    //   339: aload 4
    //   341: astore_3
    //   342: getstatic 698	com/ons/musicplayer/MainActivity:mp	Landroid/media/MediaPlayer;
    //   345: invokevirtual 701	android/media/MediaPlayer:isPlaying	()Z
    //   348: ifeq +15 -> 363
    //   351: aload 4
    //   353: astore_3
    //   354: getstatic 698	com/ons/musicplayer/MainActivity:mp	Landroid/media/MediaPlayer;
    //   357: invokevirtual 702	android/media/MediaPlayer:stop	()V
    //   360: aload 4
    //   362: astore_1
    //   363: aload_1
    //   364: ldc_w 704
    //   367: invokevirtual 248	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   370: istore_2
    //   371: aload_1
    //   372: astore_3
    //   373: iload_2
    //   374: ifeq +51 -> 425
    //   377: aload_1
    //   378: astore 4
    //   380: aload_0
    //   381: aload_1
    //   382: ldc_w 706
    //   385: invokespecial 688	com/ons/radio/PlayerActivity:getWebResponse	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   388: astore_1
    //   389: aload_1
    //   390: astore_3
    //   391: aload_1
    //   392: astore 4
    //   394: getstatic 698	com/ons/musicplayer/MainActivity:mp	Landroid/media/MediaPlayer;
    //   397: ifnull +28 -> 425
    //   400: aload_1
    //   401: astore_3
    //   402: aload_1
    //   403: astore 4
    //   405: getstatic 698	com/ons/musicplayer/MainActivity:mp	Landroid/media/MediaPlayer;
    //   408: invokevirtual 701	android/media/MediaPlayer:isPlaying	()Z
    //   411: ifeq +14 -> 425
    //   414: aload_1
    //   415: astore 4
    //   417: getstatic 698	com/ons/musicplayer/MainActivity:mp	Landroid/media/MediaPlayer;
    //   420: invokevirtual 702	android/media/MediaPlayer:stop	()V
    //   423: aload_1
    //   424: astore_3
    //   425: getstatic 698	com/ons/musicplayer/MainActivity:mp	Landroid/media/MediaPlayer;
    //   428: ifnull +18 -> 446
    //   431: getstatic 698	com/ons/musicplayer/MainActivity:mp	Landroid/media/MediaPlayer;
    //   434: invokevirtual 701	android/media/MediaPlayer:isPlaying	()Z
    //   437: ifeq +9 -> 446
    //   440: getstatic 698	com/ons/musicplayer/MainActivity:mp	Landroid/media/MediaPlayer;
    //   443: invokevirtual 702	android/media/MediaPlayer:stop	()V
    //   446: aload_3
    //   447: putstatic 709	com/ons/radio/data/information:StreamURL	Ljava/lang/String;
    //   450: aload_0
    //   451: invokevirtual 536	com/ons/radio/PlayerActivity:getIntent	()Landroid/content/Intent;
    //   454: ldc_w 711
    //   457: invokevirtual 669	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   460: putstatic 485	com/ons/radio/data/information:RadioName	Ljava/lang/String;
    //   463: ldc_w 713
    //   466: getstatic 709	com/ons/radio/data/information:StreamURL	Ljava/lang/String;
    //   469: invokestatic 238	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   472: pop
    //   473: aload_0
    //   474: new 469	android/content/Intent
    //   477: dup
    //   478: aload_0
    //   479: ldc_w 460
    //   482: invokespecial 472	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   485: putfield 715	com/ons/radio/PlayerActivity:bindIntent	Landroid/content/Intent;
    //   488: aload_0
    //   489: aload_0
    //   490: getfield 715	com/ons/radio/PlayerActivity:bindIntent	Landroid/content/Intent;
    //   493: aload_0
    //   494: getfield 127	com/ons/radio/PlayerActivity:radioConnection	Landroid/content/ServiceConnection;
    //   497: iconst_1
    //   498: invokevirtual 719	com/ons/radio/PlayerActivity:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   501: pop
    //   502: aload_0
    //   503: new 450	android/os/Handler
    //   506: dup
    //   507: invokespecial 451	android/os/Handler:<init>	()V
    //   510: putfield 721	com/ons/radio/PlayerActivity:handler	Landroid/os/Handler;
    //   513: aload_0
    //   514: invokevirtual 723	com/ons/radio/PlayerActivity:MusicOn	()V
    //   517: invokestatic 729	com/nostra13/universalimageloader/core/ImageLoader:getInstance	()Lcom/nostra13/universalimageloader/core/ImageLoader;
    //   520: astore_1
    //   521: new 731	com/nostra13/universalimageloader/core/DisplayImageOptions$Builder
    //   524: dup
    //   525: invokespecial 732	com/nostra13/universalimageloader/core/DisplayImageOptions$Builder:<init>	()V
    //   528: invokevirtual 736	com/nostra13/universalimageloader/core/DisplayImageOptions$Builder:cacheInMemory	()Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;
    //   531: invokevirtual 739	com/nostra13/universalimageloader/core/DisplayImageOptions$Builder:cacheOnDisc	()Lcom/nostra13/universalimageloader/core/DisplayImageOptions$Builder;
    //   534: invokevirtual 743	com/nostra13/universalimageloader/core/DisplayImageOptions$Builder:build	()Lcom/nostra13/universalimageloader/core/DisplayImageOptions;
    //   537: astore_3
    //   538: aload_0
    //   539: getfield 110	com/ons/radio/PlayerActivity:imageUrl	Ljava/lang/String;
    //   542: ifnull +24 -> 566
    //   545: aload_0
    //   546: getfield 639	com/ons/radio/PlayerActivity:linear	Landroid/widget/LinearLayout;
    //   549: aconst_null
    //   550: invokevirtual 747	android/widget/LinearLayout:setBackground	(Landroid/graphics/drawable/Drawable;)V
    //   553: aload_1
    //   554: aload_0
    //   555: getfield 110	com/ons/radio/PlayerActivity:imageUrl	Ljava/lang/String;
    //   558: aload_0
    //   559: getfield 644	com/ons/radio/PlayerActivity:backgroundImage	Landroid/widget/ImageView;
    //   562: aload_3
    //   563: invokevirtual 751	com/nostra13/universalimageloader/core/ImageLoader:displayImage	(Ljava/lang/String;Landroid/widget/ImageView;Lcom/nostra13/universalimageloader/core/DisplayImageOptions;)V
    //   566: new 24	com/ons/radio/PlayerActivity$JSONAsyncTask
    //   569: dup
    //   570: aload_0
    //   571: invokespecial 752	com/ons/radio/PlayerActivity$JSONAsyncTask:<init>	(Lcom/ons/radio/PlayerActivity;)V
    //   574: iconst_0
    //   575: anewarray 170	java/lang/String
    //   578: invokevirtual 755	com/ons/radio/PlayerActivity$JSONAsyncTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   581: pop
    //   582: return
    //   583: aload_0
    //   584: invokevirtual 654	com/ons/radio/PlayerActivity:getWindow	()Landroid/view/Window;
    //   587: bipush 7
    //   589: ldc_w 756
    //   592: invokevirtual 660	android/view/Window:setFeatureInt	(II)V
    //   595: goto -467 -> 128
    //   598: astore_1
    //   599: aload_0
    //   600: aload_0
    //   601: invokevirtual 760	com/ons/radio/PlayerActivity:getApplicationInfo	()Landroid/content/pm/ApplicationInfo;
    //   604: aload_0
    //   605: invokevirtual 764	com/ons/radio/PlayerActivity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   608: invokevirtual 770	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   611: checkcast 170	java/lang/String
    //   614: ldc_w 772
    //   617: invokespecial 774	com/ons/radio/PlayerActivity:showDialog	(Ljava/lang/String;Ljava/lang/String;)V
    //   620: goto -54 -> 566
    //   623: aload_0
    //   624: getfield 663	com/ons/radio/PlayerActivity:appName	Landroid/widget/TextView;
    //   627: aload_1
    //   628: invokevirtual 674	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   631: goto -435 -> 196
    //   634: astore_3
    //   635: aload_0
    //   636: getfield 663	com/ons/radio/PlayerActivity:appName	Landroid/widget/TextView;
    //   639: aload_1
    //   640: invokevirtual 674	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   643: goto -447 -> 196
    //   646: astore_1
    //   647: return
    //   648: astore_1
    //   649: goto -147 -> 502
    //   652: astore_1
    //   653: goto -207 -> 446
    //   656: astore_1
    //   657: aload 4
    //   659: astore_3
    //   660: goto -235 -> 425
    //   663: astore_1
    //   664: aload_3
    //   665: astore_1
    //   666: goto -303 -> 363
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	669	0	this	PlayerActivity
    //   0	669	1	paramBundle	android.os.Bundle
    //   265	109	2	bool	boolean
    //   169	394	3	localObject1	Object
    //   634	1	3	localException	Exception
    //   659	6	3	localObject2	Object
    //   218	440	4	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   75	128	598	java/lang/Exception
    //   128	153	598	java/lang/Exception
    //   196	266	598	java/lang/Exception
    //   363	371	598	java/lang/Exception
    //   502	566	598	java/lang/Exception
    //   583	595	598	java/lang/Exception
    //   635	643	598	java/lang/Exception
    //   153	196	634	java/lang/Exception
    //   623	631	634	java/lang/Exception
    //   566	582	646	java/lang/Exception
    //   446	502	648	java/lang/Exception
    //   425	446	652	java/lang/Exception
    //   380	389	656	java/lang/Exception
    //   394	400	656	java/lang/Exception
    //   405	414	656	java/lang/Exception
    //   417	423	656	java/lang/Exception
    //   276	285	663	java/lang/Exception
    //   290	300	663	java/lang/Exception
    //   302	324	663	java/lang/Exception
    //   330	336	663	java/lang/Exception
    //   342	351	663	java/lang/Exception
    //   354	360	663	java/lang/Exception
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    SharedPreferences.Editor localEditor = PreferenceManager.getDefaultSharedPreferences(this).edit();
    localEditor.putBoolean("first_time", false);
    localEditor.commit();
    if ((audioSignal != null) && (!audioSignal.isPlaying()) && (!audioSignal.isPreparingStarted())) {
      audioSignal.stopService(this.bindIntent);
    }
    unbindDrawables(findViewById(2131558685));
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    this.volControl = ((SeekBar)findViewById(2131558696));
    if (paramInt == 24)
    {
      paramInt = this.volControl.getProgress();
      this.volControl.setProgress(paramInt + 1);
      return true;
    }
    if (paramInt == 25)
    {
      paramInt = this.volControl.getProgress();
      this.volControl.setProgress(paramInt - 1);
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onPause()
  {
    super.onPause();
    if (this.UpRe != null) {
      unregisterReceiver(this.UpRe);
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    if (this.UpRe == null) {
      this.UpRe = new UpRe(null);
    }
    registerReceiver(this.UpRe, new IntentFilter(AudioSignal.MODE_ONE));
    registerReceiver(this.UpRe, new IntentFilter(AudioSignal.MODE_FOURTEEN));
    registerReceiver(this.UpRe, new IntentFilter(AudioSignal.MODE_FIVE));
    registerReceiver(this.UpRe, new IntentFilter(AudioSignal.MODE_TWO));
    registerReceiver(this.UpRe, new IntentFilter(AudioSignal.MODE_THREE));
    registerReceiver(this.UpRe, new IntentFilter(AudioSignal.MODE_FOUR));
    registerReceiver(this.UpRe, new IntentFilter(AudioSignal.MODE_SIX));
    registerReceiver(this.UpRe, new IntentFilter(AudioSignal.MODE_SEVEN));
    registerReceiver(this.UpRe, new IntentFilter(AudioSignal.MODE_EIGHT));
    registerReceiver(this.UpRe, new IntentFilter(AudioSignal.MODE_NINE));
    registerReceiver(this.UpRe, new IntentFilter(AudioSignal.MODE_TEN));
    registerReceiver(this.UpRe, new IntentFilter(AudioSignal.MODE_ELEVEN));
    registerReceiver(this.UpRe, new IntentFilter(AudioSignal.MODE_TWELVE));
    registerReceiver(this.UpRe, new IntentFilter(AudioSignal.MODE_THIRTEEN));
    if ((audioOncall) && (MyPhoneGapActivity.checkplaying == 0) && (audioSignal != null) && (audioSignal.getStatus().contains("buffering")))
    {
      audioSignal.play();
      audioOncall = false;
    }
    if (audioSignal != null) {}
  }
  
  public void resetMetadata()
  {
    String str = audioSignal.getStatus();
    audioSignal.resetMetadata();
    artistName.setText("");
    trackName.setText("");
    radioCounter.setText("00:00");
    radioStatus.setText(str);
  }
  
  public void startOncall()
  {
    audioSignal.play();
  }
  
  public void stopOncall()
  {
    audioSignal.stop();
  }
  
  public void updateAlbum()
  {
    LinearLayout localLinearLayout = (LinearLayout)findViewById(2131558685);
    Object localObject1 = audioSignal.getArtist();
    Object localObject2 = audioSignal.getTrack();
    Bitmap localBitmap = audioSignal.getAlbumCover();
    Log.e("disableAutoalbum==", this.disableAutoalbum);
    try
    {
      if ((this.disableAutoalbum.equalsIgnoreCase("0")) && (localBitmap != null))
      {
        if ((((String)localObject1).equals("")) && (((String)localObject2).equals(""))) {
          return;
        }
        if (this.imageUrl.length() < 1)
        {
          localObject2 = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888);
          Object localObject3 = new BitmapShader(localBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
          localObject1 = new Paint();
          ((Paint)localObject1).setAntiAlias(true);
          ((Paint)localObject1).setShader((Shader)localObject3);
          localObject2 = new Canvas((Bitmap)localObject2);
          localObject3 = new Path();
          ((Path)localObject3).addCircle(('̠' - 1.0F) / 2.0F, ('̠' - 1.0F) / 2.0F, Math.min('̠', '̠') / 2.0F, Path.Direction.CCW);
          ((Paint)localObject1).setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
          ((Paint)localObject1).setStyle(Paint.Style.STROKE);
          ((Canvas)localObject2).clipPath((Path)localObject3);
          ((Canvas)localObject2).drawBitmap(localBitmap, new Rect(0, 0, localBitmap.getWidth(), localBitmap.getHeight()), new Rect(0, 0, 800, 800), null);
          localLinearLayout.setBackgroundDrawable(new BitmapDrawable(localBitmap));
          return;
        }
      }
    }
    catch (Exception localException) {}
  }
  
  public void updateMetadata()
  {
    String str = audioSignal.getArtist();
    artistName.setText(str);
    str = audioSignal.getTrack();
    trackName.setText(str);
  }
  
  public void updatePlayTimer()
  {
    radioCounter.setText(audioSignal.getPlayingTime());
    final Handler localHandler = new Handler();
    new Timer().schedule(new TimerTask()
    {
      public void run()
      {
        localHandler.post(new Runnable()
        {
          public void run()
          {
            PlayerActivity.radioCounter.setText(PlayerActivity.audioSignal.getPlayingTime());
          }
        });
      }
    }, 0L, 1000L);
  }
  
  public void updateStatus()
  {
    String str1 = audioSignal.getStatus();
    String str2 = audioSignal.StationName();
    try
    {
      this.radioName.setText(str2);
    }
    catch (Exception localException2)
    {
      for (;;)
      {
        try
        {
          radioStatus.setText(str1);
          return;
        }
        catch (Exception localException1)
        {
          localException1.printStackTrace();
        }
        localException2 = localException2;
        localException2.printStackTrace();
      }
    }
  }
  
  class JSONAsyncTask
    extends AsyncTask<String, Void, Boolean>
  {
    JSONAsyncTask() {}
    
    protected Boolean doInBackground(String... paramVarArgs)
    {
      try
      {
        paramVarArgs = new HttpGet("https://play.google.com/store/apps/details?id=" + PlayerActivity.this.getApplicationContext().getPackageName());
        if (new DefaultHttpClient().execute(paramVarArgs).getStatusLine().getStatusCode() == 200) {
          return Boolean.valueOf(true);
        }
      }
      catch (IOException paramVarArgs)
      {
        paramVarArgs.printStackTrace();
        return Boolean.valueOf(false);
      }
      catch (Exception paramVarArgs)
      {
        for (;;)
        {
          paramVarArgs.printStackTrace();
        }
      }
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      Button localButton = (Button)PlayerActivity.this.findViewById(2131558694);
      if (paramBoolean.booleanValue())
      {
        localButton.setVisibility(0);
        return;
      }
      localButton.setVisibility(8);
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
    }
  }
  
  private class UpRe
    extends BroadcastReceiver
  {
    private UpRe() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      Log.e("BroadcastReceiver onReceive", paramIntent.getAction());
      if (paramIntent.getAction().equals(AudioSignal.MODE_ONE)) {}
      do
      {
        return;
        if (paramIntent.getAction().equals(AudioSignal.MODE_FOURTEEN))
        {
          PlayerActivity.BtnPlay.setEnabled(true);
          PlayerActivity.BtnStop.setEnabled(false);
          PlayerActivity.BtnStop.setVisibility(4);
          PlayerActivity.BtnPlay.setVisibility(0);
          PlayerActivity.this.updateMetadata();
          PlayerActivity.this.updateStatus();
          if (information.EnableLastFm.equals("yes"))
          {
            PlayerActivity.this.updateAlbum();
            return;
          }
          System.out.println("LastFm is not enable");
          return;
        }
        if (paramIntent.getAction().equals(AudioSignal.MODE_FIVE))
        {
          PlayerActivity.BtnPlay.setVisibility(0);
          PlayerActivity.BtnPlay.setEnabled(true);
          PlayerActivity.BtnStop.setEnabled(false);
          PlayerActivity.BtnStop.setVisibility(4);
          PlayerActivity.this.updateStatus();
          PlayerActivity.this.updateMetadata();
          if (information.EnableLastFm.equals("yes"))
          {
            PlayerActivity.this.updateAlbum();
            return;
          }
          System.out.println("LastFm is not enable");
          return;
        }
        if (paramIntent.getAction().equals(AudioSignal.MODE_TWO))
        {
          PlayerActivity.BtnPlay.setVisibility(4);
          PlayerActivity.BtnPlay.setEnabled(false);
          PlayerActivity.BtnStop.setEnabled(true);
          PlayerActivity.BtnStop.setVisibility(0);
          PlayerActivity.this.updateStatus();
          PlayerActivity.this.updateMetadata();
          if (information.EnableLastFm.equals("yes"))
          {
            PlayerActivity.this.updateAlbum();
            return;
          }
          System.out.println("LastFm is not enable");
          return;
        }
        if (paramIntent.getAction().equals(AudioSignal.MODE_THREE))
        {
          PlayerActivity.BtnPlay.setVisibility(4);
          PlayerActivity.BtnPlay.setEnabled(false);
          PlayerActivity.BtnStop.setEnabled(true);
          PlayerActivity.BtnStop.setVisibility(0);
          PlayerActivity.this.updateStatus();
          PlayerActivity.this.updateMetadata();
          if (information.EnableLastFm.equals("yes"))
          {
            PlayerActivity.this.updateAlbum();
            return;
          }
          System.out.println("LastFm is not enable");
          return;
        }
        if (paramIntent.getAction().equals(AudioSignal.MODE_FOUR))
        {
          PlayerActivity.BtnPlay.setEnabled(true);
          PlayerActivity.BtnStop.setVisibility(4);
          PlayerActivity.BtnStop.setEnabled(false);
          PlayerActivity.BtnPlay.setVisibility(0);
          if (information.EnableLastFm.equals("yes")) {
            PlayerActivity.this.updateAlbum();
          }
          for (;;)
          {
            PlayerActivity.this.updateStatus();
            PlayerActivity.this.updateMetadata();
            return;
            System.out.println("LastFm is not enable");
          }
        }
        if (paramIntent.getAction().equals(AudioSignal.MODE_TEN))
        {
          PlayerActivity.this.updateStatus();
          return;
        }
        if (paramIntent.getAction().equals(AudioSignal.MODE_ELEVEN))
        {
          PlayerActivity.this.updateStatus();
          return;
        }
        if (paramIntent.getAction().equals(AudioSignal.MODE_SIX))
        {
          if (PlayerActivity.audioSignal.getCurrentStationType().equals("aac"))
          {
            PlayerActivity.BtnPlay.setEnabled(false);
            PlayerActivity.BtnPlay.setVisibility(4);
            PlayerActivity.BtnStop.setEnabled(true);
            PlayerActivity.BtnStop.setVisibility(0);
            PlayerActivity.this.updateMetadata();
            if (information.EnableLastFm.equals("yes")) {
              PlayerActivity.this.updateAlbum();
            }
          }
          for (;;)
          {
            PlayerActivity.this.updateStatus();
            return;
            System.out.println("LastFm is not enable");
            continue;
            PlayerActivity.BtnPlay.setEnabled(false);
            PlayerActivity.BtnStop.setEnabled(true);
            PlayerActivity.BtnStop.setVisibility(0);
            PlayerActivity.BtnPlay.setVisibility(4);
            PlayerActivity.this.updateMetadata();
            if (information.EnableLastFm.equals("yes")) {
              PlayerActivity.this.updateAlbum();
            } else {
              System.out.println("LastFm is not enable");
            }
          }
        }
        if (paramIntent.getAction().equals(AudioSignal.MODE_SEVEN))
        {
          PlayerActivity.BtnPlay.setEnabled(true);
          PlayerActivity.BtnPlay.setVisibility(0);
          PlayerActivity.BtnStop.setEnabled(false);
          PlayerActivity.BtnStop.setVisibility(4);
          PlayerActivity.this.updateStatus();
          PlayerActivity.this.updateMetadata();
          if (information.EnableLastFm.equals("yes"))
          {
            PlayerActivity.this.updateAlbum();
            return;
          }
          System.out.println("LastFm is not enable");
          return;
        }
        if (paramIntent.getAction().equals(AudioSignal.MODE_EIGHT))
        {
          PlayerActivity.BtnPlay.setEnabled(true);
          PlayerActivity.BtnStop.setVisibility(4);
          PlayerActivity.BtnStop.setEnabled(false);
          PlayerActivity.BtnPlay.setVisibility(0);
          PlayerActivity.this.updateStatus();
          PlayerActivity.this.updateMetadata();
          if (information.EnableLastFm.equals("yes"))
          {
            PlayerActivity.this.updateAlbum();
            return;
          }
          System.out.println("LastFm is not enable");
          return;
        }
        if (paramIntent.getAction().equals(AudioSignal.MODE_NINE))
        {
          PlayerActivity.BtnPlay.setEnabled(true);
          PlayerActivity.BtnStop.setVisibility(4);
          PlayerActivity.BtnStop.setEnabled(false);
          PlayerActivity.BtnPlay.setVisibility(0);
          PlayerActivity.this.updateStatus();
          PlayerActivity.this.updateMetadata();
          return;
        }
        if (paramIntent.getAction().equals(AudioSignal.MODE_TWELVE))
        {
          PlayerActivity.this.updateMetadata();
          PlayerActivity.this.updateStatus();
          return;
        }
      } while (!paramIntent.getAction().equals(AudioSignal.MODE_THIRTEEN));
      if (information.EnableLastFm.equals("yes"))
      {
        PlayerActivity.this.updateAlbum();
        return;
      }
      System.out.println("LastFm is not enable");
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\radio\PlayerActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */