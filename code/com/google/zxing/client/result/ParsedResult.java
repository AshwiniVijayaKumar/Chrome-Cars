package com.google.zxing.client.result;

public abstract class ParsedResult
{
  private final ParsedResultType type;
  
  protected ParsedResult(ParsedResultType paramParsedResultType)
  {
    this.type = paramParsedResultType;
  }
  
  public static void maybeAppend(String paramString, StringBuilder paramStringBuilder)
  {
    if ((paramString != null) && (paramString.length() > 0))
    {
      if (paramStringBuilder.length() > 0) {
        paramStringBuilder.append('\n');
      }
      paramStringBuilder.append(paramString);
    }
  }
  
  public static void maybeAppend(String[] paramArrayOfString, StringBuilder paramStringBuilder)
  {
    int j;
    int i;
    if (paramArrayOfString != null)
    {
      j = paramArrayOfString.length;
      i = 0;
    }
    for (;;)
    {
      if (i >= j) {
        return;
      }
      String str = paramArrayOfString[i];
      if ((str != null) && (str.length() > 0))
      {
        if (paramStringBuilder.length() > 0) {
          paramStringBuilder.append('\n');
        }
        paramStringBuilder.append(str);
      }
      i += 1;
    }
  }
  
  public abstract String getDisplayResult();
  
  public final ParsedResultType getType()
  {
    return this.type;
  }
  
  public final String toString()
  {
    return getDisplayResult();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\result\ParsedResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */