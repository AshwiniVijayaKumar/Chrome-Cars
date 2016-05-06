package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public final class bo
  extends bw.a
{
  private final Activity nd;
  private bq ne;
  private bs nf;
  private dd ng;
  private b nh;
  private bt ni;
  private FrameLayout nj;
  private WebChromeClient.CustomViewCallback nk;
  private boolean nl = false;
  private boolean nm = false;
  private RelativeLayout nn;
  
  public bo(Activity paramActivity)
  {
    this.nd = paramActivity;
  }
  
  private static RelativeLayout.LayoutParams a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(paramInt3, paramInt4);
    localLayoutParams.setMargins(paramInt1, paramInt2, 0, 0);
    localLayoutParams.addRule(10);
    localLayoutParams.addRule(9);
    return localLayoutParams;
  }
  
  public static void a(Context paramContext, bq parambq)
  {
    Intent localIntent = new Intent();
    localIntent.setClassName(paramContext, "com.google.android.gms.ads.AdActivity");
    localIntent.putExtra("com.google.android.gms.ads.internal.overlay.useClientJar", parambq.kN.pX);
    bq.a(localIntent, parambq);
    localIntent.addFlags(524288);
    paramContext.startActivity(localIntent);
  }
  
  private void as()
  {
    if ((!this.nd.isFinishing()) || (this.nm)) {}
    do
    {
      do
      {
        return;
        this.nm = true;
      } while (!this.nd.isFinishing());
      if (this.ng != null)
      {
        this.ng.aY();
        this.nn.removeView(this.ng);
        if (this.nh != null)
        {
          this.ng.n(false);
          this.nh.nq.addView(this.ng, this.nh.index, this.nh.np);
        }
      }
    } while ((this.ne == null) || (this.ne.nt == null));
    this.ne.nt.S();
  }
  
  private void h(boolean paramBoolean)
    throws bo.a
  {
    this.nd.requestWindowFeature(1);
    Window localWindow = this.nd.getWindow();
    localWindow.setFlags(1024, 1024);
    setRequestedOrientation(this.ne.orientation);
    if (Build.VERSION.SDK_INT >= 11)
    {
      da.s("Enabling hardware acceleration on the AdActivity window.");
      cw.a(localWindow);
    }
    this.nn = new RelativeLayout(this.nd);
    this.nn.setBackgroundColor(-16777216);
    this.nd.setContentView(this.nn);
    boolean bool = this.ne.nu.bb().bi();
    if (paramBoolean)
    {
      this.ng = dd.a(this.nd, this.ne.nu.Q(), true, bool, null, this.ne.kN);
      this.ng.bb().a(null, null, this.ne.nv, this.ne.nz, true);
      this.ng.bb().a(new de.a()
      {
        public void a(dd paramAnonymousdd)
        {
          paramAnonymousdd.aZ();
        }
      });
      if (this.ne.mZ != null) {
        this.ng.loadUrl(this.ne.mZ);
      }
    }
    for (;;)
    {
      this.ng.a(this);
      this.nn.addView(this.ng, -1, -1);
      if (!paramBoolean) {
        this.ng.aZ();
      }
      f(bool);
      return;
      if (this.ne.ny != null)
      {
        this.ng.loadDataWithBaseURL(this.ne.nw, this.ne.ny, "text/html", "UTF-8", null);
      }
      else
      {
        throw new a("No URL or HTML to display in ad overlay.");
        this.ng = this.ne.nu;
        this.ng.setContext(this.nd);
      }
    }
  }
  
  public void a(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
  {
    this.nj = new FrameLayout(this.nd);
    this.nj.setBackgroundColor(-16777216);
    this.nj.addView(paramView, -1, -1);
    this.nd.setContentView(this.nj);
    this.nk = paramCustomViewCallback;
  }
  
  public bs ap()
  {
    return this.nf;
  }
  
  public void aq()
  {
    if (this.ne != null) {
      setRequestedOrientation(this.ne.orientation);
    }
    if (this.nj != null)
    {
      this.nd.setContentView(this.nn);
      this.nj.removeAllViews();
      this.nj = null;
    }
    if (this.nk != null)
    {
      this.nk.onCustomViewHidden();
      this.nk = null;
    }
  }
  
  public void ar()
  {
    this.nn.removeView(this.ni);
    f(true);
  }
  
  public void b(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.nf != null) {
      this.nf.setLayoutParams(a(paramInt1, paramInt2, paramInt3, paramInt4));
    }
  }
  
  public void c(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.nf == null)
    {
      this.nf = new bs(this.nd, this.ng);
      this.nn.addView(this.nf, 0, a(paramInt1, paramInt2, paramInt3, paramInt4));
      this.ng.bb().o(false);
    }
  }
  
  public void close()
  {
    this.nd.finish();
  }
  
  public void f(boolean paramBoolean)
  {
    RelativeLayout.LayoutParams localLayoutParams;
    if (paramBoolean)
    {
      i = 50;
      this.ni = new bt(this.nd, i);
      localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams.addRule(10);
      if (!paramBoolean) {
        break label86;
      }
    }
    label86:
    for (int i = 11;; i = 9)
    {
      localLayoutParams.addRule(i);
      this.ni.g(this.ne.nx);
      this.nn.addView(this.ni, localLayoutParams);
      return;
      i = 32;
      break;
    }
  }
  
  public void g(boolean paramBoolean)
  {
    if (this.ni != null) {
      this.ni.g(paramBoolean);
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    boolean bool = false;
    if (paramBundle != null) {
      bool = paramBundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
    }
    this.nl = bool;
    try
    {
      this.ne = bq.a(this.nd.getIntent());
      if (this.ne != null) {
        break label68;
      }
      throw new a("Could not get info for ad overlay.");
    }
    catch (a paramBundle)
    {
      da.w(paramBundle.getMessage());
      this.nd.finish();
    }
    return;
    label68:
    if (paramBundle == null)
    {
      if (this.ne.nt != null) {
        this.ne.nt.T();
      }
      if ((this.ne.nA != 1) && (this.ne.ns != null)) {
        this.ne.ns.O();
      }
    }
    switch (this.ne.nA)
    {
    }
    for (;;)
    {
      throw new a("Could not determine ad overlay type.");
      h(false);
      return;
      this.nh = new b(this.ne.nu);
      h(false);
      return;
      h(true);
      return;
      if (this.nl)
      {
        this.nd.finish();
        return;
      }
      if (bl.a(this.nd, this.ne.nr, this.ne.nz)) {
        break;
      }
      this.nd.finish();
      return;
    }
  }
  
  public void onDestroy()
  {
    if (this.nf != null) {
      this.nf.destroy();
    }
    if (this.ng != null) {
      this.nn.removeView(this.ng);
    }
    as();
  }
  
  public void onPause()
  {
    if (this.nf != null) {
      this.nf.pause();
    }
    aq();
    if ((this.ng != null) && ((!this.nd.isFinishing()) || (this.nh == null))) {
      cv.a(this.ng);
    }
    as();
  }
  
  public void onRestart() {}
  
  public void onResume()
  {
    if ((this.ne != null) && (this.ne.nA == 4))
    {
      if (!this.nl) {
        break label47;
      }
      this.nd.finish();
    }
    for (;;)
    {
      if (this.ng != null) {
        cv.b(this.ng);
      }
      return;
      label47:
      this.nl = true;
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.nl);
  }
  
  public void onStart() {}
  
  public void onStop()
  {
    as();
  }
  
  public void setRequestedOrientation(int paramInt)
  {
    this.nd.setRequestedOrientation(paramInt);
  }
  
  private static final class a
    extends Exception
  {
    public a(String paramString)
    {
      super();
    }
  }
  
  private static final class b
  {
    public final int index;
    public final ViewGroup.LayoutParams np;
    public final ViewGroup nq;
    
    public b(dd paramdd)
      throws bo.a
    {
      this.np = paramdd.getLayoutParams();
      ViewParent localViewParent = paramdd.getParent();
      if ((localViewParent instanceof ViewGroup))
      {
        this.nq = ((ViewGroup)localViewParent);
        this.index = this.nq.indexOfChild(paramdd);
        this.nq.removeView(paramdd);
        paramdd.n(true);
        return;
      }
      throw new bo.a("Could not get the parent of the WebView for an overlay.");
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\bo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */