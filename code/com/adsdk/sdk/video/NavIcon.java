package com.adsdk.sdk.video;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import com.adsdk.sdk.Log;
import java.lang.reflect.Method;

public class NavIcon
  extends AspectRatioImageViewWidth
  implements View.OnClickListener
{
  private Context mContext;
  private Handler mHandler;
  private NavIconData mIcon;
  
  public NavIcon(Context paramContext, NavIconData paramNavIconData)
  {
    super(paramContext);
    int i = (int)TypedValue.applyDimension(1, 4.0F, getResources().getDisplayMetrics());
    this.mContext = paramContext;
    this.mIcon = paramNavIconData;
    setPadding(i, 0, i, 0);
    this.mHandler = new Handler();
    setVisibility(8);
    setImageDrawable(paramNavIconData.iconUrl);
    setOnClickListener(this);
  }
  
  /* Error */
  private Drawable fetchImage(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: aconst_null
    //   4: astore 6
    //   6: new 84	java/net/URL
    //   9: dup
    //   10: aload_1
    //   11: invokespecial 86	java/net/URL:<init>	(Ljava/lang/String;)V
    //   14: invokevirtual 90	java/net/URL:getContent	()Ljava/lang/Object;
    //   17: checkcast 92	java/io/InputStream
    //   20: astore 8
    //   22: aload 8
    //   24: astore 6
    //   26: aload 8
    //   28: astore 7
    //   30: aload 8
    //   32: invokestatic 98	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   35: astore 10
    //   37: aload 10
    //   39: ifnull +236 -> 275
    //   42: aload 8
    //   44: astore 6
    //   46: aload 8
    //   48: astore 7
    //   50: aload_0
    //   51: getfield 40	com/adsdk/sdk/video/NavIcon:mContext	Landroid/content/Context;
    //   54: invokevirtual 101	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   57: invokevirtual 32	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   60: astore 9
    //   62: aload 8
    //   64: astore 6
    //   66: aload 8
    //   68: astore 7
    //   70: aload 10
    //   72: invokevirtual 107	android/graphics/Bitmap:getWidth	()I
    //   75: istore_2
    //   76: aload 8
    //   78: astore 6
    //   80: aload 8
    //   82: astore 7
    //   84: aload 10
    //   86: invokevirtual 110	android/graphics/Bitmap:getHeight	()I
    //   89: istore_3
    //   90: aload 8
    //   92: astore 6
    //   94: aload 8
    //   96: astore 7
    //   98: iconst_1
    //   99: iload_2
    //   100: i2f
    //   101: aload 9
    //   103: invokestatic 38	android/util/TypedValue:applyDimension	(IFLandroid/util/DisplayMetrics;)F
    //   106: f2i
    //   107: istore 4
    //   109: aload 8
    //   111: astore 6
    //   113: aload 8
    //   115: astore 7
    //   117: iconst_1
    //   118: iload_3
    //   119: i2f
    //   120: aload 9
    //   122: invokestatic 38	android/util/TypedValue:applyDimension	(IFLandroid/util/DisplayMetrics;)F
    //   125: f2i
    //   126: istore 5
    //   128: iload 4
    //   130: iload_2
    //   131: if_icmpne +13 -> 144
    //   134: aload 10
    //   136: astore 9
    //   138: iload 5
    //   140: iload_3
    //   141: if_icmpeq +23 -> 164
    //   144: aload 8
    //   146: astore 6
    //   148: aload 8
    //   150: astore 7
    //   152: aload 10
    //   154: iload 4
    //   156: iload 5
    //   158: iconst_0
    //   159: invokestatic 114	android/graphics/Bitmap:createScaledBitmap	(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;
    //   162: astore 9
    //   164: aload 8
    //   166: astore 6
    //   168: aload 8
    //   170: astore 7
    //   172: new 116	android/graphics/drawable/BitmapDrawable
    //   175: dup
    //   176: aload_0
    //   177: getfield 40	com/adsdk/sdk/video/NavIcon:mContext	Landroid/content/Context;
    //   180: invokevirtual 101	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   183: aload 9
    //   185: invokespecial 119	android/graphics/drawable/BitmapDrawable:<init>	(Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V
    //   188: astore 9
    //   190: aload 8
    //   192: ifnull +8 -> 200
    //   195: aload 8
    //   197: invokevirtual 122	java/io/InputStream:close	()V
    //   200: aload 9
    //   202: areturn
    //   203: astore 7
    //   205: aload 6
    //   207: astore 7
    //   209: new 124	java/lang/StringBuilder
    //   212: dup
    //   213: ldc 126
    //   215: invokespecial 127	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   218: aload_1
    //   219: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   222: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   225: invokestatic 140	com/adsdk/sdk/Log:d	(Ljava/lang/String;)V
    //   228: aload 6
    //   230: ifnull +8 -> 238
    //   233: aload 6
    //   235: invokevirtual 122	java/io/InputStream:close	()V
    //   238: new 84	java/net/URL
    //   241: dup
    //   242: aload_1
    //   243: invokespecial 86	java/net/URL:<init>	(Ljava/lang/String;)V
    //   246: invokevirtual 90	java/net/URL:getContent	()Ljava/lang/Object;
    //   249: checkcast 92	java/io/InputStream
    //   252: ldc -114
    //   254: invokestatic 148	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   257: astore 6
    //   259: aload 6
    //   261: areturn
    //   262: astore_1
    //   263: aload 7
    //   265: ifnull +8 -> 273
    //   268: aload 7
    //   270: invokevirtual 122	java/io/InputStream:close	()V
    //   273: aload_1
    //   274: athrow
    //   275: aload 8
    //   277: ifnull -39 -> 238
    //   280: aload 8
    //   282: invokevirtual 122	java/io/InputStream:close	()V
    //   285: goto -47 -> 238
    //   288: astore 6
    //   290: new 124	java/lang/StringBuilder
    //   293: dup
    //   294: ldc -106
    //   296: invokespecial 127	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   299: aload_1
    //   300: invokevirtual 131	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   303: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   306: aload 6
    //   308: invokestatic 154	com/adsdk/sdk/Log:e	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   311: aconst_null
    //   312: areturn
    //   313: astore_1
    //   314: aload 9
    //   316: areturn
    //   317: astore 6
    //   319: goto -81 -> 238
    //   322: astore 6
    //   324: goto -51 -> 273
    //   327: astore 6
    //   329: goto -91 -> 238
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	332	0	this	NavIcon
    //   0	332	1	paramString	String
    //   75	57	2	i	int
    //   89	53	3	j	int
    //   107	48	4	k	int
    //   126	31	5	m	int
    //   4	256	6	localObject1	Object
    //   288	19	6	localException1	Exception
    //   317	1	6	localException2	Exception
    //   322	1	6	localException3	Exception
    //   327	1	6	localException4	Exception
    //   1	170	7	localObject2	Object
    //   203	1	7	localException5	Exception
    //   207	62	7	localObject3	Object
    //   20	261	8	localInputStream	java.io.InputStream
    //   60	255	9	localObject4	Object
    //   35	118	10	localBitmap	android.graphics.Bitmap
    // Exception table:
    //   from	to	target	type
    //   6	22	203	java/lang/Exception
    //   30	37	203	java/lang/Exception
    //   50	62	203	java/lang/Exception
    //   70	76	203	java/lang/Exception
    //   84	90	203	java/lang/Exception
    //   98	109	203	java/lang/Exception
    //   117	128	203	java/lang/Exception
    //   152	164	203	java/lang/Exception
    //   172	190	203	java/lang/Exception
    //   6	22	262	finally
    //   30	37	262	finally
    //   50	62	262	finally
    //   70	76	262	finally
    //   84	90	262	finally
    //   98	109	262	finally
    //   117	128	262	finally
    //   152	164	262	finally
    //   172	190	262	finally
    //   209	228	262	finally
    //   238	259	288	java/lang/Exception
    //   195	200	313	java/lang/Exception
    //   233	238	317	java/lang/Exception
    //   268	273	322	java/lang/Exception
    //   280	285	327	java/lang/Exception
  }
  
  private void setImageDrawable(final String paramString)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        final Drawable localDrawable = NavIcon.this.fetchImage(paramString);
        if (localDrawable != null) {
          NavIcon.this.mHandler.post(new Runnable()
          {
            public void run()
            {
              NavIcon.this.setImageDrawable(localDrawable);
              NavIcon.this.setVisibility(0);
              NavIcon.this.requestLayout();
            }
          });
        }
      }
    }).start();
  }
  
  public void onClick(View paramView)
  {
    String str;
    try
    {
      if (!(this.mContext instanceof RichMediaActivity)) {
        return;
      }
      paramView = (RichMediaActivity)this.mContext;
      if (this.mIcon.openType == 1)
      {
        paramView.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.mIcon.clickUrl)));
        return;
      }
      str = this.mIcon.clickUrl;
      if ((str.startsWith("market:")) || (str.startsWith("http://market.android.com")) || (str.startsWith("sms:")) || (str.startsWith("tel:")) || (str.startsWith("mailto:")) || (str.startsWith("voicemail:")) || (str.startsWith("geo:")) || (str.startsWith("google.streetview:")))
      {
        paramView.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        return;
      }
    }
    catch (Exception paramView)
    {
      Log.w("Couldn't open URL: " + this.mIcon.clickUrl);
      return;
    }
    if (str.startsWith("mfox:external:"))
    {
      paramView.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str.substring(16))));
      return;
    }
    boolean bool = str.startsWith("mfox:replayvideo");
    if (bool) {
      try
      {
        paramView.getClass().getMethod("replayVideo", new Class[0]).invoke(paramView, new Object[0]);
        return;
      }
      catch (NoSuchMethodException paramView)
      {
        Log.d("Your activity class has no replayVideo method");
        return;
      }
      catch (Exception paramView)
      {
        Log.d("Couldn't run replayVideo method in your Activity");
        return;
      }
    }
    bool = str.startsWith("mfox:playvideo");
    if (bool) {
      try
      {
        paramView.getClass().getMethod("playVideo", new Class[0]).invoke(paramView, new Object[0]);
        return;
      }
      catch (NoSuchMethodException paramView)
      {
        Log.d("Your activity class has no playVideo method");
        return;
      }
      catch (Exception paramView)
      {
        Log.d("Couldn't run replayVideo method in your Activity");
        return;
      }
    }
    if (str.startsWith("mfox:skip"))
    {
      paramView.finish();
      return;
    }
    paramView.navigate(this.mIcon.clickUrl);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\video\NavIcon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */