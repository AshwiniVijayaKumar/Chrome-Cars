package com.joanzapata.pdfview;

import android.graphics.PointF;
import com.joanzapata.pdfview.util.DragPinchListener;
import com.joanzapata.pdfview.util.DragPinchListener.OnDoubleTapListener;
import com.joanzapata.pdfview.util.DragPinchListener.OnDragListener;
import com.joanzapata.pdfview.util.DragPinchListener.OnPinchListener;

class DragPinchManager
  implements DragPinchListener.OnDoubleTapListener, DragPinchListener.OnDragListener, DragPinchListener.OnPinchListener
{
  private DragPinchListener dragPinchListener;
  private boolean isSwipeEnabled;
  private PDFView pdfView;
  private long startDragTime;
  private float startDragX;
  private float startDragY;
  private boolean swipeVertical;
  
  public DragPinchManager(PDFView paramPDFView)
  {
    this.pdfView = paramPDFView;
    this.isSwipeEnabled = false;
    this.swipeVertical = paramPDFView.isSwipeVertical();
    this.dragPinchListener = new DragPinchListener();
    this.dragPinchListener.setOnDragListener(this);
    this.dragPinchListener.setOnPinchListener(this);
    this.dragPinchListener.setOnDoubleTapListener(this);
    paramPDFView.setOnTouchListener(this.dragPinchListener);
  }
  
  private boolean isPageChange(float paramFloat)
  {
    return Math.abs(paramFloat) > Math.abs(this.pdfView.toCurrentScale(this.pdfView.getOptimalPageWidth()) / 2.0F);
  }
  
  private boolean isQuickMove(float paramFloat, long paramLong)
  {
    return (Math.abs(paramFloat) >= 50.0F) && (paramLong <= 250L);
  }
  
  public void enableDoubletap(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.dragPinchListener.setOnDoubleTapListener(this);
      return;
    }
    this.dragPinchListener.setOnDoubleTapListener(null);
  }
  
  public void endDrag(float paramFloat1, float paramFloat2)
  {
    if (!isZooming())
    {
      long l1;
      long l2;
      if (this.isSwipeEnabled)
      {
        if (!this.swipeVertical) {
          break label85;
        }
        paramFloat1 = paramFloat2 - this.startDragY;
        l1 = System.currentTimeMillis();
        l2 = this.startDragTime;
        if (paramFloat1 <= 0.0F) {
          break label95;
        }
      }
      label85:
      label95:
      for (int i = -1;; i = 1)
      {
        if ((!isQuickMove(paramFloat1, l1 - l2)) && (!isPageChange(paramFloat1))) {
          break label100;
        }
        this.pdfView.showPage(this.pdfView.getCurrentPage() + i);
        return;
        paramFloat1 -= this.startDragX;
        break;
      }
      label100:
      this.pdfView.showPage(this.pdfView.getCurrentPage());
      return;
    }
    this.pdfView.loadPages();
  }
  
  public boolean isZooming()
  {
    return this.pdfView.isZooming();
  }
  
  public void onDoubleTap(float paramFloat1, float paramFloat2)
  {
    if (isZooming()) {
      this.pdfView.resetZoomWithAnimation();
    }
  }
  
  public void onDrag(float paramFloat1, float paramFloat2)
  {
    if ((isZooming()) || (this.isSwipeEnabled)) {
      this.pdfView.moveRelativeTo(paramFloat1, paramFloat2);
    }
  }
  
  public void onPinch(float paramFloat, PointF paramPointF)
  {
    float f = this.pdfView.getZoom() * paramFloat;
    if (f < 1.0F) {
      paramFloat = 1.0F / this.pdfView.getZoom();
    }
    for (;;)
    {
      this.pdfView.zoomCenteredRelativeTo(paramFloat, paramPointF);
      return;
      if (f > 10.0F) {
        paramFloat = 10.0F / this.pdfView.getZoom();
      }
    }
  }
  
  public void setSwipeEnabled(boolean paramBoolean)
  {
    this.isSwipeEnabled = paramBoolean;
  }
  
  public void setSwipeVertical(boolean paramBoolean)
  {
    this.swipeVertical = paramBoolean;
  }
  
  public void startDrag(float paramFloat1, float paramFloat2)
  {
    this.startDragTime = System.currentTimeMillis();
    this.startDragX = paramFloat1;
    this.startDragY = paramFloat2;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\joanzapata\pdfview\DragPinchManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */