package org.apache.james.mime4j.util;

public abstract interface ByteSequence
{
  public static final ByteSequence EMPTY = new EmptyByteSequence();
  
  public abstract byte byteAt(int paramInt);
  
  public abstract int length();
  
  public abstract byte[] toByteArray();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\util\ByteSequence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */