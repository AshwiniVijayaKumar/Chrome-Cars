package org.slf4j.helpers;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class SubstituteLoggerFactory
  implements ILoggerFactory
{
  final List loggerNameList = new ArrayList();
  
  public Logger getLogger(String paramString)
  {
    synchronized (this.loggerNameList)
    {
      this.loggerNameList.add(paramString);
      return NOPLogger.NOP_LOGGER;
    }
  }
  
  public List getLoggerNameList()
  {
    ArrayList localArrayList = new ArrayList();
    synchronized (this.loggerNameList)
    {
      localArrayList.addAll(this.loggerNameList);
      return localArrayList;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\slf4j\helpers\SubstituteLoggerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */