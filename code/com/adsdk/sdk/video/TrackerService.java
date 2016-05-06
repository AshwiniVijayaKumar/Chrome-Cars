package com.adsdk.sdk.video;

import com.adsdk.sdk.Log;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;

public class TrackerService
{
  private static Object sLock = new Object();
  private static Queue<TrackEvent> sRetryTrackEvents = new LinkedList();
  private static boolean sStopped;
  private static Thread sThread;
  private static boolean sThreadRunning = false;
  private static Queue<TrackEvent> sTrackEvents = new LinkedList();
  
  private static TrackEvent getNextUpdate()
  {
    synchronized (sLock)
    {
      if (sTrackEvents.peek() == null) {
        return null;
      }
      TrackEvent localTrackEvent = (TrackEvent)sTrackEvents.poll();
      return localTrackEvent;
    }
  }
  
  private static boolean hasMoreUpdates()
  {
    for (;;)
    {
      synchronized (sLock)
      {
        if (sTrackEvents.isEmpty())
        {
          bool = false;
          Log.d("More updates:" + bool + " size:" + sTrackEvents.size());
          return bool;
        }
      }
      boolean bool = true;
    }
  }
  
  public static void release()
  {
    Log.v("release");
    if (sThread != null)
    {
      Log.v("release stopping Tracking events thread");
      sStopped = true;
    }
  }
  
  public static void requestRetry(TrackEvent paramTrackEvent)
  {
    synchronized (sLock)
    {
      if (!sRetryTrackEvents.contains(paramTrackEvent))
      {
        paramTrackEvent.retries += 1;
        if (paramTrackEvent.retries <= 5) {
          sRetryTrackEvents.add(paramTrackEvent);
        }
      }
      Log.d("Added retry track event:" + sRetryTrackEvents.size());
      return;
    }
  }
  
  public static void requestTrack(TrackEvent paramTrackEvent)
  {
    synchronized (sLock)
    {
      if (!sTrackEvents.contains(paramTrackEvent)) {
        sTrackEvents.add(paramTrackEvent);
      }
      Log.d("Added track event:" + sTrackEvents.size());
      if (!sThreadRunning) {
        startTracking();
      }
      return;
    }
  }
  
  public static void requestTrack(TrackEvent[] paramArrayOfTrackEvent)
  {
    for (;;)
    {
      int i;
      synchronized (sLock)
      {
        int j = paramArrayOfTrackEvent.length;
        i = 0;
        if (i >= j)
        {
          Log.d("Added track event:" + sTrackEvents.size());
          if (!sThreadRunning) {
            startTracking();
          }
          return;
        }
        TrackEvent localTrackEvent = paramArrayOfTrackEvent[i];
        if (!sTrackEvents.contains(localTrackEvent)) {
          sTrackEvents.add(localTrackEvent);
        }
      }
      i += 1;
    }
  }
  
  public static void startTracking()
  {
    synchronized (sLock)
    {
      if (!sThreadRunning)
      {
        sThreadRunning = true;
        sThread = new Thread(new Runnable()
        {
          public void run()
          {
            TrackerService.sStopped = false;
            for (;;)
            {
              if (TrackerService.sStopped)
              {
                TrackerService.sStopped = false;
                TrackerService.sThreadRunning = false;
                TrackerService.sThread = null;
                return;
              }
              label81:
              while ((TrackerService.access$2()) && (!TrackerService.sStopped))
              {
                ??? = TrackerService.access$3();
                Log.d("Sending tracking :" + ((TrackEvent)???).url + " Time:" + ((TrackEvent)???).timestamp + " Events left:" + TrackerService.sTrackEvents.size());
                if (??? != null) {
                  break;
                }
              }
              if ((!TrackerService.sStopped) && (!TrackerService.sRetryTrackEvents.isEmpty())) {}
              try
              {
                Thread.sleep(30000L);
                synchronized (TrackerService.sLock)
                {
                  TrackerService.sTrackEvents.addAll(TrackerService.sRetryTrackEvents);
                  TrackerService.sRetryTrackEvents.clear();
                }
                try
                {
                  Object localObject3 = new URL(((TrackEvent)???).url);
                  Log.d("Sending conversion Request");
                  Log.d("Perform tracking HTTP Get Url: " + ((TrackEvent)???).url);
                  DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
                  HttpConnectionParams.setSoTimeout(localDefaultHttpClient.getParams(), 10000);
                  HttpConnectionParams.setConnectionTimeout(localDefaultHttpClient.getParams(), 10000);
                  localObject3 = new HttpGet(((URL)localObject3).toString());
                  try
                  {
                    if (localDefaultHttpClient.execute((HttpUriRequest)localObject3).getStatusLine().getStatusCode() == 200) {
                      break label293;
                    }
                    TrackerService.requestRetry((TrackEvent)???);
                  }
                  catch (Throwable localThrowable)
                  {
                    TrackerService.requestRetry((TrackEvent)???);
                  }
                }
                catch (MalformedURLException localMalformedURLException)
                {
                  Log.d("Wrong tracking url:" + ((TrackEvent)???).url);
                }
                break label81;
                label293:
                Log.d("Tracking OK");
                break label81;
                TrackerService.sStopped = true;
              }
              catch (Exception localException)
              {
                for (;;) {}
              }
            }
          }
        });
        sThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler()
        {
          public void uncaughtException(Thread paramAnonymousThread, Throwable paramAnonymousThrowable)
          {
            TrackerService.sThreadRunning = false;
            TrackerService.sThread = null;
            TrackerService.startTracking();
          }
        });
        sThread.start();
      }
      return;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\adsdk\sdk\video\TrackerService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */