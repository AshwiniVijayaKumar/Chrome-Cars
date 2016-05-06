package org.apache.commons.lang;

public class NullArgumentException
  extends IllegalArgumentException
{
  private static final long serialVersionUID = 1174360235354917591L;
  
  public NullArgumentException(String paramString)
  {
    super(str + " must not be null.");
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\commons\lang\NullArgumentException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */