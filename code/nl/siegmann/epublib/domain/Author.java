package nl.siegmann.epublib.domain;

import java.io.Serializable;
import nl.siegmann.epublib.util.StringUtil;

public class Author
  implements Serializable
{
  private static final long serialVersionUID = 6663408501416574200L;
  private String firstname;
  private String lastname;
  private Relator relator = Relator.AUTHOR;
  
  public Author(String paramString)
  {
    this("", paramString);
  }
  
  public Author(String paramString1, String paramString2)
  {
    this.firstname = paramString1;
    this.lastname = paramString2;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Author)) {}
    do
    {
      return false;
      paramObject = (Author)paramObject;
    } while ((!StringUtil.equals(this.firstname, ((Author)paramObject).firstname)) || (!StringUtil.equals(this.lastname, ((Author)paramObject).lastname)));
    return true;
  }
  
  public String getFirstname()
  {
    return this.firstname;
  }
  
  public String getLastname()
  {
    return this.lastname;
  }
  
  public Relator getRelator()
  {
    return this.relator;
  }
  
  public int hashCode()
  {
    return StringUtil.hashCode(new String[] { this.firstname, this.lastname });
  }
  
  public void setFirstname(String paramString)
  {
    this.firstname = paramString;
  }
  
  public void setLastname(String paramString)
  {
    this.lastname = paramString;
  }
  
  public void setRelator(Relator paramRelator)
  {
    this.relator = paramRelator;
  }
  
  public Relator setRole(String paramString)
  {
    Relator localRelator = Relator.byCode(paramString);
    paramString = localRelator;
    if (localRelator == null) {
      paramString = Relator.AUTHOR;
    }
    this.relator = paramString;
    return paramString;
  }
  
  public String toString()
  {
    return this.lastname + ", " + this.firstname;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\domain\Author.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */