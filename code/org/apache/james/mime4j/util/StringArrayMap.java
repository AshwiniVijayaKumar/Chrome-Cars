package org.apache.james.mime4j.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public class StringArrayMap
  implements Serializable
{
  private static final long serialVersionUID = -5833051164281786907L;
  private final Map<String, Object> map = new HashMap();
  
  public static Map<String, String[]> asMap(Map<String, Object> paramMap)
  {
    HashMap localHashMap = new HashMap(paramMap.size());
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      String[] arrayOfString = asStringArray(localEntry.getValue());
      localHashMap.put(localEntry.getKey(), arrayOfString);
    }
    return Collections.unmodifiableMap(localHashMap);
  }
  
  public static String asString(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    if ((paramObject instanceof String)) {
      return (String)paramObject;
    }
    if ((paramObject instanceof String[])) {
      return ((String[])(String[])paramObject)[0];
    }
    if ((paramObject instanceof List)) {
      return (String)((List)paramObject).get(0);
    }
    throw new IllegalStateException("Invalid parameter class: " + paramObject.getClass().getName());
  }
  
  public static String[] asStringArray(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    if ((paramObject instanceof String)) {
      return new String[] { (String)paramObject };
    }
    if ((paramObject instanceof String[])) {
      return (String[])paramObject;
    }
    if ((paramObject instanceof List))
    {
      paramObject = (List)paramObject;
      return (String[])((List)paramObject).toArray(new String[((List)paramObject).size()]);
    }
    throw new IllegalStateException("Invalid parameter class: " + paramObject.getClass().getName());
  }
  
  public static Enumeration<String> asStringEnum(Object paramObject)
  {
    if (paramObject == null) {
      return null;
    }
    if ((paramObject instanceof String)) {
      new Enumeration()
      {
        private Object value = this.val$pValue;
        
        public boolean hasMoreElements()
        {
          return this.value != null;
        }
        
        public String nextElement()
        {
          if (this.value == null) {
            throw new NoSuchElementException();
          }
          String str = (String)this.value;
          this.value = null;
          return str;
        }
      };
    }
    if ((paramObject instanceof String[])) {
      new Enumeration()
      {
        private int offset;
        
        public boolean hasMoreElements()
        {
          return this.offset < this.val$values.length;
        }
        
        public String nextElement()
        {
          if (this.offset >= this.val$values.length) {
            throw new NoSuchElementException();
          }
          String[] arrayOfString = this.val$values;
          int i = this.offset;
          this.offset = (i + 1);
          return arrayOfString[i];
        }
      };
    }
    if ((paramObject instanceof List)) {
      return Collections.enumeration((List)paramObject);
    }
    throw new IllegalStateException("Invalid parameter class: " + paramObject.getClass().getName());
  }
  
  protected void addMapValue(Map<String, Object> paramMap, String paramString1, String paramString2)
  {
    Object localObject = paramMap.get(paramString1);
    if (localObject == null) {}
    for (;;)
    {
      paramMap.put(paramString1, paramString2);
      return;
      ArrayList localArrayList;
      if ((localObject instanceof String))
      {
        localArrayList = new ArrayList();
        localArrayList.add(localObject);
        localArrayList.add(paramString2);
        paramString2 = localArrayList;
      }
      else if ((localObject instanceof List))
      {
        ((List)localObject).add(paramString2);
        paramString2 = (String)localObject;
      }
      else
      {
        if (!(localObject instanceof String[])) {
          break;
        }
        localArrayList = new ArrayList();
        localObject = (String[])localObject;
        int j = localObject.length;
        int i = 0;
        while (i < j)
        {
          localArrayList.add(localObject[i]);
          i += 1;
        }
        localArrayList.add(paramString2);
        paramString2 = localArrayList;
      }
    }
    throw new IllegalStateException("Invalid object type: " + localObject.getClass().getName());
  }
  
  public void addValue(String paramString1, String paramString2)
  {
    addMapValue(this.map, convertName(paramString1), paramString2);
  }
  
  protected String convertName(String paramString)
  {
    return paramString.toLowerCase();
  }
  
  public Map<String, String[]> getMap()
  {
    return asMap(this.map);
  }
  
  public String[] getNameArray()
  {
    Set localSet = this.map.keySet();
    return (String[])localSet.toArray(new String[localSet.size()]);
  }
  
  public Enumeration<String> getNames()
  {
    return Collections.enumeration(this.map.keySet());
  }
  
  public String getValue(String paramString)
  {
    return asString(this.map.get(convertName(paramString)));
  }
  
  public Enumeration<String> getValueEnum(String paramString)
  {
    return asStringEnum(this.map.get(convertName(paramString)));
  }
  
  public String[] getValues(String paramString)
  {
    return asStringArray(this.map.get(convertName(paramString)));
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\util\StringArrayMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */