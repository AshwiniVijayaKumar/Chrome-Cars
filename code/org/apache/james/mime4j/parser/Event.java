package org.apache.james.mime4j.parser;

public final class Event
{
  public static final Event HEADERS_PREMATURE_END = new Event("Unexpected end of headers detected. Higher level boundary detected or EOF reached.");
  public static final Event INALID_HEADER = new Event("Invalid header encountered");
  public static final Event MIME_BODY_PREMATURE_END = new Event("Body part ended prematurely. Boundary detected in header or EOF reached.");
  private final String code;
  
  public Event(String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("Code may not be null");
    }
    this.code = paramString;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    do
    {
      return false;
      if (this == paramObject) {
        return true;
      }
    } while (!(paramObject instanceof Event));
    paramObject = (Event)paramObject;
    return this.code.equals(((Event)paramObject).code);
  }
  
  public int hashCode()
  {
    return this.code.hashCode();
  }
  
  public String toString()
  {
    return this.code;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\parser\Event.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */