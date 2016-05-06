package twitter4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationContext;

final class DispatcherFactory
{
  private final Configuration conf;
  private final String dispatcherImpl;
  
  public DispatcherFactory()
  {
    this(ConfigurationContext.getInstance());
  }
  
  public DispatcherFactory(Configuration paramConfiguration)
  {
    this.dispatcherImpl = paramConfiguration.getDispatcherImpl();
    this.conf = paramConfiguration;
  }
  
  public Dispatcher getInstance()
  {
    try
    {
      Dispatcher localDispatcher = (Dispatcher)Class.forName(this.dispatcherImpl).getConstructor(new Class[] { Configuration.class }).newInstance(new Object[] { this.conf });
      return localDispatcher;
    }
    catch (InstantiationException localInstantiationException)
    {
      throw new AssertionError(localInstantiationException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new AssertionError(localIllegalAccessException);
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new AssertionError(localClassNotFoundException);
    }
    catch (ClassCastException localClassCastException)
    {
      throw new AssertionError(localClassCastException);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new AssertionError(localNoSuchMethodException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new AssertionError(localInvocationTargetException);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\DispatcherFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */