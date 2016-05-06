package org.apache.james.mime4j.field;

import org.apache.james.mime4j.MimeException;

public class ParseException
  extends MimeException
{
  private static final long serialVersionUID = 1L;
  
  protected ParseException(String paramString)
  {
    super(paramString);
  }
  
  protected ParseException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  protected ParseException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\ParseException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */