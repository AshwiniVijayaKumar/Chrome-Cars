package org.apache.commons.lang.mutable;

public class MutableShort
  extends Number
  implements Comparable, Mutable
{
  private static final long serialVersionUID = -2135791679L;
  private short value;
  
  public MutableShort() {}
  
  public MutableShort(Number paramNumber)
  {
    this.value = paramNumber.shortValue();
  }
  
  public MutableShort(String paramString)
    throws NumberFormatException
  {
    this.value = Short.parseShort(paramString);
  }
  
  public MutableShort(short paramShort)
  {
    this.value = paramShort;
  }
  
  public void add(Number paramNumber)
  {
    this.value = ((short)(this.value + paramNumber.shortValue()));
  }
  
  public void add(short paramShort)
  {
    this.value = ((short)(this.value + paramShort));
  }
  
  public int compareTo(Object paramObject)
  {
    int i = ((MutableShort)paramObject).value;
    if (this.value < i) {
      return -1;
    }
    if (this.value == i) {
      return 0;
    }
    return 1;
  }
  
  public void decrement()
  {
    this.value = ((short)(this.value - 1));
  }
  
  public double doubleValue()
  {
    return this.value;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof MutableShort))
    {
      bool1 = bool2;
      if (this.value == ((MutableShort)paramObject).shortValue()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public float floatValue()
  {
    return this.value;
  }
  
  public Object getValue()
  {
    return new Short(this.value);
  }
  
  public int hashCode()
  {
    return this.value;
  }
  
  public void increment()
  {
    this.value = ((short)(this.value + 1));
  }
  
  public int intValue()
  {
    return this.value;
  }
  
  public long longValue()
  {
    return this.value;
  }
  
  public void setValue(Object paramObject)
  {
    setValue(((Number)paramObject).shortValue());
  }
  
  public void setValue(short paramShort)
  {
    this.value = paramShort;
  }
  
  public short shortValue()
  {
    return this.value;
  }
  
  public void subtract(Number paramNumber)
  {
    this.value = ((short)(this.value - paramNumber.shortValue()));
  }
  
  public void subtract(short paramShort)
  {
    this.value = ((short)(this.value - paramShort));
  }
  
  public Short toShort()
  {
    return new Short(shortValue());
  }
  
  public String toString()
  {
    return String.valueOf(this.value);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\commons\lang\mutable\MutableShort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */