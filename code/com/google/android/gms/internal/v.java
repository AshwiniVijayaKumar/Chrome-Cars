package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.ViewSwitcher;
import com.google.android.gms.dynamic.b;
import com.google.android.gms.dynamic.c;
import java.util.HashSet;

public final class v
  extends ag.a
  implements ap, az, br, bu, by.a, u
{
  private final bf kH;
  private final a kI;
  private final w kJ;
  
  public v(Context paramContext, ab paramab, String paramString, bf parambf, db paramdb)
  {
    this.kI = new a(paramContext, paramab, paramString, paramdb);
    this.kH = parambf;
    this.kJ = new w(this);
    da.u("Use AdRequest.Builder.addTestDevice(\"" + cz.l(paramContext) + "\") to get test ads on this device.");
    cv.i(paramContext);
  }
  
  private void a(int paramInt)
  {
    da.w("Failed to load ad: " + paramInt);
    if (this.kI.kO != null) {}
    try
    {
      this.kI.kO.onAdFailedToLoad(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not call AdListener.onAdFailedToLoad().", localRemoteException);
    }
  }
  
  private void aa()
  {
    da.u("Ad closing.");
    if (this.kI.kO != null) {}
    try
    {
      this.kI.kO.onAdClosed();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not call AdListener.onAdClosed().", localRemoteException);
    }
  }
  
  private void ab()
  {
    da.u("Ad leaving application.");
    if (this.kI.kO != null) {}
    try
    {
      this.kI.kO.onAdLeftApplication();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not call AdListener.onAdLeftApplication().", localRemoteException);
    }
  }
  
  private void ac()
  {
    da.u("Ad opening.");
    if (this.kI.kO != null) {}
    try
    {
      this.kI.kO.onAdOpened();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not call AdListener.onAdOpened().", localRemoteException);
    }
  }
  
  private void ad()
  {
    da.u("Ad finished loading.");
    if (this.kI.kO != null) {}
    try
    {
      this.kI.kO.onAdLoaded();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      da.b("Could not call AdListener.onAdLoaded().", localRemoteException);
    }
  }
  
  private boolean ae()
  {
    boolean bool = true;
    if (!cv.a(this.kI.kL.getPackageManager(), this.kI.kL.getPackageName(), "android.permission.INTERNET"))
    {
      if (!this.kI.kQ.lo) {
        cz.a(this.kI.kK, this.kI.kQ, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
      }
      bool = false;
    }
    if (!cv.h(this.kI.kL))
    {
      if (!this.kI.kQ.lo) {
        cz.a(this.kI.kK, this.kI.kQ, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
      }
      bool = false;
    }
    if ((!bool) && (!this.kI.kQ.lo)) {
      this.kI.kK.setVisibility(0);
    }
    return bool;
  }
  
  private void af()
  {
    if (this.kI.kR == null) {
      da.w("Ad state was null when trying to ping click URLs.");
    }
    do
    {
      return;
      da.s("Pinging click URLs.");
      this.kI.kS.aK();
      if (this.kI.kR.mt != null) {
        cv.a(this.kI.kL, this.kI.kN.pU, this.kI.kR.mt);
      }
    } while ((this.kI.kR.pf == null) || (this.kI.kR.pf.mt == null));
    bd.a(this.kI.kL, this.kI.kN.pU, this.kI.kR, this.kI.adUnitId, false, this.kI.kR.pf.mt);
  }
  
  private void ag()
  {
    if (this.kI.kR != null)
    {
      this.kI.kR.nu.destroy();
      this.kI.kR = null;
    }
  }
  
  private void b(View paramView)
  {
    ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(-2, -2);
    this.kI.kK.addView(paramView, localLayoutParams);
  }
  
  private void b(boolean paramBoolean)
  {
    if (this.kI.kR == null) {
      da.w("Ad state was null when trying to ping impression URLs.");
    }
    do
    {
      return;
      da.s("Pinging Impression URLs.");
      this.kI.kS.aJ();
      if (this.kI.kR.mu != null) {
        cv.a(this.kI.kL, this.kI.kN.pU, this.kI.kR.mu);
      }
      if ((this.kI.kR.pf != null) && (this.kI.kR.pf.mu != null)) {
        bd.a(this.kI.kL, this.kI.kN.pU, this.kI.kR, this.kI.adUnitId, paramBoolean, this.kI.kR.pf.mu);
      }
    } while ((this.kI.kR.mM == null) || (this.kI.kR.mM.mp == null));
    bd.a(this.kI.kL, this.kI.kN.pU, this.kI.kR, this.kI.adUnitId, paramBoolean, this.kI.kR.mM.mp);
  }
  
  /* Error */
  private boolean b(cn paramcn)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 292	com/google/android/gms/internal/cn:ok	Z
    //   4: ifeq +184 -> 188
    //   7: aload_1
    //   8: getfield 296	com/google/android/gms/internal/cn:mN	Lcom/google/android/gms/internal/bg;
    //   11: invokeinterface 302 1 0
    //   16: invokestatic 307	com/google/android/gms/dynamic/c:b	(Lcom/google/android/gms/dynamic/b;)Ljava/lang/Object;
    //   19: checkcast 309	android/view/View
    //   22: astore_1
    //   23: aload_0
    //   24: getfield 35	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   27: getfield 170	com/google/android/gms/internal/v$a:kK	Landroid/widget/ViewSwitcher;
    //   30: invokevirtual 313	android/widget/ViewSwitcher:getNextView	()Landroid/view/View;
    //   33: astore_2
    //   34: aload_2
    //   35: ifnull +14 -> 49
    //   38: aload_0
    //   39: getfield 35	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   42: getfield 170	com/google/android/gms/internal/v$a:kK	Landroid/widget/ViewSwitcher;
    //   45: aload_2
    //   46: invokevirtual 316	android/widget/ViewSwitcher:removeView	(Landroid/view/View;)V
    //   49: aload_0
    //   50: aload_1
    //   51: invokespecial 318	com/google/android/gms/internal/v:b	(Landroid/view/View;)V
    //   54: aload_0
    //   55: getfield 35	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   58: getfield 170	com/google/android/gms/internal/v$a:kK	Landroid/widget/ViewSwitcher;
    //   61: invokevirtual 322	android/widget/ViewSwitcher:getChildCount	()I
    //   64: iconst_1
    //   65: if_icmple +13 -> 78
    //   68: aload_0
    //   69: getfield 35	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   72: getfield 170	com/google/android/gms/internal/v$a:kK	Landroid/widget/ViewSwitcher;
    //   75: invokevirtual 325	android/widget/ViewSwitcher:showNext	()V
    //   78: aload_0
    //   79: getfield 35	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   82: getfield 195	com/google/android/gms/internal/v$a:kR	Lcom/google/android/gms/internal/cn;
    //   85: ifnull +70 -> 155
    //   88: aload_0
    //   89: getfield 35	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   92: getfield 170	com/google/android/gms/internal/v$a:kK	Landroid/widget/ViewSwitcher;
    //   95: invokevirtual 313	android/widget/ViewSwitcher:getNextView	()Landroid/view/View;
    //   98: astore_1
    //   99: aload_1
    //   100: instanceof 252
    //   103: ifeq +158 -> 261
    //   106: aload_1
    //   107: checkcast 252	com/google/android/gms/internal/dd
    //   110: aload_0
    //   111: getfield 35	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   114: getfield 142	com/google/android/gms/internal/v$a:kL	Landroid/content/Context;
    //   117: aload_0
    //   118: getfield 35	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   121: getfield 160	com/google/android/gms/internal/v$a:kQ	Lcom/google/android/gms/internal/ab;
    //   124: invokevirtual 328	com/google/android/gms/internal/dd:a	(Landroid/content/Context;Lcom/google/android/gms/internal/ab;)V
    //   127: aload_0
    //   128: getfield 35	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   131: getfield 195	com/google/android/gms/internal/v$a:kR	Lcom/google/android/gms/internal/cn;
    //   134: getfield 296	com/google/android/gms/internal/cn:mN	Lcom/google/android/gms/internal/bg;
    //   137: ifnull +18 -> 155
    //   140: aload_0
    //   141: getfield 35	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   144: getfield 195	com/google/android/gms/internal/v$a:kR	Lcom/google/android/gms/internal/cn;
    //   147: getfield 296	com/google/android/gms/internal/cn:mN	Lcom/google/android/gms/internal/bg;
    //   150: invokeinterface 329 1 0
    //   155: aload_0
    //   156: getfield 35	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   159: getfield 170	com/google/android/gms/internal/v$a:kK	Landroid/widget/ViewSwitcher;
    //   162: iconst_0
    //   163: invokevirtual 190	android/widget/ViewSwitcher:setVisibility	(I)V
    //   166: iconst_1
    //   167: ireturn
    //   168: astore_1
    //   169: ldc_w 331
    //   172: aload_1
    //   173: invokestatic 104	com/google/android/gms/internal/da:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   176: iconst_0
    //   177: ireturn
    //   178: astore_1
    //   179: ldc_w 333
    //   182: aload_1
    //   183: invokestatic 104	com/google/android/gms/internal/da:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   186: iconst_0
    //   187: ireturn
    //   188: aload_1
    //   189: getfield 336	com/google/android/gms/internal/cn:pg	Lcom/google/android/gms/internal/ab;
    //   192: ifnull -138 -> 54
    //   195: aload_1
    //   196: getfield 250	com/google/android/gms/internal/cn:nu	Lcom/google/android/gms/internal/dd;
    //   199: aload_1
    //   200: getfield 336	com/google/android/gms/internal/cn:pg	Lcom/google/android/gms/internal/ab;
    //   203: invokevirtual 339	com/google/android/gms/internal/dd:a	(Lcom/google/android/gms/internal/ab;)V
    //   206: aload_0
    //   207: getfield 35	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   210: getfield 170	com/google/android/gms/internal/v$a:kK	Landroid/widget/ViewSwitcher;
    //   213: invokevirtual 342	android/widget/ViewSwitcher:removeAllViews	()V
    //   216: aload_0
    //   217: getfield 35	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   220: getfield 170	com/google/android/gms/internal/v$a:kK	Landroid/widget/ViewSwitcher;
    //   223: aload_1
    //   224: getfield 336	com/google/android/gms/internal/cn:pg	Lcom/google/android/gms/internal/ab;
    //   227: getfield 346	com/google/android/gms/internal/ab:widthPixels	I
    //   230: invokevirtual 349	android/widget/ViewSwitcher:setMinimumWidth	(I)V
    //   233: aload_0
    //   234: getfield 35	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   237: getfield 170	com/google/android/gms/internal/v$a:kK	Landroid/widget/ViewSwitcher;
    //   240: aload_1
    //   241: getfield 336	com/google/android/gms/internal/cn:pg	Lcom/google/android/gms/internal/ab;
    //   244: getfield 352	com/google/android/gms/internal/ab:heightPixels	I
    //   247: invokevirtual 355	android/widget/ViewSwitcher:setMinimumHeight	(I)V
    //   250: aload_0
    //   251: aload_1
    //   252: getfield 250	com/google/android/gms/internal/cn:nu	Lcom/google/android/gms/internal/dd;
    //   255: invokespecial 318	com/google/android/gms/internal/v:b	(Landroid/view/View;)V
    //   258: goto -204 -> 54
    //   261: aload_1
    //   262: ifnull -135 -> 127
    //   265: aload_0
    //   266: getfield 35	com/google/android/gms/internal/v:kI	Lcom/google/android/gms/internal/v$a;
    //   269: getfield 170	com/google/android/gms/internal/v$a:kK	Landroid/widget/ViewSwitcher;
    //   272: aload_1
    //   273: invokevirtual 316	android/widget/ViewSwitcher:removeView	(Landroid/view/View;)V
    //   276: goto -149 -> 127
    //   279: astore_1
    //   280: ldc_w 357
    //   283: invokestatic 89	com/google/android/gms/internal/da:w	(Ljava/lang/String;)V
    //   286: goto -131 -> 155
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	289	0	this	v
    //   0	289	1	paramcn	cn
    //   33	13	2	localView	View
    // Exception table:
    //   from	to	target	type
    //   7	23	168	android/os/RemoteException
    //   49	54	178	java/lang/Throwable
    //   140	155	279	android/os/RemoteException
  }
  
  private cd.a c(z paramz)
  {
    ApplicationInfo localApplicationInfo = this.kI.kL.getApplicationInfo();
    try
    {
      PackageInfo localPackageInfo = this.kI.kL.getPackageManager().getPackageInfo(localApplicationInfo.packageName, 0);
      if ((!this.kI.kQ.lo) && (this.kI.kK.getParent() != null))
      {
        localObject2 = new int[2];
        this.kI.kK.getLocationOnScreen((int[])localObject2);
        int j = localObject2[0];
        int k = localObject2[1];
        localObject2 = this.kI.kL.getResources().getDisplayMetrics();
        int m = this.kI.kK.getWidth();
        int n = this.kI.kK.getHeight();
        if ((this.kI.kK.isShown()) && (j + m > 0) && (k + n > 0) && (j <= ((DisplayMetrics)localObject2).widthPixels) && (k <= ((DisplayMetrics)localObject2).heightPixels))
        {
          i = 1;
          localObject2 = new Bundle(5);
          ((Bundle)localObject2).putInt("x", j);
          ((Bundle)localObject2).putInt("y", k);
          ((Bundle)localObject2).putInt("width", m);
          ((Bundle)localObject2).putInt("height", n);
          ((Bundle)localObject2).putInt("visible", i);
          String str = cp.aP();
          this.kI.kS = new co(str, this.kI.adUnitId);
          this.kI.kS.f(paramz);
          Bundle localBundle = cp.a(this.kI, str, this.kI.kL);
          return new cd.a((Bundle)localObject2, paramz, this.kI.kQ, this.kI.adUnitId, localApplicationInfo, localPackageInfo, str, cp.pu, this.kI.kN, localBundle);
        }
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        Object localObject1 = null;
        continue;
        int i = 0;
        continue;
        Object localObject2 = null;
      }
    }
  }
  
  public void O()
  {
    af();
  }
  
  public b P()
  {
    er.ac("getAdFrame must be called on the main UI thread.");
    return c.h(this.kI.kK);
  }
  
  public ab Q()
  {
    er.ac("getAdSize must be called on the main UI thread.");
    return this.kI.kQ;
  }
  
  public void R()
  {
    ab();
  }
  
  public void S()
  {
    if (this.kI.kQ.lo) {
      ag();
    }
    aa();
    this.kI.kS.aL();
  }
  
  public void T()
  {
    if (this.kI.kQ.lo) {
      b(false);
    }
    ac();
  }
  
  public void U()
  {
    O();
  }
  
  public void V()
  {
    S();
  }
  
  public void W()
  {
    R();
  }
  
  public void X()
  {
    T();
  }
  
  public void Y()
  {
    if (this.kI.kR != null) {
      da.w("Mediation adapter " + this.kI.kR.mO + " refreshed, but mediation adapters should never refresh.");
    }
    b(true);
    ad();
  }
  
  public void Z()
  {
    er.ac("recordManualImpression must be called on the main UI thread.");
    if (this.kI.kR == null) {
      da.w("Ad state was null when trying to ping manual tracking URLs.");
    }
    do
    {
      return;
      da.s("Pinging manual tracking URLs.");
    } while (this.kI.kR.om == null);
    cv.a(this.kI.kL, this.kI.kN.pU, this.kI.kR.om);
  }
  
  public void a(ab paramab)
  {
    er.ac("setAdSize must be called on the main UI thread.");
    this.kI.kQ = paramab;
    if (this.kI.kR != null) {
      this.kI.kR.nu.a(paramab);
    }
    if (this.kI.kK.getChildCount() > 1) {
      this.kI.kK.removeView(this.kI.kK.getNextView());
    }
    this.kI.kK.setMinimumWidth(paramab.widthPixels);
    this.kI.kK.setMinimumHeight(paramab.heightPixels);
    this.kI.kK.requestLayout();
  }
  
  public void a(af paramaf)
  {
    er.ac("setAdListener must be called on the main UI thread.");
    this.kI.kO = paramaf;
  }
  
  public void a(ai paramai)
  {
    er.ac("setAppEventListener must be called on the main UI thread.");
    this.kI.kT = paramai;
  }
  
  public void a(cn paramcn)
  {
    int j = 0;
    this.kI.kP = null;
    if ((paramcn.errorCode != -2) && (paramcn.errorCode != 3)) {
      cp.a(this.kI);
    }
    if (paramcn.errorCode == -1) {
      return;
    }
    boolean bool;
    if (paramcn.oc.extras != null)
    {
      bool = paramcn.oc.extras.getBoolean("_noRefresh", false);
      if (!this.kI.kQ.lo) {
        break label180;
      }
      cv.a(paramcn.nu);
    }
    for (;;)
    {
      if ((paramcn.errorCode == 3) && (paramcn.pf != null) && (paramcn.pf.mv != null))
      {
        da.s("Pinging no fill URLs.");
        bd.a(this.kI.kL, this.kI.kN.pU, paramcn, this.kI.adUnitId, false, paramcn.pf.mv);
      }
      if (paramcn.errorCode == -2) {
        break label281;
      }
      a(paramcn.errorCode);
      return;
      bool = false;
      break;
      label180:
      if (!bool) {
        if (paramcn.mx > 0L) {
          this.kJ.a(paramcn.oc, paramcn.mx);
        } else if ((paramcn.pf != null) && (paramcn.pf.mx > 0L)) {
          this.kJ.a(paramcn.oc, paramcn.pf.mx);
        } else if ((!paramcn.ok) && (paramcn.errorCode == 2)) {
          this.kJ.d(paramcn.oc);
        }
      }
    }
    label281:
    if ((!this.kI.kQ.lo) && (!b(paramcn)))
    {
      a(0);
      return;
    }
    if ((this.kI.kR != null) && (this.kI.kR.mP != null)) {
      this.kI.kR.mP.a(null);
    }
    if (paramcn.mP != null) {
      paramcn.mP.a(this);
    }
    this.kI.kR = paramcn;
    if (paramcn.pg != null) {
      this.kI.kQ = paramcn.pg;
    }
    this.kI.kS.g(paramcn.ph);
    this.kI.kS.h(paramcn.pi);
    this.kI.kS.k(this.kI.kQ.lo);
    this.kI.kS.l(paramcn.ok);
    if (!this.kI.kQ.lo) {
      b(false);
    }
    if (this.kI.kU == null) {
      this.kI.kU = new cr(this.kI.adUnitId);
    }
    int i;
    if (paramcn.pf != null)
    {
      i = paramcn.pf.my;
      j = paramcn.pf.mz;
    }
    for (;;)
    {
      this.kI.kU.a(i, j);
      ad();
      return;
      i = 0;
    }
  }
  
  public boolean a(z paramz)
  {
    er.ac("loadAd must be called on the main UI thread.");
    if (this.kI.kP != null) {
      da.w("An ad request is already in progress. Aborting.");
    }
    do
    {
      return false;
      if ((this.kI.kQ.lo) && (this.kI.kR != null))
      {
        da.w("An interstitial is already loading. Aborting.");
        return false;
      }
    } while (!ae());
    da.u("Starting ad request.");
    this.kJ.cancel();
    cd.a locala = c(paramz);
    if (this.kI.kQ.lo)
    {
      paramz = dd.a(this.kI.kL, this.kI.kQ, false, false, this.kI.kM, this.kI.kN);
      paramz.bb().a(this, null, this, this, true);
      this.kI.kP = by.a(this.kI.kL, locala, this.kI.kM, paramz, this.kH, this);
      return true;
    }
    paramz = this.kI.kK.getNextView();
    if ((paramz instanceof dd))
    {
      paramz = (dd)paramz;
      paramz.a(this.kI.kL, this.kI.kQ);
    }
    for (;;)
    {
      paramz.bb().a(this, this, this, this, false);
      break;
      if (paramz != null) {
        this.kI.kK.removeView(paramz);
      }
      dd localdd = dd.a(this.kI.kL, this.kI.kQ, false, false, this.kI.kM, this.kI.kN);
      paramz = localdd;
      if (this.kI.kQ.lp == null)
      {
        b(localdd);
        paramz = localdd;
      }
    }
  }
  
  public void b(z paramz)
  {
    ViewParent localViewParent = this.kI.kK.getParent();
    if (((localViewParent instanceof View)) && (((View)localViewParent).isShown()) && (cv.aS()))
    {
      a(paramz);
      return;
    }
    da.u("Ad is not visible. Not refreshing ad.");
    this.kJ.d(paramz);
  }
  
  public void destroy()
  {
    er.ac("destroy must be called on the main UI thread.");
    this.kI.kO = null;
    this.kI.kT = null;
    this.kJ.cancel();
    stopLoading();
    if (this.kI.kK != null) {
      this.kI.kK.removeAllViews();
    }
    if ((this.kI.kR != null) && (this.kI.kR.nu != null)) {
      this.kI.kR.nu.destroy();
    }
  }
  
  public boolean isReady()
  {
    er.ac("isLoaded must be called on the main UI thread.");
    return (this.kI.kP == null) && (this.kI.kR != null);
  }
  
  public void onAppEvent(String paramString1, String paramString2)
  {
    if (this.kI.kT != null) {}
    try
    {
      this.kI.kT.onAppEvent(paramString1, paramString2);
      return;
    }
    catch (RemoteException paramString1)
    {
      da.b("Could not call the AppEventListener.", paramString1);
    }
  }
  
  public void pause()
  {
    er.ac("pause must be called on the main UI thread.");
    if (this.kI.kR != null) {
      cv.a(this.kI.kR.nu);
    }
  }
  
  public void resume()
  {
    er.ac("resume must be called on the main UI thread.");
    if (this.kI.kR != null) {
      cv.b(this.kI.kR.nu);
    }
  }
  
  public void showInterstitial()
  {
    er.ac("showInterstitial must be called on the main UI thread.");
    if (!this.kI.kQ.lo)
    {
      da.w("Cannot call showInterstitial on a banner ad.");
      return;
    }
    if (this.kI.kR == null)
    {
      da.w("The interstitial has not loaded.");
      return;
    }
    if (this.kI.kR.nu.be())
    {
      da.w("The interstitial is already showing.");
      return;
    }
    this.kI.kR.nu.n(true);
    if (this.kI.kR.ok) {
      try
      {
        this.kI.kR.mN.showInterstitial();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        da.b("Could not show interstitial.", localRemoteException);
        ag();
        return;
      }
    }
    bq localbq = new bq(this, this, this, this.kI.kR.nu, this.kI.kR.orientation, this.kI.kN);
    bo.a(this.kI.kL, localbq);
  }
  
  public void stopLoading()
  {
    er.ac("stopLoading must be called on the main UI thread.");
    if (this.kI.kR != null)
    {
      this.kI.kR.nu.stopLoading();
      this.kI.kR = null;
    }
    if (this.kI.kP != null) {
      this.kI.kP.cancel();
    }
  }
  
  public static final class a
  {
    public final String adUnitId;
    public final ViewSwitcher kK;
    public final Context kL;
    public final l kM;
    public final db kN;
    public af kO;
    public ct kP;
    public ab kQ;
    public cn kR;
    public co kS;
    public ai kT;
    public cr kU = null;
    private HashSet<co> kV = null;
    
    public a(Context paramContext, ab paramab, String paramString, db paramdb)
    {
      if (paramab.lo) {
        this.kK = null;
      }
      for (;;)
      {
        this.kQ = paramab;
        this.adUnitId = paramString;
        this.kL = paramContext;
        this.kM = new l(k.a(paramdb.pU, paramContext));
        this.kN = paramdb;
        return;
        this.kK = new ViewSwitcher(paramContext);
        this.kK.setMinimumWidth(paramab.widthPixels);
        this.kK.setMinimumHeight(paramab.heightPixels);
        this.kK.setVisibility(4);
      }
    }
    
    public void a(HashSet<co> paramHashSet)
    {
      this.kV = paramHashSet;
    }
    
    public HashSet<co> ah()
    {
      return this.kV;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\v.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */