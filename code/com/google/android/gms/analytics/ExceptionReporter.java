package com.google.android.gms.analytics;

import android.content.Context;
import java.util.ArrayList;

public class ExceptionReporter
  implements Thread.UncaughtExceptionHandler
{
  private final Context mContext;
  private final Thread.UncaughtExceptionHandler rd;
  private final Tracker re;
  private ExceptionParser rf;
  
  public ExceptionReporter(Tracker paramTracker, Thread.UncaughtExceptionHandler paramUncaughtExceptionHandler, Context paramContext)
  {
    if (paramTracker == null) {
      throw new NullPointerException("tracker cannot be null");
    }
    if (paramContext == null) {
      throw new NullPointerException("context cannot be null");
    }
    this.rd = paramUncaughtExceptionHandler;
    this.re = paramTracker;
    this.rf = new StandardExceptionParser(paramContext, new ArrayList());
    this.mContext = paramContext.getApplicationContext();
    paramContext = new StringBuilder().append("ExceptionReporter created, original handler is ");
    if (paramUncaughtExceptionHandler == null) {}
    for (paramTracker = "null";; paramTracker = paramUncaughtExceptionHandler.getClass().getName())
    {
      aa.v(paramTracker);
      return;
    }
  }
  
  public ExceptionParser getExceptionParser()
  {
    return this.rf;
  }
  
  public void setExceptionParser(ExceptionParser paramExceptionParser)
  {
    this.rf = paramExceptionParser;
  }
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    String str = "UncaughtException";
    if (this.rf != null) {
      if (paramThread == null) {
        break label112;
      }
    }
    label112:
    for (str = paramThread.getName();; str = null)
    {
      str = this.rf.getDescription(str, paramThrowable);
      aa.v("Tracking Exception: " + str);
      this.re.send(new HitBuilders.ExceptionBuilder().setDescription(str).setFatal(true).build());
      GoogleAnalytics.getInstance(this.mContext).dispatchLocalHits();
      if (this.rd != null)
      {
        aa.v("Passing exception to original handler.");
        this.rd.uncaughtException(paramThread, paramThrowable);
      }
      return;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\analytics\ExceptionReporter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */