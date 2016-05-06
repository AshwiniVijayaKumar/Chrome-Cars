package com.ooyala.android.player;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import com.ooyala.android.util.DebugMode;

class MovieView
  extends ControlSharingSurfaceView
{
  private float _aspectRatio = -1.0F;
  
  public MovieView(boolean paramBoolean, Context paramContext)
  {
    super(paramBoolean, paramContext);
  }
  
  public MovieView(boolean paramBoolean, Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramBoolean, paramContext, paramAttributeSet);
  }
  
  public MovieView(boolean paramBoolean, Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramBoolean, paramContext, paramAttributeSet, paramInt);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (this._aspectRatio <= 0.0F)
    {
      super.onMeasure(paramInt1, paramInt2);
      return;
    }
    int j = View.MeasureSpec.getSize(paramInt1);
    int i = View.MeasureSpec.getSize(paramInt2);
    if ((j == 0) || (i == 0))
    {
      DebugMode.logE(getClass().getName(), "ERROR: cannot set MovieView size");
      super.onMeasure(paramInt1, paramInt2);
      return;
    }
    float f = j / i;
    if (f > this._aspectRatio)
    {
      paramInt2 = (int)(this._aspectRatio * i);
      paramInt1 = i;
    }
    for (;;)
    {
      setMeasuredDimension(paramInt2, paramInt1);
      return;
      if (f < this._aspectRatio)
      {
        paramInt2 = j;
        paramInt1 = (int)(j / this._aspectRatio);
      }
      else
      {
        paramInt2 = j;
        paramInt1 = i;
      }
    }
  }
  
  public void setAspectRatio(float paramFloat)
  {
    this._aspectRatio = paramFloat;
    requestLayout();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\player\MovieView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */