package com.ooyala.android.ads.vast;

import org.w3c.dom.Element;

class VASTSequenceItem
  implements Comparable<VASTSequenceItem>
{
  private Element _companions = null;
  private VASTLinearAd _linear = null;
  private Element _nonLinears = null;
  private int _number = -1;
  
  public int compareTo(VASTSequenceItem paramVASTSequenceItem)
  {
    if (this._number < paramVASTSequenceItem.getNumber()) {
      return -1;
    }
    if (this._number > paramVASTSequenceItem.getNumber()) {
      return 1;
    }
    return 0;
  }
  
  public Element getCompanions()
  {
    return this._companions;
  }
  
  public VASTLinearAd getLinear()
  {
    return this._linear;
  }
  
  public Element getNonLinears()
  {
    return this._nonLinears;
  }
  
  public int getNumber()
  {
    return this._number;
  }
  
  public boolean hasLinear()
  {
    return this._linear != null;
  }
  
  public void setCompanions(Element paramElement)
  {
    this._companions = paramElement;
  }
  
  public void setLinear(VASTLinearAd paramVASTLinearAd)
  {
    this._linear = paramVASTLinearAd;
  }
  
  public void setNonLinears(Element paramElement)
  {
    this._nonLinears = paramElement;
  }
  
  public void setNumber(int paramInt)
  {
    this._number = paramInt;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\ads\vast\VASTSequenceItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */