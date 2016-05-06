package com.joanzapata.pdfview;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.PointF;
import android.view.animation.DecelerateInterpolator;

class AnimationManager
{
  private ValueAnimator animation;
  private PDFView pdfView;
  
  public AnimationManager(PDFView paramPDFView)
  {
    this.pdfView = paramPDFView;
  }
  
  public void startXAnimation(float paramFloat1, float paramFloat2)
  {
    if (this.animation != null) {
      this.animation.cancel();
    }
    this.animation = ValueAnimator.ofFloat(new float[] { paramFloat1, paramFloat2 });
    this.animation.setInterpolator(new DecelerateInterpolator());
    this.animation.addUpdateListener(new XAnimation());
    this.animation.setDuration(400L);
    this.animation.start();
  }
  
  public void startYAnimation(float paramFloat1, float paramFloat2)
  {
    if (this.animation != null) {
      this.animation.cancel();
    }
    this.animation = ValueAnimator.ofFloat(new float[] { paramFloat1, paramFloat2 });
    this.animation.setInterpolator(new DecelerateInterpolator());
    this.animation.addUpdateListener(new YAnimation());
    this.animation.setDuration(400L);
    this.animation.start();
  }
  
  public void startZoomAnimation(float paramFloat1, float paramFloat2)
  {
    if (this.animation != null) {
      this.animation.cancel();
    }
    this.animation = ValueAnimator.ofFloat(new float[] { paramFloat1, paramFloat2 });
    this.animation.setInterpolator(new DecelerateInterpolator());
    ZoomAnimation localZoomAnimation = new ZoomAnimation();
    this.animation.addUpdateListener(localZoomAnimation);
    this.animation.addListener(localZoomAnimation);
    this.animation.setDuration(400L);
    this.animation.start();
  }
  
  public void stopAll()
  {
    if (this.animation != null)
    {
      this.animation.cancel();
      this.animation = null;
    }
  }
  
  class XAnimation
    implements ValueAnimator.AnimatorUpdateListener
  {
    XAnimation() {}
    
    public void onAnimationUpdate(ValueAnimator paramValueAnimator)
    {
      float f = ((Float)paramValueAnimator.getAnimatedValue()).floatValue();
      AnimationManager.this.pdfView.moveTo(f, AnimationManager.this.pdfView.getCurrentYOffset());
    }
  }
  
  class YAnimation
    implements ValueAnimator.AnimatorUpdateListener
  {
    YAnimation() {}
    
    public void onAnimationUpdate(ValueAnimator paramValueAnimator)
    {
      float f = ((Float)paramValueAnimator.getAnimatedValue()).floatValue();
      AnimationManager.this.pdfView.moveTo(AnimationManager.this.pdfView.getCurrentXOffset(), f);
    }
  }
  
  class ZoomAnimation
    implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener
  {
    ZoomAnimation() {}
    
    public void onAnimationCancel(Animator paramAnimator) {}
    
    public void onAnimationEnd(Animator paramAnimator)
    {
      AnimationManager.this.pdfView.loadPages();
    }
    
    public void onAnimationRepeat(Animator paramAnimator) {}
    
    public void onAnimationStart(Animator paramAnimator) {}
    
    public void onAnimationUpdate(ValueAnimator paramValueAnimator)
    {
      float f = ((Float)paramValueAnimator.getAnimatedValue()).floatValue();
      AnimationManager.this.pdfView.zoomCenteredTo(f, new PointF(AnimationManager.this.pdfView.getWidth() / 2, AnimationManager.this.pdfView.getHeight() / 2));
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\joanzapata\pdfview\AnimationManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */