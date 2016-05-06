package nl.siegmann.epublib.domain;

import java.io.Serializable;

public class ResourceReference
  implements Serializable
{
  private static final long serialVersionUID = 2596967243557743048L;
  protected Resource resource;
  
  public ResourceReference(Resource paramResource)
  {
    this.resource = paramResource;
  }
  
  public Resource getResource()
  {
    return this.resource;
  }
  
  public String getResourceId()
  {
    if (this.resource != null) {
      return this.resource.getId();
    }
    return null;
  }
  
  public void setResource(Resource paramResource)
  {
    this.resource = paramResource;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\domain\ResourceReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */