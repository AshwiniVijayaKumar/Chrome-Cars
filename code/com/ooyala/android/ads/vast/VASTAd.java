package com.ooyala.android.ads.vast;

import com.ooyala.android.util.DebugMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

class VASTAd
{
  static final String ATTRIBUTE_API_FRAMEWORK = "apiFramework";
  static final String ATTRIBUTE_BITRATE = "bitrate";
  static final String ATTRIBUTE_DELIVERY = "delivery";
  static final String ATTRIBUTE_EVENT = "event";
  static final String ATTRIBUTE_HEIGHT = "height";
  static final String ATTRIBUTE_ID = "id";
  static final String ATTRIBUTE_MAINTAIN_ASPECT_RATIO = "maintainAspectRatio";
  static final String ATTRIBUTE_SCALABLE = "scalable";
  static final String ATTRIBUTE_SEQUENCE = "sequence";
  static final String ATTRIBUTE_TYPE = "type";
  static final String ATTRIBUTE_VERSION = "version";
  static final String ATTRIBUTE_WIDTH = "width";
  static final String ELEMENT_AD = "Ad";
  static final String ELEMENT_AD_PARAMETERS = "AdParameters";
  static final String ELEMENT_AD_SYSTEM = "AdSystem";
  static final String ELEMENT_AD_TITLE = "AdTitle";
  static final String ELEMENT_CLICK_THROUGH = "ClickThrough";
  static final String ELEMENT_CLICK_TRACKING = "ClickTracking";
  static final String ELEMENT_COMPANION_ADS = "CompanionAds";
  static final String ELEMENT_CREATIVE = "Creative";
  static final String ELEMENT_CREATIVES = "Creatives";
  static final String ELEMENT_CUSTOM_CLICK = "CustomClick";
  static final String ELEMENT_DESCRIPTION = "Description";
  static final String ELEMENT_DURATION = "Duration";
  static final String ELEMENT_ERROR = "Error";
  static final String ELEMENT_EXTENSIONS = "Extensions";
  static final String ELEMENT_IMPRESSION = "Impression";
  static final String ELEMENT_IN_LINE = "InLine";
  static final String ELEMENT_LINEAR = "Linear";
  static final String ELEMENT_MEDIA_FILE = "MediaFile";
  static final String ELEMENT_MEDIA_FILES = "MediaFiles";
  static final String ELEMENT_NON_LINEAR_ADS = "NonLinearAds";
  static final String ELEMENT_SURVEY = "Survey";
  static final String ELEMENT_TRACKING = "Tracking";
  static final String ELEMENT_TRACKING_EVENTS = "TrackingEvents";
  static final String ELEMENT_VAST = "VAST";
  static final String ELEMENT_VAST_AD_TAG_URI = "VASTAdTagURI";
  static final String ELEMENT_VIDEO_CLICKS = "VideoClicks";
  static final String ELEMENT_WRAPPER = "Wrapper";
  static final String KEY_SIGNATURE = "signature";
  static final String KEY_URL = "url";
  static final String MIME_TYPE_M3U8 = "application/x-mpegURL";
  static final String MIME_TYPE_MP4 = "video/mp4";
  static final String MIME_TYPE_WIDEVINE = "video/wvm";
  static final double MINIMUM_SUPPORTED_VAST_VERSION = 2.0D;
  private String _adID;
  protected String _description;
  protected List<String> _errorURLs = new ArrayList();
  protected Element _extensions;
  protected List<String> _impressionURLs = new ArrayList();
  private int _numOfLinear = 0;
  protected List<VASTSequenceItem> _sequence = new ArrayList();
  protected List<String> _surveyURLs = new ArrayList();
  protected String _system;
  protected String _systemVersion;
  protected String _title;
  
  VASTAd(Element paramElement)
  {
    if (!paramElement.getTagName().equals("Ad")) {
      return;
    }
    this._adID = paramElement.getAttribute("id");
    update(paramElement);
  }
  
  protected void addCreative(Element paramElement)
  {
    Node localNode = paramElement.getFirstChild();
    String str;
    Object localObject3;
    VASTSequenceItem localVASTSequenceItem;
    Element localElement;
    Object localObject1;
    Object localObject2;
    while (localNode != null) {
      if ((localNode == null) || (!(localNode instanceof Element)))
      {
        localNode = localNode.getNextSibling();
      }
      else
      {
        str = paramElement.getAttribute("sequence");
        localObject3 = null;
        localVASTSequenceItem = null;
        localElement = null;
        if (!((Element)localNode).getTagName().equals("Linear")) {
          break label109;
        }
        localObject1 = new VASTLinearAd((Element)localNode);
        localObject2 = localVASTSequenceItem;
      }
    }
    while ((localObject1 == null) && (localObject2 == null) && (localElement == null))
    {
      return;
      label109:
      if (((Element)localNode).getTagName().equals("NonLinearAds"))
      {
        localObject2 = (Element)localNode;
        localObject1 = localObject3;
      }
      else
      {
        localObject1 = localObject3;
        localObject2 = localVASTSequenceItem;
        if (((Element)localNode).getTagName().equals("CompanionAds"))
        {
          localElement = (Element)localNode;
          localObject1 = localObject3;
          localObject2 = localVASTSequenceItem;
        }
      }
    }
    if ((str != null) && (str.length() > 0))
    {
      int k = Integer.parseInt(str);
      int j = 0;
      localObject3 = this._sequence.iterator();
      int i;
      do
      {
        i = j;
        if (!((Iterator)localObject3).hasNext()) {
          break;
        }
        localVASTSequenceItem = (VASTSequenceItem)((Iterator)localObject3).next();
      } while (localVASTSequenceItem.getNumber() != k);
      if (localObject1 != null)
      {
        localVASTSequenceItem.setLinear((VASTLinearAd)localObject1);
        label264:
        i = 1;
        if (i == 0)
        {
          localObject3 = new VASTSequenceItem();
          ((VASTSequenceItem)localObject3).setNumber(k);
          if (localObject1 == null) {
            break label352;
          }
          ((VASTSequenceItem)localObject3).setLinear((VASTLinearAd)localObject1);
          label298:
          this._sequence.add(localObject3);
        }
      }
    }
    for (;;)
    {
      localNode = localNode.getNextSibling();
      break;
      if (localObject2 != null)
      {
        localVASTSequenceItem.setNonLinears((Element)localObject2);
        break label264;
      }
      if (localElement == null) {
        break label264;
      }
      localVASTSequenceItem.setCompanions(localElement);
      break label264;
      label352:
      if (localObject2 != null)
      {
        ((VASTSequenceItem)localObject3).setNonLinears((Element)localObject2);
        break label298;
      }
      if (localElement == null) {
        break label298;
      }
      ((VASTSequenceItem)localObject3).setCompanions(localElement);
      break label298;
      localObject3 = new VASTSequenceItem();
      if (localObject1 != null)
      {
        if (this._sequence.size() > this._numOfLinear)
        {
          ((VASTSequenceItem)this._sequence.get(this._numOfLinear)).setLinear((VASTLinearAd)localObject1);
          this._numOfLinear += 1;
        }
        else
        {
          this._numOfLinear += 1;
          ((VASTSequenceItem)localObject3).setNumber(this._numOfLinear);
          ((VASTSequenceItem)localObject3).setLinear((VASTLinearAd)localObject1);
          this._sequence.add(localObject3);
        }
      }
      else if (localObject2 != null)
      {
        if (this._sequence.size() == 0)
        {
          ((VASTSequenceItem)localObject3).setNumber(1);
          ((VASTSequenceItem)localObject3).setNonLinears((Element)localObject2);
          this._sequence.add(localObject3);
        }
        else
        {
          ((VASTSequenceItem)this._sequence.get(this._sequence.size() - 1)).setNonLinears((Element)localObject2);
        }
      }
      else if (localElement != null) {
        if (this._sequence.size() == 0)
        {
          ((VASTSequenceItem)localObject3).setNumber(1);
          ((VASTSequenceItem)localObject3).setCompanions(localElement);
          this._sequence.add(localObject3);
        }
        else
        {
          ((VASTSequenceItem)this._sequence.get(this._sequence.size() - 1)).setCompanions(localElement);
        }
      }
    }
  }
  
  public String getAdID()
  {
    return this._adID;
  }
  
  public String getDescription()
  {
    return this._description;
  }
  
  public List<String> getErrorURLs()
  {
    return this._errorURLs;
  }
  
  public Element getExtensions()
  {
    return this._extensions;
  }
  
  public List<String> getImpressionURLs()
  {
    return this._impressionURLs;
  }
  
  public List<VASTSequenceItem> getSequence()
  {
    return this._sequence;
  }
  
  public List<String> getSurveyURLs()
  {
    return this._surveyURLs;
  }
  
  public String getSystem()
  {
    return this._system;
  }
  
  public String getSystemVersion()
  {
    return this._systemVersion;
  }
  
  public String getTitle()
  {
    return this._title;
  }
  
  boolean update(Element paramElement)
  {
    Node localNode = paramElement.getFirstChild();
    boolean bool1 = false;
    while (localNode != null) {
      if (!(localNode instanceof Element))
      {
        localNode = localNode.getNextSibling();
      }
      else
      {
        boolean bool2 = ((Element)localNode).getTagName().equals("InLine");
        Object localObject1;
        Object localObject2;
        int i;
        if (((Element)localNode).getTagName().equals("Wrapper"))
        {
          bool2 = true;
          localObject1 = new VASTWrapperAd(paramElement);
          update((Element)((VASTWrapperAd)localObject1).getChildAdXML());
          this._impressionURLs.addAll(((VASTWrapperAd)localObject1).getImpressionURLs());
          localObject2 = this._sequence.iterator();
          for (;;)
          {
            bool1 = bool2;
            if (!((Iterator)localObject2).hasNext()) {
              break;
            }
            VASTSequenceItem localVASTSequenceItem1 = (VASTSequenceItem)((Iterator)localObject2).next();
            i = localVASTSequenceItem1.getNumber();
            Iterator localIterator = ((VASTWrapperAd)localObject1)._sequence.iterator();
            while (localIterator.hasNext())
            {
              VASTSequenceItem localVASTSequenceItem2 = (VASTSequenceItem)localIterator.next();
              VASTLinearAd localVASTLinearAd = localVASTSequenceItem2.getLinear();
              int j = localVASTSequenceItem2.getNumber();
              if ((localVASTLinearAd != null) && (j == i))
              {
                localVASTSequenceItem1.getLinear().updateTrackingEvents(localVASTLinearAd.getTrackingEvents());
                localVASTSequenceItem1.getLinear().updateClickTrackingURLs(localVASTLinearAd.getClickTrackingURLs());
              }
            }
          }
        }
        if (bool2)
        {
          bool2 = true;
          for (localObject1 = localNode.getFirstChild();; localObject1 = ((Node)localObject1).getNextSibling())
          {
            bool1 = bool2;
            if (localObject1 == null) {
              break label652;
            }
            if ((localObject1 instanceof Element)) {
              break;
            }
          }
          localObject2 = ((Node)localObject1).getTextContent().trim();
          if (localObject2 != null)
          {
            i = 1;
            label306:
            if ((i == 0) || (!((Element)localObject1).getTagName().equals("AdSystem"))) {
              break label367;
            }
            this._system = ((String)localObject2);
            this._systemVersion = ((Element)localObject1).getAttribute("version");
          }
          for (;;)
          {
            localObject1 = ((Node)localObject1).getNextSibling();
            break;
            i = 0;
            break label306;
            label367:
            if ((i != 0) && (((Element)localObject1).getTagName().equals("AdTitle")))
            {
              this._title = ((String)localObject2);
            }
            else if ((i != 0) && (((Element)localObject1).getTagName().equals("Description")))
            {
              this._description = ((String)localObject2);
            }
            else if ((i != 0) && (((Element)localObject1).getTagName().equals("Survey")))
            {
              this._surveyURLs.add(localObject2);
            }
            else if ((i != 0) && (((Element)localObject1).getTagName().equals("Error")))
            {
              this._errorURLs.add(localObject2);
            }
            else if ((i != 0) && (((Element)localObject1).getTagName().equals("Impression")))
            {
              this._impressionURLs.add(localObject2);
            }
            else if (((Element)localObject1).getTagName().equals("Extensions"))
            {
              this._extensions = ((Element)localObject1);
            }
            else if (((Element)localObject1).getTagName().equals("Creatives"))
            {
              for (localObject2 = ((Node)localObject1).getFirstChild(); localObject2 != null; localObject2 = ((Node)localObject2).getNextSibling()) {
                if ((localObject2 instanceof Element)) {
                  addCreative((Element)localObject2);
                }
              }
              Collections.sort(this._sequence);
            }
          }
        }
        DebugMode.logE(VASTAd.class.getName(), "Error ad is not a wrapper or inline ad");
        label652:
        localNode = localNode.getNextSibling();
      }
    }
    return bool1;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\ads\vast\VASTAd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */