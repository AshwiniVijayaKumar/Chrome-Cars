package nl.siegmann.epublib.domain;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import nl.siegmann.epublib.service.MediatypeService;
import nl.siegmann.epublib.util.IOUtil;
import nl.siegmann.epublib.util.StringUtil;
import nl.siegmann.epublib.util.commons.io.XmlStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Resource
  implements Serializable
{
  private static final Logger LOG = LoggerFactory.getLogger(Resource.class);
  private static final long serialVersionUID = 1043946707835004037L;
  private long cachedSize;
  private byte[] data;
  private String fileName;
  private String href;
  private String id;
  private String inputEncoding = "UTF-8";
  private MediaType mediaType;
  private String title;
  
  public Resource(InputStream paramInputStream, String paramString)
    throws IOException
  {
    this(null, IOUtil.toByteArray(paramInputStream), paramString, MediatypeService.determineMediaType(paramString));
  }
  
  public Resource(Reader paramReader, String paramString)
    throws IOException
  {
    this(null, IOUtil.toByteArray(paramReader, "UTF-8"), paramString, MediatypeService.determineMediaType(paramString), "UTF-8");
  }
  
  public Resource(String paramString)
  {
    this(null, new byte[0], paramString, localMediaType);
  }
  
  public Resource(String paramString1, long paramLong, String paramString2)
  {
    this(null, null, paramString2, MediatypeService.determineMediaType(paramString2));
    this.fileName = paramString1;
    this.cachedSize = paramLong;
  }
  
  public Resource(String paramString1, byte[] paramArrayOfByte, String paramString2, MediaType paramMediaType)
  {
    this(paramString1, paramArrayOfByte, paramString2, paramMediaType, "UTF-8");
  }
  
  public Resource(String paramString1, byte[] paramArrayOfByte, String paramString2, MediaType paramMediaType, String paramString3)
  {
    this.id = paramString1;
    this.href = paramString2;
    this.mediaType = paramMediaType;
    this.inputEncoding = paramString3;
    this.data = paramArrayOfByte;
  }
  
  public Resource(byte[] paramArrayOfByte, String paramString)
  {
    this(null, paramArrayOfByte, paramString, MediatypeService.determineMediaType(paramString), "UTF-8");
  }
  
  public Resource(byte[] paramArrayOfByte, MediaType paramMediaType)
  {
    this(null, paramArrayOfByte, null, paramMediaType);
  }
  
  public void close()
  {
    if (this.fileName != null) {
      this.data = null;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Resource)) {
      return false;
    }
    return this.href.equals(((Resource)paramObject).getHref());
  }
  
  public byte[] getData()
    throws IOException
  {
    if (this.data == null)
    {
      LOG.info("Initializing lazy resource " + this.fileName + "#" + this.href);
      ZipInputStream localZipInputStream = new ZipInputStream(new FileInputStream(this.fileName));
      ZipEntry localZipEntry = localZipInputStream.getNextEntry();
      if (localZipEntry != null)
      {
        if (localZipEntry.isDirectory()) {}
        for (;;)
        {
          localZipEntry = localZipInputStream.getNextEntry();
          break;
          if (localZipEntry.getName().endsWith(this.href)) {
            this.data = IOUtil.toByteArray(localZipInputStream);
          }
        }
      }
      localZipInputStream.close();
    }
    return this.data;
  }
  
  public String getHref()
  {
    return this.href;
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public String getInputEncoding()
  {
    return this.inputEncoding;
  }
  
  public InputStream getInputStream()
    throws IOException
  {
    return new ByteArrayInputStream(getData());
  }
  
  public MediaType getMediaType()
  {
    return this.mediaType;
  }
  
  public Reader getReader()
    throws IOException
  {
    return new XmlStreamReader(new ByteArrayInputStream(getData()), getInputEncoding());
  }
  
  public long getSize()
  {
    if (this.data != null) {
      return this.data.length;
    }
    return this.cachedSize;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public int hashCode()
  {
    return this.href.hashCode();
  }
  
  public boolean isInitialized()
  {
    return this.data != null;
  }
  
  public void setData(byte[] paramArrayOfByte)
  {
    this.data = paramArrayOfByte;
  }
  
  public void setHref(String paramString)
  {
    this.href = paramString;
  }
  
  public void setId(String paramString)
  {
    this.id = paramString;
  }
  
  public void setInputEncoding(String paramString)
  {
    this.inputEncoding = paramString;
  }
  
  public void setMediaType(MediaType paramMediaType)
  {
    this.mediaType = paramMediaType;
  }
  
  public void setTitle(String paramString)
  {
    this.title = paramString;
  }
  
  public String toString()
  {
    int i = 0;
    String str1 = this.id;
    String str2 = this.title;
    String str3 = this.inputEncoding;
    MediaType localMediaType = this.mediaType;
    String str4 = this.href;
    if (this.data == null) {}
    for (;;)
    {
      return StringUtil.toString(new Object[] { "id", str1, "title", str2, "encoding", str3, "mediaType", localMediaType, "href", str4, "size", Integer.valueOf(i) });
      i = this.data.length;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\domain\Resource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */