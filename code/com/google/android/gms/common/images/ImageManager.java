package com.google.android.gms.common.images;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.internal.ed;
import com.google.android.gms.internal.ev;
import com.google.android.gms.internal.fr;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ImageManager
{
  private static final Object Ar = new Object();
  private static HashSet<Uri> As = new HashSet();
  private static ImageManager At;
  private static ImageManager Au;
  private final ExecutorService Av;
  private final b Aw;
  private final Map<a, ImageReceiver> Ax;
  private final Map<Uri, ImageReceiver> Ay;
  private final Context mContext;
  private final Handler mHandler;
  
  private ImageManager(Context paramContext, boolean paramBoolean)
  {
    this.mContext = paramContext.getApplicationContext();
    this.mHandler = new Handler(Looper.getMainLooper());
    this.Av = Executors.newFixedThreadPool(4);
    if (paramBoolean)
    {
      this.Aw = new b(this.mContext);
      if (fr.eM()) {
        dL();
      }
    }
    for (;;)
    {
      this.Ax = new HashMap();
      this.Ay = new HashMap();
      return;
      this.Aw = null;
    }
  }
  
  private Bitmap a(a.a parama)
  {
    if (this.Aw == null) {
      return null;
    }
    return (Bitmap)this.Aw.get(parama);
  }
  
  public static ImageManager a(Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (Au == null) {
        Au = new ImageManager(paramContext, true);
      }
      return Au;
    }
    if (At == null) {
      At = new ImageManager(paramContext, false);
    }
    return At;
  }
  
  private boolean b(a parama)
  {
    ed.ac("ImageManager.cleanupHashMaps() must be called in the main thread");
    if (parama.AI == 1) {
      return true;
    }
    ImageReceiver localImageReceiver = (ImageReceiver)this.Ax.get(parama);
    if (localImageReceiver == null) {
      return true;
    }
    if (localImageReceiver.AA) {
      return false;
    }
    this.Ax.remove(parama);
    localImageReceiver.d(parama);
    return true;
  }
  
  public static ImageManager create(Context paramContext)
  {
    return a(paramContext, false);
  }
  
  private void dL()
  {
    this.mContext.registerComponentCallbacks(new e(this.Aw));
  }
  
  public void a(a parama)
  {
    ed.ac("ImageManager.loadImage() must be called in the main thread");
    boolean bool = b(parama);
    parama = new d(parama);
    if (bool)
    {
      parama.run();
      return;
    }
    this.mHandler.post(parama);
  }
  
  public void loadImage(ImageView paramImageView, int paramInt)
  {
    a locala = new a(paramInt);
    locala.a(paramImageView);
    a(locala);
  }
  
  public void loadImage(ImageView paramImageView, Uri paramUri)
  {
    paramUri = new a(paramUri);
    paramUri.a(paramImageView);
    a(paramUri);
  }
  
  public void loadImage(ImageView paramImageView, Uri paramUri, int paramInt)
  {
    paramUri = new a(paramUri);
    paramUri.L(paramInt);
    paramUri.a(paramImageView);
    a(paramUri);
  }
  
  public void loadImage(OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri)
  {
    paramUri = new a(paramUri);
    paramUri.a(paramOnImageLoadedListener);
    a(paramUri);
  }
  
  public void loadImage(OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri, int paramInt)
  {
    paramUri = new a(paramUri);
    paramUri.L(paramInt);
    paramUri.a(paramOnImageLoadedListener);
    a(paramUri);
  }
  
  private final class ImageReceiver
    extends ResultReceiver
  {
    boolean AA = false;
    private final ArrayList<a> Az;
    private final Uri mUri;
    
    ImageReceiver(Uri paramUri)
    {
      super();
      this.mUri = paramUri;
      this.Az = new ArrayList();
    }
    
    public void c(a parama)
    {
      if (!this.AA) {}
      for (boolean bool = true;; bool = false)
      {
        ed.a(bool, "Cannot add an ImageRequest when mHandlingRequests is true");
        ed.ac("ImageReceiver.addImageRequest() must be called in the main thread");
        this.Az.add(parama);
        return;
      }
    }
    
    public void d(a parama)
    {
      if (!this.AA) {}
      for (boolean bool = true;; bool = false)
      {
        ed.a(bool, "Cannot remove an ImageRequest when mHandlingRequests is true");
        ed.ac("ImageReceiver.removeImageRequest() must be called in the main thread");
        this.Az.remove(parama);
        return;
      }
    }
    
    public void dN()
    {
      Intent localIntent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
      localIntent.putExtra("com.google.android.gms.extras.uri", this.mUri);
      localIntent.putExtra("com.google.android.gms.extras.resultReceiver", this);
      localIntent.putExtra("com.google.android.gms.extras.priority", 3);
      ImageManager.a(ImageManager.this).sendBroadcast(localIntent);
    }
    
    public void onReceiveResult(int paramInt, Bundle paramBundle)
    {
      paramBundle = (ParcelFileDescriptor)paramBundle.getParcelable("com.google.android.gms.extra.fileDescriptor");
      ImageManager.d(ImageManager.this).execute(new ImageManager.c(ImageManager.this, this.mUri, paramBundle));
    }
  }
  
  public static abstract interface OnImageLoadedListener
  {
    public abstract void onImageLoaded(Uri paramUri, Drawable paramDrawable, boolean paramBoolean);
  }
  
  private static final class a
  {
    static int a(ActivityManager paramActivityManager)
    {
      return paramActivityManager.getLargeMemoryClass();
    }
  }
  
  private static final class b
    extends ev<a.a, Bitmap>
  {
    public b(Context paramContext)
    {
      super();
    }
    
    private static int w(Context paramContext)
    {
      ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
      if ((paramContext.getApplicationInfo().flags & 0x100000) != 0)
      {
        i = 1;
        if ((i == 0) || (!fr.eJ())) {
          break label55;
        }
      }
      label55:
      for (int i = ImageManager.a.a(localActivityManager);; i = localActivityManager.getMemoryClass())
      {
        return (int)(i * 1048576 * 0.33F);
        i = 0;
        break;
      }
    }
    
    protected int a(a.a parama, Bitmap paramBitmap)
    {
      return paramBitmap.getHeight() * paramBitmap.getRowBytes();
    }
    
    protected void a(boolean paramBoolean, a.a parama, Bitmap paramBitmap1, Bitmap paramBitmap2)
    {
      super.entryRemoved(paramBoolean, parama, paramBitmap1, paramBitmap2);
    }
  }
  
  private final class c
    implements Runnable
  {
    private final ParcelFileDescriptor AC;
    private final Uri mUri;
    
    public c(Uri paramUri, ParcelFileDescriptor paramParcelFileDescriptor)
    {
      this.mUri = paramUri;
      this.AC = paramParcelFileDescriptor;
    }
    
    public void run()
    {
      ed.ad("LoadBitmapFromDiskRunnable can't be executed in the main thread");
      boolean bool1 = false;
      boolean bool2 = false;
      Bitmap localBitmap = null;
      CountDownLatch localCountDownLatch = null;
      if (this.AC != null) {}
      try
      {
        localBitmap = BitmapFactory.decodeFileDescriptor(this.AC.getFileDescriptor());
        bool1 = bool2;
        Object localObject;
        return;
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
        try
        {
          for (;;)
          {
            this.AC.close();
            localCountDownLatch = new CountDownLatch(1);
            ImageManager.e(ImageManager.this).post(new ImageManager.f(ImageManager.this, this.mUri, localBitmap, bool1, localCountDownLatch));
            try
            {
              localCountDownLatch.await();
              return;
            }
            catch (InterruptedException localInterruptedException)
            {
              Log.w("ImageManager", "Latch interrupted while posting " + this.mUri);
            }
            localOutOfMemoryError = localOutOfMemoryError;
            Log.e("ImageManager", "OOM while loading bitmap for uri: " + this.mUri, localOutOfMemoryError);
            bool1 = true;
            localObject = localCountDownLatch;
          }
        }
        catch (IOException localIOException)
        {
          for (;;)
          {
            Log.e("ImageManager", "closed failed", localIOException);
          }
        }
      }
    }
  }
  
  private final class d
    implements Runnable
  {
    private final a AD;
    
    public d(a parama)
    {
      this.AD = parama;
    }
    
    public void run()
    {
      ed.ac("LoadImageRunnable must be executed on the main thread");
      ImageManager.a(ImageManager.this, this.AD);
      a.a locala = this.AD.AF;
      if (locala.uri == null)
      {
        this.AD.b(ImageManager.a(ImageManager.this), true);
        return;
      }
      Object localObject1 = ImageManager.a(ImageManager.this, locala);
      if (localObject1 != null)
      {
        this.AD.a(ImageManager.a(ImageManager.this), (Bitmap)localObject1, true);
        return;
      }
      this.AD.x(ImageManager.a(ImageManager.this));
      ??? = (ImageManager.ImageReceiver)ImageManager.b(ImageManager.this).get(locala.uri);
      localObject1 = ???;
      if (??? == null)
      {
        localObject1 = new ImageManager.ImageReceiver(ImageManager.this, locala.uri);
        ImageManager.b(ImageManager.this).put(locala.uri, localObject1);
      }
      ((ImageManager.ImageReceiver)localObject1).c(this.AD);
      if (this.AD.AI != 1) {
        ImageManager.c(ImageManager.this).put(this.AD, localObject1);
      }
      synchronized (ImageManager.dd())
      {
        if (!ImageManager.dM().contains(locala.uri))
        {
          ImageManager.dM().add(locala.uri);
          ((ImageManager.ImageReceiver)localObject1).dN();
        }
        return;
      }
    }
  }
  
  private static final class e
    implements ComponentCallbacks2
  {
    private final ImageManager.b Aw;
    
    public e(ImageManager.b paramb)
    {
      this.Aw = paramb;
    }
    
    public void onConfigurationChanged(Configuration paramConfiguration) {}
    
    public void onLowMemory()
    {
      this.Aw.evictAll();
    }
    
    public void onTrimMemory(int paramInt)
    {
      if (paramInt >= 60) {
        this.Aw.evictAll();
      }
      while (paramInt < 20) {
        return;
      }
      this.Aw.trimToSize(this.Aw.size() / 2);
    }
  }
  
  private final class f
    implements Runnable
  {
    private boolean AE;
    private final Bitmap mBitmap;
    private final Uri mUri;
    private final CountDownLatch zf;
    
    public f(Uri paramUri, Bitmap paramBitmap, boolean paramBoolean, CountDownLatch paramCountDownLatch)
    {
      this.mUri = paramUri;
      this.mBitmap = paramBitmap;
      this.AE = paramBoolean;
      this.zf = paramCountDownLatch;
    }
    
    private void a(ImageManager.ImageReceiver paramImageReceiver, boolean paramBoolean)
    {
      paramImageReceiver.AA = true;
      ArrayList localArrayList = ImageManager.ImageReceiver.a(paramImageReceiver);
      int j = localArrayList.size();
      int i = 0;
      if (i < j)
      {
        a locala = (a)localArrayList.get(i);
        if (paramBoolean) {
          locala.a(ImageManager.a(ImageManager.this), this.mBitmap, false);
        }
        for (;;)
        {
          if (locala.AI != 1) {
            ImageManager.c(ImageManager.this).remove(locala);
          }
          i += 1;
          break;
          locala.b(ImageManager.a(ImageManager.this), false);
        }
      }
      paramImageReceiver.AA = false;
    }
    
    public void run()
    {
      ed.ac("OnBitmapLoadedRunnable must be executed in the main thread");
      boolean bool;
      if (this.mBitmap != null) {
        bool = true;
      }
      while (ImageManager.f(ImageManager.this) != null) {
        if (this.AE)
        {
          ImageManager.f(ImageManager.this).evictAll();
          System.gc();
          this.AE = false;
          ImageManager.e(ImageManager.this).post(this);
          return;
          bool = false;
        }
        else if (bool)
        {
          ImageManager.f(ImageManager.this).put(new a.a(this.mUri), this.mBitmap);
        }
      }
      ??? = (ImageManager.ImageReceiver)ImageManager.b(ImageManager.this).remove(this.mUri);
      if (??? != null) {
        a((ImageManager.ImageReceiver)???, bool);
      }
      this.zf.countDown();
      synchronized (ImageManager.dd())
      {
        ImageManager.dM().remove(this.mUri);
        return;
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\images\ImageManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */