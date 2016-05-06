package org.apache.james.mime4j.field.address;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.StringReader;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.james.mime4j.field.address.parser.AddressListParser;
import org.apache.james.mime4j.field.address.parser.ParseException;

public class AddressList
  extends AbstractList<Address>
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private final List<? extends Address> addresses;
  
  public AddressList(List<? extends Address> paramList, boolean paramBoolean)
  {
    if (paramList != null)
    {
      if (paramBoolean) {}
      for (;;)
      {
        this.addresses = paramList;
        return;
        paramList = new ArrayList(paramList);
      }
    }
    this.addresses = Collections.emptyList();
  }
  
  public static void main(String[] paramArrayOfString)
    throws Exception
  {
    paramArrayOfString = new BufferedReader(new InputStreamReader(System.in));
    try
    {
      for (;;)
      {
        System.out.print("> ");
        String str = paramArrayOfString.readLine();
        if ((str.length() == 0) || (str.toLowerCase().equals("exit")) || (str.toLowerCase().equals("quit")))
        {
          System.out.println("Goodbye.");
          return;
        }
        parse(str).print();
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      Thread.sleep(300L);
    }
  }
  
  public static AddressList parse(String paramString)
    throws ParseException
  {
    paramString = new AddressListParser(new StringReader(paramString));
    return Builder.getInstance().buildAddressList(paramString.parseAddressList());
  }
  
  public MailboxList flatten()
  {
    int j = 0;
    Object localObject = this.addresses.iterator();
    do
    {
      i = j;
      if (!((Iterator)localObject).hasNext()) {
        break;
      }
    } while (((Address)((Iterator)localObject).next() instanceof Mailbox));
    int i = 1;
    if (i == 0) {
      return new MailboxList(this.addresses, true);
    }
    localObject = new ArrayList();
    Iterator localIterator = this.addresses.iterator();
    while (localIterator.hasNext()) {
      ((Address)localIterator.next()).addMailboxesTo((List)localObject);
    }
    return new MailboxList((List)localObject, false);
  }
  
  public Address get(int paramInt)
  {
    return (Address)this.addresses.get(paramInt);
  }
  
  public void print()
  {
    Iterator localIterator = this.addresses.iterator();
    while (localIterator.hasNext())
    {
      Address localAddress = (Address)localIterator.next();
      System.out.println(localAddress.toString());
    }
  }
  
  public int size()
  {
    return this.addresses.size();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\address\AddressList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */