package com.ooyala.android.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Region.Op;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.ooyala.android.FCCTVRating;
import com.ooyala.android.configuration.FCCTVRatingConfiguration;
import com.ooyala.android.util.DebugMode;

public class FCCTVRatingView
  extends View
{
  private static final int FADE_IN_MSEC = 500;
  private static final int FADE_OUT_MSEC = 1000;
  static final float MINI_HEIGHT_FACTOR = 0.2F;
  private static final long OVER_CLICKING_PREVENTION_MSEC = 250L;
  private static final String TAG = "FCCTVRatingView";
  private Paint blackPaint;
  private Paint clearPaint;
  private long clickTime;
  private float miniTextScaleX;
  private float miniTextSize;
  private Bitmap nBitmap;
  private AlphaAnimation nFadeInAnimation;
  private AlphaAnimation nFadeOutAnimation;
  private FCCTVRatingViewStampDimensions nStampDimensions;
  private FCCTVRating nTVRating;
  private FCCTVRatingConfiguration nTVRatingConfiguration;
  private Paint textPaint;
  private Paint whitePaint;
  
  public FCCTVRatingView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public FCCTVRatingView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initPaints(0.9F);
    this.miniTextSize = 0.0F;
    this.miniTextScaleX = 0.0F;
    this.nTVRatingConfiguration = FCCTVRatingConfiguration.s_getDefaultTVRatingConfiguration();
  }
  
  private Pair<Float, Float> calculateTextPaintFactors(Rect paramRect, String paramString)
  {
    this.textPaint.setTextSize(1000.0F);
    Rect localRect = new Rect();
    this.textPaint.getTextBounds(paramString, 0, paramString.length(), localRect);
    return new Pair(Float.valueOf(paramRect.height() / localRect.height() * 1000.0F * 0.7F), Float.valueOf(Math.min(1.0F, paramRect.width() / localRect.width() * 1000.0F) * 0.7F));
  }
  
  private void drawBitmapStamp(Canvas paramCanvas)
  {
    drawBitmapStampBackground(paramCanvas);
    drawBitmapStampTV(paramCanvas);
    drawBitmapStampLabels(paramCanvas);
    drawBitmapStampRating(paramCanvas);
  }
  
  private void drawBitmapStampBackground(Canvas paramCanvas)
  {
    paramCanvas.clipRect(this.nStampDimensions.whiteRect, Region.Op.REPLACE);
    paramCanvas.drawRect(this.nStampDimensions.whiteRect, this.whitePaint);
    paramCanvas.drawRect(this.nStampDimensions.blackRect, this.blackPaint);
  }
  
  private void drawBitmapStampLabels(Canvas paramCanvas)
  {
    if (hasLabels())
    {
      paramCanvas.clipRect(this.nStampDimensions.labelsRect, Region.Op.REPLACE);
      drawLabels(paramCanvas, this.nStampDimensions.labelsRect, this.nTVRating.labels);
    }
  }
  
  private void drawBitmapStampRating(Canvas paramCanvas)
  {
    if (hasValidRating())
    {
      paramCanvas.clipRect(this.nStampDimensions.ratingRect, Region.Op.REPLACE);
      drawRating(paramCanvas, this.nStampDimensions.ratingRect, this.nTVRating.ageRestriction);
    }
  }
  
  private void drawBitmapStampTV(Canvas paramCanvas)
  {
    paramCanvas.clipRect(this.nStampDimensions.tvRect, Region.Op.REPLACE);
    drawTV(paramCanvas, this.nStampDimensions.tvRect);
  }
  
  private void drawLabels(Canvas paramCanvas, Rect paramRect, String paramString)
  {
    drawTextInRectGivenTextFactors(paramCanvas, paramRect, paramString, this.miniTextSize, this.miniTextScaleX);
  }
  
  private void drawRating(Canvas paramCanvas, Rect paramRect, String paramString)
  {
    drawTextInRectAutoTextFactors(paramCanvas, paramRect, paramString);
  }
  
  private void drawTV(Canvas paramCanvas, Rect paramRect)
  {
    updateMiniTextPaintFactors(paramRect);
    drawTextInRectGivenTextFactors(paramCanvas, paramRect, "TV", this.miniTextSize, this.miniTextScaleX);
  }
  
  private void drawTextInRect(Canvas paramCanvas, Rect paramRect, String paramString)
  {
    Rect localRect = new Rect();
    this.textPaint.getTextBounds(paramString, 0, paramString.length(), localRect);
    int i = paramRect.left;
    int j = Math.round(paramRect.width() / 2.0F);
    int k = paramRect.top;
    int m = Math.round((paramRect.height() + localRect.height()) / 2.0F);
    paramCanvas.drawText(paramString, i + j, k + m, this.textPaint);
  }
  
  private void drawTextInRectAutoTextFactors(Canvas paramCanvas, Rect paramRect, String paramString)
  {
    updateTextPaintFactors(paramRect, paramString);
    drawTextInRect(paramCanvas, paramRect, paramString);
  }
  
  private void drawTextInRectGivenTextFactors(Canvas paramCanvas, Rect paramRect, String paramString, float paramFloat1, float paramFloat2)
  {
    this.textPaint.setTextSize(paramFloat1);
    this.textPaint.setTextScaleX(paramFloat2);
    drawTextInRect(paramCanvas, paramRect, paramString);
  }
  
  private void freeResources()
  {
    this.nBitmap = null;
  }
  
  private void generateBitmap()
  {
    this.nBitmap = Bitmap.createBitmap(this.nStampDimensions.whiteRect.width(), this.nStampDimensions.whiteRect.height(), Bitmap.Config.ARGB_8888);
    drawBitmapStamp(new Canvas(this.nBitmap));
  }
  
  private boolean hasAnimation()
  {
    return (this.nFadeInAnimation != null) || (this.nFadeOutAnimation != null);
  }
  
  private boolean hasBitmap()
  {
    return this.nBitmap != null;
  }
  
  private boolean hasClickthrough()
  {
    return (this.nTVRating != null) && (this.nTVRating.clickthrough != null);
  }
  
  private boolean hasLabels()
  {
    return (this.nTVRating != null) && (this.nTVRating.labels != null) && (this.nTVRating.labels.length() > 0);
  }
  
  private boolean hasTVRatingConfiguration()
  {
    return this.nTVRatingConfiguration != null;
  }
  
  private boolean hasValidRating()
  {
    return (this.nTVRating != null) && (this.nTVRating.ageRestriction != null);
  }
  
  private boolean hasValidStampDimensions()
  {
    if (this.nStampDimensions == null) {}
    while ((this.nStampDimensions.whiteRect.width() <= 0) || (this.nStampDimensions.whiteRect.height() <= 0)) {
      return false;
    }
    return true;
  }
  
  private void initPaints(float paramFloat)
  {
    int i = Math.round(255.0F * paramFloat);
    this.blackPaint = new Paint();
    this.blackPaint.setColor(Color.argb(i, 0, 0, 0));
    this.blackPaint.setStyle(Paint.Style.FILL);
    this.whitePaint = new Paint();
    this.whitePaint.setColor(Color.argb(i, 255, 255, 255));
    this.whitePaint.setStyle(Paint.Style.FILL);
    this.textPaint = new Paint(129);
    this.textPaint.setColor(Color.argb(i, 255, 255, 255));
    this.textPaint.setStyle(Paint.Style.FILL);
    Typeface localTypeface = Typeface.create("DroidSans", 1);
    this.textPaint.setTypeface(localTypeface);
    this.textPaint.setTextAlign(Paint.Align.CENTER);
    this.clearPaint = new Paint();
    this.clearPaint.setColor(0);
    this.clearPaint.setStyle(Paint.Style.FILL);
  }
  
  static boolean isSquareish(int paramInt1, int paramInt2)
  {
    float f = paramInt1 / paramInt2;
    return (f > 0.9D) && (f < 1.1D);
  }
  
  private void maybeGenerateBitmap()
  {
    if (hasValidRating())
    {
      this.nStampDimensions = new FCCTVRatingViewStampDimensions(getContext(), this.nTVRatingConfiguration, getMeasuredWidth(), getMeasuredHeight(), hasLabels());
      if (hasValidStampDimensions()) {
        generateBitmap();
      }
      return;
    }
    freeResources();
  }
  
  private void setAlphaForView(View paramView, float paramFloat)
  {
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(paramFloat, paramFloat);
    localAlphaAnimation.setDuration(0L);
    localAlphaAnimation.setFillAfter(true);
    paramView.startAnimation(localAlphaAnimation);
  }
  
  private void startAnimation()
  {
    if ((hasValidRating()) && (!hasAnimation()) && (hasTVRatingConfiguration()) && (this.nTVRatingConfiguration.durationSeconds != 0L)) {
      startFadeInAnimation();
    }
  }
  
  private void startFadeInAnimation()
  {
    this.nFadeInAnimation = new AlphaAnimation(0.0F, 1.0F);
    this.nFadeInAnimation.setDuration(500L);
    this.nFadeInAnimation.setFillAfter(true);
    this.nFadeInAnimation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        FCCTVRatingView.this.startFadeOutAnimation();
      }
      
      public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
      
      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
        FCCTVRatingView.this.setVisibility(0);
      }
    });
    startAnimation(this.nFadeInAnimation);
  }
  
  private void startFadeOutAnimation()
  {
    if ((hasTVRatingConfiguration()) && (this.nTVRatingConfiguration.durationSeconds != Long.MAX_VALUE))
    {
      this.nFadeOutAnimation = new AlphaAnimation(1.0F, 0.0F);
      this.nFadeOutAnimation.setStartOffset(this.nTVRatingConfiguration.durationSeconds * 1000L);
      this.nFadeOutAnimation.setDuration(1000L);
      this.nFadeOutAnimation.setFillAfter(true);
      this.nFadeOutAnimation.setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          FCCTVRatingView.this.setVisibility(4);
        }
        
        public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
        
        public void onAnimationStart(Animation paramAnonymousAnimation) {}
      });
      startAnimation(this.nFadeOutAnimation);
    }
  }
  
  private void updateMiniTextPaintFactors(Rect paramRect)
  {
    paramRect = calculateTextPaintFactors(paramRect, "VSLDFV");
    this.miniTextSize = ((Float)paramRect.first).floatValue();
    this.miniTextScaleX = ((Float)paramRect.second).floatValue();
  }
  
  private void updateTextPaintFactors(Rect paramRect, String paramString)
  {
    paramRect = calculateTextPaintFactors(paramRect, paramString);
    this.textPaint.setTextSize(((Float)paramRect.first).floatValue());
    this.textPaint.setTextScaleX(((Float)paramRect.second).floatValue());
  }
  
  public RestoreState getRestoreState()
  {
    RestoreState localRestoreState = null;
    if (this.nTVRating != null) {
      if (getVisibility() != 0) {
        break label33;
      }
    }
    label33:
    for (boolean bool = true;; bool = false)
    {
      localRestoreState = new RestoreState(bool, this.nTVRating);
      return localRestoreState;
    }
  }
  
  public FCCTVRating getTVRating()
  {
    return this.nTVRating;
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    freeResources();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    maybeGenerateBitmap();
    if (hasBitmap()) {
      if (this.nStampDimensions == null) {
        break label61;
      }
    }
    label61:
    for (boolean bool = true;; bool = false)
    {
      DebugMode.assertCondition(bool, "FCCTVRatingView", "nStampDimensions should not be null if we bitmap is non-null");
      paramCanvas.drawBitmap(this.nBitmap, this.nStampDimensions.left, this.nStampDimensions.top, null);
      return;
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (!hasTVRatingConfiguration())
    {
      setMeasuredDimension(0, 0);
      return;
    }
    int i = getPaddingLeft();
    int j = getPaddingTop();
    int k = getPaddingRight();
    int m = getPaddingBottom();
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    setMeasuredDimension(Math.max(paramInt1 - i - k + i + k, getSuggestedMinimumWidth()), Math.max(paramInt2 - j - m + j + m, getSuggestedMinimumHeight()));
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    freeResources();
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool3 = false;
    int i;
    int j;
    label29:
    int k;
    if (System.currentTimeMillis() - this.clickTime > 250L)
    {
      i = 1;
      if (getVisibility() != 0) {
        break label140;
      }
      j = 1;
      if (paramMotionEvent.getAction() != 0) {
        break label145;
      }
      k = 1;
      label39:
      if (this.nStampDimensions != null) {
        break label151;
      }
    }
    label140:
    label145:
    label151:
    for (boolean bool1 = false;; bool1 = this.nStampDimensions.contains(paramMotionEvent.getX(), paramMotionEvent.getY()))
    {
      boolean bool2 = bool3;
      if (hasClickthrough())
      {
        bool2 = bool3;
        if (i != 0)
        {
          bool2 = bool3;
          if (j != 0)
          {
            bool2 = bool3;
            if (k != 0)
            {
              bool2 = bool3;
              if (bool1)
              {
                getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.nTVRating.clickthrough)));
                this.clickTime = paramMotionEvent.getDownTime();
                bool2 = true;
              }
            }
          }
        }
      }
      return bool2;
      i = 0;
      break;
      j = 0;
      break label29;
      k = 0;
      break label39;
    }
  }
  
  public void reshow()
  {
    if ((this.nTVRatingConfiguration != null) && (this.nTVRatingConfiguration.durationSeconds > 0L))
    {
      setAlphaForView(this, 1.0F);
      setVisibility(0);
      startAnimation();
      return;
    }
    setAlphaForView(this, 0.0F);
    setVisibility(8);
  }
  
  public void restoreState(RestoreState paramRestoreState)
  {
    setTVRating(paramRestoreState.tvRating);
    if (paramRestoreState.isShowing) {
      reshow();
    }
  }
  
  public void setTVRating(FCCTVRating paramFCCTVRating)
  {
    if ((paramFCCTVRating != null) && (!paramFCCTVRating.equals(this.nTVRating)))
    {
      this.nTVRating = paramFCCTVRating;
      freeResources();
    }
  }
  
  public void setTVRatingConfiguration(FCCTVRatingConfiguration paramFCCTVRatingConfiguration)
  {
    this.nTVRatingConfiguration = paramFCCTVRatingConfiguration;
    if (hasTVRatingConfiguration())
    {
      initPaints(paramFCCTVRatingConfiguration.opacity);
      freeResources();
    }
  }
  
  public static final class RestoreState
  {
    public final boolean isShowing;
    public final FCCTVRating tvRating;
    
    public RestoreState(boolean paramBoolean, FCCTVRating paramFCCTVRating)
    {
      this.isShowing = paramBoolean;
      this.tvRating = paramFCCTVRating;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\ui\FCCTVRatingView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */