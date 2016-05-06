package nl.siegmann.epublib.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TOCReference
  extends TitledResourceReference
  implements Serializable
{
  private static final Comparator<TOCReference> COMPARATOR_BY_TITLE_IGNORE_CASE = new Comparator()
  {
    public int compare(TOCReference paramAnonymousTOCReference1, TOCReference paramAnonymousTOCReference2)
    {
      return String.CASE_INSENSITIVE_ORDER.compare(paramAnonymousTOCReference1.getTitle(), paramAnonymousTOCReference2.getTitle());
    }
  };
  private static final long serialVersionUID = 5787958246077042456L;
  private List<TOCReference> children;
  
  public TOCReference()
  {
    this(null, null, null);
  }
  
  public TOCReference(String paramString, Resource paramResource)
  {
    this(paramString, paramResource, null);
  }
  
  public TOCReference(String paramString1, Resource paramResource, String paramString2)
  {
    this(paramString1, paramResource, paramString2, new ArrayList());
  }
  
  public TOCReference(String paramString1, Resource paramResource, String paramString2, List<TOCReference> paramList)
  {
    super(paramResource, paramString1, paramString2);
    this.children = paramList;
  }
  
  public static Comparator<TOCReference> getComparatorByTitleIgnoreCase()
  {
    return COMPARATOR_BY_TITLE_IGNORE_CASE;
  }
  
  public TOCReference addChildSection(TOCReference paramTOCReference)
  {
    this.children.add(paramTOCReference);
    return paramTOCReference;
  }
  
  public List<TOCReference> getChildren()
  {
    return this.children;
  }
  
  public void setChildren(List<TOCReference> paramList)
  {
    this.children = paramList;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\domain\TOCReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */