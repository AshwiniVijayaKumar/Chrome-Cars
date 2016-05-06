package org.apache.james.mime4j.message;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.MimeIOException;
import org.apache.james.mime4j.parser.AbstractContentHandler;
import org.apache.james.mime4j.parser.Field;
import org.apache.james.mime4j.parser.MimeStreamParser;

public class Header
  implements Iterable<Field>
{
  private Map<String, List<Field>> fieldMap = new HashMap();
  private List<Field> fields = new LinkedList();
  
  public Header() {}
  
  public Header(InputStream paramInputStream)
    throws IOException, MimeIOException
  {
    final MimeStreamParser localMimeStreamParser = new MimeStreamParser();
    localMimeStreamParser.setContentHandler(new AbstractContentHandler()
    {
      public void endHeader()
      {
        localMimeStreamParser.stop();
      }
      
      public void field(Field paramAnonymousField)
        throws MimeException
      {
        Header.this.addField(paramAnonymousField);
      }
    });
    try
    {
      localMimeStreamParser.parse(paramInputStream);
      return;
    }
    catch (MimeException paramInputStream)
    {
      throw new MimeIOException(paramInputStream);
    }
  }
  
  public Header(Header paramHeader)
  {
    paramHeader = paramHeader.fields.iterator();
    while (paramHeader.hasNext()) {
      addField((Field)paramHeader.next());
    }
  }
  
  public void addField(Field paramField)
  {
    List localList = (List)this.fieldMap.get(paramField.getName().toLowerCase());
    Object localObject = localList;
    if (localList == null)
    {
      localObject = new LinkedList();
      this.fieldMap.put(paramField.getName().toLowerCase(), localObject);
    }
    ((List)localObject).add(paramField);
    this.fields.add(paramField);
  }
  
  public Field getField(String paramString)
  {
    paramString = (List)this.fieldMap.get(paramString.toLowerCase());
    if ((paramString != null) && (!paramString.isEmpty())) {
      return (Field)paramString.get(0);
    }
    return null;
  }
  
  public List<Field> getFields()
  {
    return Collections.unmodifiableList(this.fields);
  }
  
  public List<Field> getFields(String paramString)
  {
    paramString = paramString.toLowerCase();
    paramString = (List)this.fieldMap.get(paramString);
    if ((paramString == null) || (paramString.isEmpty())) {
      return Collections.emptyList();
    }
    return Collections.unmodifiableList(paramString);
  }
  
  public Iterator<Field> iterator()
  {
    return Collections.unmodifiableList(this.fields).iterator();
  }
  
  public int removeFields(String paramString)
  {
    Object localObject = paramString.toLowerCase();
    localObject = (List)this.fieldMap.remove(localObject);
    if ((localObject == null) || (((List)localObject).isEmpty())) {
      return 0;
    }
    Iterator localIterator = this.fields.iterator();
    while (localIterator.hasNext()) {
      if (((Field)localIterator.next()).getName().equalsIgnoreCase(paramString)) {
        localIterator.remove();
      }
    }
    return ((List)localObject).size();
  }
  
  public void setField(Field paramField)
  {
    Object localObject = paramField.getName().toLowerCase();
    localObject = (List)this.fieldMap.get(localObject);
    if ((localObject == null) || (((List)localObject).isEmpty()))
    {
      addField(paramField);
      return;
    }
    ((List)localObject).clear();
    ((List)localObject).add(paramField);
    int j = -1;
    int i = 0;
    localObject = this.fields.iterator();
    while (((Iterator)localObject).hasNext())
    {
      int k = j;
      if (((Field)((Iterator)localObject).next()).getName().equalsIgnoreCase(paramField.getName()))
      {
        ((Iterator)localObject).remove();
        k = j;
        if (j == -1) {
          k = i;
        }
      }
      i += 1;
      j = k;
    }
    this.fields.add(j, paramField);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    Iterator localIterator = this.fields.iterator();
    while (localIterator.hasNext())
    {
      localStringBuilder.append(((Field)localIterator.next()).toString());
      localStringBuilder.append("\r\n");
    }
    return localStringBuilder.toString();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\message\Header.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */