package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.b;
import com.google.android.gms.internal.c.b;
import com.google.android.gms.internal.c.e;
import com.google.android.gms.internal.c.f;
import com.google.android.gms.internal.c.g;
import com.google.android.gms.internal.c.h;
import com.google.android.gms.internal.d.a;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class cr
{
  private static d.a a(int paramInt, c.f paramf, d.a[] paramArrayOfa, Set<Integer> paramSet)
    throws cr.g
  {
    int k = 0;
    int m = 0;
    int j = 0;
    if (paramSet.contains(Integer.valueOf(paramInt))) {
      bw("Value cycle detected.  Current value reference: " + paramInt + "." + "  Previous value references: " + paramSet + ".");
    }
    d.a locala1 = (d.a)a(paramf.fi, paramInt, "values");
    if (paramArrayOfa[paramInt] != null) {
      return paramArrayOfa[paramInt];
    }
    Object localObject = null;
    paramSet.add(Integer.valueOf(paramInt));
    switch (locala1.type)
    {
    }
    for (;;)
    {
      if (localObject == null) {
        bw("Invalid value: " + locala1);
      }
      paramArrayOfa[paramInt] = localObject;
      paramSet.remove(Integer.valueOf(paramInt));
      return (d.a)localObject;
      localObject = h(locala1);
      d.a locala2 = g(locala1);
      locala2.fZ = new d.a[((c.h)localObject).fK.length];
      int[] arrayOfInt = ((c.h)localObject).fK;
      k = arrayOfInt.length;
      int i = 0;
      for (;;)
      {
        localObject = locala2;
        if (j >= k) {
          break;
        }
        m = arrayOfInt[j];
        locala2.fZ[i] = a(m, paramf, paramArrayOfa, paramSet);
        j += 1;
        i += 1;
      }
      locala2 = g(locala1);
      localObject = h(locala1);
      if (((c.h)localObject).fL.length != ((c.h)localObject).fM.length) {
        bw("Uneven map keys (" + ((c.h)localObject).fL.length + ") and map values (" + ((c.h)localObject).fM.length + ")");
      }
      locala2.ga = new d.a[((c.h)localObject).fL.length];
      locala2.gb = new d.a[((c.h)localObject).fL.length];
      arrayOfInt = ((c.h)localObject).fL;
      m = arrayOfInt.length;
      j = 0;
      i = 0;
      while (j < m)
      {
        int n = arrayOfInt[j];
        locala2.ga[i] = a(n, paramf, paramArrayOfa, paramSet);
        j += 1;
        i += 1;
      }
      arrayOfInt = ((c.h)localObject).fM;
      m = arrayOfInt.length;
      i = 0;
      j = k;
      for (;;)
      {
        localObject = locala2;
        if (j >= m) {
          break;
        }
        k = arrayOfInt[j];
        locala2.gb[i] = a(k, paramf, paramArrayOfa, paramSet);
        j += 1;
        i += 1;
      }
      localObject = g(locala1);
      ((d.a)localObject).gc = di.j(a(h(locala1).fP, paramf, paramArrayOfa, paramSet));
      continue;
      locala2 = g(locala1);
      localObject = h(locala1);
      locala2.gg = new d.a[((c.h)localObject).fO.length];
      arrayOfInt = ((c.h)localObject).fO;
      k = arrayOfInt.length;
      i = 0;
      j = m;
      for (;;)
      {
        localObject = locala2;
        if (j >= k) {
          break;
        }
        m = arrayOfInt[j];
        locala2.gg[i] = a(m, paramf, paramArrayOfa, paramSet);
        j += 1;
        i += 1;
      }
      localObject = locala1;
    }
  }
  
  private static a a(c.b paramb, c.f paramf, d.a[] paramArrayOfa, int paramInt)
    throws cr.g
  {
    b localb = a.jE();
    paramb = paramb.eS;
    int i = paramb.length;
    paramInt = 0;
    if (paramInt < i)
    {
      int j = paramb[paramInt];
      Object localObject = (c.e)a(paramf.fj, Integer.valueOf(j).intValue(), "properties");
      String str = (String)a(paramf.fh, ((c.e)localObject).key, "keys");
      localObject = (d.a)a(paramArrayOfa, ((c.e)localObject).value, "values");
      if (b.dM.toString().equals(str)) {
        localb.i((d.a)localObject);
      }
      for (;;)
      {
        paramInt += 1;
        break;
        localb.b(str, (d.a)localObject);
      }
    }
    return localb.jH();
  }
  
  private static e a(c.g paramg, List<a> paramList1, List<a> paramList2, List<a> paramList3, c.f paramf)
  {
    f localf = e.jM();
    int[] arrayOfInt = paramg.fy;
    int j = arrayOfInt.length;
    int i = 0;
    while (i < j)
    {
      localf.b((a)paramList3.get(Integer.valueOf(arrayOfInt[i]).intValue()));
      i += 1;
    }
    arrayOfInt = paramg.fz;
    j = arrayOfInt.length;
    i = 0;
    while (i < j)
    {
      localf.c((a)paramList3.get(Integer.valueOf(arrayOfInt[i]).intValue()));
      i += 1;
    }
    paramList3 = paramg.fA;
    j = paramList3.length;
    i = 0;
    while (i < j)
    {
      localf.d((a)paramList1.get(Integer.valueOf(paramList3[i]).intValue()));
      i += 1;
    }
    paramList3 = paramg.fC;
    j = paramList3.length;
    i = 0;
    int k;
    while (i < j)
    {
      k = paramList3[i];
      localf.by(paramf.fi[Integer.valueOf(k).intValue()].fY);
      i += 1;
    }
    paramList3 = paramg.fB;
    j = paramList3.length;
    i = 0;
    while (i < j)
    {
      localf.e((a)paramList1.get(Integer.valueOf(paramList3[i]).intValue()));
      i += 1;
    }
    paramList1 = paramg.fD;
    j = paramList1.length;
    i = 0;
    while (i < j)
    {
      k = paramList1[i];
      localf.bz(paramf.fi[Integer.valueOf(k).intValue()].fY);
      i += 1;
    }
    paramList1 = paramg.fE;
    j = paramList1.length;
    i = 0;
    while (i < j)
    {
      localf.f((a)paramList2.get(Integer.valueOf(paramList1[i]).intValue()));
      i += 1;
    }
    paramList1 = paramg.fG;
    j = paramList1.length;
    i = 0;
    while (i < j)
    {
      k = paramList1[i];
      localf.bA(paramf.fi[Integer.valueOf(k).intValue()].fY);
      i += 1;
    }
    paramList1 = paramg.fF;
    j = paramList1.length;
    i = 0;
    while (i < j)
    {
      localf.g((a)paramList2.get(Integer.valueOf(paramList1[i]).intValue()));
      i += 1;
    }
    paramg = paramg.fH;
    j = paramg.length;
    i = 0;
    while (i < j)
    {
      k = paramg[i];
      localf.bB(paramf.fi[Integer.valueOf(k).intValue()].fY);
      i += 1;
    }
    return localf.jX();
  }
  
  private static <T> T a(T[] paramArrayOfT, int paramInt, String paramString)
    throws cr.g
  {
    if ((paramInt < 0) || (paramInt >= paramArrayOfT.length)) {
      bw("Index out of bounds detected: " + paramInt + " in " + paramString);
    }
    return paramArrayOfT[paramInt];
  }
  
  public static c b(c.f paramf)
    throws cr.g
  {
    int j = 0;
    Object localObject = new d.a[paramf.fi.length];
    int i = 0;
    while (i < paramf.fi.length)
    {
      a(i, paramf, (d.a[])localObject, new HashSet(0));
      i += 1;
    }
    d locald = c.jI();
    ArrayList localArrayList1 = new ArrayList();
    i = 0;
    while (i < paramf.fl.length)
    {
      localArrayList1.add(a(paramf.fl[i], paramf, (d.a[])localObject, i));
      i += 1;
    }
    ArrayList localArrayList2 = new ArrayList();
    i = 0;
    while (i < paramf.fm.length)
    {
      localArrayList2.add(a(paramf.fm[i], paramf, (d.a[])localObject, i));
      i += 1;
    }
    ArrayList localArrayList3 = new ArrayList();
    i = 0;
    while (i < paramf.fk.length)
    {
      a locala = a(paramf.fk[i], paramf, (d.a[])localObject, i);
      locald.a(locala);
      localArrayList3.add(locala);
      i += 1;
    }
    localObject = paramf.fn;
    int k = localObject.length;
    i = j;
    while (i < k)
    {
      locald.a(a(localObject[i], localArrayList1, localArrayList3, localArrayList2, paramf));
      i += 1;
    }
    locald.bx(paramf.fr);
    locald.bW(paramf.fw);
    return locald.jL();
  }
  
  public static void b(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        return;
      }
      paramOutputStream.write(arrayOfByte, 0, i);
    }
  }
  
  private static void bw(String paramString)
    throws cr.g
  {
    bh.t(paramString);
    throw new g(paramString);
  }
  
  public static d.a g(d.a parama)
  {
    d.a locala = new d.a();
    locala.type = parama.type;
    locala.gh = ((int[])parama.gh.clone());
    if (parama.gi) {
      locala.gi = parama.gi;
    }
    return locala;
  }
  
  private static c.h h(d.a parama)
    throws cr.g
  {
    if ((c.h)parama.a(c.h.fI) == null) {
      bw("Expected a ServingValue and didn't get one. Value is: " + parama);
    }
    return (c.h)parama.a(c.h.fI);
  }
  
  public static class a
  {
    private final Map<String, d.a> Ws;
    private final d.a Wt;
    
    private a(Map<String, d.a> paramMap, d.a parama)
    {
      this.Ws = paramMap;
      this.Wt = parama;
    }
    
    public static cr.b jE()
    {
      return new cr.b(null);
    }
    
    public void a(String paramString, d.a parama)
    {
      this.Ws.put(paramString, parama);
    }
    
    public Map<String, d.a> jF()
    {
      return Collections.unmodifiableMap(this.Ws);
    }
    
    public d.a jG()
    {
      return this.Wt;
    }
    
    public String toString()
    {
      return "Properties: " + jF() + " pushAfterEvaluate: " + this.Wt;
    }
  }
  
  public static class b
  {
    private final Map<String, d.a> Ws = new HashMap();
    private d.a Wt;
    
    public b b(String paramString, d.a parama)
    {
      this.Ws.put(paramString, parama);
      return this;
    }
    
    public b i(d.a parama)
    {
      this.Wt = parama;
      return this;
    }
    
    public cr.a jH()
    {
      return new cr.a(this.Ws, this.Wt, null);
    }
  }
  
  public static class c
  {
    private final String Un;
    private final List<cr.e> Wu;
    private final Map<String, List<cr.a>> Wv;
    private final int Ww;
    
    private c(List<cr.e> paramList, Map<String, List<cr.a>> paramMap, String paramString, int paramInt)
    {
      this.Wu = Collections.unmodifiableList(paramList);
      this.Wv = Collections.unmodifiableMap(paramMap);
      this.Un = paramString;
      this.Ww = paramInt;
    }
    
    public static cr.d jI()
    {
      return new cr.d(null);
    }
    
    public String getVersion()
    {
      return this.Un;
    }
    
    public List<cr.e> jJ()
    {
      return this.Wu;
    }
    
    public Map<String, List<cr.a>> jK()
    {
      return this.Wv;
    }
    
    public String toString()
    {
      return "Rules: " + jJ() + "  Macros: " + this.Wv;
    }
  }
  
  public static class d
  {
    private String Un = "";
    private final List<cr.e> Wu = new ArrayList();
    private final Map<String, List<cr.a>> Wv = new HashMap();
    private int Ww = 0;
    
    public d a(cr.a parama)
    {
      String str = di.j((d.a)parama.jF().get(b.cT.toString()));
      List localList = (List)this.Wv.get(str);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList();
        this.Wv.put(str, localObject);
      }
      ((List)localObject).add(parama);
      return this;
    }
    
    public d a(cr.e parame)
    {
      this.Wu.add(parame);
      return this;
    }
    
    public d bW(int paramInt)
    {
      this.Ww = paramInt;
      return this;
    }
    
    public d bx(String paramString)
    {
      this.Un = paramString;
      return this;
    }
    
    public cr.c jL()
    {
      return new cr.c(this.Wu, this.Wv, this.Un, this.Ww, null);
    }
  }
  
  public static class e
  {
    private final List<cr.a> WA;
    private final List<cr.a> WB;
    private final List<cr.a> WC;
    private final List<String> WD;
    private final List<String> WE;
    private final List<String> WF;
    private final List<String> WG;
    private final List<cr.a> Wx;
    private final List<cr.a> Wy;
    private final List<cr.a> Wz;
    
    private e(List<cr.a> paramList1, List<cr.a> paramList2, List<cr.a> paramList3, List<cr.a> paramList4, List<cr.a> paramList5, List<cr.a> paramList6, List<String> paramList7, List<String> paramList8, List<String> paramList9, List<String> paramList10)
    {
      this.Wx = Collections.unmodifiableList(paramList1);
      this.Wy = Collections.unmodifiableList(paramList2);
      this.Wz = Collections.unmodifiableList(paramList3);
      this.WA = Collections.unmodifiableList(paramList4);
      this.WB = Collections.unmodifiableList(paramList5);
      this.WC = Collections.unmodifiableList(paramList6);
      this.WD = Collections.unmodifiableList(paramList7);
      this.WE = Collections.unmodifiableList(paramList8);
      this.WF = Collections.unmodifiableList(paramList9);
      this.WG = Collections.unmodifiableList(paramList10);
    }
    
    public static cr.f jM()
    {
      return new cr.f(null);
    }
    
    public List<cr.a> jN()
    {
      return this.Wx;
    }
    
    public List<cr.a> jO()
    {
      return this.Wy;
    }
    
    public List<cr.a> jP()
    {
      return this.Wz;
    }
    
    public List<cr.a> jQ()
    {
      return this.WA;
    }
    
    public List<cr.a> jR()
    {
      return this.WB;
    }
    
    public List<String> jS()
    {
      return this.WD;
    }
    
    public List<String> jT()
    {
      return this.WE;
    }
    
    public List<String> jU()
    {
      return this.WF;
    }
    
    public List<String> jV()
    {
      return this.WG;
    }
    
    public List<cr.a> jW()
    {
      return this.WC;
    }
    
    public String toString()
    {
      return "Positive predicates: " + jN() + "  Negative predicates: " + jO() + "  Add tags: " + jP() + "  Remove tags: " + jQ() + "  Add macros: " + jR() + "  Remove macros: " + jW();
    }
  }
  
  public static class f
  {
    private final List<cr.a> WA = new ArrayList();
    private final List<cr.a> WB = new ArrayList();
    private final List<cr.a> WC = new ArrayList();
    private final List<String> WD = new ArrayList();
    private final List<String> WE = new ArrayList();
    private final List<String> WF = new ArrayList();
    private final List<String> WG = new ArrayList();
    private final List<cr.a> Wx = new ArrayList();
    private final List<cr.a> Wy = new ArrayList();
    private final List<cr.a> Wz = new ArrayList();
    
    public f b(cr.a parama)
    {
      this.Wx.add(parama);
      return this;
    }
    
    public f bA(String paramString)
    {
      this.WD.add(paramString);
      return this;
    }
    
    public f bB(String paramString)
    {
      this.WE.add(paramString);
      return this;
    }
    
    public f by(String paramString)
    {
      this.WF.add(paramString);
      return this;
    }
    
    public f bz(String paramString)
    {
      this.WG.add(paramString);
      return this;
    }
    
    public f c(cr.a parama)
    {
      this.Wy.add(parama);
      return this;
    }
    
    public f d(cr.a parama)
    {
      this.Wz.add(parama);
      return this;
    }
    
    public f e(cr.a parama)
    {
      this.WA.add(parama);
      return this;
    }
    
    public f f(cr.a parama)
    {
      this.WB.add(parama);
      return this;
    }
    
    public f g(cr.a parama)
    {
      this.WC.add(parama);
      return this;
    }
    
    public cr.e jX()
    {
      return new cr.e(this.Wx, this.Wy, this.Wz, this.WA, this.WB, this.WC, this.WD, this.WE, this.WF, this.WG, null);
    }
  }
  
  public static class g
    extends Exception
  {
    public g(String paramString)
    {
      super();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\cr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */