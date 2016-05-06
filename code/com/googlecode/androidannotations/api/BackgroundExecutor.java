package com.googlecode.androidannotations.api;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BackgroundExecutor
{
  private static Executor executor = ;
  
  public static void execute(Runnable paramRunnable)
  {
    executor.execute(paramRunnable);
  }
  
  public static void setExecutor(Executor paramExecutor)
  {
    executor = paramExecutor;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\googlecode\androidannotations\api\BackgroundExecutor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */