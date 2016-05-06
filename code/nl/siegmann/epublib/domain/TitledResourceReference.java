package nl.siegmann.epublib.domain;

import java.io.Serializable;
import nl.siegmann.epublib.util.StringUtil;

public class TitledResourceReference
  extends ResourceReference
  implements Serializable
{
  private static final long serialVersionUID = 3918155020095190080L;
  private String fragmentId;
  private String title;
  
  public TitledResourceReference(Resource paramResource)
  {
    this(paramResource, null);
  }
  
  public TitledResourceReference(Resource paramResource, String paramString)
  {
    this(paramResource, paramString, null);
  }
  
  public TitledResourceReference(Resource paramResource, String paramString1, String paramString2)
  {
    super(paramResource);
    this.title = paramString1;
    this.fragmentId = paramString2;
  }
  
  public String getCompleteHref()
  {
    if (StringUtil.isBlank(this.fragmentId)) {
      return this.resource.getHref();
    }
    return this.resource.getHref() + '#' + this.fragmentId;
  }
  
  public String getFragmentId()
  {
    return this.fragmentId;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public void setFragmentId(String paramString)
  {
    this.fragmentId = paramString;
  }
  
  public void setResource(Resource paramResource)
  {
    setResource(paramResource, null);
  }
  
  public void setResource(Resource paramResource, String paramString)
  {
    super.setResource(paramResource);
    this.fragmentId = paramString;
  }
  
  public void setTitle(String paramString)
  {
    this.title = paramString;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\domain\TitledResourceReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */