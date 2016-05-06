package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaStatus;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class dv
  extends dp
{
  private static final long yi = TimeUnit.HOURS.toMillis(24L);
  private static final long yj = TimeUnit.HOURS.toMillis(24L);
  private static final long yk = TimeUnit.HOURS.toMillis(24L);
  private static final long yl = TimeUnit.SECONDS.toMillis(1L);
  private final Handler mHandler = new Handler(Looper.getMainLooper());
  private long ym;
  private MediaStatus yn;
  private final dy yo = new dy(yj);
  private final dy yp = new dy(yi);
  private final dy yq = new dy(yi);
  private final dy yr = new dy(yi);
  private final dy ys = new dy(yk);
  private final dy yt = new dy(yi);
  private final dy yu = new dy(yi);
  private final dy yv = new dy(yi);
  private final Runnable yw = new a(null);
  private boolean yx;
  
  public dv()
  {
    super("urn:x-cast:com.google.cast.media", "MediaControlChannel");
    dj();
  }
  
  private void a(long paramLong, JSONObject paramJSONObject)
    throws JSONException
  {
    int k = 1;
    boolean bool = this.yo.m(paramLong);
    int j;
    if ((this.ys.dl()) && (!this.ys.m(paramLong)))
    {
      i = 1;
      if (this.yt.dl())
      {
        j = k;
        if (!this.yt.m(paramLong)) {}
      }
      else
      {
        if ((!this.yu.dl()) || (this.yu.m(paramLong))) {
          break label279;
        }
        j = k;
      }
      label87:
      if (i == 0) {
        break label300;
      }
    }
    label279:
    label300:
    for (int i = 2;; i = 0)
    {
      k = i;
      if (j != 0) {
        k = i | 0x1;
      }
      if ((bool) || (this.yn == null))
      {
        this.yn = new MediaStatus(paramJSONObject);
        this.ym = SystemClock.elapsedRealtime();
      }
      for (i = 7;; i = this.yn.a(paramJSONObject, k))
      {
        if ((i & 0x1) != 0)
        {
          this.ym = SystemClock.elapsedRealtime();
          onStatusUpdated();
        }
        if ((i & 0x2) != 0)
        {
          this.ym = SystemClock.elapsedRealtime();
          onStatusUpdated();
        }
        if ((i & 0x4) != 0) {
          onMetadataUpdated();
        }
        this.yo.c(paramLong, 0);
        this.yp.c(paramLong, 0);
        this.yq.c(paramLong, 0);
        this.yr.c(paramLong, 0);
        this.ys.c(paramLong, 0);
        this.yt.c(paramLong, 0);
        this.yu.c(paramLong, 0);
        this.yv.c(paramLong, 0);
        return;
        i = 0;
        break;
        j = 0;
        break label87;
      }
    }
  }
  
  private void dj()
  {
    u(false);
    this.ym = 0L;
    this.yn = null;
    this.yo.clear();
    this.ys.clear();
    this.yt.clear();
  }
  
  private void u(boolean paramBoolean)
  {
    if (this.yx != paramBoolean)
    {
      this.yx = paramBoolean;
      if (paramBoolean) {
        this.mHandler.postDelayed(this.yw, yl);
      }
    }
    else
    {
      return;
    }
    this.mHandler.removeCallbacks(this.yw);
  }
  
  public final void P(String paramString)
  {
    this.xB.b("message received: %s", new Object[] { paramString });
    String str;
    long l;
    try
    {
      Object localObject = new JSONObject(paramString);
      str = ((JSONObject)localObject).getString("type");
      l = ((JSONObject)localObject).optLong("requestId", -1L);
      if (str.equals("MEDIA_STATUS"))
      {
        localObject = ((JSONObject)localObject).getJSONArray("status");
        if (((JSONArray)localObject).length() > 0)
        {
          a(l, ((JSONArray)localObject).getJSONObject(0));
          return;
        }
        this.yn = null;
        onStatusUpdated();
        onMetadataUpdated();
        this.yv.c(l, 0);
        return;
      }
    }
    catch (JSONException localJSONException)
    {
      this.xB.d("Message is malformed (%s); ignoring: %s", new Object[] { localJSONException.getMessage(), paramString });
      return;
    }
    JSONObject localJSONObject;
    if (str.equals("INVALID_PLAYER_STATE"))
    {
      this.xB.d("received unexpected error: Invalid Player State.", new Object[0]);
      localJSONObject = localJSONException.optJSONObject("customData");
      this.yo.b(l, 1, localJSONObject);
      this.yp.b(l, 1, localJSONObject);
      this.yq.b(l, 1, localJSONObject);
      this.yr.b(l, 1, localJSONObject);
      this.ys.b(l, 1, localJSONObject);
      this.yt.b(l, 1, localJSONObject);
      this.yu.b(l, 1, localJSONObject);
      this.yv.b(l, 1, localJSONObject);
      return;
    }
    if (str.equals("LOAD_FAILED"))
    {
      localJSONObject = localJSONObject.optJSONObject("customData");
      this.yo.b(l, 1, localJSONObject);
      return;
    }
    if (str.equals("LOAD_CANCELLED"))
    {
      localJSONObject = localJSONObject.optJSONObject("customData");
      this.yo.b(l, 2, localJSONObject);
      return;
    }
    if (str.equals("INVALID_REQUEST"))
    {
      this.xB.d("received unexpected error: Invalid Request.", new Object[0]);
      localJSONObject = localJSONObject.optJSONObject("customData");
      this.yo.b(l, 1, localJSONObject);
      this.yp.b(l, 1, localJSONObject);
      this.yq.b(l, 1, localJSONObject);
      this.yr.b(l, 1, localJSONObject);
      this.ys.b(l, 1, localJSONObject);
      this.yt.b(l, 1, localJSONObject);
      this.yu.b(l, 1, localJSONObject);
      this.yv.b(l, 1, localJSONObject);
    }
  }
  
  public long a(dx paramdx)
    throws IOException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = cW();
    this.yv.a(l, paramdx);
    u(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "GET_STATUS");
      if (this.yn != null) {
        localJSONObject.put("mediaSessionId", this.yn.cU());
      }
      a(localJSONObject.toString(), l, null);
      return l;
    }
    catch (JSONException paramdx)
    {
      for (;;) {}
    }
  }
  
  public long a(dx paramdx, double paramDouble, JSONObject paramJSONObject)
    throws IOException, IllegalStateException, IllegalArgumentException
  {
    if ((Double.isInfinite(paramDouble)) || (Double.isNaN(paramDouble))) {
      throw new IllegalArgumentException("Volume cannot be " + paramDouble);
    }
    JSONObject localJSONObject = new JSONObject();
    long l = cW();
    this.yt.a(l, paramdx);
    u(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "SET_VOLUME");
      localJSONObject.put("mediaSessionId", cU());
      paramdx = new JSONObject();
      paramdx.put("level", paramDouble);
      localJSONObject.put("volume", paramdx);
      if (paramJSONObject != null) {
        localJSONObject.put("customData", paramJSONObject);
      }
    }
    catch (JSONException paramdx)
    {
      for (;;) {}
    }
    a(localJSONObject.toString(), l, null);
    return l;
  }
  
  public long a(dx paramdx, long paramLong, int paramInt, JSONObject paramJSONObject)
    throws IOException, IllegalStateException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = cW();
    this.ys.a(l, paramdx);
    u(true);
    for (;;)
    {
      try
      {
        localJSONObject.put("requestId", l);
        localJSONObject.put("type", "SEEK");
        localJSONObject.put("mediaSessionId", cU());
        localJSONObject.put("currentTime", dr.l(paramLong));
        if (paramInt != 1) {
          continue;
        }
        localJSONObject.put("resumeState", "PLAYBACK_START");
        if (paramJSONObject != null) {
          localJSONObject.put("customData", paramJSONObject);
        }
      }
      catch (JSONException paramdx)
      {
        continue;
      }
      a(localJSONObject.toString(), l, null);
      return l;
      if (paramInt == 2) {
        localJSONObject.put("resumeState", "PLAYBACK_PAUSE");
      }
    }
  }
  
  public long a(dx paramdx, MediaInfo paramMediaInfo, boolean paramBoolean, long paramLong, JSONObject paramJSONObject)
    throws IOException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = cW();
    this.yo.a(l, paramdx);
    u(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "LOAD");
      localJSONObject.put("media", paramMediaInfo.cT());
      localJSONObject.put("autoplay", paramBoolean);
      localJSONObject.put("currentTime", dr.l(paramLong));
      if (paramJSONObject != null) {
        localJSONObject.put("customData", paramJSONObject);
      }
    }
    catch (JSONException paramdx)
    {
      for (;;) {}
    }
    a(localJSONObject.toString(), l, null);
    return l;
  }
  
  public long a(dx paramdx, JSONObject paramJSONObject)
    throws IOException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = cW();
    this.yp.a(l, paramdx);
    u(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "PAUSE");
      localJSONObject.put("mediaSessionId", cU());
      if (paramJSONObject != null) {
        localJSONObject.put("customData", paramJSONObject);
      }
    }
    catch (JSONException paramdx)
    {
      for (;;) {}
    }
    a(localJSONObject.toString(), l, null);
    return l;
  }
  
  public long a(dx paramdx, boolean paramBoolean, JSONObject paramJSONObject)
    throws IOException, IllegalStateException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = cW();
    this.yu.a(l, paramdx);
    u(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "SET_VOLUME");
      localJSONObject.put("mediaSessionId", cU());
      paramdx = new JSONObject();
      paramdx.put("muted", paramBoolean);
      localJSONObject.put("volume", paramdx);
      if (paramJSONObject != null) {
        localJSONObject.put("customData", paramJSONObject);
      }
    }
    catch (JSONException paramdx)
    {
      for (;;) {}
    }
    a(localJSONObject.toString(), l, null);
    return l;
  }
  
  public void a(long paramLong, int paramInt)
  {
    this.yo.c(paramLong, paramInt);
    this.yp.c(paramLong, paramInt);
    this.yq.c(paramLong, paramInt);
    this.yr.c(paramLong, paramInt);
    this.ys.c(paramLong, paramInt);
    this.yt.c(paramLong, paramInt);
    this.yu.c(paramLong, paramInt);
    this.yv.c(paramLong, paramInt);
  }
  
  public long b(dx paramdx, JSONObject paramJSONObject)
    throws IOException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = cW();
    this.yr.a(l, paramdx);
    u(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "STOP");
      localJSONObject.put("mediaSessionId", cU());
      if (paramJSONObject != null) {
        localJSONObject.put("customData", paramJSONObject);
      }
    }
    catch (JSONException paramdx)
    {
      for (;;) {}
    }
    a(localJSONObject.toString(), l, null);
    return l;
  }
  
  public long c(dx paramdx, JSONObject paramJSONObject)
    throws IOException, IllegalStateException
  {
    JSONObject localJSONObject = new JSONObject();
    long l = cW();
    this.yq.a(l, paramdx);
    u(true);
    try
    {
      localJSONObject.put("requestId", l);
      localJSONObject.put("type", "PLAY");
      localJSONObject.put("mediaSessionId", cU());
      if (paramJSONObject != null) {
        localJSONObject.put("customData", paramJSONObject);
      }
    }
    catch (JSONException paramdx)
    {
      for (;;) {}
    }
    a(localJSONObject.toString(), l, null);
    return l;
  }
  
  public long cU()
    throws IllegalStateException
  {
    if (this.yn == null) {
      throw new IllegalStateException("No current media session");
    }
    return this.yn.cU();
  }
  
  public void cX()
  {
    dj();
  }
  
  public long getApproximateStreamPosition()
  {
    MediaInfo localMediaInfo = getMediaInfo();
    if (localMediaInfo == null) {}
    while (this.ym == 0L) {
      return 0L;
    }
    double d = this.yn.getPlaybackRate();
    long l3 = this.yn.getStreamPosition();
    int i = this.yn.getPlayerState();
    if ((d == 0.0D) || (i != 2)) {
      return l3;
    }
    long l1 = SystemClock.elapsedRealtime() - this.ym;
    if (l1 < 0L) {
      l1 = 0L;
    }
    for (;;)
    {
      if (l1 == 0L) {
        return l3;
      }
      long l2 = localMediaInfo.getStreamDuration();
      l1 = l3 + (l1 * d);
      if (l1 > l2) {
        l1 = l2;
      }
      for (;;)
      {
        return l1;
        if (l1 < 0L) {
          l1 = 0L;
        }
      }
    }
  }
  
  public MediaInfo getMediaInfo()
  {
    if (this.yn == null) {
      return null;
    }
    return this.yn.getMediaInfo();
  }
  
  public MediaStatus getMediaStatus()
  {
    return this.yn;
  }
  
  public long getStreamDuration()
  {
    MediaInfo localMediaInfo = getMediaInfo();
    if (localMediaInfo != null) {
      return localMediaInfo.getStreamDuration();
    }
    return 0L;
  }
  
  protected void onMetadataUpdated() {}
  
  protected void onStatusUpdated() {}
  
  private class a
    implements Runnable
  {
    private a() {}
    
    public void run()
    {
      boolean bool = false;
      dv.a(dv.this, false);
      long l = SystemClock.elapsedRealtime();
      dv.a(dv.this).d(l, 3);
      dv.b(dv.this).d(l, 3);
      dv.c(dv.this).d(l, 3);
      dv.d(dv.this).d(l, 3);
      dv.e(dv.this).d(l, 3);
      dv.f(dv.this).d(l, 3);
      dv.g(dv.this).d(l, 3);
      dv.h(dv.this).d(l, 3);
      for (;;)
      {
        synchronized (dy.yD)
        {
          if ((!dv.a(dv.this).dl()) && (!dv.e(dv.this).dl()) && (!dv.f(dv.this).dl()) && (!dv.g(dv.this).dl()) && (!dv.h(dv.this).dl()))
          {
            dv.b(dv.this, bool);
            return;
          }
        }
        bool = true;
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\dv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */