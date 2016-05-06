package com.example.example75f1799f07eb;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.graphics.Typeface;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.ExifInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.preference.PreferenceManager;
import android.provider.MediaStore.Images.Media;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.HttpAuthHandler;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebView.WebViewTransport;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomButtonsController;
import com.adsdk.sdk.Ad;
import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.BillingProcessor.IBillingHandler;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.appyjump.video.sdk.AppyJumpVideo;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.Callback;
import com.facebook.GraphRequest.GraphJSONObjectCallback;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gcm.GCMRegistrar;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders.AppViewBuilder;
import com.google.android.gms.analytics.Tracker;
import com.newpedometer.Pedometer;
import com.ons.barcodeUI.SimpleScannerActivity;
import com.ons.bellareader.DatabaseHelperAdapterReader;
import com.ons.bellareader.EpubManipulator;
import com.ons.bellareader.EpubReaderMain;
import com.ons.chat.Option;
import com.ons.chat.OptionforCamera;
import com.ons.geofence.MyService;
import com.ons.geofence.NotificationGeofance;
import com.ons.radio.AudioSignal;
import com.ons.radio.PlayerActivity;
import com.radiusnetworks.ibeacon.IBeacon;
import com.radiusnetworks.ibeacon.IBeaconConsumer;
import com.radiusnetworks.ibeacon.IBeaconManager;
import com.radiusnetworks.ibeacon.RangeNotifier;
import com.radiusnetworks.ibeacon.Region;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.epub.EpubReader;
import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaInterfaceImpl;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewImpl;
import org.apache.cordova.engine.SystemWebView;
import org.apache.cordova.engine.SystemWebViewClient;
import org.apache.cordova.engine.SystemWebViewEngine;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@SuppressLint({"NewApi"})
@TargetApi(9)
public class MyPhoneGapActivity
  extends CordovaActivity
  implements BillingProcessor.IBillingHandler, IBeaconConsumer, CordovaInterface
{
  static String AppId;
  static String ApplicationVersion;
  private static final int FILECHOOSER_RESULTCODE = 1;
  public static final String MyFacebookPREFERENCES = "MyFBPrefs";
  public static final String MyPREFERENCES = "MyPrefs";
  private static final String NAMESPACE_CRASH = "http://snappy.appypie.com/services/utility-soap/";
  private static final String SOAP_ACTION = "http://schemas.xmlsoap.org/wsdl/";
  private static final String URL_CRASH = "http://snappy.appypie.com/services/utility-soap/";
  public static CordovaPlugin activityResultCallback;
  public static String apiLevel;
  static String appDomainValue;
  static String appName = null;
  public static int checkplaying = 0;
  public static SystemWebView cwv;
  static WebView cwv1;
  public static String device;
  static SharedPreferences.Editor editor;
  static String fbEmailId;
  static long fbID = 0L;
  static String lastNotification;
  static String lat;
  static String lng;
  public static String mEmail;
  public static String mName;
  public static String manufacturer;
  public static String model;
  public static String osVersion;
  public static String resellerValue;
  static SharedPreferences sharedpreferences;
  static StackTraceElement[] stackTraces;
  public static String status;
  static String traces;
  String AppBackground_Img_Port = "";
  String AppBackground_Land = "";
  String AppBackground_Port = "";
  String AppImgURl_Land = "";
  String AppImgURl_Port = "";
  String AppVersion;
  String Device_Density = "";
  String Device_Oriantation = "";
  int Device_metrics_DensityDpi;
  Element Element_CustomeTag;
  Element Element_CustomeTagBG;
  Element Element_DfaultTag;
  Element Element_DfaultTagBG;
  String HeaderDonloadAction = "0";
  String ImgName = "0";
  String ImgURl = "";
  String ImgURl_Land = "";
  String ImgURl_Port = "";
  int Oriantation_ID;
  File SDCardRoot;
  String SDDirectoryShareIMG = "SDDirectoryShareIMG.jpg";
  private Object activityResultKeepRunning;
  FrameLayout adLayout;
  FrameLayout adLayout1;
  FrameLayout adLayout2;
  private com.google.android.gms.ads.AdView adView;
  String addIdBanner;
  String addIdInterstial;
  private RelativeLayout admobBanner;
  AppyJumpVideo ads;
  String airPushAppId;
  AlertDialogManager alert = new AlertDialogManager();
  String appLanguage = "en";
  public String applicationMadServe;
  public String applicationPlanType;
  private AudioManager audio;
  String autoUpdateStatus = "";
  String autologin = "";
  private ImageButton backward;
  String barFormat;
  String barResult;
  public String beaconStatus = "";
  String[] beaconURLs;
  ArrayList<String> beaconUUID = new ArrayList();
  RelativeLayout bottomDirection;
  ImageView bottomImage;
  private boolean bound = false;
  BillingProcessor bp;
  CallbackManager callbackManager;
  ConnectionDetector cd;
  ConnectionDetector cdConnectionDetector;
  ImageView closeAds;
  ImageView closeAds1;
  ImageView closeAds2;
  protected CordovaInterfaceImpl cordovaInterface = new CordovaInterfaceImpl(this)
  {
    public Object onMessage(String paramAnonymousString, Object paramAnonymousObject)
    {
      if ("onPageFinished".equals(paramAnonymousString)) {}
      return super.onMessage(paramAnonymousString, paramAnonymousObject);
    }
  };
  private CordovaWebView cordovaWebView;
  String currentAddress;
  private WebChromeClient.CustomViewCallback customViewCallback;
  private FrameLayout customViewContainer;
  String deviceModel;
  Document doc;
  String email;
  int exitNum = 0;
  SharedPreferences.Editor fbEditor;
  private SharedPreferences fbPreference;
  TextView feedback;
  File file;
  File filee;
  String firstItem = "";
  boolean flag = false;
  String foldername = "";
  private ImageButton forward;
  Handler h = null;
  String headerstatus = "";
  int height;
  private IBeaconManager iBeaconManager = IBeaconManager.getInstanceForApplication(this);
  ImageView img_down;
  private String inAppNavigation = "";
  String[] inAppURL;
  private Object keepRunning;
  private int lastBeaconMajor = 0;
  private int lastBeaconMinor = 0;
  private String lastBeaconUUID = "";
  private RelativeLayout layout;
  LinearLayout ll;
  private com.adsdk.sdk.banner.AdView mAdView;
  private Camera mCamera;
  Uri mCapturedImageURI;
  Context mContext;
  private View mCustomView;
  Dialog mDialog;
  private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      WakeLocker.acquire(MyPhoneGapActivity.this.getApplicationContext());
      WakeLocker.release();
      MyPhoneGapActivity.this.notifyMessage(paramAnonymousIntent.getExtras().getString("message"));
    }
  };
  private Handler mHandler;
  private int mInterval = 5000;
  AsyncTask<Void, Void, Void> mRegisterTask;
  Runnable mStatusChecker = new Runnable()
  {
    public void run()
    {
      try
      {
        MyPhoneGapActivity.this.updateStatus();
        return;
      }
      finally
      {
        MyPhoneGapActivity.this.mHandler.postDelayed(MyPhoneGapActivity.this.mStatusChecker, MyPhoneGapActivity.this.mInterval);
      }
    }
  };
  private ValueCallback<Uri> mUploadMessage;
  FrameLayout mainLinearLayout;
  String mapAddress;
  String mapLatitide;
  String mapLongitude;
  WebView mapWebView;
  TelephonyManager mgr;
  Preferences mpreferences;
  DatabaseHelper myDbHelper;
  String name;
  public String navigationLayout = "";
  Node nodeAppIdcustomeBG;
  Node nodeAppIdd;
  Node nodeAppIddd;
  Node nodeAppIddfaultBG;
  private String notificationNotes;
  private String notificationTitle;
  ImageView offlinemodeImage;
  ImageView offlinemodeImage1;
  ImageView offlinemodeImage2;
  boolean pauseTrue = false;
  String paymentId = "";
  int paymentStatus = 0;
  ProgressBar pd;
  PhoneStateListener phoneStateListener;
  public int picCheck = 0;
  Runnable r = null;
  private String redirectedUrl;
  private boolean releasing;
  private ImageButton reloadIcon;
  private FrameLayout reloadtwitterheader;
  private int retryCount = 0;
  Runnable runnEx = new Runnable()
  {
    public void run()
    {
      MyPhoneGapActivity.this.exitNum = 0;
    }
  };
  double screenInches;
  Intent servIntent = null;
  TextView share;
  String shareUrl;
  String signupPaymentCheck = "";
  private boolean singlePageCheck = true;
  TextView startNavigation;
  ImageView swipeShare;
  private final ExecutorService threadPool = Executors.newCachedThreadPool();
  RelativeLayout topDirection;
  ImageView topImage;
  TextView topNavigation;
  Typeface typeface;
  String uAId;
  private String webViewFirstURL = "";
  private List<String> webviewURLList = new ArrayList();
  int width;
  
  static
  {
    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
    appDomainValue = "";
    lat = "";
    lng = "";
  }
  
  private void CheckDevice_ScreenResolution()
  {
    Display localDisplay = ((WindowManager)getSystemService("window")).getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localDisplay.getMetrics(localDisplayMetrics);
    this.width = localDisplayMetrics.widthPixels;
    this.height = localDisplayMetrics.heightPixels;
  }
  
  private void Check_Device_Dencity()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.Device_metrics_DensityDpi = localDisplayMetrics.densityDpi;
    if (this.Device_metrics_DensityDpi == 240)
    {
      this.Device_Density = "HDPI";
      return;
    }
    if (this.Device_metrics_DensityDpi == 160)
    {
      this.Device_Density = "MDPI";
      return;
    }
    if (this.Device_metrics_DensityDpi == 320)
    {
      this.Device_Density = "XHDPI";
      return;
    }
    if (this.Device_metrics_DensityDpi == 480)
    {
      this.Device_Density = "XXHDPI";
      return;
    }
    if (this.Device_metrics_DensityDpi == 640)
    {
      this.Device_Density = "XXXHDPI";
      return;
    }
    this.Device_Density = "";
  }
  
  private void Check_Device_Oriantation()
  {
    this.Oriantation_ID = getResources().getConfiguration().orientation;
    switch (this.Oriantation_ID)
    {
    default: 
      this.Device_Oriantation = "";
      return;
    case 2: 
      this.Device_Oriantation = "LANDSCAPE";
      return;
    case 1: 
      this.Device_Oriantation = "PORTRAIT";
      return;
    }
    this.Device_Oriantation = "UNDEFINED";
  }
  
  private void DonloadBGImg()
  {
    this.nodeAppIddfaultBG = this.doc.getElementsByTagName("appData").item(0);
    String str2;
    if (this.nodeAppIddfaultBG.getNodeType() == 1)
    {
      this.Element_DfaultTagBG = ((Element)this.nodeAppIddfaultBG);
      str2 = this.Element_DfaultTagBG.getElementsByTagName("backgroundType").item(0).getTextContent().trim();
      editor = sharedpreferences.edit();
      editor.putString("appbackgroundType", str2);
      editor.apply();
      if ((!str2.equalsIgnoreCase("custom_image")) && (!str2.equalsIgnoreCase("library_image"))) {
        break label531;
      }
      this.nodeAppIdcustomeBG = this.doc.getElementsByTagName("customBackgroundImage").item(0);
      if (this.nodeAppIdcustomeBG.getNodeType() == 1)
      {
        this.Element_CustomeTagBG = ((Element)this.nodeAppIdcustomeBG);
        CheckDeviceAppBackgroundImg();
        this.AppImgURl_Port = GetBGImgeURl_XML(this.AppBackground_Port);
        this.AppImgURl_Land = GetBGImgeURl_XML(this.AppBackground_Land);
        System.out.println("ImgURl_Port :  " + this.AppImgURl_Port + " \n AppImgURl_Land  :  " + this.AppImgURl_Land);
        localObject = sharedpreferences.getString("AppImgURl_Port", "");
        str1 = sharedpreferences.getString("AppImgURl_Land", "");
        System.out.println("ImgURl_Port sharedpreferences :  " + (String)localObject + "\n  ImgURl_Land  sharedpreferences:  " + str1);
        this.SDCardRoot = new File(Environment.getExternalStorageDirectory(), this.foldername + appName);
        if (this.AppImgURl_Port.length() >= 10)
        {
          this.file = new File(this.SDCardRoot, "appbg_port_img.jpg");
          this.filee = new File(this.SDCardRoot, "appbg_land_img.jpg");
          if ((!this.file.exists()) || (!this.filee.exists())) {
            break label500;
          }
          if ((((String)localObject).equals(this.AppImgURl_Port)) && (str1.equals(this.AppImgURl_Land))) {
            break label490;
          }
          this.file.delete();
          this.filee.delete();
          new myAsyncTask_BGImg().execute(new String[] { this.AppImgURl_Port, this.AppImgURl_Land });
        }
      }
    }
    for (;;)
    {
      System.out.println("Abhishek Rai ImgURl_Port   file.exists() ");
      return;
      label490:
      this.ImgName = "1";
    }
    label500:
    new myAsyncTask_BGImg().execute(new String[] { this.AppImgURl_Port, this.AppImgURl_Land });
    return;
    label531:
    String str1 = this.Element_DfaultTag.getElementsByTagName("appBackground").item(0).getTextContent().trim();
    Object localObject = str1;
    if (str1.length() <= 4) {
      localObject = str1 + "fff";
    }
    System.out.println("Abhishek appBarbackgroundColor  Image Not Donload ");
    System.out.println("Headerbar color code :   HeaderbarTextColor  " + (String)localObject);
    editor = sharedpreferences.edit();
    editor.putString("appBarbackgroundColor", (String)localObject);
    editor.putString("appbackgroundType", str2);
    editor.apply();
  }
  
  private void DonloadHeaderImg()
  {
    this.nodeAppIdd = this.doc.getElementsByTagName("appData").item(0);
    Object localObject1;
    Object localObject2;
    Object localObject3;
    if (this.nodeAppIdd.getNodeType() == 1)
    {
      this.Element_DfaultTag = ((Element)this.nodeAppIdd);
      String str2 = this.Element_DfaultTag.getElementsByTagName("navigationBarType").item(0).getTextContent().trim();
      localObject1 = this.Element_DfaultTag.getElementsByTagName("headerBarBackgroundColor").item(0).getTextContent().trim();
      String str1 = this.Element_DfaultTag.getElementsByTagName("headerBarTextColor").item(0).getTextContent().trim();
      localObject2 = localObject1;
      if (((String)localObject1).equals("none")) {
        localObject2 = "#000000";
      }
      if (((String)localObject2).equals("")) {
        break label964;
      }
      if (!((String)localObject2).contains("rgba")) {
        break label854;
      }
      localObject1 = ((String)localObject2).split(",");
      localObject1[0] = localObject1[0].split("\\(")[1];
      localObject1[3] = localObject1[3].split("\\)")[0];
      Integer.toHexString(Integer.parseInt(localObject1[3]));
      localObject3 = Integer.toHexString(Integer.parseInt(localObject1[0]));
      String str3 = Integer.toHexString(Integer.parseInt(localObject1[1]));
      localObject2 = Integer.toHexString(Integer.parseInt(localObject1[2]));
      localObject1 = localObject2;
      if (((String)localObject2).length() == 1)
      {
        localObject1 = localObject2;
        if (AppId.equalsIgnoreCase("6a8e315bd2bd")) {
          localObject1 = "0" + (String)localObject2;
        }
      }
      localObject3 = "#" + (String)localObject3 + str3 + (String)localObject1;
      localObject1 = localObject3;
      if (((String)localObject3).length() <= 4) {
        localObject1 = (String)localObject3 + "000";
      }
      label348:
      localObject2 = str1;
      if (str1.length() <= 4) {
        localObject2 = str1 + "fff";
      }
      System.out.println("Headerbar color code : HeaderBarbackgroundColor  " + (String)localObject1 + "  ,  HeaderbarTextColor  " + (String)localObject2);
      editor = sharedpreferences.edit();
      editor.putString("HeaderBarbackgroundColor", (String)localObject1);
      editor.putString("HeaderbarTextColor", (String)localObject2);
      editor.putString("navigationBarType", str2);
      editor.apply();
      if (!str2.equalsIgnoreCase("image")) {
        break label1039;
      }
      this.nodeAppIddd = this.doc.getElementsByTagName("customHeaderImage").item(0);
      if (this.nodeAppIddd.getNodeType() == 1)
      {
        this.Element_CustomeTag = ((Element)this.nodeAppIddd);
        Header_CheckDevice_ScreenResolutionn();
        this.ImgURl_Port = GetHeaderImgeURl_XML(this.width);
        this.ImgURl_Land = GetHeaderImgeURl_XML(this.height);
        System.out.println("ImgURl_Port :  " + this.ImgURl_Port + " \n ImgURl_Land  :  " + this.ImgURl_Land);
        localObject1 = sharedpreferences.getString("ImgURl_Port", "");
        localObject2 = sharedpreferences.getString("ImgURl_Land", "");
        System.out.println("ImgURl_Port sharedpreferences :  " + (String)localObject1 + "\n  ImgURl_Land  sharedpreferences:  " + (String)localObject2);
        this.SDCardRoot = new File(Environment.getExternalStorageDirectory(), this.foldername + appName);
        if (this.ImgURl_Port.length() >= 10)
        {
          this.file = new File(this.SDCardRoot, "header_port_img.jpg");
          this.filee = new File(this.SDCardRoot, "header_land_img.jpg");
          if ((!this.file.exists()) || (!this.filee.exists())) {
            break label1008;
          }
          if ((((String)localObject1).equals(this.ImgURl_Port)) && (((String)localObject2).equals(this.ImgURl_Land))) {
            break label998;
          }
          this.file.delete();
          this.filee.delete();
          new myAsyncTask_Port().execute(new String[] { this.ImgURl_Port, this.ImgURl_Land });
        }
      }
    }
    for (;;)
    {
      System.out.println("Abhishek Rai ImgURl_Port   file.exists() ");
      return;
      label854:
      localObject3 = localObject2;
      if (!((String)localObject2).contains("rgb")) {
        break;
      }
      localObject3 = ((String)localObject2).split(",");
      localObject3[0] = localObject3[0].split("\\(")[1];
      localObject3[2] = localObject3[2].split("\\)")[0];
      localObject1 = Integer.toHexString(Integer.parseInt(localObject3[0]));
      localObject2 = Integer.toHexString(Integer.parseInt(localObject3[1]));
      localObject3 = Integer.toHexString(Integer.parseInt(localObject3[2]));
      localObject3 = "#" + (String)localObject1 + (String)localObject2 + (String)localObject3;
      break;
      label964:
      localObject1 = localObject2;
      if (((String)localObject2).length() > 4) {
        break label348;
      }
      localObject1 = (String)localObject2 + "000";
      break label348;
      label998:
      this.ImgName = "1";
    }
    label1008:
    new myAsyncTask_Port().execute(new String[] { this.ImgURl_Port, this.ImgURl_Land });
    return;
    label1039:
    System.out.println("Abhishek Header Image Not Donload ");
  }
  
  private String GetBGImgeURl_XML(String paramString)
  {
    localObject2 = "";
    localObject3 = localObject2;
    try
    {
      if (this.doc.getElementsByTagName("customBackgroundImage").item(0) == null) {
        break label1432;
      }
      localObject3 = localObject2;
      if (!paramString.equals("Port_Default_PHONE")) {
        break label278;
      }
      localObject1 = localObject2;
      localObject3 = localObject2;
      if (this.doc.getElementsByTagName("appBackground").item(0) != null)
      {
        localObject3 = localObject2;
        localObject1 = this.Element_CustomeTagBG.getElementsByTagName("appBackground").item(0).getTextContent().trim();
      }
      localObject2 = localObject1;
      localObject3 = localObject1;
      if (((String)localObject1).equalsIgnoreCase(""))
      {
        localObject3 = localObject1;
        Check_Device_Oriantation();
        localObject3 = localObject1;
        if (!this.Device_Oriantation.equals("PORTRAIT")) {
          break label1465;
        }
        localObject3 = localObject1;
        localObject2 = this.Element_DfaultTagBG.getElementsByTagName("appBackground").item(0).getTextContent().trim();
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject1;
        label165:
        label278:
        label1432:
        label1465:
        label1555:
        localObject2 = localObject3;
      }
    }
    System.out.println("bgImgURl  before condition return : " + (String)localObject2);
    if ((((String)localObject2).equalsIgnoreCase("")) || (this.ImgURl.length() <= 10)) {
      if (!paramString.contains("Port")) {
        break label1555;
      }
    }
    for (localObject2 = this.Element_DfaultTagBG.getElementsByTagName("appBackground").item(0).getTextContent().trim();; localObject2 = this.Element_DfaultTagBG.getElementsByTagName("appIpadBackground").item(0).getTextContent().trim())
    {
      System.out.println("bgImgURl  condition return : " + (String)localObject2);
      return (String)localObject2;
      localObject3 = localObject2;
      if (paramString.equals("Land_Default_PHONE"))
      {
        localObject1 = localObject2;
        localObject3 = localObject2;
        if (this.doc.getElementsByTagName("appBackground").item(0) == null) {
          break;
        }
        localObject3 = localObject2;
        localObject1 = this.Element_CustomeTagBG.getElementsByTagName("appBackground").item(0).getTextContent().trim();
        break;
      }
      localObject3 = localObject2;
      if (paramString.equals("Port_Default_TAB"))
      {
        localObject1 = localObject2;
        localObject3 = localObject2;
        if (this.doc.getElementsByTagName("appIpadBackground").item(0) == null) {
          break;
        }
        localObject3 = localObject2;
        localObject1 = this.Element_CustomeTagBG.getElementsByTagName("appIpadBackground").item(0).getTextContent().trim();
        break;
      }
      localObject3 = localObject2;
      if (paramString.equals("Land_Default_TAB"))
      {
        localObject1 = localObject2;
        localObject3 = localObject2;
        if (this.doc.getElementsByTagName("appIpadBackground").item(0) == null) {
          break;
        }
        localObject3 = localObject2;
        localObject1 = this.Element_CustomeTagBG.getElementsByTagName("appIpadBackground").item(0).getTextContent().trim();
        break;
      }
      localObject3 = localObject2;
      if (paramString.equals("Port_MDPI_320x480px"))
      {
        localObject1 = localObject2;
        localObject3 = localObject2;
        if (this.doc.getElementsByTagName("customBackgroundImage").item(0) == null) {
          break;
        }
        localObject3 = localObject2;
        localObject1 = this.Element_CustomeTagBG.getElementsByTagName("bg_320_480").item(0).getTextContent().trim();
        break;
      }
      localObject3 = localObject2;
      if (paramString.equals("Port_HDPI_480x800px"))
      {
        localObject1 = localObject2;
        localObject3 = localObject2;
        if (this.doc.getElementsByTagName("customBackgroundImage").item(0) == null) {
          break;
        }
        localObject3 = localObject2;
        localObject1 = this.Element_CustomeTagBG.getElementsByTagName("bg_480_800").item(0).getTextContent().trim();
        break;
      }
      localObject3 = localObject2;
      if (paramString.equals("Port_XHDPI_720x1280px"))
      {
        localObject1 = localObject2;
        localObject3 = localObject2;
        if (this.doc.getElementsByTagName("customBackgroundImage").item(0) == null) {
          break;
        }
        localObject3 = localObject2;
        localObject1 = this.Element_CustomeTagBG.getElementsByTagName("bg_720_1280").item(0).getTextContent().trim();
        break;
      }
      localObject3 = localObject2;
      if (paramString.equals("Port_TAB_720x1280px"))
      {
        localObject1 = localObject2;
        localObject3 = localObject2;
        if (this.doc.getElementsByTagName("customBackgroundImage").item(0) == null) {
          break;
        }
        localObject3 = localObject2;
        localObject1 = this.Element_CustomeTagBG.getElementsByTagName("bg_720_1280").item(0).getTextContent().trim();
        break;
      }
      localObject3 = localObject2;
      if (paramString.equals("Land_HDPI_800x480px"))
      {
        localObject1 = localObject2;
        localObject3 = localObject2;
        if (this.doc.getElementsByTagName("customBackgroundImage").item(0) == null) {
          break;
        }
        localObject3 = localObject2;
        localObject1 = this.Element_CustomeTagBG.getElementsByTagName("bg_800_480").item(0).getTextContent().trim();
        break;
      }
      localObject3 = localObject2;
      if (paramString.equals("Land_XHDPI_1280x720px"))
      {
        localObject1 = localObject2;
        localObject3 = localObject2;
        if (this.doc.getElementsByTagName("customBackgroundImage").item(0) == null) {
          break;
        }
        localObject3 = localObject2;
        localObject1 = this.Element_CustomeTagBG.getElementsByTagName("bg_1334_750").item(0).getTextContent().trim();
        break;
      }
      localObject3 = localObject2;
      if (paramString.equals("Land_TAB_1280x720px"))
      {
        localObject1 = localObject2;
        localObject3 = localObject2;
        if (this.doc.getElementsByTagName("customBackgroundImage").item(0) == null) {
          break;
        }
        localObject3 = localObject2;
        localObject1 = this.Element_CustomeTagBG.getElementsByTagName("bg_1334_750").item(0).getTextContent().trim();
        break;
      }
      localObject3 = localObject2;
      if (paramString.equals("Port_TAB_600x1024px"))
      {
        localObject1 = localObject2;
        localObject3 = localObject2;
        if (this.doc.getElementsByTagName("customBackgroundImage").item(0) == null) {
          break;
        }
        localObject3 = localObject2;
        localObject1 = this.Element_CustomeTagBG.getElementsByTagName("bg_640_1136").item(0).getTextContent().trim();
        break;
      }
      localObject3 = localObject2;
      if (paramString.equals("Land_TAB_1024x600px"))
      {
        localObject1 = localObject2;
        localObject3 = localObject2;
        if (this.doc.getElementsByTagName("customBackgroundImage").item(0) == null) {
          break;
        }
        localObject3 = localObject2;
        localObject1 = this.Element_CustomeTagBG.getElementsByTagName("bg_1136_640").item(0).getTextContent().trim();
        break;
      }
      localObject3 = localObject2;
      if (paramString.equals("Port_XXHDPI_1080x1920px"))
      {
        localObject1 = localObject2;
        localObject3 = localObject2;
        if (this.doc.getElementsByTagName("customBackgroundImage").item(0) == null) {
          break;
        }
        localObject3 = localObject2;
        localObject1 = this.Element_CustomeTagBG.getElementsByTagName("bg_1242_2208").item(0).getTextContent().trim();
        break;
      }
      localObject3 = localObject2;
      if (paramString.equals("Land_XXHDPI_1920x1080px"))
      {
        localObject1 = localObject2;
        localObject3 = localObject2;
        if (this.doc.getElementsByTagName("customBackgroundImage").item(0) == null) {
          break;
        }
        localObject3 = localObject2;
        localObject1 = this.Element_CustomeTagBG.getElementsByTagName("bg_2208_1242").item(0).getTextContent().trim();
        break;
      }
      localObject3 = localObject2;
      if (paramString.equals("Port_XXXHDPI"))
      {
        localObject1 = localObject2;
        localObject3 = localObject2;
        if (this.doc.getElementsByTagName("customBackgroundImage").item(0) == null) {
          break;
        }
        localObject3 = localObject2;
        localObject1 = this.Element_CustomeTagBG.getElementsByTagName("bg_1536_2048").item(0).getTextContent().trim();
        break;
      }
      localObject1 = localObject2;
      localObject3 = localObject2;
      if (!paramString.equals("Land_XXXHDPI")) {
        break;
      }
      localObject1 = localObject2;
      localObject3 = localObject2;
      if (this.doc.getElementsByTagName("customBackgroundImage").item(0) == null) {
        break;
      }
      localObject3 = localObject2;
      localObject1 = this.Element_CustomeTagBG.getElementsByTagName("bg_2048_1536").item(0).getTextContent().trim();
      break;
      localObject3 = localObject2;
      localObject1 = this.Element_DfaultTagBG.getElementsByTagName("appBackground").item(0).getTextContent().trim();
      break;
      localObject3 = localObject1;
      if (this.doc.getElementsByTagName("nav_header_ipad_image_name").item(0) != null)
      {
        localObject3 = localObject1;
        localObject2 = this.Element_DfaultTagBG.getElementsByTagName("appIpadBackground").item(0).getTextContent().trim();
        break label165;
      }
      localObject3 = localObject1;
      localObject2 = this.Element_DfaultTagBG.getElementsByTagName("appBackground").item(0).getTextContent().trim();
      break label165;
    }
  }
  
  private String GetHeaderImgeURl_XML(int paramInt)
  {
    this.ImgURl = "";
    for (;;)
    {
      try
      {
        if (this.doc.getElementsByTagName("customHeaderImage").item(0) == null) {
          continue;
        }
        if ((paramInt >= 320) && (paramInt < 480))
        {
          if (this.doc.getElementsByTagName("header_320_44").item(0) == null) {
            continue;
          }
          this.ImgURl = this.Element_CustomeTag.getElementsByTagName("header_320_44").item(0).getTextContent().trim();
        }
        if ((paramInt >= 480) && (paramInt < 540))
        {
          if (this.doc.getElementsByTagName("header_320_44").item(0) == null) {
            continue;
          }
          this.ImgURl = this.Element_CustomeTag.getElementsByTagName("header_320_44").item(0).getTextContent().trim();
        }
        if ((paramInt >= 540) && (paramInt < 640))
        {
          if (this.doc.getElementsByTagName("header_480_44").item(0) == null) {
            continue;
          }
          this.ImgURl = this.Element_CustomeTag.getElementsByTagName("header_480_44").item(0).getTextContent().trim();
        }
        if ((paramInt >= 640) && (paramInt < 730))
        {
          if (this.doc.getElementsByTagName("header_640_88").item(0) == null) {
            continue;
          }
          this.ImgURl = this.Element_CustomeTag.getElementsByTagName("header_640_88").item(0).getTextContent().trim();
        }
        if ((paramInt >= 730) && (paramInt < 750))
        {
          if (this.doc.getElementsByTagName("header_720_44").item(0) == null) {
            continue;
          }
          this.ImgURl = this.Element_CustomeTag.getElementsByTagName("header_720_44").item(0).getTextContent().trim();
        }
        if ((paramInt >= 750) && (paramInt < 980))
        {
          if (this.doc.getElementsByTagName("header_750_88").item(0) == null) {
            continue;
          }
          this.ImgURl = this.Element_CustomeTag.getElementsByTagName("header_750_88").item(0).getTextContent().trim();
        }
        if ((paramInt >= 1090) && (paramInt < 1136))
        {
          if (this.doc.getElementsByTagName("header_980_88").item(0) == null) {
            continue;
          }
          this.ImgURl = this.Element_CustomeTag.getElementsByTagName("header_980_88").item(0).getTextContent().trim();
        }
        if ((paramInt >= 1136) && (paramInt < 1242))
        {
          if (this.doc.getElementsByTagName("header_980_88").item(0) == null) {
            continue;
          }
          this.ImgURl = this.Element_CustomeTag.getElementsByTagName("header_980_88").item(0).getTextContent().trim();
        }
        if ((paramInt >= 1242) && (paramInt < 1280))
        {
          if (this.doc.getElementsByTagName("header_1242_132").item(0) == null) {
            continue;
          }
          this.ImgURl = this.Element_CustomeTag.getElementsByTagName("header_1242_132").item(0).getTextContent().trim();
        }
        if ((paramInt >= 1280) && (paramInt < 1334))
        {
          if (this.doc.getElementsByTagName("header_1280_44").item(0) == null) {
            continue;
          }
          this.ImgURl = this.Element_CustomeTag.getElementsByTagName("header_1280_44").item(0).getTextContent().trim();
        }
        if ((paramInt >= 1334) && (paramInt < 1536))
        {
          if (this.doc.getElementsByTagName("header_1242_132").item(0) == null) {
            continue;
          }
          this.ImgURl = this.Element_CustomeTag.getElementsByTagName("header_1242_132").item(0).getTextContent().trim();
        }
        if ((paramInt >= 1536) && (paramInt < 1925))
        {
          if (this.doc.getElementsByTagName("header_1334_88").item(0) == null) {
            continue;
          }
          this.ImgURl = this.Element_CustomeTag.getElementsByTagName("header_1334_88").item(0).getTextContent().trim();
        }
        if ((paramInt >= 1925) && (paramInt < 2048))
        {
          if (this.doc.getElementsByTagName("header_1536_88").item(0) == null) {
            continue;
          }
          this.ImgURl = this.Element_CustomeTag.getElementsByTagName("header_1536_88").item(0).getTextContent().trim();
        }
        if ((paramInt >= 2048) && (paramInt < 2208))
        {
          if (this.doc.getElementsByTagName("header_2048_88").item(0) == null) {
            continue;
          }
          this.ImgURl = this.Element_CustomeTag.getElementsByTagName("header_2048_88").item(0).getTextContent().trim();
        }
        if (paramInt >= 2208)
        {
          if (this.doc.getElementsByTagName("header_2208_132").item(0) == null) {
            continue;
          }
          this.ImgURl = this.Element_CustomeTag.getElementsByTagName("header_2208_132").item(0).getTextContent().trim();
        }
        if (this.ImgURl.equalsIgnoreCase(""))
        {
          Check_Device_Oriantation();
          if (!this.Device_Oriantation.equals("PORTRAIT")) {
            continue;
          }
          this.ImgURl = this.Element_DfaultTag.getElementsByTagName("nav_header_image_name").item(0).getTextContent().trim();
        }
      }
      catch (Exception localException)
      {
        continue;
      }
      System.out.println("ImgURl  before condition return : " + this.ImgURl);
      if ((this.ImgURl.equalsIgnoreCase("")) || (this.ImgURl.length() <= 10)) {
        this.ImgURl = this.Element_DfaultTag.getElementsByTagName("nav_header_image_name").item(0).getTextContent().trim();
      }
      System.out.println("ImgURl  condition return : " + this.ImgURl);
      return this.ImgURl;
      this.ImgURl = this.Element_DfaultTag.getElementsByTagName("nav_header_image_name").item(0).getTextContent().trim();
      continue;
      this.ImgURl = this.Element_DfaultTag.getElementsByTagName("nav_header_image_name").item(0).getTextContent().trim();
      continue;
      this.ImgURl = this.Element_DfaultTag.getElementsByTagName("nav_header_image_name").item(0).getTextContent().trim();
      continue;
      this.ImgURl = this.Element_DfaultTag.getElementsByTagName("nav_header_image_name").item(0).getTextContent().trim();
      continue;
      this.ImgURl = this.Element_DfaultTag.getElementsByTagName("nav_header_image_name").item(0).getTextContent().trim();
      continue;
      this.ImgURl = this.Element_DfaultTag.getElementsByTagName("nav_header_image_name").item(0).getTextContent().trim();
      continue;
      this.ImgURl = this.Element_DfaultTag.getElementsByTagName("nav_header_image_name").item(0).getTextContent().trim();
      continue;
      this.ImgURl = this.Element_DfaultTag.getElementsByTagName("nav_header_image_name").item(0).getTextContent().trim();
      continue;
      this.ImgURl = this.Element_DfaultTag.getElementsByTagName("nav_header_image_name").item(0).getTextContent().trim();
      continue;
      this.ImgURl = this.Element_DfaultTag.getElementsByTagName("nav_header_image_name").item(0).getTextContent().trim();
      continue;
      this.ImgURl = this.Element_DfaultTag.getElementsByTagName("nav_header_image_name").item(0).getTextContent().trim();
      continue;
      this.ImgURl = this.Element_DfaultTag.getElementsByTagName("nav_header_image_name").item(0).getTextContent().trim();
      continue;
      this.ImgURl = this.Element_DfaultTag.getElementsByTagName("nav_header_image_name").item(0).getTextContent().trim();
      continue;
      this.ImgURl = this.Element_DfaultTag.getElementsByTagName("nav_header_image_name").item(0).getTextContent().trim();
      continue;
      this.ImgURl = this.Element_DfaultTag.getElementsByTagName("nav_header_image_name").item(0).getTextContent().trim();
      continue;
      this.ImgURl = this.Element_DfaultTag.getElementsByTagName("nav_header_image_name").item(0).getTextContent().trim();
      continue;
      if (this.doc.getElementsByTagName("nav_header_ipad_image_name").item(0) != null) {
        this.ImgURl = this.Element_DfaultTag.getElementsByTagName("nav_header_ipad_image_name").item(0).getTextContent().trim();
      } else {
        this.ImgURl = this.Element_DfaultTag.getElementsByTagName("nav_header_image_name").item(0).getTextContent().trim();
      }
    }
  }
  
  public static void ShowDirectoryDetails(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    cwv.loadUrl("javascript:createServiceDetailFromMap('" + paramString3 + "',sessionStorage.getItem('servicePageIndex'),'" + paramString1 + "','locationMap','true','" + paramString4 + "');");
  }
  
  public static void autoUpdateCheck(boolean paramBoolean)
  {
    cwv.loadUrl("javascript:autoUpdateApp(" + paramBoolean + ");");
  }
  
  private void changeMarginBottom()
  {
    int i = (int)TypedValue.applyDimension(1, 46.0F, getResources().getDisplayMetrics());
    FrameLayout localFrameLayout = (FrameLayout)findViewById(2131558583);
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)localFrameLayout.getLayoutParams();
    if (isTablet(this))
    {
      if ((this.screenInches > 4.2D) && (this.screenInches < 6.0D)) {
        localMarginLayoutParams.setMargins(0, i, 0, 0);
      }
      for (;;)
      {
        cwv1.setLayoutParams(localMarginLayoutParams);
        return;
        localMarginLayoutParams.setMargins(0, i, 0, 0);
      }
    }
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    float f = localDisplayMetrics.xdpi;
    if (f <= 240.0F) {
      localMarginLayoutParams.setMargins(0, i, 0, 0);
    }
    for (;;)
    {
      localFrameLayout.setLayoutParams(localMarginLayoutParams);
      break;
      if ((f > 240.0F) && (f <= 320.0F)) {
        localMarginLayoutParams.setMargins(0, i, 0, 0);
      } else if ((f > 320.0F) && (f <= 480.0F)) {
        localMarginLayoutParams.setMargins(0, i, 0, 0);
      } else {
        localMarginLayoutParams.setMargins(0, i, 0, 0);
      }
    }
  }
  
  private void changeMarginBottomForMAdServe(String paramString)
  {
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)((RelativeLayout)findViewById(2131558596)).getLayoutParams();
    if (isTablet(this)) {
      if (paramString.equalsIgnoreCase("other")) {
        localMarginLayoutParams.setMargins(0, 20, 0, 0);
      }
    }
    for (;;)
    {
      this.mAdView.setLayoutParams(localMarginLayoutParams);
      return;
      localMarginLayoutParams.setMargins(0, 20, 0, 70);
      continue;
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
      float f = localDisplayMetrics.xdpi;
      if (f <= 240.0F)
      {
        if (paramString.equalsIgnoreCase("other")) {
          localMarginLayoutParams.setMargins(0, 20, 0, 0);
        } else {
          localMarginLayoutParams.setMargins(0, 20, 0, 100);
        }
      }
      else if ((f > 240.0F) && (f <= 320.0F))
      {
        if (paramString.equalsIgnoreCase("other")) {
          localMarginLayoutParams.setMargins(0, 20, 0, 0);
        } else {
          localMarginLayoutParams.setMargins(0, 20, 0, 200);
        }
      }
      else if ((f > 320.0F) && (f <= 480.0F))
      {
        if (paramString.equalsIgnoreCase("other")) {
          localMarginLayoutParams.setMargins(0, 20, 0, 0);
        } else {
          localMarginLayoutParams.setMargins(0, 20, 0, 200);
        }
      }
      else if (paramString.equalsIgnoreCase("other")) {
        localMarginLayoutParams.setMargins(0, 20, 0, 0);
      } else {
        localMarginLayoutParams.setMargins(0, 20, 0, 200);
      }
    }
  }
  
  private void changeMarginBottomMap()
  {
    FrameLayout localFrameLayout = (FrameLayout)findViewById(2131558585);
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)localFrameLayout.getLayoutParams();
    if (isTablet(this))
    {
      if ((this.screenInches > 4.2D) && (this.screenInches < 6.0D)) {
        localMarginLayoutParams.setMargins(0, 35, 0, 0);
      }
      for (;;)
      {
        this.mapWebView.setLayoutParams(localMarginLayoutParams);
        return;
        localMarginLayoutParams.setMargins(0, 40, 0, 0);
      }
    }
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    float f = localDisplayMetrics.xdpi;
    if (f <= 240.0F) {
      localMarginLayoutParams.setMargins(0, 40, 0, 0);
    }
    for (;;)
    {
      localFrameLayout.setLayoutParams(localMarginLayoutParams);
      break;
      if ((f > 240.0F) && (f <= 320.0F)) {
        localMarginLayoutParams.setMargins(0, 52, 0, 0);
      } else if ((f > 320.0F) && (f <= 480.0F)) {
        localMarginLayoutParams.setMargins(0, 75, 0, 0);
      } else {
        localMarginLayoutParams.setMargins(0, 90, 0, 0);
      }
    }
  }
  
  private void changeMarginBottomTop()
  {
    Object localObject = getResources();
    int i = (int)TypedValue.applyDimension(1, 23.0F, ((Resources)localObject).getDisplayMetrics());
    int j = (int)TypedValue.applyDimension(1, 30.0F, ((Resources)localObject).getDisplayMetrics());
    localObject = (FrameLayout)findViewById(2131558583);
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)((FrameLayout)localObject).getLayoutParams();
    if (isTablet(this))
    {
      if ((this.screenInches > 4.2D) && (this.screenInches < 6.0D)) {
        localMarginLayoutParams.setMargins(0, i, 0, j);
      }
      for (;;)
      {
        ((FrameLayout)localObject).setLayoutParams(localMarginLayoutParams);
        cwv1.setLayoutParams(localMarginLayoutParams);
        return;
        localMarginLayoutParams.setMargins(0, i, 0, j);
      }
    }
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    float f = localDisplayMetrics.xdpi;
    if (f <= 240.0F) {
      localMarginLayoutParams.setMargins(0, i, 0, j);
    }
    for (;;)
    {
      ((FrameLayout)localObject).setLayoutParams(localMarginLayoutParams);
      break;
      if ((f > 240.0F) && (f <= 320.0F)) {
        localMarginLayoutParams.setMargins(0, i, 0, j);
      } else if ((f > 320.0F) && (f <= 480.0F)) {
        localMarginLayoutParams.setMargins(0, i, 0, j);
      } else {
        localMarginLayoutParams.setMargins(0, i, 0, j);
      }
    }
  }
  
  private void changeMarginBottomTopForFullWebSite(String paramString)
  {
    Object localObject = getResources();
    int i = (int)TypedValue.applyDimension(1, 30.0F, ((Resources)localObject).getDisplayMetrics());
    if (paramString.contains("fullscreenwesitesinglepageid")) {
      i = (int)TypedValue.applyDimension(1, 0.0F, ((Resources)localObject).getDisplayMetrics());
    }
    paramString = (FrameLayout)findViewById(2131558583);
    localObject = (ViewGroup.MarginLayoutParams)paramString.getLayoutParams();
    if (isTablet(this))
    {
      if ((this.screenInches > 4.2D) && (this.screenInches < 6.0D)) {
        ((ViewGroup.MarginLayoutParams)localObject).setMargins(0, 0, 0, i);
      }
      for (;;)
      {
        paramString.setLayoutParams((ViewGroup.LayoutParams)localObject);
        cwv1.setLayoutParams((ViewGroup.LayoutParams)localObject);
        return;
        ((ViewGroup.MarginLayoutParams)localObject).setMargins(0, 0, 0, i);
      }
    }
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    float f = localDisplayMetrics.xdpi;
    if (f <= 240.0F) {
      ((ViewGroup.MarginLayoutParams)localObject).setMargins(0, 0, 0, i);
    }
    for (;;)
    {
      paramString.setLayoutParams((ViewGroup.LayoutParams)localObject);
      break;
      if ((f > 240.0F) && (f <= 320.0F)) {
        ((ViewGroup.MarginLayoutParams)localObject).setMargins(0, 0, 0, i);
      } else if ((f > 320.0F) && (f <= 480.0F)) {
        ((ViewGroup.MarginLayoutParams)localObject).setMargins(0, 0, 0, i);
      } else {
        ((ViewGroup.MarginLayoutParams)localObject).setMargins(0, 0, 0, i);
      }
    }
  }
  
  private void changeMarginMatrix()
  {
    Resources localResources = getResources();
    int i = (int)TypedValue.applyDimension(1, 46.0F, localResources.getDisplayMetrics());
    int j = (int)TypedValue.applyDimension(1, 30.0F, localResources.getDisplayMetrics());
    int k = (int)TypedValue.applyDimension(1, 17.0F, localResources.getDisplayMetrics());
    int m = (int)TypedValue.applyDimension(1, 15.0F, localResources.getDisplayMetrics());
    int n = (int)TypedValue.applyDimension(1, 60.0F, localResources.getDisplayMetrics());
    FrameLayout localFrameLayout = (FrameLayout)findViewById(2131558583);
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)localFrameLayout.getLayoutParams();
    FrameLayout.LayoutParams localLayoutParams1 = new FrameLayout.LayoutParams(-2, -2);
    localLayoutParams1.setMargins(0, m, n, 0);
    localLayoutParams1.gravity = 5;
    FrameLayout.LayoutParams localLayoutParams2 = new FrameLayout.LayoutParams(-2, -2);
    localLayoutParams2.setMargins(0, m, k, 0);
    localLayoutParams2.gravity = 5;
    FrameLayout.LayoutParams localLayoutParams3 = new FrameLayout.LayoutParams(-2, -2);
    localLayoutParams3.setMargins(k, m, 0, 0);
    localLayoutParams3.gravity = 3;
    if (isTablet(this))
    {
      if ((this.screenInches > 4.2D) && (this.screenInches < 6.0D))
      {
        localMarginLayoutParams.setMargins(0, i, 0, j);
        if (!this.singlePageCheck)
        {
          if (!this.headerstatus.equals("none")) {
            break label326;
          }
          localMarginLayoutParams.setMargins(0, (int)TypedValue.applyDimension(1, 30.0F, localResources.getDisplayMetrics()), 0, 0);
          this.forward.setLayoutParams(localLayoutParams2);
          this.backward.setLayoutParams(localLayoutParams1);
          this.reloadIcon.setLayoutParams(localLayoutParams3);
        }
      }
      for (;;)
      {
        localFrameLayout.setLayoutParams(localMarginLayoutParams);
        cwv1.setLayoutParams(localMarginLayoutParams);
        return;
        localMarginLayoutParams.setMargins(0, i, 0, j);
        break;
        label326:
        i = (int)TypedValue.applyDimension(1, 0.0F, localResources.getDisplayMetrics());
        localLayoutParams1.setMargins(0, 50, 95, 0);
        localLayoutParams2.setMargins(0, 50, 50, 0);
        localLayoutParams3.setMargins(50, 50, 0, 0);
        localMarginLayoutParams.setMargins(0, 50, 0, 0);
        this.forward.setLayoutParams(localLayoutParams2);
        this.backward.setLayoutParams(localLayoutParams1);
        this.reloadIcon.setLayoutParams(localLayoutParams3);
      }
    }
    System.out.println("1");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    float f = localDisplayMetrics.xdpi;
    if (f <= 240.0F)
    {
      localMarginLayoutParams.setMargins(0, i, 0, j);
      label466:
      if (this.singlePageCheck) {
        break label719;
      }
      System.out.println("2");
      if (!this.headerstatus.equals("none")) {
        break label630;
      }
      System.out.println("3");
      localMarginLayoutParams.setMargins(0, (int)TypedValue.applyDimension(1, 30.0F, localResources.getDisplayMetrics()), 0, 0);
      this.forward.setLayoutParams(localLayoutParams2);
      this.backward.setLayoutParams(localLayoutParams1);
      this.reloadIcon.setLayoutParams(localLayoutParams3);
    }
    for (;;)
    {
      localFrameLayout.setLayoutParams(localMarginLayoutParams);
      break;
      if ((f > 240.0F) && (f <= 320.0F))
      {
        localMarginLayoutParams.setMargins(0, i, 0, j);
        break label466;
      }
      if ((f > 320.0F) && (f <= 480.0F))
      {
        localMarginLayoutParams.setMargins(0, i, 0, j);
        break label466;
      }
      localMarginLayoutParams.setMargins(0, i, 0, j);
      break label466;
      label630:
      i = (int)TypedValue.applyDimension(1, 43.0F, localResources.getDisplayMetrics());
      localLayoutParams1.setMargins(0, 150, 95, 0);
      localLayoutParams2.setMargins(0, 150, 50, 0);
      localLayoutParams3.setMargins(50, 150, 0, 0);
      localMarginLayoutParams.setMargins(0, i, 0, 0);
      this.forward.setLayoutParams(localLayoutParams2);
      this.backward.setLayoutParams(localLayoutParams1);
      this.reloadIcon.setLayoutParams(localLayoutParams3);
      continue;
      label719:
      if (this.headerstatus.equals("none"))
      {
        this.forward.setLayoutParams(localLayoutParams2);
        this.backward.setLayoutParams(localLayoutParams1);
        this.reloadIcon.setLayoutParams(localLayoutParams3);
        i = (int)TypedValue.applyDimension(1, 30.0F, localResources.getDisplayMetrics());
        j = (int)TypedValue.applyDimension(1, 22.0F, localResources.getDisplayMetrics());
        if (this.navigationLayout.equalsIgnoreCase("bottom")) {
          localMarginLayoutParams.setMargins(0, j, 0, i);
        } else {
          localMarginLayoutParams.setMargins(0, j, 0, 0);
        }
      }
    }
  }
  
  private void changeMarginMatrixMap()
  {
    FrameLayout localFrameLayout = (FrameLayout)findViewById(2131558585);
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)localFrameLayout.getLayoutParams();
    if (isTablet(this))
    {
      if ((this.screenInches > 4.2D) && (this.screenInches < 6.0D)) {
        localMarginLayoutParams.setMargins(0, 31, 0, 50);
      }
      for (;;)
      {
        localFrameLayout.setLayoutParams(localMarginLayoutParams);
        this.mapWebView.setLayoutParams(localMarginLayoutParams);
        return;
        localMarginLayoutParams.setMargins(0, 35, 0, 30);
      }
    }
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    float f = localDisplayMetrics.xdpi;
    if (f <= 240.0F) {
      localMarginLayoutParams.setMargins(0, 40, 0, 45);
    }
    for (;;)
    {
      localFrameLayout.setLayoutParams(localMarginLayoutParams);
      break;
      if ((f > 240.0F) && (f <= 320.0F)) {
        localMarginLayoutParams.setMargins(0, 55, 0, 60);
      } else if ((f > 320.0F) && (f <= 480.0F)) {
        localMarginLayoutParams.setMargins(0, 115, 0, 95);
      } else {
        localMarginLayoutParams.setMargins(0, 90, 0, 105);
      }
    }
  }
  
  private void changeMarginTop()
  {
    int i = (int)TypedValue.applyDimension(1, 23.0F, getResources().getDisplayMetrics());
    FrameLayout localFrameLayout = (FrameLayout)findViewById(2131558583);
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)localFrameLayout.getLayoutParams();
    if (isTablet(this))
    {
      if ((this.screenInches > 4.2D) && (this.screenInches < 6.0D)) {
        localMarginLayoutParams.setMargins(0, i, 0, 0);
      }
      for (;;)
      {
        cwv1.setLayoutParams(localMarginLayoutParams);
        return;
        localMarginLayoutParams.setMargins(0, i, 0, 0);
      }
    }
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    float f = localDisplayMetrics.xdpi;
    if (f <= 240.0F) {
      localMarginLayoutParams.setMargins(0, i, 0, 0);
    }
    for (;;)
    {
      localFrameLayout.setLayoutParams(localMarginLayoutParams);
      break;
      if ((f > 240.0F) && (f <= 320.0F)) {
        localMarginLayoutParams.setMargins(0, i, 0, 0);
      } else if ((f > 320.0F) && (f <= 480.0F)) {
        localMarginLayoutParams.setMargins(0, i, 0, 0);
      } else {
        localMarginLayoutParams.setMargins(0, i, 0, 0);
      }
    }
  }
  
  private void changeMarginTopFull()
  {
    getResources();
    FrameLayout localFrameLayout = (FrameLayout)findViewById(2131558583);
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)localFrameLayout.getLayoutParams();
    if (isTablet(this))
    {
      if ((this.screenInches > 4.2D) && (this.screenInches < 6.0D)) {
        localMarginLayoutParams.setMargins(0, 0, 0, 0);
      }
      for (;;)
      {
        cwv1.setLayoutParams(localMarginLayoutParams);
        return;
        localMarginLayoutParams.setMargins(0, 0, 0, 0);
      }
    }
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    float f = localDisplayMetrics.xdpi;
    if (f <= 240.0F) {
      localMarginLayoutParams.setMargins(0, 0, 0, 0);
    }
    for (;;)
    {
      localFrameLayout.setLayoutParams(localMarginLayoutParams);
      break;
      if ((f > 240.0F) && (f <= 320.0F)) {
        localMarginLayoutParams.setMargins(0, 0, 0, 0);
      } else if ((f > 320.0F) && (f <= 480.0F)) {
        localMarginLayoutParams.setMargins(0, 0, 0, 0);
      } else {
        localMarginLayoutParams.setMargins(0, 0, 0, 0);
      }
    }
  }
  
  public static void clearNotifiaction()
  {
    System.out.println("krishna1234");
    editor = sharedpreferences.edit();
    editor.putInt("notificationCount", 0);
    editor.apply();
    MyApplicationName.Notification_Count = 0;
    cwv.loadUrl("javascript:clearNotificationBedge();");
  }
  
  private String copyAappypieXMl()
  {
    Log.e("", " inside copyAappypieXMl starts now....." + new Date().getSeconds() / 1000);
    Object localObject2 = getAssets();
    Object localObject1 = new File(getFilesDir(), "appypie.xml");
    if (!((File)localObject1).exists()) {}
    for (;;)
    {
      try
      {
        localObject2 = ((AssetManager)localObject2).open("www/appypie.xml");
        System.out.println("read from asset....appypie.xml" + getFilesDir());
        ((File)localObject1).createNewFile();
        localObject1 = new FileOutputStream((File)localObject1, true);
        Log.e("tag", "Failed to copy asset file: " + "appypie.xml", localIOException1);
      }
      catch (IOException localIOException1)
      {
        try
        {
          copyFile((InputStream)localObject2, (OutputStream)localObject1);
          ((InputStream)localObject2).close();
          ((OutputStream)localObject1).flush();
          ((OutputStream)localObject1).close();
          localObject1 = "done";
          Log.e("", " after copyAappypieXMl starts now....." + new Date().getSeconds() / 1000);
          return (String)localObject1;
        }
        catch (IOException localIOException2)
        {
          String str;
          for (;;) {}
        }
        localIOException1 = localIOException1;
      }
      str = "notedone";
      continue;
      str = "done";
    }
  }
  
  private void copyFile(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        break;
      }
      paramOutputStream.write(arrayOfByte, 0, i);
    }
  }
  
  public static int countSubstring(String paramString1, String paramString2)
  {
    return (paramString2.length() - paramString2.replace(paramString1, "").length()) / paramString1.length();
  }
  
  private void executeAdListener()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        MyPhoneGapActivity.this.uAId = MyPhoneGapActivity.this.getAnalyticsId();
        if ((MyPhoneGapActivity.this.uAId != null) && (MyPhoneGapActivity.this.uAId.trim().length() > 1))
        {
          localObject1 = ((UILApplication)MyPhoneGapActivity.this.getApplication()).getTracker(UILApplication.TrackerName.APP_TRACKER, MyPhoneGapActivity.this.uAId);
          ((Tracker)localObject1).setScreenName(MyPhoneGapActivity.this.getApplicationContext().getString(2131230720));
          ((Tracker)localObject1).send(new HitBuilders.AppViewBuilder().build());
        }
        String str1 = "";
        Object localObject1 = str1;
        Object localObject3;
        Object localObject4;
        try
        {
          localObject3 = MyPhoneGapActivity.this;
          localObject1 = str1;
          localObject4 = MyPhoneGapActivity.this;
          localObject1 = str1;
          str1 = ((TelephonyManager)((MyPhoneGapActivity)localObject3).getSystemService("phone")).getNetworkCountryIso();
          localObject1 = str1;
          new Locale("", str1).getDisplayCountry();
          localObject1 = str1;
          while (!MyPhoneGapActivity.this.applicationMadServe.equalsIgnoreCase("1")) {}
        }
        catch (Exception localException2)
        {
          try
          {
            localObject1 = MyPhoneGapActivity.AppId + "&pub_type=APP&device_name=AND&country=" + (String)localObject1;
            MyPhoneGapActivity.access$3302(MyPhoneGapActivity.this, new com.adsdk.sdk.banner.AdView(MyPhoneGapActivity.this, "http://advertise.appypie.com/md.request.php?pub_id=" + (String)localObject1, "8d5036076ec8e4fb1f8e89ea583a9d39", true, true));
            if (MyPhoneGapActivity.this.applicationPlanType.equalsIgnoreCase("Free")) {
              if (MyPhoneGapActivity.this.navigationLayout.equalsIgnoreCase("bottom"))
              {
                MyPhoneGapActivity.this.changeMarginBottomForMAdServe("bottom");
                MyPhoneGapActivity.this.layout.addView(MyPhoneGapActivity.this.mAdView);
                MyPhoneGapActivity.this.ads = new AppyJumpVideo(MyPhoneGapActivity.this, "112", "31683");
                MyPhoneGapActivity.this.ads.show();
              }
            }
            while (MyPhoneGapActivity.this.applicationPlanType.equalsIgnoreCase("Free")) {
              for (;;)
              {
                MyPhoneGapActivity.this.mAdView.setAdListener(new com.adsdk.sdk.AdListener()
                {
                  public void adClicked() {}
                  
                  public void adClosed(Ad paramAnonymous2Ad, boolean paramAnonymous2Boolean)
                  {
                    MyPhoneGapActivity.this.closeAds1.setVisibility(4);
                  }
                  
                  public void adLoadSucceeded(Ad paramAnonymous2Ad)
                  {
                    MyPhoneGapActivity.this.closeAds1.setVisibility(4);
                  }
                  
                  public void adShown(Ad paramAnonymous2Ad, boolean paramAnonymous2Boolean)
                  {
                    MyPhoneGapActivity.this.closeAds1.setVisibility(4);
                  }
                  
                  public void noAdFound()
                  {
                    MyPhoneGapActivity.this.closeAds1.setVisibility(4);
                  }
                });
                if (MyPhoneGapActivity.this.getIntent().getStringExtra("frompushnotification") != null)
                {
                  System.out.println("SEND TO>>>>>>>>>>" + MyPhoneGapActivity.this.getIntent().getStringExtra("SendTo"));
                  localObject1 = MyPhoneGapActivity.this.getUserStatus();
                  if (((((String)localObject1).equalsIgnoreCase("")) && (MyPhoneGapActivity.this.autologin.equalsIgnoreCase("false"))) || ((((String)localObject1).equalsIgnoreCase("true")) && (MyPhoneGapActivity.this.autologin.equalsIgnoreCase("true"))))
                  {
                    if ((MyPhoneGapActivity.this.getIntent().getStringExtra("SendTo") == null) || (MyPhoneGapActivity.this.getIntent().getStringExtra("SendTo").equals("noPageid")) || (MyPhoneGapActivity.this.getIntent().getStringExtra("SendTo").length() <= 0)) {
                      break label635;
                    }
                    MyPhoneGapActivity.cwv.loadUrl("javascript:openpagefromnotification('" + MyPhoneGapActivity.this.getIntent().getStringExtra("SendTo") + "');");
                  }
                }
                return;
                localException2 = localException2;
                localException2.printStackTrace();
                break;
                MyPhoneGapActivity.this.changeMarginBottomForMAdServe("other");
              }
            }
          }
          catch (Exception localException1)
          {
            localException1.printStackTrace();
            return;
          }
        }
        if (MyPhoneGapActivity.this.navigationLayout.equalsIgnoreCase("bottom")) {
          MyPhoneGapActivity.this.changeMarginBottomForMAdServe("bottom");
        }
        for (;;)
        {
          MyPhoneGapActivity.this.layout.addView(MyPhoneGapActivity.this.mAdView);
          break;
          MyPhoneGapActivity.this.changeMarginBottomForMAdServe("other");
        }
        label635:
        if (MyPhoneGapActivity.this.getIntent().getStringExtra("geofance").equalsIgnoreCase("geofanceAmritesh"))
        {
          Bundle localBundle = new Bundle();
          try
          {
            if (MyPhoneGapActivity.this.getIntent().getStringExtra("message") != null)
            {
              localObject2 = MyPhoneGapActivity.this.getIntent().getStringExtra("message");
              localObject3 = MyPhoneGapActivity.this.getIntent().getStringExtra("webUrl");
              MyPhoneGapActivity.this.getIntent().getStringExtra("geofance");
              MyPhoneGapActivity.this.getIntent().getStringExtra("internalUrl");
              localObject4 = MyPhoneGapActivity.this.getIntent().getStringExtra("fenceMsgImage");
              String str2 = MyPhoneGapActivity.this.getIntent().getStringExtra("frompushnotification");
              localBundle.putString("message", (String)localObject2);
              localBundle.putString("webUrl", (String)localObject3);
              localBundle.putString("fenceMsgImage", (String)localObject4);
              localBundle.putString("frompushnotification", str2);
            }
            Object localObject2 = new Intent(MyPhoneGapActivity.this, NotificationGeofance.class);
            ((Intent)localObject2).putExtras(localBundle);
            MyPhoneGapActivity.this.startActivity((Intent)localObject2);
            return;
          }
          catch (Exception localException3)
          {
            for (;;)
            {
              localException3.printStackTrace();
            }
          }
        }
        MyPhoneGapActivity.this.startActivity(new Intent(MyPhoneGapActivity.this, NotificationListActivity.class).putExtra("frompushnotification", "true"));
      }
    });
  }
  
  private String getAdId()
  {
    Object localObject10 = null;
    Object localObject11 = null;
    Object localObject12 = null;
    Object localObject13 = null;
    String str1 = null;
    Log.e("", " inside getAdId starts now....." + new Date().getSeconds() / 1000);
    String str2 = str1;
    Object localObject6 = localObject10;
    Object localObject7 = localObject11;
    Object localObject8 = localObject12;
    Object localObject9 = localObject13;
    try
    {
      Document localDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new FileInputStream(new File(getFilesDir(), "appypie.xml")));
      str2 = str1;
      localObject6 = localObject10;
      localObject7 = localObject11;
      localObject8 = localObject12;
      localObject9 = localObject13;
      localDocument.getDocumentElement().normalize();
      str2 = str1;
      localObject6 = localObject10;
      localObject7 = localObject11;
      localObject8 = localObject12;
      localObject9 = localObject13;
      str1 = localDocument.getElementsByTagName("appMobIdBanner").item(0).getTextContent().toString();
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      this.addIdInterstial = localDocument.getElementsByTagName("appMobIdInterstitial").item(0).getTextContent().toString().trim();
    }
    catch (DOMException localDOMException)
    {
      for (;;)
      {
        localDOMException.printStackTrace();
        Object localObject1 = str2;
      }
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
      for (;;)
      {
        localParserConfigurationException.printStackTrace();
        Object localObject2 = localObject6;
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
        Object localObject3 = localObject7;
      }
    }
    catch (SAXException localSAXException)
    {
      for (;;)
      {
        localSAXException.printStackTrace();
        Object localObject4 = localObject8;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        Object localObject5 = localObject9;
      }
    }
    if (str1 != null) {
      return str1.trim();
    }
    return "";
  }
  
  private String getAnalyticsId()
  {
    Object localObject10 = null;
    Object localObject11 = null;
    Object localObject12 = null;
    Object localObject13 = null;
    String str1 = null;
    Log.e("", " inside getAnalyticsId starts now....." + new Date().getSeconds() / 1000);
    String str2 = str1;
    Object localObject6 = localObject10;
    Object localObject7 = localObject11;
    Object localObject8 = localObject12;
    Object localObject9 = localObject13;
    try
    {
      Document localDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new FileInputStream(new File(getFilesDir(), "appypie.xml")));
      str2 = str1;
      localObject6 = localObject10;
      localObject7 = localObject11;
      localObject8 = localObject12;
      localObject9 = localObject13;
      localDocument.getDocumentElement().normalize();
      str2 = str1;
      localObject6 = localObject10;
      localObject7 = localObject11;
      localObject8 = localObject12;
      localObject9 = localObject13;
      str1 = localDocument.getElementsByTagName("uaId").item(0).getTextContent().toString();
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      this.applicationPlanType = localDocument.getElementsByTagName("plan").item(0).getTextContent().toString();
    }
    catch (DOMException localDOMException)
    {
      for (;;)
      {
        localDOMException.printStackTrace();
        Object localObject1 = str2;
      }
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
      for (;;)
      {
        localParserConfigurationException.printStackTrace();
        Object localObject2 = localObject6;
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
        Object localObject3 = localObject7;
      }
    }
    catch (SAXException localSAXException)
    {
      for (;;)
      {
        localSAXException.printStackTrace();
        Object localObject4 = localObject8;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        Object localObject5 = localObject9;
      }
    }
    if (str1 != null) {
      return str1;
    }
    return "";
  }
  
  private String getAppId()
  {
    Object localObject10 = null;
    Object localObject11 = null;
    Object localObject12 = null;
    Object localObject13 = null;
    String str1 = null;
    Log.e("", " inside getAppId starts now....." + new Date().getSeconds() / 1000);
    String str2 = str1;
    Object localObject6 = localObject10;
    Object localObject7 = localObject11;
    Object localObject8 = localObject12;
    Object localObject9 = localObject13;
    try
    {
      this.doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new FileInputStream(new File(getFilesDir(), "appypie.xml")));
      str2 = str1;
      localObject6 = localObject10;
      localObject7 = localObject11;
      localObject8 = localObject12;
      localObject9 = localObject13;
      this.doc.getDocumentElement().normalize();
      str2 = str1;
      localObject6 = localObject10;
      localObject7 = localObject11;
      localObject8 = localObject12;
      localObject9 = localObject13;
      Object localObject17 = this.doc.getElementsByTagName("appId").item(0);
      str2 = str1;
      localObject6 = localObject10;
      localObject7 = localObject11;
      localObject8 = localObject12;
      localObject9 = localObject13;
      Node localNode3 = this.doc.getElementsByTagName("reseller").item(0);
      str2 = str1;
      localObject6 = localObject10;
      localObject7 = localObject11;
      localObject8 = localObject12;
      localObject9 = localObject13;
      Node localNode2 = this.doc.getElementsByTagName("version").item(0);
      str2 = str1;
      localObject6 = localObject10;
      localObject7 = localObject11;
      localObject8 = localObject12;
      localObject9 = localObject13;
      Node localNode1 = this.doc.getElementsByTagName("plan").item(0);
      str2 = str1;
      localObject6 = localObject10;
      localObject7 = localObject11;
      localObject8 = localObject12;
      localObject9 = localObject13;
      Object localObject14 = this.doc.getElementsByTagName("lang").item(0);
      str2 = str1;
      localObject6 = localObject10;
      localObject7 = localObject11;
      localObject8 = localObject12;
      localObject9 = localObject13;
      Object localObject15 = this.doc.getElementsByTagName("beacon").item(0);
      str2 = str1;
      localObject6 = localObject10;
      localObject7 = localObject11;
      localObject8 = localObject12;
      localObject9 = localObject13;
      Node localNode4 = this.doc.getElementsByTagName("autoUpdate").item(0);
      str2 = str1;
      localObject6 = localObject10;
      localObject7 = localObject11;
      localObject8 = localObject12;
      localObject9 = localObject13;
      String str3 = ((Element)this.doc.getElementsByTagName("updateSettings").item(0)).getElementsByTagName("enableShare").item(0).getTextContent().toString();
      str2 = str1;
      localObject6 = localObject10;
      localObject7 = localObject11;
      localObject8 = localObject12;
      localObject9 = localObject13;
      System.out.println("Abhishek Rai enable nodeenableSharee : " + str3);
      str2 = str1;
      localObject6 = localObject10;
      localObject7 = localObject11;
      localObject8 = localObject12;
      localObject9 = localObject13;
      String str4 = this.doc.getElementsByTagName("servernameappy").item(0).getTextContent().toString();
      str2 = str1;
      localObject6 = localObject10;
      localObject7 = localObject11;
      localObject8 = localObject12;
      localObject9 = localObject13;
      Object localObject16 = this.doc.getElementsByTagName("pageid").item(0);
      str2 = str1;
      localObject6 = localObject10;
      localObject7 = localObject11;
      localObject8 = localObject12;
      localObject9 = localObject13;
      Node localNode5 = this.doc.getElementsByTagName("pageTextColor").item(0);
      str2 = str1;
      localObject6 = localObject10;
      localObject7 = localObject11;
      localObject8 = localObject12;
      localObject9 = localObject13;
      str1 = ((Node)localObject17).getTextContent().toString();
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      this.singlePageCheck = ((Node)localObject16).getTextContent().toString().contains(",");
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      this.headerstatus = this.doc.getElementsByTagName("navigationBarType").item(0).getTextContent().toString();
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      localObject16 = this.doc.getElementsByTagName("headerBarFont").item(0).getTextContent().toString();
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      this.autologin = this.doc.getElementsByTagName("autoLogin").item(0).getTextContent().toString();
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      localObject11 = this.doc.getElementsByTagName("primaryButtonBgColor").item(0).getTextContent().toString();
      localObject10 = localObject11;
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      if (((String)localObject11).length() <= 4)
      {
        str2 = str1;
        localObject6 = str1;
        localObject7 = str1;
        localObject8 = str1;
        localObject9 = str1;
        localObject10 = (String)localObject11 + "fff";
      }
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      localObject12 = this.doc.getElementsByTagName("secondaryButtonBgColor").item(0).getTextContent().toString();
      localObject11 = localObject12;
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      if (((String)localObject12).length() <= 4)
      {
        str2 = str1;
        localObject6 = str1;
        localObject7 = str1;
        localObject8 = str1;
        localObject9 = str1;
        localObject11 = (String)localObject12 + "fff";
      }
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      localObject12 = this.doc.getElementsByTagName("airPushAppIdAndroid");
      if (localObject12 != null)
      {
        str2 = str1;
        localObject6 = str1;
        localObject7 = str1;
        localObject8 = str1;
        localObject9 = str1;
        if (((NodeList)localObject12).getLength() > 0)
        {
          str2 = str1;
          localObject6 = str1;
          localObject7 = str1;
          localObject8 = str1;
          localObject9 = str1;
          this.airPushAppId = ((NodeList)localObject12).item(0).getTextContent().toString();
        }
      }
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      localObject13 = this.doc.getElementsByTagName("buttonTextColor").item(0).getTextContent().toString();
      localObject12 = localObject13;
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      if (((String)localObject13).length() <= 4)
      {
        str2 = str1;
        localObject6 = str1;
        localObject7 = str1;
        localObject8 = str1;
        localObject9 = str1;
        localObject12 = (String)localObject13 + "fff";
      }
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      localObject17 = this.doc.getElementsByTagName("headerBarTitle").item(0).getTextContent().toString();
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      this.autoUpdateStatus = localNode4.getTextContent().toString();
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      this.applicationPlanType = localNode1.getTextContent().toString();
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      this.beaconStatus = ((Node)localObject15).getTextContent().toString();
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      this.appLanguage = ((Node)localObject14).getTextContent().toString().trim();
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      localObject14 = localNode5.getTextContent().toString();
      localObject13 = localObject14;
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      if (((String)localObject14).length() <= 4)
      {
        str2 = str1;
        localObject6 = str1;
        localObject7 = str1;
        localObject8 = str1;
        localObject9 = str1;
        localObject13 = (String)localObject14 + "fff";
      }
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      localObject15 = this.doc.getElementsByTagName("pageIconColor").item(0).getTextContent().toString();
      localObject14 = localObject15;
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      if (((String)localObject15).length() <= 4)
      {
        str2 = str1;
        localObject6 = str1;
        localObject7 = str1;
        localObject8 = str1;
        localObject9 = str1;
        localObject14 = (String)localObject15 + "fff";
      }
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      editor = sharedpreferences.edit();
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      editor.putString("appPlane", this.applicationPlanType);
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      editor.putString("autoUpdateStatus", this.autoUpdateStatus);
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      editor.putString("appLanguage", this.appLanguage);
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      editor.putString("textColor", (String)localObject13);
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      editor.putString("enableShare", str3);
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      editor.putString("iconColor", (String)localObject14);
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      editor.putString("headerBarTitle", (String)localObject17);
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      editor.putString("headerBarFont", (String)localObject16);
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      editor.putString("primaryButtonBgColor", (String)localObject10);
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      editor.putString("secondaryButtonBgColor", (String)localObject11);
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      editor.putString("buttonTextColor", (String)localObject12);
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      editor.putString("appdomainValue", str4 + "." + localNode3.getTextContent().toString());
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      editor.apply();
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      appDomainValue = str4 + "." + localNode3.getTextContent().toString();
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      System.out.println("krishna appDomainValue>>>" + appDomainValue);
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      resellerValue = localNode3.getTextContent().toString().trim();
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      localObject10 = "http://" + str4 + "." + localNode3.getTextContent().toString() + "/services/angular-soap/";
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      AppId = str1;
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      this.AppVersion = localNode2.getTextContent().toString();
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      ApplicationVersion = localNode2.getTextContent().toString();
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      MyApplicationName.SOAP_URL = (String)localObject10;
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      MyApplicationName.SOAP_NAMESPACE = (String)localObject10;
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      localObject10 = this.doc.getElementsByTagName("ANDAdStatus").item(0);
      if (localNode1 != null)
      {
        str2 = str1;
        localObject6 = str1;
        localObject7 = str1;
        localObject8 = str1;
        localObject9 = str1;
        this.applicationMadServe = ((Node)localObject10).getTextContent().toString();
      }
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      DonloadHeaderImg();
      str2 = str1;
      localObject6 = str1;
      localObject7 = str1;
      localObject8 = str1;
      localObject9 = str1;
      DonloadBGImg();
    }
    catch (DOMException localDOMException)
    {
      for (;;)
      {
        localDOMException.printStackTrace();
        Object localObject1 = str2;
      }
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
      for (;;)
      {
        localParserConfigurationException.printStackTrace();
        Object localObject2 = localObject6;
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
        Object localObject3 = localObject7;
      }
    }
    catch (SAXException localSAXException)
    {
      for (;;)
      {
        localSAXException.printStackTrace();
        Object localObject4 = localObject8;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        Object localObject5 = localObject9;
      }
    }
    if (str1 != null) {
      return str1;
    }
    return "";
  }
  
  private int getDifference(Date paramDate1, Date paramDate2)
  {
    long l1 = paramDate2.getTime() - paramDate1.getTime();
    System.out.println("startDate : " + paramDate1);
    System.out.println("endDate : " + paramDate2);
    System.out.println("different : " + l1);
    long l2 = 1000L * 60L * 60L;
    l1 /= l2;
    Log.e("hours is : ", "" + l1);
    return (int)l1;
  }
  
  private void getFenceData()
  {
    for (;;)
    {
      int i;
      try
      {
        Log.v("amriteshlatlong", "MyPhoneGap :  getFenceData()");
        Object localObject1 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new FileInputStream(new File(getFilesDir(), "appypie.xml")));
        ((Document)localObject1).getDocumentElement().normalize();
        if (((Document)localObject1).getElementsByTagName("fence").item(0).getTextContent().toString().equals("true"))
        {
          JSONArray localJSONArray = new JSONArray();
          localObject1 = ((Document)localObject1).getElementsByTagName("fencingData");
          i = 0;
          if (i < ((NodeList)localObject1).getLength())
          {
            Object localObject2 = ((NodeList)localObject1).item(i);
            if (((Node)localObject2).getNodeType() == 1)
            {
              localObject2 = (Element)localObject2;
              JSONObject localJSONObject = new JSONObject();
              Log.v("amriteshPush", "" + ((Element)localObject2).getElementsByTagName("fenceMessage").item(0).getTextContent());
              localJSONObject.put("fenceMessage", ((Element)localObject2).getElementsByTagName("fenceMessage").item(0).getTextContent());
              localJSONObject.put("latitudeFence0", ((Element)localObject2).getElementsByTagName("latitudeFence0").item(0).getTextContent());
              localJSONObject.put("longitudeFence0", ((Element)localObject2).getElementsByTagName("longitudeFence0").item(0).getTextContent());
              localJSONObject.put("latitudeFence1", ((Element)localObject2).getElementsByTagName("latitudeFence1").item(0).getTextContent());
              localJSONObject.put("longitudeFence1", ((Element)localObject2).getElementsByTagName("longitudeFence1").item(0).getTextContent());
              localJSONObject.put("latitudeFence2", ((Element)localObject2).getElementsByTagName("latitudeFence2").item(0).getTextContent());
              localJSONObject.put("longitudeFence2", ((Element)localObject2).getElementsByTagName("longitudeFence2").item(0).getTextContent());
              localJSONObject.put("fenceMsgUrl", ((Element)localObject2).getElementsByTagName("fenceMsgUrl").item(0).getTextContent());
              localJSONObject.put("fenceMsgInternalUrl", ((Element)localObject2).getElementsByTagName("fenceMsgInternalUrl").item(0).getTextContent());
              localJSONObject.put("fenceMsgImage", ((Element)localObject2).getElementsByTagName("fenceMsgImage").item(0).getTextContent());
              localJSONObject.put("fenceMsgSound", ((Element)localObject2).getElementsByTagName("fenceMsgSound").item(0).getTextContent());
              localJSONObject.put("pushTimeZone", ((Element)localObject2).getElementsByTagName("pushTimeZone").item(0).getTextContent());
              localJSONObject.put("pushScheduleDate", ((Element)localObject2).getElementsByTagName("pushScheduleDate").item(0).getTextContent());
              localJSONObject.put("pushScheduleMon", ((Element)localObject2).getElementsByTagName("pushScheduleMon").item(0).getTextContent());
              localJSONObject.put("pushScheduleTue", ((Element)localObject2).getElementsByTagName("pushScheduleTue").item(0).getTextContent());
              localJSONObject.put("pushScheduleWed", ((Element)localObject2).getElementsByTagName("pushScheduleWed").item(0).getTextContent());
              localJSONObject.put("pushScheduleThur", ((Element)localObject2).getElementsByTagName("pushScheduleThur").item(0).getTextContent());
              localJSONObject.put("pushScheduleFri", ((Element)localObject2).getElementsByTagName("pushScheduleFri").item(0).getTextContent());
              localJSONObject.put("pushScheduleSat", ((Element)localObject2).getElementsByTagName("pushScheduleSat").item(0).getTextContent());
              localJSONObject.put("pushScheduleSun", ((Element)localObject2).getElementsByTagName("pushScheduleSun").item(0).getTextContent());
              localJSONArray.put(i, localJSONObject);
            }
          }
          else
          {
            editor.putString("data", localJSONArray.toString());
            editor.putString("datalength", String.valueOf(localJSONArray.length()));
            editor.apply();
            if (!isMyServiceRunning(MyService.class))
            {
              this.servIntent = new Intent(this, MyService.class);
              startService(this.servIntent);
            }
            System.out.println("ishan----" + localJSONArray.toString());
          }
        }
        else
        {
          return;
        }
      }
      catch (DOMException localDOMException)
      {
        localDOMException.printStackTrace();
        return;
      }
      catch (ParserConfigurationException localParserConfigurationException)
      {
        localParserConfigurationException.printStackTrace();
        return;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        return;
      }
      catch (SAXException localSAXException)
      {
        localSAXException.printStackTrace();
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      i += 1;
    }
  }
  
  public static String getRealPathFromUri(Context paramContext, Uri paramUri)
  {
    Context localContext = null;
    try
    {
      paramContext = paramContext.getContentResolver().query(paramUri, new String[] { "_data" }, null, null, null);
      localContext = paramContext;
      int i = paramContext.getColumnIndexOrThrow("_data");
      localContext = paramContext;
      paramContext.moveToFirst();
      localContext = paramContext;
      paramUri = paramContext.getString(i);
      return paramUri;
    }
    finally
    {
      if (localContext != null) {
        localContext.close();
      }
    }
  }
  
  private String getResponseFromUrl(String paramString)
  {
    String str3 = "";
    Object localObject1 = new DefaultHttpClient();
    Object localObject2 = new HttpGet(paramString);
    paramString = str3;
    String str1 = str3;
    try
    {
      localObject1 = ((HttpClient)localObject1).execute((HttpUriRequest)localObject2).getEntity().getContent();
      paramString = str3;
      str1 = str3;
      localObject2 = new BufferedReader(new InputStreamReader((InputStream)localObject1, "iso-8859-1"), 8);
      paramString = str3;
      str1 = str3;
      StringBuilder localStringBuilder = new StringBuilder();
      for (;;)
      {
        paramString = str3;
        str1 = str3;
        String str4 = ((BufferedReader)localObject2).readLine();
        if (str4 == null) {
          break;
        }
        paramString = str3;
        str1 = str3;
        localStringBuilder.append(str4);
      }
      String str2;
      return str2;
    }
    catch (ClientProtocolException localClientProtocolException)
    {
      localClientProtocolException.printStackTrace();
      return paramString;
      paramString = str3;
      str2 = str3;
      str3 = localStringBuilder.toString();
      paramString = str3;
      str2 = str3;
      ((InputStream)localObject1).close();
      return str3;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  private String getRightAngleImage(String paramString)
  {
    for (;;)
    {
      try
      {
        String str;
        if (paramString.contains(":")) {
          str = paramString.split(":")[1];
        }
        int i;
        switch (new ExifInterface(str).getAttributeInt("Orientation", 1))
        {
        case 1: 
          str = rotateImage(i, paramString);
          return str;
          str = paramString;
          continue;
          i = 0;
          break;
        case 6: 
          i = 90;
          break;
        case 3: 
          i = 180;
          break;
        case 8: 
          i = 270;
          break;
        case 0: 
          i = 0;
          break;
        case 2: 
        case 4: 
        case 5: 
        case 7: 
        default: 
          i = 90;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return paramString;
      }
    }
  }
  
  private String getRightAngleImage1(String paramString)
  {
    for (;;)
    {
      try
      {
        switch (new ExifInterface(paramString.split(":")[1]).getAttributeInt("Orientation", 1))
        {
        case 1: 
          String str = rotateImage1(i, paramString);
          return str;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return paramString;
      }
      int i = 0;
      continue;
      i = 90;
      continue;
      i = 180;
      continue;
      i = 270;
      continue;
      i = 0;
      continue;
      i = 90;
    }
  }
  
  public static void getStackValues(String paramString)
  {
    if ((!appName.equals("")) && (!AppId.equals(""))) {
      webServiceCall(osVersion, apiLevel, model, device, manufacturer, "Android", appName, AppId, ApplicationVersion, paramString);
    }
  }
  
  private boolean isCallable(Intent paramIntent)
  {
    return getPackageManager().queryIntentActivities(paramIntent, 65536).size() > 0;
  }
  
  private boolean isCapable()
  {
    boolean bool2 = false;
    FeatureInfo[] arrayOfFeatureInfo = getActivity().getPackageManager().getSystemAvailableFeatures();
    int j = arrayOfFeatureInfo.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        if ("android.hardware.camera.flash".equalsIgnoreCase(arrayOfFeatureInfo[i].name)) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  private boolean isMyServiceRunning(Class<?> paramClass)
  {
    Iterator localIterator = ((ActivityManager)getSystemService("activity")).getRunningServices(Integer.MAX_VALUE).iterator();
    while (localIterator.hasNext())
    {
      ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)localIterator.next();
      if (paramClass.getName().equals(localRunningServiceInfo.service.getClassName())) {
        return true;
      }
    }
    return false;
  }
  
  public static void loadFirstPage()
  {
    cwv.loadUrl("javascript:loadFirstPage();");
  }
  
  public static void loadSearchPage(String paramString)
  {
    System.out.println("indexValue====" + paramString);
    cwv.loadUrl("javascript:openPage('" + paramString + "');");
  }
  
  public static void logoutFromApp()
  {
    try
    {
      if (fbEmailId == null)
      {
        long l = fbID;
        if (l == 0L) {}
      }
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    cwv.loadUrl("javascript:logoutFromApp();");
  }
  
  private void makeMeRequest(final String paramString)
  {
    paramString = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback()
    {
      public void onCompleted(JSONObject paramAnonymousJSONObject, GraphResponse paramAnonymousGraphResponse)
      {
        Object localObject2;
        Object localObject1;
        if (paramAnonymousJSONObject != null)
        {
          localObject2 = "";
          Log.e("Facebook", "Data " + paramAnonymousJSONObject.toString());
          localObject1 = localObject2;
          try
          {
            MyPhoneGapActivity.fbID = paramAnonymousJSONObject.getLong("id");
            localObject1 = localObject2;
            Log.e("Facebook", "FB User ID ....." + MyPhoneGapActivity.fbID);
            paramAnonymousGraphResponse = (GraphResponse)localObject2;
            localObject1 = localObject2;
            if (paramAnonymousJSONObject.getString("name") != null)
            {
              localObject1 = localObject2;
              paramAnonymousGraphResponse = paramAnonymousJSONObject.getString("name");
            }
            localObject2 = paramAnonymousGraphResponse;
            localObject1 = paramAnonymousGraphResponse;
            if (paramAnonymousJSONObject.getString("email") != null)
            {
              localObject1 = paramAnonymousGraphResponse;
              MyPhoneGapActivity.fbEmailId = paramAnonymousJSONObject.getString("email");
              localObject1 = paramAnonymousGraphResponse;
              Log.e("Facebook", "Login Email ID ....." + MyPhoneGapActivity.fbEmailId);
              localObject2 = paramAnonymousGraphResponse;
            }
          }
          catch (JSONException paramAnonymousJSONObject)
          {
            do
            {
              for (;;)
              {
                Log.d("UserDetail", "Error parsing returned user data. " + paramAnonymousJSONObject);
                localObject2 = localObject1;
              }
            } while (!paramString.equalsIgnoreCase("socialapp"));
            MyPhoneGapActivity.cwv.loadUrl("javascript:callAccessSocial('" + (String)localObject2 + "','" + MyPhoneGapActivity.fbEmailId + "')");
            return;
          }
          MyPhoneGapActivity.this.fbEditor = MyPhoneGapActivity.this.fbPreference.edit();
          MyPhoneGapActivity.this.fbEditor.putLong("FBID", MyPhoneGapActivity.fbID);
          MyPhoneGapActivity.this.fbEditor.putString("FBEMAILID", MyPhoneGapActivity.fbEmailId);
          MyPhoneGapActivity.this.fbEditor.putString("FBNAME", (String)localObject2);
          MyPhoneGapActivity.this.fbEditor.apply();
          if (paramString.equalsIgnoreCase("FbLogin")) {
            MyPhoneGapActivity.cwv.loadUrl("javascript:LoginWithFBUserID(\"" + (String)localObject2 + "\",\"" + MyPhoneGapActivity.fbEmailId + "\",\"" + MyPhoneGapActivity.fbID + "\")");
          }
        }
        while (paramAnonymousGraphResponse.getError() == null) {
          return;
        }
        switch (MyPhoneGapActivity.39.$SwitchMap$com$facebook$FacebookRequestError$Category[paramAnonymousGraphResponse.getError().getCategory().ordinal()])
        {
        default: 
          return;
        case 1: 
          Log.d("UserDetail", "Authentication error: " + paramAnonymousGraphResponse.getError());
          return;
        case 2: 
          Log.d("UserDetail", "Transient error. Try again. " + paramAnonymousGraphResponse.getError());
          return;
        }
        Log.d("UserDetail", "Some other error: " + paramAnonymousGraphResponse.getError());
      }
    });
    Bundle localBundle = new Bundle();
    localBundle.putString("fields", "id,email,gender,name");
    paramString.setParameters(localBundle);
    paramString.executeAsync();
  }
  
  private String parseXmlUpdateData()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      Object localObject2 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new FileInputStream(new File(getFilesDir(), "appypie.xml")));
      ((Document)localObject2).getDocumentElement().normalize();
      Object localObject1 = ((Document)localObject2).getElementsByTagName("updateSettings").item(0);
      localObject2 = ((Document)localObject2).getElementsByTagName("appData").item(0);
      if (((Node)localObject1).getNodeType() == 1)
      {
        localObject1 = (Element)localObject1;
        localJSONObject.put("appUpdate", ((Element)localObject1).getElementsByTagName("appUpdate").item(0).getTextContent().trim());
        localJSONObject.put("autoUpdate", ((Element)localObject1).getElementsByTagName("autoUpdatePage").item(0).getTextContent().trim());
        localJSONObject.put("appUpdateAvailable", ((Element)localObject1).getElementsByTagName("appUpdateAvailable").item(0).getTextContent().trim());
        localJSONObject.put("updateButton", ((Element)localObject1).getElementsByTagName("updateButton").item(0).getTextContent().trim());
        localJSONObject.put("rateAndShare", ((Element)localObject1).getElementsByTagName("rateAndShare").item(0).getTextContent().trim());
        localJSONObject.put("appUpdateAvailableDesc", ((Element)localObject1).getElementsByTagName("appUpdateAvailableDesc").item(0).getTextContent().trim());
        localJSONObject.put("rateAndShareDesc", ((Element)localObject1).getElementsByTagName("rateAndShareDesc").item(0).getTextContent().trim());
        localJSONObject.put("rateNow", ((Element)localObject1).getElementsByTagName("rateNow").item(0).getTextContent().trim());
        localJSONObject.put("shareNow", ((Element)localObject1).getElementsByTagName("shareNow").item(0).getTextContent().trim());
        localJSONObject.put("applicationUpdated", ((Element)localObject1).getElementsByTagName("applicationUpdated").item(0).getTextContent().trim());
        localJSONObject.put("date", ((Element)localObject1).getElementsByTagName("date").item(0).getTextContent().trim());
        localJSONObject.put("lastNotification", ((Element)localObject1).getElementsByTagName("lastNotification").item(0).getTextContent().trim());
        localJSONObject.put("noUpdateAbailable", ((Element)localObject1).getElementsByTagName("noUpdateAbailable").item(0).getTextContent().trim());
      }
      if (((Node)localObject2).getNodeType() == 1)
      {
        localObject1 = (Element)localObject2;
        localJSONObject.put("appBackground", ((Element)localObject1).getElementsByTagName("appBackground").item(0).getTextContent().trim());
        localJSONObject.put("appId", ((Element)localObject1).getElementsByTagName("appId").item(0).getTextContent().trim());
        localJSONObject.put("appTheme", ((Element)localObject1).getElementsByTagName("appTheme").item(0).getTextContent().trim());
      }
    }
    catch (DOMException localDOMException)
    {
      for (;;)
      {
        localDOMException.printStackTrace();
      }
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
      for (;;)
      {
        localParserConfigurationException.printStackTrace();
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
      }
    }
    catch (SAXException localSAXException)
    {
      for (;;)
      {
        localSAXException.printStackTrace();
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
    return localJSONObject.toString();
  }
  
  private void releaseCamera()
  {
    this.releasing = true;
    new Thread(new Runnable()
    {
      public void run()
      {
        MyPhoneGapActivity.this.mCamera.setPreviewCallback(null);
        MyPhoneGapActivity.this.mCamera.stopPreview();
        MyPhoneGapActivity.this.mCamera.release();
        MyPhoneGapActivity.access$4002(MyPhoneGapActivity.this, false);
      }
    }).start();
  }
  
  private String rotateImage(int paramInt, String paramString)
  {
    if (paramInt <= 0) {
      return paramString;
    }
    for (;;)
    {
      Object localObject3;
      try
      {
        Object localObject1 = new File(paramString);
        if (!((File)localObject1).exists()) {
          break;
        }
        localObject2 = BitmapFactory.decodeFile(((File)localObject1).getAbsolutePath());
        localObject3 = new Matrix();
        float f = 'ÿ' / ((Bitmap)localObject2).getWidth();
        ((Matrix)localObject3).postRotate(paramInt);
        ((Matrix)localObject3).postScale(f, f);
        localObject1 = localObject2;
        if (((Bitmap)localObject2).getWidth() > ((Bitmap)localObject2).getHeight()) {
          localObject1 = Bitmap.createBitmap((Bitmap)localObject2, 0, 0, ((Bitmap)localObject2).getWidth(), ((Bitmap)localObject2).getHeight(), (Matrix)localObject3, false);
        }
        localObject2 = paramString.substring(paramString.lastIndexOf("/") + 1);
        localObject3 = ((String)localObject2).substring(((String)localObject2).lastIndexOf(".") + 1);
        if (paramString.contains(":"))
        {
          localObject2 = paramString.split(":")[1];
          localObject2 = new FileOutputStream((String)localObject2);
          if (!((String)localObject3).equalsIgnoreCase("png")) {
            break label230;
          }
          ((Bitmap)localObject1).compress(Bitmap.CompressFormat.PNG, 100, (OutputStream)localObject2);
          ((FileOutputStream)localObject2).flush();
          ((FileOutputStream)localObject2).close();
          ((Bitmap)localObject1).recycle();
          return paramString;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return paramString;
      }
      Object localObject2 = paramString;
      continue;
      label230:
      if ((((String)localObject3).equalsIgnoreCase("jpeg")) || (((String)localObject3).equalsIgnoreCase("jpg"))) {
        localException.compress(Bitmap.CompressFormat.JPEG, 100, (OutputStream)localObject2);
      }
    }
  }
  
  private String rotateImage1(int paramInt, String paramString)
  {
    if (paramInt <= 0) {
      return paramString;
    }
    for (;;)
    {
      Object localObject2;
      Object localObject3;
      try
      {
        Object localObject1 = new File(paramString.split(":")[1]);
        if (!((File)localObject1).exists()) {
          break;
        }
        localObject2 = BitmapFactory.decodeFile(((File)localObject1).getAbsolutePath());
        localObject3 = new Matrix();
        float f = 'ÿ' / ((Bitmap)localObject2).getWidth();
        ((Matrix)localObject3).postRotate(paramInt);
        ((Matrix)localObject3).postScale(f, f);
        localObject1 = localObject2;
        if (((Bitmap)localObject2).getWidth() > ((Bitmap)localObject2).getHeight()) {
          localObject1 = Bitmap.createBitmap((Bitmap)localObject2, 0, 0, ((Bitmap)localObject2).getWidth(), ((Bitmap)localObject2).getHeight(), (Matrix)localObject3, false);
        }
        localObject2 = paramString.substring(paramString.lastIndexOf("/") + 1);
        localObject2 = ((String)localObject2).substring(((String)localObject2).lastIndexOf(".") + 1);
        localObject3 = new FileOutputStream(paramString.split(":")[1]);
        if (((String)localObject2).equalsIgnoreCase("png"))
        {
          ((Bitmap)localObject1).compress(Bitmap.CompressFormat.PNG, 100, (OutputStream)localObject3);
          ((FileOutputStream)localObject3).flush();
          ((FileOutputStream)localObject3).close();
          ((Bitmap)localObject1).recycle();
          return paramString;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return paramString;
      }
      if ((((String)localObject2).equalsIgnoreCase("jpeg")) || (((String)localObject2).equalsIgnoreCase("jpg"))) {
        localException.compress(Bitmap.CompressFormat.JPEG, 100, (OutputStream)localObject3);
      }
    }
  }
  
  public static void sendLocationService(double paramDouble1, double paramDouble2, String paramString)
  {
    cwv.loadUrl("javascript:setLatLongService(\"" + paramDouble1 + "\",\"" + paramDouble2 + "\",\"" + paramString + "\")");
  }
  
  private void showEnableDialog(String paramString)
  {
    if (!isLocationEnabled())
    {
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
      localBuilder.setTitle("Confirm");
      localBuilder.setMessage(paramString + " wants to use your device location.");
      localBuilder.setPositiveButton("Allow", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.cancel();
          MyPhoneGapActivity.this.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
        }
      });
      localBuilder.setNegativeButton("Block", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.cancel();
        }
      });
      localBuilder.show();
    }
  }
  
  private void toggleTorch(boolean paramBoolean)
  {
    Camera.Parameters localParameters = this.mCamera.getParameters();
    if (isCapable()) {
      if (!paramBoolean) {
        break label44;
      }
    }
    label44:
    for (String str = "torch";; str = "off")
    {
      localParameters.setFlashMode(str);
      this.mCamera.setParameters(localParameters);
      this.mCamera.startPreview();
      return;
    }
  }
  
  public static void updateApp()
  {
    cwv.loadUrl("javascript:showConfirm();");
  }
  
  public static void updateFoodPaypalStatus(String paramString)
  {
    cwv.loadUrl("javascript:paypalExpressReturn('" + paramString + "');");
  }
  
  public static void updateFoodPaypalStatusForFormbuilder(String paramString)
  {
    if (paramString.equalsIgnoreCase("success"))
    {
      cwv.loadUrl("javascript:paypalFormbuilderReturn('Success');");
      return;
    }
    cwv.loadUrl("javascript:paypalFormbuilderReturn('failed');");
  }
  
  public static void updateFoodPaypalStatusForNewsStand(String paramString)
  {
    cwv.loadUrl("javascript:inappSuccess('" + paramString + "');");
  }
  
  public static void updateFoodPaypalStatusForSignup(String paramString)
  {
    if (paramString.equalsIgnoreCase("success")) {
      cwv.loadUrl("javascript:signUpPaymentSuccess();");
    }
  }
  
  public static void updateUserProfile(String paramString1, String paramString2)
  {
    System.out.println("executing user profile updates");
    cwv.loadUrl("javascript:updateUserProfile('" + paramString1 + "','" + paramString2 + "');");
  }
  
  private static String webServiceCall(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10)
  {
    String str2 = "0";
    String str1 = str2;
    try
    {
      SoapObject localSoapObject = new SoapObject("http://snappy.appypie.com/services/utility-soap/", "crashReport");
      str1 = str2;
      AndroidHttpTransport localAndroidHttpTransport = new AndroidHttpTransport("http://snappy.appypie.com/services/utility-soap/");
      str1 = str2;
      localSoapObject.addProperty("osVersion", paramString1);
      str1 = str2;
      localSoapObject.addProperty("apiLevel", paramString2);
      str1 = str2;
      localSoapObject.addProperty("model", paramString3);
      str1 = str2;
      localSoapObject.addProperty("device", paramString4);
      str1 = str2;
      localSoapObject.addProperty("manufacturer", paramString5);
      str1 = str2;
      localSoapObject.addProperty("deviceType", paramString6);
      str1 = str2;
      localSoapObject.addProperty("appName", paramString7);
      str1 = str2;
      localSoapObject.addProperty("appId", paramString8);
      str1 = str2;
      localSoapObject.addProperty("appVersion", paramString9);
      str1 = str2;
      localSoapObject.addProperty("crashReport", paramString10);
      str1 = str2;
      paramString1 = new SoapSerializationEnvelope(110);
      str1 = str2;
      paramString1.dotNet = false;
      str1 = str2;
      paramString1.setOutputSoapObject(localSoapObject);
      str1 = str2;
      localAndroidHttpTransport.call("http://schemas.xmlsoap.org/wsdl/", paramString1);
      str1 = str2;
      paramString1 = paramString1.getResponse().toString();
      str1 = paramString1;
      Log.e("lock response=>>>>>>>>>>>>>>>>>>>>>>>>>>>>.>>", paramString1);
      return paramString1;
    }
    catch (Exception paramString1) {}
    return str1;
  }
  
  public void CheckDeviceAppBackgroundImg()
  {
    boolean bool = isTablet(this);
    Check_Device_Dencity();
    Check_Device_Oriantation();
    CheckDevice_ScreenResolution();
    if (bool == true) {
      if (this.Device_Oriantation.equals("PORTRAIT")) {
        if ((this.width >= 600) && (this.width < 720) && (this.height >= 1024) && (this.height < 1280))
        {
          this.AppBackground_Port = "Port_TAB_600x1024px";
          this.AppBackground_Land = "Land_TAB_1024x600px";
        }
      }
    }
    label382:
    label518:
    label614:
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    return;
                    if ((this.width >= 720) && (this.height >= 1280))
                    {
                      this.AppBackground_Port = "Port_TAB_720x1280px";
                      this.AppBackground_Land = "Land_TAB_1280x720px";
                      return;
                    }
                    this.AppBackground_Port = "Port_Default_TAB";
                    this.AppBackground_Land = "Land_Default_TAB";
                    return;
                    if ((this.width >= 1024) && (this.width < 1280) && (this.height >= 600) && (this.height < 720))
                    {
                      this.AppBackground_Port = "Port_TAB_600x1024px";
                      this.AppBackground_Land = "Land_TAB_1024x600px";
                      return;
                    }
                    if ((this.width >= 1280) && (this.height >= 720))
                    {
                      this.AppBackground_Port = "Port_TAB_720x1280px";
                      this.AppBackground_Land = "Land_TAB_1280x720px";
                      return;
                    }
                    this.AppBackground_Port = "Port_Default_TAB";
                    this.AppBackground_Land = "Land_Default_TAB";
                    return;
                    if (!this.Device_Density.equals("MDPI")) {
                      break label382;
                    }
                    if (!this.Device_Oriantation.equals("PORTRAIT")) {
                      break;
                    }
                  } while ((this.width < 320) || (this.width >= 480) || (this.height < 480) || (this.height >= 800));
                  this.AppBackground_Port = "Port_MDPI_320x480px";
                  this.AppBackground_Land = "Land_MDPI_480x320px";
                  return;
                } while ((this.width < 480) || (this.width >= 800) || (this.height < 320) || (this.height >= 480));
                this.AppBackground_Port = "Port_MDPI_320x480px";
                this.AppBackground_Land = "Land_MDPI_480x320px";
                return;
                if (!this.Device_Density.equals("HDPI")) {
                  break label518;
                }
                if (!this.Device_Oriantation.equals("PORTRAIT")) {
                  break;
                }
              } while ((this.width < 480) || (this.width >= 720) || (this.height < 800) || (this.height >= 1280));
              this.AppBackground_Port = "Port_HDPI_480x800px";
              this.AppBackground_Land = "Land_HDPI_800x480px";
              return;
            } while ((this.width < 800) || (this.width >= 1280) || (this.height < 480) || (this.height >= 720));
            this.AppBackground_Port = "Port_HDPI_480x800px";
            this.AppBackground_Land = "Land_HDPI_800x480px";
            return;
            if (!this.Device_Density.equals("XHDPI")) {
              break label614;
            }
            if (!this.Device_Oriantation.equals("PORTRAIT")) {
              break;
            }
          } while ((this.width < 720) || (this.height < 1184));
          this.AppBackground_Port = "Port_XHDPI_720x1280px";
          this.AppBackground_Land = "Land_XHDPI_1280x720px";
          return;
        } while ((this.width < 1185) || (this.height > 720));
        this.AppBackground_Port = "Port_XHDPI_720x1280px";
        this.AppBackground_Land = "Land_XHDPI_1280x720px";
        return;
        if (!this.Device_Density.equals("XXHDPI")) {
          break label710;
        }
        if (!this.Device_Oriantation.equals("PORTRAIT")) {
          break;
        }
      } while ((this.width < 1080) || (this.height < 1700));
      this.AppBackground_Port = "Port_XXHDPI_1080x1920px";
      this.AppBackground_Land = "Land_XXHDPI_1920x1080px";
      return;
    } while ((this.width < 1700) || (this.height < 1080));
    this.AppBackground_Port = "Port_XXHDPI_1080x1920px";
    this.AppBackground_Land = "Land_XXHDPI_1920x1080px";
    return;
    label710:
    if (this.Device_Density.equals("XXXHDPI"))
    {
      if (this.Device_Oriantation.equals("PORTRAIT"))
      {
        this.AppBackground_Port = "Port_XXXHDPI";
        this.AppBackground_Land = "Land_XXXHDPI";
        return;
      }
      this.AppBackground_Port = "Port_XXXHDPI";
      this.AppBackground_Land = "Land_XXXHDPI";
      return;
    }
    this.AppBackground_Port = "Port_Default_PHONE";
    this.AppBackground_Land = "Land_Default_PHONE";
  }
  
  @JavascriptInterface
  public String CheckDevice_Screen_AppBackground()
  {
    boolean bool = isTablet(this);
    Check_Device_Dencity();
    Check_Device_Oriantation();
    CheckDevice_ScreenResolution();
    if (bool == true) {
      if (this.Device_Oriantation.equals("PORTRAIT")) {
        if ((this.width >= 600) && (this.width < 720) && (this.height >= 1024) && (this.height < 1280)) {
          this.AppBackground_Img_Port = "Port_TAB_600x1024px";
        }
      }
    }
    for (;;)
    {
      return this.AppBackground_Img_Port;
      if ((this.width >= 720) && (this.height >= 1280))
      {
        this.AppBackground_Img_Port = "Port_TAB_720x1280px";
      }
      else
      {
        this.AppBackground_Img_Port = "Port_Default_TAB";
        continue;
        if ((this.width >= 1024) && (this.width < 1280) && (this.height >= 600) && (this.height < 720))
        {
          this.AppBackground_Img_Port = "Land_TAB_1024x600px";
        }
        else if ((this.width >= 1280) && (this.height >= 720))
        {
          this.AppBackground_Img_Port = "Land_TAB_1280x720px";
        }
        else
        {
          this.AppBackground_Img_Port = "Land_Default_TAB";
          continue;
          if (this.Device_Density.equals("MDPI"))
          {
            if (this.Device_Oriantation.equals("PORTRAIT"))
            {
              if ((this.width >= 320) && (this.width < 480) && (this.height >= 480) && (this.height < 800)) {
                this.AppBackground_Img_Port = "Port_MDPI_320x480px";
              }
            }
            else if ((this.width >= 480) && (this.width < 800) && (this.height >= 320) && (this.height < 480)) {
              this.AppBackground_Img_Port = "Land_MDPI_480x320px";
            }
          }
          else if (this.Device_Density.equals("HDPI"))
          {
            if (this.Device_Oriantation.equals("PORTRAIT"))
            {
              if ((this.width >= 480) && (this.width < 720) && (this.height >= 800) && (this.height < 1280)) {
                this.AppBackground_Img_Port = "Port_HDPI_480x800px";
              }
            }
            else if ((this.width >= 800) && (this.width < 1280) && (this.height >= 480) && (this.height < 720)) {
              this.AppBackground_Img_Port = "Land_HDPI_800x480px";
            }
          }
          else if (this.Device_Density.equals("XHDPI"))
          {
            if (this.Device_Oriantation.equals("PORTRAIT"))
            {
              if ((this.width >= 720) && (this.height >= 1184)) {
                this.AppBackground_Img_Port = "Port_XHDPI_720x1280px";
              }
            }
            else if ((this.width >= 1185) && (this.height <= 720)) {
              this.AppBackground_Img_Port = "Land_XHDPI_1280x720px";
            }
          }
          else if (this.Device_Density.equals("XXHDPI"))
          {
            if (this.Device_Oriantation.equals("PORTRAIT"))
            {
              if ((this.width >= 1080) && (this.height >= 1700)) {
                this.AppBackground_Img_Port = "Port_XXHDPI_1080x1920px";
              }
            }
            else if ((this.width >= 1700) && (this.height >= 1080)) {
              this.AppBackground_Img_Port = "Land_XXHDPI_1920x1080px";
            }
          }
          else if (this.Device_Density.equals("XXXHDPI"))
          {
            if (this.Device_Oriantation.equals("PORTRAIT")) {
              this.AppBackground_Img_Port = "Port_XXXHDPI";
            } else {
              this.AppBackground_Img_Port = "Land_XXXHDPI";
            }
          }
          else {
            this.AppBackground_Img_Port = "Port_Default_PHONE";
          }
        }
      }
    }
  }
  
  @JavascriptInterface
  public String CheckIamgeOrientation(String paramString)
  {
    if (paramString.contains("content")) {
      return getRightAngleImage(getRealPathFromUri(this, Uri.parse(paramString)));
    }
    return getRightAngleImage1(paramString);
  }
  
  public void DonlaodBGImgFromURL(String paramString1, String paramString2)
  {
    try
    {
      System.out.println("ImgURl_Port imgurl  " + this.Device_Oriantation + "   write  " + paramString1 + "   , " + paramString2);
      paramString2 = (HttpURLConnection)new URL(paramString2).openConnection();
      paramString2.setRequestMethod("GET");
      paramString2.setDoOutput(true);
      paramString2.connect();
      Check_Device_Oriantation();
      Object localObject = this.Device_Oriantation;
      this.SDCardRoot = new File(Environment.getExternalStorageDirectory(), this.foldername + appName);
      if (!this.SDCardRoot.exists())
      {
        this.SDCardRoot.mkdirs();
        System.out.println("create folder  www.appypie.com.image");
      }
      if (paramString1.equals("0")) {}
      for (paramString1 = new File(this.SDCardRoot, "appbg_port_img.jpg");; paramString1 = new File(this.SDCardRoot, "appbg_land_img.jpg"))
      {
        paramString1 = new FileOutputStream(paramString1);
        localObject = paramString2.getInputStream();
        paramString2.getContentLength();
        int i = 0;
        paramString2 = new byte['Ѐ'];
        for (;;)
        {
          int j = ((InputStream)localObject).read(paramString2);
          if (j <= 0) {
            break;
          }
          paramString1.write(paramString2, 0, j);
          i += j;
        }
      }
      paramString1.close();
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public void DonlaodImgFromURL(String paramString1, String paramString2)
  {
    try
    {
      System.out.println("ImgURl_Port imgurl  " + this.Device_Oriantation + "   write  " + paramString1 + "   , " + paramString2);
      paramString2 = (HttpURLConnection)new URL(paramString2).openConnection();
      paramString2.setRequestMethod("GET");
      paramString2.setDoOutput(true);
      paramString2.connect();
      Check_Device_Oriantation();
      Object localObject = this.Device_Oriantation;
      this.SDCardRoot = new File(Environment.getExternalStorageDirectory(), this.foldername + appName);
      if (!this.SDCardRoot.exists())
      {
        this.SDCardRoot.mkdirs();
        System.out.println("create folder  www.appypie.com.image");
      }
      if (this.Device_Oriantation.equals("PORTRAIT")) {
        if (paramString1.equals("0")) {
          paramString1 = new File(this.SDCardRoot, "header_port_img.jpg");
        }
      }
      for (;;)
      {
        paramString1 = new FileOutputStream(paramString1);
        localObject = paramString2.getInputStream();
        paramString2.getContentLength();
        int i = 0;
        paramString2 = new byte['Ѐ'];
        for (;;)
        {
          int j = ((InputStream)localObject).read(paramString2);
          if (j <= 0) {
            break;
          }
          paramString1.write(paramString2, 0, j);
          i += j;
        }
        paramString1 = new File(this.SDCardRoot, "header_land_img.jpg");
        continue;
        if (paramString1.equals("0")) {
          paramString1 = new File(this.SDCardRoot, "header_land_img.jpg");
        } else {
          paramString1 = new File(this.SDCardRoot, "header_port_img.jpg");
        }
      }
      paramString1.close();
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  @JavascriptInterface
  public void EbookViewer(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    System.out.println("unzip----------url=" + paramString1 + "----info=" + paramString2);
    Object localObject1 = paramString1.substring(16);
    localObject2 = ("/mnt/sdcard" + (String)localObject1).replace("++++", " ");
    try
    {
      localObject1 = new FileInputStream((String)localObject2);
      List localList = new EpubReader().readEpub((InputStream)localObject1).getMetadata().getAuthors();
      localObject1 = paramString3;
      if (localList.size() > 0) {
        localObject1 = ((Author)localList.get(0)).getFirstname() + " " + ((Author)localList.get(0)).getLastname();
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        String str = paramString3;
        continue;
        localObject2 = new Intent(this, EpubReaderMain.class);
        ((Intent)localObject2).setFlags(67108864);
        Log.e("url ", paramString1);
        ((Intent)localObject2).putExtra("url", paramString1);
        ((Intent)localObject2).putExtra("info", paramString2);
        ((Intent)localObject2).putExtra("authorname", str);
        ((Intent)localObject2).putExtra("title", paramString4);
        ((Intent)localObject2).putExtra("activityName", "EbookViewer");
        ((Intent)localObject2).putExtra("fontsize", 15);
        startActivity((Intent)localObject2);
      }
    }
    EpubReaderMain.bookurl = (String)localObject2;
    paramString3 = new DatabaseHelperAdapterReader(this);
    paramString3.open();
    localObject2 = paramString3.getBookStatus(EpubReaderMain.bookurl);
    Log.e("book url>>>>>>>>>>>>>>", "" + ((Cursor)localObject2).getCount());
    if (((Cursor)localObject2).getCount() > 0)
    {
      ((Cursor)localObject2).moveToFirst();
      paramString1 = new Intent(this, EpubReaderMain.class);
      paramString1.putExtra("url", ((Cursor)localObject2).getString(1));
      paramString1.putExtra("info", ((Cursor)localObject2).getString(4));
      paramString1.putExtra("title", ((Cursor)localObject2).getString(4));
      paramString1.putExtra("authorname", ((Cursor)localObject2).getString(3));
      paramString1.putExtra("xvalue", ((Cursor)localObject2).getInt(6));
      paramString1.putExtra("pageno", ((Cursor)localObject2).getInt(2));
      paramString1.putExtra("p_page_value", "");
      paramString1.putExtra("activityName", "Bookmark");
      paramString1.putExtra("fontsize", ((Cursor)localObject2).getInt(7));
      startActivity(paramString1);
      paramString3.close();
      return;
    }
  }
  
  @JavascriptInterface
  public int Header_CheckDevice_ScreenResolution()
  {
    Display localDisplay = ((WindowManager)getSystemService("window")).getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localDisplay.getMetrics(localDisplayMetrics);
    this.width = localDisplayMetrics.widthPixels;
    return this.width;
  }
  
  public int Header_CheckDevice_ScreenResolutionn()
  {
    Display localDisplay = ((WindowManager)getSystemService("window")).getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localDisplay.getMetrics(localDisplayMetrics);
    this.width = localDisplayMetrics.widthPixels;
    this.height = localDisplayMetrics.heightPixels;
    return this.width;
  }
  
  @JavascriptInterface
  public int Header_CheckLandImg()
  {
    this.SDCardRoot = new File(Environment.getExternalStorageDirectory(), this.foldername + appName);
    this.file = new File(this.SDCardRoot, "header_land_img.jpg");
    if (this.file.exists()) {
      return 1;
    }
    return 0;
  }
  
  @JavascriptInterface
  public int Header_CheckPortImg()
  {
    this.SDCardRoot = new File(Environment.getExternalStorageDirectory(), this.foldername + appName);
    this.file = new File(this.SDCardRoot, "header_port_img.jpg");
    if (this.file.exists()) {
      return 1;
    }
    return 0;
  }
  
  @JavascriptInterface
  public String Headeroriantation()
  {
    Check_Device_Oriantation();
    return this.Device_Oriantation;
  }
  
  @JavascriptInterface
  public void PdfUrlToaster(String paramString)
  {
    Intent localIntent = new Intent(this, DownloadFileDemo.class);
    localIntent.putExtra("Url", paramString);
    startActivity(localIntent);
  }
  
  @JavascriptInterface
  public void Recorder()
  {
    System.out.println("1");
    startActivity(new Intent(this, OptionforCamera.class));
  }
  
  @JavascriptInterface
  public void SaveSettingProfileDirecotory(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    new SaveSettingProfileDirecotory().execute(new String[] { paramString1, paramString2, paramString3, paramString4, paramString5 });
  }
  
  @JavascriptInterface
  public void SaverequestserviceDirecotory(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12)
  {
    new SaverequestserviceDirecotory().execute(new String[] { paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, paramString9, paramString10, paramString11, paramString12 });
  }
  
  @JavascriptInterface
  public void SaverequestserviceDirecotory(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13, String paramString14)
  {
    new SaverequestserviceDirecotory().execute(new String[] { paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, paramString9, paramString10, paramString11, paramString12, paramString13, paramString14 });
  }
  
  @JavascriptInterface
  public boolean SinglePageCheck()
  {
    return this.singlePageCheck;
  }
  
  public void SkypeUri(Context paramContext, String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    paramString.setComponent(new ComponentName("com.skype.raider", "com.skype.raider.Main"));
    paramString.setFlags(268435456);
    paramContext.startActivity(paramString);
  }
  
  @JavascriptInterface
  public String androidVerion()
  {
    return Integer.toString(Build.VERSION.SDK_INT);
  }
  
  public boolean appInstalledOrNot(String paramString)
  {
    try
    {
      getPackageManager().getApplicationInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  @JavascriptInterface
  public void callBarCodeReader()
  {
    startActivityForResult(new Intent(this, SimpleScannerActivity.class), 9);
  }
  
  @JavascriptInterface
  public void callSkype(String paramString)
  {
    if (!appInstalledOrNot("com.skype.raider"))
    {
      Toast.makeText(this, "Skype Application is not installed", 1).show();
      return;
    }
    paramString = new Intent("android.intent.action.VIEW", Uri.parse("skype:" + paramString + "?call"));
    paramString.setComponent(new ComponentName("com.skype.raider", "com.skype.raider.Main"));
    paramString.setFlags(268435456);
    startActivity(paramString);
  }
  
  public void callbeaconService()
  {
    if ((Build.VERSION.SDK_INT == 18) || (Build.VERSION.SDK_INT == 19)) {
      this.iBeaconManager.bind(this);
    }
  }
  
  public String changeTag(String paramString)
  {
    int i = paramString.indexOf("<object");
    int j = paramString.indexOf("</object>");
    String str = paramString.substring(i, j + 9).replace("data", "src").replace("<object", "<iframe").replace("</object>", "</iframe>").replace("/v/", "/embed/");
    return paramString.substring(0, i) + str + paramString.substring(j + 9);
  }
  
  @JavascriptInterface
  public boolean checkFile(String paramString)
  {
    return new File("" + paramString).exists();
  }
  
  @JavascriptInterface
  public boolean checkImageValue()
  {
    return Option.bitmap != null;
  }
  
  public void close(View paramView)
  {
    showAndHideAdView("hide");
  }
  
  @JavascriptInterface
  public void closeAdsLayout()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        try
        {
          System.out.println("krishna hide layout");
          MyPhoneGapActivity.this.adLayout.setVisibility(4);
          MyPhoneGapActivity.this.adLayout1.setVisibility(4);
          MyPhoneGapActivity.this.adLayout2.setVisibility(4);
          MyPhoneGapActivity.this.closeAds2.setVisibility(4);
          MyPhoneGapActivity.this.closeAds1.setVisibility(4);
          MyPhoneGapActivity.this.closeAds.setVisibility(4);
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    });
  }
  
  @JavascriptInterface
  public void customFormBuilderGetfile(final int paramInt)
  {
    final CharSequence[] arrayOfCharSequence = new CharSequence[3];
    arrayOfCharSequence[0] = "Take Photo";
    arrayOfCharSequence[1] = "Select from Gallery";
    arrayOfCharSequence[2] = "Cancel";
    final AlertDialog.Builder localBuilder = new AlertDialog.Builder(this, 2131296282);
    localBuilder.setItems(arrayOfCharSequence, new DialogInterface.OnClickListener()
    {
      @SuppressLint({"NewApi"})
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (arrayOfCharSequence[paramAnonymousInt].equals("Take Photo")) {
          try
          {
            paramAnonymousDialogInterface = new ContentValues();
            paramAnonymousDialogInterface.put("title", "temp.jpg");
            MyPhoneGapActivity.this.mCapturedImageURI = MyPhoneGapActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, paramAnonymousDialogInterface);
            paramAnonymousDialogInterface = new Intent("android.media.action.IMAGE_CAPTURE");
            paramAnonymousDialogInterface.putExtra("output", MyPhoneGapActivity.this.mCapturedImageURI);
            MyPhoneGapActivity.this.picCheck = 1;
            MyPhoneGapActivity.this.startActivityForResult(paramAnonymousDialogInterface, paramInt);
            return;
          }
          catch (Exception paramAnonymousDialogInterface)
          {
            paramAnonymousDialogInterface.printStackTrace();
            return;
          }
        }
        if (arrayOfCharSequence[paramAnonymousInt].equals("Select from Gallery"))
        {
          try
          {
            if (paramInt == 111)
            {
              paramAnonymousDialogInterface = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
              MyPhoneGapActivity.this.startActivityForResult(paramAnonymousDialogInterface, paramInt);
              MyPhoneGapActivity.this.picCheck = 2;
              return;
            }
          }
          catch (Exception paramAnonymousDialogInterface)
          {
            paramAnonymousDialogInterface.printStackTrace();
            return;
          }
          MyPhoneGapActivity.this.getRealPathImage();
          return;
        }
        if (arrayOfCharSequence[paramAnonymousInt].equals("Cancel")) {
          try
          {
            MyPhoneGapActivity.this.picCheck = 0;
            paramAnonymousDialogInterface.cancel();
            return;
          }
          catch (Exception paramAnonymousDialogInterface)
          {
            paramAnonymousDialogInterface.printStackTrace();
            return;
          }
        }
        localBuilder.setOnDismissListener(new DialogInterface.OnDismissListener()
        {
          public void onDismiss(DialogInterface paramAnonymous2DialogInterface)
          {
            MyPhoneGapActivity.this.picCheck = 0;
            paramAnonymous2DialogInterface.cancel();
          }
        });
      }
    });
    localBuilder.show();
  }
  
  @JavascriptInterface
  public void deleteBookMark(String paramString)
  {
    new DatabaseHelperAdapterReader(this).deleteBookmarkByBookname(paramString);
  }
  
  @JavascriptInterface
  public void disconnectFromFacebook()
  {
    try
    {
      if (AccessToken.getCurrentAccessToken() == null) {
        return;
      }
      new GraphRequest(AccessToken.getCurrentAccessToken(), "/ " + fbID + "/permissions/", null, HttpMethod.DELETE, new GraphRequest.Callback()
      {
        public void onCompleted(GraphResponse paramAnonymousGraphResponse)
        {
          Log.e("Facebook", "FB ID " + MyPhoneGapActivity.fbID);
          LoginManager.getInstance().logOut();
          MyPhoneGapActivity.fbEmailId = null;
          MyPhoneGapActivity.fbID = 0L;
          MyPhoneGapActivity.this.fbEditor = MyPhoneGapActivity.this.fbPreference.edit();
          MyPhoneGapActivity.this.fbEditor.putLong("FBID", 0L);
          MyPhoneGapActivity.this.fbEditor.putString("FBEMAILID", null);
          MyPhoneGapActivity.this.fbEditor.putString("FBNAME", null);
          MyPhoneGapActivity.this.fbEditor.apply();
        }
      }).executeAsync();
      return;
    }
    catch (Exception localException)
    {
      Log.e("Facebook", "Logout Error... " + localException);
    }
  }
  
  /* Error */
  @JavascriptInterface
  public void downloadImaeg(String paramString)
  {
    // Byte code:
    //   0: getstatic 734	java/lang/System:out	Ljava/io/PrintStream;
    //   3: new 736	java/lang/StringBuilder
    //   6: dup
    //   7: invokespecial 737	java/lang/StringBuilder:<init>	()V
    //   10: ldc_w 2469
    //   13: invokevirtual 743	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   16: aload_1
    //   17: invokevirtual 743	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   20: invokevirtual 748	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   23: invokevirtual 754	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   26: aconst_null
    //   27: astore 4
    //   29: aconst_null
    //   30: astore_3
    //   31: aconst_null
    //   32: astore_2
    //   33: aload_1
    //   34: ifnull +133 -> 167
    //   37: aload_3
    //   38: astore_2
    //   39: new 766	java/io/File
    //   42: dup
    //   43: invokestatic 772	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   46: ldc_w 2471
    //   49: invokespecial 775	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   52: astore 5
    //   54: aload_3
    //   55: astore_2
    //   56: aload 5
    //   58: invokevirtual 793	java/io/File:exists	()Z
    //   61: ifne +11 -> 72
    //   64: aload_3
    //   65: astore_2
    //   66: aload 5
    //   68: invokevirtual 2186	java/io/File:mkdirs	()Z
    //   71: pop
    //   72: aload_3
    //   73: astore_2
    //   74: new 766	java/io/File
    //   77: dup
    //   78: aload 5
    //   80: ldc_w 2473
    //   83: invokespecial 775	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   86: astore 5
    //   88: aload_3
    //   89: astore_2
    //   90: new 1352	java/io/FileOutputStream
    //   93: dup
    //   94: aload 5
    //   96: invokespecial 2189	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   99: astore_3
    //   100: aload_3
    //   101: aload_1
    //   102: iconst_0
    //   103: invokestatic 2479	android/util/Base64:decode	(Ljava/lang/String;I)[B
    //   106: invokevirtual 2482	java/io/FileOutputStream:write	([B)V
    //   109: aload_3
    //   110: invokevirtual 1978	java/io/FileOutputStream:flush	()V
    //   113: aload_3
    //   114: invokevirtual 1979	java/io/FileOutputStream:close	()V
    //   117: new 1651	android/content/Intent
    //   120: dup
    //   121: invokespecial 2483	android/content/Intent:<init>	()V
    //   124: astore_1
    //   125: aload_1
    //   126: ldc_w 2485
    //   129: invokevirtual 2489	android/content/Intent:setAction	(Ljava/lang/String;)Landroid/content/Intent;
    //   132: pop
    //   133: aload_1
    //   134: ldc_w 2491
    //   137: aload 5
    //   139: invokestatic 2495	android/net/Uri:fromFile	(Ljava/io/File;)Landroid/net/Uri;
    //   142: invokevirtual 2498	android/content/Intent:putExtra	(Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;
    //   145: pop
    //   146: aload_1
    //   147: ldc_w 2500
    //   150: invokevirtual 2503	android/content/Intent:setType	(Ljava/lang/String;)Landroid/content/Intent;
    //   153: pop
    //   154: aload_0
    //   155: aload_1
    //   156: ldc_w 2505
    //   159: invokestatic 2509	android/content/Intent:createChooser	(Landroid/content/Intent;Ljava/lang/CharSequence;)Landroid/content/Intent;
    //   162: invokevirtual 2295	com/example/example75f1799f07eb/MyPhoneGapActivity:startActivity	(Landroid/content/Intent;)V
    //   165: aload_3
    //   166: astore_2
    //   167: aload_2
    //   168: ifnull +3 -> 171
    //   171: return
    //   172: astore_3
    //   173: aload 4
    //   175: astore_1
    //   176: aload_1
    //   177: astore_2
    //   178: aload_3
    //   179: invokevirtual 1454	java/lang/Exception:printStackTrace	()V
    //   182: aload_1
    //   183: ifnull -12 -> 171
    //   186: return
    //   187: astore_1
    //   188: aload_2
    //   189: ifnull +3 -> 192
    //   192: aload_1
    //   193: athrow
    //   194: astore_1
    //   195: aload_3
    //   196: astore_2
    //   197: goto -9 -> 188
    //   200: astore_2
    //   201: aload_3
    //   202: astore_1
    //   203: aload_2
    //   204: astore_3
    //   205: goto -29 -> 176
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	208	0	this	MyPhoneGapActivity
    //   0	208	1	paramString	String
    //   32	165	2	localObject1	Object
    //   200	4	2	localException1	Exception
    //   30	136	3	localFileOutputStream	FileOutputStream
    //   172	30	3	localException2	Exception
    //   204	1	3	localObject2	Object
    //   27	147	4	localObject3	Object
    //   52	86	5	localFile	File
    // Exception table:
    //   from	to	target	type
    //   39	54	172	java/lang/Exception
    //   56	64	172	java/lang/Exception
    //   66	72	172	java/lang/Exception
    //   74	88	172	java/lang/Exception
    //   90	100	172	java/lang/Exception
    //   39	54	187	finally
    //   56	64	187	finally
    //   66	72	187	finally
    //   74	88	187	finally
    //   90	100	187	finally
    //   178	182	187	finally
    //   100	165	194	finally
    //   100	165	200	java/lang/Exception
  }
  
  @JavascriptInterface
  public void downloadUnzip(String paramString1, String paramString2)
  {
    System.out.println("unzip----------url=" + paramString1 + "----info=" + paramString2);
    paramString1 = Environment.getExternalStorageDirectory() + "/Downloads/" + paramString2;
    try
    {
      new EpubManipulator(paramString1, paramString2);
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  @JavascriptInterface
  public void exitApp()
  {
    if (this.exitNum >= 2)
    {
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          try
          {
            if (!MyPhoneGapActivity.AppId.equals("2b77f6096336"))
            {
              MyPhoneGapActivity.cwv.loadUrl("");
              MyPhoneGapActivity.cwv1.loadUrl("");
              MyPhoneGapActivity.this.mapWebView.loadUrl("");
              MyPhoneGapActivity.this.mapWebView = null;
              MyPhoneGapActivity.cwv1 = null;
              MyPhoneGapActivity.cwv = null;
              System.exit(0);
            }
            return;
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        }
      });
      return;
    }
    popUpExit();
  }
  
  @TargetApi(16)
  protected void fixJellyBeanIssues() {}
  
  public Activity getActivity()
  {
    return this;
  }
  
  @JavascriptInterface
  public String getAppPackageName()
  {
    return getApplicationContext().getPackageName();
  }
  
  public String getApplicationName()
  {
    Log.e("", " inside getApplicationName starts now....." + new Date().getSeconds() / 1000);
    return getString(getApplicationInfo().labelRes);
  }
  
  @JavascriptInterface
  public String getApplicationPackage()
  {
    return sharedpreferences.getString("applicationpackage", "");
  }
  
  @JavascriptInterface
  public String getCompleteAddressString(double paramDouble1, double paramDouble2)
  {
    System.out.println("lat & lng data>>" + paramDouble1 + "////" + paramDouble2);
    new Geocoder(this, Locale.ENGLISH);
    for (;;)
    {
      try
      {
        localObject = new Geocoder(this, Locale.ENGLISH);
      }
      catch (IOException localIOException2)
      {
        Object localObject;
        StringBuilder localStringBuilder;
        String str2;
        String str3;
        String str4;
        continue;
        if ((str2 == null) && ((str3 == null) && (str4 != null))) {
          continue;
        }
        continue;
      }
      try
      {
        localObject = ((Geocoder)localObject).getFromLocation(paramDouble1, paramDouble2, 1);
        localStringBuilder = new StringBuilder();
        if (Geocoder.isPresent())
        {
          localObject = (Address)((List)localObject).get(0);
          String str1 = ((Address)localObject).getLocality();
          str2 = ((Address)localObject).getCountryName();
          str3 = ((Address)localObject).getCountryCode();
          str4 = ((Address)localObject).getPostalCode();
          localObject = str1;
          if (str1 == null)
          {
            localObject = "";
            continue;
            localStringBuilder.append((String)localObject + " ");
            return localStringBuilder.toString();
          }
        }
        else
        {
          Toast.makeText(getApplicationContext(), "geocoder not present", 0).show();
          return "";
        }
      }
      catch (IOException localIOException1)
      {
        Log.e("tag", localIOException1.getMessage());
        return "";
      }
    }
  }
  
  @JavascriptInterface
  public String getDeviceID()
  {
    return Settings.Secure.getString(getContentResolver(), "android_id");
  }
  
  @JavascriptInterface
  public String getDeviceToken()
  {
    return MyApplicationName.GCM_ID;
  }
  
  @JavascriptInterface
  public double getFileSize(String paramString)
  {
    new DecimalFormat("###0.0");
    return new File(paramString).length() / 1024.0D;
  }
  
  @JavascriptInterface
  public String getFromNotification()
  {
    return sharedpreferences.getString("FromNotification", "");
  }
  
  @JavascriptInterface
  public String getGCMId()
  {
    System.out.println("Abhi GCM ID " + MyApplicationName.GCM_ID);
    return MyApplicationName.GCM_ID;
  }
  
  @JavascriptInterface
  public String getImage()
  {
    return sharedpreferences.getString("imageUrl", "");
  }
  
  @JavascriptInterface
  public String getInternalStorageFileDir()
    throws IOException
  {
    return new File(getFilesDir(), "appypie.xml").getCanonicalPath().toString();
  }
  
  @JavascriptInterface
  public String getLoginStatus()
  {
    return sharedpreferences.getString("loginstatus", "");
  }
  
  @JavascriptInterface
  public String getRealPathFromURI(Uri paramUri)
  {
    System.out.println("image uri path>>>" + paramUri);
    int j = 0;
    Cursor localCursor = getContentResolver().query(paramUri, null, null, null, null);
    int i = j;
    try
    {
      localCursor.moveToFirst();
      i = j;
      j = localCursor.getColumnIndex("_data");
      i = j;
      if (j == -1)
      {
        i = j;
        getContentResolver().query(Uri.parse(paramUri.getPath()), null, null, null, null);
        i = j;
        paramUri = getContentResolver().query(Uri.parse(paramUri.getPath().substring(6, paramUri.getPath().indexOf("ACTUAL") - 1)), null, null, null, null);
        i = j;
        paramUri.moveToFirst();
        i = j;
        j = paramUri.getColumnIndex("_data");
        i = j;
        paramUri = paramUri.getString(j);
        return paramUri;
      }
    }
    catch (Exception paramUri) {}
    return localCursor.getString(i);
  }
  
  public String getRealPathFromURI_API19(Uri paramUri)
  {
    String str = "";
    Object localObject = android.provider.DocumentsContract.getDocumentId(paramUri).split(":")[1];
    paramUri = new String[1];
    paramUri[0] = "_data";
    localObject = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, paramUri, "_id=?", new String[] { localObject }, null);
    int i = ((Cursor)localObject).getColumnIndex(paramUri[0]);
    paramUri = str;
    if (((Cursor)localObject).moveToFirst()) {
      paramUri = ((Cursor)localObject).getString(i);
    }
    ((Cursor)localObject).close();
    return paramUri;
  }
  
  public void getRealPathImage()
  {
    Intent localIntent = new Intent("android.intent.action.GET_CONTENT");
    localIntent.setType("image/*");
    startActivityForResult(Intent.createChooser(localIntent, "Select Picture"), 0);
  }
  
  public ExecutorService getThreadPool()
  {
    return this.threadPool;
  }
  
  @JavascriptInterface
  public String getUniqueDeviceId()
  {
    String str = Settings.Secure.getString(getContentResolver(), "android_id");
    return str + AppId;
  }
  
  @JavascriptInterface
  public String getUserStatus()
  {
    return sharedpreferences.getString("fooduserid", "");
  }
  
  @JavascriptInterface
  public String getValues()
  {
    if ((this.mpreferences.getNAME() == "") && (this.mpreferences.getEMAIL() == "")) {
      return "";
    }
    return this.mpreferences.getNAME();
  }
  
  @JavascriptInterface
  public String getloyalityEmail()
  {
    return sharedpreferences.getString("loyality_email", "");
  }
  
  @JavascriptInterface
  public String getloyalityXMLPath()
  {
    return sharedpreferences.getString("loyality_xmlpath", "");
  }
  
  @JavascriptInterface
  public void goToPdfReader(String paramString1, String paramString2)
  {
    String str;
    try
    {
      System.out.println("krishna>>> goToPdfReader try" + paramString1);
      if (!paramString1.contains("@@@@@true"))
      {
        str = paramString1;
        if (!paramString1.contains("@@@@@false")) {}
      }
      else
      {
        str = paramString1.substring(0, paramString1.indexOf("pdf@@@@@") + 3);
      }
      if (paramString2.equalsIgnoreCase("YES"))
      {
        paramString1 = Uri.fromFile(new File(str.toString()));
        if (str.contains("http://")) {
          paramString1 = Uri.parse(str);
        }
        paramString2 = new Intent("android.intent.action.VIEW");
        paramString2.setDataAndType(paramString1, "application/pdf");
        paramString2.setFlags(67108864);
        try
        {
          startActivity(paramString2);
          return;
        }
        catch (ActivityNotFoundException paramString1)
        {
          Toast.makeText(this, "No Application Available to View PDF", 0).show();
          return;
        }
      }
      paramString1 = new Intent(this, PDFViewActivity.class);
    }
    catch (Exception paramString1)
    {
      System.out.println("krishna>>> goToPdfReader Exception");
      paramString1.printStackTrace();
      return;
    }
    paramString1.putExtra("URL", str);
    startActivity(paramString1);
  }
  
  public boolean hasPermission(String paramString)
  {
    return false;
  }
  
  public void hideCustomView() {}
  
  @JavascriptInterface
  public void hitInAppPurchase(final String paramString1, final String paramString2, final String paramString3)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        MyPhoneGapActivity.this.bp = new BillingProcessor(MyPhoneGapActivity.this, paramString2, MyPhoneGapActivity.this);
        Handler localHandler = new Handler();
        MyPhoneGapActivity.this.bp.consumePurchase(paramString1);
        MyPhoneGapActivity.this.paymentId = paramString1;
        localHandler.postDelayed(new Runnable()
        {
          public void run()
          {
            if (MyPhoneGapActivity.31.this.val$paymentType.equalsIgnoreCase("subscription"))
            {
              MyPhoneGapActivity.this.bp.subscribe(MyPhoneGapActivity.this, MyPhoneGapActivity.31.this.val$productId);
              return;
            }
            if (MyPhoneGapActivity.31.this.val$paymentType.equalsIgnoreCase("managed"))
            {
              MyPhoneGapActivity.this.bp.purchase(MyPhoneGapActivity.this, MyPhoneGapActivity.31.this.val$productId);
              return;
            }
            MyPhoneGapActivity.this.bp.purchase(MyPhoneGapActivity.this, MyPhoneGapActivity.31.this.val$productId);
          }
        }, 300L);
      }
    });
  }
  
  @JavascriptInterface
  public void hitInAppPurchaseForSignup(final String paramString1, final String paramString2, final String paramString3)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        MyPhoneGapActivity.this.signupPaymentCheck = "true";
        MyPhoneGapActivity.this.bp = new BillingProcessor(MyPhoneGapActivity.this, paramString2, MyPhoneGapActivity.this);
        Handler localHandler = new Handler();
        MyPhoneGapActivity.this.bp.consumePurchase(paramString1);
        MyPhoneGapActivity.this.paymentId = paramString1;
        localHandler.postDelayed(new Runnable()
        {
          public void run()
          {
            if (MyPhoneGapActivity.32.this.val$paymentType.equalsIgnoreCase("subscription"))
            {
              MyPhoneGapActivity.this.bp.subscribe(MyPhoneGapActivity.this, MyPhoneGapActivity.32.this.val$productId);
              return;
            }
            if (MyPhoneGapActivity.32.this.val$paymentType.equalsIgnoreCase("managed"))
            {
              MyPhoneGapActivity.this.bp.purchase(MyPhoneGapActivity.this, MyPhoneGapActivity.32.this.val$productId);
              return;
            }
            MyPhoneGapActivity.this.bp.purchase(MyPhoneGapActivity.this, MyPhoneGapActivity.32.this.val$productId);
          }
        }, 300L);
      }
    });
  }
  
  @JavascriptInterface
  public void holdBeaconData(String paramString)
  {
    this.beaconURLs = paramString.substring(12).split(",");
    int i = 0;
    while (i < this.beaconURLs.length)
    {
      this.beaconUUID.add(this.beaconURLs[i]);
      i += 1;
    }
    callbeaconService();
  }
  
  public boolean inCustomView()
  {
    return this.mCustomView != null;
  }
  
  public boolean isGooglePlayServicesInstalled()
  {
    try
    {
      getPackageManager().getApplicationInfo("com.google.android.gsf", 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return false;
  }
  
  @JavascriptInterface
  public boolean isLocationEnabled()
  {
    int i = 0;
    if (Build.VERSION.SDK_INT >= 19)
    {
      try
      {
        j = Settings.Secure.getInt(getApplicationContext().getContentResolver(), "location_mode");
        i = j;
      }
      catch (Settings.SettingNotFoundException localSettingNotFoundException)
      {
        for (;;)
        {
          localSettingNotFoundException.printStackTrace();
        }
      }
      if (i == 0) {}
    }
    while (!TextUtils.isEmpty(Settings.Secure.getString(getApplicationContext().getContentResolver(), "location_providers_allowed")))
    {
      int j;
      return true;
      return false;
    }
    return false;
  }
  
  public boolean isTablet(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  @JavascriptInterface
  public void liveVideoPlayMethod(String paramString)
  {
    Object localObject = R.raw.class.getFields();
    int i = 0;
    while (i < localObject.length)
    {
      Log.i("Raw Asset: ", localObject[i].getName());
      if (localObject[i].getName().equals("libarm")) {}
      i += 1;
    }
    if (AppId.equals("9267bae0cebd"))
    {
      localObject = new Intent("android.intent.action.VIEW");
      ((Intent)localObject).setDataAndType(Uri.parse(paramString), "video/*");
      startActivity((Intent)localObject);
      return;
    }
    if (1 != 0)
    {
      if (paramString.contains(".mp4"))
      {
        localObject = new Intent("android.intent.action.VIEW");
        ((Intent)localObject).setDataAndType(Uri.parse(paramString), "video/mp4");
        if (isCallable((Intent)localObject))
        {
          if ((AppId.equals("1875201f2941")) || (AppId.equals("9267bae0cebd")))
          {
            startActivity((Intent)localObject);
            return;
          }
          if (paramString.equals("http://snappy.appypie.com/media/user_space/34348da04799/video/upload_1_1440234829340_1443472517.mp4"))
          {
            startActivity((Intent)localObject);
            return;
          }
          localObject = new Intent(this, TvPlay.class);
          ((Intent)localObject).putExtra("url", paramString);
          startActivity((Intent)localObject);
          return;
        }
        localObject = new Intent(this, TvPlay.class);
        ((Intent)localObject).putExtra("url", paramString);
        startActivity((Intent)localObject);
        return;
      }
      if (paramString.equals("http://alhadath-geo-live.hls.adaptive.level3.net/hls-live/alhadath-channel01/_definst_/live/stream1.m3u8"))
      {
        localObject = new Intent(this, M3uPlayer.class);
        ((Intent)localObject).putExtra("url", paramString);
        startActivity((Intent)localObject);
        return;
      }
      localObject = new Intent(this, TvPlay.class);
      ((Intent)localObject).putExtra("url", paramString);
      startActivity((Intent)localObject);
      return;
    }
    this.alert.showAlertDialog(this, MyApplicationName.APP_NAME, "For this Feature you have to rebuilt the App or contact to support@appypie.com", 300);
  }
  
  @JavascriptInterface
  public void liveVideoPlayMethodSocial(String paramString)
  {
    try
    {
      Intent localIntent = new Intent(this, TvPlay.class);
      localIntent.putExtra("url", paramString);
      startActivity(localIntent);
      return;
    }
    catch (Exception paramString) {}
  }
  
  @JavascriptInterface
  public void loginToFacebook(final String paramString)
  {
    Log.e("Facebook", "Login Method Call.....");
    if (fbID != 0L) {
      disconnectFromFacebook();
    }
    if (this.callbackManager != null) {
      Log.i("Facebook", "CallbackManager is not null.....");
    }
    System.out.println("Facebook Login Proceed ......");
    LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList(new String[] { "public_profile", "email" }));
    LoginManager.getInstance().registerCallback(this.callbackManager, new FacebookCallback()
    {
      public void onCancel()
      {
        Log.e("Facebook", "Login Cancel.....");
      }
      
      public void onError(FacebookException paramAnonymousFacebookException)
      {
        Log.e("Facebook", "Login Error.....");
      }
      
      public void onSuccess(LoginResult paramAnonymousLoginResult)
      {
        Log.e("Facebook", "Login Successfull.....");
        paramAnonymousLoginResult.getAccessToken().getUserId();
        MyPhoneGapActivity.this.makeMeRequest(paramString);
      }
    });
  }
  
  @JavascriptInterface
  public void logout()
  {
    this.mpreferences.setFacebook_login(0);
    this.mpreferences.setNAME("");
    this.mpreferences.setEMAIL("");
    disconnectFromFacebook();
  }
  
  @JavascriptInterface
  public void makeCall(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.DIAL");
    localIntent.setData(Uri.parse("tel:" + paramString));
    startActivity(localIntent);
  }
  
  public void notifyMessage(String paramString)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle("Notification Message");
    localBuilder.setMessage(paramString);
    localBuilder.setCancelable(true);
    localBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    });
    localBuilder.show();
  }
  
  /* Error */
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    // Byte code:
    //   0: aload_0
    //   1: iload_1
    //   2: iload_2
    //   3: aload_3
    //   4: invokespecial 2864	org/apache/cordova/CordovaActivity:onActivityResult	(IILandroid/content/Intent;)V
    //   7: aload_0
    //   8: getfield 2803	com/example/example75f1799f07eb/MyPhoneGapActivity:callbackManager	Lcom/facebook/CallbackManager;
    //   11: iload_1
    //   12: iload_2
    //   13: aload_3
    //   14: invokeinterface 2869 4 0
    //   19: pop
    //   20: getstatic 2871	com/example/example75f1799f07eb/MyPhoneGapActivity:activityResultCallback	Lorg/apache/cordova/CordovaPlugin;
    //   23: astore 5
    //   25: aload 5
    //   27: ifnull +11 -> 38
    //   30: aload 5
    //   32: iload_1
    //   33: iload_2
    //   34: aload_3
    //   35: invokevirtual 2874	org/apache/cordova/CordovaPlugin:onActivityResult	(IILandroid/content/Intent;)V
    //   38: iload_1
    //   39: iconst_1
    //   40: if_icmpne +58 -> 98
    //   43: aload_0
    //   44: getfield 1037	com/example/example75f1799f07eb/MyPhoneGapActivity:mUploadMessage	Landroid/webkit/ValueCallback;
    //   47: ifnonnull +4 -> 51
    //   50: return
    //   51: aload_3
    //   52: ifnull +8 -> 60
    //   55: iload_2
    //   56: iconst_m1
    //   57: if_icmpeq +417 -> 474
    //   60: aconst_null
    //   61: astore 5
    //   63: aload_0
    //   64: getfield 2876	com/example/example75f1799f07eb/MyPhoneGapActivity:mCapturedImageURI	Landroid/net/Uri;
    //   67: ifnull +416 -> 483
    //   70: aload 5
    //   72: ifnonnull +411 -> 483
    //   75: aload_0
    //   76: getfield 1037	com/example/example75f1799f07eb/MyPhoneGapActivity:mUploadMessage	Landroid/webkit/ValueCallback;
    //   79: aload_0
    //   80: getfield 2876	com/example/example75f1799f07eb/MyPhoneGapActivity:mCapturedImageURI	Landroid/net/Uri;
    //   83: invokeinterface 2881 2 0
    //   88: aload_0
    //   89: aconst_null
    //   90: putfield 1037	com/example/example75f1799f07eb/MyPhoneGapActivity:mUploadMessage	Landroid/webkit/ValueCallback;
    //   93: aload_0
    //   94: aconst_null
    //   95: putfield 2876	com/example/example75f1799f07eb/MyPhoneGapActivity:mCapturedImageURI	Landroid/net/Uri;
    //   98: iload_1
    //   99: sipush 141
    //   102: if_icmpne +12 -> 114
    //   105: iload_2
    //   106: iconst_m1
    //   107: if_icmpne +3 -> 110
    //   110: iload_2
    //   111: ifne +3 -> 114
    //   114: iload_1
    //   115: sipush 1234
    //   118: if_icmpne +55 -> 173
    //   121: iload_2
    //   122: iconst_m1
    //   123: if_icmpne +50 -> 173
    //   126: getstatic 2884	com/ons/chat/Option:imagePath	Ljava/lang/String;
    //   129: astore 5
    //   131: ldc_w 2886
    //   134: aload 5
    //   136: invokestatic 1325	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   139: pop
    //   140: getstatic 1008	com/example/example75f1799f07eb/MyPhoneGapActivity:cwv	Lorg/apache/cordova/engine/SystemWebView;
    //   143: new 736	java/lang/StringBuilder
    //   146: dup
    //   147: invokespecial 737	java/lang/StringBuilder:<init>	()V
    //   150: ldc_w 2888
    //   153: invokevirtual 743	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: aload 5
    //   158: invokevirtual 743	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   161: ldc_w 2890
    //   164: invokevirtual 743	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: invokevirtual 748	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   170: invokevirtual 1021	org/apache/cordova/engine/SystemWebView:loadUrl	(Ljava/lang/String;)V
    //   173: iload_1
    //   174: bipush 111
    //   176: if_icmpne +55 -> 231
    //   179: iload_2
    //   180: iconst_m1
    //   181: if_icmpne +50 -> 231
    //   184: aload_0
    //   185: getfield 493	com/example/example75f1799f07eb/MyPhoneGapActivity:picCheck	I
    //   188: iconst_1
    //   189: if_icmpne +324 -> 513
    //   192: getstatic 1008	com/example/example75f1799f07eb/MyPhoneGapActivity:cwv	Lorg/apache/cordova/engine/SystemWebView;
    //   195: new 736	java/lang/StringBuilder
    //   198: dup
    //   199: invokespecial 737	java/lang/StringBuilder:<init>	()V
    //   202: ldc_w 2892
    //   205: invokevirtual 743	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: aload_0
    //   209: aload_0
    //   210: getfield 2876	com/example/example75f1799f07eb/MyPhoneGapActivity:mCapturedImageURI	Landroid/net/Uri;
    //   213: invokevirtual 2894	com/example/example75f1799f07eb/MyPhoneGapActivity:getRealPathFromURI	(Landroid/net/Uri;)Ljava/lang/String;
    //   216: invokevirtual 743	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   219: ldc_w 2000
    //   222: invokevirtual 743	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: invokevirtual 748	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   228: invokevirtual 1021	org/apache/cordova/engine/SystemWebView:loadUrl	(Ljava/lang/String;)V
    //   231: iload_1
    //   232: bipush 9
    //   234: if_icmpne +78 -> 312
    //   237: iload_2
    //   238: iconst_m1
    //   239: if_icmpne +73 -> 312
    //   242: aload_0
    //   243: aload_3
    //   244: ldc_w 2896
    //   247: invokevirtual 2899	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   250: putfield 2901	com/example/example75f1799f07eb/MyPhoneGapActivity:barResult	Ljava/lang/String;
    //   253: aload_0
    //   254: aload_3
    //   255: ldc_w 2903
    //   258: invokevirtual 2899	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   261: putfield 2905	com/example/example75f1799f07eb/MyPhoneGapActivity:barFormat	Ljava/lang/String;
    //   264: getstatic 1008	com/example/example75f1799f07eb/MyPhoneGapActivity:cwv	Lorg/apache/cordova/engine/SystemWebView;
    //   267: new 736	java/lang/StringBuilder
    //   270: dup
    //   271: invokespecial 737	java/lang/StringBuilder:<init>	()V
    //   274: ldc_w 2907
    //   277: invokevirtual 743	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   280: aload_0
    //   281: getfield 2901	com/example/example75f1799f07eb/MyPhoneGapActivity:barResult	Ljava/lang/String;
    //   284: invokevirtual 743	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   287: ldc_w 2909
    //   290: invokevirtual 743	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   293: aload_0
    //   294: getfield 2905	com/example/example75f1799f07eb/MyPhoneGapActivity:barFormat	Ljava/lang/String;
    //   297: invokevirtual 743	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   300: ldc_w 2000
    //   303: invokevirtual 743	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   306: invokevirtual 748	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   309: invokevirtual 1021	org/apache/cordova/engine/SystemWebView:loadUrl	(Ljava/lang/String;)V
    //   312: aload_0
    //   313: getfield 2911	com/example/example75f1799f07eb/MyPhoneGapActivity:bp	Lcom/anjlab/android/iab/v3/BillingProcessor;
    //   316: iload_1
    //   317: iload_2
    //   318: aload_3
    //   319: invokevirtual 2916	com/anjlab/android/iab/v3/BillingProcessor:handleActivityResult	(IILandroid/content/Intent;)Z
    //   322: ifne +10 -> 332
    //   325: aload_0
    //   326: iload_1
    //   327: iload_2
    //   328: aload_3
    //   329: invokespecial 2864	org/apache/cordova/CordovaActivity:onActivityResult	(IILandroid/content/Intent;)V
    //   332: getstatic 734	java/lang/System:out	Ljava/io/PrintStream;
    //   335: new 736	java/lang/StringBuilder
    //   338: dup
    //   339: invokespecial 737	java/lang/StringBuilder:<init>	()V
    //   342: ldc_w 2918
    //   345: invokevirtual 743	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   348: iload_2
    //   349: invokevirtual 1319	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   352: invokevirtual 748	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   355: invokevirtual 754	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   358: getstatic 734	java/lang/System:out	Ljava/io/PrintStream;
    //   361: new 736	java/lang/StringBuilder
    //   364: dup
    //   365: invokespecial 737	java/lang/StringBuilder:<init>	()V
    //   368: ldc_w 2920
    //   371: invokevirtual 743	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   374: aload_3
    //   375: invokevirtual 2921	android/content/Intent:toString	()Ljava/lang/String;
    //   378: invokevirtual 743	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   381: invokevirtual 748	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   384: invokevirtual 754	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   387: iload_2
    //   388: iconst_1
    //   389: if_icmpne -339 -> 50
    //   392: aload_0
    //   393: getfield 558	com/example/example75f1799f07eb/MyPhoneGapActivity:paymentStatus	I
    //   396: iconst_1
    //   397: if_icmpne -347 -> 50
    //   400: aload_0
    //   401: getfield 564	com/example/example75f1799f07eb/MyPhoneGapActivity:signupPaymentCheck	Ljava/lang/String;
    //   404: ldc_w 425
    //   407: invokevirtual 713	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   410: ifeq -360 -> 50
    //   413: getstatic 734	java/lang/System:out	Ljava/io/PrintStream;
    //   416: ldc_w 2923
    //   419: invokevirtual 754	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   422: getstatic 1008	com/example/example75f1799f07eb/MyPhoneGapActivity:cwv	Lorg/apache/cordova/engine/SystemWebView;
    //   425: ldc_w 2925
    //   428: invokevirtual 1021	org/apache/cordova/engine/SystemWebView:loadUrl	(Ljava/lang/String;)V
    //   431: aload_0
    //   432: getfield 2911	com/example/example75f1799f07eb/MyPhoneGapActivity:bp	Lcom/anjlab/android/iab/v3/BillingProcessor;
    //   435: aload_0
    //   436: getfield 562	com/example/example75f1799f07eb/MyPhoneGapActivity:paymentId	Ljava/lang/String;
    //   439: invokevirtual 2928	com/anjlab/android/iab/v3/BillingProcessor:consumePurchase	(Ljava/lang/String;)Z
    //   442: istore 4
    //   444: getstatic 734	java/lang/System:out	Ljava/io/PrintStream;
    //   447: new 736	java/lang/StringBuilder
    //   450: dup
    //   451: invokespecial 737	java/lang/StringBuilder:<init>	()V
    //   454: ldc_w 2930
    //   457: invokevirtual 743	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   460: iload 4
    //   462: invokevirtual 1191	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   465: invokevirtual 748	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   468: invokevirtual 754	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   471: return
    //   472: astore_3
    //   473: return
    //   474: aload_3
    //   475: invokevirtual 2934	android/content/Intent:getData	()Landroid/net/Uri;
    //   478: astore 5
    //   480: goto -417 -> 63
    //   483: aload_0
    //   484: getfield 1037	com/example/example75f1799f07eb/MyPhoneGapActivity:mUploadMessage	Landroid/webkit/ValueCallback;
    //   487: aload 5
    //   489: invokeinterface 2881 2 0
    //   494: goto -406 -> 88
    //   497: astore 5
    //   499: aload_0
    //   500: ldc_w 2936
    //   503: iconst_1
    //   504: invokestatic 2375	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   507: invokevirtual 2377	android/widget/Toast:show	()V
    //   510: goto -337 -> 173
    //   513: aload_0
    //   514: getfield 493	com/example/example75f1799f07eb/MyPhoneGapActivity:picCheck	I
    //   517: iconst_2
    //   518: if_icmpne -287 -> 231
    //   521: aload_3
    //   522: invokevirtual 2934	android/content/Intent:getData	()Landroid/net/Uri;
    //   525: astore 5
    //   527: getstatic 1008	com/example/example75f1799f07eb/MyPhoneGapActivity:cwv	Lorg/apache/cordova/engine/SystemWebView;
    //   530: new 736	java/lang/StringBuilder
    //   533: dup
    //   534: invokespecial 737	java/lang/StringBuilder:<init>	()V
    //   537: ldc_w 2892
    //   540: invokevirtual 743	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   543: aload_0
    //   544: aload 5
    //   546: invokevirtual 2894	com/example/example75f1799f07eb/MyPhoneGapActivity:getRealPathFromURI	(Landroid/net/Uri;)Ljava/lang/String;
    //   549: invokevirtual 743	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   552: ldc_w 2000
    //   555: invokevirtual 743	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   558: invokevirtual 748	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   561: invokevirtual 1021	org/apache/cordova/engine/SystemWebView:loadUrl	(Ljava/lang/String;)V
    //   564: goto -333 -> 231
    //   567: astore 5
    //   569: goto -338 -> 231
    //   572: astore_3
    //   573: getstatic 734	java/lang/System:out	Ljava/io/PrintStream;
    //   576: ldc_w 2938
    //   579: invokevirtual 754	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   582: return
    //   583: astore 5
    //   585: goto -273 -> 312
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	588	0	this	MyPhoneGapActivity
    //   0	588	1	paramInt1	int
    //   0	588	2	paramInt2	int
    //   0	588	3	paramIntent	Intent
    //   442	19	4	bool	boolean
    //   23	465	5	localObject	Object
    //   497	1	5	localException1	Exception
    //   525	20	5	localUri	Uri
    //   567	1	5	localException2	Exception
    //   583	1	5	localException3	Exception
    // Exception table:
    //   from	to	target	type
    //   431	471	472	java/lang/Exception
    //   126	173	497	java/lang/Exception
    //   184	231	567	java/lang/Exception
    //   513	564	567	java/lang/Exception
    //   312	332	572	java/lang/Exception
    //   332	387	572	java/lang/Exception
    //   392	431	572	java/lang/Exception
    //   242	312	583	java/lang/Exception
  }
  
  public void onBillingError(int paramInt, Throwable paramThrowable)
  {
    System.out.println("krishna<<<<<<<<<<<<<<<<<onBillingError");
    this.paymentStatus = 1;
    if (this.signupPaymentCheck.equalsIgnoreCase("true"))
    {
      this.signupPaymentCheck = "";
      cwv.loadUrl("javascript:signUpPaymentError();");
      return;
    }
    cwv.loadUrl("javascript:inappSuccess('failed');");
  }
  
  public void onBillingInitialized() {}
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    this.callbackManager = CallbackManager.Factory.create();
    super.init();
    sharedpreferences = getSharedPreferences("MyPrefs", 0);
    this.fbPreference = getSharedPreferences("MyFBPrefs", 0);
    this.mpreferences = new Preferences(getApplicationContext());
    osVersion = Build.VERSION.RELEASE;
    apiLevel = String.valueOf(Build.VERSION.SDK_INT);
    model = Build.MODEL;
    device = Build.DEVICE;
    manufacturer = Build.MANUFACTURER;
    if ((manufacturer.equalsIgnoreCase("amazon")) || (manufacturer.contains("amazon")) || (manufacturer.contains("Amazon"))) {
      this.alert.showAlertDialog(this, MyApplicationName.APP_NAME, "Application not supported on this device.", 22);
    }
    for (;;)
    {
      return;
      setContentView(2130903099);
      this.closeAds = ((ImageView)findViewById(2131558462));
      this.closeAds1 = ((ImageView)findViewById(2131558597));
      appName = getApplicationName();
      copyAappypieXMl();
      this.cdConnectionDetector = new ConnectionDetector(this);
      AppId = getAppId();
      try
      {
        paramBundle = getPackageManager().getPackageInfo("com.appypie.appypief21af891b956", 64);
        System.out.println("fb ckecking");
        paramBundle = paramBundle.signatures;
        int j = paramBundle.length;
        int i = 0;
        while (i < j)
        {
          str = paramBundle[i];
          System.out.println("fb ckecking sig matched");
          localObject = MessageDigest.getInstance("SHA");
          ((MessageDigest)localObject).update(str.toByteArray());
          Log.e("MY KEY HASH:", "KEY Hash " + Base64.encodeToString(((MessageDigest)localObject).digest(), 0));
          i += 1;
        }
      }
      catch (PackageManager.NameNotFoundException paramBundle)
      {
        System.out.println(paramBundle.toString());
        this.h = new Handler();
        this.r = new Runnable()
        {
          public void run()
          {
            Process.setThreadPriority(10);
            MyPhoneGapActivity.this.getFenceData();
            MyPhoneGapActivity.this.h.postDelayed(this, 2000L);
          }
        };
        this.h.postDelayed(this.r, 2000L);
        try
        {
          if ((this.beaconStatus.equalsIgnoreCase("true")) && (Build.VERSION.SDK_INT > 17))
          {
            if (!this.bound)
            {
              this.iBeaconManager.bind(this);
              this.bound = true;
            }
            Log.e("", "onresume after beacon starts now....." + new Date().getSeconds() / 1000);
          }
        }
        catch (Exception paramBundle)
        {
          for (;;) {}
        }
        Log.e("", "splash starts now....." + new Date().getSeconds() / 1000);
        paramBundle = new Intent();
        paramBundle.setClass(this, SplashScreenActivity.class);
        startActivity(paramBundle);
        this.adLayout = ((FrameLayout)findViewById(2131558460));
        this.adLayout1 = ((FrameLayout)findViewById(2131558595));
        this.admobBanner = ((RelativeLayout)findViewById(2131558593));
        this.layout = ((RelativeLayout)findViewById(2131558596));
        this.adLayout2 = ((FrameLayout)findViewById(2131558599));
        this.closeAds2 = ((ImageView)findViewById(2131558600));
        this.adLayout2.setVisibility(4);
        this.closeAds2.setVisibility(4);
        this.offlinemodeImage = ((ImageView)findViewById(2131558594));
        this.offlinemodeImage1 = ((ImageView)findViewById(2131558598));
        this.offlinemodeImage2 = ((ImageView)findViewById(2131558601));
        Log.e("", "getting current location starts now....." + new Date().getSeconds() / 1000);
      }
      catch (NoSuchAlgorithmException paramBundle)
      {
        try
        {
          label332:
          onNewIntent(getIntent());
          paramBundle = (LocationManager)getApplicationContext().getSystemService("location");
          new Criteria();
          paramBundle.requestLocationUpdates("network", 0L, 0.0F, new LocationListener()
          {
            public void onLocationChanged(Location paramAnonymousLocation)
            {
              MyPhoneGapActivity.lat = Double.toString(paramAnonymousLocation.getLatitude());
              MyPhoneGapActivity.lng = Double.toString(paramAnonymousLocation.getLongitude());
            }
            
            public void onProviderDisabled(String paramAnonymousString) {}
            
            public void onProviderEnabled(String paramAnonymousString) {}
            
            public void onStatusChanged(String paramAnonymousString, int paramAnonymousInt, Bundle paramAnonymousBundle) {}
          });
          label742:
          if (sharedpreferences.getInt("notificationCount", -1) == -1)
          {
            editor = sharedpreferences.edit();
            editor.putInt("notificationCount", 0);
            editor.apply();
          }
          if (!this.cdConnectionDetector.isConnectingToInternet()) {}
        }
        catch (Exception paramBundle)
        {
          try
          {
            String str;
            Object localObject;
            label800:
            paramBundle = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new FileInputStream(new File(getFilesDir(), "appypie.xml")));
            paramBundle.getDocumentElement().normalize();
            this.navigationLayout = paramBundle.getElementsByTagName("layout").item(0).getTextContent().toString();
            System.out.println("SplashScreenActivity called");
            cwv = (SystemWebView)findViewById(2131558578);
            cwv.addJavascriptInterface(this, "toaster");
            paramBundle = new ConfigXmlParser();
            paramBundle.parse(this);
            this.cordovaWebView = new CordovaWebViewImpl(new SystemWebViewEngine(cwv));
            this.cordovaWebView.init(this.cordovaInterface, paramBundle.getPluginEntries(), paramBundle.getPreferences());
            cwv1 = (WebView)findViewById(2131558584);
            cwv1.addJavascriptInterface(this, "krishna");
            this.mapWebView = ((WebView)findViewById(2131558586));
            this.forward = ((ImageButton)findViewById(2131558582));
            this.backward = ((ImageButton)findViewById(2131558581));
            this.reloadIcon = ((ImageButton)findViewById(2131558580));
            this.reloadtwitterheader = ((FrameLayout)findViewById(2131558579));
            System.out.println("krishna android version>>>" + Build.VERSION.SDK_INT);
            if (Build.VERSION.SDK_INT > 17) {}
            try
            {
              cwv1.getSettings().setMediaPlaybackRequiresUserGesture(false);
              if (AppId.equalsIgnoreCase("fd9a066e2787"))
              {
                cwv1.setLayerType(1, null);
                cwv1.setBackgroundColor(Color.parseColor("#284b77"));
              }
              this.customViewContainer = ((FrameLayout)findViewById(2131558602));
              this.startNavigation = ((TextView)findViewById(2131558592));
              this.bottomDirection = ((RelativeLayout)findViewById(2131558590));
              this.bottomDirection.setVisibility(4);
              this.topNavigation = ((TextView)findViewById(2131558589));
              this.topDirection = ((RelativeLayout)findViewById(2131558587));
              this.topDirection.setVisibility(4);
              this.topImage = ((ImageView)findViewById(2131558588));
              this.bottomImage = ((ImageView)findViewById(2131558591));
              this.deviceModel = Build.MODEL;
              paramBundle = new DisplayMetrics();
              getWindowManager().getDefaultDisplay().getMetrics(paramBundle);
              this.screenInches = Math.sqrt(Math.pow(paramBundle.widthPixels / paramBundle.xdpi, 2.0D) + Math.pow(paramBundle.heightPixels / paramBundle.ydpi, 2.0D));
              this.bottomImage.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymousView)
                {
                  paramAnonymousView = new Intent("android.intent.action.VIEW", Uri.parse("google.navigation:q=" + MyPhoneGapActivity.this.mapLatitide + "," + MyPhoneGapActivity.this.mapLongitude));
                  paramAnonymousView.addFlags(268435456);
                  MyPhoneGapActivity.this.startActivityForResult(paramAnonymousView, 141);
                }
              });
              this.backward.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymousView)
                {
                  if (MyPhoneGapActivity.cwv1.canGoBack())
                  {
                    if ((!MyPhoneGapActivity.AppId.equalsIgnoreCase("2b77f6096336")) && (!MyPhoneGapActivity.cwv1.getOriginalUrl().equalsIgnoreCase("http://aswaaq.net/"))) {
                      MyPhoneGapActivity.cwv1.goBack();
                    }
                    if (((MyPhoneGapActivity.cwv1.getOriginalUrl().equalsIgnoreCase("about:blank")) || (MyPhoneGapActivity.cwv1.getOriginalUrl().equalsIgnoreCase("")) || (MyPhoneGapActivity.cwv1.getOriginalUrl().toString().length() == 0)) && (!MyPhoneGapActivity.AppId.equalsIgnoreCase("2b77f6096336")) && (!MyPhoneGapActivity.cwv1.getOriginalUrl().equalsIgnoreCase("http://aswaaq.net/"))) {
                      MyPhoneGapActivity.cwv.loadUrl("javascript:onBackKeyDown();");
                    }
                  }
                }
              });
              this.forward.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymousView)
                {
                  if (MyPhoneGapActivity.cwv1.canGoForward()) {
                    MyPhoneGapActivity.cwv1.goForward();
                  }
                }
              });
              this.reloadIcon.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymousView)
                {
                  MyPhoneGapActivity.cwv1.loadUrl("javascript:window.location.reload( true )");
                }
              });
              this.pd = ((ProgressBar)findViewById(2131558555));
              cwv.setOnTouchListener(new View.OnTouchListener()
              {
                public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
                {
                  if (MyPhoneGapActivity.this.pd.isShown()) {
                    MyPhoneGapActivity.this.pd.setVisibility(8);
                  }
                  return false;
                }
              });
              cwv1.setOnTouchListener(new View.OnTouchListener()
              {
                public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
                {
                  if (MyPhoneGapActivity.this.pd.isShown()) {
                    MyPhoneGapActivity.this.pd.setVisibility(8);
                  }
                  return false;
                }
              });
              this.audio = ((AudioManager)getSystemService("audio"));
              MyApplicationName.APP_NAME = getResources().getString(2131230720);
              if (Build.VERSION.SDK_INT > 15)
              {
                fixJellyBeanIssues();
                if (getIntent().getStringExtra("fromnotification") != null)
                {
                  localObject = PreferenceManager.getDefaultSharedPreferences(this);
                  paramBundle = ((SharedPreferences)localObject).getString("lastBeaconUUID", "");
                  str = ((SharedPreferences)localObject).getString("lastBeaconMinor", "");
                  localObject = ((SharedPreferences)localObject).getString("lastBeaconMajor", "");
                  if (this.appLanguage.equalsIgnoreCase("sa")) {
                    if (!this.navigationLayout.equals("slidemenu")) {
                      cwv.loadUrl("file:///android_asset/www/index3.html?appid=" + paramBundle + "@@" + str + "@@" + (String)localObject + "");
                    }
                  }
                }
                for (;;)
                {
                  paramBundle = cwv.getSettings();
                  paramBundle.setJavaScriptEnabled(true);
                  paramBundle.setJavaScriptCanOpenWindowsAutomatically(true);
                  cwv.getSettings().setLoadWithOverviewMode(true);
                  cwv.getSettings().setDomStorageEnabled(true);
                  paramBundle.setDatabaseEnabled(true);
                  if (Build.VERSION.SDK_INT >= 16) {
                    paramBundle.setAllowUniversalAccessFromFileURLs(true);
                  }
                  paramBundle = getApplicationContext().getDir("databases", 0).getPath();
                  cwv.getSettings().setDatabasePath(paramBundle);
                  cwv.requestFocus();
                  cwv.requestFocusFromTouch();
                  cwv.requestFocus(130);
                  this.reloadtwitterheader.setVisibility(4);
                  cwv.setWebViewClient(new SystemWebViewClient((SystemWebViewEngine)this.appView.getEngine())
                  {
                    public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
                    {
                      String str1;
                      if (paramAnonymousString.contains("@@@@@"))
                      {
                        MyPhoneGapActivity.this.inAppURL = paramAnonymousString.split("@@@@@");
                        MyPhoneGapActivity.access$102(MyPhoneGapActivity.this, MyPhoneGapActivity.this.inAppURL[1]);
                        str1 = MyPhoneGapActivity.this.inAppURL[0];
                        if (!str1.contains("#####")) {
                          break label419;
                        }
                        str1.substring(12, str1.length());
                        paramAnonymousString = new Intent(MyPhoneGapActivity.this, com.ons.musicplayer.MainActivity.class);
                        paramAnonymousString.putExtra("songArray", str1);
                        MyPhoneGapActivity.this.startActivity(paramAnonymousString);
                      }
                      for (;;)
                      {
                        label104:
                        if (str1.equals("file:///android_asset/www/close")) {
                          MyPhoneGapActivity.this.finish();
                        }
                        if (str1.equalsIgnoreCase("removeWebSite:"))
                        {
                          MyPhoneGapActivity.cwv1.stopLoading();
                          MyPhoneGapActivity.cwv1.loadUrl("");
                          MyPhoneGapActivity.cwv1.setVisibility(8);
                          MyPhoneGapActivity.this.reloadtwitterheader.setVisibility(4);
                          MyPhoneGapActivity.this.mapWebView.stopLoading();
                          MyPhoneGapActivity.this.mapWebView.loadUrl("");
                          MyPhoneGapActivity.this.mapWebView.setVisibility(8);
                          MyPhoneGapActivity.this.showAndHideAdView("show");
                          MyPhoneGapActivity.this.topDirection.setVisibility(4);
                          MyPhoneGapActivity.this.bottomDirection.setVisibility(4);
                          MyPhoneGapActivity.this.startNavigation.setVisibility(4);
                          MyPhoneGapActivity.this.topNavigation.setVisibility(4);
                          label251:
                          paramAnonymousString = MyPhoneGapActivity.cwv1.getSettings();
                          paramAnonymousString.setJavaScriptEnabled(true);
                          paramAnonymousString.setJavaScriptCanOpenWindowsAutomatically(true);
                        }
                        try
                        {
                          MyPhoneGapActivity.cwv1.getSettings().setSupportZoom(true);
                          MyPhoneGapActivity.cwv1.getSettings().setBuiltInZoomControls(true);
                          if (Build.VERSION.SDK_INT >= 11) {
                            new Runnable()
                            {
                              public void run()
                              {
                                MyPhoneGapActivity.cwv1.getSettings().setDisplayZoomControls(false);
                              }
                            }.run();
                          }
                          for (;;)
                          {
                            paramAnonymousString.setPluginState(WebSettings.PluginState.ON);
                            paramAnonymousString.setDomStorageEnabled(true);
                            MyPhoneGapActivity.cwv1.setId(6);
                            MyPhoneGapActivity.cwv1.requestFocus(130);
                            MyPhoneGapActivity.cwv1.setFocusable(true);
                            MyPhoneGapActivity.cwv1.setFocusableInTouchMode(true);
                            MyPhoneGapActivity.cwv1.getSettings().setUseWideViewPort(true);
                            MyPhoneGapActivity.cwv1.getSettings().setLoadWithOverviewMode(true);
                            MyPhoneGapActivity.cwv1.getSettings().setSupportMultipleWindows(true);
                            MyPhoneGapActivity.cwv1.setWebChromeClient(new WebChromeClient()
                            {
                              private Bitmap mDefaultVideoPoster;
                              private View mVideoProgressView;
                              
                              public View getVideoLoadingProgressView()
                              {
                                if (this.mVideoProgressView == null) {
                                  this.mVideoProgressView = LayoutInflater.from(MyPhoneGapActivity.this).inflate(2130903158, null);
                                }
                                return this.mVideoProgressView;
                              }
                              
                              public boolean onCreateWindow(WebView paramAnonymous2WebView, boolean paramAnonymous2Boolean1, boolean paramAnonymous2Boolean2, Message paramAnonymous2Message)
                              {
                                paramAnonymous2WebView = new WebView(paramAnonymous2WebView.getContext());
                                paramAnonymous2WebView.setWebViewClient(new WebViewClient()
                                {
                                  public boolean shouldOverrideUrlLoading(WebView paramAnonymous3WebView, String paramAnonymous3String)
                                  {
                                    paramAnonymous3WebView = new Intent("android.intent.action.VIEW", Uri.parse(paramAnonymous3String));
                                    MyPhoneGapActivity.this.startActivity(paramAnonymous3WebView);
                                    return true;
                                  }
                                });
                                ((WebView.WebViewTransport)paramAnonymous2Message.obj).setWebView(paramAnonymous2WebView);
                                paramAnonymous2Message.sendToTarget();
                                return true;
                              }
                              
                              public void onGeolocationPermissionsShowPrompt(String paramAnonymous2String, GeolocationPermissions.Callback paramAnonymous2Callback)
                              {
                                paramAnonymous2Callback.invoke(paramAnonymous2String, true, false);
                                MyPhoneGapActivity.this.showEnableDialog(paramAnonymous2String);
                              }
                              
                              public void onHideCustomView()
                              {
                                super.onHideCustomView();
                                if (MyPhoneGapActivity.this.mCustomView == null) {
                                  return;
                                }
                                MyPhoneGapActivity.cwv1.setVisibility(0);
                                MyPhoneGapActivity.this.customViewContainer.setVisibility(8);
                                MyPhoneGapActivity.this.mCustomView.setVisibility(8);
                                MyPhoneGapActivity.this.customViewContainer.removeView(MyPhoneGapActivity.this.mCustomView);
                                MyPhoneGapActivity.this.customViewCallback.onCustomViewHidden();
                                MyPhoneGapActivity.access$1302(MyPhoneGapActivity.this, null);
                              }
                              
                              public void onShowCustomView(View paramAnonymous2View, int paramAnonymous2Int, WebChromeClient.CustomViewCallback paramAnonymous2CustomViewCallback)
                              {
                                onShowCustomView(paramAnonymous2View, paramAnonymous2CustomViewCallback);
                              }
                              
                              public void onShowCustomView(View paramAnonymous2View, WebChromeClient.CustomViewCallback paramAnonymous2CustomViewCallback)
                              {
                                if (MyPhoneGapActivity.this.mCustomView != null)
                                {
                                  paramAnonymous2CustomViewCallback.onCustomViewHidden();
                                  return;
                                }
                                MyPhoneGapActivity.access$1302(MyPhoneGapActivity.this, paramAnonymous2View);
                                MyPhoneGapActivity.cwv1.setVisibility(8);
                                MyPhoneGapActivity.this.customViewContainer.setVisibility(0);
                                MyPhoneGapActivity.this.customViewContainer.addView(paramAnonymous2View);
                                MyPhoneGapActivity.access$1502(MyPhoneGapActivity.this, paramAnonymous2CustomViewCallback);
                              }
                              
                              public boolean onShowFileChooser(WebView paramAnonymous2WebView, final ValueCallback<Uri[]> paramAnonymous2ValueCallback, WebChromeClient.FileChooserParams paramAnonymous2FileChooserParams)
                              {
                                String[] arrayOfString = paramAnonymous2FileChooserParams.getAcceptTypes();
                                paramAnonymous2WebView = "";
                                int i = 0;
                                while (i < arrayOfString.length)
                                {
                                  paramAnonymous2FileChooserParams = paramAnonymous2WebView;
                                  if (arrayOfString[i] != null)
                                  {
                                    paramAnonymous2FileChooserParams = paramAnonymous2WebView;
                                    if (arrayOfString[i].length() != 0) {
                                      paramAnonymous2FileChooserParams = paramAnonymous2WebView + arrayOfString[i] + ";";
                                    }
                                  }
                                  i += 1;
                                  paramAnonymous2WebView = paramAnonymous2FileChooserParams;
                                }
                                paramAnonymous2FileChooserParams = paramAnonymous2WebView;
                                if (paramAnonymous2WebView.length() == 0) {
                                  paramAnonymous2FileChooserParams = "*/*";
                                }
                                openFileChooser(new ValueCallback()
                                {
                                  public void onReceiveValue(Uri paramAnonymous3Uri)
                                  {
                                    Uri[] arrayOfUri;
                                    if (paramAnonymous3Uri != null)
                                    {
                                      arrayOfUri = new Uri[1];
                                      arrayOfUri[0] = paramAnonymous3Uri;
                                    }
                                    for (paramAnonymous3Uri = arrayOfUri;; paramAnonymous3Uri = null)
                                    {
                                      paramAnonymous2ValueCallback.onReceiveValue(paramAnonymous3Uri);
                                      return;
                                    }
                                  }
                                }, paramAnonymous2FileChooserParams, "filesystem");
                                return true;
                              }
                              
                              public void openFileChooser(ValueCallback<Uri> paramAnonymous2ValueCallback)
                              {
                                MyPhoneGapActivity.access$1102(MyPhoneGapActivity.this, paramAnonymous2ValueCallback);
                                paramAnonymous2ValueCallback = new Intent("android.intent.action.GET_CONTENT");
                                paramAnonymous2ValueCallback.addCategory("android.intent.category.OPENABLE");
                                paramAnonymous2ValueCallback.setType("image/*");
                                try
                                {
                                  Object localObject = new ContentValues();
                                  ((ContentValues)localObject).put("title", "temp.jpg");
                                  MyPhoneGapActivity.this.mCapturedImageURI = MyPhoneGapActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, (ContentValues)localObject);
                                  localObject = new Intent("android.media.action.IMAGE_CAPTURE");
                                  ((Intent)localObject).putExtra("output", MyPhoneGapActivity.this.mCapturedImageURI);
                                  Intent localIntent = new Intent("android.intent.action.CHOOSER");
                                  localIntent.putExtra("android.intent.extra.INTENT", paramAnonymous2ValueCallback);
                                  localIntent.putExtra("android.intent.extra.TITLE", "Select");
                                  localIntent.putExtra("android.intent.extra.INITIAL_INTENTS", new Intent[] { localObject });
                                  MyPhoneGapActivity.this.startActivityForResult(Intent.createChooser(localIntent, "File Chooser"), 1);
                                  return;
                                }
                                catch (Exception paramAnonymous2ValueCallback) {}
                              }
                              
                              public void openFileChooser(ValueCallback paramAnonymous2ValueCallback, String paramAnonymous2String)
                              {
                                MyPhoneGapActivity.access$1102(MyPhoneGapActivity.this, paramAnonymous2ValueCallback);
                                paramAnonymous2ValueCallback = new Intent("android.intent.action.GET_CONTENT");
                                paramAnonymous2ValueCallback.addCategory("android.intent.category.OPENABLE");
                                paramAnonymous2ValueCallback.setType("*/*");
                                MyPhoneGapActivity.this.startActivityForResult(Intent.createChooser(paramAnonymous2ValueCallback, "File Browser"), 1);
                              }
                              
                              public void openFileChooser(ValueCallback<Uri> paramAnonymous2ValueCallback, String paramAnonymous2String1, String paramAnonymous2String2)
                              {
                                MyPhoneGapActivity.access$1102(MyPhoneGapActivity.this, paramAnonymous2ValueCallback);
                                paramAnonymous2ValueCallback = new Intent("android.intent.action.GET_CONTENT");
                                paramAnonymous2ValueCallback.addCategory("android.intent.category.OPENABLE");
                                paramAnonymous2ValueCallback.setType("image/*");
                                try
                                {
                                  paramAnonymous2String1 = new ContentValues();
                                  paramAnonymous2String1.put("title", "temp.jpg");
                                  MyPhoneGapActivity.this.mCapturedImageURI = MyPhoneGapActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, paramAnonymous2String1);
                                  paramAnonymous2String1 = new Intent("android.media.action.IMAGE_CAPTURE");
                                  paramAnonymous2String1.putExtra("output", MyPhoneGapActivity.this.mCapturedImageURI);
                                  paramAnonymous2String2 = new Intent("android.intent.action.CHOOSER");
                                  paramAnonymous2String2.putExtra("android.intent.extra.INTENT", paramAnonymous2ValueCallback);
                                  paramAnonymous2String2.putExtra("android.intent.extra.TITLE", "Select");
                                  paramAnonymous2String2.putExtra("android.intent.extra.INITIAL_INTENTS", new Intent[] { paramAnonymous2String1 });
                                  MyPhoneGapActivity.this.startActivityForResult(Intent.createChooser(paramAnonymous2String2, "File Chooser"), 1);
                                  return;
                                }
                                catch (Exception paramAnonymous2ValueCallback) {}
                              }
                            });
                            return super.shouldOverrideUrlLoading(paramAnonymousWebView, str1);
                            MyPhoneGapActivity.access$102(MyPhoneGapActivity.this, "false");
                            str1 = paramAnonymousString;
                            break;
                            label419:
                            if (str1.contains("slidemenuissue"))
                            {
                              System.out.println("slidemenuissue" + str1);
                              if (str1.contains("full"))
                              {
                                MyPhoneGapActivity.cwv1.setX(34800.0F);
                                MyPhoneGapActivity.this.reloadtwitterheader.setVisibility(4);
                              }
                              for (;;)
                              {
                                return true;
                                MyPhoneGapActivity.cwv1.setX(0.0F);
                              }
                            }
                            if (str1.contains("foodpaypal:"))
                            {
                              paramAnonymousWebView = new Intent(MyPhoneGapActivity.this, PaypalCheckoutActivity.class);
                              paramAnonymousWebView.putExtra("webviewdata", str1.substring(11));
                              paramAnonymousWebView.putExtra("resellerValue", MyPhoneGapActivity.resellerValue);
                              MyPhoneGapActivity.this.startActivity(paramAnonymousWebView);
                              return true;
                            }
                            if (str1.contains("playvimeo:"))
                            {
                              paramAnonymousString = new Intent(MyPhoneGapActivity.this, Vimeo.class);
                              paramAnonymousString.putExtra("vimeoid", str1.substring(10, str1.length()));
                              MyPhoneGapActivity.this.startActivity(paramAnonymousString);
                              break label104;
                            }
                            if (str1.contains("m3u8url:"))
                            {
                              paramAnonymousString = new Intent(MyPhoneGapActivity.this, M3U8Player.class);
                              paramAnonymousString.putExtra("m3u8id", str1.substring(8, str1.length()));
                              MyPhoneGapActivity.this.startActivity(paramAnonymousString);
                              break label104;
                            }
                            if (str1.contains("adviewlayout:"))
                            {
                              paramAnonymousString = str1.substring(13);
                              MyPhoneGapActivity.this.showAndHideAdView(paramAnonymousString);
                              break label104;
                            }
                            Object localObject;
                            String str2;
                            if (str1.contains("maplocation:"))
                            {
                              localObject = str1.substring(12, str1.length()).split("&&");
                              paramAnonymousString = localObject[0];
                              str2 = localObject[1];
                              localObject = localObject[2];
                              if ((paramAnonymousString == null) || (paramAnonymousString == "")) {}
                            }
                            label926:
                            label1568:
                            label1629:
                            try
                            {
                              if (paramAnonymousString.equalsIgnoreCase("0")) {}
                              for (paramAnonymousString = "http://maps.google.com/maps?q=" + (String)localObject + "&t=m&z=16";; paramAnonymousString = "http://maps.google.com/maps?q=" + paramAnonymousString + "," + str2 + "&t=m&z=16")
                              {
                                str2 = paramAnonymousString;
                                if (localObject != null)
                                {
                                  str2 = paramAnonymousString;
                                  if (((String)localObject).length() > 1)
                                  {
                                    str2 = paramAnonymousString;
                                    if (!((String)localObject).equalsIgnoreCase("abc")) {
                                      str2 = "http://maps.google.com/maps?q=" + (String)localObject + "&t=m&z=16";
                                    }
                                  }
                                }
                                if (!MyPhoneGapActivity.AppId.equals("67c180749a6a")) {
                                  break label926;
                                }
                                MyPhoneGapActivity.this.openWebUrl(str2);
                                break;
                              }
                              paramAnonymousString = new Intent(MyPhoneGapActivity.this, PdfView.class);
                              paramAnonymousString.putExtra("pdfurl", str2);
                              MyPhoneGapActivity.this.startActivity(paramAnonymousString);
                            }
                            catch (Exception paramAnonymousString) {}
                            if (str1.contains("gpslocation:"))
                            {
                              MyPhoneGapActivity.cwv1.stopLoading();
                              MyPhoneGapActivity.cwv1.loadUrl("");
                              MyPhoneGapActivity.cwv1.setVisibility(8);
                              MyPhoneGapActivity.this.reloadtwitterheader.setVisibility(4);
                              paramAnonymousString = str1.substring(13, str1.length()).split("&&");
                              MyPhoneGapActivity.this.mapLatitide = paramAnonymousString[0];
                              MyPhoneGapActivity.this.mapLongitude = paramAnonymousString[1];
                              MyPhoneGapActivity.this.mapAddress = paramAnonymousString[2];
                              double d1 = Double.parseDouble(paramAnonymousString[3]);
                              double d2 = Double.parseDouble(paramAnonymousString[4]);
                              MyPhoneGapActivity.this.currentAddress = MyPhoneGapActivity.this.getCompleteAddressString(d1, d2);
                              paramAnonymousString = MyPhoneGapActivity.this.mapWebView.getSettings();
                              paramAnonymousString.setJavaScriptEnabled(true);
                              paramAnonymousString.setJavaScriptCanOpenWindowsAutomatically(true);
                              try
                              {
                                MyPhoneGapActivity.cwv.getSettings().setSupportZoom(true);
                                MyPhoneGapActivity.cwv.getSettings().setBuiltInZoomControls(true);
                                if (Build.VERSION.SDK_INT >= 11) {
                                  new Runnable()
                                  {
                                    public void run()
                                    {
                                      MyPhoneGapActivity.cwv.getSettings().setDisplayZoomControls(false);
                                    }
                                  }.run();
                                }
                                for (;;)
                                {
                                  paramAnonymousString.setPluginState(WebSettings.PluginState.ON);
                                  paramAnonymousString.setDomStorageEnabled(true);
                                  MyPhoneGapActivity.this.mapWebView.setId(6);
                                  MyPhoneGapActivity.this.mapWebView.getSettings().setLoadWithOverviewMode(true);
                                  MyPhoneGapActivity.this.mapWebView.getSettings().setUseWideViewPort(true);
                                  MyPhoneGapActivity.this.mapWebView.requestFocus();
                                  MyPhoneGapActivity.this.mapWebView.requestFocusFromTouch();
                                  MyPhoneGapActivity.this.mapWebView.clearHistory();
                                  MyPhoneGapActivity.this.mapWebView.clearCache(true);
                                  MyPhoneGapActivity.this.mapWebView.clearView();
                                  if ((!MyPhoneGapActivity.AppId.equals("fcc0b97a13f8")) && (!MyPhoneGapActivity.AppId.equals("82fe34bcdf17")) && (!MyPhoneGapActivity.AppId.equals("a873b3f9a510"))) {
                                    break label1568;
                                  }
                                  MyPhoneGapActivity.this.mapWebView.loadUrl("http://maps.google.com/maps?q=" + MyPhoneGapActivity.this.mapAddress + "&t=m&z=16");
                                  MyPhoneGapActivity.this.mapWebView.setVisibility(0);
                                  MyPhoneGapActivity.this.mapWebView.setWebChromeClient(new WebChromeClient());
                                  MyPhoneGapActivity.this.startNavigation.setTypeface(null, 0);
                                  MyPhoneGapActivity.this.topNavigation.setTypeface(null, 0);
                                  if (MyPhoneGapActivity.this.navigationLayout.equalsIgnoreCase("bottom")) {
                                    ((ViewGroup.MarginLayoutParams)MyPhoneGapActivity.this.bottomDirection.getLayoutParams()).setMargins(0, 0, 0, 90);
                                  }
                                  MyPhoneGapActivity.this.topDirection.setVisibility(0);
                                  MyPhoneGapActivity.this.bottomDirection.setVisibility(0);
                                  MyPhoneGapActivity.this.startNavigation.setVisibility(0);
                                  MyPhoneGapActivity.this.startNavigation.setText(MyPhoneGapActivity.this.mapAddress);
                                  MyPhoneGapActivity.this.topNavigation.setVisibility(0);
                                  MyPhoneGapActivity.this.topNavigation.setText(MyPhoneGapActivity.this.currentAddress);
                                  if (!str1.contains("gpslocation:1")) {
                                    break label1629;
                                  }
                                  if (Build.VERSION.SDK_INT == 10) {
                                    break;
                                  }
                                  MyPhoneGapActivity.this.changeMarginMatrixMap();
                                  break;
                                  ((ZoomButtonsController)MyPhoneGapActivity.cwv.getClass().getMethod("getZoomButtonsController", new Class[0]).invoke(MyPhoneGapActivity.cwv, null)).getContainer().setVisibility(8);
                                }
                              }
                              catch (Exception localException1)
                              {
                                for (;;)
                                {
                                  continue;
                                  MyPhoneGapActivity.this.mapWebView.loadUrl("http://maps.google.com/maps?q=" + MyPhoneGapActivity.this.mapLatitide + "," + MyPhoneGapActivity.this.mapLongitude + "&t=m&z=16");
                                }
                              }
                              if (Build.VERSION.SDK_INT == 10) {
                                break label104;
                              }
                              MyPhoneGapActivity.this.changeMarginBottomMap();
                              ((ViewGroup.MarginLayoutParams)MyPhoneGapActivity.this.startNavigation.getLayoutParams()).bottomMargin = 0;
                              break label104;
                            }
                            if (!str1.contains("beaconcheck:")) {
                              break label104;
                            }
                            MyPhoneGapActivity.this.beaconURLs = str1.substring(12).split(",");
                            int i = 0;
                            while (i < MyPhoneGapActivity.this.beaconURLs.length)
                            {
                              MyPhoneGapActivity.this.beaconUUID.add(MyPhoneGapActivity.this.beaconURLs[i]);
                              i += 1;
                            }
                            MyPhoneGapActivity.this.callbeaconService();
                            break label104;
                            if (str1.equalsIgnoreCase("removewebsitewithoutads:"))
                            {
                              MyPhoneGapActivity.cwv1.stopLoading();
                              MyPhoneGapActivity.cwv1.loadUrl("");
                              MyPhoneGapActivity.cwv1.setVisibility(8);
                              MyPhoneGapActivity.this.reloadtwitterheader.setVisibility(4);
                              MyPhoneGapActivity.this.mapWebView.stopLoading();
                              MyPhoneGapActivity.this.mapWebView.loadUrl("");
                              MyPhoneGapActivity.this.mapWebView.setVisibility(8);
                              MyPhoneGapActivity.this.topDirection.setVisibility(4);
                              MyPhoneGapActivity.this.bottomDirection.setVisibility(4);
                              MyPhoneGapActivity.this.startNavigation.setVisibility(4);
                              MyPhoneGapActivity.this.topNavigation.setVisibility(4);
                              break label251;
                            }
                            if (str1.equalsIgnoreCase("soundRecorder:"))
                            {
                              paramAnonymousString = new Intent(MyPhoneGapActivity.this, AudioRecordingActivity.class);
                              MyPhoneGapActivity.this.startActivity(paramAnonymousString);
                              break label251;
                            }
                            if (str1.equalsIgnoreCase("openSocialShare:"))
                            {
                              paramAnonymousString = new Intent(MyPhoneGapActivity.this, SocialShare.class);
                              MyPhoneGapActivity.this.startActivity(paramAnonymousString);
                              break label251;
                            }
                            String str3;
                            label2096:
                            label2300:
                            label2414:
                            label2448:
                            int j;
                            if (str1.contains("fullscreenwesite"))
                            {
                              if (MyPhoneGapActivity.this.cdConnectionDetector.isConnectingToInternet())
                              {
                                MyPhoneGapActivity.this.mapWebView.setVisibility(8);
                                MyPhoneGapActivity.this.topDirection.setVisibility(8);
                                MyPhoneGapActivity.this.bottomDirection.setVisibility(8);
                                MyPhoneGapActivity.this.startNavigation.setVisibility(8);
                                MyPhoneGapActivity.this.topNavigation.setVisibility(8);
                                str3 = str1.substring(16);
                                paramAnonymousString = str3;
                                if (str3.contains("singlepageid"))
                                {
                                  str3 = str3.substring(12);
                                  paramAnonymousString = str3;
                                  if (str3.contains("forheaderbottom"))
                                  {
                                    paramAnonymousString = str3.substring(15);
                                    if (Build.VERSION.SDK_INT != 10) {
                                      break label2414;
                                    }
                                    System.out.println("Abhishek website complete URL : " + paramAnonymousString);
                                  }
                                }
                                MyPhoneGapActivity.access$602(MyPhoneGapActivity.this, paramAnonymousString);
                                MyPhoneGapActivity.access$702(MyPhoneGapActivity.this, "bottom" + paramAnonymousString);
                                MyPhoneGapActivity.this.firstItem = "";
                                MyPhoneGapActivity.cwv1.clearView();
                                if (!MyPhoneGapActivity.this.pd.isShown()) {
                                  MyPhoneGapActivity.this.pd.setVisibility(0);
                                }
                                if ((paramAnonymousString.contains("google.com/calendar")) || (paramAnonymousString.contains("www.mcce-mil.com")))
                                {
                                  i = (int)(100.0F * MyPhoneGapActivity.cwv1.getScale());
                                  System.out.println("loading calender site>>>>>>>>>>>>>>>>>>>>>>" + i);
                                  i = (int)TypedValue.applyDimension(1, 100.0F, MyPhoneGapActivity.this.getResources().getDisplayMetrics());
                                  MyPhoneGapActivity.cwv1.setInitialScale(i);
                                  MyPhoneGapActivity.cwv1.requestFocus();
                                  MyPhoneGapActivity.cwv1.requestFocusFromTouch();
                                  if (!paramAnonymousString.contains("codepage:")) {
                                    break label2448;
                                  }
                                  MyPhoneGapActivity.this.reloadtwitterheader.setVisibility(4);
                                  MyPhoneGapActivity.access$102(MyPhoneGapActivity.this, "false");
                                  paramAnonymousString = "<html><head><meta name='msapplication-window' content='width=device-width;height=device-height'/><meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'/></head><body>" + paramAnonymousString.substring(9) + "</body></html>";
                                  MyPhoneGapActivity.cwv1.loadData(paramAnonymousString, "text/html", "UTF-8");
                                }
                                for (;;)
                                {
                                  MyPhoneGapActivity.cwv1.setVisibility(0);
                                  if ((Build.VERSION.SDK_INT == 10) || (str1.contains("forheaderbottom"))) {
                                    break;
                                  }
                                  MyPhoneGapActivity.this.changeMarginBottomTopForFullWebSite(str1);
                                  break;
                                  MyPhoneGapActivity.this.changeMarginTop();
                                  break label2096;
                                  MyPhoneGapActivity.cwv1.setInitialScale(1);
                                  MyPhoneGapActivity.cwv1.requestFocus();
                                  MyPhoneGapActivity.cwv1.requestFocusFromTouch();
                                  break label2300;
                                  System.out.println("executing object tag url>>>>>>>>>>>" + paramAnonymousString);
                                  try
                                  {
                                    if (!paramAnonymousString.contains("http://vegastechshows.com")) {
                                      break label2613;
                                    }
                                    str3 = MyPhoneGapActivity.this.getResponseFromUrl(paramAnonymousString);
                                    if (!str3.contains("<object")) {
                                      continue;
                                    }
                                    System.out.println("executing object tag html");
                                    j = MyPhoneGapActivity.countSubstring("<object", str3);
                                    i = 0;
                                    while (i < j)
                                    {
                                      str3 = MyPhoneGapActivity.this.changeTag(str3);
                                      i += 1;
                                    }
                                    System.out.println("loading object tag url>>>>>>>>>>>" + str3);
                                    MyPhoneGapActivity.cwv1.loadDataWithBaseURL(paramAnonymousString, str3, null, null, null);
                                  }
                                  catch (Exception paramAnonymousString)
                                  {
                                    System.out.println("MyPhoneGapActivity.onCreate(...).new CordovaWebViewClient() {...}.shouldOverrideUrlLoading()");
                                    paramAnonymousString.printStackTrace();
                                  }
                                  continue;
                                  label2613:
                                  MyPhoneGapActivity.cwv1.loadUrl(paramAnonymousString);
                                }
                              }
                              MyPhoneGapActivity.this.alert.showAlertDialog(MyPhoneGapActivity.this, MyApplicationName.APP_NAME, "Network not available.", 24);
                              return true;
                            }
                            if ((str1.contains("bottomhttp")) || (str1.contains("bottommailto")) || (str1.contains("bottomtel")))
                            {
                              if (MyPhoneGapActivity.this.cdConnectionDetector.isConnectingToInternet())
                              {
                                MyPhoneGapActivity.this.mapWebView.setVisibility(8);
                                MyPhoneGapActivity.this.topDirection.setVisibility(4);
                                MyPhoneGapActivity.this.bottomDirection.setVisibility(4);
                                MyPhoneGapActivity.this.startNavigation.setVisibility(4);
                                MyPhoneGapActivity.this.topNavigation.setVisibility(4);
                                if (str1.substring(6, 10).equalsIgnoreCase("tel:"))
                                {
                                  paramAnonymousString = new Intent("android.intent.action.DIAL");
                                  paramAnonymousString.setData(Uri.parse(str1.substring(6, str1.length())));
                                  MyPhoneGapActivity.this.startActivity(paramAnonymousString);
                                  break label251;
                                }
                                if (str1.substring(6, 13).equalsIgnoreCase("mailto:"))
                                {
                                  paramAnonymousString = new Intent("android.intent.action.SEND");
                                  paramAnonymousString.setType("plain/text");
                                  paramAnonymousString.putExtra("android.intent.extra.EMAIL", new String[] { str1.substring(6, str1.length()) });
                                  MyPhoneGapActivity.this.startActivity(Intent.createChooser(paramAnonymousString, "Send mail..."));
                                  break label251;
                                }
                                if ((str1.substring(str1.lastIndexOf(".")).contains(".mp4")) || (str1.substring(str1.lastIndexOf(".")).contains(".m3u8")) || (str1.substring(str1.lastIndexOf(".")).contains(".ogg")) || (str1.substring(str1.lastIndexOf(".")).contains(".mov")) || (str1.substring(str1.lastIndexOf(".")).contains(".3gp")))
                                {
                                  paramAnonymousWebView = str1.substring(6, str1.length());
                                  MyPhoneGapActivity.this.playVideoInsideNativePlayer(paramAnonymousWebView);
                                  return false;
                                }
                                str3 = str1.substring(6);
                                MyPhoneGapActivity.access$602(MyPhoneGapActivity.this, str3);
                                MyPhoneGapActivity.access$702(MyPhoneGapActivity.this, "bottom" + str3);
                                MyPhoneGapActivity.this.firstItem = "";
                                MyPhoneGapActivity.cwv1.clearView();
                                if (str3.equalsIgnoreCase("https://www.dial7.com"))
                                {
                                  paramAnonymousWebView = new Intent("android.intent.action.VIEW");
                                  paramAnonymousWebView.setData(Uri.parse(str3));
                                  MyPhoneGapActivity.this.startActivity(paramAnonymousWebView);
                                  MyPhoneGapActivity.cwv.goBack();
                                  return true;
                                }
                                if (!MyPhoneGapActivity.this.pd.isShown()) {
                                  MyPhoneGapActivity.this.pd.setVisibility(0);
                                }
                                if ((str3.contains("google.com/calendar")) || (str3.contains("www.mcce-mil.com")))
                                {
                                  i = (int)(100.0F * MyPhoneGapActivity.cwv1.getScale());
                                  System.out.println("loading calender site>>>>>>>>>>>>>>>>>>>>>>" + i);
                                  i = (int)TypedValue.applyDimension(1, 100.0F, MyPhoneGapActivity.this.getResources().getDisplayMetrics());
                                  MyPhoneGapActivity.cwv1.setInitialScale(i);
                                  MyPhoneGapActivity.cwv1.requestFocus();
                                  MyPhoneGapActivity.cwv1.requestFocusFromTouch();
                                }
                                for (;;)
                                {
                                  if (!str3.contains("codepage:")) {
                                    break label3389;
                                  }
                                  MyPhoneGapActivity.this.reloadtwitterheader.setVisibility(4);
                                  MyPhoneGapActivity.access$102(MyPhoneGapActivity.this, "false");
                                  paramAnonymousString = "<html><head><meta name='msapplication-window' content='width=device-width;height=device-height'/><meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'/></head><body>" + str3.substring(9) + "</body></html>";
                                  MyPhoneGapActivity.cwv1.loadData(paramAnonymousString, "text/html", "UTF-8");
                                  MyPhoneGapActivity.cwv1.setVisibility(0);
                                  if (Build.VERSION.SDK_INT == 10) {
                                    break;
                                  }
                                  MyPhoneGapActivity.this.changeMarginBottomTop();
                                  break;
                                  MyPhoneGapActivity.cwv1.setInitialScale(1);
                                  MyPhoneGapActivity.cwv1.requestFocus();
                                  MyPhoneGapActivity.cwv1.requestFocusFromTouch();
                                }
                                label3389:
                                System.out.println("executing object tag url>>>>>>>>>>>" + str3);
                                for (;;)
                                {
                                  try
                                  {
                                    if (!str3.contains("http://vegastechshows.com")) {
                                      break label3559;
                                    }
                                    paramAnonymousString = MyPhoneGapActivity.this.getResponseFromUrl(str3);
                                    if (paramAnonymousString.contains("<object"))
                                    {
                                      System.out.println("executing object tag html");
                                      j = MyPhoneGapActivity.countSubstring("<object", paramAnonymousString);
                                      i = 0;
                                      if (i < j)
                                      {
                                        paramAnonymousString = MyPhoneGapActivity.this.changeTag(paramAnonymousString);
                                        i += 1;
                                        continue;
                                      }
                                      System.out.println("loading object tag url>>>>>>>>>>>" + paramAnonymousString);
                                      MyPhoneGapActivity.cwv1.loadDataWithBaseURL(str3, paramAnonymousString, null, null, null);
                                    }
                                    MyPhoneGapActivity.this.flag = true;
                                  }
                                  catch (Exception paramAnonymousString)
                                  {
                                    System.out.println("MyPhoneGapActivity.onCreate(...).new CordovaWebViewClient() {...}.shouldOverrideUrlLoading()");
                                    paramAnonymousString.printStackTrace();
                                  }
                                  break;
                                  label3559:
                                  MyPhoneGapActivity.cwv1.loadUrl(str3);
                                }
                              }
                              MyPhoneGapActivity.this.alert.showAlertDialog(MyPhoneGapActivity.this, MyApplicationName.APP_NAME, "Network not available.", 24);
                              return true;
                            }
                            if ((str1.contains("showhttp")) || (str1.contains("showmailto")) || (str1.contains("showtel")))
                            {
                              if (MyPhoneGapActivity.this.cdConnectionDetector.isConnectingToInternet())
                              {
                                MyPhoneGapActivity.this.mapWebView.setVisibility(8);
                                MyPhoneGapActivity.this.topDirection.setVisibility(4);
                                MyPhoneGapActivity.this.bottomDirection.setVisibility(4);
                                MyPhoneGapActivity.this.startNavigation.setVisibility(4);
                                MyPhoneGapActivity.this.topNavigation.setVisibility(4);
                                MyPhoneGapActivity.cwv1.setX(0.0F);
                                if (str1.substring(4, 8).equalsIgnoreCase("tel:"))
                                {
                                  paramAnonymousString = new Intent("android.intent.action.DIAL");
                                  paramAnonymousString.setData(Uri.parse(str1.substring(4, str1.length())));
                                  MyPhoneGapActivity.this.startActivity(paramAnonymousString);
                                  break label251;
                                }
                                if (str1.substring(4, 11).equalsIgnoreCase("mailto:"))
                                {
                                  paramAnonymousString = new Intent("android.intent.action.SEND");
                                  paramAnonymousString.setType("plain/text");
                                  paramAnonymousString.putExtra("android.intent.extra.EMAIL", new String[] { str1.substring(11, str1.length()) });
                                  MyPhoneGapActivity.this.startActivity(Intent.createChooser(paramAnonymousString, "Send mail..."));
                                  break label251;
                                }
                                if ((str1.substring(str1.lastIndexOf(".")).contains(".mp4")) || (str1.substring(str1.lastIndexOf(".")).contains(".m3u8")) || (str1.substring(str1.lastIndexOf(".")).contains(".ogg")) || (str1.substring(str1.lastIndexOf(".")).contains(".mov")) || (str1.substring(str1.lastIndexOf(".")).contains(".3gp")))
                                {
                                  paramAnonymousWebView = str1.substring(4, str1.length());
                                  MyPhoneGapActivity.this.playVideoInsideNativePlayer(paramAnonymousWebView);
                                  return false;
                                }
                                str3 = str1.substring(4);
                                MyPhoneGapActivity.access$602(MyPhoneGapActivity.this, str3);
                                MyPhoneGapActivity.access$702(MyPhoneGapActivity.this, "showme" + str3);
                                MyPhoneGapActivity.this.firstItem = "";
                                MyPhoneGapActivity.cwv1.clearView();
                                if (str3.equalsIgnoreCase("https://www.dial7.com"))
                                {
                                  paramAnonymousWebView = new Intent("android.intent.action.VIEW");
                                  paramAnonymousWebView.setData(Uri.parse(str3));
                                  MyPhoneGapActivity.this.startActivity(paramAnonymousWebView);
                                  MyPhoneGapActivity.cwv.goBack();
                                  return true;
                                }
                                if (!MyPhoneGapActivity.this.pd.isShown()) {
                                  MyPhoneGapActivity.this.pd.setVisibility(0);
                                }
                                if ((str3.contains("google.com/calendar")) || (str3.contains("www.mcce-mil.com")))
                                {
                                  i = (int)(100.0F * MyPhoneGapActivity.cwv1.getScale());
                                  System.out.println("loading calender site>>>>>>>>>>>>>>>>>>>>>>" + i);
                                  i = (int)TypedValue.applyDimension(1, 100.0F, MyPhoneGapActivity.this.getResources().getDisplayMetrics());
                                  MyPhoneGapActivity.cwv1.setInitialScale(i);
                                  MyPhoneGapActivity.cwv1.requestFocus();
                                  MyPhoneGapActivity.cwv1.requestFocusFromTouch();
                                }
                                for (;;)
                                {
                                  if (!str3.contains("codepage:")) {
                                    break label4331;
                                  }
                                  MyPhoneGapActivity.this.reloadtwitterheader.setVisibility(4);
                                  MyPhoneGapActivity.access$102(MyPhoneGapActivity.this, "false");
                                  paramAnonymousString = "<html><head><meta name='msapplication-window' content='width=device-width;height=device-height'/><meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'/></head><body>" + str3.substring(9) + "</body></html>";
                                  MyPhoneGapActivity.cwv1.loadData(paramAnonymousString, "text/html", "UTF-8");
                                  MyPhoneGapActivity.cwv1.setVisibility(0);
                                  if (Build.VERSION.SDK_INT == 10) {
                                    break;
                                  }
                                  MyPhoneGapActivity.this.changeMarginTop();
                                  break;
                                  MyPhoneGapActivity.cwv1.requestFocus();
                                  MyPhoneGapActivity.cwv1.requestFocusFromTouch();
                                }
                                for (;;)
                                {
                                  try
                                  {
                                    label4331:
                                    if (!str3.contains("http://vegastechshows.com")) {
                                      break label4465;
                                    }
                                    paramAnonymousString = MyPhoneGapActivity.this.getResponseFromUrl(str3);
                                    if (paramAnonymousString.contains("<object"))
                                    {
                                      System.out.println("executing object tag html");
                                      j = MyPhoneGapActivity.countSubstring("<object", paramAnonymousString);
                                      i = 0;
                                      if (i < j)
                                      {
                                        paramAnonymousString = MyPhoneGapActivity.this.changeTag(paramAnonymousString);
                                        i += 1;
                                        continue;
                                      }
                                      System.out.println("loading object tag url>>>>>>>>>>>" + paramAnonymousString);
                                      MyPhoneGapActivity.cwv1.loadDataWithBaseURL(str3, paramAnonymousString, null, null, null);
                                    }
                                    MyPhoneGapActivity.this.flag = true;
                                  }
                                  catch (Exception paramAnonymousString)
                                  {
                                    paramAnonymousString.printStackTrace();
                                  }
                                  break;
                                  label4465:
                                  MyPhoneGapActivity.cwv1.loadUrl(str3);
                                }
                              }
                              MyPhoneGapActivity.this.alert.showAlertDialog(MyPhoneGapActivity.this, MyApplicationName.APP_NAME, "Network not available.", 24);
                              return true;
                            }
                            if (!str1.contains("first")) {
                              break label251;
                            }
                            if (str1.substring(5, 9).equalsIgnoreCase("tel:"))
                            {
                              paramAnonymousString = new Intent("android.intent.action.DIAL");
                              paramAnonymousString.setData(Uri.parse(str1.substring(5, str1.length())));
                              MyPhoneGapActivity.this.startActivity(paramAnonymousString);
                              break label251;
                            }
                            if (str1.substring(5, 12).equalsIgnoreCase("mailto:"))
                            {
                              paramAnonymousString = new Intent("android.intent.action.SEND");
                              paramAnonymousString.setType("plain/text");
                              paramAnonymousString.putExtra("android.intent.extra.EMAIL", new String[] { str1.substring(5, str1.length()) });
                              MyPhoneGapActivity.this.startActivity(Intent.createChooser(paramAnonymousString, "Send mail..."));
                              break label251;
                            }
                            paramAnonymousString = str1.substring(5);
                            MyPhoneGapActivity.access$602(MyPhoneGapActivity.this, paramAnonymousString);
                            MyPhoneGapActivity.this.firstItem = paramAnonymousString;
                            MyPhoneGapActivity.access$702(MyPhoneGapActivity.this, "bottom" + paramAnonymousString);
                            MyPhoneGapActivity.cwv1.clearView();
                            if ((paramAnonymousString.contains("google.com/calendar")) || (paramAnonymousString.contains("www.mcce-mil.com")))
                            {
                              i = (int)(100.0F * MyPhoneGapActivity.cwv1.getScale());
                              System.out.println("loading calender site>>>>>>>>>>>>>>>>>>>>>>" + i);
                              i = (int)TypedValue.applyDimension(1, 100.0F, MyPhoneGapActivity.this.getResources().getDisplayMetrics());
                              MyPhoneGapActivity.cwv1.setInitialScale(i);
                              MyPhoneGapActivity.cwv1.requestFocus();
                              MyPhoneGapActivity.cwv1.requestFocusFromTouch();
                            }
                            for (;;)
                            {
                              MyPhoneGapActivity.this.flag = true;
                              MyPhoneGapActivity.cwv1.loadUrl(paramAnonymousString);
                              MyPhoneGapActivity.cwv1.setVisibility(0);
                              if (Build.VERSION.SDK_INT == 10) {
                                break;
                              }
                              MyPhoneGapActivity.this.changeMarginBottomTop();
                              break;
                              MyPhoneGapActivity.cwv1.setInitialScale(1);
                              MyPhoneGapActivity.cwv1.requestFocus();
                              MyPhoneGapActivity.cwv1.requestFocusFromTouch();
                            }
                            ((ZoomButtonsController)MyPhoneGapActivity.cwv1.getClass().getMethod("getZoomButtonsController", new Class[0]).invoke(MyPhoneGapActivity.cwv1, null)).getContainer().setVisibility(8);
                          }
                        }
                        catch (Exception localException2)
                        {
                          for (;;) {}
                        }
                      }
                    }
                  });
                  cwv1.setDownloadListener(new DownloadListener()
                  {
                    public void onDownloadStart(String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3, String paramAnonymousString4, long paramAnonymousLong)
                    {
                      System.out.println("download files url>>>>>>>>>>>>>>>>>>" + paramAnonymousString1);
                      if (paramAnonymousString1.contains(".pdf"))
                      {
                        paramAnonymousString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramAnonymousString1));
                        MyPhoneGapActivity.this.startActivity(paramAnonymousString1);
                        return;
                      }
                      paramAnonymousString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramAnonymousString1));
                      MyPhoneGapActivity.this.startActivity(paramAnonymousString1);
                    }
                  });
                  cwv1.setWebViewClient(new WebViewClient()
                  {
                    public void onGeolocationPermissionsShowPrompt(String paramAnonymousString, GeolocationPermissions.Callback paramAnonymousCallback)
                    {
                      paramAnonymousCallback.invoke(paramAnonymousString, true, false);
                      MyPhoneGapActivity.this.showEnableDialog(paramAnonymousString);
                    }
                    
                    public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
                    {
                      System.out.println("on finish");
                      if ((!MyPhoneGapActivity.cwv1.canGoForward()) && (MyPhoneGapActivity.this.forward.getVisibility() == 0))
                      {
                        MyPhoneGapActivity.this.forward.setImageResource(2130837587);
                        if ((MyPhoneGapActivity.cwv1.canGoBack()) || (MyPhoneGapActivity.this.backward.getVisibility() != 0)) {
                          break label194;
                        }
                        MyPhoneGapActivity.this.backward.setImageResource(2130837510);
                      }
                      for (;;)
                      {
                        MyPhoneGapActivity.this.webviewURLList.add(0, paramAnonymousString);
                        if (paramAnonymousString.equals("http://snappy.appypie.com/code_page_html/codepage_1443770638253_28.html")) {
                          MyPhoneGapActivity.this.flag = false;
                        }
                        if (MyPhoneGapActivity.this.pd.isShown()) {
                          MyPhoneGapActivity.this.pd.setVisibility(8);
                        }
                        if ((paramAnonymousString.contains("http://www.chia-anime.com")) || (paramAnonymousString.contains("http://animeget.net/"))) {
                          paramAnonymousWebView.loadUrl("javascript: var temp=0;   var a=document.getElementsByTagName('video')[0]; a.addEventListener('play', function() {a.pause();window.location.href='videoPlayUrlInsideNativePlayer##'+a.src;}, false);");
                        }
                        return;
                        if ((!MyPhoneGapActivity.cwv1.canGoForward()) || (MyPhoneGapActivity.this.forward.getVisibility() != 0)) {
                          break;
                        }
                        MyPhoneGapActivity.this.forward.setImageResource(2130837586);
                        break;
                        label194:
                        if ((MyPhoneGapActivity.cwv1.canGoBack()) && (MyPhoneGapActivity.this.backward.getVisibility() == 0)) {
                          MyPhoneGapActivity.this.backward.setImageResource(2130837509);
                        }
                      }
                    }
                    
                    public void onReceivedError(WebView paramAnonymousWebView, int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2)
                    {
                      paramAnonymousWebView.loadUrl("file:///android_asset/help.html");
                    }
                    
                    public void onReceivedHttpAuthRequest(WebView paramAnonymousWebView, final HttpAuthHandler paramAnonymousHttpAuthHandler, String paramAnonymousString1, final String paramAnonymousString2)
                    {
                      paramAnonymousWebView = new AlertDialog.Builder(MyPhoneGapActivity.this);
                      paramAnonymousString1 = new LinearLayout(MyPhoneGapActivity.this);
                      paramAnonymousString1.setOrientation(1);
                      paramAnonymousString2 = new EditText(MyPhoneGapActivity.this);
                      final EditText localEditText = new EditText(MyPhoneGapActivity.this);
                      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -1, 0.5F);
                      paramAnonymousString2.setLayoutParams(localLayoutParams);
                      paramAnonymousString2.setHint("Login");
                      localEditText.setLayoutParams(localLayoutParams);
                      localEditText.setHint("Password");
                      paramAnonymousString1.addView(paramAnonymousString2);
                      paramAnonymousString1.addView(localEditText);
                      paramAnonymousWebView.setTitle("Authentication Required");
                      paramAnonymousWebView.setView(paramAnonymousString1);
                      paramAnonymousWebView.setPositiveButton("Ok", new DialogInterface.OnClickListener()
                      {
                        public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                        {
                          paramAnonymousHttpAuthHandler.proceed(paramAnonymousString2.getText().toString(), localEditText.getText().toString());
                        }
                      });
                      paramAnonymousWebView.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                      {
                        public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                        {
                          paramAnonymous2DialogInterface.cancel();
                          MyPhoneGapActivity.this.pd.setVisibility(4);
                        }
                      });
                      paramAnonymousWebView.show();
                    }
                    
                    public void onReceivedSslError(WebView paramAnonymousWebView, SslErrorHandler paramAnonymousSslErrorHandler, SslError paramAnonymousSslError)
                    {
                      Log.d(CordovaActivity.TAG, "==> " + paramAnonymousSslError.toString());
                      paramAnonymousSslErrorHandler.proceed();
                      super.onReceivedSslError(paramAnonymousWebView, paramAnonymousSslErrorHandler, paramAnonymousSslError);
                    }
                    
                    public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
                    {
                      System.out.println("website webview>>>>>>" + paramAnonymousString);
                      boolean bool2 = false;
                      if (paramAnonymousString.contains("whatsapp://send"))
                      {
                        System.out.println("whatsapp loading");
                        paramAnonymousWebView = new Intent("android.intent.action.VIEW");
                        paramAnonymousWebView.setData(Uri.parse("" + paramAnonymousString));
                        MyPhoneGapActivity.this.startActivityForResult(paramAnonymousWebView, 1);
                        return true;
                      }
                      if (paramAnonymousString.contains("appypielink:"))
                      {
                        MyPhoneGapActivity.this.openPage(paramAnonymousString.split(":")[1]);
                        return false;
                      }
                      if (((paramAnonymousString.contains("http://www.chia-anime.com")) || (paramAnonymousString.contains("http://animeget.net/")) || (paramAnonymousString.contains("embedcf.chia-anime.com"))) && (paramAnonymousString.contains("?id=")))
                      {
                        paramAnonymousWebView = new Intent(MyPhoneGapActivity.this, Vimeo.class);
                        paramAnonymousWebView.putExtra("vimeoid", paramAnonymousString);
                        MyPhoneGapActivity.this.startActivity(paramAnonymousWebView);
                        return true;
                      }
                      if (paramAnonymousString.contains("videoPlayUrlInsideNativePlayer"))
                      {
                        System.out.println("loading native player" + paramAnonymousString);
                        paramAnonymousWebView = paramAnonymousString.split("##");
                        MyPhoneGapActivity.this.playVideoInsideNativePlayer(paramAnonymousWebView[1]);
                        return true;
                      }
                      boolean bool1 = bool2;
                      try
                      {
                        System.out.println("url overloading>>>>" + paramAnonymousString);
                        bool1 = bool2;
                        if (paramAnonymousString.startsWith("tel:"))
                        {
                          bool2 = true;
                          bool1 = bool2;
                          paramAnonymousWebView = new Intent("android.intent.action.DIAL", Uri.parse(paramAnonymousString));
                          bool1 = bool2;
                          MyPhoneGapActivity.this.startActivity(paramAnonymousWebView);
                          return true;
                        }
                      }
                      catch (Exception paramAnonymousWebView)
                      {
                        System.out.println("MyPhoneGapActivity.onCreate(...).new CordovaWebViewClient() {...}.shouldOverrideUrlLoading()");
                        paramAnonymousWebView.printStackTrace();
                        return bool1;
                      }
                      bool1 = bool2;
                      if (paramAnonymousString.startsWith("mailto:"))
                      {
                        bool1 = bool2;
                        paramAnonymousWebView = paramAnonymousString.substring(7, paramAnonymousString.length());
                        bool1 = bool2;
                        paramAnonymousString = new Intent("android.intent.action.SEND");
                        bool1 = bool2;
                        paramAnonymousString.setType("plain/text");
                        bool1 = bool2;
                        paramAnonymousString.putExtra("android.intent.extra.EMAIL", new String[] { paramAnonymousWebView });
                        bool1 = bool2;
                        MyPhoneGapActivity.this.startActivity(Intent.createChooser(paramAnonymousString, "Send mail..."));
                        return true;
                      }
                      bool1 = bool2;
                      if (!paramAnonymousString.substring(paramAnonymousString.lastIndexOf(".")).contains(".mp4"))
                      {
                        bool1 = bool2;
                        if (!paramAnonymousString.substring(paramAnonymousString.lastIndexOf(".")).contains(".m3u8"))
                        {
                          bool1 = bool2;
                          if (!paramAnonymousString.substring(paramAnonymousString.lastIndexOf(".")).contains(".ogg"))
                          {
                            bool1 = bool2;
                            if (!paramAnonymousString.substring(paramAnonymousString.lastIndexOf(".")).contains(".mov"))
                            {
                              bool1 = bool2;
                              if (!paramAnonymousString.substring(paramAnonymousString.lastIndexOf(".")).contains(".3gp")) {
                                break label589;
                              }
                            }
                          }
                        }
                      }
                      bool1 = bool2;
                      System.out.println("loading native player" + paramAnonymousString);
                      bool1 = true;
                      MyPhoneGapActivity.this.playVideoInsideNativePlayer(paramAnonymousString);
                      return true;
                      label589:
                      bool1 = bool2;
                      if (!paramAnonymousString.replace("//m.", "//www.").equals(MyPhoneGapActivity.this.webViewFirstURL.substring(6)))
                      {
                        bool1 = bool2;
                        if (MyPhoneGapActivity.this.webViewFirstURL.substring(0, 6).matches("bottom"))
                        {
                          bool1 = bool2;
                          if (MyPhoneGapActivity.this.inAppNavigation.equals("false"))
                          {
                            bool1 = bool2;
                            MyPhoneGapActivity.this.reloadtwitterheader.setVisibility(4);
                          }
                        }
                      }
                      for (;;)
                      {
                        bool1 = bool2;
                        if (!MyPhoneGapActivity.this.pd.isShown())
                        {
                          bool1 = bool2;
                          MyPhoneGapActivity.this.pd.setVisibility(0);
                        }
                        bool1 = bool2;
                        if (!MyPhoneGapActivity.this.redirectedUrl.equals("http://idx.diversesolutions.com/search/68423/28")) {
                          break;
                        }
                        return true;
                        bool1 = bool2;
                        if (!MyPhoneGapActivity.AppId.equals("2b77f6096336"))
                        {
                          bool1 = bool2;
                          if (!MyPhoneGapActivity.AppId.equals("05ecad395d07"))
                          {
                            bool1 = bool2;
                            MyPhoneGapActivity.this.reloadtwitterheader.setVisibility(0);
                            bool1 = bool2;
                            MyPhoneGapActivity.this.changeMarginMatrix();
                            continue;
                            bool1 = bool2;
                            if (MyPhoneGapActivity.this.webViewFirstURL.substring(0, 16).matches("fullscreenwesite"))
                            {
                              bool1 = bool2;
                              if (MyPhoneGapActivity.this.inAppNavigation.equals("false"))
                              {
                                bool1 = bool2;
                                MyPhoneGapActivity.this.changeMarginTopFull();
                                bool1 = bool2;
                                MyPhoneGapActivity.this.webViewFirstURL.substring(16);
                                bool1 = bool2;
                                MyPhoneGapActivity.this.reloadtwitterheader.setVisibility(4);
                              }
                              else
                              {
                                bool1 = bool2;
                                MyPhoneGapActivity.this.reloadtwitterheader.setVisibility(0);
                                bool1 = bool2;
                                MyPhoneGapActivity.this.changeMarginTopFull();
                                bool1 = bool2;
                                MyPhoneGapActivity.this.webViewFirstURL.substring(16);
                              }
                            }
                            else
                            {
                              bool1 = bool2;
                              if (MyPhoneGapActivity.this.inAppNavigation.equals("false"))
                              {
                                bool1 = bool2;
                                MyPhoneGapActivity.this.changeMarginTop();
                                bool1 = bool2;
                                MyPhoneGapActivity.this.webViewFirstURL.substring(6);
                                bool1 = bool2;
                                MyPhoneGapActivity.this.reloadtwitterheader.setVisibility(4);
                              }
                              else
                              {
                                bool1 = bool2;
                                MyPhoneGapActivity.this.reloadtwitterheader.setVisibility(0);
                                bool1 = bool2;
                                MyPhoneGapActivity.this.changeMarginBottom();
                                bool1 = bool2;
                                MyPhoneGapActivity.this.webViewFirstURL.substring(6);
                                continue;
                                bool1 = bool2;
                                if (MyPhoneGapActivity.this.webViewFirstURL.substring(0, 6).matches("bottom"))
                                {
                                  bool1 = bool2;
                                  if (Build.VERSION.SDK_INT != 10)
                                  {
                                    bool1 = bool2;
                                    MyPhoneGapActivity.this.changeMarginBottomTop();
                                  }
                                }
                                else
                                {
                                  bool1 = bool2;
                                  if (Build.VERSION.SDK_INT != 10)
                                  {
                                    bool1 = bool2;
                                    MyPhoneGapActivity.this.changeMarginTop();
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                      return false;
                    }
                  });
                  this.mapWebView.setWebViewClient(new WebViewClient()
                  {
                    public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString) {}
                    
                    public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
                    {
                      return false;
                    }
                  });
                  Log.e("", "before getAdId starts now....." + new Date().getSeconds() / 1000);
                  this.addIdBanner = getAdId();
                  Log.e("", "before phoneStateListener starts now....." + new Date().getSeconds() / 1000);
                  this.phoneStateListener = new PhoneStateListener()
                  {
                    /* Error */
                    public void onCallStateChanged(int paramAnonymousInt, String paramAnonymousString)
                    {
                      // Byte code:
                      //   0: iload_1
                      //   1: tableswitch	default:+27->28, 0:+34->35, 1:+400->401, 2:+189->190
                      //   28: aload_0
                      //   29: iload_1
                      //   30: aload_2
                      //   31: invokespecial 25	android/telephony/PhoneStateListener:onCallStateChanged	(ILjava/lang/String;)V
                      //   34: return
                      //   35: ldc 27
                      //   37: ldc 29
                      //   39: invokestatic 35	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
                      //   42: pop
                      //   43: getstatic 41	com/ons/radio/PlayerActivity:audioSignal	Lcom/ons/radio/AudioSignal;
                      //   46: ifnull +112 -> 158
                      //   49: getstatic 41	com/ons/radio/PlayerActivity:audioSignal	Lcom/ons/radio/AudioSignal;
                      //   52: invokevirtual 47	com/ons/radio/AudioSignal:isPlaying	()Z
                      //   55: ifne +103 -> 158
                      //   58: getstatic 51	com/ons/radio/PlayerActivity:status	I
                      //   61: iconst_1
                      //   62: if_icmpne +7 -> 69
                      //   65: iconst_0
                      //   66: putstatic 55	com/ons/radio/PlayerActivity:audioOncall	Z
                      //   69: getstatic 51	com/ons/radio/PlayerActivity:status	I
                      //   72: ifne +7 -> 79
                      //   75: iconst_1
                      //   76: putstatic 55	com/ons/radio/PlayerActivity:audioOncall	Z
                      //   79: getstatic 41	com/ons/radio/PlayerActivity:audioSignal	Lcom/ons/radio/AudioSignal;
                      //   82: ifnull -54 -> 28
                      //   85: getstatic 41	com/ons/radio/PlayerActivity:audioSignal	Lcom/ons/radio/AudioSignal;
                      //   88: invokevirtual 47	com/ons/radio/AudioSignal:isPlaying	()Z
                      //   91: ifne -63 -> 28
                      //   94: getstatic 58	com/example/example75f1799f07eb/MyPhoneGapActivity:checkplaying	I
                      //   97: ifne +32 -> 129
                      //   100: getstatic 64	java/lang/System:out	Ljava/io/PrintStream;
                      //   103: ldc 66
                      //   105: invokevirtual 72	java/io/PrintStream:println	(Ljava/lang/String;)V
                      //   108: iconst_0
                      //   109: putstatic 58	com/example/example75f1799f07eb/MyPhoneGapActivity:checkplaying	I
                      //   112: getstatic 41	com/ons/radio/PlayerActivity:audioSignal	Lcom/ons/radio/AudioSignal;
                      //   115: invokevirtual 75	com/ons/radio/AudioSignal:play	()V
                      //   118: aload_0
                      //   119: getfield 15	com/example/example75f1799f07eb/MyPhoneGapActivity$14:this$0	Lcom/example/example75f1799f07eb/MyPhoneGapActivity;
                      //   122: iconst_0
                      //   123: putfield 78	com/example/example75f1799f07eb/MyPhoneGapActivity:pauseTrue	Z
                      //   126: goto -98 -> 28
                      //   129: getstatic 64	java/lang/System:out	Ljava/io/PrintStream;
                      //   132: ldc 80
                      //   134: invokevirtual 72	java/io/PrintStream:println	(Ljava/lang/String;)V
                      //   137: iconst_1
                      //   138: putstatic 58	com/example/example75f1799f07eb/MyPhoneGapActivity:checkplaying	I
                      //   141: getstatic 41	com/ons/radio/PlayerActivity:audioSignal	Lcom/ons/radio/AudioSignal;
                      //   144: invokevirtual 83	com/ons/radio/AudioSignal:stop	()V
                      //   147: aload_0
                      //   148: getfield 15	com/example/example75f1799f07eb/MyPhoneGapActivity$14:this$0	Lcom/example/example75f1799f07eb/MyPhoneGapActivity;
                      //   151: iconst_0
                      //   152: putfield 78	com/example/example75f1799f07eb/MyPhoneGapActivity:pauseTrue	Z
                      //   155: goto -127 -> 28
                      //   158: getstatic 89	com/ons/musicplayer/MainActivity:mp	Landroid/media/MediaPlayer;
                      //   161: ifnull -133 -> 28
                      //   164: getstatic 89	com/ons/musicplayer/MainActivity:mp	Landroid/media/MediaPlayer;
                      //   167: invokevirtual 92	android/media/MediaPlayer:isPlaying	()Z
                      //   170: ifne -142 -> 28
                      //   173: getstatic 64	java/lang/System:out	Ljava/io/PrintStream;
                      //   176: ldc 94
                      //   178: invokevirtual 72	java/io/PrintStream:println	(Ljava/lang/String;)V
                      //   181: getstatic 89	com/ons/musicplayer/MainActivity:mp	Landroid/media/MediaPlayer;
                      //   184: invokevirtual 97	android/media/MediaPlayer:start	()V
                      //   187: goto -159 -> 28
                      //   190: ldc 27
                      //   192: ldc 99
                      //   194: invokestatic 35	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
                      //   197: pop
                      //   198: getstatic 41	com/ons/radio/PlayerActivity:audioSignal	Lcom/ons/radio/AudioSignal;
                      //   201: ifnull +41 -> 242
                      //   204: getstatic 41	com/ons/radio/PlayerActivity:audioSignal	Lcom/ons/radio/AudioSignal;
                      //   207: invokevirtual 47	com/ons/radio/AudioSignal:isPlaying	()Z
                      //   210: ifeq +32 -> 242
                      //   213: getstatic 64	java/lang/System:out	Ljava/io/PrintStream;
                      //   216: ldc 101
                      //   218: invokevirtual 72	java/io/PrintStream:println	(Ljava/lang/String;)V
                      //   221: iconst_0
                      //   222: putstatic 58	com/example/example75f1799f07eb/MyPhoneGapActivity:checkplaying	I
                      //   225: getstatic 41	com/ons/radio/PlayerActivity:audioSignal	Lcom/ons/radio/AudioSignal;
                      //   228: invokevirtual 83	com/ons/radio/AudioSignal:stop	()V
                      //   231: aload_0
                      //   232: getfield 15	com/example/example75f1799f07eb/MyPhoneGapActivity$14:this$0	Lcom/example/example75f1799f07eb/MyPhoneGapActivity;
                      //   235: iconst_0
                      //   236: putfield 78	com/example/example75f1799f07eb/MyPhoneGapActivity:pauseTrue	Z
                      //   239: goto -211 -> 28
                      //   242: getstatic 41	com/ons/radio/PlayerActivity:audioSignal	Lcom/ons/radio/AudioSignal;
                      //   245: ifnull +76 -> 321
                      //   248: getstatic 41	com/ons/radio/PlayerActivity:audioSignal	Lcom/ons/radio/AudioSignal;
                      //   251: invokevirtual 47	com/ons/radio/AudioSignal:isPlaying	()Z
                      //   254: ifne +67 -> 321
                      //   257: getstatic 58	com/example/example75f1799f07eb/MyPhoneGapActivity:checkplaying	I
                      //   260: ifne +32 -> 292
                      //   263: getstatic 64	java/lang/System:out	Ljava/io/PrintStream;
                      //   266: ldc 101
                      //   268: invokevirtual 72	java/io/PrintStream:println	(Ljava/lang/String;)V
                      //   271: iconst_0
                      //   272: putstatic 58	com/example/example75f1799f07eb/MyPhoneGapActivity:checkplaying	I
                      //   275: getstatic 41	com/ons/radio/PlayerActivity:audioSignal	Lcom/ons/radio/AudioSignal;
                      //   278: invokevirtual 83	com/ons/radio/AudioSignal:stop	()V
                      //   281: aload_0
                      //   282: getfield 15	com/example/example75f1799f07eb/MyPhoneGapActivity$14:this$0	Lcom/example/example75f1799f07eb/MyPhoneGapActivity;
                      //   285: iconst_0
                      //   286: putfield 78	com/example/example75f1799f07eb/MyPhoneGapActivity:pauseTrue	Z
                      //   289: goto -261 -> 28
                      //   292: getstatic 64	java/lang/System:out	Ljava/io/PrintStream;
                      //   295: ldc 101
                      //   297: invokevirtual 72	java/io/PrintStream:println	(Ljava/lang/String;)V
                      //   300: iconst_1
                      //   301: putstatic 58	com/example/example75f1799f07eb/MyPhoneGapActivity:checkplaying	I
                      //   304: getstatic 41	com/ons/radio/PlayerActivity:audioSignal	Lcom/ons/radio/AudioSignal;
                      //   307: invokevirtual 83	com/ons/radio/AudioSignal:stop	()V
                      //   310: aload_0
                      //   311: getfield 15	com/example/example75f1799f07eb/MyPhoneGapActivity$14:this$0	Lcom/example/example75f1799f07eb/MyPhoneGapActivity;
                      //   314: iconst_0
                      //   315: putfield 78	com/example/example75f1799f07eb/MyPhoneGapActivity:pauseTrue	Z
                      //   318: goto -290 -> 28
                      //   321: getstatic 89	com/ons/musicplayer/MainActivity:mp	Landroid/media/MediaPlayer;
                      //   324: ifnull +29 -> 353
                      //   327: getstatic 89	com/ons/musicplayer/MainActivity:mp	Landroid/media/MediaPlayer;
                      //   330: invokevirtual 92	android/media/MediaPlayer:isPlaying	()Z
                      //   333: ifeq +20 -> 353
                      //   336: getstatic 64	java/lang/System:out	Ljava/io/PrintStream;
                      //   339: ldc 103
                      //   341: invokevirtual 72	java/io/PrintStream:println	(Ljava/lang/String;)V
                      //   344: getstatic 89	com/ons/musicplayer/MainActivity:mp	Landroid/media/MediaPlayer;
                      //   347: invokevirtual 106	android/media/MediaPlayer:pause	()V
                      //   350: goto -322 -> 28
                      //   353: aload_0
                      //   354: getfield 15	com/example/example75f1799f07eb/MyPhoneGapActivity$14:this$0	Lcom/example/example75f1799f07eb/MyPhoneGapActivity;
                      //   357: getfield 78	com/example/example75f1799f07eb/MyPhoneGapActivity:pauseTrue	Z
                      //   360: ifeq -332 -> 28
                      //   363: aload_0
                      //   364: getfield 15	com/example/example75f1799f07eb/MyPhoneGapActivity$14:this$0	Lcom/example/example75f1799f07eb/MyPhoneGapActivity;
                      //   367: iconst_1
                      //   368: putfield 78	com/example/example75f1799f07eb/MyPhoneGapActivity:pauseTrue	Z
                      //   371: getstatic 64	java/lang/System:out	Ljava/io/PrintStream;
                      //   374: ldc 108
                      //   376: invokevirtual 72	java/io/PrintStream:println	(Ljava/lang/String;)V
                      //   379: iconst_1
                      //   380: putstatic 58	com/example/example75f1799f07eb/MyPhoneGapActivity:checkplaying	I
                      //   383: getstatic 41	com/ons/radio/PlayerActivity:audioSignal	Lcom/ons/radio/AudioSignal;
                      //   386: invokevirtual 47	com/ons/radio/AudioSignal:isPlaying	()Z
                      //   389: putstatic 55	com/ons/radio/PlayerActivity:audioOncall	Z
                      //   392: getstatic 41	com/ons/radio/PlayerActivity:audioSignal	Lcom/ons/radio/AudioSignal;
                      //   395: invokevirtual 83	com/ons/radio/AudioSignal:stop	()V
                      //   398: goto -370 -> 28
                      //   401: getstatic 41	com/ons/radio/PlayerActivity:audioSignal	Lcom/ons/radio/AudioSignal;
                      //   404: ifnull +50 -> 454
                      //   407: getstatic 41	com/ons/radio/PlayerActivity:audioSignal	Lcom/ons/radio/AudioSignal;
                      //   410: invokevirtual 47	com/ons/radio/AudioSignal:isPlaying	()Z
                      //   413: ifeq +41 -> 454
                      //   416: getstatic 64	java/lang/System:out	Ljava/io/PrintStream;
                      //   419: ldc 110
                      //   421: invokevirtual 72	java/io/PrintStream:println	(Ljava/lang/String;)V
                      //   424: iconst_0
                      //   425: putstatic 58	com/example/example75f1799f07eb/MyPhoneGapActivity:checkplaying	I
                      //   428: aload_0
                      //   429: getfield 15	com/example/example75f1799f07eb/MyPhoneGapActivity$14:this$0	Lcom/example/example75f1799f07eb/MyPhoneGapActivity;
                      //   432: iconst_0
                      //   433: putfield 78	com/example/example75f1799f07eb/MyPhoneGapActivity:pauseTrue	Z
                      //   436: getstatic 41	com/ons/radio/PlayerActivity:audioSignal	Lcom/ons/radio/AudioSignal;
                      //   439: invokevirtual 47	com/ons/radio/AudioSignal:isPlaying	()Z
                      //   442: putstatic 55	com/ons/radio/PlayerActivity:audioOncall	Z
                      //   445: getstatic 41	com/ons/radio/PlayerActivity:audioSignal	Lcom/ons/radio/AudioSignal;
                      //   448: invokevirtual 83	com/ons/radio/AudioSignal:stop	()V
                      //   451: goto -423 -> 28
                      //   454: getstatic 89	com/ons/musicplayer/MainActivity:mp	Landroid/media/MediaPlayer;
                      //   457: ifnull +29 -> 486
                      //   460: getstatic 89	com/ons/musicplayer/MainActivity:mp	Landroid/media/MediaPlayer;
                      //   463: invokevirtual 92	android/media/MediaPlayer:isPlaying	()Z
                      //   466: ifeq +20 -> 486
                      //   469: getstatic 64	java/lang/System:out	Ljava/io/PrintStream;
                      //   472: ldc 112
                      //   474: invokevirtual 72	java/io/PrintStream:println	(Ljava/lang/String;)V
                      //   477: getstatic 89	com/ons/musicplayer/MainActivity:mp	Landroid/media/MediaPlayer;
                      //   480: invokevirtual 106	android/media/MediaPlayer:pause	()V
                      //   483: goto -455 -> 28
                      //   486: getstatic 64	java/lang/System:out	Ljava/io/PrintStream;
                      //   489: ldc 114
                      //   491: invokevirtual 72	java/io/PrintStream:println	(Ljava/lang/String;)V
                      //   494: iconst_1
                      //   495: putstatic 58	com/example/example75f1799f07eb/MyPhoneGapActivity:checkplaying	I
                      //   498: getstatic 41	com/ons/radio/PlayerActivity:audioSignal	Lcom/ons/radio/AudioSignal;
                      //   501: invokevirtual 83	com/ons/radio/AudioSignal:stop	()V
                      //   504: goto -476 -> 28
                      //   507: astore_3
                      //   508: goto -480 -> 28
                      //   511: astore_3
                      //   512: goto -484 -> 28
                      //   515: astore_3
                      //   516: goto -488 -> 28
                      // Local variable table:
                      //   start	length	slot	name	signature
                      //   0	519	0	this	14
                      //   0	519	1	paramAnonymousInt	int
                      //   0	519	2	paramAnonymousString	String
                      //   507	1	3	localException1	Exception
                      //   511	1	3	localException2	Exception
                      //   515	1	3	localException3	Exception
                      // Exception table:
                      //   from	to	target	type
                      //   43	69	507	java/lang/Exception
                      //   69	79	507	java/lang/Exception
                      //   79	126	507	java/lang/Exception
                      //   129	155	507	java/lang/Exception
                      //   158	187	507	java/lang/Exception
                      //   198	239	511	java/lang/Exception
                      //   242	289	511	java/lang/Exception
                      //   292	318	511	java/lang/Exception
                      //   321	350	511	java/lang/Exception
                      //   353	398	511	java/lang/Exception
                      //   401	451	515	java/lang/Exception
                      //   454	483	515	java/lang/Exception
                      //   486	504	515	java/lang/Exception
                    }
                  };
                  this.mgr = ((TelephonyManager)getSystemService("phone"));
                  if (this.mgr == null) {
                    break;
                  }
                  this.mgr.listen(this.phoneStateListener, 32);
                  return;
                  paramBundle = paramBundle;
                  System.out.println(paramBundle.toString());
                  break label332;
                  paramBundle = paramBundle;
                  System.out.println("location exception=" + paramBundle);
                  break label742;
                  showAndHideAdView("hide");
                  break label800;
                  if (Build.VERSION.SDK_INT >= 11)
                  {
                    cwv.loadUrl("file:///android_asset/www/index4.html?appid=" + paramBundle + "@@" + str + "@@" + (String)localObject + "");
                  }
                  else
                  {
                    cwv.loadUrl("file:///android_asset/www/index4.html?appid=" + paramBundle + "@@" + str + "@@" + (String)localObject + "");
                    continue;
                    if (!this.navigationLayout.equals("slidemenu"))
                    {
                      cwv.loadUrl("file:///android_asset/www/index.html?appid=" + paramBundle + "@@" + str + "@@" + (String)localObject + "");
                    }
                    else if (Build.VERSION.SDK_INT >= 11)
                    {
                      cwv.loadUrl("file:///android_asset/www/index2.html?appid=" + paramBundle + "@@" + str + "@@" + (String)localObject + "");
                    }
                    else
                    {
                      cwv.loadUrl("file:///android_asset/www/index2.html?appid=" + paramBundle + "@@" + str + "@@" + (String)localObject + "");
                      continue;
                      if (this.appLanguage.equalsIgnoreCase("sa"))
                      {
                        if (!this.navigationLayout.equals("slidemenu")) {
                          cwv.loadUrl("file:///android_asset/www/index3.html");
                        } else if (Build.VERSION.SDK_INT >= 11) {
                          cwv.loadUrl("file:///android_asset/www/index4.html");
                        } else {
                          cwv.loadUrl("file:///android_asset/www/index4.html");
                        }
                      }
                      else if (!this.navigationLayout.equals("slidemenu")) {
                        cwv.loadUrl("file:///android_asset/www/index.html");
                      } else if (Build.VERSION.SDK_INT >= 11) {
                        cwv.loadUrl("file:///android_asset/www/index2.html");
                      } else {
                        cwv.loadUrl("file:///android_asset/www/index2.html");
                      }
                    }
                  }
                }
              }
              if (getIntent().getStringExtra("fromnotification") != null)
              {
                localObject = PreferenceManager.getDefaultSharedPreferences(this);
                paramBundle = ((SharedPreferences)localObject).getString("lastBeaconUUID", "");
                str = ((SharedPreferences)localObject).getString("lastBeaconMinor", "");
                localObject = ((SharedPreferences)localObject).getString("lastBeaconMajor", "");
                if (this.appLanguage.equalsIgnoreCase("sa")) {
                  if (!this.navigationLayout.equals("slidemenu")) {
                    cwv.loadUrl("file:///android_asset/www/index3.html?appid=" + paramBundle + "@@" + str + "@@" + (String)localObject + "");
                  }
                }
              }
              for (;;)
              {
                paramBundle = cwv.getSettings();
                paramBundle.setJavaScriptEnabled(true);
                paramBundle.setJavaScriptCanOpenWindowsAutomatically(true);
                cwv.getSettings().setLoadWithOverviewMode(true);
                cwv.getSettings().setDomStorageEnabled(true);
                paramBundle.setDatabaseEnabled(true);
                if (Build.VERSION.SDK_INT >= 16) {
                  paramBundle.setAllowUniversalAccessFromFileURLs(true);
                }
                paramBundle = getApplicationContext().getDir("databases", 0).getPath();
                cwv.getSettings().setDatabasePath(paramBundle);
                cwv.requestFocus();
                cwv.requestFocusFromTouch();
                cwv.requestFocus(130);
                this.reloadtwitterheader.setVisibility(4);
                break;
                if (Build.VERSION.SDK_INT >= 11)
                {
                  cwv.loadUrl("file:///android_asset/www/index4.html?appid=" + paramBundle + "@@" + str + "@@" + (String)localObject + "");
                }
                else
                {
                  cwv.loadUrl("file:///android_asset/www/index4.html?appid=" + paramBundle + "@@" + str + "@@" + (String)localObject + "");
                  continue;
                  if (!this.navigationLayout.equals("slidemenu"))
                  {
                    cwv.loadUrl("file:///android_asset/www/index.html?appid=" + paramBundle + "@@" + str + "@@" + (String)localObject + "");
                  }
                  else if (Build.VERSION.SDK_INT >= 11)
                  {
                    cwv.loadUrl("file:///android_asset/www/index2.html?appid=" + paramBundle + "@@" + str + "@@" + (String)localObject + "");
                  }
                  else
                  {
                    cwv.loadUrl("file:///android_asset/www/index2.html?appid=" + paramBundle + "@@" + str + "@@" + (String)localObject + "");
                    continue;
                    if (this.appLanguage.equalsIgnoreCase("sa"))
                    {
                      if (!this.navigationLayout.equals("slidemenu")) {
                        cwv.loadUrl("file:///android_asset/www/index3.html");
                      } else if (Build.VERSION.SDK_INT >= 11) {
                        cwv.loadUrl("file:///android_asset/www/index4.html");
                      } else {
                        cwv.loadUrl("file:///android_asset/www/index4.html");
                      }
                    }
                    else if (!this.navigationLayout.equals("slidemenu")) {
                      cwv.loadUrl("file:///android_asset/www/index.html");
                    } else if (Build.VERSION.SDK_INT >= 11) {
                      cwv.loadUrl("file:///android_asset/www/index2.html");
                    } else {
                      cwv.loadUrl("file:///android_asset/www/index2.html");
                    }
                  }
                }
              }
            }
            catch (Exception paramBundle)
            {
              for (;;) {}
            }
          }
          catch (Exception paramBundle)
          {
            for (;;) {}
          }
        }
      }
    }
  }
  
  public void onDestroy()
  {
    if (this.exitNum >= 2) {
      try
      {
        if (this.mRegisterTask != null) {
          this.mRegisterTask.cancel(true);
        }
        setFromNotification("");
        try
        {
          unregisterReceiver(this.mHandleMessageReceiver);
          GCMRegistrar.onDestroy(this);
          if (this.mgr != null) {
            this.mgr.listen(this.phoneStateListener, 0);
          }
          if (Build.VERSION.SDK_INT > 17)
          {
            this.iBeaconManager.unBind(this);
            this.bound = false;
          }
          if (this.bp != null) {
            this.bp.release();
          }
          stopService(this.servIntent);
          Process.killProcess(Process.myPid());
          System.exit(0);
          super.onDestroy();
          return;
        }
        catch (Exception localException1)
        {
          for (;;)
          {
            Log.e("UnRegister Receiver Error", "> " + localException1.getMessage());
          }
        }
        popUpExit();
      }
      catch (Exception localException2) {}
    }
  }
  
  public void onIBeaconServiceConnect()
  {
    Log.e("", " inside onIBeaconServiceConnect starts now....." + new Date().getSeconds() / 1000);
    this.iBeaconManager.setRangeNotifier(new RangeNotifier()
    {
      public void didRangeBeaconsInRegion(Collection<IBeacon> paramAnonymousCollection, Region paramAnonymousRegion)
      {
        paramAnonymousCollection = paramAnonymousCollection.iterator();
        for (;;)
        {
          int i;
          if (paramAnonymousCollection.hasNext())
          {
            paramAnonymousRegion = (IBeacon)paramAnonymousCollection.next();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>%%%%%%%%%%%%%%%%%%UUUUUUUUUUUUUUU" + paramAnonymousRegion.getProximityUuid());
            i = 0;
          }
          while (i < MyPhoneGapActivity.this.beaconUUID.size())
          {
            Object localObject1 = ((String)MyPhoneGapActivity.this.beaconUUID.get(i)).split("@@");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>%%%%%%%%%%%%%%%%%%UUUUUUUUUUUUUUU" + paramAnonymousRegion.getProximityUuid());
            if (localObject1.length > 4)
            {
              System.out.println("latest app");
              if ((localObject1[0].equalsIgnoreCase(paramAnonymousRegion.getProximityUuid())) && (localObject1[1].equalsIgnoreCase(String.valueOf(paramAnonymousRegion.getMajor()))) && (localObject1[2].equalsIgnoreCase(String.valueOf(paramAnonymousRegion.getMinor())))) {
                try
                {
                  MyPhoneGapActivity.access$2502(MyPhoneGapActivity.this, "Notification");
                  MyPhoneGapActivity.access$2602(MyPhoneGapActivity.this, localObject1[4]);
                  if (!MyPhoneGapActivity.sharedpreferences.getString("lastNotifyTime", "").equalsIgnoreCase(""))
                  {
                    Object localObject3 = MyPhoneGapActivity.sharedpreferences.getString("lastNotifyTime", "");
                    Object localObject2 = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                    String str = ((SimpleDateFormat)localObject2).format(Calendar.getInstance().getTime());
                    localObject3 = ((SimpleDateFormat)localObject2).parse((String)localObject3);
                    localObject2 = ((SimpleDateFormat)localObject2).parse(str);
                    int j = MyPhoneGapActivity.this.getDifference((Date)localObject3, (Date)localObject2);
                    System.out.println("timeDifferenceInHrs ====" + j);
                    if (j > Integer.parseInt(localObject1[5]))
                    {
                      MyPhoneGapActivity.access$2802(MyPhoneGapActivity.this, paramAnonymousRegion.getProximityUuid());
                      MyPhoneGapActivity.access$2902(MyPhoneGapActivity.this, paramAnonymousRegion.getMinor());
                      MyPhoneGapActivity.access$3002(MyPhoneGapActivity.this, paramAnonymousRegion.getMajor());
                      localObject1 = new SimpleDateFormat("dd/MM/yyyy hh:mm").format(Calendar.getInstance().getTime());
                      MyPhoneGapActivity.editor = MyPhoneGapActivity.sharedpreferences.edit();
                      MyPhoneGapActivity.editor.putString("lastNotifyTime", (String)localObject1);
                      MyPhoneGapActivity.editor.apply();
                      MyPhoneGapActivity.this.sendNotification(paramAnonymousRegion.getProximityUuid(), paramAnonymousRegion.getMinor(), paramAnonymousRegion.getMajor());
                    }
                  }
                  else
                  {
                    MyPhoneGapActivity.access$2802(MyPhoneGapActivity.this, paramAnonymousRegion.getProximityUuid());
                    MyPhoneGapActivity.access$2902(MyPhoneGapActivity.this, paramAnonymousRegion.getMinor());
                    MyPhoneGapActivity.access$3002(MyPhoneGapActivity.this, paramAnonymousRegion.getMajor());
                    localObject1 = new SimpleDateFormat("dd/MM/yyyy hh:mm").format(Calendar.getInstance().getTime());
                    MyPhoneGapActivity.editor = MyPhoneGapActivity.sharedpreferences.edit();
                    MyPhoneGapActivity.editor.putString("lastNotifyTime", (String)localObject1);
                    MyPhoneGapActivity.editor.apply();
                    MyPhoneGapActivity.this.sendNotification(paramAnonymousRegion.getProximityUuid(), paramAnonymousRegion.getMinor(), paramAnonymousRegion.getMajor());
                  }
                }
                catch (Exception localException) {}
              }
            }
            else
            {
              System.out.println("old app");
              if ((localException[0].equalsIgnoreCase(paramAnonymousRegion.getProximityUuid())) && (localException[1].equalsIgnoreCase(String.valueOf(paramAnonymousRegion.getMajor()))) && (localException[2].equalsIgnoreCase(String.valueOf(paramAnonymousRegion.getMinor()))))
              {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>%%%%%%%%%%%%%%%%%%UUUUUUUUUUUUUUU");
                MyPhoneGapActivity.access$2502(MyPhoneGapActivity.this, "Notification");
                MyPhoneGapActivity.access$2602(MyPhoneGapActivity.this, localException[4]);
                if ((!MyPhoneGapActivity.this.lastBeaconUUID.equals(paramAnonymousRegion.getProximityUuid())) && (MyPhoneGapActivity.this.lastBeaconMinor != paramAnonymousRegion.getMinor()) && (MyPhoneGapActivity.this.lastBeaconMajor != paramAnonymousRegion.getMajor()))
                {
                  MyPhoneGapActivity.access$2802(MyPhoneGapActivity.this, paramAnonymousRegion.getProximityUuid());
                  MyPhoneGapActivity.access$2902(MyPhoneGapActivity.this, paramAnonymousRegion.getMinor());
                  MyPhoneGapActivity.access$3002(MyPhoneGapActivity.this, paramAnonymousRegion.getMajor());
                  MyPhoneGapActivity.this.sendNotification(paramAnonymousRegion.getProximityUuid(), paramAnonymousRegion.getMinor(), paramAnonymousRegion.getMajor());
                  break label746;
                  return;
                }
              }
            }
            label746:
            i += 1;
          }
        }
      }
    });
    try
    {
      this.iBeaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
      Log.e("", " after onIBeaconServiceConnect starts now....." + new Date().getSeconds() / 1000);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;) {}
    }
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    }
    for (;;)
    {
      return false;
      this.audio.adjustStreamVolume(3, 1, 1);
      return true;
      this.audio.adjustStreamVolume(3, -1, 1);
      return true;
      if (cwv.canGoBack())
      {
        if (!this.firstItem.equals(""))
        {
          finish();
        }
        else
        {
          if (!cwv1.canGoBack()) {
            cwv1.setVisibility(8);
          }
          while ((cwv1.canGoBack()) && (!cwv1.getOriginalUrl().equals("http://thesneakerszone.com/")))
          {
            cwv1.goBack();
            return true;
            if (cwv1 != null) {
              cwv1.goBack();
            }
          }
          onDestroy();
          return true;
        }
      }
      else if (cwv1.getVisibility() == 0)
      {
        if (!this.firstItem.equals(""))
        {
          finish();
        }
        else
        {
          System.out.println("teswt ");
          cwv1.setVisibility(8);
          finish();
          return false;
        }
      }
      else {
        finish();
      }
    }
  }
  
  public Object onMessage(String paramString, Object paramObject)
  {
    return null;
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    Log.v("amriteshlatlong", "MyPhoneGapActiivity : onNewIntent()");
    String str3 = "";
    String str2 = "";
    Bundle localBundle = new Bundle();
    Object localObject4 = str2;
    String str1 = str3;
    Object localObject1 = str2;
    Object localObject2 = str3;
    try
    {
      if (paramIntent.getStringExtra("message") != null)
      {
        localObject1 = str2;
        localObject2 = str3;
        str1 = paramIntent.getStringExtra("message");
        localObject1 = str2;
        localObject2 = str1;
        str3 = paramIntent.getStringExtra("webUrl");
        localObject1 = str2;
        localObject2 = str1;
        localObject4 = paramIntent.getStringExtra("geofance");
        localObject1 = localObject4;
        localObject2 = str1;
        str2 = paramIntent.getStringExtra("internalUrl");
        localObject1 = localObject4;
        localObject2 = str1;
        String str4 = paramIntent.getStringExtra("fenceMsgImage");
        localObject1 = localObject4;
        localObject2 = str1;
        String str5 = paramIntent.getStringExtra("frompushnotification");
        localObject1 = localObject4;
        localObject2 = str1;
        localBundle.putString("message", str1);
        localObject1 = localObject4;
        localObject2 = str1;
        localBundle.putString("webUrl", str3);
        localObject1 = localObject4;
        localObject2 = str1;
        localBundle.putString("fenceMsgImage", str4);
        localObject1 = localObject4;
        localObject2 = str1;
        localBundle.putString("frompushnotification", str5);
        localObject1 = localObject4;
        localObject2 = str1;
        localBundle.putString("internalUrl", str2);
      }
    }
    catch (Exception localException)
    {
      label482:
      do
      {
        for (;;)
        {
          localException.printStackTrace();
          localObject4 = localObject1;
          Object localObject3 = localObject2;
          continue;
          if ((localObject3 != null) && (((String)localObject4).equalsIgnoreCase("geofanceAmritesh")))
          {
            Log.v("amriteshlatlong", "MyPhoneGapActiivity : check for geofance");
            localObject2 = new Intent(this, NotificationGeofance.class);
            ((Intent)localObject2).putExtras(localBundle);
            startActivity((Intent)localObject2);
          }
        }
      } while (((!((String)localObject1).equalsIgnoreCase("")) || (!this.autologin.equalsIgnoreCase("false"))) && ((!((String)localObject1).equalsIgnoreCase("true")) || (!this.autologin.equalsIgnoreCase("true"))));
      if ((paramIntent.getStringExtra("SendTo") == null) || (paramIntent.getStringExtra("SendTo").equals("noPageid"))) {
        break label603;
      }
      Log.e("called", "Called2: ");
      cwv.loadUrl("javascript:openpagefromnotification('" + paramIntent.getStringExtra("SendTo") + "');");
      return;
      label603:
      startActivity(new Intent(this, NotificationListActivity.class).putExtra("frompushnotification", "true"));
    }
    localObject1 = getUserStatus();
    if (paramIntent.getStringExtra("frompushnotification") != null)
    {
      Log.e("called", "Called1: " + paramIntent.getStringExtra("SendTo"));
      System.out.println("SEND TO>>>>>>>>>>" + paramIntent.getStringExtra("SendTo"));
      if ((paramIntent.getStringExtra("SendTo") != null) && (!paramIntent.getStringExtra("SendTo").equals("noPageid")))
      {
        Log.e("called", "Called2: ");
        cwv.loadUrl("javascript:openpagefromnotification('" + paramIntent.getStringExtra("SendTo") + "');");
        if ((!((String)localObject1).equalsIgnoreCase("")) || (!this.autologin.equalsIgnoreCase("true"))) {
          break label482;
        }
        setFromNotification("true");
      }
    }
    else
    {
      return;
    }
  }
  
  protected void onPause()
  {
    super.onPause();
    this.pauseTrue = true;
    if (cwv != null) {
      if ((cwv1 != null) && (this.flag))
      {
        cwv1.onPause();
        cwv1.pauseTimers();
      }
    }
    do
    {
      return;
      this.cordovaWebView.handlePause(true);
      if (Build.VERSION.SDK_INT > 17) {
        this.bound = true;
      }
    } while (this.ads == null);
    this.ads.pause();
  }
  
  public void onProductPurchased(String paramString, TransactionDetails paramTransactionDetails)
  {
    if (this.signupPaymentCheck.equalsIgnoreCase("true"))
    {
      this.signupPaymentCheck = "";
      cwv.loadUrl("javascript:signUpPaymentSuccess();");
    }
    for (;;)
    {
      try
      {
        boolean bool = this.bp.consumePurchase(this.paymentId);
        System.out.println("consume done" + bool);
        return;
      }
      catch (Exception paramString) {}
      cwv.loadUrl("javascript:inappSuccess('success');");
    }
  }
  
  public void onPurchaseHistoryRestored() {}
  
  public void onReceivedError(int paramInt, String paramString1, String paramString2)
  {
    if (this.retryCount < 3)
    {
      this.retryCount += 1;
      cwv.loadUrl("file:///android_asset/www/index.html");
      return;
    }
    cwv.loadUrl("file:///android_asset/www/index.html");
  }
  
  protected void onResume()
  {
    super.onResume();
    if (cwv1 != null)
    {
      cwv1.onResume();
      cwv1.resumeTimers();
    }
    cwv.loadUrl("javascript:handleOrientationForViews();");
    if (this.ads != null) {
      this.ads.resume();
    }
  }
  
  public void onStart()
  {
    super.onStart();
    Log.e("", " inside onStart starts now....." + new Date().getSeconds() / 1000);
    if ((this.uAId != null) && (this.uAId.trim().length() > 1)) {
      GoogleAnalytics.getInstance(this).reportActivityStart(this);
    }
    Log.e("", " after onStart starts now....." + new Date().getSeconds() / 1000);
  }
  
  protected void onStop()
  {
    super.onStop();
    GoogleAnalytics.getInstance(this).reportActivityStop(this);
    if (inCustomView()) {
      hideCustomView();
    }
  }
  
  @JavascriptInterface
  public void openBook(String paramString)
  {
    Intent localIntent = new Intent(this, EpubBook.class);
    localIntent.putExtra("bookpath", paramString);
    startActivity(localIntent);
  }
  
  @JavascriptInterface
  public void openChat(String paramString1, String paramString2)
  {
    getPackageManager();
    if (paramString1.contains("skype"))
    {
      if (getPackageManager().getLaunchIntentForPackage("com.skype.raider") != null)
      {
        Log.e("username", paramString2);
        SkypeUri(this, "skype:" + paramString2 + "?chat");
      }
    }
    else
    {
      if (paramString1.contains("linechat"))
      {
        paramString2 = getPackageManager().getLaunchIntentForPackage("jp.naver.line.android");
        if (paramString2 == null) {
          break label211;
        }
        startActivity(paramString2);
      }
      label97:
      if (paramString1.contains("whatsapp"))
      {
        paramString2 = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
        if (paramString2 == null) {
          break label234;
        }
        startActivity(paramString2);
      }
      label127:
      if (paramString1.contains("wechat"))
      {
        paramString2 = getPackageManager().getLaunchIntentForPackage("com.tencent.mm");
        if (paramString2 == null) {
          break label257;
        }
        startActivity(paramString2);
      }
    }
    for (;;)
    {
      if (paramString1.contains("snapchat"))
      {
        paramString1 = getPackageManager().getLaunchIntentForPackage("com.snapchat.android");
        if (paramString1 == null) {
          break label280;
        }
        startActivity(paramString1);
      }
      return;
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.skype.raider")));
      break;
      label211:
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=jp.naver.line.android")));
      break label97;
      label234:
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.whatsapp")));
      break label127;
      label257:
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.tencent.mm")));
    }
    label280:
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.snapchat.android")));
  }
  
  @JavascriptInterface
  public void openDeepLinkURL(String paramString)
  {
    Object localObject = paramString.split("id=");
    if (localObject[1].contains("&")) {
      localObject = localObject[1].split("&")[0];
    }
    for (;;)
    {
      System.out.println("deeplink url===" + paramString);
      try
      {
        if (!appInstalledOrNot((String)localObject))
        {
          startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
          return;
          localObject = localObject[1];
        }
        else
        {
          startActivity(getPackageManager().getLaunchIntentForPackage((String)localObject));
          return;
        }
      }
      catch (Exception paramString) {}
    }
  }
  
  @JavascriptInterface
  public void openLocationSetting()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle("Location");
    localBuilder.setMessage("Please enable Location Service.");
    localBuilder.setCancelable(false);
    localBuilder.setPositiveButton("Enable", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        MyPhoneGapActivity.this.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
      }
    });
    localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    });
    localBuilder.show();
  }
  
  @JavascriptInterface
  public void openMapForAddress()
  {
    startActivity(new Intent(this, GetAddressOnMap.class));
  }
  
  @JavascriptInterface
  public void openNotificationListActivity()
  {
    setFromNotification("");
    startActivity(new Intent(this, NotificationListActivity.class).putExtra("frompushnotification", "true"));
  }
  
  @JavascriptInterface
  public void openOoyalaVideoPlayer(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent(this, BasicPlaybackVideoPlayerActivity.class);
    localIntent.putExtra("pcode", paramString1);
    localIntent.putExtra("embed_code", paramString2);
    localIntent.putExtra("selection_name", appName);
    startActivity(localIntent);
  }
  
  @JavascriptInterface
  public void openOptionActivity()
  {
    startActivityForResult(new Intent(this, Option.class), 1234);
  }
  
  @JavascriptInterface
  public void openPage(final String paramString)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        MyPhoneGapActivity.cwv.loadUrl("javascript:openPage(" + paramString + ");");
      }
    });
  }
  
  @JavascriptInterface
  public void openRssReader(String paramString)
  {
    Intent localIntent = new Intent(this, com.simplerssreader.MainActivity.class);
    localIntent.putExtra("url", paramString);
    startActivity(localIntent);
  }
  
  @JavascriptInterface
  public void openTheWebsiteOnNewPage(String paramString)
  {
    Intent localIntent = new Intent(this, PdfView.class);
    localIntent.putExtra("pdfurl", paramString);
    startActivity(localIntent);
  }
  
  @JavascriptInterface
  public void openWebUrl(String paramString)
  {
    try
    {
      if (paramString.contains("pdf"))
      {
        String str = paramString;
        if (paramString.contains("@@@@@false")) {
          str = paramString.split("@@@@@false")[0];
        }
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://docs.google.com/gview?embedded=true&url=" + str)));
        return;
      }
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
      return;
    }
    catch (Exception paramString)
    {
      this.alert.showAlertDialog(this, MyApplicationName.APP_NAME, "Please enter correct web url.", 1);
    }
  }
  
  @JavascriptInterface
  public void photo(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent(this, ImageGalleryActivity.class);
    localIntent.setFlags(67108864);
    localIntent.putExtra("position", paramString1);
    localIntent.putExtra("bigImageUrls", paramString2);
    startActivity(localIntent);
  }
  
  @JavascriptInterface
  public void playVideoInsideNativePlayer(String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setDataAndType(Uri.parse(paramString), "video/*");
      startActivity(localIntent);
      return;
    }
    catch (Exception paramString) {}
  }
  
  public void popUpExit()
  {
    this.exitNum += 1;
    if (this.exitNum == 1)
    {
      Toast.makeText(this, "Press again to exit", 0).show();
      new Handler().postDelayed(this.runnEx, 2000L);
    }
    while (this.exitNum != 2) {
      return;
    }
    exitApp();
  }
  
  @JavascriptInterface
  public void rateAndShare(String paramString)
  {
    String str = parseXmlUpdateData();
    Intent localIntent = new Intent(this, ReloadAppActivity.class);
    localIntent.putExtra("urlData", paramString);
    localIntent.putExtra("screenData", str);
    startActivity(localIntent);
  }
  
  public void requestPermission(CordovaPlugin paramCordovaPlugin, int paramInt, String paramString) {}
  
  public void requestPermissions(CordovaPlugin paramCordovaPlugin, int paramInt, String[] paramArrayOfString) {}
  
  @JavascriptInterface
  public String returnNotificationCount()
  {
    return String.valueOf(sharedpreferences.getInt("notificationCount", 0));
  }
  
  public void sendNotification(String paramString, int paramInt1, int paramInt2)
  {
    NotificationManager localNotificationManager = (NotificationManager)getSystemService("notification");
    Notification localNotification = new Notification(2130837602, "Alert", System.currentTimeMillis());
    Intent localIntent = new Intent(this, MyPhoneGapActivity.class);
    localIntent.putExtra("fromnotification", "true");
    editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
    editor.putString("lastBeaconUUID", paramString);
    editor.putString("lastBeaconMinor", String.valueOf(paramInt1));
    editor.putString("lastBeaconMajor", String.valueOf(paramInt2));
    editor.apply();
    paramString = PendingIntent.getActivity(this, 0, localIntent, 0);
    localNotification.setLatestEventInfo(this, this.notificationNotes, this.notificationTitle, paramString);
    localNotification.flags = 4;
    localNotification.defaults |= 0x1;
    localNotification.flags |= 0x10;
    localNotificationManager.notify(1, localNotification);
  }
  
  @JavascriptInterface
  public void sendemail(String paramString)
  {
    paramString = paramString.split("######");
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("plain/text");
    localIntent.putExtra("android.intent.extra.EMAIL", new String[] { paramString[0] });
    localIntent.putExtra("android.intent.extra.SUBJECT", paramString[1]);
    startActivity(Intent.createChooser(localIntent, "Send mail..."));
  }
  
  public void setActivityResultCallback(CordovaPlugin paramCordovaPlugin)
  {
    activityResultCallback = paramCordovaPlugin;
  }
  
  @JavascriptInterface
  public void setApplicationPackage(String paramString)
  {
    editor = sharedpreferences.edit();
    editor.putString("applicationpackage", paramString);
    editor.apply();
  }
  
  public void setFromNotification(String paramString)
  {
    editor = sharedpreferences.edit();
    editor.putString("FromNotification", paramString);
    editor.apply();
  }
  
  public void setImage(String paramString)
  {
    editor = sharedpreferences.edit();
    editor.putString("imageUrl", paramString);
    editor.apply();
  }
  
  @JavascriptInterface
  public void setLoginStatus(String paramString)
  {
    editor = sharedpreferences.edit();
    editor.putString("loginstatus", paramString);
    editor.apply();
  }
  
  @JavascriptInterface
  public void setOrientationForBarcodeCancel()
  {
    setRequestedOrientation(0);
  }
  
  @JavascriptInterface
  public void setOrientationToDefault()
  {
    setRequestedOrientation(-1);
  }
  
  @JavascriptInterface
  public void setUserStatus(String paramString)
  {
    editor = sharedpreferences.edit();
    editor.putString("fooduserid", paramString);
    editor.apply();
  }
  
  @JavascriptInterface
  public void setloyalityEmail(String paramString)
  {
    editor = sharedpreferences.edit();
    editor.putString("loyality_email", paramString);
    editor.apply();
  }
  
  @JavascriptInterface
  public void setloyalityXMLPath(String paramString)
  {
    editor = sharedpreferences.edit();
    editor.putString("loyality_xmlpath", paramString);
    editor.apply();
  }
  
  @JavascriptInterface
  public void shareDirectory(String paramString1, String paramString2)
  {
    if ((paramString2.contains("http")) && ((paramString2.contains("png")) || (paramString2.contains("jpg"))))
    {
      String str = this.SDDirectoryShareIMG;
      this.SDCardRoot = new File(Environment.getExternalStorageDirectory(), this.foldername + appName);
      if (!this.SDCardRoot.exists()) {
        this.SDCardRoot.mkdirs();
      }
      this.file = new File(this.SDCardRoot, str);
      if (this.file.exists()) {
        this.file.delete();
      }
      new myAsyncTaskShareIMG().execute(new String[] { paramString2 });
      paramString2 = new Intent("android.intent.action.SEND");
      paramString2.setType("image/*");
      paramString2.putExtra("android.intent.extra.SUBJECT", appName + " Directory Details");
      paramString2.putExtra("android.intent.extra.TEXT", paramString1);
      paramString2.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/" + this.foldername + appName + "/" + str)));
      startActivity(Intent.createChooser(paramString2, "Share using"));
      return;
    }
    System.out.println("Abhishek DIRECTORY share-view");
    paramString2 = new Intent("android.intent.action.SEND");
    paramString2.setType("text/plain");
    paramString2.putExtra("android.intent.extra.SUBJECT", appName + " Directory Details");
    paramString2.putExtra("android.intent.extra.TEXT", paramString1);
    startActivity(Intent.createChooser(paramString2, "Share using"));
  }
  
  @JavascriptInterface
  public void shareLink(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.TEXT", paramString);
    localIntent.putExtra("android.intent.extra.SUBJECT", "Share Rss");
    localIntent.setFlags(268435456);
    startActivity(Intent.createChooser(localIntent, "Share RSS Feed URL"));
  }
  
  public void shareLocation(View paramView)
  {
    paramView = new Intent("android.intent.action.SEND");
    paramView.setType("text/plain");
    paramView.putExtra("android.intent.extra.TEXT", "My current location : " + this.currentAddress);
    startActivity(Intent.createChooser(paramView, "Share via"));
  }
  
  @JavascriptInterface
  public void shareNotes(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.TEXT", paramString);
    startActivity(Intent.createChooser(localIntent, "Share using"));
  }
  
  @JavascriptInterface
  public String shareScore(String paramString)
  {
    System.out.println("666666666666--" + paramString);
    return "https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName();
  }
  
  @JavascriptInterface
  public void showAndHideAdView(final String paramString)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        System.out.println("appypie>>" + paramString);
        for (;;)
        {
          try
          {
            if (!paramString.equalsIgnoreCase("show")) {
              break label438;
            }
            if ((MyPhoneGapActivity.this.addIdBanner.toLowerCase().contains("ca-app-pub")) && (MyPhoneGapActivity.this.cdConnectionDetector.isConnectingToInternet())) {
              MyPhoneGapActivity.this.adLayout.setVisibility(0);
            }
            try
            {
              localInterstitialAd = new InterstitialAd(MyPhoneGapActivity.this);
              AdRequest localAdRequest = new AdRequest.Builder().build();
              com.google.android.gms.ads.AdListener local1 = new com.google.android.gms.ads.AdListener()
              {
                public void onAdLoaded()
                {
                  try
                  {
                    localInterstitialAd.show();
                    return;
                  }
                  catch (Exception localException)
                  {
                    Log.d("Display error", localException.toString());
                  }
                }
              };
              localInterstitialAd.setAdUnitId(MyPhoneGapActivity.this.addIdInterstial);
              localInterstitialAd.loadAd(localAdRequest);
              localInterstitialAd.setAdListener(local1);
            }
            catch (Exception localException2)
            {
              final InterstitialAd localInterstitialAd;
              System.out.println("error in interstial addid>>>>>>>>>>>>>>>>>>" + MyPhoneGapActivity.this.addIdInterstial);
              localException2.printStackTrace();
              continue;
            }
            try
            {
              localInterstitialAd.show();
              if (MyPhoneGapActivity.this.mHandler == null)
              {
                MyPhoneGapActivity.access$3102(MyPhoneGapActivity.this, new Handler());
                MyPhoneGapActivity.this.startRepeatingTask();
              }
              if ((MyPhoneGapActivity.this.applicationPlanType.equalsIgnoreCase("Free")) && (MyPhoneGapActivity.this.cdConnectionDetector.isConnectingToInternet()))
              {
                MyPhoneGapActivity.this.adLayout1.setVisibility(0);
                MyPhoneGapActivity.this.ads.show();
                if ((MyPhoneGapActivity.this.airPushAppId != null) && (MyPhoneGapActivity.this.airPushAppId.length() > 1) && (MyPhoneGapActivity.this.cdConnectionDetector.isConnectingToInternet()))
                {
                  MyPhoneGapActivity.this.adLayout2.setVisibility(0);
                  if (MyPhoneGapActivity.this.mHandler == null)
                  {
                    MyPhoneGapActivity.access$3102(MyPhoneGapActivity.this, new Handler());
                    MyPhoneGapActivity.this.startRepeatingTask();
                  }
                }
                MyPhoneGapActivity.this.closeAds2.setVisibility(4);
                MyPhoneGapActivity.this.closeAds1.setVisibility(4);
                MyPhoneGapActivity.this.closeAds.setVisibility(4);
                return;
              }
            }
            catch (Exception localException1)
            {
              Log.d("Display error", localException1.toString());
              continue;
            }
            if (MyPhoneGapActivity.this.applicationPlanType.equalsIgnoreCase("Free")) {
              continue;
            }
          }
          catch (Exception localException3)
          {
            localException3.printStackTrace();
            return;
          }
          if (MyPhoneGapActivity.this.applicationMadServe.equalsIgnoreCase("1"))
          {
            MyPhoneGapActivity.this.adLayout1.setVisibility(0);
            continue;
            label438:
            System.out.println("hide Layout");
            MyPhoneGapActivity.this.adLayout.setVisibility(4);
            MyPhoneGapActivity.this.adLayout1.setVisibility(4);
            MyPhoneGapActivity.this.adLayout2.setVisibility(4);
          }
        }
      }
    });
  }
  
  @JavascriptInterface
  public void showDirectoryOnMap(String paramString)
  {
    if (paramString.contains("mappagedir"))
    {
      localIntent = new Intent(this, MapPageMapMarker.class);
      localIntent.putExtra("urlData", paramString);
      startActivity(localIntent);
      return;
    }
    Intent localIntent = new Intent(this, DirectoryMapMarker.class);
    localIntent.putExtra("urlData", paramString);
    startActivityForResult(localIntent, 12123);
  }
  
  @JavascriptInterface
  public void showOverlay()
  {
    showProgressDiaLog(this, "");
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        if ((MyPhoneGapActivity.this.mDialog != null) && (MyPhoneGapActivity.this.mDialog.isShowing()))
        {
          MyPhoneGapActivity.this.mDialog.dismiss();
          MyPhoneGapActivity.this.mDialog = null;
        }
      }
    }, 800L);
  }
  
  public void showProgressDiaLog(Context paramContext, String paramString)
  {
    this.mDialog = new Dialog(this, 16973840);
    this.mDialog.setContentView(2130903129);
    ((TextView)this.mDialog.findViewById(2131558709)).setText(paramString);
    this.mDialog.setCancelable(true);
    this.mDialog.show();
  }
  
  public void startActivityForResult(CordovaPlugin paramCordovaPlugin, Intent paramIntent, int paramInt)
  {
    activityResultCallback = paramCordovaPlugin;
    this.activityResultKeepRunning = this.keepRunning;
    if (paramCordovaPlugin != null) {
      this.keepRunning = Boolean.valueOf(false);
    }
    super.startActivityForResult(paramIntent, paramInt);
  }
  
  @JavascriptInterface
  public void startGCM()
  {
    Log.e("", "startGCM starts now....." + new Date().getSeconds() / 1000);
    updatePushCount();
    MyApplicationName.Notification_Count = 0;
    SplashScreenActivity.TIMER_RUNTIME = 0;
    if (this.cdConnectionDetector.isConnectingToInternet()) {
      if (isGooglePlayServicesInstalled())
      {
        GCMRegistrar.checkDevice(this);
        GCMRegistrar.checkManifest(this);
      }
    }
    try
    {
      new RegisterGCMServiceTask(null).execute(new String[] { "Registering GCM Service....." });
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        try
        {
          executeAdListener();
          runOnUiThread(new Runnable()
          {
            public void run()
            {
              if (MyPhoneGapActivity.this.isGooglePlayServicesInstalled()) {
                System.out.println("addIdBanner>>>>>>>>>>>>>>>>>>" + MyPhoneGapActivity.this.addIdBanner);
              }
              try
              {
                MyPhoneGapActivity.access$3702(MyPhoneGapActivity.this, new com.google.android.gms.ads.AdView(MyPhoneGapActivity.this));
                MyPhoneGapActivity.this.adView.setAdListener(new com.google.android.gms.ads.AdListener()
                {
                  public void onAdClosed()
                  {
                    super.onAdClosed();
                    MyPhoneGapActivity.this.closeAds.setVisibility(4);
                  }
                  
                  public void onAdFailedToLoad(int paramAnonymous2Int)
                  {
                    super.onAdFailedToLoad(paramAnonymous2Int);
                    System.out.println("krishna ad loading failed");
                    MyPhoneGapActivity.this.closeAds.setVisibility(4);
                  }
                  
                  public void onAdLoaded()
                  {
                    super.onAdLoaded();
                    MyPhoneGapActivity.this.closeAds.setVisibility(4);
                  }
                  
                  public void onAdOpened()
                  {
                    super.onAdOpened();
                    MyPhoneGapActivity.this.closeAds.setVisibility(4);
                  }
                });
                MyPhoneGapActivity.this.adView.setAdSize(AdSize.BANNER);
                MyPhoneGapActivity.this.adView.setAdUnitId(MyPhoneGapActivity.this.addIdBanner);
                MyPhoneGapActivity.this.adView.loadAd(new AdRequest.Builder().build());
                MyPhoneGapActivity.this.admobBanner.addView(MyPhoneGapActivity.this.adView);
                return;
              }
              catch (Exception localException)
              {
                System.out.println("error in addIdBanner>>>>>>>>>>>>>>>>>>" + MyPhoneGapActivity.this.addIdBanner);
                localException.printStackTrace();
              }
            }
          });
          return;
        }
        catch (Exception localException2) {}
        localException1 = localException1;
        localException1.printStackTrace();
      }
    }
  }
  
  @JavascriptInterface
  public void startPedoMeter()
  {
    startActivity(new Intent(this, Pedometer.class));
  }
  
  void startRepeatingTask()
  {
    System.out.println("=====startRepeatingTask");
    this.mStatusChecker.run();
  }
  
  void stopRepeatingTask()
  {
    this.mHandler.removeCallbacks(this.mStatusChecker);
  }
  
  @JavascriptInterface
  public void switchTorch(String paramString)
  {
    System.out.println("1");
    do
    {
      try
      {
        if (!paramString.equals("on")) {
          continue;
        }
        while (this.releasing) {
          Thread.sleep(10L);
        }
        this.mCamera = Camera.open();
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return;
      }
      if (Build.VERSION.SDK_INT >= 11) {
        this.mCamera.setPreviewTexture(new SurfaceTexture(0));
      }
      toggleTorch(true);
      return;
    } while (!paramString.equals("off"));
    toggleTorch(false);
    releaseCamera();
  }
  
  @JavascriptInterface
  public void updateApplicationXML(String paramString)
  {
    if ((AppId != null) && (this.AppVersion != null)) {}
    try
    {
      if ((PlayerActivity.audioSignal != null) && (PlayerActivity.audioSignal.isPlaying())) {
        PlayerActivity.audioSignal.stop();
      }
      if ((com.ons.musicplayer.MainActivity.mp != null) && (com.ons.musicplayer.MainActivity.mp.isPlaying())) {
        com.ons.musicplayer.MainActivity.mp.stop();
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        try
        {
          String str;
          new DownloadFile().execute(new String[] { paramString, str });
          return;
        }
        catch (Exception paramString)
        {
          System.out.println("Exception in Downloading file..");
          paramString.printStackTrace();
        }
        localException = localException;
        localException.printStackTrace();
      }
    }
    if ((paramString.contains(".xml")) && (paramString != null)) {
      str = getFilesDir().toString();
    }
  }
  
  @JavascriptInterface
  public void updatePhoto(final boolean paramBoolean)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        MyPhoneGapActivity.this.uploadImage(Option.bitmap, Option.android_id, paramBoolean);
      }
    });
  }
  
  public void updatePushCount()
  {
    editor = sharedpreferences.edit();
    int i = sharedpreferences.getInt("notificationCount", 0);
    int j = MyApplicationName.Notification_Count;
    editor.putInt("notificationCount", i + j);
    editor.apply();
  }
  
  public void updateStatus()
  {
    System.out.println("===== updateStatus");
    for (;;)
    {
      try
      {
        localLayoutParams = (FrameLayout.LayoutParams)this.admobBanner.getLayoutParams();
        int i = (int)TypedValue.applyDimension(1, 67.0F, getResources().getDisplayMetrics());
        if (!this.navigationLayout.equalsIgnoreCase("bottom")) {
          continue;
        }
        localLayoutParams.setMargins(0, 0, 0, i);
        this.admobBanner.setLayoutParams(localLayoutParams);
      }
      catch (Exception localException)
      {
        FrameLayout.LayoutParams localLayoutParams;
        localException.printStackTrace();
        continue;
        System.out.println("===== reload is call not avia");
        this.admobBanner.setVisibility(4);
        this.offlinemodeImage.setVisibility(0);
      }
      System.out.println("===== reload is call");
      if (!this.cdConnectionDetector.isConnectingToInternet()) {
        continue;
      }
      System.out.println("===== reload is call avail ");
      this.admobBanner.setVisibility(0);
      this.offlinemodeImage.setVisibility(4);
      return;
      localLayoutParams.setMargins(0, 0, 0, 0);
    }
  }
  
  @JavascriptInterface
  public void uploadImage(Bitmap paramBitmap, String paramString, boolean paramBoolean)
  {
    Object localObject = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, (OutputStream)localObject);
    String str = Base64.encodeToString(((ByteArrayOutputStream)localObject).toByteArray(), 0);
    try
    {
      paramBitmap = new SoapObject("http://appypieml.onsisdev.info/services/utility-soap/", "uploadpic");
      localObject = new AndroidHttpTransport("http://appypieml.onsisdev.info/services/utility-soap/");
      paramBitmap.addProperty("image", str);
      paramBitmap.addProperty("uuid", paramString);
      paramString = new SoapSerializationEnvelope(110);
      paramString.dotNet = false;
      paramString.setOutputSoapObject(paramBitmap);
      ((AndroidHttpTransport)localObject).call("http://schemas.xmlsoap.org/wsdl/", paramString);
      paramBitmap = paramString.getResponse().toString();
      Toast.makeText(getApplicationContext(), "" + paramBitmap, 1).show();
      cwv.loadUrl("javascript:loadChatRoomAfterImageUpdate(" + paramBoolean + ")");
      return;
    }
    catch (Exception paramBitmap)
    {
      Toast.makeText(getApplicationContext(), "" + paramBitmap.getMessage(), 1).show();
    }
  }
  
  @JavascriptInterface
  public void uploadMultipleFiles(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11)
  {
    new UploadMultipleFiles().execute(new String[] { paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, paramString9, paramString10, paramString11 });
  }
  
  @JavascriptInterface
  public void uploadMultipleFilesAppointment(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13, String paramString14, String paramString15, String paramString16, String paramString17, String paramString18, String paramString19)
  {
    new AppointmentPageUploadMultipleFiles().execute(new String[] { paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, paramString9, paramString10, paramString11, paramString12, paramString13, paramString14, paramString15, paramString16, paramString17, paramString18, paramString19 });
  }
  
  @JavascriptInterface
  public void uploadMultipleFilesDirectory(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13, String paramString14, String paramString15, String paramString16, String paramString17, String paramString18, String paramString19, String paramString20, String paramString21, String paramString22, String paramString23, String paramString24, String paramString25, String paramString26, String paramString27, String paramString28, String paramString29, String paramString30, String paramString31, String paramString32, String paramString33, String paramString34)
  {
    new DirectoryPageUploadMultipleFiles().execute(new String[] { paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, paramString9, paramString10, paramString11, paramString12, paramString13, paramString14, paramString15, paramString16, paramString17, paramString18, paramString19, paramString20, paramString21, paramString22, paramString23, paramString24, paramString25, paramString26, paramString27, paramString28, paramString29, paramString30, paramString31, paramString32, paramString33, paramString34 });
  }
  
  @JavascriptInterface
  public void uploadMultipleFilesRegister(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11)
  {
    new RegisterPageUploadMultipleFiles().execute(new String[] { paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, paramString9, paramString10, paramString11 });
  }
  
  @JavascriptInterface
  public void youTube(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent(this, VideoDemo.class);
    localIntent.setFlags(67108864);
    localIntent.putExtra("videoId", paramString1);
    localIntent.putExtra("videoInfo", paramString2);
    startActivity(localIntent);
  }
  
  public class AppointmentPageUploadMultipleFiles
    extends AsyncTask<String, Void, Void>
  {
    public AppointmentPageUploadMultipleFiles() {}
    
    protected Void doInBackground(String... paramVarArgs)
    {
      String str1 = paramVarArgs[0];
      String str2 = paramVarArgs[1];
      String str3 = paramVarArgs[2];
      String str4 = paramVarArgs[3];
      String str5 = paramVarArgs[4];
      String str6 = paramVarArgs[5];
      String str7 = paramVarArgs[6];
      String str8 = paramVarArgs[7];
      String str9 = paramVarArgs[8];
      String str10 = paramVarArgs[9];
      String str11 = paramVarArgs[10];
      String str12 = paramVarArgs[11];
      String str13 = paramVarArgs[12];
      String str14 = paramVarArgs[13];
      String str15 = paramVarArgs[14];
      String str16 = paramVarArgs[15];
      String str17 = paramVarArgs[16];
      Object localObject = paramVarArgs[17];
      paramVarArgs = paramVarArgs[18];
      Log.e("appId>>>>", str1);
      Log.e("formPageId>>>>", str2);
      Log.e("subject>>>>", str3);
      Log.e("ownerEmail>>>>", str4);
      Log.e("appointmentDate>>>>", str5);
      Log.e("appointmentTime>>>>", str6);
      Log.e("userName>>>>", str7);
      Log.e("userEmail>>>>", str8);
      Log.e("userPhone>>>>", str9);
      Log.e("userRemark>>>>", str10);
      Log.e("appId>>>>", str1);
      Log.e("status>>>>", str11);
      Log.e("userDeviceType>>>>", str12);
      Log.e("formLabel>>>>", str16);
      Log.e("formDatas>>>>", str15);
      Log.e("formFields>>>>", str17);
      Log.e("totCountFile>>>>", (String)localObject);
      Log.e("path>>>>", paramVarArgs);
      paramVarArgs = paramVarArgs.split(",");
      try
      {
        localObject = new DefaultHttpClient();
        HttpPost localHttpPost = new HttpPost("http://" + MyPhoneGapActivity.appDomainValue + "/formbuilder/send-appointment-custom");
        MultipartEntity localMultipartEntity = new MultipartEntity();
        int i = 0;
        while (i < paramVarArgs.length)
        {
          Log.e("Single Files>>>>", paramVarArgs[i]);
          FileBody localFileBody = new FileBody(new File(paramVarArgs[i]));
          localMultipartEntity.addPart("uploadedfile" + i, localFileBody);
          i += 1;
        }
        localMultipartEntity.addPart("appId", new StringBody(str1));
        localMultipartEntity.addPart("formPageId", new StringBody(str2));
        localMultipartEntity.addPart("subject", new StringBody(str3));
        localMultipartEntity.addPart("ownerEmail", new StringBody(str4));
        localMultipartEntity.addPart("appointmentDate", new StringBody(str5));
        localMultipartEntity.addPart("appointmentTime", new StringBody(str6));
        localMultipartEntity.addPart("userName", new StringBody(str7));
        localMultipartEntity.addPart("userEmail", new StringBody(str8));
        localMultipartEntity.addPart("userPhone", new StringBody(str9));
        localMultipartEntity.addPart("userRemark", new StringBody(str10));
        localMultipartEntity.addPart("status", new StringBody(str11));
        localMultipartEntity.addPart("userDeviceType", new StringBody(str12));
        localMultipartEntity.addPart("userDeviceId", new StringBody(str13));
        localMultipartEntity.addPart("userDeviceToken", new StringBody(str14));
        localMultipartEntity.addPart("formLabel", new StringBody(str16));
        localMultipartEntity.addPart("formData", new StringBody(str15));
        localMultipartEntity.addPart("formFields", new StringBody(str17));
        localHttpPost.setEntity(localMultipartEntity);
        Log.e("Final Response", EntityUtils.toString(((HttpClient)localObject).execute(localHttpPost).getEntity()));
      }
      catch (Exception paramVarArgs)
      {
        for (;;)
        {
          Log.e("Debug", "error: " + paramVarArgs.getMessage(), paramVarArgs);
        }
      }
      return null;
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      MyPhoneGapActivity.cwv.loadUrl("javascript:appointementDataSendSuccess('" + MyPhoneGapActivity.status + "');");
      super.onPostExecute(paramVoid);
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
    }
  }
  
  public class DirectoryPageUploadMultipleFiles
    extends AsyncTask<String, Void, Void>
  {
    public DirectoryPageUploadMultipleFiles() {}
    
    /* Error */
    protected Void doInBackground(String... paramVarArgs)
    {
      // Byte code:
      //   0: aload_1
      //   1: iconst_0
      //   2: aaload
      //   3: astore 9
      //   5: aload_1
      //   6: iconst_1
      //   7: aaload
      //   8: astore 10
      //   10: aload_1
      //   11: iconst_2
      //   12: aaload
      //   13: astore 11
      //   15: aload_1
      //   16: iconst_3
      //   17: aaload
      //   18: astore 12
      //   20: aload_1
      //   21: iconst_4
      //   22: aaload
      //   23: astore 13
      //   25: aload_1
      //   26: iconst_5
      //   27: aaload
      //   28: astore 14
      //   30: aload_1
      //   31: bipush 6
      //   33: aaload
      //   34: astore 15
      //   36: aload_1
      //   37: bipush 7
      //   39: aaload
      //   40: astore 16
      //   42: aload_1
      //   43: bipush 8
      //   45: aaload
      //   46: astore 17
      //   48: aload_1
      //   49: bipush 9
      //   51: aaload
      //   52: astore 18
      //   54: aload_1
      //   55: bipush 10
      //   57: aaload
      //   58: astore 19
      //   60: aload_1
      //   61: bipush 11
      //   63: aaload
      //   64: astore 20
      //   66: aload_1
      //   67: bipush 12
      //   69: aaload
      //   70: astore 21
      //   72: aload_1
      //   73: bipush 13
      //   75: aaload
      //   76: astore 22
      //   78: aload_1
      //   79: bipush 14
      //   81: aaload
      //   82: astore 23
      //   84: aload_1
      //   85: bipush 15
      //   87: aaload
      //   88: astore 24
      //   90: aload_1
      //   91: bipush 16
      //   93: aaload
      //   94: astore 25
      //   96: aload_1
      //   97: bipush 17
      //   99: aaload
      //   100: astore 26
      //   102: aload_1
      //   103: bipush 18
      //   105: aaload
      //   106: astore 27
      //   108: aload_1
      //   109: bipush 19
      //   111: aaload
      //   112: astore 28
      //   114: aload_1
      //   115: bipush 20
      //   117: aaload
      //   118: astore 29
      //   120: aload_1
      //   121: bipush 21
      //   123: aaload
      //   124: astore 30
      //   126: aload_1
      //   127: bipush 22
      //   129: aaload
      //   130: astore 31
      //   132: aload_1
      //   133: bipush 23
      //   135: aaload
      //   136: astore 32
      //   138: aload_1
      //   139: bipush 24
      //   141: aaload
      //   142: astore 33
      //   144: aload_1
      //   145: bipush 25
      //   147: aaload
      //   148: astore 34
      //   150: aload_1
      //   151: bipush 26
      //   153: aaload
      //   154: astore 35
      //   156: aload_1
      //   157: bipush 27
      //   159: aaload
      //   160: astore 36
      //   162: aload_1
      //   163: bipush 28
      //   165: aaload
      //   166: astore 37
      //   168: aload_1
      //   169: bipush 29
      //   171: aaload
      //   172: astore 38
      //   174: aload_1
      //   175: bipush 30
      //   177: aaload
      //   178: astore 5
      //   180: aload_1
      //   181: bipush 31
      //   183: aaload
      //   184: astore 8
      //   186: aload_1
      //   187: bipush 32
      //   189: aaload
      //   190: astore_3
      //   191: aload_1
      //   192: bipush 33
      //   194: aaload
      //   195: astore 4
      //   197: aload_3
      //   198: ifnull +20 -> 218
      //   201: aload_3
      //   202: ldc 29
      //   204: invokevirtual 35	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   207: ifne +11 -> 218
      //   210: aload_3
      //   211: astore_1
      //   212: aload_3
      //   213: ldc 29
      //   215: if_acmpne +6 -> 221
      //   218: ldc 37
      //   220: astore_1
      //   221: aload 4
      //   223: ifnull +23 -> 246
      //   226: aload 4
      //   228: ldc 29
      //   230: invokevirtual 35	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
      //   233: ifne +13 -> 246
      //   236: aload 4
      //   238: astore_3
      //   239: aload 4
      //   241: ldc 29
      //   243: if_acmpne +6 -> 249
      //   246: ldc 37
      //   248: astore_3
      //   249: aload 5
      //   251: invokevirtual 41	java/lang/String:length	()I
      //   254: ifeq +32 -> 286
      //   257: aload 5
      //   259: ldc 29
      //   261: invokevirtual 45	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   264: ifne +22 -> 286
      //   267: aload 5
      //   269: ifnull +17 -> 286
      //   272: aload 5
      //   274: astore 4
      //   276: aload 5
      //   278: ldc 47
      //   280: invokevirtual 45	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   283: ifeq +7 -> 290
      //   286: ldc 49
      //   288: astore 4
      //   290: new 51	org/apache/http/impl/client/DefaultHttpClient
      //   293: dup
      //   294: invokespecial 52	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
      //   297: astore 5
      //   299: getstatic 56	com/example/example75f1799f07eb/MyPhoneGapActivity:appDomainValue	Ljava/lang/String;
      //   302: astore 6
      //   304: new 58	org/apache/http/client/methods/HttpPost
      //   307: dup
      //   308: new 60	java/lang/StringBuilder
      //   311: dup
      //   312: invokespecial 61	java/lang/StringBuilder:<init>	()V
      //   315: ldc 63
      //   317: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   320: getstatic 56	com/example/example75f1799f07eb/MyPhoneGapActivity:appDomainValue	Ljava/lang/String;
      //   323: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   326: ldc 69
      //   328: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   331: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   334: invokespecial 76	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
      //   337: astore 6
      //   339: new 78	org/apache/http/entity/mime/MultipartEntity
      //   342: dup
      //   343: invokespecial 79	org/apache/http/entity/mime/MultipartEntity:<init>	()V
      //   346: astore 7
      //   348: aload 7
      //   350: ldc 81
      //   352: new 83	org/apache/http/entity/mime/content/StringBody
      //   355: dup
      //   356: aload 9
      //   358: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   361: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   364: aload 7
      //   366: ldc 90
      //   368: new 83	org/apache/http/entity/mime/content/StringBody
      //   371: dup
      //   372: aload 10
      //   374: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   377: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   380: aload 7
      //   382: ldc 92
      //   384: new 83	org/apache/http/entity/mime/content/StringBody
      //   387: dup
      //   388: aload 11
      //   390: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   393: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   396: aload 7
      //   398: ldc 94
      //   400: new 83	org/apache/http/entity/mime/content/StringBody
      //   403: dup
      //   404: aload 12
      //   406: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   409: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   412: aload 7
      //   414: ldc 96
      //   416: new 83	org/apache/http/entity/mime/content/StringBody
      //   419: dup
      //   420: aload 13
      //   422: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   425: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   428: aload 7
      //   430: ldc 98
      //   432: new 83	org/apache/http/entity/mime/content/StringBody
      //   435: dup
      //   436: aload 14
      //   438: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   441: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   444: aload 7
      //   446: ldc 100
      //   448: new 83	org/apache/http/entity/mime/content/StringBody
      //   451: dup
      //   452: aload 15
      //   454: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   457: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   460: aload 7
      //   462: ldc 102
      //   464: new 83	org/apache/http/entity/mime/content/StringBody
      //   467: dup
      //   468: aload 16
      //   470: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   473: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   476: aload 7
      //   478: ldc 104
      //   480: new 83	org/apache/http/entity/mime/content/StringBody
      //   483: dup
      //   484: aload 17
      //   486: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   489: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   492: aload 7
      //   494: ldc 106
      //   496: new 83	org/apache/http/entity/mime/content/StringBody
      //   499: dup
      //   500: aload 18
      //   502: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   505: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   508: aload 7
      //   510: ldc 108
      //   512: new 83	org/apache/http/entity/mime/content/StringBody
      //   515: dup
      //   516: aload 19
      //   518: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   521: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   524: aload 7
      //   526: ldc 110
      //   528: new 83	org/apache/http/entity/mime/content/StringBody
      //   531: dup
      //   532: aload 20
      //   534: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   537: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   540: aload 7
      //   542: ldc 112
      //   544: new 83	org/apache/http/entity/mime/content/StringBody
      //   547: dup
      //   548: aload 21
      //   550: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   553: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   556: aload 7
      //   558: ldc 114
      //   560: new 83	org/apache/http/entity/mime/content/StringBody
      //   563: dup
      //   564: aload 22
      //   566: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   569: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   572: aload 7
      //   574: ldc 116
      //   576: new 83	org/apache/http/entity/mime/content/StringBody
      //   579: dup
      //   580: aload 23
      //   582: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   585: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   588: aload 7
      //   590: ldc 118
      //   592: new 83	org/apache/http/entity/mime/content/StringBody
      //   595: dup
      //   596: aload 24
      //   598: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   601: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   604: aload 7
      //   606: ldc 120
      //   608: new 83	org/apache/http/entity/mime/content/StringBody
      //   611: dup
      //   612: aload 25
      //   614: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   617: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   620: aload 7
      //   622: ldc 122
      //   624: new 83	org/apache/http/entity/mime/content/StringBody
      //   627: dup
      //   628: aload 26
      //   630: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   633: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   636: aload 7
      //   638: ldc 124
      //   640: new 83	org/apache/http/entity/mime/content/StringBody
      //   643: dup
      //   644: aload 27
      //   646: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   649: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   652: aload 7
      //   654: ldc 126
      //   656: new 83	org/apache/http/entity/mime/content/StringBody
      //   659: dup
      //   660: aload 28
      //   662: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   665: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   668: aload 7
      //   670: ldc -128
      //   672: new 83	org/apache/http/entity/mime/content/StringBody
      //   675: dup
      //   676: aload 29
      //   678: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   681: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   684: aload 7
      //   686: ldc -126
      //   688: new 83	org/apache/http/entity/mime/content/StringBody
      //   691: dup
      //   692: aload 30
      //   694: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   697: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   700: aload 7
      //   702: ldc -124
      //   704: new 83	org/apache/http/entity/mime/content/StringBody
      //   707: dup
      //   708: aload 31
      //   710: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   713: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   716: aload 7
      //   718: ldc -122
      //   720: new 83	org/apache/http/entity/mime/content/StringBody
      //   723: dup
      //   724: aload 32
      //   726: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   729: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   732: aload 7
      //   734: ldc -120
      //   736: new 83	org/apache/http/entity/mime/content/StringBody
      //   739: dup
      //   740: aload 33
      //   742: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   745: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   748: aload 7
      //   750: ldc -118
      //   752: new 83	org/apache/http/entity/mime/content/StringBody
      //   755: dup
      //   756: aload 34
      //   758: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   761: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   764: aload 7
      //   766: ldc -116
      //   768: new 83	org/apache/http/entity/mime/content/StringBody
      //   771: dup
      //   772: aload 35
      //   774: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   777: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   780: aload 7
      //   782: ldc -114
      //   784: new 83	org/apache/http/entity/mime/content/StringBody
      //   787: dup
      //   788: aload 36
      //   790: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   793: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   796: aload 7
      //   798: ldc -112
      //   800: new 83	org/apache/http/entity/mime/content/StringBody
      //   803: dup
      //   804: aload 37
      //   806: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   809: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   812: aload 7
      //   814: ldc -110
      //   816: new 83	org/apache/http/entity/mime/content/StringBody
      //   819: dup
      //   820: aload 38
      //   822: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   825: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   828: aload 7
      //   830: ldc -108
      //   832: new 83	org/apache/http/entity/mime/content/StringBody
      //   835: dup
      //   836: aload 4
      //   838: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   841: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   844: aload 7
      //   846: ldc -106
      //   848: new 83	org/apache/http/entity/mime/content/StringBody
      //   851: dup
      //   852: aload_1
      //   853: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   856: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   859: aload 7
      //   861: ldc -104
      //   863: new 83	org/apache/http/entity/mime/content/StringBody
      //   866: dup
      //   867: aload_3
      //   868: invokespecial 84	org/apache/http/entity/mime/content/StringBody:<init>	(Ljava/lang/String;)V
      //   871: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   874: new 154	java/util/ArrayList
      //   877: dup
      //   878: invokespecial 155	java/util/ArrayList:<init>	()V
      //   881: astore_3
      //   882: aload 8
      //   884: ldc -99
      //   886: invokevirtual 161	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
      //   889: astore_1
      //   890: getstatic 167	java/lang/System:out	Ljava/io/PrintStream;
      //   893: new 60	java/lang/StringBuilder
      //   896: dup
      //   897: invokespecial 61	java/lang/StringBuilder:<init>	()V
      //   900: ldc -87
      //   902: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   905: aload_1
      //   906: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   909: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   912: invokevirtual 177	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   915: aload_1
      //   916: arraylength
      //   917: ifle +59 -> 976
      //   920: getstatic 167	java/lang/System:out	Ljava/io/PrintStream;
      //   923: new 60	java/lang/StringBuilder
      //   926: dup
      //   927: invokespecial 61	java/lang/StringBuilder:<init>	()V
      //   930: ldc -77
      //   932: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   935: aload_1
      //   936: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   939: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   942: invokevirtual 177	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   945: getstatic 167	java/lang/System:out	Ljava/io/PrintStream;
      //   948: new 60	java/lang/StringBuilder
      //   951: dup
      //   952: invokespecial 61	java/lang/StringBuilder:<init>	()V
      //   955: ldc -75
      //   957: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   960: aload_1
      //   961: arraylength
      //   962: invokevirtual 184	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   965: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   968: invokevirtual 177	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   971: aload_1
      //   972: invokestatic 190	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
      //   975: astore_3
      //   976: aload_3
      //   977: invokeinterface 195 1 0
      //   982: ifle +278 -> 1260
      //   985: iconst_0
      //   986: istore_2
      //   987: iload_2
      //   988: aload_3
      //   989: invokeinterface 195 1 0
      //   994: if_icmpge +266 -> 1260
      //   997: aload_3
      //   998: iload_2
      //   999: invokeinterface 199 2 0
      //   1004: checkcast 31	java/lang/String
      //   1007: ldc -55
      //   1009: ldc -99
      //   1011: invokevirtual 205	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
      //   1014: astore 4
      //   1016: getstatic 167	java/lang/System:out	Ljava/io/PrintStream;
      //   1019: new 60	java/lang/StringBuilder
      //   1022: dup
      //   1023: invokespecial 61	java/lang/StringBuilder:<init>	()V
      //   1026: ldc -49
      //   1028: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1031: aload 4
      //   1033: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1036: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1039: invokevirtual 177	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   1042: aload 4
      //   1044: ldc -47
      //   1046: invokevirtual 213	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
      //   1049: ifne +23 -> 1072
      //   1052: aload 4
      //   1054: ldc -41
      //   1056: invokevirtual 213	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
      //   1059: ifne +13 -> 1072
      //   1062: aload 4
      //   1064: ldc -39
      //   1066: invokevirtual 213	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
      //   1069: ifeq +265 -> 1334
      //   1072: aload 4
      //   1074: ldc -47
      //   1076: invokevirtual 213	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
      //   1079: ifeq +86 -> 1165
      //   1082: aload 4
      //   1084: iconst_5
      //   1085: aload 4
      //   1087: invokevirtual 41	java/lang/String:length	()I
      //   1090: invokevirtual 221	java/lang/String:substring	(II)Ljava/lang/String;
      //   1093: astore_1
      //   1094: getstatic 167	java/lang/System:out	Ljava/io/PrintStream;
      //   1097: new 60	java/lang/StringBuilder
      //   1100: dup
      //   1101: invokespecial 61	java/lang/StringBuilder:<init>	()V
      //   1104: ldc -33
      //   1106: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1109: aload_1
      //   1110: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1113: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1116: invokevirtual 177	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   1119: new 225	org/apache/http/entity/mime/content/FileBody
      //   1122: dup
      //   1123: new 227	java/io/File
      //   1126: dup
      //   1127: aload_1
      //   1128: invokespecial 228	java/io/File:<init>	(Ljava/lang/String;)V
      //   1131: ldc -26
      //   1133: invokespecial 233	org/apache/http/entity/mime/content/FileBody:<init>	(Ljava/io/File;Ljava/lang/String;)V
      //   1136: astore_1
      //   1137: aload 7
      //   1139: new 60	java/lang/StringBuilder
      //   1142: dup
      //   1143: invokespecial 61	java/lang/StringBuilder:<init>	()V
      //   1146: ldc -21
      //   1148: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1151: iload_2
      //   1152: invokevirtual 184	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1155: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1158: aload_1
      //   1159: invokevirtual 88	org/apache/http/entity/mime/MultipartEntity:addPart	(Ljava/lang/String;Lorg/apache/http/entity/mime/content/ContentBody;)V
      //   1162: goto +172 -> 1334
      //   1165: aload 4
      //   1167: astore_1
      //   1168: aload 4
      //   1170: ldc -41
      //   1172: invokevirtual 213	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
      //   1175: ifeq -81 -> 1094
      //   1178: getstatic 241	android/os/Build$VERSION:SDK_INT	I
      //   1181: bipush 19
      //   1183: if_icmplt +19 -> 1202
      //   1186: aload_0
      //   1187: getfield 14	com/example/example75f1799f07eb/MyPhoneGapActivity$DirectoryPageUploadMultipleFiles:this$0	Lcom/example/example75f1799f07eb/MyPhoneGapActivity;
      //   1190: aload 4
      //   1192: invokestatic 247	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
      //   1195: invokevirtual 251	com/example/example75f1799f07eb/MyPhoneGapActivity:getRealPathFromURI_API19	(Landroid/net/Uri;)Ljava/lang/String;
      //   1198: astore_1
      //   1199: goto -105 -> 1094
      //   1202: aload_0
      //   1203: getfield 14	com/example/example75f1799f07eb/MyPhoneGapActivity$DirectoryPageUploadMultipleFiles:this$0	Lcom/example/example75f1799f07eb/MyPhoneGapActivity;
      //   1206: aload 4
      //   1208: invokestatic 247	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
      //   1211: invokevirtual 254	com/example/example75f1799f07eb/MyPhoneGapActivity:getRealPathFromURI	(Landroid/net/Uri;)Ljava/lang/String;
      //   1214: astore_1
      //   1215: goto -121 -> 1094
      //   1218: astore_1
      //   1219: aload_1
      //   1220: invokevirtual 257	java/lang/Exception:printStackTrace	()V
      //   1223: goto +111 -> 1334
      //   1226: astore_1
      //   1227: ldc_w 259
      //   1230: new 60	java/lang/StringBuilder
      //   1233: dup
      //   1234: invokespecial 61	java/lang/StringBuilder:<init>	()V
      //   1237: ldc_w 261
      //   1240: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1243: aload_1
      //   1244: invokevirtual 264	java/lang/Exception:getMessage	()Ljava/lang/String;
      //   1247: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1250: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1253: aload_1
      //   1254: invokestatic 270	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   1257: pop
      //   1258: aconst_null
      //   1259: areturn
      //   1260: aload 6
      //   1262: aload 7
      //   1264: invokevirtual 274	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
      //   1267: aload 5
      //   1269: aload 6
      //   1271: invokeinterface 280 2 0
      //   1276: invokeinterface 286 1 0
      //   1281: invokestatic 291	org/apache/http/util/EntityUtils:toString	(Lorg/apache/http/HttpEntity;)Ljava/lang/String;
      //   1284: astore_1
      //   1285: aload_1
      //   1286: putstatic 294	com/example/example75f1799f07eb/MyPhoneGapActivity:status	Ljava/lang/String;
      //   1289: ldc_w 296
      //   1292: aload_1
      //   1293: invokestatic 299	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
      //   1296: pop
      //   1297: getstatic 167	java/lang/System:out	Ljava/io/PrintStream;
      //   1300: new 60	java/lang/StringBuilder
      //   1303: dup
      //   1304: invokespecial 61	java/lang/StringBuilder:<init>	()V
      //   1307: ldc_w 301
      //   1310: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1313: aload 6
      //   1315: invokevirtual 304	java/lang/Object:toString	()Ljava/lang/String;
      //   1318: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1321: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1324: invokevirtual 177	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   1327: goto -69 -> 1258
      //   1330: astore_1
      //   1331: goto -457 -> 874
      //   1334: iload_2
      //   1335: iconst_1
      //   1336: iadd
      //   1337: istore_2
      //   1338: goto -351 -> 987
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	1341	0	this	DirectoryPageUploadMultipleFiles
      //   0	1341	1	paramVarArgs	String[]
      //   986	352	2	i	int
      //   190	808	3	localObject1	Object
      //   195	1012	4	localObject2	Object
      //   178	1090	5	localObject3	Object
      //   302	1012	6	localObject4	Object
      //   346	917	7	localMultipartEntity	MultipartEntity
      //   184	699	8	str1	String
      //   3	354	9	str2	String
      //   8	365	10	str3	String
      //   13	376	11	str4	String
      //   18	387	12	str5	String
      //   23	398	13	str6	String
      //   28	409	14	str7	String
      //   34	419	15	str8	String
      //   40	429	16	str9	String
      //   46	439	17	str10	String
      //   52	449	18	str11	String
      //   58	459	19	str12	String
      //   64	469	20	str13	String
      //   70	479	21	str14	String
      //   76	489	22	str15	String
      //   82	499	23	str16	String
      //   88	509	24	str17	String
      //   94	519	25	str18	String
      //   100	529	26	str19	String
      //   106	539	27	str20	String
      //   112	549	28	str21	String
      //   118	559	29	str22	String
      //   124	569	30	str23	String
      //   130	579	31	str24	String
      //   136	589	32	str25	String
      //   142	599	33	str26	String
      //   148	609	34	str27	String
      //   154	619	35	str28	String
      //   160	629	36	str29	String
      //   166	639	37	str30	String
      //   172	649	38	str31	String
      // Exception table:
      //   from	to	target	type
      //   1042	1072	1218	java/lang/Exception
      //   1072	1094	1218	java/lang/Exception
      //   1094	1162	1218	java/lang/Exception
      //   1168	1199	1218	java/lang/Exception
      //   1202	1215	1218	java/lang/Exception
      //   290	844	1226	java/lang/Exception
      //   874	915	1226	java/lang/Exception
      //   915	976	1226	java/lang/Exception
      //   976	985	1226	java/lang/Exception
      //   987	1042	1226	java/lang/Exception
      //   1219	1223	1226	java/lang/Exception
      //   1260	1327	1226	java/lang/Exception
      //   844	874	1330	java/lang/Exception
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      MyPhoneGapActivity.cwv.loadUrl("javascript:successFormSubmitDir();");
      super.onPostExecute(paramVoid);
    }
    
    protected void onPreExecute()
    {
      System.out.println("directory listing uploading");
      super.onPreExecute();
    }
  }
  
  public class DownloadFile
    extends AsyncTask<String, Void, String>
  {
    public DownloadFile() {}
    
    protected String doInBackground(String... paramVarArgs)
    {
      try
      {
        Object localObject1 = new URL(URLEncoder.encode(paramVarArgs[0].toString().trim(), "UTF-8").replace("%2F", "/").replace("%3A", ":"));
        paramVarArgs = paramVarArgs[1].toString().trim();
        Object localObject2 = ((URL)localObject1).openConnection();
        ((URLConnection)localObject2).connect();
        int i = ((URLConnection)localObject2).getContentLength();
        localObject1 = new BufferedInputStream(((URL)localObject1).openStream());
        paramVarArgs = new FileOutputStream(new File(paramVarArgs, "appypie.xml"));
        localObject2 = new byte['Ѐ'];
        long l = 0L;
        for (;;)
        {
          int j = ((InputStream)localObject1).read((byte[])localObject2);
          if (j == -1) {
            break;
          }
          l += j;
          MyPhoneGapActivity.this.setProgress((int)(100L * l / i));
          paramVarArgs.write((byte[])localObject2, 0, j);
        }
        paramVarArgs.flush();
        paramVarArgs.close();
        ((InputStream)localObject1).close();
      }
      catch (Exception paramVarArgs) {}
      return null;
    }
    
    protected void onPostExecute(String paramString)
    {
      System.out.println("Application data updating..");
      try
      {
        MyPhoneGapActivity.this.getAppId();
        return;
      }
      catch (Exception paramString) {}
    }
  }
  
  private class RegisterGCMServiceTask
    extends AsyncTask<String, Void, String>
  {
    private RegisterGCMServiceTask() {}
    
    protected String doInBackground(final String... paramVarArgs)
    {
      Log.e("", "RegisterGCMServiceTask starts now....." + new Date().getSeconds() / 1000);
      MyPhoneGapActivity.this.registerReceiver(MyPhoneGapActivity.this.mHandleMessageReceiver, new IntentFilter("com.example.pushnotification.DISPLAY_MESSAGE"));
      paramVarArgs = GCMRegistrar.getRegistrationId(MyPhoneGapActivity.this.getApplicationContext());
      MyApplicationName.GCM_ID = paramVarArgs;
      MyApplicationName.APP_ID = MyPhoneGapActivity.AppId;
      System.out.println("GCM regID=" + paramVarArgs);
      if (paramVarArgs.equals(""))
      {
        GCMRegistrar.register(MyPhoneGapActivity.this.getApplicationContext(), new String[] { "755323621607" });
        return null;
      }
      if (GCMRegistrar.isRegisteredOnServer(MyPhoneGapActivity.this.getApplicationContext()))
      {
        System.out.println("Already registered with GCM");
        return null;
      }
      final Context localContext = MyPhoneGapActivity.this.getApplicationContext();
      MyPhoneGapActivity.this.mRegisterTask = new AsyncTask()
      {
        protected Void doInBackground(Void... paramAnonymousVarArgs)
        {
          ServerUtilities.register(localContext, MyPhoneGapActivity.AppId, paramVarArgs, MyPhoneGapActivity.lat, MyPhoneGapActivity.lng);
          return null;
        }
        
        protected void onPostExecute(Void paramAnonymousVoid)
        {
          MyPhoneGapActivity.this.mRegisterTask = null;
        }
      };
      MyPhoneGapActivity.this.mRegisterTask.execute(new Void[] { null, null, null });
      return null;
    }
  }
  
  public class RegisterPageUploadMultipleFiles
    extends AsyncTask<String, Void, Void>
  {
    public RegisterPageUploadMultipleFiles() {}
    
    protected Void doInBackground(String... paramVarArgs)
    {
      String str1 = paramVarArgs[0];
      String str2 = paramVarArgs[1];
      String str3 = paramVarArgs[2];
      String str4 = paramVarArgs[3];
      String str5 = paramVarArgs[4];
      Object localObject1 = paramVarArgs[5];
      Object localObject2 = paramVarArgs[6];
      String str6 = paramVarArgs[7];
      String str7 = paramVarArgs[8];
      String str8 = paramVarArgs[9];
      paramVarArgs = paramVarArgs[10];
      Log.e("appId>>>>", str1);
      Log.e("name>>>>", str2);
      Log.e("emailId>>>>", str3);
      Log.e("password>>>>", str4);
      Log.e("contact>>>>", str5);
      Log.e("totCountFile>>>>", (String)localObject1);
      Log.e("path>>>>", (String)localObject2);
      Log.e("formDatas>>>>", str6);
      Log.e("formLabels>>>>", str7);
      Log.e("elementType>>>>", str8);
      Log.e("autoStatus>>>>", paramVarArgs);
      localObject1 = ((String)localObject2).split(",");
      try
      {
        localObject2 = new DefaultHttpClient();
        localHttpPost = new HttpPost("http://" + MyPhoneGapActivity.appDomainValue + "/appuser/user-registration-save");
        localMultipartEntity = new MultipartEntity();
        i = 0;
      }
      catch (Exception paramVarArgs)
      {
        for (;;)
        {
          HttpPost localHttpPost;
          MultipartEntity localMultipartEntity;
          int i;
          FileBody localFileBody;
          Log.e("Debug", "error: " + paramVarArgs.getMessage(), paramVarArgs);
          continue;
          i += 1;
        }
      }
      if (i < localObject1.length)
      {
        Log.e("Single Files>>>>", localObject1[i]);
        if ((localObject1[i] != null) && (localObject1[i].length() > 1))
        {
          localFileBody = new FileBody(new File(localObject1[i]));
          localMultipartEntity.addPart("uploadedfile" + i, localFileBody);
          System.out.println("dheeraj test" + localObject1[i]);
        }
      }
      else
      {
        localMultipartEntity.addPart("appId", new StringBody(str1));
        localMultipartEntity.addPart("name", new StringBody(str2));
        localMultipartEntity.addPart("email", new StringBody(str3));
        localMultipartEntity.addPart("password", new StringBody(str4));
        localMultipartEntity.addPart("phone", new StringBody(str5));
        localMultipartEntity.addPart("formLabel", new StringBody(str7));
        localMultipartEntity.addPart("formData", new StringBody(str6));
        localMultipartEntity.addPart("formFields", new StringBody(str8));
        localMultipartEntity.addPart("authoStatus", new StringBody(paramVarArgs));
        localMultipartEntity.addPart("verifyStatus", new StringBody("1"));
        localHttpPost.setEntity(localMultipartEntity);
        paramVarArgs = EntityUtils.toString(((HttpClient)localObject2).execute(localHttpPost).getEntity());
        MyPhoneGapActivity.status = paramVarArgs;
        Log.e("Final Response", paramVarArgs);
        return null;
      }
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      MyPhoneGapActivity.cwv.loadUrl("javascript:successFileuploaded('" + MyPhoneGapActivity.status + "');");
      super.onPostExecute(paramVoid);
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
    }
  }
  
  public class SaveSettingProfileDirecotory
    extends AsyncTask<String, Void, Void>
  {
    String imagePath;
    
    public SaveSettingProfileDirecotory() {}
    
    protected Void doInBackground(String... paramVarArgs)
    {
      String str1 = paramVarArgs[0];
      String str2 = paramVarArgs[1];
      String str3 = paramVarArgs[2];
      String str4 = paramVarArgs[3];
      this.imagePath = paramVarArgs[4];
      for (;;)
      {
        try
        {
          paramVarArgs = new DefaultHttpClient();
          localHttpPost = new HttpPost("http://" + MyPhoneGapActivity.appDomainValue + "/directory/directory-setting");
          localMultipartEntity = new MultipartEntity();
          localMultipartEntity.addPart("name", new StringBody(str2));
          localMultipartEntity.addPart("email", new StringBody(str4));
          localMultipartEntity.addPart("address", new StringBody(str3));
          localMultipartEntity.addPart("appId", new StringBody(str1));
        }
        catch (Exception paramVarArgs)
        {
          HttpPost localHttpPost;
          MultipartEntity localMultipartEntity;
          Log.e(" Final Response  Debug", "error: " + paramVarArgs.getMessage(), paramVarArgs);
          continue;
        }
        try
        {
          if ((!this.imagePath.contains("file")) && (!this.imagePath.contains("content://")) && (!this.imagePath.contains("storage/emulated"))) {
            continue;
          }
          if (!this.imagePath.contains("file")) {
            continue;
          }
          this.imagePath = this.imagePath.substring(5, this.imagePath.length());
          System.out.println("mediaData2>>>>>>>>>>" + this.imagePath);
          localMultipartEntity.addPart("imageupload", new FileBody(new File(this.imagePath), "image/jpeg"));
        }
        catch (Exception localException)
        {
          continue;
        }
        localHttpPost.setEntity(localMultipartEntity);
        paramVarArgs = EntityUtils.toString(paramVarArgs.execute(localHttpPost).getEntity());
        MyPhoneGapActivity.status = paramVarArgs;
        Log.e("Final Response  ", paramVarArgs + "  status " + MyPhoneGapActivity.status);
        return null;
        if (this.imagePath.contains("content://"))
        {
          this.imagePath = MyPhoneGapActivity.this.getRealPathFromURI(Uri.parse(this.imagePath));
        }
        else
        {
          this.imagePath = this.imagePath;
          continue;
          localMultipartEntity.addPart("imageupload", new StringBody(""));
        }
      }
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      if ((MyPhoneGapActivity.status.contains(".jpg")) && (MyPhoneGapActivity.status.contains("http://"))) {
        MyPhoneGapActivity.cwv.loadUrl("javascript:successFormSaveProfile('success','" + MyPhoneGapActivity.status + "');");
      }
      for (;;)
      {
        super.onPostExecute(paramVoid);
        return;
        if (this.imagePath.length() <= 10) {
          MyPhoneGapActivity.cwv.loadUrl("javascript:successFormSaveProfile('local_imageURI_exist'" + MyPhoneGapActivity.status + "');");
        } else {
          MyPhoneGapActivity.cwv.loadUrl("javascript:successFormSaveProfile('Failed'" + MyPhoneGapActivity.status + "');");
        }
      }
    }
    
    protected void onPreExecute()
    {
      System.out.println("directory listing uploading");
      super.onPreExecute();
    }
  }
  
  public class SaverequestserviceDirecotory
    extends AsyncTask<String, Void, Void>
  {
    public SaverequestserviceDirecotory() {}
    
    protected Void doInBackground(String... paramVarArgs)
    {
      String str2 = paramVarArgs[0];
      String str3 = paramVarArgs[1];
      String str4 = paramVarArgs[2];
      String str5 = paramVarArgs[3];
      String str6 = paramVarArgs[4];
      String str7 = paramVarArgs[5];
      String str8 = paramVarArgs[6];
      String str9 = paramVarArgs[7];
      String str10 = paramVarArgs[8];
      String str11 = paramVarArgs[9];
      String str1 = paramVarArgs[10];
      str1 = paramVarArgs[11];
      String str12 = paramVarArgs[12];
      paramVarArgs = paramVarArgs[13];
      for (;;)
      {
        try
        {
          localDefaultHttpClient = new DefaultHttpClient();
          localHttpPost = new HttpPost("http://" + MyPhoneGapActivity.appDomainValue + "/directory/insert-list-form-bulder");
          localMultipartEntity = new MultipartEntity();
          localMultipartEntity.addPart("appId", new StringBody(str2));
          localMultipartEntity.addPart("ownerEmailId", new StringBody(str3));
          localMultipartEntity.addPart("pageId", new StringBody(str4));
          localMultipartEntity.addPart("categoryId", new StringBody(str5));
          localMultipartEntity.addPart("dirPageId", new StringBody(str6));
          localMultipartEntity.addPart("name", new StringBody(str7));
          localMultipartEntity.addPart("phone", new StringBody(str8));
          localMultipartEntity.addPart("address", new StringBody(str9));
          localMultipartEntity.addPart("budget", new StringBody(str10));
          localMultipartEntity.addPart("requirement", new StringBody(str11));
          localMultipartEntity.addPart("appName", new StringBody(str12));
          localMultipartEntity.addPart("heading", new StringBody(paramVarArgs));
        }
        catch (Exception paramVarArgs)
        {
          DefaultHttpClient localDefaultHttpClient;
          HttpPost localHttpPost;
          MultipartEntity localMultipartEntity;
          Log.e(" Final Response  Debug", "error: " + paramVarArgs.getMessage(), paramVarArgs);
          continue;
        }
        try
        {
          if ((!str1.contains("file")) && (!str1.contains("content://")) && (!str1.contains("storage/emulated"))) {
            continue;
          }
          if (!str1.contains("file")) {
            continue;
          }
          paramVarArgs = str1.substring(5, str1.length());
          System.out.println("mediaData2>>>>>>>>>>" + paramVarArgs);
          localMultipartEntity.addPart("uploadedfile0", new FileBody(new File(paramVarArgs), "image/jpeg"));
        }
        catch (Exception paramVarArgs)
        {
          continue;
        }
        localHttpPost.setEntity(localMultipartEntity);
        paramVarArgs = EntityUtils.toString(localDefaultHttpClient.execute(localHttpPost).getEntity());
        MyPhoneGapActivity.status = paramVarArgs;
        Log.e("Final Response  ", paramVarArgs + "  status " + MyPhoneGapActivity.status);
        return null;
        paramVarArgs = str1;
        if (str1.contains("content://"))
        {
          paramVarArgs = MyPhoneGapActivity.this.getRealPathFromURI(Uri.parse(str1));
          continue;
          localMultipartEntity.addPart("uploadedfile0", new StringBody(""));
        }
      }
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      Toast.makeText(MyPhoneGapActivity.this.getApplicationContext(), MyPhoneGapActivity.status, 1).show();
      Log.e(" Final Response  Debug", "  onPostExecute " + MyPhoneGapActivity.status);
      MyPhoneGapActivity.cwv.loadUrl("javascript:sendSuccessServiceRequest('" + MyPhoneGapActivity.status + "')");
      super.onPostExecute(paramVoid);
    }
    
    protected void onPreExecute()
    {
      System.out.println("directory listing uploading");
      super.onPreExecute();
    }
  }
  
  public class UploadMultipleFiles
    extends AsyncTask<String, Void, Void>
  {
    public UploadMultipleFiles() {}
    
    protected Void doInBackground(String... paramVarArgs)
    {
      String str1 = paramVarArgs[0];
      String str2 = paramVarArgs[1];
      String str3 = paramVarArgs[2];
      String str4 = paramVarArgs[3];
      String str5 = paramVarArgs[4];
      String str6 = paramVarArgs[5];
      String str7 = paramVarArgs[6];
      String str8 = paramVarArgs[7];
      Object localObject = paramVarArgs[8];
      String str9 = paramVarArgs[9];
      paramVarArgs = paramVarArgs[10];
      Log.e("appId>>>>", str1);
      Log.e("formPageId>>>>", str2);
      Log.e("emailId>>>>", str3);
      Log.e("subject>>>>", str4);
      Log.e("totalFile>>>>", str5);
      Log.e("formName>>>>", str6);
      Log.e("formData>>>>", str7);
      Log.e("formLabel>>>>", str8);
      Log.e("ExactPath>>>>", (String)localObject);
      Log.e("formElementsType>>>>", str9);
      Log.e("formmailHeadingText>>>>", paramVarArgs);
      localObject = ((String)localObject).split(",");
      try
      {
        DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
        HttpPost localHttpPost = new HttpPost("http://" + MyPhoneGapActivity.appDomainValue + "/formbuilder/send-custom-mail-template");
        MultipartEntity localMultipartEntity = new MultipartEntity();
        int i = 0;
        while (i < localObject.length)
        {
          Log.e("Single Files>>>>", localObject[i]);
          FileBody localFileBody = new FileBody(new File(localObject[i]));
          localMultipartEntity.addPart("uploadedfile" + i, localFileBody);
          i += 1;
        }
        localMultipartEntity.addPart("appId", new StringBody(str1));
        localMultipartEntity.addPart("formPageId", new StringBody(str2));
        localMultipartEntity.addPart("emailId", new StringBody(str3));
        localMultipartEntity.addPart("subject", new StringBody(str4));
        localMultipartEntity.addPart("totalFile", new StringBody(str5));
        localMultipartEntity.addPart("formName", new StringBody(str6));
        localMultipartEntity.addPart("formData", new StringBody(str7));
        localMultipartEntity.addPart("formLabel", new StringBody(str8));
        localMultipartEntity.addPart("formFields", new StringBody(str9));
        localMultipartEntity.addPart("mailHeadingText", new StringBody(paramVarArgs));
        localMultipartEntity.addPart("appName", new StringBody(MyPhoneGapActivity.appName));
        localHttpPost.setEntity(localMultipartEntity);
        Log.e("Final Response", EntityUtils.toString(localDefaultHttpClient.execute(localHttpPost).getEntity()));
      }
      catch (Exception paramVarArgs)
      {
        for (;;)
        {
          Log.e("Debug", "error: " + paramVarArgs.getMessage(), paramVarArgs);
        }
      }
      return null;
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      MyPhoneGapActivity.cwv.loadUrl("javascript:sendOnServerSuccess();");
      super.onPostExecute(paramVoid);
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
    }
  }
  
  class myAsyncTaskShareIMG
    extends AsyncTask<String, Void, Void>
  {
    public ProgressDialog dialog = new ProgressDialog(MyPhoneGapActivity.this);
    
    myAsyncTaskShareIMG()
    {
      this.dialog.setMessage("donloading second img");
      this.dialog.setCancelable(true);
      this.dialog.setIndeterminate(true);
    }
    
    protected Void doInBackground(String... paramVarArgs)
    {
      paramVarArgs = paramVarArgs[0];
      try
      {
        Object localObject = (HttpURLConnection)new URL(paramVarArgs).openConnection();
        ((HttpURLConnection)localObject).setRequestMethod("GET");
        ((HttpURLConnection)localObject).setDoOutput(true);
        ((HttpURLConnection)localObject).connect();
        MyPhoneGapActivity.this.SDCardRoot = new File(Environment.getExternalStorageDirectory(), MyPhoneGapActivity.this.foldername + MyPhoneGapActivity.appName);
        if (!MyPhoneGapActivity.this.SDCardRoot.exists())
        {
          MyPhoneGapActivity.this.SDCardRoot.mkdirs();
          System.out.println("create folder  www.appypie.com.image");
        }
        paramVarArgs = new FileOutputStream(new File(MyPhoneGapActivity.this.SDCardRoot, MyPhoneGapActivity.this.SDDirectoryShareIMG));
        InputStream localInputStream = ((HttpURLConnection)localObject).getInputStream();
        ((HttpURLConnection)localObject).getContentLength();
        int i = 0;
        localObject = new byte['Ѐ'];
        for (;;)
        {
          int j = localInputStream.read((byte[])localObject);
          if (j <= 0) {
            break;
          }
          paramVarArgs.write((byte[])localObject, 0, j);
          i += j;
        }
        paramVarArgs.close();
      }
      catch (Exception paramVarArgs)
      {
        for (;;)
        {
          paramVarArgs.printStackTrace();
        }
      }
      return null;
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      this.dialog.dismiss();
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
    }
  }
  
  class myAsyncTask_BGImg
    extends AsyncTask<String, Void, Void>
  {
    public ProgressDialog dialog = new ProgressDialog(MyPhoneGapActivity.this);
    
    myAsyncTask_BGImg()
    {
      this.dialog.setMessage("donloading second img");
      this.dialog.setCancelable(true);
      this.dialog.setIndeterminate(true);
    }
    
    protected Void doInBackground(String... paramVarArgs)
    {
      String str = paramVarArgs[0];
      paramVarArgs = paramVarArgs[1];
      System.out.println("ImgURl_Port  img1 " + str + " , img2  " + paramVarArgs);
      MyPhoneGapActivity.this.DonlaodBGImgFromURL("0", str);
      System.out.println("startdonload second img");
      MyPhoneGapActivity.this.DonlaodBGImgFromURL("1", paramVarArgs);
      return null;
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      this.dialog.dismiss();
      MyPhoneGapActivity.editor = MyPhoneGapActivity.sharedpreferences.edit();
      MyPhoneGapActivity.this.SDCardRoot = new File(Environment.getExternalStorageDirectory(), MyPhoneGapActivity.this.foldername + MyPhoneGapActivity.appName);
      MyPhoneGapActivity.this.file = new File(MyPhoneGapActivity.this.SDCardRoot, "appbg_port_img.jpg");
      MyPhoneGapActivity.this.filee = new File(MyPhoneGapActivity.this.SDCardRoot, "appbg_land_img.jpg");
      if (MyPhoneGapActivity.this.file.exists()) {
        MyPhoneGapActivity.editor.putString("AppImgURl_Port", MyPhoneGapActivity.this.AppImgURl_Port);
      }
      if (MyPhoneGapActivity.this.filee.exists()) {
        MyPhoneGapActivity.editor.putString("AppImgURl_Land", MyPhoneGapActivity.this.AppImgURl_Land);
      }
      MyPhoneGapActivity.editor.apply();
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
    }
  }
  
  class myAsyncTask_Port
    extends AsyncTask<String, Void, Void>
  {
    public ProgressDialog dialog = new ProgressDialog(MyPhoneGapActivity.this);
    
    myAsyncTask_Port()
    {
      this.dialog.setMessage("donloading second img");
      this.dialog.setCancelable(true);
      this.dialog.setIndeterminate(true);
    }
    
    protected Void doInBackground(String... paramVarArgs)
    {
      String str = paramVarArgs[0];
      paramVarArgs = paramVarArgs[1];
      System.out.println("ImgURl_Port  img1 " + str + " , img2  " + paramVarArgs);
      MyPhoneGapActivity.this.ImgName = "0";
      MyPhoneGapActivity.this.DonlaodImgFromURL("0", str);
      MyPhoneGapActivity.this.ImgName = "1";
      System.out.println("startdonload second img");
      MyPhoneGapActivity.this.DonlaodImgFromURL("1", paramVarArgs);
      return null;
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      this.dialog.dismiss();
      MyPhoneGapActivity.editor = MyPhoneGapActivity.sharedpreferences.edit();
      MyPhoneGapActivity.this.SDCardRoot = new File(Environment.getExternalStorageDirectory(), MyPhoneGapActivity.this.foldername + MyPhoneGapActivity.appName);
      MyPhoneGapActivity.this.file = new File(MyPhoneGapActivity.this.SDCardRoot, "header_port_img.jpg");
      if (MyPhoneGapActivity.this.file.exists()) {
        MyPhoneGapActivity.editor.putString("ImgURl_Port", MyPhoneGapActivity.this.ImgURl_Port);
      }
      MyPhoneGapActivity.this.file = new File(MyPhoneGapActivity.this.SDCardRoot, "header_land_img.jpg");
      if (MyPhoneGapActivity.this.file.exists()) {
        MyPhoneGapActivity.editor.putString("ImgURl_Land", MyPhoneGapActivity.this.ImgURl_Land);
      }
      MyPhoneGapActivity.editor.apply();
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\MyPhoneGapActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */