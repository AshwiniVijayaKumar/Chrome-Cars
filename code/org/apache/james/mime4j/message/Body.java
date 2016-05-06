package org.apache.james.mime4j.message;

public abstract interface Body
  extends Disposable
{
  public abstract Entity getParent();
  
  public abstract void setParent(Entity paramEntity);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\james\mime4j\message\Body.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */