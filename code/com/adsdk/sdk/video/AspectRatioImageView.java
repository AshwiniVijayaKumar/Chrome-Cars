package com.adsdk.sdk.video;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View.MeasureSpec;
import android.widget.ImageView;

public class AspectRatioImageView
  extends ImageView
{
  private boolean mFill = false;
  private int mMaxH = -1;
  private int mMinW = -1;
  
  public AspectRatioImageView(Context paramContext)
  {
    super(paramContext);
  }
  
  public AspectRatioImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public AspectRatioImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public static float dip2pixel(int paramInt, Context paramContext)
  {
    paramContext = paramContext.getResources();
    return TypedValue.applyDimension(1, paramInt, paramContext.getDisplayMetrics());
  }
  
  void ensureConstraintMetAndSet(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i;
    int j;
    float f;
    if (paramInt3 < paramInt4)
    {
      i = 1;
      if (i == 0) {
        break label133;
      }
      j = paramInt1;
      i = paramInt2;
      if (this.mMinW > 0)
      {
        f = dip2pixel(this.mMinW, getContext());
        j = paramInt1;
        i = paramInt2;
        if (paramInt1 < f)
        {
          j = (int)f;
          i = paramInt4 / paramInt3 * j;
        }
      }
      paramInt1 = j;
      paramInt2 = i;
      if (this.mMaxH > 0)
      {
        f = dip2pixel(this.mMaxH, getContext());
        paramInt1 = j;
        paramInt2 = i;
        if (i > f)
        {
          paramInt2 = (int)f;
          paramInt1 = paramInt2 * paramInt3 / paramInt4;
        }
      }
    }
    for (;;)
    {
      setMeasuredDimension(paramInt1, paramInt2);
      return;
      i = 0;
      break;
      label133:
      j = paramInt1;
      i = paramInt2;
      if (this.mMaxH > 0)
      {
        f = dip2pixel(this.mMaxH, getContext());
        j = paramInt1;
        i = paramInt2;
        if (paramInt2 > f)
        {
          i = (int)f;
          j = i * paramInt3 / paramInt4;
        }
      }
      paramInt1 = j;
      paramInt2 = i;
      if (this.mMinW > 0)
      {
        f = dip2pixel(this.mMinW, getContext());
        paramInt1 = j;
        paramInt2 = i;
        if (j < f)
        {
          paramInt1 = (int)f;
          paramInt2 = paramInt4 / paramInt3 * paramInt1;
        }
      }
    }
  }
  
  public void fillParent(boolean paramBoolean, int paramInt1, int paramInt2)
  {
    this.mFill = paramBoolean;
    this.mMaxH = paramInt2;
    this.mMinW = paramInt1;
  }
  
  int getConstrainedHeight(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i;
    float f;
    if (paramInt3 < paramInt4)
    {
      i = 1;
      if (i == 0) {
        break label114;
      }
      i = paramInt2;
      if (this.mMinW > 0)
      {
        f = dip2pixel(this.mMinW, getContext());
        i = paramInt2;
        if (paramInt1 < f)
        {
          paramInt1 = (int)f;
          i = paramInt4 / paramInt3 * paramInt1;
        }
      }
      paramInt1 = i;
      if (this.mMaxH > 0)
      {
        f = dip2pixel(this.mMaxH, getContext());
        paramInt1 = i;
        if (i > f)
        {
          paramInt1 = (int)f;
          paramInt2 = paramInt1 * paramInt3 / paramInt4;
        }
      }
    }
    label114:
    int j;
    do
    {
      do
      {
        return paramInt1;
        i = 0;
        break;
        j = paramInt1;
        i = paramInt2;
        if (this.mMaxH > 0)
        {
          f = dip2pixel(this.mMaxH, getContext());
          j = paramInt1;
          i = paramInt2;
          if (paramInt2 > f)
          {
            i = (int)f;
            j = i * paramInt3 / paramInt4;
          }
        }
        paramInt1 = i;
      } while (this.mMinW <= 0);
      f = dip2pixel(this.mMinW, getContext());
      paramInt1 = i;
    } while (j >= f);
    paramInt1 = (int)f;
    return paramInt4 / paramInt3 * paramInt1;
  }
  
  protected int getMeasuredHeight(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getSize(paramInt1);
    View.MeasureSpec.getSize(paramInt2);
    if ((!this.mFill) || (getDrawable() == null))
    {
      super.onMeasure(paramInt1, paramInt2);
      return 0;
    }
    paramInt2 = getDrawable().getIntrinsicHeight();
    int j = getDrawable().getIntrinsicWidth();
    if (j > paramInt2) {
      paramInt1 = i * paramInt2 / j;
    }
    for (;;)
    {
      return getConstrainedHeight(i, paramInt1, j, paramInt2);
      paramInt1 = i;
      i = paramInt1 * j / paramInt2;
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getSize(paramInt1);
    View.MeasureSpec.getSize(paramInt2);
    if ((!this.mFill) || (getDrawable() == null))
    {
      super.onMeasure(paramInt1, paramInt2);
      return;
    }
    paramInt2 = getDrawable().getIntrinsicHeight();
    int j = getDrawable().getIntrinsicWidth();
    if (j > paramInt2) {
      paramInt1 = i * paramInt2 / j;
    }
    for (;;)
    {
      ensureConstraintMetAndSet(i, paramInt1, j, paramInt2);
      return;
      paramInt1 = i;
      i = paramInt1 * j / paramInt2;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\video\AspectRatioImageView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */