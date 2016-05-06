package com.google.zxing.oned.rss.expanded;

import com.google.zxing.common.BitArray;
import com.google.zxing.oned.rss.DataCharacter;
import java.util.List;

final class BitArrayBuilder
{
  static BitArray buildBitArray(List<ExpandedPair> paramList)
  {
    int j = (paramList.size() << 1) - 1;
    int i = j;
    if (((ExpandedPair)paramList.get(paramList.size() - 1)).getRightChar() == null) {
      i = j - 1;
    }
    BitArray localBitArray = new BitArray(i * 12);
    i = 0;
    int k = ((ExpandedPair)paramList.get(0)).getRightChar().getValue();
    j = 11;
    for (;;)
    {
      if (j < 0)
      {
        k = 1;
        if (k < paramList.size()) {
          break;
        }
        return localBitArray;
      }
      if ((1 << j & k) != 0) {
        localBitArray.set(i);
      }
      i += 1;
      j -= 1;
    }
    ExpandedPair localExpandedPair = (ExpandedPair)paramList.get(k);
    int m = localExpandedPair.getLeftChar().getValue();
    j = 11;
    label144:
    if (j < 0)
    {
      j = i;
      if (localExpandedPair.getRightChar() != null)
      {
        m = localExpandedPair.getRightChar().getValue();
        j = 11;
      }
    }
    for (;;)
    {
      if (j < 0)
      {
        j = i;
        k += 1;
        i = j;
        break;
        if ((1 << j & m) != 0) {
          localBitArray.set(i);
        }
        i += 1;
        j -= 1;
        break label144;
      }
      if ((1 << j & m) != 0) {
        localBitArray.set(i);
      }
      i += 1;
      j -= 1;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\rss\expanded\BitArrayBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */