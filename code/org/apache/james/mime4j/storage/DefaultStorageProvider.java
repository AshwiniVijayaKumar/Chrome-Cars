package org.apache.james.mime4j.storage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DefaultStorageProvider
{
  public static final String DEFAULT_STORAGE_PROVIDER_PROPERTY = "org.apache.james.mime4j.defaultStorageProvider";
  private static volatile StorageProvider instance;
  private static Log log = LogFactory.getLog(DefaultStorageProvider.class);
  
  static
  {
    instance = null;
    initialize();
  }
  
  public static StorageProvider getInstance()
  {
    return instance;
  }
  
  private static void initialize()
  {
    String str = System.getProperty("org.apache.james.mime4j.defaultStorageProvider");
    if (str != null) {}
    try
    {
      instance = (StorageProvider)Class.forName(str).newInstance();
      if (instance == null) {
        instance = new ThresholdStorageProvider(new TempFileStorageProvider(), 1024);
      }
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        log.warn("Unable to create or instantiate StorageProvider class '" + str + "'. Using default instead.", localException);
      }
    }
  }
  
  static void reset()
  {
    instance = null;
    initialize();
  }
  
  public static void setInstance(StorageProvider paramStorageProvider)
  {
    if (paramStorageProvider == null) {
      throw new IllegalArgumentException();
    }
    instance = paramStorageProvider;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\storage\DefaultStorageProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */