package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class fh
  extends fb
  implements SafeParcelable
{
  public static final fi CREATOR = new fi();
  private final fe CC;
  private final Parcel CJ;
  private final int CK;
  private int CL;
  private int CM;
  private final String mClassName;
  private final int wj;
  
  fh(int paramInt, Parcel paramParcel, fe paramfe)
  {
    this.wj = paramInt;
    this.CJ = ((Parcel)er.f(paramParcel));
    this.CK = 2;
    this.CC = paramfe;
    if (this.CC == null) {}
    for (this.mClassName = null;; this.mClassName = this.CC.eD())
    {
      this.CL = 2;
      return;
    }
  }
  
  private fh(SafeParcelable paramSafeParcelable, fe paramfe, String paramString)
  {
    this.wj = 1;
    this.CJ = Parcel.obtain();
    paramSafeParcelable.writeToParcel(this.CJ, 0);
    this.CK = 1;
    this.CC = ((fe)er.f(paramfe));
    this.mClassName = ((String)er.f(paramString));
    this.CL = 2;
  }
  
  public static <T extends fb,  extends SafeParcelable> fh a(T paramT)
  {
    String str = paramT.getClass().getCanonicalName();
    fe localfe = b(paramT);
    return new fh((SafeParcelable)paramT, localfe, str);
  }
  
  private static void a(fe paramfe, fb paramfb)
  {
    Object localObject = paramfb.getClass();
    if (!paramfe.b((Class)localObject))
    {
      HashMap localHashMap = paramfb.en();
      paramfe.a((Class)localObject, paramfb.en());
      localObject = localHashMap.keySet().iterator();
      while (((Iterator)localObject).hasNext())
      {
        paramfb = (fb.a)localHashMap.get((String)((Iterator)localObject).next());
        Class localClass = paramfb.ev();
        if (localClass != null) {
          try
          {
            a(paramfe, (fb)localClass.newInstance());
          }
          catch (InstantiationException paramfe)
          {
            throw new IllegalStateException("Could not instantiate an object of type " + paramfb.ev().getCanonicalName(), paramfe);
          }
          catch (IllegalAccessException paramfe)
          {
            throw new IllegalStateException("Could not access object of type " + paramfb.ev().getCanonicalName(), paramfe);
          }
        }
      }
    }
  }
  
  private void a(StringBuilder paramStringBuilder, int paramInt, Object paramObject)
  {
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException("Unknown type = " + paramInt);
    case 0: 
    case 1: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
      paramStringBuilder.append(paramObject);
      return;
    case 7: 
      paramStringBuilder.append("\"").append(fp.ap(paramObject.toString())).append("\"");
      return;
    case 8: 
      paramStringBuilder.append("\"").append(fk.d((byte[])paramObject)).append("\"");
      return;
    case 9: 
      paramStringBuilder.append("\"").append(fk.e((byte[])paramObject));
      paramStringBuilder.append("\"");
      return;
    case 10: 
      fq.a(paramStringBuilder, (HashMap)paramObject);
      return;
    }
    throw new IllegalArgumentException("Method does not accept concrete type.");
  }
  
  private void a(StringBuilder paramStringBuilder, fb.a<?, ?> parama, Parcel paramParcel, int paramInt)
  {
    switch (parama.em())
    {
    default: 
      throw new IllegalArgumentException("Unknown field out type = " + parama.em());
    case 0: 
      b(paramStringBuilder, parama, a(parama, Integer.valueOf(a.g(paramParcel, paramInt))));
      return;
    case 1: 
      b(paramStringBuilder, parama, a(parama, a.i(paramParcel, paramInt)));
      return;
    case 2: 
      b(paramStringBuilder, parama, a(parama, Long.valueOf(a.h(paramParcel, paramInt))));
      return;
    case 3: 
      b(paramStringBuilder, parama, a(parama, Float.valueOf(a.j(paramParcel, paramInt))));
      return;
    case 4: 
      b(paramStringBuilder, parama, a(parama, Double.valueOf(a.k(paramParcel, paramInt))));
      return;
    case 5: 
      b(paramStringBuilder, parama, a(parama, a.l(paramParcel, paramInt)));
      return;
    case 6: 
      b(paramStringBuilder, parama, a(parama, Boolean.valueOf(a.c(paramParcel, paramInt))));
      return;
    case 7: 
      b(paramStringBuilder, parama, a(parama, a.m(paramParcel, paramInt)));
      return;
    case 8: 
    case 9: 
      b(paramStringBuilder, parama, a(parama, a.p(paramParcel, paramInt)));
      return;
    case 10: 
      b(paramStringBuilder, parama, a(parama, c(a.o(paramParcel, paramInt))));
      return;
    }
    throw new IllegalArgumentException("Method does not accept concrete type.");
  }
  
  private void a(StringBuilder paramStringBuilder, String paramString, fb.a<?, ?> parama, Parcel paramParcel, int paramInt)
  {
    paramStringBuilder.append("\"").append(paramString).append("\":");
    if (parama.ex())
    {
      a(paramStringBuilder, parama, paramParcel, paramInt);
      return;
    }
    b(paramStringBuilder, parama, paramParcel, paramInt);
  }
  
  private void a(StringBuilder paramStringBuilder, HashMap<String, fb.a<?, ?>> paramHashMap, Parcel paramParcel)
  {
    paramHashMap = c(paramHashMap);
    paramStringBuilder.append('{');
    int j = a.o(paramParcel);
    int i = 0;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.n(paramParcel);
      Map.Entry localEntry = (Map.Entry)paramHashMap.get(Integer.valueOf(a.S(k)));
      if (localEntry != null)
      {
        if (i != 0) {
          paramStringBuilder.append(",");
        }
        a(paramStringBuilder, (String)localEntry.getKey(), (fb.a)localEntry.getValue(), paramParcel, k);
        i = 1;
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    paramStringBuilder.append('}');
  }
  
  private static fe b(fb paramfb)
  {
    fe localfe = new fe(paramfb.getClass());
    a(localfe, paramfb);
    localfe.eB();
    localfe.eA();
    return localfe;
  }
  
  private void b(StringBuilder paramStringBuilder, fb.a<?, ?> parama, Parcel paramParcel, int paramInt)
  {
    if (parama.es())
    {
      paramStringBuilder.append("[");
      switch (parama.em())
      {
      default: 
        throw new IllegalStateException("Unknown field type out.");
      case 0: 
        fj.a(paramStringBuilder, a.r(paramParcel, paramInt));
      }
      for (;;)
      {
        paramStringBuilder.append("]");
        return;
        fj.a(paramStringBuilder, a.t(paramParcel, paramInt));
        continue;
        fj.a(paramStringBuilder, a.s(paramParcel, paramInt));
        continue;
        fj.a(paramStringBuilder, a.u(paramParcel, paramInt));
        continue;
        fj.a(paramStringBuilder, a.v(paramParcel, paramInt));
        continue;
        fj.a(paramStringBuilder, a.w(paramParcel, paramInt));
        continue;
        fj.a(paramStringBuilder, a.q(paramParcel, paramInt));
        continue;
        fj.a(paramStringBuilder, a.x(paramParcel, paramInt));
        continue;
        throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
        paramParcel = a.A(paramParcel, paramInt);
        int i = paramParcel.length;
        paramInt = 0;
        while (paramInt < i)
        {
          if (paramInt > 0) {
            paramStringBuilder.append(",");
          }
          paramParcel[paramInt].setDataPosition(0);
          a(paramStringBuilder, parama.ez(), paramParcel[paramInt]);
          paramInt += 1;
        }
      }
    }
    switch (parama.em())
    {
    default: 
      throw new IllegalStateException("Unknown field type out");
    case 0: 
      paramStringBuilder.append(a.g(paramParcel, paramInt));
      return;
    case 1: 
      paramStringBuilder.append(a.i(paramParcel, paramInt));
      return;
    case 2: 
      paramStringBuilder.append(a.h(paramParcel, paramInt));
      return;
    case 3: 
      paramStringBuilder.append(a.j(paramParcel, paramInt));
      return;
    case 4: 
      paramStringBuilder.append(a.k(paramParcel, paramInt));
      return;
    case 5: 
      paramStringBuilder.append(a.l(paramParcel, paramInt));
      return;
    case 6: 
      paramStringBuilder.append(a.c(paramParcel, paramInt));
      return;
    case 7: 
      parama = a.m(paramParcel, paramInt);
      paramStringBuilder.append("\"").append(fp.ap(parama)).append("\"");
      return;
    case 8: 
      parama = a.p(paramParcel, paramInt);
      paramStringBuilder.append("\"").append(fk.d(parama)).append("\"");
      return;
    case 9: 
      parama = a.p(paramParcel, paramInt);
      paramStringBuilder.append("\"").append(fk.e(parama));
      paramStringBuilder.append("\"");
      return;
    case 10: 
      parama = a.o(paramParcel, paramInt);
      paramParcel = parama.keySet();
      paramParcel.size();
      paramStringBuilder.append("{");
      paramParcel = paramParcel.iterator();
      for (paramInt = 1; paramParcel.hasNext(); paramInt = 0)
      {
        String str = (String)paramParcel.next();
        if (paramInt == 0) {
          paramStringBuilder.append(",");
        }
        paramStringBuilder.append("\"").append(str).append("\"");
        paramStringBuilder.append(":");
        paramStringBuilder.append("\"").append(fp.ap(parama.getString(str))).append("\"");
      }
      paramStringBuilder.append("}");
      return;
    }
    paramParcel = a.z(paramParcel, paramInt);
    paramParcel.setDataPosition(0);
    a(paramStringBuilder, parama.ez(), paramParcel);
  }
  
  private void b(StringBuilder paramStringBuilder, fb.a<?, ?> parama, Object paramObject)
  {
    if (parama.er())
    {
      b(paramStringBuilder, parama, (ArrayList)paramObject);
      return;
    }
    a(paramStringBuilder, parama.el(), paramObject);
  }
  
  private void b(StringBuilder paramStringBuilder, fb.a<?, ?> parama, ArrayList<?> paramArrayList)
  {
    paramStringBuilder.append("[");
    int j = paramArrayList.size();
    int i = 0;
    while (i < j)
    {
      if (i != 0) {
        paramStringBuilder.append(",");
      }
      a(paramStringBuilder, parama.el(), paramArrayList.get(i));
      i += 1;
    }
    paramStringBuilder.append("]");
  }
  
  public static HashMap<String, String> c(Bundle paramBundle)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localHashMap.put(str, paramBundle.getString(str));
    }
    return localHashMap;
  }
  
  private static HashMap<Integer, Map.Entry<String, fb.a<?, ?>>> c(HashMap<String, fb.a<?, ?>> paramHashMap)
  {
    HashMap localHashMap = new HashMap();
    paramHashMap = paramHashMap.entrySet().iterator();
    while (paramHashMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramHashMap.next();
      localHashMap.put(Integer.valueOf(((fb.a)localEntry.getValue()).eu()), localEntry);
    }
    return localHashMap;
  }
  
  protected Object ak(String paramString)
  {
    throw new UnsupportedOperationException("Converting to JSON does not require this method.");
  }
  
  protected boolean al(String paramString)
  {
    throw new UnsupportedOperationException("Converting to JSON does not require this method.");
  }
  
  public int describeContents()
  {
    fi localfi = CREATOR;
    return 0;
  }
  
  public Parcel eF()
  {
    switch (this.CL)
    {
    }
    for (;;)
    {
      return this.CJ;
      this.CM = b.p(this.CJ);
      b.D(this.CJ, this.CM);
      this.CL = 2;
      continue;
      b.D(this.CJ, this.CM);
      this.CL = 2;
    }
  }
  
  fe eG()
  {
    switch (this.CK)
    {
    default: 
      throw new IllegalStateException("Invalid creation type: " + this.CK);
    case 0: 
      return null;
    case 1: 
      return this.CC;
    }
    return this.CC;
  }
  
  public HashMap<String, fb.a<?, ?>> en()
  {
    if (this.CC == null) {
      return null;
    }
    return this.CC.ao(this.mClassName);
  }
  
  public int getVersionCode()
  {
    return this.wj;
  }
  
  public String toString()
  {
    er.b(this.CC, "Cannot convert to JSON on client side.");
    Parcel localParcel = eF();
    localParcel.setDataPosition(0);
    StringBuilder localStringBuilder = new StringBuilder(100);
    a(localStringBuilder, this.CC.ao(this.mClassName), localParcel);
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    fi localfi = CREATOR;
    fi.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\fh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */