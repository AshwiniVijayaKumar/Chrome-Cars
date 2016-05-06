package org.vudroid.core;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.text.TextPaint;

class Page
{
  private float aspectRatio;
  RectF bounds;
  private DocumentView documentView;
  private final Paint fillPaint = fillPaint();
  final int index;
  private PageTreeNode node;
  private final Paint strokePaint = strokePaint();
  private final TextPaint textPaint = textPaint();
  
  Page(DocumentView paramDocumentView, int paramInt)
  {
    this.documentView = paramDocumentView;
    this.index = paramInt;
    this.node = new PageTreeNode(paramDocumentView, new RectF(0.0F, 0.0F, 1.0F, 1.0F), this, 1, null);
  }
  
  private Paint fillPaint()
  {
    Paint localPaint = new Paint();
    localPaint.setColor(-7829368);
    localPaint.setStyle(Paint.Style.FILL);
    return localPaint;
  }
  
  private Paint strokePaint()
  {
    Paint localPaint = new Paint();
    localPaint.setColor(-16777216);
    localPaint.setStyle(Paint.Style.STROKE);
    localPaint.setStrokeWidth(2.0F);
    return localPaint;
  }
  
  private TextPaint textPaint()
  {
    TextPaint localTextPaint = new TextPaint();
    localTextPaint.setColor(-16777216);
    localTextPaint.setAntiAlias(true);
    localTextPaint.setTextSize(24.0F);
    localTextPaint.setTextAlign(Paint.Align.CENTER);
    return localTextPaint;
  }
  
  public void draw(Canvas paramCanvas)
  {
    if (!isVisible()) {
      return;
    }
    paramCanvas.drawRect(this.bounds, this.fillPaint);
    paramCanvas.drawText("Page " + (this.index + 1), this.bounds.centerX(), this.bounds.centerY(), this.textPaint);
    this.node.draw(paramCanvas);
    paramCanvas.drawLine(this.bounds.left, this.bounds.top, this.bounds.right, this.bounds.top, this.strokePaint);
    paramCanvas.drawLine(this.bounds.left, this.bounds.bottom, this.bounds.right, this.bounds.bottom, this.strokePaint);
  }
  
  public float getAspectRatio()
  {
    return this.aspectRatio;
  }
  
  float getPageHeight(int paramInt, float paramFloat)
  {
    return paramInt / getAspectRatio() * paramFloat;
  }
  
  public int getTop()
  {
    return Math.round(this.bounds.top);
  }
  
  public void invalidate()
  {
    this.node.invalidate();
  }
  
  public boolean isVisible()
  {
    return RectF.intersects(this.documentView.getViewRect(), this.bounds);
  }
  
  public void setAspectRatio(float paramFloat)
  {
    if (this.aspectRatio != paramFloat)
    {
      this.aspectRatio = paramFloat;
      this.documentView.invalidatePageSizes();
    }
  }
  
  public void setAspectRatio(int paramInt1, int paramInt2)
  {
    setAspectRatio(paramInt1 * 1.0F / paramInt2);
  }
  
  void setBounds(RectF paramRectF)
  {
    this.bounds = paramRectF;
    this.node.invalidateNodeBounds();
  }
  
  public void updateVisibility()
  {
    this.node.updateVisibility();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\vudroid\core\Page.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */