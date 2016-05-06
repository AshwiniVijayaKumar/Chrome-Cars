package com.ons.bellareader;

import android.app.Activity;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

public class SimpleGestureFilter
  extends GestureDetector.SimpleOnGestureListener
{
  private static final int ACTION_FAKE = -13;
  public static final int MODE_DYNAMIC = 2;
  public static final int MODE_SOLID = 1;
  public static final int MODE_TRANSPARENT = 0;
  public static final int SWIPE_DOWN = 2;
  public static final int SWIPE_LEFT = 3;
  public static final int SWIPE_RIGHT = 4;
  public static final int SWIPE_UP = 1;
  private Activity context;
  private GestureDetector detector;
  private SimpleGestureListener listener;
  private int mode = 2;
  private boolean running = true;
  private int swipe_Max_Distance = 350;
  private int swipe_Min_Distance = 100;
  private int swipe_Min_Velocity = 100;
  private boolean tapIndicator = false;
  
  public SimpleGestureFilter(Activity paramActivity, SimpleGestureListener paramSimpleGestureListener)
  {
    this.context = paramActivity;
    this.detector = new GestureDetector(paramActivity, this);
    this.listener = paramSimpleGestureListener;
  }
  
  public int getMode()
  {
    return this.mode;
  }
  
  public int getSwipeMaxDistance()
  {
    return this.swipe_Max_Distance;
  }
  
  public int getSwipeMinDistance()
  {
    return this.swipe_Min_Distance;
  }
  
  public int getSwipeMinVelocity()
  {
    return this.swipe_Min_Velocity;
  }
  
  public boolean onDoubleTap(MotionEvent paramMotionEvent)
  {
    return true;
  }
  
  public boolean onDoubleTapEvent(MotionEvent paramMotionEvent)
  {
    return true;
  }
  
  public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    float f1 = Math.abs(paramMotionEvent1.getX() - paramMotionEvent2.getX());
    float f2 = Math.abs(paramMotionEvent1.getY() - paramMotionEvent2.getY());
    if ((f1 > this.swipe_Max_Distance) || (f2 > this.swipe_Max_Distance)) {}
    do
    {
      return false;
      paramFloat1 = Math.abs(paramFloat1);
      paramFloat2 = Math.abs(paramFloat2);
      if ((paramFloat1 > this.swipe_Min_Velocity) && (f1 > this.swipe_Min_Distance))
      {
        if (paramMotionEvent1.getX() > paramMotionEvent2.getX()) {
          this.listener.onSwipe(3);
        }
        for (;;)
        {
          return true;
          this.listener.onSwipe(4);
        }
      }
    } while ((paramFloat2 <= this.swipe_Min_Velocity) || (f2 <= this.swipe_Min_Distance));
    if (paramMotionEvent1.getY() > paramMotionEvent2.getY()) {
      this.listener.onSwipe(1);
    }
    for (;;)
    {
      return true;
      this.listener.onSwipe(2);
    }
  }
  
  public boolean onSingleTapConfirmed(MotionEvent paramMotionEvent)
  {
    if (this.mode == 2)
    {
      paramMotionEvent.setAction(-13);
      this.context.dispatchTouchEvent(paramMotionEvent);
    }
    return false;
  }
  
  public boolean onSingleTapUp(MotionEvent paramMotionEvent)
  {
    this.tapIndicator = true;
    return false;
  }
  
  public void onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!this.running) {}
    do
    {
      boolean bool;
      do
      {
        return;
        bool = this.detector.onTouchEvent(paramMotionEvent);
        if (this.mode == 1)
        {
          paramMotionEvent.setAction(3);
          return;
        }
      } while (this.mode != 2);
      if (paramMotionEvent.getAction() == -13)
      {
        paramMotionEvent.setAction(1);
        return;
      }
      if (bool)
      {
        paramMotionEvent.setAction(3);
        return;
      }
    } while (!this.tapIndicator);
    paramMotionEvent.setAction(0);
    this.tapIndicator = false;
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    this.running = paramBoolean;
  }
  
  public void setMode(int paramInt)
  {
    this.mode = paramInt;
  }
  
  public void setSwipeMaxDistance(int paramInt)
  {
    this.swipe_Max_Distance = paramInt;
  }
  
  public void setSwipeMinDistance(int paramInt)
  {
    this.swipe_Min_Distance = paramInt;
  }
  
  public void setSwipeMinVelocity(int paramInt)
  {
    this.swipe_Min_Velocity = paramInt;
  }
  
  static abstract interface SimpleGestureListener
  {
    public abstract void onDoubleTap();
    
    public abstract void onSwipe(int paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\bellareader\SimpleGestureFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */