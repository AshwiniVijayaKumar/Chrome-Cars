package nl.siegmann.epublib.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TableOfContents
  implements Serializable
{
  public static final String DEFAULT_PATH_SEPARATOR = "/";
  private static final long serialVersionUID = -3147391239966275152L;
  private List<TOCReference> tocReferences;
  
  public TableOfContents()
  {
    this(new ArrayList());
  }
  
  public TableOfContents(List<TOCReference> paramList)
  {
    this.tocReferences = paramList;
  }
  
  private int calculateDepth(List<TOCReference> paramList, int paramInt)
  {
    int i = 0;
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      int j = calculateDepth(((TOCReference)paramList.next()).getChildren(), 1);
      if (j > i) {
        i = j;
      }
    }
    return paramInt + i;
  }
  
  private String createSectionTitle(int[] paramArrayOfInt, int paramInt1, int paramInt2, String paramString1, String paramString2)
  {
    paramString1 = new StringBuilder(paramString1);
    int i = 0;
    while (i < paramInt1)
    {
      if (i > 0) {
        paramString1.append(paramString2);
      }
      paramString1.append(paramArrayOfInt[i] + 1);
      i += 1;
    }
    if (paramInt1 > 0) {
      paramString1.append(paramString2);
    }
    paramString1.append(paramInt2 + 1);
    return paramString1.toString();
  }
  
  private static TOCReference findTocReferenceByTitle(String paramString, List<TOCReference> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      TOCReference localTOCReference = (TOCReference)paramList.next();
      if (paramString.equals(localTOCReference.getTitle())) {
        return localTOCReference;
      }
    }
    return null;
  }
  
  private static void getAllUniqueResources(Set<String> paramSet, List<Resource> paramList, List<TOCReference> paramList1)
  {
    paramList1 = paramList1.iterator();
    while (paramList1.hasNext())
    {
      TOCReference localTOCReference = (TOCReference)paramList1.next();
      Resource localResource = localTOCReference.getResource();
      if ((localResource != null) && (!paramSet.contains(localResource.getHref())))
      {
        paramSet.add(localResource.getHref());
        paramList.add(localResource);
      }
      getAllUniqueResources(paramSet, paramList, localTOCReference.getChildren());
    }
  }
  
  private static int getTotalSize(Collection<TOCReference> paramCollection)
  {
    int i = paramCollection.size();
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      i += getTotalSize(((TOCReference)paramCollection.next()).getChildren());
    }
    return i;
  }
  
  private void paddTOCReferences(List<TOCReference> paramList, int[] paramArrayOfInt, int paramInt, String paramString1, String paramString2)
  {
    int i = paramList.size();
    while (i <= paramArrayOfInt[paramInt])
    {
      paramList.add(new TOCReference(createSectionTitle(paramArrayOfInt, paramInt, i, paramString1, paramString2), null));
      i += 1;
    }
  }
  
  public TOCReference addSection(Resource paramResource, String paramString)
  {
    return addSection(paramResource, paramString, "/");
  }
  
  public TOCReference addSection(Resource paramResource, String paramString1, String paramString2)
  {
    return addSection(paramResource, paramString1.split(paramString2));
  }
  
  public TOCReference addSection(Resource paramResource, int[] paramArrayOfInt, String paramString1, String paramString2)
  {
    if ((paramArrayOfInt == null) || (paramArrayOfInt.length == 0)) {
      return null;
    }
    Object localObject = null;
    List localList = this.tocReferences;
    int i = 0;
    if (i < paramArrayOfInt.length)
    {
      int j = paramArrayOfInt[i];
      if ((j > 0) && (j < localList.size() - 1)) {}
      for (TOCReference localTOCReference = (TOCReference)localList.get(j);; localTOCReference = null)
      {
        localObject = localTOCReference;
        if (localTOCReference == null)
        {
          paddTOCReferences(localList, paramArrayOfInt, i, paramString1, paramString2);
          localObject = (TOCReference)localList.get(j);
        }
        localList = ((TOCReference)localObject).getChildren();
        i += 1;
        break;
      }
    }
    ((TOCReference)localObject).setResource(paramResource);
    return (TOCReference)localObject;
  }
  
  public TOCReference addSection(Resource paramResource, String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0)) {
      return null;
    }
    Object localObject = null;
    List localList = this.tocReferences;
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      String str = paramArrayOfString[i];
      TOCReference localTOCReference = findTocReferenceByTitle(str, localList);
      localObject = localTOCReference;
      if (localTOCReference == null)
      {
        localObject = new TOCReference(str, null);
        localList.add(localObject);
      }
      localList = ((TOCReference)localObject).getChildren();
      i += 1;
    }
    ((TOCReference)localObject).setResource(paramResource);
    return (TOCReference)localObject;
  }
  
  public TOCReference addTOCReference(TOCReference paramTOCReference)
  {
    if (this.tocReferences == null) {
      this.tocReferences = new ArrayList();
    }
    this.tocReferences.add(paramTOCReference);
    return paramTOCReference;
  }
  
  public int calculateDepth()
  {
    return calculateDepth(this.tocReferences, 0);
  }
  
  public List<Resource> getAllUniqueResources()
  {
    HashSet localHashSet = new HashSet();
    ArrayList localArrayList = new ArrayList();
    getAllUniqueResources(localHashSet, localArrayList, this.tocReferences);
    return localArrayList;
  }
  
  public List<TOCReference> getTocReferences()
  {
    return this.tocReferences;
  }
  
  public void setTocReferences(List<TOCReference> paramList)
  {
    this.tocReferences = paramList;
  }
  
  public int size()
  {
    return getTotalSize(this.tocReferences);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\domain\TableOfContents.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */