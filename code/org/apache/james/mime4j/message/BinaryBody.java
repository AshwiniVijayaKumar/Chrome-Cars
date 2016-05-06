package org.apache.james.mime4j.message;

import java.io.IOException;
import java.io.InputStream;

public abstract class BinaryBody
  extends SingleBody
{
  public abstract InputStream getInputStream()
    throws IOException;
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\message\BinaryBody.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */