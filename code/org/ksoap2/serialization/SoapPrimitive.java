package org.ksoap2.serialization;

public class SoapPrimitive
{
  String name;
  String namespace;
  String value;
  
  public SoapPrimitive(String paramString1, String paramString2, String paramString3)
  {
    this.namespace = paramString1;
    this.name = paramString2;
    this.value = paramString3;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof SoapPrimitive)) {}
    label42:
    do
    {
      do
      {
        do
        {
          return false;
          paramObject = (SoapPrimitive)paramObject;
        } while (!this.name.equals(((SoapPrimitive)paramObject).name));
        if (this.namespace != null) {
          break;
        }
      } while (((SoapPrimitive)paramObject).namespace != null);
      if (this.value != null) {
        break label75;
      }
    } while (((SoapPrimitive)paramObject).value != null);
    for (;;)
    {
      return true;
      if (!this.namespace.equals(((SoapPrimitive)paramObject).namespace)) {
        break;
      }
      break label42;
      label75:
      if (!this.value.equals(((SoapPrimitive)paramObject).value)) {
        break;
      }
    }
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public String getNamespace()
  {
    return this.namespace;
  }
  
  public int hashCode()
  {
    int j = this.name.hashCode();
    if (this.namespace == null) {}
    for (int i = 0;; i = this.namespace.hashCode()) {
      return i ^ j;
    }
  }
  
  public String toString()
  {
    return this.value;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\ksoap2\serialization\SoapPrimitive.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */