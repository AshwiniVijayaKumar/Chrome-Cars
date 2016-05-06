package twitter4j;

import org.apache.commons.logging.LogFactory;

final class CommonsLoggingLoggerFactory
  extends LoggerFactory
{
  public Logger getLogger(Class paramClass)
  {
    return new CommonsLoggingLogger(LogFactory.getLog(paramClass));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\CommonsLoggingLoggerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */