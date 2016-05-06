package twitter4j.conf;

public abstract interface ConfigurationFactory
{
  public abstract void dispose();
  
  public abstract Configuration getInstance();
  
  public abstract Configuration getInstance(String paramString);
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\conf\ConfigurationFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */