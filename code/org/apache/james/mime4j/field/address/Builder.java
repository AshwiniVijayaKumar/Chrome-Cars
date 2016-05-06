package org.apache.james.mime4j.field.address;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.james.mime4j.codec.DecoderUtil;
import org.apache.james.mime4j.field.address.parser.ASTaddr_spec;
import org.apache.james.mime4j.field.address.parser.ASTaddress;
import org.apache.james.mime4j.field.address.parser.ASTaddress_list;
import org.apache.james.mime4j.field.address.parser.ASTangle_addr;
import org.apache.james.mime4j.field.address.parser.ASTdomain;
import org.apache.james.mime4j.field.address.parser.ASTgroup_body;
import org.apache.james.mime4j.field.address.parser.ASTlocal_part;
import org.apache.james.mime4j.field.address.parser.ASTmailbox;
import org.apache.james.mime4j.field.address.parser.ASTname_addr;
import org.apache.james.mime4j.field.address.parser.ASTphrase;
import org.apache.james.mime4j.field.address.parser.ASTroute;
import org.apache.james.mime4j.field.address.parser.Node;
import org.apache.james.mime4j.field.address.parser.SimpleNode;
import org.apache.james.mime4j.field.address.parser.Token;

class Builder
{
  private static Builder singleton = new Builder();
  
  private void addSpecials(StringBuilder paramStringBuilder, Token paramToken)
  {
    if (paramToken != null)
    {
      addSpecials(paramStringBuilder, paramToken.specialToken);
      paramStringBuilder.append(paramToken.image);
    }
  }
  
  private Mailbox buildAddrSpec(DomainList paramDomainList, ASTaddr_spec paramASTaddr_spec)
  {
    paramASTaddr_spec = new ChildNodeIterator(paramASTaddr_spec);
    return new Mailbox(paramDomainList, buildString((ASTlocal_part)paramASTaddr_spec.next(), true), buildString((ASTdomain)paramASTaddr_spec.next(), true));
  }
  
  private Mailbox buildAddrSpec(ASTaddr_spec paramASTaddr_spec)
  {
    return buildAddrSpec(null, paramASTaddr_spec);
  }
  
  private Mailbox buildAngleAddr(ASTangle_addr paramASTangle_addr)
  {
    paramASTangle_addr = new ChildNodeIterator(paramASTangle_addr);
    DomainList localDomainList = null;
    Node localNode = paramASTangle_addr.next();
    if ((localNode instanceof ASTroute))
    {
      localDomainList = buildRoute((ASTroute)localNode);
      paramASTangle_addr = paramASTangle_addr.next();
    }
    while ((paramASTangle_addr instanceof ASTaddr_spec))
    {
      return buildAddrSpec(localDomainList, (ASTaddr_spec)paramASTangle_addr);
      paramASTangle_addr = localNode;
      if (!(localNode instanceof ASTaddr_spec)) {
        throw new IllegalStateException();
      }
    }
    throw new IllegalStateException();
  }
  
  private MailboxList buildGroupBody(ASTgroup_body paramASTgroup_body)
  {
    ArrayList localArrayList = new ArrayList();
    paramASTgroup_body = new ChildNodeIterator(paramASTgroup_body);
    while (paramASTgroup_body.hasNext())
    {
      Node localNode = paramASTgroup_body.next();
      if ((localNode instanceof ASTmailbox)) {
        localArrayList.add(buildMailbox((ASTmailbox)localNode));
      } else {
        throw new IllegalStateException();
      }
    }
    return new MailboxList(localArrayList, true);
  }
  
  private Mailbox buildNameAddr(ASTname_addr paramASTname_addr)
  {
    paramASTname_addr = new ChildNodeIterator(paramASTname_addr);
    Object localObject = paramASTname_addr.next();
    if ((localObject instanceof ASTphrase))
    {
      localObject = buildString((ASTphrase)localObject, false);
      paramASTname_addr = paramASTname_addr.next();
      if ((paramASTname_addr instanceof ASTangle_addr)) {
        return new Mailbox(DecoderUtil.decodeEncodedWords((String)localObject), buildAngleAddr((ASTangle_addr)paramASTname_addr));
      }
    }
    else
    {
      throw new IllegalStateException();
    }
    throw new IllegalStateException();
  }
  
  private DomainList buildRoute(ASTroute paramASTroute)
  {
    ArrayList localArrayList = new ArrayList(paramASTroute.jjtGetNumChildren());
    paramASTroute = new ChildNodeIterator(paramASTroute);
    while (paramASTroute.hasNext())
    {
      Node localNode = paramASTroute.next();
      if ((localNode instanceof ASTdomain)) {
        localArrayList.add(buildString((ASTdomain)localNode, true));
      } else {
        throw new IllegalStateException();
      }
    }
    return new DomainList(localArrayList, true);
  }
  
  private String buildString(SimpleNode paramSimpleNode, boolean paramBoolean)
  {
    Token localToken1 = paramSimpleNode.firstToken;
    Token localToken2 = paramSimpleNode.lastToken;
    StringBuilder localStringBuilder = new StringBuilder();
    paramSimpleNode = localToken1;
    while (paramSimpleNode != localToken2)
    {
      localStringBuilder.append(paramSimpleNode.image);
      localToken1 = paramSimpleNode.next;
      paramSimpleNode = localToken1;
      if (!paramBoolean)
      {
        addSpecials(localStringBuilder, localToken1.specialToken);
        paramSimpleNode = localToken1;
      }
    }
    localStringBuilder.append(localToken2.image);
    return localStringBuilder.toString();
  }
  
  public static Builder getInstance()
  {
    return singleton;
  }
  
  public Address buildAddress(ASTaddress paramASTaddress)
  {
    paramASTaddress = new ChildNodeIterator(paramASTaddress);
    Object localObject = paramASTaddress.next();
    if ((localObject instanceof ASTaddr_spec)) {
      return buildAddrSpec((ASTaddr_spec)localObject);
    }
    if ((localObject instanceof ASTangle_addr)) {
      return buildAngleAddr((ASTangle_addr)localObject);
    }
    if ((localObject instanceof ASTphrase))
    {
      localObject = buildString((ASTphrase)localObject, false);
      paramASTaddress = paramASTaddress.next();
      if ((paramASTaddress instanceof ASTgroup_body)) {
        return new Group((String)localObject, buildGroupBody((ASTgroup_body)paramASTaddress));
      }
      if ((paramASTaddress instanceof ASTangle_addr)) {
        return new Mailbox(DecoderUtil.decodeEncodedWords((String)localObject), buildAngleAddr((ASTangle_addr)paramASTaddress));
      }
      throw new IllegalStateException();
    }
    throw new IllegalStateException();
  }
  
  public AddressList buildAddressList(ASTaddress_list paramASTaddress_list)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramASTaddress_list.jjtGetNumChildren())
    {
      localArrayList.add(buildAddress((ASTaddress)paramASTaddress_list.jjtGetChild(i)));
      i += 1;
    }
    return new AddressList(localArrayList, true);
  }
  
  public Mailbox buildMailbox(ASTmailbox paramASTmailbox)
  {
    paramASTmailbox = new ChildNodeIterator(paramASTmailbox).next();
    if ((paramASTmailbox instanceof ASTaddr_spec)) {
      return buildAddrSpec((ASTaddr_spec)paramASTmailbox);
    }
    if ((paramASTmailbox instanceof ASTangle_addr)) {
      return buildAngleAddr((ASTangle_addr)paramASTmailbox);
    }
    if ((paramASTmailbox instanceof ASTname_addr)) {
      return buildNameAddr((ASTname_addr)paramASTmailbox);
    }
    throw new IllegalStateException();
  }
  
  private static class ChildNodeIterator
    implements Iterator<Node>
  {
    private int index;
    private int len;
    private SimpleNode simpleNode;
    
    public ChildNodeIterator(SimpleNode paramSimpleNode)
    {
      this.simpleNode = paramSimpleNode;
      this.len = paramSimpleNode.jjtGetNumChildren();
      this.index = 0;
    }
    
    public boolean hasNext()
    {
      return this.index < this.len;
    }
    
    public Node next()
    {
      SimpleNode localSimpleNode = this.simpleNode;
      int i = this.index;
      this.index = (i + 1);
      return localSimpleNode.jjtGetChild(i);
    }
    
    public void remove()
    {
      throw new UnsupportedOperationException();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\address\Builder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */