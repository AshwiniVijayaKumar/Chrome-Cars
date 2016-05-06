package nl.siegmann.epublib.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import nl.siegmann.epublib.util.StringUtil;

public class Spine
  implements Serializable
{
  private static final long serialVersionUID = 3878483958947357246L;
  private List<SpineReference> spineReferences;
  private Resource tocResource;
  
  public Spine()
  {
    this(new ArrayList());
  }
  
  public Spine(List<SpineReference> paramList)
  {
    this.spineReferences = paramList;
  }
  
  public Spine(TableOfContents paramTableOfContents)
  {
    this.spineReferences = createSpineReferences(paramTableOfContents.getAllUniqueResources());
  }
  
  public static List<SpineReference> createSpineReferences(Collection<Resource> paramCollection)
  {
    ArrayList localArrayList = new ArrayList(paramCollection.size());
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      localArrayList.add(new SpineReference((Resource)paramCollection.next()));
    }
    return localArrayList;
  }
  
  public SpineReference addResource(Resource paramResource)
  {
    return addSpineReference(new SpineReference(paramResource));
  }
  
  public SpineReference addSpineReference(SpineReference paramSpineReference)
  {
    if (this.spineReferences == null) {
      this.spineReferences = new ArrayList();
    }
    this.spineReferences.add(paramSpineReference);
    return paramSpineReference;
  }
  
  public int findFirstResourceById(String paramString)
  {
    int j;
    if (StringUtil.isBlank(paramString))
    {
      j = -1;
      return j;
    }
    int i = 0;
    for (;;)
    {
      if (i >= this.spineReferences.size()) {
        break label58;
      }
      j = i;
      if (paramString.equals(((SpineReference)this.spineReferences.get(i)).getResourceId())) {
        break;
      }
      i += 1;
    }
    label58:
    return -1;
  }
  
  public Resource getResource(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.spineReferences.size())) {
      return null;
    }
    return ((SpineReference)this.spineReferences.get(paramInt)).getResource();
  }
  
  public int getResourceIndex(String paramString)
  {
    int k = -1;
    if (StringUtil.isBlank(paramString)) {
      return -1;
    }
    int i = 0;
    for (;;)
    {
      int j = k;
      if (i < this.spineReferences.size())
      {
        if (paramString.equals(((SpineReference)this.spineReferences.get(i)).getResource().getHref())) {
          j = i;
        }
      }
      else {
        return j;
      }
      i += 1;
    }
  }
  
  public int getResourceIndex(Resource paramResource)
  {
    if (paramResource == null) {
      return -1;
    }
    return getResourceIndex(paramResource.getHref());
  }
  
  public List<SpineReference> getSpineReferences()
  {
    return this.spineReferences;
  }
  
  public Resource getTocResource()
  {
    return this.tocResource;
  }
  
  public boolean isEmpty()
  {
    return this.spineReferences.isEmpty();
  }
  
  public void setSpineReferences(List<SpineReference> paramList)
  {
    this.spineReferences = paramList;
  }
  
  public void setTocResource(Resource paramResource)
  {
    this.tocResource = paramResource;
  }
  
  public int size()
  {
    return this.spineReferences.size();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\domain\Spine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */