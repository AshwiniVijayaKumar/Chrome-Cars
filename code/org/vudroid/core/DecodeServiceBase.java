package org.vudroid.core;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.vudroid.core.codec.CodecContext;
import org.vudroid.core.codec.CodecDocument;
import org.vudroid.core.codec.CodecPage;
import org.vudroid.core.utils.PathFromUri;

public class DecodeServiceBase
  implements DecodeService
{
  public static final String DECODE_SERVICE = "ViewDroidDecodeService";
  private static final int PAGE_POOL_SIZE = 16;
  private final CodecContext codecContext;
  private View containerView;
  private ContentResolver contentResolver;
  private final Map<Object, Future<?>> decodingFutures = new ConcurrentHashMap();
  private CodecDocument document;
  private final ExecutorService executorService = Executors.newSingleThreadExecutor();
  private boolean isRecycled;
  private Queue<Integer> pageEvictionQueue = new LinkedList();
  private final HashMap<Integer, SoftReference<CodecPage>> pages = new HashMap();
  
  public DecodeServiceBase(CodecContext paramCodecContext)
  {
    this.codecContext = paramCodecContext;
  }
  
  private float calculateScale(CodecPage paramCodecPage)
  {
    return 1.0F * getTargetWidth() / paramCodecPage.getWidth();
  }
  
  private void finishDecoding(DecodeTask paramDecodeTask, Bitmap paramBitmap)
  {
    updateImage(paramDecodeTask, paramBitmap);
    stopDecoding(Integer.valueOf(paramDecodeTask.pageNumber));
  }
  
  private int getScaledHeight(DecodeTask paramDecodeTask, CodecPage paramCodecPage, float paramFloat)
  {
    return Math.round(getScaledHeight(paramCodecPage, paramFloat) * paramDecodeTask.pageSliceBounds.height());
  }
  
  private int getScaledHeight(CodecPage paramCodecPage, float paramFloat)
  {
    return (int)(paramCodecPage.getHeight() * paramFloat);
  }
  
  private int getScaledWidth(DecodeTask paramDecodeTask, CodecPage paramCodecPage, float paramFloat)
  {
    return Math.round(getScaledWidth(paramCodecPage, paramFloat) * paramDecodeTask.pageSliceBounds.width());
  }
  
  private int getScaledWidth(CodecPage paramCodecPage, float paramFloat)
  {
    return (int)(paramCodecPage.getWidth() * paramFloat);
  }
  
  private int getTargetWidth()
  {
    return this.containerView.getWidth();
  }
  
  private boolean isTaskDead(DecodeTask paramDecodeTask)
  {
    for (;;)
    {
      synchronized (this.decodingFutures)
      {
        if (!this.decodingFutures.containsKey(paramDecodeTask.decodeKey))
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  private void performDecode(DecodeTask paramDecodeTask)
    throws IOException
  {
    if (isTaskDead(paramDecodeTask)) {
      Log.d("ViewDroidDecodeService", "Skipping decode task for page " + paramDecodeTask.pageNumber);
    }
    do
    {
      return;
      Log.d("ViewDroidDecodeService", "Starting decode of page: " + paramDecodeTask.pageNumber);
      localObject = getPage(paramDecodeTask.pageNumber);
      preloadNextPage(paramDecodeTask.pageNumber);
    } while (isTaskDead(paramDecodeTask));
    Log.d("ViewDroidDecodeService", "Start converting map to bitmap");
    float f = calculateScale((CodecPage)localObject) * paramDecodeTask.zoom;
    Object localObject = ((CodecPage)localObject).renderBitmap(getScaledWidth(paramDecodeTask, (CodecPage)localObject, f), getScaledHeight(paramDecodeTask, (CodecPage)localObject, f), paramDecodeTask.pageSliceBounds);
    Log.d("ViewDroidDecodeService", "Converting map to bitmap finished");
    if (isTaskDead(paramDecodeTask))
    {
      ((Bitmap)localObject).recycle();
      return;
    }
    finishDecoding(paramDecodeTask, (Bitmap)localObject);
  }
  
  private void preloadNextPage(int paramInt)
    throws IOException
  {
    paramInt += 1;
    if (paramInt >= getPageCount()) {
      return;
    }
    getPage(paramInt);
  }
  
  private void updateImage(DecodeTask paramDecodeTask, Bitmap paramBitmap)
  {
    paramDecodeTask.decodeCallback.decodeComplete(paramBitmap);
  }
  
  private void waitForDecode(CodecPage paramCodecPage)
  {
    paramCodecPage.waitForDecode();
  }
  
  public void decodePage(Object paramObject, int paramInt, DecodeService.DecodeCallback arg3, float paramFloat, final RectF paramRectF)
  {
    paramRectF = new DecodeTask(paramInt, ???, paramFloat, paramObject, paramRectF, null);
    synchronized (this.decodingFutures)
    {
      if (this.isRecycled) {
        return;
      }
      paramRectF = this.executorService.submit(new Runnable()
      {
        public void run()
        {
          try
          {
            Thread.currentThread().setPriority(4);
            DecodeServiceBase.this.performDecode(paramRectF);
            return;
          }
          catch (IOException localIOException)
          {
            Log.e("ViewDroidDecodeService", "Decode fail", localIOException);
          }
        }
      });
      paramObject = (Future)this.decodingFutures.put(paramObject, paramRectF);
      if (paramObject != null) {
        ((Future)paramObject).cancel(false);
      }
      return;
    }
  }
  
  public int getEffectivePagesHeight()
  {
    CodecPage localCodecPage = getPage(0);
    return getScaledHeight(localCodecPage, calculateScale(localCodecPage));
  }
  
  public int getEffectivePagesWidth()
  {
    CodecPage localCodecPage = getPage(0);
    return getScaledWidth(localCodecPage, calculateScale(localCodecPage));
  }
  
  public CodecPage getPage(int paramInt)
  {
    if ((!this.pages.containsKey(Integer.valueOf(paramInt))) || (((SoftReference)this.pages.get(Integer.valueOf(paramInt))).get() == null))
    {
      this.pages.put(Integer.valueOf(paramInt), new SoftReference(this.document.getPage(paramInt)));
      this.pageEvictionQueue.remove(Integer.valueOf(paramInt));
      this.pageEvictionQueue.offer(Integer.valueOf(paramInt));
      if (this.pageEvictionQueue.size() > 16)
      {
        Object localObject = (Integer)this.pageEvictionQueue.poll();
        localObject = (CodecPage)((SoftReference)this.pages.remove(localObject)).get();
        if (localObject != null) {
          ((CodecPage)localObject).recycle();
        }
      }
    }
    return (CodecPage)((SoftReference)this.pages.get(Integer.valueOf(paramInt))).get();
  }
  
  public int getPageCount()
  {
    return this.document.getPageCount();
  }
  
  public int getPageHeight(int paramInt)
  {
    return getPage(paramInt).getHeight();
  }
  
  public int getPageWidth(int paramInt)
  {
    return getPage(paramInt).getWidth();
  }
  
  public void open(Uri paramUri)
  {
    this.document = this.codecContext.openDocument(PathFromUri.retrieve(this.contentResolver, paramUri));
  }
  
  public void recycle()
  {
    synchronized (this.decodingFutures)
    {
      this.isRecycled = true;
      ??? = this.decodingFutures.keySet().iterator();
      if (((Iterator)???).hasNext()) {
        stopDecoding(((Iterator)???).next());
      }
    }
    this.executorService.submit(new Runnable()
    {
      public void run()
      {
        Iterator localIterator = DecodeServiceBase.this.pages.values().iterator();
        while (localIterator.hasNext())
        {
          CodecPage localCodecPage = (CodecPage)((SoftReference)localIterator.next()).get();
          if (localCodecPage != null) {
            localCodecPage.recycle();
          }
        }
        DecodeServiceBase.this.document.recycle();
        DecodeServiceBase.this.codecContext.recycle();
      }
    });
    this.executorService.shutdown();
  }
  
  public void setContainerView(View paramView)
  {
    this.containerView = paramView;
  }
  
  public void setContentResolver(ContentResolver paramContentResolver)
  {
    this.contentResolver = paramContentResolver;
    this.codecContext.setContentResolver(paramContentResolver);
  }
  
  public void stopDecoding(Object paramObject)
  {
    paramObject = (Future)this.decodingFutures.remove(paramObject);
    if (paramObject != null) {
      ((Future)paramObject).cancel(false);
    }
  }
  
  private class DecodeTask
  {
    private final DecodeService.DecodeCallback decodeCallback;
    private final Object decodeKey;
    private final int pageNumber;
    private final RectF pageSliceBounds;
    private final float zoom;
    
    private DecodeTask(int paramInt, DecodeService.DecodeCallback paramDecodeCallback, float paramFloat, Object paramObject, RectF paramRectF)
    {
      this.pageNumber = paramInt;
      this.decodeCallback = paramDecodeCallback;
      this.zoom = paramFloat;
      this.decodeKey = paramObject;
      this.pageSliceBounds = paramRectF;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\core\DecodeServiceBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */