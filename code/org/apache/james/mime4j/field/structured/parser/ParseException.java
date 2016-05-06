package org.apache.james.mime4j.field.structured.parser;

public class ParseException
  extends org.apache.james.mime4j.field.ParseException
{
  private static final long serialVersionUID = 1L;
  public Token currentToken;
  protected String eol = System.getProperty("line.separator", "\n");
  public int[][] expectedTokenSequences;
  protected boolean specialConstructor;
  public String[] tokenImage;
  
  public ParseException()
  {
    super("Cannot parse field");
    this.specialConstructor = false;
  }
  
  public ParseException(String paramString)
  {
    super(paramString);
    this.specialConstructor = false;
  }
  
  public ParseException(Throwable paramThrowable)
  {
    super(paramThrowable);
    this.specialConstructor = false;
  }
  
  public ParseException(Token paramToken, int[][] paramArrayOfInt, String[] paramArrayOfString)
  {
    super("");
    this.specialConstructor = true;
    this.currentToken = paramToken;
    this.expectedTokenSequences = paramArrayOfInt;
    this.tokenImage = paramArrayOfString;
  }
  
  protected String add_escapes(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    if (i < paramString.length())
    {
      char c;
      switch (paramString.charAt(i))
      {
      default: 
        c = paramString.charAt(i);
        if ((c < ' ') || (c > '~'))
        {
          String str = "0000" + Integer.toString(c, 16);
          localStringBuffer.append("\\u" + str.substring(str.length() - 4, str.length()));
        }
        break;
      }
      for (;;)
      {
        i += 1;
        break;
        localStringBuffer.append("\\b");
        continue;
        localStringBuffer.append("\\t");
        continue;
        localStringBuffer.append("\\n");
        continue;
        localStringBuffer.append("\\f");
        continue;
        localStringBuffer.append("\\r");
        continue;
        localStringBuffer.append("\\\"");
        continue;
        localStringBuffer.append("\\'");
        continue;
        localStringBuffer.append("\\\\");
        continue;
        localStringBuffer.append(c);
      }
    }
    return localStringBuffer.toString();
  }
  
  public String getMessage()
  {
    if (!this.specialConstructor) {
      return super.getMessage();
    }
    StringBuffer localStringBuffer = new StringBuffer();
    int j = 0;
    int i = 0;
    while (i < this.expectedTokenSequences.length)
    {
      int k = j;
      if (j < this.expectedTokenSequences[i].length) {
        k = this.expectedTokenSequences[i].length;
      }
      j = 0;
      while (j < this.expectedTokenSequences[i].length)
      {
        localStringBuffer.append(this.tokenImage[this.expectedTokenSequences[i][j]]).append(" ");
        j += 1;
      }
      if (this.expectedTokenSequences[i][(this.expectedTokenSequences[i].length - 1)] != 0) {
        localStringBuffer.append("...");
      }
      localStringBuffer.append(this.eol).append("    ");
      i += 1;
      j = k;
    }
    String str1 = "Encountered \"";
    Token localToken = this.currentToken.next;
    i = 0;
    String str2 = str1;
    if (i < j)
    {
      str2 = str1;
      if (i != 0) {
        str2 = str1 + " ";
      }
      if (localToken.kind == 0) {
        str2 = str2 + this.tokenImage[0];
      }
    }
    else
    {
      str1 = str2 + "\" at line " + this.currentToken.next.beginLine + ", column " + this.currentToken.next.beginColumn;
      str1 = str1 + "." + this.eol;
      if (this.expectedTokenSequences.length != 1) {
        break label430;
      }
    }
    label430:
    for (str1 = str1 + "Was expecting:" + this.eol + "    ";; str1 = str1 + "Was expecting one of:" + this.eol + "    ")
    {
      return str1 + localStringBuffer.toString();
      str1 = str2 + add_escapes(localToken.image);
      localToken = localToken.next;
      i += 1;
      break;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\structured\parser\ParseException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */