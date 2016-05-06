package org.apache.james.mime4j.field.address;

import java.io.Serializable;
import java.io.StringReader;
import java.util.List;
import org.apache.james.mime4j.field.address.parser.AddressListParser;
import org.apache.james.mime4j.field.address.parser.ParseException;

public abstract class Address
  implements Serializable
{
  private static final long serialVersionUID = 634090661990433426L;
  
  public static Address parse(String paramString)
  {
    paramString = new AddressListParser(new StringReader(paramString));
    try
    {
      paramString = Builder.getInstance().buildAddress(paramString.parseAddress());
      return paramString;
    }
    catch (ParseException paramString)
    {
      throw new IllegalArgumentException(paramString);
    }
  }
  
  final void addMailboxesTo(List<Mailbox> paramList)
  {
    doAddMailboxesTo(paramList);
  }
  
  protected abstract void doAddMailboxesTo(List<Mailbox> paramList);
  
  public final String getDisplayString()
  {
    return getDisplayString(false);
  }
  
  public abstract String getDisplayString(boolean paramBoolean);
  
  public abstract String getEncodedString();
  
  public String toString()
  {
    return getDisplayString(false);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\address\Address.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */