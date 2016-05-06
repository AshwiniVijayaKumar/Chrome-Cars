package com.google.android.gms.internal;

import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.net.UrlQuerySanitizer.ParameterValuePair;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class de
  extends WebViewClient
{
  private ap lV;
  private final Object mg = new Object();
  protected final dd ng;
  private final HashMap<String, ar> qf = new HashMap();
  private u qg;
  private br qh;
  private a qi;
  private boolean qj = false;
  private boolean qk;
  private bu ql;
  
  public de(dd paramdd, boolean paramBoolean)
  {
    this.ng = paramdd;
    this.qk = paramBoolean;
  }
  
  private void a(bq parambq)
  {
    bo.a(this.ng.getContext(), parambq);
  }
  
  private static boolean b(Uri paramUri)
  {
    paramUri = paramUri.getScheme();
    return ("http".equalsIgnoreCase(paramUri)) || ("https".equalsIgnoreCase(paramUri));
  }
  
  private void c(Uri paramUri)
  {
    String str = paramUri.getPath();
    ar localar = (ar)this.qf.get(str);
    if (localar != null)
    {
      HashMap localHashMap = new HashMap();
      Object localObject = new UrlQuerySanitizer();
      ((UrlQuerySanitizer)localObject).setAllowUnregisteredParamaters(true);
      ((UrlQuerySanitizer)localObject).setUnregisteredParameterValueSanitizer(UrlQuerySanitizer.getAllButNulLegal());
      ((UrlQuerySanitizer)localObject).parseUrl(paramUri.toString());
      paramUri = ((UrlQuerySanitizer)localObject).getParameterList().iterator();
      while (paramUri.hasNext())
      {
        localObject = (UrlQuerySanitizer.ParameterValuePair)paramUri.next();
        localHashMap.put(((UrlQuerySanitizer.ParameterValuePair)localObject).mParameter, ((UrlQuerySanitizer.ParameterValuePair)localObject).mValue);
      }
      if (da.n(2))
      {
        da.v("Received GMSG: " + str);
        paramUri = localHashMap.keySet().iterator();
        while (paramUri.hasNext())
        {
          str = (String)paramUri.next();
          da.v("  " + str + ": " + (String)localHashMap.get(str));
        }
      }
      localar.a(this.ng, localHashMap);
      return;
    }
    da.w("No GMSG handler found for GMSG: " + paramUri);
  }
  
  public final void a(bn parambn)
  {
    br localbr = null;
    boolean bool = this.ng.be();
    u localu;
    if ((bool) && (!this.ng.Q().lo))
    {
      localu = null;
      if (!bool) {
        break label69;
      }
    }
    for (;;)
    {
      a(new bq(parambn, localu, localbr, this.ql, this.ng.bd()));
      return;
      localu = this.qg;
      break;
      label69:
      localbr = this.qh;
    }
  }
  
  public final void a(a parama)
  {
    this.qi = parama;
  }
  
  public void a(u paramu, br parambr, ap paramap, bu parambu, boolean paramBoolean)
  {
    a("/appEvent", new ao(paramap));
    a("/canOpenURLs", aq.lW);
    a("/click", aq.lX);
    a("/close", aq.lY);
    a("/customClose", aq.lZ);
    a("/httpTrack", aq.ma);
    a("/log", aq.mb);
    a("/open", aq.mc);
    a("/touch", aq.md);
    a("/video", aq.me);
    this.qg = paramu;
    this.qh = parambr;
    this.lV = paramap;
    this.ql = parambu;
    o(paramBoolean);
  }
  
  public final void a(String paramString, ar paramar)
  {
    this.qf.put(paramString, paramar);
  }
  
  public final void a(boolean paramBoolean, int paramInt)
  {
    if ((this.ng.be()) && (!this.ng.Q().lo)) {}
    for (u localu = null;; localu = this.qg)
    {
      a(new bq(localu, this.qh, this.ql, this.ng, paramBoolean, paramInt, this.ng.bd()));
      return;
    }
  }
  
  public final void a(boolean paramBoolean, int paramInt, String paramString)
  {
    br localbr = null;
    boolean bool = this.ng.be();
    u localu;
    if ((bool) && (!this.ng.Q().lo))
    {
      localu = null;
      if (!bool) {
        break label85;
      }
    }
    for (;;)
    {
      a(new bq(localu, localbr, this.lV, this.ql, this.ng, paramBoolean, paramInt, paramString, this.ng.bd()));
      return;
      localu = this.qg;
      break;
      label85:
      localbr = this.qh;
    }
  }
  
  public final void a(boolean paramBoolean, int paramInt, String paramString1, String paramString2)
  {
    br localbr = null;
    boolean bool = this.ng.be();
    u localu;
    if ((bool) && (!this.ng.Q().lo))
    {
      localu = null;
      if (!bool) {
        break label87;
      }
    }
    for (;;)
    {
      a(new bq(localu, localbr, this.lV, this.ql, this.ng, paramBoolean, paramInt, paramString1, paramString2, this.ng.bd()));
      return;
      localu = this.qg;
      break;
      label87:
      localbr = this.qh;
    }
  }
  
  public final void ar()
  {
    synchronized (this.mg)
    {
      this.qj = false;
      this.qk = true;
      final bo localbo = this.ng.ba();
      if (localbo != null)
      {
        if (!cz.aX()) {
          cz.pT.post(new Runnable()
          {
            public void run()
            {
              localbo.ar();
            }
          });
        }
      }
      else {
        return;
      }
      localbo.ar();
    }
  }
  
  public boolean bi()
  {
    synchronized (this.mg)
    {
      boolean bool = this.qk;
      return bool;
    }
  }
  
  public final void o(boolean paramBoolean)
  {
    this.qj = paramBoolean;
  }
  
  public final void onPageFinished(WebView paramWebView, String paramString)
  {
    if (this.qi != null)
    {
      this.qi.a(this.ng);
      this.qi = null;
    }
  }
  
  public final void reset()
  {
    synchronized (this.mg)
    {
      this.qf.clear();
      this.qg = null;
      this.qh = null;
      this.qi = null;
      this.lV = null;
      this.qj = false;
      this.qk = false;
      this.ql = null;
      return;
    }
  }
  
  public final boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    da.v("AdWebView shouldOverrideUrlLoading: " + paramString);
    Uri localUri = Uri.parse(paramString);
    if (("gmsg".equalsIgnoreCase(localUri.getScheme())) && ("mobileads.google.com".equalsIgnoreCase(localUri.getHost()))) {
      c(localUri);
    }
    for (;;)
    {
      return true;
      if ((this.qj) && (paramWebView == this.ng) && (b(localUri))) {
        return super.shouldOverrideUrlLoading(paramWebView, paramString);
      }
      if (!this.ng.willNotDraw())
      {
        try
        {
          l locall = this.ng.bc();
          paramWebView = localUri;
          if (locall != null)
          {
            paramWebView = localUri;
            if (locall.a(localUri)) {
              paramWebView = locall.a(localUri, this.ng.getContext());
            }
          }
        }
        catch (m paramWebView)
        {
          for (;;)
          {
            da.w("Unable to append parameter to URL: " + paramString);
            paramWebView = localUri;
          }
        }
        a(new bn("android.intent.action.VIEW", paramWebView.toString(), null, null, null, null, null));
      }
      else
      {
        da.w("AdWebView unable to handle URL: " + paramString);
      }
    }
  }
  
  public static abstract interface a
  {
    public abstract void a(dd paramdd);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\de.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */