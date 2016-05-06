package twitter4j;

public abstract interface Dispatcher
{
  public abstract void invokeLater(Runnable paramRunnable);
  
  public abstract void shutdown();
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\Dispatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */