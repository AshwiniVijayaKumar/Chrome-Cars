package com.ons.barcodeSupport;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import java.util.Iterator;
import java.util.List;

public class CameraPreview
  extends SurfaceView
  implements SurfaceHolder.Callback
{
  private static final String TAG = "CameraPreview";
  Camera.AutoFocusCallback autoFocusCB = new Camera.AutoFocusCallback()
  {
    public void onAutoFocus(boolean paramAnonymousBoolean, Camera paramAnonymousCamera)
    {
      CameraPreview.this.mAutoFocusHandler.postDelayed(CameraPreview.this.doAutoFocus, 1000L);
    }
  };
  private Runnable doAutoFocus = new Runnable()
  {
    public void run()
    {
      if ((CameraPreview.this.mCamera != null) && (CameraPreview.this.mPreviewing) && (CameraPreview.this.mAutoFocus) && (CameraPreview.this.mSurfaceCreated)) {
        CameraPreview.this.mCamera.autoFocus(CameraPreview.this.autoFocusCB);
      }
    }
  };
  private boolean mAutoFocus = true;
  private Handler mAutoFocusHandler;
  private Camera mCamera;
  private Camera.PreviewCallback mPreviewCallback;
  private boolean mPreviewing = true;
  private boolean mSurfaceCreated = false;
  
  public CameraPreview(Context paramContext)
  {
    super(paramContext);
  }
  
  public CameraPreview(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  private void adjustViewSize(Camera.Size paramSize)
  {
    Point localPoint = convertSizeToLandscapeOrientation(new Point(getWidth(), getHeight()));
    float f = paramSize.width / paramSize.height;
    if (localPoint.x / localPoint.y > f)
    {
      setViewSize((int)(localPoint.y * f), localPoint.y);
      return;
    }
    setViewSize(localPoint.x, (int)(localPoint.x / f));
  }
  
  private Point convertSizeToLandscapeOrientation(Point paramPoint)
  {
    if (getDisplayOrientation() % 180 == 0) {
      return paramPoint;
    }
    return new Point(paramPoint.y, paramPoint.x);
  }
  
  private Camera.Size getOptimalPreviewSize()
  {
    Object localObject2;
    if (this.mCamera == null) {
      localObject2 = null;
    }
    Object localObject1;
    int i;
    do
    {
      return (Camera.Size)localObject2;
      localObject3 = this.mCamera.getParameters().getSupportedPreviewSizes();
      localObject1 = DisplayUtils.getScreenResolution(getContext());
      int j = ((Point)localObject1).x;
      i = ((Point)localObject1).y;
      if (DisplayUtils.getScreenOrientation(getContext()) == 1)
      {
        j = ((Point)localObject1).y;
        i = ((Point)localObject1).x;
      }
      double d2 = j / i;
      if (localObject3 == null) {
        return null;
      }
      localObject1 = null;
      d1 = Double.MAX_VALUE;
      Iterator localIterator = ((List)localObject3).iterator();
      while (localIterator.hasNext())
      {
        localObject2 = (Camera.Size)localIterator.next();
        if ((Math.abs(((Camera.Size)localObject2).width / ((Camera.Size)localObject2).height - d2) <= 0.1D) && (Math.abs(((Camera.Size)localObject2).height - i) < d1))
        {
          localObject1 = localObject2;
          d1 = Math.abs(((Camera.Size)localObject2).height - i);
        }
      }
      localObject2 = localObject1;
    } while (localObject1 != null);
    double d1 = Double.MAX_VALUE;
    Object localObject3 = ((List)localObject3).iterator();
    for (;;)
    {
      localObject2 = localObject1;
      if (!((Iterator)localObject3).hasNext()) {
        break;
      }
      localObject2 = (Camera.Size)((Iterator)localObject3).next();
      if (Math.abs(((Camera.Size)localObject2).height - i) < d1)
      {
        localObject1 = localObject2;
        d1 = Math.abs(((Camera.Size)localObject2).height - i);
      }
    }
  }
  
  private void setViewSize(int paramInt1, int paramInt2)
  {
    ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
    if (getDisplayOrientation() % 180 == 0) {
      localLayoutParams.width = paramInt1;
    }
    for (localLayoutParams.height = paramInt2;; localLayoutParams.height = paramInt1)
    {
      setLayoutParams(localLayoutParams);
      return;
      localLayoutParams.width = paramInt2;
    }
  }
  
  public int getDisplayOrientation()
  {
    Camera.CameraInfo localCameraInfo = new Camera.CameraInfo();
    Camera.getCameraInfo(0, localCameraInfo);
    int j = ((WindowManager)getContext().getSystemService("window")).getDefaultDisplay().getRotation();
    int i = 0;
    switch (j)
    {
    }
    while (localCameraInfo.facing == 1)
    {
      return (360 - (localCameraInfo.orientation + i) % 360) % 360;
      i = 0;
      continue;
      i = 90;
      continue;
      i = 180;
      continue;
      i = 270;
    }
    return (localCameraInfo.orientation - i + 360) % 360;
  }
  
  public void initCameraPreview()
  {
    if (this.mCamera != null)
    {
      getHolder().addCallback(this);
      getHolder().setType(3);
      if (this.mPreviewing) {
        requestLayout();
      }
    }
    else
    {
      return;
    }
    showCameraPreview();
  }
  
  public void setAutoFocus(boolean paramBoolean)
  {
    if ((this.mCamera == null) || (!this.mPreviewing) || (paramBoolean == this.mAutoFocus)) {
      return;
    }
    this.mAutoFocus = paramBoolean;
    if (this.mAutoFocus)
    {
      Log.v("CameraPreview", "Starting autofocus");
      this.mCamera.autoFocus(this.autoFocusCB);
      return;
    }
    Log.v("CameraPreview", "Cancelling autofocus");
    this.mCamera.cancelAutoFocus();
  }
  
  public void setCamera(Camera paramCamera, Camera.PreviewCallback paramPreviewCallback)
  {
    this.mCamera = paramCamera;
    this.mPreviewCallback = paramPreviewCallback;
    this.mAutoFocusHandler = new Handler();
  }
  
  public void setupCameraParameters()
  {
    Camera.Size localSize = getOptimalPreviewSize();
    Camera.Parameters localParameters = this.mCamera.getParameters();
    localParameters.setPreviewSize(localSize.width, localSize.height);
    this.mCamera.setParameters(localParameters);
    adjustViewSize(localSize);
  }
  
  public void showCameraPreview()
  {
    if (this.mCamera != null) {}
    try
    {
      this.mPreviewing = true;
      setupCameraParameters();
      this.mCamera.setPreviewDisplay(getHolder());
      this.mCamera.setDisplayOrientation(getDisplayOrientation());
      this.mCamera.setOneShotPreviewCallback(this.mPreviewCallback);
      this.mCamera.startPreview();
      if (this.mAutoFocus) {
        this.mCamera.autoFocus(this.autoFocusCB);
      }
      return;
    }
    catch (Exception localException)
    {
      Log.e("CameraPreview", localException.toString(), localException);
    }
  }
  
  public void stopCameraPreview()
  {
    if (this.mCamera != null) {}
    try
    {
      this.mPreviewing = false;
      this.mCamera.cancelAutoFocus();
      this.mCamera.setOneShotPreviewCallback(null);
      this.mCamera.stopPreview();
      return;
    }
    catch (Exception localException)
    {
      Log.e("CameraPreview", localException.toString(), localException);
    }
  }
  
  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramSurfaceHolder.getSurface() == null) {
      return;
    }
    stopCameraPreview();
    showCameraPreview();
  }
  
  public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
  {
    this.mSurfaceCreated = true;
  }
  
  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
  {
    this.mSurfaceCreated = false;
    stopCameraPreview();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\barcodeSupport\CameraPreview.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */