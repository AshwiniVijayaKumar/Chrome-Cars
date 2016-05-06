package org.slf4j;

import java.util.Map;
import org.slf4j.helpers.NOPMDCAdapter;
import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticMDCBinder;
import org.slf4j.spi.MDCAdapter;

public class MDC
{
  static final String NO_STATIC_MDC_BINDER_URL = "http://www.slf4j.org/codes.html#no_static_mdc_binder";
  static final String NULL_MDCA_URL = "http://www.slf4j.org/codes.html#null_MDCA";
  static MDCAdapter mdcAdapter;
  
  static
  {
    try
    {
      mdcAdapter = StaticMDCBinder.SINGLETON.getMDCA();
      return;
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      mdcAdapter = new NOPMDCAdapter();
      String str = localNoClassDefFoundError.getMessage();
      if ((str != null) && (str.indexOf("org/slf4j/impl/StaticMDCBinder") != -1))
      {
        Util.report("Failed to load class \"org.slf4j.impl.StaticMDCBinder\".");
        Util.report("Defaulting to no-operation MDCAdapter implementation.");
        Util.report("See http://www.slf4j.org/codes.html#no_static_mdc_binder for further details.");
        return;
      }
      throw localNoClassDefFoundError;
    }
    catch (Exception localException)
    {
      Util.report("MDC binding unsuccessful.", localException);
    }
  }
  
  public static void clear()
  {
    if (mdcAdapter == null) {
      throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
    }
    mdcAdapter.clear();
  }
  
  public static String get(String paramString)
    throws IllegalArgumentException
  {
    if (paramString == null) {
      throw new IllegalArgumentException("key parameter cannot be null");
    }
    if (mdcAdapter == null) {
      throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
    }
    return mdcAdapter.get(paramString);
  }
  
  public static Map getCopyOfContextMap()
  {
    if (mdcAdapter == null) {
      throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
    }
    return mdcAdapter.getCopyOfContextMap();
  }
  
  public static MDCAdapter getMDCAdapter()
  {
    return mdcAdapter;
  }
  
  public static void put(String paramString1, String paramString2)
    throws IllegalArgumentException
  {
    if (paramString1 == null) {
      throw new IllegalArgumentException("key parameter cannot be null");
    }
    if (mdcAdapter == null) {
      throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
    }
    mdcAdapter.put(paramString1, paramString2);
  }
  
  public static void remove(String paramString)
    throws IllegalArgumentException
  {
    if (paramString == null) {
      throw new IllegalArgumentException("key parameter cannot be null");
    }
    if (mdcAdapter == null) {
      throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
    }
    mdcAdapter.remove(paramString);
  }
  
  public static void setContextMap(Map paramMap)
  {
    if (mdcAdapter == null) {
      throw new IllegalStateException("MDCAdapter cannot be null. See also http://www.slf4j.org/codes.html#null_MDCA");
    }
    mdcAdapter.setContextMap(paramMap);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\slf4j\MDC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */