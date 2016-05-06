package org.apache.james.mime4j.message;

import java.io.IOException;
import java.io.Reader;

public abstract class TextBody
  extends SingleBody
{
  public abstract String getMimeCharset();
  
  public abstract Reader getReader()
    throws IOException;
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\message\TextBody.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */