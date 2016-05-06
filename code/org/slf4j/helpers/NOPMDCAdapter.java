package org.slf4j.helpers;

import java.util.Map;
import org.slf4j.spi.MDCAdapter;

public class NOPMDCAdapter
  implements MDCAdapter
{
  public void clear() {}
  
  public String get(String paramString)
  {
    return null;
  }
  
  public Map getCopyOfContextMap()
  {
    return null;
  }
  
  public void put(String paramString1, String paramString2) {}
  
  public void remove(String paramString) {}
  
  public void setContextMap(Map paramMap) {}
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\slf4j\helpers\NOPMDCAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */