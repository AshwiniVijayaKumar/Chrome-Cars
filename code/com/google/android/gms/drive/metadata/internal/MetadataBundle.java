package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.er;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class MetadataBundle
  implements SafeParcelable
{
  public static final Parcelable.Creator<MetadataBundle> CREATOR = new f();
  final Bundle Ek;
  final int wj;
  
  MetadataBundle(int paramInt, Bundle paramBundle)
  {
    this.wj = paramInt;
    this.Ek = ((Bundle)er.f(paramBundle));
    this.Ek.setClassLoader(getClass().getClassLoader());
    paramBundle = new ArrayList();
    Object localObject = this.Ek.keySet().iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      if (c.ar(str) == null)
      {
        paramBundle.add(str);
        Log.w("MetadataBundle", "Ignored unknown metadata field in bundle: " + str);
      }
    }
    paramBundle = paramBundle.iterator();
    while (paramBundle.hasNext())
    {
      localObject = (String)paramBundle.next();
      this.Ek.remove((String)localObject);
    }
  }
  
  private MetadataBundle(Bundle paramBundle)
  {
    this(1, paramBundle);
  }
  
  public static <T> MetadataBundle a(MetadataField<T> paramMetadataField, T paramT)
  {
    MetadataBundle localMetadataBundle = fh();
    localMetadataBundle.b(paramMetadataField, paramT);
    return localMetadataBundle;
  }
  
  public static MetadataBundle a(MetadataBundle paramMetadataBundle)
  {
    return new MetadataBundle(new Bundle(paramMetadataBundle.Ek));
  }
  
  public static MetadataBundle fh()
  {
    return new MetadataBundle(new Bundle());
  }
  
  public <T> T a(MetadataField<T> paramMetadataField)
  {
    return (T)paramMetadataField.d(this.Ek);
  }
  
  public <T> void b(MetadataField<T> paramMetadataField, T paramT)
  {
    if (c.ar(paramMetadataField.getName()) == null) {
      throw new IllegalArgumentException("Unregistered field: " + paramMetadataField.getName());
    }
    paramMetadataField.a(paramT, this.Ek);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof MetadataBundle)) {
      return false;
    }
    paramObject = (MetadataBundle)paramObject;
    Object localObject = this.Ek.keySet();
    if (!localObject.equals(((MetadataBundle)paramObject).Ek.keySet())) {
      return false;
    }
    localObject = ((Set)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      if (!ep.equal(this.Ek.get(str), ((MetadataBundle)paramObject).Ek.get(str))) {
        return false;
      }
    }
    return true;
  }
  
  public Set<MetadataField<?>> fi()
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.Ek.keySet().iterator();
    while (localIterator.hasNext()) {
      localHashSet.add(c.ar((String)localIterator.next()));
    }
    return localHashSet;
  }
  
  public int hashCode()
  {
    Iterator localIterator = this.Ek.keySet().iterator();
    String str;
    for (int i = 1; localIterator.hasNext(); i = this.Ek.get(str).hashCode() + i * 31) {
      str = (String)localIterator.next();
    }
    return i;
  }
  
  public String toString()
  {
    return "MetadataBundle [values=" + this.Ek + "]";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    f.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\metadata\internal\MetadataBundle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */