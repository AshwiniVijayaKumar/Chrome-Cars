package com.google.zxing;

import com.google.zxing.aztec.AztecReader;
import com.google.zxing.datamatrix.DataMatrixReader;
import com.google.zxing.maxicode.MaxiCodeReader;
import com.google.zxing.oned.MultiFormatOneDReader;
import com.google.zxing.pdf417.PDF417Reader;
import com.google.zxing.qrcode.QRCodeReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class MultiFormatReader
  implements Reader
{
  private Map<DecodeHintType, ?> hints;
  private Reader[] readers;
  
  private Result decodeInternal(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException
  {
    Reader[] arrayOfReader;
    int j;
    int i;
    if (this.readers != null)
    {
      arrayOfReader = this.readers;
      j = arrayOfReader.length;
      i = 0;
    }
    for (;;)
    {
      if (i >= j) {
        throw NotFoundException.getNotFoundInstance();
      }
      Object localObject = arrayOfReader[i];
      try
      {
        localObject = ((Reader)localObject).decode(paramBinaryBitmap, this.hints);
        return (Result)localObject;
      }
      catch (ReaderException localReaderException)
      {
        i += 1;
      }
    }
  }
  
  public Result decode(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException
  {
    setHints(null);
    return decodeInternal(paramBinaryBitmap);
  }
  
  public Result decode(BinaryBitmap paramBinaryBitmap, Map<DecodeHintType, ?> paramMap)
    throws NotFoundException
  {
    setHints(paramMap);
    return decodeInternal(paramBinaryBitmap);
  }
  
  public Result decodeWithState(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException
  {
    if (this.readers == null) {
      setHints(null);
    }
    return decodeInternal(paramBinaryBitmap);
  }
  
  public void reset()
  {
    Reader[] arrayOfReader;
    int j;
    int i;
    if (this.readers != null)
    {
      arrayOfReader = this.readers;
      j = arrayOfReader.length;
      i = 0;
    }
    for (;;)
    {
      if (i >= j) {
        return;
      }
      arrayOfReader[i].reset();
      i += 1;
    }
  }
  
  public void setHints(Map<DecodeHintType, ?> paramMap)
  {
    int j = 0;
    this.hints = paramMap;
    int i;
    Collection localCollection;
    label32:
    ArrayList localArrayList;
    if ((paramMap != null) && (paramMap.containsKey(DecodeHintType.TRY_HARDER)))
    {
      i = 1;
      if (paramMap != null) {
        break label532;
      }
      localCollection = null;
      localArrayList = new ArrayList();
      if (localCollection != null) {
        if ((localCollection.contains(BarcodeFormat.UPC_A)) || (localCollection.contains(BarcodeFormat.UPC_E)) || (localCollection.contains(BarcodeFormat.EAN_13)) || (localCollection.contains(BarcodeFormat.EAN_8)) || (localCollection.contains(BarcodeFormat.CODABAR)) || (localCollection.contains(BarcodeFormat.CODE_39)) || (localCollection.contains(BarcodeFormat.CODE_93)) || (localCollection.contains(BarcodeFormat.CODE_128)) || (localCollection.contains(BarcodeFormat.ITF)) || (localCollection.contains(BarcodeFormat.RSS_14)) || (localCollection.contains(BarcodeFormat.RSS_EXPANDED))) {
          break label549;
        }
      }
    }
    for (;;)
    {
      if ((j != 0) && (i == 0)) {
        localArrayList.add(new MultiFormatOneDReader(paramMap));
      }
      if (localCollection.contains(BarcodeFormat.QR_CODE)) {
        localArrayList.add(new QRCodeReader());
      }
      if (localCollection.contains(BarcodeFormat.DATA_MATRIX)) {
        localArrayList.add(new DataMatrixReader());
      }
      if (localCollection.contains(BarcodeFormat.AZTEC)) {
        localArrayList.add(new AztecReader());
      }
      if (localCollection.contains(BarcodeFormat.PDF_417)) {
        localArrayList.add(new PDF417Reader());
      }
      if (localCollection.contains(BarcodeFormat.MAXICODE)) {
        localArrayList.add(new MaxiCodeReader());
      }
      if ((j != 0) && (i != 0)) {
        localArrayList.add(new MultiFormatOneDReader(paramMap));
      }
      if (localArrayList.isEmpty())
      {
        if (i == 0) {
          localArrayList.add(new MultiFormatOneDReader(paramMap));
        }
        localArrayList.add(new QRCodeReader());
        localArrayList.add(new DataMatrixReader());
        localArrayList.add(new AztecReader());
        localArrayList.add(new PDF417Reader());
        localArrayList.add(new MaxiCodeReader());
        if (i != 0) {
          localArrayList.add(new MultiFormatOneDReader(paramMap));
        }
      }
      this.readers = ((Reader[])localArrayList.toArray(new Reader[localArrayList.size()]));
      return;
      i = 0;
      break;
      label532:
      localCollection = (Collection)paramMap.get(DecodeHintType.POSSIBLE_FORMATS);
      break label32;
      label549:
      j = 1;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\MultiFormatReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */