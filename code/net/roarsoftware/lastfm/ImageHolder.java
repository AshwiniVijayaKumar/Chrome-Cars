package net.roarsoftware.lastfm;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import net.roarsoftware.xml.DomElement;

public abstract class ImageHolder
{
  protected Map<ImageSize, String> imageUrls = new HashMap();
  
  protected static void loadImages(ImageHolder paramImageHolder, DomElement paramDomElement)
  {
    Object localObject2 = paramDomElement.getChildren("image");
    Object localObject1 = localObject2;
    if (((Collection)localObject2).size() == 0) {
      localObject1 = paramDomElement.getChild("album").getChildren("image");
    }
    localObject2 = ((Collection)localObject1).iterator();
    label117:
    for (;;)
    {
      DomElement localDomElement;
      if (((Iterator)localObject2).hasNext())
      {
        localDomElement = (DomElement)((Iterator)localObject2).next();
        localObject1 = localDomElement.getAttribute("size");
        paramDomElement = null;
        if (localObject1 == null) {
          paramDomElement = ImageSize.MEDIUM;
        }
      }
      for (;;)
      {
        if (paramDomElement == null) {
          break label117;
        }
        paramImageHolder.imageUrls.put(paramDomElement, localDomElement.getText());
        break;
        try
        {
          localObject1 = ImageSize.valueOf(((String)localObject1).toUpperCase(Locale.ENGLISH));
          paramDomElement = (DomElement)localObject1;
        }
        catch (IllegalArgumentException localIllegalArgumentException) {}
        return;
      }
    }
  }
  
  public Set<ImageSize> availableSizes()
  {
    return this.imageUrls.keySet();
  }
  
  public String getImageURL(ImageSize paramImageSize)
  {
    return (String)this.imageUrls.get(paramImageSize);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\ImageHolder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */