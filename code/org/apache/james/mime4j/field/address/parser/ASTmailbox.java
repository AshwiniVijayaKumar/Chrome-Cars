package org.apache.james.mime4j.field.address.parser;

public class ASTmailbox
  extends SimpleNode
{
  public ASTmailbox(int paramInt)
  {
    super(paramInt);
  }
  
  public ASTmailbox(AddressListParser paramAddressListParser, int paramInt)
  {
    super(paramAddressListParser, paramInt);
  }
  
  public Object jjtAccept(AddressListParserVisitor paramAddressListParserVisitor, Object paramObject)
  {
    return paramAddressListParserVisitor.visit(this, paramObject);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\address\parser\ASTmailbox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */