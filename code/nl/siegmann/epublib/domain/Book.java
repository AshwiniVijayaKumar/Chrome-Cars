package nl.siegmann.epublib.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Book
  implements Serializable
{
  private static final long serialVersionUID = 2068355170895770100L;
  private Resource coverImage;
  private Guide guide = new Guide();
  private Metadata metadata = new Metadata();
  private Resource ncxResource;
  private Resource opfResource;
  private Resources resources = new Resources();
  private Spine spine = new Spine();
  private TableOfContents tableOfContents = new TableOfContents();
  
  private static void addToContentsResult(Resource paramResource, Map<String, Resource> paramMap)
  {
    if ((paramResource != null) && (!paramMap.containsKey(paramResource.getHref()))) {
      paramMap.put(paramResource.getHref(), paramResource);
    }
  }
  
  public Resource addResource(Resource paramResource)
  {
    return this.resources.add(paramResource);
  }
  
  public TOCReference addSection(String paramString, Resource paramResource)
  {
    getResources().add(paramResource);
    paramString = this.tableOfContents.addTOCReference(new TOCReference(paramString, paramResource));
    if (this.spine.findFirstResourceById(paramResource.getId()) < 0) {
      this.spine.addSpineReference(new SpineReference(paramResource));
    }
    return paramString;
  }
  
  public TOCReference addSection(TOCReference paramTOCReference, String paramString, Resource paramResource)
  {
    getResources().add(paramResource);
    if (this.spine.findFirstResourceById(paramResource.getId()) < 0) {
      this.spine.addSpineReference(new SpineReference(paramResource));
    }
    return paramTOCReference.addChildSection(new TOCReference(paramString, paramResource));
  }
  
  public void generateSpineFromTableOfContents()
  {
    Spine localSpine = new Spine(this.tableOfContents);
    localSpine.setTocResource(this.spine.getTocResource());
    this.spine = localSpine;
  }
  
  public List<Resource> getContents()
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    addToContentsResult(getCoverPage(), localLinkedHashMap);
    Iterator localIterator = getSpine().getSpineReferences().iterator();
    while (localIterator.hasNext()) {
      addToContentsResult(((SpineReference)localIterator.next()).getResource(), localLinkedHashMap);
    }
    localIterator = getTableOfContents().getAllUniqueResources().iterator();
    while (localIterator.hasNext()) {
      addToContentsResult((Resource)localIterator.next(), localLinkedHashMap);
    }
    localIterator = getGuide().getReferences().iterator();
    while (localIterator.hasNext()) {
      addToContentsResult(((GuideReference)localIterator.next()).getResource(), localLinkedHashMap);
    }
    return new ArrayList(localLinkedHashMap.values());
  }
  
  public Resource getCoverImage()
  {
    return this.coverImage;
  }
  
  public Resource getCoverPage()
  {
    Resource localResource2 = this.guide.getCoverPage();
    Resource localResource1 = localResource2;
    if (localResource2 == null) {
      localResource1 = this.spine.getResource(0);
    }
    return localResource1;
  }
  
  public Guide getGuide()
  {
    return this.guide;
  }
  
  public Metadata getMetadata()
  {
    return this.metadata;
  }
  
  public Resource getNcxResource()
  {
    return this.ncxResource;
  }
  
  public Resource getOpfResource()
  {
    return this.opfResource;
  }
  
  public Resources getResources()
  {
    return this.resources;
  }
  
  public Spine getSpine()
  {
    return this.spine;
  }
  
  public TableOfContents getTableOfContents()
  {
    return this.tableOfContents;
  }
  
  public String getTitle()
  {
    return getMetadata().getFirstTitle();
  }
  
  public void setCoverImage(Resource paramResource)
  {
    if (paramResource == null) {
      return;
    }
    if (!this.resources.containsByHref(paramResource.getHref())) {
      this.resources.add(paramResource);
    }
    this.coverImage = paramResource;
  }
  
  public void setCoverPage(Resource paramResource)
  {
    if (paramResource == null) {
      return;
    }
    if (!this.resources.containsByHref(paramResource.getHref())) {
      this.resources.add(paramResource);
    }
    this.guide.setCoverPage(paramResource);
  }
  
  public void setMetadata(Metadata paramMetadata)
  {
    this.metadata = paramMetadata;
  }
  
  public void setNcxResource(Resource paramResource)
  {
    this.ncxResource = paramResource;
  }
  
  public void setOpfResource(Resource paramResource)
  {
    this.opfResource = paramResource;
  }
  
  public void setResources(Resources paramResources)
  {
    this.resources = paramResources;
  }
  
  public void setSpine(Spine paramSpine)
  {
    this.spine = paramSpine;
  }
  
  public void setTableOfContents(TableOfContents paramTableOfContents)
  {
    this.tableOfContents = paramTableOfContents;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\domain\Book.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */