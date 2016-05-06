package org.slf4j.helpers;

public class FormattingTuple
{
  public static FormattingTuple NULL = new FormattingTuple(null);
  private Object[] argArray;
  private String message;
  private Throwable throwable;
  
  public FormattingTuple(String paramString)
  {
    this(paramString, null, null);
  }
  
  public FormattingTuple(String paramString, Object[] paramArrayOfObject, Throwable paramThrowable)
  {
    this.message = paramString;
    this.throwable = paramThrowable;
    if (paramThrowable == null)
    {
      this.argArray = paramArrayOfObject;
      return;
    }
    this.argArray = trimmedCopy(paramArrayOfObject);
  }
  
  static Object[] trimmedCopy(Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject == null) || (paramArrayOfObject.length == 0)) {
      throw new IllegalStateException("non-sensical empty or null argument array");
    }
    int i = paramArrayOfObject.length - 1;
    Object[] arrayOfObject = new Object[i];
    System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 0, i);
    return arrayOfObject;
  }
  
  public Object[] getArgArray()
  {
    return this.argArray;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public Throwable getThrowable()
  {
    return this.throwable;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\slf4j\helpers\FormattingTuple.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */