package twitter4j;

public abstract interface ConnectionLifeCycleListener
{
  public abstract void onCleanUp();
  
  public abstract void onConnect();
  
  public abstract void onDisconnect();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\ConnectionLifeCycleListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */