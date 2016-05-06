package nl.siegmann.epublib.domain;

import java.io.Serializable;

public class SpineReference
  extends ResourceReference
  implements Serializable
{
  private static final long serialVersionUID = -7921609197351510248L;
  private boolean linear = true;
  
  public SpineReference(Resource paramResource)
  {
    this(paramResource, true);
  }
  
  public SpineReference(Resource paramResource, boolean paramBoolean)
  {
    super(paramResource);
    this.linear = paramBoolean;
  }
  
  public boolean isLinear()
  {
    return this.linear;
  }
  
  public void setLinear(boolean paramBoolean)
  {
    this.linear = paramBoolean;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\domain\SpineReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */