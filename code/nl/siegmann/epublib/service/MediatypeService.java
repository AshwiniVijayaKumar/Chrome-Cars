package nl.siegmann.epublib.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import nl.siegmann.epublib.domain.MediaType;
import nl.siegmann.epublib.util.StringUtil;

public class MediatypeService
{
  public static final MediaType CSS;
  public static final MediaType EPUB;
  public static final MediaType GIF;
  public static final MediaType JAVASCRIPT;
  public static final MediaType JPG;
  public static final MediaType MP3;
  public static final MediaType MP4;
  public static final MediaType NCX;
  public static final MediaType OPENTYPE;
  public static final MediaType PLS;
  public static final MediaType PNG;
  public static final MediaType SMIL;
  public static final MediaType SVG;
  public static final MediaType TTF;
  public static final MediaType WOFF;
  public static final MediaType XHTML = new MediaType("application/xhtml+xml", ".xhtml", new String[] { ".htm", ".html", ".xhtml" });
  public static final MediaType XPGT;
  public static Map<String, MediaType> mediaTypesByName;
  public static MediaType[] mediatypes;
  
  static
  {
    EPUB = new MediaType("application/epub+zip", ".epub");
    NCX = new MediaType("application/x-dtbncx+xml", ".ncx");
    JAVASCRIPT = new MediaType("text/javascript", ".js");
    CSS = new MediaType("text/css", ".css");
    JPG = new MediaType("image/jpeg", ".jpg", new String[] { ".jpg", ".jpeg" });
    PNG = new MediaType("image/png", ".png");
    GIF = new MediaType("image/gif", ".gif");
    SVG = new MediaType("image/svg+xml", ".svg");
    TTF = new MediaType("application/x-truetype-font", ".ttf");
    OPENTYPE = new MediaType("application/vnd.ms-opentype", ".otf");
    WOFF = new MediaType("application/font-woff", ".woff");
    MP3 = new MediaType("audio/mpeg", ".mp3");
    MP4 = new MediaType("audio/mp4", ".mp4");
    SMIL = new MediaType("application/smil+xml", ".smil");
    XPGT = new MediaType("application/adobe-page-template+xml", ".xpgt");
    PLS = new MediaType("application/pls+xml", ".pls");
    mediatypes = new MediaType[] { XHTML, EPUB, JPG, PNG, GIF, CSS, SVG, TTF, NCX, XPGT, OPENTYPE, WOFF, SMIL, PLS, JAVASCRIPT, MP3, MP4 };
    mediaTypesByName = new HashMap();
    int i = 0;
    while (i < mediatypes.length)
    {
      mediaTypesByName.put(mediatypes[i].getName(), mediatypes[i]);
      i += 1;
    }
  }
  
  public static MediaType determineMediaType(String paramString)
  {
    int i = 0;
    while (i < mediatypes.length)
    {
      MediaType localMediaType = mediatypes[i];
      Iterator localIterator = localMediaType.getExtensions().iterator();
      while (localIterator.hasNext()) {
        if (StringUtil.endsWithIgnoreCase(paramString, (String)localIterator.next())) {
          return localMediaType;
        }
      }
      i += 1;
    }
    return null;
  }
  
  public static MediaType getMediaTypeByName(String paramString)
  {
    return (MediaType)mediaTypesByName.get(paramString);
  }
  
  public static boolean isBitmapImage(MediaType paramMediaType)
  {
    return (paramMediaType == JPG) || (paramMediaType == PNG) || (paramMediaType == GIF);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\service\MediatypeService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */