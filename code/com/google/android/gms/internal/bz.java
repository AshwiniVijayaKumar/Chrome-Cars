package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import java.util.List;
import org.json.JSONException;

public final class bz
  extends ct
  implements ca.a, de.a
{
  private final bf kH;
  private final Context mContext;
  private final Object mg = new Object();
  private ay mh;
  private final by.a nM;
  private final Object nN = new Object();
  private final cd.a nO;
  private final l nP;
  private ct nQ;
  private cf nR;
  private boolean nS = false;
  private aw nT;
  private bc nU;
  private final dd ng;
  
  public bz(Context paramContext, cd.a parama, l paraml, dd paramdd, bf parambf, by.a parama1)
  {
    this.kH = parambf;
    this.nM = parama1;
    this.ng = paramdd;
    this.mContext = paramContext;
    this.nO = parama;
    this.nP = paraml;
  }
  
  private ab a(cd paramcd)
    throws bz.a
  {
    if (this.nR.on == null) {
      throw new a("The ad response must specify one of the supported ad sizes.", 0);
    }
    Object localObject = this.nR.on.split("x");
    if (localObject.length != 2) {
      throw new a("Could not parse the ad size from the ad response: " + this.nR.on, 0);
    }
    for (;;)
    {
      int i;
      ab localab;
      try
      {
        int m = Integer.parseInt(localObject[0]);
        int n = Integer.parseInt(localObject[1]);
        localObject = paramcd.kQ.lp;
        int i1 = localObject.length;
        i = 0;
        if (i >= i1) {
          break;
        }
        localab = localObject[i];
        float f = this.mContext.getResources().getDisplayMetrics().density;
        if (localab.width == -1)
        {
          j = (int)(localab.widthPixels / f);
          if (localab.height != -2) {
            break label253;
          }
          k = (int)(localab.heightPixels / f);
          if ((m != j) || (n != k)) {
            break label263;
          }
          return new ab(localab, paramcd.kQ.lp);
        }
      }
      catch (NumberFormatException paramcd)
      {
        throw new a("Could not parse the ad size from the ad response: " + this.nR.on, 0);
      }
      int j = localab.width;
      continue;
      label253:
      int k = localab.height;
      continue;
      label263:
      i += 1;
    }
    throw new a("The ad size from the ad response was not one of the requested sizes: " + this.nR.on, 0);
  }
  
  private void a(cd paramcd, long paramLong)
    throws bz.a
  {
    synchronized (this.nN)
    {
      this.nT = new aw(this.mContext, paramcd, this.kH, this.mh);
      this.nU = this.nT.a(paramLong, 60000L);
      switch (this.nU.mL)
      {
      default: 
        throw new a("Unexpected mediation result: " + this.nU.mL, 0);
      }
    }
    throw new a("No fill from any mediation ad networks.", 3);
  }
  
  private void aC()
    throws bz.a
  {
    if (this.nR.errorCode == -3) {}
    do
    {
      return;
      if (TextUtils.isEmpty(this.nR.oi)) {
        throw new a("No fill from ad server.", 3);
      }
    } while (!this.nR.ok);
    try
    {
      this.mh = new ay(this.nR.oi);
      return;
    }
    catch (JSONException localJSONException)
    {
      throw new a("Could not parse mediation config: " + this.nR.oi, 0);
    }
  }
  
  private void b(long paramLong)
    throws bz.a
  {
    cz.pT.post(new Runnable()
    {
      public void run()
      {
        for (;;)
        {
          synchronized (bz.a(bz.this))
          {
            if (bz.c(bz.this).errorCode != -2) {
              return;
            }
            bz.d(bz.this).bb().a(bz.this);
            if (bz.c(bz.this).errorCode == -3)
            {
              da.v("Loading URL in WebView: " + bz.c(bz.this).nw);
              bz.d(bz.this).loadUrl(bz.c(bz.this).nw);
              return;
            }
          }
          da.v("Loading HTML in WebView.");
          bz.d(bz.this).loadDataWithBaseURL(cv.p(bz.c(bz.this).nw), bz.c(bz.this).oi, "text/html", "UTF-8", null);
        }
      }
    });
    d(paramLong);
  }
  
  private void c(long paramLong)
    throws bz.a
  {
    do
    {
      if (!e(paramLong)) {
        throw new a("Timed out waiting for ad response.", 2);
      }
    } while (this.nR == null);
    synchronized (this.nN)
    {
      this.nQ = null;
      if ((this.nR.errorCode != -2) && (this.nR.errorCode != -3)) {
        throw new a("There was a problem getting an ad response. ErrorCode: " + this.nR.errorCode, this.nR.errorCode);
      }
    }
  }
  
  private void d(long paramLong)
    throws bz.a
  {
    do
    {
      if (!e(paramLong)) {
        throw new a("Timed out waiting for WebView to finish loading.", 2);
      }
    } while (!this.nS);
  }
  
  private boolean e(long paramLong)
    throws bz.a
  {
    paramLong = 60000L - (SystemClock.elapsedRealtime() - paramLong);
    if (paramLong <= 0L) {
      return false;
    }
    try
    {
      this.mg.wait(paramLong);
      return true;
    }
    catch (InterruptedException localInterruptedException)
    {
      throw new a("Ad request cancelled.", -1);
    }
  }
  
  public void a(cf paramcf)
  {
    synchronized (this.mg)
    {
      da.s("Received ad response.");
      this.nR = paramcf;
      this.mg.notify();
      return;
    }
  }
  
  public void a(dd arg1)
  {
    synchronized (this.mg)
    {
      da.s("WebView finished loading.");
      this.nS = true;
      this.mg.notify();
      return;
    }
  }
  
  public void aB()
  {
    for (;;)
    {
      long l2;
      synchronized (this.mg)
      {
        da.s("AdLoaderBackgroundTask started.");
        String str1 = this.nP.y().a(this.mContext);
        localObject5 = new cd(this.nO, str1);
        str1 = null;
        localbg = null;
        int i = -2;
        l2 = -1L;
        localObject4 = localbg;
        l1 = l2;
        long l3;
        try
        {
          l3 = SystemClock.elapsedRealtime();
          localObject4 = localbg;
          l1 = l2;
          localObject8 = ca.a(this.mContext, (cd)localObject5, this);
          localObject4 = localbg;
          l1 = l2;
          localObject6 = this.nN;
          localObject4 = localbg;
          l1 = l2;
          try
          {
            this.nQ = ((ct)localObject8);
            if (this.nQ != null) {
              continue;
            }
            throw new a("Could not start the ad request service.", 0);
          }
          finally
          {
            localObject4 = localbg;
            l1 = l2;
          }
          if (i != -1) {
            break label569;
          }
        }
        catch (a locala)
        {
          i = locala.getErrorCode();
          if (i == 3) {}
        }
        da.u(locala.getMessage());
        this.nR = new cf(i);
        cz.pT.post(new Runnable()
        {
          public void run()
          {
            bz.this.onStop();
          }
        });
        final Object localObject2 = localObject4;
        Object localObject8 = ((cd)localObject5).oc;
        dd localdd = this.ng;
        List localList1 = this.nR.mt;
        List localList2 = this.nR.mu;
        List localList3 = this.nR.om;
        int j = this.nR.orientation;
        l2 = this.nR.mx;
        String str2 = ((cd)localObject5).of;
        boolean bool = this.nR.ok;
        if (this.nU == null) {
          break label580;
        }
        localObject4 = this.nU.mM;
        if (this.nU == null) {
          break label586;
        }
        localbg = this.nU.mN;
        if (this.nU == null) {
          break label592;
        }
        localObject5 = this.nU.mO;
        ay localay = this.mh;
        if (this.nU == null) {
          break label598;
        }
        localObject6 = this.nU.mP;
        localObject2 = new cn((z)localObject8, localdd, localList1, i, localList2, localList3, j, l2, str2, bool, (ax)localObject4, localbg, (String)localObject5, localay, (ba)localObject6, this.nR.ol, (ab)localObject2, this.nR.oj, l1, this.nR.oo);
        cz.pT.post(new Runnable()
        {
          public void run()
          {
            synchronized (bz.a(bz.this))
            {
              bz.b(bz.this).a(localObject2);
              return;
            }
          }
        });
        return;
        localObject4 = localbg;
        l1 = l2;
        c(l3);
        localObject4 = localbg;
        l1 = l2;
        l2 = SystemClock.elapsedRealtime();
        localObject4 = localbg;
        l1 = l2;
        aC();
        localObject4 = localbg;
        l1 = l2;
        if (((cd)localObject5).kQ.lp != null)
        {
          localObject4 = localbg;
          l1 = l2;
          localObject2 = a((cd)localObject5);
        }
        localObject4 = localObject2;
        l1 = l2;
        if (this.nR.ok)
        {
          localObject4 = localObject2;
          l1 = l2;
          a((cd)localObject5, l3);
        }
        else
        {
          localObject4 = localObject2;
          l1 = l2;
          b(l3);
        }
      }
      label569:
      da.w(((a)localObject3).getMessage());
      continue;
      label580:
      Object localObject4 = null;
      continue;
      label586:
      bg localbg = null;
      continue;
      label592:
      Object localObject5 = null;
      continue;
      label598:
      Object localObject6 = null;
      continue;
      long l1 = l2;
    }
  }
  
  public void onStop()
  {
    synchronized (this.nN)
    {
      if (this.nQ != null) {
        this.nQ.cancel();
      }
      this.ng.stopLoading();
      cv.a(this.ng);
      if (this.nT != null) {
        this.nT.cancel();
      }
      return;
    }
  }
  
  private static final class a
    extends Exception
  {
    private final int nX;
    
    public a(String paramString, int paramInt)
    {
      super();
      this.nX = paramInt;
    }
    
    public int getErrorCode()
    {
      return this.nX;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\bz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */