package twitter4j.management;

import java.util.Map;

public abstract interface APIStatisticsMBean
  extends InvocationStatistics
{
  public abstract Iterable<? extends InvocationStatistics> getInvocationStatistics();
  
  public abstract Map<String, String> getMethodLevelSummariesAsString();
  
  public abstract String getMethodLevelSummary(String paramString);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\management\APIStatisticsMBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */