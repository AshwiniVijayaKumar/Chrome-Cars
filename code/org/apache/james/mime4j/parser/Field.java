package org.apache.james.mime4j.parser;

import org.apache.james.mime4j.util.ByteSequence;

public abstract interface Field
{
  public abstract String getBody();
  
  public abstract String getName();
  
  public abstract ByteSequence getRaw();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\parser\Field.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */