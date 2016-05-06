package org.apache.james.mime4j.message;

public class BodyCopier
{
  public static Body copy(Body paramBody)
  {
    if (paramBody == null) {
      throw new IllegalArgumentException("Body is null");
    }
    if ((paramBody instanceof Message)) {
      return new Message((Message)paramBody);
    }
    if ((paramBody instanceof Multipart)) {
      return new Multipart((Multipart)paramBody);
    }
    if ((paramBody instanceof SingleBody)) {
      return ((SingleBody)paramBody).copy();
    }
    throw new IllegalArgumentException("Unsupported body class");
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\message\BodyCopier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */