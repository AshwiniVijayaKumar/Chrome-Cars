package nl.siegmann.epublib.util;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class CollectionUtil
{
  public static <T> Enumeration<T> createEnumerationFromIterator(Iterator<T> paramIterator)
  {
    return new IteratorEnumerationAdapter(paramIterator);
  }
  
  public static <T> T first(List<T> paramList)
  {
    if ((paramList == null) || (paramList.isEmpty())) {
      return null;
    }
    return (T)paramList.get(0);
  }
  
  private static class IteratorEnumerationAdapter<T>
    implements Enumeration<T>
  {
    private Iterator<T> iterator;
    
    public IteratorEnumerationAdapter(Iterator<T> paramIterator)
    {
      this.iterator = paramIterator;
    }
    
    public boolean hasMoreElements()
    {
      return this.iterator.hasNext();
    }
    
    public T nextElement()
    {
      return (T)this.iterator.next();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\util\CollectionUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */