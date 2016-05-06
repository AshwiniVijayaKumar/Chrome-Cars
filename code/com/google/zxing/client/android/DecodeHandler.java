package com.google.zxing.client.android;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.ReaderException;
import com.google.zxing.client.android.camera.CameraManager;
import com.google.zxing.common.HybridBinarizer;
import java.util.Map;

final class DecodeHandler
  extends Handler
{
  private static final String TAG = DecodeHandler.class.getSimpleName();
  private final CaptureActivity activity;
  private final MultiFormatReader multiFormatReader = new MultiFormatReader();
  private boolean running = true;
  
  DecodeHandler(CaptureActivity paramCaptureActivity, Map<DecodeHintType, Object> paramMap)
  {
    this.multiFormatReader.setHints(paramMap);
    this.activity = paramCaptureActivity;
  }
  
  private void decode(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    long l1 = System.currentTimeMillis();
    Object localObject = null;
    PlanarYUVLuminanceSource localPlanarYUVLuminanceSource = this.activity.getCameraManager().buildLuminanceSource(paramArrayOfByte, paramInt1, paramInt2);
    paramArrayOfByte = (byte[])localObject;
    if (localPlanarYUVLuminanceSource != null) {
      paramArrayOfByte = new BinaryBitmap(new HybridBinarizer(localPlanarYUVLuminanceSource));
    }
    try
    {
      paramArrayOfByte = this.multiFormatReader.decodeWithState(paramArrayOfByte);
      this.multiFormatReader.reset();
    }
    catch (ReaderException paramArrayOfByte)
    {
      for (;;)
      {
        paramArrayOfByte = paramArrayOfByte;
        this.multiFormatReader.reset();
        paramArrayOfByte = (byte[])localObject;
      }
    }
    finally
    {
      paramArrayOfByte = finally;
      this.multiFormatReader.reset();
      throw paramArrayOfByte;
    }
    localObject = this.activity.getHandler();
    if (paramArrayOfByte != null)
    {
      l2 = System.currentTimeMillis();
      Log.d(TAG, "Found barcode in " + (l2 - l1) + " ms");
      if (localObject != null)
      {
        paramArrayOfByte = Message.obtain((Handler)localObject, R.id.decode_succeeded, paramArrayOfByte);
        localObject = new Bundle();
        ((Bundle)localObject).putParcelable("barcode_bitmap", toBitmap(localPlanarYUVLuminanceSource, localPlanarYUVLuminanceSource.renderCroppedGreyscaleBitmap()));
        paramArrayOfByte.setData((Bundle)localObject);
        paramArrayOfByte.sendToTarget();
      }
    }
    while (localObject == null)
    {
      long l2;
      return;
    }
    Message.obtain((Handler)localObject, R.id.decode_failed).sendToTarget();
  }
  
  private static Bitmap toBitmap(LuminanceSource paramLuminanceSource, int[] paramArrayOfInt)
  {
    int i = paramLuminanceSource.getWidth();
    int j = paramLuminanceSource.getHeight();
    paramLuminanceSource = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
    paramLuminanceSource.setPixels(paramArrayOfInt, 0, i, 0, 0, i, j);
    return paramLuminanceSource;
  }
  
  public void handleMessage(Message paramMessage)
  {
    if (!this.running) {}
    do
    {
      return;
      if (paramMessage.what == R.id.decode)
      {
        decode((byte[])paramMessage.obj, paramMessage.arg1, paramMessage.arg2);
        return;
      }
    } while (paramMessage.what != R.id.quit);
    this.running = false;
    Looper.myLooper().quit();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\DecodeHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */