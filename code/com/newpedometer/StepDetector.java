package com.newpedometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

public class StepDetector
  implements SensorEventListener
{
  private static final String TAG = "StepDetector";
  private float[] mLastDiff = new float[6];
  private float[] mLastDirections = new float[6];
  private float[][] mLastExtremes = { new float[6], new float[6] };
  private int mLastMatch = -1;
  private float[] mLastValues = new float[6];
  private float mLimit = 10.0F;
  private float[] mScale = new float[2];
  private ArrayList<StepListener> mStepListeners = new ArrayList();
  private float mYOffset = 'Ǡ' * 0.5F;
  
  public StepDetector()
  {
    this.mScale[0] = (-('Ǡ' * 0.5F * 0.05098581F));
    this.mScale[1] = (-('Ǡ' * 0.5F * 0.016666668F));
  }
  
  public void addStepListener(StepListener paramStepListener)
  {
    this.mStepListeners.add(paramStepListener);
  }
  
  public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
  
  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    Sensor localSensor = paramSensorEvent.sensor;
    int i;
    int j;
    float f1;
    for (;;)
    {
      Object localObject;
      try
      {
        if (localSensor.getType() == 3) {
          return;
        }
        if (localSensor.getType() != 1) {
          break label352;
        }
        i = 1;
      }
      finally {}
      if (j >= 3) {
        continue;
      }
      localObject += this.mYOffset + paramSensorEvent.values[j] * this.mScale[i];
      j += 1;
    }
    f1 /= 3.0F;
    label92:
    float f2;
    label117:
    float f3;
    label188:
    int k;
    if (f1 > this.mLastValues[0])
    {
      i = 1;
      f2 = i;
      if (f2 != -this.mLastDirections[0]) {
        break label313;
      }
      if (f2 <= 0.0F) {
        break label364;
      }
      i = 0;
      this.mLastExtremes[i][0] = this.mLastValues[0];
      f3 = Math.abs(this.mLastExtremes[i][0] - this.mLastExtremes[(1 - i)][0]);
      if (f3 <= this.mLimit) {
        break label305;
      }
      if (f3 <= this.mLastDiff[0] * 2.0F / 3.0F) {
        break label370;
      }
      j = 1;
      if (this.mLastDiff[0] <= f3 / 3.0F) {
        break label376;
      }
      k = 1;
      label206:
      if (this.mLastMatch == 1 - i) {
        break label382;
      }
    }
    label305:
    label313:
    label352:
    label356:
    label358:
    label364:
    label370:
    label376:
    label382:
    for (int m = 1;; m = 0)
    {
      if ((j != 0) && (k != 0) && (m != 0))
      {
        Log.i("StepDetector", "step");
        paramSensorEvent = this.mStepListeners.iterator();
        for (;;)
        {
          if (paramSensorEvent.hasNext())
          {
            ((StepListener)paramSensorEvent.next()).onStep();
            continue;
            if (f1 >= this.mLastValues[0]) {
              break label358;
            }
            i = -1;
            break;
          }
        }
      }
      for (this.mLastMatch = i;; this.mLastMatch = -1)
      {
        this.mLastDiff[0] = f3;
        this.mLastDirections[0] = f2;
        this.mLastValues[0] = f1;
        break;
      }
      for (;;)
      {
        if (i != 1) {
          break label356;
        }
        f1 = 0.0F;
        j = 0;
        break;
        i = 0;
      }
      break;
      i = 0;
      break label92;
      i = 1;
      break label117;
      j = 0;
      break label188;
      k = 0;
      break label206;
    }
  }
  
  public void setSensitivity(float paramFloat)
  {
    this.mLimit = paramFloat;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\newpedometer\StepDetector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */