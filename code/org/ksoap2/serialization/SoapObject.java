package org.ksoap2.serialization;

import java.util.Hashtable;
import java.util.Vector;

public class SoapObject
  implements KvmSerializable
{
  protected Vector attributes = new Vector();
  protected String name;
  protected String namespace;
  protected Vector properties = new Vector();
  
  public SoapObject(String paramString1, String paramString2)
  {
    this.namespace = paramString1;
    this.name = paramString2;
  }
  
  public SoapObject addAttribute(String paramString, Object paramObject)
  {
    AttributeInfo localAttributeInfo = new AttributeInfo();
    localAttributeInfo.name = paramString;
    if (paramObject == null) {}
    for (paramString = PropertyInfo.OBJECT_CLASS;; paramString = paramObject.getClass())
    {
      localAttributeInfo.type = paramString;
      localAttributeInfo.value = paramObject;
      return addAttribute(localAttributeInfo);
    }
  }
  
  public SoapObject addAttribute(AttributeInfo paramAttributeInfo)
  {
    this.attributes.addElement(paramAttributeInfo);
    return this;
  }
  
  public SoapObject addProperty(String paramString, Object paramObject)
  {
    PropertyInfo localPropertyInfo = new PropertyInfo();
    localPropertyInfo.name = paramString;
    if (paramObject == null) {}
    for (paramString = PropertyInfo.OBJECT_CLASS;; paramString = paramObject.getClass())
    {
      localPropertyInfo.type = paramString;
      localPropertyInfo.value = paramObject;
      return addProperty(localPropertyInfo);
    }
  }
  
  public SoapObject addProperty(PropertyInfo paramPropertyInfo)
  {
    this.properties.addElement(paramPropertyInfo);
    return this;
  }
  
  public SoapObject addProperty(PropertyInfo paramPropertyInfo, Object paramObject)
  {
    paramPropertyInfo.setValue(paramObject);
    addProperty(paramPropertyInfo);
    return this;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof SoapObject)) {}
    int k;
    int j;
    do
    {
      do
      {
        return false;
        paramObject = (SoapObject)paramObject;
        k = this.properties.size();
      } while (k != ((SoapObject)paramObject).properties.size());
      j = this.attributes.size();
    } while (j != ((SoapObject)paramObject).attributes.size());
    int i = 0;
    if (i < k) {}
    for (;;)
    {
      try
      {
        localObject = (PropertyInfo)this.properties.elementAt(i);
        if (!((PropertyInfo)localObject).getValue().equals(((SoapObject)paramObject).getProperty(((PropertyInfo)localObject).getName()))) {
          break;
        }
        i += 1;
      }
      catch (Exception paramObject)
      {
        Object localObject;
        boolean bool;
        return false;
      }
      if (i < j)
      {
        localObject = (AttributeInfo)this.properties.elementAt(i);
        bool = ((AttributeInfo)localObject).getValue().equals(((SoapObject)paramObject).getProperty(((AttributeInfo)localObject).getName()));
        if (!bool) {
          break;
        }
        i += 1;
        continue;
      }
      return true;
      i = 0;
    }
  }
  
  public Object getAttribute(int paramInt)
  {
    return ((AttributeInfo)this.attributes.elementAt(paramInt)).getValue();
  }
  
  public Object getAttribute(String paramString)
  {
    int i = 0;
    while (i < this.attributes.size())
    {
      if (paramString.equals(((AttributeInfo)this.attributes.elementAt(i)).getName())) {
        return getAttribute(i);
      }
      i += 1;
    }
    throw new RuntimeException("illegal property: " + paramString);
  }
  
  public int getAttributeCount()
  {
    return this.attributes.size();
  }
  
  public void getAttributeInfo(int paramInt, AttributeInfo paramAttributeInfo)
  {
    AttributeInfo localAttributeInfo = (AttributeInfo)this.attributes.elementAt(paramInt);
    paramAttributeInfo.name = localAttributeInfo.name;
    paramAttributeInfo.namespace = localAttributeInfo.namespace;
    paramAttributeInfo.flags = localAttributeInfo.flags;
    paramAttributeInfo.type = localAttributeInfo.type;
    paramAttributeInfo.elementType = localAttributeInfo.elementType;
    paramAttributeInfo.value = localAttributeInfo.getValue();
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getNamespace()
  {
    return this.namespace;
  }
  
  public Object getProperty(int paramInt)
  {
    return ((PropertyInfo)this.properties.elementAt(paramInt)).getValue();
  }
  
  public Object getProperty(String paramString)
  {
    int i = 0;
    while (i < this.properties.size())
    {
      if (paramString.equals(((PropertyInfo)this.properties.elementAt(i)).getName())) {
        return getProperty(i);
      }
      i += 1;
    }
    throw new RuntimeException("illegal property: " + paramString);
  }
  
  public int getPropertyCount()
  {
    return this.properties.size();
  }
  
  public void getPropertyInfo(int paramInt, Hashtable paramHashtable, PropertyInfo paramPropertyInfo)
  {
    getPropertyInfo(paramInt, paramPropertyInfo);
  }
  
  public void getPropertyInfo(int paramInt, PropertyInfo paramPropertyInfo)
  {
    PropertyInfo localPropertyInfo = (PropertyInfo)this.properties.elementAt(paramInt);
    paramPropertyInfo.name = localPropertyInfo.name;
    paramPropertyInfo.namespace = localPropertyInfo.namespace;
    paramPropertyInfo.flags = localPropertyInfo.flags;
    paramPropertyInfo.type = localPropertyInfo.type;
    paramPropertyInfo.elementType = localPropertyInfo.elementType;
  }
  
  public SoapObject newInstance()
  {
    SoapObject localSoapObject = new SoapObject(this.namespace, this.name);
    int i = 0;
    while (i < this.properties.size())
    {
      localSoapObject.addProperty((PropertyInfo)this.properties.elementAt(i));
      i += 1;
    }
    i = 0;
    while (i < this.attributes.size())
    {
      localSoapObject.addAttribute((AttributeInfo)this.attributes.elementAt(i));
      i += 1;
    }
    return localSoapObject;
  }
  
  public void setProperty(int paramInt, Object paramObject)
  {
    ((PropertyInfo)this.properties.elementAt(paramInt)).setValue(paramObject);
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer("" + this.name + "{");
    int i = 0;
    while (i < getPropertyCount())
    {
      localStringBuffer.append("" + ((PropertyInfo)this.properties.elementAt(i)).getName() + "=" + getProperty(i) + "; ");
      i += 1;
    }
    localStringBuffer.append("}");
    return localStringBuffer.toString();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\ksoap2\serialization\SoapObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */