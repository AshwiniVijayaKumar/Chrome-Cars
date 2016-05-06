package org.apache.james.mime4j.field.address.parser;

import java.util.Stack;

class JJTAddressListParserState
{
  private Stack<Integer> marks = new Stack();
  private int mk = 0;
  private boolean node_created;
  private Stack<Node> nodes = new Stack();
  private int sp = 0;
  
  void clearNodeScope(Node paramNode)
  {
    while (this.sp > this.mk) {
      popNode();
    }
    this.mk = ((Integer)this.marks.pop()).intValue();
  }
  
  void closeNodeScope(Node paramNode, int paramInt)
  {
    this.mk = ((Integer)this.marks.pop()).intValue();
    for (;;)
    {
      int i = paramInt - 1;
      if (paramInt <= 0) {
        break;
      }
      Node localNode = popNode();
      localNode.jjtSetParent(paramNode);
      paramNode.jjtAddChild(localNode, i);
      paramInt = i;
    }
    paramNode.jjtClose();
    pushNode(paramNode);
    this.node_created = true;
  }
  
  void closeNodeScope(Node paramNode, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      int i = nodeArity();
      this.mk = ((Integer)this.marks.pop()).intValue();
      for (;;)
      {
        int j = i - 1;
        if (i <= 0) {
          break;
        }
        Node localNode = popNode();
        localNode.jjtSetParent(paramNode);
        paramNode.jjtAddChild(localNode, j);
        i = j;
      }
      paramNode.jjtClose();
      pushNode(paramNode);
      this.node_created = true;
      return;
    }
    this.mk = ((Integer)this.marks.pop()).intValue();
    this.node_created = false;
  }
  
  int nodeArity()
  {
    return this.sp - this.mk;
  }
  
  boolean nodeCreated()
  {
    return this.node_created;
  }
  
  void openNodeScope(Node paramNode)
  {
    this.marks.push(new Integer(this.mk));
    this.mk = this.sp;
    paramNode.jjtOpen();
  }
  
  Node peekNode()
  {
    return (Node)this.nodes.peek();
  }
  
  Node popNode()
  {
    int i = this.sp - 1;
    this.sp = i;
    if (i < this.mk) {
      this.mk = ((Integer)this.marks.pop()).intValue();
    }
    return (Node)this.nodes.pop();
  }
  
  void pushNode(Node paramNode)
  {
    this.nodes.push(paramNode);
    this.sp += 1;
  }
  
  void reset()
  {
    this.nodes.removeAllElements();
    this.marks.removeAllElements();
    this.sp = 0;
    this.mk = 0;
  }
  
  Node rootNode()
  {
    return (Node)this.nodes.elementAt(0);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\address\parser\JJTAddressListParserState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */