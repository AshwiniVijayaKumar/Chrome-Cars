package com.google.android.gms.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class dd
  extends WebView
  implements DownloadListener
{
  private ab mF;
  private final db mG;
  private final Object mg = new Object();
  private final l nP;
  private final de pY;
  private final a pZ;
  private bo qa;
  private boolean qb;
  private boolean qc;
  
  private dd(a parama, ab paramab, boolean paramBoolean1, boolean paramBoolean2, l paraml, db paramdb)
  {
    super(parama);
    this.pZ = parama;
    this.mF = paramab;
    this.qb = paramBoolean1;
    this.nP = paraml;
    this.mG = paramdb;
    setBackgroundColor(0);
    paramab = getSettings();
    paramab.setJavaScriptEnabled(true);
    paramab.setSavePassword(false);
    paramab.setSupportMultipleWindows(true);
    paramab.setJavaScriptCanOpenWindowsAutomatically(true);
    cv.a(parama, paramdb.pU, paramab);
    if (Build.VERSION.SDK_INT >= 17)
    {
      cx.a(getContext(), paramab);
      setDownloadListener(this);
      if (Build.VERSION.SDK_INT < 11) {
        break label178;
      }
      this.pY = new dg(this, paramBoolean2);
      label126:
      setWebViewClient(this.pY);
      if (Build.VERSION.SDK_INT < 14) {
        break label195;
      }
      setWebChromeClient(new dh(this));
    }
    for (;;)
    {
      bf();
      return;
      if (Build.VERSION.SDK_INT < 11) {
        break;
      }
      cw.a(getContext(), paramab);
      break;
      label178:
      this.pY = new de(this, paramBoolean2);
      break label126;
      label195:
      if (Build.VERSION.SDK_INT >= 11) {
        setWebChromeClient(new df(this));
      }
    }
  }
  
  public static dd a(Context paramContext, ab paramab, boolean paramBoolean1, boolean paramBoolean2, l paraml, db paramdb)
  {
    return new dd(new a(paramContext), paramab, paramBoolean1, paramBoolean2, paraml, paramdb);
  }
  
  private void bf()
  {
    for (;;)
    {
      synchronized (this.mg)
      {
        if ((this.qb) || (this.mF.lo))
        {
          if (Build.VERSION.SDK_INT < 14)
          {
            da.s("Disabling hardware acceleration on an overlay.");
            bg();
            return;
          }
          da.s("Enabling hardware acceleration on an overlay.");
          bh();
        }
      }
      if (Build.VERSION.SDK_INT < 18)
      {
        da.s("Disabling hardware acceleration on an AdView.");
        bg();
      }
      else
      {
        da.s("Enabling hardware acceleration on an AdView.");
        bh();
      }
    }
  }
  
  private void bg()
  {
    synchronized (this.mg)
    {
      if ((!this.qc) && (Build.VERSION.SDK_INT >= 11)) {
        cw.c(this);
      }
      this.qc = true;
      return;
    }
  }
  
  private void bh()
  {
    synchronized (this.mg)
    {
      if ((this.qc) && (Build.VERSION.SDK_INT >= 11)) {
        cw.d(this);
      }
      this.qc = false;
      return;
    }
  }
  
  public ab Q()
  {
    synchronized (this.mg)
    {
      ab localab = this.mF;
      return localab;
    }
  }
  
  public void a(Context paramContext, ab paramab)
  {
    synchronized (this.mg)
    {
      this.pZ.setBaseContext(paramContext);
      this.qa = null;
      this.mF = paramab;
      this.qb = false;
      cv.b(this);
      loadUrl("about:blank");
      this.pY.reset();
      return;
    }
  }
  
  public void a(ab paramab)
  {
    synchronized (this.mg)
    {
      this.mF = paramab;
      requestLayout();
      return;
    }
  }
  
  public void a(bo parambo)
  {
    synchronized (this.mg)
    {
      this.qa = parambo;
      return;
    }
  }
  
  public void a(String paramString, Map<String, ?> paramMap)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("javascript:AFMA_ReceiveMessage('");
    localStringBuilder.append(paramString);
    localStringBuilder.append("'");
    if (paramMap != null) {}
    try
    {
      paramString = cv.m(paramMap).toString();
      localStringBuilder.append(",");
      localStringBuilder.append(paramString);
      localStringBuilder.append(");");
      da.v("Dispatching AFMA event: " + localStringBuilder);
      loadUrl(localStringBuilder.toString());
      return;
    }
    catch (JSONException paramString)
    {
      da.w("Could not convert AFMA event parameters to JSON.");
    }
  }
  
  public void aY()
  {
    HashMap localHashMap = new HashMap(1);
    localHashMap.put("version", this.mG.pU);
    a("onhide", localHashMap);
  }
  
  public void aZ()
  {
    HashMap localHashMap = new HashMap(1);
    localHashMap.put("version", this.mG.pU);
    a("onshow", localHashMap);
  }
  
  public bo ba()
  {
    synchronized (this.mg)
    {
      bo localbo = this.qa;
      return localbo;
    }
  }
  
  public de bb()
  {
    return this.pY;
  }
  
  public l bc()
  {
    return this.nP;
  }
  
  public db bd()
  {
    return this.mG;
  }
  
  public boolean be()
  {
    synchronized (this.mg)
    {
      boolean bool = this.qb;
      return bool;
    }
  }
  
  public void n(boolean paramBoolean)
  {
    synchronized (this.mg)
    {
      this.qb = paramBoolean;
      bf();
      return;
    }
  }
  
  public void onDownloadStart(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
  {
    try
    {
      paramString2 = new Intent("android.intent.action.VIEW");
      paramString2.setDataAndType(Uri.parse(paramString1), paramString4);
      getContext().startActivity(paramString2);
      return;
    }
    catch (ActivityNotFoundException paramString2)
    {
      da.s("Couldn't find an Activity to view url/mimetype: " + paramString1 + " / " + paramString4);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int j = Integer.MAX_VALUE;
    for (;;)
    {
      int i;
      int m;
      int k;
      synchronized (this.mg)
      {
        if ((isInEditMode()) || (this.qb))
        {
          super.onMeasure(paramInt1, paramInt2);
          return;
        }
        int n = View.MeasureSpec.getMode(paramInt1);
        i = View.MeasureSpec.getSize(paramInt1);
        m = View.MeasureSpec.getMode(paramInt2);
        k = View.MeasureSpec.getSize(paramInt2);
        if (n == Integer.MIN_VALUE) {
          break label248;
        }
        if (n != 1073741824) {
          break label241;
        }
        break label248;
        if ((this.mF.widthPixels > paramInt1) || (this.mF.heightPixels > paramInt2))
        {
          da.w("Not enough space to show ad. Needs " + this.mF.widthPixels + "x" + this.mF.heightPixels + " pixels, but only has " + i + "x" + k + " pixels.");
          if (getVisibility() != 8) {
            setVisibility(4);
          }
          setMeasuredDimension(0, 0);
          return;
        }
      }
      if (getVisibility() != 8) {
        setVisibility(0);
      }
      setMeasuredDimension(this.mF.widthPixels, this.mF.heightPixels);
      continue;
      label241:
      paramInt1 = Integer.MAX_VALUE;
      break label250;
      label248:
      paramInt1 = i;
      label250:
      if (m != Integer.MIN_VALUE)
      {
        paramInt2 = j;
        if (m != 1073741824) {}
      }
      else
      {
        paramInt2 = k;
      }
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.nP != null) {
      this.nP.a(paramMotionEvent);
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public void setContext(Context paramContext)
  {
    this.pZ.setBaseContext(paramContext);
  }
  
  private static class a
    extends MutableContextWrapper
  {
    private Activity qd;
    private Context qe;
    
    public a(Context paramContext)
    {
      super();
      setBaseContext(paramContext);
    }
    
    public void setBaseContext(Context paramContext)
    {
      this.qe = paramContext.getApplicationContext();
      if ((paramContext instanceof Activity)) {}
      for (paramContext = (Activity)paramContext;; paramContext = null)
      {
        this.qd = paramContext;
        super.setBaseContext(this.qe);
        return;
      }
    }
    
    public void startActivity(Intent paramIntent)
    {
      if (this.qd != null)
      {
        this.qd.startActivity(paramIntent);
        return;
      }
      paramIntent.setFlags(268435456);
      this.qe.startActivity(paramIntent);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\dd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */