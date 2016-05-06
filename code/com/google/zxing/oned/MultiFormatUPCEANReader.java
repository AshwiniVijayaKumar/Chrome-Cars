package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.BitArray;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class MultiFormatUPCEANReader
  extends OneDReader
{
  private final UPCEANReader[] readers;
  
  public MultiFormatUPCEANReader(Map<DecodeHintType, ?> paramMap)
  {
    ArrayList localArrayList;
    if (paramMap == null)
    {
      paramMap = null;
      localArrayList = new ArrayList();
      if (paramMap != null)
      {
        if (!paramMap.contains(BarcodeFormat.EAN_13)) {
          break label190;
        }
        localArrayList.add(new EAN13Reader());
      }
    }
    for (;;)
    {
      if (paramMap.contains(BarcodeFormat.EAN_8)) {
        localArrayList.add(new EAN8Reader());
      }
      if (paramMap.contains(BarcodeFormat.UPC_E)) {
        localArrayList.add(new UPCEReader());
      }
      if (localArrayList.isEmpty())
      {
        localArrayList.add(new EAN13Reader());
        localArrayList.add(new EAN8Reader());
        localArrayList.add(new UPCEReader());
      }
      this.readers = ((UPCEANReader[])localArrayList.toArray(new UPCEANReader[localArrayList.size()]));
      return;
      paramMap = (Collection)paramMap.get(DecodeHintType.POSSIBLE_FORMATS);
      break;
      label190:
      if (paramMap.contains(BarcodeFormat.UPC_A)) {
        localArrayList.add(new UPCAReader());
      }
    }
  }
  
  public Result decodeRow(int paramInt, BitArray paramBitArray, Map<DecodeHintType, ?> paramMap)
    throws NotFoundException
  {
    int j = 0;
    int[] arrayOfInt = UPCEANReader.findStartGuardPattern(paramBitArray);
    UPCEANReader[] arrayOfUPCEANReader = this.readers;
    int k = arrayOfUPCEANReader.length;
    int i = 0;
    if (i >= k) {
      throw NotFoundException.getNotFoundInstance();
    }
    Object localObject = arrayOfUPCEANReader[i];
    for (;;)
    {
      try
      {
        localObject = ((UPCEANReader)localObject).decodeRow(paramInt, paramBitArray, arrayOfInt, paramMap);
        if ((((Result)localObject).getBarcodeFormat() != BarcodeFormat.EAN_13) || (((Result)localObject).getText().charAt(0) != '0')) {
          break label167;
        }
        paramInt = 1;
        if (paramMap != null) {
          break label172;
        }
        paramBitArray = null;
        if ((paramBitArray == null) || (paramBitArray.contains(BarcodeFormat.UPC_A))) {
          break label188;
        }
        i = j;
        if ((paramInt == 0) || (i == 0)) {
          break label194;
        }
        paramBitArray = new Result(((Result)localObject).getText().substring(1), ((Result)localObject).getRawBytes(), ((Result)localObject).getResultPoints(), BarcodeFormat.UPC_A);
        paramBitArray.putAllMetadata(((Result)localObject).getResultMetadata());
        return paramBitArray;
      }
      catch (ReaderException localReaderException)
      {
        i += 1;
      }
      break;
      label167:
      paramInt = 0;
      continue;
      label172:
      paramBitArray = (Collection)paramMap.get(DecodeHintType.POSSIBLE_FORMATS);
      continue;
      label188:
      i = 1;
    }
    label194:
    return localReaderException;
  }
  
  public void reset()
  {
    UPCEANReader[] arrayOfUPCEANReader = this.readers;
    int j = arrayOfUPCEANReader.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return;
      }
      arrayOfUPCEANReader[i].reset();
      i += 1;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\MultiFormatUPCEANReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */