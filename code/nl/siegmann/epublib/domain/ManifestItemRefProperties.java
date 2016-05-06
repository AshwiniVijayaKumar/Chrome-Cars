package nl.siegmann.epublib.domain;

public enum ManifestItemRefProperties
  implements ManifestProperties
{
  PAGE_SPREAD_LEFT("page-spread-left"),  PAGE_SPREAD_RIGHT("page-spread-right");
  
  private String name;
  
  private ManifestItemRefProperties(String paramString)
  {
    this.name = paramString;
  }
  
  public String getName()
  {
    return this.name;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\domain\ManifestItemRefProperties.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */