package com.ons.adapter;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class TouchImageView
  extends ImageView
{
  static final int CLICK = 3;
  static final int DRAG = 1;
  static final int NONE = 0;
  static final int ZOOM = 2;
  Context context;
  PointF last = new PointF();
  float[] m;
  ScaleGestureDetector mScaleDetector;
  Matrix matrix;
  float maxScale = 3.0F;
  float minScale = 1.0F;
  int mode = 0;
  int oldMeasuredHeight;
  int oldMeasuredWidth;
  protected float origHeight;
  protected float origWidth;
  float saveScale = 1.0F;
  PointF start = new PointF();
  int viewHeight;
  int viewWidth;
  
  public TouchImageView(Context paramContext)
  {
    super(paramContext);
    sharedConstructing(paramContext);
  }
  
  public TouchImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    sharedConstructing(paramContext);
  }
  
  private void sharedConstructing(Context paramContext)
  {
    super.setClickable(true);
    this.context = paramContext;
    this.mScaleDetector = new ScaleGestureDetector(paramContext, new ScaleListener(null));
    this.matrix = new Matrix();
    this.m = new float[9];
    setImageMatrix(this.matrix);
    setScaleType(ImageView.ScaleType.MATRIX);
    setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        TouchImageView.this.mScaleDetector.onTouchEvent(paramAnonymousMotionEvent);
        paramAnonymousView = new PointF(paramAnonymousMotionEvent.getX(), paramAnonymousMotionEvent.getY());
        switch (paramAnonymousMotionEvent.getAction())
        {
        }
        for (;;)
        {
          TouchImageView.this.setImageMatrix(TouchImageView.this.matrix);
          TouchImageView.this.invalidate();
          return true;
          TouchImageView.this.last.set(paramAnonymousView);
          TouchImageView.this.start.set(TouchImageView.this.last);
          TouchImageView.this.mode = 1;
          continue;
          if (TouchImageView.this.mode == 1)
          {
            float f3 = paramAnonymousView.x;
            float f4 = TouchImageView.this.last.x;
            float f1 = paramAnonymousView.y;
            float f2 = TouchImageView.this.last.y;
            f3 = TouchImageView.this.getFixDragTrans(f3 - f4, TouchImageView.this.viewWidth, TouchImageView.this.origWidth * TouchImageView.this.saveScale);
            f1 = TouchImageView.this.getFixDragTrans(f1 - f2, TouchImageView.this.viewHeight, TouchImageView.this.origHeight * TouchImageView.this.saveScale);
            TouchImageView.this.matrix.postTranslate(f3, f1);
            TouchImageView.this.fixTrans();
            TouchImageView.this.last.set(paramAnonymousView.x, paramAnonymousView.y);
            continue;
            TouchImageView.this.mode = 0;
            int i = (int)Math.abs(paramAnonymousView.x - TouchImageView.this.start.x);
            int j = (int)Math.abs(paramAnonymousView.y - TouchImageView.this.start.y);
            if ((i < 3) && (j < 3))
            {
              TouchImageView.this.performClick();
              continue;
              TouchImageView.this.mode = 0;
            }
          }
        }
      }
    });
  }
  
  void fixTrans()
  {
    this.matrix.getValues(this.m);
    float f2 = this.m[2];
    float f1 = this.m[5];
    f2 = getFixTrans(f2, this.viewWidth, this.origWidth * this.saveScale);
    f1 = getFixTrans(f1, this.viewHeight, this.origHeight * this.saveScale);
    if ((f2 != 0.0F) || (f1 != 0.0F)) {
      this.matrix.postTranslate(f2, f1);
    }
  }
  
  float getFixDragTrans(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if (paramFloat3 <= paramFloat2) {
      paramFloat1 = 0.0F;
    }
    return paramFloat1;
  }
  
  float getFixTrans(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if (paramFloat3 <= paramFloat2)
    {
      float f = 0.0F;
      paramFloat2 -= paramFloat3;
      paramFloat3 = f;
    }
    while (paramFloat1 < paramFloat3)
    {
      return -paramFloat1 + paramFloat3;
      paramFloat3 = paramFloat2 - paramFloat3;
      paramFloat2 = 0.0F;
    }
    if (paramFloat1 > paramFloat2) {
      return -paramFloat1 + paramFloat2;
    }
    return 0.0F;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    this.viewWidth = View.MeasureSpec.getSize(paramInt1);
    this.viewHeight = View.MeasureSpec.getSize(paramInt2);
    if (((this.oldMeasuredHeight == this.viewWidth) && (this.oldMeasuredHeight == this.viewHeight)) || (this.viewWidth == 0) || (this.viewHeight == 0)) {}
    Drawable localDrawable;
    do
    {
      return;
      this.oldMeasuredHeight = this.viewHeight;
      this.oldMeasuredWidth = this.viewWidth;
      if (this.saveScale != 1.0F) {
        break;
      }
      localDrawable = getDrawable();
    } while ((localDrawable == null) || (localDrawable.getIntrinsicWidth() == 0) || (localDrawable.getIntrinsicHeight() == 0));
    paramInt1 = localDrawable.getIntrinsicWidth();
    paramInt2 = localDrawable.getIntrinsicHeight();
    Log.d("bmSize", "bmWidth: " + paramInt1 + " bmHeight : " + paramInt2);
    float f1 = Math.min(this.viewWidth / paramInt1, this.viewHeight / paramInt2);
    this.matrix.setScale(f1, f1);
    float f4 = this.viewHeight;
    float f5 = paramInt2;
    float f2 = this.viewWidth;
    float f3 = paramInt1;
    f4 = (f4 - f5 * f1) / 2.0F;
    f1 = (f2 - f3 * f1) / 2.0F;
    this.matrix.postTranslate(f1, f4);
    this.origWidth = (this.viewWidth - 2.0F * f1);
    this.origHeight = (this.viewHeight - 2.0F * f4);
    setImageMatrix(this.matrix);
    fixTrans();
  }
  
  public void setMaxZoom(float paramFloat)
  {
    this.maxScale = paramFloat;
  }
  
  private class ScaleListener
    extends ScaleGestureDetector.SimpleOnScaleGestureListener
  {
    private ScaleListener() {}
    
    public boolean onScale(ScaleGestureDetector paramScaleGestureDetector)
    {
      float f1 = paramScaleGestureDetector.getScaleFactor();
      float f2 = TouchImageView.this.saveScale;
      TouchImageView localTouchImageView = TouchImageView.this;
      localTouchImageView.saveScale *= f1;
      if (TouchImageView.this.saveScale > TouchImageView.this.maxScale)
      {
        TouchImageView.this.saveScale = TouchImageView.this.maxScale;
        f1 = TouchImageView.this.maxScale / f2;
        if ((TouchImageView.this.origWidth * TouchImageView.this.saveScale > TouchImageView.this.viewWidth) && (TouchImageView.this.origHeight * TouchImageView.this.saveScale > TouchImageView.this.viewHeight)) {
          break label214;
        }
        TouchImageView.this.matrix.postScale(f1, f1, TouchImageView.this.viewWidth / 2, TouchImageView.this.viewHeight / 2);
      }
      for (;;)
      {
        TouchImageView.this.fixTrans();
        return true;
        if (TouchImageView.this.saveScale >= TouchImageView.this.minScale) {
          break;
        }
        TouchImageView.this.saveScale = TouchImageView.this.minScale;
        f1 = TouchImageView.this.minScale / f2;
        break;
        label214:
        TouchImageView.this.matrix.postScale(f1, f1, paramScaleGestureDetector.getFocusX(), paramScaleGestureDetector.getFocusY());
      }
    }
    
    public boolean onScaleBegin(ScaleGestureDetector paramScaleGestureDetector)
    {
      TouchImageView.this.mode = 2;
      return true;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\adapter\TouchImageView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */