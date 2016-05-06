package org.apache.james.mime4j.storage;

import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.codec.CodecUtil;

public abstract class AbstractStorageProvider
  implements StorageProvider
{
  public final Storage store(InputStream paramInputStream)
    throws IOException
  {
    StorageOutputStream localStorageOutputStream = createStorageOutputStream();
    CodecUtil.copy(paramInputStream, localStorageOutputStream);
    return localStorageOutputStream.toStorage();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\storage\AbstractStorageProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */