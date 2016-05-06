package org.apache.commons.lang.math;

import java.io.Serializable;
import org.apache.commons.lang.text.StrBuilder;

public final class IntRange
  extends Range
  implements Serializable
{
  private static final long serialVersionUID = 71849363892730L;
  private transient int hashCode = 0;
  private final int max;
  private transient Integer maxObject = null;
  private final int min;
  private transient Integer minObject = null;
  private transient String toString = null;
  
  public IntRange(int paramInt)
  {
    this.min = paramInt;
    this.max = paramInt;
  }
  
  public IntRange(int paramInt1, int paramInt2)
  {
    if (paramInt2 < paramInt1)
    {
      this.min = paramInt2;
      this.max = paramInt1;
      return;
    }
    this.min = paramInt1;
    this.max = paramInt2;
  }
  
  public IntRange(Number paramNumber)
  {
    if (paramNumber == null) {
      throw new IllegalArgumentException("The number must not be null");
    }
    this.min = paramNumber.intValue();
    this.max = paramNumber.intValue();
    if ((paramNumber instanceof Integer))
    {
      this.minObject = ((Integer)paramNumber);
      this.maxObject = ((Integer)paramNumber);
    }
  }
  
  public IntRange(Number paramNumber1, Number paramNumber2)
  {
    if ((paramNumber1 == null) || (paramNumber2 == null)) {
      throw new IllegalArgumentException("The numbers must not be null");
    }
    int i = paramNumber1.intValue();
    int j = paramNumber2.intValue();
    if (j < i)
    {
      this.min = j;
      this.max = i;
      if ((paramNumber2 instanceof Integer)) {
        this.minObject = ((Integer)paramNumber2);
      }
      if ((paramNumber1 instanceof Integer)) {
        this.maxObject = ((Integer)paramNumber1);
      }
    }
    do
    {
      return;
      this.min = i;
      this.max = j;
      if ((paramNumber1 instanceof Integer)) {
        this.minObject = ((Integer)paramNumber1);
      }
    } while (!(paramNumber2 instanceof Integer));
    this.maxObject = ((Integer)paramNumber2);
  }
  
  public boolean containsInteger(int paramInt)
  {
    return (paramInt >= this.min) && (paramInt <= this.max);
  }
  
  public boolean containsNumber(Number paramNumber)
  {
    if (paramNumber == null) {
      return false;
    }
    return containsInteger(paramNumber.intValue());
  }
  
  public boolean containsRange(Range paramRange)
  {
    if (paramRange == null) {}
    while ((!containsInteger(paramRange.getMinimumInteger())) || (!containsInteger(paramRange.getMaximumInteger()))) {
      return false;
    }
    return true;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof IntRange)) {
        return false;
      }
      paramObject = (IntRange)paramObject;
    } while ((this.min == ((IntRange)paramObject).min) && (this.max == ((IntRange)paramObject).max));
    return false;
  }
  
  public double getMaximumDouble()
  {
    return this.max;
  }
  
  public float getMaximumFloat()
  {
    return this.max;
  }
  
  public int getMaximumInteger()
  {
    return this.max;
  }
  
  public long getMaximumLong()
  {
    return this.max;
  }
  
  public Number getMaximumNumber()
  {
    if (this.maxObject == null) {
      this.maxObject = new Integer(this.max);
    }
    return this.maxObject;
  }
  
  public double getMinimumDouble()
  {
    return this.min;
  }
  
  public float getMinimumFloat()
  {
    return this.min;
  }
  
  public int getMinimumInteger()
  {
    return this.min;
  }
  
  public long getMinimumLong()
  {
    return this.min;
  }
  
  public Number getMinimumNumber()
  {
    if (this.minObject == null) {
      this.minObject = new Integer(this.min);
    }
    return this.minObject;
  }
  
  public int hashCode()
  {
    if (this.hashCode == 0)
    {
      this.hashCode = 17;
      this.hashCode = (this.hashCode * 37 + getClass().hashCode());
      this.hashCode = (this.hashCode * 37 + this.min);
      this.hashCode = (this.hashCode * 37 + this.max);
    }
    return this.hashCode;
  }
  
  public boolean overlapsRange(Range paramRange)
  {
    if (paramRange == null) {}
    while ((!paramRange.containsInteger(this.min)) && (!paramRange.containsInteger(this.max)) && (!containsInteger(paramRange.getMinimumInteger()))) {
      return false;
    }
    return true;
  }
  
  public int[] toArray()
  {
    int[] arrayOfInt = new int[this.max - this.min + 1];
    int i = 0;
    while (i < arrayOfInt.length)
    {
      arrayOfInt[i] = (this.min + i);
      i += 1;
    }
    return arrayOfInt;
  }
  
  public String toString()
  {
    if (this.toString == null)
    {
      StrBuilder localStrBuilder = new StrBuilder(32);
      localStrBuilder.append("Range[");
      localStrBuilder.append(this.min);
      localStrBuilder.append(',');
      localStrBuilder.append(this.max);
      localStrBuilder.append(']');
      this.toString = localStrBuilder.toString();
    }
    return this.toString;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\commons\lang\math\IntRange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */