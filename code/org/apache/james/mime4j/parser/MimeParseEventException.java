package org.apache.james.mime4j.parser;

import org.apache.james.mime4j.MimeException;

public class MimeParseEventException
  extends MimeException
{
  private static final long serialVersionUID = 4632991604246852302L;
  private final Event event;
  
  public MimeParseEventException(Event paramEvent)
  {
    super(paramEvent.toString());
    this.event = paramEvent;
  }
  
  public Event getEvent()
  {
    return this.event;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\parser\MimeParseEventException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */