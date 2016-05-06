package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;

public final class cn
{
  public final int errorCode;
  public final ax mM;
  public final bg mN;
  public final String mO;
  public final ba mP;
  public final List<String> mt;
  public final List<String> mu;
  public final long mx;
  public final dd nu;
  public final z oc;
  public final String of;
  public final long oj;
  public final boolean ok;
  public final long ol;
  public final List<String> om;
  public final int orientation;
  public final ay pf;
  public final ab pg;
  public final long ph;
  public final long pi;
  
  public cn(z paramz, dd paramdd, List<String> paramList1, int paramInt1, List<String> paramList2, List<String> paramList3, int paramInt2, long paramLong1, String paramString1, boolean paramBoolean, ax paramax, bg parambg, String paramString2, ay paramay, ba paramba, long paramLong2, ab paramab, long paramLong3, long paramLong4, long paramLong5)
  {
    this.oc = paramz;
    this.nu = paramdd;
    if (paramList1 != null)
    {
      paramz = Collections.unmodifiableList(paramList1);
      this.mt = paramz;
      this.errorCode = paramInt1;
      if (paramList2 == null) {
        break label156;
      }
      paramz = Collections.unmodifiableList(paramList2);
      label45:
      this.mu = paramz;
      if (paramList3 == null) {
        break label161;
      }
    }
    label156:
    label161:
    for (paramz = Collections.unmodifiableList(paramList3);; paramz = null)
    {
      this.om = paramz;
      this.orientation = paramInt2;
      this.mx = paramLong1;
      this.of = paramString1;
      this.ok = paramBoolean;
      this.mM = paramax;
      this.mN = parambg;
      this.mO = paramString2;
      this.pf = paramay;
      this.mP = paramba;
      this.ol = paramLong2;
      this.pg = paramab;
      this.oj = paramLong3;
      this.ph = paramLong4;
      this.pi = paramLong5;
      return;
      paramz = null;
      break;
      paramz = null;
      break label45;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\cn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */