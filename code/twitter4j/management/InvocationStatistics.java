package twitter4j.management;

public abstract interface InvocationStatistics
{
  public abstract long getAverageTime();
  
  public abstract long getCallCount();
  
  public abstract long getErrorCount();
  
  public abstract String getName();
  
  public abstract long getTotalTime();
  
  public abstract void reset();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\management\InvocationStatistics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */