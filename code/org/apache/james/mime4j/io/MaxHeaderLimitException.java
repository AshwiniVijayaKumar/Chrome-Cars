package org.apache.james.mime4j.io;

import org.apache.james.mime4j.MimeException;

public class MaxHeaderLimitException
  extends MimeException
{
  private static final long serialVersionUID = 2154269045186186769L;
  
  public MaxHeaderLimitException(String paramString)
  {
    super(paramString);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\io\MaxHeaderLimitException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */