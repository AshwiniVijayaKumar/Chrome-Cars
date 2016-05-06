package org.apache.james.mime4j.field.address.parser;

public abstract interface Node
{
  public abstract Object jjtAccept(AddressListParserVisitor paramAddressListParserVisitor, Object paramObject);
  
  public abstract void jjtAddChild(Node paramNode, int paramInt);
  
  public abstract void jjtClose();
  
  public abstract Node jjtGetChild(int paramInt);
  
  public abstract int jjtGetNumChildren();
  
  public abstract Node jjtGetParent();
  
  public abstract void jjtOpen();
  
  public abstract void jjtSetParent(Node paramNode);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\address\parser\Node.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */