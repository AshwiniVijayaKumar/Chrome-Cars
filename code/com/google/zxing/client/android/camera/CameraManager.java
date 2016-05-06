package com.google.zxing.client.android.camera;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.client.android.camera.open.OpenCameraInterface;
import com.google.zxing.client.android.camera.open.OpenCameraManager;
import java.io.IOException;

public final class CameraManager
{
  private static final int MAX_FRAME_HEIGHT = 400;
  private static final int MAX_FRAME_WIDTH = 600;
  private static final int MIN_FRAME_HEIGHT = 240;
  private static final int MIN_FRAME_WIDTH = 240;
  private static final String TAG = CameraManager.class.getSimpleName();
  private AutoFocusManager autoFocusManager;
  private Camera camera;
  private final CameraConfigurationManager configManager;
  private final Context context;
  private Rect framingRect;
  private Rect framingRectInPreview;
  private boolean initialized;
  private final PreviewCallback previewCallback;
  private boolean previewing;
  private int requestedFramingRectHeight;
  private int requestedFramingRectWidth;
  
  public CameraManager(Context paramContext)
  {
    this.context = paramContext;
    this.configManager = new CameraConfigurationManager(paramContext);
    this.previewCallback = new PreviewCallback(this.configManager);
  }
  
  public PlanarYUVLuminanceSource buildLuminanceSource(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    Rect localRect = getFramingRectInPreview();
    if (localRect == null) {
      return null;
    }
    return new PlanarYUVLuminanceSource(paramArrayOfByte, paramInt1, paramInt2, localRect.left, localRect.top, localRect.width(), localRect.height(), false);
  }
  
  public void closeDriver()
  {
    try
    {
      if (this.camera != null)
      {
        this.camera.release();
        this.camera = null;
        this.framingRect = null;
        this.framingRectInPreview = null;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public Rect getFramingRect()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_0
    //   6: getfield 98	com/google/zxing/client/android/camera/CameraManager:framingRect	Landroid/graphics/Rect;
    //   9: ifnonnull +146 -> 155
    //   12: aload_0
    //   13: getfield 91	com/google/zxing/client/android/camera/CameraManager:camera	Landroid/hardware/Camera;
    //   16: astore 6
    //   18: aload 6
    //   20: ifnonnull +8 -> 28
    //   23: aload_0
    //   24: monitorexit
    //   25: aload 5
    //   27: areturn
    //   28: aload_0
    //   29: getfield 55	com/google/zxing/client/android/camera/CameraManager:configManager	Lcom/google/zxing/client/android/camera/CameraConfigurationManager;
    //   32: invokevirtual 105	com/google/zxing/client/android/camera/CameraConfigurationManager:getScreenResolution	()Landroid/graphics/Point;
    //   35: astore 6
    //   37: aload 6
    //   39: ifnull -16 -> 23
    //   42: aload 6
    //   44: getfield 110	android/graphics/Point:x	I
    //   47: iconst_3
    //   48: imul
    //   49: iconst_4
    //   50: idiv
    //   51: istore_2
    //   52: iload_2
    //   53: sipush 240
    //   56: if_icmpge +108 -> 164
    //   59: sipush 240
    //   62: istore_1
    //   63: aload 6
    //   65: getfield 113	android/graphics/Point:y	I
    //   68: iconst_3
    //   69: imul
    //   70: iconst_4
    //   71: idiv
    //   72: istore_3
    //   73: iload_3
    //   74: sipush 240
    //   77: if_icmpge +103 -> 180
    //   80: sipush 240
    //   83: istore_2
    //   84: aload 6
    //   86: getfield 110	android/graphics/Point:x	I
    //   89: iload_1
    //   90: isub
    //   91: iconst_2
    //   92: idiv
    //   93: istore_3
    //   94: aload 6
    //   96: getfield 113	android/graphics/Point:y	I
    //   99: iload_2
    //   100: isub
    //   101: iconst_2
    //   102: idiv
    //   103: istore 4
    //   105: aload_0
    //   106: new 72	android/graphics/Rect
    //   109: dup
    //   110: iload_3
    //   111: iload 4
    //   113: iload_3
    //   114: iload_1
    //   115: iadd
    //   116: iload 4
    //   118: iload_2
    //   119: iadd
    //   120: invokespecial 116	android/graphics/Rect:<init>	(IIII)V
    //   123: putfield 98	com/google/zxing/client/android/camera/CameraManager:framingRect	Landroid/graphics/Rect;
    //   126: getstatic 42	com/google/zxing/client/android/camera/CameraManager:TAG	Ljava/lang/String;
    //   129: new 118	java/lang/StringBuilder
    //   132: dup
    //   133: invokespecial 119	java/lang/StringBuilder:<init>	()V
    //   136: ldc 121
    //   138: invokevirtual 125	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   141: aload_0
    //   142: getfield 98	com/google/zxing/client/android/camera/CameraManager:framingRect	Landroid/graphics/Rect;
    //   145: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   148: invokevirtual 131	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   151: invokestatic 137	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   154: pop
    //   155: aload_0
    //   156: getfield 98	com/google/zxing/client/android/camera/CameraManager:framingRect	Landroid/graphics/Rect;
    //   159: astore 5
    //   161: goto -138 -> 23
    //   164: iload_2
    //   165: istore_1
    //   166: iload_2
    //   167: sipush 600
    //   170: if_icmple -107 -> 63
    //   173: sipush 600
    //   176: istore_1
    //   177: goto -114 -> 63
    //   180: iload_3
    //   181: istore_2
    //   182: iload_3
    //   183: sipush 400
    //   186: if_icmple -102 -> 84
    //   189: sipush 400
    //   192: istore_2
    //   193: goto -109 -> 84
    //   196: astore 5
    //   198: aload_0
    //   199: monitorexit
    //   200: aload 5
    //   202: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	203	0	this	CameraManager
    //   62	115	1	i	int
    //   51	142	2	j	int
    //   72	115	3	k	int
    //   103	17	4	m	int
    //   1	159	5	localRect	Rect
    //   196	5	5	localObject1	Object
    //   16	79	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   5	18	196	finally
    //   28	37	196	finally
    //   42	52	196	finally
    //   63	73	196	finally
    //   84	155	196	finally
    //   155	161	196	finally
  }
  
  /* Error */
  public Rect getFramingRectInPreview()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: getfield 100	com/google/zxing/client/android/camera/CameraManager:framingRectInPreview	Landroid/graphics/Rect;
    //   8: ifnonnull +144 -> 152
    //   11: aload_0
    //   12: invokevirtual 139	com/google/zxing/client/android/camera/CameraManager:getFramingRect	()Landroid/graphics/Rect;
    //   15: astore_1
    //   16: aload_1
    //   17: ifnonnull +9 -> 26
    //   20: aload_2
    //   21: astore_1
    //   22: aload_0
    //   23: monitorexit
    //   24: aload_1
    //   25: areturn
    //   26: new 72	android/graphics/Rect
    //   29: dup
    //   30: aload_1
    //   31: invokespecial 142	android/graphics/Rect:<init>	(Landroid/graphics/Rect;)V
    //   34: astore_3
    //   35: aload_0
    //   36: getfield 55	com/google/zxing/client/android/camera/CameraManager:configManager	Lcom/google/zxing/client/android/camera/CameraConfigurationManager;
    //   39: invokevirtual 145	com/google/zxing/client/android/camera/CameraConfigurationManager:getCameraResolution	()Landroid/graphics/Point;
    //   42: astore 4
    //   44: aload_0
    //   45: getfield 55	com/google/zxing/client/android/camera/CameraManager:configManager	Lcom/google/zxing/client/android/camera/CameraConfigurationManager;
    //   48: invokevirtual 105	com/google/zxing/client/android/camera/CameraConfigurationManager:getScreenResolution	()Landroid/graphics/Point;
    //   51: astore 5
    //   53: aload_2
    //   54: astore_1
    //   55: aload 4
    //   57: ifnull -35 -> 22
    //   60: aload_2
    //   61: astore_1
    //   62: aload 5
    //   64: ifnull -42 -> 22
    //   67: aload_3
    //   68: aload_3
    //   69: getfield 75	android/graphics/Rect:left	I
    //   72: aload 4
    //   74: getfield 110	android/graphics/Point:x	I
    //   77: imul
    //   78: aload 5
    //   80: getfield 110	android/graphics/Point:x	I
    //   83: idiv
    //   84: putfield 75	android/graphics/Rect:left	I
    //   87: aload_3
    //   88: aload_3
    //   89: getfield 148	android/graphics/Rect:right	I
    //   92: aload 4
    //   94: getfield 110	android/graphics/Point:x	I
    //   97: imul
    //   98: aload 5
    //   100: getfield 110	android/graphics/Point:x	I
    //   103: idiv
    //   104: putfield 148	android/graphics/Rect:right	I
    //   107: aload_3
    //   108: aload_3
    //   109: getfield 78	android/graphics/Rect:top	I
    //   112: aload 4
    //   114: getfield 113	android/graphics/Point:y	I
    //   117: imul
    //   118: aload 5
    //   120: getfield 113	android/graphics/Point:y	I
    //   123: idiv
    //   124: putfield 78	android/graphics/Rect:top	I
    //   127: aload_3
    //   128: aload_3
    //   129: getfield 151	android/graphics/Rect:bottom	I
    //   132: aload 4
    //   134: getfield 113	android/graphics/Point:y	I
    //   137: imul
    //   138: aload 5
    //   140: getfield 113	android/graphics/Point:y	I
    //   143: idiv
    //   144: putfield 151	android/graphics/Rect:bottom	I
    //   147: aload_0
    //   148: aload_3
    //   149: putfield 100	com/google/zxing/client/android/camera/CameraManager:framingRectInPreview	Landroid/graphics/Rect;
    //   152: aload_0
    //   153: getfield 100	com/google/zxing/client/android/camera/CameraManager:framingRectInPreview	Landroid/graphics/Rect;
    //   156: astore_1
    //   157: goto -135 -> 22
    //   160: astore_1
    //   161: aload_0
    //   162: monitorexit
    //   163: aload_1
    //   164: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	165	0	this	CameraManager
    //   15	142	1	localObject1	Object
    //   160	4	1	localObject2	Object
    //   1	60	2	localObject3	Object
    //   34	115	3	localRect	Rect
    //   42	91	4	localPoint1	android.graphics.Point
    //   51	88	5	localPoint2	android.graphics.Point
    // Exception table:
    //   from	to	target	type
    //   4	16	160	finally
    //   26	53	160	finally
    //   67	152	160	finally
    //   152	157	160	finally
  }
  
  /* Error */
  public boolean isOpen()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 91	com/google/zxing/client/android/camera/CameraManager:camera	Landroid/hardware/Camera;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnull +9 -> 17
    //   11: iconst_1
    //   12: istore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: iload_1
    //   16: ireturn
    //   17: iconst_0
    //   18: istore_1
    //   19: goto -6 -> 13
    //   22: astore_2
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_2
    //   26: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	27	0	this	CameraManager
    //   12	7	1	bool	boolean
    //   6	2	2	localCamera	Camera
    //   22	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	22	finally
  }
  
  public void openDriver(SurfaceHolder paramSurfaceHolder)
    throws IOException
  {
    Camera localCamera1;
    try
    {
      Camera localCamera2 = this.camera;
      localCamera1 = localCamera2;
      if (localCamera2 != null) {
        break label54;
      }
      localCamera1 = ((OpenCameraInterface)new OpenCameraManager().build()).open();
      if (localCamera1 == null) {
        throw new IOException();
      }
    }
    finally {}
    this.camera = localCamera1;
    label54:
    localCamera1.setPreviewDisplay(paramSurfaceHolder);
    if (!this.initialized)
    {
      this.initialized = true;
      this.configManager.initFromCameraParameters(localCamera1);
      if ((this.requestedFramingRectWidth > 0) && (this.requestedFramingRectHeight > 0))
      {
        setManualFramingRect(this.requestedFramingRectWidth, this.requestedFramingRectHeight);
        this.requestedFramingRectWidth = 0;
        this.requestedFramingRectHeight = 0;
      }
    }
    paramSurfaceHolder = localCamera1.getParameters();
    if (paramSurfaceHolder == null) {
      paramSurfaceHolder = null;
    }
    try
    {
      for (;;)
      {
        this.configManager.setDesiredCameraParameters(localCamera1, false);
        return;
        paramSurfaceHolder = paramSurfaceHolder.flatten();
      }
    }
    catch (RuntimeException localRuntimeException)
    {
      for (;;)
      {
        Log.w(TAG, "Camera rejected parameters. Setting only minimal safe-mode parameters");
        Log.i(TAG, "Resetting to saved camera params: " + paramSurfaceHolder);
        if (paramSurfaceHolder != null)
        {
          Camera.Parameters localParameters = localCamera1.getParameters();
          localParameters.unflatten(paramSurfaceHolder);
          try
          {
            localCamera1.setParameters(localParameters);
            this.configManager.setDesiredCameraParameters(localCamera1, true);
          }
          catch (RuntimeException paramSurfaceHolder)
          {
            Log.w(TAG, "Camera rejected even safe-mode parameters! No configuration");
          }
        }
      }
    }
  }
  
  public void requestPreviewFrame(Handler paramHandler, int paramInt)
  {
    try
    {
      Camera localCamera = this.camera;
      if ((localCamera != null) && (this.previewing))
      {
        this.previewCallback.setHandler(paramHandler, paramInt);
        localCamera.setOneShotPreviewCallback(this.previewCallback);
      }
      return;
    }
    finally {}
  }
  
  /* Error */
  public void setManualFramingRect(int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 178	com/google/zxing/client/android/camera/CameraManager:initialized	Z
    //   6: ifeq +125 -> 131
    //   9: aload_0
    //   10: getfield 55	com/google/zxing/client/android/camera/CameraManager:configManager	Lcom/google/zxing/client/android/camera/CameraConfigurationManager;
    //   13: invokevirtual 105	com/google/zxing/client/android/camera/CameraConfigurationManager:getScreenResolution	()Landroid/graphics/Point;
    //   16: astore 5
    //   18: iload_1
    //   19: istore_3
    //   20: iload_1
    //   21: aload 5
    //   23: getfield 110	android/graphics/Point:x	I
    //   26: if_icmple +9 -> 35
    //   29: aload 5
    //   31: getfield 110	android/graphics/Point:x	I
    //   34: istore_3
    //   35: iload_2
    //   36: istore_1
    //   37: iload_2
    //   38: aload 5
    //   40: getfield 113	android/graphics/Point:y	I
    //   43: if_icmple +9 -> 52
    //   46: aload 5
    //   48: getfield 113	android/graphics/Point:y	I
    //   51: istore_1
    //   52: aload 5
    //   54: getfield 110	android/graphics/Point:x	I
    //   57: iload_3
    //   58: isub
    //   59: iconst_2
    //   60: idiv
    //   61: istore_2
    //   62: aload 5
    //   64: getfield 113	android/graphics/Point:y	I
    //   67: iload_1
    //   68: isub
    //   69: iconst_2
    //   70: idiv
    //   71: istore 4
    //   73: aload_0
    //   74: new 72	android/graphics/Rect
    //   77: dup
    //   78: iload_2
    //   79: iload 4
    //   81: iload_2
    //   82: iload_3
    //   83: iadd
    //   84: iload 4
    //   86: iload_1
    //   87: iadd
    //   88: invokespecial 116	android/graphics/Rect:<init>	(IIII)V
    //   91: putfield 98	com/google/zxing/client/android/camera/CameraManager:framingRect	Landroid/graphics/Rect;
    //   94: getstatic 42	com/google/zxing/client/android/camera/CameraManager:TAG	Ljava/lang/String;
    //   97: new 118	java/lang/StringBuilder
    //   100: dup
    //   101: invokespecial 119	java/lang/StringBuilder:<init>	()V
    //   104: ldc -19
    //   106: invokevirtual 125	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: aload_0
    //   110: getfield 98	com/google/zxing/client/android/camera/CameraManager:framingRect	Landroid/graphics/Rect;
    //   113: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   116: invokevirtual 131	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   119: invokestatic 137	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   122: pop
    //   123: aload_0
    //   124: aconst_null
    //   125: putfield 100	com/google/zxing/client/android/camera/CameraManager:framingRectInPreview	Landroid/graphics/Rect;
    //   128: aload_0
    //   129: monitorexit
    //   130: return
    //   131: aload_0
    //   132: iload_1
    //   133: putfield 184	com/google/zxing/client/android/camera/CameraManager:requestedFramingRectWidth	I
    //   136: aload_0
    //   137: iload_2
    //   138: putfield 186	com/google/zxing/client/android/camera/CameraManager:requestedFramingRectHeight	I
    //   141: goto -13 -> 128
    //   144: astore 5
    //   146: aload_0
    //   147: monitorexit
    //   148: aload 5
    //   150: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	151	0	this	CameraManager
    //   0	151	1	paramInt1	int
    //   0	151	2	paramInt2	int
    //   19	65	3	i	int
    //   71	17	4	j	int
    //   16	47	5	localPoint	android.graphics.Point
    //   144	5	5	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	18	144	finally
    //   20	35	144	finally
    //   37	52	144	finally
    //   52	128	144	finally
    //   131	141	144	finally
  }
  
  public void setTorch(boolean paramBoolean)
  {
    try
    {
      if (this.camera != null)
      {
        if (this.autoFocusManager != null) {
          this.autoFocusManager.stop();
        }
        this.configManager.setTorch(this.camera, paramBoolean);
        if (this.autoFocusManager != null) {
          this.autoFocusManager.start();
        }
      }
      return;
    }
    finally {}
  }
  
  public void startPreview()
  {
    try
    {
      Camera localCamera = this.camera;
      if ((localCamera != null) && (!this.previewing))
      {
        localCamera.startPreview();
        this.previewing = true;
        this.autoFocusManager = new AutoFocusManager(this.context, this.camera);
      }
      return;
    }
    finally {}
  }
  
  public void stopPreview()
  {
    try
    {
      if (this.autoFocusManager != null)
      {
        this.autoFocusManager.stop();
        this.autoFocusManager = null;
      }
      if ((this.camera != null) && (this.previewing))
      {
        this.camera.stopPreview();
        this.previewCallback.setHandler(null, 0);
        this.previewing = false;
      }
      return;
    }
    finally {}
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\camera\CameraManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */