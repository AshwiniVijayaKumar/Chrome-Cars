package net.roarsoftware.lastfm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import net.roarsoftware.xml.DomElement;

public class Image
  extends ImageHolder
{
  private static final DateFormat DATE_ADDED_FORMAT = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale.ENGLISH);
  private Date dateAdded;
  private String format;
  private String owner;
  private int thumbsDown;
  private int thumbsUp;
  private String title;
  private String url;
  
  static Image imageFromElement(DomElement paramDomElement)
  {
    Image localImage = new Image();
    localImage.title = paramDomElement.getChildText("title");
    localImage.url = paramDomElement.getChildText("url");
    localImage.format = paramDomElement.getChildText("format");
    try
    {
      localImage.dateAdded = DATE_ADDED_FORMAT.parse(paramDomElement.getChildText("dateadded"));
      Object localObject = paramDomElement.getChild("owner");
      if (localObject != null) {
        localImage.owner = ((DomElement)localObject).getChildText("name");
      }
      localObject = paramDomElement.getChild("votes");
      if (localObject != null)
      {
        localImage.thumbsUp = Integer.parseInt(((DomElement)localObject).getChildText("thumbsup"));
        localImage.thumbsDown = Integer.parseInt(((DomElement)localObject).getChildText("thumbsdown"));
      }
      localObject = paramDomElement.getChild("sizes").getChildren("size").iterator();
      for (;;)
      {
        if (!((Iterator)localObject).hasNext()) {
          return localImage;
        }
        DomElement localDomElement = (DomElement)((Iterator)localObject).next();
        paramDomElement = localDomElement.getAttribute("name");
        if (paramDomElement != null) {
          break;
        }
        paramDomElement = ImageSize.MEDIUM;
        localImage.imageUrls.put(paramDomElement, localDomElement.getText());
      }
    }
    catch (ParseException localParseException)
    {
      for (;;)
      {
        localParseException.printStackTrace();
        continue;
        paramDomElement = ImageSize.valueOf(paramDomElement.toUpperCase(Locale.ENGLISH));
      }
    }
    return localImage;
  }
  
  public Date getDateAdded()
  {
    return this.dateAdded;
  }
  
  public String getFormat()
  {
    return this.format;
  }
  
  public String getOwner()
  {
    return this.owner;
  }
  
  public int getThumbsDown()
  {
    return this.thumbsDown;
  }
  
  public int getThumbsUp()
  {
    return this.thumbsUp;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public String getUrl()
  {
    return this.url;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\Image.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */