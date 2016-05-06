package nl.siegmann.epublib.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Guide
  implements Serializable
{
  private static final int COVERPAGE_NOT_FOUND = -1;
  private static final int COVERPAGE_UNITIALIZED = -2;
  public static final String DEFAULT_COVER_TITLE = GuideReference.COVER;
  private static final long serialVersionUID = -6256645339915751189L;
  private int coverPageIndex = -1;
  private List<GuideReference> references = new ArrayList();
  
  private void checkCoverPage()
  {
    if (this.coverPageIndex == -2) {
      initCoverPage();
    }
  }
  
  private void initCoverPage()
  {
    int k = -1;
    int i = 0;
    for (;;)
    {
      int j = k;
      if (i < this.references.size())
      {
        if (((GuideReference)this.references.get(i)).getType().equals(GuideReference.COVER)) {
          j = i;
        }
      }
      else
      {
        this.coverPageIndex = j;
        return;
      }
      i += 1;
    }
  }
  
  private void uncheckCoverPage()
  {
    this.coverPageIndex = -2;
  }
  
  public ResourceReference addReference(GuideReference paramGuideReference)
  {
    this.references.add(paramGuideReference);
    uncheckCoverPage();
    return paramGuideReference;
  }
  
  public Resource getCoverPage()
  {
    GuideReference localGuideReference = getCoverReference();
    if (localGuideReference == null) {
      return null;
    }
    return localGuideReference.getResource();
  }
  
  public GuideReference getCoverReference()
  {
    checkCoverPage();
    if (this.coverPageIndex >= 0) {
      return (GuideReference)this.references.get(this.coverPageIndex);
    }
    return null;
  }
  
  public List<GuideReference> getGuideReferencesByType(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.references.iterator();
    while (localIterator.hasNext())
    {
      GuideReference localGuideReference = (GuideReference)localIterator.next();
      if (paramString.equalsIgnoreCase(localGuideReference.getType())) {
        localArrayList.add(localGuideReference);
      }
    }
    return localArrayList;
  }
  
  public List<GuideReference> getReferences()
  {
    return this.references;
  }
  
  public void setCoverPage(Resource paramResource)
  {
    setCoverReference(new GuideReference(paramResource, GuideReference.COVER, DEFAULT_COVER_TITLE));
  }
  
  public int setCoverReference(GuideReference paramGuideReference)
  {
    if (this.coverPageIndex >= 0) {
      this.references.set(this.coverPageIndex, paramGuideReference);
    }
    for (;;)
    {
      return this.coverPageIndex;
      this.references.add(0, paramGuideReference);
      this.coverPageIndex = 0;
    }
  }
  
  public void setReferences(List<GuideReference> paramList)
  {
    this.references = paramList;
    uncheckCoverPage();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\domain\Guide.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */