package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.c.f;
import com.google.android.gms.internal.c.i;
import com.google.android.gms.internal.c.j;
import com.google.android.gms.internal.d.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Container
{
  private final String TM;
  private final DataLayer TN;
  private ct TO;
  private Map<String, FunctionCallMacroCallback> TP = new HashMap();
  private Map<String, FunctionCallTagCallback> TQ = new HashMap();
  private volatile long TR;
  private volatile String TS = "";
  private final Context mContext;
  
  Container(Context paramContext, DataLayer paramDataLayer, String paramString, long paramLong, c.j paramj)
  {
    this.mContext = paramContext;
    this.TN = paramDataLayer;
    this.TM = paramString;
    this.TR = paramLong;
    a(paramj.fV);
    if (paramj.fU != null) {
      a(paramj.fU);
    }
  }
  
  Container(Context paramContext, DataLayer paramDataLayer, String paramString, long paramLong, cr.c paramc)
  {
    this.mContext = paramContext;
    this.TN = paramDataLayer;
    this.TM = paramString;
    this.TR = paramLong;
    a(paramc);
  }
  
  private void a(c.f paramf)
  {
    if (paramf == null) {
      throw new NullPointerException();
    }
    try
    {
      cr.c localc = cr.b(paramf);
      a(localc);
      return;
    }
    catch (cr.g localg)
    {
      bh.t("Not loading resource: " + paramf + " because it is invalid: " + localg.toString());
    }
  }
  
  private void a(cr.c paramc)
  {
    this.TS = paramc.getVersion();
    ag localag = bb(this.TS);
    a(new ct(this.mContext, paramc, this.TN, new a(null), new b(null), localag));
  }
  
  private void a(ct paramct)
  {
    try
    {
      this.TO = paramct;
      return;
    }
    finally
    {
      paramct = finally;
      throw paramct;
    }
  }
  
  private void a(c.i[] paramArrayOfi)
  {
    ArrayList localArrayList = new ArrayList();
    int j = paramArrayOfi.length;
    int i = 0;
    while (i < j)
    {
      localArrayList.add(paramArrayOfi[i]);
      i += 1;
    }
    iE().f(localArrayList);
  }
  
  private ct iE()
  {
    try
    {
      ct localct = this.TO;
      return localct;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  FunctionCallMacroCallback aY(String paramString)
  {
    synchronized (this.TP)
    {
      paramString = (FunctionCallMacroCallback)this.TP.get(paramString);
      return paramString;
    }
  }
  
  FunctionCallTagCallback aZ(String paramString)
  {
    synchronized (this.TQ)
    {
      paramString = (FunctionCallTagCallback)this.TQ.get(paramString);
      return paramString;
    }
  }
  
  void ba(String paramString)
  {
    iE().ba(paramString);
  }
  
  ag bb(String paramString)
  {
    if (ce.ju().jv().equals(ce.a.VY)) {}
    return new bq();
  }
  
  public boolean getBoolean(String paramString)
  {
    ct localct = iE();
    if (localct == null)
    {
      bh.t("getBoolean called for closed container.");
      return di.kr().booleanValue();
    }
    try
    {
      boolean bool = di.n((d.a)localct.bC(paramString).getObject()).booleanValue();
      return bool;
    }
    catch (Exception paramString)
    {
      bh.t("Calling getBoolean() threw an exception: " + paramString.getMessage() + " Returning default value.");
    }
    return di.kr().booleanValue();
  }
  
  public String getContainerId()
  {
    return this.TM;
  }
  
  public double getDouble(String paramString)
  {
    ct localct = iE();
    if (localct == null)
    {
      bh.t("getDouble called for closed container.");
      return di.kq().doubleValue();
    }
    try
    {
      double d = di.m((d.a)localct.bC(paramString).getObject()).doubleValue();
      return d;
    }
    catch (Exception paramString)
    {
      bh.t("Calling getDouble() threw an exception: " + paramString.getMessage() + " Returning default value.");
    }
    return di.kq().doubleValue();
  }
  
  public long getLastRefreshTime()
  {
    return this.TR;
  }
  
  public long getLong(String paramString)
  {
    ct localct = iE();
    if (localct == null)
    {
      bh.t("getLong called for closed container.");
      return di.kp().longValue();
    }
    try
    {
      long l = di.l((d.a)localct.bC(paramString).getObject()).longValue();
      return l;
    }
    catch (Exception paramString)
    {
      bh.t("Calling getLong() threw an exception: " + paramString.getMessage() + " Returning default value.");
    }
    return di.kp().longValue();
  }
  
  public String getString(String paramString)
  {
    ct localct = iE();
    if (localct == null)
    {
      bh.t("getString called for closed container.");
      return di.kt();
    }
    try
    {
      paramString = di.j((d.a)localct.bC(paramString).getObject());
      return paramString;
    }
    catch (Exception paramString)
    {
      bh.t("Calling getString() threw an exception: " + paramString.getMessage() + " Returning default value.");
    }
    return di.kt();
  }
  
  String iD()
  {
    return this.TS;
  }
  
  public boolean isDefault()
  {
    return getLastRefreshTime() == 0L;
  }
  
  public void registerFunctionCallMacroCallback(String paramString, FunctionCallMacroCallback paramFunctionCallMacroCallback)
  {
    if (paramFunctionCallMacroCallback == null) {
      throw new NullPointerException("Macro handler must be non-null");
    }
    synchronized (this.TP)
    {
      this.TP.put(paramString, paramFunctionCallMacroCallback);
      return;
    }
  }
  
  public void registerFunctionCallTagCallback(String paramString, FunctionCallTagCallback paramFunctionCallTagCallback)
  {
    if (paramFunctionCallTagCallback == null) {
      throw new NullPointerException("Tag callback must be non-null");
    }
    synchronized (this.TQ)
    {
      this.TQ.put(paramString, paramFunctionCallTagCallback);
      return;
    }
  }
  
  void release()
  {
    this.TO = null;
  }
  
  public void unregisterFunctionCallMacroCallback(String paramString)
  {
    synchronized (this.TP)
    {
      this.TP.remove(paramString);
      return;
    }
  }
  
  public void unregisterFunctionCallTagCallback(String paramString)
  {
    synchronized (this.TQ)
    {
      this.TQ.remove(paramString);
      return;
    }
  }
  
  public static abstract interface FunctionCallMacroCallback
  {
    public abstract Object getValue(String paramString, Map<String, Object> paramMap);
  }
  
  public static abstract interface FunctionCallTagCallback
  {
    public abstract void execute(String paramString, Map<String, Object> paramMap);
  }
  
  private class a
    implements s.a
  {
    private a() {}
    
    public Object b(String paramString, Map<String, Object> paramMap)
    {
      Container.FunctionCallMacroCallback localFunctionCallMacroCallback = Container.this.aY(paramString);
      if (localFunctionCallMacroCallback == null) {
        return null;
      }
      return localFunctionCallMacroCallback.getValue(paramString, paramMap);
    }
  }
  
  private class b
    implements s.a
  {
    private b() {}
    
    public Object b(String paramString, Map<String, Object> paramMap)
    {
      Container.FunctionCallTagCallback localFunctionCallTagCallback = Container.this.aZ(paramString);
      if (localFunctionCallTagCallback != null) {
        localFunctionCallTagCallback.execute(paramString, paramMap);
      }
      return di.kt();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\Container.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */