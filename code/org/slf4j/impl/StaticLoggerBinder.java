package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

public class StaticLoggerBinder
  implements LoggerFactoryBinder
{
  public static String REQUESTED_API_VERSION = "1.6";
  private static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();
  private static final String loggerFactoryClassStr = AndroidLoggerFactory.class.getName();
  private final ILoggerFactory loggerFactory = new AndroidLoggerFactory();
  
  public static final StaticLoggerBinder getSingleton()
  {
    return SINGLETON;
  }
  
  public ILoggerFactory getLoggerFactory()
  {
    return this.loggerFactory;
  }
  
  public String getLoggerFactoryClassStr()
  {
    return loggerFactoryClassStr;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\slf4j\impl\StaticLoggerBinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */