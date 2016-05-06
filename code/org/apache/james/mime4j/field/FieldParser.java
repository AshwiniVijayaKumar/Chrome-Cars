package org.apache.james.mime4j.field;

import org.apache.james.mime4j.util.ByteSequence;

public abstract interface FieldParser
{
  public abstract ParsedField parse(String paramString1, String paramString2, ByteSequence paramByteSequence);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\FieldParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */