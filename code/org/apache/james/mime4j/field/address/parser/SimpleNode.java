package org.apache.james.mime4j.field.address.parser;

import java.io.PrintStream;

public class SimpleNode
  extends BaseNode
  implements Node
{
  protected Node[] children;
  protected int id;
  protected Node parent;
  protected AddressListParser parser;
  
  public SimpleNode(int paramInt)
  {
    this.id = paramInt;
  }
  
  public SimpleNode(AddressListParser paramAddressListParser, int paramInt)
  {
    this(paramInt);
    this.parser = paramAddressListParser;
  }
  
  public Object childrenAccept(AddressListParserVisitor paramAddressListParserVisitor, Object paramObject)
  {
    if (this.children != null)
    {
      int i = 0;
      while (i < this.children.length)
      {
        this.children[i].jjtAccept(paramAddressListParserVisitor, paramObject);
        i += 1;
      }
    }
    return paramObject;
  }
  
  public void dump(String paramString)
  {
    System.out.println(toString(paramString));
    if (this.children != null)
    {
      int i = 0;
      while (i < this.children.length)
      {
        SimpleNode localSimpleNode = (SimpleNode)this.children[i];
        if (localSimpleNode != null) {
          localSimpleNode.dump(paramString + " ");
        }
        i += 1;
      }
    }
  }
  
  public Object jjtAccept(AddressListParserVisitor paramAddressListParserVisitor, Object paramObject)
  {
    return paramAddressListParserVisitor.visit(this, paramObject);
  }
  
  public void jjtAddChild(Node paramNode, int paramInt)
  {
    if (this.children == null) {
      this.children = new Node[paramInt + 1];
    }
    for (;;)
    {
      this.children[paramInt] = paramNode;
      return;
      if (paramInt >= this.children.length)
      {
        Node[] arrayOfNode = new Node[paramInt + 1];
        System.arraycopy(this.children, 0, arrayOfNode, 0, this.children.length);
        this.children = arrayOfNode;
      }
    }
  }
  
  public void jjtClose() {}
  
  public Node jjtGetChild(int paramInt)
  {
    return this.children[paramInt];
  }
  
  public int jjtGetNumChildren()
  {
    if (this.children == null) {
      return 0;
    }
    return this.children.length;
  }
  
  public Node jjtGetParent()
  {
    return this.parent;
  }
  
  public void jjtOpen() {}
  
  public void jjtSetParent(Node paramNode)
  {
    this.parent = paramNode;
  }
  
  public String toString()
  {
    return AddressListParserTreeConstants.jjtNodeName[this.id];
  }
  
  public String toString(String paramString)
  {
    return paramString + toString();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\address\parser\SimpleNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */