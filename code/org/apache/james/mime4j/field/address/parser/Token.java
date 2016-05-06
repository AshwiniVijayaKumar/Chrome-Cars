package org.apache.james.mime4j.field.address.parser;

public class Token
{
  public int beginColumn;
  public int beginLine;
  public int endColumn;
  public int endLine;
  public String image;
  public int kind;
  public Token next;
  public Token specialToken;
  
  public static final Token newToken(int paramInt)
  {
    return new Token();
  }
  
  public String toString()
  {
    return this.image;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\address\parser\Token.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */