package com.nostra13.universalimageloader.core;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import com.nostra13.universalimageloader.cache.disc.DiscCacheAware;
import com.nostra13.universalimageloader.cache.memory.MemoryCacheAware;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoader
{
  private static final String ERROR_IMAGEVIEW_CONTEXT = "ImageView context must be of Activity typeIf you create ImageView in code you must pass your current activity in ImageView constructor (e.g. new ImageView(MyActivity.this); or new ImageView(getActivity())).";
  private static final String ERROR_INIT_CONFIG_WITH_NULL = "ImageLoader configuration can not be initialized with null";
  private static final String ERROR_NOT_INIT = "ImageLoader must be init with configuration before using";
  private static final String ERROR_WRONG_ARGUMENTS = "Wrong arguments were passed to displayImage() method (image URL and ImageView reference are required)";
  private static final String LOG_CACHE_IMAGE_IN_MEMORY = "Cache image in memory [%s]";
  private static final String LOG_CACHE_IMAGE_ON_DISC = "Cache image on disc [%s]";
  private static final String LOG_DISPLAY_IMAGE_IN_IMAGEVIEW = "Display image in ImageView [%s]";
  private static final String LOG_LOAD_IMAGE_FROM_DISC_CACHE = "Load image from disc cache [%s]";
  private static final String LOG_LOAD_IMAGE_FROM_INTERNET = "Load image from Internet [%s]";
  private static final String LOG_LOAD_IMAGE_FROM_MEMORY_CACHE = "Load image from memory cache [%s]";
  private static final String LOG_START_DISPLAY_IMAGE_TASK = "Start display image task [%s]";
  public static final String TAG = ImageLoader.class.getSimpleName();
  private static volatile ImageLoader instance;
  private Map<ImageView, String> cacheKeyForImageView = Collections.synchronizedMap(new WeakHashMap());
  private ExecutorService cachedImageLoadingExecutor;
  private ImageLoaderConfiguration configuration;
  private ImageLoadingListener emptyListener;
  private ExecutorService imageLoadingExecutor;
  private boolean loggingEnabled = false;
  
  private void checkExecutors()
  {
    if ((this.imageLoadingExecutor == null) || (this.imageLoadingExecutor.isShutdown())) {
      this.imageLoadingExecutor = Executors.newFixedThreadPool(this.configuration.threadPoolSize, this.configuration.displayImageThreadFactory);
    }
    if ((this.cachedImageLoadingExecutor == null) || (this.cachedImageLoadingExecutor.isShutdown())) {
      this.cachedImageLoadingExecutor = Executors.newSingleThreadExecutor(this.configuration.displayImageThreadFactory);
    }
  }
  
  private ImageSize getImageSizeScaleTo(ImageView paramImageView)
  {
    int k = -1;
    m = -1;
    try
    {
      localObject = ImageView.class.getDeclaredField("mMaxWidth");
      Field localField = ImageView.class.getDeclaredField("mMaxHeight");
      ((Field)localObject).setAccessible(true);
      localField.setAccessible(true);
      j = ((Integer)((Field)localObject).get(paramImageView)).intValue();
      n = ((Integer)localField.get(paramImageView)).intValue();
      i = k;
      if (j >= 0)
      {
        i = k;
        if (j < Integer.MAX_VALUE) {
          i = j;
        }
      }
      j = m;
      k = i;
      if (n >= 0)
      {
        j = m;
        k = i;
        if (n < Integer.MAX_VALUE)
        {
          j = n;
          k = i;
        }
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject;
        int n;
        int i;
        Log.e(TAG, localException.getMessage(), localException);
        int j = m;
      }
    }
    n = j;
    m = k;
    if (k < 0)
    {
      n = j;
      m = k;
      if (j < 0)
      {
        localObject = paramImageView.getLayoutParams();
        m = ((ViewGroup.LayoutParams)localObject).width;
        n = ((ViewGroup.LayoutParams)localObject).height;
      }
    }
    j = n;
    i = m;
    if (m < 0)
    {
      j = n;
      i = m;
      if (n < 0)
      {
        k = this.configuration.maxImageWidthForMemoryCache;
        m = this.configuration.maxImageHeightForMemoryCache;
        n = paramImageView.getContext().getResources().getConfiguration().orientation;
        if ((n != 1) || (k <= m))
        {
          j = m;
          i = k;
          if (n == 2)
          {
            j = m;
            i = k;
            if (k >= m) {}
          }
        }
        else
        {
          i = m;
          j = k;
        }
      }
    }
    return new ImageSize(i, j);
  }
  
  public static ImageLoader getInstance()
  {
    if (instance == null) {}
    try
    {
      if (instance == null) {
        instance = new ImageLoader();
      }
      return instance;
    }
    finally {}
  }
  
  public void clearDiscCache()
  {
    if (this.configuration != null) {
      this.configuration.discCache.clear();
    }
  }
  
  public void clearMemoryCache()
  {
    if (this.configuration != null) {
      this.configuration.memoryCache.clear();
    }
  }
  
  public void displayImage(String paramString, ImageView paramImageView)
  {
    displayImage(paramString, paramImageView, null, null);
  }
  
  public void displayImage(String paramString, ImageView paramImageView, DisplayImageOptions paramDisplayImageOptions)
  {
    displayImage(paramString, paramImageView, paramDisplayImageOptions, null);
  }
  
  public void displayImage(String paramString, ImageView paramImageView, DisplayImageOptions paramDisplayImageOptions, ImageLoadingListener paramImageLoadingListener)
  {
    if (this.configuration == null) {
      throw new RuntimeException("ImageLoader must be init with configuration before using");
    }
    if ((paramString == null) || (paramString.length() == 0) || (paramImageView == null))
    {
      Log.w(TAG, "Wrong arguments were passed to displayImage() method (image URL and ImageView reference are required)");
      return;
    }
    ImageLoadingListener localImageLoadingListener = paramImageLoadingListener;
    if (paramImageLoadingListener == null) {
      localImageLoadingListener = this.emptyListener;
    }
    paramImageLoadingListener = paramDisplayImageOptions;
    if (paramDisplayImageOptions == null) {
      paramImageLoadingListener = this.configuration.defaultDisplayImageOptions;
    }
    paramDisplayImageOptions = getImageSizeScaleTo(paramImageView);
    String str = MemoryCacheKeyUtil.generateKey(paramString, paramDisplayImageOptions);
    this.cacheKeyForImageView.put(paramImageView, str);
    Bitmap localBitmap = (Bitmap)this.configuration.memoryCache.get(str);
    if ((localBitmap != null) && (!localBitmap.isRecycled()))
    {
      if (this.loggingEnabled) {
        Log.i(TAG, String.format("Load image from memory cache [%s]", new Object[] { str }));
      }
      localImageLoadingListener.onLoadingStarted();
      paramImageView.setImageBitmap(localBitmap);
      localImageLoadingListener.onLoadingComplete();
      return;
    }
    localImageLoadingListener.onLoadingStarted();
    checkExecutors();
    paramString = new DisplayImageTask(new ImageLoadingInfo(paramString, paramImageView, paramDisplayImageOptions, paramImageLoadingListener, localImageLoadingListener));
    if (paramString.isImageCachedOnDisc()) {
      this.cachedImageLoadingExecutor.submit(paramString);
    }
    while (paramImageLoadingListener.isShowStubImage())
    {
      paramImageView.setImageResource(paramImageLoadingListener.getStubImage().intValue());
      return;
      this.imageLoadingExecutor.submit(paramString);
    }
    paramImageView.setImageBitmap(null);
  }
  
  public void displayImage(String paramString, ImageView paramImageView, ImageLoadingListener paramImageLoadingListener)
  {
    displayImage(paramString, paramImageView, null, paramImageLoadingListener);
  }
  
  public void enableLogging()
  {
    this.loggingEnabled = true;
  }
  
  public void init(ImageLoaderConfiguration paramImageLoaderConfiguration)
  {
    if (paramImageLoaderConfiguration == null) {
      try
      {
        throw new IllegalArgumentException("ImageLoader configuration can not be initialized with null");
      }
      finally {}
    }
    if (this.configuration == null)
    {
      this.configuration = paramImageLoaderConfiguration;
      this.emptyListener = new EmptyListener(null);
    }
  }
  
  public void stop()
  {
    if (this.imageLoadingExecutor != null) {
      this.imageLoadingExecutor.shutdown();
    }
    if (this.cachedImageLoadingExecutor != null) {
      this.cachedImageLoadingExecutor.shutdown();
    }
  }
  
  private class DisplayBitmapTask
    implements Runnable
  {
    private final Bitmap bitmap;
    private final ImageLoader.ImageLoadingInfo imageLoadingInfo;
    
    public DisplayBitmapTask(ImageLoader.ImageLoadingInfo paramImageLoadingInfo, Bitmap paramBitmap)
    {
      this.bitmap = paramBitmap;
      this.imageLoadingInfo = paramImageLoadingInfo;
    }
    
    public void run()
    {
      if (this.imageLoadingInfo.isConsistent())
      {
        if (ImageLoader.this.loggingEnabled) {
          Log.i(ImageLoader.TAG, String.format("Display image in ImageView [%s]", new Object[] { ImageLoader.ImageLoadingInfo.access$400(this.imageLoadingInfo) }));
        }
        ImageLoader.ImageLoadingInfo.access$1100(this.imageLoadingInfo).setImageBitmap(this.bitmap);
        ImageLoader.ImageLoadingInfo.access$1000(this.imageLoadingInfo).onLoadingComplete();
      }
    }
  }
  
  private class DisplayImageTask
    implements Runnable
  {
    private final ImageLoader.ImageLoadingInfo imageLoadingInfo;
    
    public DisplayImageTask(ImageLoader.ImageLoadingInfo paramImageLoadingInfo)
    {
      this.imageLoadingInfo = paramImageLoadingInfo;
    }
    
    private Bitmap decodeImage(URL paramURL)
      throws IOException
    {
      try
      {
        paramURL = new ImageDecoder(paramURL, ImageLoader.ImageLoadingInfo.access$800(this.imageLoadingInfo), ImageLoader.ImageLoadingInfo.access$500(this.imageLoadingInfo).getDecodingType()).decodeFile();
        return paramURL;
      }
      catch (OutOfMemoryError paramURL)
      {
        Log.e(ImageLoader.TAG, paramURL.getMessage(), paramURL);
        fireImageLoadingFailedEvent(FailReason.MEMORY_OVERFLOW);
      }
      return null;
    }
    
    private void fireImageLoadingFailedEvent(final FailReason paramFailReason)
    {
      tryRunOnUiThread(new Runnable()
      {
        public void run()
        {
          ImageLoader.ImageLoadingInfo.access$1000(ImageLoader.DisplayImageTask.this.imageLoadingInfo).onLoadingFailed(paramFailReason);
        }
      });
    }
    
    private boolean isImageCachedOnDisc()
    {
      return ImageLoader.this.configuration.discCache.get(ImageLoader.ImageLoadingInfo.access$700(this.imageLoadingInfo)).exists();
    }
    
    private Bitmap loadBitmap()
    {
      File localFile = ImageLoader.this.configuration.discCache.get(ImageLoader.ImageLoadingInfo.access$700(this.imageLoadingInfo));
      Object localObject5 = null;
      localObject4 = null;
      Object localObject1 = localObject4;
      Object localObject2 = localObject5;
      try
      {
        if (localFile.exists())
        {
          localObject1 = localObject4;
          localObject2 = localObject5;
          if (ImageLoader.this.loggingEnabled)
          {
            localObject1 = localObject4;
            localObject2 = localObject5;
            Log.i(ImageLoader.TAG, String.format("Load image from disc cache [%s]", new Object[] { ImageLoader.ImageLoadingInfo.access$400(this.imageLoadingInfo) }));
          }
          localObject1 = localObject4;
          localObject2 = localObject5;
          localObject3 = decodeImage(localFile.toURL());
          if (localObject3 != null) {
            return (Bitmap)localObject3;
          }
        }
        localObject1 = localObject4;
        localObject2 = localObject5;
        if (ImageLoader.this.loggingEnabled)
        {
          localObject1 = localObject4;
          localObject2 = localObject5;
          Log.i(ImageLoader.TAG, String.format("Load image from Internet [%s]", new Object[] { ImageLoader.ImageLoadingInfo.access$400(this.imageLoadingInfo) }));
        }
        localObject1 = localObject4;
        localObject2 = localObject5;
        if (ImageLoader.ImageLoadingInfo.access$500(this.imageLoadingInfo).isCacheOnDisc())
        {
          localObject1 = localObject4;
          localObject2 = localObject5;
          if (ImageLoader.this.loggingEnabled)
          {
            localObject1 = localObject4;
            localObject2 = localObject5;
            Log.i(ImageLoader.TAG, String.format("Cache image on disc [%s]", new Object[] { ImageLoader.ImageLoadingInfo.access$400(this.imageLoadingInfo) }));
          }
          localObject1 = localObject4;
          localObject2 = localObject5;
          saveImageOnDisc(localFile);
          localObject1 = localObject4;
          localObject2 = localObject5;
        }
        for (Object localObject3 = localFile.toURL();; localObject3 = new URL(ImageLoader.ImageLoadingInfo.access$700(this.imageLoadingInfo)))
        {
          localObject1 = localObject4;
          localObject2 = localObject5;
          localObject3 = decodeImage((URL)localObject3);
          localObject1 = localObject3;
          localObject2 = localObject3;
          localObject4 = localObject3;
          if (!ImageLoader.ImageLoadingInfo.access$500(this.imageLoadingInfo).isCacheOnDisc()) {
            break;
          }
          localObject1 = localObject3;
          localObject2 = localObject3;
          ImageLoader.this.configuration.discCache.put(ImageLoader.ImageLoadingInfo.access$700(this.imageLoadingInfo), localFile);
          localObject4 = localObject3;
          break;
          localObject1 = localObject4;
          localObject2 = localObject5;
        }
        return (Bitmap)localObject4;
      }
      catch (IOException localIOException)
      {
        Log.e(ImageLoader.TAG, localIOException.getMessage(), localIOException);
        fireImageLoadingFailedEvent(FailReason.IO_ERROR);
        localObject4 = localObject1;
        if (localFile.exists())
        {
          localFile.delete();
          localObject4 = localObject1;
        }
      }
      catch (Throwable localThrowable)
      {
        Log.e(ImageLoader.TAG, localThrowable.getMessage(), localThrowable);
        fireImageLoadingFailedEvent(FailReason.UNKNOWN);
        localObject4 = localIOException;
      }
    }
    
    /* Error */
    private void saveImageOnDisc(File paramFile)
      throws java.net.MalformedURLException, IOException
    {
      // Byte code:
      //   0: new 166	java/net/URL
      //   3: dup
      //   4: aload_0
      //   5: getfield 24	com/nostra13/universalimageloader/core/ImageLoader$DisplayImageTask:imageLoadingInfo	Lcom/nostra13/universalimageloader/core/ImageLoader$ImageLoadingInfo;
      //   8: invokestatic 110	com/nostra13/universalimageloader/core/ImageLoader$ImageLoadingInfo:access$700	(Lcom/nostra13/universalimageloader/core/ImageLoader$ImageLoadingInfo;)Ljava/lang/String;
      //   11: invokespecial 169	java/net/URL:<init>	(Ljava/lang/String;)V
      //   14: invokevirtual 186	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   17: checkcast 188	java/net/HttpURLConnection
      //   20: astore_2
      //   21: aload_2
      //   22: aload_0
      //   23: getfield 19	com/nostra13/universalimageloader/core/ImageLoader$DisplayImageTask:this$0	Lcom/nostra13/universalimageloader/core/ImageLoader;
      //   26: invokestatic 100	com/nostra13/universalimageloader/core/ImageLoader:access$600	(Lcom/nostra13/universalimageloader/core/ImageLoader;)Lcom/nostra13/universalimageloader/core/ImageLoaderConfiguration;
      //   29: getfield 192	com/nostra13/universalimageloader/core/ImageLoaderConfiguration:httpConnectTimeout	I
      //   32: invokevirtual 196	java/net/HttpURLConnection:setConnectTimeout	(I)V
      //   35: aload_2
      //   36: aload_0
      //   37: getfield 19	com/nostra13/universalimageloader/core/ImageLoader$DisplayImageTask:this$0	Lcom/nostra13/universalimageloader/core/ImageLoader;
      //   40: invokestatic 100	com/nostra13/universalimageloader/core/ImageLoader:access$600	(Lcom/nostra13/universalimageloader/core/ImageLoader;)Lcom/nostra13/universalimageloader/core/ImageLoaderConfiguration;
      //   43: getfield 199	com/nostra13/universalimageloader/core/ImageLoaderConfiguration:httpReadTimeout	I
      //   46: invokevirtual 202	java/net/HttpURLConnection:setReadTimeout	(I)V
      //   49: new 204	java/io/BufferedInputStream
      //   52: dup
      //   53: aload_2
      //   54: invokevirtual 208	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   57: invokespecial 211	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
      //   60: astore_2
      //   61: new 213	java/io/FileOutputStream
      //   64: dup
      //   65: aload_1
      //   66: invokespecial 215	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   69: astore_1
      //   70: aload_2
      //   71: aload_1
      //   72: invokestatic 221	com/nostra13/universalimageloader/utils/FileUtils:copyStream	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
      //   75: aload_1
      //   76: invokevirtual 226	java/io/OutputStream:close	()V
      //   79: aload_2
      //   80: invokevirtual 227	java/io/BufferedInputStream:close	()V
      //   83: return
      //   84: astore_3
      //   85: aload_1
      //   86: invokevirtual 226	java/io/OutputStream:close	()V
      //   89: aload_3
      //   90: athrow
      //   91: astore_1
      //   92: aload_2
      //   93: invokevirtual 227	java/io/BufferedInputStream:close	()V
      //   96: aload_1
      //   97: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	98	0	this	DisplayImageTask
      //   0	98	1	paramFile	File
      //   20	73	2	localObject1	Object
      //   84	6	3	localObject2	Object
      // Exception table:
      //   from	to	target	type
      //   70	75	84	finally
      //   61	70	91	finally
      //   75	79	91	finally
      //   85	91	91	finally
    }
    
    private void tryRunOnUiThread(Runnable paramRunnable)
    {
      Context localContext = ImageLoader.ImageLoadingInfo.access$1100(this.imageLoadingInfo).getContext();
      if ((localContext instanceof Activity))
      {
        ((Activity)localContext).runOnUiThread(paramRunnable);
        return;
      }
      Log.e(ImageLoader.TAG, "ImageView context must be of Activity typeIf you create ImageView in code you must pass your current activity in ImageView constructor (e.g. new ImageView(MyActivity.this); or new ImageView(getActivity())).");
      ImageLoader.ImageLoadingInfo.access$1000(this.imageLoadingInfo).onLoadingFailed(FailReason.WRONG_CONTEXT);
    }
    
    public void run()
    {
      if (ImageLoader.this.loggingEnabled) {
        Log.i(ImageLoader.TAG, String.format("Start display image task [%s]", new Object[] { ImageLoader.ImageLoadingInfo.access$400(this.imageLoadingInfo) }));
      }
      if (!this.imageLoadingInfo.isConsistent()) {}
      Bitmap localBitmap;
      do
      {
        return;
        localBitmap = loadBitmap();
      } while ((localBitmap == null) || (!this.imageLoadingInfo.isConsistent()));
      if (ImageLoader.ImageLoadingInfo.access$500(this.imageLoadingInfo).isCacheInMemory())
      {
        if (ImageLoader.this.loggingEnabled) {
          Log.i(ImageLoader.TAG, String.format("Cache image in memory [%s]", new Object[] { ImageLoader.ImageLoadingInfo.access$400(this.imageLoadingInfo) }));
        }
        ImageLoader.this.configuration.memoryCache.put(ImageLoader.ImageLoadingInfo.access$400(this.imageLoadingInfo), localBitmap);
      }
      tryRunOnUiThread(new ImageLoader.DisplayBitmapTask(ImageLoader.this, this.imageLoadingInfo, localBitmap));
    }
  }
  
  private class EmptyListener
    implements ImageLoadingListener
  {
    private EmptyListener() {}
    
    public void onLoadingComplete() {}
    
    public void onLoadingFailed(FailReason paramFailReason) {}
    
    public void onLoadingStarted() {}
  }
  
  private final class ImageLoadingInfo
  {
    private final ImageView imageView;
    private final ImageLoadingListener listener;
    private final String memoryCacheKey;
    private final DisplayImageOptions options;
    private final ImageSize targetSize;
    private final String url;
    
    public ImageLoadingInfo(String paramString, ImageView paramImageView, ImageSize paramImageSize, DisplayImageOptions paramDisplayImageOptions, ImageLoadingListener paramImageLoadingListener)
    {
      this.url = paramString;
      this.imageView = paramImageView;
      this.targetSize = paramImageSize;
      this.options = paramDisplayImageOptions;
      this.listener = paramImageLoadingListener;
      this.memoryCacheKey = MemoryCacheKeyUtil.generateKey(paramString, paramImageSize);
    }
    
    boolean isConsistent()
    {
      String str = (String)ImageLoader.this.cacheKeyForImageView.get(this.imageView);
      return (str == null) || (this.memoryCacheKey.equals(str));
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\nostra13\universalimageloader\core\ImageLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */