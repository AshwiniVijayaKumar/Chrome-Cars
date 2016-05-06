package com.ons.barcodeSupport;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class ViewFinderView
  extends View
{
  private static final long ANIMATION_DELAY = 80L;
  private static final float LANDSCAPE_HEIGHT_RATIO = 0.625F;
  private static final int LANDSCAPE_MAX_FRAME_HEIGHT = 675;
  private static final int LANDSCAPE_MAX_FRAME_WIDTH = 1200;
  private static final float LANDSCAPE_WIDTH_RATIO = 0.625F;
  private static final int MIN_FRAME_HEIGHT = 240;
  private static final int MIN_FRAME_WIDTH = 240;
  private static final int POINT_SIZE = 10;
  private static final float PORTRAIT_HEIGHT_RATIO = 0.375F;
  private static final int PORTRAIT_MAX_FRAME_HEIGHT = 720;
  private static final int PORTRAIT_MAX_FRAME_WIDTH = 945;
  private static final float PORTRAIT_WIDTH_RATIO = 0.875F;
  private static final int[] SCANNER_ALPHA = { 0, 64, 128, 192, 255, 192, 128, 64 };
  private static final String TAG = "ViewFinderView";
  private Rect mFramingRect;
  private int scannerAlpha;
  
  public ViewFinderView(Context paramContext)
  {
    super(paramContext);
  }
  
  public ViewFinderView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  private static int findDesiredDimensionInRange(float paramFloat, int paramInt1, int paramInt2, int paramInt3)
  {
    paramInt1 = (int)(paramInt1 * paramFloat);
    if (paramInt1 < paramInt2) {
      return paramInt2;
    }
    if (paramInt1 > paramInt3) {
      return paramInt3;
    }
    return paramInt1;
  }
  
  public void drawLaser(Canvas paramCanvas)
  {
    Paint localPaint = new Paint();
    localPaint.setColor(getResources().getColor(2131361824));
    localPaint.setAlpha(SCANNER_ALPHA[this.scannerAlpha]);
    localPaint.setStyle(Paint.Style.FILL);
    this.scannerAlpha = ((this.scannerAlpha + 1) % SCANNER_ALPHA.length);
    int i = this.mFramingRect.height() / 2 + this.mFramingRect.top;
    paramCanvas.drawRect(this.mFramingRect.left + 2, i - 1, this.mFramingRect.right - 1, i + 2, localPaint);
    postInvalidateDelayed(80L, this.mFramingRect.left - 10, this.mFramingRect.top - 10, this.mFramingRect.right + 10, this.mFramingRect.bottom + 10);
  }
  
  public void drawViewFinderBorder(Canvas paramCanvas)
  {
    Paint localPaint = new Paint();
    Resources localResources = getResources();
    localPaint.setColor(localResources.getColor(2131361847));
    localPaint.setStyle(Paint.Style.STROKE);
    localPaint.setStrokeWidth(localResources.getInteger(2131427329));
    int i = localResources.getInteger(2131427329);
    paramCanvas.drawLine(this.mFramingRect.left - 1, this.mFramingRect.top - 1, this.mFramingRect.left - 1, this.mFramingRect.top - 1 + i, localPaint);
    paramCanvas.drawLine(this.mFramingRect.left - 1, this.mFramingRect.top - 1, this.mFramingRect.left - 1 + i, this.mFramingRect.top - 1, localPaint);
    paramCanvas.drawLine(this.mFramingRect.left - 1, this.mFramingRect.bottom + 1, this.mFramingRect.left - 1, this.mFramingRect.bottom + 1 - i, localPaint);
    paramCanvas.drawLine(this.mFramingRect.left - 1, this.mFramingRect.bottom + 1, this.mFramingRect.left - 1 + i, this.mFramingRect.bottom + 1, localPaint);
    paramCanvas.drawLine(this.mFramingRect.right + 1, this.mFramingRect.top - 1, this.mFramingRect.right + 1, this.mFramingRect.top - 1 + i, localPaint);
    paramCanvas.drawLine(this.mFramingRect.right + 1, this.mFramingRect.top - 1, this.mFramingRect.right + 1 - i, this.mFramingRect.top - 1, localPaint);
    paramCanvas.drawLine(this.mFramingRect.right + 1, this.mFramingRect.bottom + 1, this.mFramingRect.right + 1, this.mFramingRect.bottom + 1 - i, localPaint);
    paramCanvas.drawLine(this.mFramingRect.right + 1, this.mFramingRect.bottom + 1, this.mFramingRect.right + 1 - i, this.mFramingRect.bottom + 1, localPaint);
  }
  
  public void drawViewFinderMask(Canvas paramCanvas)
  {
    Paint localPaint = new Paint();
    localPaint.setColor(getResources().getColor(2131361825));
    int i = paramCanvas.getWidth();
    int j = paramCanvas.getHeight();
    paramCanvas.drawRect(0.0F, 0.0F, i, this.mFramingRect.top, localPaint);
    paramCanvas.drawRect(0.0F, this.mFramingRect.top, this.mFramingRect.left, this.mFramingRect.bottom + 1, localPaint);
    paramCanvas.drawRect(this.mFramingRect.right + 1, this.mFramingRect.top, i, this.mFramingRect.bottom + 1, localPaint);
    paramCanvas.drawRect(0.0F, this.mFramingRect.bottom + 1, i, j, localPaint);
  }
  
  public Rect getFramingRect()
  {
    return this.mFramingRect;
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    if (this.mFramingRect == null) {
      return;
    }
    drawViewFinderMask(paramCanvas);
    drawViewFinderBorder(paramCanvas);
    drawLaser(paramCanvas);
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    updateFramingRect();
  }
  
  public void setupViewFinder()
  {
    updateFramingRect();
    invalidate();
  }
  
  public void updateFramingRect()
  {
    for (;;)
    {
      try
      {
        Point localPoint = new Point(getWidth(), getHeight());
        if (localPoint == null) {
          return;
        }
        if (DisplayUtils.getScreenOrientation(getContext()) != 1)
        {
          j = findDesiredDimensionInRange(0.625F, localPoint.x, 240, 1200);
          i = findDesiredDimensionInRange(0.625F, localPoint.y, 240, 675);
          int k = (localPoint.x - j) / 2;
          int m = (localPoint.y - i) / 2;
          this.mFramingRect = new Rect(k, m, k + j, m + i);
          continue;
        }
        int j = findDesiredDimensionInRange(0.875F, ((Point)localObject).x, 240, 945);
      }
      finally {}
      int i = findDesiredDimensionInRange(0.375F, ((Point)localObject).y, 240, 720);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\barcodeSupport\ViewFinderView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */