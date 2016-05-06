package org.apache.james.mime4j.util;

final class EmptyByteSequence
  implements ByteSequence
{
  private static final byte[] EMPTY_BYTES = new byte[0];
  
  public byte byteAt(int paramInt)
  {
    throw new IndexOutOfBoundsException();
  }
  
  public int length()
  {
    return 0;
  }
  
  public byte[] toByteArray()
  {
    return EMPTY_BYTES;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\util\EmptyByteSequence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */