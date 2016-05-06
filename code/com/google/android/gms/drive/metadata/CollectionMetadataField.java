package com.google.android.gms.drive.metadata;

import com.google.android.gms.common.data.DataHolder;
import java.util.Collection;

public abstract class CollectionMetadataField<T>
  extends MetadataField<Collection<T>>
{
  protected CollectionMetadataField(String paramString, int paramInt)
  {
    super(paramString, paramInt);
  }
  
  protected Collection<T> a(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    throw new UnsupportedOperationException("Cannot read collections from a dataHolder.");
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\drive\metadata\CollectionMetadataField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */