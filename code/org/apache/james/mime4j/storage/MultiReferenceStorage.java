package org.apache.james.mime4j.storage;

import java.io.IOException;
import java.io.InputStream;

public class MultiReferenceStorage
  implements Storage
{
  private int referenceCounter;
  private final Storage storage;
  
  public MultiReferenceStorage(Storage paramStorage)
  {
    if (paramStorage == null) {
      throw new IllegalArgumentException();
    }
    this.storage = paramStorage;
    this.referenceCounter = 1;
  }
  
  private boolean decrementCounter()
  {
    try
    {
      if (this.referenceCounter == 0) {
        throw new IllegalStateException("storage has been deleted");
      }
    }
    finally {}
    int i = this.referenceCounter - 1;
    this.referenceCounter = i;
    if (i == 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private void incrementCounter()
  {
    try
    {
      if (this.referenceCounter == 0) {
        throw new IllegalStateException("storage has been deleted");
      }
    }
    finally {}
    this.referenceCounter += 1;
  }
  
  public void addReference()
  {
    incrementCounter();
  }
  
  public void delete()
  {
    if (decrementCounter()) {
      this.storage.delete();
    }
  }
  
  public InputStream getInputStream()
    throws IOException
  {
    return this.storage.getInputStream();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\storage\MultiReferenceStorage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */