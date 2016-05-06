package org.vudroid.core;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import java.lang.ref.SoftReference;
import org.vudroid.core.models.DecodingProgressModel;
import org.vudroid.core.models.ZoomModel;

class PageTreeNode
{
  private static final int SLICE_SIZE = 65535;
  private Bitmap bitmap;
  private final Paint bitmapPaint = new Paint();
  private SoftReference<Bitmap> bitmapWeakReference;
  private PageTreeNode[] children;
  private boolean decodingNow;
  private DocumentView documentView;
  private boolean invalidateFlag;
  private Matrix matrix = new Matrix();
  private final Page page;
  private final RectF pageSliceBounds;
  private Rect targetRect;
  private RectF targetRectF;
  private final int treeNodeDepthLevel;
  
  PageTreeNode(DocumentView paramDocumentView, RectF paramRectF, Page paramPage, int paramInt, PageTreeNode paramPageTreeNode)
  {
    this.documentView = paramDocumentView;
    this.pageSliceBounds = evaluatePageSliceBounds(paramRectF, paramPageTreeNode);
    this.page = paramPage;
    this.treeNodeDepthLevel = paramInt;
  }
  
  private boolean childrenContainBitmaps()
  {
    if (this.children == null) {}
    for (;;)
    {
      return false;
      PageTreeNode[] arrayOfPageTreeNode = this.children;
      int j = arrayOfPageTreeNode.length;
      int i = 0;
      while (i < j)
      {
        if (arrayOfPageTreeNode[i].containsBitmaps()) {
          return true;
        }
        i += 1;
      }
    }
  }
  
  private boolean containsBitmaps()
  {
    return (getBitmap() != null) || (childrenContainBitmaps());
  }
  
  private void decodePageTreeNode()
  {
    if (isDecodingNow()) {
      return;
    }
    setDecodingNow(true);
    this.documentView.decodeService.decodePage(this, this.page.index, new DecodeService.DecodeCallback()
    {
      public void decodeComplete(final Bitmap paramAnonymousBitmap)
      {
        PageTreeNode.this.documentView.post(new Runnable()
        {
          public void run()
          {
            PageTreeNode.this.setBitmap(paramAnonymousBitmap);
            PageTreeNode.access$102(PageTreeNode.this, false);
            PageTreeNode.this.setDecodingNow(false);
            PageTreeNode.this.page.setAspectRatio(PageTreeNode.this.documentView.decodeService.getPageWidth(PageTreeNode.this.page.index), PageTreeNode.this.documentView.decodeService.getPageHeight(PageTreeNode.this.page.index));
            PageTreeNode.this.invalidateChildren();
          }
        });
      }
    }, this.documentView.zoomModel.getZoom(), this.pageSliceBounds);
  }
  
  private RectF evaluatePageSliceBounds(RectF paramRectF, PageTreeNode paramPageTreeNode)
  {
    if (paramPageTreeNode == null) {
      return paramRectF;
    }
    Matrix localMatrix = new Matrix();
    localMatrix.postScale(paramPageTreeNode.pageSliceBounds.width(), paramPageTreeNode.pageSliceBounds.height());
    localMatrix.postTranslate(paramPageTreeNode.pageSliceBounds.left, paramPageTreeNode.pageSliceBounds.top);
    paramPageTreeNode = new RectF();
    localMatrix.mapRect(paramPageTreeNode, paramRectF);
    return paramPageTreeNode;
  }
  
  private Rect getTargetRect()
  {
    if (this.targetRect == null)
    {
      this.matrix.reset();
      this.matrix.postScale(this.page.bounds.width(), this.page.bounds.height());
      this.matrix.postTranslate(this.page.bounds.left, this.page.bounds.top);
      RectF localRectF = new RectF();
      this.matrix.mapRect(localRectF, this.pageSliceBounds);
      this.targetRect = new Rect((int)localRectF.left, (int)localRectF.top, (int)localRectF.right, (int)localRectF.bottom);
    }
    return this.targetRect;
  }
  
  private RectF getTargetRectF()
  {
    if (this.targetRectF == null) {
      this.targetRectF = new RectF(getTargetRect());
    }
    return this.targetRectF;
  }
  
  private void invalidateChildren()
  {
    if ((thresholdHit()) && (this.children == null) && (isVisible()))
    {
      int i = this.treeNodeDepthLevel * 2;
      this.children = new PageTreeNode[] { new PageTreeNode(this.documentView, new RectF(0.0F, 0.0F, 0.5F, 0.5F), this.page, i, this), new PageTreeNode(this.documentView, new RectF(0.5F, 0.0F, 1.0F, 0.5F), this.page, i, this), new PageTreeNode(this.documentView, new RectF(0.0F, 0.5F, 0.5F, 1.0F), this.page, i, this), new PageTreeNode(this.documentView, new RectF(0.5F, 0.5F, 1.0F, 1.0F), this.page, i, this) };
    }
    if (((!thresholdHit()) && (getBitmap() != null)) || (!isVisible())) {
      recycleChildren();
    }
  }
  
  private void invalidateRecursive()
  {
    this.invalidateFlag = true;
    if (this.children != null)
    {
      PageTreeNode[] arrayOfPageTreeNode = this.children;
      int j = arrayOfPageTreeNode.length;
      int i = 0;
      while (i < j)
      {
        arrayOfPageTreeNode[i].invalidateRecursive();
        i += 1;
      }
    }
    stopDecodingThisNode();
  }
  
  private boolean isDecodingNow()
  {
    return this.decodingNow;
  }
  
  private boolean isHiddenByChildren()
  {
    if (this.children == null) {
      return false;
    }
    PageTreeNode[] arrayOfPageTreeNode = this.children;
    int j = arrayOfPageTreeNode.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label40;
      }
      if (arrayOfPageTreeNode[i].getBitmap() == null) {
        break;
      }
      i += 1;
    }
    label40:
    return true;
  }
  
  private boolean isVisible()
  {
    return RectF.intersects(this.documentView.getViewRect(), getTargetRectF());
  }
  
  private boolean isVisibleAndNotHiddenByChildren()
  {
    return (isVisible()) && (!isHiddenByChildren());
  }
  
  private void recycle()
  {
    stopDecodingThisNode();
    setBitmap(null);
    if (this.children != null)
    {
      PageTreeNode[] arrayOfPageTreeNode = this.children;
      int j = arrayOfPageTreeNode.length;
      int i = 0;
      while (i < j)
      {
        arrayOfPageTreeNode[i].recycle();
        i += 1;
      }
    }
  }
  
  private void recycleChildren()
  {
    if (this.children == null) {}
    do
    {
      return;
      PageTreeNode[] arrayOfPageTreeNode = this.children;
      int j = arrayOfPageTreeNode.length;
      int i = 0;
      while (i < j)
      {
        arrayOfPageTreeNode[i].recycle();
        i += 1;
      }
    } while (childrenContainBitmaps());
    this.children = null;
  }
  
  private void restoreBitmapReference()
  {
    setBitmap(getBitmap());
  }
  
  private void setBitmap(Bitmap paramBitmap)
  {
    if ((paramBitmap != null) && (paramBitmap.getWidth() == -1) && (paramBitmap.getHeight() == -1)) {}
    while (this.bitmap == paramBitmap) {
      return;
    }
    if (paramBitmap != null)
    {
      if (this.bitmap != null) {
        this.bitmap.recycle();
      }
      this.bitmapWeakReference = new SoftReference(paramBitmap);
      this.documentView.postInvalidate();
    }
    this.bitmap = paramBitmap;
  }
  
  private void setDecodingNow(boolean paramBoolean)
  {
    if (this.decodingNow != paramBoolean)
    {
      this.decodingNow = paramBoolean;
      if (paramBoolean) {
        this.documentView.progressModel.increase();
      }
    }
    else
    {
      return;
    }
    this.documentView.progressModel.decrease();
  }
  
  private void stopDecodingThisNode()
  {
    if (!isDecodingNow()) {
      return;
    }
    this.documentView.decodeService.stopDecoding(this);
    setDecodingNow(false);
  }
  
  private boolean thresholdHit()
  {
    float f1 = this.documentView.zoomModel.getZoom();
    int i = this.documentView.getWidth();
    float f2 = this.page.getPageHeight(i, f1);
    return i * f1 * f2 / (this.treeNodeDepthLevel * this.treeNodeDepthLevel) > 65535.0F;
  }
  
  void draw(Canvas paramCanvas)
  {
    int i = 0;
    if (getBitmap() != null) {
      paramCanvas.drawBitmap(getBitmap(), new Rect(0, 0, getBitmap().getWidth(), getBitmap().getHeight()), getTargetRect(), this.bitmapPaint);
    }
    if (this.children == null) {}
    for (;;)
    {
      return;
      PageTreeNode[] arrayOfPageTreeNode = this.children;
      int j = arrayOfPageTreeNode.length;
      while (i < j)
      {
        arrayOfPageTreeNode[i].draw(paramCanvas);
        i += 1;
      }
    }
  }
  
  public Bitmap getBitmap()
  {
    if (this.bitmapWeakReference != null) {
      return (Bitmap)this.bitmapWeakReference.get();
    }
    return null;
  }
  
  public void invalidate()
  {
    invalidateChildren();
    invalidateRecursive();
    updateVisibility();
  }
  
  void invalidateNodeBounds()
  {
    this.targetRect = null;
    this.targetRectF = null;
    if (this.children != null)
    {
      PageTreeNode[] arrayOfPageTreeNode = this.children;
      int j = arrayOfPageTreeNode.length;
      int i = 0;
      while (i < j)
      {
        arrayOfPageTreeNode[i].invalidateNodeBounds();
        i += 1;
      }
    }
  }
  
  public void updateVisibility()
  {
    invalidateChildren();
    if (this.children != null)
    {
      PageTreeNode[] arrayOfPageTreeNode = this.children;
      int j = arrayOfPageTreeNode.length;
      int i = 0;
      while (i < j)
      {
        arrayOfPageTreeNode[i].updateVisibility();
        i += 1;
      }
    }
    if ((isVisible()) && (!thresholdHit()))
    {
      if ((getBitmap() == null) || (this.invalidateFlag)) {
        break label88;
      }
      restoreBitmapReference();
    }
    for (;;)
    {
      if (!isVisibleAndNotHiddenByChildren())
      {
        stopDecodingThisNode();
        setBitmap(null);
      }
      return;
      label88:
      decodePageTreeNode();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\core\PageTreeNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */