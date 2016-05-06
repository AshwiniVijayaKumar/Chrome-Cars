package org.slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import org.slf4j.helpers.NOPLoggerFactory;
import org.slf4j.helpers.SubstituteLoggerFactory;
import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticLoggerBinder;

public final class LoggerFactory
{
  private static final String[] API_COMPATIBILITY_LIST = { "1.6" };
  static final String CODES_PREFIX = "http://www.slf4j.org/codes.html";
  static final int FAILED_INITILIZATION = 2;
  static int INITIALIZATION_STATE = 0;
  static final String MULTIPLE_BINDINGS_URL = "http://www.slf4j.org/codes.html#multiple_bindings";
  static NOPLoggerFactory NOP_FALLBACK_FACTORY;
  static final int NOP_FALLBACK_INITILIZATION = 4;
  static final String NO_STATICLOGGERBINDER_URL = "http://www.slf4j.org/codes.html#StaticLoggerBinder";
  static final String NULL_LF_URL = "http://www.slf4j.org/codes.html#null_LF";
  static final int ONGOING_INITILIZATION = 1;
  private static String STATIC_LOGGER_BINDER_PATH = "org/slf4j/impl/StaticLoggerBinder.class";
  static final String SUBSTITUTE_LOGGER_URL = "http://www.slf4j.org/codes.html#substituteLogger";
  static final int SUCCESSFUL_INITILIZATION = 3;
  static SubstituteLoggerFactory TEMP_FACTORY = new SubstituteLoggerFactory();
  static final int UNINITIALIZED = 0;
  static final String UNSUCCESSFUL_INIT_MSG = "org.slf4j.LoggerFactory could not be successfully initialized. See also http://www.slf4j.org/codes.html#unsuccessfulInit";
  static final String UNSUCCESSFUL_INIT_URL = "http://www.slf4j.org/codes.html#unsuccessfulInit";
  static final String VERSION_MISMATCH = "http://www.slf4j.org/codes.html#version_mismatch";
  
  static
  {
    NOP_FALLBACK_FACTORY = new NOPLoggerFactory();
  }
  
  private static final void bind()
  {
    try
    {
      StaticLoggerBinder.getSingleton();
      INITIALIZATION_STATE = 3;
      emitSubstituteLoggerWarning();
      return;
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      str = localNoClassDefFoundError.getMessage();
      if ((str != null) && (str.indexOf("org/slf4j/impl/StaticLoggerBinder") != -1))
      {
        INITIALIZATION_STATE = 4;
        Util.report("Failed to load class \"org.slf4j.impl.StaticLoggerBinder\".");
        Util.report("Defaulting to no-operation (NOP) logger implementation");
        Util.report("See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.");
        return;
      }
      failedBinding(localNoClassDefFoundError);
      throw localNoClassDefFoundError;
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
      String str = localNoSuchMethodError.getMessage();
      if ((str != null) && (str.indexOf("org.slf4j.impl.StaticLoggerBinder.getSingleton()") != -1))
      {
        INITIALIZATION_STATE = 2;
        Util.report("slf4j-api 1.6.x (or later) is incompatible with this binding.");
        Util.report("Your binding is version 1.5.5 or earlier.");
        Util.report("Upgrade your binding to version 1.6.x. or 2.0.x");
      }
      throw localNoSuchMethodError;
    }
    catch (Exception localException)
    {
      failedBinding(localException);
      throw new IllegalStateException("Unexpected initialization failure", localException);
    }
  }
  
  private static final void emitSubstituteLoggerWarning()
  {
    List localList = TEMP_FACTORY.getLoggerNameList();
    if (localList.size() == 0) {}
    for (;;)
    {
      return;
      Util.report("The following loggers will not work becasue they were created");
      Util.report("during the default configuration phase of the underlying logging system.");
      Util.report("See also http://www.slf4j.org/codes.html#substituteLogger");
      int i = 0;
      while (i < localList.size())
      {
        Util.report((String)localList.get(i));
        i += 1;
      }
    }
  }
  
  static void failedBinding(Throwable paramThrowable)
  {
    INITIALIZATION_STATE = 2;
    Util.report("Failed to instantiate SLF4J LoggerFactory", paramThrowable);
  }
  
  public static ILoggerFactory getILoggerFactory()
  {
    if (INITIALIZATION_STATE == 0)
    {
      INITIALIZATION_STATE = 1;
      performInitialization();
    }
    switch (INITIALIZATION_STATE)
    {
    default: 
      throw new IllegalStateException("Unreachable code");
    case 3: 
      return StaticLoggerBinder.getSingleton().getLoggerFactory();
    case 4: 
      return NOP_FALLBACK_FACTORY;
    case 2: 
      throw new IllegalStateException("org.slf4j.LoggerFactory could not be successfully initialized. See also http://www.slf4j.org/codes.html#unsuccessfulInit");
    }
    return TEMP_FACTORY;
  }
  
  public static Logger getLogger(Class paramClass)
  {
    return getLogger(paramClass.getName());
  }
  
  public static Logger getLogger(String paramString)
  {
    return getILoggerFactory().getLogger(paramString);
  }
  
  private static final void performInitialization()
  {
    singleImplementationSanityCheck();
    bind();
    if (INITIALIZATION_STATE == 3) {
      versionSanityCheck();
    }
  }
  
  static void reset()
  {
    INITIALIZATION_STATE = 0;
    TEMP_FACTORY = new SubstituteLoggerFactory();
  }
  
  private static void singleImplementationSanityCheck()
  {
    ArrayList localArrayList;
    do
    {
      for (;;)
      {
        try
        {
          Object localObject = LoggerFactory.class.getClassLoader();
          if (localObject == null)
          {
            localObject = ClassLoader.getSystemResources(STATIC_LOGGER_BINDER_PATH);
            localArrayList = new ArrayList();
            if (!((Enumeration)localObject).hasMoreElements()) {
              break;
            }
            localArrayList.add((URL)((Enumeration)localObject).nextElement());
            continue;
          }
          Enumeration localEnumeration = localIOException.getResources(STATIC_LOGGER_BINDER_PATH);
        }
        catch (IOException localIOException)
        {
          Util.report("Error getting resources from path", localIOException);
          return;
        }
      }
    } while (localArrayList.size() <= 1);
    Util.report("Class path contains multiple SLF4J bindings.");
    int i = 0;
    while (i < localArrayList.size())
    {
      Util.report("Found binding in [" + localArrayList.get(i) + "]");
      i += 1;
    }
    Util.report("See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.");
  }
  
  private static final void versionSanityCheck()
  {
    for (;;)
    {
      int i;
      try
      {
        String str = StaticLoggerBinder.REQUESTED_API_VERSION;
        int j = 0;
        i = 0;
        if (i < API_COMPATIBILITY_LIST.length)
        {
          if (str.startsWith(API_COMPATIBILITY_LIST[i])) {
            j = 1;
          }
        }
        else
        {
          if (j == 0)
          {
            Util.report("The requested version " + str + " by your slf4j binding is not compatible with " + Arrays.asList(API_COMPATIBILITY_LIST).toString());
            Util.report("See http://www.slf4j.org/codes.html#version_mismatch for further details.");
          }
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        Util.report("Unexpected problem occured during version sanity check", localThrowable);
        return;
      }
      catch (NoSuchFieldError localNoSuchFieldError)
      {
        return;
      }
      i += 1;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\slf4j\LoggerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */