package com.ooyala.android.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.widget.SeekBar;
import java.util.Iterator;
import java.util.Set;

public class CuePointsSeekBar
  extends SeekBar
{
  private static final int CUE_POINT_COLOR = -1;
  private static final float CUE_POINT_RADIUS_FACTOR = 0.15F;
  private Set<Integer> _cuePoints;
  private Paint _paint;
  
  public CuePointsSeekBar(Context paramContext)
  {
    super(paramContext);
    init();
  }
  
  public CuePointsSeekBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }
  
  public CuePointsSeekBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }
  
  private void init()
  {
    this._paint = new Paint(1);
    this._paint.setStyle(Paint.Style.FILL);
    this._paint.setColor(-1);
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    try
    {
      super.onDraw(paramCanvas);
      if (this._cuePoints != null)
      {
        float f2 = getWidth() - getThumbOffset() * 2;
        float f1 = getHeight();
        f2 /= getMax();
        Iterator localIterator = this._cuePoints.iterator();
        while (localIterator.hasNext()) {
          paramCanvas.drawCircle(((Integer)localIterator.next()).intValue() * f2 + getThumbOffset(), f1 / 2.0F, 0.15F * f1, this._paint);
        }
      }
    }
    finally {}
  }
  
  public void setCuePoints(Set<Integer> paramSet)
  {
    this._cuePoints = paramSet;
    invalidate();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\ui\CuePointsSeekBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */