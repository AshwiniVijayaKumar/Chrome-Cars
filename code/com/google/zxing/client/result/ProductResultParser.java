package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.oned.UPCEReader;

public final class ProductResultParser
  extends ResultParser
{
  public ProductParsedResult parse(Result paramResult)
  {
    BarcodeFormat localBarcodeFormat = paramResult.getBarcodeFormat();
    if ((localBarcodeFormat != BarcodeFormat.UPC_A) && (localBarcodeFormat != BarcodeFormat.UPC_E) && (localBarcodeFormat != BarcodeFormat.EAN_8) && (localBarcodeFormat != BarcodeFormat.EAN_13)) {
      return null;
    }
    String str = getMassagedText(paramResult);
    int j = str.length();
    int i = 0;
    label54:
    if (i >= j) {
      if (localBarcodeFormat != BarcodeFormat.UPC_E) {
        break label113;
      }
    }
    label113:
    for (paramResult = UPCEReader.convertUPCEtoUPCA(str);; paramResult = str)
    {
      return new ProductParsedResult(str, paramResult);
      int k = str.charAt(i);
      if ((k < 48) || (k > 57)) {
        break;
      }
      i += 1;
      break label54;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\result\ProductResultParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */