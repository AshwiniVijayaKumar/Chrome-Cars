package org.apache.james.mime4j.field.address;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MailboxList
  extends AbstractList<Mailbox>
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private final List<Mailbox> mailboxes;
  
  public MailboxList(List<Mailbox> paramList, boolean paramBoolean)
  {
    if (paramList != null)
    {
      if (paramBoolean) {}
      for (;;)
      {
        this.mailboxes = paramList;
        return;
        paramList = new ArrayList(paramList);
      }
    }
    this.mailboxes = Collections.emptyList();
  }
  
  public Mailbox get(int paramInt)
  {
    return (Mailbox)this.mailboxes.get(paramInt);
  }
  
  public void print()
  {
    int i = 0;
    while (i < size())
    {
      Mailbox localMailbox = get(i);
      System.out.println(localMailbox.toString());
      i += 1;
    }
  }
  
  public int size()
  {
    return this.mailboxes.size();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\address\MailboxList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */