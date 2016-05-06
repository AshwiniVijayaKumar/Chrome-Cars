package com.google.zxing.oned.rss.expanded;

import com.google.zxing.oned.rss.DataCharacter;
import com.google.zxing.oned.rss.FinderPattern;

final class ExpandedPair
{
  private final FinderPattern finderPattern;
  private final DataCharacter leftChar;
  private final boolean mayBeLast;
  private final DataCharacter rightChar;
  
  ExpandedPair(DataCharacter paramDataCharacter1, DataCharacter paramDataCharacter2, FinderPattern paramFinderPattern, boolean paramBoolean)
  {
    this.leftChar = paramDataCharacter1;
    this.rightChar = paramDataCharacter2;
    this.finderPattern = paramFinderPattern;
    this.mayBeLast = paramBoolean;
  }
  
  FinderPattern getFinderPattern()
  {
    return this.finderPattern;
  }
  
  DataCharacter getLeftChar()
  {
    return this.leftChar;
  }
  
  DataCharacter getRightChar()
  {
    return this.rightChar;
  }
  
  boolean mayBeLast()
  {
    return this.mayBeLast;
  }
  
  public boolean mustBeLast()
  {
    return this.rightChar == null;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\oned\rss\expanded\ExpandedPair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */