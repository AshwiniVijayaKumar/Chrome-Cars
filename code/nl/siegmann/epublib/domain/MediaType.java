package nl.siegmann.epublib.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

public class MediaType
  implements Serializable
{
  private static final long serialVersionUID = -7256091153727506788L;
  private String defaultExtension;
  private Collection<String> extensions;
  private String name;
  
  public MediaType(String paramString1, String paramString2)
  {
    this(paramString1, paramString2, new String[] { paramString2 });
  }
  
  public MediaType(String paramString1, String paramString2, Collection<String> paramCollection)
  {
    this.name = paramString1;
    this.defaultExtension = paramString2;
    this.extensions = paramCollection;
  }
  
  public MediaType(String paramString1, String paramString2, String[] paramArrayOfString)
  {
    this(paramString1, paramString2, Arrays.asList(paramArrayOfString));
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof MediaType)) {
      return false;
    }
    return this.name.equals(((MediaType)paramObject).getName());
  }
  
  public String getDefaultExtension()
  {
    return this.defaultExtension;
  }
  
  public Collection<String> getExtensions()
  {
    return this.extensions;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public int hashCode()
  {
    if (this.name == null) {
      return 0;
    }
    return this.name.hashCode();
  }
  
  public String toString()
  {
    return this.name;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\domain\MediaType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */