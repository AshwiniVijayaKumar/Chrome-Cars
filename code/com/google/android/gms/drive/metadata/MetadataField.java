package com.google.android.gms.drive.metadata;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.er;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class MetadataField<T>
{
  private final String Eg;
  private final Set<String> Eh;
  private final int Ei;
  
  protected MetadataField(String paramString, int paramInt)
  {
    this.Eg = ((String)er.b(paramString, "fieldName"));
    this.Eh = Collections.singleton(paramString);
    this.Ei = paramInt;
  }
  
  protected MetadataField(String paramString, Collection<String> paramCollection, int paramInt)
  {
    this.Eg = ((String)er.b(paramString, "fieldName"));
    this.Eh = Collections.unmodifiableSet(new HashSet(paramCollection));
    this.Ei = paramInt;
  }
  
  protected abstract void a(Bundle paramBundle, T paramT);
  
  public final void a(DataHolder paramDataHolder, MetadataBundle paramMetadataBundle, int paramInt1, int paramInt2)
  {
    er.b(paramDataHolder, "dataHolder");
    er.b(paramMetadataBundle, "bundle");
    paramMetadataBundle.b(this, c(paramDataHolder, paramInt1, paramInt2));
  }
  
  public final void a(T paramT, Bundle paramBundle)
  {
    er.b(paramBundle, "bundle");
    if (paramT == null)
    {
      paramBundle.putString(getName(), null);
      return;
    }
    a(paramBundle, paramT);
  }
  
  protected abstract T b(DataHolder paramDataHolder, int paramInt1, int paramInt2);
  
  public final T c(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    Iterator localIterator = this.Eh.iterator();
    while (localIterator.hasNext()) {
      if (paramDataHolder.hasNull((String)localIterator.next(), paramInt1, paramInt2)) {
        return null;
      }
    }
    return (T)b(paramDataHolder, paramInt1, paramInt2);
  }
  
  public final T d(Bundle paramBundle)
  {
    er.b(paramBundle, "bundle");
    if (paramBundle.get(getName()) != null) {
      return (T)e(paramBundle);
    }
    return null;
  }
  
  protected abstract T e(Bundle paramBundle);
  
  public final Collection<String> ff()
  {
    return this.Eh;
  }
  
  public final String getName()
  {
    return this.Eg;
  }
  
  public final int getVersionAdded()
  {
    return this.Ei;
  }
  
  public String toString()
  {
    return this.Eg;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\metadata\MetadataField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */