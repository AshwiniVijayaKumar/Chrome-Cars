package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.c.i;
import com.google.android.gms.internal.d.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class ct
{
  private static final by<d.a> WH = new by(di.ku(), true);
  private final DataLayer TN;
  private final cr.c WI;
  private final ag WJ;
  private final Map<String, aj> WK;
  private final Map<String, aj> WL;
  private final Map<String, aj> WM;
  private final k<cr.a, by<d.a>> WN;
  private final k<String, b> WO;
  private final Set<cr.e> WP;
  private final Map<String, c> WQ;
  private volatile String WR;
  private int WS;
  
  public ct(Context paramContext, cr.c paramc, DataLayer paramDataLayer, s.a parama1, s.a parama2, ag paramag)
  {
    if (paramc == null) {
      throw new NullPointerException("resource cannot be null");
    }
    this.WI = paramc;
    this.WP = new HashSet(paramc.jJ());
    this.TN = paramDataLayer;
    this.WJ = paramag;
    paramc = new l.a()
    {
      public int a(cr.a paramAnonymousa, by<d.a> paramAnonymousby)
      {
        return ((d.a)paramAnonymousby.getObject()).eW();
      }
    };
    this.WN = new l().a(1048576, paramc);
    paramc = new l.a()
    {
      public int a(String paramAnonymousString, ct.b paramAnonymousb)
      {
        return paramAnonymousString.length() + paramAnonymousb.getSize();
      }
    };
    this.WO = new l().a(1048576, paramc);
    this.WK = new HashMap();
    b(new i(paramContext));
    b(new s(parama2));
    b(new w(paramDataLayer));
    b(new dj(paramContext, paramDataLayer));
    this.WL = new HashMap();
    c(new q());
    c(new ad());
    c(new ae());
    c(new al());
    c(new am());
    c(new bd());
    c(new be());
    c(new ci());
    c(new dc());
    this.WM = new HashMap();
    a(new b(paramContext));
    a(new c(paramContext));
    a(new e(paramContext));
    a(new f(paramContext));
    a(new g(paramContext));
    a(new h(paramContext));
    a(new m());
    a(new p(this.WI.getVersion()));
    a(new s(parama1));
    a(new u(paramDataLayer));
    a(new z(paramContext));
    a(new aa());
    a(new ac());
    a(new ah(this));
    a(new an());
    a(new ao());
    a(new ax(paramContext));
    a(new az());
    a(new bc());
    a(new bk(paramContext));
    a(new bz());
    a(new cc());
    a(new cf());
    a(new ch());
    a(new cj(paramContext));
    a(new cu());
    a(new cv());
    a(new de());
    this.WQ = new HashMap();
    paramDataLayer = this.WP.iterator();
    while (paramDataLayer.hasNext())
    {
      parama1 = (cr.e)paramDataLayer.next();
      if (paramag.jb())
      {
        a(parama1.jR(), parama1.jS(), "add macro");
        a(parama1.jW(), parama1.jT(), "remove macro");
        a(parama1.jP(), parama1.jU(), "add tag");
        a(parama1.jQ(), parama1.jV(), "remove tag");
      }
      int i = 0;
      while (i < parama1.jR().size())
      {
        parama2 = (cr.a)parama1.jR().get(i);
        paramc = "Unknown";
        paramContext = paramc;
        if (paramag.jb())
        {
          paramContext = paramc;
          if (i < parama1.jS().size()) {
            paramContext = (String)parama1.jS().get(i);
          }
        }
        paramc = c(this.WQ, h(parama2));
        paramc.b(parama1);
        paramc.a(parama1, parama2);
        paramc.a(parama1, paramContext);
        i += 1;
      }
      i = 0;
      while (i < parama1.jW().size())
      {
        parama2 = (cr.a)parama1.jW().get(i);
        paramc = "Unknown";
        paramContext = paramc;
        if (paramag.jb())
        {
          paramContext = paramc;
          if (i < parama1.jT().size()) {
            paramContext = (String)parama1.jT().get(i);
          }
        }
        paramc = c(this.WQ, h(parama2));
        paramc.b(parama1);
        paramc.b(parama1, parama2);
        paramc.b(parama1, paramContext);
        i += 1;
      }
    }
    paramContext = this.WI.jK().entrySet().iterator();
    while (paramContext.hasNext())
    {
      paramc = (Map.Entry)paramContext.next();
      paramDataLayer = ((List)paramc.getValue()).iterator();
      while (paramDataLayer.hasNext())
      {
        parama1 = (cr.a)paramDataLayer.next();
        if (!di.n((d.a)parama1.jF().get(com.google.android.gms.internal.b.ds.toString())).booleanValue()) {
          c(this.WQ, (String)paramc.getKey()).i(parama1);
        }
      }
    }
  }
  
  private by<d.a> a(d.a parama, Set<String> paramSet, dk paramdk)
  {
    if (!parama.gi) {
      return new by(parama, true);
    }
    by localby1;
    switch (parama.type)
    {
    case 5: 
    case 6: 
    default: 
      bh.t("Unknown type: " + parama.type);
      return WH;
    case 2: 
      locala = cr.g(parama);
      locala.fZ = new d.a[parama.fZ.length];
      i = 0;
      while (i < parama.fZ.length)
      {
        localby1 = a(parama.fZ[i], paramSet, paramdk.bS(i));
        if (localby1 == WH) {
          return WH;
        }
        locala.fZ[i] = ((d.a)localby1.getObject());
        i += 1;
      }
      return new by(locala, false);
    case 3: 
      locala = cr.g(parama);
      if (parama.ga.length != parama.gb.length)
      {
        bh.t("Invalid serving value: " + parama.toString());
        return WH;
      }
      locala.ga = new d.a[parama.ga.length];
      locala.gb = new d.a[parama.ga.length];
      i = 0;
      while (i < parama.ga.length)
      {
        localby1 = a(parama.ga[i], paramSet, paramdk.bT(i));
        by localby2 = a(parama.gb[i], paramSet, paramdk.bU(i));
        if ((localby1 == WH) || (localby2 == WH)) {
          return WH;
        }
        locala.ga[i] = ((d.a)localby1.getObject());
        locala.gb[i] = ((d.a)localby2.getObject());
        i += 1;
      }
      return new by(locala, false);
    case 4: 
      if (paramSet.contains(parama.gc))
      {
        bh.t("Macro cycle detected.  Current macro reference: " + parama.gc + "." + "  Previous macro references: " + paramSet.toString() + ".");
        return WH;
      }
      paramSet.add(parama.gc);
      paramdk = dl.a(a(parama.gc, paramSet, paramdk.jq()), parama.gh);
      paramSet.remove(parama.gc);
      return paramdk;
    }
    d.a locala = cr.g(parama);
    locala.gg = new d.a[parama.gg.length];
    int i = 0;
    while (i < parama.gg.length)
    {
      localby1 = a(parama.gg[i], paramSet, paramdk.bV(i));
      if (localby1 == WH) {
        return WH;
      }
      locala.gg[i] = ((d.a)localby1.getObject());
      i += 1;
    }
    return new by(locala, false);
  }
  
  private by<d.a> a(String paramString, Set<String> paramSet, bj parambj)
  {
    this.WS += 1;
    Object localObject = (b)this.WO.get(paramString);
    if ((localObject != null) && (!this.WJ.jb()))
    {
      a(((b)localObject).jG(), paramSet);
      this.WS -= 1;
      return ((b)localObject).ka();
    }
    localObject = (c)this.WQ.get(paramString);
    if (localObject == null)
    {
      bh.t(jZ() + "Invalid macro: " + paramString);
      this.WS -= 1;
      return WH;
    }
    by localby = a(paramString, ((c)localObject).kb(), ((c)localObject).kc(), ((c)localObject).kd(), ((c)localObject).kf(), ((c)localObject).ke(), paramSet, parambj.iS());
    if (((Set)localby.getObject()).isEmpty()) {}
    for (localObject = ((c)localObject).kg(); localObject == null; localObject = (cr.a)((Set)localby.getObject()).iterator().next())
    {
      this.WS -= 1;
      return WH;
      if (((Set)localby.getObject()).size() > 1) {
        bh.w(jZ() + "Multiple macros active for macroName " + paramString);
      }
    }
    parambj = a(this.WM, (cr.a)localObject, paramSet, parambj.jh());
    boolean bool;
    if ((localby.jr()) && (parambj.jr()))
    {
      bool = true;
      if (parambj != WH) {
        break label392;
      }
    }
    label392:
    for (parambj = WH;; parambj = new by(parambj.getObject(), bool))
    {
      localObject = ((cr.a)localObject).jG();
      if (parambj.jr()) {
        this.WO.e(paramString, new b(parambj, (d.a)localObject));
      }
      a((d.a)localObject, paramSet);
      this.WS -= 1;
      return parambj;
      bool = false;
      break;
    }
  }
  
  private by<d.a> a(Map<String, aj> paramMap, cr.a parama, Set<String> paramSet, ck paramck)
  {
    boolean bool = true;
    Object localObject1 = (d.a)parama.jF().get(com.google.android.gms.internal.b.cI.toString());
    if (localObject1 == null)
    {
      bh.t("No function id in properties");
      paramMap = WH;
    }
    aj localaj;
    do
    {
      return paramMap;
      localObject1 = ((d.a)localObject1).gd;
      localaj = (aj)paramMap.get(localObject1);
      if (localaj == null)
      {
        bh.t((String)localObject1 + " has no backing implementation.");
        return WH;
      }
      paramMap = (by)this.WN.get(parama);
    } while ((paramMap != null) && (!this.WJ.jb()));
    paramMap = new HashMap();
    Iterator localIterator = parama.jF().entrySet().iterator();
    int i = 1;
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject2 = paramck.bs((String)localEntry.getKey());
      localObject2 = a((d.a)localEntry.getValue(), paramSet, ((cm)localObject2).e((d.a)localEntry.getValue()));
      if (localObject2 == WH) {
        return WH;
      }
      if (((by)localObject2).jr()) {
        parama.a((String)localEntry.getKey(), (d.a)((by)localObject2).getObject());
      }
      for (;;)
      {
        paramMap.put(localEntry.getKey(), ((by)localObject2).getObject());
        break;
        i = 0;
      }
    }
    if (!localaj.a(paramMap.keySet()))
    {
      bh.t("Incorrect keys for function " + (String)localObject1 + " required " + localaj.jd() + " had " + paramMap.keySet());
      return WH;
    }
    if ((i != 0) && (localaj.iy())) {}
    for (;;)
    {
      paramMap = new by(localaj.u(paramMap), bool);
      if (bool) {
        this.WN.e(parama, paramMap);
      }
      paramck.d((d.a)paramMap.getObject());
      return paramMap;
      bool = false;
    }
  }
  
  private by<Set<cr.a>> a(Set<cr.e> paramSet, Set<String> paramSet1, a parama, cs paramcs)
  {
    HashSet localHashSet1 = new HashSet();
    HashSet localHashSet2 = new HashSet();
    paramSet = paramSet.iterator();
    boolean bool = true;
    if (paramSet.hasNext())
    {
      cr.e locale = (cr.e)paramSet.next();
      cn localcn = paramcs.jp();
      by localby = a(locale, paramSet1, localcn);
      if (((Boolean)localby.getObject()).booleanValue()) {
        parama.a(locale, localHashSet1, localHashSet2, localcn);
      }
      if ((bool) && (localby.jr())) {}
      for (bool = true;; bool = false) {
        break;
      }
    }
    localHashSet1.removeAll(localHashSet2);
    paramcs.b(localHashSet1);
    return new by(localHashSet1, bool);
  }
  
  private void a(d.a parama, Set<String> paramSet)
  {
    if (parama == null) {}
    for (;;)
    {
      return;
      parama = a(parama, paramSet, new bw());
      if (parama != WH)
      {
        parama = di.o((d.a)parama.getObject());
        if ((parama instanceof Map))
        {
          parama = (Map)parama;
          this.TN.push(parama);
          return;
        }
        if (!(parama instanceof List)) {
          break;
        }
        parama = ((List)parama).iterator();
        while (parama.hasNext())
        {
          paramSet = parama.next();
          if ((paramSet instanceof Map))
          {
            paramSet = (Map)paramSet;
            this.TN.push(paramSet);
          }
          else
          {
            bh.w("pushAfterEvaluate: value not a Map");
          }
        }
      }
    }
    bh.w("pushAfterEvaluate: value not a Map or List");
  }
  
  private static void a(List<cr.a> paramList, List<String> paramList1, String paramString)
  {
    if (paramList.size() != paramList1.size()) {
      bh.u("Invalid resource: imbalance of rule names of functions for " + paramString + " operation. Using default rule name instead");
    }
  }
  
  private static void a(Map<String, aj> paramMap, aj paramaj)
  {
    if (paramMap.containsKey(paramaj.jc())) {
      throw new IllegalArgumentException("Duplicate function type name: " + paramaj.jc());
    }
    paramMap.put(paramaj.jc(), paramaj);
  }
  
  private static c c(Map<String, c> paramMap, String paramString)
  {
    c localc2 = (c)paramMap.get(paramString);
    c localc1 = localc2;
    if (localc2 == null)
    {
      localc1 = new c();
      paramMap.put(paramString, localc1);
    }
    return localc1;
  }
  
  private static String h(cr.a parama)
  {
    return di.j((d.a)parama.jF().get(com.google.android.gms.internal.b.cT.toString()));
  }
  
  private String jZ()
  {
    if (this.WS <= 1) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Integer.toString(this.WS));
    int i = 2;
    while (i < this.WS)
    {
      localStringBuilder.append(' ');
      i += 1;
    }
    localStringBuilder.append(": ");
    return localStringBuilder.toString();
  }
  
  by<Boolean> a(cr.a parama, Set<String> paramSet, ck paramck)
  {
    parama = a(this.WL, parama, paramSet, paramck);
    paramSet = di.n((d.a)parama.getObject());
    paramck.d(di.r(paramSet));
    return new by(paramSet, parama.jr());
  }
  
  by<Boolean> a(cr.e parame, Set<String> paramSet, cn paramcn)
  {
    Object localObject = parame.jO().iterator();
    boolean bool = true;
    if (((Iterator)localObject).hasNext())
    {
      by localby = a((cr.a)((Iterator)localObject).next(), paramSet, paramcn.jj());
      if (((Boolean)localby.getObject()).booleanValue())
      {
        paramcn.f(di.r(Boolean.valueOf(false)));
        return new by(Boolean.valueOf(false), localby.jr());
      }
      if ((bool) && (localby.jr())) {}
      for (bool = true;; bool = false) {
        break;
      }
    }
    parame = parame.jN().iterator();
    while (parame.hasNext())
    {
      localObject = a((cr.a)parame.next(), paramSet, paramcn.jk());
      if (!((Boolean)((by)localObject).getObject()).booleanValue())
      {
        paramcn.f(di.r(Boolean.valueOf(false)));
        return new by(Boolean.valueOf(false), ((by)localObject).jr());
      }
      if ((bool) && (((by)localObject).jr())) {
        bool = true;
      } else {
        bool = false;
      }
    }
    paramcn.f(di.r(Boolean.valueOf(true)));
    return new by(Boolean.valueOf(true), bool);
  }
  
  by<Set<cr.a>> a(String paramString, Set<cr.e> paramSet, final Map<cr.e, List<cr.a>> paramMap1, final Map<cr.e, List<String>> paramMap2, final Map<cr.e, List<cr.a>> paramMap3, final Map<cr.e, List<String>> paramMap4, Set<String> paramSet1, cs paramcs)
  {
    a(paramSet, paramSet1, new a()
    {
      public void a(cr.e paramAnonymouse, Set<cr.a> paramAnonymousSet1, Set<cr.a> paramAnonymousSet2, cn paramAnonymouscn)
      {
        List localList1 = (List)paramMap1.get(paramAnonymouse);
        List localList2 = (List)paramMap2.get(paramAnonymouse);
        if (localList1 != null)
        {
          paramAnonymousSet1.addAll(localList1);
          paramAnonymouscn.jl().b(localList1, localList2);
        }
        paramAnonymousSet1 = (List)paramMap3.get(paramAnonymouse);
        paramAnonymouse = (List)paramMap4.get(paramAnonymouse);
        if (paramAnonymousSet1 != null)
        {
          paramAnonymousSet2.addAll(paramAnonymousSet1);
          paramAnonymouscn.jm().b(paramAnonymousSet1, paramAnonymouse);
        }
      }
    }, paramcs);
  }
  
  by<Set<cr.a>> a(Set<cr.e> paramSet, cs paramcs)
  {
    a(paramSet, new HashSet(), new a()
    {
      public void a(cr.e paramAnonymouse, Set<cr.a> paramAnonymousSet1, Set<cr.a> paramAnonymousSet2, cn paramAnonymouscn)
      {
        paramAnonymousSet1.addAll(paramAnonymouse.jP());
        paramAnonymousSet2.addAll(paramAnonymouse.jQ());
        paramAnonymouscn.jn().b(paramAnonymouse.jP(), paramAnonymouse.jU());
        paramAnonymouscn.jo().b(paramAnonymouse.jQ(), paramAnonymouse.jV());
      }
    }, paramcs);
  }
  
  void a(aj paramaj)
  {
    a(this.WM, paramaj);
  }
  
  void b(aj paramaj)
  {
    a(this.WK, paramaj);
  }
  
  public by<d.a> bC(String paramString)
  {
    this.WS = 0;
    af localaf = this.WJ.bl(paramString);
    paramString = a(paramString, new HashSet(), localaf.iY());
    localaf.ja();
    return paramString;
  }
  
  void bD(String paramString)
  {
    try
    {
      this.WR = paramString;
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void ba(String paramString)
  {
    try
    {
      bD(paramString);
      paramString = this.WJ.bm(paramString);
      t localt = paramString.iZ();
      Iterator localIterator = ((Set)a(this.WP, localt.iS()).getObject()).iterator();
      while (localIterator.hasNext())
      {
        cr.a locala = (cr.a)localIterator.next();
        a(this.WK, locala, new HashSet(), localt.iR());
      }
      paramString.ja();
    }
    finally {}
    bD(null);
  }
  
  void c(aj paramaj)
  {
    a(this.WL, paramaj);
  }
  
  public void f(List<c.i> paramList)
  {
    for (;;)
    {
      try
      {
        paramList = paramList.iterator();
        if (!paramList.hasNext()) {
          break;
        }
        c.i locali = (c.i)paramList.next();
        if ((locali.name == null) || (!locali.name.startsWith("gaExperiment:"))) {
          bh.v("Ignored supplemental: " + locali);
        } else {
          ai.a(this.TN, locali);
        }
      }
      finally {}
    }
  }
  
  String jY()
  {
    try
    {
      String str = this.WR;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  static abstract interface a
  {
    public abstract void a(cr.e parame, Set<cr.a> paramSet1, Set<cr.a> paramSet2, cn paramcn);
  }
  
  private static class b
  {
    private by<d.a> WY;
    private d.a Wt;
    
    public b(by<d.a> paramby, d.a parama)
    {
      this.WY = paramby;
      this.Wt = parama;
    }
    
    public int getSize()
    {
      int j = ((d.a)this.WY.getObject()).eW();
      if (this.Wt == null) {}
      for (int i = 0;; i = this.Wt.eW()) {
        return i + j;
      }
    }
    
    public d.a jG()
    {
      return this.Wt;
    }
    
    public by<d.a> ka()
    {
      return this.WY;
    }
  }
  
  private static class c
  {
    private final Set<cr.e> WP = new HashSet();
    private final Map<cr.e, List<cr.a>> WZ = new HashMap();
    private final Map<cr.e, List<cr.a>> Xa = new HashMap();
    private final Map<cr.e, List<String>> Xb = new HashMap();
    private final Map<cr.e, List<String>> Xc = new HashMap();
    private cr.a Xd;
    
    public void a(cr.e parame, cr.a parama)
    {
      List localList = (List)this.WZ.get(parame);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList();
        this.WZ.put(parame, localObject);
      }
      ((List)localObject).add(parama);
    }
    
    public void a(cr.e parame, String paramString)
    {
      List localList = (List)this.Xb.get(parame);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList();
        this.Xb.put(parame, localObject);
      }
      ((List)localObject).add(paramString);
    }
    
    public void b(cr.e parame)
    {
      this.WP.add(parame);
    }
    
    public void b(cr.e parame, cr.a parama)
    {
      List localList = (List)this.Xa.get(parame);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList();
        this.Xa.put(parame, localObject);
      }
      ((List)localObject).add(parama);
    }
    
    public void b(cr.e parame, String paramString)
    {
      List localList = (List)this.Xc.get(parame);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList();
        this.Xc.put(parame, localObject);
      }
      ((List)localObject).add(paramString);
    }
    
    public void i(cr.a parama)
    {
      this.Xd = parama;
    }
    
    public Set<cr.e> kb()
    {
      return this.WP;
    }
    
    public Map<cr.e, List<cr.a>> kc()
    {
      return this.WZ;
    }
    
    public Map<cr.e, List<String>> kd()
    {
      return this.Xb;
    }
    
    public Map<cr.e, List<String>> ke()
    {
      return this.Xc;
    }
    
    public Map<cr.e, List<cr.a>> kf()
    {
      return this.Xa;
    }
    
    public cr.a kg()
    {
      return this.Xd;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\tagmanager\ct.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */