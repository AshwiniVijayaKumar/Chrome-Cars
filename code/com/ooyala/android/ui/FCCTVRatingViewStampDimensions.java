package com.ooyala.android.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.TypedValue;
import com.ooyala.android.configuration.FCCTVRatingConfiguration;
import com.ooyala.android.configuration.FCCTVRatingConfiguration.Position;

final class FCCTVRatingViewStampDimensions
{
  private static final int BLACK_BORDER_DP = 4;
  private static final int MINIMUM_SIZE_PT = 24;
  private static final int WHITE_BORDER_DP = 2;
  private int blackBorderSize;
  public Rect blackRect;
  public Rect labelsRect;
  public int left;
  private int miniHeight;
  public Rect ratingRect;
  private Dimensions textableDimensions;
  public int top;
  private int totalBorderSize;
  public Rect tvRect;
  private int whiteBorderSize;
  public Rect whiteRect;
  
  public FCCTVRatingViewStampDimensions(Context paramContext, FCCTVRatingConfiguration paramFCCTVRatingConfiguration, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    updateBorder(paramContext);
    updateDimensions(paramContext, paramFCCTVRatingConfiguration.scale, paramInt1, paramInt2);
    updateRects(paramInt1, paramInt2, paramBoolean);
    updatePosition(paramFCCTVRatingConfiguration.position, paramInt1, paramInt2);
  }
  
  private Dimensions calculateBasicTextableDimentions(float paramFloat, int paramInt1, int paramInt2)
  {
    float f2 = paramInt1;
    float f1 = paramInt2;
    if (paramInt1 > paramInt2) {
      f2 = paramInt1 / 2;
    }
    for (;;)
    {
      return new Dimensions(Math.round(paramFloat * f2), Math.round(paramFloat * f1));
      f1 = paramInt2 / 2;
    }
  }
  
  private Dimensions calculateFinalTextableDimensions(Context paramContext, Dimensions paramDimensions)
  {
    int k = paramDimensions.width;
    int i = paramDimensions.height;
    int j = (int)TypedValue.applyDimension(3, 24.0F, paramContext.getResources().getDisplayMetrics());
    k = Math.max(k, j);
    i = Math.max(i, j);
    return new Dimensions(Math.min(i, k), i);
  }
  
  private Dimensions calculateTextableDimensions(Context paramContext, float paramFloat, int paramInt1, int paramInt2)
  {
    return calculateFinalTextableDimensions(paramContext, calculateBasicTextableDimentions(paramFloat, paramInt1, paramInt2));
  }
  
  private void updateBorder(Context paramContext)
  {
    this.whiteBorderSize = ((int)TypedValue.applyDimension(1, 2.0F, paramContext.getResources().getDisplayMetrics()));
    this.blackBorderSize = ((int)TypedValue.applyDimension(1, 4.0F, paramContext.getResources().getDisplayMetrics()));
    this.totalBorderSize = (this.whiteBorderSize + this.blackBorderSize);
  }
  
  private void updateDimensions(Context paramContext, float paramFloat, int paramInt1, int paramInt2)
  {
    this.textableDimensions = calculateTextableDimensions(paramContext, paramFloat, paramInt1, paramInt2);
  }
  
  private void updatePosition(FCCTVRatingConfiguration.Position paramPosition, int paramInt1, int paramInt2)
  {
    paramInt1 -= this.whiteRect.width();
    paramInt2 -= this.whiteRect.height();
    switch (paramPosition)
    {
    default: 
      this.left = 0;
      this.top = 0;
      return;
    case ???: 
      this.left = 0;
      this.top = paramInt2;
      return;
    case ???: 
      this.left = paramInt1;
      this.top = 0;
      return;
    }
    this.left = paramInt1;
    this.top = paramInt2;
  }
  
  private void updateRects(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    Rect localRect = new Rect(this.totalBorderSize, this.totalBorderSize, this.textableDimensions.width, this.textableDimensions.height);
    this.blackRect = new Rect(localRect);
    this.blackRect.inset(-this.blackBorderSize, -this.blackBorderSize);
    this.whiteRect = new Rect(this.blackRect);
    this.whiteRect.inset(-this.whiteBorderSize, -this.whiteBorderSize);
    this.miniHeight = Math.round(localRect.height() * 0.2F);
    paramInt1 = localRect.left;
    int i = localRect.top;
    int j = localRect.right;
    paramInt2 = i + this.miniHeight;
    this.tvRect = new Rect(paramInt1, i, j, paramInt2);
    i = localRect.left;
    j = localRect.right;
    int k = localRect.bottom;
    if (paramBoolean) {}
    for (paramInt1 = this.miniHeight;; paramInt1 = 0)
    {
      paramInt1 = k - paramInt1;
      this.ratingRect = new Rect(i, paramInt2, j, paramInt1);
      this.labelsRect = new Rect(localRect.left, paramInt1, localRect.right, localRect.bottom);
      return;
    }
  }
  
  public boolean contains(float paramFloat1, float paramFloat2)
  {
    return this.whiteRect.contains((int)paramFloat1 - this.left, (int)paramFloat2 - this.top);
  }
  
  private static final class Dimensions
  {
    public final int height;
    public final int width;
    
    public Dimensions(int paramInt1, int paramInt2)
    {
      this.width = paramInt1;
      this.height = paramInt2;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\ui\FCCTVRatingViewStampDimensions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */