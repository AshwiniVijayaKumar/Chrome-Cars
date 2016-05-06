package com.google.zxing.client.android;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.ResultPointCallback;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

final class DecodeThread
  extends Thread
{
  public static final String BARCODE_BITMAP = "barcode_bitmap";
  private final CaptureActivity activity;
  private Handler handler;
  private final CountDownLatch handlerInitLatch;
  private final Map<DecodeHintType, Object> hints;
  
  DecodeThread(CaptureActivity paramCaptureActivity, Collection<BarcodeFormat> paramCollection, String paramString, ResultPointCallback paramResultPointCallback)
  {
    this.activity = paramCaptureActivity;
    this.handlerInitLatch = new CountDownLatch(1);
    this.hints = new EnumMap(DecodeHintType.class);
    Object localObject;
    if (paramCollection != null)
    {
      localObject = paramCollection;
      if (!paramCollection.isEmpty()) {}
    }
    else
    {
      paramCollection = PreferenceManager.getDefaultSharedPreferences(paramCaptureActivity);
      paramCaptureActivity = EnumSet.noneOf(BarcodeFormat.class);
      if (paramCollection.getBoolean("preferences_decode_1D", false)) {
        paramCaptureActivity.addAll(DecodeFormatManager.ONE_D_FORMATS);
      }
      if (paramCollection.getBoolean("preferences_decode_QR", false)) {
        paramCaptureActivity.addAll(DecodeFormatManager.QR_CODE_FORMATS);
      }
      localObject = paramCaptureActivity;
      if (paramCollection.getBoolean("preferences_decode_Data_Matrix", false))
      {
        paramCaptureActivity.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
        localObject = paramCaptureActivity;
      }
    }
    this.hints.put(DecodeHintType.POSSIBLE_FORMATS, localObject);
    if (paramString != null) {
      this.hints.put(DecodeHintType.CHARACTER_SET, paramString);
    }
    this.hints.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK, paramResultPointCallback);
  }
  
  Handler getHandler()
  {
    try
    {
      this.handlerInitLatch.await();
      return this.handler;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;) {}
    }
  }
  
  public void run()
  {
    Looper.prepare();
    this.handler = new DecodeHandler(this.activity, this.hints);
    this.handlerInitLatch.countDown();
    Looper.loop();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\DecodeThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */