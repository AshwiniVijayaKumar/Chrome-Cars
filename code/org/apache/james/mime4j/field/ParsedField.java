package org.apache.james.mime4j.field;

import org.apache.james.mime4j.parser.Field;

public abstract interface ParsedField
  extends Field
{
  public abstract ParseException getParseException();
  
  public abstract boolean isValidField();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\field\ParsedField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */