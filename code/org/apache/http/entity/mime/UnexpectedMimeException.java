package org.apache.http.entity.mime;

import org.apache.james.mime4j.MimeException;

@Deprecated
public class UnexpectedMimeException
  extends RuntimeException
{
  private static final long serialVersionUID = 1316818299528463579L;
  
  public UnexpectedMimeException(MimeException paramMimeException)
  {
    super(paramMimeException.getMessage(), paramMimeException);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\http\entity\mime\UnexpectedMimeException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */