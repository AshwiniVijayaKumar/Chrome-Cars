package com.joanzapata.pdfview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.SurfaceView;
import com.joanzapata.pdfview.exception.FileNotFoundException;
import com.joanzapata.pdfview.listener.OnDrawListener;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.joanzapata.pdfview.model.PagePart;
import com.joanzapata.pdfview.util.ArrayUtils;
import com.joanzapata.pdfview.util.Constants.Cache;
import com.joanzapata.pdfview.util.FileUtils;
import com.joanzapata.pdfview.util.NumberUtils;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import org.vudroid.core.DecodeService;

public class PDFView
  extends SurfaceView
{
  private static final String TAG = PDFView.class.getSimpleName();
  private AnimationManager animationManager = new AnimationManager(this);
  private CacheManager cacheManager = new CacheManager();
  private int currentFilteredPage;
  private int currentPage;
  private float currentXOffset = 0.0F;
  private float currentYOffset = 0.0F;
  private Paint debugPaint = new Paint();
  private DecodeService decodeService;
  private DecodingAsyncTask decodingAsyncTask;
  private int defaultPage = 0;
  private int documentPageCount;
  private DragPinchManager dragPinchManager = new DragPinchManager(this);
  private int[] filteredUserPageIndexes;
  private int[] filteredUserPages;
  private RectF leftMask;
  private Paint maskPaint;
  private boolean miniMapRequired = false;
  private RectF minimapBounds;
  private RectF minimapScreenBounds;
  private OnDrawListener onDrawListener;
  private OnLoadCompleteListener onLoadCompleteListener;
  private OnPageChangeListener onPageChangeListener;
  private float optimalPageHeight;
  private float optimalPageWidth;
  private int[] originalUserPages;
  private int pageHeight;
  private int pageWidth;
  private Paint paint = new Paint();
  private Paint paintMinimapBack;
  private Paint paintMinimapFront;
  private boolean recycled = true;
  private RenderingAsyncTask renderingAsyncTask;
  private RectF rightMask;
  private State state = State.DEFAULT;
  private boolean swipeVertical = false;
  private boolean userWantsMinimap = false;
  private float zoom = 1.0F;
  
  public PDFView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.debugPaint.setStyle(Paint.Style.STROKE);
    this.paintMinimapBack = new Paint();
    this.paintMinimapBack.setStyle(Paint.Style.FILL);
    this.paintMinimapBack.setColor(-16777216);
    this.paintMinimapBack.setAlpha(50);
    this.paintMinimapFront = new Paint();
    this.paintMinimapFront.setStyle(Paint.Style.FILL);
    this.paintMinimapFront.setColor(-16777216);
    this.paintMinimapFront.setAlpha(50);
    setWillNotDraw(false);
  }
  
  private float calculateCenterOffsetForPage(int paramInt)
  {
    if (this.swipeVertical) {
      return -(paramInt * this.optimalPageHeight) + (getHeight() / 2 - this.optimalPageHeight / 2.0F);
    }
    return -(paramInt * this.optimalPageWidth) + (getWidth() / 2 - this.optimalPageWidth / 2.0F);
  }
  
  private void calculateMasksBounds()
  {
    this.leftMask = new RectF(0.0F, 0.0F, getWidth() / 2 - toCurrentScale(this.optimalPageWidth) / 2.0F, getHeight());
    this.rightMask = new RectF(getWidth() / 2 + toCurrentScale(this.optimalPageWidth) / 2.0F, 0.0F, getWidth(), getHeight());
  }
  
  private void calculateMinimapAreaBounds()
  {
    if (this.minimapBounds == null) {
      return;
    }
    if (this.zoom == 1.0F)
    {
      this.miniMapRequired = false;
      return;
    }
    float f1 = (-this.currentXOffset - toCurrentScale(this.currentFilteredPage * this.optimalPageWidth)) / toCurrentScale(this.optimalPageWidth) * this.minimapBounds.width();
    float f2 = getWidth() / toCurrentScale(this.optimalPageWidth);
    float f3 = this.minimapBounds.width();
    float f4 = -this.currentYOffset / toCurrentScale(this.optimalPageHeight) * this.minimapBounds.height();
    float f5 = getHeight() / toCurrentScale(this.optimalPageHeight);
    float f6 = this.minimapBounds.height();
    this.minimapScreenBounds = new RectF(this.minimapBounds.left + f1, this.minimapBounds.top + f4, this.minimapBounds.left + f1 + f2 * f3, this.minimapBounds.top + f4 + f5 * f6);
    this.minimapScreenBounds.intersect(this.minimapBounds);
    this.miniMapRequired = true;
  }
  
  private void calculateMinimapBounds()
  {
    float f1 = Math.min(200.0F / this.optimalPageWidth, 200.0F / this.optimalPageHeight);
    float f2 = this.optimalPageWidth;
    float f3 = this.optimalPageHeight;
    this.minimapBounds = new RectF(getWidth() - 5 - f2 * f1, 5.0F, getWidth() - 5, 5.0F + f3 * f1);
    calculateMinimapAreaBounds();
  }
  
  private void calculateOptimalWidthAndHeight()
  {
    if ((this.state == State.DEFAULT) || (getWidth() == 0)) {
      return;
    }
    float f2 = getWidth();
    float f3 = getHeight();
    float f5 = this.pageWidth / this.pageHeight;
    float f1 = f2;
    float f4 = (float)Math.floor(f2 / f5);
    f2 = f4;
    if (f4 > f3)
    {
      f2 = f3;
      f1 = (float)Math.floor(f3 * f5);
    }
    this.optimalPageWidth = f1;
    this.optimalPageHeight = f2;
    calculateMasksBounds();
    calculateMinimapBounds();
  }
  
  private int determineValidPageNumberFrom(int paramInt)
  {
    int i;
    if (paramInt <= 0) {
      i = 0;
    }
    do
    {
      do
      {
        return i;
        if (this.originalUserPages == null) {
          break;
        }
        i = paramInt;
      } while (paramInt < this.originalUserPages.length);
      return this.originalUserPages.length - 1;
      i = paramInt;
    } while (paramInt < this.documentPageCount);
    return this.documentPageCount - 1;
  }
  
  private void drawMiniMap(Canvas paramCanvas)
  {
    paramCanvas.drawRect(this.minimapBounds, this.paintMinimapBack);
    paramCanvas.drawRect(this.minimapScreenBounds, this.paintMinimapFront);
  }
  
  private void drawPart(Canvas paramCanvas, PagePart paramPagePart)
  {
    RectF localRectF = paramPagePart.getPageRelativeBounds();
    Bitmap localBitmap = paramPagePart.getRenderedBitmap();
    float f1 = 0.0F;
    float f2 = 0.0F;
    if (this.swipeVertical) {
      f2 = toCurrentScale(paramPagePart.getUserPage() * this.optimalPageHeight);
    }
    for (;;)
    {
      paramCanvas.translate(f1, f2);
      paramPagePart = new Rect(0, 0, localBitmap.getWidth(), localBitmap.getHeight());
      float f3 = toCurrentScale(localRectF.left * this.optimalPageWidth);
      float f4 = toCurrentScale(localRectF.top * this.optimalPageHeight);
      float f5 = toCurrentScale(localRectF.width() * this.optimalPageWidth);
      float f6 = toCurrentScale(localRectF.height() * this.optimalPageHeight);
      localRectF = new RectF((int)f3, (int)f4, (int)(f3 + f5), (int)(f4 + f6));
      f3 = this.currentXOffset + f1;
      f4 = this.currentYOffset + f2;
      if ((localRectF.left + f3 < getWidth()) && (localRectF.right + f3 > 0.0F) && (localRectF.top + f4 < getHeight()) && (localRectF.bottom + f4 > 0.0F)) {
        break;
      }
      paramCanvas.translate(-f1, -f2);
      return;
      f1 = toCurrentScale(paramPagePart.getUserPage() * this.optimalPageWidth);
    }
    paramCanvas.drawBitmap(localBitmap, paramPagePart, localRectF, this.paint);
    paramCanvas.translate(-f1, -f2);
  }
  
  private void load(Uri paramUri, OnLoadCompleteListener paramOnLoadCompleteListener)
  {
    load(paramUri, paramOnLoadCompleteListener, null);
  }
  
  private void load(Uri paramUri, OnLoadCompleteListener paramOnLoadCompleteListener, int[] paramArrayOfInt)
  {
    if (!this.recycled) {
      throw new IllegalStateException("Don't call load on a PDF View without recycling it first.");
    }
    if (paramArrayOfInt != null)
    {
      this.originalUserPages = paramArrayOfInt;
      this.filteredUserPages = ArrayUtils.deleteDuplicatedPages(this.originalUserPages);
      this.filteredUserPageIndexes = ArrayUtils.calculateIndexesInDuplicateArray(this.originalUserPages);
    }
    this.onLoadCompleteListener = paramOnLoadCompleteListener;
    this.decodingAsyncTask = new DecodingAsyncTask(paramUri, this);
    this.decodingAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    this.renderingAsyncTask = new RenderingAsyncTask(this);
    this.renderingAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
  }
  
  private int loadPage(final int paramInt1, final int paramInt2)
  {
    final int i = paramInt1;
    if (this.filteredUserPages != null)
    {
      if ((paramInt1 < 0) || (paramInt1 >= this.filteredUserPages.length)) {
        return 0;
      }
      i = this.filteredUserPages[paramInt1];
    }
    if ((i < 0) || (paramInt1 >= this.documentPageCount)) {
      return 0;
    }
    if (!this.cacheManager.containsThumbnail(paramInt1, i, (int)(this.optimalPageWidth * 0.2F), (int)(this.optimalPageHeight * 0.2F), new RectF(0.0F, 0.0F, 1.0F, 1.0F))) {
      this.renderingAsyncTask.addRenderingTask(paramInt1, i, (int)(this.optimalPageWidth * 0.2F), (int)(this.optimalPageHeight * 0.2F), new RectF(0.0F, 0.0F, 1.0F, 1.0F), true, 0);
    }
    float f2 = 1.0F / this.optimalPageWidth;
    float f1 = 256.0F * (1.0F / this.optimalPageHeight) / this.zoom;
    f2 = 256.0F * f2 / this.zoom;
    int j = (int)Math.ceil(1.0F / f1);
    int k = (int)Math.ceil(1.0F / f2);
    final float f3 = 1.0F / k;
    final float f4 = 1.0F / j;
    f2 = -this.currentXOffset + getWidth() / 2;
    f1 = -this.currentYOffset + getHeight() / 2;
    if (!this.swipeVertical) {
      f2 -= paramInt1 * toCurrentScale(this.optimalPageWidth);
    }
    for (;;)
    {
      f2 /= toCurrentScale(this.optimalPageWidth);
      f1 /= toCurrentScale(this.optimalPageHeight);
      int n = (int)(j * f1);
      int m = (int)(k * f2);
      n = NumberUtils.limit(n, 0, j);
      m = NumberUtils.limit(m, 0, k);
      SpiralLoopManager.SpiralLoopListener local1SpiralLoopListenerImpl = new SpiralLoopManager.SpiralLoopListener()
      {
        int nbItemTreated = 0;
        
        public boolean onLoop(int paramAnonymousInt1, int paramAnonymousInt2)
        {
          float f4 = f3 * paramAnonymousInt2;
          float f5 = f4 * paramAnonymousInt1;
          float f2 = f3;
          float f3 = f4;
          float f7 = 256.0F / f2;
          float f6 = 256.0F / f3;
          float f1 = f2;
          if (f4 + f2 > 1.0F) {
            f1 = 1.0F - f4;
          }
          f2 = f3;
          if (f5 + f3 > 1.0F) {
            f2 = 1.0F - f5;
          }
          f3 = f7 * f1;
          f6 *= f2;
          RectF localRectF = new RectF(f4, f5, f4 + f1, f5 + f2);
          if ((f3 != 0.0F) && (f6 != 0.0F) && (!this.this$0.cacheManager.upPartIfContained(paramInt1, i, f3, f6, localRectF, this.nbItemTreated))) {
            this.this$0.renderingAsyncTask.addRenderingTask(paramInt1, i, f3, f6, localRectF, false, this.nbItemTreated);
          }
          this.nbItemTreated += 1;
          return this.nbItemTreated < paramInt2;
        }
      };
      new SpiralLoopManager(local1SpiralLoopListenerImpl).startLoop(j, k, n, m);
      return local1SpiralLoopListenerImpl.nbItemTreated;
      f1 -= paramInt1 * toCurrentScale(this.optimalPageHeight);
    }
  }
  
  private void setDefaultPage(int paramInt)
  {
    this.defaultPage = paramInt;
  }
  
  private void setOnDrawListener(OnDrawListener paramOnDrawListener)
  {
    this.onDrawListener = paramOnDrawListener;
  }
  
  private void setOnPageChangeListener(OnPageChangeListener paramOnPageChangeListener)
  {
    this.onPageChangeListener = paramOnPageChangeListener;
  }
  
  private void setUserWantsMinimap(boolean paramBoolean)
  {
    this.userWantsMinimap = paramBoolean;
  }
  
  public void enableDoubletap(boolean paramBoolean)
  {
    this.dragPinchManager.enableDoubletap(paramBoolean);
  }
  
  public void enableSwipe(boolean paramBoolean)
  {
    this.dragPinchManager.setSwipeEnabled(paramBoolean);
  }
  
  public Configurator fromAsset(String paramString)
  {
    try
    {
      Configurator localConfigurator = fromFile(FileUtils.fileFromAsset(getContext(), paramString));
      return localConfigurator;
    }
    catch (IOException localIOException)
    {
      throw new FileNotFoundException(paramString + " does not exist.", localIOException);
    }
  }
  
  public Configurator fromFile(File paramFile)
  {
    if (!paramFile.exists()) {
      throw new FileNotFoundException(paramFile.getAbsolutePath() + "does not exist.");
    }
    return new Configurator(Uri.fromFile(paramFile), null);
  }
  
  public int getCurrentPage()
  {
    return this.currentPage;
  }
  
  public float getCurrentXOffset()
  {
    return this.currentXOffset;
  }
  
  public float getCurrentYOffset()
  {
    return this.currentYOffset;
  }
  
  DecodeService getDecodeService()
  {
    return this.decodeService;
  }
  
  public float getOptimalPageWidth()
  {
    return this.optimalPageWidth;
  }
  
  public int getPageCount()
  {
    if (this.originalUserPages != null) {
      return this.originalUserPages.length;
    }
    return this.documentPageCount;
  }
  
  public float getZoom()
  {
    return this.zoom;
  }
  
  public boolean isSwipeVertical()
  {
    return this.swipeVertical;
  }
  
  public boolean isZooming()
  {
    return this.zoom != 1.0F;
  }
  
  public void jumpTo(int paramInt)
  {
    showPage(paramInt - 1);
  }
  
  public void loadComplete(DecodeService paramDecodeService)
  {
    this.decodeService = paramDecodeService;
    this.documentPageCount = paramDecodeService.getPageCount();
    this.pageWidth = paramDecodeService.getPageWidth(0);
    this.pageHeight = paramDecodeService.getPageHeight(0);
    this.state = State.LOADED;
    calculateOptimalWidthAndHeight();
    jumpTo(this.defaultPage);
    if (this.onLoadCompleteListener != null) {
      this.onLoadCompleteListener.loadComplete(this.documentPageCount);
    }
  }
  
  public void loadPages()
  {
    if ((this.optimalPageWidth == 0.0F) || (this.optimalPageHeight == 0.0F)) {
      return;
    }
    this.renderingAsyncTask.removeAllTasks();
    this.cacheManager.makeANewSet();
    int j = this.currentPage;
    if (this.filteredUserPageIndexes != null) {
      j = this.filteredUserPageIndexes[this.currentPage];
    }
    int i = 0;
    int k = 0;
    while ((k <= 1) && (i < Constants.Cache.CACHE_SIZE))
    {
      int m = i + loadPage(j + k, Constants.Cache.CACHE_SIZE - i);
      i = m;
      if (k != 0)
      {
        i = m;
        if (m < Constants.Cache.CACHE_SIZE) {
          i = m + loadPage(j - k, Constants.Cache.CACHE_SIZE - m);
        }
      }
      k += 1;
    }
    invalidate();
  }
  
  public void moveRelativeTo(float paramFloat1, float paramFloat2)
  {
    moveTo(this.currentXOffset + paramFloat1, this.currentYOffset + paramFloat2);
  }
  
  public void moveTo(float paramFloat1, float paramFloat2)
  {
    float f3;
    float f2;
    float f1;
    if (this.swipeVertical) {
      if (toCurrentScale(this.optimalPageWidth) < getWidth())
      {
        f3 = getWidth() / 2 - toCurrentScale(this.optimalPageWidth) / 2.0F;
        if (!isZooming()) {
          break label289;
        }
        if (toCurrentScale(this.optimalPageHeight) >= getHeight()) {
          break label177;
        }
        this.miniMapRequired = false;
        f2 = getHeight() / 2 - toCurrentScale((this.currentFilteredPage + 0.5F) * this.optimalPageHeight);
        f1 = f3;
      }
    }
    for (;;)
    {
      this.currentXOffset = f1;
      this.currentYOffset = f2;
      calculateMinimapAreaBounds();
      invalidate();
      return;
      if (paramFloat1 > 0.0F)
      {
        f3 = 0.0F;
        break;
      }
      f3 = paramFloat1;
      if (toCurrentScale(this.optimalPageWidth) + paramFloat1 >= getWidth()) {
        break;
      }
      f3 = getWidth() - toCurrentScale(this.optimalPageWidth);
      break;
      label177:
      this.miniMapRequired = true;
      if (toCurrentScale(this.currentFilteredPage * this.optimalPageHeight) + paramFloat2 > 0.0F)
      {
        f2 = -toCurrentScale(this.currentFilteredPage * this.optimalPageHeight);
        f1 = f3;
      }
      else
      {
        f1 = f3;
        f2 = paramFloat2;
        if (toCurrentScale((this.currentFilteredPage + 1) * this.optimalPageHeight) + paramFloat2 < getHeight())
        {
          f2 = getHeight() - toCurrentScale((this.currentFilteredPage + 1) * this.optimalPageHeight);
          f1 = f3;
          continue;
          label289:
          f2 = calculateCenterOffsetForPage(this.currentFilteredPage + 1);
          paramFloat1 = calculateCenterOffsetForPage(this.currentFilteredPage - 1);
          if (paramFloat2 < f2)
          {
            f1 = f3;
          }
          else
          {
            f1 = f3;
            f2 = paramFloat2;
            if (paramFloat2 > paramFloat1)
            {
              f2 = paramFloat1;
              f1 = f3;
              continue;
              if (toCurrentScale(this.optimalPageHeight) < getHeight()) {
                f3 = getHeight() / 2 - toCurrentScale(this.optimalPageHeight) / 2.0F;
              }
              for (;;)
              {
                if (isZooming())
                {
                  if (toCurrentScale(this.optimalPageWidth) < getWidth())
                  {
                    this.miniMapRequired = false;
                    f1 = getWidth() / 2 - toCurrentScale((this.currentFilteredPage + 0.5F) * this.optimalPageWidth);
                    f2 = f3;
                    break;
                    if (paramFloat2 > 0.0F)
                    {
                      f3 = 0.0F;
                      continue;
                    }
                    f3 = paramFloat2;
                    if (toCurrentScale(this.optimalPageHeight) + paramFloat2 >= getHeight()) {
                      continue;
                    }
                    f3 = getHeight() - toCurrentScale(this.optimalPageHeight);
                    continue;
                  }
                  this.miniMapRequired = true;
                  if (toCurrentScale(this.currentFilteredPage * this.optimalPageWidth) + paramFloat1 > 0.0F)
                  {
                    f1 = -toCurrentScale(this.currentFilteredPage * this.optimalPageWidth);
                    f2 = f3;
                    break;
                  }
                  f1 = paramFloat1;
                  f2 = f3;
                  if (toCurrentScale((this.currentFilteredPage + 1) * this.optimalPageWidth) + paramFloat1 >= getWidth()) {
                    break;
                  }
                  f1 = getWidth() - toCurrentScale((this.currentFilteredPage + 1) * this.optimalPageWidth);
                  f2 = f3;
                  break;
                }
              }
              f1 = calculateCenterOffsetForPage(this.currentFilteredPage + 1);
              paramFloat2 = calculateCenterOffsetForPage(this.currentFilteredPage - 1);
              if (paramFloat1 < f1)
              {
                f2 = f3;
              }
              else
              {
                f1 = paramFloat1;
                f2 = f3;
                if (paramFloat1 > paramFloat2)
                {
                  f1 = paramFloat2;
                  f2 = f3;
                }
              }
            }
          }
        }
      }
    }
  }
  
  public void onBitmapRendered(PagePart paramPagePart)
  {
    if (paramPagePart.isThumbnail()) {
      this.cacheManager.cacheThumbnail(paramPagePart);
    }
    for (;;)
    {
      invalidate();
      return;
      this.cacheManager.cachePart(paramPagePart);
    }
  }
  
  protected void onDetachedFromWindow()
  {
    recycle();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.drawColor(-1);
    if (this.state != State.SHOWN) {}
    do
    {
      return;
      float f1 = this.currentXOffset;
      float f2 = this.currentYOffset;
      paramCanvas.translate(f1, f2);
      Iterator localIterator = this.cacheManager.getThumbnails().iterator();
      while (localIterator.hasNext()) {
        drawPart(paramCanvas, (PagePart)localIterator.next());
      }
      localIterator = this.cacheManager.getPageParts().iterator();
      while (localIterator.hasNext()) {
        drawPart(paramCanvas, (PagePart)localIterator.next());
      }
      if (this.onDrawListener != null)
      {
        paramCanvas.translate(toCurrentScale(this.currentFilteredPage * this.optimalPageWidth), 0.0F);
        this.onDrawListener.onLayerDrawn(paramCanvas, toCurrentScale(this.optimalPageWidth), toCurrentScale(this.optimalPageHeight), this.currentPage);
        paramCanvas.translate(-toCurrentScale(this.currentFilteredPage * this.optimalPageWidth), 0.0F);
      }
      paramCanvas.translate(-f1, -f2);
      paramCanvas.drawRect(this.leftMask, this.maskPaint);
      paramCanvas.drawRect(this.rightMask, this.maskPaint);
    } while ((!this.userWantsMinimap) || (!this.miniMapRequired));
    drawMiniMap(paramCanvas);
  }
  
  public void onLayerUpdate()
  {
    invalidate();
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.animationManager.stopAll();
    calculateOptimalWidthAndHeight();
    loadPages();
    if (this.swipeVertical)
    {
      moveTo(this.currentXOffset, calculateCenterOffsetForPage(this.currentFilteredPage));
      return;
    }
    moveTo(calculateCenterOffsetForPage(this.currentFilteredPage), this.currentYOffset);
  }
  
  public void recycle()
  {
    if (this.renderingAsyncTask != null) {
      this.renderingAsyncTask.cancel(true);
    }
    if (this.decodingAsyncTask != null) {
      this.decodingAsyncTask.cancel(true);
    }
    this.cacheManager.recycle();
    this.recycled = true;
    this.state = State.DEFAULT;
  }
  
  public void resetZoom()
  {
    zoomTo(1.0F);
  }
  
  public void resetZoomWithAnimation()
  {
    this.animationManager.startZoomAnimation(this.zoom, 1.0F);
  }
  
  public void setSwipeVertical(boolean paramBoolean)
  {
    this.swipeVertical = paramBoolean;
  }
  
  void showPage(int paramInt)
  {
    this.state = State.SHOWN;
    int i = determineValidPageNumberFrom(paramInt);
    this.currentPage = i;
    this.currentFilteredPage = i;
    paramInt = i;
    if (this.filteredUserPageIndexes != null)
    {
      paramInt = i;
      if (i >= 0)
      {
        paramInt = i;
        if (i < this.filteredUserPageIndexes.length)
        {
          paramInt = this.filteredUserPageIndexes[i];
          this.currentFilteredPage = paramInt;
        }
      }
    }
    resetZoom();
    if (this.swipeVertical) {
      this.animationManager.startYAnimation(this.currentYOffset, calculateCenterOffsetForPage(paramInt));
    }
    for (;;)
    {
      loadPages();
      if (this.onPageChangeListener != null) {
        this.onPageChangeListener.onPageChanged(this.currentPage + 1, getPageCount());
      }
      return;
      this.animationManager.startXAnimation(this.currentXOffset, calculateCenterOffsetForPage(paramInt));
    }
  }
  
  public float toCurrentScale(float paramFloat)
  {
    return this.zoom * paramFloat;
  }
  
  public float toRealScale(float paramFloat)
  {
    return paramFloat / this.zoom;
  }
  
  public void zoomCenteredRelativeTo(float paramFloat, PointF paramPointF)
  {
    zoomCenteredTo(this.zoom * paramFloat, paramPointF);
  }
  
  public void zoomCenteredTo(float paramFloat, PointF paramPointF)
  {
    float f1 = paramFloat / this.zoom;
    zoomTo(paramFloat);
    paramFloat = this.currentXOffset;
    float f2 = this.currentYOffset;
    moveTo(paramFloat * f1 + (paramPointF.x - paramPointF.x * f1), f2 * f1 + (paramPointF.y - paramPointF.y * f1));
  }
  
  public void zoomTo(float paramFloat)
  {
    this.zoom = paramFloat;
    calculateMasksBounds();
  }
  
  public class Configurator
  {
    private int defaultPage = 1;
    private boolean enableDoubletap = true;
    private boolean enableSwipe = true;
    private int maskAlpha = 20;
    private int maskColor = -16777216;
    private OnDrawListener onDrawListener;
    private OnLoadCompleteListener onLoadCompleteListener;
    private OnPageChangeListener onPageChangeListener;
    private int[] pageNumbers = null;
    private boolean showMinimap = false;
    private boolean swipeVertical = false;
    private final Uri uri;
    
    private Configurator(Uri paramUri)
    {
      this.uri = paramUri;
    }
    
    public Configurator defaultPage(int paramInt)
    {
      this.defaultPage = paramInt;
      return this;
    }
    
    public Configurator enableDoubletap(boolean paramBoolean)
    {
      this.enableDoubletap = paramBoolean;
      return this;
    }
    
    public Configurator enableSwipe(boolean paramBoolean)
    {
      this.enableSwipe = paramBoolean;
      return this;
    }
    
    public void load()
    {
      PDFView.this.recycle();
      PDFView.this.setOnDrawListener(this.onDrawListener);
      PDFView.this.setOnPageChangeListener(this.onPageChangeListener);
      PDFView.this.enableSwipe(this.enableSwipe);
      PDFView.this.enableDoubletap(this.enableDoubletap);
      PDFView.this.setDefaultPage(this.defaultPage);
      PDFView.this.setUserWantsMinimap(this.showMinimap);
      PDFView.this.setSwipeVertical(this.swipeVertical);
      PDFView.this.dragPinchManager.setSwipeVertical(this.swipeVertical);
      PDFView.access$802(PDFView.this, new Paint());
      PDFView.this.maskPaint.setColor(this.maskColor);
      PDFView.this.maskPaint.setAlpha(this.maskAlpha);
      if (this.pageNumbers != null)
      {
        PDFView.this.load(this.uri, this.onLoadCompleteListener, this.pageNumbers);
        return;
      }
      PDFView.this.load(this.uri, this.onLoadCompleteListener);
    }
    
    public Configurator mask(int paramInt1, int paramInt2)
    {
      this.maskColor = paramInt1;
      this.maskAlpha = paramInt2;
      return this;
    }
    
    public Configurator onDraw(OnDrawListener paramOnDrawListener)
    {
      this.onDrawListener = paramOnDrawListener;
      return this;
    }
    
    public Configurator onLoad(OnLoadCompleteListener paramOnLoadCompleteListener)
    {
      this.onLoadCompleteListener = paramOnLoadCompleteListener;
      return this;
    }
    
    public Configurator onPageChange(OnPageChangeListener paramOnPageChangeListener)
    {
      this.onPageChangeListener = paramOnPageChangeListener;
      return this;
    }
    
    public Configurator pages(int... paramVarArgs)
    {
      this.pageNumbers = paramVarArgs;
      return this;
    }
    
    public Configurator showMinimap(boolean paramBoolean)
    {
      this.showMinimap = paramBoolean;
      return this;
    }
    
    public Configurator swipeVertical(boolean paramBoolean)
    {
      this.swipeVertical = paramBoolean;
      return this;
    }
  }
  
  private static enum State
  {
    DEFAULT,  LOADED,  SHOWN;
    
    private State() {}
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\joanzapata\pdfview\PDFView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */