package com.ons.barcodeSupport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public abstract class BarcodeScannerView
  extends FrameLayout
  implements Camera.PreviewCallback
{
  private Camera mCamera;
  private Rect mFramingRectInPreview;
  private CameraPreview mPreview;
  private ViewFinderView mViewFinderView;
  
  public BarcodeScannerView(Context paramContext)
  {
    super(paramContext);
    setupLayout();
  }
  
  public BarcodeScannerView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setupLayout();
  }
  
  @SuppressLint({"NewApi"})
  public boolean getFlash()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.mCamera != null)
    {
      bool1 = bool2;
      if (CameraUtils.isFlashSupported(this.mCamera))
      {
        bool1 = bool2;
        if (this.mCamera.getParameters().getFlashMode().equals("torch")) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  /* Error */
  public Rect getFramingRectInPreview(int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 64	com/ons/barcodeSupport/BarcodeScannerView:mFramingRectInPreview	Landroid/graphics/Rect;
    //   6: ifnonnull +126 -> 132
    //   9: aload_0
    //   10: getfield 66	com/ons/barcodeSupport/BarcodeScannerView:mViewFinderView	Lcom/ons/barcodeSupport/ViewFinderView;
    //   13: invokevirtual 72	com/ons/barcodeSupport/ViewFinderView:getFramingRect	()Landroid/graphics/Rect;
    //   16: astore 5
    //   18: aload_0
    //   19: getfield 66	com/ons/barcodeSupport/BarcodeScannerView:mViewFinderView	Lcom/ons/barcodeSupport/ViewFinderView;
    //   22: invokevirtual 76	com/ons/barcodeSupport/ViewFinderView:getWidth	()I
    //   25: istore_3
    //   26: aload_0
    //   27: getfield 66	com/ons/barcodeSupport/BarcodeScannerView:mViewFinderView	Lcom/ons/barcodeSupport/ViewFinderView;
    //   30: invokevirtual 79	com/ons/barcodeSupport/ViewFinderView:getHeight	()I
    //   33: istore 4
    //   35: aload 5
    //   37: ifnull +12 -> 49
    //   40: iload_3
    //   41: ifeq +8 -> 49
    //   44: iload 4
    //   46: ifne +11 -> 57
    //   49: aconst_null
    //   50: astore 5
    //   52: aload_0
    //   53: monitorexit
    //   54: aload 5
    //   56: areturn
    //   57: new 81	android/graphics/Rect
    //   60: dup
    //   61: aload 5
    //   63: invokespecial 84	android/graphics/Rect:<init>	(Landroid/graphics/Rect;)V
    //   66: astore 5
    //   68: aload 5
    //   70: aload 5
    //   72: getfield 88	android/graphics/Rect:left	I
    //   75: iload_1
    //   76: imul
    //   77: iload_3
    //   78: idiv
    //   79: putfield 88	android/graphics/Rect:left	I
    //   82: aload 5
    //   84: aload 5
    //   86: getfield 91	android/graphics/Rect:right	I
    //   89: iload_1
    //   90: imul
    //   91: iload_3
    //   92: idiv
    //   93: putfield 91	android/graphics/Rect:right	I
    //   96: aload 5
    //   98: aload 5
    //   100: getfield 94	android/graphics/Rect:top	I
    //   103: iload_2
    //   104: imul
    //   105: iload 4
    //   107: idiv
    //   108: putfield 94	android/graphics/Rect:top	I
    //   111: aload 5
    //   113: aload 5
    //   115: getfield 97	android/graphics/Rect:bottom	I
    //   118: iload_2
    //   119: imul
    //   120: iload 4
    //   122: idiv
    //   123: putfield 97	android/graphics/Rect:bottom	I
    //   126: aload_0
    //   127: aload 5
    //   129: putfield 64	com/ons/barcodeSupport/BarcodeScannerView:mFramingRectInPreview	Landroid/graphics/Rect;
    //   132: aload_0
    //   133: getfield 64	com/ons/barcodeSupport/BarcodeScannerView:mFramingRectInPreview	Landroid/graphics/Rect;
    //   136: astore 5
    //   138: goto -86 -> 52
    //   141: astore 5
    //   143: aload_0
    //   144: monitorexit
    //   145: aload 5
    //   147: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	148	0	this	BarcodeScannerView
    //   0	148	1	paramInt1	int
    //   0	148	2	paramInt2	int
    //   25	68	3	i	int
    //   33	90	4	j	int
    //   16	121	5	localRect	Rect
    //   141	5	5	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	35	141	finally
    //   57	132	141	finally
    //   132	138	141	finally
  }
  
  public void setAutoFocus(boolean paramBoolean)
  {
    if (this.mPreview != null) {
      this.mPreview.setAutoFocus(paramBoolean);
    }
  }
  
  @SuppressLint({"NewApi"})
  public void setFlash(boolean paramBoolean)
  {
    Camera.Parameters localParameters;
    if ((this.mCamera != null) && (CameraUtils.isFlashSupported(this.mCamera)))
    {
      localParameters = this.mCamera.getParameters();
      if (!paramBoolean) {
        break label57;
      }
      if (!localParameters.getFlashMode().equals("torch")) {}
    }
    else
    {
      return;
    }
    localParameters.setFlashMode("torch");
    for (;;)
    {
      this.mCamera.setParameters(localParameters);
      return;
      label57:
      if (localParameters.getFlashMode().equals("off")) {
        break;
      }
      localParameters.setFlashMode("off");
    }
  }
  
  public void setupLayout()
  {
    this.mPreview = new CameraPreview(getContext());
    this.mViewFinderView = new ViewFinderView(getContext());
    RelativeLayout localRelativeLayout = new RelativeLayout(getContext());
    localRelativeLayout.setGravity(17);
    localRelativeLayout.setBackgroundColor(-16777216);
    localRelativeLayout.addView(this.mPreview);
    addView(localRelativeLayout);
    addView(this.mViewFinderView);
  }
  
  public void startCamera()
  {
    startCamera(CameraUtils.getCameraInstance());
  }
  
  public void startCamera(int paramInt)
  {
    startCamera(CameraUtils.getCameraInstance(paramInt));
  }
  
  public void startCamera(Camera paramCamera)
  {
    this.mCamera = paramCamera;
    if (this.mCamera != null)
    {
      this.mViewFinderView.setupViewFinder();
      this.mPreview.setCamera(this.mCamera, this);
      this.mPreview.initCameraPreview();
    }
  }
  
  public void stopCamera()
  {
    if (this.mCamera != null)
    {
      this.mPreview.stopCameraPreview();
      this.mPreview.setCamera(null, null);
      this.mCamera.release();
      this.mCamera = null;
    }
  }
  
  @SuppressLint({"NewApi"})
  public void toggleFlash()
  {
    Camera.Parameters localParameters;
    if ((this.mCamera != null) && (CameraUtils.isFlashSupported(this.mCamera)))
    {
      localParameters = this.mCamera.getParameters();
      if (!localParameters.getFlashMode().equals("torch")) {
        break label52;
      }
      localParameters.setFlashMode("off");
    }
    for (;;)
    {
      this.mCamera.setParameters(localParameters);
      return;
      label52:
      localParameters.setFlashMode("torch");
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\barcodeSupport\BarcodeScannerView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */