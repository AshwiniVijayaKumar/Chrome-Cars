package com.example.example75f1799f07eb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

@SuppressLint({"NewApi"})
public class SocialShare
  extends Activity
  implements View.OnClickListener
{
  private static final String APP_ID = "854586857929405";
  private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
  private static final int CAMERA_PIC_REQUEST = 1;
  private static final String EXPIRES = "expires_in";
  private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";
  private static final String KEY = "facebook-credentials";
  public static final int MEDIA_TYPE_IMAGE = 1;
  private static final String[] PERMISSIONS;
  static String PREFERENCE_NAME;
  static final String PREF_KEY_OAUTH_SECRET = "oauth_token_secret";
  static final String PREF_KEY_OAUTH_TOKEN = "oauth_token";
  static final String PREF_KEY_TWITTER_LOGIN = "isTwitterLogedIn";
  private static final String TOKEN = "access_token";
  static final String TWITTER_CALLBACK_URL = "oauth://appypie";
  static String TWITTER_CONSUMER_KEY = "bUy4foCoqam2nxfKfwT4UQ";
  static String TWITTER_CONSUMER_SECRET = "7aZt2r6U7L2RQpRvNdAU5PlHHKRfFTbrebg6ak6ue6Q";
  static final String URL_TWITTER_AUTH = "auth_url";
  static final String URL_TWITTER_OAUTH_TOKEN = "oauth_token";
  static final String URL_TWITTER_OAUTH_VERIFIER = "oauth_verifier";
  private static SharedPreferences mSharedPreferences;
  private static RequestToken requestToken;
  private static Twitter twitter;
  String MyPREFERENCES = "MyPrefs";
  AlertDialogManager alert = new AlertDialogManager();
  Button cameraButton;
  private ConnectionDetector cd;
  File filePath;
  private Uri fileUri;
  FileInputStream fis;
  ImageView imageBackToHome;
  Button mBtnPostStatus;
  Button mBtnTwitter;
  private ImageView mImage;
  Button mailButton;
  private String messageToPost;
  ProgressDialog pDialog;
  SharedPreferences sharedpreferences;
  TextView textHeader;
  Bitmap thumbnail;
  private String typeLanguage;
  Button whatsappButton;
  
  static
  {
    PREFERENCE_NAME = "twitter_oauth";
    PERMISSIONS = new String[] { "publish_actions" };
    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
  }
  
  private static File getOutputMediaFile(int paramInt)
  {
    File localFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Hello Camera");
    if ((!localFile.exists()) && (!localFile.mkdirs())) {
      Log.d("Hello Camera", "Oops! Failed create Hello Camera directory");
    }
    String str;
    do
    {
      return null;
      str = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
    } while (paramInt != 1);
    return new File(localFile.getPath() + File.separator + "IMG_" + str + ".jpg");
  }
  
  private boolean isTwitterLoggedInAlready()
  {
    return mSharedPreferences.getBoolean("isTwitterLogedIn", false);
  }
  
  private void loginToTwitter()
  {
    if (!isTwitterLoggedInAlready())
    {
      ConfigurationBuilder localConfigurationBuilder = new ConfigurationBuilder();
      localConfigurationBuilder.setOAuthConsumerKey(TWITTER_CONSUMER_KEY);
      localConfigurationBuilder.setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET);
      twitter = new TwitterFactory(localConfigurationBuilder.build()).getInstance();
      try
      {
        requestToken = twitter.getOAuthRequestToken("oauth://appypie");
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(requestToken.getAuthenticationURL())));
        return;
      }
      catch (TwitterException localTwitterException)
      {
        localTwitterException.printStackTrace();
        return;
      }
    }
    Toast.makeText(getApplicationContext(), "Already Logged into twitter", 1).show();
    String str = "Shared Via " + getApplicationName() + "Mobile App";
    new updateTwitterStatus().execute(new String[] { str + " " + new Date().toLocaleString() });
  }
  
  private void logoutFromTwitter()
  {
    SharedPreferences.Editor localEditor = mSharedPreferences.edit();
    localEditor.remove("oauth_token");
    localEditor.remove("oauth_token_secret");
    localEditor.remove("isTwitterLogedIn");
    localEditor.commit();
  }
  
  private void previewCapturedImage()
  {
    try
    {
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localOptions.inSampleSize = 8;
      this.thumbnail = BitmapFactory.decodeFile(this.fileUri.getPath(), localOptions);
      this.mImage.setImageBitmap(this.thumbnail);
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      localNullPointerException.printStackTrace();
    }
  }
  
  private void sendEmail()
  {
    try
    {
      String str = "Shared Via " + getApplicationName() + "Mobile App";
      Uri localUri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), this.thumbnail, "title", null));
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setFlags(268435456);
      localIntent.putExtra("android.intent.extra.STREAM", localUri);
      localIntent.setType("image/png");
      localIntent.putExtra("android.intent.extra.TEXT", str);
      startActivity(Intent.createChooser(localIntent, "Email:"));
      return;
    }
    catch (Exception localException)
    {
      System.out.println("Exception" + localException.getMessage());
    }
  }
  
  private void sendImageToWhatsApp()
  {
    Object localObject = new Intent("android.intent.action.SEND");
    if (isPackageExisted("com.whatsapp"))
    {
      String str = "Shared Via " + getApplicationName() + "Mobile App";
      ((Intent)localObject).setPackage("com.whatsapp");
      ((Intent)localObject).putExtra("android.intent.extra.STREAM", Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), this.thumbnail, "title", null)));
      ((Intent)localObject).putExtra("android.intent.extra.TEXT", str);
      ((Intent)localObject).setType("image/*");
      startActivity((Intent)localObject);
      return;
    }
    localObject = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject).setTitle("WhatsApp Not Installed");
    ((AlertDialog.Builder)localObject).setMessage("Please install whatsapp for sharing image").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    ((AlertDialog.Builder)localObject).create().show();
  }
  
  private void showToast(String paramString)
  {
    Toast.makeText(getApplicationContext(), paramString, 0).show();
  }
  
  public String getApplicationName()
  {
    Log.e("", " inside getApplicationName starts now....." + new Date().getSeconds() / 1000);
    return getString(getApplicationInfo().labelRes);
  }
  
  public Uri getOutputMediaFileUri(int paramInt)
  {
    return Uri.fromFile(getOutputMediaFile(paramInt));
  }
  
  public boolean isPackageExisted(String paramString)
  {
    PackageManager localPackageManager = getPackageManager();
    try
    {
      localPackageManager.getPackageInfo(paramString, 128);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    System.out.println("onActivityResult started");
    if (paramInt1 == 100)
    {
      if (paramInt2 == -1)
      {
        this.mBtnPostStatus.setVisibility(0);
        this.mBtnTwitter.setVisibility(0);
        this.mailButton.setVisibility(0);
        this.whatsappButton.setVisibility(0);
        previewCapturedImage();
        return;
      }
      if (paramInt2 == 0)
      {
        Toast.makeText(getApplicationContext(), "User cancelled image capture", 0).show();
        return;
      }
      Toast.makeText(getApplicationContext(), "Sorry! Failed to capture image", 0).show();
      return;
    }
    System.out.println("onActivityResult started for FB OUTH");
  }
  
  public void onClick(View paramView)
  {
    if ((paramView == this.textHeader) || (paramView == this.imageBackToHome)) {
      finish();
    }
    do
    {
      do
      {
        return;
        if (paramView == this.mBtnTwitter)
        {
          if (this.mImage.getDrawable() == null)
          {
            Toast.makeText(this, "Please take snap by using camera button before posting it to Twitter", 1).show();
            return;
          }
          loginToTwitter();
          return;
        }
        if (paramView != this.mBtnPostStatus) {
          break;
        }
      } while (this.mImage.getDrawable() != null);
      Toast.makeText(this, "Please take snap by using camera button before posting it to Facebook ", 1).show();
      return;
      if (paramView == this.mailButton)
      {
        if (this.mImage.getDrawable() == null)
        {
          Toast.makeText(this, "Please take snap by using camera button before Mailing", 1).show();
          return;
        }
        sendEmail();
        return;
      }
    } while (paramView != this.whatsappButton);
    if (this.mImage.getDrawable() == null)
    {
      Toast.makeText(this, "Please take snap by using camera button before Mailing", 1).show();
      return;
    }
    sendImageToWhatsApp();
  }
  
  @SuppressLint({"NewApi"})
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    this.sharedpreferences = getSharedPreferences(this.MyPREFERENCES, 0);
    this.typeLanguage = this.sharedpreferences.getString("appLanguage", "");
    System.out.println("===== type language is in SocialShare class : " + this.typeLanguage);
    if (this.typeLanguage.equalsIgnoreCase("en")) {
      setContentView(2130903150);
    }
    for (;;)
    {
      setRequestedOrientation(1);
      this.cd = new ConnectionDetector(getApplicationContext());
      if (this.cd.isConnectingToInternet()) {
        break;
      }
      this.alert.showAlertDialog(this, "Internet Connection Error", "Please connect to working Internet connection", 0);
      return;
      setContentView(2130903151);
    }
    if ((TWITTER_CONSUMER_KEY.trim().length() == 0) || (TWITTER_CONSUMER_SECRET.trim().length() == 0))
    {
      this.alert.showAlertDialog(this, "Twitter oAuth tokens", "Please set your twitter oauth tokens first!", 0);
      return;
    }
    mSharedPreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
    this.textHeader = ((TextView)findViewById(2131558520));
    this.textHeader.setText(getResources().getString(2131230720));
    this.textHeader.setOnClickListener(this);
    this.imageBackToHome = ((ImageView)findViewById(2131558521));
    this.imageBackToHome.setOnClickListener(this);
    this.mBtnTwitter = ((Button)findViewById(2131558780));
    this.mBtnTwitter.setOnClickListener(this);
    this.mailButton = ((Button)findViewById(2131558781));
    this.mailButton.setOnClickListener(this);
    this.whatsappButton = ((Button)findViewById(2131558782));
    this.whatsappButton.setOnClickListener(this);
    this.filePath = new File(Environment.getExternalStorageDirectory().toString());
    paramBundle = this.filePath.listFiles();
    int j = paramBundle.length;
    int i = 0;
    for (;;)
    {
      SharedPreferences.Editor localEditor;
      if (i < j)
      {
        localEditor = paramBundle[i];
        if (localEditor.getName().equals("appypie.png")) {
          this.filePath = localEditor;
        }
      }
      else if ((!this.filePath.exists()) || (!isTwitterLoggedInAlready()))
      {
        paramBundle = getIntent().getData();
        if ((paramBundle != null) && (paramBundle.toString().startsWith("oauth://appypie"))) {
          paramBundle = paramBundle.getQueryParameter("oauth_verifier");
        }
      }
      try
      {
        paramBundle = twitter.getOAuthAccessToken(requestToken, paramBundle);
        localEditor = mSharedPreferences.edit();
        localEditor.putString("oauth_token", paramBundle.getToken());
        localEditor.putString("oauth_token_secret", paramBundle.getTokenSecret());
        localEditor.putBoolean("isTwitterLogedIn", true);
        localEditor.commit();
        Log.e("Twitter OAuth Token", "> " + paramBundle.getToken());
        long l = paramBundle.getUserId();
        twitter.showUser(l).getName();
        new updateTwitterStatus().execute(new String[] { "Posted by appypie " + new Date().toLocaleString() });
        paramBundle = new Intent("android.media.action.IMAGE_CAPTURE");
        this.fileUri = getOutputMediaFileUri(1);
        paramBundle.putExtra("output", this.fileUri);
        startActivityForResult(paramBundle, 100);
        this.cameraButton = ((Button)findViewById(2131558523));
        this.cameraButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            paramAnonymousView = new Intent("android.media.action.IMAGE_CAPTURE");
            SocialShare.access$002(SocialShare.this, SocialShare.this.getOutputMediaFileUri(1));
            paramAnonymousView.putExtra("output", SocialShare.this.fileUri);
            SocialShare.this.startActivityForResult(paramAnonymousView, 100);
          }
        });
        this.mImage = ((ImageView)findViewById(2131558522));
        this.mBtnPostStatus = ((Button)findViewById(2131558524));
        this.mBtnPostStatus.setOnClickListener(this);
        this.mBtnPostStatus.setVisibility(8);
        this.mBtnTwitter.setVisibility(8);
        this.mailButton.setVisibility(8);
        this.whatsappButton.setVisibility(8);
        return;
        i += 1;
      }
      catch (Exception paramBundle)
      {
        for (;;)
        {
          Log.e("Twitter Login Error", "> " + paramBundle.getMessage());
        }
      }
    }
  }
  
  protected void onRestoreInstanceState(Bundle paramBundle)
  {
    super.onRestoreInstanceState(paramBundle);
    this.fileUri = ((Uri)paramBundle.getParcelable("file_uri"));
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putParcelable("file_uri", this.fileUri);
  }
  
  public void postToWall1(String paramString)
  {
    if (this.filePath.exists())
    {
      paramString = BitmapFactory.decodeStream(this.fis);
      Object localObject = new ByteArrayOutputStream();
      paramString.compress(Bitmap.CompressFormat.PNG, 100, (OutputStream)localObject);
      paramString = ((ByteArrayOutputStream)localObject).toByteArray();
      localObject = new Bundle();
      String str = "Shared Via " + getApplicationName() + "Mobile App";
      ((Bundle)localObject).putString("This is test post", str + " " + new Date().toLocaleString());
      ((Bundle)localObject).putString("filename", "TEst");
      ((Bundle)localObject).putByteArray("image", paramString);
      return;
    }
    showToast("Plz take a picture using Camera button first!");
  }
  
  class updateTwitterStatus
    extends AsyncTask<String, Void, String>
  {
    updateTwitterStatus() {}
    
    protected String doInBackground(String... paramVarArgs)
    {
      Log.d("Tweet Text", "> " + paramVarArgs[0]);
      paramVarArgs = paramVarArgs[0];
      try
      {
        Object localObject = new ConfigurationBuilder();
        ((ConfigurationBuilder)localObject).setOAuthConsumerKey(SocialShare.TWITTER_CONSUMER_KEY);
        ((ConfigurationBuilder)localObject).setOAuthConsumerSecret(SocialShare.TWITTER_CONSUMER_SECRET);
        AccessToken localAccessToken = new AccessToken(SocialShare.mSharedPreferences.getString("oauth_token", ""), SocialShare.mSharedPreferences.getString("oauth_token_secret", ""));
        localObject = new TwitterFactory(((ConfigurationBuilder)localObject).build()).getInstance(localAccessToken);
        paramVarArgs = new StatusUpdate(paramVarArgs);
        paramVarArgs.setMedia(SocialShare.this.filePath);
        paramVarArgs = ((Twitter)localObject).updateStatus(paramVarArgs);
        Log.d("Status", "> " + paramVarArgs.getText());
        return null;
      }
      catch (TwitterException paramVarArgs)
      {
        for (;;)
        {
          Log.d("Twitter Update Error", paramVarArgs.getMessage());
        }
      }
    }
    
    protected void onPostExecute(String paramString)
    {
      SocialShare.this.pDialog.dismiss();
      SocialShare.this.runOnUiThread(new Runnable()
      {
        public void run()
        {
          Toast.makeText(SocialShare.this.getApplicationContext(), "Status tweeted successfully", 0).show();
        }
      });
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      SocialShare.this.pDialog = new ProgressDialog(SocialShare.this);
      SocialShare.this.pDialog.setMessage("Updating to twitter...");
      SocialShare.this.pDialog.setIndeterminate(false);
      SocialShare.this.pDialog.setCancelable(false);
      SocialShare.this.pDialog.show();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\SocialShare.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */