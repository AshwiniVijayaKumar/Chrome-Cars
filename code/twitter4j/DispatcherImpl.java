package twitter4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import twitter4j.conf.Configuration;

final class DispatcherImpl
  implements Dispatcher
{
  private final ExecutorService executorService;
  
  public DispatcherImpl(final Configuration paramConfiguration)
  {
    this.executorService = Executors.newFixedThreadPool(paramConfiguration.getAsyncNumThreads(), new ThreadFactory()
    {
      int count = 0;
      
      public Thread newThread(Runnable paramAnonymousRunnable)
      {
        paramAnonymousRunnable = new Thread(paramAnonymousRunnable);
        int i = this.count;
        this.count = (i + 1);
        paramAnonymousRunnable.setName(String.format("Twitter4J Async Dispatcher[%d]", new Object[] { Integer.valueOf(i) }));
        paramAnonymousRunnable.setDaemon(paramConfiguration.isDaemonEnabled());
        return paramAnonymousRunnable;
      }
    });
    Runtime.getRuntime().addShutdownHook(new Thread()
    {
      public void run()
      {
        DispatcherImpl.this.executorService.shutdown();
      }
    });
  }
  
  public void invokeLater(Runnable paramRunnable)
  {
    try
    {
      this.executorService.execute(paramRunnable);
      return;
    }
    finally
    {
      paramRunnable = finally;
      throw paramRunnable;
    }
  }
  
  public void shutdown()
  {
    try
    {
      this.executorService.shutdown();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\twitter4j\DispatcherImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */