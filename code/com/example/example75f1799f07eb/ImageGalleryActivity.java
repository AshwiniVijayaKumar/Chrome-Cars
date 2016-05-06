package com.example.example75f1799f07eb;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.DecodingType;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.FailReason;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoadingListener;
import com.ons.adapter.TouchImageView;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageGalleryActivity
  extends Activity
{
  public static final String MyPREFERENCES = "MyPrefs";
  static SharedPreferences sharedpreferences;
  String Device_Oriantation = "";
  String HeaderBarbackgroundColor;
  String HeaderbarTextColor;
  String ImgURl_Land;
  String ImgURl_Port;
  int Oriantation_ID;
  File SDCardRoot;
  TextView appName;
  String applicationName;
  private String bigImageUrls;
  String currentImageUrl;
  File file;
  String foldername = "";
  private ViewPager gallery;
  RelativeLayout headerback;
  protected ImageLoader imageLoader = ImageLoader.getInstance();
  String[] imageUrls;
  int img_position = 0;
  int longClickDuration = 1000;
  Preferences mpreferences;
  String navigationBarType;
  private String photoShare;
  String[] picTextArray;
  String[] picTextcommentsArray;
  String[] picTextlikesArray;
  private String picTexts;
  private String piccommentsTexts;
  private String piclikesTexts;
  String position;
  int positionValue;
  ImageView saveToMemory;
  ImageButton shareButton;
  long then;
  
  private Bitmap LoadImage(String paramString, BitmapFactory.Options paramOptions)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    try
    {
      InputStream localInputStream = OpenHttpConnection(paramString);
      localObject1 = localObject2;
      paramString = BitmapFactory.decodeStream(localInputStream, null, paramOptions);
      localObject1 = paramString;
      localInputStream.close();
      return paramString;
    }
    catch (IOException paramString) {}
    return (Bitmap)localObject1;
  }
  
  private InputStream OpenHttpConnection(String paramString)
    throws IOException
  {
    Object localObject = null;
    paramString = new URL(paramString).openConnection();
    try
    {
      HttpURLConnection localHttpURLConnection = (HttpURLConnection)paramString;
      localHttpURLConnection.setRequestMethod("GET");
      localHttpURLConnection.connect();
      paramString = (String)localObject;
      if (localHttpURLConnection.getResponseCode() == 200) {
        paramString = localHttpURLConnection.getInputStream();
      }
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  private void Setheaderimage()
  {
    try
    {
      Object localObject = getApplicationName();
      this.navigationBarType = sharedpreferences.getString("navigationBarType", "");
      this.HeaderBarbackgroundColor = sharedpreferences.getString("HeaderBarbackgroundColor", "");
      this.HeaderbarTextColor = sharedpreferences.getString("HeaderbarTextColor", "");
      this.ImgURl_Port = sharedpreferences.getString("ImgURl_Port", "");
      this.ImgURl_Land = sharedpreferences.getString("ImgURl_Land", "");
      System.out.println("navigationBarType " + this.navigationBarType + " , HeaderBarbackgroundColor " + this.HeaderBarbackgroundColor + " , HeaderbarTextColor " + this.HeaderbarTextColor);
      if (this.navigationBarType.equals("image"))
      {
        this.SDCardRoot = new File(Environment.getExternalStorageDirectory(), this.foldername + (String)localObject);
        if (this.Device_Oriantation.equals("PORTRAIT"))
        {
          this.file = new File(this.SDCardRoot, "header_port_img.jpg");
          if (this.file.exists())
          {
            localObject = BitmapFactory.decodeFile(this.file.getAbsolutePath());
            localObject = new BitmapDrawable(getResources(), (Bitmap)localObject);
            this.headerback.setBackground((Drawable)localObject);
            this.appName.setText("");
          }
        }
        else
        {
          this.file = new File(this.SDCardRoot, "header_land_img.jpg");
          if (this.file.exists())
          {
            localObject = BitmapFactory.decodeFile(this.file.getAbsolutePath());
            localObject = new BitmapDrawable(getResources(), (Bitmap)localObject);
            this.headerback.setBackground((Drawable)localObject);
            this.appName.setText("");
          }
        }
      }
      else
      {
        System.out.println("Headerbar color code : HeaderBarbackgroundColor  " + this.HeaderBarbackgroundColor + "  ,  HeaderbarTextColor  " + this.HeaderbarTextColor);
        this.headerback.setBackgroundColor(Color.parseColor(this.HeaderBarbackgroundColor));
        this.appName.setTextColor(Color.parseColor(this.HeaderbarTextColor));
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  private void btnShare()
  {
    getResources().getString(2131230720);
    getIntent();
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.TEXT", "" + this.imageUrls[this.img_position]);
    startActivity(Intent.createChooser(localIntent, "Share via"));
  }
  
  /* Error */
  private void saveImageToMemory()
  {
    // Byte code:
    //   0: new 340	android/graphics/BitmapFactory$Options
    //   3: dup
    //   4: invokespecial 341	android/graphics/BitmapFactory$Options:<init>	()V
    //   7: astore_1
    //   8: aload_1
    //   9: iconst_1
    //   10: putfield 344	android/graphics/BitmapFactory$Options:inSampleSize	I
    //   13: aload_0
    //   14: aload_0
    //   15: getfield 322	com/example/example75f1799f07eb/ImageGalleryActivity:imageUrls	[Ljava/lang/String;
    //   18: aload_0
    //   19: getfield 77	com/example/example75f1799f07eb/ImageGalleryActivity:img_position	I
    //   22: aaload
    //   23: aload_1
    //   24: invokespecial 346	com/example/example75f1799f07eb/ImageGalleryActivity:LoadImage	(Ljava/lang/String;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   27: astore 4
    //   29: invokestatic 214	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   32: invokevirtual 347	java/io/File:toString	()Ljava/lang/String;
    //   35: astore 5
    //   37: aload_0
    //   38: getfield 349	com/example/example75f1799f07eb/ImageGalleryActivity:currentImageUrl	Ljava/lang/String;
    //   41: aload_0
    //   42: getfield 349	com/example/example75f1799f07eb/ImageGalleryActivity:currentImageUrl	Ljava/lang/String;
    //   45: bipush 47
    //   47: invokevirtual 353	java/lang/String:lastIndexOf	(I)I
    //   50: iconst_1
    //   51: iadd
    //   52: aload_0
    //   53: getfield 349	com/example/example75f1799f07eb/ImageGalleryActivity:currentImageUrl	Ljava/lang/String;
    //   56: invokevirtual 356	java/lang/String:length	()I
    //   59: invokevirtual 360	java/lang/String:substring	(II)Ljava/lang/String;
    //   62: astore_3
    //   63: aload_3
    //   64: iconst_0
    //   65: aload_3
    //   66: bipush 46
    //   68: invokevirtual 353	java/lang/String:lastIndexOf	(I)I
    //   71: invokevirtual 360	java/lang/String:substring	(II)Ljava/lang/String;
    //   74: astore 6
    //   76: ldc 87
    //   78: astore_1
    //   79: aload_1
    //   80: astore_2
    //   81: aload_3
    //   82: ldc_w 362
    //   85: invokevirtual 366	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   88: pop
    //   89: aload_1
    //   90: astore_2
    //   91: ldc 87
    //   93: ldc_w 368
    //   96: invokevirtual 372	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   99: ifeq +219 -> 318
    //   102: ldc_w 374
    //   105: astore_1
    //   106: aload_1
    //   107: astore_2
    //   108: new 208	java/io/File
    //   111: dup
    //   112: new 179	java/lang/StringBuilder
    //   115: dup
    //   116: invokespecial 180	java/lang/StringBuilder:<init>	()V
    //   119: aload 5
    //   121: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: ldc_w 376
    //   127: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: aload_0
    //   131: getfield 378	com/example/example75f1799f07eb/ImageGalleryActivity:applicationName	Ljava/lang/String;
    //   134: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   137: ldc_w 376
    //   140: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: invokevirtual 193	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   146: invokespecial 379	java/io/File:<init>	(Ljava/lang/String;)V
    //   149: astore 7
    //   151: aload_1
    //   152: astore_3
    //   153: aload_1
    //   154: astore_2
    //   155: aload 7
    //   157: invokevirtual 229	java/io/File:exists	()Z
    //   160: ifne +13 -> 173
    //   163: aload_1
    //   164: astore_2
    //   165: aload 7
    //   167: invokevirtual 382	java/io/File:mkdir	()Z
    //   170: pop
    //   171: aload_1
    //   172: astore_3
    //   173: new 208	java/io/File
    //   176: dup
    //   177: new 179	java/lang/StringBuilder
    //   180: dup
    //   181: invokespecial 180	java/lang/StringBuilder:<init>	()V
    //   184: aload 5
    //   186: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: ldc_w 376
    //   192: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: aload_0
    //   196: getfield 378	com/example/example75f1799f07eb/ImageGalleryActivity:applicationName	Ljava/lang/String;
    //   199: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   202: ldc_w 376
    //   205: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: invokevirtual 193	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   211: new 179	java/lang/StringBuilder
    //   214: dup
    //   215: invokespecial 180	java/lang/StringBuilder:<init>	()V
    //   218: aload 6
    //   220: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: aload_3
    //   224: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   227: invokevirtual 193	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   230: invokespecial 385	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   233: astore_1
    //   234: new 387	java/io/FileOutputStream
    //   237: dup
    //   238: aload_1
    //   239: invokespecial 390	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   242: astore_2
    //   243: aload_3
    //   244: ldc_w 368
    //   247: invokevirtual 372	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   250: ifeq +85 -> 335
    //   253: aload 4
    //   255: getstatic 395	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
    //   258: bipush 100
    //   260: aload_2
    //   261: invokevirtual 401	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   264: pop
    //   265: aload_2
    //   266: invokevirtual 406	java/io/OutputStream:flush	()V
    //   269: aload_2
    //   270: invokevirtual 407	java/io/OutputStream:close	()V
    //   273: aload_0
    //   274: invokevirtual 411	com/example/example75f1799f07eb/ImageGalleryActivity:getApplicationContext	()Landroid/content/Context;
    //   277: astore_2
    //   278: aload_1
    //   279: invokevirtual 414	java/io/File:getPath	()Ljava/lang/String;
    //   282: astore_1
    //   283: new 8	com/example/example75f1799f07eb/ImageGalleryActivity$2
    //   286: dup
    //   287: aload_0
    //   288: invokespecial 416	com/example/example75f1799f07eb/ImageGalleryActivity$2:<init>	(Lcom/example/example75f1799f07eb/ImageGalleryActivity;)V
    //   291: astore_3
    //   292: aload_2
    //   293: iconst_1
    //   294: anewarray 202	java/lang/String
    //   297: dup
    //   298: iconst_0
    //   299: aload_1
    //   300: aastore
    //   301: aconst_null
    //   302: aload_3
    //   303: invokestatic 422	android/media/MediaScannerConnection:scanFile	(Landroid/content/Context;[Ljava/lang/String;[Ljava/lang/String;Landroid/media/MediaScannerConnection$OnScanCompletedListener;)V
    //   306: aload_0
    //   307: ldc_w 424
    //   310: iconst_1
    //   311: invokestatic 430	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   314: invokevirtual 433	android/widget/Toast:show	()V
    //   317: return
    //   318: ldc_w 435
    //   321: astore_1
    //   322: goto -216 -> 106
    //   325: astore_1
    //   326: aload_1
    //   327: invokevirtual 438	java/lang/Exception:printStackTrace	()V
    //   330: aload_2
    //   331: astore_3
    //   332: goto -159 -> 173
    //   335: aload 4
    //   337: getstatic 441	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   340: bipush 100
    //   342: aload_2
    //   343: invokevirtual 401	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   346: pop
    //   347: goto -82 -> 265
    //   350: astore_1
    //   351: aload_1
    //   352: invokevirtual 442	java/io/FileNotFoundException:printStackTrace	()V
    //   355: aload_0
    //   356: ldc_w 444
    //   359: iconst_1
    //   360: invokestatic 430	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   363: invokevirtual 433	android/widget/Toast:show	()V
    //   366: return
    //   367: astore_1
    //   368: aload_1
    //   369: invokevirtual 445	java/io/IOException:printStackTrace	()V
    //   372: aload_0
    //   373: ldc_w 444
    //   376: iconst_1
    //   377: invokestatic 430	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   380: invokevirtual 433	android/widget/Toast:show	()V
    //   383: return
    //   384: astore_1
    //   385: return
    //   386: astore_1
    //   387: return
    //   388: astore_1
    //   389: goto -21 -> 368
    //   392: astore_1
    //   393: goto -42 -> 351
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	396	0	this	ImageGalleryActivity
    //   7	315	1	localObject1	Object
    //   325	2	1	localException1	Exception
    //   350	2	1	localFileNotFoundException1	java.io.FileNotFoundException
    //   367	2	1	localIOException1	IOException
    //   384	1	1	localException2	Exception
    //   386	1	1	localException3	Exception
    //   388	1	1	localIOException2	IOException
    //   392	1	1	localFileNotFoundException2	java.io.FileNotFoundException
    //   80	263	2	localObject2	Object
    //   62	270	3	localObject3	Object
    //   27	309	4	localBitmap	Bitmap
    //   35	150	5	str1	String
    //   74	145	6	str2	String
    //   149	17	7	localFile	File
    // Exception table:
    //   from	to	target	type
    //   81	89	325	java/lang/Exception
    //   91	102	325	java/lang/Exception
    //   108	151	325	java/lang/Exception
    //   155	163	325	java/lang/Exception
    //   165	171	325	java/lang/Exception
    //   243	265	350	java/io/FileNotFoundException
    //   265	317	350	java/io/FileNotFoundException
    //   335	347	350	java/io/FileNotFoundException
    //   234	243	367	java/io/IOException
    //   234	243	384	java/lang/Exception
    //   243	265	386	java/lang/Exception
    //   265	317	386	java/lang/Exception
    //   335	347	386	java/lang/Exception
    //   243	265	388	java/io/IOException
    //   265	317	388	java/io/IOException
    //   335	347	388	java/io/IOException
    //   234	243	392	java/io/FileNotFoundException
  }
  
  private void showActionSheet()
  {
    final Dialog localDialog = new Dialog(this, 2131296277);
    localDialog.setContentView(2130903042);
    ((TextView)localDialog.findViewById(2131558432)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.dismiss();
        ImageGalleryActivity.this.saveImageToMemory();
      }
    });
    ((TextView)localDialog.findViewById(2131558433)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.dismiss();
        ImageGalleryActivity.this.btnShare();
      }
    });
    ((TextView)localDialog.findViewById(2131558434)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.dismiss();
      }
    });
    localDialog.show();
    localDialog.getWindow().setGravity(80);
  }
  
  public void close(View paramView)
  {
    finish();
  }
  
  public String getApplicationName()
  {
    return getString(getApplicationInfo().labelRes);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    sharedpreferences = getSharedPreferences("MyPrefs", 0);
    this.mpreferences = new Preferences(getApplicationContext());
    paramBundle = sharedpreferences.getString("appLanguage", "");
    System.out.println("===== type language is in imagegalleryActivity : " + paramBundle);
    requestWindowFeature(7);
    setContentView(2130903040);
    if (paramBundle.equalsIgnoreCase("sa")) {
      getWindow().setFeatureInt(7, 2130903065);
    }
    for (;;)
    {
      this.applicationName = getResources().getString(2131230720);
      this.appName = ((TextView)findViewById(2131558500));
      try
      {
        sharedpreferences = getSharedPreferences("MyPrefs", 0);
        this.mpreferences = new Preferences(getApplicationContext());
        MyPhoneGapActivity.sharedpreferences.getString("textColor", "#ffffff");
        this.headerback = ((RelativeLayout)findViewById(2131558499));
        this.shareButton = ((ImageButton)findViewById(2131558505));
        this.shareButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            ImageGalleryActivity.this.showActionSheet();
          }
        });
        this.appName.setText(this.applicationName);
        paramBundle = getIntent().getExtras();
        this.position = paramBundle.getString("position");
        this.positionValue = Integer.parseInt(this.position);
        this.bigImageUrls = paramBundle.getString("bigImageUrls");
        this.piclikesTexts = paramBundle.getString("piclikes");
        this.piccommentsTexts = paramBundle.getString("piccomments");
        for (;;)
        {
          try
          {
            this.photoShare = paramBundle.getString("photoShare");
            this.picTexts = paramBundle.getString("pictext");
            if (!this.photoShare.equalsIgnoreCase("Off")) {
              continue;
            }
            this.shareButton.setVisibility(8);
            if ((this.picTexts != null) && (this.picTexts.length() > 0)) {
              this.picTextArray = this.picTexts.split(",");
            }
          }
          catch (Exception localException)
          {
            int i;
            continue;
          }
          if ((this.piclikesTexts != null) && (this.piclikesTexts.length() > 0)) {
            this.picTextlikesArray = this.piclikesTexts.split(",");
          }
          if ((this.piccommentsTexts != null) && (this.piccommentsTexts.length() > 0)) {
            this.picTextcommentsArray = this.piccommentsTexts.split(",");
          }
          this.imageUrls = this.bigImageUrls.split(",");
          i = paramBundle.getInt("com.nostra13.example.universalimageloader.IMAGE_POSITION", this.positionValue);
          this.gallery = ((ViewPager)findViewById(2131558429));
          this.gallery.setAdapter(new ImagePagerAdapter(this.imageUrls));
          this.gallery.setCurrentItem(i);
          Setheaderimage();
          return;
          getWindow().setFeatureInt(7, 2130903063);
          break;
          this.shareButton.setVisibility(0);
        }
      }
      catch (Exception paramBundle)
      {
        for (;;) {}
      }
    }
  }
  
  private class ImagePagerAdapter
    extends PagerAdapter
  {
    private String[] images;
    private LayoutInflater inflater;
    
    ImagePagerAdapter(String[] paramArrayOfString)
    {
      this.images = paramArrayOfString;
      this.inflater = ImageGalleryActivity.this.getLayoutInflater();
    }
    
    public void destroyItem(View paramView, int paramInt, Object paramObject)
    {
      ((ViewPager)paramView).removeView((View)paramObject);
    }
    
    public void finishUpdate(View paramView) {}
    
    public int getCount()
    {
      return this.images.length;
    }
    
    public Object instantiateItem(View paramView, final int paramInt)
    {
      FrameLayout localFrameLayout = (FrameLayout)this.inflater.inflate(2130903088, null);
      final TouchImageView localTouchImageView = (TouchImageView)localFrameLayout.findViewById(2131558556);
      final ProgressBar localProgressBar = (ProgressBar)localFrameLayout.findViewById(2131558557);
      RelativeLayout localRelativeLayout = (RelativeLayout)localFrameLayout.findViewById(2131558559);
      TextView localTextView1 = (TextView)localFrameLayout.findViewById(2131558562);
      TextView localTextView2 = (TextView)localFrameLayout.findViewById(2131558565);
      TextView localTextView3 = (TextView)localFrameLayout.findViewById(2131558558);
      DisplayImageOptions localDisplayImageOptions = new DisplayImageOptions.Builder().cacheInMemory().cacheOnDisc().decodingType(DecodingType.MEMORY_SAVING).build();
      if ((ImageGalleryActivity.this.picTextlikesArray != null) && (ImageGalleryActivity.this.picTextlikesArray.length > 0) && (ImageGalleryActivity.this.picTextlikesArray.length > paramInt))
      {
        localRelativeLayout.setVisibility(0);
        localTextView1.setText(ImageGalleryActivity.this.picTextlikesArray[paramInt]);
        localTextView1.setMovementMethod(new ScrollingMovementMethod());
      }
      if ((ImageGalleryActivity.this.picTextcommentsArray != null) && (ImageGalleryActivity.this.picTextcommentsArray.length > 0) && (ImageGalleryActivity.this.picTextcommentsArray.length > paramInt))
      {
        localRelativeLayout.setVisibility(0);
        localTextView2.setText(ImageGalleryActivity.this.picTextcommentsArray[paramInt]);
        localTextView2.setMovementMethod(new ScrollingMovementMethod());
      }
      if (ImageGalleryActivity.this.picTextArray != null)
      {
        if ((ImageGalleryActivity.this.picTextArray.length <= 0) || (ImageGalleryActivity.this.picTextArray.length <= paramInt)) {
          break label409;
        }
        localTextView3.setText(ImageGalleryActivity.this.picTextArray[paramInt]);
        localTextView3.setMovementMethod(new ScrollingMovementMethod());
      }
      for (;;)
      {
        if ((ImageGalleryActivity.this.picTextcommentsArray == null) || (ImageGalleryActivity.this.picTextcommentsArray.length <= 0) || (ImageGalleryActivity.this.picTextcommentsArray.length <= paramInt) || (ImageGalleryActivity.this.picTextlikesArray == null) || (ImageGalleryActivity.this.picTextlikesArray.length <= 0) || (ImageGalleryActivity.this.picTextlikesArray.length <= paramInt)) {
          localRelativeLayout.setVisibility(4);
        }
        ImageGalleryActivity.this.imageLoader.displayImage(this.images[paramInt], localTouchImageView, localDisplayImageOptions, new ImageLoadingListener()
        {
          public void onLoadingComplete()
          {
            localProgressBar.setVisibility(8);
            ImageGalleryActivity.this.img_position = ImageGalleryActivity.this.gallery.getCurrentItem();
          }
          
          public void onLoadingFailed(FailReason paramAnonymousFailReason)
          {
            localProgressBar.setVisibility(8);
            localTouchImageView.setImageResource(17301533);
            switch (ImageGalleryActivity.6.$SwitchMap$com$nostra13$universalimageloader$core$FailReason[paramAnonymousFailReason.ordinal()])
            {
            default: 
              return;
            }
            ImageGalleryActivity.this.imageLoader.clearMemoryCache();
          }
          
          public void onLoadingStarted()
          {
            localProgressBar.setVisibility(0);
            ImageGalleryActivity.this.currentImageUrl = ImageGalleryActivity.ImagePagerAdapter.this.images[paramInt];
          }
        });
        ((ViewPager)paramView).addView(localFrameLayout, 0);
        return localFrameLayout;
        label409:
        localTextView3.setVisibility(8);
      }
    }
    
    public boolean isViewFromObject(View paramView, Object paramObject)
    {
      return paramView.equals(paramObject);
    }
    
    public void restoreState(Parcelable paramParcelable, ClassLoader paramClassLoader) {}
    
    public Parcelable saveState()
    {
      return null;
    }
    
    public void startUpdate(View paramView) {}
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\example\example75f1799f07eb\ImageGalleryActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */