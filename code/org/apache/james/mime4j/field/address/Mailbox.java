package org.apache.james.mime4j.field.address;

import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.apache.james.mime4j.codec.EncoderUtil;
import org.apache.james.mime4j.field.address.parser.AddressListParser;
import org.apache.james.mime4j.field.address.parser.ParseException;

public class Mailbox
  extends Address
{
  private static final DomainList EMPTY_ROUTE_LIST = new DomainList(Collections.emptyList(), true);
  private static final long serialVersionUID = 1L;
  private final String domain;
  private final String localPart;
  private final String name;
  private final DomainList route;
  
  public Mailbox(String paramString1, String paramString2)
  {
    this(null, null, paramString1, paramString2);
  }
  
  public Mailbox(String paramString1, String paramString2, String paramString3)
  {
    this(paramString1, null, paramString2, paramString3);
  }
  
  public Mailbox(String paramString1, DomainList paramDomainList, String paramString2, String paramString3)
  {
    if ((paramString2 == null) || (paramString2.length() == 0)) {
      throw new IllegalArgumentException();
    }
    String str;
    if (paramString1 != null)
    {
      str = paramString1;
      if (paramString1.length() != 0) {}
    }
    else
    {
      str = null;
    }
    this.name = str;
    paramString1 = paramDomainList;
    if (paramDomainList == null) {
      paramString1 = EMPTY_ROUTE_LIST;
    }
    this.route = paramString1;
    this.localPart = paramString2;
    if (paramString3 != null)
    {
      paramString1 = paramString3;
      if (paramString3.length() != 0) {}
    }
    else
    {
      paramString1 = null;
    }
    this.domain = paramString1;
  }
  
  Mailbox(String paramString, Mailbox paramMailbox)
  {
    this(paramString, paramMailbox.getRoute(), paramMailbox.getLocalPart(), paramMailbox.getDomain());
  }
  
  public Mailbox(DomainList paramDomainList, String paramString1, String paramString2)
  {
    this(null, paramDomainList, paramString1, paramString2);
  }
  
  private Object getCanonicalizedAddress()
  {
    if (this.domain == null) {
      return this.localPart;
    }
    return this.localPart + '@' + this.domain.toLowerCase(Locale.US);
  }
  
  public static Mailbox parse(String paramString)
  {
    paramString = new AddressListParser(new StringReader(paramString));
    try
    {
      paramString = Builder.getInstance().buildMailbox(paramString.parseMailbox());
      return paramString;
    }
    catch (ParseException paramString)
    {
      throw new IllegalArgumentException(paramString);
    }
  }
  
  protected final void doAddMailboxesTo(List<Mailbox> paramList)
  {
    paramList.add(this);
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof Mailbox)) {
      return false;
    }
    paramObject = (Mailbox)paramObject;
    return getCanonicalizedAddress().equals(((Mailbox)paramObject).getCanonicalizedAddress());
  }
  
  public String getAddress()
  {
    if (this.domain == null) {
      return this.localPart;
    }
    return this.localPart + '@' + this.domain;
  }
  
  public String getDisplayString(boolean paramBoolean)
  {
    boolean bool2;
    if (this.route != null)
    {
      bool1 = true;
      bool2 = paramBoolean & bool1;
      if ((this.name == null) && (!bool2)) {
        break label155;
      }
    }
    label155:
    for (boolean bool1 = true;; bool1 = false)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      if (this.name != null)
      {
        localStringBuilder.append(this.name);
        localStringBuilder.append(' ');
      }
      if (bool1) {
        localStringBuilder.append('<');
      }
      if (bool2)
      {
        localStringBuilder.append(this.route.toRouteString());
        localStringBuilder.append(':');
      }
      localStringBuilder.append(this.localPart);
      if (this.domain != null)
      {
        localStringBuilder.append('@');
        localStringBuilder.append(this.domain);
      }
      if (bool1) {
        localStringBuilder.append('>');
      }
      return localStringBuilder.toString();
      bool1 = false;
      break;
    }
  }
  
  public String getDomain()
  {
    return this.domain;
  }
  
  public String getEncodedString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (this.name != null)
    {
      localStringBuilder.append(EncoderUtil.encodeAddressDisplayName(this.name));
      localStringBuilder.append(" <");
    }
    localStringBuilder.append(EncoderUtil.encodeAddressLocalPart(this.localPart));
    if (this.domain != null)
    {
      localStringBuilder.append('@');
      localStringBuilder.append(this.domain);
    }
    if (this.name != null) {
      localStringBuilder.append('>');
    }
    return localStringBuilder.toString();
  }
  
  public String getLocalPart()
  {
    return this.localPart;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public DomainList getRoute()
  {
    return this.route;
  }
  
  public int hashCode()
  {
    return getCanonicalizedAddress().hashCode();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\address\Mailbox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */