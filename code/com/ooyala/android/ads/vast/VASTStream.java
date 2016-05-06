package com.ooyala.android.ads.vast;

import com.ooyala.android.item.Stream;
import org.w3c.dom.Element;

class VASTStream
  extends Stream
{
  private String _apiFramework;
  private boolean _maintainAspectRatio;
  private boolean _scalable;
  private String _vastDeliveryType;
  
  VASTStream(Element paramElement)
  {
    if (!paramElement.getTagName().equals("MediaFile")) {
      return;
    }
    this._vastDeliveryType = paramElement.getAttribute("delivery");
    this._apiFramework = paramElement.getAttribute("apiFramework");
    String str = paramElement.getAttribute("scalable");
    if (!VASTUtils.isNullOrEmpty(str)) {
      this._scalable = Boolean.getBoolean(str);
    }
    str = paramElement.getAttribute("maintainAspectRatio");
    if (str != null) {
      this._maintainAspectRatio = Boolean.getBoolean(str);
    }
    str = paramElement.getAttribute("type");
    if (str != null)
    {
      if (str.equals("application/x-mpegURL")) {
        this._deliveryType = "hls";
      }
      if (!str.equals("video/mp4")) {
        break label220;
      }
    }
    label220:
    for (this._deliveryType = "mp4";; this._deliveryType = str)
    {
      str = paramElement.getAttribute("bitrate");
      if (!VASTUtils.isNullOrEmpty(str)) {
        this._videoBitrate = Integer.parseInt(str);
      }
      str = paramElement.getAttribute("width");
      if (!VASTUtils.isNullOrEmpty(str)) {
        this._width = Integer.parseInt(str);
      }
      str = paramElement.getAttribute("height");
      if (!VASTUtils.isNullOrEmpty(str)) {
        this._height = Integer.parseInt(str);
      }
      this._urlFormat = "text";
      this._url = paramElement.getTextContent();
      return;
    }
  }
  
  public String getApiFramework()
  {
    return this._apiFramework;
  }
  
  public String getVastDeliveryType()
  {
    return this._vastDeliveryType;
  }
  
  public boolean isMaintainAspectRatio()
  {
    return this._maintainAspectRatio;
  }
  
  public boolean isScalable()
  {
    return this._scalable;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\ads\vast\VASTStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */