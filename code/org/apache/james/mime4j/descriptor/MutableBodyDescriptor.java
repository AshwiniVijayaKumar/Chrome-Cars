package org.apache.james.mime4j.descriptor;

import org.apache.james.mime4j.parser.Field;

public abstract interface MutableBodyDescriptor
  extends BodyDescriptor
{
  public abstract void addField(Field paramField);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\descriptor\MutableBodyDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */