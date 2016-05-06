package org.apache.james.mime4j.field.address;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.james.mime4j.codec.EncoderUtil;

public class Group
  extends Address
{
  private static final long serialVersionUID = 1L;
  private final MailboxList mailboxList;
  private final String name;
  
  public Group(String paramString, Collection<Mailbox> paramCollection)
  {
    this(paramString, new MailboxList(new ArrayList(paramCollection), true));
  }
  
  public Group(String paramString, MailboxList paramMailboxList)
  {
    if (paramString == null) {
      throw new IllegalArgumentException();
    }
    if (paramMailboxList == null) {
      throw new IllegalArgumentException();
    }
    this.name = paramString;
    this.mailboxList = paramMailboxList;
  }
  
  public Group(String paramString, Mailbox... paramVarArgs)
  {
    this(paramString, new MailboxList(Arrays.asList(paramVarArgs), true));
  }
  
  public static Group parse(String paramString)
  {
    paramString = Address.parse(paramString);
    if (!(paramString instanceof Group)) {
      throw new IllegalArgumentException("Not a group address");
    }
    return (Group)paramString;
  }
  
  protected void doAddMailboxesTo(List<Mailbox> paramList)
  {
    Iterator localIterator = this.mailboxList.iterator();
    while (localIterator.hasNext()) {
      paramList.add((Mailbox)localIterator.next());
    }
  }
  
  public String getDisplayString(boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.name);
    localStringBuilder.append(':');
    int i = 1;
    Iterator localIterator = this.mailboxList.iterator();
    if (localIterator.hasNext())
    {
      Mailbox localMailbox = (Mailbox)localIterator.next();
      if (i != 0) {
        i = 0;
      }
      for (;;)
      {
        localStringBuilder.append(' ');
        localStringBuilder.append(localMailbox.getDisplayString(paramBoolean));
        break;
        localStringBuilder.append(',');
      }
    }
    localStringBuilder.append(";");
    return localStringBuilder.toString();
  }
  
  public String getEncodedString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(EncoderUtil.encodeAddressDisplayName(this.name));
    localStringBuilder.append(':');
    int i = 1;
    Iterator localIterator = this.mailboxList.iterator();
    if (localIterator.hasNext())
    {
      Mailbox localMailbox = (Mailbox)localIterator.next();
      if (i != 0) {
        i = 0;
      }
      for (;;)
      {
        localStringBuilder.append(' ');
        localStringBuilder.append(localMailbox.getEncodedString());
        break;
        localStringBuilder.append(',');
      }
    }
    localStringBuilder.append(';');
    return localStringBuilder.toString();
  }
  
  public MailboxList getMailboxes()
  {
    return this.mailboxList;
  }
  
  public String getName()
  {
    return this.name;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\address\Group.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */