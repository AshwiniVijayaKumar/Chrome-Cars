package org.apache.james.mime4j.storage;

import java.io.IOException;
import java.io.InputStream;

public abstract interface StorageProvider
{
  public abstract StorageOutputStream createStorageOutputStream()
    throws IOException;
  
  public abstract Storage store(InputStream paramInputStream)
    throws IOException;
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\storage\StorageProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */