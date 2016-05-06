package nl.siegmann.epublib.domain;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import nl.siegmann.epublib.util.StringUtil;

public class Identifier
  implements Serializable
{
  private static final long serialVersionUID = 955949951416391810L;
  private boolean bookId = false;
  private String scheme;
  private String value;
  
  public Identifier()
  {
    this("UUID", UUID.randomUUID().toString());
  }
  
  public Identifier(String paramString1, String paramString2)
  {
    this.scheme = paramString1;
    this.value = paramString2;
  }
  
  public static Identifier getBookIdIdentifier(List<Identifier> paramList)
  {
    Object localObject2;
    if ((paramList == null) || (paramList.isEmpty())) {
      localObject2 = null;
    }
    Object localObject1;
    do
    {
      return (Identifier)localObject2;
      localObject2 = null;
      Iterator localIterator = paramList.iterator();
      do
      {
        localObject1 = localObject2;
        if (!localIterator.hasNext()) {
          break;
        }
        localObject1 = (Identifier)localIterator.next();
      } while (!((Identifier)localObject1).isBookId());
      localObject2 = localObject1;
    } while (localObject1 != null);
    return (Identifier)paramList.get(0);
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Identifier)) {
      return false;
    }
    if ((StringUtil.equals(this.scheme, ((Identifier)paramObject).scheme)) && (StringUtil.equals(this.value, ((Identifier)paramObject).value))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public String getScheme()
  {
    return this.scheme;
  }
  
  public String getValue()
  {
    return this.value;
  }
  
  public int hashCode()
  {
    return StringUtil.defaultIfNull(this.scheme).hashCode() ^ StringUtil.defaultIfNull(this.value).hashCode();
  }
  
  public boolean isBookId()
  {
    return this.bookId;
  }
  
  public void setBookId(boolean paramBoolean)
  {
    this.bookId = paramBoolean;
  }
  
  public void setScheme(String paramString)
  {
    this.scheme = paramString;
  }
  
  public void setValue(String paramString)
  {
    this.value = paramString;
  }
  
  public String toString()
  {
    if (StringUtil.isBlank(this.scheme)) {
      return "" + this.value;
    }
    return "" + this.scheme + ":" + this.value;
  }
  
  public static abstract interface Scheme
  {
    public static final String ISBN = "ISBN";
    public static final String URI = "URI";
    public static final String URL = "URL";
    public static final String UUID = "UUID";
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\domain\Identifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */