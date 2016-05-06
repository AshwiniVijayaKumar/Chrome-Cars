package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class fb
{
  private void a(StringBuilder paramStringBuilder, a parama, Object paramObject)
  {
    if (parama.el() == 11)
    {
      paramStringBuilder.append(((fb)parama.ev().cast(paramObject)).toString());
      return;
    }
    if (parama.el() == 7)
    {
      paramStringBuilder.append("\"");
      paramStringBuilder.append(fp.ap((String)paramObject));
      paramStringBuilder.append("\"");
      return;
    }
    paramStringBuilder.append(paramObject);
  }
  
  private void a(StringBuilder paramStringBuilder, a parama, ArrayList<Object> paramArrayList)
  {
    paramStringBuilder.append("[");
    int i = 0;
    int j = paramArrayList.size();
    while (i < j)
    {
      if (i > 0) {
        paramStringBuilder.append(",");
      }
      Object localObject = paramArrayList.get(i);
      if (localObject != null) {
        a(paramStringBuilder, parama, localObject);
      }
      i += 1;
    }
    paramStringBuilder.append("]");
  }
  
  protected <O, I> I a(a<I, O> parama, Object paramObject)
  {
    Object localObject = paramObject;
    if (a.c(parama) != null) {
      localObject = parama.g(paramObject);
    }
    return (I)localObject;
  }
  
  protected boolean a(a parama)
  {
    if (parama.em() == 11)
    {
      if (parama.es()) {
        return an(parama.et());
      }
      return am(parama.et());
    }
    return al(parama.et());
  }
  
  protected abstract Object ak(String paramString);
  
  protected abstract boolean al(String paramString);
  
  protected boolean am(String paramString)
  {
    throw new UnsupportedOperationException("Concrete types not supported");
  }
  
  protected boolean an(String paramString)
  {
    throw new UnsupportedOperationException("Concrete type arrays not supported");
  }
  
  protected Object b(a parama)
  {
    boolean bool = true;
    String str = parama.et();
    if (parama.ev() != null)
    {
      if (ak(parama.et()) == null)
      {
        er.a(bool, "Concrete field shouldn't be value object: " + parama.et());
        if (!parama.es()) {
          break label80;
        }
      }
      label80:
      for (parama = ep();; parama = eo())
      {
        if (parama == null) {
          break label88;
        }
        return parama.get(str);
        bool = false;
        break;
      }
      try
      {
        label88:
        parama = "get" + Character.toUpperCase(str.charAt(0)) + str.substring(1);
        parama = getClass().getMethod(parama, new Class[0]).invoke(this, new Object[0]);
        return parama;
      }
      catch (Exception parama)
      {
        throw new RuntimeException(parama);
      }
    }
    return ak(parama.et());
  }
  
  public abstract HashMap<String, a<?, ?>> en();
  
  public HashMap<String, Object> eo()
  {
    return null;
  }
  
  public HashMap<String, Object> ep()
  {
    return null;
  }
  
  public String toString()
  {
    HashMap localHashMap = en();
    StringBuilder localStringBuilder = new StringBuilder(100);
    Iterator localIterator = localHashMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      a locala = (a)localHashMap.get(str);
      if (a(locala))
      {
        Object localObject = a(locala, b(locala));
        if (localStringBuilder.length() == 0) {
          localStringBuilder.append("{");
        }
        for (;;)
        {
          localStringBuilder.append("\"").append(str).append("\":");
          if (localObject != null) {
            break label135;
          }
          localStringBuilder.append("null");
          break;
          localStringBuilder.append(",");
        }
        label135:
        switch (locala.em())
        {
        default: 
          if (locala.er()) {
            a(localStringBuilder, locala, (ArrayList)localObject);
          }
          break;
        case 8: 
          localStringBuilder.append("\"").append(fk.d((byte[])localObject)).append("\"");
          break;
        case 9: 
          localStringBuilder.append("\"").append(fk.e((byte[])localObject)).append("\"");
          break;
        case 10: 
          fq.a(localStringBuilder, (HashMap)localObject);
          continue;
          a(localStringBuilder, locala, localObject);
        }
      }
    }
    if (localStringBuilder.length() > 0) {
      localStringBuilder.append("}");
    }
    for (;;)
    {
      return localStringBuilder.toString();
      localStringBuilder.append("{}");
    }
  }
  
  public static class a<I, O>
    implements SafeParcelable
  {
    public static final fc CREATOR = new fc();
    protected final Class<? extends fb> CA;
    protected final String CB;
    private fe CC;
    private fb.b<I, O> CD;
    protected final int Cu;
    protected final boolean Cv;
    protected final int Cw;
    protected final boolean Cx;
    protected final String Cy;
    protected final int Cz;
    private final int wj;
    
    a(int paramInt1, int paramInt2, boolean paramBoolean1, int paramInt3, boolean paramBoolean2, String paramString1, int paramInt4, String paramString2, ew paramew)
    {
      this.wj = paramInt1;
      this.Cu = paramInt2;
      this.Cv = paramBoolean1;
      this.Cw = paramInt3;
      this.Cx = paramBoolean2;
      this.Cy = paramString1;
      this.Cz = paramInt4;
      if (paramString2 == null) {
        this.CA = null;
      }
      for (this.CB = null; paramew == null; this.CB = paramString2)
      {
        this.CD = null;
        return;
        this.CA = fh.class;
      }
      this.CD = paramew.ej();
    }
    
    protected a(int paramInt1, boolean paramBoolean1, int paramInt2, boolean paramBoolean2, String paramString, int paramInt3, Class<? extends fb> paramClass, fb.b<I, O> paramb)
    {
      this.wj = 1;
      this.Cu = paramInt1;
      this.Cv = paramBoolean1;
      this.Cw = paramInt2;
      this.Cx = paramBoolean2;
      this.Cy = paramString;
      this.Cz = paramInt3;
      this.CA = paramClass;
      if (paramClass == null) {}
      for (this.CB = null;; this.CB = paramClass.getCanonicalName())
      {
        this.CD = paramb;
        return;
      }
    }
    
    public static a a(String paramString, int paramInt, fb.b<?, ?> paramb, boolean paramBoolean)
    {
      return new a(paramb.el(), paramBoolean, paramb.em(), false, paramString, paramInt, null, paramb);
    }
    
    public static <T extends fb> a<T, T> a(String paramString, int paramInt, Class<T> paramClass)
    {
      return new a(11, false, 11, false, paramString, paramInt, paramClass, null);
    }
    
    public static <T extends fb> a<ArrayList<T>, ArrayList<T>> b(String paramString, int paramInt, Class<T> paramClass)
    {
      return new a(11, true, 11, true, paramString, paramInt, paramClass, null);
    }
    
    public static a<Integer, Integer> g(String paramString, int paramInt)
    {
      return new a(0, false, 0, false, paramString, paramInt, null, null);
    }
    
    public static a<Double, Double> h(String paramString, int paramInt)
    {
      return new a(4, false, 4, false, paramString, paramInt, null, null);
    }
    
    public static a<Boolean, Boolean> i(String paramString, int paramInt)
    {
      return new a(6, false, 6, false, paramString, paramInt, null, null);
    }
    
    public static a<String, String> j(String paramString, int paramInt)
    {
      return new a(7, false, 7, false, paramString, paramInt, null, null);
    }
    
    public static a<ArrayList<String>, ArrayList<String>> k(String paramString, int paramInt)
    {
      return new a(7, true, 7, true, paramString, paramInt, null, null);
    }
    
    public void a(fe paramfe)
    {
      this.CC = paramfe;
    }
    
    public int describeContents()
    {
      fc localfc = CREATOR;
      return 0;
    }
    
    public int el()
    {
      return this.Cu;
    }
    
    public int em()
    {
      return this.Cw;
    }
    
    public a<I, O> eq()
    {
      return new a(this.wj, this.Cu, this.Cv, this.Cw, this.Cx, this.Cy, this.Cz, this.CB, ey());
    }
    
    public boolean er()
    {
      return this.Cv;
    }
    
    public boolean es()
    {
      return this.Cx;
    }
    
    public String et()
    {
      return this.Cy;
    }
    
    public int eu()
    {
      return this.Cz;
    }
    
    public Class<? extends fb> ev()
    {
      return this.CA;
    }
    
    String ew()
    {
      if (this.CB == null) {
        return null;
      }
      return this.CB;
    }
    
    public boolean ex()
    {
      return this.CD != null;
    }
    
    ew ey()
    {
      if (this.CD == null) {
        return null;
      }
      return ew.a(this.CD);
    }
    
    public HashMap<String, a<?, ?>> ez()
    {
      er.f(this.CB);
      er.f(this.CC);
      return this.CC.ao(this.CB);
    }
    
    public I g(O paramO)
    {
      return (I)this.CD.g(paramO);
    }
    
    public int getVersionCode()
    {
      return this.wj;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append("Field\n");
      localStringBuilder1.append("            versionCode=").append(this.wj).append('\n');
      localStringBuilder1.append("                 typeIn=").append(this.Cu).append('\n');
      localStringBuilder1.append("            typeInArray=").append(this.Cv).append('\n');
      localStringBuilder1.append("                typeOut=").append(this.Cw).append('\n');
      localStringBuilder1.append("           typeOutArray=").append(this.Cx).append('\n');
      localStringBuilder1.append("        outputFieldName=").append(this.Cy).append('\n');
      localStringBuilder1.append("      safeParcelFieldId=").append(this.Cz).append('\n');
      localStringBuilder1.append("       concreteTypeName=").append(ew()).append('\n');
      if (ev() != null) {
        localStringBuilder1.append("     concreteType.class=").append(ev().getCanonicalName()).append('\n');
      }
      StringBuilder localStringBuilder2 = localStringBuilder1.append("          converterName=");
      if (this.CD == null) {}
      for (String str = "null";; str = this.CD.getClass().getCanonicalName())
      {
        localStringBuilder2.append(str).append('\n');
        return localStringBuilder1.toString();
      }
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      fc localfc = CREATOR;
      fc.a(this, paramParcel, paramInt);
    }
  }
  
  public static abstract interface b<I, O>
  {
    public abstract int el();
    
    public abstract int em();
    
    public abstract I g(O paramO);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\fb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */