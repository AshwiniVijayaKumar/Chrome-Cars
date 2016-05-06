package org.slf4j.impl;

import org.slf4j.helpers.NOPMDCAdapter;
import org.slf4j.spi.MDCAdapter;

public class StaticMDCBinder
{
  public static final StaticMDCBinder SINGLETON = new StaticMDCBinder();
  
  public MDCAdapter getMDCA()
  {
    return new NOPMDCAdapter();
  }
  
  public String getMDCAdapterClassStr()
  {
    return NOPMDCAdapter.class.getName();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\slf4j\impl\StaticMDCBinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */