package com.adsdk.sdk.video;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.adsdk.sdk.Log;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.client.methods.HttpGet;

public class ResourceManager
{
  public static final String BACK_ICON = "browser_back.png";
  public static final String BOTTOMBAR_BG = "bar.png";
  public static final String CLOSE_BUTTON_NORMAL = "close_button_normal.png";
  public static final String CLOSE_BUTTON_PRESSED = "close_button_pressed.png";
  public static final int DEFAULT_BACK_IMAGE_RESOURCE_ID = -14;
  public static final int DEFAULT_BOTTOMBAR_BG_RESOURCE_ID = -2;
  public static final int DEFAULT_CLOSE_BUTTON_NORMAL_RESOURCE_ID = -29;
  public static final int DEFAULT_CLOSE_BUTTON_PRESSED_RESOURCE_ID = -30;
  public static final int DEFAULT_EXTERNAL_IMAGE_RESOURCE_ID = -17;
  public static final int DEFAULT_FORWARD_IMAGE_RESOURCE_ID = -15;
  public static final int DEFAULT_PAUSE_IMAGE_RESOURCE_ID = -12;
  public static final int DEFAULT_PLAY_IMAGE_RESOURCE_ID = -11;
  public static final int DEFAULT_RELOAD_IMAGE_RESOURCE_ID = -16;
  public static final int DEFAULT_REPLAY_IMAGE_RESOURCE_ID = -13;
  public static final int DEFAULT_SKIP_IMAGE_RESOURCE_ID = -18;
  public static final int DEFAULT_TOPBAR_BG_RESOURCE_ID = -1;
  public static final String EXTERNAL_ICON = "browser_external.png";
  public static final String FORWARD_ICON = "browser_forward.png";
  public static final String PAUSE_ICON = "video_pause.png";
  public static final String PLAY_ICON = "video_play.png";
  public static final String RELOAD_ICON = "video_replay.png";
  public static final String REPLAY_ICON = "video_replay.png";
  public static final int RESOURCE_LOADED_MSG = 100;
  public static final String SKIP_ICON = "skip.png";
  public static final String TOPBAR_BG = "bar.png";
  public static final int TYPE_FILE = 0;
  public static final int TYPE_UNKNOWN = -1;
  public static final int TYPE_ZIP = 1;
  public static final String VERSION = "version.txt";
  public static boolean sCancel;
  public static HttpGet sDownloadGet;
  public static boolean sDownloading;
  private static HashMap<Integer, Drawable> sResources;
  private Handler mHandler;
  private HashMap<Integer, Drawable> mResources = new HashMap();
  
  static
  {
    if (!ResourceManager.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      sDownloading = false;
      sCancel = false;
      sResources = new HashMap();
      return;
    }
  }
  
  public ResourceManager(Context paramContext, Handler paramHandler)
  {
    this.mHandler = paramHandler;
  }
  
  /* Error */
  private static Drawable buildDrawable(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: aconst_null
    //   4: astore 6
    //   6: aload_0
    //   7: invokevirtual 127	java/lang/Object:getClass	()Ljava/lang/Class;
    //   10: invokevirtual 131	java/lang/Class:getClassLoader	()Ljava/lang/ClassLoader;
    //   13: new 133	java/lang/StringBuilder
    //   16: dup
    //   17: ldc -121
    //   19: invokespecial 138	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   22: aload_1
    //   23: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: invokevirtual 146	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   29: invokevirtual 152	java/lang/ClassLoader:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   32: astore 8
    //   34: aload 8
    //   36: astore 6
    //   38: aload 8
    //   40: astore 7
    //   42: aload 8
    //   44: invokestatic 158	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   47: astore 10
    //   49: aload 10
    //   51: ifnull +205 -> 256
    //   54: aload 8
    //   56: astore 6
    //   58: aload 8
    //   60: astore 7
    //   62: aload_0
    //   63: invokevirtual 164	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   66: invokevirtual 170	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   69: astore 9
    //   71: aload 8
    //   73: astore 6
    //   75: aload 8
    //   77: astore 7
    //   79: aload 10
    //   81: invokevirtual 176	android/graphics/Bitmap:getWidth	()I
    //   84: istore_2
    //   85: aload 8
    //   87: astore 6
    //   89: aload 8
    //   91: astore 7
    //   93: aload 10
    //   95: invokevirtual 179	android/graphics/Bitmap:getHeight	()I
    //   98: istore_3
    //   99: aload 8
    //   101: astore 6
    //   103: aload 8
    //   105: astore 7
    //   107: iconst_1
    //   108: iload_2
    //   109: i2f
    //   110: aload 9
    //   112: invokestatic 185	android/util/TypedValue:applyDimension	(IFLandroid/util/DisplayMetrics;)F
    //   115: f2i
    //   116: istore 4
    //   118: aload 8
    //   120: astore 6
    //   122: aload 8
    //   124: astore 7
    //   126: iconst_1
    //   127: iload_3
    //   128: i2f
    //   129: aload 9
    //   131: invokestatic 185	android/util/TypedValue:applyDimension	(IFLandroid/util/DisplayMetrics;)F
    //   134: f2i
    //   135: istore 5
    //   137: iload 4
    //   139: iload_2
    //   140: if_icmpne +13 -> 153
    //   143: aload 10
    //   145: astore 9
    //   147: iload 5
    //   149: iload_3
    //   150: if_icmpeq +23 -> 173
    //   153: aload 8
    //   155: astore 6
    //   157: aload 8
    //   159: astore 7
    //   161: aload 10
    //   163: iload 4
    //   165: iload 5
    //   167: iconst_0
    //   168: invokestatic 189	android/graphics/Bitmap:createScaledBitmap	(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;
    //   171: astore 9
    //   173: aload 8
    //   175: astore 6
    //   177: aload 8
    //   179: astore 7
    //   181: new 191	android/graphics/drawable/BitmapDrawable
    //   184: dup
    //   185: aload_0
    //   186: invokevirtual 164	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   189: aload 9
    //   191: invokespecial 194	android/graphics/drawable/BitmapDrawable:<init>	(Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V
    //   194: astore_0
    //   195: aload 8
    //   197: ifnull +8 -> 205
    //   200: aload 8
    //   202: invokevirtual 199	java/io/InputStream:close	()V
    //   205: aload_0
    //   206: areturn
    //   207: astore_0
    //   208: aload 6
    //   210: astore 7
    //   212: new 133	java/lang/StringBuilder
    //   215: dup
    //   216: ldc -55
    //   218: invokespecial 138	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   221: aload_1
    //   222: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: invokevirtual 146	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   228: invokestatic 206	com/adsdk/sdk/Log:i	(Ljava/lang/String;)V
    //   231: aload 6
    //   233: ifnull +8 -> 241
    //   236: aload 6
    //   238: invokevirtual 199	java/io/InputStream:close	()V
    //   241: aconst_null
    //   242: areturn
    //   243: astore_0
    //   244: aload 7
    //   246: ifnull +8 -> 254
    //   249: aload 7
    //   251: invokevirtual 199	java/io/InputStream:close	()V
    //   254: aload_0
    //   255: athrow
    //   256: aload 8
    //   258: ifnull -17 -> 241
    //   261: aload 8
    //   263: invokevirtual 199	java/io/InputStream:close	()V
    //   266: goto -25 -> 241
    //   269: astore_1
    //   270: aload_0
    //   271: areturn
    //   272: astore_0
    //   273: goto -32 -> 241
    //   276: astore_1
    //   277: goto -23 -> 254
    //   280: astore_0
    //   281: goto -40 -> 241
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	284	0	paramContext	Context
    //   0	284	1	paramString	String
    //   84	57	2	i	int
    //   98	53	3	j	int
    //   116	48	4	k	int
    //   135	31	5	m	int
    //   4	233	6	localObject1	Object
    //   1	249	7	localObject2	Object
    //   32	230	8	localInputStream	InputStream
    //   69	121	9	localObject3	Object
    //   47	115	10	localBitmap	Bitmap
    // Exception table:
    //   from	to	target	type
    //   6	34	207	java/lang/Exception
    //   42	49	207	java/lang/Exception
    //   62	71	207	java/lang/Exception
    //   79	85	207	java/lang/Exception
    //   93	99	207	java/lang/Exception
    //   107	118	207	java/lang/Exception
    //   126	137	207	java/lang/Exception
    //   161	173	207	java/lang/Exception
    //   181	195	207	java/lang/Exception
    //   6	34	243	finally
    //   42	49	243	finally
    //   62	71	243	finally
    //   79	85	243	finally
    //   93	99	243	finally
    //   107	118	243	finally
    //   126	137	243	finally
    //   161	173	243	finally
    //   181	195	243	finally
    //   212	231	243	finally
    //   200	205	269	java/lang/Exception
    //   236	241	272	java/lang/Exception
    //   249	254	276	java/lang/Exception
    //   261	266	280	java/lang/Exception
  }
  
  public static void cancel()
  {
    sCancel = true;
    if (sDownloadGet != null)
    {
      sDownloadGet.abort();
      sDownloadGet = null;
    }
    sResources.clear();
  }
  
  public static Drawable getDefaultResource(int paramInt)
  {
    return (Drawable)sResources.get(Integer.valueOf(paramInt));
  }
  
  public static Drawable getDefaultSkipButton(Context paramContext)
  {
    return buildDrawable(paramContext, "skip.png");
  }
  
  public static long getInstalledVersion(Context paramContext)
  {
    l2 = -1L;
    localContext2 = null;
    localContext1 = null;
    try
    {
      paramContext = paramContext.openFileInput("version.txt");
      localContext1 = paramContext;
      localContext2 = paramContext;
      l1 = Long.valueOf(new BufferedReader(new InputStreamReader(paramContext, "UTF-8")).readLine()).longValue();
      l2 = l1;
      l1 = l2;
      if (paramContext == null) {}
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        label64:
        l1 = l2;
        if (localContext1 != null) {
          try
          {
            localContext1.close();
            l1 = l2;
          }
          catch (Exception paramContext)
          {
            l1 = l2;
          }
        }
      }
    }
    finally
    {
      if (localContext2 != null) {}
      try
      {
        localContext2.close();
        throw paramContext;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
    try
    {
      paramContext.close();
      l1 = l2;
    }
    catch (Exception paramContext)
    {
      l1 = l2;
      break label64;
    }
    Log.d("Resources installed version:" + l1);
    return l1;
  }
  
  public static Drawable getStaticResource(Context paramContext, int paramInt)
  {
    BitmapDrawable localBitmapDrawable2 = (BitmapDrawable)sResources.get(Integer.valueOf(paramInt));
    BitmapDrawable localBitmapDrawable1;
    if (localBitmapDrawable2 != null)
    {
      localBitmapDrawable1 = localBitmapDrawable2;
      if (!localBitmapDrawable2.getBitmap().isRecycled()) {}
    }
    else
    {
      initDefaultResource(paramContext, paramInt);
      localBitmapDrawable1 = (BitmapDrawable)sResources.get(Integer.valueOf(paramInt));
    }
    return localBitmapDrawable1;
  }
  
  private static void initDefaultResource(Context paramContext, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return;
    case -11: 
      registerImageResource(paramContext, -11, "video_play.png");
      return;
    case -12: 
      registerImageResource(paramContext, -12, "video_pause.png");
      return;
    case -13: 
      registerImageResource(paramContext, -13, "video_replay.png");
      return;
    case -14: 
      registerImageResource(paramContext, -14, "browser_back.png");
      return;
    case -15: 
      registerImageResource(paramContext, -15, "browser_forward.png");
      return;
    case -16: 
      registerImageResource(paramContext, -16, "video_replay.png");
      return;
    case -17: 
      registerImageResource(paramContext, -17, "browser_external.png");
      return;
    case -18: 
      registerImageResource(paramContext, -18, "skip.png");
      return;
    case -1: 
      registerImageResource(paramContext, -1, "bar.png");
      return;
    case -2: 
      registerImageResource(paramContext, -2, "bar.png");
      return;
    case -29: 
      registerImageResource(paramContext, -29, "close_button_normal.png");
      return;
    }
    registerImageResource(paramContext, -30, "close_button_pressed.png");
  }
  
  public static boolean isDownloading()
  {
    return sDownloading;
  }
  
  private static void registerImageResource(Context paramContext, int paramInt, String paramString)
  {
    paramContext = buildDrawable(paramContext, paramString);
    if (paramContext != null)
    {
      sResources.put(Integer.valueOf(paramInt), paramContext);
      return;
    }
    Log.i("registerImageResource", "drawable was null " + paramString);
  }
  
  public static boolean resourcesInstalled(Context paramContext)
  {
    paramContext = paramContext.fileList();
    int i = 0;
    for (;;)
    {
      if (i >= paramContext.length) {
        return false;
      }
      if ("version.txt".equals(paramContext[i]))
      {
        Log.d("Resources already installed");
        return true;
      }
      i += 1;
    }
  }
  
  public static void saveInstalledVersion(Context paramContext, long paramLong)
  {
    Context localContext2 = null;
    Context localContext1 = null;
    try
    {
      paramContext = paramContext.openFileOutput("version.txt", 0);
      localContext1 = paramContext;
      localContext2 = paramContext;
      OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(paramContext, "UTF-8");
      localContext1 = paramContext;
      localContext2 = paramContext;
      localOutputStreamWriter.write(String.valueOf(paramLong));
      localContext1 = paramContext;
      localContext2 = paramContext;
      localOutputStreamWriter.flush();
      if (paramContext != null) {}
      return;
    }
    catch (Exception paramContext)
    {
      do
      {
        paramContext = paramContext;
      } while (localContext1 == null);
      try
      {
        localContext1.close();
        return;
      }
      catch (Exception paramContext)
      {
        return;
      }
    }
    finally
    {
      if (localContext2 != null) {}
      try
      {
        localContext2.close();
        throw paramContext;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
  }
  
  public boolean containsResource(int paramInt)
  {
    return (this.mResources.get(Integer.valueOf(paramInt)) != null) || (this.mResources.get(Integer.valueOf(paramInt)) != null);
  }
  
  public void fetchResource(Context paramContext, String paramString, int paramInt)
  {
    if (sResources.get(Integer.valueOf(paramInt)) == null) {
      new FetchImageTask(paramContext, paramString, paramInt).execute(new Void[0]);
    }
  }
  
  public Drawable getResource(Context paramContext, int paramInt)
  {
    BitmapDrawable localBitmapDrawable = (BitmapDrawable)this.mResources.get(Integer.valueOf(paramInt));
    if (localBitmapDrawable != null) {
      return localBitmapDrawable;
    }
    return getStaticResource(paramContext, paramInt);
  }
  
  public void releaseInstance()
  {
    Iterator localIterator = this.mResources.entrySet().iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        if (($assertionsDisabled) || (this.mResources.size() == 0)) {
          break;
        }
        throw new AssertionError();
      }
      Object localObject = (Map.Entry)localIterator.next();
      localIterator.remove();
      localObject = (BitmapDrawable)((Map.Entry)localObject).getValue();
    }
    System.gc();
  }
  
  private class FetchImageTask
    extends AsyncTask<Void, Void, Boolean>
  {
    Context mContext;
    int mResourceId;
    String mUrl;
    
    public FetchImageTask(Context paramContext, String paramString, int paramInt)
    {
      this.mContext = paramContext;
      this.mUrl = paramString;
      this.mResourceId = paramInt;
      Log.i("Fetching: " + this.mUrl);
    }
    
    private Drawable fetchImage(String paramString)
    {
      try
      {
        Bitmap localBitmap = BitmapFactory.decodeStream((InputStream)new URL(paramString).getContent());
        if (localBitmap != null)
        {
          Object localObject = this.mContext.getResources().getDisplayMetrics();
          int i = localBitmap.getWidth();
          int j = localBitmap.getHeight();
          int k = (int)TypedValue.applyDimension(1, i, (DisplayMetrics)localObject);
          int m = (int)TypedValue.applyDimension(1, j, (DisplayMetrics)localObject);
          if (k == i)
          {
            localObject = localBitmap;
            if (m == j) {}
          }
          else
          {
            localObject = Bitmap.createScaledBitmap(localBitmap, k, m, false);
          }
          localObject = new BitmapDrawable(this.mContext.getResources(), (Bitmap)localObject);
          return (Drawable)localObject;
        }
      }
      catch (Exception localException)
      {
        Log.e("Cannot fetch image:" + paramString, localException);
      }
      return null;
    }
    
    protected Boolean doInBackground(Void... paramVarArgs)
    {
      Object localObject = null;
      paramVarArgs = (Void[])localObject;
      if (this.mUrl != null)
      {
        paramVarArgs = (Void[])localObject;
        if (this.mUrl.length() > 0) {
          paramVarArgs = fetchImage(this.mUrl);
        }
      }
      if (paramVarArgs != null)
      {
        ResourceManager.this.mResources.put(Integer.valueOf(this.mResourceId), paramVarArgs);
        return Boolean.valueOf(true);
      }
      return Boolean.valueOf(false);
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      super.onPostExecute(paramBoolean);
      Log.i("Fetched: " + this.mUrl);
      paramBoolean = ResourceManager.this.mHandler.obtainMessage(100, this.mResourceId, 0);
      ResourceManager.this.mHandler.sendMessage(paramBoolean);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\video\ResourceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */