package com.ons.barcodeSupport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.util.AttributeSet;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ZXingScannerView
  extends BarcodeScannerView
{
  public static final List<BarcodeFormat> ALL_FORMATS = new ArrayList();
  private List<BarcodeFormat> mFormats;
  private MultiFormatReader mMultiFormatReader;
  private ResultHandler mResultHandler;
  
  static
  {
    ALL_FORMATS.add(BarcodeFormat.UPC_A);
    ALL_FORMATS.add(BarcodeFormat.UPC_E);
    ALL_FORMATS.add(BarcodeFormat.EAN_13);
    ALL_FORMATS.add(BarcodeFormat.EAN_8);
    ALL_FORMATS.add(BarcodeFormat.RSS_14);
    ALL_FORMATS.add(BarcodeFormat.CODE_39);
    ALL_FORMATS.add(BarcodeFormat.CODE_93);
    ALL_FORMATS.add(BarcodeFormat.CODE_128);
    ALL_FORMATS.add(BarcodeFormat.ITF);
    ALL_FORMATS.add(BarcodeFormat.CODABAR);
    ALL_FORMATS.add(BarcodeFormat.QR_CODE);
    ALL_FORMATS.add(BarcodeFormat.DATA_MATRIX);
    ALL_FORMATS.add(BarcodeFormat.PDF_417);
  }
  
  public ZXingScannerView(Context paramContext)
  {
    super(paramContext);
    initMultiFormatReader();
  }
  
  public ZXingScannerView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initMultiFormatReader();
  }
  
  private void initMultiFormatReader()
  {
    EnumMap localEnumMap = new EnumMap(DecodeHintType.class);
    localEnumMap.put(DecodeHintType.POSSIBLE_FORMATS, getFormats());
    this.mMultiFormatReader = new MultiFormatReader();
    this.mMultiFormatReader.setHints(localEnumMap);
  }
  
  public PlanarYUVLuminanceSource buildLuminanceSource(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    Rect localRect = getFramingRectInPreview(paramInt1, paramInt2);
    if (localRect == null) {
      return null;
    }
    try
    {
      paramArrayOfByte = new PlanarYUVLuminanceSource(paramArrayOfByte, paramInt1, paramInt2, localRect.left, localRect.top, localRect.width(), localRect.height(), false);
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte) {}
    return null;
  }
  
  public Collection<BarcodeFormat> getFormats()
  {
    if (this.mFormats == null) {
      return ALL_FORMATS;
    }
    return this.mFormats;
  }
  
  @SuppressLint({"NewApi"})
  public void onPreviewFrame(byte[] paramArrayOfByte, Camera paramCamera)
  {
    Object localObject1 = paramCamera.getParameters().getPreviewSize();
    int i = ((Camera.Size)localObject1).width;
    int j = ((Camera.Size)localObject1).height;
    int m = j;
    int k = i;
    localObject1 = paramArrayOfByte;
    if (DisplayUtils.getScreenOrientation(getContext()) == 1)
    {
      localObject1 = new byte[paramArrayOfByte.length];
      k = 0;
      while (k < j)
      {
        m = 0;
        while (m < i)
        {
          localObject1[(m * j + j - k - 1)] = paramArrayOfByte[(k * i + m)];
          m += 1;
        }
        k += 1;
      }
      k = j;
      m = i;
    }
    Object localObject2 = null;
    localObject1 = buildLuminanceSource((byte[])localObject1, k, m);
    paramArrayOfByte = (byte[])localObject2;
    if (localObject1 != null) {
      paramArrayOfByte = new BinaryBitmap(new HybridBinarizer((LuminanceSource)localObject1));
    }
    try
    {
      paramArrayOfByte = this.mMultiFormatReader.decodeWithState(paramArrayOfByte);
      this.mMultiFormatReader.reset();
    }
    catch (ReaderException paramArrayOfByte)
    {
      for (;;)
      {
        paramArrayOfByte = paramArrayOfByte;
        this.mMultiFormatReader.reset();
        paramArrayOfByte = (byte[])localObject2;
      }
    }
    catch (NullPointerException paramArrayOfByte)
    {
      for (;;)
      {
        paramArrayOfByte = paramArrayOfByte;
        this.mMultiFormatReader.reset();
        paramArrayOfByte = (byte[])localObject2;
      }
    }
    catch (ArrayIndexOutOfBoundsException paramArrayOfByte)
    {
      for (;;)
      {
        paramArrayOfByte = paramArrayOfByte;
        this.mMultiFormatReader.reset();
        paramArrayOfByte = (byte[])localObject2;
      }
    }
    finally
    {
      paramArrayOfByte = finally;
      this.mMultiFormatReader.reset();
      throw paramArrayOfByte;
    }
    if (paramArrayOfByte != null)
    {
      stopCamera();
      if (this.mResultHandler != null) {
        this.mResultHandler.handleResult(paramArrayOfByte);
      }
      return;
    }
    paramCamera.setOneShotPreviewCallback(this);
  }
  
  public void setFormats(List<BarcodeFormat> paramList)
  {
    this.mFormats = paramList;
    initMultiFormatReader();
  }
  
  public void setResultHandler(ResultHandler paramResultHandler)
  {
    this.mResultHandler = paramResultHandler;
  }
  
  public static abstract interface ResultHandler
  {
    public abstract void handleResult(Result paramResult);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\barcodeSupport\ZXingScannerView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */