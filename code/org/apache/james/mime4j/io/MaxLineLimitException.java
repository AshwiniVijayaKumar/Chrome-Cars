package org.apache.james.mime4j.io;

import org.apache.james.mime4j.MimeIOException;

public class MaxLineLimitException
  extends MimeIOException
{
  private static final long serialVersionUID = 8039001187837730773L;
  
  public MaxLineLimitException(String paramString)
  {
    super(paramString);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\io\MaxLineLimitException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */