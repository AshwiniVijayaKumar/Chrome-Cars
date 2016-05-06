package nl.siegmann.epublib.domain;

public enum ManifestItemProperties
  implements ManifestProperties
{
  COVER_IMAGE("cover-image"),  MATHML("mathml"),  NAV("nav"),  REMOTE_RESOURCES("remote-resources"),  SCRIPTED("scripted"),  SVG("svg"),  SWITCH("switch");
  
  private String name;
  
  private ManifestItemProperties(String paramString)
  {
    this.name = paramString;
  }
  
  public String getName()
  {
    return this.name;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\domain\ManifestItemProperties.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */