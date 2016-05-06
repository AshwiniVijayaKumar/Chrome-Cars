package twitter4j;

final class StdOutLoggerFactory
  extends LoggerFactory
{
  private static final Logger SINGLETON = new StdOutLogger();
  
  public Logger getLogger(Class paramClass)
  {
    return SINGLETON;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\StdOutLoggerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */