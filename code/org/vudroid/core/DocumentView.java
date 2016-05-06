package org.vudroid.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Scroller;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import org.vudroid.core.events.ZoomListener;
import org.vudroid.core.models.CurrentPageModel;
import org.vudroid.core.models.DecodingProgressModel;
import org.vudroid.core.models.ZoomModel;
import org.vudroid.core.multitouch.MultiTouchZoom;

public class DocumentView
  extends View
  implements ZoomListener
{
  private static final int DOUBLE_TAP_TIME = 500;
  private final CurrentPageModel currentPageModel;
  DecodeService decodeService;
  private boolean inZoom;
  private boolean isInitialized = false;
  private long lastDownEventTime;
  private float lastX;
  private float lastY;
  private MultiTouchZoom multiTouchZoom;
  private int pageToGoTo;
  private final HashMap<Integer, Page> pages = new HashMap();
  DecodingProgressModel progressModel;
  private final Scroller scroller;
  private VelocityTracker velocityTracker;
  private RectF viewRect;
  final ZoomModel zoomModel;
  
  public DocumentView(Context paramContext, ZoomModel paramZoomModel, DecodingProgressModel paramDecodingProgressModel, CurrentPageModel paramCurrentPageModel)
  {
    super(paramContext);
    this.zoomModel = paramZoomModel;
    this.progressModel = paramDecodingProgressModel;
    this.currentPageModel = paramCurrentPageModel;
    setKeepScreenOn(true);
    this.scroller = new Scroller(getContext());
    setFocusable(true);
    setFocusableInTouchMode(true);
    initMultiTouchZoomIfAvailable(paramZoomModel);
  }
  
  private int getBottomLimit()
  {
    return (int)((Page)this.pages.get(Integer.valueOf(this.pages.size() - 1))).bounds.bottom - getHeight();
  }
  
  private int getLeftLimit()
  {
    return 0;
  }
  
  private int getRightLimit()
  {
    return (int)(getWidth() * this.zoomModel.getZoom()) - getWidth();
  }
  
  private float getScrollScaleRatio()
  {
    Page localPage = (Page)this.pages.get(Integer.valueOf(0));
    if ((localPage == null) || (localPage.bounds == null)) {
      return 0.0F;
    }
    float f = this.zoomModel.getZoom();
    return getWidth() * f / localPage.bounds.width();
  }
  
  private int getTopLimit()
  {
    return 0;
  }
  
  private void goToPageImpl(int paramInt)
  {
    scrollTo(0, ((Page)this.pages.get(Integer.valueOf(paramInt))).getTop());
  }
  
  private void init()
  {
    if (this.isInitialized) {
      return;
    }
    int j = this.decodeService.getEffectivePagesWidth();
    int k = this.decodeService.getEffectivePagesHeight();
    int i = 0;
    while (i < this.decodeService.getPageCount())
    {
      this.pages.put(Integer.valueOf(i), new Page(this, i));
      ((Page)this.pages.get(Integer.valueOf(i))).setAspectRatio(j, k);
      i += 1;
    }
    this.isInitialized = true;
    invalidatePageSizes();
    goToPageImpl(this.pageToGoTo);
  }
  
  private void initMultiTouchZoomIfAvailable(ZoomModel paramZoomModel)
  {
    try
    {
      this.multiTouchZoom = ((MultiTouchZoom)Class.forName("org.vudroid.core.multitouch.MultiTouchZoomImpl").getConstructor(new Class[] { ZoomModel.class }).newInstance(new Object[] { paramZoomModel }));
      return;
    }
    catch (Exception paramZoomModel)
    {
      System.out.println("Multi touch zoom is not available: " + paramZoomModel);
    }
  }
  
  private void invalidateScroll(float paramFloat)
  {
    if (!this.isInitialized) {}
    Page localPage;
    do
    {
      return;
      stopScroller();
      localPage = (Page)this.pages.get(Integer.valueOf(0));
    } while ((localPage == null) || (localPage.bounds == null));
    scrollTo((int)(getScrollX() * paramFloat), (int)(getScrollY() * paramFloat));
  }
  
  private void lineByLineMoveTo(int paramInt)
  {
    if (paramInt == 1)
    {
      if (getScrollX() != getRightLimit()) {
        break label109;
      }
      Scroller localScroller = this.scroller;
      int i = getScrollX();
      int j = getScrollY();
      int k = getLeftLimit();
      int m = getRightLimit();
      float f = paramInt;
      localScroller.startScroll(i, j, paramInt * (k - m), (int)(((Page)this.pages.get(Integer.valueOf(getCurrentPage()))).bounds.height() * f / 50.0F));
    }
    for (;;)
    {
      invalidate();
      return;
      if (getScrollX() == getLeftLimit()) {
        break;
      }
      label109:
      this.scroller.startScroll(getScrollX(), getScrollY(), getWidth() * paramInt / 2, 0);
    }
  }
  
  private void setLastPosition(MotionEvent paramMotionEvent)
  {
    this.lastX = paramMotionEvent.getX();
    this.lastY = paramMotionEvent.getY();
  }
  
  private void stopScroller()
  {
    if (!this.scroller.isFinished()) {
      this.scroller.abortAnimation();
    }
  }
  
  private void updatePageVisibility()
  {
    Iterator localIterator = this.pages.values().iterator();
    while (localIterator.hasNext()) {
      ((Page)localIterator.next()).updateVisibility();
    }
  }
  
  private void verticalDpadScroll(int paramInt)
  {
    this.scroller.startScroll(getScrollX(), getScrollY(), 0, getHeight() * paramInt / 2);
    invalidate();
  }
  
  public void commitZoom()
  {
    Iterator localIterator = this.pages.values().iterator();
    while (localIterator.hasNext()) {
      ((Page)localIterator.next()).invalidate();
    }
    this.inZoom = false;
  }
  
  public void computeScroll()
  {
    if (this.scroller.computeScrollOffset()) {
      scrollTo(this.scroller.getCurrX(), this.scroller.getCurrY());
    }
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getAction() == 0) {}
    switch (paramKeyEvent.getKeyCode())
    {
    default: 
      return super.dispatchKeyEvent(paramKeyEvent);
    case 22: 
      lineByLineMoveTo(1);
      return true;
    case 21: 
      lineByLineMoveTo(-1);
      return true;
    case 20: 
      verticalDpadScroll(1);
      return true;
    }
    verticalDpadScroll(-1);
    return true;
  }
  
  public int getCurrentPage()
  {
    Iterator localIterator = this.pages.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (((Page)localEntry.getValue()).isVisible()) {
        return ((Integer)localEntry.getKey()).intValue();
      }
    }
    return 0;
  }
  
  RectF getViewRect()
  {
    if (this.viewRect == null) {
      this.viewRect = new RectF(getScrollX(), getScrollY(), getScrollX() + getWidth(), getScrollY() + getHeight());
    }
    return this.viewRect;
  }
  
  public void goToPage(int paramInt)
  {
    if (this.isInitialized)
    {
      goToPageImpl(paramInt);
      return;
    }
    this.pageToGoTo = paramInt;
  }
  
  void invalidatePageSizes()
  {
    if (!this.isInitialized) {}
    for (;;)
    {
      return;
      float f1 = 0.0F;
      int j = getWidth();
      float f2 = this.zoomModel.getZoom();
      int i = 0;
      while (i < this.pages.size())
      {
        Page localPage = (Page)this.pages.get(Integer.valueOf(i));
        float f3 = localPage.getPageHeight(j, f2);
        localPage.setBounds(new RectF(0.0F, f1, j * f2, f1 + f3));
        f1 += f3;
        i += 1;
      }
    }
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    Iterator localIterator = this.pages.values().iterator();
    while (localIterator.hasNext()) {
      ((Page)localIterator.next()).draw(paramCanvas);
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    float f = getScrollScaleRatio();
    invalidatePageSizes();
    invalidateScroll(f);
    commitZoom();
  }
  
  protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    post(new Runnable()
    {
      public void run()
      {
        DocumentView.this.currentPageModel.setCurrentPageIndex(DocumentView.this.getCurrentPage());
      }
    });
    if (this.inZoom) {
      return;
    }
    post(new Runnable()
    {
      public void run()
      {
        DocumentView.this.updatePageVisibility();
      }
    });
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    super.onTouchEvent(paramMotionEvent);
    if (this.multiTouchZoom != null)
    {
      if (this.multiTouchZoom.onTouchEvent(paramMotionEvent)) {
        return true;
      }
      if (this.multiTouchZoom.isResetLastPointAfterZoom())
      {
        setLastPosition(paramMotionEvent);
        this.multiTouchZoom.setResetLastPointAfterZoom(false);
      }
    }
    if (this.velocityTracker == null) {
      this.velocityTracker = VelocityTracker.obtain();
    }
    this.velocityTracker.addMovement(paramMotionEvent);
    switch (paramMotionEvent.getAction())
    {
    default: 
      return true;
    case 0: 
      stopScroller();
      setLastPosition(paramMotionEvent);
      if (paramMotionEvent.getEventTime() - this.lastDownEventTime < 500L)
      {
        this.zoomModel.toggleZoomControls();
        return true;
      }
      this.lastDownEventTime = paramMotionEvent.getEventTime();
      return true;
    case 2: 
      scrollBy((int)(this.lastX - paramMotionEvent.getX()), (int)(this.lastY - paramMotionEvent.getY()));
      setLastPosition(paramMotionEvent);
      return true;
    }
    this.velocityTracker.computeCurrentVelocity(1000);
    this.scroller.fling(getScrollX(), getScrollY(), (int)-this.velocityTracker.getXVelocity(), (int)-this.velocityTracker.getYVelocity(), getLeftLimit(), getRightLimit(), getTopLimit(), getBottomLimit());
    this.velocityTracker.recycle();
    this.velocityTracker = null;
    return true;
  }
  
  public void scrollTo(int paramInt1, int paramInt2)
  {
    super.scrollTo(Math.min(Math.max(paramInt1, getLeftLimit()), getRightLimit()), Math.min(Math.max(paramInt2, getTopLimit()), getBottomLimit()));
    this.viewRect = null;
  }
  
  public void setDecodeService(DecodeService paramDecodeService)
  {
    this.decodeService = paramDecodeService;
  }
  
  public void showDocument()
  {
    post(new Runnable()
    {
      public void run()
      {
        DocumentView.this.init();
        DocumentView.this.updatePageVisibility();
      }
    });
  }
  
  public void zoomChanged(float paramFloat1, float paramFloat2)
  {
    this.inZoom = true;
    stopScroller();
    paramFloat1 /= paramFloat2;
    invalidatePageSizes();
    scrollTo((int)((getScrollX() + getWidth() / 2) * paramFloat1 - getWidth() / 2), (int)((getScrollY() + getHeight() / 2) * paramFloat1 - getHeight() / 2));
    postInvalidate();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\core\DocumentView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */